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
import com.drew.metadata.Directory;
import com.drew.metadata.DirectoryBase;
import com.drew.metadata.IntegerKey;
import com.drew.metadata.Key;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * These tags can be found in Panasonic/Leica RAW, RW2 and RWL images. The index values are 'fake' but
 * chosen specifically to make processing easier
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class PanasonicRawDistortionDirectory extends DirectoryBase<Integer, PanasonicRawDistortionDirectory.Keys>
{
    // 0 and 1 are checksums
    public enum Keys implements IntegerKey
    {
        TagDistortionParam02(   2,  "Distortion Param 2"),
        TagDistortionParam04(   4,  "Distortion Param 4"),
        TagDistortionScale(     5,  "Distortion Scale"),
        TagDistortionCorrection(7,  "Distortion Correction"),
        TagDistortionParam08(   8,  "Distortion Param 8"),
        TagDistortionParam09(   9,  "Distortion Param 9"),
        TagDistortionParam11(   11, "Distortion Param 11"),
        TagDistortionN(         12, "Distortion N");

        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();
        static {
            for (Keys type : values())
                lookup.put(type.getValue(), type);
        }

        private final int key;
        private final String description;
        Keys(int key, String description)
        {
            this.key = key;
            this.description = description;
        }

        public Integer getValue()
        {
            return key;
        }

        public static @Nullable Keys fromValue(Integer value)
        {
            return lookup.get(value);
        }

        @Override
        public String getTagName()
        {
            return name();
        }

        @Override
        public String getTagType()
        {
            return Integer.toString(key);
        }

        @Override
        public String getDescription()
        {
            return description;
        }
    }

    public PanasonicRawDistortionDirectory()
    {
        super(Keys.class);
        this.setDescriptor(new PanasonicRawDistortionDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PanasonicRaw DistortionInfo";
    }

    @Override
    protected EnumSet<Keys> getTagSet()
    {
        return EnumSet.allOf(Keys.class);
    }

    @Override
    protected Keys getTagFromValue(Integer value)
    {
        return Keys.fromValue(value);
    }
}
