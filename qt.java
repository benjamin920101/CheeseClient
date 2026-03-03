/*
 * Decompiled with CFR 0.152.
 */
public class qt
extends rd {
    private ua a;
    private wn b;
    private adm c;
    private float d;
    private int e;

    public qt(ua ua2, float f2) {
        this.a = ua2;
        this.c = ua2.o;
        this.d = f2;
        this.a(2);
    }

    @Override
    public boolean a() {
        this.b = this.c.a((pk)this.a, (double)this.d);
        if (this.b == null) {
            return false;
        }
        return this.a(this.b);
    }

    @Override
    public boolean b() {
        if (!this.b.ai()) {
            return false;
        }
        if (this.a.h(this.b) > (double)(this.d * this.d)) {
            return false;
        }
        return this.e > 0 && this.a(this.b);
    }

    @Override
    public void c() {
        this.a.p(true);
        this.e = 40 + this.a.bc().nextInt(40);
    }

    @Override
    public void d() {
        this.a.p(false);
        this.b = null;
    }

    @Override
    public void e() {
        this.a.p().a(this.b.s, this.b.t + (double)this.b.aS(), this.b.u, 10.0f, this.a.bQ());
        --this.e;
    }

    private boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (zx2 == null) {
            return false;
        }
        if (!this.a.cl() && zx2.b() == zy.aX) {
            return true;
        }
        return this.a.d(zx2);
    }
}

