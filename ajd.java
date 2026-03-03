/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ajd
extends akf {
    private static Map<adm, List<a>> b = Maps.newHashMap();
    private final boolean N;

    private boolean a(adm adm2, cj cj2, boolean bl2) {
        if (!b.containsKey(adm2)) {
            b.put(adm2, Lists.newArrayList());
        }
        List<a> list = b.get(adm2);
        if (bl2) {
            list.add(new a(cj2, adm2.K()));
        }
        int \u26032 = 0;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            a a2 = list.get(i2);
            if (!a2.a.equals(cj2) || ++\u26032 < 8) continue;
            return true;
        }
        return false;
    }

    protected ajd(boolean bl2) {
        this.N = bl2;
        this.a(true);
        this.a((yz)null);
    }

    @Override
    public int a(adm adm2) {
        return 2;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        if (this.N) {
            for (cq cq2 : cq.values()) {
                adm2.c(cj2.a(cq2), this);
            }
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        if (this.N) {
            for (cq cq2 : cq.values()) {
                adm2.c(cj2.a(cq2), this);
            }
        }
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (this.N && alz2.b(a) != cq2) {
            return 15;
        }
        return 0;
    }

    private boolean g(adm adm2, cj cj2, alz alz2) {
        cq cq2 = alz2.b(a).d();
        return adm2.b(cj2.a(cq2), cq2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, Random random) {
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz2, Random random) {
        boolean bl2 = this.g(adm22, cj2, alz2);
        List<a> \u26032 = b.get(adm22);
        while (\u26032 != null && !\u26032.isEmpty() && adm22.K() - \u26032.get((int)0).b > 60L) {
            \u26032.remove(0);
        }
        if (this.N) {
            if (bl2) {
                adm22.a(cj2, afi.aE.Q().a(a, alz2.b(a)), 3);
                if (this.a(adm22, cj2, true)) {
                    adm adm22;
                    adm22.a((float)cj2.n() + 0.5f, (double)((float)cj2.o() + 0.5f), (double)((float)cj2.p() + 0.5f), "random.fizz", 0.5f, 2.6f + (adm22.s.nextFloat() - adm22.s.nextFloat()) * 0.8f);
                    for (int i2 = 0; i2 < 5; ++i2) {
                        double d2 = (double)cj2.n() + random.nextDouble() * 0.6 + 0.2;
                        \u2603 = (double)cj2.o() + random.nextDouble() * 0.6 + 0.2;
                        \u2603 = (double)cj2.p() + random.nextDouble() * 0.6 + 0.2;
                        adm22.a(cy.l, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
                    }
                    adm22.a(cj2, adm22.p(cj2).c(), 160);
                }
            }
        } else if (!bl2 && !this.a(adm22, cj2, false)) {
            adm22.a(cj2, afi.aF.Q().a(a, alz2.b(a)), 3);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (this.e(adm2, cj2, alz2)) {
            return;
        }
        if (this.N == this.g(adm2, cj2, alz2)) {
            adm2.a(cj2, (afh)this, this.a(adm2));
        }
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (cq2 == cq.a) {
            return this.a(adq2, cj2, alz2, cq2);
        }
        return 0;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.aF);
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        if (!this.N) {
            return;
        }
        double d2 = (double)cj2.n() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
        \u2603 = (double)cj2.o() + 0.7 + (random.nextDouble() - 0.5) * 0.2;
        \u2603 = (double)cj2.p() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
        cq \u26032 = alz2.b(a);
        if (\u26032.k().c()) {
            cq cq2 = \u26032.d();
            double \u26033 = 0.27;
            d2 += 0.27 * (double)cq2.g();
            \u2603 += 0.22;
            \u2603 += 0.27 * (double)cq2.i();
        }
        adm2.a(cy.E, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.aF);
    }

    @Override
    public boolean b(afh afh2) {
        return afh2 == afi.aE || afh2 == afi.aF;
    }

    static class a {
        cj a;
        long b;

        public a(cj cj2, long l2) {
            this.a = cj2;
            this.b = l2;
        }
    }
}

