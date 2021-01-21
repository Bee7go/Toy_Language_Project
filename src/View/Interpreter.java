package View;

import Controller.Controller;
import Model.ADT.MyDictionary;
import Model.ADT.MyHeap;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Statements.File.closeRFile;
import Model.Statements.File.openRFile;
import Model.Statements.File.readFile;
import Model.Statements.Heap.HeapAllocation;
import Model.Statements.Heap.HeapWriting;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import Exception.*;

public class Interpreter {

    public static IStmt ex1() {
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
    }

    public static IStmt ex2(){
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                                new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

    }

    public static IStmt ex3(){
        return new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                        new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                                new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

    }

    public static IStmt ex4(){
        return new CompStmt(new VarDeclStmt("fileName", new StringType()),
                new CompStmt(new AssignStmt("fileName", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("fileName")),
                                new CompStmt(new VarDeclStmt("x", new IntType()),
                                        new CompStmt(new readFile(new VarExp("fileName"), "x"),
                                                new CompStmt(new PrintStmt(new VarExp("x")),
                                                        new CompStmt(new readFile(new VarExp("fileName"), "x"),
                                                                new CompStmt(new PrintStmt(new VarExp("x")),
                                                                        new closeRFile(new VarExp("fileName"))))))))));
    }
    public static IStmt ex5(){
        return new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(8))),
                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))),
                                new CompStmt(new IfStmt(new RelationExp( new VarExp("a"), new VarExp("b"),">="), new AssignStmt("a", new ValueExp(new IntValue(0))),
                                        new AssignStmt("b", new ValueExp(new IntValue(0)))), new PrintStmt(new VarExp("a")))))));


    }

    public static IStmt ex6(){
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationExp(new VarExp("v"), new ValueExp(new IntValue(0)),">"),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),2)))),
                                new PrintStmt(new VarExp("v")))));
    }

    public static IStmt ex7(){
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocation(new ValueExp(new IntValue(20)),"v"),
                        new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),
                                new CompStmt(new HeapWriting(new ValueExp(new IntValue(30)),"v"),
                                        new PrintStmt(new ArithExp(new HeapReading(new VarExp("v")),new ValueExp(new IntValue(5)),1))))));



    }

    public static IStmt ex8(){
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocation( new ValueExp(new IntValue(20)),"v"),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                        new CompStmt(new HeapAllocation(new VarExp("v"),"a"), new CompStmt(new HeapAllocation(new ValueExp(new IntValue(30)),"v"),
                                new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))))))));

    }

    public static IStmt ex9(){
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocation( new ValueExp(new IntValue(22)),"a"),

                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new HeapWriting(new ValueExp(new IntValue(30)),"a"),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new HeapReading(new VarExp("a"))))))),
                                                //fork
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new HeapReading(new VarExp("a")))))))));
    }

    public static IStmt ex10(){
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocation( new ValueExp(new IntValue(22)),"a"),

                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new HeapWriting(new ValueExp(new IntValue(30)),"a"),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new HeapReading(new VarExp("a"))))))),

                                                new CompStmt(new ForkStmt(
                                                        new CompStmt(new HeapWriting(new ValueExp(new IntValue(47)),"a"),
                                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(7))),
                                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                new PrintStmt(new HeapReading(new VarExp("a"))))))),
                                                        //fork
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new HeapReading(new VarExp("a"))))))))));


    }




    public static <MyIRepository> void main(String[] args) throws IOException, MyException, InterruptedException {

        try{
            ex1().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
        System.out.println(e);
        return;}

        PrgState prg1 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex1(),
                new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Integer, Value>());

        IRepo repo1 = new Repo("log1.txt");
        repo1.add(prg1);
        Controller ctr1 = new Controller(repo1);

        // ------------------------------------------------------->>>



        try{
            ex2().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}

        PrgState prg2 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex2(),
                new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Integer, Value>());

        IRepo repo2 = new Repo("log2.txt");
        repo2.add(prg2);
        Controller ctr2 = new Controller(repo2);

        // ------------------------------------------------------->>>


        try{
            ex3().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}
        PrgState prg3 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex3(),
                new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Integer, Value>());

        IRepo repo3 = new Repo("log3.txt");
        repo3.add(prg3);
        Controller ctr3 = new Controller(repo3);


        // ------------------------------------------------------->>>



        try{
            ex4().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            }

        PrgState prg4 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex4(),
                new MyDictionary<StringValue, BufferedReader>(),new MyHeap<Integer, Value>());
        IRepo repo4 = new Repo("log4.txt");
        repo4.add(prg4);
        Controller ctr4 = new Controller(repo4);

        // ------------------------------------------------------->>>


        try{
            ex5().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}

        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex5(),
                new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Integer, Value>());

        IRepo repo5 = new Repo("log5.txt");
        repo5.add(prg5);
        Controller ctr5 = new Controller(repo5);

        // ------------------------------------------------------->>>





        try{
            ex6().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}

        PrgState prg6 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex6(),
                new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Integer, Value>());

        IRepo repo6 = new Repo("log6.txt");
        repo6.add(prg6);
        Controller ctr6 = new Controller(repo6);

        // ------------------------------------------------------->>>



        try{
            ex7().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}


        PrgState prg7 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex7(),
                new MyDictionary<StringValue, BufferedReader>(), new MyHeap<Integer, Value>());

        IRepo repo7 = new Repo("log7.txt");
        repo7.add(prg7);
        Controller ctr7 = new Controller(repo7);


        //-------------------------------------------------------



        try{
            ex8().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}

        PrgState prg8 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex8(),
                new MyDictionary<StringValue, BufferedReader>(),new MyHeap<Integer, Value>());
        IRepo repo8 = new Repo("log8.txt");
        repo8.add(prg8);
        Controller ctr8 = new Controller(repo8);

        //-------------------------------------------------------

        /*int v; Ref int a; v=10; new(a,22);
        fork(wH(a,30);v=32;print(v);print(rH(a)));
        print(v);print(rH(a))*/

        try{
            ex9().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);}

        PrgState prg9 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex9(),
                new MyDictionary<StringValue, BufferedReader>(),new MyHeap<Integer, Value>());
        IRepo repo9 = new Repo("log9.txt");
        repo9.add(prg9);
        Controller ctr9 = new Controller(repo9);

        //-------------------------------------------------------



        try{
            ex10().typecheck(new MyDictionary<String, Type>());}
        catch(MyException e){
            System.out.println(e);
            return;}

        PrgState prg10 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex10(),
                new MyDictionary<StringValue, BufferedReader>(),new MyHeap<Integer, Value>());
        IRepo repo10 = new Repo("log10.txt");
        repo10.add(prg10);
        Controller ctr10 = new Controller(repo10);

        //-------------------------------------------------------


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1().toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2().toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3().toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4().toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5().toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6().toString(), ctr6)); //while statement
        menu.addCommand(new RunExample("7", ex7().toString(), ctr7)); //heap
        menu.addCommand(new RunExample("8", ex8().toString(), ctr8)); //Garbage Collector
        menu.addCommand(new RunExample("9", ex9().toString(), ctr9)); //fork (teacher example)
        menu.addCommand(new RunExample("10", ex10().toString(), ctr10)); //2_forks
        menu.show();

    }
}