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
 * Describes tags specific to Sony cameras that use the Sony Type 1 makernote tags.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class SonyType1MakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_CAMERA_INFO = new IntegerKey(0x0010);
    public static final IntegerKey TAG_FOCUS_INFO = new IntegerKey(0x0020);

    public static final IntegerKey TAG_IMAGE_QUALITY = new IntegerKey(0x0102);
    public static final IntegerKey TAG_FLASH_EXPOSURE_COMP = new IntegerKey(0x0104);
    public static final IntegerKey TAG_TELECONVERTER = new IntegerKey(0x0105);

    public static final IntegerKey TAG_WHITE_BALANCE_FINE_TUNE = new IntegerKey(0x0112);
    public static final IntegerKey TAG_CAMERA_SETTINGS = new IntegerKey(0x0114);
    public static final IntegerKey TAG_WHITE_BALANCE = new IntegerKey(0x0115);
    public static final IntegerKey TAG_EXTRA_INFO = new IntegerKey(0x0116);

    public static final IntegerKey TAG_PRINT_IMAGE_MATCHING_INFO = new IntegerKey(0x0E00);

    public static final IntegerKey TAG_MULTI_BURST_MODE = new IntegerKey(0x1000);
    public static final IntegerKey TAG_MULTI_BURST_IMAGE_WIDTH = new IntegerKey(0x1001);
    public static final IntegerKey TAG_MULTI_BURST_IMAGE_HEIGHT = new IntegerKey(0x1002);
    public static final IntegerKey TAG_PANORAMA = new IntegerKey(0x1003);

    public static final IntegerKey TAG_PREVIEW_IMAGE = new IntegerKey(0x2001);
    public static final IntegerKey TAG_RATING = new IntegerKey(0x2002);
    public static final IntegerKey TAG_CONTRAST = new IntegerKey(0x2004);
    public static final IntegerKey TAG_SATURATION = new IntegerKey(0x2005);
    public static final IntegerKey TAG_SHARPNESS = new IntegerKey(0x2006);
    public static final IntegerKey TAG_BRIGHTNESS = new IntegerKey(0x2007);
    public static final IntegerKey TAG_LONG_EXPOSURE_NOISE_REDUCTION = new IntegerKey(0x2008);
    public static final IntegerKey TAG_HIGH_ISO_NOISE_REDUCTION = new IntegerKey(0x2009);
    public static final IntegerKey TAG_HDR = new IntegerKey(0x200a);
    public static final IntegerKey TAG_MULTI_FRAME_NOISE_REDUCTION = new IntegerKey(0x200b);
    public static final IntegerKey TAG_PICTURE_EFFECT = new IntegerKey(0x200e);
    public static final IntegerKey TAG_SOFT_SKIN_EFFECT = new IntegerKey(0x200f);

    public static final IntegerKey TAG_VIGNETTING_CORRECTION = new IntegerKey(0x2011);
    public static final IntegerKey TAG_LATERAL_CHROMATIC_ABERRATION = new IntegerKey(0x2012);
    public static final IntegerKey TAG_DISTORTION_CORRECTION = new IntegerKey(0x2013);
    public static final IntegerKey TAG_WB_SHIFT_AMBER_MAGENTA = new IntegerKey(0x2014);
    public static final IntegerKey TAG_AUTO_PORTRAIT_FRAMED = new IntegerKey(0x2016);
    public static final IntegerKey TAG_FOCUS_MODE = new IntegerKey(0x201b);
    public static final IntegerKey TAG_AF_POINT_SELECTED = new IntegerKey(0x201e);

    public static final IntegerKey TAG_SHOT_INFO = new IntegerKey(0x3000);

    public static final IntegerKey TAG_FILE_FORMAT = new IntegerKey(0xb000);
    public static final IntegerKey TAG_SONY_MODEL_ID = new IntegerKey(0xb001);

    public static final IntegerKey TAG_COLOR_MODE_SETTING = new IntegerKey(0xb020);
    public static final IntegerKey TAG_COLOR_TEMPERATURE = new IntegerKey(0xb021);
    public static final IntegerKey TAG_COLOR_COMPENSATION_FILTER = new IntegerKey(0xb022);
    public static final IntegerKey TAG_SCENE_MODE = new IntegerKey(0xb023);
    public static final IntegerKey TAG_ZONE_MATCHING = new IntegerKey(0xb024);
    public static final IntegerKey TAG_DYNAMIC_RANGE_OPTIMISER = new IntegerKey(0xb025);
    public static final IntegerKey TAG_IMAGE_STABILISATION = new IntegerKey(0xb026);
    public static final IntegerKey TAG_LENS_ID = new IntegerKey(0xb027);
    public static final IntegerKey TAG_MINOLTA_MAKERNOTE = new IntegerKey(0xb028);
    public static final IntegerKey TAG_COLOR_MODE = new IntegerKey(0xb029);
    public static final IntegerKey TAG_LENS_SPEC = new IntegerKey(0xb02a);
    public static final IntegerKey TAG_FULL_IMAGE_SIZE = new IntegerKey(0xb02b);
    public static final IntegerKey TAG_PREVIEW_IMAGE_SIZE = new IntegerKey(0xb02c);

    public static final IntegerKey TAG_MACRO = new IntegerKey(0xb040);
    public static final IntegerKey TAG_EXPOSURE_MODE = new IntegerKey(0xb041);
    public static final IntegerKey TAG_FOCUS_MODE_2 = new IntegerKey(0xb042);
    public static final IntegerKey TAG_AF_MODE = new IntegerKey(0xb043);
    public static final IntegerKey TAG_AF_ILLUMINATOR = new IntegerKey(0xb044);
    public static final IntegerKey TAG_JPEG_QUALITY = new IntegerKey(0xb047);
    public static final IntegerKey TAG_FLASH_LEVEL = new IntegerKey(0xb048);
    public static final IntegerKey TAG_RELEASE_MODE = new IntegerKey(0xb049);
    public static final IntegerKey TAG_SEQUENCE_NUMBER = new IntegerKey(0xb04a);
    public static final IntegerKey TAG_ANTI_BLUR = new IntegerKey(0xb04b);
    /**
     * (FocusMode for RX100)
     * 0 = new IntegerKey(Manual
     * 2 = new IntegerKey(AF-S
     * 3 = new IntegerKey(AF-C
     * 5 = new IntegerKey(Semi-manual
     * 6 = new IntegerKey(Direct Manual Focus
     * (LongExposureNoiseReduction for other models)
     * 0 = new IntegerKey(Off
     * 1 = new IntegerKey(On
     * 2 = new IntegerKey(On 2
     * 65535 = new IntegerKey(n/a
     */
    public static final IntegerKey TAG_LONG_EXPOSURE_NOISE_REDUCTION_OR_FOCUS_MODE = new IntegerKey(0xb04e);
    public static final IntegerKey TAG_DYNAMIC_RANGE_OPTIMIZER = new IntegerKey(0xb04f);

    public static final IntegerKey TAG_HIGH_ISO_NOISE_REDUCTION_2 = new IntegerKey(0xb050);
    public static final IntegerKey TAG_INTELLIGENT_AUTO = new IntegerKey(0xb052);
    public static final IntegerKey TAG_WHITE_BALANCE_2 = new IntegerKey(0xb054);

    public static final IntegerKey TAG_NO_PRINT = new IntegerKey(0xFFFF);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_CAMERA_INFO, "Camera Info");
        _tagNameMap.put(TAG_FOCUS_INFO, "Focus Info");

        _tagNameMap.put(TAG_IMAGE_QUALITY, "Image Quality");
        _tagNameMap.put(TAG_FLASH_EXPOSURE_COMP, "Flash Exposure Compensation");
        _tagNameMap.put(TAG_TELECONVERTER, "Teleconverter Model");

        _tagNameMap.put(TAG_WHITE_BALANCE_FINE_TUNE, "White Balance Fine Tune Value");
        _tagNameMap.put(TAG_CAMERA_SETTINGS, "Camera Settings");
        _tagNameMap.put(TAG_WHITE_BALANCE, "White Balance");
        _tagNameMap.put(TAG_EXTRA_INFO, "Extra Info");

        _tagNameMap.put(TAG_PRINT_IMAGE_MATCHING_INFO, "Print Image Matching (PIM) Info");

        _tagNameMap.put(TAG_MULTI_BURST_MODE, "Multi Burst Mode");
        _tagNameMap.put(TAG_MULTI_BURST_IMAGE_WIDTH, "Multi Burst Image Width");
        _tagNameMap.put(TAG_MULTI_BURST_IMAGE_HEIGHT, "Multi Burst Image Height");
        _tagNameMap.put(TAG_PANORAMA, "Panorama");

        _tagNameMap.put(TAG_PREVIEW_IMAGE, "Preview Image");
        _tagNameMap.put(TAG_RATING, "Rating");
        _tagNameMap.put(TAG_CONTRAST, "Contrast");
        _tagNameMap.put(TAG_SATURATION, "Saturation");
        _tagNameMap.put(TAG_SHARPNESS, "Sharpness");
        _tagNameMap.put(TAG_BRIGHTNESS, "Brightness");
        _tagNameMap.put(TAG_LONG_EXPOSURE_NOISE_REDUCTION, "Long Exposure Noise Reduction");
        _tagNameMap.put(TAG_HIGH_ISO_NOISE_REDUCTION, "High ISO Noise Reduction");
        _tagNameMap.put(TAG_HDR, "HDR");
        _tagNameMap.put(TAG_MULTI_FRAME_NOISE_REDUCTION, "Multi Frame Noise Reduction");
        _tagNameMap.put(TAG_PICTURE_EFFECT, "Picture Effect");
        _tagNameMap.put(TAG_SOFT_SKIN_EFFECT, "Soft Skin Effect");

        _tagNameMap.put(TAG_VIGNETTING_CORRECTION, "Vignetting Correction");
        _tagNameMap.put(TAG_LATERAL_CHROMATIC_ABERRATION, "Lateral Chromatic Aberration");
        _tagNameMap.put(TAG_DISTORTION_CORRECTION, "Distortion Correction");
        _tagNameMap.put(TAG_WB_SHIFT_AMBER_MAGENTA, "WB Shift Amber/Magenta");
        _tagNameMap.put(TAG_AUTO_PORTRAIT_FRAMED, "Auto Portrait Framing");
        _tagNameMap.put(TAG_FOCUS_MODE, "Focus Mode");
        _tagNameMap.put(TAG_AF_POINT_SELECTED, "AF Point Selected");

        _tagNameMap.put(TAG_SHOT_INFO, "Shot Info");

        _tagNameMap.put(TAG_FILE_FORMAT, "File Format");
        _tagNameMap.put(TAG_SONY_MODEL_ID, "Sony Model ID");

        _tagNameMap.put(TAG_COLOR_MODE_SETTING, "Color Mode Setting");
        _tagNameMap.put(TAG_COLOR_TEMPERATURE, "Color Temperature");
        _tagNameMap.put(TAG_COLOR_COMPENSATION_FILTER, "Color Compensation Filter");
        _tagNameMap.put(TAG_SCENE_MODE, "Scene Mode");
        _tagNameMap.put(TAG_ZONE_MATCHING, "Zone Matching");
        _tagNameMap.put(TAG_DYNAMIC_RANGE_OPTIMISER, "Dynamic Range Optimizer");
        _tagNameMap.put(TAG_IMAGE_STABILISATION, "Image Stabilisation");
        _tagNameMap.put(TAG_LENS_ID, "Lens ID");
        _tagNameMap.put(TAG_MINOLTA_MAKERNOTE, "Minolta Makernote");
        _tagNameMap.put(TAG_COLOR_MODE, "Color Mode");
        _tagNameMap.put(TAG_LENS_SPEC, "Lens Spec");
        _tagNameMap.put(TAG_FULL_IMAGE_SIZE, "Full Image Size");
        _tagNameMap.put(TAG_PREVIEW_IMAGE_SIZE, "Preview Image Size");

        _tagNameMap.put(TAG_MACRO, "Macro");
        _tagNameMap.put(TAG_EXPOSURE_MODE, "Exposure Mode");
        _tagNameMap.put(TAG_FOCUS_MODE_2, "Focus Mode");
        _tagNameMap.put(TAG_AF_MODE, "AF Mode");
        _tagNameMap.put(TAG_AF_ILLUMINATOR, "AF Illuminator");
        _tagNameMap.put(TAG_JPEG_QUALITY, "Quality");
        _tagNameMap.put(TAG_FLASH_LEVEL, "Flash Level");
        _tagNameMap.put(TAG_RELEASE_MODE, "Release Mode");
        _tagNameMap.put(TAG_SEQUENCE_NUMBER, "Sequence Number");
        _tagNameMap.put(TAG_ANTI_BLUR, "Anti Blur");
        _tagNameMap.put(TAG_LONG_EXPOSURE_NOISE_REDUCTION_OR_FOCUS_MODE, "Long Exposure Noise Reduction");
        _tagNameMap.put(TAG_DYNAMIC_RANGE_OPTIMIZER, "Dynamic Range Optimizer");

        _tagNameMap.put(TAG_HIGH_ISO_NOISE_REDUCTION_2, "High ISO Noise Reduction");
        _tagNameMap.put(TAG_INTELLIGENT_AUTO, "Intelligent Auto");
        _tagNameMap.put(TAG_WHITE_BALANCE_2, "White Balance 2");

        _tagNameMap.put(TAG_NO_PRINT, "No Print");
    }

    public SonyType1MakernoteDirectory()
    {
        this.setDescriptor(new SonyType1MakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Sony Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
