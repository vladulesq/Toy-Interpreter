package Model.ADT;

import Exceptions.StackException;

public interface IMyStack<T>
{
    void push(T v);
    boolean isEmpty();
    T pop() throws StackException;
    T top() throws StackException;
}
