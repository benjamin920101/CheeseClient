/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apo
extends aot {
    private afh a;
    private int b;

    public apo(afh afh2, int n2) {
        this.a = afh2;
        this.b = n2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        if (adm2.p(cj2).c().t() != arm.h) {
            return false;
        }
        int n2 = random.nextInt(this.b - 2) + 2;
        \u2603 = 2;
        for (\u2603 = cj2.n() - n2; \u2603 <= cj2.n() + n2; ++\u2603) {
            for (\u2603 = cj2.p() - n2; \u2603 <= cj2.p() + n2; ++\u2603) {
                \u2603 = \u2603 - cj2.n();
                if (\u2603 * \u2603 + (\u2603 = \u2603 - cj2.p()) * \u2603 > n2 * n2) continue;
                for (\u2603 = cj2.o() - \u2603; \u2603 <= cj2.o() + \u2603; ++\u2603) {
                    cj cj3 = new cj(\u2603, \u2603, \u2603);
                    afh \u26032 = adm2.p(cj3).c();
                    if (\u26032 != afi.d && \u26032 != afi.c) continue;
                    adm2.a(cj3, this.a.Q(), 2);
                }
            }
        }
        return true;
    }
}

