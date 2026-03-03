/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class afr
extends afh {
    public static final amn a = amn.a("level", 0, 3);

    public afr() {
        super(arm.f, arn.m);
        this.j(this.M.b().a(a, 0));
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.3125f, 1.0f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        float f2 = 0.125f;
        this.a(0.0f, 0.0f, 0.0f, f2, 1.0f, 1.0f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f2);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.a(1.0f - f2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.a(0.0f, 0.0f, 1.0f - f2, 1.0f, 1.0f, 1.0f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.j();
    }

    @Override
    public void j() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        int n2 = alz2.b(a);
        float \u26032 = (float)cj2.o() + (6.0f + (float)(3 * n2)) / 16.0f;
        if (!adm2.D && pk2.at() && n2 > 0 && pk2.aR().b <= (double)\u26032) {
            pk2.N();
            this.a(adm2, cj2, alz2, n2 - 1);
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        zx zx2 = wn2.bi.h();
        if (zx2 == null) {
            return true;
        }
        int \u26032 = alz2.b(a);
        zw \u26033 = zx2.b();
        if (\u26033 == zy.ax) {
            if (\u26032 < 3) {
                if (!wn2.bA.d) {
                    wn2.bi.a(wn2.bi.c, new zx(zy.aw));
                }
                wn2.b(na.I);
                this.a(adm2, cj2, alz2, 3);
            }
            return true;
        }
        if (\u26033 == zy.bA) {
            if (\u26032 > 0) {
                if (!wn2.bA.d) {
                    \u2603 = new zx(zy.bz, 1, 0);
                    if (!wn2.bi.a(\u2603)) {
                        adm2.d(new uz(adm2, (double)cj2.n() + 0.5, (double)cj2.o() + 1.5, (double)cj2.p() + 0.5, \u2603));
                    } else if (wn2 instanceof lf) {
                        ((lf)wn2).a(wn2.bj);
                    }
                    wn2.b(na.J);
                    --zx2.b;
                    if (zx2.b <= 0) {
                        wn2.bi.a(wn2.bi.c, null);
                    }
                }
                this.a(adm2, cj2, alz2, \u26032 - 1);
            }
            return true;
        }
        if (\u26032 > 0 && \u26033 instanceof yj && ((yj)(object = (yj)\u26033)).x_() == yj.a.a && ((yj)object).d_(zx2)) {
            ((yj)object).c(zx2);
            this.a(adm2, cj2, alz2, \u26032 - 1);
            wn2.b(na.K);
            return true;
        }
        if (\u26032 > 0 && \u26033 instanceof ym && aku.c(zx2) > 0) {
            Object object = zx2.k();
            ((zx)object).b = 1;
            aku.e((zx)object);
            if (zx2.b > 1 || wn2.bA.d) {
                if (!wn2.bi.a((zx)object)) {
                    adm2.d(new uz(adm2, (double)cj2.n() + 0.5, (double)cj2.o() + 1.5, (double)cj2.p() + 0.5, (zx)object));
                } else if (wn2 instanceof lf) {
                    ((lf)wn2).a(wn2.bj);
                }
                wn2.b(na.L);
                if (!wn2.bA.d) {
                    --zx2.b;
                }
            } else {
                wn2.bi.a(wn2.bi.c, (zx)object);
            }
            if (!wn2.bA.d) {
                this.a(adm2, cj2, alz2, \u26032 - 1);
            }
            return true;
        }
        return false;
    }

    public void a(adm adm2, cj cj2, alz alz2, int n2) {
        adm2.a(cj2, alz2.a(a, ns.a(n2, 0, 3)), 2);
        adm2.e(cj2, this);
    }

    @Override
    public void k(adm adm2, cj cj2) {
        if (adm2.s.nextInt(20) != 1) {
            return;
        }
        alz alz2 = adm2.p(cj2);
        if (alz2.b(a) < 3) {
            adm2.a(cj2, alz2.a(a), 2);
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.bG;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.bG;
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        return adm2.p(cj2).b(a);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a);
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

