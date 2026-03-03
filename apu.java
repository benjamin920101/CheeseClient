/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apu
extends aot {
    private final alz a;

    public apu(akc.a a2) {
        this.a = afi.H.Q().a(akc.a, a2);
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        while (((\u2603 = adm2.p(cj2).c()).t() == arm.a || \u2603.t() == arm.j) && cj2.o() > 0) {
            cj2 = cj2.b();
        }
        for (int i2 = 0; i2 < 128; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3) || !afi.H.f(adm2, cj3, this.a)) continue;
            adm2.a(cj3, this.a, 2);
        }
        return true;
    }
}

