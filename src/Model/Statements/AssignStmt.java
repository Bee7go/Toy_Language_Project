package Model.Statements;
import Exception.*;
import Model.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String _id, Exp _exp)
    {
        id = _id;
        exp = _exp;
    }

    public String toString() {
        return id + " = " + exp.toString();
    }


    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer,Value> hp = state.getHeap();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl,hp);
            Type typId = symTbl.lookup(id).getType();

            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("Declared type of variable" + id + " and type of the assigned expression do not match!");
        } else
            throw new MyException("The used variable" + id + " was not declared before!");

        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}
