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
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * These tags can be found in Panasonic/Leica RAW, RW2 and RWL images. The index values are 'fake' but
 * chosen specifically to make processing easier
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class PanasonicRawWbInfo2Directory extends Directory<IntegerKey>
{
    public static final IntegerKey TagNumWbEntries = new IntegerKey(0);

    public static final IntegerKey TagWbType1 = new IntegerKey(1);
    public static final IntegerKey TagWbRgbLevels1 = new IntegerKey(2);

    public static final IntegerKey TagWbType2 = new IntegerKey(5);
    public static final IntegerKey TagWbRgbLevels2 = new IntegerKey(6);

    public static final IntegerKey TagWbType3 = new IntegerKey(9);
    public static final IntegerKey TagWbRgbLevels3 = new IntegerKey(10);

    public static final IntegerKey TagWbType4 = new IntegerKey(13);
    public static final IntegerKey TagWbRgbLevels4 = new IntegerKey(14);

    public static final IntegerKey TagWbType5 = new IntegerKey(17);
    public static final IntegerKey TagWbRgbLevels5 = new IntegerKey(18);

    public static final IntegerKey TagWbType6 = new IntegerKey(21);
    public static final IntegerKey TagWbRgbLevels6 = new IntegerKey(22);

    public static final IntegerKey TagWbType7 = new IntegerKey(25);
    public static final IntegerKey TagWbRgbLevels7 = new IntegerKey(26);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TagNumWbEntries, "Num WB Entries");
        _tagNameMap.put(TagNumWbEntries, "Num WB Entries");
        _tagNameMap.put(TagWbType1, "WB Type 1");
        _tagNameMap.put(TagWbRgbLevels1, "WB RGB Levels 1");
        _tagNameMap.put(TagWbType2, "WB Type 2");
        _tagNameMap.put(TagWbRgbLevels2, "WB RGB Levels 2");
        _tagNameMap.put(TagWbType3, "WB Type 3");
        _tagNameMap.put(TagWbRgbLevels3, "WB RGB Levels 3");
        _tagNameMap.put(TagWbType4, "WB Type 4");
        _tagNameMap.put(TagWbRgbLevels4, "WB RGB Levels 4");
        _tagNameMap.put(TagWbType5, "WB Type 5");
        _tagNameMap.put(TagWbRgbLevels5, "WB RGB Levels 5");
        _tagNameMap.put(TagWbType6, "WB Type 6");
        _tagNameMap.put(TagWbRgbLevels6, "WB RGB Levels 6");
        _tagNameMap.put(TagWbType7, "WB Type 7");
        _tagNameMap.put(TagWbRgbLevels7, "WB RGB Levels 7");
    }

    public PanasonicRawWbInfo2Directory()
    {
        this.setDescriptor(new PanasonicRawWbInfo2Descriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PanasonicRaw WbInfo2";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
