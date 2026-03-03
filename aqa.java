/*
 * Decompiled with CFR 0.152.
 */
public class aqa {
    private final int a;
    private alz b;
    private int c = 1;
    private int d;

    public aqa(int n2, afh afh2) {
        this(3, n2, afh2);
    }

    public aqa(int n2, int n3, afh afh2) {
        this.a = n2;
        this.c = n3;
        this.b = afh2.Q();
    }

    public aqa(int n2, int n3, afh afh2, int n4) {
        this(n2, n3, afh2);
        this.b = afh2.a(n4);
    }

    public int b() {
        return this.c;
    }

    public alz c() {
        return this.b;
    }

    private afh e() {
        return this.b.c();
    }

    private int f() {
        return this.b.c().c(this.b);
    }

    public int d() {
        return this.d;
    }

    public void b(int n2) {
        this.d = n2;
    }

    public String toString() {
        String string;
        if (this.a >= 3) {
            jy jy2 = (jy)afh.c.c(this.e());
            String string2 = string = jy2 == null ? "null" : jy2.toString();
            if (this.c > 1) {
                string = this.c + "*" + string;
            }
        } else {
            string = Integer.toString(afh.a(this.e()));
            if (this.c > 1) {
                string = this.c + "x" + string;
            }
        }
        int n2 = this.f();
        if (n2 > 0) {
            string = string + ":" + n2;
        }
        return string;
    }
}

