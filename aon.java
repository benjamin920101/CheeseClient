/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aon
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        for (int i2 = 0; i2 < 10; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3)) continue;
            int \u26032 = 1 + random.nextInt(random.nextInt(3) + 1);
            for (int i3 = 0; i3 < \u26032; ++i3) {
                if (!afi.aK.e(adm2, cj3)) continue;
                adm2.a(cj3.b(i3), afi.aK.Q(), 2);
            }
        }
        return true;
    }
}

