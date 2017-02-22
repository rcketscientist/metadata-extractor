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
    private final EnumMap<Keys, Object> _populatedProperties = new EnumMap<Keys, Object>(Keys.class);

    public ExifIFD0Directory() {}

    @Override
    protected Object put(Key tag, Object value)
    {
        if (tag instanceof Keys)
            return _populatedProperties.put((Keys) tag, value);

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
        if (tagType instanceof  Keys)
            return _populatedProperties.get(tagType);

        return super.get(tagType);
    }

    @Override
    protected boolean hasKey(Key tag)
    {
        return tag instanceof Keys && getTagSet().contains(tag) || super.hasKey(tag);
    }

    private EnumSet<Keys> getTagSet()
    {
        return EnumSet.allOf(Keys.class);
    }

    @Override
    protected ExifDirectoryBase.Keys getTagFromValue(Integer value)
    {
        return super.getTagFromValue(value);
    }

    public enum Keys implements Key
    {
        TAG_EXIF_SUB_IFD_OFFSET(0x8769, "Exif SubIfd pointer"),
        TAG_GPS_INFO_OFFSET(0x8825, "Exif GPS IFD pointer");

        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();

        static
        {
            for (Keys type : values())
                lookup.put(type.getValue(), type);
        }

        private final int key;
        private final String summary;

        Keys(int key, String summary)
        {
            this.key = key;
            this.summary = summary;
        }

        public Integer getValue()
        {
            return key;
        }

        public static
        @Nullable
        Keys fromValue(Integer value)
        {
            return lookup.get(value);
        }

        @Override
        public String getName()
        {
            return name();
        }

        @Override
        public String getType()
        {
            return Integer.toString(key);
        }

        @Override
        public String getSummary()
        {
            return summary;
        }

        /**
         * Default description handler.
         *
         * @param directory storing the values to describe
         * @return
         */
        @Override
        public String getDescription(DirectoryBase directory)
        {
            return directory.getDescription(this);
        }
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Exif IFD0";
    }
}
