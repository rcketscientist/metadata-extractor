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
package com.drew.metadata.bmp;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;

import java.util.HashMap;

/**
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class BmpHeaderDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_HEADER_SIZE = -1;

    public static final IntegerKey TAG_IMAGE_HEIGHT = new IntegerKey(1);
    public static final IntegerKey TAG_IMAGE_WIDTH = new IntegerKey(2);
    public static final IntegerKey TAG_COLOUR_PLANES = new IntegerKey(3);
    public static final IntegerKey TAG_BITS_PER_PIXEL = new IntegerKey(4);
    public static final IntegerKey TAG_COMPRESSION = new IntegerKey(5);
    public static final IntegerKey TAG_X_PIXELS_PER_METER = new IntegerKey(6);
    public static final IntegerKey TAG_Y_PIXELS_PER_METER = new IntegerKey(7);
    public static final IntegerKey TAG_PALETTE_COLOUR_COUNT = new IntegerKey(8);
    public static final IntegerKey TAG_IMPORTANT_COLOUR_COUNT = new IntegerKey(9);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TAG_HEADER_SIZE, "Header Size");

        _tagNameMap.put(TAG_IMAGE_HEIGHT, "Image Height");
        _tagNameMap.put(TAG_IMAGE_WIDTH, "Image Width");
        _tagNameMap.put(TAG_COLOUR_PLANES, "Planes");
        _tagNameMap.put(TAG_BITS_PER_PIXEL, "Bits Per Pixel");
        _tagNameMap.put(TAG_COMPRESSION, "Compression");
        _tagNameMap.put(TAG_X_PIXELS_PER_METER, "X Pixels per Meter");
        _tagNameMap.put(TAG_Y_PIXELS_PER_METER, "Y Pixels per Meter");
        _tagNameMap.put(TAG_PALETTE_COLOUR_COUNT, "Palette Colour Count");
        _tagNameMap.put(TAG_IMPORTANT_COLOUR_COUNT, "Important Colour Count");
    }

    public BmpHeaderDirectory()
    {
        this.setDescriptor(new BmpHeaderDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "BMP Header";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
