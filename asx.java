/*
 * Decompiled with CFR 0.152.
 */
public class asx {
    private final asv[] a;
    private int b;
    private int c;

    public asx(asv[] asvArray) {
        this.a = asvArray;
        this.c = asvArray.length;
    }

    public void a() {
        ++this.b;
    }

    public boolean b() {
        return this.b >= this.c;
    }

    public asv c() {
        if (this.c > 0) {
            return this.a[this.c - 1];
        }
        return null;
    }

    public asv a(int n2) {
        return this.a[n2];
    }

    public int d() {
        return this.c;
    }

    public void b(int n2) {
        this.c = n2;
    }

    public int e() {
        return this.b;
    }

    public void c(int n2) {
        this.b = n2;
    }

    public aui a(pk pk2, int n2) {
        double d2 = (double)this.a[n2].a + (double)((int)(pk2.J + 1.0f)) * 0.5;
        \u2603 = this.a[n2].b;
        \u2603 = (double)this.a[n2].c + (double)((int)(pk2.J + 1.0f)) * 0.5;
        return new aui(d2, \u2603, \u2603);
    }

    public aui a(pk pk2) {
        return this.a(pk2, this.b);
    }

    public boolean a(asx asx2) {
        if (asx2 == null) {
            return false;
        }
        if (asx2.a.length != this.a.length) {
            return false;
        }
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2].a == asx2.a[i2].a && this.a[i2].b == asx2.a[i2].b && this.a[i2].c == asx2.a[i2].c) continue;
            return false;
        }
        return true;
    }

    public boolean b(aui aui2) {
        asv asv2 = this.c();
        if (asv2 == null) {
            return false;
        }
        return asv2.a == (int)aui2.a && asv2.c == (int)aui2.c;
    }
}

