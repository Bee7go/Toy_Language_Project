package Model.ADT;
import Exception.*;

import java.util.List;

public interface MyIList<T>{

    public void add(T elem);
    public T remove() throws MyException;
    public List<T> getList();
}
