package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.Expression.IExpression;
import Model.ProgramState;

public class HeapAllocation implements IStatement {

    private IExpression expr;
    private String var_name;

    public HeapAllocation() {}
    public HeapAllocation(String str,IExpression e)
    {
        this.expr=e;
        this.var_name=str;
    }

    public String getVar_name()
    {
        return this.var_name;
    }
    public IExpression getExpr()
    {
        return this.expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException {
        try {
            int v=expr.evaluate(state.getSymbolTable(),state.getHeap());
            if (state.getSymbolTable().isDefined(var_name))
            {
                state.getSymbolTable().update(var_name,state.getHeap().getNewFreeLocation());
                state.getHeap().add(state.getHeap().getNewFreeLocation(),v);
            }
            else
            {
                state.getSymbolTable().add(var_name,state.getHeap().getNewFreeLocation());
                state.getHeap().add(state.getHeap().getNewFreeLocation(),v);
            }
        }
        catch (MyExprEvalException|DictionaryException e)
        {
            throw new MyStmtExecException(e.getMessage()+"Heap allocation error!");
        }
        return null;
    }

    @Override
    public IStatement clone() {
        try
        {
            return (HeapAllocation)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new HeapAllocation(this.getVar_name(),this.getExpr());
        }
    }

    @Override
    public String toString()
    {
        return "new("+this.var_name+","+this.expr.toString()+")";
    }
}
