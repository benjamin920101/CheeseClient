/*
 * Decompiled with CFR 0.152.
 */
public class bdt
extends beb {
    float a;

    protected bdt(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        this(adm2, d2, d3, d4, d5, d6, d7, 2.0f);
    }

    protected bdt(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, float f2) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.v *= (double)0.01f;
        this.w *= (double)0.01f;
        this.x *= (double)0.01f;
        this.w += 0.1;
        this.h *= 0.75f;
        this.h *= f2;
        this.a = this.h;
        this.g = 16;
        this.T = false;
        this.k(80);
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
        if (this.t == this.q) {
            this.v *= 1.1;
            this.x *= 1.1;
        }
        this.v *= (double)0.86f;
        this.w *= (double)0.86f;
        this.x *= (double)0.86f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bdt bdt2 = new bdt(adm2, d2, d3 + 0.5, d4, d5, d6, d7);
            bdt2.k(81);
            bdt2.b(1.0f, 1.0f, 1.0f);
            return bdt2;
        }
    }

    public static class b
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdt(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

