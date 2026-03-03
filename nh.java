/*
 * Decompiled with CFR 0.152.
 */
public class nh {
    private final long[] a = new long[240];
    private int b;
    private int c;
    private int d;

    public void a(long l2) {
        this.a[this.d] = l2;
        ++this.d;
        if (this.d == 240) {
            this.d = 0;
        }
        if (this.c < 240) {
            this.b = 0;
            ++this.c;
        } else {
            this.b = this.b(this.d + 1);
        }
    }

    public int a(long l2, int n2) {
        double d2 = (double)l2 / 1.6666666E7;
        return (int)(d2 * (double)n2);
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public int b(int n2) {
        return n2 % 240;
    }

    public long[] c() {
        return this.a;
    }
}

