/*
 * Decompiled with CFR 0.152.
 */
public class zr
extends zw {
    public zr() {
        this.h = 1;
        this.d(64);
        this.a(yz.i);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (!wn2.a(cj2 = cj2.a(cq2), cq2, zx2)) {
            return false;
        }
        if (adm2.p(cj2).c().t() == arm.a) {
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "fire.ignite", 1.0f, g.nextFloat() * 0.4f + 0.8f);
            adm2.a(cj2, afi.ab.Q());
        }
        zx2.a(1, (pr)wn2);
        return true;
    }
}

