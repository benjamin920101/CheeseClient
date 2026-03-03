/*
 * Decompiled with CFR 0.152.
 */
public abstract class ro
extends rd {
    private final py c;
    private final double d;
    protected int a;
    private int e;
    private int f;
    protected cj b = cj.a;
    private boolean g;
    private int h;

    public ro(py py2, double d2, int n2) {
        this.c = py2;
        this.d = d2;
        this.h = n2;
        this.a(5);
    }

    @Override
    public boolean a() {
        if (this.a > 0) {
            --this.a;
            return false;
        }
        this.a = 200 + this.c.bc().nextInt(200);
        return this.g();
    }

    @Override
    public boolean b() {
        return this.e >= -this.f && this.e <= 1200 && this.a(this.c.o, this.b);
    }

    @Override
    public void c() {
        this.c.s().a((double)this.b.n() + 0.5, this.b.o() + 1, (double)this.b.p() + 0.5, this.d);
        this.e = 0;
        this.f = this.c.bc().nextInt(this.c.bc().nextInt(1200) + 1200) + 1200;
    }

    @Override
    public void d() {
    }

    @Override
    public void e() {
        if (this.c.c(this.b.a()) > 1.0) {
            this.g = false;
            ++this.e;
            if (this.e % 40 == 0) {
                this.c.s().a((double)this.b.n() + 0.5, this.b.o() + 1, (double)this.b.p() + 0.5, this.d);
            }
        } else {
            this.g = true;
            --this.e;
        }
    }

    protected boolean f() {
        return this.g;
    }

    private boolean g() {
        int n2 = this.h;
        boolean \u26032 = true;
        cj \u26033 = new cj(this.c);
        n3 = 0;
        while (n3 <= 1) {
            int n3;
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                n5 = 0;
                while (n5 <= \u2603) {
                    int n4 = \u26034 = n5 < \u2603 && n5 > -\u2603 ? \u2603 : 0;
                    while (\u26034 <= \u2603) {
                        cj cj2 = \u26033.a(n5, n3 - 1, \u26034);
                        if (this.c.e(cj2) && this.a(this.c.o, cj2)) {
                            this.b = cj2;
                            return true;
                        }
                        int \u26034 = \u26034 > 0 ? -\u26034 : 1 - \u26034;
                    }
                    int n5 = n5 > 0 ? -n5 : 1 - n5;
                }
            }
            n3 = n3 > 0 ? -n3 : 1 - n3;
        }
        return false;
    }

    protected abstract boolean a(adm var1, cj var2);
}

