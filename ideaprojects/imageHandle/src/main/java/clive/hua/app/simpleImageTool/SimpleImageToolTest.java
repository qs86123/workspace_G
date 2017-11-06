package clive.hua.app.simpleImageTool;

import clive.hua.app.simpleImageTool.common.Positions;
import clive.hua.app.simpleImageTool.common.WatermarkParameter;
import clive.hua.app.simpleImageTool.exception.MyImageException;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.WriteRender;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:42 2017/11/6
 * @Email:tao8.wang@changhong.com
 */
public class SimpleImageToolTest {
    private SimpleImageTool simpleImageTool;

    @Before
    public void before() {
//        simpleImageTool = SimpleImageTool.of("D:/111.jpg");
        simpleImageTool = SimpleImageTool.of("D:/water.png");
    }

    @Test
    public void cut() throws MyImageException {
        simpleImageTool.cut(400, 400).toFile(new File("D:cut400x600.jpg"));
    }

    @Test
    public void cutRotate() throws MyImageException {
        simpleImageTool.cut(400, 400).rotate(20).toFile(new File("D:cut400x600Rotate.jpg"));
    }

    @Test
    public void cutWithCutModel() throws MyImageException {
        simpleImageTool.cut(400, 400).cutModel(true).toFile(new File("D:cut400x600CutmodelTrue.jpg"));
    }

    @Test
    public void cutRotateWithCutModel() throws MyImageException {
        simpleImageTool.cut(400, 400).rotate(20).cutModel(true).toFile(new File("D:cut400x600RotateCutmodelTrue.jpg"));
    }

    @Test
    public void format() throws MyImageException {
        simpleImageTool.toFile(new File("D:format.png"));
    }

    @Test
    public void pngToGif() throws MyImageException {
        simpleImageTool.toFile(new File("D:pngToGif.gif"));
    }

    @Test
    public void height600Gray() throws MyImageException {
        simpleImageTool.height(600).gray(true).toFile(new File("D:height600gray.jpg"));
    }

    @Test
    public void height600Rotategray() throws MyImageException {
        simpleImageTool.height(600).rotate(20).gray(true).toFile(new File("D:height600Rotategray.jpg"));
    }

    @Test
    public void size() throws MyImageException {
        simpleImageTool.size(600, 600).toFile(new File("D:size600x600.jpg"));
    }

    @Test
    public void sizeBgColor() throws MyImageException {
        simpleImageTool.size(600, 600).bgcolor(Color.BLUE).toFile(new File("D:size600x600BgColorblue.jpg"));
    }

    @Test
    public void sizeBgColorRotate() throws MyImageException {
        simpleImageTool.size(600, 600).rotate(20).bgcolor(Color.BLUE).toFile(new File("D:size600x600rotate20Colorblue.jpg"));
    }

    @Test
    public void sizeRotate() throws MyImageException {
        simpleImageTool.size(600, 600).scale(0.5).toFile(new File("D:size600x600scale.jpg"));
    }

    @Test
    public void sizeRLockScaleFalse() throws MyImageException {
        simpleImageTool.size(600, 600).lockScale(false).toFile(new File("D:size600x600Lockscalefalse.jpg"));
    }

    @Test
    public void water() throws MyImageException, IOException {
//        BufferedImage watermarkImage = ImageIO.read(new FileInputStream("D:water.png"));
        BufferedImage watermarkImage = new SimpleImageTool(new FileInputStream("D:water.png")).size(318, 419).toBufferedImage();
        WatermarkParameter watermark = new WatermarkParameter().addWaterMarkImage(watermarkImage)
                .postion(Positions.CENTER)
                .rotate(20f)
                .color(Color.blue)
                .opacity(0.8f);
        BufferedImage watermarkImage2 = ImageIO.read(new FileInputStream("D:water.jpg"));
        WatermarkParameter watermark2 = new WatermarkParameter().addWaterMarkImage(watermarkImage2)
                .postion(Positions.CENTER)
                .rotate(20f)
                .color(Color.blue)
                .opacity(0.8f);
        WatermarkParameter watermark3 = new WatermarkParameter().addWaterText("隶书水印")
                .postion(Positions.CENTER)
                .font(new Font("隶书", Font.BOLD, 50))
                .rotate(20f)
                .color(Color.blue)
                .opacity(0.8f);
        simpleImageTool.size(600, 600).watermark(watermark).waterMarkerMin(100, 100).watermark(watermark2).watermark(watermark3).toFile(new File("D:图片水印.jpg"));
    }

}
