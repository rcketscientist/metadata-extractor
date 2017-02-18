package com.drew.metadata;

/**
 * Can we avoid the maps if the properties define themselves?
 */
public class Property<T> implements Key, Description
{
    private T key;
    private String description;

    public Property(T key)
    {
        this.key = key;
    }

    public Property(T key, String description)
    {
        this(key);
        this.description = description;
    }

    public void setKey(T key)
    {
        this.key = key;
    }

    public void setDesription(String desription)
    {
        this.description = desription;
    }

    @Override
    public T getKey()
    {
        return key;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}
