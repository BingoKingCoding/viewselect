package com.bingo.viewselect;

import java.util.ArrayList;
import java.util.List;

/**
 * <内部用ArrayList实现的多对象持有管理类>
 */

public class FObjectsHolder<T> implements FIObjectsHolder<T>
{
    private List<T> mListObject = new ArrayList<T>();

    @Override
    public synchronized void add(T object)
    {
        if (object == null)
        {
            return;
        }
        if (!mListObject.contains(object))
        {
            mListObject.add(object);
        }
    }

    @Override
    public synchronized boolean remove(T object)
    {
        if (object == null)
        {
            return false;
        }
        return mListObject.remove(object);
    }

    @Override
    public synchronized boolean contains(T object)
    {
        if (object == null)
        {
            return false;
        }
        return mListObject.contains(object);
    }

    @Override
    public int size()
    {
        return mListObject.size();
    }

    @Override
    public synchronized void clear()
    {
        mListObject.clear();
    }

    @Override
    public synchronized boolean foreach(FIterateCallback<T> callback)
    {
        return FCollectionUtil.foreach(mListObject, callback);
    }

    @Override
    public synchronized boolean foreachReverse(FIterateCallback<T> callback)
    {
        return FCollectionUtil.foreachReverse(mListObject, callback);
    }
}
