/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agm
extends afc {
    protected agm() {
        super(arm.e, arn.D);
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
        this.e(0);
        this.a(yz.c);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        super.c(adm2, cj2, alz2, random);
        for (int i2 = -2; i2 <= 2; ++i2) {
            block1: for (\u2603 = -2; \u2603 <= 2; ++\u2603) {
                if (i2 > -2 && i2 < 2 && \u2603 == -1) {
                    \u2603 = 2;
                }
                if (random.nextInt(16) != 0) continue;
                for (\u2603 = 0; \u2603 <= 1; ++\u2603) {
                    cj cj3 = cj2.a(i2, \u2603, \u2603);
                    if (adm2.p(cj3).c() != afi.X) continue;
                    if (!adm2.d(cj2.a(i2 / 2, 0, \u2603 / 2))) continue block1;
                    adm2.a(cy.z, (double)cj2.n() + 0.5, (double)cj2.o() + 2.0, (double)cj2.p() + 0.5, (double)((float)i2 + random.nextFloat()) - 0.5, (double)((float)\u2603 - random.nextFloat() - 1.0f), (double)((float)\u2603 + random.nextFloat()) - 0.5, new int[0]);
                }
            }
        }
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new ale();
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof ale) {
            wn2.a((ale)akw2);
        }
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        super.a(adm2, cj2, alz2, pr2, zx2);
        if (zx2.s() && (\u2603 = adm2.s(cj2)) instanceof ale) {
            ((ale)\u2603).a(zx2.q());
        }
    }
}

