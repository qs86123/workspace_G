//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.common;

public enum ImageFormat {
    JPEG,
    TIFF,
    PNG,
    BMP,
    GIF,
    ICO,
    RAW,
    PSD,
    SGI,
    TGA,
    PNM,
    WBMP,
    UNKNOWN;

    private ImageFormat() {
    }

    public static ImageFormat getImageFormat(String suffix) {
        return "JPEG".equalsIgnoreCase(suffix)?JPEG:("JPG".equalsIgnoreCase(suffix)?JPEG:("BMP".equalsIgnoreCase(suffix)?BMP:("GIF".equalsIgnoreCase(suffix)?GIF:("PNG".equalsIgnoreCase(suffix)?PNG:("TIFF".equalsIgnoreCase(suffix)?TIFF:("ICO".equalsIgnoreCase(suffix)?ICO:("SGI".equalsIgnoreCase(suffix)?SGI:("TGA".equalsIgnoreCase(suffix)?TGA:("PSD".equalsIgnoreCase(suffix)?PSD:("PNM".equalsIgnoreCase(suffix)?PNM:("WBMP".equalsIgnoreCase(suffix)?WBMP:UNKNOWN)))))))))));
    }

    public static String getDesc(ImageFormat format) {
        return JPEG == format?"JPEG":(BMP == format?"BMP":(GIF == format?"GIF":(ICO == format?"ICO":(TGA == format?"TGA":(SGI == format?"SGI":(PNG == format?"PNG":(TIFF == format?"TIFF":(PSD == format?"PSD":(PNM == format?"PNM":"UNKNOWN")))))))));
    }

    public static boolean isSouport(String suffix) {
        ImageFormat format = getImageFormat(suffix);
        return JPEG == format || BMP == format || GIF == format || TIFF == format || PNG == format || TGA == format || SGI == format || ICO == format || PSD == format || PNM == format || WBMP == format;
    }

    public String getDesc() {
        return getDesc(this);
    }
}
