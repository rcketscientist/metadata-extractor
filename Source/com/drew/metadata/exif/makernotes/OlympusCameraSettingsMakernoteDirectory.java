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
 * The Olympus camera settings makernote is used by many manufacturers (Epson, Konica, Minolta and Agfa...), and as such contains some tags
 * that appear specific to those manufacturers.
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class OlympusCameraSettingsMakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAGCameraSettingsVersion = new IntegerKey(0x0000);
    public static final IntegerKey TAGPreviewImageValid = new IntegerKey(0x0100);
    public static final IntegerKey TAGPreviewImageStart = new IntegerKey(0x0101);
    public static final IntegerKey TAGPreviewImageLength = new IntegerKey(0x0102);

    public static final IntegerKey TAGExposureMode = new IntegerKey(0x0200);
    public static final IntegerKey TAGAeLock = new IntegerKey(0x0201);
    public static final IntegerKey TAGMeteringMode = new IntegerKey(0x0202);
    public static final IntegerKey TAGExposureShift = new IntegerKey(0x0203);
    public static final IntegerKey TAGNdFilter = new IntegerKey(0x0204);

    public static final IntegerKey TAGMacroMode = new IntegerKey(0x0300);
    public static final IntegerKey TAGFocusMode = new IntegerKey(0x0301);
    public static final IntegerKey TAGFocusProcess = new IntegerKey(0x0302);
    public static final IntegerKey TAGAfSearch = new IntegerKey(0x0303);
    public static final IntegerKey TAGAfAreas = new IntegerKey(0x0304);
    public static final IntegerKey TAGAfPointSelected = new IntegerKey(0x0305);
    public static final IntegerKey TAGAfFineTune = new IntegerKey(0x0306);
    public static final IntegerKey TAGAfFineTuneAdj = new IntegerKey(0x0307);

    public static final IntegerKey TAGFlashMode = new IntegerKey(0x400;
    public static final IntegerKey TAGFlashExposureComp = new IntegerKey(0x401;
    public static final IntegerKey TAGFlashRemoteControl = new IntegerKey(0x403;
    public static final IntegerKey TAGFlashControlMode = new IntegerKey(0x404;
    public static final IntegerKey TAGFlashIntensity = new IntegerKey(0x405;
    public static final IntegerKey TAGManualFlashStrength = new IntegerKey(0x406;

    public static final IntegerKey TAGWhiteBalance2 = new IntegerKey(0x500;
    public static final IntegerKey TAGWhiteBalanceTemperature = new IntegerKey(0x501;
    public static final IntegerKey TAGWhiteBalanceBracket = new IntegerKey(0x502;
    public static final IntegerKey TAGCustomSaturation = new IntegerKey(0x503;
    public static final IntegerKey TAGModifiedSaturation = new IntegerKey(0x504;
    public static final IntegerKey TAGContrastSetting = new IntegerKey(0x505;
    public static final IntegerKey TAGSharpnessSetting = new IntegerKey(0x506;
    public static final IntegerKey TAGColorSpace = new IntegerKey(0x507;
    public static final IntegerKey TAGSceneMode = new IntegerKey(0x509;
    public static final IntegerKey TAGNoiseReduction = new IntegerKey(0x50a;
    public static final IntegerKey TAGDistortionCorrection = new IntegerKey(0x50b;
    public static final IntegerKey TAGShadingCompensation = new IntegerKey(0x50c;
    public static final IntegerKey TAGCompressionFactor = new IntegerKey(0x50d;
    public static final IntegerKey TAGGradation = new IntegerKey(0x50f;
    public static final IntegerKey TAGPictureMode = new IntegerKey(0x520;
    public static final IntegerKey TAGPictureModeSaturation = new IntegerKey(0x521;
    public static final IntegerKey TAGPictureModeHue = new IntegerKey(0x522;
    public static final IntegerKey TAGPictureModeContrast = new IntegerKey(0x523;
    public static final IntegerKey TAGPictureModeSharpness = new IntegerKey(0x524;
    public static final IntegerKey TAGPictureModeBWFilter = new IntegerKey(0x525;
    public static final IntegerKey TAGPictureModeTone = new IntegerKey(0x526;
    public static final IntegerKey TAGNoiseFilter = new IntegerKey(0x527;
    public static final IntegerKey TAGArtFilter = new IntegerKey(0x529;
    public static final IntegerKey TAGMagicFilter = new IntegerKey(0x52c;
    public static final IntegerKey TAGPictureModeEffect = new IntegerKey(0x52d;
    public static final IntegerKey TAGToneLevel = new IntegerKey(0x52e;
    public static final IntegerKey TAGArtFilterEffect = new IntegerKey(0x52f;
    public static final IntegerKey TAGColorCreatorEffect = new IntegerKey(0x532;

    public static final IntegerKey TAGDriveMode = new IntegerKey(0x600;
    public static final IntegerKey TAGPanoramaMode = new IntegerKey(0x601;
    public static final IntegerKey TAGImageQuality2 = new IntegerKey(0x603;
    public static final IntegerKey TAGImageStabilization = new IntegerKey(0x604;

    public static final IntegerKey TAGStackedImage = new IntegerKey(0x804;

    public static final IntegerKey TAGManometerPressure = new IntegerKey(0x900;
    public static final IntegerKey TAGManometerReading = new IntegerKey(0x901;
    public static final IntegerKey TAGExtendedWBDetect = new IntegerKey(0x902;
    public static final IntegerKey TAGRollAngle = new IntegerKey(0x903;
    public static final IntegerKey TAGPitchAngle = new IntegerKey(0x904;
    public static final IntegerKey TAGDateTimeUtc = new IntegerKey(0x908;

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TagCameraSettingsVersion, "Camera Settings Version");
        _tagNameMap.put(TagPreviewImageValid, "Preview Image Valid");
        _tagNameMap.put(TagPreviewImageStart, "Preview Image Start");
        _tagNameMap.put(TagPreviewImageLength, "Preview Image Length");

        _tagNameMap.put(TagExposureMode, "Exposure Mode");
        _tagNameMap.put(TagAeLock, "AE Lock");
        _tagNameMap.put(TagMeteringMode, "Metering Mode");
        _tagNameMap.put(TagExposureShift, "Exposure Shift");
        _tagNameMap.put(TagNdFilter, "ND Filter");

        _tagNameMap.put(TagMacroMode, "Macro Mode");
        _tagNameMap.put(TagFocusMode, "Focus Mode");
        _tagNameMap.put(TagFocusProcess, "Focus Process");
        _tagNameMap.put(TagAfSearch, "AF Search");
        _tagNameMap.put(TagAfAreas, "AF Areas");
        _tagNameMap.put(TagAfPointSelected, "AF Point Selected");
        _tagNameMap.put(TagAfFineTune, "AF Fine Tune");
        _tagNameMap.put(TagAfFineTuneAdj, "AF Fine Tune Adj");

        _tagNameMap.put(TagFlashMode, "Flash Mode");
        _tagNameMap.put(TagFlashExposureComp, "Flash Exposure Comp");
        _tagNameMap.put(TagFlashRemoteControl, "Flash Remote Control");
        _tagNameMap.put(TagFlashControlMode, "Flash Control Mode");
        _tagNameMap.put(TagFlashIntensity, "Flash Intensity");
        _tagNameMap.put(TagManualFlashStrength, "Manual Flash Strength");

        _tagNameMap.put(TagWhiteBalance2, "White Balance 2");
        _tagNameMap.put(TagWhiteBalanceTemperature, "White Balance Temperature");
        _tagNameMap.put(TagWhiteBalanceBracket, "White Balance Bracket");
        _tagNameMap.put(TagCustomSaturation, "Custom Saturation");
        _tagNameMap.put(TagModifiedSaturation, "Modified Saturation");
        _tagNameMap.put(TagContrastSetting, "Contrast Setting");
        _tagNameMap.put(TagSharpnessSetting, "Sharpness Setting");
        _tagNameMap.put(TagColorSpace, "Color Space");
        _tagNameMap.put(TagSceneMode, "Scene Mode");
        _tagNameMap.put(TagNoiseReduction, "Noise Reduction");
        _tagNameMap.put(TagDistortionCorrection, "Distortion Correction");
        _tagNameMap.put(TagShadingCompensation, "Shading Compensation");
        _tagNameMap.put(TagCompressionFactor, "Compression Factor");
        _tagNameMap.put(TagGradation, "Gradation");
        _tagNameMap.put(TagPictureMode, "Picture Mode");
        _tagNameMap.put(TagPictureModeSaturation, "Picture Mode Saturation");
        _tagNameMap.put(TagPictureModeHue, "Picture Mode Hue");
        _tagNameMap.put(TagPictureModeContrast, "Picture Mode Contrast");
        _tagNameMap.put(TagPictureModeSharpness, "Picture Mode Sharpness");
        _tagNameMap.put(TagPictureModeBWFilter, "Picture Mode BW Filter");
        _tagNameMap.put(TagPictureModeTone, "Picture Mode Tone");
        _tagNameMap.put(TagNoiseFilter, "Noise Filter");
        _tagNameMap.put(TagArtFilter, "Art Filter");
        _tagNameMap.put(TagMagicFilter, "Magic Filter");
        _tagNameMap.put(TagPictureModeEffect, "Picture Mode Effect");
        _tagNameMap.put(TagToneLevel, "Tone Level");
        _tagNameMap.put(TagArtFilterEffect, "Art Filter Effect");
        _tagNameMap.put(TagColorCreatorEffect, "Color Creator Effect");

        _tagNameMap.put(TagDriveMode, "Drive Mode");
        _tagNameMap.put(TagPanoramaMode, "Panorama Mode");
        _tagNameMap.put(TagImageQuality2, "Image Quality 2");
        _tagNameMap.put(TagImageStabilization, "Image Stabilization");

        _tagNameMap.put(TagStackedImage, "Stacked Image");

        _tagNameMap.put(TagManometerPressure, "Manometer Pressure");
        _tagNameMap.put(TagManometerReading, "Manometer Reading");
        _tagNameMap.put(TagExtendedWBDetect, "Extended WB Detect");
        _tagNameMap.put(TagRollAngle, "Roll Angle");
        _tagNameMap.put(TagPitchAngle, "Pitch Angle");
        _tagNameMap.put(TagDateTimeUtc, "Date Time UTC");
    }

    public OlympusCameraSettingsMakernoteDirectory()
    {
        this.setDescriptor(new OlympusCameraSettingsMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Olympus Camera Settings";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
