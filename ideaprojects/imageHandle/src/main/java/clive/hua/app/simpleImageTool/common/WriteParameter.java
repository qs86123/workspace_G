//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package clive.hua.app.simpleImageTool.common;

public class WriteParameter {
    private float quality = 0.75F;
    private int dpi = 96;
    private WriteParameter.QuantAlgorithm quantAlgorithm;

    public WriteParameter() {
        this.quantAlgorithm = WriteParameter.QuantAlgorithm.OctTree;
        this.quality = this.quality;
        this.dpi = this.dpi;
    }

    public WriteParameter quality(float quality) {
        this.quality = quality;
        return this;
    }

    public WriteParameter dpi(int dpi) {
        this.dpi = dpi;
        return this;
    }

    public WriteParameter quantAlgorithm(WriteParameter.QuantAlgorithm quantAlgorithm) {
        this.quantAlgorithm = quantAlgorithm;
        return this;
    }

    public float getQuality() {
        return this.quality;
    }

    public int getDpi() {
        return this.dpi;
    }

    public WriteParameter.QuantAlgorithm getQuantAlgorithm() {
        return this.quantAlgorithm;
    }

    public static enum QuantAlgorithm {
        OctTree,
        NeuQuant,
        MedianCut;

        private QuantAlgorithm() {
        }
    }
}
