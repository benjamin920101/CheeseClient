/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aoy
extends aot {
    private final afh a;
    private final boolean b;

    public aoy(afh afh2, boolean bl2) {
        this.a = afh2;
        this.b = bl2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        if (adm2.p(cj2.a()).c() != afi.aV) {
            return false;
        }
        if (adm2.p(cj2).c().t() != arm.a && adm2.p(cj2).c() != afi.aV) {
            return false;
        }
        int n2 = 0;
        if (adm2.p(cj2.e()).c() == afi.aV) {
            ++n2;
        }
        if (adm2.p(cj2.f()).c() == afi.aV) {
            ++n2;
        }
        if (adm2.p(cj2.c()).c() == afi.aV) {
            ++n2;
        }
        if (adm2.p(cj2.d()).c() == afi.aV) {
            ++n2;
        }
        if (adm2.p(cj2.b()).c() == afi.aV) {
            ++n2;
        }
        \u2603 = 0;
        if (adm2.d(cj2.e())) {
            ++\u2603;
        }
        if (adm2.d(cj2.f())) {
            ++\u2603;
        }
        if (adm2.d(cj2.c())) {
            ++\u2603;
        }
        if (adm2.d(cj2.d())) {
            ++\u2603;
        }
        if (adm2.d(cj2.b())) {
            ++\u2603;
        }
        if (!this.b && n2 == 4 && \u2603 == 1 || n2 == 5) {
            adm2.a(cj2, this.a.Q(), 2);
            adm2.a(this.a, cj2, random);
        }
        return true;
    }
}

