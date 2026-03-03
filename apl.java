/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apl
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        for (int i2 = 0; i2 < 64; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3) || adm2.p(cj3.b()).c() != afi.c || !afi.aU.d(adm2, cj3)) continue;
            adm2.a(cj3, afi.aU.Q().a(aiv.O, cq.c.a.a(random)), 2);
        }
        return true;
    }
}

