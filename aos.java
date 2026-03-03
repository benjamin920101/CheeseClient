/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aos
extends aot {
    private agi.b a;

    public void a(agi.b b2) {
        this.a = b2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        boolean \u26032 = false;
        for (int i2 = 0; i2 < 64; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3) || adm2.t.o() && cj3.o() >= 254 || !afi.cF.d(adm2, cj3)) continue;
            afi.cF.a(adm2, cj3, this.a, 2);
            \u26032 = true;
        }
        return \u26032;
    }
}

