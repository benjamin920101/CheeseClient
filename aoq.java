/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aoq
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        while (((\u2603 = adm2.p(cj2).c()).t() == arm.a || \u2603.t() == arm.j) && cj2.o() > 0) {
            cj2 = cj2.b();
        }
        for (int i2 = 0; i2 < 4; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3) || !afi.I.f(adm2, cj3, afi.I.Q())) continue;
            adm2.a(cj3, afi.I.Q(), 2);
        }
        return true;
    }
}

