/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aok
extends aot {
    private final afh a;
    private final int b;

    public aok(afh afh2, int n2) {
        super(false);
        this.a = afh2;
        this.b = n2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj22) {
        while (cj22.o() > 3 && (adm2.d(cj22.b()) || (\u2603 = adm2.p(cj22.b()).c()) != afi.c && \u2603 != afi.d && \u2603 != afi.b)) {
            cj22 = cj22.b();
        }
        if (cj22.o() <= 3) {
            return false;
        }
        int n2 = this.b;
        for (\u2603 = 0; n2 >= 0 && \u2603 < 3; ++\u2603) {
            cj cj22;
            \u2603 = n2 + random.nextInt(2);
            \u2603 = n2 + random.nextInt(2);
            \u2603 = n2 + random.nextInt(2);
            float f2 = (float)(\u2603 + \u2603 + \u2603) * 0.333f + 0.5f;
            for (cj cj3 : cj.a(cj22.a(-\u2603, -\u2603, -\u2603), cj22.a(\u2603, \u2603, \u2603))) {
                if (!(cj3.i(cj22) <= (double)(f2 * f2))) continue;
                adm2.a(cj3, this.a.Q(), 4);
            }
            cj22 = cj22.a(-(n2 + 1) + random.nextInt(2 + n2 * 2), 0 - random.nextInt(2), -(n2 + 1) + random.nextInt(2 + n2 * 2));
        }
        return true;
    }
}

