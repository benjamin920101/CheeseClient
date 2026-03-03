/*
 * Decompiled with CFR 0.152.
 */
public class rp
extends rd {
    private py a;
    private double b;
    private double c;
    private double d;
    private double e;

    public rp(py py2, double d2) {
        this.a = py2;
        this.e = d2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.a.cg()) {
            return false;
        }
        cj cj2 = this.a.ch();
        aui \u26032 = tc.a(this.a, 16, 7, new aui(cj2.n(), cj2.o(), cj2.p()));
        if (\u26032 == null) {
            return false;
        }
        this.b = \u26032.a;
        this.c = \u26032.b;
        this.d = \u26032.c;
        return true;
    }

    @Override
    public boolean b() {
        return !this.a.s().m();
    }

    @Override
    public void c() {
        this.a.s().a(this.b, this.c, this.d, this.e);
    }
}

