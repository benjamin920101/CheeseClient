/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class ahs
extends akg {
    public static final amk a = amk.a("decayable");
    public static final amk b = amk.a("check_decay");
    int[] N;
    protected int O;
    protected boolean P;

    public ahs() {
        super(arm.j, false);
        this.a(true);
        this.a(yz.c);
        this.c(0.2f);
        this.e(1);
        this.a(h);
    }

    @Override
    public int H() {
        return adj.a(0.5, 1.0);
    }

    @Override
    public int h(alz alz2) {
        return adj.c();
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        return aea.b(adq2, cj2);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        int n2 = 1;
        \u2603 = n2 + 1;
        \u2603 = cj2.n();
        if (adm2.a(new cj(\u2603 - \u2603, (\u2603 = cj2.o()) - \u2603, (\u2603 = cj2.p()) - \u2603), new cj(\u2603 + \u2603, \u2603 + \u2603, \u2603 + \u2603))) {
            for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                    for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                        cj cj3 = cj2.a(\u2603, \u2603, \u2603);
                        alz \u26032 = adm2.p(cj3);
                        if (\u26032.c().t() != arm.j || \u26032.b(b).booleanValue()) continue;
                        adm2.a(cj3, \u26032.a(b, true), 4);
                    }
                }
            }
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        if (alz2.b(b).booleanValue() && alz2.b(a).booleanValue()) {
            int n2;
            int n3 = 4;
            \u2603 = n3 + 1;
            \u2603 = cj2.n();
            \u2603 = cj2.o();
            \u2603 = cj2.p();
            \u2603 = 32;
            \u2603 = \u2603 * \u2603;
            \u2603 = \u2603 / 2;
            if (this.N == null) {
                this.N = new int[\u2603 * \u2603 * \u2603];
            }
            if (adm2.a(new cj(\u2603 - \u2603, \u2603 - \u2603, \u2603 - \u2603), new cj(\u2603 + \u2603, \u2603 + \u2603, \u2603 + \u2603))) {
                int n4;
                cj.a a2 = new cj.a();
                for (n4 = -n3; n4 <= n3; ++n4) {
                    for (\u2603 = -n3; \u2603 <= n3; ++\u2603) {
                        for (\u2603 = -n3; \u2603 <= n3; ++\u2603) {
                            afh afh2 = adm2.p(a2.c(\u2603 + n4, \u2603 + \u2603, \u2603 + \u2603)).c();
                            this.N[(n4 + \u2603) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603)] = afh2 == afi.r || afh2 == afi.s ? 0 : (afh2.t() == arm.j ? -2 : -1);
                        }
                    }
                }
                for (n4 = 1; n4 <= 4; ++n4) {
                    for (\u2603 = -n3; \u2603 <= n3; ++\u2603) {
                        for (\u2603 = -n3; \u2603 <= n3; ++\u2603) {
                            for (\u2603 = -n3; \u2603 <= n3; ++\u2603) {
                                if (this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603)] != n4 - 1) continue;
                                if (this.N[(\u2603 + \u2603 - 1) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603)] == -2) {
                                    this.N[(\u2603 + \u2603 - 1) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603)] = n4;
                                }
                                if (this.N[(\u2603 + \u2603 + 1) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603)] == -2) {
                                    this.N[(\u2603 + \u2603 + 1) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603)] = n4;
                                }
                                if (this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 - 1) * \u2603 + (\u2603 + \u2603)] == -2) {
                                    this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 - 1) * \u2603 + (\u2603 + \u2603)] = n4;
                                }
                                if (this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 + 1) * \u2603 + (\u2603 + \u2603)] == -2) {
                                    this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 + 1) * \u2603 + (\u2603 + \u2603)] = n4;
                                }
                                if (this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 - 1)] == -2) {
                                    this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 - 1)] = n4;
                                }
                                if (this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 + 1)] != -2) continue;
                                this.N[(\u2603 + \u2603) * \u2603 + (\u2603 + \u2603) * \u2603 + (\u2603 + \u2603 + 1)] = n4;
                            }
                        }
                    }
                }
            }
            if ((n2 = this.N[\u2603 * \u2603 + \u2603 * \u2603 + \u2603]) >= 0) {
                adm2.a(cj2, alz2.a(b, false), 4);
            } else {
                this.e(adm2, cj2);
            }
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.C(cj2.a()) && !adm.a(adm2, cj2.b()) && random.nextInt(15) == 1) {
            double d2 = (float)cj2.n() + random.nextFloat();
            \u2603 = (double)cj2.o() - 0.05;
            \u2603 = (float)cj2.p() + random.nextFloat();
            adm2.a(cy.s, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
        }
    }

    private void e(adm adm2, cj cj2) {
        this.b(adm2, cj2, adm2.p(cj2), 0);
        adm2.g(cj2);
    }

    @Override
    public int a(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.g);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        if (!adm2.D) {
            n3 = this.d(alz2);
            if (n2 > 0 && (n3 -= 2 << n2) < 10) {
                n3 = 10;
            }
            if (adm2.s.nextInt(n3) == 0) {
                zw zw2 = this.a(alz2, adm2.s, n2);
                ahs.a(adm2, cj2, new zx(zw2, 1, this.a(alz2)));
            }
            int n3 = 200;
            if (n2 > 0 && (n3 -= 10 << n2) < 40) {
                n3 = 40;
            }
            this.a(adm2, cj2, alz2, n3);
        }
    }

    protected void a(adm adm2, cj cj2, alz alz2, int n2) {
    }

    protected int d(alz alz2) {
        return 20;
    }

    @Override
    public boolean c() {
        return !this.R;
    }

    public void b(boolean bl2) {
        this.P = bl2;
        this.R = bl2;
        this.O = bl2 ? 0 : 1;
    }

    @Override
    public adf m() {
        return this.P ? adf.b : adf.a;
    }

    @Override
    public boolean w() {
        return false;
    }

    public abstract aio.a b(int var1);
}

