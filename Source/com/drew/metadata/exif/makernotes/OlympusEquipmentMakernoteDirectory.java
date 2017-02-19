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
 * The Olympus equipment makernote is used by many manufacturers (Epson, Konica, Minolta and Agfa...), and as such contains some tags
 * that appear specific to those manufacturers.
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class OlympusEquipmentMakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAG_EQUIPMENT_VERSION = new IntegerKey(0x0000);
    public static final IntegerKey TAG_CAMERA_TYPE_2 = new IntegerKey(0x0100);
    public static final IntegerKey TAG_SERIAL_NUMBER = new IntegerKey(0x0101);

    public static final IntegerKey TAG_INTERNAL_SERIAL_NUMBER = new IntegerKey(0x0102);
    public static final IntegerKey TAG_FOCAL_PLANE_DIAGONAL = new IntegerKey(0x0103);
    public static final IntegerKey TAG_BODY_FIRMWARE_VERSION = new IntegerKey(0x0104);

    public static final IntegerKey TAG_LENS_TYPE = new IntegerKey(0x0201);
    public static final IntegerKey TAG_LENS_SERIAL_NUMBER = new IntegerKey(0x0202);
    public static final IntegerKey TAG_LENS_MODEL = new IntegerKey(0x0203);
    public static final IntegerKey TAG_LENS_FIRMWARE_VERSION = new IntegerKey(0x0204);
    public static final IntegerKey TAG_MAX_APERTURE_AT_MIN_FOCAL = new IntegerKey(0x0205);
    public static final IntegerKey TAG_MAX_APERTURE_AT_MAX_FOCAL = new IntegerKey(0x0206);
    public static final IntegerKey TAG_MIN_FOCAL_LENGTH = new IntegerKey(0x0207);
    public static final IntegerKey TAG_MAX_FOCAL_LENGTH = new IntegerKey(0x0208);
    public static final IntegerKey TAG_MAX_APERTURE = new IntegerKey(0x020A);
    public static final IntegerKey TAG_LENS_PROPERTIES = new IntegerKey(0x020B);

    public static final IntegerKey TAG_EXTENDER = new IntegerKey(0x0301);
    public static final IntegerKey TAG_EXTENDER_SERIAL_NUMBER = new IntegerKey(0x0302);
    public static final IntegerKey TAG_EXTENDER_MODEL = new IntegerKey(0x0303);
    public static final IntegerKey TAG_EXTENDER_FIRMWARE_VERSION = new IntegerKey(0x0304);

    public static final IntegerKey TAG_CONVERSION_LENS = new IntegerKey(0x0403);

    public static final IntegerKey TAG_FLASH_TYPE = new IntegerKey(0x1000);
    public static final IntegerKey TAG_FLASH_MODEL = new IntegerKey(0x1001);
    public static final IntegerKey TAG_FLASH_FIRMWARE_VERSION = new IntegerKey(0x1002);
    public static final IntegerKey TAG_FLASH_SERIAL_NUMBER = new IntegerKey(0x1003);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static {
        _tagNameMap.put(TAG_EQUIPMENT_VERSION, "Equipment Version");
        _tagNameMap.put(TAG_CAMERA_TYPE_2, "Camera Type 2");
        _tagNameMap.put(TAG_SERIAL_NUMBER, "Serial Number");
        _tagNameMap.put(TAG_INTERNAL_SERIAL_NUMBER, "Internal Serial Number");
        _tagNameMap.put(TAG_FOCAL_PLANE_DIAGONAL, "Focal Plane Diagonal");
        _tagNameMap.put(TAG_BODY_FIRMWARE_VERSION, "Body Firmware Version");
        _tagNameMap.put(TAG_LENS_TYPE, "Lens Type");
        _tagNameMap.put(TAG_LENS_SERIAL_NUMBER, "Lens Serial Number");
        _tagNameMap.put(TAG_LENS_MODEL, "Lens Model");
        _tagNameMap.put(TAG_LENS_FIRMWARE_VERSION, "Lens Firmware Version");
        _tagNameMap.put(TAG_MAX_APERTURE_AT_MIN_FOCAL, "Max Aperture At Min Focal");
        _tagNameMap.put(TAG_MAX_APERTURE_AT_MAX_FOCAL, "Max Aperture At Max Focal");
        _tagNameMap.put(TAG_MIN_FOCAL_LENGTH, "Min Focal Length");
        _tagNameMap.put(TAG_MAX_FOCAL_LENGTH, "Max Focal Length");
        _tagNameMap.put(TAG_MAX_APERTURE, "Max Aperture");
        _tagNameMap.put(TAG_LENS_PROPERTIES, "Lens Properties");
        _tagNameMap.put(TAG_EXTENDER, "Extender");
        _tagNameMap.put(TAG_EXTENDER_SERIAL_NUMBER, "Extender Serial Number");
        _tagNameMap.put(TAG_EXTENDER_MODEL, "Extender Model");
        _tagNameMap.put(TAG_EXTENDER_FIRMWARE_VERSION, "Extender Firmware Version");
        _tagNameMap.put(TAG_CONVERSION_LENS, "Conversion Lens");
        _tagNameMap.put(TAG_FLASH_TYPE, "Flash Type");
        _tagNameMap.put(TAG_FLASH_MODEL, "Flash Model");
        _tagNameMap.put(TAG_FLASH_FIRMWARE_VERSION, "Flash Firmware Version");
        _tagNameMap.put(TAG_FLASH_SERIAL_NUMBER, "Flash Serial Number");
    }

    public OlympusEquipmentMakernoteDirectory()
    {
        this.setDescriptor(new OlympusEquipmentMakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Olympus Equipment";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
