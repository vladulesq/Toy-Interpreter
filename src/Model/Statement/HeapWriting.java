package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.Expression.IExpression;
import Model.ProgramState;

public class HeapWriting implements IStatement{

    private String var_name;
    private IExpression expr;

    public HeapWriting() {}
    public HeapWriting(String str,IExpression e)
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
        try
        {
            int addr = state.getSymbolTable().find(this.var_name);
            int v = this.expr.evaluate(state.getSymbolTable(), state.getHeap());
            if(state.getHeap().isDefined(addr))
                state.getHeap().update(addr,v);
            else
                throw new MyStmtExecException("Invalid address!");
        }
        catch (DictionaryException| MyExprEvalException e)
        {
            throw new MyStmtExecException(e.getMessage()+" Heap writing error!");
        }
        return null;
    }

    @Override
    public IStatement clone() {
        try
        {
            return (HeapWriting)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new HeapWriting(this.getVar_name(),this.getExpr());
        }
    }

    @Override
    public String toString()
    {
        return "wh("+this.var_name+","+this.expr+")";
    }
}
