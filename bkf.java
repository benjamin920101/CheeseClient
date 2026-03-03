/*
 * Decompiled with CFR 0.152.
 */
public class bkf
extends biv<vj> {
    public bkf(biu biu2) {
        super(biu2);
        this.c = 0.5f;
    }

    @Override
    public void a(vj vj22, double d2, double d3, double d4, float f2, float f3) {
        vj vj22;
        float \u26032;
        bgd bgd2 = ave.A().ae();
        bfl.E();
        bfl.b((float)d2, (float)d3 + 0.5f, (float)d4);
        if ((float)vj22.a - f3 + 1.0f < 10.0f) {
            \u26032 = 1.0f - ((float)vj22.a - f3 + 1.0f) / 10.0f;
            \u26032 = ns.a(\u26032, 0.0f, 1.0f);
            \u26032 *= \u26032;
            \u26032 *= \u26032;
            \u2603 = 1.0f + \u26032 * 0.3f;
            bfl.a(\u2603, \u2603, \u2603);
        }
        \u26032 = (1.0f - ((float)vj22.a - f3 + 1.0f) / 100.0f) * 0.8f;
        this.c(vj22);
        bfl.b(-0.5f, -0.5f, 0.5f);
        bgd2.a(afi.W.Q(), vj22.c(f3));
        bfl.b(0.0f, 0.0f, 1.0f);
        if (vj22.a / 5 % 2 == 0) {
            bfl.x();
            bfl.f();
            bfl.l();
            bfl.b(770, 772);
            bfl.c(1.0f, 1.0f, 1.0f, \u26032);
            bfl.a(-3.0f, -3.0f);
            bfl.q();
            bgd2.a(afi.W.Q(), 1.0f);
            bfl.a(0.0f, 0.0f);
            bfl.r();
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            bfl.k();
            bfl.e();
            bfl.w();
        }
        bfl.F();
        super.a(vj22, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(vj vj2) {
        return bmh.g;
    }
}

