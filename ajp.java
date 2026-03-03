/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ajp
extends afh {
    public static final amn a = amn.a("layers", 1, 8);

    protected ajp() {
        super(arm.y);
        this.j(this.M.b().a(a, 1));
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.a(true);
        this.a(yz.c);
        this.j();
    }

    @Override
    public boolean b(adq adq2, cj cj2) {
        return adq2.p(cj2).b(a) < 5;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        int n2 = alz2.b(a) - 1;
        float \u26032 = 0.125f;
        return new aug((double)cj2.n() + this.B, (double)cj2.o() + this.C, (double)cj2.p() + this.D, (double)cj2.n() + this.E, (float)cj2.o() + (float)n2 * \u26032, (double)cj2.p() + this.G);
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
    public void j() {
        this.b(0);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        this.b(alz2.b(a));
    }

    protected void b(int n2) {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, (float)n2 / 8.0f, 1.0f);
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2.b());
        afh \u26032 = alz2.c();
        if (\u26032 == afi.aI || \u26032 == afi.cB) {
            return false;
        }
        if (\u26032.t() == arm.j) {
            return true;
        }
        if (\u26032 == this && alz2.b(a) >= 7) {
            return true;
        }
        return \u26032.c() && \u26032.J.c();
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        this.e(adm2, cj2, alz2);
    }

    private boolean e(adm adm2, cj cj2, alz alz2) {
        if (!this.d(adm2, cj2)) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
            return false;
        }
        return true;
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        ajp.a(adm2, cj2, new zx(zy.aD, alz2.b(a) + 1, 0));
        adm2.g(cj2);
        wn2.b(na.ab[afh.a(this)]);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.aD;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.b(ads.b, cj2) > 11) {
            this.b(adm2, cj2, adm2.p(cj2), 0);
            adm2.g(cj2);
        }
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (cq2 == cq.b) {
            return true;
        }
        return super.a(adq2, cj2, cq2);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, (n2 & 7) + 1);
    }

    @Override
    public boolean a(adm adm2, cj cj2) {
        return adm2.p(cj2).b(a) == 1;
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a) - 1;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

