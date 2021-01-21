package Model.Statements;
import Exception.*;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;

public class NopStmt implements IStmt{

    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    public String toString() {
        return "-";
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return typeEnv;
    }
}
