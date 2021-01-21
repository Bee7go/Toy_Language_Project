package Model.Statements;
import Exception.*;
import Model.*;
import Model.ADT.MyIDictionary;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;

    public VarDeclStmt(String _name, Type _type)
    {
        name = _name;
        type = _type;
    }

    public PrgState execute(PrgState state) throws MyException{
        if (state.getSymTable().isDefined(name))
            throw new MyException("variable is already declared!");

        state.getSymTable().add(name,type.defaultValue());

        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        typeEnv.add(name,type);
        return typeEnv;
    }

    public String toString(){ return type + " " + name; }


}
