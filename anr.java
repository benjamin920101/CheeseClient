/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class anr
extends any {
    private float[] d = new float[1024];

    protected void a(long l2, int n2, int n3, ans ans2, double d2, double d3, double d4, float f2, float f3, float f4, int n4, int n5, double d5) {
        int n6;
        Random random = new Random(l2);
        double \u26032 = n2 * 16 + 8;
        double \u26033 = n3 * 16 + 8;
        float \u26034 = 0.0f;
        float \u26035 = 0.0f;
        if (n5 <= 0) {
            n6 = this.a * 16 - 16;
            n5 = n6 - random.nextInt(n6 / 4);
        }
        n6 = 0;
        if (n4 == -1) {
            n4 = n5 / 2;
            n6 = 1;
        }
        float \u26036 = 1.0f;
        for (\u2603 = 0; \u2603 < 256; ++\u2603) {
            if (\u2603 == 0 || random.nextInt(3) == 0) {
                \u26036 = 1.0f + random.nextFloat() * random.nextFloat() * 1.0f;
            }
            this.d[\u2603] = \u26036 * \u26036;
        }
        while (n4 < n5) {
            double d6 = 1.5 + (double)(ns.a((float)n4 * (float)Math.PI / (float)n5) * f2 * 1.0f);
            \u2603 = d6 * d5;
            d6 *= (double)random.nextFloat() * 0.25 + 0.75;
            \u2603 *= (double)random.nextFloat() * 0.25 + 0.75;
            float \u26037 = ns.b(f4);
            float \u26038 = ns.a(f4);
            d2 += (double)(ns.b(f3) * \u26037);
            d3 += (double)\u26038;
            d4 += (double)(ns.a(f3) * \u26037);
            f4 *= 0.7f;
            f4 += \u26035 * 0.05f;
            f3 += \u26034 * 0.05f;
            \u26035 *= 0.8f;
            \u26034 *= 0.5f;
            \u26035 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            \u26034 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (n6 != 0 || random.nextInt(4) != 0) {
                \u2603 = d2 - \u26032;
                \u2603 = d4 - \u26033;
                \u2603 = n5 - n4;
                \u2603 = f2 + 2.0f + 16.0f;
                if (\u2603 * \u2603 + \u2603 * \u2603 - \u2603 * \u2603 > \u2603 * \u2603) {
                    return;
                }
                if (!(d2 < \u26032 - 16.0 - d6 * 2.0 || d4 < \u26033 - 16.0 - d6 * 2.0 || d2 > \u26032 + 16.0 + d6 * 2.0 || d4 > \u26033 + 16.0 + d6 * 2.0)) {
                    int n7 = ns.c(d2 - d6) - n2 * 16 - 1;
                    \u2603 = ns.c(d2 + d6) - n2 * 16 + 1;
                    \u2603 = ns.c(d3 - \u2603) - 1;
                    \u2603 = ns.c(d3 + \u2603) + 1;
                    \u2603 = ns.c(d4 - d6) - n3 * 16 - 1;
                    \u2603 = ns.c(d4 + d6) - n3 * 16 + 1;
                    if (n7 < 0) {
                        n7 = 0;
                    }
                    if (\u2603 > 16) {
                        \u2603 = 16;
                    }
                    if (\u2603 < 1) {
                        \u2603 = 1;
                    }
                    if (\u2603 > 248) {
                        \u2603 = 248;
                    }
                    if (\u2603 < 0) {
                        \u2603 = 0;
                    }
                    if (\u2603 > 16) {
                        \u2603 = 16;
                    }
                    boolean \u26039 = false;
                    for (\u2603 = n7; !\u26039 && \u2603 < \u2603; ++\u2603) {
                        for (i2 = \u2603; !\u26039 && i2 < \u2603; ++i2) {
                            for (\u260310 = \u2603 + 1; !\u26039 && \u260310 >= \u2603 - 1; --\u260310) {
                                if (\u260310 < 0 || \u260310 >= 256) continue;
                                alz alz2 = ans2.a(\u2603, \u260310, i2);
                                if (alz2.c() == afi.i || alz2.c() == afi.j) {
                                    \u26039 = true;
                                }
                                if (\u260310 == \u2603 - 1 || \u2603 == n7 || \u2603 == \u2603 - 1 || i2 == \u2603 || i2 == \u2603 - 1) continue;
                                int \u260310 = \u2603;
                            }
                        }
                    }
                    if (!\u26039) {
                        cj.a a2 = new cj.a();
                        for (int i2 = n7; i2 < \u2603; ++i2) {
                            double d7 = ((double)(i2 + n2 * 16) + 0.5 - d2) / d6;
                            for (int i3 = \u2603; i3 < \u2603; ++i3) {
                                double d8 = ((double)(i3 + n3 * 16) + 0.5 - d4) / d6;
                                boolean \u260311 = false;
                                if (!(d7 * d7 + d8 * d8 < 1.0)) continue;
                                for (int i4 = \u2603; i4 > \u2603; --i4) {
                                    double d9 = ((double)(i4 - 1) + 0.5 - d3) / \u2603;
                                    if (!((d7 * d7 + d8 * d8) * (double)this.d[i4 - 1] + d9 * d9 / 6.0 < 1.0)) continue;
                                    alz \u260312 = ans2.a(i2, i4, i3);
                                    if (\u260312.c() == afi.c) {
                                        \u260311 = true;
                                    }
                                    if (\u260312.c() != afi.b && \u260312.c() != afi.d && \u260312.c() != afi.c) continue;
                                    if (i4 - 1 < 10) {
                                        ans2.a(i2, i4, i3, afi.k.Q());
                                        continue;
                                    }
                                    ans2.a(i2, i4, i3, afi.a.Q());
                                    if (!\u260311 || ans2.a(i2, i4 - 1, i3).c() != afi.d) continue;
                                    a2.c(i2 + n2 * 16, 0, i3 + n3 * 16);
                                    ans2.a(i2, i4 - 1, i3, this.c.b((cj)a2).ak);
                                }
                            }
                        }
                        if (n6 != 0) break;
                    }
                }
            }
            ++n4;
        }
    }

    @Override
    protected void a(adm adm2, int n2, int n3, int n4, int n5, ans ans2) {
        if (this.b.nextInt(50) != 0) {
            return;
        }
        double d2 = n2 * 16 + this.b.nextInt(16);
        \u2603 = this.b.nextInt(this.b.nextInt(40) + 8) + 20;
        \u2603 = n3 * 16 + this.b.nextInt(16);
        int \u26032 = 1;
        for (int i2 = 0; i2 < \u26032; ++i2) {
            float f2 = this.b.nextFloat() * (float)Math.PI * 2.0f;
            \u2603 = (this.b.nextFloat() - 0.5f) * 2.0f / 8.0f;
            \u2603 = (this.b.nextFloat() * 2.0f + this.b.nextFloat()) * 2.0f;
            this.a(this.b.nextLong(), n4, n5, ans2, d2, \u2603, \u2603, \u2603, f2, \u2603, 0, 0, 3.0);
        }
    }
}

