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
package com.drew.metadata.png;

import com.drew.imaging.png.PngChunkType;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class PngDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_IMAGE_WIDTH = new IntegerKey(1);
    public static final IntegerKey TAG_IMAGE_HEIGHT = new IntegerKey(2);
    public static final IntegerKey TAG_BITS_PER_SAMPLE = new IntegerKey(3);
    public static final IntegerKey TAG_COLOR_TYPE = new IntegerKey(4);
    public static final IntegerKey TAG_COMPRESSION_TYPE = new IntegerKey(5);
    public static final IntegerKey TAG_FILTER_METHOD = new IntegerKey(6);
    public static final IntegerKey TAG_INTERLACE_METHOD = new IntegerKey(7);
    public static final IntegerKey TAG_PALETTE_SIZE = new IntegerKey(8);
    public static final IntegerKey TAG_PALETTE_HAS_TRANSPARENCY = new IntegerKey(9);
    public static final IntegerKey TAG_SRGB_RENDERING_INTENT = new IntegerKey(10);
    public static final IntegerKey TAG_GAMMA = new IntegerKey(11);
    public static final IntegerKey TAG_ICC_PROFILE_NAME = new IntegerKey(12);
    public static final IntegerKey TAG_TEXTUAL_DATA = new IntegerKey(13);
    public static final IntegerKey TAG_LAST_MODIFICATION_TIME = new IntegerKey(14);
    public static final IntegerKey TAG_BACKGROUND_COLOR = new IntegerKey(15);

    public static final IntegerKey TAG_PIXELS_PER_UNIT_X = new IntegerKey(16);
    public static final IntegerKey TAG_PIXELS_PER_UNIT_Y = new IntegerKey(17);
    public static final IntegerKey TAG_UNIT_SPECIFIER = new IntegerKey(18);

    public static final IntegerKey TAG_SIGNIFICANT_BITS = new IntegerKey(19);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TAG_IMAGE_HEIGHT, "Image Height");
        _tagNameMap.put(TAG_IMAGE_WIDTH, "Image Width");
        _tagNameMap.put(TAG_BITS_PER_SAMPLE, "Bits Per Sample");
        _tagNameMap.put(TAG_COLOR_TYPE, "Color Type");
        _tagNameMap.put(TAG_COMPRESSION_TYPE, "Compression Type");
        _tagNameMap.put(TAG_FILTER_METHOD, "Filter Method");
        _tagNameMap.put(TAG_INTERLACE_METHOD, "Interlace Method");
        _tagNameMap.put(TAG_PALETTE_SIZE, "Palette Size");
        _tagNameMap.put(TAG_PALETTE_HAS_TRANSPARENCY, "Palette Has Transparency");
        _tagNameMap.put(TAG_SRGB_RENDERING_INTENT, "sRGB Rendering Intent");
        _tagNameMap.put(TAG_GAMMA, "Image Gamma");
        _tagNameMap.put(TAG_ICC_PROFILE_NAME, "ICC Profile Name");
        _tagNameMap.put(TAG_TEXTUAL_DATA, "Textual Data");
        _tagNameMap.put(TAG_LAST_MODIFICATION_TIME, "Last Modification Time");
        _tagNameMap.put(TAG_BACKGROUND_COLOR, "Background Color");
        _tagNameMap.put(TAG_PIXELS_PER_UNIT_X, "Pixels Per Unit X");
        _tagNameMap.put(TAG_PIXELS_PER_UNIT_Y, "Pixels Per Unit Y");
        _tagNameMap.put(TAG_UNIT_SPECIFIER, "Unit Specifier");
        _tagNameMap.put(TAG_SIGNIFICANT_BITS, "Significant Bits");
    }

    private final PngChunkType _pngChunkType;

    public PngDirectory(@NotNull PngChunkType pngChunkType)
    {
        _pngChunkType = pngChunkType;

        this.setDescriptor(new PngDescriptor(this));
    }

    @NotNull
    public PngChunkType getPngChunkType()
    {
        return _pngChunkType;
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PNG-" + _pngChunkType.getIdentifier();
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
