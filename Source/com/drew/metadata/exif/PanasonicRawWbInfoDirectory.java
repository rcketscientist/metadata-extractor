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
public class PanasonicRawWbInfoDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TagNumWbEntries = new IntegerKey(0);

    public static final IntegerKey TagWbType1 = new IntegerKey(1);
    public static final IntegerKey TagWbRbLevels1 = new IntegerKey(2);

    public static final IntegerKey TagWbType2 = new IntegerKey(4);
    public static final IntegerKey TagWbRbLevels2 = new IntegerKey(5);

    public static final IntegerKey TagWbType3 = new IntegerKey(7);
    public static final IntegerKey TagWbRbLevels3 = new IntegerKey(8);

    public static final IntegerKey TagWbType4 = new IntegerKey(10);
    public static final IntegerKey TagWbRbLevels4 = new IntegerKey(11);

    public static final IntegerKey TagWbType5 = new IntegerKey(13);
    public static final IntegerKey TagWbRbLevels5 = new IntegerKey(14);

    public static final IntegerKey TagWbType6 = new IntegerKey(16);
    public static final IntegerKey TagWbRbLevels6 = new IntegerKey(17);

    public static final IntegerKey TagWbType7 = new IntegerKey(19);
    public static final IntegerKey TagWbRbLevels7 = new IntegerKey(20);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TagNumWbEntries, "Num WB Entries");
        _tagNameMap.put(TagWbType1, "WB Type 1");
        _tagNameMap.put(TagWbRbLevels1, "WB RGB Levels 1");
        _tagNameMap.put(TagWbType2, "WB Type 2");
        _tagNameMap.put(TagWbRbLevels2, "WB RGB Levels 2");
        _tagNameMap.put(TagWbType3, "WB Type 3");
        _tagNameMap.put(TagWbRbLevels3, "WB RGB Levels 3");
        _tagNameMap.put(TagWbType4, "WB Type 4");
        _tagNameMap.put(TagWbRbLevels4, "WB RGB Levels 4");
        _tagNameMap.put(TagWbType5, "WB Type 5");
        _tagNameMap.put(TagWbRbLevels5, "WB RGB Levels 5");
        _tagNameMap.put(TagWbType6, "WB Type 6");
        _tagNameMap.put(TagWbRbLevels6, "WB RGB Levels 6");
        _tagNameMap.put(TagWbType7, "WB Type 7");
        _tagNameMap.put(TagWbRbLevels7, "WB RGB Levels 7");
    }

    public PanasonicRawWbInfoDirectory()
    {
        this.setDescriptor(new PanasonicRawWbInfoDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PanasonicRaw WbInfo";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
