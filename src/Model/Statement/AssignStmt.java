package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.ADT.IMyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;

public class AssignStmt implements IStatement
{
    private String id;
    private IExpression exp;

    public AssignStmt() {}
    public AssignStmt(String id,IExpression exp)
    {
        this.id=id;
        this.exp=exp;
    }
    public AssignStmt(AssignStmt st)
    {
        this.id=st.getId();
        this.exp=st.getExp();
    }

    public String getId()
    {
        return this.id;
    }
    public IExpression getExp()
    {
        return this.exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException
    {
        IMyDictionary<String,Integer> symTable=state.getSymbolTable();
        try
        {
            int val = exp.evaluate(symTable,state.getHeap());
            if (symTable.isDefined(id))
                symTable.update(id, val);
            else
                symTable.add(id, val);
        }
        catch(MyExprEvalException| DictionaryException e)
        {
            throw new MyStmtExecException(e.getMessage()+" The assignment cannot be done!");
        }
        return null;
    }

    @Override
    public String toString()
    {
        return id+"="+exp.toString();
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (AssignStmt)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new AssignStmt(this.getId(),this.getExp());
        }
    }

}
