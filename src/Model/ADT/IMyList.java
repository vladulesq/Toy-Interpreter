package Model.ADT;

import Exceptions.ListException;

import java.util.List;

public interface IMyList<T>
{
    void add(T val);
    void remove(T val) throws ListException;
    boolean isEmpty();
    List<T> getList();
}
