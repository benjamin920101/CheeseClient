/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class yk
extends zw {
    public yk() {
        this.a(yz.c);
    }

    @Override
    public boolean a(zx zx22, wn wn2, adm adm22, cj cj2, cq cq2, float f2, float f3, float f4) {
        zx zx22;
        if (cq2 == cq.a) {
            return false;
        }
        boolean bl2 = adm22.p(cj2).c().a(adm22, cj2);
        cj cj3 = \u2603 = bl2 ? cj2 : cj2.a(cq2);
        if (!wn2.a(\u2603, cq2, zx22)) {
            return false;
        }
        cj \u26032 = \u2603.a();
        \u2603 = !adm22.d(\u2603) && !adm22.p(\u2603).c().a(adm22, \u2603);
        if (\u2603 |= !adm22.d(\u26032) && !adm22.p(\u26032).c().a(adm22, \u26032)) {
            return false;
        }
        double \u26033 = \u2603.n();
        List<pk> \u26034 = adm22.b(null, aug.a(\u26033, \u2603 = (double)\u2603.o(), \u2603 = (double)\u2603.p(), \u26033 + 1.0, \u2603 + 2.0, \u2603 + 1.0));
        if (\u26034.size() > 0) {
            return false;
        }
        if (!adm22.D) {
            adm adm22;
            adm22.g(\u2603);
            adm22.g(\u26032);
            um um2 = new um(adm22, \u26033 + 0.5, \u2603, \u2603 + 0.5);
            float \u26035 = (float)ns.d((ns.g(wn2.y - 180.0f) + 22.5f) / 45.0f) * 45.0f;
            um2.b(\u26033 + 0.5, \u2603, \u2603 + 0.5, \u26035, 0.0f);
            this.a(um2, adm22.s);
            dn \u26036 = zx22.o();
            if (\u26036 != null && \u26036.b("EntityTag", 10)) {
                dn dn2 = new dn();
                um2.d(dn2);
                dn2.a(\u26036.m("EntityTag"));
                um2.f(dn2);
            }
            adm22.d(um2);
        }
        --zx22.b;
        return true;
    }

    private void a(um um2, Random random) {
        dc dc2 = um2.t();
        float \u26032 = random.nextFloat() * 5.0f;
        float \u26033 = random.nextFloat() * 20.0f - 10.0f;
        \u2603 = new dc(dc2.b() + \u26032, dc2.c() + \u26033, dc2.d());
        um2.a(\u2603);
        dc2 = um2.u();
        \u26032 = random.nextFloat() * 10.0f - 5.0f;
        \u2603 = new dc(dc2.b(), dc2.c() + \u26032, dc2.d());
        um2.b(\u2603);
    }
}

