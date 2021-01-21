package Model.ADT;

import java.util.*;
import java.util.stream.Collectors;

import Exception.*;

public class MyHeap<T1,T2> implements MyIHeap<T1,T2>{

    HashMap<T1,T2> hashMap;

    public MyHeap(){
        hashMap = new HashMap<T1,T2>();
    }

    @Override
    public void add(T1 key, T2 value) {
        hashMap.put(key, value);
    }

    @Override
    public void update(T1 key, T2 new_value) throws MyException {
        if(hashMap.get(key) == null)
            throw new MyException("Can't update! Address doesn't exists in the Heap!");

        hashMap.put(key, new_value);
    }

    @Override
    public T2 lookup(T1 key) throws MyException {
        if(hashMap.get(key) == null)
            throw new MyException("Address doesn't exists in the Heap!");
        return hashMap.get(key);
    }

    @Override
    public boolean isDefined(T1 key) {
        if(hashMap.get(key) == null)
            return false;
        return true;
    }

    @Override
    public void remove(T1 key) throws MyException {
        if(hashMap.get(key) == null)
            throw new MyException("Can't remove! Address doesn't exists in the Heap!");
        hashMap.remove(key);
    }

    @Override
    public String toString() {
        return hashMap.entrySet().stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public HashMap<T1,T2> getContent(){
        return hashMap;
    }

    @Override
    public void setContent(HashMap<T1,T2> x){
        hashMap = x;
    }
}
