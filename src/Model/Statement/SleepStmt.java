package Model.Statement;

import Exceptions.MyStmtExecException;
import Exceptions.StackException;
import Model.ProgramState;
import java.util.concurrent.TimeUnit;

public class SleepStmt implements IStatement{

    private int number;

    public SleepStmt(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyStmtExecException {
        try
        {
            if(number == 0)
                state.getStack().pop();
            else {
                state.getStack().push(new SleepStmt(number - 1));
                TimeUnit.SECONDS.sleep(number - 1);
                state.getStack().pop();
            }
        } catch (StackException | InterruptedException e)
        {
            throw new MyStmtExecException(e.getMessage() + "Cannot execute sleep!");
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "sleep("+(number-1) + ");";
    }

    @Override
    public IStatement clone() {
        try
        {
            return (SleepStmt) super.clone();
        }catch (CloneNotSupportedException e)
        {
            return new SleepStmt(this.getNumber() - 1);
        }
    }
}
