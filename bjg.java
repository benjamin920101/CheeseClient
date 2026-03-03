/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public class bjg
extends biv<uo> {
    private static final jy a = new jy("textures/map/map_background.png");
    private final ave e = ave.A();
    private final bov f = new bov("item_frame", "normal");
    private final bov g = new bov("item_frame", "map");
    private bjh h;

    public bjg(biu biu2, bjh bjh2) {
        super(biu2);
        this.h = bjh2;
    }

    @Override
    public void a(uo uo2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        cj cj2 = uo2.n();
        double \u26032 = (double)cj2.n() - uo2.s + d2;
        double \u26033 = (double)cj2.o() - uo2.t + d3;
        double \u26034 = (double)cj2.p() - uo2.u + d4;
        bfl.b(\u26032 + 0.5, \u26033 + 0.5, \u26034 + 0.5);
        bfl.b(180.0f - uo2.y, 0.0f, 1.0f, 0.0f);
        this.b.a.a(bmh.g);
        bgd \u26035 = this.e.ae();
        bou \u26036 = \u26035.a().b();
        boq \u26037 = uo2.o() != null && uo2.o().b() == zy.bd ? \u26036.a(this.g) : \u26036.a(this.f);
        bfl.E();
        bfl.b(-0.5f, -0.5f, -0.5f);
        \u26035.b().a(\u26037, 1.0f, 1.0f, 1.0f, 1.0f);
        bfl.F();
        bfl.b(0.0f, 0.0f, 0.4375f);
        this.b(uo2);
        bfl.F();
        this.a(uo2, d2 + (double)((float)uo2.b.g() * 0.3f), d3 - 0.25, d4 + (double)((float)uo2.b.i() * 0.3f));
    }

    @Override
    protected jy a(uo uo2) {
        return null;
    }

    private void b(uo uo2) {
        zx zx2 = uo2.o();
        if (zx2 == null) {
            return;
        }
        uz \u26032 = new uz(uo2.o, 0.0, 0.0, 0.0, zx2);
        zw \u26033 = \u26032.l().b();
        \u26032.l().b = 1;
        \u26032.a = 0.0f;
        bfl.E();
        bfl.f();
        int \u26034 = uo2.p();
        if (\u26033 == zy.bd) {
            \u26034 = \u26034 % 4 * 2;
        }
        bfl.b((float)\u26034 * 360.0f / 8.0f, 0.0f, 0.0f, 1.0f);
        if (\u26033 == zy.bd) {
            this.b.a.a(a);
            bfl.b(180.0f, 0.0f, 0.0f, 1.0f);
            float f2 = 0.0078125f;
            bfl.a(f2, f2, f2);
            bfl.b(-64.0f, -64.0f, 0.0f);
            atg \u26035 = zy.bd.a(\u26032.l(), uo2.o);
            bfl.b(0.0f, 0.0f, -1.0f);
            if (\u26035 != null) {
                this.e.o.k().a(\u26035, true);
            }
        } else {
            bmi \u26036 = null;
            if (\u26033 == zy.aQ) {
                \u26036 = this.e.T().a(bmp.l);
                this.e.P().a(bmh.g);
                if (\u26036 instanceof bmp) {
                    bmp bmp2 = (bmp)\u26036;
                    double \u26037 = bmp2.j;
                    double \u26038 = bmp2.k;
                    bmp2.j = 0.0;
                    bmp2.k = 0.0;
                    bmp2.a(uo2.o, uo2.s, uo2.u, ns.g(180 + uo2.b.b() * 90), false, true);
                    bmp2.j = \u26037;
                    bmp2.k = \u26038;
                } else {
                    \u26036 = null;
                }
            }
            bfl.a(0.5f, 0.5f, 0.5f);
            if (!this.h.a(\u26032.l()) || \u26033 instanceof aat) {
                bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
            }
            bfl.a();
            avc.b();
            this.h.a(\u26032.l(), bgr.b.g);
            avc.a();
            bfl.b();
            if (\u26036 != null && \u26036.k() > 0) {
                \u26036.j();
            }
        }
        bfl.e();
        bfl.F();
    }

    @Override
    protected void a(uo uo2, double d2, double d3, double d4) {
        if (ave.v() && uo2.o() != null && uo2.o().s() && this.b.d == uo2) {
            float f2 = 1.6f;
            \u2603 = 0.016666668f * f2;
            double \u26032 = uo2.h(this.b.c);
            float f3 = \u2603 = uo2.av() ? 32.0f : 64.0f;
            if (\u26032 < (double)(\u2603 * \u2603)) {
                String string = uo2.o().q();
                if (uo2.av()) {
                    avn avn2 = this.c();
                    bfl.E();
                    bfl.b((float)d2 + 0.0f, (float)d3 + uo2.K + 0.5f, (float)d4);
                    GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                    bfl.b(-this.b.e, 0.0f, 1.0f, 0.0f);
                    bfl.b(this.b.f, 1.0f, 0.0f, 0.0f);
                    bfl.a(-\u2603, -\u2603, \u2603);
                    bfl.f();
                    bfl.b(0.0f, 0.25f / \u2603, 0.0f);
                    bfl.a(false);
                    bfl.l();
                    bfl.b(770, 771);
                    bfx \u26033 = bfx.a();
                    bfd \u26034 = \u26033.c();
                    int \u26035 = avn2.a(string) / 2;
                    bfl.x();
                    \u26034.a(7, bms.f);
                    \u26034.b((double)(-\u26035 - 1), -1.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
                    \u26034.b((double)(-\u26035 - 1), 8.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
                    \u26034.b((double)(\u26035 + 1), 8.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
                    \u26034.b((double)(\u26035 + 1), -1.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
                    \u26033.b();
                    bfl.w();
                    bfl.a(true);
                    avn2.a(string, -avn2.a(string) / 2, 0, 0x20FFFFFF);
                    bfl.e();
                    bfl.k();
                    bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
                    bfl.F();
                } else {
                    this.a(uo2, string, d2, d3, d4, 64);
                }
            }
        }
    }
}

