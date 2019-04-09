package Model.Statement;

import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.Expression.IExpression;
import Model.ProgramState;

public class PrintStmt implements IStatement
{
    private IExpression exp;

    public PrintStmt(){}
    public PrintStmt(IExpression e)
    {
        this.exp=e;
    }
    public PrintStmt(PrintStmt ps)
    {
        this.exp=ps.getExp();
    }

    public IExpression getExp()
    {
        return this.exp;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException
    {
        try
        {
            state.getOutput().add(exp.evaluate(state.getSymbolTable(),state.getHeap()));
        }
        catch(MyExprEvalException e)
        {
            throw new MyStmtExecException(e.getMessage()+" Print statement cannot be executed!");
        }
        return null;
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (PrintStmt)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new PrintStmt(this.getExp());
        }
    }

    @Override
    public String toString()
    {
        return "Print("+exp.toString()+")";
    }
}
