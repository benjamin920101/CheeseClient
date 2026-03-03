/*
 * Decompiled with CFR 0.152.
 */
public class zj
extends zw {
    public zj() {
        this.a(yz.f);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj22, cq cq2, float f2, float f3, float f4) {
        alz alz2 = adm2.p(cj22);
        if (wn2.a(cj22.a(cq2), cq2, zx2) && alz2.c() == afi.bG && !alz2.b(ago.b).booleanValue()) {
            Object \u26038;
            if (adm2.D) {
                return true;
            }
            adm2.a(cj22, alz2.a(ago.b, true), 2);
            adm2.e(cj22, afi.bG);
            --zx2.b;
            for (int i2 = 0; i2 < 16; ++i2) {
                double d2 = (float)cj22.n() + (5.0f + g.nextFloat() * 6.0f) / 16.0f;
                \u2603 = (float)cj22.o() + 0.8125f;
                \u2603 = (float)cj22.p() + (5.0f + g.nextFloat() * 6.0f) / 16.0f;
                \u2603 = 0.0;
                \u2603 = 0.0;
                \u2603 = 0.0;
                adm2.a(cy.l, d2, \u2603, \u2603, \u2603, \u2603, \u2603, new int[0]);
            }
            cq \u26032 = alz2.b(ago.a);
            int \u26033 = 0;
            int \u26034 = 0;
            boolean \u26035 = false;
            boolean \u26036 = true;
            cq \u26037 = \u26032.e();
            for (int i3 = -2; i3 <= 2; ++i3) {
                cj cj3 = cj22.a(\u26037, i3);
                \u26038 = adm2.p(cj3);
                if (\u26038.c() != afi.bG) continue;
                if (!\u26038.b(ago.b).booleanValue()) {
                    \u26036 = false;
                    break;
                }
                \u26034 = i3;
                if (\u26035) continue;
                \u26033 = i3;
                \u26035 = true;
            }
            if (\u26036 && \u26034 == \u26033 + 2) {
                int \u26039;
                \u2603 = cj22.a(\u26032, 4);
                for (\u26039 = \u26033; \u26039 <= \u26034; ++\u26039) {
                    \u26038 = \u2603.a(\u26037, \u26039);
                    alz alz3 = adm2.p((cj)\u26038);
                    if (alz3.c() == afi.bG && alz3.b(ago.b).booleanValue()) continue;
                    \u26036 = false;
                    break;
                }
                block3: for (\u26039 = \u26033 - 1; \u26039 <= \u26034 + 1; \u26039 += 4) {
                    cj cj22;
                    \u2603 = cj22.a(\u26037, \u26039);
                    for (int i4 = 1; i4 <= 3; ++i4) {
                        cj cj4 = \u2603.a(\u26032, i4);
                        alz \u260310 = adm2.p(cj4);
                        if (\u260310.c() == afi.bG && \u260310.b(ago.b).booleanValue()) continue;
                        \u26036 = false;
                        continue block3;
                    }
                }
                if (\u26036) {
                    for (\u26039 = \u26033; \u26039 <= \u26034; ++\u26039) {
                        \u2603 = cj22.a(\u26037, \u26039);
                        for (int \u260311 = 1; \u260311 <= 3; ++\u260311) {
                            \u2603 = \u2603.a(\u26032, \u260311);
                            adm2.a(\u2603, afi.bF.Q(), 2);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public zx a(zx zx22, adm adm2, wn wn2) {
        zx zx22;
        auh auh2 = this.a(adm2, wn2, false);
        if (auh2 != null && auh2.a == auh.a.b && adm2.p(auh2.a()).c() == afi.bG) {
            return zx22;
        }
        if (!adm2.D && (\u2603 = adm2.a("Stronghold", new cj(wn2))) != null) {
            wr wr2 = new wr(adm2, wn2.s, wn2.t, wn2.u);
            wr2.a(\u2603);
            adm2.d(wr2);
            adm2.a((pk)wn2, "random.bow", 0.5f, 0.4f / (g.nextFloat() * 0.4f + 0.8f));
            adm2.a(null, 1002, new cj(wn2), 0);
            if (!wn2.bA.d) {
                --zx22.b;
            }
            wn2.b(na.ad[zw.b(this)]);
        }
        return zx22;
    }
}

