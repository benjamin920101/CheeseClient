/*
 * Decompiled with CFR 0.152.
 */
public class aar
extends zw {
    public aar() {
        this.h = 16;
        this.a(yz.c);
    }

    @Override
    public boolean a(zx zx22, wn wn2, adm adm22, cj cj2, cq cq2, float f2, float f3, float f4) {
        zx zx22;
        if (cq2 == cq.a) {
            return false;
        }
        if (!adm22.p(cj2).c().t().a()) {
            return false;
        }
        if (!wn2.a(cj2 = cj2.a(cq2), cq2, zx22)) {
            return false;
        }
        if (!afi.an.d(adm22, cj2)) {
            return false;
        }
        if (adm22.D) {
            return true;
        }
        if (cq2 == cq.b) {
            int n2 = ns.c((double)((wn2.y + 180.0f) * 16.0f / 360.0f) + 0.5) & 0xF;
            adm22.a(cj2, afi.an.Q().a(ajv.a, n2), 3);
        } else {
            adm adm22;
            adm22.a(cj2, afi.ax.Q().a(akm.a, cq2), 3);
        }
        --zx22.b;
        akw \u26032 = adm22.s(cj2);
        if (\u26032 instanceof aln && !yo.a(adm22, wn2, cj2, zx22)) {
            wn2.a((aln)\u26032);
        }
        return true;
    }
}

