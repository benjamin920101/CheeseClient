/*
 * Decompiled with CFR 0.152.
 */
public class bdr
extends beb {
    private float a;

    protected bdr(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.v = this.v * (double)0.01f + d5;
        this.w = this.w * (double)0.01f + d6;
        this.x = this.x * (double)0.01f + d7;
        this.s += (double)((this.V.nextFloat() - this.V.nextFloat()) * 0.05f);
        this.t += (double)((this.V.nextFloat() - this.V.nextFloat()) * 0.05f);
        this.u += (double)((this.V.nextFloat() - this.V.nextFloat()) * 0.05f);
        this.a = this.h;
        this.at = 1.0f;
        this.as = 1.0f;
        this.ar = 1.0f;
        this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
        this.T = true;
        this.k(48);
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.f + f2) / (float)this.g;
        this.h = this.a * (1.0f - \u2603 * \u2603 * 0.5f);
        super.a(bfd2, pk2, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public int b(float f2) {
        \u2603 = ((float)this.f + f2) / (float)this.g;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        int n2 = super.b(f2);
        \u2603 = n2 & 0xFF;
        \u2603 = n2 >> 16 & 0xFF;
        if ((\u2603 += (int)(\u2603 * 15.0f * 16.0f)) > 240) {
            \u2603 = 240;
        }
        return \u2603 | \u2603 << 16;
    }

    @Override
    public float c(float f2) {
        \u2603 = ((float)this.f + f2) / (float)this.g;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = super.c(f2);
        return \u2603 * \u2603 + (1.0f - \u2603);
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
            return new bdr(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

