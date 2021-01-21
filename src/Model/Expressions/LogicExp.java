package Model.Expressions;
import Exception.*;
import Model.ADT.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.ADT.MyIDictionary;
import Model.Value.IntValue;
import Model.Value.Value;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    public LogicExp(Exp _e1, Exp _e2, int _op){
        e1 = _e1;
        e2 = _e2;
        op = _op;
    }

    public LogicExp(String _op, Exp _e1, Exp _e2) {
        e1 = _e1; e2 = _e2;
        if(_op.equals("and"))
            op = 1;
        else{
            if(_op.equals("or"))
                op = 2;
        }
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException{
        Value v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new BoolValue(n1 && n2);
                if (op == 2) return new BoolValue(n1 || n2);
            }else
                throw new MyException("Second operand is not a boolean");
        }else
            throw new MyException("First operand is not a boolean");

        return null;
    }

    @Override
    public String toString() {
        if (op == 1) {
            return e1.toString() + "and" + e2.toString();
        } else {
            return e1.toString() + "or" + e2.toString();
        }
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType()) ){
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not a boolean");
        }else
            throw new MyException("first operand is not a boolean");
    }
}
