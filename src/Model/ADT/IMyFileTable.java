package Model.ADT;

import Exceptions.DictionaryException;

import java.util.Collection;
import java.util.Map;

public interface IMyFileTable<T1,T2> {
    void add(T1 key, T2 val);
    T2 find(T1 key) throws DictionaryException;
    void remove(T1 key) throws DictionaryException;
    boolean isDefined(T1 id);
    void update(T1 id,T2 val) throws DictionaryException;

    Map<T1,T2> getContent();
    void setContent(Map<T1,T2> m);
    Collection<T2> getValues();
    Integer computeUniqueKey();
}
