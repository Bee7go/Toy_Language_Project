package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.*;

public class HeapReading implements Exp{
    Exp exp;

    public HeapReading(Exp _exp) {
        exp = _exp;
    }


    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {

        if (exp.eval(tbl,hp).equals(new RefValue()))
        {
            RefValue myValue = (RefValue)exp.eval(tbl,hp);
            if (hp.isDefined(myValue.getAddr()))
                return hp.lookup(myValue.getAddr());
            else
                throw new MyException("The address is not defined in the Heap!");
        }
        else
            throw new MyException("Expression is not evaluated to a RefValue!");
    }

    @Override
    public String toString() {
        return "HeapReading(" + exp.toString() + ")";
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ=exp.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}

