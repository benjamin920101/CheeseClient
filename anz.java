/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class anz
extends any {
    protected void a(long l2, int n2, int n3, ans ans2, double d2, double d3, double d4) {
        this.a(l2, n2, n3, ans2, d2, d3, d4, 1.0f + this.b.nextFloat() * 6.0f, 0.0f, 0.0f, -1, -1, 0.5);
    }

    protected void a(long l2, int n2, int n3, ans ans2, double d2, double d3, double d4, float f2, float f3, float \u260352, int n42, int n5, double d5) {
        int n6;
        \u2603 = n2 * 16 + 8;
        \u2603 = n3 * 16 + 8;
        float f4 = 0.0f;
        \u2603 = 0.0f;
        Random \u26032 = new Random(l2);
        if (n5 <= 0) {
            n6 = this.a * 16 - 16;
            n5 = n6 - \u26032.nextInt(n6 / 4);
        }
        n6 = 0;
        if (n42 == -1) {
            n42 = n5 / 2;
            n6 = 1;
        }
        \u2603 = \u26032.nextInt(n5 / 2) + n5 / 4;
        boolean bl2 = \u2603 = \u26032.nextInt(6) == 0;
        while (n42 < n5) {
            int n42;
            double d6 = 1.5 + (double)(ns.a((float)n42 * (float)Math.PI / (float)n5) * f2 * 1.0f);
            \u2603 = d6 * d5;
            float \u26033 = ns.b(\u260352);
            float \u26034 = ns.a(\u260352);
            d2 += (double)(ns.b(f3) * \u26033);
            d3 += (double)\u26034;
            d4 += (double)(ns.a(f3) * \u26033);
            float \u260352 = \u2603 ? (\u260352 *= 0.92f) : (\u260352 *= 0.7f);
            \u260352 += \u2603 * 0.1f;
            f3 += f4 * 0.1f;
            \u2603 *= 0.9f;
            f4 *= 0.75f;
            \u2603 += (\u26032.nextFloat() - \u26032.nextFloat()) * \u26032.nextFloat() * 2.0f;
            f4 += (\u26032.nextFloat() - \u26032.nextFloat()) * \u26032.nextFloat() * 4.0f;
            if (n6 == 0 && n42 == \u2603 && f2 > 1.0f) {
                this.a(\u26032.nextLong(), n2, n3, ans2, d2, d3, d4, \u26032.nextFloat() * 0.5f + 0.5f, f3 - 1.5707964f, \u260352 / 3.0f, n42, n5, 1.0);
                this.a(\u26032.nextLong(), n2, n3, ans2, d2, d3, d4, \u26032.nextFloat() * 0.5f + 0.5f, f3 + 1.5707964f, \u260352 / 3.0f, n42, n5, 1.0);
                return;
            }
            if (n6 != 0 || \u26032.nextInt(4) != 0) {
                \u2603 = d2 - \u2603;
                \u2603 = d4 - \u2603;
                \u2603 = n5 - n42;
                \u2603 = f2 + 2.0f + 16.0f;
                if (\u2603 * \u2603 + \u2603 * \u2603 - \u2603 * \u2603 > \u2603 * \u2603) {
                    return;
                }
                if (!(d2 < \u2603 - 16.0 - d6 * 2.0 || d4 < \u2603 - 16.0 - d6 * 2.0 || d2 > \u2603 + 16.0 + d6 * 2.0 || d4 > \u2603 + 16.0 + d6 * 2.0)) {
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
                    if (\u2603 > 120) {
                        \u2603 = 120;
                    }
                    if (\u2603 < 0) {
                        \u2603 = 0;
                    }
                    if (\u2603 > 16) {
                        \u2603 = 16;
                    }
                    boolean \u26036 = false;
                    for (i2 = n7; !\u26036 && i2 < \u2603; ++i2) {
                        for (\u2603 = \u2603; !\u26036 && \u2603 < \u2603; ++\u2603) {
                            for (\u26037 = \u2603 + 1; !\u26036 && \u26037 >= \u2603 - 1; --\u26037) {
                                if (\u26037 < 0 || \u26037 >= 128) continue;
                                alz alz2 = ans2.a(i2, \u26037, \u2603);
                                if (alz2.c() == afi.k || alz2.c() == afi.l) {
                                    \u26036 = true;
                                }
                                if (\u26037 == \u2603 - 1 || i2 == n7 || i2 == \u2603 - 1 || \u2603 == \u2603 || \u2603 == \u2603 - 1) continue;
                                int \u26037 = \u2603;
                            }
                        }
                    }
                    if (!\u26036) {
                        for (int i2 = n7; i2 < \u2603; ++i2) {
                            double d7 = ((double)(i2 + n2 * 16) + 0.5 - d2) / d6;
                            for (int i3 = \u2603; i3 < \u2603; ++i3) {
                                double d8 = ((double)(i3 + n3 * 16) + 0.5 - d4) / d6;
                                for (int i4 = \u2603; i4 > \u2603; --i4) {
                                    double d9 = ((double)(i4 - 1) + 0.5 - d3) / \u2603;
                                    if (!(d9 > -0.7) || !(d7 * d7 + d9 * d9 + d8 * d8 < 1.0) || (\u2603 = ans2.a(i2, i4, i3)).c() != afi.aV && \u2603.c() != afi.d && \u2603.c() != afi.c) continue;
                                    ans2.a(i2, i4, i3, afi.a.Q());
                                }
                            }
                        }
                        if (n6 != 0) break;
                    }
                }
            }
            ++n42;
        }
    }

    @Override
    protected void a(adm adm2, int n2, int n3, int n4, int n5, ans ans2) {
        int n6 = this.b.nextInt(this.b.nextInt(this.b.nextInt(10) + 1) + 1);
        if (this.b.nextInt(5) != 0) {
            n6 = 0;
        }
        for (\u2603 = 0; \u2603 < n6; ++\u2603) {
            double d2 = n2 * 16 + this.b.nextInt(16);
            \u2603 = this.b.nextInt(128);
            \u2603 = n3 * 16 + this.b.nextInt(16);
            int \u26032 = 1;
            if (this.b.nextInt(4) == 0) {
                this.a(this.b.nextLong(), n4, n5, ans2, d2, \u2603, \u2603);
                \u26032 += this.b.nextInt(4);
            }
            for (int i2 = 0; i2 < \u26032; ++i2) {
                float f2 = this.b.nextFloat() * (float)Math.PI * 2.0f;
                \u2603 = (this.b.nextFloat() - 0.5f) * 2.0f / 8.0f;
                \u2603 = this.b.nextFloat() * 2.0f + this.b.nextFloat();
                this.a(this.b.nextLong(), n4, n5, ans2, d2, \u2603, \u2603, \u2603 * 2.0f, f2, \u2603, 0, 0, 0.5);
            }
        }
    }
}

