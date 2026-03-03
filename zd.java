/*
 * Decompiled with CFR 0.152.
 */
public enum zd implements nw
{
    a(0, 15, "white", "white", arn.j, a.p),
    b(1, 14, "orange", "orange", arn.q, a.g),
    c(2, 13, "magenta", "magenta", arn.r, a.l),
    d(3, 12, "light_blue", "lightBlue", arn.s, a.j),
    e(4, 11, "yellow", "yellow", arn.t, a.o),
    f(5, 10, "lime", "lime", arn.u, a.k),
    g(6, 9, "pink", "pink", arn.v, a.n),
    h(7, 8, "gray", "gray", arn.w, a.i),
    i(8, 7, "silver", "silver", arn.x, a.h),
    j(9, 6, "cyan", "cyan", arn.y, a.d),
    k(10, 5, "purple", "purple", arn.z, a.f),
    l(11, 4, "blue", "blue", arn.A, a.b),
    m(12, 3, "brown", "brown", arn.B, a.g),
    n(13, 2, "green", "green", arn.C, a.c),
    o(14, 1, "red", "red", arn.D, a.e),
    p(15, 0, "black", "black", arn.E, a.a);

    private static final zd[] q;
    private static final zd[] r;
    private final int s;
    private final int t;
    private final String u;
    private final String v;
    private final arn w;
    private final a x;

    private zd(int n3, int n4, String string2, String string3, arn arn2, a a2) {
        this.s = n3;
        this.t = n4;
        this.u = string2;
        this.v = string3;
        this.w = arn2;
        this.x = a2;
    }

    public int a() {
        return this.s;
    }

    public int b() {
        return this.t;
    }

    public String d() {
        return this.v;
    }

    public arn e() {
        return this.w;
    }

    public static zd a(int n2) {
        if (n2 < 0 || n2 >= r.length) {
            n2 = 0;
        }
        return r[n2];
    }

    public static zd b(int n2) {
        if (n2 < 0 || n2 >= q.length) {
            n2 = 0;
        }
        return q[n2];
    }

    public String toString() {
        return this.v;
    }

    @Override
    public String l() {
        return this.u;
    }

    static {
        q = new zd[zd.values().length];
        r = new zd[zd.values().length];
        zd[] zdArray = zd.values();
        int \u26032 = zdArray.length;
        for (int i2 = 0; i2 < \u26032; ++i2) {
            zd.q[\u2603.a()] = \u2603 = zdArray[i2];
            zd.r[\u2603.b()] = \u2603;
        }
    }
}

