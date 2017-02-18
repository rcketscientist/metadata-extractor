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
package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * @author Drew Noakes https://drewnoakes.com
 * @author Kevin Mott https://github.com/kwhopper
 */
@SuppressWarnings("WeakerAccess")
public class GifImageDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_LEFT = new IntegerKey(1);
    public static final IntegerKey TAG_TOP = new IntegerKey(2);
    public static final IntegerKey TAG_WIDTH = new IntegerKey(3);
    public static final IntegerKey TAG_HEIGHT = new IntegerKey(4);
    public static final IntegerKey TAG_HAS_LOCAL_COLOUR_TABLE = new IntegerKey(5);
    public static final IntegerKey TAG_IS_INTERLACED = new IntegerKey(6);
    public static final IntegerKey TAG_IS_COLOR_TABLE_SORTED = new IntegerKey(7);
    public static final IntegerKey TAG_LOCAL_COLOUR_TABLE_BITS_PER_PIXEL = new IntegerKey(8);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_LEFT, "Left");
        _tagNameMap.put(TAG_TOP, "Top");
        _tagNameMap.put(TAG_WIDTH, "Width");
        _tagNameMap.put(TAG_HEIGHT, "Height");
        _tagNameMap.put(TAG_HAS_LOCAL_COLOUR_TABLE, "Has Local Colour Table");
        _tagNameMap.put(TAG_IS_INTERLACED, "Is Interlaced");
        _tagNameMap.put(TAG_IS_COLOR_TABLE_SORTED, "Is Local Colour Table Sorted");
        _tagNameMap.put(TAG_LOCAL_COLOUR_TABLE_BITS_PER_PIXEL, "Local Colour Table Bits Per Pixel");
    }

    public GifImageDirectory()
    {
        this.setDescriptor(new GifImageDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "GIF Image";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
