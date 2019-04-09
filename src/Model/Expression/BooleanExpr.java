package Model.Expression;

import Exceptions.MyExprEvalException;
import Model.ADT.IMyDictionary;
import Model.ADT.IMyHeap;

public class BooleanExpr implements IExpression {

    private IExpression expr1,expr2;
    private String op;

    public BooleanExpr(String op, IExpression expr1,IExpression expr2)
    {
        this.op=op;
        this.expr1=expr1;
        this.expr2=expr2;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> st, IMyHeap<Integer, Integer> heap) throws MyExprEvalException {
        if(op.equals("<"))
            if(expr1.evaluate(st,heap)< expr2.evaluate(st,heap))
                return 1;
            else
                return 0;
        else
        if(op.equals("<="))
            if(expr1.evaluate(st,heap)<= expr2.evaluate(st,heap))
                return 1;
            else
                return 0;
        else
        if(op.equals(">"))
            if(expr1.evaluate(st,heap)> expr2.evaluate(st,heap))
                return 1;
            else
                return 0;
        else
        if(op.equals(">="))
            if(expr1.evaluate(st,heap)>= expr2.evaluate(st,heap))
                return 1;
            else
                return 0;
        else
        if(op.equals("=="))
            if(expr1.evaluate(st,heap)== expr2.evaluate(st,heap))
                return 1;
            else
                return 0;
        else
        if(op.equals("!="))
            if(expr1.evaluate(st,heap)!= expr2.evaluate(st,heap))
                return 1;
            else
                return 0;
        else
            throw new MyExprEvalException("Invalid operator!");
    }

    public String toString()
    {
        return "("+expr1.toString()+op+expr2.toString()+")";
    }
}
