package Model.Statements;
import Exception.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt (IStmt _first, IStmt _snd){
        first = _first;
        snd = _snd;
    }

    public String toString() {
        return first.toString() + "; " + snd.toString();
    }

    public PrgState execute(PrgState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        return snd.typecheck(first.typecheck(typeEnv));
    }
}

