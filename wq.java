/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class wq
extends pk
implements wv {
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private afh g;
    private int h;
    private boolean i;
    public int a;
    public int b;
    public pk c;
    private int ar;
    private int as;
    private double at = 2.0;
    private int au;

    public wq(adm adm2) {
        super(adm2);
        this.j = 10.0;
        this.a(0.5f, 0.5f);
    }

    public wq(adm adm2, double d2, double d3, double d4) {
        super(adm2);
        this.j = 10.0;
        this.a(0.5f, 0.5f);
        this.b(d2, d3, d4);
    }

    public wq(adm adm2, pr pr2, pr pr3, float f2, float f3) {
        super(adm2);
        this.j = 10.0;
        this.c = pr2;
        if (pr2 instanceof wn) {
            this.a = 1;
        }
        this.t = pr2.t + (double)pr2.aS() - (double)0.1f;
        double d2 = pr3.s - pr2.s;
        \u2603 = pr3.aR().b + (double)(pr3.K / 3.0f) - this.t;
        \u2603 = pr3.u - pr2.u;
        \u2603 = ns.a(d2 * d2 + \u2603 * \u2603);
        if (\u2603 < 1.0E-7) {
            return;
        }
        float \u26032 = (float)(ns.b(\u2603, d2) * 180.0 / 3.1415927410125732) - 90.0f;
        float \u26033 = (float)(-(ns.b(\u2603, \u2603) * 180.0 / 3.1415927410125732));
        \u2603 = d2 / \u2603;
        \u2603 = \u2603 / \u2603;
        this.b(pr2.s + \u2603, this.t, pr2.u + \u2603, \u26032, \u26033);
        float \u26034 = (float)(\u2603 * (double)0.2f);
        this.c(d2, \u2603 + (double)\u26034, \u2603, f2, f3);
    }

    public wq(adm adm2, pr pr2, float f2) {
        super(adm2);
        this.j = 10.0;
        this.c = pr2;
        if (pr2 instanceof wn) {
            this.a = 1;
        }
        this.a(0.5f, 0.5f);
        this.b(pr2.s, pr2.t + (double)pr2.aS(), pr2.u, pr2.y, pr2.z);
        this.s -= (double)(ns.b(this.y / 180.0f * (float)Math.PI) * 0.16f);
        this.t -= (double)0.1f;
        this.u -= (double)(ns.a(this.y / 180.0f * (float)Math.PI) * 0.16f);
        this.b(this.s, this.t, this.u);
        this.v = -ns.a(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI);
        this.x = ns.b(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI);
        this.w = -ns.a(this.z / 180.0f * (float)Math.PI);
        this.c(this.v, this.w, this.x, f2 * 1.5f, 1.0f);
    }

    @Override
    protected void h() {
        this.ac.a(16, Byte.valueOf((byte)0));
    }

    @Override
    public void c(double d2, double d3, double d4, float f2, float f3) {
        \u2603 = ns.a(d2 * d2 + d3 * d3 + d4 * d4);
        d2 /= (double)\u2603;
        d3 /= (double)\u2603;
        d4 /= (double)\u2603;
        d2 += this.V.nextGaussian() * (double)(this.V.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)f3;
        d3 += this.V.nextGaussian() * (double)(this.V.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)f3;
        d4 += this.V.nextGaussian() * (double)(this.V.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)f3;
        this.v = d2 *= (double)f2;
        this.w = d3 *= (double)f2;
        this.x = d4 *= (double)f2;
        \u2603 = ns.a(d2 * d2 + d4 * d4);
        this.A = this.y = (float)(ns.b(d2, d4) * 180.0 / 3.1415927410125732);
        this.B = this.z = (float)(ns.b(d3, \u2603) * 180.0 / 3.1415927410125732);
        this.ar = 0;
    }

    @Override
    public void a(double d2, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        this.b(d2, d3, d4);
        this.b(f2, f3);
    }

    @Override
    public void i(double d2, double d3, double d4) {
        this.v = d2;
        this.w = d3;
        this.x = d4;
        if (this.B == 0.0f && this.A == 0.0f) {
            float f2 = ns.a(d2 * d2 + d4 * d4);
            this.A = this.y = (float)(ns.b(d2, d4) * 180.0 / 3.1415927410125732);
            this.B = this.z = (float)(ns.b(d3, f2) * 180.0 / 3.1415927410125732);
            this.B = this.z;
            this.A = this.y;
            this.b(this.s, this.t, this.u, this.y, this.z);
            this.ar = 0;
        }
    }

    @Override
    public void t_() {
        float f2;
        float \u26037;
        Object \u260311;
        int \u260312;
        Object object;
        afh afh2;
        super.t_();
        if (this.B == 0.0f && this.A == 0.0f) {
            float f3 = ns.a(this.v * this.v + this.x * this.x);
            this.A = this.y = (float)(ns.b(this.v, this.x) * 180.0 / 3.1415927410125732);
            this.B = this.z = (float)(ns.b(this.w, f3) * 180.0 / 3.1415927410125732);
        }
        if ((afh2 = (\u2603 = this.o.p(\u2603 = new cj(this.d, this.e, this.f))).c()).t() != arm.a) {
            afh2.a((adq)this.o, \u2603);
            object = afh2.a(this.o, \u2603, \u2603);
            if (object != null && ((aug)object).a(new aui(this.s, this.t, this.u))) {
                this.i = true;
            }
        }
        if (this.b > 0) {
            --this.b;
        }
        if (this.i) {
            int \u26032 = afh2.c(\u2603);
            if (afh2 != this.g || \u26032 != this.h) {
                this.i = false;
                this.v *= (double)(this.V.nextFloat() * 0.2f);
                this.w *= (double)(this.V.nextFloat() * 0.2f);
                this.x *= (double)(this.V.nextFloat() * 0.2f);
                this.ar = 0;
                this.as = 0;
            } else {
                ++this.ar;
                if (this.ar >= 1200) {
                    this.J();
                }
            }
            return;
        }
        ++this.as;
        object = new aui(this.s, this.t, this.u);
        aui \u26033 = new aui(this.s + this.v, this.t + this.w, this.u + this.x);
        auh \u26034 = this.o.a((aui)object, \u26033, false, true, false);
        object = new aui(this.s, this.t, this.u);
        \u26033 = new aui(this.s + this.v, this.t + this.w, this.u + this.x);
        if (\u26034 != null) {
            \u26033 = new aui(\u26034.c.a, \u26034.c.b, \u26034.c.c);
        }
        \u2603 = null;
        List<pk> \u26035 = this.o.b(this, this.aR().a(this.v, this.w, this.x).b(1.0, 1.0, 1.0));
        double \u26036 = 0.0;
        for (\u260312 = 0; \u260312 < \u26035.size(); ++\u260312) {
            \u260311 = \u26035.get(\u260312);
            if (!((pk)\u260311).ad() || \u260311 == this.c && this.as < 5) continue;
            \u26037 = 0.3f;
            object2 = ((pk)\u260311).aR().b(\u26037, \u26037, \u26037);
            auh \u26038 = ((aug)object2).a((aui)object, \u26033);
            if (\u26038 == null || !((\u2603 = ((aui)object).g(\u26038.c)) < \u26036) && \u26036 != 0.0) continue;
            \u2603 = \u260311;
            \u26036 = \u2603;
        }
        if (\u2603 != null) {
            \u26034 = new auh((pk)\u2603);
        }
        if (\u26034 != null && \u26034.d != null && \u26034.d instanceof wn) {
            wn wn2 = (wn)\u26034.d;
            if (wn2.bA.a || this.c instanceof wn && !((wn)this.c).a(wn2)) {
                \u26034 = null;
            }
        }
        if (\u26034 != null) {
            if (\u26034.d != null) {
                float f4 = ns.a(this.v * this.v + this.w * this.w + this.x * this.x);
                int \u26039 = ns.f((double)f4 * this.at);
                if (this.l()) {
                    \u26039 += this.V.nextInt(\u26039 / 2 + 2);
                }
                ow \u260310 = this.c == null ? ow.a(this, (pk)this) : ow.a(this, this.c);
                if (this.at() && !(\u26034.d instanceof vo)) {
                    \u26034.d.e(5);
                }
                if (\u26034.d.a(\u260310, (float)\u26039)) {
                    if (\u26034.d instanceof pr) {
                        Object object2 = (pr)\u26034.d;
                        if (!this.o.D) {
                            ((pr)object2).o(((pr)object2).bv() + 1);
                        }
                        if (this.au > 0 && (\u2603 = ns.a(this.v * this.v + this.x * this.x)) > 0.0f) {
                            \u26034.d.g(this.v * (double)this.au * (double)0.6f / (double)\u2603, 0.1, this.x * (double)this.au * (double)0.6f / (double)\u2603);
                        }
                        if (this.c instanceof pr) {
                            ack.a((pr)object2, this.c);
                            ack.b((pr)this.c, (pk)object2);
                        }
                        if (this.c != null && \u26034.d != this.c && \u26034.d instanceof wn && this.c instanceof lf) {
                            ((lf)this.c).a.a(new gm(6, 0.0f));
                        }
                    }
                    this.a("random.bowhit", 1.0f, 1.2f / (this.V.nextFloat() * 0.2f + 0.9f));
                    if (!(\u26034.d instanceof vo)) {
                        this.J();
                    }
                } else {
                    this.v *= (double)-0.1f;
                    this.w *= (double)-0.1f;
                    this.x *= (double)-0.1f;
                    this.y += 180.0f;
                    this.A += 180.0f;
                    this.as = 0;
                }
            } else {
                cj cj2 = \u26034.a();
                this.d = cj2.n();
                this.e = cj2.o();
                this.f = cj2.p();
                \u260311 = this.o.p(cj2);
                this.g = \u260311.c();
                this.h = this.g.c((alz)\u260311);
                this.v = (float)(\u26034.c.a - this.s);
                this.w = (float)(\u26034.c.b - this.t);
                this.x = (float)(\u26034.c.c - this.u);
                \u26037 = ns.a(this.v * this.v + this.w * this.w + this.x * this.x);
                this.s -= this.v / (double)\u26037 * (double)0.05f;
                this.t -= this.w / (double)\u26037 * (double)0.05f;
                this.u -= this.x / (double)\u26037 * (double)0.05f;
                this.a("random.bowhit", 1.0f, 1.2f / (this.V.nextFloat() * 0.2f + 0.9f));
                this.i = true;
                this.b = 7;
                this.a(false);
                if (this.g.t() != arm.a) {
                    this.g.a(this.o, cj2, (alz)\u260311, this);
                }
            }
        }
        if (this.l()) {
            for (\u260312 = 0; \u260312 < 4; ++\u260312) {
                this.o.a(cy.j, this.s + this.v * (double)\u260312 / 4.0, this.t + this.w * (double)\u260312 / 4.0, this.u + this.x * (double)\u260312 / 4.0, -this.v, -this.w + 0.2, -this.x, new int[0]);
            }
        }
        this.s += this.v;
        this.t += this.w;
        this.u += this.x;
        float f5 = ns.a(this.v * this.v + this.x * this.x);
        this.y = (float)(ns.b(this.v, this.x) * 180.0 / 3.1415927410125732);
        this.z = (float)(ns.b(this.w, f5) * 180.0 / 3.1415927410125732);
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
        f2 = 0.99f;
        \u2603 = 0.05f;
        if (this.V()) {
            for (int i2 = 0; i2 < 4; ++i2) {
                float f6 = 0.25f;
                this.o.a(cy.e, this.s - this.v * (double)f6, this.t - this.w * (double)f6, this.u - this.x * (double)f6, this.v, this.w, this.x, new int[0]);
            }
            f2 = 0.6f;
        }
        if (this.U()) {
            this.N();
        }
        this.v *= (double)f2;
        this.w *= (double)f2;
        this.x *= (double)f2;
        this.w -= (double)\u2603;
        this.b(this.s, this.t, this.u);
        this.Q();
    }

    @Override
    public void b(dn dn2) {
        dn2.a("xTile", (short)this.d);
        dn2.a("yTile", (short)this.e);
        dn2.a("zTile", (short)this.f);
        dn2.a("life", (short)this.ar);
        jy jy2 = (jy)afh.c.c(this.g);
        dn2.a("inTile", jy2 == null ? "" : jy2.toString());
        dn2.a("inData", (byte)this.h);
        dn2.a("shake", (byte)this.b);
        dn2.a("inGround", (byte)(this.i ? (char)'\u0001' : '\u0000'));
        dn2.a("pickup", (byte)this.a);
        dn2.a("damage", this.at);
    }

    @Override
    public void a(dn dn2) {
        this.d = dn2.e("xTile");
        this.e = dn2.e("yTile");
        this.f = dn2.e("zTile");
        this.ar = dn2.e("life");
        this.g = dn2.b("inTile", 8) ? afh.b(dn2.j("inTile")) : afh.c(dn2.d("inTile") & 0xFF);
        this.h = dn2.d("inData") & 0xFF;
        this.b = dn2.d("shake") & 0xFF;
        boolean bl2 = this.i = dn2.d("inGround") == 1;
        if (dn2.b("damage", 99)) {
            this.at = dn2.i("damage");
        }
        if (dn2.b("pickup", 99)) {
            this.a = dn2.d("pickup");
        } else if (dn2.b("player", 99)) {
            this.a = dn2.n("player") ? 1 : 0;
        }
    }

    @Override
    public void d(wn wn2) {
        boolean bl2;
        if (this.o.D || !this.i || this.b > 0) {
            return;
        }
        boolean bl3 = bl2 = this.a == 1 || this.a == 2 && wn2.bA.d;
        if (this.a == 1 && !wn2.bi.a(new zx(zy.g, 1))) {
            bl2 = false;
        }
        if (bl2) {
            this.a("random.pop", 0.2f, ((this.V.nextFloat() - this.V.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            wn2.a(this, 1);
            this.J();
        }
    }

    @Override
    protected boolean s_() {
        return false;
    }

    public void b(double d2) {
        this.at = d2;
    }

    public double j() {
        return this.at;
    }

    public void a(int n2) {
        this.au = n2;
    }

    @Override
    public boolean aD() {
        return false;
    }

    @Override
    public float aS() {
        return 0.0f;
    }

    public void a(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 1));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFFE));
        }
    }

    public boolean l() {
        byte by = this.ac.a(16);
        return (by & 1) != 0;
    }
}

