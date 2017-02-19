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

import java.util.EnumMap;
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
public class PanasonicRawDistortionDirectory extends Directory<Integer, PanasonicRawDistortionDirectory.Keys>
{
    // 0 and 1 are checksums
    public enum Keys
    {
        TagDistortionParam02(2),
        TagDistortionParam04(4),
        TagDistortionScale(5),
        TagDistortionCorrection(7),
        TagDistortionParam08(8),
        TagDistortionParam09(9),
        TagDistortionParam11(11),
        TagDistortionN(12);

        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();
        static {
            for (Keys type : values())
                lookup.put(type.getValue(), type);
        }

        private final int key;
        Keys(int key)
        {
            this.key = key;
        }

        public Integer getValue()
        {
            return key;
        }

        public static @Nullable Keys fromValue(Integer value)
        {
            return lookup.get(value);
        }
    }

    @NotNull
    protected static final EnumMap<Keys, String> _tagNameMap = new EnumMap<Keys, String>(Keys.class);
    @NotNull
    protected static final EnumMap<Keys, Object> _tagMap = new EnumMap<Keys, Object>(Keys.class);

    static
    {
        _tagNameMap.put(Keys.TagDistortionParam02, "Distortion Param 2");
        _tagNameMap.put(Keys.TagDistortionParam04, "Distortion Param 4");
        _tagNameMap.put(Keys.TagDistortionScale, "Distortion Scale");
        _tagNameMap.put(Keys.TagDistortionCorrection, "Distortion Correction");
        _tagNameMap.put(Keys.TagDistortionParam08, "Distortion Param 8");
        _tagNameMap.put(Keys.TagDistortionParam09, "Distortion Param 9");
        _tagNameMap.put(Keys.TagDistortionParam11, "Distortion Param 11");
        _tagNameMap.put(Keys.TagDistortionN, "Distortion N");
    }

    public PanasonicRawDistortionDirectory()
    {
        this.setDescriptor(new PanasonicRawDistortionDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PanasonicRaw DistortionInfo";
    }

    @Override
    protected EnumMap<Keys, String> getTagNameMap()
    {
        return _tagNameMap;
    }

    @Override
    protected EnumMap<Keys, Object> getTagMap()
    {
        return _tagMap;
    }

    @Override
    protected Keys getTagFromValue(Integer value)
    {
        return Keys.fromValue(value);
    }
}
