package com.drew.metadata;

public interface Key
{
    String getTagName();
    String getTagType();
    String getSummary();
    String getDescription(DirectoryBase directory);
    Object getValue();
}
