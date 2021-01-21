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

public class openRFile implements IStmt {
    Exp exp;

    public openRFile (Exp _exp){
        exp = _exp;
    }

    public String toString() {
        return "openRFile(" + exp.toString()  +")";
    }

    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        IStmt myStmt;
        MyIStack<IStmt> stk = state.getStk();

        MyIDictionary<String, Value> symTable = state.getSymTable();

        MyIDictionary<StringValue, BufferedReader> myFileTable = state.getFileTable();


        MyIHeap<Integer,Value> hp = state.getHeap();


        if(exp.eval(symTable,hp).getType().equals(new StringType()) ){

            if(!myFileTable.searchfor((StringValue) exp.eval(symTable,hp))){
                try{
                    BufferedReader reader =
                            new BufferedReader(new FileReader(((StringValue) exp.eval(symTable,hp)).getVal()));

                    myFileTable.add((StringValue)exp.eval(symTable,hp),reader);
                System.out.println("My Reader is  ----> " + reader);
                }
                catch (IOException e){
                    throw new MyException ("IO Exception: " + e);
                }

            }else
                throw new MyException("The string value is already defined!");
        }
        else
            throw new MyException("Given expression is not a String!");


        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
