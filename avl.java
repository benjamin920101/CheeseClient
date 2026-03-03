/*
 * Decompiled with CFR 0.152.
 */
public class avl {
    float a;
    private double f;
    public int b;
    public float c;
    public float d = 1.0f;
    public float e;
    private long g;
    private long h;
    private long i;
    private double j = 1.0;

    public avl(float f2) {
        this.a = f2;
        this.g = ave.J();
        this.h = System.nanoTime() / 1000000L;
    }

    public void a() {
        long l2 = ave.J();
        \u2603 = l2 - this.g;
        l3 = System.nanoTime() / 1000000L;
        double \u26032 = (double)l3 / 1000.0;
        if (\u2603 > 1000L || \u2603 < 0L) {
            this.f = \u26032;
        } else {
            this.i += \u2603;
            if (this.i > 1000L) {
                \u2603 = l3 - this.h;
                double d2 = (double)this.i / (double)\u2603;
                this.j += (d2 - this.j) * (double)0.2f;
                this.h = l3;
                this.i = 0L;
            }
            if (this.i < 0L) {
                long l3;
                this.h = l3;
            }
        }
        this.g = l2;
        double \u26033 = (\u26032 - this.f) * this.j;
        this.f = \u26032;
        \u26033 = ns.a(\u26033, 0.0, 1.0);
        this.e = (float)((double)this.e + \u26033 * (double)this.d * (double)this.a);
        this.b = (int)this.e;
        this.e -= (float)this.b;
        if (this.b > 10) {
            this.b = 10;
        }
        this.c = this.e;
    }
}

