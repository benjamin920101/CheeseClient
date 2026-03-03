/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class afd
extends afh {
    protected afd(arm arm2) {
        this(arm2, arm2.r());
    }

    protected afd(arm arm2, arn arn2) {
        super(arm2, arn2);
        this.a(yz.d);
        this.a(true);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.d(adq2.p(cj2));
    }

    protected void d(alz alz2) {
        boolean bl2 = this.e(alz2) > 0;
        float \u26032 = 0.0625f;
        if (bl2) {
            this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.03125f, 0.9375f);
        } else {
            this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.0625f, 0.9375f);
        }
    }

    @Override
    public int a(adm adm2) {
        return 20;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
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
    public boolean b(adq adq2, cj cj2) {
        return true;
    }

    @Override
    public boolean g() {
        return true;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return this.m(adm2, cj2.b());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!this.m(adm2, cj2.b())) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
    }

    private boolean m(adm adm2, cj cj2) {
        return adm.a(adm2, cj2) || adm2.p(cj2).c() instanceof agt;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, Random random) {
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        int n2 = this.e(alz2);
        if (n2 > 0) {
            this.a(adm2, cj2, alz2, n2);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (adm2.D) {
            return;
        }
        int n2 = this.e(alz2);
        if (n2 == 0) {
            this.a(adm2, cj2, alz2, n2);
        }
    }

    protected void a(adm adm2, cj cj2, alz alz22, int n2) {
        boolean bl2;
        \u2603 = this.f(adm2, cj2);
        boolean bl3 = n2 > 0;
        boolean bl4 = bl2 = \u2603 > 0;
        if (n2 != \u2603) {
            alz alz22 = this.a(alz22, \u2603);
            adm2.a(cj2, alz22, 2);
            this.e(adm2, cj2);
            adm2.b(cj2, cj2);
        }
        if (!bl2 && bl3) {
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.1, (double)cj2.p() + 0.5, "random.click", 0.3f, 0.5f);
        } else if (bl2 && !bl3) {
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.1, (double)cj2.p() + 0.5, "random.click", 0.3f, 0.6f);
        }
        if (bl2) {
            adm2.a(cj2, (afh)this, this.a(adm2));
        }
    }

    protected aug a(cj cj2) {
        float f2 = 0.125f;
        return new aug((float)cj2.n() + 0.125f, cj2.o(), (float)cj2.p() + 0.125f, (float)(cj2.n() + 1) - 0.125f, (double)cj2.o() + 0.25, (float)(cj2.p() + 1) - 0.125f);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        if (this.e(alz2) > 0) {
            this.e(adm2, cj2);
        }
        super.b(adm2, cj2, alz2);
    }

    protected void e(adm adm2, cj cj2) {
        adm2.c(cj2, this);
        adm2.c(cj2.b(), this);
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        return this.e(alz2);
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (cq2 == cq.b) {
            return this.e(alz2);
        }
        return 0;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public void j() {
        float f2 = 0.5f;
        \u2603 = 0.125f;
        \u2603 = 0.5f;
        this.a(0.0f, 0.375f, 0.0f, 1.0f, 0.625f, 1.0f);
    }

    @Override
    public int k() {
        return 1;
    }

    protected abstract int f(adm var1, cj var2);

    protected abstract int e(alz var1);

    protected abstract alz a(alz var1, int var2);
}

