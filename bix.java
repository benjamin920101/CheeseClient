/*
 * Decompiled with CFR 0.152.
 */
public class bix
extends biv<uy> {
    public bix(biu biu2) {
        super(biu2);
        this.c = 0.5f;
    }

    @Override
    public void a(uy uy2, double d2, double d3, double d4, float f2, float f3) {
        if (uy2.l() == null) {
            return;
        }
        this.a(bmh.g);
        alz alz2 = uy2.l();
        afh \u26032 = alz2.c();
        cj \u26033 = new cj(uy2);
        adm \u26034 = uy2.j();
        if (alz2 == \u26034.p(\u26033) || \u26032.b() == -1) {
            return;
        }
        if (\u26032.b() != 3) {
            return;
        }
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        bfl.f();
        bfx \u26035 = bfx.a();
        bfd \u26036 = \u26035.c();
        \u26036.a(7, bms.a);
        int \u26037 = \u26033.n();
        int \u26038 = \u26033.o();
        int \u26039 = \u26033.p();
        \u26036.c((double)((float)(-\u26037) - 0.5f), (double)(-\u26038), (double)((float)(-\u26039) - 0.5f));
        bgd \u260310 = ave.A().ae();
        boq \u260311 = \u260310.a(alz2, \u26034, null);
        \u260310.b().a((adq)\u26034, \u260311, alz2, \u26033, \u26036, false);
        \u26036.c(0.0, 0.0, 0.0);
        \u26035.b();
        bfl.e();
        bfl.F();
        super.a(uy2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(uy uy2) {
        return bmh.g;
    }
}

