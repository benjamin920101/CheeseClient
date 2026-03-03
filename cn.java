/*
 * Decompiled with CFR 0.152.
 */
public class cn
implements cr {
    @Override
    public final zx a(ck ck2, zx zx2) {
        \u2603 = this.b(ck2, zx2);
        this.a(ck2);
        this.a(ck2, agg.b(ck2.f()));
        return \u2603;
    }

    protected zx b(ck ck2, zx zx2) {
        cq cq2 = agg.b(ck2.f());
        cz \u26032 = agg.a(ck2);
        zx \u26033 = zx2.a(1);
        cn.a(ck2.i(), \u26033, 6, cq2, \u26032);
        return zx2;
    }

    public static void a(adm adm2, zx zx2, int n2, cq cq2, cz cz2) {
        double d2 = cz2.a();
        \u2603 = cz2.b();
        \u2603 = cz2.c();
        \u2603 = cq2.k() == cq.a.b ? (\u2603 -= 0.125) : (\u2603 -= 0.15625);
        uz \u26032 = new uz(adm2, d2, \u2603, \u2603, zx2);
        \u2603 = adm2.s.nextDouble() * 0.1 + 0.2;
        \u26032.v = (double)cq2.g() * \u2603;
        \u26032.w = 0.2f;
        \u26032.x = (double)cq2.i() * \u2603;
        \u26032.v += adm2.s.nextGaussian() * (double)0.0075f * (double)n2;
        \u26032.w += adm2.s.nextGaussian() * (double)0.0075f * (double)n2;
        \u26032.x += adm2.s.nextGaussian() * (double)0.0075f * (double)n2;
        adm2.d(\u26032);
    }

    protected void a(ck ck2) {
        ck2.i().b(1000, ck2.d(), 0);
    }

    protected void a(ck ck2, cq cq2) {
        ck2.i().b(2000, ck2.d(), this.a(cq2));
    }

    private int a(cq cq2) {
        return cq2.g() + 1 + (cq2.i() + 1) * 3;
    }
}

