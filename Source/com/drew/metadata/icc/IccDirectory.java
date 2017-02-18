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
package com.drew.metadata.icc;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * @author Yuri Binev
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class IccDirectory extends Directory<IntegerKey>
{
    // These (smaller valued) tags have an integer value that's equal to their offset within the ICC data buffer.

    public static final IntegerKey TAG_PROFILE_BYTE_COUNT = new IntegerKey(0);
    public static final IntegerKey TAG_CMM_TYPE = new IntegerKey(4);
    public static final IntegerKey TAG_PROFILE_VERSION = new IntegerKey(8);
    public static final IntegerKey TAG_PROFILE_CLASS = new IntegerKey(12);
    public static final IntegerKey TAG_COLOR_SPACE = new IntegerKey(16);
    public static final IntegerKey TAG_PROFILE_CONNECTION_SPACE = new IntegerKey(20);
    public static final IntegerKey TAG_PROFILE_DATETIME = new IntegerKey(24);
    public static final IntegerKey TAG_SIGNATURE = new IntegerKey(36);
    public static final IntegerKey TAG_PLATFORM = new IntegerKey(40);
    public static final IntegerKey TAG_CMM_FLAGS = new IntegerKey(44);
    public static final IntegerKey TAG_DEVICE_MAKE = new IntegerKey(48);
    public static final IntegerKey TAG_DEVICE_MODEL = new IntegerKey(52);
    public static final IntegerKey TAG_DEVICE_ATTR = new IntegerKey(56);
    public static final IntegerKey TAG_RENDERING_INTENT = new IntegerKey(64);
    public static final IntegerKey TAG_XYZ_VALUES = new IntegerKey(68);
    public static final IntegerKey TAG_PROFILE_CREATOR = new IntegerKey(80);
    public static final IntegerKey TAG_TAG_COUNT = new IntegerKey(128);

    // These tag values

    public static final IntegerKey TAG_TAG_A2B0 = new IntegerKey(0x41324230);
    public static final IntegerKey TAG_TAG_A2B1 = new IntegerKey(0x41324231);
    public static final IntegerKey TAG_TAG_A2B2 = new IntegerKey(0x41324232);
    public static final IntegerKey TAG_TAG_bXYZ = new IntegerKey(0x6258595A);
    public static final IntegerKey TAG_TAG_bTRC = new IntegerKey(0x62545243);
    public static final IntegerKey TAG_TAG_B2A0 = new IntegerKey(0x42324130);
    public static final IntegerKey TAG_TAG_B2A1 = new IntegerKey(0x42324131);
    public static final IntegerKey TAG_TAG_B2A2 = new IntegerKey(0x42324132);
    public static final IntegerKey TAG_TAG_calt = new IntegerKey(0x63616C74);
    public static final IntegerKey TAG_TAG_targ = new IntegerKey(0x74617267);
    public static final IntegerKey TAG_TAG_chad = new IntegerKey(0x63686164);
    public static final IntegerKey TAG_TAG_chrm = new IntegerKey(0x6368726D);
    public static final IntegerKey TAG_TAG_cprt = new IntegerKey(0x63707274);
    public static final IntegerKey TAG_TAG_crdi = new IntegerKey(0x63726469);
    public static final IntegerKey TAG_TAG_dmnd = new IntegerKey(0x646D6E64);
    public static final IntegerKey TAG_TAG_dmdd = new IntegerKey(0x646D6464);
    public static final IntegerKey TAG_TAG_devs = new IntegerKey(0x64657673);
    public static final IntegerKey TAG_TAG_gamt = new IntegerKey(0x67616D74);
    public static final IntegerKey TAG_TAG_kTRC = new IntegerKey(0x6B545243);
    public static final IntegerKey TAG_TAG_gXYZ = new IntegerKey(0x6758595A);
    public static final IntegerKey TAG_TAG_gTRC = new IntegerKey(0x67545243);
    public static final IntegerKey TAG_TAG_lumi = new IntegerKey(0x6C756D69);
    public static final IntegerKey TAG_TAG_meas = new IntegerKey(0x6D656173);
    public static final IntegerKey TAG_TAG_bkpt = new IntegerKey(0x626B7074);
    public static final IntegerKey TAG_TAG_wtpt = new IntegerKey(0x77747074);
    public static final IntegerKey TAG_TAG_ncol = new IntegerKey(0x6E636F6C);
    public static final IntegerKey TAG_TAG_ncl2 = new IntegerKey(0x6E636C32);
    public static final IntegerKey TAG_TAG_resp = new IntegerKey(0x72657370);
    public static final IntegerKey TAG_TAG_pre0 = new IntegerKey(0x70726530);
    public static final IntegerKey TAG_TAG_pre1 = new IntegerKey(0x70726531);
    public static final IntegerKey TAG_TAG_pre2 = new IntegerKey(0x70726532);
    public static final IntegerKey TAG_TAG_desc = new IntegerKey(0x64657363);
    public static final IntegerKey TAG_TAG_pseq = new IntegerKey(0x70736571);
    public static final IntegerKey TAG_TAG_psd0 = new IntegerKey(0x70736430);
    public static final IntegerKey TAG_TAG_psd1 = new IntegerKey(0x70736431);
    public static final IntegerKey TAG_TAG_psd2 = new IntegerKey(0x70736432);
    public static final IntegerKey TAG_TAG_psd3 = new IntegerKey(0x70736433);
    public static final IntegerKey TAG_TAG_ps2s = new IntegerKey(0x70733273);
    public static final IntegerKey TAG_TAG_ps2i = new IntegerKey(0x70733269);
    public static final IntegerKey TAG_TAG_rXYZ = new IntegerKey(0x7258595A);
    public static final IntegerKey TAG_TAG_rTRC = new IntegerKey(0x72545243);
    public static final IntegerKey TAG_TAG_scrd = new IntegerKey(0x73637264);
    public static final IntegerKey TAG_TAG_scrn = new IntegerKey(0x7363726E);
    public static final IntegerKey TAG_TAG_tech = new IntegerKey(0x74656368);
    public static final IntegerKey TAG_TAG_bfd = new IntegerKey(0x62666420);
    public static final IntegerKey TAG_TAG_vued = new IntegerKey(0x76756564);
    public static final IntegerKey TAG_TAG_view = new IntegerKey(0x76696577);

    public static final IntegerKey TAG_APPLE_MULTI_LANGUAGE_PROFILE_NAME = new IntegerKey(0x6473636d);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TAG_PROFILE_BYTE_COUNT, "Profile Size");
        _tagNameMap.put(TAG_CMM_TYPE, "CMM Type");
        _tagNameMap.put(TAG_PROFILE_VERSION, "Version");
        _tagNameMap.put(TAG_PROFILE_CLASS, "Class");
        _tagNameMap.put(TAG_COLOR_SPACE, "Color space");
        _tagNameMap.put(TAG_PROFILE_CONNECTION_SPACE, "Profile Connection Space");
        _tagNameMap.put(TAG_PROFILE_DATETIME, "Profile Date/Time");
        _tagNameMap.put(TAG_SIGNATURE, "Signature");
        _tagNameMap.put(TAG_PLATFORM, "Primary Platform");
        _tagNameMap.put(TAG_CMM_FLAGS, "CMM Flags");
        _tagNameMap.put(TAG_DEVICE_MAKE, "Device manufacturer");
        _tagNameMap.put(TAG_DEVICE_MODEL, "Device model");
        _tagNameMap.put(TAG_DEVICE_ATTR, "Device attributes");
        _tagNameMap.put(TAG_RENDERING_INTENT, "Rendering Intent");
        _tagNameMap.put(TAG_XYZ_VALUES, "XYZ values");
        _tagNameMap.put(TAG_PROFILE_CREATOR, "Profile Creator");
        _tagNameMap.put(TAG_TAG_COUNT, "Tag Count");
        _tagNameMap.put(TAG_TAG_A2B0, "AToB 0");
        _tagNameMap.put(TAG_TAG_A2B1, "AToB 1");
        _tagNameMap.put(TAG_TAG_A2B2, "AToB 2");
        _tagNameMap.put(TAG_TAG_bXYZ, "Blue Colorant");
        _tagNameMap.put(TAG_TAG_bTRC, "Blue TRC");
        _tagNameMap.put(TAG_TAG_B2A0, "BToA 0");
        _tagNameMap.put(TAG_TAG_B2A1, "BToA 1");
        _tagNameMap.put(TAG_TAG_B2A2, "BToA 2");
        _tagNameMap.put(TAG_TAG_calt, "Calibration Date/Time");
        _tagNameMap.put(TAG_TAG_targ, "Char Target");
        _tagNameMap.put(TAG_TAG_chad, "Chromatic Adaptation");
        _tagNameMap.put(TAG_TAG_chrm, "Chromaticity");
        _tagNameMap.put(TAG_TAG_cprt, "Copyright");
        _tagNameMap.put(TAG_TAG_crdi, "CrdInfo");
        _tagNameMap.put(TAG_TAG_dmnd, "Device Mfg Description");
        _tagNameMap.put(TAG_TAG_dmdd, "Device Model Description");
        _tagNameMap.put(TAG_TAG_devs, "Device Settings");
        _tagNameMap.put(TAG_TAG_gamt, "Gamut");
        _tagNameMap.put(TAG_TAG_kTRC, "Gray TRC");
        _tagNameMap.put(TAG_TAG_gXYZ, "Green Colorant");
        _tagNameMap.put(TAG_TAG_gTRC, "Green TRC");
        _tagNameMap.put(TAG_TAG_lumi, "Luminance");
        _tagNameMap.put(TAG_TAG_meas, "Measurement");
        _tagNameMap.put(TAG_TAG_bkpt, "Media Black Point");
        _tagNameMap.put(TAG_TAG_wtpt, "Media White Point");
        _tagNameMap.put(TAG_TAG_ncol, "Named Color");
        _tagNameMap.put(TAG_TAG_ncl2, "Named Color 2");
        _tagNameMap.put(TAG_TAG_resp, "Output Response");
        _tagNameMap.put(TAG_TAG_pre0, "Preview 0");
        _tagNameMap.put(TAG_TAG_pre1, "Preview 1");
        _tagNameMap.put(TAG_TAG_pre2, "Preview 2");
        _tagNameMap.put(TAG_TAG_desc, "Profile Description");
        _tagNameMap.put(TAG_TAG_pseq, "Profile Sequence Description");
        _tagNameMap.put(TAG_TAG_psd0, "Ps2 CRD 0");
        _tagNameMap.put(TAG_TAG_psd1, "Ps2 CRD 1");
        _tagNameMap.put(TAG_TAG_psd2, "Ps2 CRD 2");
        _tagNameMap.put(TAG_TAG_psd3, "Ps2 CRD 3");
        _tagNameMap.put(TAG_TAG_ps2s, "Ps2 CSA");
        _tagNameMap.put(TAG_TAG_ps2i, "Ps2 Rendering Intent");
        _tagNameMap.put(TAG_TAG_rXYZ, "Red Colorant");
        _tagNameMap.put(TAG_TAG_rTRC, "Red TRC");
        _tagNameMap.put(TAG_TAG_scrd, "Screening Desc");
        _tagNameMap.put(TAG_TAG_scrn, "Screening");
        _tagNameMap.put(TAG_TAG_tech, "Technology");
        _tagNameMap.put(TAG_TAG_bfd, "Ucrbg");
        _tagNameMap.put(TAG_TAG_vued, "Viewing Conditions Description");
        _tagNameMap.put(TAG_TAG_view, "Viewing Conditions");
        _tagNameMap.put(TAG_APPLE_MULTI_LANGUAGE_PROFILE_NAME, "Apple Multi-language Profile Name");
    }

    public IccDirectory()
    {
        this.setDescriptor(new IccDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "ICC Profile";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
