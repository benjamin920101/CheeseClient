/*
 * Decompiled with CFR 0.152.
 */
public class asz
extends asw {
    @Override
    public void a(adq adq2, pk pk2) {
        super.a(adq2, pk2);
    }

    @Override
    public void a() {
        super.a();
    }

    @Override
    public asv a(pk pk2) {
        return this.a(ns.c(pk2.aR().a), ns.c(pk2.aR().b + 0.5), ns.c(pk2.aR().c));
    }

    @Override
    public asv a(pk pk2, double d2, double d3, double d4) {
        return this.a(ns.c(d2 - (double)(pk2.J / 2.0f)), ns.c(d3 + 0.5), ns.c(d4 - (double)(pk2.J / 2.0f)));
    }

    @Override
    public int a(asv[] asvArray, pk pk2, asv asv2, asv asv3, float f2) {
        int n2 = 0;
        for (cq cq2 : cq.values()) {
            asv asv4 = this.a(pk2, asv2.a + cq2.g(), asv2.b + cq2.h(), asv2.c + cq2.i());
            if (asv4 == null || asv4.i || !(asv4.a(asv3) < f2)) continue;
            asvArray[n2++] = asv4;
        }
        return n2;
    }

    private asv a(pk pk2, int n2, int n3, int n4) {
        \u2603 = this.b(pk2, n2, n3, n4);
        if (\u2603 == -1) {
            return this.a(n2, n3, n4);
        }
        return null;
    }

    private int b(pk pk2, int n2, int n3, int n4) {
        cj.a a2 = new cj.a();
        for (int i2 = n2; i2 < n2 + this.c; ++i2) {
            for (\u2603 = n3; \u2603 < n3 + this.d; ++\u2603) {
                for (\u2603 = n4; \u2603 < n4 + this.e; ++\u2603) {
                    afh afh2 = this.a.p(a2.c(i2, \u2603, \u2603)).c();
                    if (afh2.t() == arm.h) continue;
                    return 0;
                }
            }
        }
        return -1;
    }
}

