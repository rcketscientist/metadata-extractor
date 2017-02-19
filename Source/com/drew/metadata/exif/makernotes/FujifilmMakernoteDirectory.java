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
 * Describes tags specific to Fujifilm cameras.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class FujifilmMakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_MAKERNOTE_VERSION = new IntegerKey(0x0000);
    public static final IntegerKey TAG_SERIAL_NUMBER = new IntegerKey(0x0010);

    public static final IntegerKey TAG_QUALITY = new IntegerKey(0x1000);
    public static final IntegerKey TAG_SHARPNESS = new IntegerKey(0x1001);
    public static final IntegerKey TAG_WHITE_BALANCE = new IntegerKey(0x1002);
    public static final IntegerKey TAG_COLOR_SATURATION = new IntegerKey(0x1003);
    public static final IntegerKey TAG_TONE = new IntegerKey(0x1004);
    public static final IntegerKey TAG_COLOR_TEMPERATURE = new IntegerKey(0x1005);
    public static final IntegerKey TAG_CONTRAST = new IntegerKey(0x1006);

    public static final IntegerKey TAG_WHITE_BALANCE_FINE_TUNE = new IntegerKey(0x100a);
    public static final IntegerKey TAG_NOISE_REDUCTION = new IntegerKey(0x100b);
    public static final IntegerKey TAG_HIGH_ISO_NOISE_REDUCTION = new IntegerKey(0x100e);

    public static final IntegerKey TAG_FLASH_MODE = new IntegerKey(0x1010);
    public static final IntegerKey TAG_FLASH_EV = new IntegerKey(0x1011);

    public static final IntegerKey TAG_MACRO = new IntegerKey(0x1020);
    public static final IntegerKey TAG_FOCUS_MODE = new IntegerKey(0x1021);
    public static final IntegerKey TAG_FOCUS_PIXEL = new IntegerKey(0x1023);

    public static final IntegerKey TAG_SLOW_SYNC = new IntegerKey(0x1030);
    public static final IntegerKey TAG_PICTURE_MODE = new IntegerKey(0x1031);
    public static final IntegerKey TAG_EXR_AUTO = new IntegerKey(0x1033);
    public static final IntegerKey TAG_EXR_MODE = new IntegerKey(0x1034);

    public static final IntegerKey TAG_AUTO_BRACKETING = new IntegerKey(0x1100);
    public static final IntegerKey TAG_SEQUENCE_NUMBER = new IntegerKey(0x1101);

    public static final IntegerKey TAG_FINE_PIX_COLOR = new IntegerKey(0x1210);

    public static final IntegerKey TAG_BLUR_WARNING = new IntegerKey(0x1300);
    public static final IntegerKey TAG_FOCUS_WARNING = new IntegerKey(0x1301);
    public static final IntegerKey TAG_AUTO_EXPOSURE_WARNING = new IntegerKey(0x1302);
    public static final IntegerKey TAG_GE_IMAGE_SIZE = new IntegerKey(0x1304);

    public static final IntegerKey TAG_DYNAMIC_RANGE = new IntegerKey(0x1400);
    public static final IntegerKey TAG_FILM_MODE = new IntegerKey(0x1401);
    public static final IntegerKey TAG_DYNAMIC_RANGE_SETTING = new IntegerKey(0x1402);
    public static final IntegerKey TAG_DEVELOPMENT_DYNAMIC_RANGE = new IntegerKey(0x1403);
    public static final IntegerKey TAG_MIN_FOCAL_LENGTH = new IntegerKey(0x1404);
    public static final IntegerKey TAG_MAX_FOCAL_LENGTH = new IntegerKey(0x1405);
    public static final IntegerKey TAG_MAX_APERTURE_AT_MIN_FOCAL = new IntegerKey(0x1406);
    public static final IntegerKey TAG_MAX_APERTURE_AT_MAX_FOCAL = new IntegerKey(0x1407);

    public static final IntegerKey TAG_AUTO_DYNAMIC_RANGE = new IntegerKey(0x140b);

    public static final IntegerKey TAG_FACES_DETECTED = new IntegerKey(0x4100);
    /**
     * Left, top, right and bottom coordinates in full-sized image for each face detected.
     */
    public static final IntegerKey TAG_FACE_POSITIONS = new IntegerKey(0x4103);
    public static final IntegerKey TAG_FACE_REC_INFO = new IntegerKey(0x4282);

    public static final IntegerKey TAG_FILE_SOURCE = new IntegerKey(0x8000);
    public static final IntegerKey TAG_ORDER_NUMBER = new IntegerKey(0x8002);
    public static final IntegerKey TAG_FRAME_NUMBER = new IntegerKey(0x8003);

    public static final IntegerKey TAG_PARALLAX = new IntegerKey(0xb211);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TAG_MAKERNOTE_VERSION, "Makernote Version");
        _tagNameMap.put(TAG_SERIAL_NUMBER, "Serial Number");

        _tagNameMap.put(TAG_QUALITY, "Quality");
        _tagNameMap.put(TAG_SHARPNESS, "Sharpness");
        _tagNameMap.put(TAG_WHITE_BALANCE, "White Balance");
        _tagNameMap.put(TAG_COLOR_SATURATION, "Color Saturation");
        _tagNameMap.put(TAG_TONE, "Tone (Contrast)");
        _tagNameMap.put(TAG_COLOR_TEMPERATURE, "Color Temperature");
        _tagNameMap.put(TAG_CONTRAST, "Contrast");

        _tagNameMap.put(TAG_WHITE_BALANCE_FINE_TUNE, "White Balance Fine Tune");
        _tagNameMap.put(TAG_NOISE_REDUCTION, "Noise Reduction");
        _tagNameMap.put(TAG_HIGH_ISO_NOISE_REDUCTION, "High ISO Noise Reduction");

        _tagNameMap.put(TAG_FLASH_MODE, "Flash Mode");
        _tagNameMap.put(TAG_FLASH_EV, "Flash Strength");

        _tagNameMap.put(TAG_MACRO, "Macro");
        _tagNameMap.put(TAG_FOCUS_MODE, "Focus Mode");
        _tagNameMap.put(TAG_FOCUS_PIXEL, "Focus Pixel");

        _tagNameMap.put(TAG_SLOW_SYNC, "Slow Sync");
        _tagNameMap.put(TAG_PICTURE_MODE, "Picture Mode");
        _tagNameMap.put(TAG_EXR_AUTO, "EXR Auto");
        _tagNameMap.put(TAG_EXR_MODE, "EXR Mode");

        _tagNameMap.put(TAG_AUTO_BRACKETING, "Auto Bracketing");
        _tagNameMap.put(TAG_SEQUENCE_NUMBER, "Sequence Number");

        _tagNameMap.put(TAG_FINE_PIX_COLOR, "FinePix Color Setting");

        _tagNameMap.put(TAG_BLUR_WARNING, "Blur Warning");
        _tagNameMap.put(TAG_FOCUS_WARNING, "Focus Warning");
        _tagNameMap.put(TAG_AUTO_EXPOSURE_WARNING, "AE Warning");
        _tagNameMap.put(TAG_GE_IMAGE_SIZE, "GE Image Size");

        _tagNameMap.put(TAG_DYNAMIC_RANGE, "Dynamic Range");
        _tagNameMap.put(TAG_FILM_MODE, "Film Mode");
        _tagNameMap.put(TAG_DYNAMIC_RANGE_SETTING, "Dynamic Range Setting");
        _tagNameMap.put(TAG_DEVELOPMENT_DYNAMIC_RANGE, "Development Dynamic Range");
        _tagNameMap.put(TAG_MIN_FOCAL_LENGTH, "Minimum Focal Length");
        _tagNameMap.put(TAG_MAX_FOCAL_LENGTH, "Maximum Focal Length");
        _tagNameMap.put(TAG_MAX_APERTURE_AT_MIN_FOCAL, "Maximum Aperture at Minimum Focal Length");
        _tagNameMap.put(TAG_MAX_APERTURE_AT_MAX_FOCAL, "Maximum Aperture at Maximum Focal Length");

        _tagNameMap.put(TAG_AUTO_DYNAMIC_RANGE, "Auto Dynamic Range");

        _tagNameMap.put(TAG_FACES_DETECTED, "Faces Detected");
        _tagNameMap.put(TAG_FACE_POSITIONS, "Face Positions");
        _tagNameMap.put(TAG_FACE_REC_INFO, "Face Detection Data");

        _tagNameMap.put(TAG_FILE_SOURCE, "File Source");
        _tagNameMap.put(TAG_ORDER_NUMBER, "Order Number");
        _tagNameMap.put(TAG_FRAME_NUMBER, "Frame Number");

        _tagNameMap.put(TAG_PARALLAX, "Parallax");
    }

    public FujifilmMakernoteDirectory()
    {
        this.setDescriptor(new FujifilmMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Fujifilm Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
