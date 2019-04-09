package Model.Expression;

import Exceptions.DictionaryException;
import Exceptions.MyExprEvalException;
import Model.ADT.IMyDictionary;
import Model.ADT.IMyHeap;

public class HeapReading implements IExpression {

    private String var_name;

    public HeapReading(String st) {this.var_name=st;}
    @Override
    public int evaluate(IMyDictionary<String, Integer> st, IMyHeap<Integer,Integer> heap) throws MyExprEvalException {
        try
        {
            int evalRez = st.find(var_name);
            return heap.find(evalRez);
        }
        catch (DictionaryException e)
        {
            throw new MyExprEvalException(e.getMessage()+" Heap reading error!");
        }
    }

    @Override
    public String toString()
    {
        return "rH("+this.var_name+")";
    }
}
