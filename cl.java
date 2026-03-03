/*
 * Decompiled with CFR 0.152.
 */
public class cl
implements ck {
    private final adm a;
    private final cj b;

    public cl(adm adm2, cj cj2) {
        this.a = adm2;
        this.b = cj2;
    }

    @Override
    public adm i() {
        return this.a;
    }

    @Override
    public double a() {
        return (double)this.b.n() + 0.5;
    }

    @Override
    public double b() {
        return (double)this.b.o() + 0.5;
    }

    @Override
    public double c() {
        return (double)this.b.p() + 0.5;
    }

    @Override
    public cj d() {
        return this.b;
    }

    @Override
    public int f() {
        alz alz2 = this.a.p(this.b);
        return alz2.c().c(alz2);
    }

    @Override
    public <T extends akw> T h() {
        return (T)this.a.s(this.b);
    }
}

