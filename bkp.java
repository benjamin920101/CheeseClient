/*
 * Decompiled with CFR 0.152.
 */
public class bkp
implements blb<bet> {
    private final bln a;

    public bkp(bln bln2) {
        this.a = bln2;
    }

    @Override
    public void a(bet bet2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!bet2.a() || bet2.ax() || !bet2.a(wo.a) || bet2.k() == null) {
            return;
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.a.a(bet2.k());
        bfl.E();
        bfl.b(0.0f, 0.0f, 0.125f);
        double d2 = bet2.bq + (bet2.bt - bet2.bq) * (double)f4 - (bet2.p + (bet2.s - bet2.p) * (double)f4);
        \u2603 = bet2.br + (bet2.bu - bet2.br) * (double)f4 - (bet2.q + (bet2.t - bet2.q) * (double)f4);
        \u2603 = bet2.bs + (bet2.bv - bet2.bs) * (double)f4 - (bet2.r + (bet2.u - bet2.r) * (double)f4);
        float \u26032 = bet2.aJ + (bet2.aI - bet2.aJ) * f4;
        \u2603 = ns.a(\u26032 * (float)Math.PI / 180.0f);
        \u2603 = -ns.b(\u26032 * (float)Math.PI / 180.0f);
        float \u26033 = (float)\u2603 * 10.0f;
        \u26033 = ns.a(\u26033, -6.0f, 32.0f);
        float \u26034 = (float)(d2 * \u2603 + \u2603 * \u2603) * 100.0f;
        float \u26035 = (float)(d2 * \u2603 - \u2603 * \u2603) * 100.0f;
        if (\u26034 < 0.0f) {
            \u26034 = 0.0f;
        }
        float \u26036 = bet2.bn + (bet2.bo - bet2.bn) * f4;
        \u26033 += ns.a((bet2.L + (bet2.M - bet2.L) * f4) * 6.0f) * 32.0f * \u26036;
        if (bet2.av()) {
            \u26033 += 25.0f;
        }
        bfl.b(6.0f + \u26034 / 2.0f + \u26033, 1.0f, 0.0f, 0.0f);
        bfl.b(\u26035 / 2.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(-\u26035 / 2.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
        this.a.g().c(0.0625f);
        bfl.F();
    }

    @Override
    public boolean b() {
        return false;
    }
}

