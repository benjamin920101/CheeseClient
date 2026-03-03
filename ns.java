/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;
import java.util.UUID;

public class ns {
    public static final float a;
    private static final float[] b;
    private static final int[] c;
    private static final double d;
    private static final double[] e;
    private static final double[] f;

    public static float a(float f2) {
        return b[(int)(f2 * 10430.378f) & 0xFFFF];
    }

    public static float b(float f2) {
        return b[(int)(f2 * 10430.378f + 16384.0f) & 0xFFFF];
    }

    public static float c(float f2) {
        return (float)Math.sqrt(f2);
    }

    public static float a(double d2) {
        return (float)Math.sqrt(d2);
    }

    public static int d(float f2) {
        int n2 = (int)f2;
        return f2 < (float)n2 ? n2 - 1 : n2;
    }

    public static int b(double d2) {
        return (int)(d2 + 1024.0) - 1024;
    }

    public static int c(double d2) {
        int n2 = (int)d2;
        return d2 < (double)n2 ? n2 - 1 : n2;
    }

    public static long d(double d2) {
        long l2 = (long)d2;
        return d2 < (double)l2 ? l2 - 1L : l2;
    }

    public static int e(double d2) {
        return (int)(d2 >= 0.0 ? d2 : -d2 + 1.0);
    }

    public static float e(float f2) {
        return f2 >= 0.0f ? f2 : -f2;
    }

    public static int a(int n2) {
        return n2 >= 0 ? n2 : -n2;
    }

    public static int f(float f2) {
        int n2 = (int)f2;
        return f2 > (float)n2 ? n2 + 1 : n2;
    }

    public static int f(double d2) {
        int n2 = (int)d2;
        return d2 > (double)n2 ? n2 + 1 : n2;
    }

    public static int a(int n2, int n3, int n4) {
        if (n2 < n3) {
            return n3;
        }
        if (n2 > n4) {
            return n4;
        }
        return n2;
    }

    public static float a(float f2, float f3, float f4) {
        if (f2 < f3) {
            return f3;
        }
        if (f2 > f4) {
            return f4;
        }
        return f2;
    }

    public static double a(double d2, double d3, double d4) {
        if (d2 < d3) {
            return d3;
        }
        if (d2 > d4) {
            return d4;
        }
        return d2;
    }

    public static double b(double d2, double d3, double d4) {
        if (d4 < 0.0) {
            return d2;
        }
        if (d4 > 1.0) {
            return d3;
        }
        return d2 + (d3 - d2) * d4;
    }

    public static double a(double d2, double d3) {
        if (d2 < 0.0) {
            d2 = -d2;
        }
        if (d3 < 0.0) {
            d3 = -d3;
        }
        return d2 > d3 ? d2 : d3;
    }

    public static int a(int n2, int n3) {
        if (n2 < 0) {
            return -((-n2 - 1) / n3) - 1;
        }
        return n2 / n3;
    }

    public static int a(Random random, int n2, int n3) {
        if (n2 >= n3) {
            return n2;
        }
        return random.nextInt(n3 - n2 + 1) + n2;
    }

    public static float a(Random random, float f2, float f3) {
        if (f2 >= f3) {
            return f2;
        }
        return random.nextFloat() * (f3 - f2) + f2;
    }

    public static double a(Random random, double d2, double d3) {
        if (d2 >= d3) {
            return d2;
        }
        return random.nextDouble() * (d3 - d2) + d2;
    }

    public static double a(long[] lArray) {
        long l2 = 0L;
        for (long l3 : lArray) {
            l2 += l3;
        }
        return (double)l2 / (double)lArray.length;
    }

    public static boolean a(float f2, float f3) {
        return ns.e(f3 - f2) < 1.0E-5f;
    }

    public static int b(int n2, int n3) {
        return (n2 % n3 + n3) % n3;
    }

    public static float g(float f2) {
        if ((f2 %= 360.0f) >= 180.0f) {
            f2 -= 360.0f;
        }
        if (f2 < -180.0f) {
            f2 += 360.0f;
        }
        return f2;
    }

    public static double g(double d2) {
        if ((d2 %= 360.0) >= 180.0) {
            d2 -= 360.0;
        }
        if (d2 < -180.0) {
            d2 += 360.0;
        }
        return d2;
    }

    public static int a(String string, int n2) {
        try {
            return Integer.parseInt(string);
        }
        catch (Throwable throwable) {
            return n2;
        }
    }

    public static int a(String string, int n2, int n3) {
        return Math.max(n3, ns.a(string, n2));
    }

    public static double a(String string, double d2) {
        try {
            return Double.parseDouble(string);
        }
        catch (Throwable throwable) {
            return d2;
        }
    }

    public static double a(String string, double d2, double d3) {
        return Math.max(d3, ns.a(string, d2));
    }

    public static int b(int n2) {
        \u2603 = n2 - 1;
        \u2603 |= \u2603 >> 1;
        \u2603 |= \u2603 >> 2;
        \u2603 |= \u2603 >> 4;
        \u2603 |= \u2603 >> 8;
        \u2603 |= \u2603 >> 16;
        return \u2603 + 1;
    }

    private static boolean d(int n2) {
        return n2 != 0 && (n2 & n2 - 1) == 0;
    }

    private static int e(int n2) {
        n2 = ns.d(n2) ? n2 : ns.b(n2);
        return c[(int)((long)n2 * 125613361L >> 27) & 0x1F];
    }

    public static int c(int n2) {
        return ns.e(n2) - (ns.d(n2) ? 0 : 1);
    }

    public static int c(int n2, int n3) {
        if (n3 == 0) {
            return 0;
        }
        if (n2 == 0) {
            return n3;
        }
        if (n2 < 0) {
            n3 *= -1;
        }
        if ((\u2603 = n2 % n3) == 0) {
            return n2;
        }
        return n2 + n3 - \u2603;
    }

    public static int b(float f2, float f3, float f4) {
        return ns.b(ns.d(f2 * 255.0f), ns.d(f3 * 255.0f), ns.d(f4 * 255.0f));
    }

    public static int b(int n2, int n3, int n4) {
        \u2603 = n2;
        \u2603 = (\u2603 << 8) + n3;
        \u2603 = (\u2603 << 8) + n4;
        return \u2603;
    }

    public static int d(int n2, int n3) {
        \u2603 = (n2 & 0xFF0000) >> 16;
        \u2603 = (n3 & 0xFF0000) >> 16;
        \u2603 = (n2 & 0xFF00) >> 8;
        \u2603 = (n3 & 0xFF00) >> 8;
        \u2603 = (n2 & 0xFF) >> 0;
        \u2603 = (n3 & 0xFF) >> 0;
        \u2603 = (int)((float)\u2603 * (float)\u2603 / 255.0f);
        \u2603 = (int)((float)\u2603 * (float)\u2603 / 255.0f);
        \u2603 = (int)((float)\u2603 * (float)\u2603 / 255.0f);
        return n2 & 0xFF000000 | \u2603 << 16 | \u2603 << 8 | \u2603;
    }

    public static double h(double d2) {
        return d2 - Math.floor(d2);
    }

    public static long a(df df2) {
        return ns.c(df2.n(), df2.o(), df2.p());
    }

    public static long c(int n2, int n3, int n4) {
        long l2 = (long)(n2 * 3129871) ^ (long)n4 * 116129781L ^ (long)n3;
        l2 = l2 * l2 * 42317861L + l2 * 11L;
        return l2;
    }

    public static UUID a(Random random) {
        long l2 = random.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
        \u2603 = random.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
        return new UUID(l2, \u2603);
    }

    public static double c(double d2, double d3, double d4) {
        return (d2 - d3) / (d4 - d3);
    }

    public static double b(double d2, double d3) {
        \u2603 = d3 * d3 + d2 * d2;
        if (Double.isNaN(\u2603)) {
            return Double.NaN;
        }
        boolean bl2 = \u2603 = d2 < 0.0;
        if (\u2603) {
            d2 = -d2;
        }
        boolean bl3 = \u2603 = d3 < 0.0;
        if (\u2603) {
            d3 = -d3;
        }
        boolean bl4 = \u2603 = d2 > d3;
        if (\u2603) {
            \u2603 = d3;
            d3 = d2;
            d2 = \u2603;
        }
        \u2603 = ns.i(\u2603);
        d3 *= \u2603;
        \u2603 = d + (d2 *= \u2603);
        int n2 = (int)Double.doubleToRawLongBits(\u2603);
        double \u26032 = e[n2];
        double \u26033 = f[n2];
        double \u26034 = \u2603 - d;
        double \u26035 = d2 * \u26033 - d3 * \u26034;
        double \u26036 = (6.0 + \u26035 * \u26035) * \u26035 * 0.16666666666666666;
        double \u26037 = \u26032 + \u26036;
        if (\u2603) {
            \u26037 = 1.5707963267948966 - \u26037;
        }
        if (\u2603) {
            \u26037 = Math.PI - \u26037;
        }
        if (\u2603) {
            \u26037 = -\u26037;
        }
        return \u26037;
    }

    public static double i(double \u260322) {
        double d2;
        d2 = 0.5 * \u260322;
        long l2 = Double.doubleToRawLongBits(\u260322);
        l2 = 6910469410427058090L - (l2 >> 1);
        double \u260322 = Double.longBitsToDouble(l2);
        \u260322 *= 1.5 - d2 * \u260322 * \u260322;
        return \u260322;
    }

    public static int c(float f22, float f3, float f42) {
        float f5;
        int n2 = (int)(f22 * 6.0f) % 6;
        float \u26032 = f22 * 6.0f - (float)n2;
        float \u26033 = f42 * (1.0f - f3);
        float \u26034 = f42 * (1.0f - \u26032 * f3);
        float \u26035 = f42 * (1.0f - (1.0f - \u26032) * f3);
        switch (n2) {
            case 0: {
                f5 = f42;
                \u2603 = \u26035;
                \u2603 = \u26033;
                break;
            }
            case 1: {
                f5 = \u26034;
                \u2603 = f42;
                \u2603 = \u26033;
                break;
            }
            case 2: {
                f5 = \u26033;
                \u2603 = f42;
                \u2603 = \u26035;
                break;
            }
            case 3: {
                f5 = \u26033;
                \u2603 = \u26034;
                \u2603 = f42;
                break;
            }
            case 4: {
                f5 = \u26035;
                \u2603 = \u26033;
                \u2603 = f42;
                break;
            }
            case 5: {
                float f42;
                f5 = f42;
                \u2603 = \u26033;
                \u2603 = \u26034;
                break;
            }
            default: {
                float f22;
                throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + f22 + ", " + f3 + ", " + f42);
            }
        }
        int \u26036 = ns.a((int)(f5 * 255.0f), 0, 255);
        int \u26037 = ns.a((int)(\u2603 * 255.0f), 0, 255);
        int \u26038 = ns.a((int)(\u2603 * 255.0f), 0, 255);
        return \u26036 << 16 | \u26037 << 8 | \u26038;
    }

    static {
        int n2;
        a = ns.c(2.0f);
        b = new float[65536];
        for (n2 = 0; n2 < 65536; ++n2) {
            ns.b[n2] = (float)Math.sin((double)n2 * Math.PI * 2.0 / 65536.0);
        }
        c = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
        d = Double.longBitsToDouble(4805340802404319232L);
        e = new double[257];
        f = new double[257];
        for (n2 = 0; n2 < 257; ++n2) {
            double d2 = (double)n2 / 256.0;
            \u2603 = Math.asin(d2);
            ns.f[n2] = Math.cos(\u2603);
            ns.e[n2] = \u2603;
        }
    }
}

