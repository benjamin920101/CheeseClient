/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ard
extends arh {
    private arg[] a;
    private int b;

    public ard(Random random, int n2) {
        this.b = n2;
        this.a = new arg[n2];
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            this.a[\u2603] = new arg(random);
        }
    }

    public double a(double d2, double d3) {
        double d4;
        d4 = 0.0;
        \u2603 = 1.0;
        for (int i2 = 0; i2 < this.b; ++i2) {
            d4 += this.a[i2].a(d2 * \u2603, d3 * \u2603) / \u2603;
            \u2603 /= 2.0;
        }
        return d4;
    }

    public double[] a(double[] dArray, double d2, double d3, int n2, int n3, double d4, double d5, double d6) {
        return this.a(dArray, d2, d3, n2, n3, d4, d5, d6, 0.5);
    }

    public double[] a(double[] dArray2, double d2, double d3, int n2, int n3, double d4, double d5, double d6, double d7) {
        double[] dArray2;
        if (dArray2 == null || dArray2.length < n2 * n3) {
            dArray2 = new double[n2 * n3];
        } else {
            for (int i2 = 0; i2 < dArray2.length; ++i2) {
                dArray2[i2] = 0.0;
            }
        }
        double d8 = 1.0;
        \u2603 = 1.0;
        for (int i3 = 0; i3 < this.b; ++i3) {
            this.a[i3].a(dArray2, d2, d3, n2, n3, d4 * \u2603 * d8, d5 * \u2603 * d8, 0.55 / d8);
            \u2603 *= d6;
            d8 *= d7;
        }
        return dArray2;
    }
}

