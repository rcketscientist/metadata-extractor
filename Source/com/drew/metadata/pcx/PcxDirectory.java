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
package com.drew.metadata.pcx;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class PcxDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_VERSION        = new IntegerKey(1);
    public static final IntegerKey TAG_BITS_PER_PIXEL = new IntegerKey(2);
    public static final IntegerKey TAG_XMIN           = new IntegerKey(3);
    public static final IntegerKey TAG_YMIN           = new IntegerKey(4);
    public static final IntegerKey TAG_XMAX           = new IntegerKey(5);
    public static final IntegerKey TAG_YMAX           = new IntegerKey(6);
    public static final IntegerKey TAG_HORIZONTAL_DPI = new IntegerKey(7);
    public static final IntegerKey TAG_VERTICAL_DPI   = new IntegerKey(8);
    public static final IntegerKey TAG_PALETTE        = new IntegerKey(9);
    public static final IntegerKey TAG_COLOR_PLANES   = new IntegerKey(10);
    public static final IntegerKey TAG_BYTES_PER_LINE = new IntegerKey(11);
    public static final IntegerKey TAG_PALETTE_TYPE   = new IntegerKey(12);
    public static final IntegerKey TAG_HSCR_SIZE      = new IntegerKey(13);
    public static final IntegerKey TAG_VSCR_SIZE      = new IntegerKey(14);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TAG_VERSION, "Version");
        _tagNameMap.put(TAG_BITS_PER_PIXEL, "Bits Per Pixel");
        _tagNameMap.put(TAG_XMIN, "X Min");
        _tagNameMap.put(TAG_YMIN, "Y Min");
        _tagNameMap.put(TAG_XMAX, "X Max");
        _tagNameMap.put(TAG_YMAX, "Y Max");
        _tagNameMap.put(TAG_HORIZONTAL_DPI, "Horizontal DPI");
        _tagNameMap.put(TAG_VERTICAL_DPI, "Vertical DPI");
        _tagNameMap.put(TAG_PALETTE, "Palette");
        _tagNameMap.put(TAG_COLOR_PLANES, "Color Planes");
        _tagNameMap.put(TAG_BYTES_PER_LINE, "Bytes Per Line");
        _tagNameMap.put(TAG_PALETTE_TYPE, "Palette Type");
        _tagNameMap.put(TAG_HSCR_SIZE, "H Scr Size");
        _tagNameMap.put(TAG_VSCR_SIZE, "V Scr Size");
    }

    public PcxDirectory()
    {
        this.setDescriptor(new PcxDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PCX";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
