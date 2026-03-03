/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class aol
extends aot {
    private final List<ob> a;
    private final int b;

    public aol(List<ob> list, int n2) {
        this.a = list;
        this.b = n2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        while (((\u2603 = adm2.p(cj2).c()).t() == arm.a || \u2603.t() == arm.j) && cj2.o() > 1) {
            cj2 = cj2.b();
        }
        if (cj2.o() < 1) {
            return false;
        }
        cj2 = cj2.a();
        for (int i2 = 0; i2 < 4; ++i2) {
            cj cj3 = cj2.a(random.nextInt(4) - random.nextInt(4), random.nextInt(3) - random.nextInt(3), random.nextInt(4) - random.nextInt(4));
            if (!adm2.d(cj3) || !adm.a(adm2, cj3.b())) continue;
            adm2.a(cj3, afi.ae.Q(), 2);
            akw \u26032 = adm2.s(cj3);
            if (\u26032 instanceof aky) {
                ob.a(random, this.a, (aky)\u26032, this.b);
            }
            \u2603 = cj3.f();
            \u2603 = cj3.e();
            \u2603 = cj3.c();
            \u2603 = cj3.d();
            if (adm2.d(\u2603) && adm.a(adm2, \u2603.b())) {
                adm2.a(\u2603, afi.aa.Q(), 2);
            }
            if (adm2.d(\u2603) && adm.a(adm2, \u2603.b())) {
                adm2.a(\u2603, afi.aa.Q(), 2);
            }
            if (adm2.d(\u2603) && adm.a(adm2, \u2603.b())) {
                adm2.a(\u2603, afi.aa.Q(), 2);
            }
            if (adm2.d(\u2603) && adm.a(adm2, \u2603.b())) {
                adm2.a(\u2603, afi.aa.Q(), 2);
            }
            return true;
        }
        return false;
    }
}

