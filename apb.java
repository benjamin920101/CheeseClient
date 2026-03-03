/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apb
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj22) {
        int \u26032;
        int n2;
        while (adm2.d(cj22) && cj22.o() > 2) {
            cj22 = cj22.b();
        }
        if (adm2.p(cj22).c() != afi.aJ) {
            return false;
        }
        cj22 = cj22.b(random.nextInt(4));
        int n3 = random.nextInt(4) + 7;
        \u2603 = n3 / 4 + random.nextInt(2);
        if (\u2603 > 1 && random.nextInt(60) == 0) {
            cj cj22 = cj22.b(10 + random.nextInt(30));
        }
        for (n2 = 0; n2 < n3; ++n2) {
            float f2 = (1.0f - (float)n2 / (float)n3) * (float)\u2603;
            \u26032 = ns.f(f2);
            for (int i2 = -\u26032; i2 <= \u26032; ++i2) {
                float f3 = (float)ns.a(i2) - 0.25f;
                for (int i3 = -\u26032; i3 <= \u26032; ++i3) {
                    float f4 = (float)ns.a(i3) - 0.25f;
                    if ((i2 != 0 || i3 != 0) && f3 * f3 + f4 * f4 > f2 * f2 || (i2 == -\u26032 || i2 == \u26032 || i3 == -\u26032 || i3 == \u26032) && random.nextFloat() > 0.75f) continue;
                    afh \u26033 = adm2.p(cj22.a(i2, n2, i3)).c();
                    if (\u26033.t() == arm.a || \u26033 == afi.d || \u26033 == afi.aJ || \u26033 == afi.aI) {
                        this.a(adm2, cj22.a(i2, n2, i3), afi.cB.Q());
                    }
                    if (n2 == 0 || \u26032 <= 1 || (\u26033 = adm2.p(cj22.a(i2, -n2, i3)).c()).t() != arm.a && \u26033 != afi.d && \u26033 != afi.aJ && \u26033 != afi.aI) continue;
                    this.a(adm2, cj22.a(i2, -n2, i3), afi.cB.Q());
                }
            }
        }
        n2 = \u2603 - 1;
        if (n2 < 0) {
            n2 = 0;
        } else if (n2 > 1) {
            n2 = 1;
        }
        for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
            for (\u26032 = -n2; \u26032 <= n2; ++\u26032) {
                cj cj3 = cj22.a(\u2603, -1, \u26032);
                int \u26034 = 50;
                if (Math.abs(\u2603) == 1 && Math.abs(\u26032) == 1) {
                    \u26034 = random.nextInt(5);
                }
                while (cj3.o() > 50 && ((\u2603 = adm2.p(cj3).c()).t() == arm.a || \u2603 == afi.d || \u2603 == afi.aJ || \u2603 == afi.aI || \u2603 == afi.cB)) {
                    this.a(adm2, cj3, afi.cB.Q());
                    cj3 = cj3.b();
                    if (--\u26034 > 0) continue;
                    cj3 = cj3.c(random.nextInt(5) + 1);
                    \u26034 = random.nextInt(5);
                }
            }
        }
        return true;
    }
}

