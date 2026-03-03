/*
 * Decompiled with CFR 0.152.
 */
public class adg {
    public final int a;
    public final int b;

    public adg(int n2, int n3) {
        this.a = n2;
        this.b = n3;
    }

    public static long a(int n2, int n3) {
        return (long)n2 & 0xFFFFFFFFL | ((long)n3 & 0xFFFFFFFFL) << 32;
    }

    public int hashCode() {
        int n2 = 1664525 * this.a + 1013904223;
        \u2603 = 1664525 * (this.b ^ 0xDEADBEEF) + 1013904223;
        return n2 ^ \u2603;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof adg) {
            adg adg2 = (adg)object;
            return this.a == adg2.a && this.b == adg2.b;
        }
        return false;
    }

    public int a() {
        return (this.a << 4) + 8;
    }

    public int b() {
        return (this.b << 4) + 8;
    }

    public int c() {
        return this.a << 4;
    }

    public int d() {
        return this.b << 4;
    }

    public int e() {
        return (this.a << 4) + 15;
    }

    public int f() {
        return (this.b << 4) + 15;
    }

    public cj a(int n2, int n3, int n4) {
        return new cj((this.a << 4) + n2, n3, (this.b << 4) + n4);
    }

    public cj a(int n2) {
        return new cj(this.a(), n2, this.b());
    }

    public String toString() {
        return "[" + this.a + ", " + this.b + "]";
    }
}

