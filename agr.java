/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agr
extends afh {
    public static boolean N;

    public agr() {
        super(arm.p);
        this.a(yz.b);
    }

    public agr(arm arm2) {
        super(arm2);
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
        if (!adm2.D) {
            this.f(adm2, cj2);
        }
    }

    private void f(adm adm22, cj cj2) {
        adm adm22;
        if (!agr.e(adm22, cj2.b()) || cj2.o() < 0) {
            return;
        }
        int n2 = 32;
        if (N || !adm22.a(cj2.a(-n2, -n2, -n2), cj2.a(n2, n2, n2))) {
            adm22.g(cj2);
            cj cj3 = cj2.b();
            while (agr.e(adm22, cj3) && cj3.o() > 0) {
                cj3 = cj3.b();
            }
            if (cj3.o() > 0) {
                adm22.a(cj3.a(), this.Q());
            }
        } else if (!adm22.D) {
            uy uy2 = new uy(adm22, (double)cj2.n() + 0.5, cj2.o(), (double)cj2.p() + 0.5, adm22.p(cj2));
            this.a(uy2);
            adm22.d(uy2);
        }
    }

    protected void a(uy uy2) {
    }

    @Override
    public int a(adm adm2) {
        return 2;
    }

    public static boolean e(adm adm2, cj cj2) {
        afh afh2 = adm2.p(cj2).c();
        arm \u26032 = afh2.J;
        return afh2 == afi.ab || \u26032 == arm.a || \u26032 == arm.h || \u26032 == arm.i;
    }

    public void a_(adm adm2, cj cj2) {
    }
}

