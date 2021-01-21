package Model.Expressions;
import Exception.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException;

    public String toString();

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;

}
