/*
 * Decompiled with CFR 0.152.
 */
public class bem
extends beb {
    protected bem(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.ar = \u2603 = this.V.nextFloat() * 0.1f + 0.2f;
        this.as = \u2603;
        this.at = \u2603;
        this.k(0);
        this.a(0.02f, 0.02f);
        this.h *= this.V.nextFloat() * 0.6f + 0.5f;
        this.v *= (double)0.02f;
        this.w *= (double)0.02f;
        this.x *= (double)0.02f;
        this.g = (int)(20.0 / (Math.random() * 0.8 + 0.2));
        this.T = true;
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.d(this.v, this.w, this.x);
        this.v *= 0.99;
        this.w *= 0.99;
        this.x *= 0.99;
        if (this.g-- <= 0) {
            this.J();
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bem bem2 = new bem(adm2, d2, d3, d4, d5, d6, d7);
            bem2.k(82);
            bem2.b(1.0f, 1.0f, 1.0f);
            return bem2;
        }
    }

    public static class b
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bem(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

