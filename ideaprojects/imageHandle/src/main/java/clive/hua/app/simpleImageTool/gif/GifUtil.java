//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.gif;

import clive.hua.app.simpleImageTool.common.WatermarkParameter;
import clive.hua.app.simpleImageTool.common.WriteParameter;
import clive.hua.app.simpleImageTool.exception.MyImageException;
import clive.hua.app.simpleImageTool.gif.AnimatedGifEncoder;
import clive.hua.app.simpleImageTool.gif.GifDecoder;
import clive.hua.app.simpleImageTool.util.ImageUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class GifUtil {
    public GifUtil() {
    }

    protected static int read(InputStream in) {
        int curByte = 0;

        try {
            curByte = in.read();
        } catch (IOException var3) {
            ;
        }

        return curByte;
    }

    public static boolean isGif(BufferedInputStream in) {
        String type = "";
        in.mark(6);

        for(int e = 0; e < 6; ++e) {
            type = type + (char)read(in);
        }

        try {
            in.reset();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return type.startsWith("GIF");
    }

    public static void writeMultiframeGif(InputStream is, OutputStream out, int width, int height, double scale, boolean lockScale, boolean force, boolean cutModel, double degree, Color bgcolor, boolean gray, ArrayList<WatermarkParameter> watermarkList, int waterMinWidth, int waterMinHeight, WriteParameter writeParam) throws MyImageException {
        GifDecoder gd = new GifDecoder();
        int status = gd.read(is);
        if(status == 0) {
            AnimatedGifEncoder ge = new AnimatedGifEncoder();
            ge.setTransparent(Color.white);
            ge.start(out);
            ge.setRepeat(0);
            ge.setQuality((int)(255.0F * (1.0F - writeParam.getQuality())));

            for(int e = 0; e < gd.getFrameCount(); ++e) {
                BufferedImage frame = gd.getFrame(e);
                if((frame.getWidth() >= width || frame.getHeight() >= height || width == 0 || height == 0 || force) && (width != 0 || frame.getHeight() >= height || force) && (height != 0 || frame.getWidth() >= width || force)) {
                    frame = ImageUtils.imageHandle(frame, width, height, scale, lockScale, cutModel, degree, bgcolor, gray, watermarkList, waterMinWidth, waterMinHeight);
                }

                int delay = gd.getDelay(e);
                ge.setDelay(delay);
                ge.addFrame(frame);
            }

            ge.finish();

            try {
                is.close();
            } catch (IOException var24) {
                var24.printStackTrace();
                return;
            }

            try {
                out.close();
            } catch (IOException var23) {
                var23.printStackTrace();
            }
        }
    }
}
