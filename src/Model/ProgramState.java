package Model;

import Exceptions.ControllerException;
import Exceptions.DomainException;
import Exceptions.MyStmtExecException;
import Exceptions.StackException;
import Model.ADT.*;
import Model.Statement.IStatement;
import Model.Utils.Pair;

import java.io.BufferedReader;

public class ProgramState
{
    private IMyStack<IStatement> exeStack;
    private IMyDictionary<String, Integer> symTable;
    private IMyList<Integer> out;
    private IStatement originalProgram;
    private IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable;
    private IMyHeap<Integer,Integer> heap;
    private Integer id;

    public ProgramState(IMyStack<IStatement> stk, IMyDictionary<String,Integer> symtbl, IMyList<Integer> ot,
                        IStatement prg, IMyFileTable<Integer,Pair<String,BufferedReader>> fileTable1,IMyHeap<Integer,Integer> heap1, Integer id)
    {
        this.exeStack=stk;
        this.symTable=symtbl;
        this.out = ot;
        this.originalProgram=prg.clone();
        this.fileTable=fileTable1;
        this.heap=heap1;
        this.id=id;
        stk.push(prg);
    }
    public Integer getID()
    {
        return this.id;
    }
    public void setID(Integer id)
    {
        this.id=id;
    }


    public IMyFileTable<Integer, Pair<String, BufferedReader>> getFileTable()
    {
        return this.fileTable;
    }
    public IMyStack<IStatement> getStack()
    {
        return this.exeStack;
    }
    public IMyDictionary<String, Integer> getSymbolTable()
    {
        return this.symTable;
    }
    public IMyList<Integer> getOutput()
    {
        return this.out;
    }
    public IStatement getOriginalProgram()
    {
        return this.originalProgram;
    }
    public IMyHeap<Integer,Integer> getHeap() { return  this.heap; }
    public void setFileTable(IMyFileTable<Integer, Pair<String, BufferedReader>> dict)
    {
        this.fileTable=dict;
    }
    public void setStack(IMyStack<IStatement> st)
    {
        this.exeStack=st;
    }
    public void setSymbolTable(IMyDictionary<String, Integer> st)
    {
        this.symTable=st;
    }
    public void setOutput(IMyList<Integer> o)
    {
        this.out=o;
    }
    public void setOriginalProgram(IStatement state)
    {
        this.originalProgram=state;
    }
    public void setHeap(IMyHeap<Integer,Integer> heap1) { this.heap=heap1; }
    public String toString()
    {
        return this.id.toString()+"\nExeStack:\n"+exeStack.toString()+"SymTable:\n"+symTable.toString()+"Out:\n"+out.toString()+"FileTable:\n"
                +fileTable.toString()+"Heap:\n"+heap.toString()+"-------------\n";
    }

    public boolean isNotCompleted()
    {
        return !this.exeStack.isEmpty();
    }

    public ProgramState oneStep() throws MyStmtExecException
    {
        try {
            IStatement crtStmt = this.exeStack.pop();
            return crtStmt.execute(this);
        }
        catch (StackException e)
        {
            throw new MyStmtExecException(e.getMessage()+" Cannot execute one step!");
        }
    }

}
