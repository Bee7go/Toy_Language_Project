package Model.ADT;
import Exception.*;

import java.util.Hashtable;
import java.util.Stack;

public interface MyIStack<T>{

    public T pop() throws MyException;
    public T lastElement();
    public void push(T v);
    boolean isEmpty();
    public Stack<T> getContent();

}
