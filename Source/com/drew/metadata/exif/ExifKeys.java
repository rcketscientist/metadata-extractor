package com.drew.metadata.exif;

import com.drew.imaging.PhotographicConversions;
import com.drew.lang.Rational;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.DescriptionUtil;
import com.drew.metadata.DirectoryBase;
import com.drew.metadata.Key;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public enum ExifKeys implements Key
{
    // Note for the potential addition of brightness presentation in eV:
    // Brightness of taken subject. To calculate Exposure(Ev) from BrightnessValue(Bv),
    // you must add SensitivityValue(Sv).
    // Ev=BV+Sv   Sv=log2(ISOSpeedRating/3.125)
    // ISO100:Sv=5, ISO200:Sv=6, ISO400:Sv=7, ISO125:Sv=5.32.

    TAG_INTEROP_INDEX(      0x0001, "Interoperability Index")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                String value = directory.getString(TAG_INTEROP_INDEX);

                if (value == null)
                    return null;

                return "R98".equalsIgnoreCase(value.trim())
                    ? "Recommended Exif Interoperability Rules (ExifR98)"
                    : "Unknown (" + value + ")";
            }
        },
    TAG_INTEROP_VERSION(    0x0002, "Interoperability Version")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                int[] values = directory.getIntArray(TAG_INTEROP_VERSION);
                return values == null ? null : DescriptionUtil.convertBytesToVersionString(values, 2);
            }
        },
    TAG_NEW_SUBFILE_TYPE(   0x00FE, "New Subfile Type")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(this, 0,
                    "Full-resolution image",
                    "Reduced-resolution image",
                    "Single page of multi-page image",
                    "Single page of multi-page reduced-resolution image",
                    "Transparency mask",
                    "Transparency mask of reduced-resolution image",
                    "Transparency mask of multi-page image",
                    "Transparency mask of reduced-resolution multi-page image"
                );
            }
        },
    TAG_SUBFILE_TYPE(0x00FF, "Subfile Type")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(this, 1,
                    "Full-resolution image",    /*Main image*/
                    "Reduced-resolution image", /*Thumbnail*/
                    "Single page of multi-page image"
                );
            }
        },
    TAG_IMAGE_WIDTH(0x0100, "Image Width")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                String value = directory.getString(TAG_IMAGE_WIDTH);
                return value == null ? null : value + " pixels";
            }
        },
    TAG_IMAGE_HEIGHT(0x0101, "Image Height")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                String value = directory.getString(TAG_IMAGE_HEIGHT);
                return value == null ? null : value + " pixels";
            }
        },

    /**
     * When image format is no compression, this value shows the number of bits
     * per component for each pixel. Usually this value is '8,8,8'.
     */
    TAG_BITS_PER_SAMPLE(0x0102, "Bits Per Sample")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                String value = directory.getString(TAG_BITS_PER_SAMPLE);
                return value == null ? null : value + " bits/component/pixel";
            }
        },
    TAG_COMPRESSION(0x0103, "Compression")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Integer value = directory.getInteger(TAG_COMPRESSION);
                if (value == null)
                    return null;
                switch (value) {
                    case 1: return "Uncompressed";
                    case 2: return "CCITT 1D";
                    case 3: return "T4/Group 3 Fax";
                    case 4: return "T6/Group 4 Fax";
                    case 5: return "LZW";
                    case 6: return "JPEG (old-style)";
                    case 7: return "JPEG";
                    case 8: return "Adobe Deflate";
                    case 9: return "JBIG B&W";
                    case 10: return "JBIG Color";
                    case 99: return "JPEG";
                    case 262: return "Kodak 262";
                    case 32766: return "Next";
                    case 32767: return "Sony ARW Compressed";
                    case 32769: return "Packed RAW";
                    case 32770: return "Samsung SRW Compressed";
                    case 32771: return "CCIRLEW";
                    case 32772: return "Samsung SRW Compressed 2";
                    case 32773: return "PackBits";
                    case 32809: return "Thunderscan";
                    case 32867: return "Kodak KDC Compressed";
                    case 32895: return "IT8CTPAD";
                    case 32896: return "IT8LW";
                    case 32897: return "IT8MP";
                    case 32898: return "IT8BL";
                    case 32908: return "PixarFilm";
                    case 32909: return "PixarLog";
                    case 32946: return "Deflate";
                    case 32947: return "DCS";
                    case 34661: return "JBIG";
                    case 34676: return "SGILog";
                    case 34677: return "SGILog24";
                    case 34712: return "JPEG 2000";
                    case 34713: return "Nikon NEF Compressed";
                    case 34715: return "JBIG2 TIFF FX";
                    case 34718: return "Microsoft Document Imaging (MDI) Binary Level Codec";
                    case 34719: return "Microsoft Document Imaging (MDI) Progressive Transform Codec";
                    case 34720: return "Microsoft Document Imaging (MDI) Vector";
                    case 34892: return "Lossy JPEG";
                    case 65000: return "Kodak DCR Compressed";
                    case 65535: return "Pentax PEF Compressed";
                    default:
                        return "Unknown (" + value + ")";
                }

            }
        },

    /**
     * Shows the color space of the image data components.
     */
    TAG_PHOTOMETRIC_INTERPRETATION(0x0106, "Photometric Interpretation")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // Shows the color space of the image data components
                Integer value = directory.getInteger(TAG_PHOTOMETRIC_INTERPRETATION);
                if (value == null)
                    return null;
                switch (value) {
                    case 0: return "WhiteIsZero";
                    case 1: return "BlackIsZero";
                    case 2: return "RGB";
                    case 3: return "RGB Palette";
                    case 4: return "Transparency Mask";
                    case 5: return "CMYK";
                    case 6: return "YCbCr";
                    case 8: return "CIELab";
                    case 9: return "ICCLab";
                    case 10: return "ITULab";
                    case 32803: return "Color Filter Array";
                    case 32844: return "Pixar LogL";
                    case 32845: return "Pixar LogLuv";
                    case 32892: return "Linear Raw";
                    default:
                        return "Unknown colour space";
                }
            }
        },

    TAG_THRESHOLDING(0x0107, "Thresholding")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(this, 1,
                    "No dithering or halftoning",
                    "Ordered dither or halftone",
                    "Randomized dither"
                );
            }
        },

    TAG_FILL_ORDER(0x010A, "Fill Order")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(this, 1,
                    "Normal",
                    "Reversed"
                );
            }
        },
    TAG_DOCUMENT_NAME(0x010D, "Document Name")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },

    TAG_IMAGE_DESCRIPTION(0x010E, "Image Description")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },

    TAG_MAKE(0x010F, "Make")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },
    TAG_MODEL(0x0110, "Model")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },
    /** The position in the file of raster data. */
    TAG_STRIP_OFFSETS(0x0111, "Strip Offsets")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },
    TAG_ORIENTATION(0x0112, "Orientation")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getOrientationDescription(this);
            }
        },
    /** Each pixel is composed of this many samples. */
    TAG_SAMPLES_PER_PIXEL(0x0115, "Samples Per Pixel")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                String value = directory.getString(TAG_SAMPLES_PER_PIXEL);
                return value == null ? null : value + " samples/pixel";
            }
        },
    /** The raster is codified by a single block of data holding this many rows. */
    TAG_ROWS_PER_STRIP(0x0116, "Rows Per Strip")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                final String value = directory.getString(TAG_ROWS_PER_STRIP);
                return value == null ? null : value + " rows/strip";
            }
        },
    /** The size of the raster data in bytes. */
    TAG_STRIP_BYTE_COUNTS(0x0117, "Strip Byte Counts")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                final String value = directory.getString(TAG_STRIP_BYTE_COUNTS);
                return value == null ? null : value + " bytes";
            }
        },
    TAG_MIN_SAMPLE_VALUE(0x0118, "Minimum Sample Value")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },
    TAG_MAX_SAMPLE_VALUE(0x0119, "Maximum Sample Value")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getDescription(this);  //TODO: generic?
            }
        },
    TAG_X_RESOLUTION(0x011A, "X Resolution")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_X_RESOLUTION);
                if (value == null)
                    return null;
                final String unit = TAG_RESOLUTION_UNIT.getDescription(directory);
                return String.format("%s dots per %s",
                    value.toSimpleString(_allowDecimalRepresentationOfRationals),
                    unit == null ? "unit" : unit.toLowerCase());
            }
        },
    TAG_Y_RESOLUTION(0x011B, "Y Resolution")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_Y_RESOLUTION);
                if (value==null)
                    return null;
                final String unit = TAG_RESOLUTION_UNIT.getDescription(directory);
                return String.format("%s dots per %s",
                    value.toSimpleString(_allowDecimalRepresentationOfRationals),
                    unit == null ? "unit" : unit.toLowerCase());
            }
        },
    /**
     * When image format is no compression YCbCr, this value shows byte aligns of
     * YCbCr data. If value is '1', Y/Cb/Cr value is chunky format, contiguous for
     * each subsampling pixel. If value is '2', Y/Cb/Cr value is separated and
     * stored to Y plane/Cb plane/Cr plane format.
     */
    TAG_PLANAR_CONFIGURATION(0x011C, "Planar Configuration")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // When image format is no compression YCbCr, this value shows byte aligns of YCbCr
                // data. If value is '1', Y/Cb/Cr value is chunky format, contiguous for each subsampling
                // pixel. If value is '2', Y/Cb/Cr value is separated and stored to Y plane/Cb plane/Cr
                // plane format.
                return directory.getIndexedDescription(this,
                    1,
                    "Chunky (contiguous for each subsampling pixel)",
                    "Separate (Y-plane/Cb-plane/Cr-plane format)"
                );
            }
        },
    TAG_PAGE_NAME(0x011D, "Page Name"),

    TAG_RESOLUTION_UNIT(0x0128, "Resolution Unit")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // '1' means no-unit, '2' means inch, '3' means centimeter. Default value is '2'(inch)
                return directory.getIndexedDescription(this, 1,
                    "(No unit)",
                    "Inch",
                    "cm");
            }
        },
    TAG_PAGE_NUMBER(0x0129, "Page Number"),

    TAG_TRANSFER_FUNCTION(0x012D, "Transfer Function"),
    TAG_SOFTWARE(0x0131, "Software"),
    TAG_DATETIME(0x0132, "Date/Time"),  // TODO: Just the default handler????
    TAG_ARTIST(0x013B, "Artist"),
    TAG_HOST_COMPUTER(0x013C, "Host Computer"),
    TAG_PREDICTOR(0x013D, "Predictor"),
    TAG_WHITE_POINT(0x013E, "White Point"),
    TAG_PRIMARY_CHROMATICITIES(0x013F, "Primary Chromaticities"),
    TAG_TILE_WIDTH(0x0142, "Tile Width"),
    TAG_TILE_LENGTH(0x0143, "Tile Length"),
    TAG_TILE_OFFSETS(0x0144, "Tile Offsets"),
    TAG_TILE_BYTE_COUNTS(0x0145, "Tile Byte Counts"),

    /**
     * Tag is a pointer to one or more sub-IFDs.
     + Seems to be used exclusively by raw formats, referencing one or two IFDs.
     */
    TAG_SUB_IFD_OFFSET(0x014a, "Sub IFD Pointer(s)"),
    TAG_TRANSFER_RANGE(0x0156, "Transfer Range"),
    TAG_JPEG_TABLES(0x015B, "JPEG Tables"),
    TAG_JPEG_PROC(0x0200, "JPEG Proc")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Integer value = directory.getInteger(TAG_JPEG_PROC);
                if (value == null)
                    return null;
                switch (value) {
                    case 1: return "Baseline";
                    case 14: return "Lossless";
                    default:
                        return "Unknown (" + value + ")";
                }
            }
        },

    // 0x0201 can have all kinds of descriptions for thumbnail starting index
    // 0x0202 can have all kinds of descriptions for thumbnail length
    TAG_JPEG_RESTART_INTERVAL(0x0203, "JPEG Restart Interval"),
    TAG_JPEG_LOSSLESS_PREDICTORS(0x0205, "JPEG Lossless Predictors"),
    TAG_JPEG_POINT_TRANSFORMS(0x0206, "JPEG Point Transforms"),
    TAG_JPEG_Q_TABLES(0x0207, "JPEGQ Tables"),
    TAG_JPEG_DC_TABLES(0x0208, "JPEGDC Tables"),
    TAG_JPEG_AC_TABLES(0x0209, "JPEGAC Tables"),

    TAG_YCBCR_COEFFICIENTS(0x0211, "YCbCr Coefficients"),
    TAG_YCBCR_SUBSAMPLING(0x0212, "YCbCr Sub-Sampling")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                int[] positions = directory.getIntArray(TAG_YCBCR_SUBSAMPLING);
                if (positions == null || positions.length < 2)
                    return null;
                if (positions[0] == 2 && positions[1] == 1) {
                    return "YCbCr4:2:2";
                } else if (positions[0] == 2 && positions[1] == 2) {
                    return "YCbCr4:2:0";
                } else {
                    return "(Unknown)";
                }
            }
        },
    TAG_YCBCR_POSITIONING(0x0213, "YCbCr Positioning")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(this, 1,
                    "Center of pixel array",
                    "Datum point");
            }
        },
    TAG_REFERENCE_BLACK_WHITE(0x0214, "Reference Black/White")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // For some reason, sometimes this is read as a long[] and
                // getIntArray isn't able to deal with it
                int[] ints = directory.getIntArray(TAG_REFERENCE_BLACK_WHITE);
                if (ints==null || ints.length < 6)
                {
                    Object o = directory.getObject(TAG_REFERENCE_BLACK_WHITE);
                    if (o != null && (o instanceof long[]))
                    {
                        long[] longs = (long[])o;
                        if (longs.length < 6)
                            return null;

                        ints = new int[longs.length];
                        for (int i = 0; i < longs.length; i++)
                            ints[i] = (int)longs[i];
                    }
                    else
                        return null;
                }

                int blackR = ints[0];
                int whiteR = ints[1];
                int blackG = ints[2];
                int whiteG = ints[3];
                int blackB = ints[4];
                int whiteB = ints[5];
                return String.format("[%d,%d,%d] [%d,%d,%d]", blackR, blackG, blackB, whiteR, whiteG, whiteB);
            }
        },
    TAG_STRIP_ROW_COUNTS(0x022f, "Strip Row Counts"),
    TAG_APPLICATION_NOTES(0x02bc, "Application Notes"),
    TAG_RELATED_IMAGE_FILE_FORMAT(0x1000, "Related Image File Format"),
    TAG_RELATED_IMAGE_WIDTH(0x1001, "Related Image Width"),
    TAG_RELATED_IMAGE_HEIGHT(0x1002, "Related Image Height"),
    TAG_RATING(0x4746, "Rating"),

    TAG_CFA_REPEAT_PATTERN_DIM(0x828D, "CFA Repeat Pattern Dim"),
    /** There are two definitions for CFA pattern, I don't know the difference... */
    TAG_CFA_PATTERN_2(0x828E, "CFA Pattern")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                byte[] values = directory.getByteArray(TAG_CFA_PATTERN_2);
                if (values == null)
                    return null;

                int[] repeatPattern = directory.getIntArray(TAG_CFA_REPEAT_PATTERN_DIM);
                if (repeatPattern == null)
                    return String.format("Repeat Pattern not found for CFAPattern (%s)", directory.getDescription(TAG_CFA_PATTERN_2));

                if (repeatPattern.length == 2 && values.length == (repeatPattern[0] * repeatPattern[1]))
                {
                    int[] intpattern = new int[2 + values.length];
                    intpattern[0] = repeatPattern[0];
                    intpattern[1] = repeatPattern[1];

                    for (int i = 0; i < values.length; i++)
                        intpattern[i + 2] = values[i] & 0xFF;   // convert the values[i] byte to unsigned

                    return formatCFAPattern(intpattern);
                }

                return String.format("Unknown Pattern (%s)", directory.getDescription(TAG_CFA_PATTERN_2));
            }
        },
    TAG_BATTERY_LEVEL(0x828F, "Battery Level"),
    TAG_COPYRIGHT(0x8298, "Copyright"),
    /**
     * Exposure time (reciprocal of shutter speed). Unit is second.
     */
    TAG_EXPOSURE_TIME(0x829A, "Exposure Time")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                String value = directory.getString(TAG_EXPOSURE_TIME);
                return value == null ? null : value + " sec";
            }
        },
    /**
     * The actual F-number(F-stop) of lens when the image was taken.
     */
    TAG_FNUMBER(0x829D, "F-Number")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_FNUMBER);
                if (value == null)
                    return null;
                return DescriptionUtil.getFStopDescription(value.doubleValue());
            }
        },
    TAG_IPTC_NAA(0x83BB, "IPTC/NAA"),
    TAG_INTER_COLOR_PROFILE(0x8773, "Inter Color Profile"),
    TAG_EXPOSURE_PROGRAM(0x8822, "Exposure Program")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(this,
                    1,
                    "Manual control",
                    "Program normal",
                    "Aperture priority",
                    "Shutter priority",
                    "Program creative (slow program)",
                    "Program action (high-speed program)",
                    "Portrait mode",
                    "Landscape mode"
                );
            }
        },
    TAG_SPECTRAL_SENSITIVITY(0x8824, "Spectral Sensitivity"),
    TAG_ISO_EQUIVALENT(0x8827, "ISO Speed Ratings")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // Have seen an exception here from files produced by ACDSEE that stored an int[] here with two values
                Integer isoEquiv = directory.getInteger(TAG_ISO_EQUIVALENT);
                // There used to be a check here that multiplied ISO values < 50 by 200.
                // Issue 36 shows a smart-phone image from a Samsung Galaxy S2 with ISO-40.
                return isoEquiv != null
                    ? Integer.toString(isoEquiv)
                    : null;
            }
        },
    /**
     * Indicates the Opto-Electric Conversion Function (OECF) specified in ISO 14524.
     * <p>
     * OECF is the relationship between the camera optical input and the image values.
     * <p>
     * The values are:
     * <ul>
     *   <li>Two shorts, indicating respectively number of columns, and number of rows.</li>
     *   <li>For each column, the column name in a null-terminated ASCII string.</li>
     *   <li>For each cell, an SRATIONAL value.</li>
     * </ul>
     */
    TAG_OPTO_ELECTRIC_CONVERSION_FUNCTION(0x8828, "Opto-electric Conversion Function (OECF)"),
    TAG_INTERLACE(0x8829, "Interlace"),
    TAG_TIME_ZONE_OFFSET_TIFF_EP(0x882A, "Time Zone Offset"),
    TAG_SELF_TIMER_MODE_TIFF_EP(0x882B, "Self Timer Mode"),
    TAG_SENSITIVITY_TYPE(0x8830, "Sensitivity Type")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_SENSITIVITY_TYPE, 0,
                    "Unknown",
                    "Standard Output Sensitivity",
                    "Recommended Exposure Index",
                    "ISO Speed",
                    "Standard Output Sensitivity and Recommended Exposure Index",
                    "Standard Output Sensitivity and ISO Speed",
                    "Recommended Exposure Index and ISO Speed",
                    "Standard Output Sensitivity, Recommended Exposure Index and ISO Speed"
                );
            }
        },
    TAG_STANDARD_OUTPUT_SENSITIVITY(0x8831, "Standard Output Sensitivity"),
    TAG_RECOMMENDED_EXPOSURE_INDEX(0x8832, "Recommended Exposure Index"),
    /** Non-standard, but in use. */
    TAG_TIME_ZONE_OFFSET(0x882A, "Time Zone Offset"),
    TAG_SELF_TIMER_MODE(0x882B, "Self Timer Mode"),
    TAG_EXIF_VERSION(0x9000, "Exif Version")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getVersionBytesDescription(TAG_EXIF_VERSION, 2);
            }
        },
    TAG_DATETIME_ORIGINAL(0x9003, "Date/Time Original"),
    TAG_DATETIME_DIGITIZED(0x9004, "Date/Time Digitized"),
    TAG_COMPONENTS_CONFIGURATION(0x9101, "Components Configuration")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                int[] components = directory.getIntArray(TAG_COMPONENTS_CONFIGURATION);
                if (components == null)
                    return null;
                String[] componentStrings = {"", "Y", "Cb", "Cr", "R", "G", "B"};
                StringBuilder componentConfig = new StringBuilder();
                for (int i = 0; i < Math.min(4, components.length); i++) {
                    int j = components[i];
                    if (j > 0 && j < componentStrings.length) {
                        componentConfig.append(componentStrings[j]);
                    }
                }
                return componentConfig.toString();
            }
        },
    /**
     * Average (rough estimate) compression level in JPEG bits per pixel.
     * */
    TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL(0x9102, "Compressed Bits Per Pixel")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL);
                if (value == null)
                    return null;
                String ratio = value.toSimpleString(_allowDecimalRepresentationOfRationals);
                return value.isInteger() && value.intValue() == 1
                    ? ratio + " bit/pixel"
                    : ratio + " bits/pixel";
            }
        },

    /**
     * Shutter speed by APEX value. To convert this value to ordinary 'Shutter Speed';
     * calculate this value's power of 2, then reciprocal. For example, if the
     * ShutterSpeedValue is '4', shutter speed is 1/(24)=1/16 second.
     */
    TAG_SHUTTER_SPEED(0x9201, "Shutter Speed Value")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getShutterSpeedDescription(TAG_SHUTTER_SPEED);
            }
        },
    /**
     * The actual aperture value of lens when the image was taken. Unit is APEX.
     * To convert this value to ordinary F-number (F-stop), calculate this value's
     * power of root 2 (=1.4142). For example, if the ApertureValue is '5',
     * F-number is 1.4142^5 = F5.6.
     */
    TAG_APERTURE(0x9202, "Aperture Value")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Double aperture = directory.getDoubleObject(TAG_APERTURE);
                if (aperture == null)
                    return null;
                double fStop = PhotographicConversions.apertureToFStop(aperture);
                return DescriptionUtil.getFStopDescription(fStop);
            }
        },
    TAG_BRIGHTNESS_VALUE(0x9203, "Brightness Value"),
    TAG_EXPOSURE_BIAS(0x9204, "Exposure Bias Value")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_EXPOSURE_BIAS);
                if (value == null)
                    return null;
                return value.toSimpleString(true) + " EV";
            }
        },
    /**
     * Maximum aperture value of lens. You can convert to F-number by calculating
     * power of root 2 (same process of ApertureValue:0x9202).
     * The actual aperture value of lens when the image was taken. To convert this
     * value to ordinary f-number(f-stop), calculate the value's power of root 2
     * (=1.4142). For example, if the ApertureValue is '5', f-number is 1.41425^5 = F5.6.
     */
    TAG_MAX_APERTURE(0x9205, "Max Aperture Value")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Double aperture = directory.getDoubleObject(TAG_MAX_APERTURE);
                if (aperture == null)
                    return null;
                double fStop = PhotographicConversions.apertureToFStop(aperture);
                return DescriptionUtil.getFStopDescription(fStop);
            }
        },
    /**
     * Indicates the distance the autofocus camera is focused to.  Tends to be less accurate as distance increases.
     */
    TAG_SUBJECT_DISTANCE(0x9206, "Subject Distance")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_SUBJECT_DISTANCE);
                if (value == null)
                    return null;
                DecimalFormat formatter = new DecimalFormat("0.0##");
                return formatter.format(value.doubleValue()) + " metres";
            }
        },
    /**
     * Exposure metering method. '0' means unknown, '1' average, '2' center
     * weighted average, '3' spot, '4' multi-spot, '5' multi-segment, '6' partial,
     * '255' other.
     */
    TAG_METERING_MODE(0x9207, "Metering Mode")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // '0' means unknown, '1' average, '2' center weighted average, '3' spot
                // '4' multi-spot, '5' multi-segment, '6' partial, '255' other
                Integer value = directory.getInteger(TAG_METERING_MODE);
                if (value == null)
                    return null;
                switch (value) {
                    case 0: return "Unknown";
                    case 1: return "Average";
                    case 2: return "Center weighted average";
                    case 3: return "Spot";
                    case 4: return "Multi-spot";
                    case 5: return "Multi-segment";
                    case 6: return "Partial";
                    case 255: return "(Other)";
                    default:
                        return "Unknown (" + value + ")";
                }
            }
        },

    /**
     * @deprecated use {@link ExifKeys#TAG_WHITE_BALANCE} instead.
     */
    @Deprecated
    TAG_LIGHT_SOURCE(0x9208, "(Deprecated) Use White Balance"),
    /**
     * White balance (aka light source). '0' means unknown, '1' daylight,
     * '2' fluorescent, '3' tungsten, '10' flash, '17' standard light A,
     * '18' standard light B, '19' standard light C, '20' D55, '21' D65,
     * '22' D75, '255' other.
     */
    TAG_WHITE_BALANCE(0x9208, "White Balance")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                //TODO: Think I saw this same list in Panasonic...reuse?
                // See http://web.archive.org/web/20131018091152/http://exif.org/Exif2-2.PDF page 35
                final Integer value = directory.getInteger(TAG_WHITE_BALANCE);
                if (value == null)
                    return null;
                switch (value) {
                    case 0: return "Unknown";
                    case 1: return "Daylight";
                    case 2: return "Florescent";
                    case 3: return "Tungsten";
                    case 4: return "Flash";
                    case 9: return "Fine Weather";
                    case 10: return "Cloudy";
                    case 11: return "Shade";
                    case 12: return "Daylight Fluorescent";
                    case 13: return "Day White Fluorescent";
                    case 14: return "Cool White Fluorescent";
                    case 15: return "White Fluorescent";
                    case 16: return "Warm White Fluorescent";
                    case 17: return "Standard light";
                    case 18: return "Standard light (B)";
                    case 19: return "Standard light (C)";
                    case 20: return "D55";
                    case 21: return "D65";
                    case 22: return "D75";
                    case 23: return "D50";
                    case 24: return "Studio Tungsten";
                    case 255: return "(Other)";
                    default:
                        return "Unknown (" + value + ")";
                }
            }
        },
    /**
     * 0x0  = 0000000 = No Flash
     * 0x1  = 0000001 = Fired
     * 0x5  = 0000101 = Fired, Return not detected
     * 0x7  = 0000111 = Fired, Return detected
     * 0x9  = 0001001 = On
     * 0xd  = 0001101 = On, Return not detected
     * 0xf  = 0001111 = On, Return detected
     * 0x10 = 0010000 = Off
     * 0x18 = 0011000 = Auto, Did not fire
     * 0x19 = 0011001 = Auto, Fired
     * 0x1d = 0011101 = Auto, Fired, Return not detected
     * 0x1f = 0011111 = Auto, Fired, Return detected
     * 0x20 = 0100000 = No flash function
     * 0x41 = 1000001 = Fired, Red-eye reduction
     * 0x45 = 1000101 = Fired, Red-eye reduction, Return not detected
     * 0x47 = 1000111 = Fired, Red-eye reduction, Return detected
     * 0x49 = 1001001 = On, Red-eye reduction
     * 0x4d = 1001101 = On, Red-eye reduction, Return not detected
     * 0x4f = 1001111 = On, Red-eye reduction, Return detected
     * 0x59 = 1011001 = Auto, Fired, Red-eye reduction
     * 0x5d = 1011101 = Auto, Fired, Red-eye reduction, Return not detected
     * 0x5f = 1011111 = Auto, Fired, Red-eye reduction, Return detected
     *        6543210 (positions)
     *
     * This is a bitmask.
     * 0 = flash fired
     * 1 = return detected
     * 2 = return able to be detected
     * 3 = unknown
     * 4 = auto used
     * 5 = unknown
     * 6 = red eye reduction used
     */
    TAG_FLASH(0x9209, "Flash")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                final Integer value = directory.getInteger(TAG_FLASH);

                if (value == null)
                    return null;

                StringBuilder sb = new StringBuilder();

                if ((value & 0x1) != 0)
                    sb.append("Flash fired");
                else
                    sb.append("Flash did not fire");

                // check if we're able to detect a return, before we mention it
                if ((value & 0x4) != 0) {
                    if ((value & 0x2) != 0)
                        sb.append(", return detected");
                    else
                        sb.append(", return not detected");
                }

                if ((value & 0x10) != 0)
                    sb.append(", auto");

                if ((value & 0x40) != 0)
                    sb.append(", red-eye reduction");

                return sb.toString();
            }
        },
    /**
     * Focal length of lens used to take image.  Unit is millimeter.
     * Nice digital cameras actually save the focal length as a function of how far they are zoomed in.
     */
    TAG_FOCAL_LENGTH(0x920A, "Focal Length")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_FOCAL_LENGTH);
                return value == null ? null : DescriptionUtil.getFocalLengthDescription(value.doubleValue());
            }
        },

    TAG_FLASH_ENERGY_TIFF_EP(0x920B, "Flash Energy"),
    TAG_SPATIAL_FREQ_RESPONSE_TIFF_EP(0x920C, "Spatial Frequency Response"),
    TAG_NOISE(0x920D, "Noise"),
    TAG_FOCAL_PLANE_X_RESOLUTION_TIFF_EP(0x920E, "Focal Plane X Resolution"),
    TAG_FOCAL_PLANE_Y_RESOLUTION_TIFF_EP(0x920F, "Focal Plane Y Resolution"),
    TAG_IMAGE_NUMBER(0x9211, "Image Number"),
    TAG_SECURITY_CLASSIFICATION(0x9212, "Security Classification"),
    TAG_IMAGE_HISTORY(0x9213, "Image History"),
    TAG_SUBJECT_LOCATION_TIFF_EP(0x9214, "Subject Location"),
    TAG_EXPOSURE_INDEX_TIFF_EP(0x9215, "Exposure Index"),
    TAG_STANDARD_ID_TIFF_EP(0x9216, "TIFF/EP Standard ID"),

    /**
     * This tag holds the Exif Makernote. Makernotes are free to be in any format, though they are often IFDs.
     * To determine the format, we consider the starting bytes of the makernote itself and sometimes the
     * camera model and make.
     * <p>
     * The component count for this tag includes all of the bytes needed for the makernote.
     */
    TAG_MAKERNOTE(0x927C, "Makernote"),  //TODO: There are notes on how to parse, but no custom parse originally
    TAG_USER_COMMENT(0x9286, "User Comment")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                byte[] commentBytes = directory.getByteArray(TAG_USER_COMMENT);
                if (commentBytes == null)
                    return null;
                if (commentBytes.length == 0)
                    return "";

                final Map<String, String> encodingMap = new HashMap<String, String>();
                encodingMap.put("ASCII", System.getProperty("file.encoding")); // Someone suggested "ISO-8859-1".
                encodingMap.put("UNICODE", "UTF-16LE");
                encodingMap.put("JIS", "Shift-JIS"); // We assume this charset for now.  Another suggestion is "JIS".

                try {
                    if (commentBytes.length >= 10) {
                        String firstTenBytesString = new String(commentBytes, 0, 10);

                        // try each encoding name
                        for (Map.Entry<String, String> pair : encodingMap.entrySet()) {
                            String encodingName = pair.getKey();
                            String charset = pair.getValue();
                            if (firstTenBytesString.startsWith(encodingName)) {
                                // skip any null or blank characters commonly present after the encoding name, up to a limit of 10 from the start
                                for (int j = encodingName.length(); j < 10; j++) {
                                    byte b = commentBytes[j];
                                    if (b != '\0' && b != ' ')
                                        return new String(commentBytes, j, commentBytes.length - j, charset).trim();
                                }
                                return new String(commentBytes, 10, commentBytes.length - 10, charset).trim();
                            }
                        }
                    }
                    // special handling fell through, return a plain string representation
                    return new String(commentBytes, System.getProperty("file.encoding")).trim();
                } catch (UnsupportedEncodingException ex) {
                    return null;
                }
            }
        },

    TAG_SUBSECOND_TIME(0x9290, "Sub-Sec Time"),
    TAG_SUBSECOND_TIME_ORIGINAL(0x9291, "Sub-Sec Time Original"),
    TAG_SUBSECOND_TIME_DIGITIZED(0x9292, "Sub-Sec Time Digitized"),

    /** The image title, as used by Windows XP. */
    TAG_WIN_TITLE(0x9C9B, "Windows XP Title")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getUnicodeDescription(this);
            }
        },
    /** The image comment, as used by Windows XP. */
    TAG_WIN_COMMENT(0x9C9C, "Windows XP Comment")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getUnicodeDescription(this);
            }
        },
    /** The image author, as used by Windows XP (called Artist in the Windows shell). */
    TAG_WIN_AUTHOR(0x9C9D, "Windows XP Author")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getUnicodeDescription(this);
            }
        },
    /** The image keywords, as used by Windows XP. */
    TAG_WIN_KEYWORDS(0x9C9E, "Windows XP Keywords")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getUnicodeDescription(this);
            }
        },
    /** The image subject, as used by Windows XP. */
    TAG_WIN_SUBJECT(0x9C9F, "Windows XP Subject")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getUnicodeDescription(this);
            }
        },

    TAG_FLASHPIX_VERSION(0xA000, "FlashPix Version")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getVersionBytesDescription(TAG_FLASHPIX_VERSION, 2);
            }
        },
    TAG_COLOR_SPACE(0xA001, "Color Space")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                final Integer value = directory.getInteger(TAG_COLOR_SPACE);
                if (value == null)
                    return null;
                if (value == 1)
                    return "sRGB";
                if (value == 65535)
                    return "Undefined";
                return "Unknown (" + value + ")";
            }
        },
    TAG_EXIF_IMAGE_WIDTH(0xA002, "Exif Image Width")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                final Integer value = directory.getInteger(TAG_EXIF_IMAGE_WIDTH);
                return value == null ? null : value + " pixels";
            }
        },
    TAG_EXIF_IMAGE_HEIGHT(0xA003, "Exif Image Height")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                final Integer value = directory.getInteger(TAG_EXIF_IMAGE_HEIGHT);
                return value == null ? null : value + " pixels";
            }
        },
    TAG_RELATED_SOUND_FILE(0xA004, "Related Sound File"),
    TAG_FLASH_ENERGY(0xA20B, "Flash Energy"),
    TAG_SPATIAL_FREQ_RESPONSE(0xA20C, "Spatial Frequency Response"),
    TAG_FOCAL_PLANE_X_RESOLUTION(0xA20E, "Focal Plane X Resolution")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational rational = directory.getRational(TAG_FOCAL_PLANE_X_RESOLUTION);
                if (rational == null)
                    return null;
                final String unit = TAG_FOCAL_PLANE_RESOLUTION_UNIT.getDescription(directory);
                return rational.getReciprocal().toSimpleString(_allowDecimalRepresentationOfRationals)
                    + (unit == null ? "" : " " + unit.toLowerCase());
            }
        },
    TAG_FOCAL_PLANE_Y_RESOLUTION(0xA20F, "Focal Plane Y Resolution")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational rational = directory.getRational(TAG_FOCAL_PLANE_Y_RESOLUTION);
                if (rational == null)
                    return null;
                final String unit = TAG_FOCAL_PLANE_RESOLUTION_UNIT.getDescription(directory);
                return rational.getReciprocal().toSimpleString(_allowDecimalRepresentationOfRationals)
                    + (unit == null ? "" : " " + unit.toLowerCase());
            }
        },
    /**
     * Unit of FocalPlaneXResolution/FocalPlaneYResolution. '1' means no-unit,
     * '2' inch, '3' centimeter.
     *
     * Note: Some of Fujifilm's digicam(e.g.FX2700,FX2900,Finepix4700Z/40i etc)
     * uses value '3' so it must be 'centimeter', but it seems that they use a
     * '8.3mm?'(1/3in.?) to their ResolutionUnit. Fuji's BUG? Finepix4900Z has
     * been changed to use value '2' but it doesn't match to actual value also.
     */
    TAG_FOCAL_PLANE_RESOLUTION_UNIT(0xA210, "Focal Plane Resolution Unit")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // Unit of FocalPlaneXResolution/FocalPlaneYResolution.
                // '1' means no-unit, '2' inch, '3' centimeter.
                return directory.getIndexedDescription(TAG_FOCAL_PLANE_RESOLUTION_UNIT,
                    1,
                    "(No unit)",
                    "Inches",
                    "cm"
                );
            }
        },
    TAG_SUBJECT_LOCATION(0xA214, "Subject Location"),
    TAG_EXPOSURE_INDEX(0xA215, "Exposure Index"),
    TAG_SENSING_METHOD(0xA217, "Sensing Method")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                // '1' Not defined, '2' One-chip color area sensor, '3' Two-chip color area sensor
                // '4' Three-chip color area sensor, '5' Color sequential area sensor
                // '7' Trilinear sensor '8' Color sequential linear sensor,  'Other' reserved
                return directory.getIndexedDescription(TAG_SENSING_METHOD,
                    1,
                    "(Not defined)",
                    "One-chip color area sensor",
                    "Two-chip color area sensor",
                    "Three-chip color area sensor",
                    "Color sequential area sensor",
                    null,
                    "Trilinear sensor",
                    "Color sequential linear sensor"
                );
            }
        },

    TAG_FILE_SOURCE(0xA300, "File Source")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_FILE_SOURCE,
                    1,
                    "Film Scanner",
                    "Reflection Print Scanner",
                    "Digital Still Camera (DSC)"
                );
            }
        },
    TAG_SCENE_TYPE(0xA301, "Scene Type")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                //TODO: wtf?
                return directory.getIndexedDescription(TAG_SCENE_TYPE,
                    1,
                    "Directly photographed image"
                );
            }
        },
    TAG_CFA_PATTERN(0xA302, "CFA Pattern")
        {
            /// <summary>
            /// String description of CFA Pattern
            /// </summary>
            /// <remarks>
            /// Converted from Exiftool version 10.33 created by Phil Harvey
            /// http://www.sno.phy.queensu.ca/~phil/exiftool/
            /// lib\Image\ExifTool\Exif.pm
            ///
            /// Indicates the color filter array (CFA) geometric pattern of the image sensor when a one-chip color area sensor is used.
            /// It does not apply to all sensing methods.
            /// </remarks>
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return formatCFAPattern(directory.decodeCfaPattern(TAG_CFA_PATTERN));
            }
        },

    /**
     * This tag indicates the use of special processing on image data, such as rendering
     * geared to output. When special processing is performed, the reader is expected to
     * disable or minimize any further processing.
     * Tag = 41985 (A401.H)
     * Type = SHORT
     * Count = 1
     * Default = 0
     *   0 = Normal process
     *   1 = Custom process
     *   Other = reserved
     */
    TAG_CUSTOM_RENDERED(0xA401, "Custom Rendered")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_CUSTOM_RENDERED, 0,
                    "Normal process",
                    "Custom process"
                );
            }
        },
    /**
     * This tag indicates the exposure mode set when the image was shot. In auto-bracketing
     * mode, the camera shoots a series of frames of the same scene at different exposure settings.
     * Tag = 41986 (A402.H)
     * Type = SHORT
     * Count = 1
     * Default = none
     *   0 = Auto exposure
     *   1 = Manual exposure
     *   2 = Auto bracket
     *   Other = reserved
     */
    TAG_EXPOSURE_MODE(0xA402, "Exposure Mode")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_EXPOSURE_MODE, 0,
                    "Auto exposure",
                    "Manual exposure",
                    "Auto bracket"
                );
            }
        },
    /**
     * This tag indicates the white balance mode set when the image was shot.
     * Tag = 41987 (A403.H)
     * Type = SHORT
     * Count = 1
     * Default = none
     *   0 = Auto white balance
     *   1 = Manual white balance
     *   Other = reserved
     */
    TAG_WHITE_BALANCE_MODE(0xA403, "White Balance Mode")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_WHITE_BALANCE_MODE, 0,
                    "Auto white balance",
                    "Manual white balance"
                );
            }
        },
    /**
     * This tag indicates the digital zoom ratio when the image was shot. If the
     * numerator of the recorded value is 0, this indicates that digital zoom was
     * not used.
     * Tag = 41988 (A404.H)
     * Type = RATIONAL
     * Count = 1
     * Default = none
     */
    TAG_DIGITAL_ZOOM_RATIO(0xA404, "Digital Zoom Ratio")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Rational value = directory.getRational(TAG_DIGITAL_ZOOM_RATIO);
                return value == null
                    ? null
                    : value.getNumerator() == 0
                    ? "Digital zoom not used"
                    : new DecimalFormat("0.#").format(value.doubleValue());
            }
        },
    /**
     * This tag indicates the equivalent focal length assuming a 35mm film camera,
     * in mm. A value of 0 means the focal length is unknown. Note that this tag
     * differs from the FocalLength tag.
     * Tag = 41989 (A405.H)
     * Type = SHORT
     * Count = 1
     * Default = none
     */
    TAG_35MM_FILM_EQUIV_FOCAL_LENGTH(0xA405, "Focal Length 35")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                Integer value = directory.getInteger(TAG_35MM_FILM_EQUIV_FOCAL_LENGTH);
                return value == null
                    ? null
                    : value == 0
                    ? "Unknown"
                    : DescriptionUtil.getFocalLengthDescription(value);
            }
        },
    /**
     * This tag indicates the type of scene that was shot. It can also be used to
     * record the mode in which the image was shot. Note that this differs from
     * the scene type (SceneType) tag.
     * Tag = 41990 (A406.H)
     * Type = SHORT
     * Count = 1
     * Default = 0
     *   0 = Standard
     *   1 = Landscape
     *   2 = Portrait
     *   3 = Night scene
     *   Other = reserved
     */
    TAG_SCENE_CAPTURE_TYPE(0xA406, "Scene Capture Type")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_SCENE_CAPTURE_TYPE, 0,
                    "Standard",
                    "Landscape",
                    "Portrait",
                    "Night scene"
                );
            }
        },
    /**
     * This tag indicates the degree of overall image gain adjustment.
     * Tag = 41991 (A407.H)
     * Type = SHORT
     * Count = 1
     * Default = none
     *   0 = None
     *   1 = Low gain up
     *   2 = High gain up
     *   3 = Low gain down
     *   4 = High gain down
     *   Other = reserved
     */
    TAG_GAIN_CONTROL(0xA407, "Gain Control")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_GAIN_CONTROL, 0,
                    "None",
                    "Low gain up",
                    "Low gain down",
                    "High gain up",
                    "High gain down"
                );
            }
        },
    /**
     * This tag indicates the direction of contrast processing applied by the camera
     * when the image was shot.
     * Tag = 41992 (A408.H)
     * Type = SHORT
     * Count = 1
     * Default = 0
     *   0 = Normal
     *   1 = Soft
     *   2 = Hard
     *   Other = reserved
     */
    TAG_CONTRAST(0xA408, "Contrast")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_CONTRAST, 0,
                    "None",
                    "Soft",
                    "Hard"
                );
            }
        },
    /**
     * This tag indicates the direction of saturation processing applied by the camera
     * when the image was shot.
     * Tag = 41993 (A409.H)
     * Type = SHORT
     * Count = 1
     * Default = 0
     *   0 = Normal
     *   1 = Low saturation
     *   2 = High saturation
     *   Other = reserved
     */
    TAG_SATURATION(0xA409, "Saturation")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_SATURATION, 0,
                    "None",
                    "Low saturation",
                    "High saturation"
                );
            }
        },
    /**
     * This tag indicates the direction of sharpness processing applied by the camera
     * when the image was shot.
     * Tag = 41994 (A40A.H)
     * Type = SHORT
     * Count = 1
     * Default = 0
     *   0 = Normal
     *   1 = Soft
     *   2 = Hard
     *   Other = reserved
     */
    TAG_SHARPNESS(0xA40A, "Sharpness")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_SHARPNESS, 0,
                    "None",
                    "Low",
                    "Hard"
                );
            }
        },
    /**
     * This tag indicates information on the picture-taking conditions of a particular
     * camera model. The tag is used only to indicate the picture-taking conditions in
     * the reader.
     * Tag = 41995 (A40B.H)
     * Type = UNDEFINED
     * Count = Any
     * Default = none
     *
     * The information is recorded in the format shown below. The data is recorded
     * in Unicode using SHORT type for the number of display rows and columns and
     * UNDEFINED type for the camera settings. The Unicode (UCS-2) string including
     * Signature is NULL terminated. The specifics of the Unicode string are as given
     * in ISO/IEC 10464-1.
     *
     *      Length  Type        Meaning
     *      ------+-----------+------------------
     *      2       SHORT       Display columns
     *      2       SHORT       Display rows
     *      Any     UNDEFINED   Camera setting-1
     *      Any     UNDEFINED   Camera setting-2
     *      :       :           :
     *      Any     UNDEFINED   Camera setting-n
     */
    TAG_DEVICE_SETTING_DESCRIPTION(0xA40B, "Device Setting Description"),   //TODO: Notes, but no handler
    /**
     * This tag indicates the distance to the subject.
     * Tag = 41996 (A40C.H)
     * Type = SHORT
     * Count = 1
     * Default = none
     *   0 = unknown
     *   1 = Macro
     *   2 = Close view
     *   3 = Distant view
     *   Other = reserved
     */
    TAG_SUBJECT_DISTANCE_RANGE(0xA40C, "Subject Distance Range")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getIndexedDescription(TAG_SUBJECT_DISTANCE_RANGE, 0,
                    "Unknown",
                    "Macro",
                    "Close view",
                    "Distant view"
                );
            }
        },

    /**
     * This tag indicates an identifier assigned uniquely to each image. It is
     * recorded as an ASCII string equivalent to hexadecimal notation and 128-bit
     * fixed length.
     * Tag = 42016 (A420.H)
     * Type = ASCII
     * Count = 33
     * Default = none
     */
    TAG_IMAGE_UNIQUE_ID(0xA420, "Unique Image ID"),
    /** String. */
    TAG_CAMERA_OWNER_NAME(0xA430, "Camera Owner Name"),
    /** String. */
    TAG_BODY_SERIAL_NUMBER(0xA431, "Body Serial Number"),
    /** An array of four Rational64u numbers giving focal and aperture ranges. */
    TAG_LENS_SPECIFICATION(0xA432, "Lens Specification")
        {
            @Override
            public String getDescription(DirectoryBase directory)
            {
                return directory.getLensSpecificationDescription(TAG_LENS_SPECIFICATION);
            }
        },
    /** String. */
    TAG_LENS_MAKE(0xA433, "Lens Make"),
    /** String. */
    TAG_LENS_MODEL(0xA434, "Lens Model"),
    /** String. */
    TAG_LENS_SERIAL_NUMBER(0xA435, "Lens Serial Number"),
    /** Rational64u. */
    TAG_GAMMA(0xA500, "Gamma"),
    TAG_PRINT_IMAGE_MATCHING_INFO(0xC4A5, "Print Image Matching (PIM) Info"),
    TAG_PANASONIC_TITLE(0xC6D2, "Panasonic Title"),
    TAG_PANASONIC_TITLE_2(0xC6D3, "Panasonic Title (2)"),
    TAG_PADDING(0xEA1C, "Padding"),
    TAG_LENS(0xFDEA, "Lens");


/*    //TODO: Use a sparse array trie, or FastUtil
    private static final Map<Integer, ExifKeys> lookup = new HashMap<Integer, ExifKeys>();
    static {
        for (ExifKeys type : values())
            lookup.put(type.getValue(), type);
    }*/

    private final int key;
    private final String summary;
    ExifKeys(int key, String summary)
    {
        this.key = key;
        this.summary = summary;
    }

    public final Integer getValue()
    {
        return key;
    }

/*    @Nullable
    public static ExifKeys fromValue(Integer value)
    {
        return lookup.get(value);
    }*/

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
     * @param directory storing the values to describe
     * @return
     */
    @Override
    public String getDescription(DirectoryBase directory)
    {
        return directory.getDescription(this);
    }

    /**
     * Dictates whether rational values will be represented in decimal format in instances
     * where decimal notation is elegant (such as 1/2 -> 0.5, but not 1/3).
     */
    private static final boolean _allowDecimalRepresentationOfRationals = true;

    @Nullable
    private static String formatCFAPattern(@Nullable int[] pattern)
    {
        if (pattern == null)
            return null;
        if (pattern.length < 2)
            return "<truncated data>";
        if (pattern[0] == 0 && pattern[1] == 0)
            return "<zero pattern size>";

        int end = 2 + pattern[0] * pattern[1];
        if (end > pattern.length)
            return "<invalid pattern size>";

        String[] cfaColors = { "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "White" };

        StringBuilder ret = new StringBuilder();
        ret.append("[");
        for (int pos = 2; pos < end; pos++)
        {
            if (pattern[pos] <= cfaColors.length - 1)
                ret.append(cfaColors[pattern[pos]]);
            else
                ret.append("Unknown");      // indicated pattern position is outside the array bounds

            if ((pos - 2) % pattern[1] == 0)
                ret.append(",");
            else if(pos != end - 1)
                ret.append("][");
        }
        ret.append("]");

        return ret.toString();
    }
}
