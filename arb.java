/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class arb
extends arh {
    private int[] d = new int[512];
    public double a;
    public double b;
    public double c;
    private static final double[] e = new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0};
    private static final double[] f = new double[]{1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0};
    private static final double[] g = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0};
    private static final double[] h = new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0};
    private static final double[] i = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0};

    public arb() {
        this(new Random());
    }

    public arb(Random random) {
        int n2;
        this.a = random.nextDouble() * 256.0;
        this.b = random.nextDouble() * 256.0;
        this.c = random.nextDouble() * 256.0;
        for (n2 = 0; n2 < 256; ++n2) {
            this.d[n2] = n2;
        }
        for (n2 = 0; n2 < 256; ++n2) {
            \u2603 = random.nextInt(256 - n2) + n2;
            \u2603 = this.d[n2];
            this.d[n2] = this.d[\u2603];
            this.d[\u2603] = \u2603;
            this.d[n2 + 256] = this.d[n2];
        }
    }

    public final double b(double d2, double d3, double d4) {
        return d3 + d2 * (d4 - d3);
    }

    public final double a(int n2, double d2, double d3) {
        int n3 = n2 & 0xF;
        return h[n3] * d2 + i[n3] * d3;
    }

    public final double a(int n2, double d2, double d3, double d4) {
        int n3 = n2 & 0xF;
        return e[n3] * d2 + f[n3] * d3 + g[n3] * d4;
    }

    public void a(double[] dArray, double d2, double d3, double d4, int n2, int n3, int n4, double d5, double d6, double d7, double d8) {
        if (n3 == 1) {
            int \u26039 = 0;
            \u260310 = 0;
            \u260311 = 0;
            \u260312 = 0;
            double \u26032 = 0.0;
            double \u26033 = 0.0;
            \u2603 = 0;
            double \u26034 = 1.0 / d8;
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                double d9 = d2 + (double)\u2603 * d5 + this.a;
                int \u26035 = (int)d9;
                if (d9 < (double)\u26035) {
                    --\u26035;
                }
                int \u26036 = \u26035 & 0xFF;
                \u2603 = (d9 -= (double)\u26035) * d9 * d9 * (d9 * (d9 * 6.0 - 15.0) + 10.0);
                for (int i2 = 0; i2 < n4; ++i2) {
                    double d10 = d4 + (double)i2 * d7 + this.c;
                    int \u26037 = (int)d10;
                    if (d10 < (double)\u26037) {
                        --\u26037;
                    }
                    int \u26038 = \u26037 & 0xFF;
                    \u2603 = (d10 -= (double)\u26037) * d10 * d10 * (d10 * (d10 * 6.0 - 15.0) + 10.0);
                    \u26039 = this.d[\u26036] + 0;
                    int \u260310 = this.d[\u26039] + \u26038;
                    int \u260311 = this.d[\u26036 + 1] + 0;
                    int \u260312 = this.d[\u260311] + \u26038;
                    \u26032 = this.b(\u2603, this.a(this.d[\u260310], d9, d10), this.a(this.d[\u260312], d9 - 1.0, 0.0, d10));
                    \u26033 = this.b(\u2603, this.a(this.d[\u260310 + 1], d9, 0.0, d10 - 1.0), this.a(this.d[\u260312 + 1], d9 - 1.0, 0.0, d10 - 1.0));
                    \u2603 = this.b(\u2603, \u26032, \u26033);
                    int n5 = \u2603++;
                    dArray[n5] = dArray[n5] + \u2603 * \u26034;
                }
            }
            return;
        }
        int n6 = 0;
        double \u260313 = 1.0 / d8;
        n7 = -1;
        \u2603 = 0;
        \u2603 = 0;
        \u2603 = 0;
        \u2603 = 0;
        \u2603 = 0;
        \u2603 = 0;
        double \u260314 = 0.0;
        double \u260315 = 0.0;
        double \u260316 = 0.0;
        double \u260317 = 0.0;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            double d11 = d2 + (double)\u2603 * d5 + this.a;
            int \u260318 = (int)d11;
            if (d11 < (double)\u260318) {
                --\u260318;
            }
            int \u260319 = \u260318 & 0xFF;
            \u2603 = (d11 -= (double)\u260318) * d11 * d11 * (d11 * (d11 * 6.0 - 15.0) + 10.0);
            for (int i3 = 0; i3 < n4; ++i3) {
                double d12 = d4 + (double)i3 * d7 + this.c;
                int \u260320 = (int)d12;
                if (d12 < (double)\u260320) {
                    --\u260320;
                }
                int \u260321 = \u260320 & 0xFF;
                \u2603 = (d12 -= (double)\u260320) * d12 * d12 * (d12 * (d12 * 6.0 - 15.0) + 10.0);
                for (int i4 = 0; i4 < n3; ++i4) {
                    double d13;
                    double d14 = d3 + (double)i4 * d6 + this.b;
                    int \u260322 = (int)d14;
                    if (d14 < (double)\u260322) {
                        --\u260322;
                    }
                    int \u260323 = \u260322 & 0xFF;
                    d13 = (d14 -= (double)\u260322) * d14 * d14 * (d14 * (d14 * 6.0 - 15.0) + 10.0);
                    if (i4 == 0 || \u260323 != n7) {
                        int n7 = \u260323;
                        \u2603 = this.d[\u260319] + \u260323;
                        \u2603 = this.d[\u2603] + \u260321;
                        \u2603 = this.d[\u2603 + 1] + \u260321;
                        \u2603 = this.d[\u260319 + 1] + \u260323;
                        \u2603 = this.d[\u2603] + \u260321;
                        \u2603 = this.d[\u2603 + 1] + \u260321;
                        \u260314 = this.b(\u2603, this.a(this.d[\u2603], d11, d14, d12), this.a(this.d[\u2603], d11 - 1.0, d14, d12));
                        \u260315 = this.b(\u2603, this.a(this.d[\u2603], d11, d14 - 1.0, d12), this.a(this.d[\u2603], d11 - 1.0, d14 - 1.0, d12));
                        \u260316 = this.b(\u2603, this.a(this.d[\u2603 + 1], d11, d14, d12 - 1.0), this.a(this.d[\u2603 + 1], d11 - 1.0, d14, d12 - 1.0));
                        \u260317 = this.b(\u2603, this.a(this.d[\u2603 + 1], d11, d14 - 1.0, d12 - 1.0), this.a(this.d[\u2603 + 1], d11 - 1.0, d14 - 1.0, d12 - 1.0));
                    }
                    \u2603 = this.b(d13, \u260314, \u260315);
                    \u2603 = this.b(d13, \u260316, \u260317);
                    \u2603 = this.b(\u2603, \u2603, \u2603);
                    int n8 = n6++;
                    dArray[n8] = dArray[n8] + \u2603 * \u260313;
                }
            }
        }
    }
}

