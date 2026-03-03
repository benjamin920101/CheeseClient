/*
 * Decompiled with CFR 0.152.
 */
public class bjr
extends biv<uq> {
    private static final jy a = new jy("textures/painting/paintings_kristoffer_zetterstrand.png");

    public bjr(biu biu2) {
        super(biu2);
    }

    @Override
    public void a(uq uq2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.b(d2, d3, d4);
        bfl.b(180.0f - f2, 0.0f, 1.0f, 0.0f);
        bfl.B();
        this.c(uq2);
        uq.a a2 = uq2.c;
        float \u26032 = 0.0625f;
        bfl.a(\u26032, \u26032, \u26032);
        this.a(uq2, a2.C, a2.D, a2.E, a2.F);
        bfl.C();
        bfl.F();
        super.a(uq2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(uq uq2) {
        return a;
    }

    private void a(uq uq2, int n2, int n3, int n4, int n5) {
        float f2 = (float)(-n2) / 2.0f;
        \u2603 = (float)(-n3) / 2.0f;
        \u2603 = 0.5f;
        \u2603 = 0.75f;
        \u2603 = 0.8125f;
        \u2603 = 0.0f;
        \u2603 = 0.0625f;
        \u2603 = 0.75f;
        \u2603 = 0.8125f;
        \u2603 = 0.001953125f;
        \u2603 = 0.001953125f;
        \u2603 = 0.7519531f;
        \u2603 = 0.7519531f;
        \u2603 = 0.0f;
        \u2603 = 0.0625f;
        for (int i2 = 0; i2 < n2 / 16; ++i2) {
            for (\u2603 = 0; \u2603 < n3 / 16; ++\u2603) {
                float f3 = f2 + (float)((i2 + 1) * 16);
                \u2603 = f2 + (float)(i2 * 16);
                \u2603 = \u2603 + (float)((\u2603 + 1) * 16);
                \u2603 = \u2603 + (float)(\u2603 * 16);
                this.a(uq2, (f3 + \u2603) / 2.0f, (\u2603 + \u2603) / 2.0f);
                \u2603 = (float)(n4 + n2 - i2 * 16) / 256.0f;
                \u2603 = (float)(n4 + n2 - (i2 + 1) * 16) / 256.0f;
                \u2603 = (float)(n5 + n3 - \u2603 * 16) / 256.0f;
                \u2603 = (float)(n5 + n3 - (\u2603 + 1) * 16) / 256.0f;
                bfx \u26032 = bfx.a();
                bfd \u26033 = \u26032.c();
                \u26033.a(7, bms.j);
                \u26033.b((double)f3, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, 0.0f, -1.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, 0.0f, -1.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, 0.0f, -1.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, 0.0f, -1.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, 0.0f, 1.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, 0.0f, 1.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, 0.0f, 1.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, 0.0f, 1.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, 1.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, 1.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, 1.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, 1.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, -1.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(0.0f, -1.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, -1.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(0.0f, -1.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(-1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(-1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(-1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)f3, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(-1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)(-\u2603)).a(\u2603, \u2603).c(1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(1.0f, 0.0f, 0.0f).d();
                \u26033.b((double)\u2603, (double)\u2603, (double)\u2603).a(\u2603, \u2603).c(1.0f, 0.0f, 0.0f).d();
                \u26032.b();
            }
        }
    }

    private void a(uq uq2, float f2, float f3) {
        int n2 = ns.c(uq2.s);
        \u2603 = ns.c(uq2.t + (double)(f3 / 16.0f));
        \u2603 = ns.c(uq2.u);
        cq \u26032 = uq2.b;
        if (\u26032 == cq.c) {
            n2 = ns.c(uq2.s + (double)(f2 / 16.0f));
        }
        if (\u26032 == cq.e) {
            \u2603 = ns.c(uq2.u - (double)(f2 / 16.0f));
        }
        if (\u26032 == cq.d) {
            n2 = ns.c(uq2.s - (double)(f2 / 16.0f));
        }
        if (\u26032 == cq.f) {
            \u2603 = ns.c(uq2.u + (double)(f2 / 16.0f));
        }
        \u2603 = this.b.b.b(new cj(n2, \u2603, \u2603), 0);
        \u2603 = \u2603 % 65536;
        \u2603 = \u2603 / 65536;
        bqs.a(bqs.r, \u2603, (float)\u2603);
        bfl.c(1.0f, 1.0f, 1.0f);
    }
}

