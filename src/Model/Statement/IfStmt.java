package Model.Statement;

import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.Expression.IExpression;
import Model.ProgramState;

public class IfStmt implements IStatement
{
    private IExpression exp;
    private IStatement thenS;
    private IStatement elseS;

    public IfStmt(){}
    public IfStmt(IExpression ex,IStatement t,IStatement e)
    {
        this.exp=ex;
        this.thenS=t;
        this.elseS=e;
    }

    public IExpression getExp()
    {
        return this.exp;
    }
    public IStatement getThenS()
    {
        return this.thenS;
    }
    public IStatement getElseS()
    {
        return this.elseS;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException
    {
        try
        {
            if(this.exp.evaluate(state.getSymbolTable(),state.getHeap())!=0)
                this.thenS.execute(state);
            else
                this.elseS.execute(state);
        }
        catch(MyExprEvalException|MyStmtExecException e)
        {
            throw new MyStmtExecException(e.getMessage()+"If statement cannot be executed!");
        }
        return null;
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (IfStmt)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new IfStmt(this.getExp(),this.getThenS(),this.getElseS());
        }
    }

    @Override
    public String toString()
    {
        return "If("+exp.toString()+")then("+thenS.toString()+")else("+elseS.toString()+")";
    }
}
