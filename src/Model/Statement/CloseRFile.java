package Model.Statement;
import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.Expression.IExpression;
import Model.ProgramState;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement
{
    private IExpression exp;

    public CloseRFile(){}
    public CloseRFile(IExpression e)
    {
        this.exp=e;
    }
    public CloseRFile(CloseRFile cF)
    {
        this.exp=cF.exp;
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
            int fileDescriptor=this.exp.evaluate(state.getSymbolTable(),state.getHeap());
            BufferedReader bufReader=state.getFileTable().find(fileDescriptor).getValue2();
            bufReader.close();
            state.getFileTable().remove(fileDescriptor);
        }
        catch (MyExprEvalException| DictionaryException| IOException e)
        {
            throw new MyStmtExecException(e.getMessage()+"");
        }
        return null;
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (CloseRFile)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new CloseRFile(this.getExp());
        }
    }

    public String toString()
    {
        return "close("+exp.toString()+")";
    }
}
