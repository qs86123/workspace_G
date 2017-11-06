//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.common;

import clive.hua.app.simpleImageTool.common.Position;
import clive.hua.app.simpleImageTool.util.ImageUtils;
import clive.hua.app.simpleImageTool.util.WaterMarkUtils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class WatermarkParameter {
    private Font font;
    private Color color;
    private Color bgColor;
    private float alpha = 1.0F;
    private double degree;
    private Point point;
    private BufferedImage waterImage;
    private String warerText;
    private Position position;

    private void init() {
        this.font = new Font("宋体", 1, 30);
        this.color = Color.blue;
        this.bgColor = null;
        this.alpha = 0.6F;
        this.degree = 0.0D;
        this.point = new Point(0, 0);
        this.waterImage = null;
        this.warerText = "";
        this.position = null;
    }

    public WatermarkParameter() {
        this.init();
    }

    public WatermarkParameter opacity(float alpha) {
        if(alpha < 1.0F && alpha >= 0.0F) {
            this.alpha = alpha;
            return this;
        } else {
            return this;
        }
    }

    public WatermarkParameter postion(Position position) {
        this.position = position;
        return this;
    }

    public WatermarkParameter font(Font font) {
        this.font = font;
        return this;
    }

    public WatermarkParameter color(Color color) {
        this.color = color;
        return this;
    }

    public WatermarkParameter bgColor(Color bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public WatermarkParameter rotate(float degree) {
        this.degree = (double)degree;
        return this;
    }

    public WatermarkParameter point(int x, int y) {
        if(0 == x && 0 == y) {
            return this;
        } else {
            this.point = new Point(x, y);
            return this;
        }
    }

    public WatermarkParameter addWaterMarkImage(BufferedImage waterImage) {
        this.waterImage = waterImage;
        this.warerText = "";
        return this;
    }

    public WatermarkParameter addWaterText(String warerText) {
        this.warerText = warerText;
        this.waterImage = null;
        return this;
    }

    public BufferedImage apply(BufferedImage image) {
        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();
        if(!this.warerText.isEmpty()) {
            int[] watermarkWidth = WaterMarkUtils.getTextWidthHeight(this.warerText, this.font);
            this.waterImage = WaterMarkUtils.createWaterTextImage(this.warerText, watermarkWidth, this.font, this.color, this.bgColor);
        }

        if(this.waterImage == null) {
            return image;
        } else {
            int watermarkWidth1 = this.waterImage.getWidth();
            int watermarkHeight = this.waterImage.getHeight();
            if(watermarkHeight > srcHeight || watermarkWidth1 > srcWidth) {
                this.waterImage = ImageUtils.scale(this.waterImage, (int)((double)srcWidth * 0.25D), (int)((double)srcHeight * 0.25D), 0.0D, true, (Color)null);
            }

            if(this.degree != 0.0D) {
                this.waterImage = ImageUtils.rotate(this.waterImage, this.degree, (Color)null);
            }

            watermarkWidth1 = this.waterImage.getWidth();
            watermarkHeight = this.waterImage.getHeight();
            BufferedImage WithWatermarkImage = ImageUtils.createNewImage(image, srcWidth, srcHeight);
            if(this.position != null) {
                this.point = this.position.calculate(srcWidth, srcHeight, watermarkWidth1, watermarkHeight, 0, 0, 0, 0);
            }

            Graphics2D g = WithWatermarkImage.createGraphics();
            g.drawImage(image, 0, 0, (ImageObserver)null);
            g.setComposite(AlphaComposite.getInstance(3, this.alpha));
            g.drawImage(this.waterImage, this.point.x, this.point.y, (ImageObserver)null);
            g.dispose();
            return WithWatermarkImage;
        }
    }
}
