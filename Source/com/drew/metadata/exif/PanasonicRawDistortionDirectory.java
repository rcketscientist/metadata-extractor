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

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.DirectoryBase;
import com.drew.metadata.Key;

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
    public enum Keys implements Key
    {
        TagDistortionParam02(   2,  "Distortion Param 2")
            {
                @Override
                public String getDescription(DirectoryBase directory)
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    return new Rational((Integer)storedValue, 32678).toString();
                }
            },
        TagDistortionParam04(   4,  "Distortion Param 4")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    return new Rational((Integer)storedValue, 32678).toString();
                }
            },
        TagDistortionScale(     5,  "Distortion Scale")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    //return (1 / (1 + value / 32768)).toString();
                    return Integer.toString(1 / (1 + (Integer)storedValue / 32768));
                }
            },
        TagDistortionCorrection(7,  "Distortion Correction")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    // (have seen the upper 4 bits set for GF5 and GX1, giving a value of -4095 - PH)
                    int mask = 0x000f;
                    switch ((Integer)storedValue & mask)
                    {
                        case 0:
                            return "Off";
                        case 1:
                            return "On";
                        default:
                            return "Unknown (" + storedValue + ")";
                    }
                }
            },
        TagDistortionParam08(   8,  "Distortion Param 8")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    return new Rational((Integer)storedValue, 32678).toString();
                }
            },
        TagDistortionParam09(   9,  "Distortion Param 9")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    return new Rational((Integer)storedValue, 32678).toString();
                }
            },
        TagDistortionParam11(   11, "Distortion Param 11")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    if (!(storedValue instanceof Integer))
                        return null;

                    return new Rational((Integer)storedValue, 32678).toString();
                }
            },
        TagDistortionN(         12, "Distortion N")
            {
                @Override
                public String getDescription(DirectoryBase directory))
                {
                    return null;
                }
            };

        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();
        static {
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
        public String getSummary()
        {
            return summary;
        }
    }

    public PanasonicRawDistortionDirectory()
    {
        super(Keys.class);
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
