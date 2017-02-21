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

import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.lang.annotations.SuppressWarnings;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
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
public abstract class DirectoryBase<T, K extends Enum<K> & Key> implements Directory
{
    private static final String _floatFormatPattern = "0.###";

    /**
     * A convenient list holding tag values in the order in which they were stored.
     * This is used for creation of an iterator, and for counting the number of
     * defined tags.
     */
    @NotNull
    protected final Collection<Key> _definedTagList = new ArrayList<Key>();

    /**
     * Map of keys and values that have been set.
     */
    @NotNull
    protected final EnumMap<K, Object> _tagMap;

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

    /**
     * Provides the full set of tags
     *
     * @return the set of tags
     */
    @NotNull
    protected abstract EnumSet<K> getTagSet();

//    /**
//     * Provides the map of tag names, hashed by tag type identifier.
//     *
//     * @return the map of tag names
//     */
//    @NotNull
//    protected abstract EnumMap<K, Object> _tagMap;

    /**
     * Reverse lookup from tag value to it's enum
     * @param value Base value of the tag
     * @return The enum representing value's tag if it exists
     */
    protected abstract K getTagFromValue(T value);

    protected DirectoryBase(Class<K> keyClass)
    {
        _tagMap = new EnumMap<K, Object>(keyClass);
    }

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
        return _tagMap.containsKey(tagType);
    }

    /**
     * Indicates whether the specified tag type has been set.
     *
     * @param tagValue the tag type to check for
     * @return true if a value exists for the specified tag type, false if not
     */
    public boolean containsTag(T tagValue)
    {
        return containsTag(getTagFromValue(tagValue));
    }

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
    public void setInt(T tagValue, int value)
    {
        setInt(getTagFromValue(tagValue), value);
    }

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
    public void setIntArray(T tagValue, @NotNull int[] ints)
    {
        setIntArray(getTagFromValue(tagValue), ints);
    }

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
    public void setFloat(T tagValue, float value)
    {
        setFloat(getTagFromValue(tagValue), value);
    }


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
    public void setFloatArray(T tagValue, @NotNull float[] floats)
    {
        setFloatArray(getTagFromValue(tagValue), floats);
    }

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
    public void setDouble(T tagValue, double value)
    {
        setDouble(getTagFromValue(tagValue), value);
    }


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

    public void setDoubleArray(T tagValue, @NotNull double[] doubles)
    {
        setDoubleArray(getTagFromValue(tagValue), doubles);
    }

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

    public void setStringValue(T tagValue, @NotNull StringValue value)
    {
        setStringValue(getTagFromValue(tagValue), value);
    }

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

    public void setString(T tagValue, @NotNull String value)
    {
        setString(getTagFromValue(tagValue), value);
    }

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

    public void setStringArray(T tagValue, @NotNull String[] strings)
    {
        setStringArray(getTagFromValue(tagValue), strings);
    }

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

    public void setStringValueArray(T tagValue, @NotNull StringValue[] strings)
    {
        setStringValueArray(getTagFromValue(tagValue), strings);
    }

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
    public void setBoolean(T tagValue, boolean value)
    {
        setBoolean(getTagFromValue(tagValue), value);
    }

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

    public void setLong(T tagValue, long value)
    {
        setLong(getTagFromValue(tagValue), value);
    }


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

    public void setDate(T tagValue, @NotNull java.util.Date value)
    {
        setDate(getTagFromValue(tagValue), value);
    }

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

    public void setRational(T tagValue, @NotNull Rational rational)
    {
        setRational(getTagFromValue(tagValue), rational);
    }

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

    public void setRationalArray(T tagValue, @NotNull Rational[] rationals)
    {
        setRationalArray(getTagFromValue(tagValue), rationals);
    }

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
    public void setByteArray(T tagValue, @NotNull byte[] bytes)
    {
        setByteArray(getTagFromValue(tagValue), bytes);
    }

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

        if (!_tagMap.containsKey(tagType)) {
            _definedTagList.add(tagType);
        }
        _tagMap.put(tagType, value);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #setObject(Enum, Object)}.</em><p>
     */
    public void setObject(T tagValue, @NotNull Object value)
    {
        setObject(getTagFromValue(tagValue), value);
    }

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
    public void setObjectArray(T tagValue, @NotNull Object array)
    {
        // for now, we don't do anything special -- this method might be a candidate for removal once the dust settles
        setObject(tagValue, array);
    }

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
    public int getInt(K tagType) throws MetadataException
    {
        Integer integer = getInteger(tagType);
        if (integer!=null)
            return integer;

        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getTagName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to int.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getInt(Enum)}.</em><p>
     */
    public int getInt(T tagValue) throws MetadataException
    {
        return getInt(getTagFromValue(tagValue));
    }

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
    public Integer getInteger(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getInteger(Enum)}.</em><p>
     */
    @Nullable
    public Integer getInteger(T tagValue)
    {
        return getInteger(getTagFromValue(tagValue));
    }

    /**
     * Gets the specified tag's value as a String array, if possible.  Only supported
     * where the tag is set as StringValue[], String[], StringValue, String, int[], byte[] or Rational[].
     *
     * @param tagType the tag identifier
     * @return the tag's value as an array of Strings. If the value is unset or cannot be converted, <code>null</code> is returned.
     */
    @Nullable
    public String[] getStringArray(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getStringArray(Enum)}.</em><p>
     */
    @Nullable
    public String[] getStringArray(T tagValue)
    {
        return getStringArray(getTagFromValue(tagValue));
    }

    /**
     * Gets the specified tag's value as a StringValue array, if possible.
     * Only succeeds if the tag is set as StringValue[], or StringValue.
     *
     * @param tagType the tag identifier
     * @return the tag's value as an array of StringValues. If the value is unset or cannot be converted, <code>null</code> is returned.
     */
    @Nullable
    public StringValue[] getStringValueArray(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getStringValueArray(Enum)}.</em><p>
     */
    @Nullable
    public StringValue[] getStringValueArray(T tagValue)
    {
        return getStringValueArray(getTagFromValue(tagValue));
    }

    /**
     * Gets the specified tag's value as an int array, if possible.  Only supported
     * where the tag is set as String, Integer, int[], byte[] or Rational[].
     *
     * @param tagType the tag identifier
     * @return the tag's value as an int array
     */
    @Nullable
    public int[] getIntArray(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getIntArray(Enum)}.</em><p>
     */
    @Nullable
    public int[] getIntArray(T tagValue)
    {
        return getIntArray(getTagFromValue(tagValue));
    }

    /**
     * Gets the specified tag's value as an byte array, if possible.  Only supported
     * where the tag is set as String, Integer, int[], byte[] or Rational[].
     *
     * @param tagType the tag identifier
     * @return the tag's value as a byte array
     */
    @Nullable
    public byte[] getByteArray(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getByteArray(Enum)}.</em><p>
     */
    @Nullable
    public byte[] getByteArray(T tagValue)
    {
        return getByteArray(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a double, if possible. */
    public double getDouble(K tagType) throws MetadataException
    {
        Double value = getDoubleObject(tagType);
        if (value!=null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getTagName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a double.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDouble(Enum)}.</em><p>
     */
    public double getDouble(T tagValue) throws MetadataException
    {
        return getDouble(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a Double.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Double getDoubleObject(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getDoubleObject(Enum)}.</em><p>
     */
    @Nullable
    public Double getDoubleObject(T tagValue)
    {
        return getDoubleObject(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a float, if possible. */
    public float getFloat(K tagType) throws MetadataException
    {
        Float value = getFloatObject(tagType);
        if (value!=null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getTagName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a float.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getFloat(Enum)}.</em><p>
     */
    public float getFloat(T tagValue) throws MetadataException
    {
        return getFloat(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a float.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Float getFloatObject(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getFloatObject(Enum)}.</em><p>
     */
    @Nullable
    public Float getFloatObject(T tagValue)
    {
        return getFloatObject(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a long, if possible. */
    public long getLong(K tagType) throws MetadataException
    {
        Long value = getLongObject(tagType);
        if (value != null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getTagName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a long.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getLong(Enum)}.</em><p>
     */
    public long getLong(T tagValue) throws MetadataException
    {
        return getLong(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a long.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Long getLongObject(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getLongObject(Enum)}.</em><p>
     */
    @Nullable
    public Long getLongObject(T tagValue)
    {
        return getLongObject(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a boolean, if possible. */
    public boolean getBoolean(K tagType) throws MetadataException
    {
        Boolean value = getBooleanObject(tagType);
        if (value != null)
            return value;
        Object o = getObject(tagType);
        if (o == null)
            throw new MetadataException("Tag '" + tagType.getTagName() + "' has not been set -- check using containsTag() first");
        throw new MetadataException("Tag '" + tagType + "' cannot be converted to a boolean.  It is of type '" + o.getClass() + "'.");
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getBoolean(Enum)}.</em><p>
     */
    public boolean getBoolean(T tagValue) throws MetadataException
    {
        return getBoolean(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a boolean.  If the tag is not set or cannot be converted, <code>null</code> is returned. */
    @Nullable
    @SuppressWarnings(value = "NP_BOOLEAN_RETURN_NULL", justification = "keep API interface consistent")
    public Boolean getBooleanObject(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getBooleanObject(Enum)}.</em><p>
     */
    @Nullable
    @SuppressWarnings(value = "NP_BOOLEAN_RETURN_NULL", justification = "keep API interface consistent")
    public Boolean getBooleanObject(T tagValue)
    {
        return getBooleanObject(getTagFromValue(tagValue));
    }

    /**
     * Returns the specified tag's value as a java.util.Date.  If the value is unset or cannot be converted, <code>null</code> is returned.
     * <p>
     * If the underlying value is a {@link String}, then attempts will be made to parse the string as though it is in
     * the GMT {@link TimeZone}.  If the {@link TimeZone} is known, call the overload that accepts one as an argument.
     */
    @Nullable
    public java.util.Date getDate(K tagType)
    {
        return getDate(tagType, null, null);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDate(Enum)}.</em><p>
     */
    @Nullable
    public java.util.Date getDate(T tagValue)
    {
        return getDate(getTagFromValue(tagValue));
    }

    /**
     * Returns the specified tag's value as a java.util.Date.  If the value is unset or cannot be converted, <code>null</code> is returned.
     * <p>
     * If the underlying value is a {@link String}, then attempts will be made to parse the string as though it is in
     * the {@link TimeZone} represented by the {@code timeZone} parameter (if it is non-null).  Note that this parameter
     * is only considered if the underlying value is a string and it has no time zone information, otherwise it has no effect.
     */
    @Nullable
    public java.util.Date getDate(K tagType, @Nullable TimeZone timeZone)
    {
        return getDate(tagType, null, timeZone);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDate(Enum,TimeZone)}.</em><p>
     */
    @Nullable
    public java.util.Date getDate(T tagValue, @Nullable TimeZone timeZone)
    {
        return getDate(getTagFromValue(tagValue), timeZone);
    }

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
    public java.util.Date getDate(K tagType, @Nullable String subsecond, @Nullable TimeZone timeZone)
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
     * <em>For backwards compatibility.  If possible use {@link #getDate(Enum,String,TimeZone)}.</em><p>
     */
    @Nullable
    public java.util.Date getDate(T tagValue, @Nullable String subsecond, @Nullable TimeZone timeZone)
    {
        return getDate(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as a Rational.  If the value is unset or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Rational getRational(K tagType)
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
     * <em>For backwards compatibility.  If possible use {@link #getRational(Enum)}.</em><p>
     */
    @Nullable
    public Rational getRational(T tagValue)
    {
        return getRational(getTagFromValue(tagValue));
    }

    /** Returns the specified tag's value as an array of Rational.  If the value is unset or cannot be converted, <code>null</code> is returned. */
    @Nullable
    public Rational[] getRationalArray(K tagType)
    {
        Object o = getObject(tagType);
        if (o == null)
            return null;

        if (o instanceof Rational[])
            return (Rational[])o;

        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getRationalArray(Enum)}.</em><p>
     */
    @Nullable
    public Rational[] getRationalArray(T tagValue)
    {
        return getRationalArray(getTagFromValue(tagValue));
    }

    /**
     * Returns the specified tag's value as a String.  This value is the 'raw' value.  A more presentable decoding
     * of this value may be obtained from the corresponding Descriptor.
     *
     * @return the String representation of the tag's value, or
     *         <code>null</code> if the tag hasn't been defined.
     */
    @Nullable
    public String getString(K tagType)
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
    public String getString(K tagType, String charset)
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
    public StringValue getStringValue(K tagType)
    {
        Object o = getObject(tagType);
        if (o instanceof StringValue)
            return (StringValue)o;
        return null;
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getString(Enum)}.</em><p>
     */
    @Nullable
    public String getString(T tagValue)
    {
        return getString(getTagFromValue(tagValue));
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getString(Enum, String)}.</em><p>
     */
    @Nullable
    public String getString(T tagValue, String charset)
    {
        return getString(getTagFromValue(tagValue), charset);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getStringValue(Enum)}.</em><p>
     */
    @Nullable
    public StringValue getStringValue(T tagValue)
    {
        return getStringValue(getTagFromValue(tagValue));
    }

    /**
     * Returns the object hashed for the particular tag type specified, if available.
     *
     * @param tagType the tag type identifier
     * @return the tag's value as an Object if available, else <code>null</code>
     */
    @java.lang.SuppressWarnings({ "UnnecessaryBoxing" })
    @Nullable
    public Object getObject(K tagType)
    {
        return _tagMap.get(tagType);
    }

// OTHER METHODS

    /**
     * Deprecated.
     * See {@link Key#getTagName()}
     *
     * @param tagType the tag type identifier
     * @return the tag's name as a String
     */
    @Deprecated
    @NotNull
    public String getTagName(K tagType)
    {
        return tagType.getTagName();
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getTagName(Enum)}.</em><p>
     */
    @Deprecated
    @NotNull
    public String getTagName(T tagValue)
    {
        return getTagName(getTagFromValue(tagValue));
    }

    /**
     * Gets whether the specified tag is known by the directory and has a name.
     *
     * @param tagType the tag type identifier
     * @return whether this directory has a name for the specified tag
     */
    public boolean hasTagName(K tagType)
    {
        return getTagSet().contains(tagType);
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #hasTagName(Enum)}.</em><p>
     */
    public boolean hasTagName(T value)
    {
        return hasTagName(getTagFromValue(value));
    }

    /**
     * Provides a formatted description of a tag's value.
     *
     * @param tagType the tag type identifier
     * @return the tag value's description as a String
     */
    @Nullable
    public String getDescription(K tagType)
    {
        return tagType.getDescription(getObject(tagType));
    }

    /**
     * <em>For backwards compatibility.  If possible use {@link #getDescription(Enum)}.</em><p>
     */
    @Nullable
    public String getDescription(T tagValue)
    {
        return getDescription(getTagFromValue(tagValue));
    }

    @Override
    public String toString()
    {
        return String.format("%s Directory (%d %s)",
            getName(),
            _tagMap.size(),
            _tagMap.size() == 1
                ? "tag"
                : "tags");
    }

    // Descriptor methods
    @Nullable
    protected static String getIndexedDescription(int index, final int baseIndex, @NotNull String... descriptions)
    {
        final int arrayIndex = index - baseIndex;
        if (arrayIndex >= 0 && arrayIndex < descriptions.length) {
            String description = descriptions[arrayIndex];
            if (description != null)
                return description;
        }
        return "Unknown (" + index + ")";
    }
}
