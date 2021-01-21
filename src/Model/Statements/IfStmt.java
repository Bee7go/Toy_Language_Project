package Model.Statements;
import Exception.*;
import Model.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return " IF(" + exp.toString() + ") THEN{ " + thenS.toString() + "} ELSE{" + elseS.toString() + "} ";
    }



    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer,Value> hp = state.getHeap();
        Value val = exp.eval(tbl,hp);
        BoolValue bool = new BoolValue(true);

        if(val.getType().equals(new BoolType()))
        {
            if(val.equals(bool))
            {
                state.getStk().push(thenS);
            }
            else
            {
                state.getStk().push(elseS);
            }
        }
        else
            throw new MyException("Conditional expression is not a boolean!");

        return null;
    }



    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.deepCopy());
            elseS.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
}
