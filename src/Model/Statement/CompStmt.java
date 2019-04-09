package Model.Statement;

import Model.ProgramState;
import Model.ADT.IMyStack;

public class CompStmt implements IStatement
{
    private IStatement first;
    private IStatement snd;

    public CompStmt() {}
    public CompStmt(IStatement first,IStatement snd)
    {
        this.first=first;
        this.snd=snd;
    }
    public CompStmt(CompStmt cs)
    {
        this.first=cs.getFirst();
        this.snd=cs.getSnd();
    }
    public IStatement getFirst()
    {
        return this.first;
    }
    public IStatement getSnd()
    {
        return this.snd;
    }

    @Override
    public ProgramState execute(ProgramState state)
    {
        IMyStack<IStatement> stk = state.getStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (CompStmt)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new CompStmt(this.getFirst(),this.getSnd());
        }
    }

    @Override
    public String toString()
    {
        return "("+first.toString()+";"+snd.toString()+")";
    }
}
