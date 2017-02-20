package com.drew.metadata;

public interface Key
{
    String getTagName();
    String getTagType();
    String getDescription();
    Object getValue();
}
