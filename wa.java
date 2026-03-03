/*
 * Decompiled with CFR 0.152.
 */
public class wa
extends vv
implements vx {
    private sa a = new sa(this, 1.0, 20, 60, 15.0f);
    private rl b = new rl(this, wn.class, 1.2, false);

    public wa(adm adm2) {
        super(adm2);
        this.i.a(1, new ra(this));
        this.i.a(2, new sc(this));
        this.i.a(3, new qz(this, 1.0));
        this.i.a(3, new qs<ua>(this, ua.class, 6.0f, 1.0, 1.2));
        this.i.a(4, new rz(this, 1.0));
        this.i.a(6, new ri(this, wn.class, 8.0f));
        this.i.a(6, new ry(this));
        this.bi.a(1, new sm((py)this, false, new Class[0]));
        this.bi.a(2, new sp<wn>((py)this, wn.class, true));
        this.bi.a(3, new sp<ty>((py)this, ty.class, true));
        if (adm2 != null && !adm2.D) {
            this.n();
        }
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.d).a(0.25);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(13, new Byte(0));
    }

    @Override
    protected String z() {
        return "mob.skeleton.say";
    }

    @Override
    protected String bo() {
        return "mob.skeleton.hurt";
    }

    @Override
    protected String bp() {
        return "mob.skeleton.death";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.skeleton.step", 0.15f, 1.0f);
    }

    @Override
    public boolean r(pk pk2) {
        if (super.r(pk2)) {
            if (this.cm() == 1 && pk2 instanceof pr) {
                ((pr)pk2).c(new pf(pe.v.H, 200));
            }
            return true;
        }
        return false;
    }

    @Override
    public pw bz() {
        return pw.b;
    }

    @Override
    public void m() {
        if (this.o.w() && !this.o.D) {
            float f2 = this.c(1.0f);
            cj \u26032 = new cj(this.s, Math.round(this.t), this.u);
            if (f2 > 0.5f && this.V.nextFloat() * 30.0f < (f2 - 0.4f) * 2.0f && this.o.i(\u26032)) {
                boolean bl2 = true;
                zx \u26033 = this.p(4);
                if (\u26033 != null) {
                    if (\u26033.e()) {
                        \u26033.b(\u26033.h() + this.V.nextInt(2));
                        if (\u26033.h() >= \u26033.j()) {
                            this.b(\u26033);
                            this.c(4, null);
                        }
                    }
                    bl2 = false;
                }
                if (bl2) {
                    this.e(8);
                }
            }
        }
        if (this.o.D && this.cm() == 1) {
            this.a(0.72f, 2.535f);
        }
        super.m();
    }

    @Override
    public void ak() {
        super.ak();
        if (this.m instanceof py) {
            py py2 = (py)this.m;
            this.aI = py2.aI;
        }
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (ow2.i() instanceof wq && ow2.j() instanceof wn) {
            wn wn2 = (wn)ow2.j();
            double \u26032 = wn2.s - this.s;
            double \u26033 = wn2.u - this.u;
            if (\u26032 * \u26032 + \u26033 * \u26033 >= 2500.0) {
                wn2.b(mr.v);
            }
        } else if (ow2.j() instanceof vn && ((vn)ow2.j()).n() && ((vn)ow2.j()).cp()) {
            ((vn)ow2.j()).cq();
            this.a(new zx(zy.bX, 1, this.cm() == 1 ? 1 : 0), 0.0f);
        }
    }

    @Override
    protected zw A() {
        return zy.g;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        if (this.cm() == 1) {
            \u2603 = this.V.nextInt(3 + n2) - 1;
            for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                this.a(zy.h, 1);
            }
        } else {
            \u2603 = this.V.nextInt(3 + n2);
            for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                this.a(zy.g, 1);
            }
        }
        \u2603 = this.V.nextInt(3 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.aX, 1);
        }
    }

    @Override
    protected void bq() {
        if (this.cm() == 1) {
            this.a(new zx(zy.bX, 1, 1), 0.0f);
        }
    }

    @Override
    protected void a(ok ok2) {
        super.a(ok2);
        this.c(0, new zx(zy.f));
    }

    @Override
    public pu a(ok ok2, pu pu2) {
        pu2 = super.a(ok2, pu2);
        if (this.o.t instanceof ann && this.bc().nextInt(5) > 0) {
            this.i.a(4, this.b);
            this.a(1);
            this.c(0, new zx(zy.q));
            this.a(vy.e).a(4.0);
        } else {
            this.i.a(4, this.a);
            this.a(ok2);
            this.b(ok2);
        }
        this.j(this.V.nextFloat() < 0.55f * ok2.c());
        if (this.p(4) == null && (\u2603 = this.o.Y()).get(2) + 1 == 10 && \u2603.get(5) == 31 && this.V.nextFloat() < 0.25f) {
            this.c(4, new zx(this.V.nextFloat() < 0.1f ? afi.aZ : afi.aU));
            this.bj[4] = 0.0f;
        }
        return pu2;
    }

    public void n() {
        this.i.a(this.b);
        this.i.a(this.a);
        zx zx2 = this.bA();
        if (zx2 != null && zx2.b() == zy.f) {
            this.i.a(4, this.a);
        } else {
            this.i.a(4, this.b);
        }
    }

    @Override
    public void a(pr pr2, float f2) {
        wq wq2 = new wq(this.o, this, pr2, 1.6f, 14 - this.o.aa().a() * 4);
        int \u26032 = ack.a(aci.v.B, this.bA());
        int \u26033 = ack.a(aci.w.B, this.bA());
        wq2.b((double)(f2 * 2.0f) + (this.V.nextGaussian() * 0.25 + (double)((float)this.o.aa().a() * 0.11f)));
        if (\u26032 > 0) {
            wq2.b(wq2.j() + (double)\u26032 * 0.5 + 0.5);
        }
        if (\u26033 > 0) {
            wq2.a(\u26033);
        }
        if (ack.a(aci.x.B, this.bA()) > 0 || this.cm() == 1) {
            wq2.e(100);
        }
        this.a("random.bow", 1.0f, 1.0f / (this.bc().nextFloat() * 0.4f + 0.8f));
        this.o.d(wq2);
    }

    public int cm() {
        return this.ac.a(13);
    }

    public void a(int n2) {
        this.ac.b(13, (byte)n2);
        boolean bl2 = this.ab = n2 == 1;
        if (n2 == 1) {
            this.a(0.72f, 2.535f);
        } else {
            this.a(0.6f, 1.95f);
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        if (dn2.b("SkeletonType", 99)) {
            byte by = dn2.d("SkeletonType");
            this.a((int)by);
        }
        this.n();
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("SkeletonType", (byte)this.cm());
    }

    @Override
    public void c(int n2, zx zx2) {
        super.c(n2, zx2);
        if (!this.o.D && n2 == 0) {
            this.n();
        }
    }

    @Override
    public float aS() {
        if (this.cm() == 1) {
            return super.aS();
        }
        return 1.74f;
    }

    @Override
    public double am() {
        return this.j_() ? 0.0 : -0.35;
    }
}

