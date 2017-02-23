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
import java.util.HashMap;
import java.util.Map;

/**
 * Describes Exif tags from the IFD0 directory.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class ExifIFD0Directory extends ExifDirectoryBase
{
    @NotNull
    private final EnumMap<ExifIFD0Keys, Object> _populatedProperties = new EnumMap<ExifIFD0Keys, Object>(ExifIFD0Keys.class);

    public ExifIFD0Directory() {}

    @Override
    protected Object put(Key tag, Object value)
    {
        if (tag instanceof ExifIFD0Keys)
            return _populatedProperties.put((ExifIFD0Keys) tag, value);

        return super.put(tag, value);
    }

    @Override
    protected boolean isKeyPopulated(Key tag)
    {
        return super.isKeyPopulated(tag) || _populatedProperties.containsKey(tag);
    }

    @Override
    protected int size()
    {
        return super.size() + _populatedProperties.size();
    }

    @Override
    protected Object get(Key tagType)
    {
        if (tagType instanceof  ExifIFD0Keys)
            return _populatedProperties.get(tagType);

        return super.get(tagType);
    }

    @Override
    protected boolean hasKey(Key tag)
    {
        return tag instanceof ExifIFD0Keys && getTagSet().contains(tag) || super.hasKey(tag);
    }

    private EnumSet<ExifIFD0Keys> getTagSet()
    {
        return EnumSet.allOf(ExifIFD0Keys.class);
    }

//    @Override
//    protected ExifIFD0Keys getTagFromValue(Integer value)
//    {
//        return super.getTagFromValue(value);
//    }

    @Override
    @NotNull
    public String getName()
    {
        return "Exif IFD0";
    }
}
