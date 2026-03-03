/*
 * Decompiled with CFR 0.152.
 */
public class tx
extends tz {
    public float a;
    public float b;
    public float c;
    public float bk;
    public float bl;
    public float bm;
    public float bn;
    public float bo;
    private float bp;
    private float bq;
    private float br;
    private float bs;
    private float bt;
    private float bu;

    public tx(adm adm2) {
        super(adm2);
        this.a(0.95f, 0.95f);
        this.V.setSeed(1 + this.F());
        this.bq = 1.0f / (this.V.nextFloat() + 1.0f) * 0.2f;
        this.i.a(0, new a(this));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(10.0);
    }

    @Override
    public float aS() {
        return this.K * 0.5f;
    }

    @Override
    protected String z() {
        return null;
    }

    @Override
    protected String bo() {
        return null;
    }

    @Override
    protected String bp() {
        return null;
    }

    @Override
    protected float bB() {
        return 0.4f;
    }

    @Override
    protected zw A() {
        return null;
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3 + n2) + 1;
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(new zx(zy.aW, 1, zd.p.b()), 0.0f);
        }
    }

    @Override
    public boolean V() {
        return this.o.a(this.aR().b(0.0, -0.6f, 0.0), arm.h, this);
    }

    @Override
    public void m() {
        super.m();
        this.b = this.a;
        this.bk = this.c;
        this.bm = this.bl;
        this.bo = this.bn;
        this.bl += this.bq;
        if ((double)this.bl > Math.PI * 2) {
            if (this.o.D) {
                this.bl = (float)Math.PI * 2;
            } else {
                this.bl = (float)((double)this.bl - Math.PI * 2);
                if (this.V.nextInt(10) == 0) {
                    this.bq = 1.0f / (this.V.nextFloat() + 1.0f) * 0.2f;
                }
                this.o.a((pk)this, (byte)19);
            }
        }
        if (this.Y) {
            float f2;
            if (this.bl < (float)Math.PI) {
                f2 = this.bl / (float)Math.PI;
                this.bn = ns.a(f2 * f2 * (float)Math.PI) * (float)Math.PI * 0.25f;
                if ((double)f2 > 0.75) {
                    this.bp = 1.0f;
                    this.br = 1.0f;
                } else {
                    this.br *= 0.8f;
                }
            } else {
                this.bn = 0.0f;
                this.bp *= 0.9f;
                this.br *= 0.99f;
            }
            if (!this.o.D) {
                this.v = this.bs * this.bp;
                this.w = this.bt * this.bp;
                this.x = this.bu * this.bp;
            }
            f2 = ns.a(this.v * this.v + this.x * this.x);
            this.aI += (-((float)ns.b(this.v, this.x)) * 180.0f / (float)Math.PI - this.aI) * 0.1f;
            this.y = this.aI;
            this.c = (float)((double)this.c + Math.PI * (double)this.br * 1.5);
            this.a += (-((float)ns.b(f2, this.w)) * 180.0f / (float)Math.PI - this.a) * 0.1f;
        } else {
            this.bn = ns.e(ns.a(this.bl)) * (float)Math.PI * 0.25f;
            if (!this.o.D) {
                this.v = 0.0;
                this.w -= 0.08;
                this.w *= (double)0.98f;
                this.x = 0.0;
            }
            this.a = (float)((double)this.a + (double)(-90.0f - this.a) * 0.02);
        }
    }

    @Override
    public void g(float f2, float f3) {
        this.d(this.v, this.w, this.x);
    }

    @Override
    public boolean bR() {
        return this.t > 45.0 && this.t < (double)this.o.F() && super.bR();
    }

    @Override
    public void a(byte by) {
        if (by == 19) {
            this.bl = 0.0f;
        } else {
            super.a(by);
        }
    }

    public void b(float f2, float f3, float f4) {
        this.bs = f2;
        this.bt = f3;
        this.bu = f4;
    }

    public boolean n() {
        return this.bs != 0.0f || this.bt != 0.0f || this.bu != 0.0f;
    }

    static class a
    extends rd {
        private tx a;

        public a(tx tx2) {
            this.a = tx2;
        }

        @Override
        public boolean a() {
            return true;
        }

        @Override
        public void e() {
            int n2 = this.a.bh();
            if (n2 > 100) {
                this.a.b(0.0f, 0.0f, 0.0f);
            } else if (this.a.bc().nextInt(50) == 0 || !this.a.Y || !this.a.n()) {
                float f2 = this.a.bc().nextFloat() * (float)Math.PI * 2.0f;
                \u2603 = ns.b(f2) * 0.2f;
                \u2603 = -0.1f + this.a.bc().nextFloat() * 0.2f;
                \u2603 = ns.a(f2) * 0.2f;
                this.a.b(\u2603, \u2603, \u2603);
            }
        }
    }
}

