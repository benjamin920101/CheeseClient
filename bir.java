/*
 * Decompiled with CFR 0.152.
 */
public class bir
extends bjo<ug> {
    private static final jy e = new jy("textures/entity/endercrystal/endercrystal_beam.png");
    private static final jy j = new jy("textures/entity/enderdragon/dragon_exploding.png");
    private static final jy k = new jy("textures/entity/enderdragon/dragon.png");
    protected bco a;

    public bir(biu biu2) {
        super(biu2, new bco(0.0f), 0.5f);
        this.a = (bco)this.f;
        this.a(new bkv(this));
        this.a(new bku());
    }

    @Override
    protected void a(ug ug2, float f2, float f3, float f4) {
        \u2603 = (float)ug2.b(7, f4)[0];
        \u2603 = (float)(ug2.b(5, f4)[1] - ug2.b(10, f4)[1]);
        bfl.b(-\u2603, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603 * 10.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(0.0f, 0.0f, 1.0f);
        if (ug2.ax > 0) {
            \u2603 = ((float)ug2.ax + f4 - 1.0f) / 20.0f * 1.6f;
            if ((\u2603 = ns.c(\u2603)) > 1.0f) {
                \u2603 = 1.0f;
            }
            bfl.b(\u2603 * this.b(ug2), 0.0f, 0.0f, 1.0f);
        }
    }

    @Override
    protected void a(ug ug2, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (ug2.by > 0) {
            \u2603 = (float)ug2.by / 200.0f;
            bfl.c(515);
            bfl.d();
            bfl.a(516, \u2603);
            this.a(j);
            this.f.a(ug2, f2, f3, f4, f5, f6, f7);
            bfl.a(516, 0.1f);
            bfl.c(514);
        }
        this.c(ug2);
        this.f.a(ug2, f2, f3, f4, f5, f6, f7);
        if (ug2.au > 0) {
            bfl.c(514);
            bfl.x();
            bfl.l();
            bfl.b(770, 771);
            bfl.c(1.0f, 0.0f, 0.0f, 0.5f);
            this.f.a(ug2, f2, f3, f4, f5, f6, f7);
            bfl.w();
            bfl.k();
            bfl.c(515);
        }
    }

    @Override
    public void a(ug ug2, double d2, double d3, double d4, float f2, float f3) {
        bfc.a(ug2, false);
        super.a(ug2, d2, d3, d4, f2, f3);
        if (ug2.bz != null) {
            this.a(ug2, d2, d3, d4, f3);
        }
    }

    protected void a(ug ug2, double d2, double d3, double d4, float f2) {
        \u2603 = (float)ug2.bz.a + f2;
        \u2603 = ns.a(\u2603 * 0.2f) / 2.0f + 0.5f;
        \u2603 = (\u2603 * \u2603 + \u2603) * 0.2f;
        \u2603 = (float)(ug2.bz.s - ug2.s - (ug2.p - ug2.s) * (double)(1.0f - f2));
        \u2603 = (float)((double)\u2603 + ug2.bz.t - 1.0 - ug2.t - (ug2.q - ug2.t) * (double)(1.0f - f2));
        \u2603 = (float)(ug2.bz.u - ug2.u - (ug2.r - ug2.u) * (double)(1.0f - f2));
        \u2603 = ns.c(\u2603 * \u2603 + \u2603 * \u2603);
        \u2603 = ns.c(\u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603);
        bfl.E();
        bfl.b((float)d2, (float)d3 + 2.0f, (float)d4);
        bfl.b((float)(-Math.atan2(\u2603, \u2603)) * 180.0f / (float)Math.PI - 90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b((float)(-Math.atan2(\u2603, \u2603)) * 180.0f / (float)Math.PI - 90.0f, 1.0f, 0.0f, 0.0f);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        avc.a();
        bfl.p();
        this.a(e);
        bfl.j(7425);
        float \u26033 = 0.0f - ((float)ug2.W + f2) * 0.01f;
        float \u26034 = ns.c(\u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603) / 32.0f - ((float)ug2.W + f2) * 0.01f;
        \u26032.a(5, bms.i);
        int \u26035 = 8;
        for (int i2 = 0; i2 <= 8; ++i2) {
            float f3 = ns.a((float)(i2 % 8) * (float)Math.PI * 2.0f / 8.0f) * 0.75f;
            \u2603 = ns.b((float)(i2 % 8) * (float)Math.PI * 2.0f / 8.0f) * 0.75f;
            \u2603 = (float)(i2 % 8) * 1.0f / 8.0f;
            \u26032.b((double)(f3 * 0.2f), (double)(\u2603 * 0.2f), 0.0).a(\u2603, \u26034).b(0, 0, 0, 255).d();
            \u26032.b((double)f3, (double)\u2603, (double)\u2603).a(\u2603, \u26033).b(255, 255, 255, 255).d();
        }
        bfx2.b();
        bfl.o();
        bfl.j(7424);
        avc.b();
        bfl.F();
    }

    @Override
    protected jy a(ug ug2) {
        return k;
    }
}

