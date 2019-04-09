package Model.Expression;

import Exceptions.MyExprEvalException;
import Model.ADT.IMyDictionary;
import Model.ADT.IMyHeap;

public interface IExpression
{
    int evaluate(IMyDictionary<String,Integer> st, IMyHeap<Integer,Integer> heap) throws MyExprEvalException;
}
