/*
 * Decompiled with CFR 0.152.
 */
public class bhi
extends bhd<alu> {
    private final bgd c = ave.A().ae();

    @Override
    public void a(alu alu2, double d2, double d3, double d4, float f2, int n2) {
        cj cj2 = alu2.v();
        alz \u26032 = alu2.b();
        afh \u26033 = \u26032.c();
        if (\u26033.t() == arm.a || alu2.a(f2) >= 1.0f) {
            return;
        }
        bfx \u26034 = bfx.a();
        bfd \u26035 = \u26034.c();
        this.a(bmh.g);
        avc.a();
        bfl.b(770, 771);
        bfl.l();
        bfl.p();
        if (ave.x()) {
            bfl.j(7425);
        } else {
            bfl.j(7424);
        }
        \u26035.a(7, bms.a);
        \u26035.c((double)((float)d2 - (float)cj2.n() + alu2.b(f2)), (double)((float)d3 - (float)cj2.o() + alu2.c(f2)), (double)((float)d4 - (float)cj2.p() + alu2.d(f2)));
        adm \u26036 = this.b();
        if (\u26033 == afi.K && alu2.a(f2) < 0.5f) {
            \u26032 = \u26032.a(alt.N, true);
            this.c.b().a((adq)\u26036, this.c.a(\u26032, \u26036, cj2), \u26032, cj2, \u26035, true);
        } else if (alu2.g() && !alu2.d()) {
            alt.a a2 = \u26033 == afi.F ? alt.a.b : alt.a.a;
            alz \u26037 = afi.K.Q().a(alt.b, a2).a(alt.a, \u26032.b(als.a));
            \u26037 = \u26037.a(alt.N, alu2.a(f2) >= 0.5f);
            this.c.b().a((adq)\u26036, this.c.a(\u26037, \u26036, cj2), \u26037, cj2, \u26035, true);
            \u26035.c((double)((float)d2 - (float)cj2.n()), (double)((float)d3 - (float)cj2.o()), (double)((float)d4 - (float)cj2.p()));
            \u26032.a(als.b, true);
            this.c.b().a((adq)\u26036, this.c.a(\u26032, \u26036, cj2), \u26032, cj2, \u26035, true);
        } else {
            this.c.b().a((adq)\u26036, this.c.a(\u26032, \u26036, cj2), \u26032, cj2, \u26035, false);
        }
        \u26035.c(0.0, 0.0, 0.0);
        \u26034.b();
        avc.b();
    }
}

