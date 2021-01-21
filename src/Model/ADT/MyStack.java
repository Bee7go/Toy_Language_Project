package Model.ADT;
import java.util.*;
import java.util.stream.Collectors;

import Exception.*;
import Model.Statements.IStmt;

public class MyStack<T> implements MyIStack<T>{
    Stack<T> stack;

    public MyStack()
    {
        stack = new Stack<T>();
    }

    @Override
    public T pop() throws MyException{
        if (stack.empty())
            throw new MyException("Stack is empty! Can not remove elements anymore!");
        return stack.pop();
    }

    @Override
    public void push(T v){
        stack.push(v);
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public Stack<T> getContent() {
        return stack;
    }

    @Override
    public String toString() {
        return stack.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public T lastElement(){
        return stack.lastElement();
    }
}
