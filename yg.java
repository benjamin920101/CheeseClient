/*
 * Decompiled with CFR 0.152.
 */
public class yg {
    private final int a;
    public final og d;
    public int e;
    public int f;
    public int g;

    public yg(og og2, int n2, int n3, int n4) {
        this.d = og2;
        this.a = n2;
        this.f = n3;
        this.g = n4;
    }

    public void a(zx zx2, zx zx3) {
        if (zx2 == null || zx3 == null) {
            return;
        }
        if (zx2.b() != zx3.b()) {
            return;
        }
        int n2 = zx3.b - zx2.b;
        if (n2 > 0) {
            this.a(zx2, n2);
        }
    }

    protected void a(zx zx2, int n2) {
    }

    protected void c(zx zx2) {
    }

    public void a(wn wn2, zx zx2) {
        this.f();
    }

    public boolean a(zx zx2) {
        return true;
    }

    public zx d() {
        return this.d.a(this.a);
    }

    public boolean e() {
        return this.d() != null;
    }

    public void d(zx zx2) {
        this.d.a(this.a, zx2);
        this.f();
    }

    public void f() {
        this.d.p_();
    }

    public int a() {
        return this.d.q_();
    }

    public int b(zx zx2) {
        return this.a();
    }

    public String c() {
        return null;
    }

    public zx a(int n2) {
        return this.d.a(this.a, n2);
    }

    public boolean a(og og2, int n2) {
        return og2 == this.d && n2 == this.a;
    }

    public boolean a(wn wn2) {
        return true;
    }

    public boolean b() {
        return true;
    }
}

