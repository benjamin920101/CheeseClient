/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agj
extends afh {
    public agj() {
        super(arm.D, arn.E);
        this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 1.0f, 0.9375f);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        adm2.a(cj2, (afh)this, this.a(adm2));
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        adm2.a(cj2, (afh)this, this.a(adm2));
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        this.e(adm2, cj2);
    }

    private void e(adm adm22, cj cj2) {
        if (!agr.e(adm22, cj2.b()) || cj2.o() < 0) {
            return;
        }
        int n2 = 32;
        if (agr.N || !adm22.a(cj2.a(-n2, -n2, -n2), cj2.a(n2, n2, n2))) {
            adm22.g(cj2);
            cj cj3 = cj2;
            while (agr.e(adm22, cj3) && cj3.o() > 0) {
                cj3 = cj3.b();
            }
            if (cj3.o() > 0) {
                adm22.a(cj3, this.Q(), 2);
            }
        } else {
            adm adm22;
            adm22.d(new uy(adm22, (float)cj2.n() + 0.5f, cj2.o(), (float)cj2.p() + 0.5f, this.Q()));
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        this.f(adm2, cj2);
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, wn wn2) {
        this.f(adm2, cj2);
    }

    private void f(adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        if (alz2.c() != this) {
            return;
        }
        for (int i2 = 0; i2 < 1000; ++i2) {
            cj cj3 = cj2.a(adm2.s.nextInt(16) - adm2.s.nextInt(16), adm2.s.nextInt(8) - adm2.s.nextInt(8), adm2.s.nextInt(16) - adm2.s.nextInt(16));
            if (adm2.p((cj)cj3).c().J != arm.a) continue;
            if (adm2.D) {
                for (int i3 = 0; i3 < 128; ++i3) {
                    double d2 = adm2.s.nextDouble();
                    float \u26032 = (adm2.s.nextFloat() - 0.5f) * 0.2f;
                    float \u26033 = (adm2.s.nextFloat() - 0.5f) * 0.2f;
                    float \u26034 = (adm2.s.nextFloat() - 0.5f) * 0.2f;
                    \u2603 = (double)cj3.n() + (double)(cj2.n() - cj3.n()) * d2 + (adm2.s.nextDouble() - 0.5) * 1.0 + 0.5;
                    \u2603 = (double)cj3.o() + (double)(cj2.o() - cj3.o()) * d2 + adm2.s.nextDouble() * 1.0 - 0.5;
                    \u2603 = (double)cj3.p() + (double)(cj2.p() - cj3.p()) * d2 + (adm2.s.nextDouble() - 0.5) * 1.0 + 0.5;
                    adm2.a(cy.y, \u2603, \u2603, \u2603, (double)\u26032, (double)\u26033, (double)\u26034, new int[0]);
                }
            } else {
                adm2.a(cj3, alz2, 2);
                adm2.g(cj2);
            }
            return;
        }
    }

    @Override
    public int a(adm adm2) {
        return 5;
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
    public boolean a(adq adq2, cj cj2, cq cq2) {
        return true;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return null;
    }
}

