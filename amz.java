/*
 * Decompiled with CFR 0.152.
 */
public class amz {
    private int a;
    private int b;
    private int c;
    private char[] d;
    private amw e;
    private amw f;

    public amz(int n2, boolean bl2) {
        this.a = n2;
        this.d = new char[4096];
        this.e = new amw();
        if (bl2) {
            this.f = new amw();
        }
    }

    public alz a(int n2, int n3, int n4) {
        alz alz2 = afh.d.a(this.d[n3 << 8 | n4 << 4 | n2]);
        if (alz2 != null) {
            return alz2;
        }
        return afi.a.Q();
    }

    public void a(int n2, int n3, int n4, alz alz2) {
        \u2603 = this.a(n2, n3, n4);
        afh afh2 = \u2603.c();
        \u2603 = alz2.c();
        if (afh2 != afi.a) {
            --this.b;
            if (afh2.y()) {
                --this.c;
            }
        }
        if (\u2603 != afi.a) {
            ++this.b;
            if (\u2603.y()) {
                ++this.c;
            }
        }
        this.d[n3 << 8 | n4 << 4 | n2] = (char)afh.d.b(alz2);
    }

    public afh b(int n2, int n3, int n4) {
        return this.a(n2, n3, n4).c();
    }

    public int c(int n2, int n3, int n4) {
        alz alz2 = this.a(n2, n3, n4);
        return alz2.c().c(alz2);
    }

    public boolean a() {
        return this.b == 0;
    }

    public boolean b() {
        return this.c > 0;
    }

    public int d() {
        return this.a;
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.f.a(n2, n3, n4, n5);
    }

    public int d(int n2, int n3, int n4) {
        return this.f.a(n2, n3, n4);
    }

    public void b(int n2, int n3, int n4, int n5) {
        this.e.a(n2, n3, n4, n5);
    }

    public int e(int n2, int n3, int n4) {
        return this.e.a(n2, n3, n4);
    }

    public void e() {
        this.b = 0;
        this.c = 0;
        for (int i2 = 0; i2 < 16; ++i2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                    afh afh2 = this.b(i2, \u2603, \u2603);
                    if (afh2 == afi.a) continue;
                    ++this.b;
                    if (!afh2.y()) continue;
                    ++this.c;
                }
            }
        }
    }

    public char[] g() {
        return this.d;
    }

    public void a(char[] cArray) {
        this.d = cArray;
    }

    public amw h() {
        return this.e;
    }

    public amw i() {
        return this.f;
    }

    public void a(amw amw2) {
        this.e = amw2;
    }

    public void b(amw amw2) {
        this.f = amw2;
    }
}

