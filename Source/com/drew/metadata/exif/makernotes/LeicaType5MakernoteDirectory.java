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
 * Describes tags specific to certain Leica cameras.
 * <p>
 * Tag reference from: http://www.sno.phy.queensu.ca/~phil/exiftool/TagNames/Panasonic.html
 *
 * @author Kevin Mott https://github.com/kwhopper
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class LeicaType5MakernoteDirectory extends Directory<IntegerKey>
{
    public static final IntegerKey TAGLensModel = new IntegerKey(0x0303);
    public static final IntegerKey TAGOriginalFileName = new IntegerKey(0x0407);
    public static final IntegerKey TAGOriginalDirectory = new IntegerKey(0x0408);
    public static final IntegerKey TAGExposureMode = new IntegerKey(0x040d);
    public static final IntegerKey TAGShotInfo = new IntegerKey(0x0410);
    public static final IntegerKey TAGFilmMode = new IntegerKey(0x0412);
    public static final IntegerKey TAGWbRgbLevels = new IntegerKey(0x0413);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        _tagNameMap.put(TagLensModel, "Lens Model");
        _tagNameMap.put(TagOriginalFileName, "Original File Name");
        _tagNameMap.put(TagOriginalDirectory, "Original Directory");
        _tagNameMap.put(TagExposureMode, "Exposure Mode");
        _tagNameMap.put(TagShotInfo, "Shot Info" );
        _tagNameMap.put(TagFilmMode, "Film Mode");
        _tagNameMap.put(TagWbRgbLevels, "WB RGB Levels");
    }

    public LeicaType5MakernoteDirectory()
    {
        this.setDescriptor(new LeicaType5MakernoteDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Leica Makernote";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
