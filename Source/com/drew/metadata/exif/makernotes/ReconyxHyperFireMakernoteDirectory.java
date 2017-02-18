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
 * Describes tags specific to Reconyx HyperFire cameras.
 *
 * Reconyx uses a fixed makernote block.  Tag values are the byte index of the tag within the makernote.
 * @author Todd West http://cascadescarnivoreproject.blogspot.com
 */
@SuppressWarnings("WeakerAccess")
public class ReconyxHyperFireMakernoteDirectory extends Directory<IntegerKey>
{
    /**
     * Version number used for identifying makernotes from Reconyx HyperFire cameras.
     */
    public static final IntegerKey MAKERNOTE_VERSION = new IntegerKey(61697);

    public static final IntegerKey TAG_MAKERNOTE_VERSION = new IntegerKey(0);
    public static final IntegerKey TAG_FIRMWARE_VERSION = new IntegerKey(2);
    public static final IntegerKey TAG_TRIGGER_MODE = new IntegerKey(12);
    public static final IntegerKey TAG_SEQUENCE = new IntegerKey(14);
    public static final IntegerKey TAG_EVENT_NUMBER = new IntegerKey(18);
    public static final IntegerKey TAG_DATE_TIME_ORIGINAL = new IntegerKey(22);
    public static final IntegerKey TAG_MOON_PHASE = new IntegerKey(36);
    public static final IntegerKey TAG_AMBIENT_TEMPERATURE_FAHRENHEIT = new IntegerKey(38);
    public static final IntegerKey TAG_AMBIENT_TEMPERATURE = new IntegerKey(40);
    public static final IntegerKey TAG_SERIAL_NUMBER = new IntegerKey(42);
    public static final IntegerKey TAG_CONTRAST = new IntegerKey(72);
    public static final IntegerKey TAG_BRIGHTNESS = new IntegerKey(74);
    public static final IntegerKey TAG_SHARPNESS = new IntegerKey(76);
    public static final IntegerKey TAG_SATURATION = new IntegerKey(78);
    public static final IntegerKey TAG_INFRARED_ILLUMINATOR = new IntegerKey(80);
    public static final IntegerKey TAG_MOTION_SENSITIVITY = new IntegerKey(82);
    public static final IntegerKey TAG_BATTERY_VOLTAGE = new IntegerKey(84);
    public static final IntegerKey TAG_USER_LABEL = new IntegerKey(86);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_MAKERNOTE_VERSION, "Makernote Version");
        _tagNameMap.put(TAG_FIRMWARE_VERSION, "Firmware Version");
        _tagNameMap.put(TAG_TRIGGER_MODE, "Trigger Mode");
        _tagNameMap.put(TAG_SEQUENCE, "Sequence");
        _tagNameMap.put(TAG_EVENT_NUMBER, "Event Number");
        _tagNameMap.put(TAG_DATE_TIME_ORIGINAL, "Date/Time Original");
        _tagNameMap.put(TAG_MOON_PHASE, "Moon Phase");
        _tagNameMap.put(TAG_AMBIENT_TEMPERATURE_FAHRENHEIT, "Ambient Temperature Fahrenheit");
        _tagNameMap.put(TAG_AMBIENT_TEMPERATURE, "Ambient Temperature");
        _tagNameMap.put(TAG_SERIAL_NUMBER, "Serial Number");
        _tagNameMap.put(TAG_CONTRAST, "Contrast");
        _tagNameMap.put(TAG_BRIGHTNESS, "Brightness");
        _tagNameMap.put(TAG_SHARPNESS, "Sharpness");
        _tagNameMap.put(TAG_SATURATION, "Saturation");
        _tagNameMap.put(TAG_INFRARED_ILLUMINATOR, "Infrared Illuminator");
        _tagNameMap.put(TAG_MOTION_SENSITIVITY, "Motion Sensitivity");
        _tagNameMap.put(TAG_BATTERY_VOLTAGE, "Battery Voltage");
        _tagNameMap.put(TAG_USER_LABEL, "User Label");
    }

    public ReconyxHyperFireMakernoteDirectory()
    {
        this.setDescriptor(new ReconyxHyperFireMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Reconyx HyperFire Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
