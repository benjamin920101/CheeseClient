/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicates;
import java.util.Random;

public class aor
extends aot {
    private static final amh a = amh.a(afi.m).a(ajh.a, Predicates.equalTo(ajh.a.a));
    private final alz b = afi.U.Q().a(akb.N, akb.a.b).a(ahh.a, ahh.a.b);
    private final alz c = afi.A.Q();
    private final alz d = afi.i.Q();

    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        int n2;
        adm adm22;
        int n3;
        while (adm22.d(cj2) && cj2.o() > 2) {
            cj2 = cj2.b();
        }
        if (!a.a(adm22.p(cj2))) {
            return false;
        }
        for (n3 = -2; n3 <= 2; ++n3) {
            for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                if (!adm22.d(cj2.a(n3, -1, \u2603)) || !adm22.d(cj2.a(n3, -2, \u2603))) continue;
                return false;
            }
        }
        for (n3 = -1; n3 <= 0; ++n3) {
            for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                    adm22.a(cj2.a(\u2603, n3, \u2603), this.c, 2);
                }
            }
        }
        adm22.a(cj2, this.d, 2);
        for (cq cq2 : cq.c.a) {
            adm22.a(cj2.a(cq2), this.d, 2);
        }
        for (n2 = -2; n2 <= 2; ++n2) {
            for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                if (n2 != -2 && n2 != 2 && \u2603 != -2 && \u2603 != 2) continue;
                adm22.a(cj2.a(n2, 1, \u2603), this.c, 2);
            }
        }
        adm22.a(cj2.a(2, 1, 0), this.b, 2);
        adm22.a(cj2.a(-2, 1, 0), this.b, 2);
        adm22.a(cj2.a(0, 1, 2), this.b, 2);
        adm22.a(cj2.a(0, 1, -2), this.b, 2);
        for (n2 = -1; n2 <= 1; ++n2) {
            for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                if (n2 == 0 && \u2603 == 0) {
                    adm22.a(cj2.a(n2, 4, \u2603), this.c, 2);
                    continue;
                }
                adm22.a(cj2.a(n2, 4, \u2603), this.b, 2);
            }
        }
        for (n2 = 1; n2 <= 3; ++n2) {
            adm22.a(cj2.a(-1, n2, -1), this.c, 2);
            adm22.a(cj2.a(-1, n2, 1), this.c, 2);
            adm22.a(cj2.a(1, n2, -1), this.c, 2);
            adm22.a(cj2.a(1, n2, 1), this.c, 2);
        }
        return true;
    }
}

