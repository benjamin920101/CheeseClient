/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class ahv
extends afh {
    public static final amn b = amn.a("level", 0, 15);

    protected ahv(arm arm2) {
        super(arm2);
        this.j(this.M.b().a(b, 0));
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.a(true);
    }

    @Override
    public boolean b(adq adq2, cj cj2) {
        return this.J != arm.i;
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        if (this.J == arm.h) {
            return aea.c(adq2, cj2);
        }
        return 0xFFFFFF;
    }

    public static float b(int n2) {
        if (n2 >= 8) {
            n2 = 0;
        }
        return (float)(n2 + 1) / 9.0f;
    }

    protected int e(adq adq2, cj cj2) {
        if (adq2.p(cj2).c().t() == this.J) {
            return adq2.p(cj2).b(b);
        }
        return -1;
    }

    protected int f(adq adq2, cj cj2) {
        int n2 = this.e(adq2, cj2);
        return n2 >= 8 ? 0 : n2;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean a(alz alz2, boolean bl2) {
        return bl2 && alz2.b(b) == 0;
    }

    @Override
    public boolean b(adq adq2, cj cj2, cq cq2) {
        arm arm2 = adq2.p(cj2).c().t();
        if (arm2 == this.J) {
            return false;
        }
        if (cq2 == cq.b) {
            return true;
        }
        if (arm2 == arm.w) {
            return false;
        }
        return super.b(adq2, cj2, cq2);
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (adq2.p(cj2).c().t() == this.J) {
            return false;
        }
        if (cq2 == cq.b) {
            return true;
        }
        return super.a(adq2, cj2, cq2);
    }

    public boolean g(adq adq2, cj cj2) {
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                alz alz2 = adq2.p(cj2.a(i2, 0, \u2603));
                afh \u26032 = alz2.c();
                arm \u26033 = \u26032.t();
                if (\u26033 == this.J || \u26032.o()) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public int b() {
        return 1;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    protected aui h(adq adq2, cj cj2) {
        cj cj3;
        aui \u26034 = new aui(0.0, 0.0, 0.0);
        int \u26032 = this.f(adq2, cj2);
        for (cq cq2 : cq.c.a) {
            cj3 = cj2.a(cq2);
            int \u26033 = this.f(adq2, cj3);
            if (\u26033 < 0) {
                if (adq2.p(cj3).c().t().c() || (\u26033 = this.f(adq2, cj3.b())) < 0) continue;
                int n2 = \u26033 - (\u26032 - 8);
                \u26034 = \u26034.b((cj3.n() - cj2.n()) * n2, (cj3.o() - cj2.o()) * n2, (cj3.p() - cj2.p()) * n2);
                continue;
            }
            if (\u26033 < 0) continue;
            n2 = \u26033 - \u26032;
            \u26034 = \u26034.b((cj3.n() - cj2.n()) * n2, (cj3.o() - cj2.o()) * n2, (cj3.p() - cj2.p()) * n2);
        }
        if (adq2.p(cj2).b(b) >= 8) {
            for (cq cq2 : cq.c.a) {
                cj3 = cj2.a(cq2);
                if (!this.b(adq2, cj3, cq2) && !this.b(adq2, cj3.a(), cq2)) continue;
                \u26034 = \u26034.a().b(0.0, -6.0, 0.0);
                break;
            }
        }
        return \u26034.a();
    }

    @Override
    public aui a(adm adm2, cj cj2, pk pk2, aui aui2) {
        return aui2.e(this.h(adm2, cj2));
    }

    @Override
    public int a(adm adm2) {
        if (this.J == arm.h) {
            return 5;
        }
        if (this.J == arm.i) {
            return adm2.t.o() ? 10 : 30;
        }
        return 0;
    }

    @Override
    public int c(adq adq2, cj cj2) {
        int n2 = adq2.b(cj2, 0);
        \u2603 = adq2.b(cj2.a(), 0);
        \u2603 = n2 & 0xFF;
        \u2603 = \u2603 & 0xFF;
        \u2603 = n2 >> 16 & 0xFF;
        \u2603 = \u2603 >> 16 & 0xFF;
        return (\u2603 > \u2603 ? \u2603 : \u2603) | (\u2603 > \u2603 ? \u2603 : \u2603) << 16;
    }

    @Override
    public adf m() {
        return this.J == arm.h ? adf.d : adf.a;
    }

    @Override
    public void c(adm adm22, cj cj2, alz alz2, Random random2) {
        Random random2;
        adm adm22;
        double d2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        if (this.J == arm.h) {
            int n2 = alz2.b(b);
            if (n2 > 0 && n2 < 8) {
                if (random2.nextInt(64) == 0) {
                    adm22.a(d2 + 0.5, \u2603 + 0.5, \u2603 + 0.5, "liquid.water", random2.nextFloat() * 0.25f + 0.75f, random2.nextFloat() * 1.0f + 0.5f, false);
                }
            } else if (random2.nextInt(10) == 0) {
                adm22.a(cy.h, d2 + (double)random2.nextFloat(), \u2603 + (double)random2.nextFloat(), \u2603 + (double)random2.nextFloat(), 0.0, 0.0, 0.0, new int[0]);
            }
        }
        if (this.J == arm.i && adm22.p(cj2.a()).c().t() == arm.a && !adm22.p(cj2.a()).c().c()) {
            if (random2.nextInt(100) == 0) {
                double d3 = d2 + (double)random2.nextFloat();
                \u2603 = \u2603 + this.F;
                \u2603 = \u2603 + (double)random2.nextFloat();
                adm22.a(cy.B, d3, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
                adm22.a(d3, \u2603, \u2603, "liquid.lavapop", 0.2f + random2.nextFloat() * 0.2f, 0.9f + random2.nextFloat() * 0.15f, false);
            }
            if (random2.nextInt(200) == 0) {
                adm22.a(d2, \u2603, \u2603, "liquid.lava", 0.2f + random2.nextFloat() * 0.2f, 0.9f + random2.nextFloat() * 0.15f, false);
            }
        }
        if (random2.nextInt(10) == 0 && adm.a(adm22, cj2.b()) && !(\u2603 = adm22.p(cj2.c(2)).c().t()).c() && !\u2603.d()) {
            double d4 = d2 + (double)random2.nextFloat();
            \u2603 = \u2603 - 1.05;
            \u2603 = \u2603 + (double)random2.nextFloat();
            if (this.J == arm.h) {
                adm22.a(cy.s, d4, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
            } else {
                adm22.a(cy.t, d4, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
            }
        }
    }

    public static double a(adq adq2, cj cj2, arm arm2) {
        aui aui2 = ahv.a(arm2).h(adq2, cj2);
        if (aui2.a == 0.0 && aui2.c == 0.0) {
            return -1000.0;
        }
        return ns.b(aui2.c, aui2.a) - 1.5707963267948966;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.e(adm2, cj2, alz2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        this.e(adm2, cj2, alz2);
    }

    public boolean e(adm adm2, cj cj2, alz alz2) {
        if (this.J == arm.i) {
            boolean bl2 = false;
            for (cq cq2 : cq.values()) {
                if (cq2 == cq.a || adm2.p(cj2.a(cq2)).c().t() != arm.h) continue;
                bl2 = true;
                break;
            }
            if (bl2) {
                Integer n2 = alz2.b(b);
                if (n2 == 0) {
                    adm2.a(cj2, afi.Z.Q());
                    this.e(adm2, cj2);
                    return true;
                }
                if (n2 <= 4) {
                    adm2.a(cj2, afi.e.Q());
                    this.e(adm2, cj2);
                    return true;
                }
            }
        }
        return false;
    }

    protected void e(adm adm2, cj cj2) {
        double d2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        adm2.a(d2 + 0.5, \u2603 + 0.5, \u2603 + 0.5, "random.fizz", 0.5f, 2.6f + (adm2.s.nextFloat() - adm2.s.nextFloat()) * 0.8f);
        for (int i2 = 0; i2 < 8; ++i2) {
            adm2.a(cy.m, d2 + Math.random(), \u2603 + 1.2, \u2603 + Math.random(), 0.0, 0.0, 0.0, new int[0]);
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(b, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(b);
    }

    @Override
    protected ama e() {
        return new ama(this, b);
    }

    public static agl a(arm arm2) {
        if (arm2 == arm.h) {
            return afi.i;
        }
        if (arm2 == arm.i) {
            return afi.k;
        }
        throw new IllegalArgumentException("Invalid material");
    }

    public static ajw b(arm arm2) {
        if (arm2 == arm.h) {
            return afi.j;
        }
        if (arm2 == arm.i) {
            return afi.l;
        }
        throw new IllegalArgumentException("Invalid material");
    }
}

