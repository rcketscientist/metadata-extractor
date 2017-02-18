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
 * Describes tags specific to Reconyx UltraFire cameras.
 *
 * Reconyx uses a fixed makernote block.  Tag values are the byte index of the tag within the makernote.
 * @author Todd West http://cascadescarnivoreproject.blogspot.com
 */
@SuppressWarnings("WeakerAccess")
public class ReconyxUltraFireMakernoteDirectory extends Directory<IntegerKey>
{
    /**
     * Version number used for identifying makernotes from Reconyx UltraFire cameras.
     */
    public static final IntegerKey MAKERNOTE_ID = new IntegerKey(0x00010000);

    /**
     * Version number used for identifying the public portion of makernotes from Reconyx UltraFire cameras.
     */
    public static final IntegerKey MAKERNOTE_PUBLIC_ID = new IntegerKey(0x07f10001);

    public static final IntegerKey TAG_LABEL = new IntegerKey(0);
    public static final IntegerKey TAG_MAKERNOTE_ID = new IntegerKey(10);
    public static final IntegerKey TAG_MAKERNOTE_SIZE = new IntegerKey(14);
    public static final IntegerKey TAG_MAKERNOTE_PUBLIC_ID = new IntegerKey(18);
    public static final IntegerKey TAG_MAKERNOTE_PUBLIC_SIZE = new IntegerKey(22);
    public static final IntegerKey TAG_CAMERA_VERSION = new IntegerKey(24);
    public static final IntegerKey TAG_UIB_VERSION = new IntegerKey(31);
    public static final IntegerKey TAG_BTL_VERSION = new IntegerKey(38);
    public static final IntegerKey TAG_PEX_VERSION = new IntegerKey(45);
    public static final IntegerKey TAG_EVENT_TYPE = new IntegerKey(52);
    public static final IntegerKey TAG_SEQUENCE = new IntegerKey(53);
    public static final IntegerKey TAG_EVENT_NUMBER = new IntegerKey(55);
    public static final IntegerKey TAG_DATE_TIME_ORIGINAL = new IntegerKey(59);
    public static final IntegerKey TAG_DAY_OF_WEEK = new IntegerKey(66);
    public static final IntegerKey TAG_MOON_PHASE = new IntegerKey(67);
    public static final IntegerKey TAG_AMBIENT_TEMPERATURE_FAHRENHEIT = new IntegerKey(68);
    public static final IntegerKey TAG_AMBIENT_TEMPERATURE = new IntegerKey(70);
    public static final IntegerKey TAG_FLASH = new IntegerKey(72);
    public static final IntegerKey TAG_BATTERY_VOLTAGE = new IntegerKey(73);
    public static final IntegerKey TAG_SERIAL_NUMBER = new IntegerKey(75);
    public static final IntegerKey TAG_USER_LABEL = new IntegerKey(80);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_LABEL, "Makernote Label");
        _tagNameMap.put(TAG_MAKERNOTE_ID, "Makernote ID");
        _tagNameMap.put(TAG_MAKERNOTE_SIZE, "Makernote Size");
        _tagNameMap.put(TAG_MAKERNOTE_PUBLIC_ID, "Makernote Public ID");
        _tagNameMap.put(TAG_MAKERNOTE_PUBLIC_SIZE, "Makernote Public Size");
        _tagNameMap.put(TAG_CAMERA_VERSION, "Camera Version");
        _tagNameMap.put(TAG_UIB_VERSION, "Uib Version");
        _tagNameMap.put(TAG_BTL_VERSION, "Btl Version");
        _tagNameMap.put(TAG_PEX_VERSION, "Pex Version");
        _tagNameMap.put(TAG_EVENT_TYPE, "Event Type");
        _tagNameMap.put(TAG_SEQUENCE, "Sequence");
        _tagNameMap.put(TAG_EVENT_NUMBER, "Event Number");
        _tagNameMap.put(TAG_DATE_TIME_ORIGINAL, "Date/Time Original");
        _tagNameMap.put(TAG_DAY_OF_WEEK, "Day of Week");
        _tagNameMap.put(TAG_MOON_PHASE, "Moon Phase");
        _tagNameMap.put(TAG_AMBIENT_TEMPERATURE_FAHRENHEIT, "Ambient Temperature Fahrenheit");
        _tagNameMap.put(TAG_AMBIENT_TEMPERATURE, "Ambient Temperature");
        _tagNameMap.put(TAG_FLASH, "Flash");
        _tagNameMap.put(TAG_BATTERY_VOLTAGE, "Battery Voltage");
        _tagNameMap.put(TAG_SERIAL_NUMBER, "Serial Number");
        _tagNameMap.put(TAG_USER_LABEL, "User Label");
    }

    public ReconyxUltraFireMakernoteDirectory()
    {
        this.setDescriptor(new ReconyxUltraFireMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Reconyx UltraFire Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
