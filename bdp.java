/*
 * Decompiled with CFR 0.152.
 */
public class bdp
extends beb {
    protected bdp(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.v = d5 + (Math.random() * 2.0 - 1.0) * (double)0.05f;
        this.w = d6 + (Math.random() * 2.0 - 1.0) * (double)0.05f;
        this.x = d7 + (Math.random() * 2.0 - 1.0) * (double)0.05f;
        this.as = this.at = this.V.nextFloat() * 0.3f + 0.7f;
        this.ar = this.at;
        this.h = this.V.nextFloat() * this.V.nextFloat() * 6.0f + 1.0f;
        this.g = (int)(16.0 / ((double)this.V.nextFloat() * 0.8 + 0.2)) + 2;
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
        this.w += 0.004;
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.9f;
        this.w *= (double)0.9f;
        this.x *= (double)0.9f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdp(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

