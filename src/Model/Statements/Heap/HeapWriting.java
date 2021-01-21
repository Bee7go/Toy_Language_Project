package Model.Statements.Heap;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.net.Proxy;

import Exception.*;

public class HeapWriting implements IStmt {
    String var_name;
    Exp exp;

    public HeapWriting (Exp _exp, String _var_name){
        exp = _exp;
        var_name = _var_name;
    }

    public String toString() {
        return "HeapWriting("+ var_name.toString() + ", " + exp.toString() + ")";
    }



    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt myStmt;

        MyIDictionary<String, Value> symTable = state.getSymTable();

        MyIDictionary<StringValue, BufferedReader> myFileTable = state.getFileTable();

        MyIHeap<Integer, Value> Heap = state.getHeap();


        if(symTable.lookup(this.var_name).equals(new RefValue())){

            RefValue myValue = (RefValue)symTable.lookup(this.var_name);
            if(Heap.isDefined(myValue.getAddr()))
            {
                Type type1 = myValue.getLocationType();
                Type type2 = exp.eval(symTable,Heap).getType();

                if(type1.equals(type2))
                {
                    Heap.update(myValue.getAddr(), exp.eval(symTable,Heap));
                }
                else
                    throw new MyException("Exp doesn't have the type equal to the locationType of the var_name type");
            }
            else
                throw new MyException("The address from the RefValue associated in SymTable is not a key in Heap!");

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
            throw new MyException("HeapWriting stmt: right hand side and left hand side have different types");
    }
}

