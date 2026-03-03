/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class aob
implements amv {
    private Random h;
    private arc i;
    private arc j;
    private arc k;
    public arc a;
    public arc b;
    private adm l;
    private double[] m;
    private ady[] n;
    double[] c;
    double[] d;
    double[] e;
    double[] f;
    double[] g;

    public aob(adm adm2, long l2) {
        this.l = adm2;
        this.h = new Random(l2);
        this.i = new arc(this.h, 16);
        this.j = new arc(this.h, 16);
        this.k = new arc(this.h, 8);
        this.a = new arc(this.h, 10);
        this.b = new arc(this.h, 16);
    }

    public void a(int n2, int n3, ans ans2) {
        int n4 = 2;
        \u2603 = n4 + 1;
        \u2603 = 33;
        \u2603 = n4 + 1;
        this.m = this.a(this.m, n2 * n4, 0, n3 * n4, \u2603, \u2603, \u2603);
        for (\u2603 = 0; \u2603 < n4; ++\u2603) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                for (\u2603 = 0; \u2603 < 32; ++\u2603) {
                    double d2 = 0.25;
                    \u2603 = this.m[((\u2603 + 0) * \u2603 + \u2603 + 0) * \u2603 + \u2603 + 0];
                    \u2603 = this.m[((\u2603 + 0) * \u2603 + \u2603 + 1) * \u2603 + \u2603 + 0];
                    \u2603 = this.m[((\u2603 + 1) * \u2603 + \u2603 + 0) * \u2603 + \u2603 + 0];
                    \u2603 = this.m[((\u2603 + 1) * \u2603 + \u2603 + 1) * \u2603 + \u2603 + 0];
                    \u2603 = (this.m[((\u2603 + 0) * \u2603 + \u2603 + 0) * \u2603 + \u2603 + 1] - \u2603) * d2;
                    \u2603 = (this.m[((\u2603 + 0) * \u2603 + \u2603 + 1) * \u2603 + \u2603 + 1] - \u2603) * d2;
                    \u2603 = (this.m[((\u2603 + 1) * \u2603 + \u2603 + 0) * \u2603 + \u2603 + 1] - \u2603) * d2;
                    \u2603 = (this.m[((\u2603 + 1) * \u2603 + \u2603 + 1) * \u2603 + \u2603 + 1] - \u2603) * d2;
                    for (int i2 = 0; i2 < 4; ++i2) {
                        double d3 = 0.125;
                        \u2603 = \u2603;
                        \u2603 = \u2603;
                        \u2603 = (\u2603 - \u2603) * d3;
                        \u2603 = (\u2603 - \u2603) * d3;
                        for (int i3 = 0; i3 < 8; ++i3) {
                            double d4 = 0.125;
                            \u2603 = \u2603;
                            \u2603 = (\u2603 - \u2603) * d4;
                            for (int i4 = 0; i4 < 8; ++i4) {
                                alz alz2 = null;
                                if (\u2603 > 0.0) {
                                    alz2 = afi.bH.Q();
                                }
                                int \u26032 = i3 + \u2603 * 8;
                                int \u26033 = i2 + \u2603 * 4;
                                int \u26034 = i4 + \u2603 * 8;
                                ans2.a(\u26032, \u26033, \u26034, alz2);
                                \u2603 += \u2603;
                            }
                            \u2603 += \u2603;
                            \u2603 += \u2603;
                        }
                        \u2603 += \u2603;
                        \u2603 += \u2603;
                        \u2603 += \u2603;
                        \u2603 += \u2603;
                    }
                }
            }
        }
    }

    public void a(ans ans2) {
        for (int i2 = 0; i2 < 16; ++i2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                \u2603 = 1;
                n2 = -1;
                alz alz2 = afi.bH.Q();
                \u2603 = afi.bH.Q();
                for (int i3 = 127; i3 >= 0; --i3) {
                    int n2;
                    alz alz3 = ans2.a(i2, i3, \u2603);
                    if (alz3.c().t() == arm.a) {
                        n2 = -1;
                        continue;
                    }
                    if (alz3.c() != afi.b) continue;
                    if (n2 == -1) {
                        if (\u2603 <= 0) {
                            alz2 = afi.a.Q();
                            \u2603 = afi.bH.Q();
                        }
                        n2 = \u2603;
                        if (i3 >= 0) {
                            ans2.a(i2, i3, \u2603, alz2);
                            continue;
                        }
                        ans2.a(i2, i3, \u2603, \u2603);
                        continue;
                    }
                    if (n2 <= 0) continue;
                    --n2;
                    ans2.a(i2, i3, \u2603, \u2603);
                }
            }
        }
    }

    @Override
    public amy d(int n2, int n3) {
        this.h.setSeed((long)n2 * 341873128712L + (long)n3 * 132897987541L);
        ans ans2 = new ans();
        this.n = this.l.v().b(this.n, n2 * 16, n3 * 16, 16, 16);
        this.a(n2, n3, ans2);
        this.a(ans2);
        amy \u26032 = new amy(this.l, ans2, n2, n3);
        byte[] \u26033 = \u26032.k();
        for (int i2 = 0; i2 < \u26033.length; ++i2) {
            \u26033[i2] = (byte)this.n[i2].az;
        }
        \u26032.b();
        return \u26032;
    }

    private double[] a(double[] dArray2, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (dArray2 == null) {
            double[] dArray2 = new double[n5 * n6 * n7];
        }
        double d2 = 684.412;
        \u2603 = 684.412;
        this.f = this.a.a(this.f, n2, n4, n5, n7, 1.121, 1.121, 0.5);
        this.g = this.b.a(this.g, n2, n4, n5, n7, 200.0, 200.0, 0.5);
        this.c = this.k.a(this.c, n2, n3, n4, n5, n6, n7, (d2 *= 2.0) / 80.0, \u2603 / 160.0, d2 / 80.0);
        this.d = this.i.a(this.d, n2, n3, n4, n5, n6, n7, d2, \u2603, d2);
        this.e = this.j.a(this.e, n2, n3, n4, n5, n6, n7, d2, \u2603, d2);
        int \u26032 = 0;
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n7; ++\u2603) {
                float f2 = (float)(i2 + n2) / 1.0f;
                \u2603 = (float)(\u2603 + n4) / 1.0f;
                \u2603 = 100.0f - ns.c(f2 * f2 + \u2603 * \u2603) * 8.0f;
                if (\u2603 > 80.0f) {
                    \u2603 = 80.0f;
                }
                if (\u2603 < -100.0f) {
                    \u2603 = -100.0f;
                }
                for (int i3 = 0; i3 < n6; ++i3) {
                    double d3 = 0.0;
                    \u2603 = this.d[\u26032] / 512.0;
                    \u2603 = this.e[\u26032] / 512.0;
                    \u2603 = (this.c[\u26032] / 10.0 + 1.0) / 2.0;
                    d3 = \u2603 < 0.0 ? \u2603 : (\u2603 > 1.0 ? \u2603 : \u2603 + (\u2603 - \u2603) * \u2603);
                    d3 -= 8.0;
                    d3 += (double)\u2603;
                    int \u26033 = 2;
                    if (i3 > n6 / 2 - \u26033) {
                        \u2603 = (float)(i3 - (n6 / 2 - \u26033)) / 64.0f;
                        \u2603 = ns.a(\u2603, 0.0, 1.0);
                        d3 = d3 * (1.0 - \u2603) + -3000.0 * \u2603;
                    }
                    if (i3 < (\u26033 = 8)) {
                        \u2603 = (float)(\u26033 - i3) / ((float)\u26033 - 1.0f);
                        d3 = d3 * (1.0 - \u2603) + -30.0 * \u2603;
                    }
                    dArray2[\u26032] = d3;
                    ++\u26032;
                }
            }
        }
        return dArray2;
    }

    @Override
    public boolean a(int n2, int n3) {
        return true;
    }

    @Override
    public void a(amv amv2, int n2, int n3) {
        agr.N = true;
        cj cj2 = new cj(n2 * 16, 0, n3 * 16);
        this.l.b(cj2.a(16, 0, 16)).a(this.l, this.l.s, cj2);
        agr.N = false;
    }

    @Override
    public boolean a(amv amv2, amy amy2, int n2, int n3) {
        return false;
    }

    @Override
    public boolean a(boolean bl2, nu nu2) {
        return true;
    }

    @Override
    public void c() {
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public String f() {
        return "RandomLevelSource";
    }

    @Override
    public List<ady.c> a(pt pt2, cj cj2) {
        return this.l.b(cj2).a(pt2);
    }

    @Override
    public cj a(adm adm2, String string, cj cj2) {
        return null;
    }

    @Override
    public int g() {
        return 0;
    }

    @Override
    public void a(amy amy2, int n2, int n3) {
    }

    @Override
    public amy a(cj cj2) {
        return this.d(cj2.n() >> 4, cj2.p() >> 4);
    }
}

