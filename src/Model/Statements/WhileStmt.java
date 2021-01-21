package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import Exception.*;

public class WhileStmt implements IStmt {
    Exp exp;
    IStmt statement;

    public WhileStmt(Exp _exp, IStmt _statement)
    {
        exp = _exp;
        statement = _statement;
    }

    public String toString() {
        return "WHILE(" + exp.toString() + ") " + statement.toString();
    }


    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer,Value> hp = state.getHeap();

        Value val = exp.eval(tbl,hp);

        if(val.getType().equals(new BoolType()))
        {
            if(val.equals( new BoolValue(true))) //True
            {
                state.getStk().push(new CompStmt(statement,new WhileStmt(exp,statement))); //or this
            }
        }
        else
            throw new MyException("Conditional expression is not a boolean!");

        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }
}