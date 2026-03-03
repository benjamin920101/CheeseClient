/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aax
extends zw {
    public aax() {
        this.a(true);
        this.a(yz.f);
    }

    @Override
    public String a(zx zx2) {
        String string = ("" + di.a(this.a() + ".name")).trim();
        \u2603 = pm.b(zx2.i());
        if (\u2603 != null) {
            string = string + " " + di.a("entity." + \u2603 + ".name");
        }
        return string;
    }

    @Override
    public int a(zx zx2, int n2) {
        pm.a a2 = pm.a.get(zx2.i());
        if (a2 != null) {
            if (n2 == 0) {
                return a2.b;
            }
            return a2.c;
        }
        return 0xFFFFFF;
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj22, cq cq2, float f2, float f3, float f4) {
        cj cj22;
        if (adm2.D) {
            return true;
        }
        if (!wn2.a(cj22.a(cq2), cq2, zx2)) {
            return false;
        }
        alz alz2 = adm2.p(cj22);
        if (alz2.c() == afi.ac && (\u2603 = adm2.s(cj22)) instanceof all) {
            add add2 = ((all)\u2603).b();
            add2.a(pm.b(zx2.i()));
            \u2603.p_();
            adm2.h(cj22);
            if (!wn2.bA.d) {
                --zx2.b;
            }
            return true;
        }
        cj22 = cj22.a(cq2);
        double \u26032 = 0.0;
        if (cq2 == cq.b && alz2 instanceof agt) {
            \u26032 = 0.5;
        }
        if ((\u2603 = aax.a(adm2, zx2.i(), (double)cj22.n() + 0.5, (double)cj22.o() + \u26032, (double)cj22.p() + 0.5)) != null) {
            if (\u2603 instanceof pr && zx2.s()) {
                \u2603.a(zx2.q());
            }
            if (!wn2.bA.d) {
                --zx2.b;
            }
        }
        return true;
    }

    @Override
    public zx a(zx zx22, adm adm2, wn wn2) {
        zx zx22;
        if (adm2.D) {
            return zx22;
        }
        auh auh2 = this.a(adm2, wn2, true);
        if (auh2 == null) {
            return zx22;
        }
        if (auh2.a == auh.a.b) {
            cj cj2 = auh2.a();
            if (!adm2.a(wn2, cj2)) {
                return zx22;
            }
            if (!wn2.a(cj2, auh2.b, zx22)) {
                return zx22;
            }
            if (adm2.p(cj2).c() instanceof ahv && (\u2603 = aax.a(adm2, zx22.i(), (double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5)) != null) {
                if (\u2603 instanceof pr && zx22.s()) {
                    ((ps)\u2603).a(zx22.q());
                }
                if (!wn2.bA.d) {
                    --zx22.b;
                }
                wn2.b(na.ad[zw.b(this)]);
            }
        }
        return zx22;
    }

    public static pk a(adm adm2, int n2, double d2, double d3, double d4) {
        if (!pm.a.containsKey(n2)) {
            return null;
        }
        pk pk2 = null;
        for (int i2 = 0; i2 < 1; ++i2) {
            pk2 = pm.a(n2, adm2);
            if (!(pk2 instanceof pr)) continue;
            ps \u26032 = (ps)pk2;
            pk2.b(d2, d3, d4, ns.g(adm2.s.nextFloat() * 360.0f), 0.0f);
            \u26032.aK = \u26032.y;
            \u26032.aI = \u26032.y;
            \u26032.a(adm2.E(new cj(\u26032)), null);
            adm2.d(pk2);
            \u26032.x();
        }
        return pk2;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (pm.a a2 : pm.a.values()) {
            list.add(new zx(zw2, 1, a2.a));
        }
    }
}

