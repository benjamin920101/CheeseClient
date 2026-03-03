/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class arg {
    private static int[][] e = new int[][]{{1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}};
    public static final double a = Math.sqrt(3.0);
    private int[] f = new int[512];
    public double b;
    public double c;
    public double d;
    private static final double g = 0.5 * (a - 1.0);
    private static final double h = (3.0 - a) / 6.0;

    public arg() {
        this(new Random());
    }

    public arg(Random random) {
        int n2;
        this.b = random.nextDouble() * 256.0;
        this.c = random.nextDouble() * 256.0;
        this.d = random.nextDouble() * 256.0;
        for (n2 = 0; n2 < 256; ++n2) {
            this.f[n2] = n2;
        }
        for (n2 = 0; n2 < 256; ++n2) {
            \u2603 = random.nextInt(256 - n2) + n2;
            \u2603 = this.f[n2];
            this.f[n2] = this.f[\u2603];
            this.f[\u2603] = \u2603;
            this.f[n2 + 256] = this.f[n2];
        }
    }

    private static int a(double d2) {
        return d2 > 0.0 ? (int)d2 : (int)d2 - 1;
    }

    private static double a(int[] nArray, double d2, double d3) {
        return (double)nArray[0] * d2 + (double)nArray[1] * d3;
    }

    public double a(double d2, double d3) {
        double \u26039;
        \u2603 = 0.5 * (a - 1.0);
        \u2603 = (d2 + d3) * \u2603;
        int n2 = arg.a(d2 + \u2603);
        double \u26032 = (double)n2 - (\u2603 = (double)(n2 + (\u2603 = arg.a(d3 + \u2603))) * (\u2603 = (3.0 - a) / 6.0));
        double \u26033 = d2 - \u26032;
        if (\u26033 > (\u2603 = d3 - (\u2603 = (double)\u2603 - \u2603))) {
            \u2603 = 1;
            \u2603 = 0;
        } else {
            \u2603 = 0;
            \u2603 = 1;
        }
        double \u26034 = \u26033 - (double)\u2603 + \u2603;
        double \u26035 = \u2603 - (double)\u2603 + \u2603;
        double \u26036 = \u26033 - 1.0 + 2.0 * \u2603;
        double \u26037 = \u2603 - 1.0 + 2.0 * \u2603;
        \u2603 = n2 & 0xFF;
        \u2603 = \u2603 & 0xFF;
        n3 = this.f[\u2603 + this.f[\u2603]] % 12;
        \u2603 = this.f[\u2603 + \u2603 + this.f[\u2603 + \u2603]] % 12;
        \u2603 = this.f[\u2603 + 1 + this.f[\u2603 + 1]] % 12;
        double \u26038 = 0.5 - \u26033 * \u26033 - \u2603 * \u2603;
        if (\u26038 < 0.0) {
            \u26039 = 0.0;
        } else {
            int n3;
            \u26038 *= \u26038;
            \u26039 = \u26038 * \u26038 * arg.a(e[n3], \u26033, \u2603);
        }
        double d4 = 0.5 - \u26034 * \u26034 - \u26035 * \u26035;
        if (d4 < 0.0) {
            \u2603 = 0.0;
        } else {
            d4 *= d4;
            \u2603 = d4 * d4 * arg.a(e[\u2603], \u26034, \u26035);
        }
        \u2603 = 0.5 - \u26036 * \u26036 - \u26037 * \u26037;
        if (\u2603 < 0.0) {
            \u2603 = 0.0;
        } else {
            \u2603 *= \u2603;
            \u2603 = \u2603 * \u2603 * arg.a(e[\u2603], \u26036, \u26037);
        }
        return 70.0 * (\u26039 + \u2603 + \u2603);
    }

    public void a(double[] dArray, double d2, double d3, int n2, int n3, double d4, double d5, double d6) {
        int n4 = 0;
        for (\u2603 = 0; \u2603 < n3; ++\u2603) {
            double d7 = (d3 + (double)\u2603) * d5 + this.c;
            for (int i2 = 0; i2 < n2; ++i2) {
                double d8;
                int n5;
                double d9 = (d2 + (double)i2) * d4 + this.b;
                \u2603 = (d9 + d7) * g;
                int \u26032 = arg.a(d9 + \u2603);
                \u2603 = (double)\u26032 - (\u2603 = (double)(\u26032 + (\u2603 = arg.a(d7 + \u2603))) * h);
                d8 = d9 - \u2603;
                if (d8 > (\u2603 = d7 - (\u2603 = (double)\u2603 - \u2603))) {
                    n5 = 1;
                    \u2603 = 0;
                } else {
                    n5 = 0;
                    \u2603 = 1;
                }
                \u2603 = d8 - (double)n5 + h;
                \u2603 = \u2603 - (double)\u2603 + h;
                \u2603 = d8 - 1.0 + 2.0 * h;
                \u2603 = \u2603 - 1.0 + 2.0 * h;
                int \u26033 = \u26032 & 0xFF;
                int \u26034 = \u2603 & 0xFF;
                int \u26035 = this.f[\u26033 + this.f[\u26034]] % 12;
                int \u26036 = this.f[\u26033 + n5 + this.f[\u26034 + \u2603]] % 12;
                int \u26037 = this.f[\u26033 + 1 + this.f[\u26034 + 1]] % 12;
                \u2603 = 0.5 - d8 * d8 - \u2603 * \u2603;
                if (\u2603 < 0.0) {
                    \u2603 = 0.0;
                } else {
                    \u2603 *= \u2603;
                    \u2603 = \u2603 * \u2603 * arg.a(e[\u26035], d8, \u2603);
                }
                \u2603 = 0.5 - \u2603 * \u2603 - \u2603 * \u2603;
                if (\u2603 < 0.0) {
                    \u2603 = 0.0;
                } else {
                    \u2603 *= \u2603;
                    \u2603 = \u2603 * \u2603 * arg.a(e[\u26036], \u2603, \u2603);
                }
                \u2603 = 0.5 - \u2603 * \u2603 - \u2603 * \u2603;
                if (\u2603 < 0.0) {
                    \u2603 = 0.0;
                } else {
                    \u2603 *= \u2603;
                    \u2603 = \u2603 * \u2603 * arg.a(e[\u26037], \u2603, \u2603);
                }
                int n6 = n4++;
                dArray[n6] = dArray[n6] + 70.0 * (\u2603 + \u2603 + \u2603) * d6;
            }
        }
    }
}

