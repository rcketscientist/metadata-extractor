package com.drew.metadata;

import java.util.Collection;

public interface Directory
{
    int getTagCount();
    Collection<Key> getTags();
    boolean isEmpty();
    boolean hasErrors();
    Iterable<String> getErrors();
    String getName();
}
