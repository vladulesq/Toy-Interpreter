package Model.Statement;

import Exceptions.MyStmtExecException;
import Model.ADT.*;
import Model.ProgramState;
import Model.Utils.Pair;

import java.io.BufferedReader;


public class ForkStmt implements IStatement {

    private IStatement statement;
    public ForkStmt(IStatement stmt)
    {
        this.statement=stmt;
    }

    IStatement getStatement()
    {
        return statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException {
        IMyDictionary<String,Integer> symbolTable=new MyDictionary<>(state.getSymbolTable());

        IMyStack<IStatement> stack= new MyStack<>();

        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable=state.getFileTable();
        IMyHeap<Integer,Integer> heap=state.getHeap();
        IMyList<Integer> output=state.getOutput();

        return new ProgramState(stack,symbolTable,output,this.statement,fileTable,heap,state.getID()+1);
    }

    @Override
    public IStatement clone() {
        try
        {
            return (ForkStmt)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new ForkStmt(this.getStatement());
        }
    }

    @Override
    public String toString()
    {
        return "fork("+this.statement.toString()+" );";
    }
}
