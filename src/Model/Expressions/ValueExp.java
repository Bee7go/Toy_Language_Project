package Model.Expressions;
import  Exception.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class ValueExp implements Exp{
    Value e;

    public ValueExp(Value _e)
    {
        e = _e;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {

        return e;
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }

}
