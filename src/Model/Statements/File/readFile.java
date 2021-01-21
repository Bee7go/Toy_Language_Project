package Model.Statements.File;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Exception.*;

public class readFile implements IStmt {
    Exp exp;
    String var_name;

    public readFile (Exp _exp, String _var_name){
        exp = _exp;
        var_name = _var_name;
    }

    public String toString() {
        return "readFile(" + exp.toString() + ", " + var_name +")";
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IStmt myStmt;
        MyIDictionary<String, Value> symtable = state.getSymTable();

        MyIHeap<Integer,Value> hp = state.getHeap();

        if(symtable.lookup(this.var_name).getType().equals(new IntType()))
        {
            if(exp.eval(symtable,hp).getType().equals(new StringType()))
            {
                MyIDictionary<StringValue, BufferedReader> filetable = state.getFileTable();
                try{
                    BufferedReader reader = filetable.lookup((StringValue)exp.eval(symtable,hp));

                    String lineForRead = reader.readLine();
                    if( lineForRead == null)
                    {
                        symtable.update(var_name, new IntValue(0));
                    }
                    else
                    {
                        symtable.update(var_name,new IntValue(Integer.parseInt(lineForRead)));
                    }
                }
                catch (IOException e) {
                    throw new MyException("IO Exception: " + e);
                }

            }
            else throw new MyException("Exp Value is not a String");
        }
        else throw new MyException("Variable type is not integer! ");

        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("readFile stmt: right hand side and left hand side have different types");
    }
}