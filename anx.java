/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;
import java.util.Random;

public class anx
extends any {
    protected void a(long l2, int n2, int n3, ans ans2, double d2, double d3, double d4) {
        this.a(l2, n2, n3, ans2, d2, d3, d4, 1.0f + this.b.nextFloat() * 6.0f, 0.0f, 0.0f, -1, -1, 0.5);
    }

    protected void a(long l2, int n2, int n3, ans ans2, double d2, double d3, double d4, float f2, float f3, float \u260352, int n4, int n5, double d5) {
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
        if (n4 == -1) {
            n4 = n5 / 2;
            n6 = 1;
        }
        \u2603 = \u26032.nextInt(n5 / 2) + n5 / 4;
        boolean bl2 = \u2603 = \u26032.nextInt(6) == 0;
        while (n4 < n5) {
            double d6 = 1.5 + (double)(ns.a((float)n4 * (float)Math.PI / (float)n5) * f2 * 1.0f);
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
            if (n6 == 0 && n4 == \u2603 && f2 > 1.0f && n5 > 0) {
                this.a(\u26032.nextLong(), n2, n3, ans2, d2, d3, d4, \u26032.nextFloat() * 0.5f + 0.5f, f3 - 1.5707964f, \u260352 / 3.0f, n4, n5, 1.0);
                this.a(\u26032.nextLong(), n2, n3, ans2, d2, d3, d4, \u26032.nextFloat() * 0.5f + 0.5f, f3 + 1.5707964f, \u260352 / 3.0f, n4, n5, 1.0);
                return;
            }
            if (n6 != 0 || \u26032.nextInt(4) != 0) {
                \u2603 = d2 - \u2603;
                \u2603 = d4 - \u2603;
                \u2603 = n5 - n4;
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
                    if (\u2603 > 248) {
                        \u2603 = 248;
                    }
                    if (\u2603 < 0) {
                        \u2603 = 0;
                    }
                    if (\u2603 > 16) {
                        \u2603 = 16;
                    }
                    boolean \u26036 = false;
                    for (\u2603 = n7; !\u26036 && \u2603 < \u2603; ++\u2603) {
                        for (i2 = \u2603; !\u26036 && i2 < \u2603; ++i2) {
                            for (\u26037 = \u2603 + 1; !\u26036 && \u26037 >= \u2603 - 1; --\u26037) {
                                if (\u26037 < 0 || \u26037 >= 256) continue;
                                alz alz2 = ans2.a(\u2603, \u26037, i2);
                                if (alz2.c() == afi.i || alz2.c() == afi.j) {
                                    \u26036 = true;
                                }
                                if (\u26037 == \u2603 - 1 || \u2603 == n7 || \u2603 == \u2603 - 1 || i2 == \u2603 || i2 == \u2603 - 1) continue;
                                int \u26037 = \u2603;
                            }
                        }
                    }
                    if (!\u26036) {
                        cj.a a2 = new cj.a();
                        for (int i2 = n7; i2 < \u2603; ++i2) {
                            double d7 = ((double)(i2 + n2 * 16) + 0.5 - d2) / d6;
                            for (int i3 = \u2603; i3 < \u2603; ++i3) {
                                double d8 = ((double)(i3 + n3 * 16) + 0.5 - d4) / d6;
                                boolean \u26038 = false;
                                if (!(d7 * d7 + d8 * d8 < 1.0)) continue;
                                for (int i4 = \u2603; i4 > \u2603; --i4) {
                                    double d9 = ((double)(i4 - 1) + 0.5 - d3) / \u2603;
                                    if (!(d9 > -0.7) || !(d7 * d7 + d9 * d9 + d8 * d8 < 1.0)) continue;
                                    alz \u26039 = ans2.a(i2, i4, i3);
                                    alz \u260310 = Objects.firstNonNull(ans2.a(i2, i4 + 1, i3), afi.a.Q());
                                    if (\u26039.c() == afi.c || \u26039.c() == afi.bw) {
                                        \u26038 = true;
                                    }
                                    if (!this.a(\u26039, \u260310)) continue;
                                    if (i4 - 1 < 10) {
                                        ans2.a(i2, i4, i3, afi.l.Q());
                                        continue;
                                    }
                                    ans2.a(i2, i4, i3, afi.a.Q());
                                    if (\u260310.c() == afi.m) {
                                        ans2.a(i2, i4 + 1, i3, \u260310.b(ajh.a) == ajh.a.b ? afi.cM.Q() : afi.A.Q());
                                    }
                                    if (!\u26038 || ans2.a(i2, i4 - 1, i3).c() != afi.d) continue;
                                    a2.c(i2 + n2 * 16, 0, i3 + n3 * 16);
                                    ans2.a(i2, i4 - 1, i3, this.c.b((cj)a2).ak.c().Q());
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

    protected boolean a(alz alz2, alz alz3) {
        if (alz2.c() == afi.b) {
            return true;
        }
        if (alz2.c() == afi.d) {
            return true;
        }
        if (alz2.c() == afi.c) {
            return true;
        }
        if (alz2.c() == afi.cz) {
            return true;
        }
        if (alz2.c() == afi.cu) {
            return true;
        }
        if (alz2.c() == afi.A) {
            return true;
        }
        if (alz2.c() == afi.cM) {
            return true;
        }
        if (alz2.c() == afi.bw) {
            return true;
        }
        if (alz2.c() == afi.aH) {
            return true;
        }
        return (alz2.c() == afi.m || alz2.c() == afi.n) && alz3.c().t() != arm.h;
    }

    @Override
    protected void a(adm adm2, int n2, int n3, int n4, int n5, ans ans2) {
        int n6 = this.b.nextInt(this.b.nextInt(this.b.nextInt(15) + 1) + 1);
        if (this.b.nextInt(7) != 0) {
            n6 = 0;
        }
        for (\u2603 = 0; \u2603 < n6; ++\u2603) {
            double d2 = n2 * 16 + this.b.nextInt(16);
            \u2603 = this.b.nextInt(this.b.nextInt(120) + 8);
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
                if (this.b.nextInt(10) == 0) {
                    \u2603 *= this.b.nextFloat() * this.b.nextFloat() * 3.0f + 1.0f;
                }
                this.a(this.b.nextLong(), n4, n5, ans2, d2, \u2603, \u2603, \u2603, f2, \u2603, 0, 0, 1.0);
            }
        }
    }
}

