package Model.ADT;

import Exceptions.ListException;
import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IMyList<T> {

    private List<T> list;

    public MyList()
    {
        this.list=new ArrayList<T>();
    }

    @Override
    public void add(T val)
    {
        this.list.add(val);
    }

    @Override
    public void remove(T val) throws ListException {
        if(this.list.contains(val))
            this.list.remove(val);
        else
            throw new ListException("The element is not in the list!Cannot be removed!");
    }

    @Override
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString()
    {
        String str="";
        for (T el:list) {
            str=str.concat(el.toString()+'\n');
        }
        return str;
    }
}
