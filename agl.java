/*
 * Decompiled with CFR 0.152.
 */
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

public class agl
extends ahv {
    int a;

    protected agl(arm arm2) {
        super(arm2);
    }

    private void f(adm adm2, cj cj2, alz alz2) {
        adm2.a(cj2, agl.b(this.J).Q().a(b, alz2.b(b)), 2);
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz22, Random random) {
        adm adm22;
        int n2 = alz22.b(b);
        \u2603 = 1;
        if (this.J == arm.i && !adm22.t.n()) {
            \u2603 = 2;
        }
        \u2603 = this.a(adm22);
        if (n2 > 0) {
            int n3;
            n3 = -100;
            this.a = 0;
            for (cq cq2 : cq.c.a) {
                n3 = this.a(adm22, cj2.a(cq2), n3);
            }
            n4 = n3 + \u2603;
            if (n4 >= 8 || n3 < 0) {
                n4 = -1;
            }
            if (this.e((adq)adm22, cj2.a()) >= 0) {
                \u2603 = this.e((adq)adm22, cj2.a());
                n4 = \u2603 >= 8 ? \u2603 : \u2603 + 8;
            }
            if (this.a >= 2 && this.J == arm.h) {
                int n4;
                alz alz3 = adm22.p(cj2.b());
                if (alz3.c().t().a()) {
                    n4 = 0;
                } else if (alz3.c().t() == this.J && alz3.b(b) == 0) {
                    n4 = 0;
                }
            }
            if (this.J == arm.i && n2 < 8 && n4 < 8 && n4 > n2 && random.nextInt(4) != 0) {
                \u2603 *= 4;
            }
            if (n4 == n2) {
                this.f(adm22, cj2, alz22);
            } else {
                n2 = n4;
                if (n2 < 0) {
                    adm22.g(cj2);
                } else {
                    alz alz22 = alz22.a(b, n2);
                    adm22.a(cj2, alz22, 2);
                    adm22.a(cj2, (afh)this, \u2603);
                    adm22.c(cj2, this);
                }
            }
        } else {
            this.f(adm22, cj2, alz22);
        }
        alz \u26032 = adm22.p(cj2.b());
        if (this.h(adm22, cj2.b(), \u26032)) {
            if (this.J == arm.i && adm22.p(cj2.b()).c().t() == arm.h) {
                adm22.a(cj2.b(), afi.b.Q());
                this.e(adm22, cj2.b());
                return;
            }
            if (n2 >= 8) {
                this.a(adm22, cj2.b(), \u26032, n2);
            } else {
                this.a(adm22, cj2.b(), \u26032, n2 + 8);
            }
        } else if (n2 >= 0 && (n2 == 0 || this.g(adm22, cj2.b(), \u26032))) {
            Set<cq> set = this.f(adm22, cj2);
            int \u26033 = n2 + \u2603;
            if (n2 >= 8) {
                \u26033 = 1;
            }
            if (\u26033 >= 8) {
                return;
            }
            for (cq cq3 : set) {
                this.a(adm22, cj2.a(cq3), adm22.p(cj2.a(cq3)), \u26033);
            }
        }
    }

    private void a(adm adm2, cj cj2, alz alz2, int n2) {
        if (this.h(adm2, cj2, alz2)) {
            if (alz2.c() != afi.a) {
                if (this.J == arm.i) {
                    this.e(adm2, cj2);
                } else {
                    alz2.c().b(adm2, cj2, alz2, 0);
                }
            }
            adm2.a(cj2, this.Q().a(b, n2), 3);
        }
    }

    private int a(adm adm2, cj cj2, int n22, cq cq2) {
        int n3 = 1000;
        for (cq cq3 : cq.c.a) {
            int n22;
            if (cq3 == cq2 || this.g(adm2, \u2603 = cj2.a(cq3), \u2603 = adm2.p(\u2603)) || \u2603.c().t() == this.J && \u2603.b(b) <= 0) continue;
            if (this.g(adm2, \u2603.b(), \u2603)) {
                if (n22 >= 4 || (\u2603 = this.a(adm2, \u2603, n22 + 1, cq3.d())) >= n3) continue;
                n3 = \u2603;
                continue;
            }
            return n22;
        }
        return n3;
    }

    private Set<cq> f(adm adm2, cj cj2) {
        int \u26034 = 1000;
        EnumSet<cq> \u26032 = EnumSet.noneOf(cq.class);
        for (cq cq2 : cq.c.a) {
            cj cj3 = cj2.a(cq2);
            if (this.g(adm2, cj3, \u2603 = adm2.p(cj3)) || \u2603.c().t() == this.J && \u2603.b(b) <= 0) continue;
            int \u26033 = this.g(adm2, cj3.b(), adm2.p(cj3.b())) ? this.a(adm2, cj3, 1, cq2.d()) : 0;
            if (\u26033 < \u26034) {
                \u26032.clear();
            }
            if (\u26033 > \u26034) continue;
            \u26032.add(cq2);
            \u26034 = \u26033;
        }
        return \u26032;
    }

    private boolean g(adm adm2, cj cj2, alz alz2) {
        afh afh2 = adm2.p(cj2).c();
        if (afh2 instanceof agh || afh2 == afi.an || afh2 == afi.au || afh2 == afi.aM) {
            return true;
        }
        if (afh2.J == arm.E) {
            return true;
        }
        return afh2.J.c();
    }

    protected int a(adm adm2, cj cj2, int n2) {
        \u2603 = this.e((adq)adm2, cj2);
        if (\u2603 < 0) {
            return n2;
        }
        if (\u2603 == 0) {
            ++this.a;
        }
        if (\u2603 >= 8) {
            \u2603 = 0;
        }
        return n2 < 0 || \u2603 < n2 ? \u2603 : n2;
    }

    private boolean h(adm adm2, cj cj2, alz alz2) {
        arm arm2 = alz2.c().t();
        return arm2 != this.J && arm2 != arm.i && !this.g(adm2, cj2, alz2);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        if (!this.e(adm2, cj2, alz2)) {
            adm2.a(cj2, (afh)this, this.a(adm2));
        }
    }
}

