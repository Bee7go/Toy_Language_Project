package Model.ADT;
import java.util.*;
import java.util.stream.Collectors;

import Exception.*;

public class MyList<T> implements MyIList<T>{
    List<T> list;

    public MyList(){
        list = new ArrayList<T>();
    }

    @Override
    public void add(T v) {
        list.add(v);

    }

    @Override
    public List<T> getList(){
        return list;
    }


    @Override
    public T remove() throws MyException {
        if (list.isEmpty())
            throw new MyException("List is empty! Can not remove elements anymore!");
        return list.remove(0);
    }

    @Override
    public String toString() {
        return list.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }


}
