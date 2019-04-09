package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.MyStmtExecException;
import Model.ADT.IMyDictionary;
import Model.ProgramState;
import Model.Utils.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStatement
{
    private String var_file_id;
    private String filename;

    public OpenRFile(String id, String filename)
    {
        this.var_file_id=id;
        this.filename=filename;
    }
    public String getVar_file_id()
    {
        return this.var_file_id;
    }
    public String getFilename()
    {
        return this.filename;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException
    {
        try
        {
            int key = state.getSymbolTable().find(var_file_id);
            if(state.getFileTable().find(key).getValue1().equals(filename))
                throw new MyStmtExecException("Filename already defined!");
        }
        catch(DictionaryException e){}
        try
        {
            BufferedReader bufReader =new BufferedReader(new FileReader(filename));
            Pair<String,BufferedReader> p=new Pair<>(filename,bufReader);
            int unique=state.getFileTable().computeUniqueKey();
            state.getFileTable().add(unique,p);
            state.getSymbolTable().add(var_file_id,unique);
        }
        catch(IOException e)
        {
            throw new MyStmtExecException(e.getMessage()+"pgrStateError!");
        }
        return null;
    }

    @Override
    public IStatement clone()
    {
        try
        {
            return (OpenRFile)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return new OpenRFile(this.getVar_file_id(),this.getFilename());
        }
    }

    public String toString()
    {
        return "open("+var_file_id+", "+filename+")";
    }
}
