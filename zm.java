/*
 * Decompiled with CFR 0.152.
 */
public class zm
extends zw {
    public zm() {
        this.a(yz.f);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        if (!wn2.a(cj2 = cj2.a(cq2), cq2, zx2)) {
            return false;
        }
        if (adm2.p(cj2).c().t() == arm.a) {
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "item.fireCharge.use", 1.0f, (g.nextFloat() - g.nextFloat()) * 0.2f + 1.0f);
            adm2.a(cj2, afi.ab.Q());
        }
        if (!wn2.bA.d) {
            --zx2.b;
        }
        return true;
    }
}

