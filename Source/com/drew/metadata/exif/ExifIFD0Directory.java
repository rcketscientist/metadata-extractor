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
import com.drew.metadata.DirectoryBase;
import com.drew.metadata.Key;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Describes Exif tags from the IFD0 directory.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class ExifIFD0Directory extends DirectoryBase
{
    @NotNull
    private final EnumMap<ExifIFD0Keys, Object> _populatedIfD0Properties = new EnumMap<ExifIFD0Keys, Object>(ExifIFD0Keys.class);
    @NotNull
    private final EnumMap<ExifKeys, Object> _populatedProperties = new EnumMap<ExifKeys, Object>(ExifKeys.class);

    public ExifIFD0Directory() {}

    @Override
    protected Object put(Key tag, Object value)
    {
        if (tag instanceof ExifIFD0Keys)
            return _populatedIfD0Properties.put((ExifIFD0Keys) tag, value);
        else if (tag instanceof ExifKeys)
            return _populatedProperties.put((ExifKeys)tag, value);

        return null;    // TODO: We might want to throw here
    }

    @Override
    protected boolean isKeyPopulated(Key tag)
    {
        return _populatedIfD0Properties.containsKey(tag) || _populatedProperties.containsKey(tag);
    }

    @Override
    protected int size()
    {
        return _populatedIfD0Properties.size() + _populatedProperties.size();
    }

    @Override
    protected Object get(Key tagType)
    {
        if (tagType instanceof  ExifIFD0Keys)
            return _populatedIfD0Properties.get(tagType);
        else if (tagType instanceof ExifKeys)
            return _populatedProperties.get(tagType);
        else
            return null;    // Save the search if it's the wrong type
    }

    @Override
    protected boolean hasKey(Key tag)
    {
        return  tag instanceof ExifIFD0Keys && EnumSet.allOf(ExifIFD0Keys.class).contains(tag) ||
                tag instanceof ExifKeys && EnumSet.allOf(ExifKeys.class).contains(tag);
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Exif IFD0";
    }
}
