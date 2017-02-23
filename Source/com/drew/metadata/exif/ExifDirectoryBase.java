/*
 * Copyright 2002-2017 Drew Noakes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * More information about this project is available at:
 *
 *    https://drewnoakes.com/code/exif/
 *    https://github.com/drewnoakes/metadata-extractor
 */

package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.DirectoryBase;
import com.drew.metadata.Key;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Base class for several Exif format tag directories.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")   //TODO: What if we chain in the sub key class?  We might be able to handle all logic in the abstract.
public abstract class ExifDirectoryBase extends DirectoryBase</*Integer, */ExifKeys>
{
    public ExifDirectoryBase() {}

    @NotNull
    private final EnumMap<ExifKeys, Object> _populatedProperties = new EnumMap<ExifKeys, Object>(ExifKeys.class);

    @Override
    protected Object put(Key tag, Object value)
    {
        if (tag instanceof ExifKeys)
            return _populatedProperties.put((ExifKeys)tag, value);
        return null;
    }

    @Override
    protected boolean isKeyPopulated(Key tag)
    {
        // typesafe, returns false if wrong type
        return _populatedProperties.containsKey(tag);
    }

    @Override
    protected int size()
    {
        return _populatedProperties.size();
    }

    @Override
    protected Object get(Key tagType)
    {
        // typesafe, returns null if wrong type
        return _populatedProperties.get(tagType);
    }

    @Override
    protected boolean hasKey(Key tag)
    {
        return tag instanceof ExifKeys && getTagSet().contains(tag);
    }

    private EnumSet<ExifKeys> getTagSet()
    {
        return EnumSet.allOf(ExifKeys.class);
    }

//    @Override
//    protected EnumMap<ExifKeys, Object> getTagMap()
//    {
//        return null;
//    }

//    @Override
//    protected ExifKeys getTagFromValue(Integer value)
//    {
//        return ExifKeys.fromValue(value);
//    }
}
