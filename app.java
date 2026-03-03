/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class app
extends aoh {
    private static final alz a = afi.s.Q().a(aig.b, aio.a.e);
    private static final alz b = afi.u.Q().a(aif.Q, aio.a.e).a(ahs.b, false);

    public app(boolean bl2) {
        super(bl2);
    }

    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        int n2;
        adm adm22;
        int \u26036;
        int n3 = random.nextInt(3) + random.nextInt(3) + 5;
        boolean \u26032 = true;
        if (cj2.o() < 1 || cj2.o() + n3 + 1 > 256) {
            return false;
        }
        for (\u2603 = cj2.o(); \u2603 <= cj2.o() + 1 + n3; ++\u2603) {
            \u2603 = 1;
            if (\u2603 == cj2.o()) {
                \u2603 = 0;
            }
            if (\u2603 >= cj2.o() + 1 + n3 - 2) {
                \u2603 = 2;
            }
            cj.a a2 = new cj.a();
            for (\u26036 = cj2.n() - \u2603; \u26036 <= cj2.n() + \u2603 && \u26032; ++\u26036) {
                for (\u26037 = cj2.p() - \u2603; \u26037 <= cj2.p() + \u2603 && \u26032; ++\u26037) {
                    if (\u2603 >= 0 && \u2603 < 256) {
                        if (this.a(adm22.p(a2.c(\u26036, \u2603, \u26037)).c())) continue;
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
        if (\u26033 != afi.c && \u26033 != afi.d || cj2.o() >= 256 - n3 - 1) {
            return false;
        }
        this.a(adm22, cj2.b());
        cq \u26034 = cq.c.a.a(random);
        int \u26035 = n3 - random.nextInt(4) - 1;
        \u26036 = 3 - random.nextInt(3);
        int \u26037 = cj2.n();
        int \u26038 = cj2.p();
        int \u26039 = 0;
        for (int i2 = 0; i2 < n3; ++i2) {
            n2 = cj2.o() + i2;
            if (i2 >= \u26035 && \u26036 > 0) {
                \u26037 += \u26034.g();
                \u26038 += \u26034.i();
                --\u26036;
            }
            if ((\u2603 = adm22.p(\u2603 = new cj(\u26037, n2, \u26038)).c().t()) != arm.a && \u2603 != arm.j) continue;
            this.b(adm22, \u2603);
            \u26039 = n2;
        }
        Object \u260310 = new cj(\u26037, \u26039, \u26038);
        for (n2 = -3; n2 <= 3; ++n2) {
            for (\u2603 = -3; \u2603 <= 3; ++\u2603) {
                if (Math.abs(n2) == 3 && Math.abs(\u2603) == 3) continue;
                this.c(adm22, ((cj)\u260310).a(n2, 0, \u2603));
            }
        }
        \u260310 = ((cj)\u260310).a();
        for (n2 = -1; n2 <= 1; ++n2) {
            for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                this.c(adm22, ((cj)\u260310).a(n2, 0, \u2603));
            }
        }
        this.c(adm22, ((cj)\u260310).g(2));
        this.c(adm22, ((cj)\u260310).f(2));
        this.c(adm22, ((cj)\u260310).e(2));
        this.c(adm22, ((cj)\u260310).d(2));
        \u26037 = cj2.n();
        \u26038 = cj2.p();
        \u260310 = cq.c.a.a(random);
        if (\u260310 != \u26034) {
            n2 = \u26035 - random.nextInt(2) - 1;
            \u2603 = 1 + random.nextInt(3);
            \u26039 = 0;
            for (\u2603 = n2; \u2603 < n3 && \u2603 > 0; ++\u2603, --\u2603) {
                if (\u2603 < 1) continue;
                \u260312 = cj2.o() + \u2603;
                cj cj3 = new cj(\u26037 += ((cq)\u260310).g(), \u260312, \u26038 += ((cq)\u260310).i());
                arm \u260311 = adm22.p(cj3).c().t();
                if (\u260311 != arm.a && \u260311 != arm.j) continue;
                this.b(adm22, cj3);
                \u26039 = \u260312;
            }
            if (\u26039 > 0) {
                cj cj4;
                int \u260312;
                cj4 = new cj(\u26037, \u26039, \u26038);
                for (\u260312 = -2; \u260312 <= 2; ++\u260312) {
                    for (int i3 = -2; i3 <= 2; ++i3) {
                        if (Math.abs(\u260312) == 2 && Math.abs(i3) == 2) continue;
                        this.c(adm22, cj4.a(\u260312, 0, i3));
                    }
                }
                cj4 = cj4.a();
                for (\u260312 = -1; \u260312 <= 1; ++\u260312) {
                    for (int i4 = -1; i4 <= 1; ++i4) {
                        this.c(adm22, cj4.a(\u260312, 0, i4));
                    }
                }
            }
        }
        return true;
    }

    private void b(adm adm2, cj cj2) {
        this.a(adm2, cj2, a);
    }

    private void c(adm adm2, cj cj2) {
        arm arm2 = adm2.p(cj2).c().t();
        if (arm2 == arm.a || arm2 == arm.j) {
            this.a(adm2, cj2, b);
        }
    }
}

