/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class agn
extends afc {
    protected agn(arm arm2) {
        super(arm2);
        this.a(1.0f);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alp();
    }

    @Override
    public void a(adq adq2, cj cj2) {
        float f2 = 0.0625f;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, f2, 1.0f);
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (cq2 == cq.a) {
            return super.a(adq2, cj2, cq2);
        }
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (pk2.m == null && pk2.l == null && !adm2.D) {
            pk2.c(1);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        double d2 = (float)cj2.n() + random.nextFloat();
        \u2603 = (float)cj2.o() + 0.8f;
        \u2603 = (float)cj2.p() + random.nextFloat();
        \u2603 = 0.0;
        \u2603 = 0.0;
        \u2603 = 0.0;
        adm2.a(cy.l, d2, \u2603, \u2603, \u2603, \u2603, \u2603, new int[0]);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return null;
    }

    @Override
    public arn g(alz alz2) {
        return arn.E;
    }
}

