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

import java.util.HashMap;

/**
 * The Olympus raw development 2 makernote is used by many manufacturers (Epson, Konica, Minolta and Agfa...), and as such contains some tags
 * that appear specific to those manufacturers.
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class OlympusRawDevelopment2MakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAGRawDevVersion = new IntegerKey(0x0000);
    public static final IntegerKey TAGRawDevExposureBiasValue = new IntegerKey(0x0100);
    public static final IntegerKey TAGRawDevWhiteBalance = new IntegerKey(0x0101);
    public static final IntegerKey TAGRawDevWhiteBalanceValue = new IntegerKey(0x0102);
    public static final IntegerKey TAGRawDevWbFineAdjustment = new IntegerKey(0x0103);
    public static final IntegerKey TAGRawDevGrayPoint = new IntegerKey(0x0104);
    public static final IntegerKey TAGRawDevContrastValue = new IntegerKey(0x0105);
    public static final IntegerKey TAGRawDevSharpnessValue = new IntegerKey(0x0106);
    public static final IntegerKey TAGRawDevSaturationEmphasis = new IntegerKey(0x0107);
    public static final IntegerKey TAGRawDevMemoryColorEmphasis = new IntegerKey(0x0108);
    public static final IntegerKey TAGRawDevColorSpace = new IntegerKey(0x0109);
    public static final IntegerKey TAGRawDevNoiseReduction = new IntegerKey(0x010a);
    public static final IntegerKey TAGRawDevEngine = new IntegerKey(0x010b);
    public static final IntegerKey TAGRawDevPictureMode = new IntegerKey(0x010c);
    public static final IntegerKey TAGRawDevPmSaturation = new IntegerKey(0x010d);
    public static final IntegerKey TAGRawDevPmContrast = new IntegerKey(0x010e);
    public static final IntegerKey TAGRawDevPmSharpness = new IntegerKey(0x010f);
    public static final IntegerKey TAGRawDevPmBwFilter = new IntegerKey(0x0110);
    public static final IntegerKey TAGRawDevPmPictureTone = new IntegerKey(0x0111);
    public static final IntegerKey TAGRawDevGradation = new IntegerKey(0x0112);
    public static final IntegerKey TAGRawDevSaturation3 = new IntegerKey(0x0113);
    public static final IntegerKey TAGRawDevAutoGradation = new IntegerKey(0x0119);
    public static final IntegerKey TAGRawDevPmNoiseFilter = new IntegerKey(0x0120);
    public static final IntegerKey TAGRawDevArtFilter = new IntegerKey(0x0121);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TagRawDevVersion, "Raw Dev Version");
        _tagNameMap.put(TagRawDevExposureBiasValue, "Raw Dev Exposure Bias Value");
        _tagNameMap.put(TagRawDevWhiteBalance, "Raw Dev White Balance");
        _tagNameMap.put(TagRawDevWhiteBalanceValue, "Raw Dev White Balance Value");
        _tagNameMap.put(TagRawDevWbFineAdjustment, "Raw Dev WB Fine Adjustment");
        _tagNameMap.put(TagRawDevGrayPoint, "Raw Dev Gray Point");
        _tagNameMap.put(TagRawDevContrastValue, "Raw Dev Contrast Value");
        _tagNameMap.put(TagRawDevSharpnessValue, "Raw Dev Sharpness Value");
        _tagNameMap.put(TagRawDevSaturationEmphasis, "Raw Dev Saturation Emphasis");
        _tagNameMap.put(TagRawDevMemoryColorEmphasis, "Raw Dev Memory Color Emphasis");
        _tagNameMap.put(TagRawDevColorSpace, "Raw Dev Color Space");
        _tagNameMap.put(TagRawDevNoiseReduction, "Raw Dev Noise Reduction");
        _tagNameMap.put(TagRawDevEngine, "Raw Dev Engine");
        _tagNameMap.put(TagRawDevPictureMode, "Raw Dev Picture Mode");
        _tagNameMap.put(TagRawDevPmSaturation, "Raw Dev PM Saturation");
        _tagNameMap.put(TagRawDevPmContrast, "Raw Dev PM Contrast");
        _tagNameMap.put(TagRawDevPmSharpness, "Raw Dev PM Sharpness");
        _tagNameMap.put(TagRawDevPmBwFilter, "Raw Dev PM BW Filter");
        _tagNameMap.put(TagRawDevPmPictureTone, "Raw Dev PM Picture Tone");
        _tagNameMap.put(TagRawDevGradation, "Raw Dev Gradation");
        _tagNameMap.put(TagRawDevSaturation3, "Raw Dev Saturation 3");
        _tagNameMap.put(TagRawDevAutoGradation, "Raw Dev Auto Gradation");
        _tagNameMap.put(TagRawDevPmNoiseFilter, "Raw Dev PM Noise Filter");
        _tagNameMap.put(TagRawDevArtFilter, "Raw Dev Art Filter");
    }

    public OlympusRawDevelopment2MakernoteDirectory()
    {
        this.setDescriptor(new OlympusRawDevelopment2MakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Olympus Raw Development 2";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
