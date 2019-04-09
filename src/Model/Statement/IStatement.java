package Model.Statement;

import Exceptions.MyStmtExecException;
import Model.ProgramState;

public interface IStatement
{
    ProgramState execute(ProgramState state) throws MyStmtExecException;
    IStatement clone();
}
