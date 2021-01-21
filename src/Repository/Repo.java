package Repository;
import Model.ADT.*;
import Model.PrgState;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import Exception.MyException;
import Model.Statements.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;


public class Repo implements IRepo {
    List<PrgState> RepoList;
    PrgState currentState;
    String logFilePath;

    public Repo(String _logFilePath){

        RepoList = new ArrayList<PrgState>();
        logFilePath = _logFilePath;

        try{
            PrintWriter writer = new PrintWriter(logFilePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void add(PrgState newState){
        RepoList.add(newState);
    }

    public void logPrgStateExec(PrgState state) throws MyException, IOException {

        currentState = state;


        if (currentState == null)
            throw new MyException ("Current State is null");

        MyIStack<IStmt> Stack = currentState.getStk();
        MyIDictionary<String, Value> SymTable = currentState.getSymTable();
        MyIList<Value> Out = currentState.getOut();
        MyIDictionary<StringValue, BufferedReader> FileTable = currentState.getFileTable();
        MyIHeap<Integer, Value> Heap = currentState.getHeap();


        try(PrintWriter printWriter = new PrintWriter(new FileWriter(logFilePath,true)))
        {
            printWriter.println("---> Id <---");
            printWriter.println(currentState.getId());
            printWriter.println("---> Stack <---");
            printWriter.println(Stack.toString());
            printWriter.println("---> SymTable <---");
            printWriter.println(SymTable.toString());
            printWriter.println("---> Out <---");
            printWriter.println(Out.toString());
            printWriter.println("---> FileTable <---");
            printWriter.println(FileTable.toString());
            printWriter.println("---> Heap <---");
            printWriter.println(Heap.toString());
            printWriter.println("\n* * * * * * * * * *\n");

        }
        catch (IOException e){
            throw new MyException ("IO Exception: " + e);
        }
    }


    public List<PrgState> getPrgList(){ return RepoList; }

    public void setPrgList(List<PrgState> RepoList){this.RepoList = RepoList;}



}
