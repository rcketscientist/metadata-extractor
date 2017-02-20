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

package com.drew.metadata.adobe;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.exif.makernotes.KodakMakernoteDirectory;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains image encoding information for DCT filters, as stored by Adobe.
 */
@SuppressWarnings("WeakerAccess")
public class AdobeJpegDirectory extends Directory<Integer, AdobeJpegDirectory.Keys> {

    public enum Keys
    {
        TAG_DCT_ENCODE_VERSION(0),
        /**
         * The convention for TAG_APP14_FLAGS0 and TAG_APP14_FLAGS1 is that 0 bits are benign.
         * 1 bits in TAG_APP14_FLAGS0 pass information that is possibly useful but not essential for decoding.
         * <p>
         * 0x8000 bit: Encoder used Blend=1 downsampling
         */
        TAG_APP14_FLAGS0(1),
        /**
         * The convention for TAG_APP14_FLAGS0 and TAG_APP14_FLAGS1 is that 0 bits are benign.
         * 1 bits in TAG_APP14_FLAGS1 pass information essential for decoding. DCTDecode could reject a compressed
         * image, if there are 1 bits in TAG_APP14_FLAGS1 or color transform codes that it cannot interpret.
         */
        TAG_APP14_FLAGS1(2),
        TAG_COLOR_TRANSFORM(3);

        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();
        static {
            for (Keys type : values())
                lookup.put(type.getValue(), type);
        }

        private final int key;
        Keys(int key)
        {
            this.key = key;
        }

        public Integer getValue()
        {
            return key;
        }

        public static @Nullable
        Keys fromValue(Integer value)
        {
            return lookup.get(value);
        }
    }

    @NotNull
    protected static final EnumMap<Keys, String> _tagNameMap = new EnumMap<Keys, String>(Keys.class);
    @NotNull
    protected static final EnumMap<Keys, Object> _tagMap = new EnumMap<Keys, Object>(Keys.class);

    static {
        _tagNameMap.put(Keys.TAG_DCT_ENCODE_VERSION, "DCT Encode Version");
        _tagNameMap.put(Keys.TAG_APP14_FLAGS0, "Flags 0");
        _tagNameMap.put(Keys.TAG_APP14_FLAGS1, "Flags 1");
        _tagNameMap.put(Keys.TAG_COLOR_TRANSFORM, "Color Transform");
    }

    public AdobeJpegDirectory() {
        this.setDescriptor(new AdobeJpegDescriptor(this));
    }

    @NotNull
    @Override
    public String getName() {
        return "Adobe JPEG";
    }

    @Override
    protected EnumMap<Keys, String> getTagNameMap()
    {
        return _tagNameMap;
    }

    @Override
    protected EnumMap<Keys, Object> getTagMap()
    {
        return _tagMap;
    }

    @Override
    protected Keys getTagFromValue(Integer value)
    {
        return Keys.fromValue(value);
    }
}
