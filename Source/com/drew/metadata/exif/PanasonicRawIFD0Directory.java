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

package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * These tags are found in IFD0 of Panasonic/Leica RAW, RW2 and RWL images.
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class PanasonicRawIFD0Directory extends Directory<IntegerKey>
{
    public static final IntegerKey TagPanasonicRawVersion = new IntegerKey(0x0001);
    public static final IntegerKey TagSensorWidth = new IntegerKey(0x0002);
    public static final IntegerKey TagSensorHeight = new IntegerKey(0x0003);
    public static final IntegerKey TagSensorTopBorder = new IntegerKey(0x0004);
    public static final IntegerKey TagSensorLeftBorder = new IntegerKey(0x0005);
    public static final IntegerKey TagSensorBottomBorder = new IntegerKey(0x0006);
    public static final IntegerKey TagSensorRightBorder = new IntegerKey(0x0007);

    public static final IntegerKey TagBlackLevel1 = new IntegerKey(0x0008);
    public static final IntegerKey TagBlackLevel2 = new IntegerKey(0x0009);
    public static final IntegerKey TagBlackLevel3 = new IntegerKey(0x000a);
    public static final IntegerKey TagLinearityLimitRed = new IntegerKey(0x000e);
    public static final IntegerKey TagLinearityLimitGreen = new IntegerKey(0x000f);
    public static final IntegerKey TagLinearityLimitBlue = new IntegerKey(0x0010);
    public static final IntegerKey TagRedBalance = new IntegerKey(0x0011);
    public static final IntegerKey TagBlueBalance = new IntegerKey(0x0012);
    public static final IntegerKey TagWbInfo = new IntegerKey(0x0013);

    public static final IntegerKey TagIso = new IntegerKey(0x0017);
    public static final IntegerKey TagHighIsoMultiplierRed = new IntegerKey(0x0018);
    public static final IntegerKey TagHighIsoMultiplierGreen = new IntegerKey(0x0019);
    public static final IntegerKey TagHighIsoMultiplierBlue = new IntegerKey(0x001a);
    public static final IntegerKey TagBlackLevelRed = new IntegerKey(0x001c);
    public static final IntegerKey TagBlackLevelGreen = new IntegerKey(0x001d);
    public static final IntegerKey TagBlackLevelBlue = new IntegerKey(0x001e);
    public static final IntegerKey TagWbRedLevel = new IntegerKey(0x0024);
    public static final IntegerKey TagWbGreenLevel = new IntegerKey(0x0025);
    public static final IntegerKey TagWbBlueLevel = new IntegerKey(0x0026);

    public static final IntegerKey TagWbInfo2 = new IntegerKey(0x0027);

    public static final IntegerKey TagJpgFromRaw = new IntegerKey(0x002e);

    public static final IntegerKey TagCropTop = new IntegerKey(0x002f);
    public static final IntegerKey TagCropLeft = new IntegerKey(0x0030);
    public static final IntegerKey TagCropBottom = new IntegerKey(0x0031);
    public static final IntegerKey TagCropRight = new IntegerKey(0x0032);

    public static final IntegerKey TagMake = new IntegerKey(0x010f);
    public static final IntegerKey TagModel = new IntegerKey(0x0110);
    public static final IntegerKey TagStripOffsets = new IntegerKey(0x0111);
    public static final IntegerKey TagOrientation = new IntegerKey(0x0112);
    public static final IntegerKey TagRowsPerStrip = new IntegerKey(0x0116);
    public static final IntegerKey TagStripByteCounts = new IntegerKey(0x0117);
    public static final IntegerKey TagRawDataOffset = new IntegerKey(0x0118);

    public static final IntegerKey TagDistortionInfo = new IntegerKey(0x0119);

    public PanasonicRawIFD0Directory()
    {
        this.setDescriptor(new PanasonicRawIFD0Descriptor(this));
    }

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TagPanasonicRawVersion, "Panasonic Raw Version");
        _tagNameMap.put(TagSensorWidth, "Sensor Width");
        _tagNameMap.put(TagSensorHeight, "Sensor Height");
        _tagNameMap.put(TagSensorTopBorder, "Sensor Top Border");
        _tagNameMap.put(TagSensorLeftBorder, "Sensor Left Border");
        _tagNameMap.put(TagSensorBottomBorder, "Sensor Bottom Border");
        _tagNameMap.put(TagSensorRightBorder, "Sensor Right Border");

        _tagNameMap.put(TagBlackLevel1, "Black Level 1");
        _tagNameMap.put(TagBlackLevel2, "Black Level 2");
        _tagNameMap.put(TagBlackLevel3, "Black Level 3");
        _tagNameMap.put(TagLinearityLimitRed, "Linearity Limit Red");
        _tagNameMap.put(TagLinearityLimitGreen, "Linearity Limit Green");
        _tagNameMap.put(TagLinearityLimitBlue, "Linearity Limit Blue");
        _tagNameMap.put(TagRedBalance, "Red Balance");
        _tagNameMap.put(TagBlueBalance, "Blue Balance");

        _tagNameMap.put(TagIso, "ISO");
        _tagNameMap.put(TagHighIsoMultiplierRed, "High ISO Multiplier Red");
        _tagNameMap.put(TagHighIsoMultiplierGreen, "High ISO Multiplier Green");
        _tagNameMap.put(TagHighIsoMultiplierBlue, "High ISO Multiplier Blue");
        _tagNameMap.put(TagBlackLevelRed, "Black Level Red");
        _tagNameMap.put(TagBlackLevelGreen, "Black Level Green");
        _tagNameMap.put(TagBlackLevelBlue, "Black Level Blue");
        _tagNameMap.put(TagWbRedLevel, "WB Red Level");
        _tagNameMap.put(TagWbGreenLevel, "WB Green Level");
        _tagNameMap.put(TagWbBlueLevel, "WB Blue Level");

        _tagNameMap.put(TagJpgFromRaw, "Jpg From Raw");

        _tagNameMap.put(TagCropTop, "Crop Top");
        _tagNameMap.put(TagCropLeft, "Crop Left");
        _tagNameMap.put(TagCropBottom, "Crop Bottom");
        _tagNameMap.put(TagCropRight, "Crop Right");

        _tagNameMap.put(TagMake, "Make");
        _tagNameMap.put(TagModel, "Model");
        _tagNameMap.put(TagStripOffsets, "Strip Offsets");
        _tagNameMap.put(TagOrientation, "Orientation");
        _tagNameMap.put(TagRowsPerStrip, "Rows Per Strip");
        _tagNameMap.put(TagStripByteCounts, "Strip Byte Counts");
        _tagNameMap.put(TagRawDataOffset, "Raw Data Offset");
    }

    @Override
    @NotNull
    public String getName()
    {
        return "PanasonicRaw Exif IFD0";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
