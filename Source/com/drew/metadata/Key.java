package com.drew.metadata;

/**
 * This interface is a helper to enforce the extended Enum structure that allows conversion to and from
 * the inherent metadata key type (ex: int, string, compound, etc)
 * @param <K>
 */
public interface Key<K>
{
    K getValue();
//    Enum getTagFromValue(K value);
}
