package Model.ADT;

import Exceptions.StackException;
import java.util.Stack;

public class MyStack<T> implements IMyStack<T>
{
    private Stack<T> stack;
    public MyStack()
    {
        this.stack=new Stack<T>();
    }

    @Override
    public void push(T e) {
//        if(stack.capacity()>stack.size())
//            this.stack.add(e);
//        else
//            throw new StackException("The stack is full!");
        this.stack.add(e);
    }

    @Override
    public boolean isEmpty()
    {
        return this.stack.isEmpty();
    }

    @Override
    public T pop() throws StackException
    {
        if(!isEmpty())
            return this.stack.pop();
        throw new StackException("There are no elements in the stack!");
    }

    @Override
    public T top() throws StackException
    {
        if(!isEmpty())
            return this.stack.peek();
        else
            throw new StackException("The stack is empty!");
    }

    @Override
    public String toString()
    {
        String str="";
        for (int i=stack.size()-1;i>=0;i--)
            str=str.concat(stack.get(i).toString()+'\n');

        return str;
    }
}
