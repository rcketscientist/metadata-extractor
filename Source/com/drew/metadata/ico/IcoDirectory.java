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
package com.drew.metadata.ico;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class IcoDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_IMAGE_TYPE = new IntegerKey(1);

    public static final IntegerKey TAG_IMAGE_WIDTH = new IntegerKey(2);
    public static final IntegerKey TAG_IMAGE_HEIGHT = new IntegerKey(3);
    public static final IntegerKey TAG_COLOUR_PALETTE_SIZE = new IntegerKey(4);
    public static final IntegerKey TAG_COLOUR_PLANES = new IntegerKey(5);
    public static final IntegerKey TAG_CURSOR_HOTSPOT_X = new IntegerKey(6);
    public static final IntegerKey TAG_BITS_PER_PIXEL = new IntegerKey(7);
    public static final IntegerKey TAG_CURSOR_HOTSPOT_Y = new IntegerKey(8);
    public static final IntegerKey TAG_IMAGE_SIZE_BYTES = new IntegerKey(9);
    public static final IntegerKey TAG_IMAGE_OFFSET_BYTES = new IntegerKey(10);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TAG_IMAGE_TYPE, "Image Type");
        _tagNameMap.put(TAG_IMAGE_WIDTH, "Image Width");
        _tagNameMap.put(TAG_IMAGE_HEIGHT, "Image Height");
        _tagNameMap.put(TAG_COLOUR_PALETTE_SIZE, "Colour Palette Size");
        _tagNameMap.put(TAG_COLOUR_PLANES, "Colour Planes");
        _tagNameMap.put(TAG_CURSOR_HOTSPOT_X, "Hotspot X");
        _tagNameMap.put(TAG_BITS_PER_PIXEL, "Bits Per Pixel");
        _tagNameMap.put(TAG_CURSOR_HOTSPOT_Y, "Hotspot Y");
        _tagNameMap.put(TAG_IMAGE_SIZE_BYTES, "Image Size Bytes");
        _tagNameMap.put(TAG_IMAGE_OFFSET_BYTES, "Image Offset Bytes");
    }

    public IcoDirectory()
    {
        this.setDescriptor(new IcoDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "ICO";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
