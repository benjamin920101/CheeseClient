/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agh
extends afh {
    public static final aml a = aml.a("facing", cq.c.a);
    public static final amk b = amk.a("open");
    public static final amm<b> N = amm.a("hinge", b.class);
    public static final amk O = amk.a("powered");
    public static final amm<a> P = amm.a("half", a.class);

    protected agh(arm arm2) {
        super(arm2);
        this.j(this.M.b().a(a, cq.c).a(b, false).a(N, agh$b.a).a(O, false).a(P, agh$a.b));
    }

    @Override
    public String f() {
        return di.a((this.a() + ".name").replaceAll("tile", "item"));
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean b(adq adq2, cj cj2) {
        return agh.g(agh.e(adq2, cj2));
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        this.a((adq)adm2, cj2);
        return super.b(adm2, cj2);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, alz2);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.k(agh.e(adq2, cj2));
    }

    private void k(int n2) {
        float f2 = 0.1875f;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
        cq \u26032 = agh.f(n2);
        boolean \u26033 = agh.g(n2);
        boolean \u26034 = agh.j(n2);
        if (\u26033) {
            if (\u26032 == cq.f) {
                if (!\u26034) {
                    this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f2);
                } else {
                    this.a(0.0f, 0.0f, 1.0f - f2, 1.0f, 1.0f, 1.0f);
                }
            } else if (\u26032 == cq.d) {
                if (!\u26034) {
                    this.a(1.0f - f2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                } else {
                    this.a(0.0f, 0.0f, 0.0f, f2, 1.0f, 1.0f);
                }
            } else if (\u26032 == cq.e) {
                if (!\u26034) {
                    this.a(0.0f, 0.0f, 1.0f - f2, 1.0f, 1.0f, 1.0f);
                } else {
                    this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f2);
                }
            } else if (\u26032 == cq.c) {
                if (!\u26034) {
                    this.a(0.0f, 0.0f, 0.0f, f2, 1.0f, 1.0f);
                } else {
                    this.a(1.0f - f2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                }
            }
        } else if (\u26032 == cq.f) {
            this.a(0.0f, 0.0f, 0.0f, f2, 1.0f, 1.0f);
        } else if (\u26032 == cq.d) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f2);
        } else if (\u26032 == cq.e) {
            this.a(1.0f - f2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else if (\u26032 == cq.c) {
            this.a(0.0f, 0.0f, 1.0f - f2, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz \u260322, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (this.J == arm.f) {
            return true;
        }
        cj cj3 = \u260322.b(P) == agh$a.b ? cj2 : cj2.b();
        alz alz2 = \u2603 = cj2.equals(cj3) ? \u260322 : adm2.p(cj3);
        if (\u2603.c() != this) {
            return false;
        }
        alz \u260322 = \u2603.a(b);
        adm2.a(cj3, \u260322, 2);
        adm2.b(cj3, cj2);
        adm2.a(wn2, \u260322.b(b) != false ? 1003 : 1006, cj2, 0);
        return true;
    }

    public void a(adm adm2, cj cj2, boolean bl2) {
        alz alz2 = adm2.p(cj2);
        if (alz2.c() != this) {
            return;
        }
        cj \u26032 = alz2.b(P) == agh$a.b ? cj2 : cj2.b();
        alz alz3 = \u2603 = cj2 == \u26032 ? alz2 : adm2.p(\u26032);
        if (\u2603.c() == this && \u2603.b(b) != bl2) {
            adm2.a(\u26032, \u2603.a(b, bl2), 2);
            adm2.b(\u26032, cj2);
            adm2.a(null, bl2 ? 1003 : 1006, cj2, 0);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (alz2.b(P) == agh$a.a) {
            cj cj3 = cj2.b();
            alz \u26032 = adm2.p(cj3);
            if (\u26032.c() != this) {
                adm2.g(cj2);
            } else if (afh2 != this) {
                this.a(adm2, cj3, \u26032, afh2);
            }
        } else {
            boolean \u26033 = false;
            \u2603 = cj2.a();
            alz \u26034 = adm2.p(\u2603);
            if (\u26034.c() != this) {
                adm2.g(cj2);
                \u26033 = true;
            }
            if (!adm.a(adm2, cj2.b())) {
                adm2.g(cj2);
                \u26033 = true;
                if (\u26034.c() == this) {
                    adm2.g(\u2603);
                }
            }
            if (\u26033) {
                if (!adm2.D) {
                    this.b(adm2, cj2, alz2, 0);
                }
            } else {
                boolean bl2 = \u2603 = adm2.z(cj2) || adm2.z(\u2603);
                if ((\u2603 || afh2.i()) && afh2 != this && \u2603 != \u26034.b(O)) {
                    adm2.a(\u2603, \u26034.a(O, \u2603), 2);
                    if (\u2603 != alz2.b(b)) {
                        adm2.a(cj2, alz2.a(b, \u2603), 2);
                        adm2.b(cj2, cj2);
                        adm2.a(null, \u2603 ? 1003 : 1006, cj2, 0);
                    }
                }
            }
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (alz2.b(P) == agh$a.a) {
            return null;
        }
        return this.l();
    }

    @Override
    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, aui2, aui3);
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        if (cj2.o() >= 255) {
            return false;
        }
        return adm.a(adm2, cj2.b()) && super.d(adm2, cj2) && super.d(adm2, cj2.a());
    }

    @Override
    public int k() {
        return 1;
    }

    public static int e(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        int \u26032 = alz2.c().c(alz2);
        boolean \u26033 = agh.i(\u26032);
        \u2603 = adq2.p(cj2.b());
        int \u26034 = \u2603.c().c(\u2603);
        int \u26035 = \u26033 ? \u26034 : \u26032;
        \u2603 = adq2.p(cj2.a());
        int \u26036 = \u2603.c().c(\u2603);
        int \u26037 = \u26033 ? \u26032 : \u26036;
        boolean \u26038 = (\u26037 & 1) != 0;
        boolean \u26039 = (\u26037 & 2) != 0;
        return agh.b(\u26035) | (\u26033 ? 8 : 0) | (\u26038 ? 16 : 0) | (\u26039 ? 32 : 0);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return this.l();
    }

    private zw l() {
        if (this == afi.aA) {
            return zy.aB;
        }
        if (this == afi.ap) {
            return zy.ar;
        }
        if (this == afi.aq) {
            return zy.as;
        }
        if (this == afi.ar) {
            return zy.at;
        }
        if (this == afi.as) {
            return zy.au;
        }
        if (this == afi.at) {
            return zy.av;
        }
        return zy.aq;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
        cj cj3 = cj2.b();
        if (wn2.bA.d && alz2.b(P) == agh$a.a && adm2.p(cj3).c() == this) {
            adm2.g(cj3);
        }
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(alz alz22, adq adq22, cj cj2) {
        alz alz22;
        if (alz22.b(P) == agh$a.b) {
            alz alz3 = adq22.p(cj2.a());
            if (alz3.c() == this) {
                alz22 = alz22.a(N, alz3.b(N)).a(O, alz3.b(O));
            }
        } else {
            adq adq22;
            alz \u26032 = adq22.p(cj2.b());
            if (\u26032.c() == this) {
                alz22 = alz22.a(a, \u26032.b(a)).a(b, \u26032.b(b));
            }
        }
        return alz22;
    }

    @Override
    public alz a(int n2) {
        if ((n2 & 8) > 0) {
            return this.Q().a(P, agh$a.a).a(N, (n2 & 1) > 0 ? agh$b.b : agh$b.a).a(O, (n2 & 2) > 0);
        }
        return this.Q().a(P, agh$a.b).a(a, cq.b(n2 & 3).f()).a(b, (n2 & 4) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        if (alz2.b(P) == agh$a.a) {
            n2 |= 8;
            if (alz2.b(N) == agh$b.b) {
                n2 |= 1;
            }
            if (alz2.b(O).booleanValue()) {
                n2 |= 2;
            }
        } else {
            n2 |= alz2.b(a).e().b();
            if (alz2.b(b).booleanValue()) {
                n2 |= 4;
            }
        }
        return n2;
    }

    protected static int b(int n2) {
        return n2 & 7;
    }

    public static boolean f(adq adq2, cj cj2) {
        return agh.g(agh.e(adq2, cj2));
    }

    public static cq h(adq adq2, cj cj2) {
        return agh.f(agh.e(adq2, cj2));
    }

    public static cq f(int n2) {
        return cq.b(n2 & 3).f();
    }

    protected static boolean g(int n2) {
        return (n2 & 4) != 0;
    }

    protected static boolean i(int n2) {
        return (n2 & 8) != 0;
    }

    protected static boolean j(int n2) {
        return (n2 & 0x10) != 0;
    }

    @Override
    protected ama e() {
        return new ama(this, P, a, b, N, O);
    }

    public static enum b implements nw
    {
        a,
        b;


        public String toString() {
            return this.l();
        }

        @Override
        public String l() {
            return this == a ? "left" : "right";
        }
    }

    public static enum a implements nw
    {
        a,
        b;


        public String toString() {
            return this.l();
        }

        @Override
        public String l() {
            return this == a ? "upper" : "lower";
        }
    }
}

