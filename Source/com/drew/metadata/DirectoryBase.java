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
package com.drew.metadata;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.lang.annotations.SuppressWarnings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract base class for all directory implementations, having methods for getting and setting tag values of various
 * data types.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@java.lang.SuppressWarnings({"WeakerAccess", "unused"}) //TODO: Ignore unused for now
//TODO: T is only for backwards compatibility which is looking less and less possible
public abstract class DirectoryBase</*T, */K extends Enum<K> & Key> implements Directory
{
    private static final String _floatFormatPattern = "0.###";

    /**
     * A convenient list holding tag values in the order in which they were stored.
     * This is used for creation of an iterator, and for counting the number of
     * defined tags.
     */
    @NotNull
    protected final Collection<Key> _definedTagList = new ArrayList<Key>();

    @NotNull
    private final Collection<String> _errorList = new ArrayList<String>(4);

    @Nullable
    private Directory _parent;

// ABSTRACT METHODS

    /**
     * Provides the name of the directory, for display purposes.  E.g. <code>Exif</code>
     *
     * @return the name of the directory
     */
    @NotNull
    public abstract String getName();

//    /**
//     * Provides the full set of tags
//     *
//     * @return the set of tags
//     */
    //TODO: If we want this method it must be a list of Key; inherited directories can't mix their keys in EnumMap
//    @NotNull
//    protected abstract EnumSet<K> getTagSet();

//    /**
//     * Maps tag to populated value
//     * @return mapping of all populated tags
//     */
    //TODO: If we want this method it must be a map of (Key, Object); inherited directories can't mix their keys in EnumMap
//    @NotNull
//    protected abstract EnumMap<K,Object> getTagMap();

    /**
     * see {@link EnumMap#put(Enum, Object)}
     */
    protected abstract Object put(Key tag, Object value);   //TODO: There's typesafe concerns here, also how do we force every sub to implement?

    /**
     * Checks if the given key has been populated.
     * @param tag tag to check
     */
    protected abstract boolean isKeyPopulated(Key tag);

    /**
     * Checks whether a tag is known to this directory.
     * To see if the tag is populated see {@link #isKeyPopulated(Key)}.
     * @param tag
     * @return
     */
    protected abstract boolean hasKey(Key tag);

    protected abstract int size();

    protected abstract Object get(Key tagType);

//    /**
//     * Provides the map of tag names, hashed by tag type identifier.
//     *
//     * @return the map of tag names
//     */
//    @NotNull
//    protected abstract EnumMap<K, Object> _tagMap;

//    /**
//     * Reverse lookup from tag value to it's enum
//     * @param value Base value of the tag
//     * @return The enum representing value's tag if it exists
//     */
//    protected abstract K getTagFromValue(T value);

    protected DirectoryBase() {}

// VARIOUS METHODS

    /**
     * Gets a value indicating whether the directory is empty, meaning it contains no errors and no tag values.
     */
    public boolean isEmpty()
    {
        return _errorList.isEmpty() && _definedTagList.isEmpty();
    }

    /**
     * Indicates whether the specified tag type has been set.
     *
     * @param tagType the tag type to check for
     * @return true if a value exists for the specified tag type, false if not
     */
    public boolean containsTag(K tagType)
    {
        return isKeyPopulated(tagType);
    }

//    /**
//     * Indicates whether the specified tag type has been set.
//     *
//     * @param tagValue the tag type to check for
//     * @return true if a value exists for the specified tag type, false if not
//     */
//    public boolean containsTag(T tagValue)
//    {
//        return containsTag(getTagFromValue(tagValue));
//    }

    /**
     * Returns an Iterator of Tag instances that have been set in this Directory.
     *
     * @return an Iterator of Tag instances
     */
    @NotNull
    public Collection<Key> getTags()
    {
        return Collections.unmodifiableCollection(_definedTagList);
    }

    /**
     * Returns the number of tags set in this Directory.
     *
     * @return the number of tags set in this Directory
     */
    public int getTagCount()
    {
        return _definedTagList.size();
    }

    /**
     * Registers an error message with this directory.
     *
     * @param message an error message.
     */
    public void addError(@NotNull String message)
    {
        _errorList.add(message);
    }

    /**
     * Gets a value indicating whether this directory has any error messages.
     *
     * @return true if the directory contains errors, otherwise false
     */
    public boolean hasErrors()
    {
        return _errorList.size() > 0;
    }

    /**
     * Used to iterate over any error messages contained in this directory.
     *
     * @return an iterable collection of error message strings.
     */
    @NotNull
    public Iterable<String> getErrors()
    {
        return Collections.unmodifiableCollection(_errorList);
    }

    /** Returns the count of error messages in this directory. */
    public int getErrorCount()
    {
        return _errorList.size();
    }

    @Nullable
    public Directory getParent()
    {
        return _parent;
    }

    public void setParent(@NotNull Directory parent)
    {
        _parent = parent;
    }

// TAG SETTERS

    /**
     * Sets an <code>int</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as an int
     */
    public void setInt(K tagType, int value)
    {
        setObject(tagType, value);
    }
//    public void setInt(T tagValue, int value)
//    {
//        setInt(getTagFromValue(tagValue), value);
//    }

    /**
     * Sets an <code>int[]</code> (array) for the specified tag.
     *
     * @param tagType the tag identifier
     * @param ints    the int array to store
     */
    public void setIntArray(K tagType, @NotNull int[] ints)
    {
        setObjectArray(tagType, ints);
    }
//    public void setIntArray(T tagValue, @NotNull int[] ints)
//    {
//        setIntArray(getTagFromValue(tagValue), ints);
//    }

    /**
     * Sets a <code>float</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a float
     */
    public void setFloat(K tagType, float value)
    {
        setObject(tagType, value);
    }
//    public void setFloat(T tagValue, float value)
//    {
//        setFloat(getTagFromValue(tagValue), value);
//    }


    /**
     * Sets a <code>float[]</code> (array) for the specified tag.
     *
     * @param tagType the tag identifier
     * @param floats  the float array to store
     */
    public void setFloatArray(K tagType, @NotNull float[] floats)
    {
        setObjectArray(tagType, floats);
    }
//    public void setFloatArray(T tagValue, @NotNull float[] floats)
//    {
//        setFloatArray(getTagFromValue(tagValue), floats);
//    }

    /**
     * Sets a <code>double</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a double
     */
    public void setDouble(K tagType, double value)
    {
        setObject(tagType, value);
    }
//    public void setDouble(T tagValue, double value)
//    {
//        setDouble(getTagFromValue(tagValue), value);
//    }


    /**
     * Sets a <code>double[]</code> (array) for the specified tag.
     *
     * @param tagType the tag identifier
     * @param doubles the double array to store
     */
    public void setDoubleArray(K tagType, @NotNull double[] doubles)
    {
        setObjectArray(tagType, doubles);
    }

//    public void setDoubleArray(T tagValue, @NotNull double[] doubles)
//    {
//        setDoubleArray(getTagFromValue(tagValue), doubles);
//    }

    /**
     * Sets a <code>StringValue</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a StringValue
     */
    @java.lang.SuppressWarnings({ "ConstantConditions" })
    public void setStringValue(K tagType, @NotNull StringValue value)
    {
        if (value == null)
            throw new NullPointerException("cannot set a null StringValue");
        setObject(tagType, value);
    }

//    public void setStringValue(T tagValue, @NotNull StringValue value)
//    {
//        setStringValue(getTagFromValue(tagValue), value);
//    }

    /**
     * Sets a <code>String</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a String
     */
    @java.lang.SuppressWarnings({ "ConstantConditions" })
    public void setString(K tagType, @NotNull String value)
    {
        if (value == null)
            throw new NullPointerException("cannot set a null String");
        setObject(tagType, value);
    }

//    public void setString(T tagValue, @NotNull String value)
//    {
//        setString(getTagFromValue(tagValue), value);
//    }

    /**
     * Sets a <code>String[]</code> (array) for the specified tag.
     *
     * @param tagType the tag identifier
     * @param strings the String array to store
     */
    public void setStringArray(K tagType, @NotNull String[] strings)
    {
        setObjectArray(tagType, strings);
    }

//    public void setStringArray(T tagValue, @NotNull String[] strings)
//    {
//        setStringArray(getTagFromValue(tagValue), strings);
//    }

    /**
     * Sets a <code>StringValue[]</code> (array) for the specified tag.
     *
     * @param tagType the tag identifier
     * @param strings the StringValue array to store
     */
    public void setStringValueArray(K tagType, @NotNull StringValue[] strings)
    {
        setObjectArray(tagType, strings);
    }

//    public void setStringValueArray(T tagValue, @NotNull StringValue[] strings)
//    {
//        setStringValueArray(getTagFromValue(tagValue), strings);
//    }

    /**
     * Sets a <code>boolean</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a boolean
     */
    public void setBoolean(K tagType, boolean value)
    {
        setObject(tagType, value);
    }
//    public void setBoolean(T tagValue, boolean value)
//    {
//        setBoolean(getTagFromValue(tagValue), value);
//    }

    /**
     * Sets a <code>long</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a long
     */
    public void setLong(K tagType, long value)
    {
        setObject(tagType, value);
    }

//    public void setLong(T tagValue, long value)
//    {
//        setLong(getTagFromValue(tagValue), value);
//    }


    /**
     * Sets a <code>java.util.Date</code> value for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag as a java.util.Date
     */
    public void setDate(K tagType, @NotNull java.util.Date value)
    {
        setObject(tagType, value);
    }

//    public void setDate(T tagValue, @NotNull java.util.Date value)
//    {
//        setDate(getTagFromValue(tagValue), value);
//    }

    /**
     * Sets a <code>Rational</code> value for the specified tag.
     *
     * @param tagType  the tag's value as an int
     * @param rational rational number
     */
    public void setRational(K tagType, @NotNull Rational rational)
    {
        setObject(tagType, rational);
    }

//    public void setRational(T tagValue, @NotNull Rational rational)
//    {
//        setRational(getTagFromValue(tagValue), rational);
//    }

    /**
     * Sets a <code>Rational[]</code> (array) for the specified tag.
     *
     * @param tagType   the tag identifier
     * @param rationals the Rational array to store
     */
    public void setRationalArray(K tagType, @NotNull Rational[] rationals)
    {
        setObjectArray(tagType, rationals);
    }

//    public void setRationalArray(T tagValue, @NotNull Rational[] rationals)
//    {
//        setRationalArray(getTagFromValue(tagValue), rationals);
//    }

    /**
     * Sets a <code>byte[]</code> (array) for the specified tag.
     *
     * @param tagType the tag identifier
     * @param bytes   the byte array to store
     */
    public void setByteArray(K tagType, @NotNull byte[] bytes)
    {
        setObjectArray(tagType, bytes);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #setByteArray(Enum, byte[])}.</em><p>
     */
//    public void setByteArray(T tagValue, @NotNull byte[] bytes)
//    {
//        setByteArray(getTagFromValue(tagValue), bytes);
//    }

    /**
     * Sets a <code>Object</code> for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param value   the value for the specified tag
     * @throws NullPointerException if value is <code>null</code>
     */
    @java.lang.SuppressWarnings( { "ConstantConditions", "UnnecessaryBoxing" })
    public void setObject(K tagType, @NotNull Object value)
    {
        if (value == null)
            throw new NullPointerException("cannot set a null object");

        if (!isKeyPopulated(tagType)) {
            _definedTagList.add(tagType);
        }
        put(tagType, value);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #setObject(Enum, Object)}.</em><p>
     */
//    public void setObject(T tagValue, @NotNull Object value)
//    {
//        setObject(getTagFromValue(tagValue), value);
//    }

    /**
     * Sets an array <code>Object</code> for the specified tag.
     *
     * @param tagType the tag's value as an int
     * @param array   the array of values for the specified tag
     */
    public void setObjectArray(K tagType, @NotNull Object array)
    {
        // for now, we don't do anything special -- this method might be a candidate for removal once the dust settles
        setObject(tagType, array);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #setObjectArray(Enum, Object)}.</em><p>
     */
//    public void setObjectArray(T tagValue, @NotNull Object array)
//    {
//        // for now, we don't do anything special -- this method might be a candidate for removal once the dust settles
//        setObject(tagValue, array);
//    }

// TAG GETTERS

    /**
     * Returns the specified tag's value as an int, if possible.  Every attempt to represent the tag's value as an int
     * is taken.  Here is a list of the action taken depending upon the tag's original type:
     * <ul>
     * <li> int - Return unchanged.
     * <li> Number - Return an int value (real numbers are truncated).
     * <li> Rational - Truncate any fractional part and returns remaining int.
     * <li> String - Attempt to parse string as an int.  If this fails, convert the char[] to an int (using shifts and OR).
     * <li> Rational[] - Return int value of first item in array.
     * <li> byte[] - Return int value of first item in array.
     * <li> int[] - Return int value of first item in array.
     * </ul>
     *
     * @throws MetadataException if no value exists for tagType or if it cannot be converted to an int.
     */
    public int getInt(Key tagType) throws MetadataException
    {
        Integer integer = getInteger(tagType);
        if (integer!=null)
            return integer;

        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to int.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getInt(Key)}.</em><p>
     */
//    public int getInt(T tagValue) throws MetadataException
//    {
//        return getInt(getTagFromValue(tagValue));
//    }

    /**
     * Returns the specified tag's value as an Integer, if possible.  Every attempt to represent the tag's value as an
     * Integer is taken.  Here is a list of the action taken depending upon the tag's original type:
     * <ul>
     * <li> int - Return unchanged
     * <li> Number - Return an int value (real numbers are truncated)
     * <li> Rational - Truncate any fractional part and returns remaining int
     * <li> String - Attempt to parse string as an int.  If this fails, convert the char[] to an int (using shifts and OR)
     * <li> Rational[] - Return int value of first item in array if length &gt; 0
     * <li> byte[] - Return int value of first item in array if length &gt; 0
     * <li> int[] - Return int value of first item in array if length &gt; 0
     * </ul>
     *
     * If the value is not found or cannot be converted to int, <code>null</code> is returned.
     */
    @Nullable
    public Integer getInteger(Key tagType)
    {
        Object o = getObject(tagType);

        if (o == null)
            return null;

        if (o instanceof Number) {
            return ((Number)o).intValue();
        } else if (o instanceof String || o instanceof StringValue) {
            try {
                return Integer.parseInt(o.toString());
            } catch (NumberFormatException nfe) {
                // convert the char array to an int
                String s = o.toString();
                byte[] bytes = s.getBytes();
                long val = 0;
                for (byte aByte : bytes) {
                    val = val << 8;
                    val += (aByte & 0xff);
                }
                return (int)val;
            }
        } else if (o instanceof Rational[]) {
            Rational[] rationals = (Rational[])o;
            if (rationals.length == 1)
                return rationals[0].intValue();
        } else if (o instanceof byte[]) {
            byte[] bytes = (byte[])o;
            if (bytes.length == 1)
                return (int)bytes[0];
        } else if (o instanceof int[]) {
            int[] ints = (int[])o;
            if (ints.length == 1)
                return ints[0];
        } else if (o instanceof short[]) {
            short[] shorts = (short[])o;
            if (shorts.length == 1)
                return (int)shorts[0];
        }
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getInteger(Key)}.</em><p>
     */
//    @Nullable
//    public Integer getInteger(T tagValue)
//    {
//        return getInteger(getTagFromValue(tagValue));
//    }

    /**
     * Gets the specified tag's value as a String array, if possible.  Only supported
     * where the tag is set as StringValue[], String[], StringValue, String, int[], byte[] or Rational[].
     *
     * @param tagType the tag identifier
     * @return the tag's value as an array of Strings. If the value is unset or cannot be converted, <code>null</code> is returned.
     */
    @Nullable
    public String[] getStringArray(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof String[])
            return (String[])o;
        if (o instanceof String)
            return new String[] { (String)o };
        if (o instanceof StringValue)
            return new String[] { o.toString() };
        if (o instanceof StringValue[]) {
            StringValue[] stringValues = (StringValue[])o;
            String[] strings = new String[stringValues.length];
            for (int i = 0; i < strings.length; i++)
                strings[i] = stringValues[i].toString();
            return strings;
        }
        if (o instanceof int[]) {
            int[] ints = (int[])o;
            String[] strings = new String[ints.length];
            for (int i = 0; i < strings.length; i++)
                strings[i] = Integer.toString(ints[i]);
            return strings;
        }
        if (o instanceof byte[]) {
            byte[] bytes = (byte[])o;
            String[] strings = new String[bytes.length];
            for (int i = 0; i < strings.length; i++)
                strings[i] = Byte.toString(bytes[i]);
            return strings;
        }
        if (o instanceof Rational[]) {
            Rational[] rationals = (Rational[])o;
            String[] strings = new String[rationals.length];
            for (int i = 0; i < strings.length; i++)
                strings[i] = rationals[i].toSimpleString(false);
            return strings;
        }
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getStringArray(Key)}.</em><p>
     */
//    @Nullable
//    public String[] getStringArray(T tagValue)
//    {
//        return getStringArray(getTagFromValue(tagValue));
//    }

    /**
     * Gets the specified tag's value as a StringValue array, if possible.
     * Only succeeds if the tag is set as StringValue[], or StringValue.
     *
     * @param tagType the tag identifier
     * @return the tag's value as an array of StringValues. If the value is unset or cannot be converted, <code>null</code> is returned.
     */
    @Nullable
    public StringValue[] getStringValueArray(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof StringValue[])
            return (StringValue[])o;
        if (o instanceof StringValue)
            return new StringValue[] {(StringValue) o};
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getStringValueArray(Key)}.</em><p>
     */
//    @Nullable
//    public StringValue[] getStringValueArray(T tagValue)
//    {
//        return getStringValueArray(getTagFromValue(tagValue));
//    }

    /**
     * Gets the specified tag's value as an int array, if possible.  Only supported
     * where the tag is set as String, Integer, int[], byte[] or Rational[].
     *
     * @param tagType the tag identifier
     * @return the tag's value as an int array
     */
    @Nullable
    public int[] getIntArray(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof int[])
            return (int[])o;
        if (o instanceof Rational[]) {
            Rational[] rationals = (Rational[])o;
            int[] ints = new int[rationals.length];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = rationals[i].intValue();
            }
            return ints;
        }
        if (o instanceof short[]) {
            short[] shorts = (short[])o;
            int[] ints = new int[shorts.length];
            for (int i = 0; i < shorts.length; i++) {
                ints[i] = shorts[i];
            }
            return ints;
        }
        if (o instanceof byte[]) {
            byte[] bytes = (byte[])o;
            int[] ints = new int[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                ints[i] = bytes[i];
            }
            return ints;
        }
        if (o instanceof CharSequence) {
            CharSequence str = (CharSequence)o;
            int[] ints = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                ints[i] = str.charAt(i);
            }
            return ints;
        }
        if (o instanceof Integer)
            return new int[] { (Integer)o };

        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getIntArray(Key)}.</em><p>
     */
//    @Nullable
//    public int[] getIntArray(T tagValue)
//    {
//        return getIntArray(getTagFromValue(tagValue));
//    }

    /**
     * Gets the specified tag's value as an byte array, if possible.  Only supported
     * where the tag is set as String, Integer, int[], byte[] or Rational[].
     *
     * @param tagType the tag identifier
     * @return the tag's value as a byte array
     */
    @Nullable
    public byte[] getByteArray(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null) {
            return null;
        } else if (o instanceof StringValue) {
            return ((StringValue)o).getBytes();
        } else if (o instanceof Rational[]) {
            Rational[] rationals = (Rational[])o;
            byte[] bytes = new byte[rationals.length];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = rationals[i].byteValue();
            }
            return bytes;
        } else if (o instanceof byte[]) {
            return (byte[])o;
        } else if (o instanceof int[]) {
            int[] ints = (int[])o;
            byte[] bytes = new byte[ints.length];
            for (int i = 0; i < ints.length; i++) {
                bytes[i] = (byte)ints[i];
            }
            return bytes;
        } else if (o instanceof short[]) {
            short[] shorts = (short[])o;
            byte[] bytes = new byte[shorts.length];
            for (int i = 0; i < shorts.length; i++) {
                bytes[i] = (byte)shorts[i];
            }
            return bytes;
        } else if (o instanceof CharSequence) {
            CharSequence str = (CharSequence)o;
            byte[] bytes = new byte[str.length()];
            for (int i = 0; i < str.length(); i++) {
                bytes[i] = (byte)str.charAt(i);
            }
            return bytes;
        }
        if (o instanceof Integer)
            return new byte[] { ((Integer)o).byteValue() };

        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getByteArray(Key)}.</em><p>
     */
//    @Nullable
//    public byte[] getByteArray(T tagValue)
//    {
//        return getByteArray(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a double, if possible. */
    public double getDouble(Key tagType) throws MetadataException
    {
        Double value = getDoubleObject(tagType);
        if (value!=null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a double.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDouble(Key)}.</em><p>
     */
//    public double getDouble(T tagValue) throws MetadataException
//    {
//        return getDouble(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a Double.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Double getDoubleObject(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof String || o instanceof StringValue) {
            try {
                return Double.parseDouble(o.toString());
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        if (o instanceof Number)
            return ((Number)o).doubleValue();

        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDoubleObject(Key)}.</em><p>
     */
//    @Nullable
//    public Double getDoubleObject(T tagValue)
//    {
//        return getDoubleObject(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a float, if possible. */
    public float getFloat(Key tagType) throws MetadataException
    {
        Float value = getFloatObject(tagType);
        if (value!=null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a float.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getFloat(Key)}.</em><p>
     */
//    public float getFloat(T tagValue) throws MetadataException
//    {
//        return getFloat(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a float.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Float getFloatObject(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof String || o instanceof StringValue) {
            try {
                return Float.parseFloat(o.toString());
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        if (o instanceof Number)
            return ((Number)o).floatValue();
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getFloatObject(Key)}.</em><p>
     */
//    @Nullable
//    public Float getFloatObject(T tagValue)
//    {
//        return getFloatObject(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a long, if possible. */
    public long getLong(Key tagType) throws MetadataException
    {
        Long value = getLongObject(tagType);
        if (value != null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a long.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getLong(Key)}.</em><p>
     */
//    public long getLong(T tagValue) throws MetadataException
//    {
//        return getLong(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a long.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Long getLongObject(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof String || o instanceof StringValue) {
            try {
                return Long.parseLong(o.toString());
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        if (o instanceof Number)
            return ((Number)o).longValue();
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getLongObject(Key)}.</em><p>
     */
//    @Nullable
//    public Long getLongObject(T tagValue)
//    {
//        return getLongObject(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a boolean, if possible. */
    public boolean getBoolean(Key tagType) throws MetadataException
    {
        Boolean value = getBooleanObject(tagType);
        if (value != null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a boolean.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getBoolean(Key)}.</em><p>
     */
//    public boolean getBoolean(T tagValue) throws MetadataException
//    {
//        return getBoolean(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a boolean.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    @SuppressWarnings(value = "NP_BOOLEAN_RETURN_NULL", justification = "keep API interface consistent")
    public Boolean getBooleanObject(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;
        if (o instanceof Boolean)
            return (Boolean)o;
        if (o instanceof String || o instanceof StringValue) {
            try {
                return Boolean.getBoolean(o.toString());
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        if (o instanceof Number)
            return (((Number)o).doubleValue() != 0);
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getBooleanObject(Key)}.</em><p>
     */
//    @Nullable
//    @SuppressWarnings(value = "NP_BOOLEAN_RETURN_NULL", justification = "keep API interface consistent")
//    public Boolean getBooleanObject(T tagValue)
//    {
//        return getBooleanObject(getTagFromValue(tagValue));
//    }

    /**
     * Returns the specified tag's value as a java.util.Date.  If the value is unset or cannot be converted, <code>null</code> is returned.
     * <p>
     * If the underlying value is a {@link String}, then attempts will be made to parse the string as though it is in
     * the GMT {@link TimeZone}.  If the {@link TimeZone} is known, call the overload that accepts one as an argument.
     */
    @Nullable
    public java.util.Date getDate(Key tagType)
    {
        return getDate(tagType, null, null);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDate(Key)}.</em><p>
     */
//    @Nullable
//    public java.util.Date getDate(T tagValue)
//    {
//        return getDate(getTagFromValue(tagValue));
//    }

    /**
     * Returns the specified tag's value as a java.util.Date.  If the value is unset or cannot be converted, <code>null</code> is returned.
     * <p>
     * If the underlying value is a {@link String}, then attempts will be made to parse the string as though it is in
     * the {@link TimeZone} represented by the {@code timeZone} parameter (if it is non-null).  Note that this parameter
     * is only considered if the underlying value is a string and it has no time zone information, otherwise it has no effect.
     */
    @Nullable
    public java.util.Date getDate(Key tagType, @Nullable TimeZone timeZone)
    {
        return getDate(tagType, null, timeZone);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDate(Key,TimeZone)}.</em><p>
     */
//    @Nullable
//    public java.util.Date getDate(T tagValue, @Nullable TimeZone timeZone)
//    {
//        return getDate(getTagFromValue(tagValue), timeZone);
//    }

    /**
     * Returns the specified tag's value as a java.util.Date.  If the value is unset or cannot be converted, <code>null</code> is returned.
     * <p>
     * If the underlying value is a {@link String}, then attempts will be made to parse the string as though it is in
     * the {@link TimeZone} represented by the {@code timeZone} parameter (if it is non-null).  Note that this parameter
     * is only considered if the underlying value is a string and it has no time zone information, otherwise it has no effect.
     * In addition, the {@code subsecond} parameter, which specifies the number of digits after the decimal point in the seconds,
     * is set to the returned Date. This parameter is only considered if the underlying value is a string and is has
     * no subsecond information, otherwise it has no effect.
     *
     * @param tagType the tag identifier
     * @param subsecond the subsecond value for the Date
     * @param timeZone the time zone to use
     * @return a Date representing the time value
     */
    @Nullable
    public java.util.Date getDate(Key tagType, @Nullable String subsecond, @Nullable TimeZone timeZone)
    {
        Object o = getObject(tagType);

        if (o instanceof java.util.Date)
            return (java.util.Date)o;

        java.util.Date date = null;

        if ((o instanceof String) || (o instanceof StringValue)) {
            // This seems to cover all known Exif and Xmp date strings
            // Note that "    :  :     :  :  " is a valid date string according to the Exif spec (which means 'unknown date'): http://www.awaresystems.be/imaging/tiff/tifftags/privateifd/exif/datetimeoriginal.html
            String datePatterns[] = {
                    "yyyy:MM:dd HH:mm:ss",
                    "yyyy:MM:dd HH:mm",
                    "yyyy-MM-dd HH:mm:ss",
                    "yyyy-MM-dd HH:mm",
                    "yyyy.MM.dd HH:mm:ss",
                    "yyyy.MM.dd HH:mm",
                    "yyyy-MM-dd'T'HH:mm:ss",
                    "yyyy-MM-dd'T'HH:mm",
                    "yyyy-MM-dd",
                    "yyyy-MM",
                    "yyyyMMdd", // as used in IPTC data
                    "yyyy" };

            String dateString = o.toString();

            // if the date string has subsecond information, it supersedes the subsecond parameter
            Pattern subsecondPattern = Pattern.compile("(\\d\\d:\\d\\d:\\d\\d)(\\.\\d+)");
            Matcher subsecondMatcher = subsecondPattern.matcher(dateString);
            if (subsecondMatcher.find()) {
                subsecond = subsecondMatcher.group(2).substring(1);
                dateString = subsecondMatcher.replaceAll("$1");
            }

            // if the date string has time zone information, it supersedes the timeZone parameter
            Pattern timeZonePattern = Pattern.compile("(Z|[+-]\\d\\d:\\d\\d)$");
            Matcher timeZoneMatcher = timeZonePattern.matcher(dateString);
            if (timeZoneMatcher.find()) {
                timeZone = TimeZone.getTimeZone("GMT" + timeZoneMatcher.group().replaceAll("Z", ""));
                dateString = timeZoneMatcher.replaceAll("");
            }

            for (String datePattern : datePatterns) {
                try {
                    DateFormat parser = new SimpleDateFormat(datePattern);
                    if (timeZone != null)
                        parser.setTimeZone(timeZone);
                    else
                        parser.setTimeZone(TimeZone.getTimeZone("GMT")); // don't interpret zone time

                    date = parser.parse(dateString);
                    break;
                } catch (ParseException ex) {
                    // simply try the next pattern
                }
            }
        }

        if (date == null)
            return null;

        if (subsecond == null)
            return date;

        try {
            int millisecond = (int) (Double.parseDouble("." + subsecond) * 1000);
            if (millisecond >= 0 && millisecond < 1000) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.MILLISECOND, millisecond);
                return calendar.getTime();
            }
            return date;
        } catch (NumberFormatException e) {
            return date;
        }
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDate(Key,String,TimeZone)}.</em><p>
     */
//    @Nullable
//    public java.util.Date getDate(T tagValue, @Nullable String subsecond, @Nullable TimeZone timeZone)
//    {
//        return getDate(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as a Rational.  If the value is unset or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Rational getRational(Key tagType)
    {
        Object o = getObject(tagType);

        if (o == null)
            return null;

        if (o instanceof Rational)
            return (Rational)o;
        if (o instanceof Integer)
            return new Rational((Integer)o, 1);
        if (o instanceof Long)
            return new Rational((Long)o, 1);

        // NOTE not doing conversions for real number types

        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getRational(Key)}.</em><p>
     */
//    @Nullable
//    public Rational getRational(T tagValue)
//    {
//        return getRational(getTagFromValue(tagValue));
//    }

    /** Returns the specified tag's value as an array of Rational.  If the value is unset or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Rational[] getRationalArray(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;

        if (o instanceof Rational[])
            return (Rational[])o;

        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getRationalArray(Key)}.</em><p>
     */
//    @Nullable
//    public Rational[] getRationalArray(T tagValue)
//    {
//        return getRationalArray(getTagFromValue(tagValue));
//    }

    /**
     * Returns the specified tag's value as a String.  This value is the 'raw' value.  A more presentable decoding
     * of this value may be obtained from the corresponding Descriptor.
     *
     * @return the String representation of the tag's value, or
     *         <code>null</code> if the tag hasn't been defined.
     */
    @Nullable
    public String getString(Key tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;

        if (o instanceof Rational)
            return ((Rational)o).toSimpleString(true);

        if (o.getClass().isArray()) {
            // handle arrays of objects and primitives
            int arrayLength = Array.getLength(o);
            final Class<?> componentType = o.getClass().getComponentType();

            StringBuilder string = new StringBuilder();

            if (Object.class.isAssignableFrom(componentType)) {
                // object array
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(Array.get(o, i).toString());
                }
            } else if (componentType.getName().equals("int")) {
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(Array.getInt(o, i));
                }
            } else if (componentType.getName().equals("short")) {
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(Array.getShort(o, i));
                }
            } else if (componentType.getName().equals("long")) {
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(Array.getLong(o, i));
                }
            } else if (componentType.getName().equals("float")) {
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(new DecimalFormat(_floatFormatPattern).format(Array.getFloat(o, i)));
                }
            } else if (componentType.getName().equals("double")) {
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(new DecimalFormat(_floatFormatPattern).format(Array.getDouble(o, i)));
                }
            } else if (componentType.getName().equals("byte")) {
                for (int i = 0; i < arrayLength; i++) {
                    if (i != 0)
                        string.append(' ');
                    string.append(Array.getByte(o, i) & 0xff);
                }
            } else {
                addError("Unexpected array component type: " + componentType.getName());
            }

            return string.toString();
        }

        if (o instanceof Double)
            return new DecimalFormat(_floatFormatPattern).format(((Double)o).doubleValue());

        if (o instanceof Float)
            return new DecimalFormat(_floatFormatPattern).format(((Float)o).floatValue());

        // Note that several cameras leave trailing spaces (Olympus, Nikon) but this library is intended to show
        // the actual data within the file.  It is not inconceivable that whitespace may be significant here, so we
        // do not trim.  Also, if support is added for writing data back to files, this may cause issues.
        // We leave trimming to the presentation layer.
        return o.toString();
    }

    /**
     * Returns the specified tag's value as a String.  This value is the 'raw' value.  A more presentable decoding
     * of this value may be obtained from the corresponding Descriptor.
     *
     * @return the String representation of the tag's value, or
     *         <code>null</code> if the tag hasn't been defined.
     */
    @Nullable
    public String getString(Key tagType, String charset)
    {
        byte[] bytes = getByteArray(tagType);
        if (bytes==null)
            return null;
        try {
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Nullable
    public StringValue getStringValue(Key tagType)
    {
        Object o = getObject(tagType);
        if (o instanceof StringValue)
            return (StringValue)o;
        return null;
    }

//    /**
//     * <em>For backwards compatibility.  If possible use {@link #getString(Key)}.</em><p>
//     */
//    @Nullable
//    public String getString(T tagValue)
//    {
//        return getString(getTagFromValue(tagValue));
//    }
//
//    /**
//     * <em>For backwards compatibility.  If possible use {@link #getString(Key, String)}.</em><p>
//     */
//    @Nullable
//    public String getString(T tagValue, String charset)
//    {
//        return getString(getTagFromValue(tagValue), charset);
//    }
//
//    /**
//     * <em>For backwards compatibility.  If possible use {@link #getStringValue(Key)}.</em><p>
//     */
//    @Nullable
//    public StringValue getStringValue(T tagValue)
//    {
//        return getStringValue(getTagFromValue(tagValue));
//    }

    /**
     * Returns the object hashed for the particular tag type specified, if available.
     *
     * @param tagType the tag type identifier
     * @return the tag's value as an Object if available, else <code>null</code>
     */
    @java.lang.SuppressWarnings({ "UnnecessaryBoxing" })
    @Nullable
    public Object getObject(Key tagType)
    {
        return get(tagType);
    }

// OTHER METHODS

    /**
     * Deprecated.
     * See {@link Key#getName()}
     *
     * @param tagType the tag type identifier
     * @return the tag's name as a String
     */
    @Deprecated
    @NotNull
    public String getTagName(Key tagType)
    {
        return tagType.getName();
    }

//    /**
//     * <em>For backwards compatibility.  If possible use {@link #getTagName(Key)}.</em><p>
//     */
//    @Deprecated
//    @NotNull
//    public String getTagName(T tagValue)
//    {
//        return getTagName(getTagFromValue(tagValue));
//    }

    /**
     * Gets whether the specified tag is known by the directory and has a name.
     *
     * @param tagType the tag type identifier
     * @return whether this directory has a name for the specified tag
     */
    public boolean hasTagName(Key tagType)
    {
        return hasKey(tagType);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #hasTagName(Key)}.</em><p>
     */
//    public boolean hasTagName(T value)
//    {
//        return hasTagName(getTagFromValue(value));
//    }

    /**
     * Returns a descriptive value of the specified tag for this image.
     * Where possible, known values will be substituted here in place of the raw
     * tokens actually kept in the metadata segment.  If no substitution is
     * available, the value provided by <code>getString(tagType)</code> will be returned.
     *
     * @param tagType the tag to find a description for
     * @return a description of the image's value for the specified tag, or
     *         <code>null</code> if the tag hasn't been defined.
     */
    @Nullable
    public String getDescription(Key tagType)
    {
        Object object = getObject(tagType);

        if (object == null)
            return null;

        // special presentation for long arrays
        if (object.getClass().isArray()) {
            final int length = Array.getLength(object);
            if (length > 16) {
                return String.format("[%d values]", length);
            }
        }

        if (object instanceof Date)
        {
            // Produce a date string having a format that includes the offset in form "+00:00"
            return new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy")
                .format((Date) object)
                .replaceAll("([0-9]{2} [^ ]+)$", ":$1");
        }

        // no special handling required, so use default conversion to a string
        return getString(tagType);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDescription(Key)}.</em><p>
     */
//    @Nullable
//    public String getDescription(T tagValue)
//    {
//        return getDescription(getTagFromValue(tagValue));
//    }

    @Override
    public String toString()
    {
        return String.format("%s Directory (%d %s)",
            getName(),
            size(),
            size() == 1
                ? "tag"
                : "tags");
    }

    // Descriptor methods TODO: Many of these can be relocated
    @Nullable
    public String getIndexedDescription(Key tagType, final int baseIndex, @NotNull String... descriptions)
    {
        final Integer index = getInteger(tagType);
        if (index == null)
            return null;
        final int arrayIndex = index - baseIndex;
        if (arrayIndex >= 0 && arrayIndex < descriptions.length) {
            String description = descriptions[arrayIndex];
            if (description != null)
                return description;
        }
        return "Unknown (" + index + ")";
    }

    @Nullable
    public String getOrientationDescription(Key tagType)
    {
        return getIndexedDescription(tagType, 1,
            "Top, left side (Horizontal / normal)",
            "Top, right side (Mirror horizontal)",
            "Bottom, right side (Rotate 180)",
            "Bottom, left side (Mirror vertical)",
            "Left side, top (Mirror horizontal and rotate 270 CW)",
            "Right side, top (Rotate 90 CW)",
            "Right side, bottom (Mirror horizontal and rotate 90 CW)",
            "Left side, bottom (Rotate 270 CW)");
    }

    @Nullable
    public String getVersionBytesDescription(final Key tagType, int majorDigits)
    {
        int[] values = getIntArray(tagType);
        return values == null ? null : DescriptionUtil.convertBytesToVersionString(values, majorDigits);
    }

    @Nullable
    public String getShutterSpeedDescription(Key tag)
    {
        // I believe this method to now be stable, but am leaving some alternative snippets of
        // code in here, to assist anyone who's looking into this (given that I don't have a public CVS).

//        float apexValue = _directory.getFloat(ExifSubIFDDirectory.TAG_SHUTTER_SPEED);
//        int apexPower = (int)Math.pow(2.0, apexValue);
//        return "1/" + apexPower + " sec";
        // TODO test this method
        // thanks to Mark Edwards for spotting and patching a bug in the calculation of this
        // description (spotted bug using a Canon EOS 300D)
        // thanks also to Gli Blr for spotting this bug
        Float apexValue = getFloatObject(tag);
        if (apexValue == null)
            return null;
        if (apexValue <= 1) {
            float apexPower = (float)(1 / (Math.exp(apexValue * Math.log(2))));
            long apexPower10 = Math.round((double)apexPower * 10.0);
            float fApexPower = (float)apexPower10 / 10.0f;
            DecimalFormat format = new DecimalFormat("0.##");
            format.setRoundingMode(RoundingMode.HALF_UP);
            return format.format(fApexPower) + " sec";
        } else {
            int apexPower = (int)((Math.exp(apexValue * Math.log(2))));
            return "1/" + apexPower + " sec";
        }

/*
        // This alternative implementation offered by Bill Richards
        // TODO determine which is the correct / more-correct implementation
        double apexValue = _directory.getDouble(ExifSubIFDDirectory.TAG_SHUTTER_SPEED);
        double apexPower = Math.pow(2.0, apexValue);

        StringBuffer sb = new StringBuffer();
        if (apexPower > 1)
            apexPower = Math.floor(apexPower);

        if (apexPower < 1) {
            sb.append((int)Math.round(1/apexPower));
        } else {
            sb.append("1/");
            sb.append((int)apexPower);
        }
        sb.append(" sec");
        return sb.toString();
*/
    }

    /** The Windows specific tags uses plain Unicode. */
    @Nullable
    public String getUnicodeDescription(Key tagType)
    {
        byte[] bytes = getByteArray(tagType);
        if (bytes == null)
            return null;
        try {
            // Decode the unicode string and trim the unicode zero "\0" from the end.
            return new String(bytes, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    /// <summary>
    /// Decode raw CFAPattern value
    /// </summary>
    /// <remarks>
    /// Converted from Exiftool version 10.33 created by Phil Harvey
    /// http://www.sno.phy.queensu.ca/~phil/exiftool/
    /// lib\Image\ExifTool\Exif.pm
    ///
    /// The value consists of:
    /// - Two short, being the grid width and height of the repeated pattern.
    /// - Next, for every pixel in that pattern, an identification code.
    /// </remarks>
    @Nullable
    public int[] decodeCfaPattern(Key tagType)
    {
        int[] ret;

        byte[] values = getByteArray(tagType);
        if (values == null)
            return null;

        if (values.length < 4)
        {
            ret = new int[values.length];
            for (int i = 0; i < values.length; i++)
                ret[i] = values[i];
            return ret;
        }

        ret = new int[values.length - 2];

        try {
            ByteArrayReader reader = new ByteArrayReader(values);

            // first two values should be read as 16-bits (2 bytes)
            short item0 = reader.getInt16(0);
            short item1 = reader.getInt16(2);

            Boolean copyArray = false;
            int end = 2 + item0 * item1;
            if (end > values.length) // sanity check in case of byte order problems; calculated 'end' should be <= length of the values
            {
                // try swapping byte order (I have seen this order different than in EXIF)
                reader.setMotorolaByteOrder(!reader.isMotorolaByteOrder());
                item0 = reader.getInt16(0);
                item1 = reader.getInt16(2);

                if (values.length >= (2 + item0 * item1))
                    copyArray = true;
            }
            else
                copyArray = true;

            if(copyArray)
            {
                ret[0] = item0;
                ret[1] = item1;

                for (int i = 4; i < values.length; i++)
                    ret[i - 2] = reader.getInt8(i);
            }
        } catch (IOException ex) {
            addError("IO exception processing data: " + ex.getMessage());
        }

        return ret;
    }

    @Nullable
    public String getLensSpecificationDescription(Key tag)
    {
        Rational[] values = getRationalArray(tag);

        if (values == null || values.length != 4 || (values[0].isZero() && values[2].isZero()))
            return null;

        StringBuilder sb = new StringBuilder();

        if (values[0].equals(values[1]))
            sb.append(values[0].toSimpleString(true)).append("mm");
        else
            sb.append(values[0].toSimpleString(true)).append('-').append(values[1].toSimpleString(true)).append("mm");

        if (!values[2].isZero()) {
            sb.append(' ');

            DecimalFormat format = new DecimalFormat("0.0");
            format.setRoundingMode(RoundingMode.HALF_UP);

            if (values[2].equals(values[3]))
                sb.append(DescriptionUtil.getFStopDescription(values[2].doubleValue()));
            else
                sb.append("f/").append(format.format(values[2].doubleValue())).append('-').append(format.format(values[3].doubleValue()));
        }

        return sb.toString();
    }
}
