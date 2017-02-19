/*
 * Copyright 2002-2015 Drew Noakes
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
 * These tags are found only in ORF images of some models (eg. C8080WZ)
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class OlympusRawInfoMakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TagRawInfoVersion = new IntegerKey(0x0000);
    public static final IntegerKey TagWbRbLevelsUsed = new IntegerKey(0x0100);
    public static final IntegerKey TagWbRbLevelsAuto = new IntegerKey(0x0110);
    public static final IntegerKey TagWbRbLevelsShade = new IntegerKey(0x0120);
    public static final IntegerKey TagWbRbLevelsCloudy = new IntegerKey(0x0121);
    public static final IntegerKey TagWbRbLevelsFineWeather = new IntegerKey(0x0122);
    public static final IntegerKey TagWbRbLevelsTungsten = new IntegerKey(0x0123);
    public static final IntegerKey TagWbRbLevelsEveningSunlight = new IntegerKey(0x0124);
    public static final IntegerKey TagWbRbLevelsDaylightFluor = new IntegerKey(0x0130);
    public static final IntegerKey TagWbRbLevelsDayWhiteFluor = new IntegerKey(0x0131);
    public static final IntegerKey TagWbRbLevelsCoolWhiteFluor = new IntegerKey(0x0132);
    public static final IntegerKey TagWbRbLevelsWhiteFluorescent = new IntegerKey(0x0133);

    public static final IntegerKey TagColorMatrix2 = new IntegerKey(0x0200);
    public static final IntegerKey TagCoringFilter = new IntegerKey(0x0310);
    public static final IntegerKey TagCoringValues = new IntegerKey(0x0311);
    public static final IntegerKey TagBlackLevel2 = new IntegerKey(0x0600);
    public static final IntegerKey TagYCbCrCoefficients = new IntegerKey(0x0601);
    public static final IntegerKey TagValidPixelDepth = new IntegerKey(0x0611);
    public static final IntegerKey TagCropLeft = new IntegerKey(0x0612);
    public static final IntegerKey TagCropTop = new IntegerKey(0x0613);
    public static final IntegerKey TagCropWidth = new IntegerKey(0x0614);
    public static final IntegerKey TagCropHeight = new IntegerKey(0x0615);

    public static final IntegerKey TagLightSource = new IntegerKey(0x1000);

    //the following 5 tags all have 3 values: val, min, max
    public static final IntegerKey TagWhiteBalanceComp = new IntegerKey(0x1001);
    public static final IntegerKey TagSaturationSetting = new IntegerKey(0x1010);
    public static final IntegerKey TagHueSetting = new IntegerKey(0x1011);
    public static final IntegerKey TagContrastSetting = new IntegerKey(0x1012);
    public static final IntegerKey TagSharpnessSetting = new IntegerKey(0x1013);

    // settings written by Camedia Master 4.x
    public static final IntegerKey TagCmExposureCompensation = new IntegerKey(0x2000);
    public static final IntegerKey TagCmWhiteBalance = new IntegerKey(0x2001);
    public static final IntegerKey TagCmWhiteBalanceComp = new IntegerKey(0x2002);
    public static final IntegerKey TagCmWhiteBalanceGrayPoint = new IntegerKey(0x2010);
    public static final IntegerKey TagCmSaturation = new IntegerKey(0x2020);
    public static final IntegerKey TagCmHue = new IntegerKey(0x2021);
    public static final IntegerKey TagCmContrast = new IntegerKey(0x2022);
    public static final IntegerKey TagCmSharpness = new IntegerKey(0x2023);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TagRawInfoVersion, "Raw Info Version");
        _tagNameMap.put(TagWbRbLevelsUsed, "WB RB Levels Used");
        _tagNameMap.put(TagWbRbLevelsAuto, "WB RB Levels Auto");
        _tagNameMap.put(TagWbRbLevelsShade, "WB RB Levels Shade");
        _tagNameMap.put(TagWbRbLevelsCloudy, "WB RB Levels Cloudy");
        _tagNameMap.put(TagWbRbLevelsFineWeather, "WB RB Levels Fine Weather");
        _tagNameMap.put(TagWbRbLevelsTungsten, "WB RB Levels Tungsten");
        _tagNameMap.put(TagWbRbLevelsEveningSunlight, "WB RB Levels Evening Sunlight");
        _tagNameMap.put(TagWbRbLevelsDaylightFluor, "WB RB Levels Daylight Fluor");
        _tagNameMap.put(TagWbRbLevelsDayWhiteFluor, "WB RB Levels Day White Fluor");
        _tagNameMap.put(TagWbRbLevelsCoolWhiteFluor, "WB RB Levels Cool White Fluor");
        _tagNameMap.put(TagWbRbLevelsWhiteFluorescent, "WB RB Levels White Fluorescent");
        _tagNameMap.put(TagColorMatrix2, "Color Matrix 2");
        _tagNameMap.put(TagCoringFilter, "Coring Filter");
        _tagNameMap.put(TagCoringValues, "Coring Values");
        _tagNameMap.put(TagBlackLevel2, "Black Level 2");
        _tagNameMap.put(TagYCbCrCoefficients, "YCbCrCoefficients");
        _tagNameMap.put(TagValidPixelDepth, "Valid Pixel Depth");
        _tagNameMap.put(TagCropLeft, "Crop Left");
        _tagNameMap.put(TagCropTop, "Crop Top");
        _tagNameMap.put(TagCropWidth, "Crop Width");
        _tagNameMap.put(TagCropHeight, "Crop Height");
        _tagNameMap.put(TagLightSource, "Light Source");

        _tagNameMap.put(TagWhiteBalanceComp, "White Balance Comp");
        _tagNameMap.put(TagSaturationSetting, "Saturation Setting");
        _tagNameMap.put(TagHueSetting, "Hue Setting");
        _tagNameMap.put(TagContrastSetting, "Contrast Setting");
        _tagNameMap.put(TagSharpnessSetting, "Sharpness Setting");

        _tagNameMap.put(TagCmExposureCompensation, "CM Exposure Compensation");
        _tagNameMap.put(TagCmWhiteBalance, "CM White Balance");
        _tagNameMap.put(TagCmWhiteBalanceComp, "CM White Balance Comp");
        _tagNameMap.put(TagCmWhiteBalanceGrayPoint, "CM White Balance Gray Point");
        _tagNameMap.put(TagCmSaturation, "CM Saturation");
        _tagNameMap.put(TagCmHue, "CM Hue");
        _tagNameMap.put(TagCmContrast, "CM Contrast");
        _tagNameMap.put(TagCmSharpness, "CM Sharpness");
    }

    public OlympusRawInfoMakernoteDirectory()
    {
        this.setDescriptor(new OlympusRawInfoMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Olympus Raw Info";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
