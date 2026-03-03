/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apv
extends aoh {
    private static final alz a = afi.r.Q().a(ail.b, aio.a.a);
    private static final alz b = afi.t.Q().a(aik.Q, aio.a.a).a(ahs.b, false);
    private final int c;
    private final boolean d;
    private final alz e;
    private final alz f;

    public apv(boolean bl2) {
        this(bl2, 4, a, b, false);
    }

    public apv(boolean bl2, int n2, alz alz2, alz alz3, boolean bl3) {
        super(bl2);
        this.c = n2;
        this.e = alz2;
        this.f = alz3;
        this.d = bl3;
    }

    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        adm adm22;
        int n2;
        int n3 = random.nextInt(3) + this.c;
        boolean \u26032 = true;
        if (cj2.o() < 1 || cj2.o() + n3 + 1 > 256) {
            return false;
        }
        for (\u2603 = cj2.o(); \u2603 <= cj2.o() + 1 + n3; ++\u2603) {
            \u26034 = 1;
            if (\u2603 == cj2.o()) {
                \u26034 = 0;
            }
            if (\u2603 >= cj2.o() + 1 + n3 - 2) {
                \u26034 = 2;
            }
            cj.a a2 = new cj.a();
            for (n2 = cj2.n() - \u26034; n2 <= cj2.n() + \u26034 && \u26032; ++n2) {
                for (\u2603 = cj2.p() - \u26034; \u2603 <= cj2.p() + \u26034 && \u26032; ++\u2603) {
                    if (\u2603 >= 0 && \u2603 < 256) {
                        if (this.a(adm22.p(a2.c(n2, \u2603, \u2603)).c())) continue;
                        \u26032 = false;
                        continue;
                    }
                    \u26032 = false;
                }
            }
        }
        if (!\u26032) {
            return false;
        }
        afh \u26033 = adm22.p(cj2.b()).c();
        if (\u26033 != afi.c && \u26033 != afi.d && \u26033 != afi.ak || cj2.o() >= 256 - n3 - 1) {
            return false;
        }
        this.a(adm22, cj2.b());
        int \u26034 = 3;
        int \u26035 = 0;
        for (n2 = cj2.o() - \u26034 + n3; n2 <= cj2.o() + n3; ++n2) {
            \u2603 = n2 - (cj2.o() + n3);
            \u2603 = \u26035 + 1 - \u2603 / 2;
            for (\u2603 = cj2.n() - \u2603; \u2603 <= cj2.n() + \u2603; ++\u2603) {
                i2 = \u2603 - cj2.n();
                for (\u2603 = cj2.p() - \u2603; \u2603 <= cj2.p() + \u2603; ++\u2603) {
                    \u2603 = \u2603 - cj2.p();
                    if (Math.abs(i2) == \u2603 && Math.abs(\u2603) == \u2603 && (random.nextInt(2) == 0 || \u2603 == 0) || ((afh)(\u26036 = adm22.p(\u2603 = new cj(\u2603, n2, \u2603)).c())).t() != arm.a && ((afh)\u26036).t() != arm.j && ((afh)\u26036).t() != arm.l) continue;
                    this.a(adm22, \u2603, this.f);
                }
            }
        }
        for (n2 = 0; n2 < n3; ++n2) {
            afh afh2 = adm22.p(cj2.b(n2)).c();
            if (afh2.t() != arm.a && afh2.t() != arm.j && afh2.t() != arm.l) continue;
            this.a(adm22, cj2.b(n2), this.e);
            if (!this.d || n2 <= 0) continue;
            if (random.nextInt(3) > 0 && adm22.d(cj2.a(-1, n2, 0))) {
                this.a(adm22, cj2.a(-1, n2, 0), akk.N);
            }
            if (random.nextInt(3) > 0 && adm22.d(cj2.a(1, n2, 0))) {
                this.a(adm22, cj2.a(1, n2, 0), akk.P);
            }
            if (random.nextInt(3) > 0 && adm22.d(cj2.a(0, n2, -1))) {
                this.a(adm22, cj2.a(0, n2, -1), akk.O);
            }
            if (random.nextInt(3) <= 0 || !adm22.d(cj2.a(0, n2, 1))) continue;
            this.a(adm22, cj2.a(0, n2, 1), akk.b);
        }
        if (this.d) {
            for (n2 = cj2.o() - 3 + n3; n2 <= cj2.o() + n3; ++n2) {
                \u2603 = n2 - (cj2.o() + n3);
                \u2603 = 2 - \u2603 / 2;
                cj.a a3 = new cj.a();
                for (int i2 = cj2.n() - \u2603; i2 <= cj2.n() + \u2603; ++i2) {
                    for (\u2603 = cj2.p() - \u2603; \u2603 <= cj2.p() + \u2603; ++\u2603) {
                        a3.c(i2, n2, \u2603);
                        if (adm22.p(a3).c().t() != arm.j) continue;
                        cj cj3 = a3.e();
                        \u2603 = a3.f();
                        Object \u26036 = a3.c();
                        \u2603 = a3.d();
                        if (random.nextInt(4) == 0 && adm22.p(cj3).c().t() == arm.a) {
                            this.b(adm22, cj3, akk.N);
                        }
                        if (random.nextInt(4) == 0 && adm22.p(\u2603).c().t() == arm.a) {
                            this.b(adm22, \u2603, akk.P);
                        }
                        if (random.nextInt(4) == 0 && adm22.p((cj)\u26036).c().t() == arm.a) {
                            this.b(adm22, (cj)\u26036, akk.O);
                        }
                        if (random.nextInt(4) != 0 || adm22.p(\u2603).c().t() != arm.a) continue;
                        this.b(adm22, \u2603, akk.b);
                    }
                }
            }
            if (random.nextInt(5) == 0 && n3 > 5) {
                for (n2 = 0; n2 < 2; ++n2) {
                    for (cq cq2 : cq.c.a) {
                        if (random.nextInt(4 - n2) != 0) continue;
                        \u2603 = cq2.d();
                        this.a(adm22, random.nextInt(3), cj2.a(\u2603.g(), n3 - 5 + n2, \u2603.i()), cq2);
                    }
                }
            }
        }
        return true;
    }

    private void a(adm adm2, int n2, cj cj2, cq cq2) {
        this.a(adm2, cj2, afi.bN.Q().a(afu.a, n2).a(afu.O, cq2));
    }

    private void a(adm adm2, cj cj2, amk amk2) {
        this.a(adm2, cj2, afi.bn.Q().a(amk2, true));
    }

    private void b(adm adm2, cj cj22, amk amk2) {
        this.a(adm2, cj22, amk2);
        cj cj22 = cj22.b();
        for (int i2 = 4; adm2.p(cj22).c().t() == arm.a && i2 > 0; --i2) {
            this.a(adm2, cj22, amk2);
            cj22 = cj22.b();
        }
    }
}

