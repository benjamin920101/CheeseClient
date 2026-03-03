/*
 * Decompiled with CFR 0.152.
 */
public class vl
extends vv {
    private float a = 0.5f;
    private int b;

    public vl(adm adm2) {
        super(adm2);
        this.ab = true;
        this.b_ = 10;
        this.i.a(4, new a(this));
        this.i.a(5, new rp(this, 1.0));
        this.i.a(7, new rz(this, 1.0));
        this.i.a(8, new ri(this, wn.class, 8.0f));
        this.i.a(8, new ry(this));
        this.bi.a(1, new sm((py)this, true, new Class[0]));
        this.bi.a(2, new sp<wn>((py)this, wn.class, true));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.e).a(6.0);
        this.a(vy.d).a(0.23f);
        this.a(vy.b).a(48.0);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, new Byte(0));
    }

    @Override
    protected String z() {
        return "mob.blaze.breathe";
    }

    @Override
    protected String bo() {
        return "mob.blaze.hit";
    }

    @Override
    protected String bp() {
        return "mob.blaze.death";
    }

    @Override
    public int b(float f2) {
        return 0xF000F0;
    }

    @Override
    public float c(float f2) {
        return 1.0f;
    }

    @Override
    public void m() {
        if (!this.C && this.w < 0.0) {
            this.w *= 0.6;
        }
        if (this.o.D) {
            if (this.V.nextInt(24) == 0 && !this.R()) {
                this.o.a(this.s + 0.5, this.t + 0.5, this.u + 0.5, "fire.fire", 1.0f + this.V.nextFloat(), this.V.nextFloat() * 0.7f + 0.3f, false);
            }
            for (int i2 = 0; i2 < 2; ++i2) {
                this.o.a(cy.m, this.s + (this.V.nextDouble() - 0.5) * (double)this.J, this.t + this.V.nextDouble() * (double)this.K, this.u + (this.V.nextDouble() - 0.5) * (double)this.J, 0.0, 0.0, 0.0, new int[0]);
            }
        }
        super.m();
    }

    @Override
    protected void E() {
        pr pr2;
        if (this.U()) {
            this.a(ow.f, 1.0f);
        }
        --this.b;
        if (this.b <= 0) {
            this.b = 100;
            this.a = 0.5f + (float)this.V.nextGaussian() * 3.0f;
        }
        if ((pr2 = this.u()) != null && pr2.t + (double)pr2.aS() > this.t + (double)this.aS() + (double)this.a) {
            this.w += ((double)0.3f - this.w) * (double)0.3f;
            this.ai = true;
        }
        super.E();
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    protected zw A() {
        return zy.bv;
    }

    @Override
    public boolean at() {
        return this.n();
    }

    @Override
    protected void b(boolean bl2, int n2) {
        if (bl2) {
            \u2603 = this.V.nextInt(2 + n2);
            for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                this.a(zy.bv, 1);
            }
        }
    }

    public boolean n() {
        return (this.ac.a(16) & 1) != 0;
    }

    public void a(boolean bl2) {
        byte by = this.ac.a(16);
        by = bl2 ? (byte)(by | 1) : (byte)(by & 0xFFFFFFFE);
        this.ac.b(16, by);
    }

    @Override
    protected boolean n_() {
        return true;
    }

    static class a
    extends rd {
        private vl a;
        private int b;
        private int c;

        public a(vl vl2) {
            this.a = vl2;
            this.a(3);
        }

        @Override
        public boolean a() {
            pr pr2 = this.a.u();
            return pr2 != null && pr2.ai();
        }

        @Override
        public void c() {
            this.b = 0;
        }

        @Override
        public void d() {
            this.a.a(false);
        }

        @Override
        public void e() {
            --this.c;
            pr pr2 = this.a.u();
            double \u26032 = this.a.h(pr2);
            if (\u26032 < 4.0) {
                if (this.c <= 0) {
                    this.c = 20;
                    this.a.r(pr2);
                }
                this.a.q().a(pr2.s, pr2.t, pr2.u, 1.0);
            } else if (\u26032 < 256.0) {
                double d2 = pr2.s - this.a.s;
                \u2603 = pr2.aR().b + (double)(pr2.K / 2.0f) - (this.a.t + (double)(this.a.K / 2.0f));
                \u2603 = pr2.u - this.a.u;
                if (this.c <= 0) {
                    ++this.b;
                    if (this.b == 1) {
                        this.c = 60;
                        this.a.a(true);
                    } else if (this.b <= 4) {
                        this.c = 6;
                    } else {
                        this.c = 100;
                        this.b = 0;
                        this.a.a(false);
                    }
                    if (this.b > 1) {
                        float f2 = ns.c(ns.a(\u26032)) * 0.5f;
                        this.a.o.a(null, 1009, new cj((int)this.a.s, (int)this.a.t, (int)this.a.u), 0);
                        for (int i2 = 0; i2 < 1; ++i2) {
                            ww ww2 = new ww(this.a.o, this.a, d2 + this.a.bc().nextGaussian() * (double)f2, \u2603, \u2603 + this.a.bc().nextGaussian() * (double)f2);
                            ww2.t = this.a.t + (double)(this.a.K / 2.0f) + 0.5;
                            this.a.o.d(ww2);
                        }
                    }
                }
                this.a.p().a(pr2, 10.0f, 10.0f);
            } else {
                this.a.s().n();
                this.a.q().a(pr2.s, pr2.t, pr2.u, 1.0);
            }
            super.e();
        }
    }
}

