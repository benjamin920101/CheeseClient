/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apx
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        for (int i2 = 0; i2 < 10; ++i2) {
            \u2603 = cj2.n() + random.nextInt(8) - random.nextInt(8);
            if (!adm2.d(new cj(\u2603, \u2603 = cj2.o() + random.nextInt(4) - random.nextInt(4), \u2603 = cj2.p() + random.nextInt(8) - random.nextInt(8))) || !afi.bx.d(adm2, new cj(\u2603, \u2603, \u2603))) continue;
            adm2.a(new cj(\u2603, \u2603, \u2603), afi.bx.Q(), 2);
        }
        return true;
    }
}

