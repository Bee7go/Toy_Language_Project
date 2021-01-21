package Model.ADT;

import Exception.*;

import java.util.HashMap;

public interface MyIHeap<T1,T2>{

    public void add(T1 key, T2 value);
    public void update(T1 key, T2 new_value) throws MyException;
    public T2 lookup(T1 key) throws MyException;

    public boolean isDefined(T1 key);

    public void remove(T1 key) throws MyException;

    public HashMap<T1,T2> getContent();
    public void setContent(HashMap<T1,T2> x);
}
