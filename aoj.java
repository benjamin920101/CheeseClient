/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aoj
extends aoh {
    private static final alz a = afi.r.Q().a(ail.b, aio.a.c);
    private static final alz b = afi.t.Q().a(aik.Q, aio.a.c).a(aik.b, false);
    private boolean c;

    public aoj(boolean bl2, boolean bl3) {
        super(bl2);
        this.c = bl3;
    }

    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        int n2;
        adm adm22;
        int n3 = random.nextInt(3) + 5;
        if (this.c) {
            n3 += random.nextInt(7);
        }
        boolean \u26032 = true;
        if (cj2.o() < 1 || cj2.o() + n3 + 1 > 256) {
            return false;
        }
        for (\u2603 = cj2.o(); \u2603 <= cj2.o() + 1 + n3; ++\u2603) {
            n2 = 1;
            if (\u2603 == cj2.o()) {
                n2 = 0;
            }
            if (\u2603 >= cj2.o() + 1 + n3 - 2) {
                n2 = 2;
            }
            cj.a a2 = new cj.a();
            for (int i2 = cj2.n() - n2; i2 <= cj2.n() + n2 && \u26032; ++i2) {
                for (\u2603 = cj2.p() - n2; \u2603 <= cj2.p() + n2 && \u26032; ++\u2603) {
                    if (\u2603 >= 0 && \u2603 < 256) {
                        if (this.a(adm22.p(a2.c(i2, \u2603, \u2603)).c())) continue;
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
        for (n2 = cj2.o() - 3 + n3; n2 <= cj2.o() + n3; ++n2) {
            \u2603 = n2 - (cj2.o() + n3);
            i2 = 1 - \u2603 / 2;
            for (\u2603 = cj2.n() - i2; \u2603 <= cj2.n() + i2; ++\u2603) {
                \u2603 = \u2603 - cj2.n();
                for (\u2603 = cj2.p() - i2; \u2603 <= cj2.p() + i2; ++\u2603) {
                    \u2603 = \u2603 - cj2.p();
                    if (Math.abs(\u2603) == i2 && Math.abs(\u2603) == i2 && (random.nextInt(2) == 0 || \u2603 == 0) || (\u2603 = adm22.p(\u2603 = new cj(\u2603, n2, \u2603)).c()).t() != arm.a && \u2603.t() != arm.j) continue;
                    this.a(adm22, \u2603, b);
                }
            }
        }
        for (n2 = 0; n2 < n3; ++n2) {
            afh afh2 = adm22.p(cj2.b(n2)).c();
            if (afh2.t() != arm.a && afh2.t() != arm.j) continue;
            this.a(adm22, cj2.b(n2), a);
        }
        return true;
    }
}

