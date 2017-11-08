//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool;

import clive.hua.app.simpleImageTool.common.ImageFormat;
import clive.hua.app.simpleImageTool.common.ImageWrapper;
import clive.hua.app.simpleImageTool.common.ImageWriteHelper;
import clive.hua.app.simpleImageTool.common.WatermarkParameter;
import clive.hua.app.simpleImageTool.common.WriteParameter;
import clive.hua.app.simpleImageTool.exception.MyImageException;
import clive.hua.app.simpleImageTool.gif.GifUtil;
import clive.hua.app.simpleImageTool.util.ImageUtils;
import com.alibaba.simpleimage.io.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class SimpleImageTool {
    public static final int Max_WIDTH = 20000;
    public static final int Max_HEIGHTH = 20000;
    private BufferedInputStream imageStream;
    private File outFile;
    private double degree;
    private double scale;
    private int width;
    private int height;
    private boolean gray;
    private boolean cutModel;
    private Color bgcolor;
    private boolean lockScale;
    private boolean force;
    private WriteParameter writeParam;
    private ArrayList<WatermarkParameter> watermarkList;
    private int waterMinWidth;
    private int waterMinHeight;

    private void init() {
        this.outFile = null;
        this.imageStream = null;
        this.degree = 0.0D;
        this.scale = 0.0D;
        this.width = 0;
        this.height = 0;
        this.gray = false;
        this.lockScale = true;
        this.force = false;
        this.cutModel = false;
        this.bgcolor = null;
        this.writeParam = new WriteParameter();
        this.watermarkList = new ArrayList();
        this.waterMinWidth = 0;
        this.waterMinHeight = 0;
    }

    public SimpleImageTool(InputStream imageStream) {
        this.init();
        this.imageStream = new BufferedInputStream(imageStream);
    }

    public SimpleImageTool() {
        this.init();
    }

    public SimpleImageTool lockScale(boolean lockScale) {
        this.lockScale = lockScale;
        return this;
    }

    public SimpleImageTool writeParam(WriteParameter writeParam) {
        this.writeParam = writeParam;
        return this;
    }

    public SimpleImageTool cutModel(boolean cutModel) {
        this.cutModel = cutModel;
        return this;
    }

    public SimpleImageTool watermark(WatermarkParameter watermark) {
        this.watermarkList.add(watermark);
        return this;
    }

    public SimpleImageTool addWatermarkList(ArrayList<WatermarkParameter> watermarkList) {
        if (watermarkList != null) {
            this.watermarkList.addAll(watermarkList);
        }

        return this;
    }

    private void getInputStream(String inImagePath) {
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(inImagePath);
            this.imageStream = new BufferedInputStream(inputStream);
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
            this.imageStream = null;
        }
    }

    public static SimpleImageTool of(String inImagePath) {
        SimpleImageTool imageUtils = new SimpleImageTool();
        imageUtils.getInputStream(inImagePath);
        return imageUtils;
    }

    public static SimpleImageTool of(InputStream imageStream) {
        new BufferedInputStream(imageStream);
        SimpleImageTool imageUtils = new SimpleImageTool(imageStream);
        return imageUtils;
    }

    public SimpleImageTool scale(double scale) {
        if (scale <= 0.0D) {
            return this;
        } else {
            this.scale = scale;
            this.width = 0;
            this.height = 0;
            this.cutModel = false;
            return this;
        }
    }

    public SimpleImageTool width(int width) {
        if (width <= 0) {
            return this;
        } else {
            if (width >= 20000) {
                width = 20000;
            }

            this.height = 0;
            this.width = width;
            this.cutModel = false;
            return this;
        }
    }

    public SimpleImageTool height(int height) {
        if (height <= 0) {
            return this;
        } else {
            if (height >= 20000) {
                height = 20000;
            }

            this.width = 0;
            this.height = height;
            this.cutModel = false;
            return this;
        }
    }

    public SimpleImageTool size(int width, int height) {
        if (width > 0 && height > 0) {
            if (width >= 20000) {
                width = 20000;
            }

            if (height >= 20000) {
                height = 20000;
            }

            this.width = width;
            this.height = height;
            return this;
        } else {
            return this;
        }
    }

    public SimpleImageTool waterMarkerMin(int minwidth, int minheight) {
        this.waterMinWidth = minwidth;
        this.waterMinHeight = minheight;
        return this;
    }

    public SimpleImageTool cut(int width, int height) {
        if (width > 0 && height > 0) {
            if (width >= 20000) {
                width = 20000;
            }

            if (height >= 20000) {
                height = 20000;
            }

            this.width = width;
            this.height = height;
            return this;
        } else {
            return this;
        }
    }

    public SimpleImageTool rotate(double degree) {
        this.degree = degree;
        return this;
    }

    public SimpleImageTool bgcolor(Color bgcolor) {
        this.bgcolor = bgcolor;
        return this;
    }

    public SimpleImageTool gray(boolean gray) {
        this.gray = gray;
        return this;
    }

    public SimpleImageTool force(boolean force) {
        this.force = force;
        return this;
    }

    public void toFile(File outFile) throws MyImageException {
        this.outFile = outFile;
        if (!ImageFormat.isSouport(getSuffix(outFile))) {
            throw new MyImageException(getSuffix(outFile) + "format not surport!!");
        } else if (GifUtil.isGif(this.imageStream) && getSuffix(outFile).equalsIgnoreCase("gif")) {
            try {
                FileOutputStream outImage1 = new FileOutputStream(outFile);
                GifUtil.writeMultiframeGif(this.imageStream, outImage1, this.width, this.height, this.scale, this.lockScale, this.force, this.cutModel, this.degree, this.bgcolor, this.gray, this.watermarkList, this.waterMinWidth, this.waterMinHeight, this.writeParam);

                try {
                    outImage1.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            } catch (FileNotFoundException var16) {
                var16.printStackTrace();
            }
        } else {
            BufferedImage outImage = this.toBufferedImage();

            try {
                this.writeImageToFile(outImage);
                outImage.flush();
                return;
            } catch (IOException var17) {
                var17.printStackTrace();
                System.out.println("create image error!");
            } finally {
                if (this.imageStream != null) {
                    try {
                        this.imageStream.close();
                    } catch (IOException var14) {
                        var14.printStackTrace();
                        return;
                    }
                }

            }

        }
    }

    public BufferedImage toBufferedImage() throws MyImageException {
        BufferedImage tempBufferImage = null;

        try {
            tempBufferImage = ImageIO.read(this.imageStream);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }

        if (tempBufferImage == null) {
            throw new MyImageException("not surport image format!!");
        } else if ((tempBufferImage.getWidth() > this.width || tempBufferImage.getHeight() > this.height || this.width == 0 || this.height == 0 || this.force) && (this.width != 0 || tempBufferImage.getHeight() > this.height || this.force) && (this.height != 0 || tempBufferImage.getWidth() > this.width || this.force)) {
            tempBufferImage = ImageUtils.imageHandle(tempBufferImage, this.width, this.height, this.scale, this.lockScale, this.cutModel, this.degree, this.bgcolor, this.gray, this.watermarkList, this.waterMinWidth, this.waterMinHeight);
            return tempBufferImage;
        } else {
            this.writeParam.quality(1.0F);
            return tempBufferImage;
        }
    }

    public void writeImageToFile(BufferedImage newImage) throws IOException, MyImageException {
        String suffix = getSuffix(this.outFile);
        FileOutputStream os = new FileOutputStream(this.outFile);
        ImageWrapper imageWrapper = new ImageWrapper(newImage);
        ImageWriteHelper.write(imageWrapper, os, ImageFormat.getImageFormat(suffix), this.writeParam);
        os.close();
    }

    public InputStream toInputstream(String format) throws IOException, MyImageException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageWrapper imageWrapper = new ImageWrapper(this.toBufferedImage());
        ImageWriteHelper.write(imageWrapper, os, ImageFormat.getImageFormat(format), this.writeParam);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        os.close();
        return is;
    }

    private static String getSuffix(File file) {
        String fileName = file.getName();
        if (fileName.indexOf(46) != -1 && fileName.lastIndexOf(46) != fileName.length() - 1) {
            int lastIndex = fileName.lastIndexOf(46);
            return fileName.substring(lastIndex + 1);
        } else {
            return null;
        }
    }
}
