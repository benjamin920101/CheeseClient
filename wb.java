/*
 * Decompiled with CFR 0.152.
 */
public class wb
extends ps
implements vq {
    public float a;
    public float b;
    public float c;
    private boolean bk;

    public wb(adm adm2) {
        super(adm2);
        this.f = new d(this);
        this.i.a(1, new b(this));
        this.i.a(2, new a(this));
        this.i.a(3, new e(this));
        this.i.a(5, new c(this));
        this.bi.a(1, new so(this));
        this.bi.a(3, new sn(this, ty.class));
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)1));
    }

    protected void a(int n2) {
        this.ac.b(16, (byte)n2);
        this.a(0.51000005f * (float)n2, 0.51000005f * (float)n2);
        this.b(this.s, this.t, this.u);
        this.a(vy.a).a((double)(n2 * n2));
        this.a(vy.d).a(0.2f + 0.1f * (float)n2);
        this.i(this.bu());
        this.b_ = n2;
    }

    public int cm() {
        return this.ac.a(16);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Size", this.cm() - 1);
        dn2.a("wasOnGround", this.bk);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        int n2 = dn2.f("Size");
        if (n2 < 0) {
            n2 = 0;
        }
        this.a(n2 + 1);
        this.bk = dn2.n("wasOnGround");
    }

    protected cy n() {
        return cy.H;
    }

    protected String ck() {
        return "mob.slime." + (this.cm() > 1 ? "big" : "small");
    }

    @Override
    public void t_() {
        if (!this.o.D && this.o.aa() == oj.a && this.cm() > 0) {
            this.I = true;
        }
        this.b += (this.a - this.b) * 0.5f;
        this.c = this.b;
        super.t_();
        if (this.C && !this.bk) {
            int n2 = this.cm();
            for (\u2603 = 0; \u2603 < n2 * 8; ++\u2603) {
                float f2 = this.V.nextFloat() * (float)Math.PI * 2.0f;
                \u2603 = this.V.nextFloat() * 0.5f + 0.5f;
                \u2603 = ns.a(f2) * (float)n2 * 0.5f * \u2603;
                \u2603 = ns.b(f2) * (float)n2 * 0.5f * \u2603;
                this.o.a(this.n(), this.s + (double)\u2603, this.aR().b, this.u + (double)\u2603, 0.0, 0.0, 0.0, new int[0]);
            }
            if (this.cl()) {
                this.a(this.ck(), this.bB(), ((this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            }
            this.a = -0.5f;
        } else if (!this.C && this.bk) {
            this.a = 1.0f;
        }
        this.bk = this.C;
        this.ch();
    }

    protected void ch() {
        this.a *= 0.6f;
    }

    protected int cg() {
        return this.V.nextInt(20) + 10;
    }

    protected wb cf() {
        return new wb(this.o);
    }

    @Override
    public void i(int n2) {
        if (n2 == 16) {
            \u2603 = this.cm();
            this.a(0.51000005f * (float)\u2603, 0.51000005f * (float)\u2603);
            this.y = this.aK;
            this.aI = this.aK;
            if (this.V() && this.V.nextInt(20) == 0) {
                this.X();
            }
        }
        super.i(n2);
    }

    @Override
    public void J() {
        int n2 = this.cm();
        if (!this.o.D && n2 > 1 && this.bn() <= 0.0f) {
            \u2603 = 2 + this.V.nextInt(3);
            for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                float f2 = ((float)(\u2603 % 2) - 0.5f) * (float)n2 / 4.0f;
                \u2603 = ((float)(\u2603 / 2) - 0.5f) * (float)n2 / 4.0f;
                wb \u26032 = this.cf();
                if (this.l_()) {
                    \u26032.a(this.aM());
                }
                if (this.bZ()) {
                    \u26032.bX();
                }
                \u26032.a(n2 / 2);
                \u26032.b(this.s + (double)f2, this.t + 0.5, this.u + (double)\u2603, this.V.nextFloat() * 360.0f, 0.0f);
                this.o.d(\u26032);
            }
        }
        super.J();
    }

    @Override
    public void i(pk pk2) {
        super.i(pk2);
        if (pk2 instanceof ty && this.ci()) {
            this.e((pr)pk2);
        }
    }

    @Override
    public void d(wn wn2) {
        if (this.ci()) {
            this.e((pr)wn2);
        }
    }

    protected void e(pr pr2) {
        int n2 = this.cm();
        if (this.t(pr2) && this.h(pr2) < 0.6 * (double)n2 * (0.6 * (double)n2) && pr2.a(ow.a(this), (float)this.cj())) {
            this.a("mob.attack", 1.0f, (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
            this.a(this, pr2);
        }
    }

    @Override
    public float aS() {
        return 0.625f * this.K;
    }

    protected boolean ci() {
        return this.cm() > 1;
    }

    protected int cj() {
        return this.cm();
    }

    @Override
    protected String bo() {
        return "mob.slime." + (this.cm() > 1 ? "big" : "small");
    }

    @Override
    protected String bp() {
        return "mob.slime." + (this.cm() > 1 ? "big" : "small");
    }

    @Override
    protected zw A() {
        if (this.cm() == 1) {
            return zy.aM;
        }
        return null;
    }

    @Override
    public boolean bR() {
        cj cj2 = new cj(ns.c(this.s), 0, ns.c(this.u));
        amy \u26032 = this.o.f(cj2);
        if (this.o.P().u() == adr.c && this.V.nextInt(4) != 1) {
            return false;
        }
        if (this.o.aa() != oj.a) {
            ady ady2 = this.o.b(cj2);
            if (ady2 == ady.v && this.t > 50.0 && this.t < 70.0 && this.V.nextFloat() < 0.5f && this.V.nextFloat() < this.o.y() && this.o.l(new cj(this)) <= this.V.nextInt(8)) {
                return super.bR();
            }
            if (this.V.nextInt(10) == 0 && \u26032.a(987234911L).nextInt(10) == 0 && this.t < 40.0) {
                return super.bR();
            }
        }
        return false;
    }

    @Override
    protected float bB() {
        return 0.4f * (float)this.cm();
    }

    @Override
    public int bQ() {
        return 0;
    }

    protected boolean cn() {
        return this.cm() > 0;
    }

    protected boolean cl() {
        return this.cm() > 2;
    }

    @Override
    protected void bF() {
        this.w = 0.42f;
        this.ai = true;
    }

    @Override
    public pu a(ok ok2, pu pu2) {
        int n2 = this.V.nextInt(3);
        if (n2 < 2 && this.V.nextFloat() < 0.5f * ok2.c()) {
            ++n2;
        }
        \u2603 = 1 << n2;
        this.a(\u2603);
        return super.a(ok2, pu2);
    }

    static class c
    extends rd {
        private wb a;

        public c(wb wb2) {
            this.a = wb2;
            this.a(5);
        }

        @Override
        public boolean a() {
            return true;
        }

        @Override
        public void e() {
            ((d)this.a.q()).a(1.0);
        }
    }

    static class b
    extends rd {
        private wb a;

        public b(wb wb2) {
            this.a = wb2;
            this.a(5);
            ((sv)wb2.s()).d(true);
        }

        @Override
        public boolean a() {
            return this.a.V() || this.a.ab();
        }

        @Override
        public void e() {
            if (this.a.bc().nextFloat() < 0.8f) {
                this.a.r().a();
            }
            ((d)this.a.q()).a(1.2);
        }
    }

    static class e
    extends rd {
        private wb a;
        private float b;
        private int c;

        public e(wb wb2) {
            this.a = wb2;
            this.a(2);
        }

        @Override
        public boolean a() {
            return this.a.u() == null && (this.a.C || this.a.V() || this.a.ab());
        }

        @Override
        public void e() {
            if (--this.c <= 0) {
                this.c = 40 + this.a.bc().nextInt(60);
                this.b = this.a.bc().nextInt(360);
            }
            ((d)this.a.q()).a(this.b, false);
        }
    }

    static class a
    extends rd {
        private wb a;
        private int b;

        public a(wb wb2) {
            this.a = wb2;
            this.a(2);
        }

        @Override
        public boolean a() {
            pr pr2 = this.a.u();
            if (pr2 == null) {
                return false;
            }
            if (!pr2.ai()) {
                return false;
            }
            return !(pr2 instanceof wn) || !((wn)pr2).bA.a;
        }

        @Override
        public void c() {
            this.b = 300;
            super.c();
        }

        @Override
        public boolean b() {
            pr pr2 = this.a.u();
            if (pr2 == null) {
                return false;
            }
            if (!pr2.ai()) {
                return false;
            }
            if (pr2 instanceof wn && ((wn)pr2).bA.a) {
                return false;
            }
            return --this.b > 0;
        }

        @Override
        public void e() {
            this.a.a(this.a.u(), 10.0f, 10.0f);
            ((d)this.a.q()).a(this.a.y, this.a.ci());
        }
    }

    static class d
    extends qq {
        private float g;
        private int h;
        private wb i;
        private boolean j;

        public d(wb wb2) {
            super(wb2);
            this.i = wb2;
        }

        public void a(float f2, boolean bl2) {
            this.g = f2;
            this.j = bl2;
        }

        public void a(double d2) {
            this.e = d2;
            this.f = true;
        }

        @Override
        public void c() {
            this.a.aK = this.a.y = this.a(this.a.y, this.g, 30.0f);
            this.a.aI = this.a.y;
            if (!this.f) {
                this.a.n(0.0f);
                return;
            }
            this.f = false;
            if (this.a.C) {
                this.a.k((float)(this.e * this.a.a(vy.d).e()));
                if (this.h-- <= 0) {
                    this.h = this.i.cg();
                    if (this.j) {
                        this.h /= 3;
                    }
                    this.i.r().a();
                    if (this.i.cn()) {
                        this.i.a(this.i.ck(), this.i.bB(), ((this.i.bc().nextFloat() - this.i.bc().nextFloat()) * 0.2f + 1.0f) * 0.8f);
                    }
                } else {
                    this.i.ba = 0.0f;
                    this.i.aZ = 0.0f;
                    this.a.k(0.0f);
                }
            } else {
                this.a.k((float)(this.e * this.a.a(vy.d).e()));
            }
        }
    }
}

