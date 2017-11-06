//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.util;

import clive.hua.app.simpleImageTool.common.WatermarkParameter;
import clive.hua.app.simpleImageTool.exception.MyImageException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ImageUtils {
    public ImageUtils() {
    }

    public static BufferedImage createNewImage(BufferedImage image, int x, int y, int scaleWidth, int scaleHeight, int outWidth, int outHeight, double degree, Color bgcolor) {
        BufferedImage newImage = new BufferedImage(outWidth, outHeight, 2);
        Graphics2D g = newImage.createGraphics();
        setRenderingHint(g);
        if(bgcolor != null) {
            g.setPaint(bgcolor);
            g.fillRect(0, 0, outWidth, outHeight);
        } else {
            newImage = g.getDeviceConfiguration().createCompatibleImage(outWidth, outHeight, 3);
            g.dispose();
            g = newImage.createGraphics();
        }

        if(degree != 0.0D) {
            g.rotate(Math.toRadians(degree), (double)outWidth / 2.0D, (double)outHeight / 2.0D);
        }

        g.drawImage(image.getScaledInstance(scaleWidth, scaleHeight, 4), x, y, (ImageObserver)null);
        g.dispose();
        float softenFactor = 0.05F;
        float[] softenArray = new float[]{0.0F, softenFactor, 0.0F, softenFactor, 1.0F - softenFactor * 4.0F, softenFactor, 0.0F, softenFactor, 0.0F};
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, 1, (RenderingHints)null);
        newImage = cOp.filter(newImage, (BufferedImage)null);
        return newImage;
    }

    public static BufferedImage createNewImage(BufferedImage image, int outWidth, int outHeight) {
        return createNewImage(image, 0, 0, outWidth, outHeight, outWidth, outHeight, 0.0D, (Color)null);
    }

    public static BufferedImage lockScaleCreateImage(BufferedImage bufferImage, int outWidth, int outHeight, Color paddingColor) {
        int sourceWidth = bufferImage.getWidth();
        int sourceHeight = bufferImage.getHeight();
        boolean x = false;
        boolean y = false;
        int scaleWidth = outWidth;
        int scaleHeight = outHeight;
        double sourceRatio = (double)sourceWidth / (double)sourceHeight;
        double targetRatio = (double)outWidth / (double)outHeight;
        if(Double.compare(sourceRatio, targetRatio) != 0) {
            if(sourceRatio > targetRatio) {
                scaleHeight = (int)Math.round((double)outWidth / sourceRatio);
            } else {
                scaleWidth = (int)Math.round((double)outHeight * sourceRatio);
            }
        }

        int x1 = (outWidth - scaleWidth) / 2;
        int y1 = (outHeight - scaleHeight) / 2;
        outWidth = outWidth == 0?1:outWidth;
        outHeight = outHeight == 0?1:outHeight;
        return createNewImage(bufferImage, x1, y1, scaleWidth, scaleHeight, outWidth, outHeight, 0.0D, paddingColor);
    }

    public static BufferedImage rotate(BufferedImage image, double degree, Color paddingColor) {
        int srcWidth = image.getWidth((ImageObserver)null);
        int srcHeight = image.getHeight((ImageObserver)null);
        double[][] newPositions = new double[][]{calculatePosition(0.0D, 0.0D, degree), calculatePosition((double)srcWidth, 0.0D, degree), calculatePosition(0.0D, (double)srcHeight, degree), calculatePosition((double)srcWidth, (double)srcHeight, degree)};
        double minX = Math.min(Math.min(newPositions[0][0], newPositions[1][0]), Math.min(newPositions[2][0], newPositions[3][0]));
        double maxX = Math.max(Math.max(newPositions[0][0], newPositions[1][0]), Math.max(newPositions[2][0], newPositions[3][0]));
        double minY = Math.min(Math.min(newPositions[0][1], newPositions[1][1]), Math.min(newPositions[2][1], newPositions[3][1]));
        double maxY = Math.max(Math.max(newPositions[0][1], newPositions[1][1]), Math.max(newPositions[2][1], newPositions[3][1]));
        int newWidth = (int)Math.round(maxX - minX);
        int newHeight = (int)Math.round(maxY - minY);
        int centerX = (int)Math.round((double)(newWidth - srcWidth) / 2.0D);
        int centerY = (int)Math.round((double)(newHeight - srcHeight) / 2.0D);
        return createNewImage(image, centerX, centerY, srcWidth, srcHeight, newWidth, newHeight, degree, paddingColor);
    }

    public static BufferedImage scale(BufferedImage inImage, int width, int height, double scale, boolean lockScale, Color paddingColor) {
        int outWidth = 0;
        int outHeight = 0;
        int srcWidth = inImage.getWidth();
        int srcHeight = inImage.getHeight();
        if(width > 0 && height > 0) {
            if(lockScale) {
                return lockScaleCreateImage(inImage, width, height, paddingColor);
            }

            outWidth = width;
            outHeight = height;
        } else if(scale > 0.0D) {
            outWidth = (int)((double)srcWidth * scale);
            outHeight = (int)((double)srcHeight * scale);
        } else if(width > 0 && height == 0) {
            outHeight = width * srcHeight / srcWidth;
            outWidth = width;
        } else if(width == 0 && height > 0) {
            outWidth = height * srcWidth / srcHeight;
            outHeight = height;
        }

        if(outWidth == 0 || outHeight == 0) {
            outHeight = srcHeight;
            outWidth = srcWidth;
        }

        if(outWidth > 1 && outHeight > 1) {
            return createNewImage(inImage, outWidth, outHeight);
        } else {
            throw new IllegalStateException("outWidth or outHeight value error!");
        }
    }

    public static BufferedImage cut(BufferedImage bufferImage, int outWidth, int outHeight) {
        int sourceWidth = bufferImage.getWidth();
        int sourceHeight = bufferImage.getHeight();
        boolean x = false;
        boolean y = false;
        int scaleWidth = outWidth;
        int scaleHeight = outHeight;
        double sourceRatio = (double)sourceWidth / (double)sourceHeight;
        double targetRatio = (double)outWidth / (double)outHeight;
        if(Double.compare(sourceRatio, targetRatio) != 0) {
            if(sourceRatio > targetRatio) {
                scaleWidth = (int)Math.round((double)outHeight * sourceRatio);
            } else {
                scaleHeight = (int)Math.round((double)outWidth / sourceRatio);
            }
        }

        int x1 = (scaleWidth - outWidth) / 2;
        int y1 = (scaleHeight - outHeight) / 2;
        bufferImage = createNewImage(bufferImage, scaleWidth, scaleHeight);
        bufferImage = bufferImage.getSubimage(x1, y1, outWidth, outHeight);
        return createNewImage(bufferImage, outWidth, outHeight);
    }

    public static double[] calculatePosition(double x, double y, double degree) {
        degree = Math.toRadians(degree);
        double nx = Math.cos(degree) * x - Math.sin(degree) * y;
        double ny = Math.sin(degree) * x + Math.cos(degree) * y;
        return new double[]{nx, ny};
    }

    public static void setRenderingHint(Graphics2D g) {
        HashMap m = new HashMap();
        m.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        m.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        m.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        m.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        m.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        m.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHints(m);
    }

    public static BufferedImage imageHandle(BufferedImage tempBufferImage, int width, int height, double scale, boolean lockScale, boolean cutModel, double degree, Color bgcolor, boolean gray, ArrayList<WatermarkParameter> watermarkList, int waterMinWidth, int waterMinHeight) throws MyImageException {
        if(scale > 0.0D && width == 0 && height == 0) {
            width = (int)((double)tempBufferImage.getWidth() * scale);
            height = (int)((double)tempBufferImage.getHeight() * scale);
        }

        if(degree != 0.0D) {
            tempBufferImage = rotate(tempBufferImage, degree, bgcolor);
        }

        if(cutModel) {
            tempBufferImage = cut(tempBufferImage, width, height);
        } else {
            tempBufferImage = scale(tempBufferImage, width, height, scale, lockScale, bgcolor);
        }

        WatermarkParameter op;
        if(!watermarkList.isEmpty() && tempBufferImage.getWidth() > waterMinWidth && tempBufferImage.getHeight() > waterMinHeight) {
            for(Iterator cs = watermarkList.iterator(); cs.hasNext(); tempBufferImage = op.apply(tempBufferImage)) {
                op = (WatermarkParameter)cs.next();
            }
        }

        if(gray) {
            ColorSpace cs1 = ColorSpace.getInstance(1003);
            ColorConvertOp op1 = new ColorConvertOp(cs1, (RenderingHints)null);
            tempBufferImage = op1.filter(tempBufferImage, (BufferedImage)null);
        }

        return tempBufferImage;
    }
}
