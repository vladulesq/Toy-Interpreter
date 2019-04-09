package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Exceptions.MyStmtExecException;
import Model.ADT.IMyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;

public class condStmt implements IStatement{

    private String id;
    private IExpression exp;
    private IExpression cons1;
    private IExpression cons2;

    public condStmt (condStmt caexp)
    {
        this.id = caexp.getId();
        this.exp = caexp.getExp();
        this.cons1 = caexp.getCons1();
        this.cons2 = caexp.getCons2();
    }

    public  condStmt (String id, IExpression exp, IExpression cons1, IExpression cons2)
    {
        this.id = id;
        this.exp = exp;
        this.cons1 = cons1;
        this.cons2 = cons2;
    }

    public String getId(){return this.id;}
    public IExpression getExp(){return this.exp;}
    public IExpression getCons1(){return this.cons1;}
    public IExpression getCons2(){return this.cons2;}

    public ProgramState execute(ProgramState state) throws MyStmtExecException {
        IMyDictionary<String, Integer> symTable = state.getSymbolTable();

        try {
            int con1 = cons1.evaluate(symTable, state.getHeap());
            int con2 = cons2.evaluate(symTable,state.getHeap());
            if(exp.evaluate(symTable,state.getHeap())==1)
                symTable.add(id, con1);
            else
                symTable.add(id,con2);
        }catch (MyExprEvalException e)
        {
            throw new MyStmtExecException( e.getMessage()+ "assignment can't be done");
        }
        return null;
    }


    public String toString(){
        return id.toString()+"="+exp+"?"+cons1.toString()+":"+cons2.toString();
    }

    @Override
    public IStatement clone() {
        try{
            return (condStmt)super.clone();
        }catch (CloneNotSupportedException e)
        {
            return new condStmt (this.getId(),this.getExp(),this.getCons1(),this.getCons2());
        }
    }
}