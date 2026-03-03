/*
 * Decompiled with CFR 0.152.
 */
public class asy {
    private asu a = new asu();
    private asv[] b = new asv[32];
    private asw c;

    public asy(asw asw2) {
        this.c = asw2;
    }

    public asx a(adq adq2, pk pk2, pk pk3, float f2) {
        return this.a(adq2, pk2, pk3.s, pk3.aR().b, pk3.u, f2);
    }

    public asx a(adq adq2, pk pk2, cj cj2, float f2) {
        return this.a(adq2, pk2, (float)cj2.n() + 0.5f, (float)cj2.o() + 0.5f, (float)cj2.p() + 0.5f, f2);
    }

    private asx a(adq adq2, pk pk2, double d2, double d3, double d4, float f2) {
        this.a.a();
        this.c.a(adq2, pk2);
        asv asv2 = this.c.a(pk2);
        \u2603 = this.c.a(pk2, d2, d3, d4);
        asx \u26032 = this.a(pk2, asv2, \u2603, f2);
        this.c.a();
        return \u26032;
    }

    private asx a(pk pk2, asv asv2, asv asv3, float f2) {
        asv2.e = 0.0f;
        asv2.g = asv2.f = asv2.b(asv3);
        this.a.a();
        this.a.a(asv2);
        asv asv4 = asv2;
        while (!this.a.e()) {
            \u2603 = this.a.c();
            if (\u2603.equals(asv3)) {
                return this.a(asv2, asv3);
            }
            if (\u2603.b(asv3) < asv4.b(asv3)) {
                asv4 = \u2603;
            }
            \u2603.i = true;
            int n2 = this.c.a(this.b, pk2, \u2603, asv3, f2);
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                asv asv5 = this.b[\u2603];
                float \u26032 = \u2603.e + \u2603.b(asv5);
                if (!(\u26032 < f2 * 2.0f) || asv5.a() && !(\u26032 < asv5.e)) continue;
                asv5.h = \u2603;
                asv5.e = \u26032;
                asv5.f = asv5.b(asv3);
                if (asv5.a()) {
                    this.a.a(asv5, asv5.e + asv5.f);
                    continue;
                }
                asv5.g = asv5.e + asv5.f;
                this.a.a(asv5);
            }
        }
        if (asv4 == asv2) {
            return null;
        }
        return this.a(asv2, asv4);
    }

    private asx a(asv asv2, asv asv3) {
        int n2 = 1;
        asv \u26032 = asv3;
        while (\u26032.h != null) {
            ++n2;
            \u26032 = \u26032.h;
        }
        asv[] \u26033 = new asv[n2];
        \u26032 = asv3;
        \u26033[--n2] = \u26032;
        while (\u26032.h != null) {
            \u26032 = \u26032.h;
            \u26033[--n2] = \u26032;
        }
        return new asx(\u26033);
    }
}

