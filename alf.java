/*
 * Decompiled with CFR 0.152.
 */
public class alf
extends akw
implements km {
    public float a;
    public float f;
    public int g;
    private int h;

    @Override
    public void c() {
        if (++this.h % 20 * 4 == 0) {
            this.b.c(this.c, afi.bQ, 1, this.g);
        }
        this.f = this.a;
        int n2 = this.c.n();
        \u2603 = this.c.o();
        \u2603 = this.c.p();
        float \u26032 = 0.1f;
        if (this.g > 0 && this.a == 0.0f) {
            double d2 = (double)n2 + 0.5;
            d3 = (double)\u2603 + 0.5;
            this.b.a(d2, (double)\u2603 + 0.5, d3, "random.chestopen", 0.5f, this.b.s.nextFloat() * 0.1f + 0.9f);
        }
        if (this.g == 0 && this.a > 0.0f || this.g > 0 && this.a < 1.0f) {
            float f2 = this.a;
            this.a = this.g > 0 ? (this.a += \u26032) : (this.a -= \u26032);
            if (this.a > 1.0f) {
                this.a = 1.0f;
            }
            if (this.a < (\u2603 = 0.5f) && f2 >= \u2603) {
                double d3 = (double)n2 + 0.5;
                \u2603 = (double)\u2603 + 0.5;
                this.b.a(d3, (double)\u2603 + 0.5, \u2603, "random.chestclosed", 0.5f, this.b.s.nextFloat() * 0.1f + 0.9f);
            }
            if (this.a < 0.0f) {
                this.a = 0.0f;
            }
        }
    }

    @Override
    public boolean c(int n2, int n3) {
        if (n2 == 1) {
            this.g = n3;
            return true;
        }
        return super.c(n2, n3);
    }

    @Override
    public void y() {
        this.E();
        super.y();
    }

    public void b() {
        ++this.g;
        this.b.c(this.c, afi.bQ, 1, this.g);
    }

    public void d() {
        --this.g;
        this.b.c(this.c, afi.bQ, 1, this.g);
    }

    public boolean a(wn wn2) {
        if (this.b.s(this.c) != this) {
            return false;
        }
        return !(wn2.e((double)this.c.n() + 0.5, (double)this.c.o() + 0.5, (double)this.c.p() + 0.5) > 64.0);
    }
}

