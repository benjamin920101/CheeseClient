/*
 * Decompiled with CFR 0.152.
 */
public class aal
extends zw {
    public aal() {
        this.a(yz.d);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        boolean bl2 = adm2.p(cj2).c().a(adm2, cj2);
        cj cj3 = \u2603 = bl2 ? cj2 : cj2.a(cq2);
        if (!wn2.a(\u2603, cq2, zx2)) {
            return false;
        }
        afh \u26032 = adm2.p(\u2603).c();
        if (!adm2.a(\u26032, \u2603, false, cq2, null, zx2)) {
            return false;
        }
        if (afi.af.d(adm2, \u2603)) {
            --zx2.b;
            adm2.a(\u2603, afi.af.Q());
            return true;
        }
        return false;
    }
}

