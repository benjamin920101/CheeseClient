/*
 * Decompiled with CFR 0.152.
 */
public class zb
extends zw {
    private afh a;

    public zb(afh afh2) {
        this.a = afh2;
        this.a(yz.d);
    }

    @Override
    public boolean a(zx zx2, wn wn22, adm adm2, cj cj22, cq cq2, float f2, float f3, float f4) {
        wn wn22;
        if (cq2 != cq.b) {
            return false;
        }
        alz alz2 = adm2.p(cj22);
        afh \u26032 = alz2.c();
        if (!\u26032.a(adm2, cj22)) {
            cj cj22 = cj22.a(cq2);
        }
        if (!wn22.a(cj22, cq2, zx2)) {
            return false;
        }
        if (!this.a.d(adm2, cj22)) {
            return false;
        }
        zb.a(adm2, cj22, cq.a(wn22.y), this.a);
        --zx2.b;
        return true;
    }

    public static void a(adm adm2, cj cj2, cq cq2, afh afh2) {
        cj cj3 = cj2.a(cq2.e());
        \u2603 = cj2.a(cq2.f());
        int \u26032 = (adm2.p(\u2603).c().v() ? 1 : 0) + (adm2.p(\u2603.a()).c().v() ? 1 : 0);
        int \u26033 = (adm2.p(cj3).c().v() ? 1 : 0) + (adm2.p(cj3.a()).c().v() ? 1 : 0);
        boolean \u26034 = adm2.p(\u2603).c() == afh2 || adm2.p(\u2603.a()).c() == afh2;
        boolean \u26035 = adm2.p(cj3).c() == afh2 || adm2.p(cj3.a()).c() == afh2;
        boolean \u26036 = false;
        if (\u26034 && !\u26035 || \u26033 > \u26032) {
            \u26036 = true;
        }
        \u2603 = cj2.a();
        alz \u26037 = afh2.Q().a(agh.a, cq2).a(agh.N, \u26036 ? agh.b.b : agh.b.a);
        adm2.a(cj2, \u26037.a(agh.P, agh.a.b), 2);
        adm2.a(\u2603, \u26037.a(agh.P, agh.a.a), 2);
        adm2.c(cj2, afh2);
        adm2.c(\u2603, afh2);
    }
}

