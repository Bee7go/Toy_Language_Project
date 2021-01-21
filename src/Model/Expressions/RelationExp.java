package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

import Exception.*;

public class RelationExp implements Exp{
    Exp e1;
    Exp e2;
    String op;

    public RelationExp(Exp _e1, Exp _e2, String _op) {
        e1 = _e1; e2 = _e2;
        op = _op;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();

                if(op.equals("<")) return new BoolValue(n1<n2);
                else{
                    if(op.equals("<=")) return new BoolValue(n1<=n2);
                    else{
                        if(op.equals("==")) return new BoolValue(n1==n2);
                        else{
                            if(op.equals("!=")) return new BoolValue(n1!=n2);
                            else{
                                if(op.equals(">")) return new BoolValue(n1>n2);
                                else{
                                    if(op.equals(">=")) return new BoolValue(n1>=n2);
                                    else
                                        throw new MyException("The received operand is not an accepted input!");
                                }
                            }
                        }
                    }
                }

            }else
                throw new MyException("The second expression is not an integer!");
        }else
            throw new MyException("First expression is not an integer!");

    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType()) ){
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString() + op + e2.toString() ;
    }
}
