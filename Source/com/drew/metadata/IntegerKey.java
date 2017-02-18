package com.drew.metadata;

public class IntegerKey implements Key
{
    Integer key;
    public IntegerKey(int  key)
    {
        this.key = key;
    }

    @Override
    public Integer getKey()
    {
        return key;
    }

    @Override
    public String toString()
    {
        return Integer.toHexString(key);
    }
}
