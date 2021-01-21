package Model.ADT;
import Exception.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public interface MyIDictionary<T1,T2>{

    public void add(T1 key, T2 value);
    public void update(T1 key, T2 new_value) throws MyException;
    public T2 lookup(T1 key) throws MyException;

    public boolean searchfor(T1 key);

    public boolean isDefined(T1 key);

    public void remove(T1 key) throws MyException;

    public List<T2> values();

    public MyIDictionary<T1,T2> deepCopy();

    public Hashtable<T1,T2> getContent();
}
