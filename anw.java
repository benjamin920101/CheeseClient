/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class anw
implements amv {
    private final adm h;
    private final boolean i;
    private final Random j;
    private double[] k = new double[256];
    private double[] l = new double[256];
    private double[] m = new double[256];
    private double[] n;
    private final arc o;
    private final arc p;
    private final arc q;
    private final arc r;
    private final arc s;
    public final arc a;
    public final arc b;
    private final aow t = new aow();
    private final apd u = new apd();
    private final aox v = new aox();
    private final aot w = new apj(afi.co.Q(), 14, amg.a(afi.aV));
    private final aoy x = new aoy(afi.k, true);
    private final aoy y = new aoy(afi.k, false);
    private final aom z = new aom(afi.P);
    private final aom A = new aom(afi.Q);
    private final aqi B = new aqi();
    private final any C = new anz();
    double[] c;
    double[] d;
    double[] e;
    double[] f;
    double[] g;

    public anw(adm adm2, boolean bl2, long l2) {
        this.h = adm2;
        this.i = bl2;
        this.j = new Random(l2);
        this.o = new arc(this.j, 16);
        this.p = new arc(this.j, 16);
        this.q = new arc(this.j, 8);
        this.r = new arc(this.j, 4);
        this.s = new arc(this.j, 4);
        this.a = new arc(this.j, 10);
        this.b = new arc(this.j, 16);
        adm2.b(63);
    }

    public void a(int n2, int n3, ans ans2) {
        int n4 = 4;
        \u2603 = this.h.F() / 2 + 1;
        \u2603 = n4 + 1;
        \u2603 = 17;
        \u2603 = n4 + 1;
        this.n = this.a(this.n, n2 * n4, 0, n3 * n4, \u2603, \u2603, \u2603);
        for (\u2603 = 0; \u2603 < n4; ++\u2603) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                    double d2 = 0.125;
                    \u2603 = this.n[((\u2603 + 0) * \u2603 + (\u2603 + 0)) * \u2603 + (\u2603 + 0)];
                    \u2603 = this.n[((\u2603 + 0) * \u2603 + (\u2603 + 1)) * \u2603 + (\u2603 + 0)];
                    \u2603 = this.n[((\u2603 + 1) * \u2603 + (\u2603 + 0)) * \u2603 + (\u2603 + 0)];
                    \u2603 = this.n[((\u2603 + 1) * \u2603 + (\u2603 + 1)) * \u2603 + (\u2603 + 0)];
                    \u2603 = (this.n[((\u2603 + 0) * \u2603 + (\u2603 + 0)) * \u2603 + (\u2603 + 1)] - \u2603) * d2;
                    \u2603 = (this.n[((\u2603 + 0) * \u2603 + (\u2603 + 1)) * \u2603 + (\u2603 + 1)] - \u2603) * d2;
                    \u2603 = (this.n[((\u2603 + 1) * \u2603 + (\u2603 + 0)) * \u2603 + (\u2603 + 1)] - \u2603) * d2;
                    \u2603 = (this.n[((\u2603 + 1) * \u2603 + (\u2603 + 1)) * \u2603 + (\u2603 + 1)] - \u2603) * d2;
                    for (int i2 = 0; i2 < 8; ++i2) {
                        double d3 = 0.25;
                        \u2603 = \u2603;
                        \u2603 = \u2603;
                        \u2603 = (\u2603 - \u2603) * d3;
                        \u2603 = (\u2603 - \u2603) * d3;
                        for (int i3 = 0; i3 < 4; ++i3) {
                            double d4 = 0.25;
                            \u2603 = \u2603;
                            \u2603 = (\u2603 - \u2603) * d4;
                            for (int i4 = 0; i4 < 4; ++i4) {
                                alz alz2 = null;
                                if (\u2603 * 8 + i2 < \u2603) {
                                    alz2 = afi.l.Q();
                                }
                                if (\u2603 > 0.0) {
                                    alz2 = afi.aV.Q();
                                }
                                int \u26032 = i3 + \u2603 * 4;
                                int \u26033 = i2 + \u2603 * 8;
                                int \u26034 = i4 + \u2603 * 4;
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

    public void b(int n2, int n3, ans ans2) {
        int n4 = this.h.F() + 1;
        double \u26032 = 0.03125;
        this.k = this.r.a(this.k, n2 * 16, n3 * 16, 0, 16, 16, 1, \u26032, \u26032, 1.0);
        this.l = this.r.a(this.l, n2 * 16, 109, n3 * 16, 16, 1, 16, \u26032, 1.0, \u26032);
        this.m = this.s.a(this.m, n2 * 16, n3 * 16, 0, 16, 16, 1, \u26032 * 2.0, \u26032 * 2.0, \u26032 * 2.0);
        for (\u2603 = 0; \u2603 < 16; ++\u2603) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                boolean bl2 = this.k[\u2603 + \u2603 * 16] + this.j.nextDouble() * 0.2 > 0.0;
                \u2603 = this.l[\u2603 + \u2603 * 16] + this.j.nextDouble() * 0.2 > 0.0;
                int \u26033 = (int)(this.m[\u2603 + \u2603 * 16] / 3.0 + 3.0 + this.j.nextDouble() * 0.25);
                int \u26034 = -1;
                alz \u26035 = afi.aV.Q();
                alz \u26036 = afi.aV.Q();
                for (int i2 = 127; i2 >= 0; --i2) {
                    if (i2 >= 127 - this.j.nextInt(5) || i2 <= this.j.nextInt(5)) {
                        ans2.a(\u2603, i2, \u2603, afi.h.Q());
                        continue;
                    }
                    alz alz2 = ans2.a(\u2603, i2, \u2603);
                    if (alz2.c() == null || alz2.c().t() == arm.a) {
                        \u26034 = -1;
                        continue;
                    }
                    if (alz2.c() != afi.aV) continue;
                    if (\u26034 == -1) {
                        if (\u26033 <= 0) {
                            \u26035 = null;
                            \u26036 = afi.aV.Q();
                        } else if (i2 >= n4 - 4 && i2 <= n4 + 1) {
                            \u26035 = afi.aV.Q();
                            \u26036 = afi.aV.Q();
                            if (\u2603) {
                                \u26035 = afi.n.Q();
                                \u26036 = afi.aV.Q();
                            }
                            if (bl2) {
                                \u26035 = afi.aW.Q();
                                \u26036 = afi.aW.Q();
                            }
                        }
                        if (i2 < n4 && (\u26035 == null || \u26035.c().t() == arm.a)) {
                            \u26035 = afi.l.Q();
                        }
                        \u26034 = \u26033;
                        if (i2 >= n4 - 1) {
                            ans2.a(\u2603, i2, \u2603, \u26035);
                            continue;
                        }
                        ans2.a(\u2603, i2, \u2603, \u26036);
                        continue;
                    }
                    if (\u26034 <= 0) continue;
                    --\u26034;
                    ans2.a(\u2603, i2, \u2603, \u26036);
                }
            }
        }
    }

    @Override
    public amy d(int n2, int n3) {
        this.j.setSeed((long)n2 * 341873128712L + (long)n3 * 132897987541L);
        ans ans2 = new ans();
        this.a(n2, n3, ans2);
        this.b(n2, n3, ans2);
        this.C.a(this, this.h, n2, n3, ans2);
        if (this.i) {
            this.B.a(this, this.h, n2, n3, ans2);
        }
        amy \u26032 = new amy(this.h, ans2, n2, n3);
        ady[] \u26033 = this.h.v().b(null, n2 * 16, n3 * 16, 16, 16);
        byte[] \u26034 = \u26032.k();
        for (int i2 = 0; i2 < \u26034.length; ++i2) {
            \u26034[i2] = (byte)\u26033[i2].az;
        }
        \u26032.l();
        return \u26032;
    }

    private double[] a(double[] dArray2, int n2, int n3, int n4, int n5, int n6, int n7) {
        int n8;
        double[] dArray2;
        if (dArray2 == null) {
            dArray2 = new double[n5 * n6 * n7];
        }
        double d2 = 684.412;
        \u2603 = 2053.236;
        this.f = this.a.a(this.f, n2, n3, n4, n5, 1, n7, 1.0, 0.0, 1.0);
        this.g = this.b.a(this.g, n2, n3, n4, n5, 1, n7, 100.0, 0.0, 100.0);
        this.c = this.q.a(this.c, n2, n3, n4, n5, n6, n7, d2 / 80.0, \u2603 / 60.0, d2 / 80.0);
        this.d = this.o.a(this.d, n2, n3, n4, n5, n6, n7, d2, \u2603, d2);
        this.e = this.p.a(this.e, n2, n3, n4, n5, n6, n7, d2, \u2603, d2);
        int \u26032 = 0;
        double[] \u26033 = new double[n6];
        for (n8 = 0; n8 < n6; ++n8) {
            \u26033[n8] = Math.cos((double)n8 * Math.PI * 6.0 / (double)n6) * 2.0;
            double d3 = n8;
            if (n8 > n6 / 2) {
                d3 = n6 - 1 - n8;
            }
            if (!(d3 < 4.0)) continue;
            d3 = 4.0 - d3;
            int n9 = n8;
            \u26033[n9] = \u26033[n9] - d3 * d3 * d3 * 10.0;
        }
        for (n8 = 0; n8 < n5; ++n8) {
            for (\u2603 = 0; \u2603 < n7; ++\u2603) {
                double d4 = 0.0;
                for (int i2 = 0; i2 < n6; ++i2) {
                    double d5 = 0.0;
                    \u2603 = \u26033[i2];
                    \u2603 = this.d[\u26032] / 512.0;
                    \u2603 = this.e[\u26032] / 512.0;
                    \u2603 = (this.c[\u26032] / 10.0 + 1.0) / 2.0;
                    d5 = \u2603 < 0.0 ? \u2603 : (\u2603 > 1.0 ? \u2603 : \u2603 + (\u2603 - \u2603) * \u2603);
                    d5 -= \u2603;
                    if (i2 > n6 - 4) {
                        \u2603 = (float)(i2 - (n6 - 4)) / 3.0f;
                        d5 = d5 * (1.0 - \u2603) + -10.0 * \u2603;
                    }
                    if ((double)i2 < d4) {
                        \u2603 = (d4 - (double)i2) / 4.0;
                        \u2603 = ns.a(\u2603, 0.0, 1.0);
                        d5 = d5 * (1.0 - \u2603) + -10.0 * \u2603;
                    }
                    dArray2[\u26032] = d5;
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
        int n4;
        agr.N = true;
        cj cj2 = new cj(n2 * 16, 0, n3 * 16);
        adg \u26032 = new adg(n2, n3);
        this.B.a(this.h, this.j, \u26032);
        for (n4 = 0; n4 < 8; ++n4) {
            this.y.b(this.h, this.j, cj2.a(this.j.nextInt(16) + 8, this.j.nextInt(120) + 4, this.j.nextInt(16) + 8));
        }
        for (n4 = 0; n4 < this.j.nextInt(this.j.nextInt(10) + 1) + 1; ++n4) {
            this.t.b(this.h, this.j, cj2.a(this.j.nextInt(16) + 8, this.j.nextInt(120) + 4, this.j.nextInt(16) + 8));
        }
        for (n4 = 0; n4 < this.j.nextInt(this.j.nextInt(10) + 1); ++n4) {
            this.u.b(this.h, this.j, cj2.a(this.j.nextInt(16) + 8, this.j.nextInt(120) + 4, this.j.nextInt(16) + 8));
        }
        for (n4 = 0; n4 < 10; ++n4) {
            this.v.b(this.h, this.j, cj2.a(this.j.nextInt(16) + 8, this.j.nextInt(128), this.j.nextInt(16) + 8));
        }
        if (this.j.nextBoolean()) {
            this.z.b(this.h, this.j, cj2.a(this.j.nextInt(16) + 8, this.j.nextInt(128), this.j.nextInt(16) + 8));
        }
        if (this.j.nextBoolean()) {
            this.A.b(this.h, this.j, cj2.a(this.j.nextInt(16) + 8, this.j.nextInt(128), this.j.nextInt(16) + 8));
        }
        for (n4 = 0; n4 < 16; ++n4) {
            this.w.b(this.h, this.j, cj2.a(this.j.nextInt(16), this.j.nextInt(108) + 10, this.j.nextInt(16)));
        }
        for (n4 = 0; n4 < 16; ++n4) {
            this.x.b(this.h, this.j, cj2.a(this.j.nextInt(16), this.j.nextInt(108) + 10, this.j.nextInt(16)));
        }
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
        return "HellRandomLevelSource";
    }

    @Override
    public List<ady.c> a(pt pt2, cj cj2) {
        if (pt2 == pt.a) {
            if (this.B.b(cj2)) {
                return this.B.b();
            }
            if (this.B.a(this.h, cj2) && this.h.p(cj2.b()).c() == afi.by) {
                return this.B.b();
            }
        }
        ady ady2 = this.h.b(cj2);
        return ady2.a(pt2);
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
        this.B.a(this, this.h, n2, n3, null);
    }

    @Override
    public amy a(cj cj2) {
        return this.d(cj2.n() >> 4, cj2.p() >> 4);
    }
}

