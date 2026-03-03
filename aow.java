/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aow
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        for (int i2 = 0; i2 < 64; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3) || adm2.p(cj3.b()).c() != afi.aV) continue;
            adm2.a(cj3, afi.ab.Q(), 2);
        }
        return true;
    }
}

