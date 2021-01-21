package Model.Statements;
import Exception.*;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Type.Type;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    public PrgState execute(PrgState state) throws MyException, IOException;
    public String toString();
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
