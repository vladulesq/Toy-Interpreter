package Model.Expression;

import Exceptions.MyExprEvalException;
import Model.ADT.IMyDictionary;
import Model.ADT.IMyHeap;

public class ArithExp implements IExpression
{
    private IExpression expr1;
    private IExpression expr2;
    private int op;

    public ArithExp(int op1,IExpression a1,IExpression a2)
    {
        this.expr1=a1;
        this.expr2=a2;
        this.op=op1;
    }

    @Override
    public int evaluate(IMyDictionary<String,Integer> st, IMyHeap<Integer,Integer> heap) throws MyExprEvalException
    {
        if(op==1)
            return expr1.evaluate(st,heap)+ expr2.evaluate(st,heap);
        else
        if(op==2)
            return expr1.evaluate(st,heap)- expr2.evaluate(st,heap);
        else
        if(op==3)
            return expr1.evaluate(st,heap)* expr2.evaluate(st,heap);
        else
        if(op==4)
            return expr1.evaluate(st,heap)/ expr2.evaluate(st,heap);
        else
            throw new MyExprEvalException("Invalid operator!");
    }

    @Override
    public String toString()
    {
        switch (op)
        {
            case 1: {
                return "("+expr1.toString() + "+" + expr2.toString()+")";
            }
            case 2:{
                return "("+expr1.toString() + "-" + expr2.toString()+")";
            }
            case 3:{
                return "("+expr1.toString() + "*" + expr2.toString()+")";
            }
            case 4:{
                return "("+expr1.toString() + "/" + expr2.toString()+")";
            }
            default: return null;
        }
    }
}
