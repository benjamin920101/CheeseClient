/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apm
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        for (int i2 = 0; i2 < 20; ++i2) {
            cj cj3 = cj2.a(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
            if (!adm2.d(cj3) || adm2.p((\u2603 = cj3.b()).e()).c().t() != arm.h && adm2.p(\u2603.f()).c().t() != arm.h && adm2.p(\u2603.c()).c().t() != arm.h && adm2.p(\u2603.d()).c().t() != arm.h) continue;
            int \u26032 = 2 + random.nextInt(random.nextInt(3) + 1);
            for (int i3 = 0; i3 < \u26032; ++i3) {
                if (!afi.aM.e(adm2, cj3)) continue;
                adm2.a(cj3.b(i3), afi.aM.Q(), 2);
            }
        }
        return true;
    }
}

