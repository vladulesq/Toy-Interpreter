package View;

import Controller.InterpreterController;
import Exceptions.ViewException;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.Pair;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Interpreter
{
    public static void main(String[] args)
    {
        IMyHeap<Integer,Integer> heap1=new Heap<Integer, Integer>();
        IMyList<Integer> list1=new MyList<Integer>();
        IMyDictionary<String,Integer> table1=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack1=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable1=new FileTable<>();
        IStatement expr1 =  new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
        ProgramState program1 = new ProgramState(stack1, table1, list1, expr1, fileTable1,heap1,1);
        List<ProgramState> prgList=new ArrayList<>();
        prgList.add(program1);
        IRepo repo1 = new Repo(prgList, "log1.txt");
        InterpreterController ctrl1 = new InterpreterController(repo1);

        IMyHeap<Integer,Integer> heap13=new Heap<Integer, Integer>();
        IMyList<Integer> list13=new MyList<Integer>();
        IMyDictionary<String,Integer> table13=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack13=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable13=new FileTable<>();

        IStatement expr13=new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new HeapAllocation("a",new ConstExp(22)),new CompStmt(new ForkStmt
                        (new CompStmt
                                (new HeapWriting("a",new ConstExp(30)),
                                        new CompStmt(new AssignStmt("v",new ConstExp(32)),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new HeapReading("a")))))),
                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReading("a"))))));

        ProgramState program13 = new ProgramState(stack13, table13, list13, expr13, fileTable13,heap13,1);
        List<ProgramState> prgList13=new ArrayList<>();
        prgList13.add(program13);
        IRepo repo13 = new Repo(prgList13, "log13.txt");
        InterpreterController ctrl13 = new InterpreterController(repo13);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit\n"));
        menu.addCommand(new RunExample("1","v=2;\n       Print(v);\n",ctrl1));

        menu.addCommand(new RunExample("13"," v=10;new(a,22);\n" +
                "        fork(wH(a,30);  v=32;  print(v);  print(rH(a)));\n" +
                "        print(v);print(rH(a)) ",ctrl13));

        try
        {
            menu.show();
        }
        catch (ViewException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
