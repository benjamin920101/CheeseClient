/*
 * Decompiled with CFR 0.152.
 */
public class yn
extends zw {
    public yn() {
        this.a(yz.c);
    }

    @Override
    public boolean a(zx zx2, wn wn22, adm adm2, cj cj22, cq cq2, float f2, float f3, float f4) {
        wn wn22;
        if (adm2.D) {
            return true;
        }
        if (cq2 != cq.b) {
            return false;
        }
        alz alz2 = adm2.p(cj22);
        afh \u26032 = alz2.c();
        boolean \u26033 = \u26032.a(adm2, cj22);
        if (!\u26033) {
            cj cj22 = cj22.a();
        }
        int \u26034 = ns.c((double)(wn22.y * 4.0f / 360.0f) + 0.5) & 3;
        cq \u26035 = cq.b(\u26034);
        cj \u26036 = cj22.a(\u26035);
        if (!wn22.a(cj22, cq2, zx2) || !wn22.a(\u26036, cq2, zx2)) {
            return false;
        }
        boolean \u26037 = adm2.p(\u26036).c().a(adm2, \u26036);
        boolean \u26038 = \u26033 || adm2.d(cj22);
        boolean bl2 = \u2603 = \u26037 || adm2.d(\u26036);
        if (\u26038 && \u2603 && adm.a(adm2, cj22.b()) && adm.a(adm2, \u26036.b())) {
            alz alz3 = afi.C.Q().a(afg.b, false).a(afg.O, \u26035).a(afg.a, afg.a.b);
            if (adm2.a(cj22, alz3, 3)) {
                \u2603 = alz3.a(afg.a, afg.a.a);
                adm2.a(\u26036, \u2603, 3);
            }
            --zx2.b;
            return true;
        }
        return false;
    }
}

