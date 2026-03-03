/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.List;

public class ur
extends pk {
    private static final List<us> d = Arrays.asList(new us(new zx(zy.T), 10).a(0.9f), new us(new zx(zy.aF), 10), new us(new zx(zy.aX), 10), new us(new zx(zy.bz), 10), new us(new zx(zy.F), 5), new us(new zx(zy.aR), 2).a(0.9f), new us(new zx(zy.z), 10), new us(new zx(zy.y), 5), new us(new zx(zy.aW, 10, zd.p.b()), 1), new us(new zx(afi.bR), 10), new us(new zx(zy.bt), 10));
    private static final List<us> e = Arrays.asList(new us(new zx(afi.bx), 1), new us(new zx(zy.co), 1), new us(new zx(zy.aA), 1), new us(new zx(zy.f), 1).a(0.25f).a(), new us(new zx(zy.aR), 1).a(0.25f).a(), new us(new zx(zy.aL), 1).a());
    private static final List<us> f = Arrays.asList(new us(new zx(zy.aU, 1, zp.a.a.a()), 60), new us(new zx(zy.aU, 1, zp.a.b.a()), 25), new us(new zx(zy.aU, 1, zp.a.c.a()), 2), new us(new zx(zy.aU, 1, zp.a.d.a()), 13));
    private int g = -1;
    private int h = -1;
    private int i = -1;
    private afh ar;
    private boolean as;
    public int a;
    public wn b;
    private int at;
    private int au;
    private int av;
    private int aw;
    private int ax;
    private float ay;
    public pk c;
    private int az;
    private double aA;
    private double aB;
    private double aC;
    private double aD;
    private double aE;
    private double aF;
    private double aG;
    private double aH;

    public static List<us> j() {
        return f;
    }

    public ur(adm adm2) {
        super(adm2);
        this.a(0.25f, 0.25f);
        this.ah = true;
    }

    public ur(adm adm2, double d2, double d3, double d4, wn wn2) {
        this(adm2);
        this.b(d2, d3, d4);
        this.ah = true;
        this.b = wn2;
        wn2.bG = this;
    }

    public ur(adm adm2, wn wn2) {
        super(adm2);
        this.ah = true;
        this.b = wn2;
        this.b.bG = this;
        this.a(0.25f, 0.25f);
        this.b(wn2.s, wn2.t + (double)wn2.aS(), wn2.u, wn2.y, wn2.z);
        this.s -= (double)(ns.b(this.y / 180.0f * (float)Math.PI) * 0.16f);
        this.t -= (double)0.1f;
        this.u -= (double)(ns.a(this.y / 180.0f * (float)Math.PI) * 0.16f);
        this.b(this.s, this.t, this.u);
        float f2 = 0.4f;
        this.v = -ns.a(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI) * f2;
        this.x = ns.b(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI) * f2;
        this.w = -ns.a(this.z / 180.0f * (float)Math.PI) * f2;
        this.c(this.v, this.w, this.x, 1.5f, 1.0f);
    }

    @Override
    protected void h() {
    }

    @Override
    public boolean a(double d2) {
        \u2603 = this.aR().a() * 4.0;
        if (Double.isNaN(\u2603)) {
            \u2603 = 4.0;
        }
        return d2 < (\u2603 *= 64.0) * \u2603;
    }

    public void c(double d2, double d3, double d4, float f2, float f3) {
        \u2603 = ns.a(d2 * d2 + d3 * d3 + d4 * d4);
        d2 /= (double)\u2603;
        d3 /= (double)\u2603;
        d4 /= (double)\u2603;
        d2 += this.V.nextGaussian() * (double)0.0075f * (double)f3;
        d3 += this.V.nextGaussian() * (double)0.0075f * (double)f3;
        d4 += this.V.nextGaussian() * (double)0.0075f * (double)f3;
        this.v = d2 *= (double)f2;
        this.w = d3 *= (double)f2;
        this.x = d4 *= (double)f2;
        \u2603 = ns.a(d2 * d2 + d4 * d4);
        this.A = this.y = (float)(ns.b(d2, d4) * 180.0 / 3.1415927410125732);
        this.B = this.z = (float)(ns.b(d3, \u2603) * 180.0 / 3.1415927410125732);
        this.at = 0;
    }

    @Override
    public void a(double d2, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        this.aA = d2;
        this.aB = d3;
        this.aC = d4;
        this.aD = f2;
        this.aE = f3;
        this.az = n2;
        this.v = this.aF;
        this.w = this.aG;
        this.x = this.aH;
    }

    @Override
    public void i(double d2, double d3, double d4) {
        this.aF = this.v = d2;
        this.aG = this.w = d3;
        this.aH = this.x = d4;
    }

    @Override
    public void t_() {
        float f2;
        double \u260314;
        Object object;
        super.t_();
        if (this.az > 0) {
            double d2 = this.s + (this.aA - this.s) / (double)this.az;
            \u2603 = this.t + (this.aB - this.t) / (double)this.az;
            \u2603 = this.u + (this.aC - this.u) / (double)this.az;
            \u2603 = ns.g(this.aD - (double)this.y);
            this.y = (float)((double)this.y + \u2603 / (double)this.az);
            this.z = (float)((double)this.z + (this.aE - (double)this.z) / (double)this.az);
            --this.az;
            this.b(d2, \u2603, \u2603);
            this.b(this.y, this.z);
            return;
        }
        if (!this.o.D) {
            object = this.b.bZ();
            if (this.b.I || !this.b.ai() || object == null || ((zx)object).b() != zy.aR || this.h(this.b) > 1024.0) {
                this.J();
                this.b.bG = null;
                return;
            }
            if (this.c != null) {
                if (this.c.I) {
                    this.c = null;
                } else {
                    this.s = this.c.s;
                    this.t = this.c.aR().b + (double)this.c.K * 0.8;
                    this.u = this.c.u;
                    return;
                }
            }
        }
        if (this.a > 0) {
            --this.a;
        }
        if (this.as) {
            if (this.o.p(new cj(this.g, this.h, this.i)).c() == this.ar) {
                ++this.at;
                if (this.at == 1200) {
                    this.J();
                }
                return;
            }
            this.as = false;
            this.v *= (double)(this.V.nextFloat() * 0.2f);
            this.w *= (double)(this.V.nextFloat() * 0.2f);
            this.x *= (double)(this.V.nextFloat() * 0.2f);
            this.at = 0;
            this.au = 0;
        } else {
            ++this.au;
        }
        object = new aui(this.s, this.t, this.u);
        aui \u26032 = new aui(this.s + this.v, this.t + this.w, this.u + this.x);
        auh \u26033 = this.o.a((aui)object, \u26032);
        object = new aui(this.s, this.t, this.u);
        \u26032 = new aui(this.s + this.v, this.t + this.w, this.u + this.x);
        if (\u26033 != null) {
            \u26032 = new aui(\u26033.c.a, \u26033.c.b, \u26033.c.c);
        }
        pk \u26034 = null;
        List<pk> \u26035 = this.o.b(this, this.aR().a(this.v, this.w, this.x).b(1.0, 1.0, 1.0));
        double \u26036 = 0.0;
        for (int i2 = 0; i2 < \u26035.size(); ++i2) {
            pk pk2 = \u26035.get(i2);
            if (!pk2.ad() || pk2 == this.b && this.au < 5) continue;
            float \u26037 = 0.3f;
            aug \u26038 = pk2.aR().b(\u26037, \u26037, \u26037);
            auh \u26039 = \u26038.a((aui)object, \u26032);
            if (\u26039 == null || !((\u2603 = ((aui)object).g(\u26039.c)) < \u26036) && \u26036 != 0.0) continue;
            \u26034 = pk2;
            \u26036 = \u2603;
        }
        if (\u26034 != null) {
            \u26033 = new auh(\u26034);
        }
        if (\u26033 != null) {
            if (\u26033.d != null) {
                if (\u26033.d.a(ow.a(this, (pk)this.b), 0.0f)) {
                    this.c = \u26033.d;
                }
            } else {
                this.as = true;
            }
        }
        if (this.as) {
            return;
        }
        this.d(this.v, this.w, this.x);
        float f3 = ns.a(this.v * this.v + this.x * this.x);
        this.y = (float)(ns.b(this.v, this.x) * 180.0 / 3.1415927410125732);
        this.z = (float)(ns.b(this.w, f3) * 180.0 / 3.1415927410125732);
        while (this.z - this.B < -180.0f) {
            this.B -= 360.0f;
        }
        while (this.z - this.B >= 180.0f) {
            this.B += 360.0f;
        }
        while (this.y - this.A < -180.0f) {
            this.A -= 360.0f;
        }
        while (this.y - this.A >= 180.0f) {
            this.A += 360.0f;
        }
        this.z = this.B + (this.z - this.B) * 0.2f;
        this.y = this.A + (this.y - this.A) * 0.2f;
        f2 = 0.92f;
        if (this.C || this.D) {
            f2 = 0.5f;
        }
        int \u260310 = 5;
        double \u260311 = 0.0;
        for (int i3 = 0; i3 < \u260310; ++i3) {
            aug aug2 = this.aR();
            double \u260312 = aug2.e - aug2.b;
            double \u260313 = aug2.b + \u260312 * (double)i3 / (double)\u260310;
            \u260314 = aug2.b + \u260312 * (double)(i3 + 1) / (double)\u260310;
            \u2603 = new aug(aug2.a, \u260313, aug2.c, aug2.d, \u260314, aug2.f);
            if (!this.o.b(\u2603, arm.h)) continue;
            \u260311 += 1.0 / (double)\u260310;
        }
        if (!this.o.D && \u260311 > 0.0) {
            le le2 = (le)this.o;
            int \u260315 = 1;
            cj \u260316 = new cj(this).a();
            if (this.V.nextFloat() < 0.25f && this.o.C(\u260316)) {
                \u260315 = 2;
            }
            if (this.V.nextFloat() < 0.5f && !this.o.i(\u260316)) {
                --\u260315;
            }
            if (this.av > 0) {
                --this.av;
                if (this.av <= 0) {
                    this.aw = 0;
                    this.ax = 0;
                }
            } else if (this.ax > 0) {
                this.ax -= \u260315;
                if (this.ax <= 0) {
                    this.w -= (double)0.2f;
                    this.a("random.splash", 0.25f, 1.0f + (this.V.nextFloat() - this.V.nextFloat()) * 0.4f);
                    float f4 = ns.c(this.aR().b);
                    le2.a(cy.e, this.s, (double)(f4 + 1.0f), this.u, (int)(1.0f + this.J * 20.0f), (double)this.J, 0.0, (double)this.J, (double)0.2f, new int[0]);
                    le2.a(cy.g, this.s, (double)(f4 + 1.0f), this.u, (int)(1.0f + this.J * 20.0f), (double)this.J, 0.0, (double)this.J, (double)0.2f, new int[0]);
                    this.av = ns.a(this.V, 10, 30);
                } else {
                    this.ay = (float)((double)this.ay + this.V.nextGaussian() * 4.0);
                    float f5 = this.ay * ((float)Math.PI / 180);
                    \u2603 = ns.a(f5);
                    \u2603 = ns.b(f5);
                    \u260314 = this.s + (double)(\u2603 * (float)this.ax * 0.1f);
                    double \u260317 = (float)ns.c(this.aR().b) + 1.0f;
                    afh \u260318 = le2.p(new cj((int)\u260314, (int)\u260317 - 1, (int)(\u2603 = this.u + (double)(\u2603 * (float)this.ax * 0.1f)))).c();
                    if (\u260318 == afi.j || \u260318 == afi.i) {
                        if (this.V.nextFloat() < 0.15f) {
                            le2.a(cy.e, \u260314, \u260317 - (double)0.1f, \u2603, 1, (double)\u2603, 0.1, (double)\u2603, 0.0, new int[0]);
                        }
                        \u2603 = \u2603 * 0.04f;
                        \u2603 = \u2603 * 0.04f;
                        le2.a(cy.g, \u260314, \u260317, \u2603, 0, (double)\u2603, 0.01, (double)(-\u2603), 1.0, new int[0]);
                        le2.a(cy.g, \u260314, \u260317, \u2603, 0, (double)(-\u2603), 0.01, (double)\u2603, 1.0, new int[0]);
                    }
                }
            } else if (this.aw > 0) {
                this.aw -= \u260315;
                \u2603 = 0.15f;
                if (this.aw < 20) {
                    \u2603 = (float)((double)\u2603 + (double)(20 - this.aw) * 0.05);
                } else if (this.aw < 40) {
                    \u2603 = (float)((double)\u2603 + (double)(40 - this.aw) * 0.02);
                } else if (this.aw < 60) {
                    \u2603 = (float)((double)\u2603 + (double)(60 - this.aw) * 0.01);
                }
                if (this.V.nextFloat() < \u2603) {
                    \u2603 = ns.a(this.V, 0.0f, 360.0f) * ((float)Math.PI / 180);
                    \u2603 = ns.a(this.V, 25.0f, 60.0f);
                    \u260314 = this.s + (double)(ns.a(\u2603) * \u2603 * 0.1f);
                    afh \u260319 = le2.p(new cj((int)\u260314, (int)(\u2603 = (double)((float)ns.c(this.aR().b) + 1.0f)) - 1, (int)(\u2603 = this.u + (double)(ns.b(\u2603) * \u2603 * 0.1f)))).c();
                    if (\u260319 == afi.j || \u260319 == afi.i) {
                        le2.a(cy.f, \u260314, \u2603, \u2603, 2 + this.V.nextInt(2), (double)0.1f, 0.0, (double)0.1f, 0.0, new int[0]);
                    }
                }
                if (this.aw <= 0) {
                    this.ay = ns.a(this.V, 0.0f, 360.0f);
                    this.ax = ns.a(this.V, 20, 80);
                }
            } else {
                this.aw = ns.a(this.V, 100, 900);
                this.aw -= ack.h(this.b) * 20 * 5;
            }
            if (this.av > 0) {
                this.w -= (double)(this.V.nextFloat() * this.V.nextFloat() * this.V.nextFloat()) * 0.2;
            }
        }
        double d3 = \u260311 * 2.0 - 1.0;
        this.w += (double)0.04f * d3;
        if (\u260311 > 0.0) {
            f2 = (float)((double)f2 * 0.9);
            this.w *= 0.8;
        }
        this.v *= (double)f2;
        this.w *= (double)f2;
        this.x *= (double)f2;
        this.b(this.s, this.t, this.u);
    }

    @Override
    public void b(dn dn2) {
        dn2.a("xTile", (short)this.g);
        dn2.a("yTile", (short)this.h);
        dn2.a("zTile", (short)this.i);
        jy jy2 = (jy)afh.c.c(this.ar);
        dn2.a("inTile", jy2 == null ? "" : jy2.toString());
        dn2.a("shake", (byte)this.a);
        dn2.a("inGround", (byte)(this.as ? (char)'\u0001' : '\u0000'));
    }

    @Override
    public void a(dn dn2) {
        this.g = dn2.e("xTile");
        this.h = dn2.e("yTile");
        this.i = dn2.e("zTile");
        this.ar = dn2.b("inTile", 8) ? afh.b(dn2.j("inTile")) : afh.c(dn2.d("inTile") & 0xFF);
        this.a = dn2.d("shake") & 0xFF;
        this.as = dn2.d("inGround") == 1;
    }

    public int l() {
        if (this.o.D) {
            return 0;
        }
        int \u26032 = 0;
        if (this.c != null) {
            double d2 = this.b.s - this.s;
            \u2603 = this.b.t - this.t;
            \u2603 = this.b.u - this.u;
            \u2603 = ns.a(d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603);
            \u2603 = 0.1;
            this.c.v += d2 * \u2603;
            this.c.w += \u2603 * \u2603 + (double)ns.a(\u2603) * 0.08;
            this.c.x += \u2603 * \u2603;
            \u26032 = 3;
        } else if (this.av > 0) {
            uz uz2 = new uz(this.o, this.s, this.t, this.u, this.m());
            double \u26033 = this.b.s - this.s;
            double \u26034 = this.b.t - this.t;
            double \u26035 = this.b.u - this.u;
            double \u26036 = ns.a(\u26033 * \u26033 + \u26034 * \u26034 + \u26035 * \u26035);
            double \u26037 = 0.1;
            uz2.v = \u26033 * \u26037;
            uz2.w = \u26034 * \u26037 + (double)ns.a(\u26036) * 0.08;
            uz2.x = \u26035 * \u26037;
            this.o.d(uz2);
            this.b.o.d(new pp(this.b.o, this.b.s, this.b.t + 0.5, this.b.u + 0.5, this.V.nextInt(6) + 1));
            \u26032 = 1;
        }
        if (this.as) {
            \u26032 = 2;
        }
        this.J();
        this.b.bG = null;
        return \u26032;
    }

    private zx m() {
        float f2 = this.o.s.nextFloat();
        int \u26032 = ack.g(this.b);
        int \u26033 = ack.h(this.b);
        \u2603 = 0.1f - (float)\u26032 * 0.025f - (float)\u26033 * 0.01f;
        \u2603 = 0.05f + (float)\u26032 * 0.01f - (float)\u26033 * 0.01f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        if (f2 < \u2603) {
            this.b.b(na.D);
            return oa.a(this.V, d).a(this.V);
        }
        if ((f2 -= \u2603) < \u2603) {
            this.b.b(na.E);
            return oa.a(this.V, e).a(this.V);
        }
        f2 -= \u2603;
        this.b.b(na.C);
        return oa.a(this.V, f).a(this.V);
    }

    @Override
    public void J() {
        super.J();
        if (this.b != null) {
            this.b.bG = null;
        }
    }
}

