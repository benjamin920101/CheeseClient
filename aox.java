/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aox
extends aot {
    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        if (!adm22.d(cj2)) {
            return false;
        }
        if (adm22.p(cj2.a()).c() != afi.aV) {
            return false;
        }
        adm22.a(cj2, afi.aX.Q(), 2);
        for (int i2 = 0; i2 < 1500; ++i2) {
            adm adm22;
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), -random.nextInt(12), random.nextInt(8) - random.nextInt(8));
            if (adm22.p(cj3).c().t() != arm.a) continue;
            int \u26032 = 0;
            for (cq cq2 : cq.values()) {
                if (adm22.p(cj3.a(cq2)).c() == afi.aX) {
                    ++\u26032;
                }
                if (\u26032 > 1) break;
            }
            if (\u26032 != true) continue;
            adm22.a(cj3, afi.aX.Q(), 2);
        }
        return true;
    }
}

