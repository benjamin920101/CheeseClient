/*
 * Decompiled with CFR 0.152.
 */
public class bdo
extends beb {
    private float a;
    private double az;
    private double aA;
    private double aB;

    protected bdo(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.v = d5;
        this.w = d6;
        this.x = d7;
        this.az = d2;
        this.aA = d3;
        this.aB = d4;
        this.s = this.p = d2 + d5;
        this.t = this.q = d3 + d6;
        this.u = this.r = d4 + d7;
        float f2 = this.V.nextFloat() * 0.6f + 0.4f;
        this.a = this.h = this.V.nextFloat() * 0.5f + 0.2f;
        this.as = this.at = 1.0f * f2;
        this.ar = this.at;
        this.as *= 0.9f;
        this.ar *= 0.9f;
        this.g = (int)(Math.random() * 10.0) + 30;
        this.T = true;
        this.k((int)(Math.random() * 26.0 + 1.0 + 224.0));
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
        \u2603 *= \u2603;
        \u2603 *= \u2603;
        return \u2603 * (1.0f - \u2603) + \u2603;
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        float f2 = (float)this.f / (float)this.g;
        f2 = 1.0f - f2;
        \u2603 = 1.0f - f2;
        \u2603 *= \u2603;
        \u2603 *= \u2603;
        this.s = this.az + this.v * (double)f2;
        this.t = this.aA + this.w * (double)f2 - (double)(\u2603 * 1.2f);
        this.u = this.aB + this.x * (double)f2;
        if (this.f++ >= this.g) {
            this.J();
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdo(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

