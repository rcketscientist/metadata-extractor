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

package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * Describes tags specific to Sony cameras that use the Sony Type 6 makernote tags.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class SonyType6MakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_MAKERNOTE_THUMB_OFFSET = new IntegerKey(0x0513);
    public static final IntegerKey TAG_MAKERNOTE_THUMB_LENGTH = new IntegerKey(0x0514);
//    public static final IntegerKey TAG_UNKNOWN_1 = new IntegerKey(0x0515);
    public static final IntegerKey TAG_MAKERNOTE_THUMB_VERSION = new IntegerKey(0x2000);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_MAKERNOTE_THUMB_OFFSET, "Makernote Thumb Offset");
        _tagNameMap.put(TAG_MAKERNOTE_THUMB_LENGTH, "Makernote Thumb Length");
//        _tagNameMap.put(TAG_UNKNOWN_1, "Sony-6-0x0203");
        _tagNameMap.put(TAG_MAKERNOTE_THUMB_VERSION, "Makernote Thumb Version");
    }

    public SonyType6MakernoteDirectory()
    {
        this.setDescriptor(new SonyType6MakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Sony Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
