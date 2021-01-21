package Controller;

import Model.GarbageCollector;
import Model.PrgState;
import Exception.MyException;
import Model.Value.Value;
import Repository.IRepo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Model.GarbageCollector.*;

public class Controller {
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo repo){
        this.repo = repo;
        executor = Executors.newFixedThreadPool(2);
    }

    public void addProgram(PrgState program_state){
        repo.add(program_state);
    }



    public void allStep() throws InterruptedException, FileNotFoundException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        GarbageCollector gc = new GarbageCollector();

        while(prgList.size() > 0){

            PrgState prg = prgList.get(0);
            prg.getHeap().setContent((HashMap<Integer, Value>) gc.safeGarbageCollector(
                    gc.getAddrFromSymTable(prgList),
                    prg.getHeap().getContent()));

            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);

    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }


    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                e.printStackTrace();
            }
        });
//RUN concurrently one step for each of the existing PrgStates
//-----------------------------------------------------------------------
//prepare the list of callables
        List<Callable<PrgState>> callList = new ArrayList<>();
        for (PrgState p : prgList) {
            Callable<PrgState> oneStep = p::oneStep;
            callList.add(oneStep);
        }

        List<PrgState> newPrgList = this.executor.invokeAll(callList). stream()
                . map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })

                .filter(Objects::nonNull).collect(Collectors.toList());
//add the new created threads to the list of existing threads
                    prgList.addAll(newPrgList);
//------------------------------------------------------------------------------
//after the execution, print the PrgState List into the log file
                    prgList.forEach(prg -> {
                        try {
                            repo.logPrgStateExec(prg);
                        } catch (MyException | IOException e) {
                            e.printStackTrace();
                        }
                    });
//Save the current programs in the repository
                    repo.setPrgList(prgList);
                }
}
