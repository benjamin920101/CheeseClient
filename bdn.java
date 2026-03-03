/*
 * Decompiled with CFR 0.152.
 */
public class bdn
extends beb {
    private arm a;
    private int az;

    protected bdn(adm adm2, double d2, double d3, double d4, arm arm2) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.x = 0.0;
        this.w = 0.0;
        this.v = 0.0;
        if (arm2 == arm.h) {
            this.ar = 0.0f;
            this.as = 0.0f;
            this.at = 1.0f;
        } else {
            this.ar = 1.0f;
            this.as = 0.0f;
            this.at = 0.0f;
        }
        this.k(113);
        this.a(0.01f, 0.01f);
        this.i = 0.06f;
        this.a = arm2;
        this.az = 40;
        this.g = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        this.x = 0.0;
        this.w = 0.0;
        this.v = 0.0;
    }

    @Override
    public int b(float f2) {
        if (this.a == arm.h) {
            return super.b(f2);
        }
        return 257;
    }

    @Override
    public float c(float f2) {
        if (this.a == arm.h) {
            return super.c(f2);
        }
        return 1.0f;
    }

    @Override
    public void t_() {
        arm arm2;
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        if (this.a == arm.h) {
            this.ar = 0.2f;
            this.as = 0.3f;
            this.at = 1.0f;
        } else {
            this.ar = 1.0f;
            this.as = 16.0f / (float)(40 - this.az + 16);
            this.at = 4.0f / (float)(40 - this.az + 8);
        }
        this.w -= (double)this.i;
        if (this.az-- > 0) {
            this.v *= 0.02;
            this.w *= 0.02;
            this.x *= 0.02;
            this.k(113);
        } else {
            this.k(112);
        }
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.98f;
        this.w *= (double)0.98f;
        this.x *= (double)0.98f;
        if (this.g-- <= 0) {
            this.J();
        }
        if (this.C) {
            if (this.a == arm.h) {
                this.J();
                this.o.a(cy.f, this.s, this.t, this.u, 0.0, 0.0, 0.0, new int[0]);
            } else {
                this.k(114);
            }
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
        if ((arm2 = (\u2603 = this.o.p(\u2603 = new cj(this))).c().t()).d() || arm2.a()) {
            double d2 = 0.0;
            if (\u2603.c() instanceof ahv) {
                d2 = ahv.b(\u2603.b(ahv.b));
            }
            if (this.t < (\u2603 = (double)(ns.c(this.t) + 1) - d2)) {
                this.J();
            }
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdn(adm2, d2, d3, d4, arm.i);
        }
    }

    public static class b
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdn(adm2, d2, d3, d4, arm.h);
        }
    }
}

