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
 * Describes tags specific to Nikon (type 1) cameras.  Type-1 is for E-Series cameras prior to (not including) E990.
 *
 * There are 3 formats of Nikon's Makernote. Makernote of E700/E800/E900/E900S/E910/E950
 * starts from ASCII string "Nikon". Data format is the same as IFD, but it starts from
 * offset 0x08. This is the same as Olympus except start string. Example of actual data
 * structure is shown below.
 * <pre><code>
 * :0000: 4E 69 6B 6F 6E 00 01 00-05 00 02 00 02 00 06 00 Nikon...........
 * :0010: 00 00 EC 02 00 00 03 00-03 00 01 00 00 00 06 00 ................
 * </code></pre>
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class NikonType1MakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_UNKNOWN_1 = new IntegerKey(0x0002);
    public static final IntegerKey TAG_QUALITY = new IntegerKey(0x0003);
    public static final IntegerKey TAG_COLOR_MODE = new IntegerKey(0x0004);
    public static final IntegerKey TAG_IMAGE_ADJUSTMENT = new IntegerKey(0x0005);
    public static final IntegerKey TAG_CCD_SENSITIVITY = new IntegerKey(0x0006);
    public static final IntegerKey TAG_WHITE_BALANCE = new IntegerKey(0x0007);
    public static final IntegerKey TAG_FOCUS = new IntegerKey(0x0008);
    public static final IntegerKey TAG_UNKNOWN_2 = new IntegerKey(0x0009);
    public static final IntegerKey TAG_DIGITAL_ZOOM = new IntegerKey(0x000A);
    public static final IntegerKey TAG_CONVERTER = new IntegerKey(0x000B);
    public static final IntegerKey TAG_UNKNOWN_3 = new IntegerKey(0x0F00);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_CCD_SENSITIVITY, "CCD Sensitivity");
        _tagNameMap.put(TAG_COLOR_MODE, "Color Mode");
        _tagNameMap.put(TAG_DIGITAL_ZOOM, "Digital Zoom");
        _tagNameMap.put(TAG_CONVERTER, "Fisheye Converter");
        _tagNameMap.put(TAG_FOCUS, "Focus");
        _tagNameMap.put(TAG_IMAGE_ADJUSTMENT, "Image Adjustment");
        _tagNameMap.put(TAG_QUALITY, "Quality");
        _tagNameMap.put(TAG_UNKNOWN_1, "Makernote Unknown 1");
        _tagNameMap.put(TAG_UNKNOWN_2, "Makernote Unknown 2");
        _tagNameMap.put(TAG_UNKNOWN_3, "Makernote Unknown 3");
        _tagNameMap.put(TAG_WHITE_BALANCE, "White Balance");
    }

    public NikonType1MakernoteDirectory()
    {
        this.setDescriptor(new NikonType1MakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Nikon Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
