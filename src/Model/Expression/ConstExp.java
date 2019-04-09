package Model.Expression;

import Model.ADT.IMyDictionary;
import Model.ADT.IMyHeap;

public class ConstExp implements IExpression
{
    private int number;

    public ConstExp(int val)
    {
        this.number=val;
    }

    @Override
    public int evaluate(IMyDictionary<String,Integer> st, IMyHeap<Integer,Integer> heap)
    {
        return this.number;
    }

    @Override
    public String toString()
    {
        Integer a=(Integer)number;
        return a.toString();
    }
}
