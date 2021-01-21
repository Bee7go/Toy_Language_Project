package Model.Statements;
import Exception.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp _exp){
        exp = _exp;
    }

    public String toString(){ return "print(" +exp.toString()+")";}

    public PrgState execute(PrgState state) throws MyException{

        MyIHeap<Integer, Value> hp = state.getHeap();
        state.getOut().add(exp.eval(state.getSymTable(),hp));

        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
