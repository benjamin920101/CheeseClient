/*
 * Decompiled with CFR 0.152.
 */
public class abb
extends aaz {
    public abb(afh afh2) {
        super(afh2, false);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        auh auh2 = this.a(adm2, wn2, true);
        if (auh2 == null) {
            return zx2;
        }
        if (auh2.a == auh.a.b) {
            cj cj2 = auh2.a();
            if (!adm2.a(wn2, cj2)) {
                return zx2;
            }
            if (!wn2.a(cj2.a(auh2.b), auh2.b, zx2)) {
                return zx2;
            }
            \u2603 = cj2.a();
            alz \u26032 = adm2.p(cj2);
            if (\u26032.c().t() == arm.h && \u26032.b(ahv.b) == 0 && adm2.d(\u2603)) {
                adm2.a(\u2603, afi.bx.Q());
                if (!wn2.bA.d) {
                    --zx2.b;
                }
                wn2.b(na.ad[zw.b(this)]);
            }
        }
        return zx2;
    }

    @Override
    public int a(zx zx2, int n2) {
        return afi.bx.h(afi.bx.a(zx2.i()));
    }
}

