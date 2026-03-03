/*
 * Decompiled with CFR 0.152.
 */
public class tn
extends tm {
    public float bm;
    public float bo;
    public float bp;
    public float bq;
    public float br = 1.0f;
    public int bs;
    public boolean bt;

    public tn(adm adm2) {
        super(adm2);
        this.a(0.4f, 0.7f);
        this.bs = this.V.nextInt(6000) + 6000;
        this.i.a(0, new ra(this));
        this.i.a(1, new rv(this, 1.4));
        this.i.a(2, new qv(this, 1.0));
        this.i.a(3, new sh(this, 1.0, zy.N, false));
        this.i.a(4, new rc(this, 1.1));
        this.i.a(5, new rz(this, 1.0));
        this.i.a(6, new ri(this, wn.class, 6.0f));
        this.i.a(7, new ry(this));
    }

    @Override
    public float aS() {
        return this.K;
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(4.0);
        this.a(vy.d).a(0.25);
    }

    @Override
    public void m() {
        super.m();
        this.bq = this.bm;
        this.bp = this.bo;
        this.bo = (float)((double)this.bo + (double)(this.C ? -1 : 4) * 0.3);
        this.bo = ns.a(this.bo, 0.0f, 1.0f);
        if (!this.C && this.br < 1.0f) {
            this.br = 1.0f;
        }
        this.br = (float)((double)this.br * 0.9);
        if (!this.C && this.w < 0.0) {
            this.w *= 0.6;
        }
        this.bm += this.br * 2.0f;
        if (!(this.o.D || this.j_() || this.cl() || --this.bs > 0)) {
            this.a("mob.chicken.plop", 1.0f, (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
            this.a(zy.aP, 1);
            this.bs = this.V.nextInt(6000) + 6000;
        }
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    protected String z() {
        return "mob.chicken.say";
    }

    @Override
    protected String bo() {
        return "mob.chicken.hurt";
    }

    @Override
    protected String bp() {
        return "mob.chicken.hurt";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.chicken.step", 0.15f, 1.0f);
    }

    @Override
    protected zw A() {
        return zy.G;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3) + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.G, 1);
        }
        if (this.at()) {
            this.a(zy.bl, 1);
        } else {
            this.a(zy.bk, 1);
        }
    }

    public tn b(ph ph2) {
        return new tn(this.o);
    }

    @Override
    public boolean d(zx zx2) {
        return zx2 != null && zx2.b() == zy.N;
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.bt = dn2.n("IsChickenJockey");
        if (dn2.c("EggLayTime")) {
            this.bs = dn2.f("EggLayTime");
        }
    }

    @Override
    protected int b(wn wn2) {
        if (this.cl()) {
            return 10;
        }
        return super.b(wn2);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("IsChickenJockey", this.bt);
        dn2.a("EggLayTime", this.bs);
    }

    @Override
    protected boolean C() {
        return this.cl() && this.l == null;
    }

    @Override
    public void al() {
        super.al();
        float f2 = ns.a(this.aI * (float)Math.PI / 180.0f);
        \u2603 = ns.b(this.aI * (float)Math.PI / 180.0f);
        \u2603 = 0.1f;
        \u2603 = 0.0f;
        this.l.b(this.s + (double)(\u2603 * f2), this.t + (double)(this.K * 0.5f) + this.l.am() + (double)\u2603, this.u - (double)(\u2603 * \u2603));
        if (this.l instanceof pr) {
            ((pr)this.l).aI = this.aI;
        }
    }

    public boolean cl() {
        return this.bt;
    }

    public void l(boolean bl2) {
        this.bt = bl2;
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }
}

