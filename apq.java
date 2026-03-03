/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apq
extends aot {
    private afh a;

    public apq(afh afh2) {
        this.a = afh2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        if (!adm2.d(cj2) || adm2.p(cj2.b()).c() != this.a) {
            return false;
        }
        int n2 = random.nextInt(32) + 6;
        \u2603 = random.nextInt(4) + 1;
        cj.a \u26032 = new cj.a();
        for (\u2603 = cj2.n() - \u2603; \u2603 <= cj2.n() + \u2603; ++\u2603) {
            for (\u2603 = cj2.p() - \u2603; \u2603 <= cj2.p() + \u2603; ++\u2603) {
                \u2603 = \u2603 - cj2.n();
                if (\u2603 * \u2603 + (\u2603 = \u2603 - cj2.p()) * \u2603 > \u2603 * \u2603 + 1 || adm2.p(\u26032.c(\u2603, cj2.o() - 1, \u2603)).c() == this.a) continue;
                return false;
            }
        }
        for (\u2603 = cj2.o(); \u2603 < cj2.o() + n2 && \u2603 < 256; ++\u2603) {
            for (\u2603 = cj2.n() - \u2603; \u2603 <= cj2.n() + \u2603; ++\u2603) {
                for (\u2603 = cj2.p() - \u2603; \u2603 <= cj2.p() + \u2603; ++\u2603) {
                    \u2603 = \u2603 - cj2.n();
                    if (\u2603 * \u2603 + (\u2603 = \u2603 - cj2.p()) * \u2603 > \u2603 * \u2603 + 1) continue;
                    adm2.a(new cj(\u2603, \u2603, \u2603), afi.Z.Q(), 2);
                }
            }
        }
        uf \u26033 = new uf(adm2);
        \u26033.b((float)cj2.n() + 0.5f, cj2.o() + n2, (float)cj2.p() + 0.5f, random.nextFloat() * 360.0f, 0.0f);
        adm2.d(\u26033);
        adm2.a(cj2.b(n2), afi.h.Q(), 2);
        return true;
    }
}

