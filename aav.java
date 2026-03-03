/*
 * Decompiled with CFR 0.152.
 */
public class aav
extends yo {
    public aav(afh afh2) {
        super(afh2);
        this.d(0);
        this.a(true);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (zx2.b == 0) {
            return false;
        }
        if (!wn2.a(cj2, cq2, zx2)) {
            return false;
        }
        alz alz2 = adm2.p(cj2);
        afh \u26032 = alz2.c();
        cj \u26033 = cj2;
        if (!(cq2 == cq.b && \u26032 == this.a || \u26032.a(adm2, cj2))) {
            \u26033 = cj2.a(cq2);
            alz2 = adm2.p(\u26033);
            \u26032 = alz2.c();
        }
        if (\u26032 == this.a && (\u2603 = alz2.b(ajp.a).intValue()) <= 7 && (\u2603 = this.a.a(adm2, \u26033, \u2603 = alz2.a(ajp.a, \u2603 + 1))) != null && adm2.b(\u2603) && adm2.a(\u26033, \u2603, 2)) {
            adm2.a((float)\u26033.n() + 0.5f, (double)((float)\u26033.o() + 0.5f), (double)((float)\u26033.p() + 0.5f), this.a.H.b(), (this.a.H.d() + 1.0f) / 2.0f, this.a.H.e() * 0.8f);
            --zx2.b;
            return true;
        }
        return super.a(zx2, wn2, adm2, \u26033, cq2, f2, f3, f4);
    }

    @Override
    public int a(int n2) {
        return n2;
    }
}

