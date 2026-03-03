/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apt
extends aoh {
    private static final alz a = afi.r.Q().a(ail.b, aio.a.a);
    private static final alz b = afi.t.Q().a(aik.Q, aio.a.a).a(aik.b, false);

    public apt() {
        super(false);
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj22) {
        int n2;
        int n3 = random.nextInt(4) + 5;
        while (adm2.p(cj22.b()).c().t() == arm.h) {
            cj cj22 = cj22.b();
        }
        boolean bl2 = true;
        if (cj22.o() < 1 || cj22.o() + n3 + 1 > 256) {
            return false;
        }
        for (int i2 = cj22.o(); i2 <= cj22.o() + 1 + n3; ++i2) {
            n2 = 1;
            if (i2 == cj22.o()) {
                n2 = 0;
            }
            if (i2 >= cj22.o() + 1 + n3 - 2) {
                n2 = 3;
            }
            cj.a a2 = new cj.a();
            for (int i3 = cj22.n() - n2; i3 <= cj22.n() + n2 && bl2; ++i3) {
                for (\u2603 = cj22.p() - n2; \u2603 <= cj22.p() + n2 && bl2; ++\u2603) {
                    if (i2 >= 0 && i2 < 256) {
                        afh afh2 = adm2.p(a2.c(i3, i2, \u2603)).c();
                        if (afh2.t() == arm.a || afh2.t() == arm.j) continue;
                        if (afh2 == afi.j || afh2 == afi.i) {
                            if (i2 <= cj22.o()) continue;
                            bl2 = false;
                            continue;
                        }
                        bl2 = false;
                        continue;
                    }
                    bl2 = false;
                }
            }
        }
        if (!bl2) {
            return false;
        }
        afh \u26032 = adm2.p(cj22.b()).c();
        if (\u26032 != afi.c && \u26032 != afi.d || cj22.o() >= 256 - n3 - 1) {
            return false;
        }
        this.a(adm2, cj22.b());
        for (n2 = cj22.o() - 3 + n3; n2 <= cj22.o() + n3; ++n2) {
            \u2603 = n2 - (cj22.o() + n3);
            i3 = 2 - \u2603 / 2;
            for (\u2603 = cj22.n() - i3; \u2603 <= cj22.n() + i3; ++\u2603) {
                \u2603 = \u2603 - cj22.n();
                for (\u2603 = cj22.p() - i3; \u2603 <= cj22.p() + i3; ++\u2603) {
                    \u2603 = \u2603 - cj22.p();
                    if (Math.abs(\u2603) == i3 && Math.abs(\u2603) == i3 && (random.nextInt(2) == 0 || \u2603 == 0) || adm2.p(\u2603 = new cj(\u2603, n2, \u2603)).c().o()) continue;
                    this.a(adm2, \u2603, b);
                }
            }
        }
        for (n2 = 0; n2 < n3; ++n2) {
            afh afh3 = adm2.p(cj22.b(n2)).c();
            if (afh3.t() != arm.a && afh3.t() != arm.j && afh3 != afi.i && afh3 != afi.j) continue;
            this.a(adm2, cj22.b(n2), a);
        }
        for (n2 = cj22.o() - 3 + n3; n2 <= cj22.o() + n3; ++n2) {
            \u2603 = n2 - (cj22.o() + n3);
            i3 = 2 - \u2603 / 2;
            cj.a a3 = new cj.a();
            for (int i4 = cj22.n() - i3; i4 <= cj22.n() + i3; ++i4) {
                for (\u2603 = cj22.p() - i3; \u2603 <= cj22.p() + i3; ++\u2603) {
                    a3.c(i4, n2, \u2603);
                    if (adm2.p(a3).c().t() != arm.j) continue;
                    cj cj3 = a3.e();
                    \u2603 = a3.f();
                    \u2603 = a3.c();
                    \u2603 = a3.d();
                    if (random.nextInt(4) == 0 && adm2.p(cj3).c().t() == arm.a) {
                        this.a(adm2, cj3, akk.N);
                    }
                    if (random.nextInt(4) == 0 && adm2.p(\u2603).c().t() == arm.a) {
                        this.a(adm2, \u2603, akk.P);
                    }
                    if (random.nextInt(4) == 0 && adm2.p(\u2603).c().t() == arm.a) {
                        this.a(adm2, \u2603, akk.O);
                    }
                    if (random.nextInt(4) != 0 || adm2.p(\u2603).c().t() != arm.a) continue;
                    this.a(adm2, \u2603, akk.b);
                }
            }
        }
        return true;
    }

    private void a(adm adm2, cj \u260322, amk amk2) {
        alz alz2 = afi.bn.Q().a(amk2, true);
        this.a(adm2, \u260322, alz2);
        cj \u260322 = \u260322.b();
        for (int i2 = 4; adm2.p(\u260322).c().t() == arm.a && i2 > 0; --i2) {
            this.a(adm2, \u260322, alz2);
            \u260322 = \u260322.b();
        }
    }
}

