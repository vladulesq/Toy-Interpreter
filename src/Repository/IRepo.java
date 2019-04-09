package Repository;
import Exceptions.RepoException;
import Model.ProgramState;

import java.util.List;


public interface IRepo
{
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> prgList);
    void logPrgStateExec(ProgramState ps);
    void startWithEmptyFile() throws RepoException;
}
