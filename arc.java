/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class arc
extends arh {
    private arb[] a;
    private int b;

    public arc(Random random, int n2) {
        this.b = n2;
        this.a = new arb[n2];
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            this.a[\u2603] = new arb(random);
        }
    }

    public double[] a(double[] dArray2, int n2, int n3, int n4, int n5, int n6, int n7, double d2, double d3, double d4) {
        if (dArray2 == null) {
            double[] dArray2 = new double[n5 * n6 * n7];
        } else {
            for (int i2 = 0; i2 < dArray2.length; ++i2) {
                dArray2[i2] = 0.0;
            }
        }
        double d5 = 1.0;
        for (int i3 = 0; i3 < this.b; ++i3) {
            double d6 = (double)n2 * d5 * d2;
            \u2603 = (double)n3 * d5 * d3;
            \u2603 = (double)n4 * d5 * d4;
            long \u26032 = ns.d(d6);
            long \u26033 = ns.d(\u2603);
            d6 -= (double)\u26032;
            \u2603 -= (double)\u26033;
            this.a[i3].a(dArray2, d6 += (double)(\u26032 %= 0x1000000L), \u2603, \u2603 += (double)(\u26033 %= 0x1000000L), n5, n6, n7, d2 * d5, d3 * d5, d4 * d5, d5);
            d5 /= 2.0;
        }
        return dArray2;
    }

    public double[] a(double[] dArray, int n2, int n3, int n4, int n5, double d2, double d3, double d4) {
        return this.a(dArray, n2, 10, n3, n4, 1, n5, d2, 1.0, d3);
    }
}

