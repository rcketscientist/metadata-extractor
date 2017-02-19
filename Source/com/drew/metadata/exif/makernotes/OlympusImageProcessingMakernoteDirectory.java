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
 * The Olympus image processing makernote is used by many manufacturers (Epson, Konica, Minolta and Agfa...), and as such contains some tags
 * that appear specific to those manufacturers.
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class OlympusImageProcessingMakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAGImageProcessingVersion = new IntegerKey(0x0000);
    public static final IntegerKey TAGWbRbLevels = new IntegerKey(0x0100);
    // 0x0101 - in-camera AutoWB unless it is all 0's or all 256's (ref IB)
    public static final IntegerKey TAGWbRbLevels3000K = new IntegerKey(0x0102);
    public static final IntegerKey TAGWbRbLevels3300K = new IntegerKey(0x0103);
    public static final IntegerKey TAGWbRbLevels3600K = new IntegerKey(0x0104);
    public static final IntegerKey TAGWbRbLevels3900K = new IntegerKey(0x0105);
    public static final IntegerKey TAGWbRbLevels4000K = new IntegerKey(0x0106);
    public static final IntegerKey TAGWbRbLevels4300K = new IntegerKey(0x0107);
    public static final IntegerKey TAGWbRbLevels4500K = new IntegerKey(0x0108);
    public static final IntegerKey TAGWbRbLevels4800K = new IntegerKey(0x0109);
    public static final IntegerKey TAGWbRbLevels5300K = new IntegerKey(0x010a);
    public static final IntegerKey TAGWbRbLevels6000K = new IntegerKey(0x010b);
    public static final IntegerKey TAGWbRbLevels6600K = new IntegerKey(0x010c);
    public static final IntegerKey TAGWbRbLevels7500K = new IntegerKey(0x010d);
    public static final IntegerKey TAGWbRbLevelsCwB1 = new IntegerKey(0x010e);
    public static final IntegerKey TAGWbRbLevelsCwB2 = new IntegerKey(0x010f);
    public static final IntegerKey TAGWbRbLevelsCwB3 = new IntegerKey(0x0110);
    public static final IntegerKey TAGWbRbLevelsCwB4 = new IntegerKey(0x0111);
    public static final IntegerKey TAGWbGLevel3000K = new IntegerKey(0x0113);
    public static final IntegerKey TAGWbGLevel3300K = new IntegerKey(0x0114);
    public static final IntegerKey TAGWbGLevel3600K = new IntegerKey(0x0115);
    public static final IntegerKey TAGWbGLevel3900K = new IntegerKey(0x0116);
    public static final IntegerKey TAGWbGLevel4000K = new IntegerKey(0x0117);
    public static final IntegerKey TAGWbGLevel4300K = new IntegerKey(0x0118);
    public static final IntegerKey TAGWbGLevel4500K = new IntegerKey(0x0119);
    public static final IntegerKey TAGWbGLevel4800K = new IntegerKey(0x011a);
    public static final IntegerKey TAGWbGLevel5300K = new IntegerKey(0x011b);
    public static final IntegerKey TAGWbGLevel6000K = new IntegerKey(0x011c);
    public static final IntegerKey TAGWbGLevel6600K = new IntegerKey(0x011d);
    public static final IntegerKey TAGWbGLevel7500K = new IntegerKey(0x011e);
    public static final IntegerKey TAGWbGLevel = new IntegerKey(0x011f);
    // 0x0121 = WB preset for flash (about 6000K) (ref IB)
    // 0x0125 = WB preset for underwater (ref IB)

    public static final IntegerKey TAGColorMatrix = new IntegerKey(0x0200);
    // color matrices (ref 11):
    // 0x0201-0x020d are sRGB color matrices
    // 0x020e-0x021a are Adobe RGB color matrices
    // 0x021b-0x0227 are ProPhoto RGB color matrices
    // 0x0228 and 0x0229 are ColorMatrix for E-330
    // 0x0250-0x0252 are sRGB color matrices
    // 0x0253-0x0255 are Adobe RGB color matrices
    // 0x0256-0x0258 are ProPhoto RGB color matrices

    public static final IntegerKey TAGEnhancer = new IntegerKey(0x0300);
    public static final IntegerKey TAGEnhancerValues = new IntegerKey(0x0301);
    public static final IntegerKey TAGCoringFilter = new IntegerKey(0x0310);
    public static final IntegerKey TAGCoringValues = new IntegerKey(0x0311);
    public static final IntegerKey TAGBlackLevel2 = new IntegerKey(0x0600);
    public static final IntegerKey TAGGainBase = new IntegerKey(0x0610);
    public static final IntegerKey TAGValidBits = new IntegerKey(0x0611);
    public static final IntegerKey TAGCropLeft = new IntegerKey(0x0612);
    public static final IntegerKey TAGCropTop = new IntegerKey(0x0613);
    public static final IntegerKey TAGCropWidth = new IntegerKey(0x0614);
    public static final IntegerKey TAGCropHeight = new IntegerKey(0x0615);
    public static final IntegerKey TAGUnknownBlock1 = new IntegerKey(0x0635);
    public static final IntegerKey TAGUnknownBlock2 = new IntegerKey(0x0636);

    // 0x0800 LensDistortionParams, float[9] (ref 11)
    // 0x0801 LensShadingParams, int16u[16] (ref 11)
    public static final IntegerKey TAGSensorCalibration = new IntegerKey(0x0805);

    public static final IntegerKey TAGNoiseReduction2 = new IntegerKey(0x1010);
    public static final IntegerKey TAGDistortionCorrection2 = new IntegerKey(0x1011);
    public static final IntegerKey TAGShadingCompensation2 = new IntegerKey(0x1012);
    public static final IntegerKey TAGMultipleExposureMode = new IntegerKey(0x101c);
    public static final IntegerKey TAGUnknownBlock3 = new IntegerKey(0x1103);
    public static final IntegerKey TAGUnknownBlock4 = new IntegerKey(0x1104);
    public static final IntegerKey TAGAspectRatio = new IntegerKey(0x1112);
    public static final IntegerKey TAGAspectFrame = new IntegerKey(0x1113);
    public static final IntegerKey TAGFacesDetected = new IntegerKey(0x1200);
    public static final IntegerKey TAGFaceDetectArea = new IntegerKey(0x1201);
    public static final IntegerKey TAGMaxFaces = new IntegerKey(0x1202);
    public static final IntegerKey TAGFaceDetectFrameSize = new IntegerKey(0x1203);
    public static final IntegerKey TAGFaceDetectFrameCrop = new IntegerKey(0x1207);
    public static final IntegerKey TAGCameraTemperature = new IntegerKey(0x1306);

    public static final IntegerKey TAGKeystoneCompensation = new IntegerKey(0x1900);
    public static final IntegerKey TAGKeystoneDirection = new IntegerKey(0x1901);
    // 0x1905 - focal length (PH, E-M1)
    public static final IntegerKey TAGKeystoneValue = new IntegerKey(0x1906);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TagImageProcessingVersion, "Image Processing Version");
        _tagNameMap.put(TagWbRbLevels, "WB RB Levels");
        _tagNameMap.put(TagWbRbLevels3000K, "WB RB Levels 3000K");
        _tagNameMap.put(TagWbRbLevels3300K, "WB RB Levels 3300K");
        _tagNameMap.put(TagWbRbLevels3600K, "WB RB Levels 3600K");
        _tagNameMap.put(TagWbRbLevels3900K, "WB RB Levels 3900K");
        _tagNameMap.put(TagWbRbLevels4000K, "WB RB Levels 4000K");
        _tagNameMap.put(TagWbRbLevels4300K, "WB RB Levels 4300K");
        _tagNameMap.put(TagWbRbLevels4500K, "WB RB Levels 4500K");
        _tagNameMap.put(TagWbRbLevels4800K, "WB RB Levels 4800K");
        _tagNameMap.put(TagWbRbLevels5300K, "WB RB Levels 5300K");
        _tagNameMap.put(TagWbRbLevels6000K, "WB RB Levels 6000K");
        _tagNameMap.put(TagWbRbLevels6600K, "WB RB Levels 6600K");
        _tagNameMap.put(TagWbRbLevels7500K, "WB RB Levels 7500K");
        _tagNameMap.put(TagWbRbLevelsCwB1, "WB RB Levels CWB1");
        _tagNameMap.put(TagWbRbLevelsCwB2, "WB RB Levels CWB2");
        _tagNameMap.put(TagWbRbLevelsCwB3, "WB RB Levels CWB3");
        _tagNameMap.put(TagWbRbLevelsCwB4, "WB RB Levels CWB4");
        _tagNameMap.put(TagWbGLevel3000K, "WB G Level 3000K");
        _tagNameMap.put(TagWbGLevel3300K, "WB G Level 3300K");
        _tagNameMap.put(TagWbGLevel3600K, "WB G Level 3600K");
        _tagNameMap.put(TagWbGLevel3900K, "WB G Level 3900K");
        _tagNameMap.put(TagWbGLevel4000K, "WB G Level 4000K");
        _tagNameMap.put(TagWbGLevel4300K, "WB G Level 4300K");
        _tagNameMap.put(TagWbGLevel4500K, "WB G Level 4500K");
        _tagNameMap.put(TagWbGLevel4800K, "WB G Level 4800K");
        _tagNameMap.put(TagWbGLevel5300K, "WB G Level 5300K");
        _tagNameMap.put(TagWbGLevel6000K, "WB G Level 6000K");
        _tagNameMap.put(TagWbGLevel6600K, "WB G Level 6600K");
        _tagNameMap.put(TagWbGLevel7500K, "WB G Level 7500K");
        _tagNameMap.put(TagWbGLevel, "WB G Level");

        _tagNameMap.put(TagColorMatrix, "Color Matrix");

        _tagNameMap.put(TagEnhancer, "Enhancer");
        _tagNameMap.put(TagEnhancerValues, "Enhancer Values");
        _tagNameMap.put(TagCoringFilter, "Coring Filter");
        _tagNameMap.put(TagCoringValues, "Coring Values");
        _tagNameMap.put(TagBlackLevel2, "Black Level 2");
        _tagNameMap.put(TagGainBase, "Gain Base");
        _tagNameMap.put(TagValidBits, "Valid Bits");
        _tagNameMap.put(TagCropLeft, "Crop Left");
        _tagNameMap.put(TagCropTop, "Crop Top");
        _tagNameMap.put(TagCropWidth, "Crop Width");
        _tagNameMap.put(TagCropHeight, "Crop Height");
        _tagNameMap.put(TagUnknownBlock1, "Unknown Block 1");
        _tagNameMap.put(TagUnknownBlock2, "Unknown Block 2");

        _tagNameMap.put(TagSensorCalibration, "Sensor Calibration");

        _tagNameMap.put(TagNoiseReduction2, "Noise Reduction 2");
        _tagNameMap.put(TagDistortionCorrection2, "Distortion Correction 2");
        _tagNameMap.put(TagShadingCompensation2, "Shading Compensation 2");
        _tagNameMap.put(TagMultipleExposureMode, "Multiple Exposure Mode");
        _tagNameMap.put(TagUnknownBlock3, "Unknown Block 3");
        _tagNameMap.put(TagUnknownBlock4, "Unknown Block 4");
        _tagNameMap.put(TagAspectRatio, "Aspect Ratio");
        _tagNameMap.put(TagAspectFrame, "Aspect Frame");
        _tagNameMap.put(TagFacesDetected, "Faces Detected");
        _tagNameMap.put(TagFaceDetectArea, "Face Detect Area");
        _tagNameMap.put(TagMaxFaces, "Max Faces");
        _tagNameMap.put(TagFaceDetectFrameSize, "Face Detect Frame Size");
        _tagNameMap.put(TagFaceDetectFrameCrop, "Face Detect Frame Crop");
        _tagNameMap.put(TagCameraTemperature , "Camera Temperature");
        _tagNameMap.put(TagKeystoneCompensation, "Keystone Compensation");
        _tagNameMap.put(TagKeystoneDirection, "Keystone Direction");
        _tagNameMap.put(TagKeystoneValue, "Keystone Value");
    }

    public OlympusImageProcessingMakernoteDirectory()
    {
        this.setDescriptor(new OlympusImageProcessingMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Olympus Image Processing";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
