/*
 * Decompiled with CFR 0.152.
 */
public class rq
extends rd {
    private py a;
    private pr b;
    private double c;
    private double d;
    private double e;
    private double f;
    private float g;

    public rq(py py2, double d2, float f2) {
        this.a = py2;
        this.f = d2;
        this.g = f2;
        this.a(1);
    }

    @Override
    public boolean a() {
        this.b = this.a.u();
        if (this.b == null) {
            return false;
        }
        if (this.b.h(this.a) > (double)(this.g * this.g)) {
            return false;
        }
        aui aui2 = tc.a(this.a, 16, 7, new aui(this.b.s, this.b.t, this.b.u));
        if (aui2 == null) {
            return false;
        }
        this.c = aui2.a;
        this.d = aui2.b;
        this.e = aui2.c;
        return true;
    }

    @Override
    public boolean b() {
        return !this.a.s().m() && this.b.ai() && this.b.h(this.a) < (double)(this.g * this.g);
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void c() {
        this.a.s().a(this.c, this.d, this.e, this.f);
    }
}

