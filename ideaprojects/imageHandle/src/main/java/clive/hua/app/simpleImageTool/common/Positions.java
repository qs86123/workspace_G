//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.common;

import clive.hua.app.simpleImageTool.common.Position;
import java.awt.Point;

public enum Positions implements Position {
    TOP_LEFT {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            return new Point(insetLeft, insetTop);
        }
    },
    TOP_CENTER {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int x = enclosingWidth / 2 - width / 2;
            return new Point(x, insetTop);
        }
    },
    TOP_RIGHT {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int x = enclosingWidth - width - insetRight;
            return new Point(x, insetTop);
        }
    },
    CENTER_LEFT {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int y = enclosingHeight / 2 - height / 2;
            return new Point(insetLeft, y);
        }
    },
    CENTER {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int x = enclosingWidth / 2 - width / 2;
            int y = enclosingHeight / 2 - height / 2;
            return new Point(x, y);
        }
    },
    CENTER_RIGHT {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int x = enclosingWidth - width - insetRight;
            int y = enclosingHeight / 2 - height / 2;
            return new Point(x, y);
        }
    },
    BOTTOM_LEFT {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int y = enclosingHeight - height - insetBottom;
            return new Point(insetLeft, y);
        }
    },
    BOTTOM_CENTER {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int x = enclosingWidth / 2 - width / 2;
            int y = enclosingHeight - height - insetBottom;
            return new Point(x, y);
        }
    },
    BOTTOM_RIGHT {
        public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
            int x = enclosingWidth - width - insetRight;
            int y = enclosingHeight - height - insetBottom;
            return new Point(x, y);
        }
    };

    private Positions() {
    }
}
