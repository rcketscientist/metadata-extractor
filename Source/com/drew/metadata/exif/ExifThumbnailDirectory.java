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
import com.drew.metadata.IntegerKey;

import java.util.HashMap;

/**
 * One of several Exif directories.  Otherwise known as IFD1, this directory holds information about an embedded thumbnail image.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public class ExifThumbnailDirectory extends ExifDirectoryBase
{
    /**
     * The offset to thumbnail image bytes.
     */
    public static final IntegerKey TAG_THUMBNAIL_OFFSET = new IntegerKey(0x0201);
    /**
     * The size of the thumbnail image data in bytes.
     */
    public static final IntegerKey TAG_THUMBNAIL_LENGTH = new IntegerKey(0x0202);

    /**
     * @deprecated use {@link com.drew.metadata.exif.ExifDirectoryBase#TAG_COMPRESSION} instead.
     */
    @Deprecated
    public static final IntegerKey TAG_THUMBNAIL_COMPRESSION = new IntegerKey(0x0103);

    @NotNull
    protected static final HashMap<IntegerKey, String> _tagNameMap = new HashMap<IntegerKey, String>();

    static
    {
        addExifTagNames(_tagNameMap);

        _tagNameMap.put(TAG_THUMBNAIL_OFFSET, "Thumbnail Offset");
        _tagNameMap.put(TAG_THUMBNAIL_LENGTH, "Thumbnail Length");
    }

    public ExifThumbnailDirectory()
    {
        this.setDescriptor(new ExifThumbnailDescriptor(this));
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Exif Thumbnail";
    }

    @Override
    @NotNull
    protected HashMap<IntegerKey, String> getTagNameMap()
    {
        return _tagNameMap;
    }
}
