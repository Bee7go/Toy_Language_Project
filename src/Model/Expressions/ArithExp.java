package Model.Expressions;
import Exception.*;
import Model.ADT.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.ADT.MyIDictionary;
import Model.Value.Value;

public class ArithExp implements Exp{
    Exp e1; //a
    Exp e2; //b
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(Exp _e1, Exp _e2, int _op) {
        e1 = _e1; e2 = _e2; op = _op;
    }

    public ArithExp(char _op, Exp _e1, Exp _e2) {
        e1 = _e1; e2 = _e2;
        if(_op == '+')
            op = 1;
        else{
            if(_op == '-')
                op = 2;
            else {
                if(_op == '*')
                    op = 3;
                else{
                    if(_op == '/')
                        op = 4;
                }
            }

        }
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException{
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
                if (op==1) return new IntValue(n1+n2);
                if (op ==2) return new IntValue(n1-n2);
                if(op==3) return new IntValue(n1*n2);
                if(op==4)
                    if(n2==0) throw new MyException("We can not divide by zero!");
                    else return new IntValue(n1/n2);
            }else
                throw new MyException("The second operand is not an integer!");
        }else
            throw new MyException("First operand is not an integer!");

        return null;
    }

    @Override
    public String toString() {
        if(op==1){
            return e1.toString() + "+" + e2.toString() ;
        }
        else{
            if(op == 2){
                return e1.toString() + "-" + e2.toString() ;
            }
            else{
                if(op == 3){
                    return e1.toString() + "*" + e2.toString() ;
                }
                else{
                    return e1.toString() + "/" + e2.toString() ;
                }
            }
        }

    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType()) ){
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
            throw new MyException("second operand is not an integer");
        }else
        throw new MyException("first operand is not an integer");
    }
}
