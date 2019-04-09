package View;

import Controller.InterpreterController;
import Exceptions.ControllerException;
import Exceptions.ViewException;

public class RunExample extends Command
{
    private InterpreterController ctrl;
    public RunExample(String key, String desc,InterpreterController ctrl)
    {
        super(key, desc);
        this.ctrl=ctrl;
    }

    @Override
    public void execute() throws ViewException
    {
//        try
//        {
        ctrl.allSteps();
//        }
//        catch (ControllerException e)
//        {
//            throw new ViewException(e.getMessage()+" View exception!");
//        }
    }
}
