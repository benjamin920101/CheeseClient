/*
 * Decompiled with CFR 0.152.
 */
public class yv
extends zw {
    private afh a;

    public yv(afh afh2) {
        this.h = 1;
        this.a = afh2;
        this.a(yz.f);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        boolean bl2 = this.a == afi.a;
        auh \u26032 = this.a(adm2, wn2, bl2);
        if (\u26032 == null) {
            return zx2;
        }
        if (\u26032.a == auh.a.b) {
            cj cj2 = \u26032.a();
            if (!adm2.a(wn2, cj2)) {
                return zx2;
            }
            if (bl2) {
                if (!wn2.a(cj2.a(\u26032.b), \u26032.b, zx2)) {
                    return zx2;
                }
                alz alz2 = adm2.p(cj2);
                arm \u26033 = alz2.c().t();
                if (\u26033 == arm.h && alz2.b(ahv.b) == 0) {
                    adm2.g(cj2);
                    wn2.b(na.ad[zw.b(this)]);
                    return this.a(zx2, wn2, zy.ax);
                }
                if (\u26033 == arm.i && alz2.b(ahv.b) == 0) {
                    adm2.g(cj2);
                    wn2.b(na.ad[zw.b(this)]);
                    return this.a(zx2, wn2, zy.ay);
                }
            } else {
                if (this.a == afi.a) {
                    return new zx(zy.aw);
                }
                cj \u26034 = cj2.a(\u26032.b);
                if (!wn2.a(\u26034, \u26032.b, zx2)) {
                    return zx2;
                }
                if (this.a(adm2, \u26034) && !wn2.bA.d) {
                    wn2.b(na.ad[zw.b(this)]);
                    return new zx(zy.aw);
                }
            }
        }
        return zx2;
    }

    private zx a(zx zx2, wn wn2, zw zw2) {
        if (wn2.bA.d) {
            return zx2;
        }
        if (--zx2.b <= 0) {
            return new zx(zw2);
        }
        if (!wn2.bi.a(new zx(zw2))) {
            wn2.a(new zx(zw2, 1, 0), false);
        }
        return zx2;
    }

    public boolean a(adm adm22, cj cj2) {
        if (this.a == afi.a) {
            return false;
        }
        arm arm2 = adm22.p(cj2).c().t();
        boolean bl2 = \u2603 = !arm2.a();
        if (adm22.d(cj2) || \u2603) {
            if (adm22.t.n() && this.a == afi.i) {
                int n2 = cj2.n();
                \u2603 = cj2.o();
                \u2603 = cj2.p();
                adm22.a((float)n2 + 0.5f, (double)((float)\u2603 + 0.5f), (double)((float)\u2603 + 0.5f), "random.fizz", 0.5f, 2.6f + (adm22.s.nextFloat() - adm22.s.nextFloat()) * 0.8f);
                for (\u2603 = 0; \u2603 < 8; ++\u2603) {
                    adm22.a(cy.m, (double)n2 + Math.random(), (double)\u2603 + Math.random(), (double)\u2603 + Math.random(), 0.0, 0.0, 0.0, new int[0]);
                }
            } else {
                adm adm22;
                if (!adm22.D && \u2603 && !arm2.d()) {
                    adm22.b(cj2, true);
                }
                adm22.a(cj2, this.a.Q(), 3);
            }
            return true;
        }
        return false;
    }
}

