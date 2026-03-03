/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apr
extends aot {
    private afh a;

    public apr(afh afh2) {
        this.a = afh2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        if (adm2.p(cj2.a()).c() != afi.b) {
            return false;
        }
        if (adm2.p(cj2.b()).c() != afi.b) {
            return false;
        }
        if (adm2.p(cj2).c().t() != arm.a && adm2.p(cj2).c() != afi.b) {
            return false;
        }
        int n2 = 0;
        if (adm2.p(cj2.e()).c() == afi.b) {
            ++n2;
        }
        if (adm2.p(cj2.f()).c() == afi.b) {
            ++n2;
        }
        if (adm2.p(cj2.c()).c() == afi.b) {
            ++n2;
        }
        if (adm2.p(cj2.d()).c() == afi.b) {
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
        if (n2 == 3 && \u2603 == 1) {
            adm2.a(cj2, this.a.Q(), 2);
            adm2.a(this.a, cj2, random);
        }
        return true;
    }
}

