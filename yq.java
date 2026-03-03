/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class yq
extends zw {
    public yq() {
        this.h = 1;
        this.a(yz.e);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        pk \u260313;
        float f2 = 1.0f;
        \u2603 = wn2.B + (wn2.z - wn2.B) * f2;
        \u2603 = wn2.A + (wn2.y - wn2.A) * f2;
        double \u26032 = wn2.p + (wn2.s - wn2.p) * (double)f2;
        double \u26033 = wn2.q + (wn2.t - wn2.q) * (double)f2 + (double)wn2.aS();
        double \u26034 = wn2.r + (wn2.u - wn2.r) * (double)f2;
        aui \u26035 = new aui(\u26032, \u26033, \u26034);
        \u2603 = ns.b(-\u2603 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = ns.a(-\u2603 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = \u2603 * (\u2603 = -ns.b(-\u2603 * ((float)Math.PI / 180)));
        aui \u26036 = \u26035.b((double)\u2603 * (\u2603 = 5.0), (double)(\u2603 = (\u2603 = ns.a(-\u2603 * ((float)Math.PI / 180)))) * \u2603, (double)(\u2603 = \u2603 * \u2603) * \u2603);
        auh \u26037 = adm2.a(\u26035, \u26036, true);
        if (\u26037 == null) {
            return zx2;
        }
        aui \u26038 = wn2.d(f2);
        boolean \u26039 = false;
        \u2603 = 1.0f;
        List<pk> \u260310 = adm2.b(wn2, wn2.aR().a(\u26038.a * \u2603, \u26038.b * \u2603, \u26038.c * \u2603).b(\u2603, \u2603, \u2603));
        for (int i2 = 0; i2 < \u260310.size(); ++i2) {
            \u260313 = \u260310.get(i2);
            if (!\u260313.ad()) continue;
            float \u260311 = \u260313.ao();
            aug \u260312 = \u260313.aR().b(\u260311, \u260311, \u260311);
            if (!\u260312.a(\u26035)) continue;
            \u26039 = true;
        }
        if (\u26039) {
            return zx2;
        }
        if (\u26037.a == auh.a.b) {
            cj cj2 = \u26037.a();
            if (adm2.p(cj2).c() == afi.aH) {
                cj2 = cj2.b();
            }
            \u260313 = new ux(adm2, (float)cj2.n() + 0.5f, (float)cj2.o() + 1.0f, (float)cj2.p() + 0.5f);
            ((ux)\u260313).y = ((ns.c((double)(wn2.y * 4.0f / 360.0f) + 0.5) & 3) - 1) * 90;
            if (!adm2.a(\u260313, \u260313.aR().b(-0.1, -0.1, -0.1)).isEmpty()) {
                return zx2;
            }
            if (!adm2.D) {
                adm2.d(\u260313);
            }
            if (!wn2.bA.d) {
                --zx2.b;
            }
            wn2.b(na.ad[zw.b(this)]);
        }
        return zx2;
    }
}

