/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.UUID;

public abstract class wy
extends pk
implements wv {
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private afh f;
    protected boolean a;
    public int b;
    private pr g;
    private String h;
    private int i;
    private int ar;

    public wy(adm adm2) {
        super(adm2);
        this.a(0.25f, 0.25f);
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

    public wy(adm adm2, pr pr2) {
        super(adm2);
        this.g = pr2;
        this.a(0.25f, 0.25f);
        this.b(pr2.s, pr2.t + (double)pr2.aS(), pr2.u, pr2.y, pr2.z);
        this.s -= (double)(ns.b(this.y / 180.0f * (float)Math.PI) * 0.16f);
        this.t -= (double)0.1f;
        this.u -= (double)(ns.a(this.y / 180.0f * (float)Math.PI) * 0.16f);
        this.b(this.s, this.t, this.u);
        float f2 = 0.4f;
        this.v = -ns.a(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI) * f2;
        this.x = ns.b(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI) * f2;
        this.w = -ns.a((this.z + this.l()) / 180.0f * (float)Math.PI) * f2;
        this.c(this.v, this.w, this.x, this.j(), 1.0f);
    }

    public wy(adm adm2, double d2, double d3, double d4) {
        super(adm2);
        this.i = 0;
        this.a(0.25f, 0.25f);
        this.b(d2, d3, d4);
    }

    protected float j() {
        return 1.5f;
    }

    protected float l() {
        return 0.0f;
    }

    @Override
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
        this.i = 0;
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
        }
    }

    @Override
    public void t_() {
        float f2;
        this.P = this.s;
        this.Q = this.t;
        this.R = this.u;
        super.t_();
        if (this.b > 0) {
            --this.b;
        }
        if (this.a) {
            if (this.o.p(new cj(this.c, this.d, this.e)).c() == this.f) {
                ++this.i;
                if (this.i == 1200) {
                    this.J();
                }
                return;
            }
            this.a = false;
            this.v *= (double)(this.V.nextFloat() * 0.2f);
            this.w *= (double)(this.V.nextFloat() * 0.2f);
            this.x *= (double)(this.V.nextFloat() * 0.2f);
            this.i = 0;
            this.ar = 0;
        } else {
            ++this.ar;
        }
        aui aui2 = new aui(this.s, this.t, this.u);
        \u2603 = new aui(this.s + this.v, this.t + this.w, this.u + this.x);
        auh \u26032 = this.o.a(aui2, \u2603);
        aui2 = new aui(this.s, this.t, this.u);
        \u2603 = new aui(this.s + this.v, this.t + this.w, this.u + this.x);
        if (\u26032 != null) {
            \u2603 = new aui(\u26032.c.a, \u26032.c.b, \u26032.c.c);
        }
        if (!this.o.D) {
            pk pk2 = null;
            List<pk> \u26033 = this.o.b(this, this.aR().a(this.v, this.w, this.x).b(1.0, 1.0, 1.0));
            double \u26034 = 0.0;
            pr \u26035 = this.n();
            for (int i2 = 0; i2 < \u26033.size(); ++i2) {
                pk pk3 = \u26033.get(i2);
                if (!pk3.ad() || pk3 == \u26035 && this.ar < 5) continue;
                float \u26036 = 0.3f;
                aug \u26037 = pk3.aR().b(\u26036, \u26036, \u26036);
                auh \u26038 = \u26037.a(aui2, \u2603);
                if (\u26038 == null || !((\u2603 = aui2.g(\u26038.c)) < \u26034) && \u26034 != 0.0) continue;
                pk2 = pk3;
                \u26034 = \u2603;
            }
            if (pk2 != null) {
                \u26032 = new auh(pk2);
            }
        }
        if (\u26032 != null) {
            if (\u26032.a == auh.a.b && this.o.p(\u26032.a()).c() == afi.aY) {
                this.d(\u26032.a());
            } else {
                this.a(\u26032);
            }
        }
        this.s += this.v;
        this.t += this.w;
        this.u += this.x;
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
        f2 = 0.99f;
        \u2603 = this.m();
        if (this.V()) {
            for (int i3 = 0; i3 < 4; ++i3) {
                float f4 = 0.25f;
                this.o.a(cy.e, this.s - this.v * (double)f4, this.t - this.w * (double)f4, this.u - this.x * (double)f4, this.v, this.w, this.x, new int[0]);
            }
            f2 = 0.8f;
        }
        this.v *= (double)f2;
        this.w *= (double)f2;
        this.x *= (double)f2;
        this.w -= (double)\u2603;
        this.b(this.s, this.t, this.u);
    }

    protected float m() {
        return 0.03f;
    }

    protected abstract void a(auh var1);

    @Override
    public void b(dn dn2) {
        dn2.a("xTile", (short)this.c);
        dn2.a("yTile", (short)this.d);
        dn2.a("zTile", (short)this.e);
        jy jy2 = (jy)afh.c.c(this.f);
        dn2.a("inTile", jy2 == null ? "" : jy2.toString());
        dn2.a("shake", (byte)this.b);
        dn2.a("inGround", (byte)(this.a ? (char)'\u0001' : '\u0000'));
        if ((this.h == null || this.h.length() == 0) && this.g instanceof wn) {
            this.h = this.g.e_();
        }
        dn2.a("ownerName", this.h == null ? "" : this.h);
    }

    @Override
    public void a(dn dn2) {
        this.c = dn2.e("xTile");
        this.d = dn2.e("yTile");
        this.e = dn2.e("zTile");
        this.f = dn2.b("inTile", 8) ? afh.b(dn2.j("inTile")) : afh.c(dn2.d("inTile") & 0xFF);
        this.b = dn2.d("shake") & 0xFF;
        this.a = dn2.d("inGround") == 1;
        this.g = null;
        this.h = dn2.j("ownerName");
        if (this.h != null && this.h.length() == 0) {
            this.h = null;
        }
        this.g = this.n();
    }

    public pr n() {
        if (this.g == null && this.h != null && this.h.length() > 0) {
            this.g = this.o.a(this.h);
            if (this.g == null && this.o instanceof le) {
                try {
                    pk pk2 = ((le)this.o).a(UUID.fromString(this.h));
                    if (pk2 instanceof pr) {
                        this.g = (pr)pk2;
                    }
                }
                catch (Throwable throwable) {
                    this.g = null;
                }
            }
        }
        return this.g;
    }
}

