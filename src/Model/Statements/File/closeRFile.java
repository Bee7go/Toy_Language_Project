
package Model.Statements.File;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;

import Exception.*;
import Model.Statements.IStmt;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.*;

public class closeRFile implements IStmt {
    Exp exp;

    public closeRFile (Exp _exp){
        exp = _exp;
    }

    public String toString() {
        return "closeRFile(" + exp.toString()  +")";
    }

    public PrgState execute(PrgState state) throws MyException, IOException {
        IStmt myStmt;
        MyIStack<IStmt> stk = state.getStk();

        MyIDictionary<String, Value> symTable = state.getSymTable();

        MyIDictionary<StringValue, BufferedReader> myFileTable = state.getFileTable();

        MyIHeap<Integer,Value> hp = state.getHeap();


        try{
            BufferedReader associatedBuffer = myFileTable.lookup((StringValue) exp.eval(symTable,hp));
            associatedBuffer.close();

            myFileTable.remove((StringValue) exp.eval(symTable,hp));
        }
        catch (IOException e){
            throw new MyException ("IO Exception: " + e);
        }
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

