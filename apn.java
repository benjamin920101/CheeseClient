/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apn
extends aoh {
    private static final alz a = afi.s.Q().a(aig.b, aio.a.f);
    private static final alz b = afi.u.Q().a(aif.Q, aio.a.f).a(ahs.b, false);

    public apn(boolean bl2) {
        super(bl2);
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        int n2 = random.nextInt(3) + random.nextInt(2) + 6;
        \u2603 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        if (\u2603 < 1 || \u2603 + n2 + 1 >= 256) {
            return false;
        }
        cj \u26032 = cj2.b();
        afh \u26033 = adm2.p(\u26032).c();
        if (\u26033 != afi.c && \u26033 != afi.d) {
            return false;
        }
        if (!this.a(adm2, cj2, n2)) {
            return false;
        }
        this.a(adm2, \u26032);
        this.a(adm2, \u26032.f());
        this.a(adm2, \u26032.d());
        this.a(adm2, \u26032.d().f());
        cq \u26034 = cq.c.a.a(random);
        \u2603 = n2 - random.nextInt(4);
        \u2603 = 2 - random.nextInt(3);
        \u2603 = \u2603;
        \u2603 = \u2603;
        \u2603 = \u2603 + n2 - 1;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            if (\u2603 >= \u2603 && \u2603 > 0) {
                \u2603 += \u26034.g();
                \u2603 += \u26034.i();
                --\u2603;
            }
            if ((\u2603 = adm2.p(\u2603 = new cj(\u2603, \u2603 = \u2603 + \u2603, \u2603)).c().t()) != arm.a && \u2603 != arm.j) continue;
            this.b(adm2, \u2603);
            this.b(adm2, \u2603.f());
            this.b(adm2, \u2603.d());
            this.b(adm2, \u2603.f().d());
        }
        for (\u2603 = -2; \u2603 <= 0; ++\u2603) {
            for (\u2603 = -2; \u2603 <= 0; ++\u2603) {
                \u2603 = -1;
                this.a(adm2, \u2603 + \u2603, \u2603 + \u2603, \u2603 + \u2603);
                this.a(adm2, 1 + \u2603 - \u2603, \u2603 + \u2603, \u2603 + \u2603);
                this.a(adm2, \u2603 + \u2603, \u2603 + \u2603, 1 + \u2603 - \u2603);
                this.a(adm2, 1 + \u2603 - \u2603, \u2603 + \u2603, 1 + \u2603 - \u2603);
                if (\u2603 <= -2 && \u2603 <= -1 || \u2603 == -1 && \u2603 == -2) continue;
                \u2603 = 1;
                this.a(adm2, \u2603 + \u2603, \u2603 + \u2603, \u2603 + \u2603);
                this.a(adm2, 1 + \u2603 - \u2603, \u2603 + \u2603, \u2603 + \u2603);
                this.a(adm2, \u2603 + \u2603, \u2603 + \u2603, 1 + \u2603 - \u2603);
                this.a(adm2, 1 + \u2603 - \u2603, \u2603 + \u2603, 1 + \u2603 - \u2603);
            }
        }
        if (random.nextBoolean()) {
            this.a(adm2, \u2603, \u2603 + 2, \u2603);
            this.a(adm2, \u2603 + 1, \u2603 + 2, \u2603);
            this.a(adm2, \u2603 + 1, \u2603 + 2, \u2603 + 1);
            this.a(adm2, \u2603, \u2603 + 2, \u2603 + 1);
        }
        for (\u2603 = -3; \u2603 <= 4; ++\u2603) {
            for (\u2603 = -3; \u2603 <= 4; ++\u2603) {
                if (\u2603 == -3 && \u2603 == -3 || \u2603 == -3 && \u2603 == 4 || \u2603 == 4 && \u2603 == -3 || \u2603 == 4 && \u2603 == 4 || Math.abs(\u2603) >= 3 && Math.abs(\u2603) >= 3) continue;
                this.a(adm2, \u2603 + \u2603, \u2603, \u2603 + \u2603);
            }
        }
        for (\u2603 = -1; \u2603 <= 2; ++\u2603) {
            for (\u2603 = -1; \u2603 <= 2; ++\u2603) {
                if (\u2603 >= 0 && \u2603 <= 1 && \u2603 >= 0 && \u2603 <= 1 || random.nextInt(3) > 0) continue;
                \u2603 = random.nextInt(3) + 2;
                for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                    this.b(adm2, new cj(\u2603 + \u2603, \u2603 - \u2603 - 1, \u2603 + \u2603));
                }
                for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                    for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                        this.a(adm2, \u2603 + \u2603 + \u2603, \u2603, \u2603 + \u2603 + \u2603);
                    }
                }
                for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                    for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                        if (Math.abs(\u2603) == 2 && Math.abs(\u2603) == 2) continue;
                        this.a(adm2, \u2603 + \u2603 + \u2603, \u2603 - 1, \u2603 + \u2603 + \u2603);
                    }
                }
            }
        }
        return true;
    }

    private boolean a(adm adm2, cj cj2, int n2) {
        \u2603 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        cj.a a2 = new cj.a();
        for (int i2 = 0; i2 <= n2 + 1; ++i2) {
            \u2603 = 1;
            if (i2 == 0) {
                \u2603 = 0;
            }
            if (i2 >= n2 - 1) {
                \u2603 = 2;
            }
            for (\u2603 = -\u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = -\u2603; \u2603 <= \u2603; ++\u2603) {
                    if (this.a(adm2.p(a2.c(\u2603 + \u2603, \u2603 + i2, \u2603 + \u2603)).c())) continue;
                    return false;
                }
            }
        }
        return true;
    }

    private void b(adm adm2, cj cj2) {
        if (this.a(adm2.p(cj2).c())) {
            this.a(adm2, cj2, a);
        }
    }

    private void a(adm adm2, int n2, int n3, int n4) {
        cj cj2 = new cj(n2, n3, n4);
        afh \u26032 = adm2.p(cj2).c();
        if (\u26032.t() == arm.a) {
            this.a(adm2, cj2, b);
        }
    }
}

