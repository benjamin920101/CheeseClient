/*
 * Decompiled with CFR 0.152.
 */
public class bch
extends bbo {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct e;
    public bct f;

    public bch() {
        this(0.0f);
    }

    public bch(float f2) {
        this(f2, -7.0f);
    }

    public bch(float f2, float f3) {
        int n2 = 128;
        \u2603 = 128;
        this.a = new bct(this).b(n2, \u2603);
        this.a.a(0.0f, 0.0f + f3, -2.0f);
        this.a.a(0, 0).a(-4.0f, -12.0f, -5.5f, 8, 10, 8, f2);
        this.a.a(24, 0).a(-1.0f, -5.0f, -7.5f, 2, 4, 2, f2);
        this.b = new bct(this).b(n2, \u2603);
        this.b.a(0.0f, 0.0f + f3, 0.0f);
        this.b.a(0, 40).a(-9.0f, -2.0f, -6.0f, 18, 12, 11, f2);
        this.b.a(0, 70).a(-4.5f, 10.0f, -3.0f, 9, 5, 6, f2 + 0.5f);
        this.c = new bct(this).b(n2, \u2603);
        this.c.a(0.0f, -7.0f, 0.0f);
        this.c.a(60, 21).a(-13.0f, -2.5f, -3.0f, 4, 30, 6, f2);
        this.d = new bct(this).b(n2, \u2603);
        this.d.a(0.0f, -7.0f, 0.0f);
        this.d.a(60, 58).a(9.0f, -2.5f, -3.0f, 4, 30, 6, f2);
        this.e = new bct(this, 0, 22).b(n2, \u2603);
        this.e.a(-4.0f, 18.0f + f3, 0.0f);
        this.e.a(37, 0).a(-3.5f, -3.0f, -3.0f, 6, 16, 5, f2);
        this.f = new bct(this, 0, 22).b(n2, \u2603);
        this.f.i = true;
        this.f.a(60, 0).a(5.0f, 18.0f + f3, 0.0f);
        this.f.a(-3.5f, -3.0f, -3.0f, 6, 16, 5, f2);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.a(f7);
        this.b.a(f7);
        this.e.a(f7);
        this.f.a(f7);
        this.c.a(f7);
        this.d.a(f7);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        this.a.g = f5 / 57.295776f;
        this.a.f = f6 / 57.295776f;
        this.e.f = -1.5f * this.a(f2, 13.0f) * f3;
        this.f.f = 1.5f * this.a(f2, 13.0f) * f3;
        this.e.g = 0.0f;
        this.f.g = 0.0f;
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4) {
        ty ty2 = (ty)pr2;
        int \u26032 = ty2.cl();
        if (\u26032 > 0) {
            this.c.f = -2.0f + 1.5f * this.a((float)\u26032 - f4, 10.0f);
            this.d.f = -2.0f + 1.5f * this.a((float)\u26032 - f4, 10.0f);
        } else {
            int n2 = ty2.cm();
            if (n2 > 0) {
                this.c.f = -0.8f + 0.025f * this.a(n2, 70.0f);
                this.d.f = 0.0f;
            } else {
                this.c.f = (-0.2f + 1.5f * this.a(f2, 13.0f)) * f3;
                this.d.f = (-0.2f - 1.5f * this.a(f2, 13.0f)) * f3;
            }
        }
    }

    private float a(float f2, float f3) {
        return (Math.abs(f2 % f3 - f3 * 0.5f) - f3 * 0.25f) / (f3 * 0.25f);
    }
}

