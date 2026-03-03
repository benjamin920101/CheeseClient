/*
 * Decompiled with CFR 0.152.
 */
public class ber
extends beb {
    protected ber(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.v *= (double)0.3f;
        this.w = Math.random() * (double)0.2f + (double)0.1f;
        this.x *= (double)0.3f;
        this.ar = 1.0f;
        this.as = 1.0f;
        this.at = 1.0f;
        this.k(19 + this.V.nextInt(4));
        this.a(0.01f, 0.01f);
        this.i = 0.06f;
        this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.w -= (double)this.i;
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.98f;
        this.w *= (double)0.98f;
        this.x *= (double)0.98f;
        if (this.g-- <= 0) {
            this.J();
        }
        if (this.C) {
            if (Math.random() < 0.5) {
                this.J();
            }
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
        cj cj2 = new cj(this);
        alz \u26032 = this.o.p(cj2);
        afh \u26033 = \u26032.c();
        \u26033.a((adq)this.o, cj2);
        arm \u26034 = \u26032.c().t();
        if (\u26034.d() || \u26034.a()) {
            double d2 = 0.0;
            d2 = \u26032.c() instanceof ahv ? (double)(1.0f - ahv.b(\u26032.b(ahv.b))) : \u26033.E();
            \u2603 = (double)ns.c(this.t) + d2;
            if (this.t < \u2603) {
                this.J();
            }
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new ber(adm2, d2, d3, d4);
        }
    }
}

