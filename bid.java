/*
 * Decompiled with CFR 0.152.
 */
public class bid {
    public float[][] a = new float[6][4];
    public float[] b = new float[16];
    public float[] c = new float[16];
    public float[] d = new float[16];

    private double a(float[] fArray, double d2, double d3, double d4) {
        return (double)fArray[0] * d2 + (double)fArray[1] * d3 + (double)fArray[2] * d4 + (double)fArray[3];
    }

    public boolean b(double d2, double d3, double d4, double d5, double d6, double d7) {
        for (int i2 = 0; i2 < 6; ++i2) {
            float[] fArray = this.a[i2];
            if (this.a(fArray, d2, d3, d4) > 0.0 || this.a(fArray, d5, d3, d4) > 0.0 || this.a(fArray, d2, d6, d4) > 0.0 || this.a(fArray, d5, d6, d4) > 0.0 || this.a(fArray, d2, d3, d7) > 0.0 || this.a(fArray, d5, d3, d7) > 0.0 || this.a(fArray, d2, d6, d7) > 0.0 || this.a(fArray, d5, d6, d7) > 0.0) continue;
            return false;
        }
        return true;
    }
}

