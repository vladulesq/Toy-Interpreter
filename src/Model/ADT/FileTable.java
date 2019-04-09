package Model.ADT;

import Exceptions.DictionaryException;
import Model.Utils.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileTable<T1,T2> implements IMyFileTable<T1,T2>
{
    private Integer uniqueKey;
    private Map<T1,T2> dictionary;

    public FileTable()
    {
        this.dictionary=new HashMap<T1,T2>();
        this.uniqueKey=10000;
    }
    @Override
    public void add(T1 key, T2 val)
    {
        this.dictionary.put(key,val);
    }

    @Override
    public T2 find(T1 key) throws DictionaryException
    {
        T2 el=dictionary.get(key);
        if(el!=null)
            return el;
        throw new DictionaryException("The variable "+key.toString()+" is not defined!");
    }

    @Override
    public void remove(T1 key) throws DictionaryException
    {
        try
        {
            find(key);
        }
        catch (DictionaryException e)
        {
            throw new DictionaryException(e.getMessage()+"Nothing to remove!");
        }
        dictionary.remove(key);
    }

    @Override
    public boolean isDefined(T1 id)
    {
        return dictionary.containsKey(id);
    }

    @Override
    public void update(T1 id, T2 val) throws DictionaryException
    {
        try
        {
            find(id);
        }
        catch (DictionaryException e)
        {
            throw new DictionaryException(e.getMessage()+" Nothing to update!");
        }
        dictionary.put(id,val);
    }

    @Override
    public Map<T1, T2> getContent() {
        return this.dictionary;
    }

    @Override
    public void setContent(Map<T1, T2> m) {
        this.dictionary=m;
    }

    @Override
    public String toString()
    {
        String str="";
        for(HashMap.Entry<T1,T2> entry: dictionary.entrySet())
            str=str.concat(entry.getKey()+"-->"+entry.getValue().toString()+'\n');
        return str;
    }

    public Collection<T2> getValues()
    {
        return this.dictionary.values();
    }

    public Integer computeUniqueKey()
    {
        this.uniqueKey+=1;
        return this.uniqueKey;
    }

}
