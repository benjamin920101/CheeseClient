/*
 * Decompiled with CFR 0.152.
 */
public class bdm
extends beb {
    float a;

    protected bdm(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        this(adm2, d2, d3, d4, d5, d6, d7, 1.0f);
    }

    protected bdm(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, float f2) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.v *= (double)0.1f;
        this.w *= (double)0.1f;
        this.x *= (double)0.1f;
        this.v += d5 * 0.4;
        this.w += d6 * 0.4;
        this.x += d7 * 0.4;
        this.as = this.at = (float)(Math.random() * (double)0.3f + (double)0.6f);
        this.ar = this.at;
        this.h *= 0.75f;
        this.h *= f2;
        this.a = this.h;
        this.g = (int)(6.0 / (Math.random() * 0.8 + 0.6));
        this.g = (int)((float)this.g * f2);
        this.T = false;
        this.k(65);
        this.t_();
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.f + f2) / (float)this.g * 32.0f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        this.h = this.a * \u2603;
        super.a(bfd2, pk2, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        if (this.f++ >= this.g) {
            this.J();
        }
        this.d(this.v, this.w, this.x);
        this.as = (float)((double)this.as * 0.96);
        this.at = (float)((double)this.at * 0.9);
        this.v *= (double)0.7f;
        this.w *= (double)0.7f;
        this.x *= (double)0.7f;
        this.w -= (double)0.02f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bdm bdm2 = new bdm(adm2, d2, d3, d4, d5, d6, d7);
            bdm2.b(bdm2.b() * 0.3f, bdm2.g() * 0.8f, bdm2.i());
            bdm2.k();
            return bdm2;
        }
    }

    public static class b
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdm(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

