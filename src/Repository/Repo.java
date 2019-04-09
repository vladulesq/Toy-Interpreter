package Repository;
import Exceptions.RepoException;
import Model.ProgramState;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Repo implements IRepo{

    private List<ProgramState> prgState;
    private String logFilePath="file1.txt";

    public Repo() {
        this.prgState= new ArrayList<ProgramState>();
    }
    public Repo(List<ProgramState> ps)
    {
        this.prgState= ps;
    }
    public Repo(List<ProgramState> ps,String filename)
    {
        this.prgState=ps;
        this.logFilePath=filename;
    }

    @Override
    public List<ProgramState> getPrgList()
    {
        return this.prgState;
    }

    @Override
    public void setPrgList(List<ProgramState> prgList) {
        this.prgState=prgList;
    }

    @Override
    public void logPrgStateExec(ProgramState ps)
    {
        try
        {
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(ps.toString());
            logFile.close();
        }
        catch (IOException e){ }
    }

    public void startWithEmptyFile() throws RepoException {
        try
        {
            PrintWriter pw=new PrintWriter(this.logFilePath);
            pw.close();
        }
        catch (IOException e)
        {
            throw new RepoException(e.getMessage()+"Cannot clear the file!");
        }
    }

}
