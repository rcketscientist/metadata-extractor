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

import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.DirectoryBase;
import com.drew.metadata.IntegerKey;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for several Exif format tag directories.
 *
 * @author Drew Noakes https://drewnoakes.com
 */
@SuppressWarnings("WeakerAccess")
public abstract class ExifDirectoryBase extends DirectoryBase<Integer, ExifDirectoryBase.Keys>
{
    private ExifDirectoryBase(){ super(Keys.class);}
    public enum Keys implements IntegerKey
    {
        TAG_INTEROP_INDEX(      0x0001, "Interoperability Index"),
        TAG_INTEROP_VERSION(    0x0002, "Interoperability Version"),

        /**
         * The new subfile type tag.
         * 0 = Full-resolution Image
         * 1 = Reduced-resolution image
         * 2 = Single page of multi-page image
         * 3 = Single page of multi-page reduced-resolution image
         * 4 = Transparency mask
         * 5 = Transparency mask of reduced-resolution image
         * 6 = Transparency mask of multi-page image
         * 7 = Transparency mask of reduced-resolution multi-page image
         */
        TAG_NEW_SUBFILE_TYPE(   0x00FE, "New Subfile Type"),
        /**
         * The old subfile type tag.
         * 1 = Full-resolution image (Main image)
         * 2 = Reduced-resolution image (Thumbnail)
         * 3 = Single page of multi-page image
         */
        TAG_SUBFILE_TYPE(0x00FF, "Subfile Type"),

        TAG_IMAGE_WIDTH(0x0100, "Image Width"),
        TAG_IMAGE_HEIGHT(0x0101, "Image Height"),

        /**
         * When image format is no compression, this value shows the number of bits
         * per component for each pixel. Usually this value is '8,8,8'.
         */
        TAG_BITS_PER_SAMPLE(0x0102, "Bits Per Sample"),
        TAG_COMPRESSION(0x0103, "Compression"),

        /**
         * Shows the color space of the image data components.
         * 0 = WhiteIsZero
         * 1 = BlackIsZero
         * 2 = RGB
         * 3 = RGB Palette
         * 4 = Transparency Mask
         * 5 = CMYK
         * 6 = YCbCr
         * 8 = CIELab
         * 9 = ICCLab
         * 10 = ITULab
         * 32803 = Color Filter Array
         * 32844 = Pixar LogL
         * 32845 = Pixar LogLuv
         * 34892 = Linear Raw
         */
        TAG_PHOTOMETRIC_INTERPRETATION(0x0106, "Photometric Interpretation"),

        /**
         * 1 = No dithering or halftoning
         * 2 = Ordered dither or halftone
         * 3 = Randomized dither
         */
        TAG_THRESHOLDING(0x0107, "Thresholding"),

        /**
         * 1 = Normal
         * 2 = Reversed
         */
        TAG_FILL_ORDER(0x010A, "Fill Order"),
        TAG_DOCUMENT_NAME(0x010D, "Document Name"),

        TAG_IMAGE_DESCRIPTION(0x010E, "Image Description"),

        TAG_MAKE(0x010F, "Make"),
        TAG_MODEL(0x0110, "Model"),
        /** The position in the file of raster data. */
        TAG_STRIP_OFFSETS(0x0111, "Strip Offsets"),
        TAG_ORIENTATION(0x0112, "Orientation"),
        /** Each pixel is composed of this many samples. */
        TAG_SAMPLES_PER_PIXEL(0x0115, "Samples Per Pixel"),
        /** The raster is codified by a single block of data holding this many rows. */
        TAG_ROWS_PER_STRIP(0x0116, "Rows Per Strip"),
        /** The size of the raster data in bytes. */
        TAG_STRIP_BYTE_COUNTS(0x0117, "Strip Byte Counts"),
        TAG_MIN_SAMPLE_VALUE(0x0118, "Minimum Sample Value"),
        TAG_MAX_SAMPLE_VALUE(0x0119, "Maximum Sample Value"),
        TAG_X_RESOLUTION(0x011A, "X Resolution"),
        TAG_Y_RESOLUTION(0x011B, "Y Resolution"),
        /**
         * When image format is no compression YCbCr, this value shows byte aligns of
         * YCbCr data. If value is '1', Y/Cb/Cr value is chunky format, contiguous for
         * each subsampling pixel. If value is '2', Y/Cb/Cr value is separated and
         * stored to Y plane/Cb plane/Cr plane format.
         */
        TAG_PLANAR_CONFIGURATION(0x011C, "Planar Configuration"),
        TAG_PAGE_NAME(0x011D, "Page Name"),

        TAG_RESOLUTION_UNIT(0x0128, "Resolution Unit"),
        TAG_PAGE_NUMBER(0x0129, "Page Number"),

        TAG_TRANSFER_FUNCTION(0x012D, "Transfer Function"),
        TAG_SOFTWARE(0x0131, "Software"),
        TAG_DATETIME(0x0132, "Date/Time"),
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
        TAG_JPEG_PROC(0x0200, "JPEG Proc"),

        // 0x0201 can have all kinds of descriptions for thumbnail starting index
        // 0x0202 can have all kinds of descriptions for thumbnail length
        TAG_JPEG_RESTART_INTERVAL(0x0203, "JPEG Restart Interval"),
        TAG_JPEG_LOSSLESS_PREDICTORS(0x0205, "JPEG Lossless Predictors"),
        TAG_JPEG_POINT_TRANSFORMS(0x0206, "JPEG Point Transforms"),
        TAG_JPEG_Q_TABLES(0x0207, "JPEGQ Tables"),
        TAG_JPEG_DC_TABLES(0x0208, "JPEGDC Tables"),
        TAG_JPEG_AC_TABLES(0x0209, "JPEGAC Tables"),

        TAG_YCBCR_COEFFICIENTS(0x0211, "YCbCr Coefficients"),
        TAG_YCBCR_SUBSAMPLING(0x0212, "YCbCr Sub-Sampling"),
        TAG_YCBCR_POSITIONING(0x0213, "YCbCr Positioning"),
        TAG_REFERENCE_BLACK_WHITE(0x0214, "Reference Black/White"),
        TAG_STRIP_ROW_COUNTS(0x022f, "Strip Row Counts"),
        TAG_APPLICATION_NOTES(0x02bc, "Application Notes"),

        TAG_RELATED_IMAGE_FILE_FORMAT(0x1000, "Related Image File Format"),
        TAG_RELATED_IMAGE_WIDTH(0x1001, "Related Image Width"),
        TAG_RELATED_IMAGE_HEIGHT(0x1002, "Related Image Height"),

        TAG_RATING(0x4746, "Rating"),

        TAG_CFA_REPEAT_PATTERN_DIM(0x828D, "CFA Repeat Pattern Dim"),
        /** There are two definitions for CFA pattern, I don't know the difference... */
        TAG_CFA_PATTERN_2(0x828E, "CFA Pattern"),
        TAG_BATTERY_LEVEL(0x828F, "Battery Level"),
        TAG_COPYRIGHT(0x8298, "Copyright"),
        /**
         * Exposure time (reciprocal of shutter speed). Unit is second.
         */
        TAG_EXPOSURE_TIME(0x829A, "Exposure Time"),
        /**
         * The actual F-number(F-stop) of lens when the image was taken.
         */
        TAG_FNUMBER(0x829D, "F-Number"),
        TAG_IPTC_NAA(0x83BB, "IPTC/NAA"),
        TAG_INTER_COLOR_PROFILE(0x8773, "Inter Color Profile"),
        /**
         * Exposure program that the camera used when image was taken. '1' means
         * manual control, '2' program normal, '3' aperture priority, '4' shutter
         * priority, '5' program creative (slow program), '6' program action
         * (high-speed program), '7' portrait mode, '8' landscape mode.
         */
        TAG_EXPOSURE_PROGRAM(0x8822, "Exposure Program"),
        TAG_SPECTRAL_SENSITIVITY(0x8824, "Spectral Sensitivity"),
        TAG_ISO_EQUIVALENT(0x8827, "ISO Speed Ratings"),
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
        /**
         * Applies to ISO tag.
         *
         * 0 = Unknown
         * 1 = Standard Output Sensitivity
         * 2 = Recommended Exposure Index
         * 3 = ISO Speed
         * 4 = Standard Output Sensitivity and Recommended Exposure Index
         * 5 = Standard Output Sensitivity and ISO Speed
         * 6 = Recommended Exposure Index and ISO Speed
         * 7 = Standard Output Sensitivity, Recommended Exposure Index and ISO Speed
         */
        TAG_SENSITIVITY_TYPE(0x8830, "Sensitivity Type"),
        TAG_STANDARD_OUTPUT_SENSITIVITY(0x8831, "Standard Output Sensitivity"),
        TAG_RECOMMENDED_EXPOSURE_INDEX(0x8832, "Recommended Exposure Index"),
        /** Non-standard, but in use. */
        TAG_TIME_ZONE_OFFSET(0x882A, "Time Zone Offset"),
        TAG_SELF_TIMER_MODE(0x882B, "Self Timer Mode"),

        TAG_EXIF_VERSION(0x9000, "Exif Version"),
        TAG_DATETIME_ORIGINAL(0x9003, "Date/Time Original"),
        TAG_DATETIME_DIGITIZED(0x9004, "Date/Time Digitized"),

        TAG_COMPONENTS_CONFIGURATION(0x9101, "Components Configuration"),
        /**
         * Average (rough estimate) compression level in JPEG bits per pixel.
         * */
        TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL(0x9102, "Compressed Bits Per Pixel"),

        /**
         * Shutter speed by APEX value. To convert this value to ordinary 'Shutter Speed';
         * calculate this value's power of 2, then reciprocal. For example, if the
         * ShutterSpeedValue is '4', shutter speed is 1/(24)=1/16 second.
         */
        TAG_SHUTTER_SPEED(0x9201, "Shutter Speed Value"),
        /**
         * The actual aperture value of lens when the image was taken. Unit is APEX.
         * To convert this value to ordinary F-number (F-stop), calculate this value's
         * power of root 2 (=1.4142). For example, if the ApertureValue is '5',
         * F-number is 1.4142^5 = F5.6.
         */
        TAG_APERTURE(0x9202, "Aperture Value"),
        TAG_BRIGHTNESS_VALUE(0x9203, "Brightness Value"),
        TAG_EXPOSURE_BIAS(0x9204, "Exposure Bias Value"),
        /**
         * Maximum aperture value of lens. You can convert to F-number by calculating
         * power of root 2 (same process of ApertureValue:0x9202).
         * The actual aperture value of lens when the image was taken. To convert this
         * value to ordinary f-number(f-stop), calculate the value's power of root 2
         * (=1.4142). For example, if the ApertureValue is '5', f-number is 1.41425^5 = F5.6.
         */
        TAG_MAX_APERTURE(0x9205, "Max Aperture Value"),
        /**
         * Indicates the distance the autofocus camera is focused to.  Tends to be less accurate as distance increases.
         */
        TAG_SUBJECT_DISTANCE(0x9206, "Subject Distance"),
        /**
         * Exposure metering method. '0' means unknown, '1' average, '2' center
         * weighted average, '3' spot, '4' multi-spot, '5' multi-segment, '6' partial,
         * '255' other.
         */
        TAG_METERING_MODE(0x9207, "Metering Mode"),

        /**
         * @deprecated use {@link com.drew.metadata.exif.ExifDirectoryBase.Keys#TAG_WHITE_BALANCE} instead.
         */
        @Deprecated
        TAG_LIGHT_SOURCE(0x9208, "(Deprecated) Use White Balance"),
        /**
         * White balance (aka light source). '0' means unknown, '1' daylight,
         * '2' fluorescent, '3' tungsten, '10' flash, '17' standard light A,
         * '18' standard light B, '19' standard light C, '20' D55, '21' D65,
         * '22' D75, '255' other.
         */
        TAG_WHITE_BALANCE(0x9208, "White Balance"),
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
        TAG_FLASH(0x9209, "Flash"),
        /**
         * Focal length of lens used to take image.  Unit is millimeter.
         * Nice digital cameras actually save the focal length as a function of how far they are zoomed in.
         */
        TAG_FOCAL_LENGTH(0x920A, "Focal Length"),

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
        TAG_MAKERNOTE(0x927C, "Makernote"),

        TAG_USER_COMMENT(0x9286, "User Comment"),

        TAG_SUBSECOND_TIME(0x9290, "Sub-Sec Time"),
        TAG_SUBSECOND_TIME_ORIGINAL(0x9291, "Sub-Sec Time Original"),
        TAG_SUBSECOND_TIME_DIGITIZED(0x9292, "Sub-Sec Time Digitized"),

        /** The image title, as used by Windows XP. */
        TAG_WIN_TITLE(0x9C9B, "Windows XP Title"),
        /** The image comment, as used by Windows XP. */
        TAG_WIN_COMMENT(0x9C9C, "Windows XP Comment"),
        /** The image author, as used by Windows XP (called Artist in the Windows shell). */
        TAG_WIN_AUTHOR(0x9C9D, "Windows XP Author"),
        /** The image keywords, as used by Windows XP. */
        TAG_WIN_KEYWORDS(0x9C9E, "Windows XP Keywords"),
        /** The image subject, as used by Windows XP. */
        TAG_WIN_SUBJECT(0x9C9F, "Windows XP Subject"),

        TAG_FLASHPIX_VERSION(0xA000, "FlashPix Version"),
        /**
         * Defines Color Space. DCF image must use sRGB color space so value is
         * always '1'. If the picture uses the other color space, value is
         * '65535':Uncalibrated.
         */
        TAG_COLOR_SPACE(0xA001, "Color Space"),
        TAG_EXIF_IMAGE_WIDTH(0xA002, "Exif Image Width"),
        TAG_EXIF_IMAGE_HEIGHT(0xA003, "Exif Image Height"),
        TAG_RELATED_SOUND_FILE(0xA004, "Related Sound File"),

        TAG_FLASH_ENERGY(0xA20B, "Flash Energy"),
        TAG_SPATIAL_FREQ_RESPONSE(0xA20C, "Spatial Frequency Response"),
        TAG_FOCAL_PLANE_X_RESOLUTION(0xA20E, "Focal Plane X Resolution"),
        TAG_FOCAL_PLANE_Y_RESOLUTION(0xA20F, "Focal Plane Y Resolution"),
        /**
         * Unit of FocalPlaneXResolution/FocalPlaneYResolution. '1' means no-unit,
         * '2' inch, '3' centimeter.
         *
         * Note: Some of Fujifilm's digicam(e.g.FX2700,FX2900,Finepix4700Z/40i etc)
         * uses value '3' so it must be 'centimeter', but it seems that they use a
         * '8.3mm?'(1/3in.?) to their ResolutionUnit. Fuji's BUG? Finepix4900Z has
         * been changed to use value '2' but it doesn't match to actual value also.
         */
        TAG_FOCAL_PLANE_RESOLUTION_UNIT(0xA210, "Focal Plane Resolution Unit"),
        TAG_SUBJECT_LOCATION(0xA214, "Subject Location"),
        TAG_EXPOSURE_INDEX(0xA215, "Exposure Index"),
        TAG_SENSING_METHOD(0xA217, "Sensing Method"),

        TAG_FILE_SOURCE(0xA300, "File Source"),
        TAG_SCENE_TYPE(0xA301, "Scene Type"),
        TAG_CFA_PATTERN(0xA302, "CFA Pattern"),

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
        TAG_CUSTOM_RENDERED(0xA401, "Custom Rendered"),
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
        TAG_EXPOSURE_MODE(0xA402, "Exposure Mode"),
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
        TAG_WHITE_BALANCE_MODE(0xA403, "White Balance Mode"),
        /**
         * This tag indicates the digital zoom ratio when the image was shot. If the
         * numerator of the recorded value is 0, this indicates that digital zoom was
         * not used.
         * Tag = 41988 (A404.H)
         * Type = RATIONAL
         * Count = 1
         * Default = none
         */
        TAG_DIGITAL_ZOOM_RATIO(0xA404, "Digital Zoom Ratio"),
        /**
         * This tag indicates the equivalent focal length assuming a 35mm film camera,
         * in mm. A value of 0 means the focal length is unknown. Note that this tag
         * differs from the FocalLength tag.
         * Tag = 41989 (A405.H)
         * Type = SHORT
         * Count = 1
         * Default = none
         */
        TAG_35MM_FILM_EQUIV_FOCAL_LENGTH(0xA405, "Focal Length 35"),
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
        TAG_SCENE_CAPTURE_TYPE(0xA406, "Scene Capture Type"),
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
        TAG_GAIN_CONTROL(0xA407, "Gain Control"),
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
        TAG_CONTRAST(0xA408, "Contrast"),
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
        TAG_SATURATION(0xA409, "Saturation"),
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
        TAG_SHARPNESS(0xA40A, "Sharpness"),
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
        TAG_DEVICE_SETTING_DESCRIPTION(0xA40B, "Device Setting Description"),
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
        TAG_SUBJECT_DISTANCE_RANGE(0xA40C, "Subject Distance Range"),

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
        TAG_LENS_SPECIFICATION(0xA432, "Lens Specification"),
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


        //TODO: Use a sparse array trie, or FastUtil
        private static final Map<Integer, Keys> lookup = new HashMap<Integer, Keys>();
        static {
            for (Keys type : values())
                lookup.put(type.getValue(), type);
        }

        private final int key;
        private final String description;
        Keys(int key, String description)
        {
            this.key = key;
            this.description = description;
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

        @Override
        public String getTagName()
        {
            return name();
        }

        @Override
        public String getTagType()
        {
            return Integer.toString(key);
        }

        @Override
        public String getDescription()
        {
            return description;
        }
    }

    //TODO: Subclasses need to handle their own keys and these keys
//    protected static void addExifTagNames(HashMap<Integer, String> map)
//    {
//    }
}
