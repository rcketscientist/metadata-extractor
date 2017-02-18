package com.drew.metadata;

public class StringKey implements Key
{
    String key;
    public StringKey(String key)
    {
        this.key = key;
    }

    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public String toString()
    {
        return key;
    }
}
