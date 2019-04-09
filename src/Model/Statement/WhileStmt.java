package Model.Statement;

import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Exceptions.StackException;
import Model.Expression.IExpression;
import Model.ProgramState;

public class WhileStmt implements IStatement {
    private IStatement statement;
    private IExpression expr;

    public WhileStmt(IExpression ex,IStatement st)
    {
        this.statement=st;
        this.expr=ex;
    }

    public IStatement getStatement()
    {
        return this.statement;
    }
    public IExpression getExpr()
    {
        return this.expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException {
        try
        {
            state.getStack().push(new WhileStmt(expr,statement));
            if (expr.evaluate(state.getSymbolTable(), state.getHeap()) == 0)
                state.getStack().pop();
            else
                state.getStack().push(statement);
        }
        catch (MyExprEvalException| StackException e)
        {
            throw new MyStmtExecException(e.getMessage()+"Cannot execute while!");
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "while("+expr.toString()+")"+statement.toString();
    }

    @Override
    public IStatement clone() {
        try
        {
            return (WhileStmt)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new WhileStmt(this.getExpr(),this.getStatement());
        }
    }
}
