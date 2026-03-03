/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aou
extends aot {
    private agw a;
    private alz b;

    public aou(agw agw2, agw.a a2) {
        this.a(agw2, a2);
    }

    public void a(agw agw2, agw.a a2) {
        this.a = agw2;
        this.b = agw2.Q().a(agw2.n(), a2);
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        for (int i2 = 0; i2 < 64; ++i2) {
            cj cj3 = cj2.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!adm2.d(cj3) || adm2.t.o() && cj3.o() >= 255 || !this.a.f(adm2, cj3, this.b)) continue;
            adm2.a(cj3, this.b, 2);
        }
        return true;
    }
}

