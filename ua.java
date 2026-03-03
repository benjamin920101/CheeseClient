/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class ua
extends qa {
    private float bo;
    private float bp;
    private boolean bq;
    private boolean br;
    private float bs;
    private float bt;

    public ua(adm adm2) {
        super(adm2);
        this.a(0.6f, 0.8f);
        ((sv)this.s()).a(true);
        this.i.a(1, new ra(this));
        this.i.a(2, this.bm);
        this.i.a(3, new rh(this, 0.4f));
        this.i.a(4, new rl(this, 1.0, true));
        this.i.a(5, new rb(this, 1.0, 10.0f, 2.0f));
        this.i.a(6, new qv(this, 1.0));
        this.i.a(7, new rz(this, 1.0));
        this.i.a(8, new qt(this, 8.0f));
        this.i.a(9, new ri(this, wn.class, 8.0f));
        this.i.a(9, new ry(this));
        this.bi.a(1, new sr(this));
        this.bi.a(2, new ss(this));
        this.bi.a(3, new sm((py)this, true, new Class[0]));
        this.bi.a(4, new sq<pk>(this, tm.class, false, new Predicate<pk>(){

            public boolean a(pk pk2) {
                return pk2 instanceof tv || pk2 instanceof tu;
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pk)object);
            }
        }));
        this.bi.a(5, new sp<wa>((py)this, wa.class, false));
        this.m(false);
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.d).a(0.3f);
        if (this.cl()) {
            this.a(vy.a).a(20.0);
        } else {
            this.a(vy.a).a(8.0);
        }
        this.by().b(vy.e);
        this.a(vy.e).a(2.0);
    }

    @Override
    public void d(pr pr2) {
        super.d(pr2);
        if (pr2 == null) {
            this.o(false);
        } else if (!this.cl()) {
            this.o(true);
        }
    }

    @Override
    protected void E() {
        this.ac.b(18, Float.valueOf(this.bn()));
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(18, new Float(this.bn()));
        this.ac.a(19, new Byte(0));
        this.ac.a(20, new Byte((byte)zd.o.a()));
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.wolf.step", 0.15f, 1.0f);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Angry", this.cv());
        dn2.a("CollarColor", (byte)this.cw().b());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.o(dn2.n("Angry"));
        if (dn2.b("CollarColor", 99)) {
            this.a(zd.a(dn2.d("CollarColor")));
        }
    }

    @Override
    protected String z() {
        if (this.cv()) {
            return "mob.wolf.growl";
        }
        if (this.V.nextInt(3) == 0) {
            if (this.cl() && this.ac.d(18) < 10.0f) {
                return "mob.wolf.whine";
            }
            return "mob.wolf.panting";
        }
        return "mob.wolf.bark";
    }

    @Override
    protected String bo() {
        return "mob.wolf.hurt";
    }

    @Override
    protected String bp() {
        return "mob.wolf.death";
    }

    @Override
    protected float bB() {
        return 0.4f;
    }

    @Override
    protected zw A() {
        return zw.b(-1);
    }

    @Override
    public void m() {
        super.m();
        if (!this.o.D && this.bq && !this.br && !this.cf() && this.C) {
            this.br = true;
            this.bs = 0.0f;
            this.bt = 0.0f;
            this.o.a((pk)this, (byte)8);
        }
        if (!this.o.D && this.u() == null && this.cv()) {
            this.o(false);
        }
    }

    @Override
    public void t_() {
        super.t_();
        this.bp = this.bo;
        this.bo = this.cx() ? (this.bo += (1.0f - this.bo) * 0.4f) : (this.bo += (0.0f - this.bo) * 0.4f);
        if (this.U()) {
            this.bq = true;
            this.br = false;
            this.bs = 0.0f;
            this.bt = 0.0f;
        } else if ((this.bq || this.br) && this.br) {
            if (this.bs == 0.0f) {
                this.a("mob.wolf.shake", this.bB(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
            }
            this.bt = this.bs;
            this.bs += 0.05f;
            if (this.bt >= 2.0f) {
                this.bq = false;
                this.br = false;
                this.bt = 0.0f;
                this.bs = 0.0f;
            }
            if (this.bs > 0.4f) {
                float f2 = (float)this.aR().b;
                int \u26032 = (int)(ns.a((this.bs - 0.4f) * (float)Math.PI) * 7.0f);
                for (int i2 = 0; i2 < \u26032; ++i2) {
                    float f3 = (this.V.nextFloat() * 2.0f - 1.0f) * this.J * 0.5f;
                    \u2603 = (this.V.nextFloat() * 2.0f - 1.0f) * this.J * 0.5f;
                    this.o.a(cy.f, this.s + (double)f3, (double)(f2 + 0.8f), this.u + (double)\u2603, this.v, this.w, this.x, new int[0]);
                }
            }
        }
    }

    public boolean ct() {
        return this.bq;
    }

    public float p(float f2) {
        return 0.75f + (this.bt + (this.bs - this.bt) * f2) / 2.0f * 0.25f;
    }

    public float i(float f2, float f3) {
        \u2603 = (this.bt + (this.bs - this.bt) * f2 + f3) / 1.8f;
        if (\u2603 < 0.0f) {
            \u2603 = 0.0f;
        } else if (\u2603 > 1.0f) {
            \u2603 = 1.0f;
        }
        return ns.a(\u2603 * (float)Math.PI) * ns.a(\u2603 * (float)Math.PI * 11.0f) * 0.15f * (float)Math.PI;
    }

    public float q(float f2) {
        return (this.bp + (this.bo - this.bp) * f2) * 0.15f * (float)Math.PI;
    }

    @Override
    public float aS() {
        return this.K * 0.8f;
    }

    @Override
    public int bQ() {
        if (this.cn()) {
            return 20;
        }
        return super.bQ();
    }

    @Override
    public boolean a(ow ow22, float f22) {
        ow ow22;
        if (this.b(ow22)) {
            return false;
        }
        pk pk2 = ow22.j();
        this.bm.a(false);
        if (pk2 != null && !(pk2 instanceof wn) && !(pk2 instanceof wq)) {
            float f22 = (f22 + 1.0f) / 2.0f;
        }
        return super.a(ow22, f22);
    }

    @Override
    public boolean r(pk pk2) {
        boolean bl2 = pk2.a(ow.a(this), (float)((int)this.a(vy.e).e()));
        if (bl2) {
            this.a((pr)this, pk2);
        }
        return bl2;
    }

    @Override
    public void m(boolean bl2) {
        super.m(bl2);
        if (bl2) {
            this.a(vy.a).a(20.0);
        } else {
            this.a(vy.a).a(8.0);
        }
        this.a(vy.e).a(4.0);
    }

    @Override
    public boolean a(wn wn22) {
        zx zx2 = wn22.bi.h();
        if (this.cl()) {
            wn wn22;
            if (zx2 != null) {
                if (zx2.b() instanceof zs) {
                    zs zs2 = (zs)zx2.b();
                    if (zs2.g() && this.ac.d(18) < 20.0f) {
                        if (!wn22.bA.d) {
                            --zx2.b;
                        }
                        this.h((float)zs2.h(zx2));
                        if (zx2.b <= 0) {
                            wn22.bi.a(wn22.bi.c, null);
                        }
                        return true;
                    }
                } else if (zx2.b() == zy.aW && (\u2603 = zd.a(zx2.i())) != this.cw()) {
                    this.a(\u2603);
                    if (!wn22.bA.d && --zx2.b <= 0) {
                        wn22.bi.a(wn22.bi.c, null);
                    }
                    return true;
                }
            }
            if (this.e((pr)wn22) && !this.o.D && !this.d(zx2)) {
                this.bm.a(!this.cn());
                this.aY = false;
                this.h.n();
                this.d((pr)null);
            }
        } else if (zx2 != null && zx2.b() == zy.aX && !this.cv()) {
            if (!wn22.bA.d) {
                --zx2.b;
            }
            if (zx2.b <= 0) {
                wn22.bi.a(wn22.bi.c, null);
            }
            if (!this.o.D) {
                if (this.V.nextInt(3) == 0) {
                    this.m(true);
                    this.h.n();
                    this.d((pr)null);
                    this.bm.a(true);
                    this.i(20.0f);
                    this.b(wn22.aK().toString());
                    this.l(true);
                    this.o.a((pk)this, (byte)7);
                } else {
                    this.l(false);
                    this.o.a((pk)this, (byte)6);
                }
            }
            return true;
        }
        return super.a(wn22);
    }

    @Override
    public void a(byte by) {
        if (by == 8) {
            this.br = true;
            this.bs = 0.0f;
            this.bt = 0.0f;
        } else {
            super.a(by);
        }
    }

    public float cu() {
        if (this.cv()) {
            return 1.5393804f;
        }
        if (this.cl()) {
            return (0.55f - (20.0f - this.ac.d(18)) * 0.02f) * (float)Math.PI;
        }
        return 0.62831855f;
    }

    @Override
    public boolean d(zx zx2) {
        if (zx2 == null) {
            return false;
        }
        if (!(zx2.b() instanceof zs)) {
            return false;
        }
        return ((zs)zx2.b()).g();
    }

    @Override
    public int bV() {
        return 8;
    }

    public boolean cv() {
        return (this.ac.a(16) & 2) != 0;
    }

    public void o(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 2));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFFD));
        }
    }

    public zd cw() {
        return zd.a(this.ac.a(20) & 0xF);
    }

    public void a(zd zd2) {
        this.ac.b(20, (byte)(zd2.b() & 0xF));
    }

    public ua b(ph ph2) {
        ua ua2 = new ua(this.o);
        String \u26032 = this.b();
        if (\u26032 != null && \u26032.trim().length() > 0) {
            ua2.b(\u26032);
            ua2.m(true);
        }
        return ua2;
    }

    public void p(boolean bl2) {
        if (bl2) {
            this.ac.b(19, (byte)1);
        } else {
            this.ac.b(19, (byte)0);
        }
    }

    @Override
    public boolean a(tm tm2) {
        if (tm2 == this) {
            return false;
        }
        if (!this.cl()) {
            return false;
        }
        if (!(tm2 instanceof ua)) {
            return false;
        }
        ua ua2 = (ua)tm2;
        if (!ua2.cl()) {
            return false;
        }
        if (ua2.cn()) {
            return false;
        }
        return this.cr() && ua2.cr();
    }

    public boolean cx() {
        return this.ac.a(19) == 1;
    }

    @Override
    protected boolean C() {
        return !this.cl() && this.W > 2400;
    }

    @Override
    public boolean a(pr pr2, pr pr3) {
        if (pr2 instanceof vn || pr2 instanceof vr) {
            return false;
        }
        if (pr2 instanceof ua && (\u2603 = (ua)pr2).cl() && \u2603.co() == pr3) {
            return false;
        }
        if (pr2 instanceof wn && pr3 instanceof wn && !((wn)pr3).a((wn)pr2)) {
            return false;
        }
        return !(pr2 instanceof tp) || !((tp)pr2).co();
    }

    @Override
    public boolean cb() {
        return !this.cv() && super.cb();
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }
}

