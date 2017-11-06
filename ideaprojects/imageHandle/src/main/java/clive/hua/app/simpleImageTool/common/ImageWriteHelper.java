//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.common;

import clive.hua.app.simpleImageTool.common.ImageFormat;
import clive.hua.app.simpleImageTool.common.ImageWrapper;
import clive.hua.app.simpleImageTool.common.WriteParameter;
import clive.hua.app.simpleImageTool.exception.MyImageException;
import clive.hua.app.simpleImageTool.gif.AnimatedGifEncoder;
import com.twelvemonkeys.imageio.plugins.jpeg.JPEGImageWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.Element;

public class ImageWriteHelper {
    public ImageWriteHelper() {
    }

    public static void write(ImageWrapper imageWrapper, OutputStream os, ImageFormat format, WriteParameter param) throws MyImageException, IOException {
        if(format == ImageFormat.JPEG) {
            writeJPEG(imageWrapper, os, param);
        } else if(format == ImageFormat.GIF) {
            writeGif(imageWrapper.getBufferedImage(), os, param);
        } else {
            if(format != ImageFormat.TIFF && format != ImageFormat.PNG && format != ImageFormat.BMP) {
                throw new IllegalArgumentException("Unsupported output format, only JPEG, BMP, GIF, PNG and TIFF are ok");
            }

            try {
                ImageIO.write(imageWrapper.getBufferedImage(), format.getDesc(), os);
            } catch (IOException var5) {
                throw new MyImageException(var5);
            }
        }

    }

    public static void writeJPEG(ImageWrapper imageWrapper, OutputStream os, WriteParameter inParam) throws MyImageException, IOException {
        BufferedImage bufferedImage = imageWrapper.getBufferedImage();
        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 4);
        newBufferedImage.getGraphics().drawImage(bufferedImage, 0, 0, Color.white, (ImageObserver)null);
        JPEGImageWriter imageWriter = (JPEGImageWriter)ImageIO.getImageWritersBySuffix("jpeg").next();
        ImageWriteParam writeParam = imageWriter.getDefaultWriteParam();
        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(1);
        IIOMetadata metadata = imageWriter.getDefaultImageMetadata(typeSpecifier, writeParam);
        Element tree = (Element)metadata.getAsTree("javax_imageio_jpeg_image_1.0");
        Element jfif = (Element)tree.getElementsByTagName("app0JFIF").item(0);
        int dpi = inParam.getDpi();
        if(dpi > 0 && dpi <= 400) {
            jfif.setAttribute("Xdensity", Integer.toString(dpi));
            jfif.setAttribute("Ydensity", Integer.toString(dpi));
            jfif.setAttribute("resUnits", "1");
            metadata.setFromTree("javax_imageio_jpeg_image_1.0", tree);
        }

        float quality = inParam.getQuality();
        JPEGImageWriteParam jpegParams = (JPEGImageWriteParam)imageWriter.getDefaultWriteParam();
        if(quality > 0.0F && quality <= 1.0F) {
            jpegParams.setCompressionMode(2);
            jpegParams.setCompressionQuality(quality);
        }

        ImageOutputStream stream = ImageIO.createImageOutputStream(os);

        try {
            imageWriter.setOutput(stream);
            imageWriter.write(metadata, new IIOImage(newBufferedImage, (List)null, metadata), jpegParams);
            imageWriter.dispose();
        } finally {
            stream.close();
        }

    }

    public static void writeGif(BufferedImage bufferedImage, OutputStream os, WriteParameter param) throws MyImageException {
        AnimatedGifEncoder ge = new AnimatedGifEncoder();
        ge.setTransparent(Color.white);
        ge.start(os);
        ge.setQuality((int)(255.0F * (1.0F - param.getQuality())));
        ge.setRepeat(0);
        ge.setDelay(0);
        ge.addFrame(bufferedImage);
        ge.finish();

        try {
            os.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }
}
