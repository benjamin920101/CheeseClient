/*
 * Decompiled with CFR 0.152.
 */
public class rz
extends rd {
    private py a;
    private double b;
    private double c;
    private double d;
    private double e;
    private int f;
    private boolean g;

    public rz(py py2, double d2) {
        this(py2, d2, 120);
    }

    public rz(py py2, double d2, int n2) {
        this.a = py2;
        this.e = d2;
        this.f = n2;
        this.a(1);
    }

    @Override
    public boolean a() {
        aui aui2;
        if (!this.g) {
            if (this.a.bh() >= 100) {
                return false;
            }
            if (this.a.bc().nextInt(this.f) != 0) {
                return false;
            }
        }
        if ((aui2 = tc.a(this.a, 10, 7)) == null) {
            return false;
        }
        this.b = aui2.a;
        this.c = aui2.b;
        this.d = aui2.c;
        this.g = false;
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

    public void f() {
        this.g = true;
    }

    public void b(int n2) {
        this.f = n2;
    }
}

