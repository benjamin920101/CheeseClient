/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apw
extends aot {
    @Override
    public boolean b(adm adm2, Random random, cj cj22) {
        while (cj22.o() < 128) {
            cj cj22;
            if (adm2.d(cj22)) {
                for (cq cq2 : cq.c.a.a()) {
                    if (!afi.bn.b(adm2, cj22, cq2)) continue;
                    alz alz2 = afi.bn.Q().a(akk.b, cq2 == cq.c).a(akk.N, cq2 == cq.f).a(akk.O, cq2 == cq.d).a(akk.P, cq2 == cq.e);
                    adm2.a(cj22, alz2, 2);
                    break;
                }
            } else {
                cj22 = cj22.a(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
            }
            cj22 = cj22.a();
        }
        return true;
    }
}

