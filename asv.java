/*
 * Decompiled with CFR 0.152.
 */
public class asv {
    public final int a;
    public final int b;
    public final int c;
    private final int j;
    int d = -1;
    float e;
    float f;
    float g;
    asv h;
    public boolean i;

    public asv(int n2, int n3, int n4) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
        this.j = asv.a(n2, n3, n4);
    }

    public static int a(int n2, int n3, int n4) {
        return n3 & 0xFF | (n2 & Short.MAX_VALUE) << 8 | (n4 & Short.MAX_VALUE) << 24 | (n2 < 0 ? Integer.MIN_VALUE : 0) | (n4 < 0 ? 32768 : 0);
    }

    public float a(asv asv2) {
        float f2 = asv2.a - this.a;
        \u2603 = asv2.b - this.b;
        \u2603 = asv2.c - this.c;
        return ns.c(f2 * f2 + \u2603 * \u2603 + \u2603 * \u2603);
    }

    public float b(asv asv2) {
        float f2 = asv2.a - this.a;
        \u2603 = asv2.b - this.b;
        \u2603 = asv2.c - this.c;
        return f2 * f2 + \u2603 * \u2603 + \u2603 * \u2603;
    }

    public boolean equals(Object object) {
        if (object instanceof asv) {
            asv asv2 = (asv)object;
            return this.j == asv2.j && this.a == asv2.a && this.b == asv2.b && this.c == asv2.c;
        }
        return false;
    }

    public int hashCode() {
        return this.j;
    }

    public boolean a() {
        return this.d >= 0;
    }

    public String toString() {
        return this.a + ", " + this.b + ", " + this.c;
    }
}

