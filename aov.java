/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aov
extends apv {
    private final alz a;
    private final alz b;

    public aov(alz alz2, alz alz3) {
        super(false);
        this.b = alz2;
        this.a = alz3;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj22) {
        while (((\u2603 = adm2.p(cj22).c()).t() == arm.a || \u2603.t() == arm.j) && cj22.o() > 0) {
            cj22 = cj22.b();
        }
        afh afh2 = adm2.p(cj22).c();
        if (afh2 == afi.d || afh2 == afi.c) {
            cj cj22 = cj22.a();
            this.a(adm2, cj22, this.b);
            for (int i2 = cj22.o(); i2 <= cj22.o() + 2; ++i2) {
                \u2603 = i2 - cj22.o();
                \u2603 = 2 - \u2603;
                for (\u2603 = cj22.n() - \u2603; \u2603 <= cj22.n() + \u2603; ++\u2603) {
                    \u2603 = \u2603 - cj22.n();
                    for (\u2603 = cj22.p() - \u2603; \u2603 <= cj22.p() + \u2603; ++\u2603) {
                        \u2603 = \u2603 - cj22.p();
                        if (Math.abs(\u2603) == \u2603 && Math.abs(\u2603) == \u2603 && random.nextInt(2) == 0 || adm2.p(\u2603 = new cj(\u2603, i2, \u2603)).c().o()) continue;
                        this.a(adm2, \u2603, this.a);
                    }
                }
            }
        }
        return true;
    }
}

