//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import sun.font.FontDesignMetrics;

public class WaterMarkUtils {
    public WaterMarkUtils() {
    }

    public static int[] getTextWidthHeight(String text, Font font) {
        BufferedImage buffImg = new BufferedImage(1, 1, 1);
        Graphics2D g = buffImg.createGraphics();
        g.setFont(font);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D stringBounds = g.getFont().getStringBounds(text, context);
        int waterTextWidth = (int)stringBounds.getWidth();
        FontDesignMetrics fm = FontDesignMetrics.getMetrics(g.getFont());
        int waterTextHeight = fm.getHeight();
        int baseLine = waterTextHeight - fm.getDescent() - fm.getLeading();
        int[] size = new int[]{waterTextWidth, waterTextHeight, baseLine};
        g.dispose();
        return size;
    }

    public static BufferedImage createWaterTextImage(String text, int[] size, Font font, Color color, Color bgColor) {
        BufferedImage buffImg = new BufferedImage(size[0], size[1], 1);
        Graphics2D g = buffImg.createGraphics();
        if(bgColor != null) {
            g.setPaint(bgColor);
            g.fillRect(0, 0, size[0], size[1]);
        } else {
            buffImg = g.getDeviceConfiguration().createCompatibleImage(size[0], size[1], 3);
            g = buffImg.createGraphics();
        }

        g.setColor(color);
        g.setFont(font);
        g.drawString(text, 0, size[2]);
        g.dispose();
        return buffImg;
    }
}
