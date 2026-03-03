/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public abstract class biv<T extends pk> {
    private static final jy a = new jy("textures/misc/shadow.png");
    protected final biu b;
    protected float c;
    protected float d = 1.0f;

    protected biv(biu biu2) {
        this.b = biu2;
    }

    public boolean a(T t2, bia bia2, double d2, double d3, double d4) {
        aug aug2 = ((pk)t2).aR();
        if (aug2.b() || aug2.a() == 0.0) {
            aug2 = new aug(((pk)t2).s - 2.0, ((pk)t2).t - 2.0, ((pk)t2).u - 2.0, ((pk)t2).s + 2.0, ((pk)t2).t + 2.0, ((pk)t2).u + 2.0);
        }
        return ((pk)t2).h(d2, d3, d4) && (((pk)t2).ah || bia2.a(aug2));
    }

    public void a(T t2, double d2, double d3, double d4, float f2, float f3) {
        this.a(t2, d2, d3, d4);
    }

    protected void a(T t2, double d2, double d3, double d4) {
        if (!this.b(t2)) {
            return;
        }
        this.a(t2, ((pk)t2).f_().d(), d2, d3, d4, 64);
    }

    protected boolean b(T t2) {
        return ((pk)t2).aO() && ((pk)t2).l_();
    }

    protected void a(T t2, double d2, double d3, double d4, String string, float f2, double d5) {
        this.a(t2, string, d2, d3, d4, 64);
    }

    protected abstract jy a(T var1);

    protected boolean c(T t2) {
        jy jy2 = this.a(t2);
        if (jy2 == null) {
            return false;
        }
        this.a(jy2);
        return true;
    }

    public void a(jy jy2) {
        this.b.a.a(jy2);
    }

    private void a(pk pk2, double d2, double d3, double d4, float f2) {
        bfl.f();
        bmh bmh2 = ave.A().T();
        bmi \u26032 = bmh2.a("minecraft:blocks/fire_layer_0");
        bmi \u26033 = bmh2.a("minecraft:blocks/fire_layer_1");
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        float \u26034 = pk2.J * 1.4f;
        bfl.a(\u26034, \u26034, \u26034);
        bfx \u26035 = bfx.a();
        bfd \u26036 = \u26035.c();
        float \u26037 = 0.5f;
        float \u26038 = 0.0f;
        float \u26039 = pk2.K / \u26034;
        float \u260310 = (float)(pk2.t - pk2.aR().b);
        bfl.b(-this.b.e, 0.0f, 1.0f, 0.0f);
        bfl.b(0.0f, 0.0f, -0.3f + (float)((int)\u26039) * 0.02f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        float \u260311 = 0.0f;
        int \u260312 = 0;
        \u26036.a(7, bms.g);
        while (\u26039 > 0.0f) {
            bmi bmi2 = \u260312 % 2 == 0 ? \u26032 : \u26033;
            this.a(bmh.g);
            float \u260313 = bmi2.e();
            float \u260314 = bmi2.g();
            float \u260315 = bmi2.f();
            float \u260316 = bmi2.h();
            if (\u260312 / 2 % 2 == 0) {
                float f3 = \u260315;
                \u260315 = \u260313;
                \u260313 = f3;
            }
            \u26036.b((double)(\u26037 - \u26038), (double)(0.0f - \u260310), (double)\u260311).a(\u260315, \u260316).d();
            \u26036.b((double)(-\u26037 - \u26038), (double)(0.0f - \u260310), (double)\u260311).a(\u260313, \u260316).d();
            \u26036.b((double)(-\u26037 - \u26038), (double)(1.4f - \u260310), (double)\u260311).a(\u260313, \u260314).d();
            \u26036.b((double)(\u26037 - \u26038), (double)(1.4f - \u260310), (double)\u260311).a(\u260315, \u260314).d();
            \u26039 -= 0.45f;
            \u260310 -= 0.45f;
            \u26037 *= 0.9f;
            \u260311 += 0.03f;
            ++\u260312;
        }
        \u26035.b();
        bfl.F();
        bfl.e();
    }

    private void c(pk pk22, double d2, double d3, double d4, float f2, float f3) {
        bfl.l();
        bfl.b(770, 771);
        this.b.a.a(a);
        adm adm2 = this.a();
        bfl.a(false);
        float \u26032 = this.c;
        if (pk22 instanceof ps) {
            ps ps2 = (ps)pk22;
            \u26032 *= ps2.bT();
            if (ps2.j_()) {
                \u26032 *= 0.5f;
            }
        }
        double \u26033 = pk22.P + (pk22.s - pk22.P) * (double)f3;
        double \u26034 = pk22.Q + (pk22.t - pk22.Q) * (double)f3;
        double \u26035 = pk22.R + (pk22.u - pk22.R) * (double)f3;
        int \u26036 = ns.c(\u26033 - (double)\u26032);
        int \u26037 = ns.c(\u26033 + (double)\u26032);
        int \u26038 = ns.c(\u26034 - (double)\u26032);
        int \u26039 = ns.c(\u26034);
        int \u260310 = ns.c(\u26035 - (double)\u26032);
        int \u260311 = ns.c(\u26035 + (double)\u26032);
        double \u260312 = d2 - \u26033;
        double \u260313 = d3 - \u26034;
        double \u260314 = d4 - \u26035;
        bfx \u260315 = bfx.a();
        bfd \u260316 = \u260315.c();
        \u260316.a(7, bms.i);
        for (cj cj2 : cj.b(new cj(\u26036, \u26038, \u260310), new cj(\u26037, \u26039, \u260311))) {
            afh afh2 = adm2.p(cj2.b()).c();
            if (afh2.b() == -1 || adm2.l(cj2) <= 3) continue;
            this.a(afh2, d2, d3, d4, cj2, f2, \u26032, \u260312, \u260313, \u260314);
        }
        \u260315.b();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
        bfl.a(true);
    }

    private adm a() {
        return this.b.b;
    }

    private void a(afh afh2, double d2, double d3, double d4, cj cj2, float f2, float f3, double d5, double d6, double d7) {
        if (!afh2.d()) {
            return;
        }
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        double \u26033 = ((double)f2 - (d3 - ((double)cj2.o() + d6)) / 2.0) * 0.5 * (double)this.a().o(cj2);
        if (\u26033 < 0.0) {
            return;
        }
        if (\u26033 > 1.0) {
            \u26033 = 1.0;
        }
        double \u26034 = (double)cj2.n() + afh2.B() + d5;
        double \u26035 = (double)cj2.n() + afh2.C() + d5;
        double \u26036 = (double)cj2.o() + afh2.D() + d6 + 0.015625;
        double \u26037 = (double)cj2.p() + afh2.F() + d7;
        double \u26038 = (double)cj2.p() + afh2.G() + d7;
        float \u26039 = (float)((d2 - \u26034) / 2.0 / (double)f3 + 0.5);
        float \u260310 = (float)((d2 - \u26035) / 2.0 / (double)f3 + 0.5);
        float \u260311 = (float)((d4 - \u26037) / 2.0 / (double)f3 + 0.5);
        float \u260312 = (float)((d4 - \u26038) / 2.0 / (double)f3 + 0.5);
        \u26032.b(\u26034, \u26036, \u26037).a(\u26039, \u260311).a(1.0f, 1.0f, 1.0f, (float)\u26033).d();
        \u26032.b(\u26034, \u26036, \u26038).a(\u26039, \u260312).a(1.0f, 1.0f, 1.0f, (float)\u26033).d();
        \u26032.b(\u26035, \u26036, \u26038).a(\u260310, \u260312).a(1.0f, 1.0f, 1.0f, (float)\u26033).d();
        \u26032.b(\u26035, \u26036, \u26037).a(\u260310, \u260311).a(1.0f, 1.0f, 1.0f, (float)\u26033).d();
    }

    public static void a(aug aug2, double d2, double d3, double d4) {
        bfl.x();
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        \u26032.c(d2, d3, d4);
        \u26032.a(7, bms.h);
        \u26032.b(aug2.a, aug2.e, aug2.c).c(0.0f, 0.0f, -1.0f).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).c(0.0f, 0.0f, -1.0f).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).c(0.0f, 0.0f, -1.0f).d();
        \u26032.b(aug2.a, aug2.b, aug2.c).c(0.0f, 0.0f, -1.0f).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).c(0.0f, 0.0f, 1.0f).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).c(0.0f, 0.0f, 1.0f).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).c(0.0f, 0.0f, 1.0f).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).c(0.0f, 0.0f, 1.0f).d();
        \u26032.b(aug2.a, aug2.b, aug2.c).c(0.0f, -1.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).c(0.0f, -1.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).c(0.0f, -1.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).c(0.0f, -1.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.e, aug2.c).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).c(-1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).c(-1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.e, aug2.c).c(-1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.a, aug2.b, aug2.c).c(-1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).c(1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).c(1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).c(1.0f, 0.0f, 0.0f).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).c(1.0f, 0.0f, 0.0f).d();
        bfx2.b();
        \u26032.c(0.0, 0.0, 0.0);
        bfl.w();
    }

    public void b(pk pk2, double d2, double d3, double d4, float f2, float f3) {
        if (this.b.g == null) {
            return;
        }
        if (this.b.g.W && this.c > 0.0f && !pk2.ax() && this.b.a() && (\u2603 = (float)((1.0 - (\u2603 = this.b.b(pk2.s, pk2.t, pk2.u)) / 256.0) * (double)this.d)) > 0.0f) {
            this.c(pk2, d2, d3, d4, \u2603, f3);
        }
        if (!(!pk2.aJ() || pk2 instanceof wn && ((wn)pk2).v())) {
            this.a(pk2, d2, d3, d4, f3);
        }
    }

    public avn c() {
        return this.b.c();
    }

    protected void a(T t2, String string, double d2, double d3, double d4, int n2) {
        double d5 = ((pk)t2).h(this.b.c);
        if (d5 > (double)(n2 * n2)) {
            return;
        }
        avn \u26032 = this.c();
        float \u26033 = 1.6f;
        float \u26034 = 0.016666668f * \u26033;
        bfl.E();
        bfl.b((float)d2 + 0.0f, (float)d3 + ((pk)t2).K + 0.5f, (float)d4);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        bfl.b(-this.b.e, 0.0f, 1.0f, 0.0f);
        bfl.b(this.b.f, 1.0f, 0.0f, 0.0f);
        bfl.a(-\u26034, -\u26034, \u26034);
        bfl.f();
        bfl.a(false);
        bfl.i();
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfx \u26035 = bfx.a();
        bfd \u26036 = \u26035.c();
        int \u26037 = 0;
        if (string.equals("deadmau5")) {
            \u26037 = -10;
        }
        int \u26038 = \u26032.a(string) / 2;
        bfl.x();
        \u26036.a(7, bms.f);
        \u26036.b((double)(-\u26038 - 1), (double)(-1 + \u26037), 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
        \u26036.b((double)(-\u26038 - 1), (double)(8 + \u26037), 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
        \u26036.b((double)(\u26038 + 1), (double)(8 + \u26037), 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
        \u26036.b((double)(\u26038 + 1), (double)(-1 + \u26037), 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
        \u26035.b();
        bfl.w();
        \u26032.a(string, -\u26032.a(string) / 2, \u26037, 0x20FFFFFF);
        bfl.j();
        bfl.a(true);
        \u26032.a(string, -\u26032.a(string) / 2, \u26037, -1);
        bfl.e();
        bfl.k();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.F();
    }

    public biu d() {
        return this.b;
    }
}

