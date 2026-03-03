/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public class bfn {
    private static final jy a = new jy("textures/map/map_background.png");
    private static final jy b = new jy("textures/misc/underwater.png");
    private final ave c;
    private zx d;
    private float e;
    private float f;
    private final biu g;
    private final bjh h;
    private int i = -1;

    public bfn(ave ave2) {
        this.c = ave2;
        this.g = ave2.af();
        this.h = ave2.ag();
    }

    public void a(pr pr2, zx zx2, bgr.b b2) {
        if (zx2 == null) {
            return;
        }
        zw zw2 = zx2.b();
        afh \u26032 = afh.a(zw2);
        bfl.E();
        if (this.h.a(zx2)) {
            bfl.a(2.0f, 2.0f, 2.0f);
            if (this.a(\u26032)) {
                bfl.a(false);
            }
        }
        this.h.a(zx2, pr2, b2);
        if (this.a(\u26032)) {
            bfl.a(true);
        }
        bfl.F();
    }

    private boolean a(afh afh2) {
        return afh2 != null && afh2.m() == adf.d;
    }

    private void a(float f2, float f3) {
        bfl.E();
        bfl.b(f2, 1.0f, 0.0f, 0.0f);
        bfl.b(f3, 0.0f, 1.0f, 0.0f);
        avc.b();
        bfl.F();
    }

    private void a(bet bet2) {
        int n2 = this.c.f.b(new cj(bet2.s, bet2.t + (double)bet2.aS(), bet2.u), 0);
        float \u26032 = n2 & 0xFFFF;
        float \u26033 = n2 >> 16;
        bqs.a(bqs.r, \u26032, \u26033);
    }

    private void a(bew bew2, float f2) {
        \u2603 = bew2.i + (bew2.g - bew2.i) * f2;
        \u2603 = bew2.h + (bew2.f - bew2.h) * f2;
        bfl.b((bew2.z - \u2603) * 0.1f, 1.0f, 0.0f, 0.0f);
        bfl.b((bew2.y - \u2603) * 0.1f, 0.0f, 1.0f, 0.0f);
    }

    private float c(float f2) {
        \u2603 = 1.0f - f2 / 45.0f + 0.1f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = -ns.b(\u2603 * (float)Math.PI) * 0.5f + 0.5f;
        return \u2603;
    }

    private void a(bln bln2) {
        bfl.E();
        bfl.b(54.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(64.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(-62.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(0.25f, -0.85f, 0.75f);
        bln2.b(this.c.h);
        bfl.F();
    }

    private void b(bln bln2) {
        bfl.E();
        bfl.b(92.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(45.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(41.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(-0.3f, -1.1f, 0.45f);
        bln2.c(this.c.h);
        bfl.F();
    }

    private void b(bet bet2) {
        this.c.P().a(bet2.i());
        biv biv2 = this.g.a(this.c.h);
        bln \u26032 = (bln)biv2;
        if (!bet2.ax()) {
            bfl.p();
            this.a(\u26032);
            this.b(\u26032);
            bfl.o();
        }
    }

    private void a(bet bet2, float f2, float f3, float f4) {
        \u2603 = -0.4f * ns.a(ns.c(f4) * (float)Math.PI);
        \u2603 = 0.2f * ns.a(ns.c(f4) * (float)Math.PI * 2.0f);
        \u2603 = -0.2f * ns.a(f4 * (float)Math.PI);
        bfl.b(\u2603, \u2603, \u2603);
        \u2603 = this.c(f2);
        bfl.b(0.0f, 0.04f, -0.72f);
        bfl.b(0.0f, f3 * -1.2f, 0.0f);
        bfl.b(0.0f, \u2603 * -0.5f, 0.0f);
        bfl.b(90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603 * -85.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(0.0f, 1.0f, 0.0f, 0.0f);
        this.b(bet2);
        \u2603 = ns.a(f4 * f4 * (float)Math.PI);
        \u2603 = ns.a(ns.c(f4) * (float)Math.PI);
        bfl.b(\u2603 * -20.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603 * -20.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(\u2603 * -80.0f, 1.0f, 0.0f, 0.0f);
        bfl.a(0.38f, 0.38f, 0.38f);
        bfl.b(90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(180.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(0.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(-1.0f, -1.0f, 0.0f);
        bfl.a(0.015625f, 0.015625f, 0.015625f);
        this.c.P().a(a);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        GL11.glNormal3f(0.0f, 0.0f, -1.0f);
        \u26032.a(7, bms.g);
        \u26032.b(-7.0, 135.0, 0.0).a(0.0, 1.0).d();
        \u26032.b(135.0, 135.0, 0.0).a(1.0, 1.0).d();
        \u26032.b(135.0, -7.0, 0.0).a(1.0, 0.0).d();
        \u26032.b(-7.0, -7.0, 0.0).a(0.0, 0.0).d();
        bfx2.b();
        atg \u26033 = zy.bd.a(this.d, this.c.f);
        if (\u26033 != null) {
            this.c.o.k().a(\u26033, false);
        }
    }

    private void a(bet bet2, float f2, float f3) {
        \u2603 = -0.3f * ns.a(ns.c(f3) * (float)Math.PI);
        \u2603 = 0.4f * ns.a(ns.c(f3) * (float)Math.PI * 2.0f);
        \u2603 = -0.4f * ns.a(f3 * (float)Math.PI);
        bfl.b(\u2603, \u2603, \u2603);
        bfl.b(0.64000005f, -0.6f, -0.71999997f);
        bfl.b(0.0f, f2 * -0.6f, 0.0f);
        bfl.b(45.0f, 0.0f, 1.0f, 0.0f);
        \u2603 = ns.a(f3 * f3 * (float)Math.PI);
        \u2603 = ns.a(ns.c(f3) * (float)Math.PI);
        bfl.b(\u2603 * 70.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603 * -20.0f, 0.0f, 0.0f, 1.0f);
        this.c.P().a(bet2.i());
        bfl.b(-1.0f, 3.6f, 3.5f);
        bfl.b(120.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(200.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(-135.0f, 0.0f, 1.0f, 0.0f);
        bfl.a(1.0f, 1.0f, 1.0f);
        bfl.b(5.6f, 0.0f, 0.0f);
        biv biv2 = this.g.a(this.c.h);
        bfl.p();
        bln \u26032 = (bln)biv2;
        \u26032.b(this.c.h);
        bfl.o();
    }

    private void d(float f2) {
        \u2603 = -0.4f * ns.a(ns.c(f2) * (float)Math.PI);
        \u2603 = 0.2f * ns.a(ns.c(f2) * (float)Math.PI * 2.0f);
        \u2603 = -0.2f * ns.a(f2 * (float)Math.PI);
        bfl.b(\u2603, \u2603, \u2603);
    }

    private void a(bet bet2, float f2) {
        \u2603 = (float)bet2.bR() - f2 + 1.0f;
        \u2603 = \u2603 / (float)this.d.l();
        \u2603 = ns.e(ns.b(\u2603 / 4.0f * (float)Math.PI) * 0.1f);
        if (\u2603 >= 0.8f) {
            \u2603 = 0.0f;
        }
        bfl.b(0.0f, \u2603, 0.0f);
        \u2603 = 1.0f - (float)Math.pow(\u2603, 27.0);
        bfl.b(\u2603 * 0.6f, \u2603 * -0.5f, \u2603 * 0.0f);
        bfl.b(\u2603 * 90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603 * 10.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(\u2603 * 30.0f, 0.0f, 0.0f, 1.0f);
    }

    private void b(float f2, float f3) {
        bfl.b(0.56f, -0.52f, -0.71999997f);
        bfl.b(0.0f, f2 * -0.6f, 0.0f);
        bfl.b(45.0f, 0.0f, 1.0f, 0.0f);
        \u2603 = ns.a(f3 * f3 * (float)Math.PI);
        \u2603 = ns.a(ns.c(f3) * (float)Math.PI);
        bfl.b(\u2603 * -20.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603 * -20.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(\u2603 * -80.0f, 1.0f, 0.0f, 0.0f);
        bfl.a(0.4f, 0.4f, 0.4f);
    }

    private void a(float f2, bet bet2) {
        bfl.b(-18.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(-12.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(-8.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(-0.9f, 0.2f, 0.0f);
        float f3 = (float)this.d.l() - ((float)bet2.bR() - f2 + 1.0f);
        \u2603 = f3 / 20.0f;
        \u2603 = (\u2603 * \u2603 + \u2603 * 2.0f) / 3.0f;
        if (\u2603 > 1.0f) {
            \u2603 = 1.0f;
        }
        if (\u2603 > 0.1f) {
            \u2603 = ns.a((f3 - 0.1f) * 1.3f);
            \u2603 = \u2603 - 0.1f;
            \u2603 = \u2603 * \u2603;
            bfl.b(\u2603 * 0.0f, \u2603 * 0.01f, \u2603 * 0.0f);
        }
        bfl.b(\u2603 * 0.0f, \u2603 * 0.0f, \u2603 * 0.1f);
        bfl.a(1.0f, 1.0f, 1.0f + \u2603 * 0.2f);
    }

    private void d() {
        bfl.b(-0.5f, 0.2f, 0.0f);
        bfl.b(30.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(-80.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(60.0f, 0.0f, 1.0f, 0.0f);
    }

    public void a(float f2) {
        f3 = 1.0f - (this.f + (this.e - this.f) * f2);
        bew bew2 = this.c.h;
        float \u26032 = bew2.l(f2);
        float \u26033 = bew2.B + (bew2.z - bew2.B) * f2;
        float \u26034 = bew2.A + (bew2.y - bew2.A) * f2;
        this.a(\u26033, \u26034);
        this.a(bew2);
        this.a(bew2, f2);
        bfl.B();
        bfl.E();
        if (this.d != null) {
            if (this.d.b() == zy.bd) {
                this.a(bew2, \u26033, f3, \u26032);
            } else if (bew2.bR() > 0) {
                aba aba2 = this.d.m();
                switch (aba2) {
                    case a: {
                        this.b(f3, 0.0f);
                        break;
                    }
                    case b: 
                    case c: {
                        this.a((bet)bew2, f2);
                        this.b(f3, 0.0f);
                        break;
                    }
                    case d: {
                        this.b(f3, 0.0f);
                        this.d();
                        break;
                    }
                    case e: {
                        this.b(f3, 0.0f);
                        this.a(f2, bew2);
                    }
                }
            } else {
                float f3;
                this.d(\u26032);
                this.b(f3, \u26032);
            }
            this.a((pr)bew2, this.d, bgr.b.c);
        } else if (!bew2.ax()) {
            this.a(bew2, f3, \u26032);
        }
        bfl.F();
        bfl.C();
        avc.a();
    }

    public void b(float f2) {
        bfl.c();
        if (this.c.h.aj()) {
            alz \u26035 = this.c.f.p(new cj(this.c.h));
            bew \u26032 = this.c.h;
            for (int i2 = 0; i2 < 8; ++i2) {
                double d2 = \u26032.s + (double)(((float)((i2 >> 0) % 2) - 0.5f) * \u26032.J * 0.8f);
                \u2603 = \u26032.t + (double)(((float)((i2 >> 1) % 2) - 0.5f) * 0.1f);
                \u2603 = \u26032.u + (double)(((float)((i2 >> 2) % 2) - 0.5f) * \u26032.J * 0.8f);
                cj \u26033 = new cj(d2, \u2603 + (double)\u26032.aS(), \u2603);
                alz \u26034 = this.c.f.p(\u26033);
                if (!\u26034.c().w()) continue;
                \u26035 = \u26034;
            }
            if (\u26035.c().b() != -1) {
                this.a(f2, this.c.ae().a().a(\u26035));
            }
        }
        if (!this.c.h.v()) {
            if (this.c.h.a(arm.h)) {
                this.e(f2);
            }
            if (this.c.h.at()) {
                this.f(f2);
            }
        }
        bfl.d();
    }

    private void a(float f2, bmi bmi2) {
        this.c.P().a(bmh.g);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        float \u26033 = 0.1f;
        bfl.c(0.1f, 0.1f, 0.1f, 0.5f);
        bfl.E();
        float \u26034 = -1.0f;
        float \u26035 = 1.0f;
        float \u26036 = -1.0f;
        float \u26037 = 1.0f;
        float \u26038 = -0.5f;
        float \u26039 = bmi2.e();
        float \u260310 = bmi2.f();
        float \u260311 = bmi2.g();
        float \u260312 = bmi2.h();
        \u26032.a(7, bms.g);
        \u26032.b(-1.0, -1.0, -0.5).a(\u260310, \u260312).d();
        \u26032.b(1.0, -1.0, -0.5).a(\u26039, \u260312).d();
        \u26032.b(1.0, 1.0, -0.5).a(\u26039, \u260311).d();
        \u26032.b(-1.0, 1.0, -0.5).a(\u260310, \u260311).d();
        bfx2.b();
        bfl.F();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void e(float f2) {
        this.c.P().a(b);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        float \u26033 = this.c.h.c(f2);
        bfl.c(\u26033, \u26033, \u26033, 0.5f);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.E();
        float \u26034 = 4.0f;
        float \u26035 = -1.0f;
        float \u26036 = 1.0f;
        float \u26037 = -1.0f;
        float \u26038 = 1.0f;
        float \u26039 = -0.5f;
        float \u260310 = -this.c.h.y / 64.0f;
        float \u260311 = this.c.h.z / 64.0f;
        \u26032.a(7, bms.g);
        \u26032.b(-1.0, -1.0, -0.5).a(4.0f + \u260310, 4.0f + \u260311).d();
        \u26032.b(1.0, -1.0, -0.5).a(0.0f + \u260310, 4.0f + \u260311).d();
        \u26032.b(1.0, 1.0, -0.5).a(0.0f + \u260310, 0.0f + \u260311).d();
        \u26032.b(-1.0, 1.0, -0.5).a(4.0f + \u260310, 0.0f + \u260311).d();
        bfx2.b();
        bfl.F();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
    }

    private void f(float f2) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        bfl.c(1.0f, 1.0f, 1.0f, 0.9f);
        bfl.c(519);
        bfl.a(false);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        float \u26033 = 1.0f;
        for (int i2 = 0; i2 < 2; ++i2) {
            bfl.E();
            bmi bmi2 = this.c.T().a("minecraft:blocks/fire_layer_1");
            this.c.P().a(bmh.g);
            float \u26034 = bmi2.e();
            float \u26035 = bmi2.f();
            float \u26036 = bmi2.g();
            float \u26037 = bmi2.h();
            float \u26038 = (0.0f - \u26033) / 2.0f;
            float \u26039 = \u26038 + \u26033;
            float \u260310 = 0.0f - \u26033 / 2.0f;
            float \u260311 = \u260310 + \u26033;
            float \u260312 = -0.5f;
            bfl.b((float)(-(i2 * 2 - 1)) * 0.24f, -0.3f, 0.0f);
            bfl.b((float)(i2 * 2 - 1) * 10.0f, 0.0f, 1.0f, 0.0f);
            \u26032.a(7, bms.g);
            \u26032.b((double)\u26038, (double)\u260310, (double)\u260312).a(\u26035, \u26037).d();
            \u26032.b((double)\u26039, (double)\u260310, (double)\u260312).a(\u26034, \u26037).d();
            \u26032.b((double)\u26039, (double)\u260311, (double)\u260312).a(\u26034, \u26036).d();
            \u26032.b((double)\u26038, (double)\u260311, (double)\u260312).a(\u26035, \u26036).d();
            bfx2.b();
            bfl.F();
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
        bfl.a(true);
        bfl.c(515);
    }

    public void a() {
        this.f = this.e;
        bew bew2 = this.c.h;
        zx \u26032 = bew2.bi.h();
        boolean \u26033 = false;
        if (this.d != null && \u26032 != null) {
            if (!this.d.c(\u26032)) {
                \u26033 = true;
            }
        } else {
            \u26033 = this.d != null || \u26032 != null;
        }
        float \u26034 = 0.4f;
        float \u26035 = \u26033 ? 0.0f : 1.0f;
        float \u26036 = ns.a(\u26035 - this.e, -\u26034, \u26034);
        this.e += \u26036;
        if (this.e < 0.1f) {
            this.d = \u26032;
            this.i = bew2.bi.c;
        }
    }

    public void b() {
        this.e = 0.0f;
    }

    public void c() {
        this.e = 0.0f;
    }
}

