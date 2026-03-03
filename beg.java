/*
 * Decompiled with CFR 0.152.
 */
public class beg
extends beb {
    float a;

    protected beg(adm adm2, double d2, double d3, double d4, float f2, float f3, float f4) {
        this(adm2, d2, d3, d4, 1.0f, f2, f3, f4);
    }

    protected beg(adm adm2, double d2, double d3, double d4, float f2, float f3, float f4, float f5) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.v *= (double)0.1f;
        this.w *= (double)0.1f;
        this.x *= (double)0.1f;
        if (f3 == 0.0f) {
            f3 = 1.0f;
        }
        \u2603 = (float)Math.random() * 0.4f + 0.6f;
        this.ar = ((float)(Math.random() * (double)0.2f) + 0.8f) * f3 * \u2603;
        this.as = ((float)(Math.random() * (double)0.2f) + 0.8f) * f4 * \u2603;
        this.at = ((float)(Math.random() * (double)0.2f) + 0.8f) * f5 * \u2603;
        this.h *= 0.75f;
        this.h *= f2;
        this.a = this.h;
        this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.g = (int)((float)this.g * f2);
        this.T = false;
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
        this.k(7 - this.f * 8 / this.g);
        this.d(this.v, this.w, this.x);
        if (this.t == this.q) {
            this.v *= 1.1;
            this.x *= 1.1;
        }
        this.v *= (double)0.96f;
        this.w *= (double)0.96f;
        this.x *= (double)0.96f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new beg(adm2, d2, d3, d4, (float)d5, (float)d6, (float)d7);
        }
    }
}

