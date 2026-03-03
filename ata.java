/*
 * Decompiled with CFR 0.152.
 */
public class ata
extends asw {
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;

    @Override
    public void a(adq adq2, pk pk2) {
        super.a(adq2, pk2);
        this.j = this.h;
    }

    @Override
    public void a() {
        super.a();
        this.h = this.j;
    }

    @Override
    public asv a(pk pk2) {
        if (this.i && pk2.V()) {
            int n2 = (int)pk2.aR().b;
            cj.a \u26032 = new cj.a(ns.c(pk2.s), n2, ns.c(pk2.u));
            afh \u26033 = this.a.p(\u26032).c();
            while (\u26033 == afi.i || \u26033 == afi.j) {
                \u26032.c(ns.c(pk2.s), ++n2, ns.c(pk2.u));
                \u26033 = this.a.p(\u26032).c();
            }
            this.h = false;
        } else {
            n2 = ns.c(pk2.aR().b + 0.5);
        }
        return this.a(ns.c(pk2.aR().a), n2, ns.c(pk2.aR().c));
    }

    @Override
    public asv a(pk pk2, double d2, double d3, double d4) {
        return this.a(ns.c(d2 - (double)(pk2.J / 2.0f)), ns.c(d3), ns.c(d4 - (double)(pk2.J / 2.0f)));
    }

    @Override
    public int a(asv[] asvArray, pk pk2, asv asv2, asv asv3, float f2) {
        int n2 = 0;
        \u2603 = 0;
        if (this.a(pk2, asv2.a, asv2.b + 1, asv2.c) == 1) {
            \u2603 = 1;
        }
        asv \u26032 = this.a(pk2, asv2.a, asv2.b, asv2.c + 1, \u2603);
        asv \u26033 = this.a(pk2, asv2.a - 1, asv2.b, asv2.c, \u2603);
        asv \u26034 = this.a(pk2, asv2.a + 1, asv2.b, asv2.c, \u2603);
        asv \u26035 = this.a(pk2, asv2.a, asv2.b, asv2.c - 1, \u2603);
        if (\u26032 != null && !\u26032.i && \u26032.a(asv3) < f2) {
            asvArray[n2++] = \u26032;
        }
        if (\u26033 != null && !\u26033.i && \u26033.a(asv3) < f2) {
            asvArray[n2++] = \u26033;
        }
        if (\u26034 != null && !\u26034.i && \u26034.a(asv3) < f2) {
            asvArray[n2++] = \u26034;
        }
        if (\u26035 != null && !\u26035.i && \u26035.a(asv3) < f2) {
            asvArray[n2++] = \u26035;
        }
        return n2;
    }

    private asv a(pk pk2, int n2, int n3, int n4, int n5) {
        asv asv2 = null;
        int \u26032 = this.a(pk2, n2, n3, n4);
        if (\u26032 == 2) {
            return this.a(n2, n3, n4);
        }
        if (\u26032 == 1) {
            asv2 = this.a(n2, n3, n4);
        }
        if (asv2 == null && n5 > 0 && \u26032 != -3 && \u26032 != -4 && this.a(pk2, n2, n3 + n5, n4) == 1) {
            asv2 = this.a(n2, n3 + n5, n4);
            n3 += n5;
        }
        if (asv2 != null) {
            int n6;
            int n7 = 0;
            n6 = 0;
            while (n3 > 0) {
                n6 = this.a(pk2, n2, n3 - 1, n4);
                if (this.h && n6 == -1) {
                    return null;
                }
                if (n6 != 1) break;
                if (n7++ >= pk2.aE()) {
                    return null;
                }
                if (--n3 > 0) {
                    asv2 = this.a(n2, n3, n4);
                    continue;
                }
                return null;
            }
            if (n6 == -2) {
                return null;
            }
        }
        return asv2;
    }

    private int a(pk pk2, int n2, int n3, int n4) {
        return ata.a(this.a, pk2, n2, n3, n4, this.c, this.d, this.e, this.h, this.g, this.f);
    }

    public static int a(adq adq2, pk pk2, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl2, boolean bl3, boolean bl42) {
        bl5 = false;
        cj cj2 = new cj(pk2);
        cj.a \u26032 = new cj.a();
        for (int i2 = n2; i2 < n2 + n5; ++i2) {
            for (\u2603 = n3; \u2603 < n3 + n6; ++\u2603) {
                for (\u2603 = n4; \u2603 < n4 + n7; ++\u2603) {
                    boolean bl42;
                    boolean bl5;
                    \u26032.c(i2, \u2603, \u2603);
                    afh afh2 = adq2.p(\u26032).c();
                    if (afh2.t() == arm.a) continue;
                    if (afh2 == afi.bd || afh2 == afi.cw) {
                        bl5 = true;
                    } else if (afh2 == afi.i || afh2 == afi.j) {
                        if (bl2) {
                            return -1;
                        }
                        bl5 = true;
                    } else if (!bl42 && afh2 instanceof agh && afh2.t() == arm.d) {
                        return 0;
                    }
                    if (pk2.o.p(\u26032).c() instanceof afe) {
                        if (pk2.o.p(cj2).c() instanceof afe || pk2.o.p(cj2.b()).c() instanceof afe) continue;
                        return -3;
                    }
                    if (afh2.b(adq2, (cj)\u26032) || bl3 && afh2 instanceof agh && afh2.t() == arm.d) continue;
                    if (afh2 instanceof agt || afh2 instanceof agu || afh2 instanceof akl) {
                        return -3;
                    }
                    if (afh2 == afi.bd || afh2 == afi.cw) {
                        return -4;
                    }
                    arm \u26033 = afh2.t();
                    if (\u26033 == arm.i) {
                        if (pk2.ab()) continue;
                        return -2;
                    }
                    return 0;
                }
            }
        }
        return bl5 ? 2 : 1;
    }

    public void a(boolean bl2) {
        this.f = bl2;
    }

    public void b(boolean bl2) {
        this.g = bl2;
    }

    public void c(boolean bl2) {
        this.h = bl2;
    }

    public void d(boolean bl2) {
        this.i = bl2;
    }

    public boolean b() {
        return this.f;
    }

    public boolean d() {
        return this.i;
    }

    public boolean e() {
        return this.h;
    }
}

