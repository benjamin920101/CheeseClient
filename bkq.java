/*
 * Decompiled with CFR 0.152.
 */
public class bkq
implements blb<vo> {
    private final bis a;

    public bkq(bis bis2) {
        this.a = bis2;
    }

    @Override
    public void a(vo vo2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        alz alz2 = vo2.cm();
        if (alz2.c().t() == arm.a) {
            return;
        }
        bgd \u26032 = ave.A().ae();
        bfl.B();
        bfl.E();
        bfl.b(0.0f, 0.6875f, -0.75f);
        bfl.b(20.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(45.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(0.25f, 0.1875f, 0.25f);
        float \u26033 = 0.5f;
        bfl.a(-\u26033, -\u26033, \u26033);
        int \u26034 = vo2.b(f4);
        int \u26035 = \u26034 % 65536;
        int \u26036 = \u26034 / 65536;
        bqs.a(bqs.r, (float)\u26035 / 1.0f, (float)\u26036 / 1.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.a.a(bmh.g);
        \u26032.a(alz2, 1.0f);
        bfl.F();
        bfl.C();
    }

    @Override
    public boolean b() {
        return false;
    }
}

