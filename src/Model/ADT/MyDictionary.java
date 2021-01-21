package Model.ADT;
import java.util.*;
import java.util.stream.Collectors;

import Exception.*;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2>{

    Hashtable<T1,T2> dictionary;

    public MyDictionary(){
        dictionary = new Hashtable<T1,T2>();
    }

    @Override
    public void add(T1 key, T2 value){
        dictionary.put(key, value);

    }

    @Override
    public void update(T1 key, T2 new_value) throws MyException{


        if(dictionary.get(key) == null)
            throw new MyException("Can't update! Key doesn't exists in the dictionary!");

        dictionary.put(key, new_value);
    }


    @Override
    public T2 lookup(T1 key) throws MyException{

        if(dictionary.get(key) == null)
            throw new MyException("Key doesn't exists in the dictionary!");
        return dictionary.get(key);
    }

    @Override
    public boolean searchfor(T1 key){
        if(dictionary.get(key) == null)
            return false;
        return true;
    }

    @Override
    public boolean isDefined(T1 key)
    {
        Enumeration keys = dictionary.keys();
        boolean result = false;
        while(keys.hasMoreElements())
        {
            if(key == keys.nextElement())
                result = true;
        }
        return result;
    }

    @Override
    public void remove(T1 key) throws MyException{
        if(dictionary.get(key) == null)
            throw new MyException("Can't remove! Key doesn't exists in the dictionary!");
        dictionary.remove(key);
    }

    @Override
    public String toString() {
        return dictionary.entrySet().stream().map(Objects::toString).collect(Collectors.joining("\n"));

    }

    @Override
    public List<T2> values(){
        List<T2> result = new ArrayList<T2>();

        Enumeration enu = dictionary.keys();

        while (enu.hasMoreElements()) {
            result.add(dictionary.get(enu.nextElement()));
        }
        return result;
    }

    @Override
    public MyDictionary<T1,T2> deepCopy(){
        MyDictionary<T1,T2> result = new MyDictionary<T1,T2>();
        Enumeration <T1> enumeration = dictionary.keys();
        T1 key;
        // iterate using enumeration object
        while(enumeration.hasMoreElements()) {
            key = enumeration.nextElement();
            result.add(key,dictionary.get(key));
        }
        return result;
    }

    @Override
    public Hashtable<T1,T2> getContent() {
        return dictionary;
    }
}
