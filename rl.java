/*
 * Decompiled with CFR 0.152.
 */
public class rl
extends rd {
    adm a;
    protected py b;
    int c;
    double d;
    boolean e;
    asx f;
    Class<? extends pk> g;
    private int h;
    private double i;
    private double j;
    private double k;

    public rl(py py2, Class<? extends pk> clazz, double d2, boolean bl2) {
        this(py2, d2, bl2);
        this.g = clazz;
    }

    public rl(py py2, double d2, boolean bl2) {
        this.b = py2;
        this.a = py2.o;
        this.d = d2;
        this.e = bl2;
        this.a(3);
    }

    @Override
    public boolean a() {
        pr pr2 = this.b.u();
        if (pr2 == null) {
            return false;
        }
        if (!pr2.ai()) {
            return false;
        }
        if (this.g != null && !this.g.isAssignableFrom(pr2.getClass())) {
            return false;
        }
        this.f = this.b.s().a(pr2);
        return this.f != null;
    }

    @Override
    public boolean b() {
        pr pr2 = this.b.u();
        if (pr2 == null) {
            return false;
        }
        if (!pr2.ai()) {
            return false;
        }
        if (!this.e) {
            return !this.b.s().m();
        }
        return this.b.e(new cj(pr2));
    }

    @Override
    public void c() {
        this.b.s().a(this.f, this.d);
        this.h = 0;
    }

    @Override
    public void d() {
        this.b.s().n();
    }

    @Override
    public void e() {
        pr pr2 = this.b.u();
        this.b.p().a(pr2, 30.0f, 30.0f);
        double \u26032 = this.b.e(pr2.s, pr2.aR().b, pr2.u);
        double \u26033 = this.a(pr2);
        --this.h;
        if ((this.e || this.b.t().a(pr2)) && this.h <= 0 && (this.i == 0.0 && this.j == 0.0 && this.k == 0.0 || pr2.e(this.i, this.j, this.k) >= 1.0 || this.b.bc().nextFloat() < 0.05f)) {
            this.i = pr2.s;
            this.j = pr2.aR().b;
            this.k = pr2.u;
            this.h = 4 + this.b.bc().nextInt(7);
            if (\u26032 > 1024.0) {
                this.h += 10;
            } else if (\u26032 > 256.0) {
                this.h += 5;
            }
            if (!this.b.s().a(pr2, this.d)) {
                this.h += 15;
            }
        }
        this.c = Math.max(this.c - 1, 0);
        if (\u26032 <= \u26033 && this.c <= 0) {
            this.c = 20;
            if (this.b.bA() != null) {
                this.b.bw();
            }
            this.b.r(pr2);
        }
    }

    protected double a(pr pr2) {
        return this.b.J * 2.0f * (this.b.J * 2.0f) + pr2.J;
    }
}

