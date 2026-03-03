/*
 * Decompiled with CFR 0.152.
 */
public class bef
extends beb {
    private float a;
    private double az;
    private double aA;
    private double aB;

    protected bef(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.v = d5;
        this.w = d6;
        this.x = d7;
        this.az = this.s = d2;
        this.aA = this.t = d3;
        this.aB = this.u = d4;
        float f2 = this.V.nextFloat() * 0.6f + 0.4f;
        this.a = this.h = this.V.nextFloat() * 0.2f + 0.5f;
        this.as = this.at = 1.0f * f2;
        this.ar = this.at;
        this.as *= 0.3f;
        this.ar *= 0.9f;
        this.g = (int)(Math.random() * 10.0) + 40;
        this.T = true;
        this.k((int)(Math.random() * 8.0));
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.f + f2) / (float)this.g;
        \u2603 = 1.0f - \u2603;
        \u2603 *= \u2603;
        \u2603 = 1.0f - \u2603;
        this.h = this.a * \u2603;
        super.a(bfd2, pk2, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public int b(float f2) {
        int n2 = super.b(f2);
        float \u26032 = (float)this.f / (float)this.g;
        \u26032 *= \u26032;
        \u26032 *= \u26032;
        \u2603 = n2 & 0xFF;
        \u2603 = n2 >> 16 & 0xFF;
        if ((\u2603 += (int)(\u26032 * 15.0f * 16.0f)) > 240) {
            \u2603 = 240;
        }
        return \u2603 | \u2603 << 16;
    }

    @Override
    public float c(float f2) {
        \u2603 = super.c(f2);
        \u2603 = (float)this.f / (float)this.g;
        \u2603 = \u2603 * \u2603 * \u2603 * \u2603;
        return \u2603 * (1.0f - \u2603) + \u2603;
    }

    @Override
    public void t_() {
        float f2;
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        \u2603 = f2 = (float)this.f / (float)this.g;
        f2 = -f2 + f2 * f2 * 2.0f;
        f2 = 1.0f - f2;
        this.s = this.az + this.v * (double)f2;
        this.t = this.aA + this.w * (double)f2 + (double)(1.0f - \u2603);
        this.u = this.aB + this.x * (double)f2;
        if (this.f++ >= this.g) {
            this.J();
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bef(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

