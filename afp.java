/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afp
extends afh {
    public static final amn a = amn.a("bites", 0, 6);

    protected afp() {
        super(arm.F);
        this.j(this.M.b().a(a, 0));
        this.a(true);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        float f2 = 0.0625f;
        \u2603 = (float)(1 + adq2.p(cj2).b(a) * 2) / 16.0f;
        \u2603 = 0.5f;
        this.a(\u2603, 0.0f, f2, 1.0f - f2, \u2603, 1.0f - f2);
    }

    @Override
    public void j() {
        float f2 = 0.0625f;
        \u2603 = 0.5f;
        this.a(f2, 0.0f, f2, 1.0f - f2, \u2603, 1.0f - f2);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        float f2 = 0.0625f;
        \u2603 = (float)(1 + alz2.b(a) * 2) / 16.0f;
        \u2603 = 0.5f;
        return new aug((float)cj2.n() + \u2603, cj2.o(), (float)cj2.p() + f2, (float)(cj2.n() + 1) - f2, (float)cj2.o() + \u2603, (float)(cj2.p() + 1) - f2);
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        return this.a(adm2, cj2, adm2.p(cj2));
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
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        this.b(adm2, cj2, alz2, wn2);
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, wn wn2) {
        this.b(adm2, cj2, adm2.p(cj2), wn2);
    }

    private void b(adm adm2, cj cj2, alz alz2, wn wn2) {
        if (!wn2.j(false)) {
            return;
        }
        wn2.b(na.H);
        wn2.cl().a(2, 0.1f);
        int n2 = alz2.b(a);
        if (n2 < 6) {
            adm2.a(cj2, alz2.a(a, n2 + 1), 3);
        } else {
            adm2.g(cj2);
        }
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
            adm2.g(cj2);
        }
    }

    private boolean e(adm adm2, cj cj2) {
        return adm2.p(cj2.b()).c().t().a();
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.aZ;
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

    @Override
    public int l(adm adm2, cj cj2) {
        return (7 - adm2.p(cj2).b(a)) * 2;
    }

    @Override
    public boolean O() {
        return true;
    }
}

