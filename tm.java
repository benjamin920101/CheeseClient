/*
 * Decompiled with CFR 0.152.
 */
public abstract class tm
extends ph
implements pi {
    protected afh bn = afi.c;
    private int bm;
    private wn bo;

    public tm(adm adm2) {
        super(adm2);
    }

    @Override
    protected void E() {
        if (this.l() != 0) {
            this.bm = 0;
        }
        super.E();
    }

    @Override
    public void m() {
        super.m();
        if (this.l() != 0) {
            this.bm = 0;
        }
        if (this.bm > 0) {
            --this.bm;
            if (this.bm % 10 == 0) {
                double d2 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                this.o.a(cy.I, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 0.5 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, d2, \u2603, \u2603, new int[0]);
            }
        }
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        this.bm = 0;
        return super.a(ow2, f2);
    }

    @Override
    public float a(cj cj2) {
        if (this.o.p(cj2.b()).c() == afi.c) {
            return 10.0f;
        }
        return this.o.o(cj2) - 0.5f;
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("InLove", this.bm);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.bm = dn2.f("InLove");
    }

    @Override
    public boolean bR() {
        int n2 = ns.c(this.s);
        cj \u26032 = new cj(n2, \u2603 = ns.c(this.aR().b), \u2603 = ns.c(this.u));
        return this.o.p(\u26032.b()).c() == this.bn && this.o.k(\u26032) > 8 && super.bR();
    }

    @Override
    public int w() {
        return 120;
    }

    @Override
    protected boolean C() {
        return false;
    }

    @Override
    protected int b(wn wn2) {
        return 1 + this.o.s.nextInt(3);
    }

    public boolean d(zx zx2) {
        if (zx2 == null) {
            return false;
        }
        return zx2.b() == zy.O;
    }

    @Override
    public boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (zx2 != null) {
            if (this.d(zx2) && this.l() == 0 && this.bm <= 0) {
                this.a(wn2, zx2);
                this.c(wn2);
                return true;
            }
            if (this.j_() && this.d(zx2)) {
                this.a(wn2, zx2);
                this.a((int)((float)(-this.l() / 20) * 0.1f), true);
                return true;
            }
        }
        return super.a(wn2);
    }

    protected void a(wn wn2, zx zx2) {
        if (!wn2.bA.d) {
            --zx2.b;
            if (zx2.b <= 0) {
                wn2.bi.a(wn2.bi.c, null);
            }
        }
    }

    public void c(wn wn2) {
        this.bm = 600;
        this.bo = wn2;
        this.o.a((pk)this, (byte)18);
    }

    public wn cq() {
        return this.bo;
    }

    public boolean cr() {
        return this.bm > 0;
    }

    public void cs() {
        this.bm = 0;
    }

    public boolean a(tm tm2) {
        if (tm2 == this) {
            return false;
        }
        if (tm2.getClass() != this.getClass()) {
            return false;
        }
        return this.cr() && tm2.cr();
    }

    @Override
    public void a(byte by2) {
        if (by2 == 18) {
            for (int i2 = 0; i2 < 7; ++i2) {
                double d2 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                this.o.a(cy.I, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 0.5 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, d2, \u2603, \u2603, new int[0]);
            }
        } else {
            byte by2;
            super.a(by2);
        }
    }
}

