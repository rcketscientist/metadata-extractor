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
 * Describes tags specific to Reconyx UltraFire cameras.
 *
 * Reconyx uses a fixed makernote block.  Tag values are the byte index of the tag within the makernote.
 * @author Todd West http://cascadescarnivoreproject.blogspot.com
 */
@SuppressWarnings("WeakerAccess")
public class ReconyxUltraFireMakernoteDirectory extends Directory<Integer, ReconyxUltraFireMakernoteDirectory.Keys>
{
    public enum Keys
    {
        /**
         * Version number used for identifying makernotes from Reconyx UltraFire cameras.
         */
        MAKERNOTE_ID(0x00010000),

        /**
         * Version number used for identifying the public portion of makernotes from Reconyx UltraFire cameras.
         */
        MAKERNOTE_PUBLIC_ID(0x07f10001),

        TAG_LABEL(0),
        TAG_MAKERNOTE_ID(10),
        TAG_MAKERNOTE_SIZE(14),
        TAG_MAKERNOTE_PUBLIC_ID(18),
        TAG_MAKERNOTE_PUBLIC_SIZE(22),
        TAG_CAMERA_VERSION(24),
        TAG_UIB_VERSION(31),
        TAG_BTL_VERSION(38),
        TAG_PEX_VERSION(45),
        TAG_EVENT_TYPE(52),
        TAG_SEQUENCE(53),
        TAG_EVENT_NUMBER(55),
        TAG_DATE_TIME_ORIGINAL(59),
        TAG_DAY_OF_WEEK(66),
        TAG_MOON_PHASE(67),
        TAG_AMBIENT_TEMPERATURE_FAHRENHEIT(68),
        TAG_AMBIENT_TEMPERATURE(70),
        TAG_FLASH(72),
        TAG_BATTERY_VOLTAGE(73),
        TAG_SERIAL_NUMBER(75),
        TAG_USER_LABEL(80);

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
        _tagNameMap.put(Keys.TAG_LABEL, "Makernote Label");
        _tagNameMap.put(Keys.TAG_MAKERNOTE_ID, "Makernote ID");
        _tagNameMap.put(Keys.TAG_MAKERNOTE_SIZE, "Makernote Size");
        _tagNameMap.put(Keys.TAG_MAKERNOTE_PUBLIC_ID, "Makernote Public ID");
        _tagNameMap.put(Keys.TAG_MAKERNOTE_PUBLIC_SIZE, "Makernote Public Size");
        _tagNameMap.put(Keys.TAG_CAMERA_VERSION, "Camera Version");
        _tagNameMap.put(Keys.TAG_UIB_VERSION, "Uib Version");
        _tagNameMap.put(Keys.TAG_BTL_VERSION, "Btl Version");
        _tagNameMap.put(Keys.TAG_PEX_VERSION, "Pex Version");
        _tagNameMap.put(Keys.TAG_EVENT_TYPE, "Event Type");
        _tagNameMap.put(Keys.TAG_SEQUENCE, "Sequence");
        _tagNameMap.put(Keys.TAG_EVENT_NUMBER, "Event Number");
        _tagNameMap.put(Keys.TAG_DATE_TIME_ORIGINAL, "Date/Time Original");
        _tagNameMap.put(Keys.TAG_DAY_OF_WEEK, "Day of Week");
        _tagNameMap.put(Keys.TAG_MOON_PHASE, "Moon Phase");
        _tagNameMap.put(Keys.TAG_AMBIENT_TEMPERATURE_FAHRENHEIT, "Ambient Temperature Fahrenheit");
        _tagNameMap.put(Keys.TAG_AMBIENT_TEMPERATURE, "Ambient Temperature");
        _tagNameMap.put(Keys.TAG_FLASH, "Flash");
        _tagNameMap.put(Keys.TAG_BATTERY_VOLTAGE, "Battery Voltage");
        _tagNameMap.put(Keys.TAG_SERIAL_NUMBER, "Serial Number");
        _tagNameMap.put(Keys.TAG_USER_LABEL, "User Label");
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
