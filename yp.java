/*
 * Decompiled with CFR 0.152.
 */
public class yp
extends zw {
    private afh a;

    public yp(afh afh2) {
        this.a = afh2;
    }

    @Override
    public boolean a(zx zx2, wn wn22, adm adm22, cj cj22, cq cq22, float f2, float f3, float f4) {
        wn wn22;
        adm adm22;
        alz alz2 = adm22.p(cj22);
        afh \u26032 = alz2.c();
        if (\u26032 == afi.aH && alz2.b(ajp.a) < 1) {
            cq cq22 = cq.b;
        } else if (!\u26032.a(adm22, cj22)) {
            cj cj22 = cj22.a(cq22);
        }
        if (!wn22.a(cj22, cq22, zx2)) {
            return false;
        }
        if (zx2.b == 0) {
            return false;
        }
        if (adm22.a(this.a, cj22, false, cq22, null, zx2) && adm22.a(cj22, alz3 = this.a.a(adm22, cj22, cq22, f2, f3, f4, 0, wn22), 3)) {
            alz alz3 = adm22.p(cj22);
            if (alz3.c() == this.a) {
                yo.a(adm22, wn22, cj22, zx2);
                alz3.c().a(adm22, cj22, alz3, wn22, zx2);
            }
            adm22.a((float)cj22.n() + 0.5f, (double)((float)cj22.o() + 0.5f), (double)((float)cj22.p() + 0.5f), this.a.H.b(), (this.a.H.d() + 1.0f) / 2.0f, this.a.H.e() * 0.8f);
            --zx2.b;
            return true;
        }
        return false;
    }
}

