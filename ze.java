/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ze
extends zw {
    public static final int[] a = new int[]{0x1E1B1B, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 0xABABAB, 0x434343, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 0xF0F0F0};

    public ze() {
        this.a(true);
        this.d(0);
        this.a(yz.l);
    }

    @Override
    public String e_(zx zx2) {
        int n2 = zx2.i();
        return super.a() + "." + zd.a(n2).d();
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (!wn2.a(cj2.a(cq2), cq2, zx2)) {
            return false;
        }
        zd zd2 = zd.a(zx2.i());
        if (zd2 == zd.a) {
            if (ze.a(zx2, adm2, cj2)) {
                if (!adm2.D) {
                    adm2.b(2005, cj2, 0);
                }
                return true;
            }
        } else if (zd2 == zd.m && (\u2603 = (\u2603 = adm2.p(cj2)).c()) == afi.r && \u2603.b(aio.a) == aio.a.d) {
            if (cq2 == cq.a) {
                return false;
            }
            if (cq2 == cq.b) {
                return false;
            }
            if (adm2.d(cj2 = cj2.a(cq2))) {
                alz alz2 = afi.bN.a(adm2, cj2, cq2, f2, f3, f4, 0, wn2);
                adm2.a(cj2, alz2, 2);
                if (!wn2.bA.d) {
                    --zx2.b;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean a(zx zx2, adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        if (alz2.c() instanceof afj && (\u2603 = (afj)((Object)alz2.c())).a(adm2, cj2, alz2, adm2.D)) {
            if (!adm2.D) {
                if (\u2603.a(adm2, adm2.s, cj2, alz2)) {
                    \u2603.b(adm2, adm2.s, cj2, alz2);
                }
                --zx2.b;
            }
            return true;
        }
        return false;
    }

    public static void a(adm adm2, cj cj2, int n2) {
        if (n2 == 0) {
            n2 = 15;
        }
        if ((\u2603 = adm2.p(cj2).c()).t() == arm.a) {
            return;
        }
        \u2603.a((adq)adm2, cj2);
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            double d2 = g.nextGaussian() * 0.02;
            \u2603 = g.nextGaussian() * 0.02;
            \u2603 = g.nextGaussian() * 0.02;
            adm2.a(cy.v, (float)cj2.n() + g.nextFloat(), (double)cj2.o() + (double)g.nextFloat() * \u2603.E(), (double)((float)cj2.p() + g.nextFloat()), d2, \u2603, \u2603, new int[0]);
        }
    }

    @Override
    public boolean a(zx zx2, wn wn2, pr pr2) {
        if (pr2 instanceof tv) {
            tv tv2 = (tv)pr2;
            zd \u26032 = zd.a(zx2.i());
            if (!tv2.cm() && tv2.cl() != \u26032) {
                tv2.b(\u26032);
                --zx2.b;
            }
            return true;
        }
        return false;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (int i2 = 0; i2 < 16; ++i2) {
            list.add(new zx(zw2, 1, i2));
        }
    }
}

