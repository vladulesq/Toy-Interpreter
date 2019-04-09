package Model.Expression;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Model.ADT.IMyDictionary;
import Model.ADT.IMyHeap;

public class VarExp implements IExpression
{
    private String id;

    public VarExp(String st)
    {
        this.id=st;
    }

    @Override
    public int evaluate(IMyDictionary<String,Integer> st, IMyHeap<Integer,Integer> heap) throws MyExprEvalException
    {
        try
        {
            return st.find(id);
        }
        catch (DictionaryException e)
        {
            throw new MyExprEvalException(e.getMessage()+" The evaluation cannot be done!");
        }
    }

    @Override
    public String toString()
    {
        return id;
    }
}
