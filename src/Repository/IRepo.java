package Repository;

import Model.ADT.MyList;
import Model.PrgState;

import Exception.MyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IRepo {

    public void add(PrgState newState);

    public List<PrgState> getPrgList();

    public void setPrgList(List<PrgState> state);

    public void logPrgStateExec(PrgState state) throws MyException, IOException;

}
