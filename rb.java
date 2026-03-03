/*
 * Decompiled with CFR 0.152.
 */
public class rb
extends rd {
    private qa d;
    private pr e;
    adm a;
    private double f;
    private sw g;
    private int h;
    float b;
    float c;
    private boolean i;

    public rb(qa qa2, double d2, float f2, float f3) {
        this.d = qa2;
        this.a = qa2.o;
        this.f = d2;
        this.g = qa2.s();
        this.c = f2;
        this.b = f3;
        this.a(3);
        if (!(qa2.s() instanceof sv)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    @Override
    public boolean a() {
        pr pr2 = this.d.co();
        if (pr2 == null) {
            return false;
        }
        if (pr2 instanceof wn && ((wn)pr2).v()) {
            return false;
        }
        if (this.d.cn()) {
            return false;
        }
        if (this.d.h(pr2) < (double)(this.c * this.c)) {
            return false;
        }
        this.e = pr2;
        return true;
    }

    @Override
    public boolean b() {
        return !this.g.m() && this.d.h(this.e) > (double)(this.b * this.b) && !this.d.cn();
    }

    @Override
    public void c() {
        this.h = 0;
        this.i = ((sv)this.d.s()).e();
        ((sv)this.d.s()).a(false);
    }

    @Override
    public void d() {
        this.e = null;
        this.g.n();
        ((sv)this.d.s()).a(true);
    }

    private boolean a(cj cj2) {
        alz alz2 = this.a.p(cj2);
        afh \u26032 = alz2.c();
        if (\u26032 == afi.a) {
            return true;
        }
        return !\u26032.d();
    }

    @Override
    public void e() {
        this.d.p().a(this.e, 10.0f, (float)this.d.bQ());
        if (this.d.cn()) {
            return;
        }
        if (--this.h > 0) {
            return;
        }
        this.h = 10;
        if (this.g.a(this.e, this.f)) {
            return;
        }
        if (this.d.cc()) {
            return;
        }
        if (this.d.h(this.e) < 144.0) {
            return;
        }
        int n2 = ns.c(this.e.s) - 2;
        \u2603 = ns.c(this.e.u) - 2;
        \u2603 = ns.c(this.e.aR().b);
        for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
            for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
                if (\u2603 >= 1 && \u2603 >= 1 && \u2603 <= 3 && \u2603 <= 3 || !adm.a(this.a, new cj(n2 + \u2603, \u2603 - 1, \u2603 + \u2603)) || !this.a(new cj(n2 + \u2603, \u2603, \u2603 + \u2603)) || !this.a(new cj(n2 + \u2603, \u2603 + 1, \u2603 + \u2603))) continue;
                this.d.b((float)(n2 + \u2603) + 0.5f, \u2603, (float)(\u2603 + \u2603) + 0.5f, this.d.y, this.d.z);
                this.g.n();
                return;
            }
        }
    }
}

