package Model.Expressions;
import Exception.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String _id)
    {
        id = _id;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException
    {
        return tbl.lookup(id);
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
