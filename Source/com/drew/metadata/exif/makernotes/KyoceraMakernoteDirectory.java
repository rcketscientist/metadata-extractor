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

import java.util.HashMap;

/**
 * Describes tags specific to Kyocera and Contax cameras.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class KyoceraMakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_PROPRIETARY_THUMBNAIL = new IntegerKey(0x0001);
    public static final IntegerKey TAG_PRINT_IMAGE_MATCHING_INFO = new IntegerKey(0x0E00);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_PROPRIETARY_THUMBNAIL, "Proprietary Thumbnail Format Data");
        _tagNameMap.put(TAG_PRINT_IMAGE_MATCHING_INFO, "Print Image Matching (PIM) Info");
    }

    public KyoceraMakernoteDirectory()
    {
        this.setDescriptor(new KyoceraMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Kyocera/Contax Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
