/*
 * Decompiled with CFR 0.152.
 */
public class blh
implements blb<ty> {
    private final bkg a;

    public blh(bkg bkg2) {
        this.a = bkg2;
    }

    @Override
    public void a(ty ty2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (ty2.cm() == 0) {
            return;
        }
        bgd bgd2 = ave.A().ae();
        bfl.B();
        bfl.E();
        bfl.b(5.0f + 180.0f * ((bch)this.a.b()).c.f / (float)Math.PI, 1.0f, 0.0f, 0.0f);
        bfl.b(90.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(-0.9375f, -0.625f, -0.9375f);
        float \u26032 = 0.5f;
        bfl.a(\u26032, -\u26032, \u26032);
        int \u26033 = ty2.b(f4);
        int \u26034 = \u26033 % 65536;
        int \u26035 = \u26033 / 65536;
        bqs.a(bqs.r, (float)\u26034 / 1.0f, (float)\u26035 / 1.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.a.a(bmh.g);
        bgd2.a(afi.O.Q(), 1.0f);
        bfl.F();
        bfl.C();
    }

    @Override
    public boolean b() {
        return false;
    }
}

