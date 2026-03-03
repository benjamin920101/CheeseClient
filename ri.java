/*
 * Decompiled with CFR 0.152.
 */
public class ri
extends rd {
    protected ps a;
    protected pk b;
    protected float c;
    private int e;
    private float f;
    protected Class<? extends pk> d;

    public ri(ps ps2, Class<? extends pk> clazz, float f2) {
        this.a = ps2;
        this.d = clazz;
        this.c = f2;
        this.f = 0.02f;
        this.a(2);
    }

    public ri(ps ps2, Class<? extends pk> clazz, float f2, float f3) {
        this.a = ps2;
        this.d = clazz;
        this.c = f2;
        this.f = f3;
        this.a(2);
    }

    @Override
    public boolean a() {
        if (this.a.bc().nextFloat() >= this.f) {
            return false;
        }
        if (this.a.u() != null) {
            this.b = this.a.u();
        }
        this.b = this.d == wn.class ? this.a.o.a((pk)this.a, (double)this.c) : this.a.o.a(this.d, this.a.aR().b(this.c, 3.0, this.c), this.a);
        return this.b != null;
    }

    @Override
    public boolean b() {
        if (!this.b.ai()) {
            return false;
        }
        if (this.a.h(this.b) > (double)(this.c * this.c)) {
            return false;
        }
        return this.e > 0;
    }

    @Override
    public void c() {
        this.e = 40 + this.a.bc().nextInt(40);
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        this.a.p().a(this.b.s, this.b.t + (double)this.b.aS(), this.b.u, 10.0f, this.a.bQ());
        --this.e;
    }
}

