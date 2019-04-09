package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.Expression.IExpression;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement
{
    private IExpression exp_file_id;
    private String var_name;

    public ReadFile(IExpression id, String name)
    {
        this.var_name=name;
        this.exp_file_id=id;
    }
    public IExpression getExp_file_id()
    {
        return this.exp_file_id;
    }
    public String getVar_name()
    {
        return this.var_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException {
        try
        {
            int fileDescriptor=this.exp_file_id.evaluate(state.getSymbolTable(),state.getHeap());
            BufferedReader bufReader=state.getFileTable().find(fileDescriptor).getValue2();
            String readLine=bufReader.readLine();
            int value;
            if(readLine==null)
                value=0;
            else
                value=Integer.parseInt(readLine);
            if(state.getSymbolTable().isDefined(var_name))
                state.getSymbolTable().update(var_name,value);
            else
                state.getSymbolTable().add(var_name,value);
        }
        catch (MyExprEvalException | DictionaryException | IOException e)
        {
            throw new MyStmtExecException(e.getMessage()+"ReadFile execution failed!");
        }
        return null;
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (ReadFile)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new ReadFile(this.getExp_file_id(),this.getVar_name());
        }
    }

    public String toString()
    {
        return "ReadFile("+exp_file_id.toString()+", "+var_name+" )";
    }
}
