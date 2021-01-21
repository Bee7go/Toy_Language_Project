package Model;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.Statements.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;
import Exception.*;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable ;
    MyIHeap<Integer, Value> Heap;
    static int id=0;
    int aux;

    IStmt originalProgram; //optional field,but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> _fileTable, MyIHeap<Integer, Value> _Heap) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = _fileTable;
        Heap = _Heap;
        aux = newId();
        stk.push(prg);
    }

    public void setStk(MyIStack<IStmt> new_exeStack) {
        exeStack = new_exeStack;
    }

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> new_symTable) {
        symTable = new_symTable;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setOut(MyIList<Value> new_out) {
        out = new_out;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public String toString() {
        return "Id is: " + "\n" + aux + "Stack is: " + "\n" + exeStack.toString() + "\n" + "SymTable is: " + "\n" +  symTable.toString() +
                "\n" + "Out is: " + "\n" +  out.toString()+ "\n"  + "FileTable is: " + "\n" +  fileTable.toString()
                + "\n" + "Heap is: " + "\n" + Heap.toString();
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> new_fileTable) {
        fileTable = new_fileTable;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setHeap(MyIHeap<Integer, Value> new_Heap) {
        Heap = new_Heap;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return Heap;
    }

    public int getId(){return aux;}

    public static synchronized int newId(){
        id += 1;
        return id;
    }


    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, IOException {
        if(exeStack.isEmpty())
            throw new MyException("PrgState Stack is empty!");

        IStmt crtStmt = exeStack.pop();

        return crtStmt.execute(this);
    }


}
