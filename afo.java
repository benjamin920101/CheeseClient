/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afo
extends afh {
    public static final amn a = amn.a("age", 0, 15);

    protected afo() {
        super(arm.A);
        this.j(this.M.b().a(a, 0));
        this.a(true);
        this.a(yz.c);
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz2, Random random) {
        cj cj3 = cj2.a();
        if (!adm22.d(cj3)) {
            return;
        }
        int \u26032 = 1;
        while (adm22.p(cj2.c(\u26032)).c() == this) {
            ++\u26032;
        }
        if (\u26032 >= 3) {
            return;
        }
        int \u26033 = alz2.b(a);
        if (\u26033 == 15) {
            adm22.a(cj3, this.Q());
            alz alz3 = alz2.a(a, 0);
            adm22.a(cj2, alz3, 4);
            this.a(adm22, cj3, alz3, this);
        } else {
            adm adm22;
            adm22.a(cj2, alz2.a(a, \u26033 + 1), 4);
        }
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        float f2 = 0.0625f;
        return new aug((float)cj2.n() + f2, cj2.o(), (float)cj2.p() + f2, (float)(cj2.n() + 1) - f2, (float)(cj2.o() + 1) - f2, (float)(cj2.p() + 1) - f2);
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        float f2 = 0.0625f;
        return new aug((float)cj2.n() + f2, cj2.o(), (float)cj2.p() + f2, (float)(cj2.n() + 1) - f2, cj2.o() + 1, (float)(cj2.p() + 1) - f2);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        if (super.d(adm2, cj2)) {
            return this.e(adm2, cj2);
        }
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!this.e(adm2, cj2)) {
            adm2.b(cj2, true);
        }
    }

    public boolean e(adm adm22, cj cj2) {
        adm adm22;
        for (cq cq2 : cq.c.a) {
            if (!adm22.p(cj2.a(cq2)).c().t().a()) continue;
            return false;
        }
        afh \u26032 = adm22.p(cj2.b()).c();
        return \u26032 == afi.aK || \u26032 == afi.m;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        pk2.a(ow.h, 1.0f);
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a);
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

