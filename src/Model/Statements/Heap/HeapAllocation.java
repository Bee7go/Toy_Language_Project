package Model.Statements.Heap;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Exception.*;
import Model.Statements.IStmt;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HeapAllocation implements IStmt {
    String var_name;
    Exp exp;

    public HeapAllocation (Exp _exp, String _var_name){
        exp = _exp;
        var_name = _var_name;
    }

    public String toString() {
        return "HeapAllocation("+ var_name.toString() + ", " + exp.toString() + ")";

    }


    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt myStmt;

        MyIDictionary<String, Value> symTable = state.getSymTable();

        MyIDictionary<StringValue, BufferedReader> myFileTable = state.getFileTable();

        MyIHeap<Integer, Value> Heap = state.getHeap();


        if(symTable.lookup(this.var_name).equals(new RefValue())){

            RefValue myValue = (RefValue)symTable.lookup(this.var_name);
            Type type1 = myValue.getLocationType();
            Type type2 = exp.eval(symTable,Heap).getType();

            if(type1.equals(type2))
            {
                int free = 1;
                while(Heap.isDefined(free))
                    free+=1;

                Heap.add(free,exp.eval(symTable,Heap));
                symTable.update(this.var_name,new RefValue(free, type1));
            }
            else
                throw new MyException("Types are not equal!");
        }
        else
            throw new MyException("var_name is not a variable in SymTable with type RefType!");

        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("HeapAllocation stmt: right hand side and left hand side have different types");
    }
}
