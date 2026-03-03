/*
 * Decompiled with CFR 0.152.
 */
public class rk
extends rd {
    private wi b;
    private wi c;
    private adm d;
    private int e;
    tf a;

    public rk(wi wi2) {
        this.b = wi2;
        this.d = wi2.o;
        this.a(3);
    }

    @Override
    public boolean a() {
        if (this.b.l() != 0) {
            return false;
        }
        if (this.b.bc().nextInt(500) != 0) {
            return false;
        }
        this.a = this.d.ae().a(new cj(this.b), 0);
        if (this.a == null) {
            return false;
        }
        if (!this.f() || !this.b.n(true)) {
            return false;
        }
        wi wi2 = this.d.a(wi.class, this.b.aR().b(8.0, 3.0, 8.0), this.b);
        if (wi2 == null) {
            return false;
        }
        this.c = wi2;
        return this.c.l() == 0 && this.c.n(true);
    }

    @Override
    public void c() {
        this.e = 300;
        this.b.l(true);
    }

    @Override
    public void d() {
        this.a = null;
        this.c = null;
        this.b.l(false);
    }

    @Override
    public boolean b() {
        return this.e >= 0 && this.f() && this.b.l() == 0 && this.b.n(false);
    }

    @Override
    public void e() {
        --this.e;
        this.b.p().a(this.c, 10.0f, 30.0f);
        if (this.b.h(this.c) > 2.25) {
            this.b.s().a(this.c, 0.25);
        } else if (this.e == 0 && this.c.cm()) {
            this.g();
        }
        if (this.b.bc().nextInt(35) == 0) {
            this.d.a((pk)this.b, (byte)12);
        }
    }

    private boolean f() {
        if (!this.a.i()) {
            return false;
        }
        int n2 = (int)((double)this.a.c() * 0.35);
        return this.a.e() < n2;
    }

    private void g() {
        wi wi2 = this.b.b(this.c);
        this.c.b(6000);
        this.b.b(6000);
        this.c.o(false);
        this.b.o(false);
        wi2.b(-24000);
        wi2.b(this.b.s, this.b.t, this.b.u, 0.0f, 0.0f);
        this.d.d(wi2);
        this.d.a((pk)wi2, (byte)12);
    }
}

