package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.ADT.MyStack;
import Model.PrgState;
import Exception.*;
import Model.Type.Type;
import Model.Value.Value;

public class ForkStmt implements IStmt {
    IStmt statement;

    public ForkStmt (IStmt statement){
        this.statement = statement;
    }

    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {


        MyIStack<IStmt> stk_result = new MyStack<>();

        MyIDictionary<String, Value> symTable_result = state.getSymTable().deepCopy();

        return new PrgState(stk_result,symTable_result,state.getOut(),statement,state.getFileTable(),state.getHeap());

    }


    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        return statement.typecheck(typeEnv);
    }
}
