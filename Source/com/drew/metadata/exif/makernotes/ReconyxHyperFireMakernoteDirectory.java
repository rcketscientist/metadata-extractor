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
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Describes tags specific to Reconyx HyperFire cameras.
 *
 * Reconyx uses a fixed makernote block.  Tag values are the byte index of the tag within the makernote.
 * @author Todd West http://cascadescarnivoreproject.blogspot.com
 */
@SuppressWarnings("WeakerAccess")
public class ReconyxHyperFireMakernoteDirectory extends Directory<Integer, ReconyxHyperFireMakernoteDirectory.Keys>
{
    public enum Keys
    {
        /**
         * Version number used for identifying makernotes from Reconyx HyperFire cameras.
         */
        MAKERNOTE_VERSION(61697),
        TAG_MAKERNOTE_VERSION(0),
        TAG_FIRMWARE_VERSION(2),
        TAG_TRIGGER_MODE(12),
        TAG_SEQUENCE(14),
        TAG_EVENT_NUMBER(18),
        TAG_DATE_TIME_ORIGINAL(22),
        TAG_MOON_PHASE(36),
        TAG_AMBIENT_TEMPERATURE_FAHRENHEIT(38),
        TAG_AMBIENT_TEMPERATURE(40),
        TAG_SERIAL_NUMBER(42),
        TAG_CONTRAST(72),
        TAG_BRIGHTNESS(74),
        TAG_SHARPNESS(76),
        TAG_SATURATION(78),
        TAG_INFRARED_ILLUMINATOR(80),
        TAG_MOTION_SENSITIVITY(82),
        TAG_BATTERY_VOLTAGE(84),
        TAG_USER_LABEL(86);

        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();
        static {
            for (Keys type : values())
                lookup.put(type.getValue(), type);
        }

        private final int key;
        Keys(int key)
        {
            this.key = key;
        }

        public Integer getValue()
        {
            return key;
        }

        public static @Nullable
        Keys fromValue(Integer value)
        {
            return lookup.get(value);
        }
    }

    @NotNull
    protected static final EnumMap<Keys, String> _tagNameMap = new EnumMap<Keys, String>(Keys.class);
    @NotNull
    protected static final EnumMap<Keys, Object> _tagMap = new EnumMap<Keys, Object>(Keys.class);

    static
    {
        _tagNameMap.put(Keys.TAG_MAKERNOTE_VERSION, "Makernote Version");
        _tagNameMap.put(Keys.TAG_FIRMWARE_VERSION, "Firmware Version");
        _tagNameMap.put(Keys.TAG_TRIGGER_MODE, "Trigger Mode");
        _tagNameMap.put(Keys.TAG_SEQUENCE, "Sequence");
        _tagNameMap.put(Keys.TAG_EVENT_NUMBER, "Event Number");
        _tagNameMap.put(Keys.TAG_DATE_TIME_ORIGINAL, "Date/Time Original");
        _tagNameMap.put(Keys.TAG_MOON_PHASE, "Moon Phase");
        _tagNameMap.put(Keys.TAG_AMBIENT_TEMPERATURE_FAHRENHEIT, "Ambient Temperature Fahrenheit");
        _tagNameMap.put(Keys.TAG_AMBIENT_TEMPERATURE, "Ambient Temperature");
        _tagNameMap.put(Keys.TAG_SERIAL_NUMBER, "Serial Number");
        _tagNameMap.put(Keys.TAG_CONTRAST, "Contrast");
        _tagNameMap.put(Keys.TAG_BRIGHTNESS, "Brightness");
        _tagNameMap.put(Keys.TAG_SHARPNESS, "Sharpness");
        _tagNameMap.put(Keys.TAG_SATURATION, "Saturation");
        _tagNameMap.put(Keys.TAG_INFRARED_ILLUMINATOR, "Infrared Illuminator");
        _tagNameMap.put(Keys.TAG_MOTION_SENSITIVITY, "Motion Sensitivity");
        _tagNameMap.put(Keys.TAG_BATTERY_VOLTAGE, "Battery Voltage");
        _tagNameMap.put(Keys.TAG_USER_LABEL, "User Label");
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
    protected EnumMap<Keys, String> getTagNameMap()
    {
        return _tagNameMap;
    }

    @Override
    protected EnumMap<Keys, Object> getTagMap()
    {
        return _tagMap;
    }

    @Override
    protected Keys getTagFromValue(Integer value)
    {
        return Keys.fromValue(value);
    }
}
