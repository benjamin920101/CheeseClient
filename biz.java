/*
 * Decompiled with CFR 0.152.
 */
public class biz
extends biv<ur> {
    private static final jy a = new jy("textures/particle/particles.png");

    public biz(biu biu2) {
        super(biu2);
    }

    @Override
    public void a(ur ur22, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        bfl.B();
        bfl.a(0.5f, 0.5f, 0.5f);
        this.c(ur22);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        boolean \u26033 = true;
        int \u26034 = 2;
        float \u26035 = 0.0625f;
        float \u26036 = 0.125f;
        float \u26037 = 0.125f;
        float \u26038 = 0.1875f;
        float \u26039 = 1.0f;
        float \u260310 = 0.5f;
        float \u260311 = 0.5f;
        bfl.b(180.0f - this.b.e, 0.0f, 1.0f, 0.0f);
        bfl.b(-this.b.f, 1.0f, 0.0f, 0.0f);
        \u26032.a(7, bms.j);
        \u26032.b(-0.5, -0.5, 0.0).a(0.0625, 0.1875).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(0.5, -0.5, 0.0).a(0.125, 0.1875).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(0.5, 0.5, 0.0).a(0.125, 0.125).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b(-0.5, 0.5, 0.0).a(0.0625, 0.125).c(0.0f, 1.0f, 0.0f).d();
        bfx2.b();
        bfl.C();
        bfl.F();
        if (ur22.b != null) {
            ur ur22;
            float f4 = ur22.b.l(f3);
            \u2603 = ns.a(ns.c(f4) * (float)Math.PI);
            aui \u260312 = new aui(-0.36, 0.03, 0.35);
            \u260312 = \u260312.a(-(ur22.b.B + (ur22.b.z - ur22.b.B) * f3) * (float)Math.PI / 180.0f);
            \u260312 = \u260312.b(-(ur22.b.A + (ur22.b.y - ur22.b.A) * f3) * (float)Math.PI / 180.0f);
            \u260312 = \u260312.b(\u2603 * 0.5f);
            \u260312 = \u260312.a(-\u2603 * 0.7f);
            double \u260313 = ur22.b.p + (ur22.b.s - ur22.b.p) * (double)f3 + \u260312.a;
            double \u260314 = ur22.b.q + (ur22.b.t - ur22.b.q) * (double)f3 + \u260312.b;
            double \u260315 = ur22.b.r + (ur22.b.u - ur22.b.r) * (double)f3 + \u260312.c;
            double \u260316 = ur22.b.aS();
            if (this.b.g != null && this.b.g.aA > 0 || ur22.b != ave.A().h) {
                \u2603 = (ur22.b.aJ + (ur22.b.aI - ur22.b.aJ) * f3) * (float)Math.PI / 180.0f;
                double d5 = ns.a(\u2603);
                \u2603 = ns.b(\u2603);
                \u2603 = 0.35;
                \u2603 = 0.8;
                \u260313 = ur22.b.p + (ur22.b.s - ur22.b.p) * (double)f3 - \u2603 * 0.35 - d5 * 0.8;
                \u260314 = ur22.b.q + \u260316 + (ur22.b.t - ur22.b.q) * (double)f3 - 0.45;
                \u260315 = ur22.b.r + (ur22.b.u - ur22.b.r) * (double)f3 - d5 * 0.35 + \u2603 * 0.8;
                \u260316 = ur22.b.av() ? -0.1875 : 0.0;
            }
            double \u260317 = ur22.p + (ur22.s - ur22.p) * (double)f3;
            double \u260318 = ur22.q + (ur22.t - ur22.q) * (double)f3 + 0.25;
            double \u260319 = ur22.r + (ur22.u - ur22.r) * (double)f3;
            double \u260320 = (float)(\u260313 - \u260317);
            double \u260321 = (double)((float)(\u260314 - \u260318)) + \u260316;
            double \u260322 = (float)(\u260315 - \u260319);
            bfl.x();
            bfl.f();
            \u26032.a(3, bms.f);
            int \u260323 = 16;
            for (int i2 = 0; i2 <= 16; ++i2) {
                float f5 = (float)i2 / 16.0f;
                \u26032.b(d2 + \u260320 * (double)f5, d3 + \u260321 * (double)(f5 * f5 + f5) * 0.5 + 0.25, d4 + \u260322 * (double)f5).b(0, 0, 0, 255).d();
            }
            bfx2.b();
            bfl.e();
            bfl.w();
            super.a(ur22, d2, d3, d4, f2, f3);
        }
    }

    @Override
    protected jy a(ur ur2) {
        return a;
    }
}

