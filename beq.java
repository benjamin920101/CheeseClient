/*
 * Decompiled with CFR 0.152.
 */
public class beq
extends beb {
    protected beq(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.v *= (double)0.3f;
        this.w = Math.random() * (double)0.2f + (double)0.1f;
        this.x *= (double)0.3f;
        this.ar = 1.0f;
        this.as = 1.0f;
        this.at = 1.0f;
        this.k(19);
        this.a(0.01f, 0.01f);
        this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.i = 0.0f;
        this.v = d5;
        this.w = d6;
        this.x = d7;
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
        int n2 = 60 - this.g;
        float \u26032 = (float)n2 * 0.001f;
        this.a(\u26032, \u26032);
        this.k(19 + n2 % 4);
        if (this.g-- <= 0) {
            this.J();
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new beq(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

