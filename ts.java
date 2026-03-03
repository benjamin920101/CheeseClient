/*
 * Decompiled with CFR 0.152.
 */
public class ts
extends qa {
    private qs<wn> bo;
    private sh bp;

    public ts(adm adm2) {
        super(adm2);
        this.a(0.6f, 0.7f);
        ((sv)this.s()).a(true);
        this.i.a(1, new ra(this));
        this.i.a(2, this.bm);
        this.bp = new sh(this, 0.6, zy.aU, true);
        this.i.a(3, this.bp);
        this.i.a(5, new rb(this, 1.0, 10.0f, 5.0f));
        this.i.a(6, new rs(this, 0.8));
        this.i.a(7, new rh(this, 0.3f));
        this.i.a(8, new rr(this));
        this.i.a(9, new qv(this, 0.8));
        this.i.a(10, new rz(this, 0.8));
        this.i.a(11, new ri(this, wn.class, 10.0f));
        this.bi.a(1, new sq<tn>(this, tn.class, false, null));
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(18, Byte.valueOf((byte)0));
    }

    @Override
    public void E() {
        if (this.q().a()) {
            double d2 = this.q().b();
            if (d2 == 0.6) {
                this.c(true);
                this.d(false);
            } else if (d2 == 1.33) {
                this.c(false);
                this.d(true);
            } else {
                this.c(false);
                this.d(false);
            }
        } else {
            this.c(false);
            this.d(false);
        }
    }

    @Override
    protected boolean C() {
        return !this.cl() && this.W > 2400;
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(10.0);
        this.a(vy.d).a(0.3f);
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("CatType", this.ct());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.r(dn2.f("CatType"));
    }

    @Override
    protected String z() {
        if (this.cl()) {
            if (this.cr()) {
                return "mob.cat.purr";
            }
            if (this.V.nextInt(4) == 0) {
                return "mob.cat.purreow";
            }
            return "mob.cat.meow";
        }
        return "";
    }

    @Override
    protected String bo() {
        return "mob.cat.hitt";
    }

    @Override
    protected String bp() {
        return "mob.cat.hitt";
    }

    @Override
    protected float bB() {
        return 0.4f;
    }

    @Override
    protected zw A() {
        return zy.aF;
    }

    @Override
    public boolean r(pk pk2) {
        return pk2.a(ow.a(this), 3.0f);
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        this.bm.a(false);
        return super.a(ow2, f2);
    }

    @Override
    protected void b(boolean bl2, int n2) {
    }

    @Override
    public boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (this.cl()) {
            if (this.e((pr)wn2) && !this.o.D && !this.d(zx2)) {
                this.bm.a(!this.cn());
            }
        } else if (this.bp.f() && zx2 != null && zx2.b() == zy.aU && wn2.h(this) < 9.0) {
            if (!wn2.bA.d) {
                --zx2.b;
            }
            if (zx2.b <= 0) {
                wn2.bi.a(wn2.bi.c, null);
            }
            if (!this.o.D) {
                if (this.V.nextInt(3) == 0) {
                    this.m(true);
                    this.r(1 + this.o.s.nextInt(3));
                    this.b(wn2.aK().toString());
                    this.l(true);
                    this.bm.a(true);
                    this.o.a((pk)this, (byte)7);
                } else {
                    this.l(false);
                    this.o.a((pk)this, (byte)6);
                }
            }
            return true;
        }
        return super.a(wn2);
    }

    public ts b(ph ph2) {
        ts ts2 = new ts(this.o);
        if (this.cl()) {
            ts2.b(this.b());
            ts2.m(true);
            ts2.r(this.ct());
        }
        return ts2;
    }

    @Override
    public boolean d(zx zx2) {
        return zx2 != null && zx2.b() == zy.aU;
    }

    @Override
    public boolean a(tm tm2) {
        if (tm2 == this) {
            return false;
        }
        if (!this.cl()) {
            return false;
        }
        if (!(tm2 instanceof ts)) {
            return false;
        }
        ts ts2 = (ts)tm2;
        if (!ts2.cl()) {
            return false;
        }
        return this.cr() && ts2.cr();
    }

    public int ct() {
        return this.ac.a(18);
    }

    public void r(int n2) {
        this.ac.b(18, (byte)n2);
    }

    @Override
    public boolean bR() {
        return this.o.s.nextInt(3) != 0;
    }

    @Override
    public boolean bS() {
        if (this.o.a(this.aR(), (pk)this) && this.o.a((pk)this, this.aR()).isEmpty() && !this.o.d(this.aR())) {
            cj cj2 = new cj(this.s, this.aR().b, this.u);
            if (cj2.o() < this.o.F()) {
                return false;
            }
            afh \u26032 = this.o.p(cj2.b()).c();
            if (\u26032 == afi.c || \u26032.t() == arm.j) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String e_() {
        if (this.l_()) {
            return this.aM();
        }
        if (this.cl()) {
            return di.a("entity.Cat.name");
        }
        return super.e_();
    }

    @Override
    public void m(boolean bl2) {
        super.m(bl2);
    }

    @Override
    protected void cm() {
        if (this.bo == null) {
            this.bo = new qs<wn>(this, wn.class, 16.0f, 0.8, 1.33);
        }
        this.i.a(this.bo);
        if (!this.cl()) {
            this.i.a(4, this.bo);
        }
    }

    @Override
    public pu a(ok ok2, pu pu22) {
        pu pu22;
        pu22 = super.a(ok2, pu22);
        if (this.o.s.nextInt(7) == 0) {
            for (int i2 = 0; i2 < 2; ++i2) {
                ts ts2 = new ts(this.o);
                ts2.b(this.s, this.t, this.u, this.y, 0.0f);
                ts2.b(-24000);
                this.o.d(ts2);
            }
        }
        return pu22;
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }
}

