/*
 * Decompiled with CFR 0.152.
 */
public class aau
extends yo {
    private final ahh b;
    private final ahh c;

    public aau(afh afh2, ahh ahh2, ahh ahh3) {
        super(afh2);
        this.b = ahh2;
        this.c = ahh3;
        this.d(0);
        this.a(true);
    }

    @Override
    public int a(int n2) {
        return n2;
    }

    @Override
    public String e_(zx zx2) {
        return this.b.b(zx2.i());
    }

    @Override
    public boolean a(zx zx22, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        zx zx22;
        if (zx22.b == 0) {
            return false;
        }
        if (!wn2.a(cj2.a(cq2), cq2, zx22)) {
            return false;
        }
        Object object = this.b.a(zx22);
        alz \u26032 = adm2.p(cj2);
        if (\u26032.c() == this.b) {
            amo<?> amo2 = this.b.n();
            Object \u26033 = \u26032.b(amo2);
            ahh.a \u26034 = \u26032.b(ahh.a);
            if ((cq2 == cq.b && \u26034 == ahh.a.b || cq2 == cq.a && \u26034 == ahh.a.a) && \u26033 == object) {
                alz alz2 = this.c.Q().a(amo2, \u26033);
                if (adm2.b(this.c.a(adm2, cj2, alz2)) && adm2.a(cj2, alz2, 3)) {
                    adm2.a((float)cj2.n() + 0.5f, (double)((float)cj2.o() + 0.5f), (double)((float)cj2.p() + 0.5f), this.c.H.b(), (this.c.H.d() + 1.0f) / 2.0f, this.c.H.e() * 0.8f);
                    --zx22.b;
                }
                return true;
            }
        }
        if (this.a(zx22, adm2, cj2.a(cq2), object)) {
            return true;
        }
        return super.a(zx22, wn2, adm2, cj2, cq2, f2, f3, f4);
    }

    @Override
    public boolean a(adm adm2, cj cj2, cq cq2, wn wn2, zx zx2) {
        cj cj3 = cj2;
        amo<?> \u26032 = this.b.n();
        Object \u26033 = this.b.a(zx2);
        alz \u26034 = adm2.p(cj2);
        if (\u26034.c() == this.b) {
            boolean bl2 = \u2603 = \u26034.b(ahh.a) == ahh.a.a;
            if ((cq2 == cq.b && !\u2603 || cq2 == cq.a && \u2603) && \u26033 == \u26034.b(\u26032)) {
                return true;
            }
        }
        if ((\u2603 = adm2.p(cj2 = cj2.a(cq2))).c() == this.b && \u26033 == \u2603.b(\u26032)) {
            return true;
        }
        return super.a(adm2, cj3, cq2, wn2, zx2);
    }

    private boolean a(zx zx2, adm adm2, cj cj2, Object object) {
        alz alz2 = adm2.p(cj2);
        if (alz2.c() == this.b && (\u2603 = alz2.b(this.b.n())) == object) {
            \u2603 = this.c.Q().a(this.b.n(), \u2603);
            if (adm2.b(this.c.a(adm2, cj2, \u2603)) && adm2.a(cj2, \u2603, 3)) {
                adm2.a((float)cj2.n() + 0.5f, (double)((float)cj2.o() + 0.5f), (double)((float)cj2.p() + 0.5f), this.c.H.b(), (this.c.H.d() + 1.0f) / 2.0f, this.c.H.e() * 0.8f);
                --zx2.b;
            }
            return true;
        }
        return false;
    }
}

