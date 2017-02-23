package com.drew.metadata;

import com.drew.lang.annotations.Nullable;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DescriptionUtil
{
    /**
     * Takes a series of 4 bytes from the specified offset, and converts these to a
     * well-known version number, where possible.
     * <p>
     * Two different formats are processed:
     * <ul>
     * <li>[30 32 31 30] -&gt; 2.10</li>
     * <li>[0 1 0 0] -&gt; 1.00</li>
     * </ul>
     *
     * @param components  the four version values
     * @param majorDigits the number of components to be
     * @return the version as a string of form "2.10" or null if the argument cannot be converted
     */
    @Nullable
    public static String convertBytesToVersionString(@Nullable int[] components, final int majorDigits)
    {
        if (components == null)
            return null;
        StringBuilder version = new StringBuilder();
        for (int i = 0; i < 4 && i < components.length; i++) {
            if (i == majorDigits)
                version.append('.');
            char c = (char)components[i];
            if (c < '0')
                c += '0';
            if (i == 0 && c == '0')
                continue;
            version.append(c);
        }
        return version.toString();
    }

    @Nullable
    public static String getFocalLengthDescription(double mm)
    {
        DecimalFormat format = new DecimalFormat("0.#");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(mm) + " mm";
    }

    @Nullable
    public static String getFStopDescription(double fStop)
    {
        DecimalFormat format = new DecimalFormat("0.0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return "f/" + format.format(fStop);
    }
}
