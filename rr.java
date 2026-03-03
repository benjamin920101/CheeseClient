/*
 * Decompiled with CFR 0.152.
 */
public class rr
extends rd {
    adm a;
    ps b;
    pr c;
    int d;

    public rr(ps ps2) {
        this.b = ps2;
        this.a = ps2.o;
        this.a(3);
    }

    @Override
    public boolean a() {
        pr pr2 = this.b.u();
        if (pr2 == null) {
            return false;
        }
        this.c = pr2;
        return true;
    }

    @Override
    public boolean b() {
        if (!this.c.ai()) {
            return false;
        }
        if (this.b.h(this.c) > 225.0) {
            return false;
        }
        return !this.b.s().m() || this.a();
    }

    @Override
    public void d() {
        this.c = null;
        this.b.s().n();
    }

    @Override
    public void e() {
        this.b.p().a(this.c, 30.0f, 30.0f);
        double d2 = this.b.J * 2.0f * (this.b.J * 2.0f);
        \u2603 = this.b.e(this.c.s, this.c.aR().b, this.c.u);
        \u2603 = 0.8;
        if (\u2603 > d2 && \u2603 < 16.0) {
            \u2603 = 1.33;
        } else if (\u2603 < 225.0) {
            \u2603 = 0.6;
        }
        this.b.s().a(this.c, \u2603);
        this.d = Math.max(this.d - 1, 0);
        if (\u2603 > d2) {
            return;
        }
        if (this.d > 0) {
            return;
        }
        this.d = 20;
        this.b.r(this.c);
    }
}

