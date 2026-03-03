/*
 * Decompiled with CFR 0.152.
 */
public class zv
extends zw {
    protected zw.a a;

    public zv(zw.a a2) {
        this.a = a2;
        this.h = 1;
        this.d(a2.a());
        this.a(yz.i);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (!wn2.a(cj2.a(cq2), cq2, zx2)) {
            return false;
        }
        alz alz2 = adm2.p(cj2);
        afh \u26032 = alz2.c();
        if (cq2 != cq.a && adm2.p(cj2.a()).c().t() == arm.a) {
            if (\u26032 == afi.c) {
                return this.a(zx2, wn2, adm2, cj2, afi.ak.Q());
            }
            if (\u26032 == afi.d) {
                switch (alz2.b(agf.a)) {
                    case a: {
                        return this.a(zx2, wn2, adm2, cj2, afi.ak.Q());
                    }
                    case b: {
                        return this.a(zx2, wn2, adm2, cj2, afi.d.Q().a(agf.a, agf.a.a));
                    }
                }
            }
        }
        return false;
    }

    protected boolean a(zx zx2, wn wn2, adm adm2, cj cj2, alz alz2) {
        adm2.a((float)cj2.n() + 0.5f, (double)((float)cj2.o() + 0.5f), (double)((float)cj2.p() + 0.5f), alz2.c().H.c(), (alz2.c().H.d() + 1.0f) / 2.0f, alz2.c().H.e() * 0.8f);
        if (adm2.D) {
            return true;
        }
        adm2.a(cj2, alz2);
        zx2.a(1, (pr)wn2);
        return true;
    }

    @Override
    public boolean w_() {
        return true;
    }

    public String g() {
        return this.a.toString();
    }
}

