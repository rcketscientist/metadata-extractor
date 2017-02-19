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
import com.drew.metadata.exif.PanasonicRawDistortionDirectory;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Describes tags specific to Kodak cameras.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class KodakMakernoteDirectory extends Directory<Integer, KodakMakernoteDirectory.Keys>
{
    public enum Keys
    {
        TAG_KODAK_MODEL(0),
        TAG_QUALITY(9),
        TAG_BURST_MODE(10),
        TAG_IMAGE_WIDTH(12),
        TAG_IMAGE_HEIGHT(14),
        TAG_YEAR_CREATED(16),
        TAG_MONTH_DAY_CREATED(18),
        TAG_TIME_CREATED(20),
        TAG_BURST_MODE_2(24),
        TAG_SHUTTER_MODE(27),
        TAG_METERING_MODE(28),
        TAG_SEQUENCE_NUMBER(29),
        TAG_F_NUMBER(30),
        TAG_EXPOSURE_TIME(32),
        TAG_EXPOSURE_COMPENSATION(36),
        TAG_FOCUS_MODE(56),
        TAG_WHITE_BALANCE(64),
        TAG_FLASH_MODE(92),
        TAG_FLASH_FIRED(93),
        TAG_ISO_SETTING(94),
        TAG_ISO(96),
        TAG_TOTAL_ZOOM(98),
        TAG_DATE_TIME_STAMP(100),
        TAG_COLOR_MODE(102),
        TAG_DIGITAL_ZOOM(104),
        TAG_SHARPNESS(107);

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
        _tagNameMap.put(Keys.TAG_KODAK_MODEL, "Kodak Model");
        _tagNameMap.put(Keys.TAG_QUALITY, "Quality");
        _tagNameMap.put(Keys.TAG_BURST_MODE, "Burst Mode");
        _tagNameMap.put(Keys.TAG_IMAGE_WIDTH, "Image Width");
        _tagNameMap.put(Keys.TAG_IMAGE_HEIGHT, "Image Height");
        _tagNameMap.put(Keys.TAG_YEAR_CREATED, "Year Created");
        _tagNameMap.put(Keys.TAG_MONTH_DAY_CREATED, "Month/Day Created");
        _tagNameMap.put(Keys.TAG_TIME_CREATED, "Time Created");
        _tagNameMap.put(Keys.TAG_BURST_MODE_2, "Burst Mode 2");
        _tagNameMap.put(Keys.TAG_SHUTTER_MODE, "Shutter Speed");
        _tagNameMap.put(Keys.TAG_METERING_MODE, "Metering Mode");
        _tagNameMap.put(Keys.TAG_SEQUENCE_NUMBER, "Sequence Number");
        _tagNameMap.put(Keys.TAG_F_NUMBER, "F Number");
        _tagNameMap.put(Keys.TAG_EXPOSURE_TIME, "Exposure Time");
        _tagNameMap.put(Keys.TAG_EXPOSURE_COMPENSATION, "Exposure Compensation");
        _tagNameMap.put(Keys.TAG_FOCUS_MODE, "Focus Mode");
        _tagNameMap.put(Keys.TAG_WHITE_BALANCE, "White Balance");
        _tagNameMap.put(Keys.TAG_FLASH_MODE, "Flash Mode");
        _tagNameMap.put(Keys.TAG_FLASH_FIRED, "Flash Fired");
        _tagNameMap.put(Keys.TAG_ISO_SETTING, "ISO Setting");
        _tagNameMap.put(Keys.TAG_ISO, "ISO");
        _tagNameMap.put(Keys.TAG_TOTAL_ZOOM, "Total Zoom");
        _tagNameMap.put(Keys.TAG_DATE_TIME_STAMP, "Date/Time Stamp");
        _tagNameMap.put(Keys.TAG_COLOR_MODE, "Color Mode");
        _tagNameMap.put(Keys.TAG_DIGITAL_ZOOM, "Digital Zoom");
        _tagNameMap.put(Keys.TAG_SHARPNESS, "Sharpness");
    }

    public KodakMakernoteDirectory()
    {
        this.setDescriptor(new KodakMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Kodak Makernote";
    }

    @Override
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
