/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apa
extends aot {
    private afh a = afi.cB;
    private int b;

    public apa(int n2) {
        this.b = n2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        while (adm2.d(cj2) && cj2.o() > 2) {
            cj2 = cj2.b();
        }
        if (adm2.p(cj2).c() != afi.aJ) {
            return false;
        }
        int n2 = random.nextInt(this.b - 2) + 2;
        \u2603 = 1;
        for (\u2603 = cj2.n() - n2; \u2603 <= cj2.n() + n2; ++\u2603) {
            for (\u2603 = cj2.p() - n2; \u2603 <= cj2.p() + n2; ++\u2603) {
                \u2603 = \u2603 - cj2.n();
                if (\u2603 * \u2603 + (\u2603 = \u2603 - cj2.p()) * \u2603 > n2 * n2) continue;
                for (\u2603 = cj2.o() - \u2603; \u2603 <= cj2.o() + \u2603; ++\u2603) {
                    cj cj3 = new cj(\u2603, \u2603, \u2603);
                    afh \u26032 = adm2.p(cj3).c();
                    if (\u26032 != afi.d && \u26032 != afi.aJ && \u26032 != afi.aI) continue;
                    adm2.a(cj3, this.a.Q(), 2);
                }
            }
        }
        return true;
    }
}

