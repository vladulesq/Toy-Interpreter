package Model.ADT;

import Exceptions.DictionaryException;

import java.util.Map;

public interface IMyHeap<T1,T2> {
    void add(T1 key, T2 val);
    T2 find(T1 key) throws DictionaryException;
    void remove(T1 key) throws DictionaryException;
    boolean isDefined(T1 id);
    void update(T1 id,T2 val) throws DictionaryException;

    void setContent(Map<T1,T2> m);
    Map<T1,T2> getContent();

    int getNewFreeLocation();
}
