/*
 * Decompiled with CFR 0.152.
 */
public class agk
extends agg {
    private final cr P = new cn();

    @Override
    protected cr a(zx zx2) {
        return this.P;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new ald();
    }

    @Override
    protected void f(adm adm2, cj cj2) {
        zx zx2;
        cl cl2 = new cl(adm2, cj2);
        alc \u26032 = (alc)cl2.h();
        if (\u26032 == null) {
            return;
        }
        int \u26033 = \u26032.m();
        if (\u26033 < 0) {
            adm2.b(1001, cj2, 0);
            return;
        }
        zx \u26034 = \u26032.a(\u26033);
        if (\u26034 == null) {
            return;
        }
        cq \u26035 = adm2.p(cj2).b(a);
        cj \u26036 = cj2.a(\u26035);
        og \u26037 = alj.b(adm2, \u26036.n(), (double)\u26036.o(), \u26036.p());
        if (\u26037 == null) {
            zx2 = this.P.a(cl2, \u26034);
            if (zx2 != null && zx2.b <= 0) {
                zx2 = null;
            }
        } else {
            zx2 = alj.a(\u26037, \u26034.k().a(1), \u26035.d());
            if (zx2 == null) {
                zx2 = \u26034.k();
                if (--zx2.b <= 0) {
                    zx2 = null;
                }
            } else {
                zx2 = \u26034.k();
            }
        }
        \u26032.a(\u26033, zx2);
    }
}

