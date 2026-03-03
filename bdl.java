/*
 * Decompiled with CFR 0.152.
 */
public class bdl
extends beb {
    protected bdl(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.ar = 1.0f;
        this.as = 1.0f;
        this.at = 1.0f;
        this.k(32);
        this.a(0.02f, 0.02f);
        this.h *= this.V.nextFloat() * 0.6f + 0.2f;
        this.v = d5 * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.w = d6 * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.x = d7 * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.w += 0.002;
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.85f;
        this.w *= (double)0.85f;
        this.x *= (double)0.85f;
        if (this.o.p(new cj(this)).c().t() != arm.h) {
            this.J();
        }
        if (this.g-- <= 0) {
            this.J();
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdl(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

