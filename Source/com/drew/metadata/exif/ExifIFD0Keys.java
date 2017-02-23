package com.drew.metadata.exif;

import com.drew.metadata.DirectoryBase;
import com.drew.metadata.Key;

public enum ExifIFD0Keys implements Key
{
    TAG_EXIF_SUB_IFD_OFFSET(0x8769, "Exif SubIfd pointer"),
    TAG_GPS_INFO_OFFSET(0x8825, "Exif GPS IFD pointer");

/*    //TODO: Use a sparse array trie, or FastUtil
    private static final Map<Integer, ExifIFD0Keys> lookup = new HashMap<Integer, ExifIFD0Keys>();

    static
    {
        for (ExifIFD0Keys type : values())
            lookup.put(type.getValue(), type);
    }*/

    private final int key;
    private final String summary;

    ExifIFD0Keys(int key, String summary)
    {
        this.key = key;
        this.summary = summary;
    }

    public Integer getValue()
    {
        return key;
    }


//    @Nullable
//    public static ExifIFD0Keys fromValue(Integer value)
//    {
//        return lookup.get(value);
//    }

    @Override
    public String getName()
    {
        return name();
    }

    @Override
    public String getType()
    {
        return Integer.toString(key);
    }

    @Override
    public String getSummary()
    {
        return summary;
    }

    /**
     * Default description handler.
     *
     * @param directory storing the values to describe
     * @return human-friendly metadata description
     */
    @Override
    public String getDescription(DirectoryBase directory)
    {
        return directory.getDescription(this);
    }
}
