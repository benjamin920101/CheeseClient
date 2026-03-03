/*
 * Decompiled with CFR 0.152.
 */
public class tu
extends tm {
    private c<ua> bm;
    private int bo = 0;
    private int bp = 0;
    private boolean bq = false;
    private boolean br = false;
    private int bs = 0;
    private b bt = tu$b.b;
    private int bu = 0;
    private wn bv = null;

    public tu(adm adm2) {
        super(adm2);
        this.a(0.6f, 0.7f);
        this.g = new e(this);
        this.f = new f(this);
        ((sv)this.s()).a(true);
        this.h.a(2.5f);
        this.i.a(1, new ra(this));
        this.i.a(1, new g(this, 1.33));
        this.i.a(2, new sh(this, 1.0, zy.bR, false));
        this.i.a(2, new sh(this, 1.0, zy.bW, false));
        this.i.a(2, new sh(this, 1.0, zw.a(afi.N), false));
        this.i.a(3, new qv(this, 0.8));
        this.i.a(5, new h(this));
        this.i.a(5, new rz(this, 0.6));
        this.i.a(11, new ri(this, wn.class, 10.0f));
        this.bm = new c<ua>(this, ua.class, 16.0f, 1.33, 1.33);
        this.i.a(4, this.bm);
        this.b(0.0);
    }

    @Override
    protected float bE() {
        if (this.f.a() && this.f.e() > this.t + 0.5) {
            return 0.5f;
        }
        return this.bt.b();
    }

    public void a(b b2) {
        this.bt = b2;
    }

    public float p(float f2) {
        if (this.bp == 0) {
            return 0.0f;
        }
        return ((float)this.bo + f2) / (float)this.bp;
    }

    public void b(double d2) {
        this.s().a(d2);
        this.f.a(this.f.d(), this.f.e(), this.f.f(), d2);
    }

    public void a(boolean bl2, b b2) {
        super.i(bl2);
        if (!bl2) {
            if (this.bt == tu$b.e) {
                this.bt = tu$b.b;
            }
        } else {
            this.b(1.5 * (double)b2.a());
            this.a(this.cm(), this.bB(), ((this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f) * 0.8f);
        }
        this.bq = bl2;
    }

    public void b(b b2) {
        this.a(true, b2);
        this.bp = b2.d();
        this.bo = 0;
    }

    public boolean cl() {
        return this.bq;
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(18, Byte.valueOf((byte)0));
    }

    @Override
    public void E() {
        if (this.f.b() > 0.8) {
            this.a(tu$b.d);
        } else if (this.bt != tu$b.e) {
            this.a(tu$b.b);
        }
        if (this.bs > 0) {
            --this.bs;
        }
        if (this.bu > 0) {
            this.bu -= this.V.nextInt(3);
            if (this.bu < 0) {
                this.bu = 0;
            }
        }
        if (this.C) {
            Object object;
            if (!this.br) {
                this.a(false, tu$b.a);
                this.cw();
            }
            if (this.cn() == 99 && this.bs == 0 && (object = this.u()) != null && this.h((pk)object) < 16.0) {
                this.a(((pr)object).s, ((pr)object).u);
                this.f.a(((pr)object).s, ((pr)object).t, ((pr)object).u, this.f.b());
                this.b(tu$b.e);
                this.br = true;
            }
            if (!((e)(object = (e)this.g)).c()) {
                if (this.f.a() && this.bs == 0) {
                    asx asx2 = this.h.j();
                    aui \u26032 = new aui(this.f.d(), this.f.e(), this.f.f());
                    if (asx2 != null && asx2.e() < asx2.d()) {
                        \u26032 = asx2.a(this);
                    }
                    this.a(\u26032.a, \u26032.c);
                    this.b(this.bt);
                }
            } else if (!((e)object).d()) {
                this.ct();
            }
        }
        this.br = this.C;
    }

    @Override
    public void Y() {
    }

    private void a(double d2, double d3) {
        this.y = (float)(ns.b(d3 - this.u, d2 - this.s) * 180.0 / 3.1415927410125732) - 90.0f;
    }

    private void ct() {
        ((e)this.g).a(true);
    }

    private void cu() {
        ((e)this.g).a(false);
    }

    private void cv() {
        this.bs = this.co();
    }

    private void cw() {
        this.cv();
        this.cu();
    }

    @Override
    public void m() {
        super.m();
        if (this.bo != this.bp) {
            if (this.bo == 0 && !this.o.D) {
                this.o.a((pk)this, (byte)1);
            }
            ++this.bo;
        } else if (this.bp != 0) {
            this.bo = 0;
            this.bp = 0;
        }
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(10.0);
        this.a(vy.d).a(0.3f);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("RabbitType", this.cn());
        dn2.a("MoreCarrotTicks", this.bu);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.r(dn2.f("RabbitType"));
        this.bu = dn2.f("MoreCarrotTicks");
    }

    protected String cm() {
        return "mob.rabbit.hop";
    }

    @Override
    protected String z() {
        return "mob.rabbit.idle";
    }

    @Override
    protected String bo() {
        return "mob.rabbit.hurt";
    }

    @Override
    protected String bp() {
        return "mob.rabbit.death";
    }

    @Override
    public boolean r(pk pk2) {
        if (this.cn() == 99) {
            this.a("mob.attack", 1.0f, (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
            return pk2.a(ow.a(this), 8.0f);
        }
        return pk2.a(ow.a(this), 3.0f);
    }

    @Override
    public int br() {
        if (this.cn() == 99) {
            return 8;
        }
        return super.br();
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        return super.a(ow2, f2);
    }

    @Override
    protected void bq() {
        this.a(new zx(zy.br, 1), 0.0f);
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(2) + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.bs, 1);
        }
        \u2603 = this.V.nextInt(2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            if (this.at()) {
                this.a(zy.bp, 1);
                continue;
            }
            this.a(zy.bo, 1);
        }
    }

    private boolean a(zw zw2) {
        return zw2 == zy.bR || zw2 == zy.bW || zw2 == zw.a(afi.N);
    }

    public tu b(ph ph2) {
        tu tu2 = new tu(this.o);
        if (ph2 instanceof tu) {
            tu2.r(this.V.nextBoolean() ? this.cn() : ((tu)ph2).cn());
        }
        return tu2;
    }

    @Override
    public boolean d(zx zx2) {
        return zx2 != null && this.a(zx2.b());
    }

    public int cn() {
        return this.ac.a(18);
    }

    public void r(int n2) {
        if (n2 == 99) {
            this.i.a(this.bm);
            this.i.a(4, new a(this));
            this.bi.a(1, new sm((py)this, false, new Class[0]));
            this.bi.a(2, new sp<wn>((py)this, wn.class, true));
            this.bi.a(2, new sp<ua>((py)this, ua.class, true));
            if (!this.l_()) {
                this.a(di.a("entity.KillerBunny.name"));
            }
        }
        this.ac.b(18, (byte)n2);
    }

    @Override
    public pu a(ok ok2, pu pu22) {
        pu22 = super.a(ok2, pu22);
        int n2 = this.V.nextInt(6);
        boolean \u26032 = false;
        if (pu22 instanceof d) {
            n2 = ((d)pu22).a;
            \u26032 = true;
        } else {
            pu pu22 = new d(n2);
        }
        this.r(n2);
        if (\u26032) {
            this.b(-24000);
        }
        return pu22;
    }

    private boolean cx() {
        return this.bu == 0;
    }

    protected int co() {
        return this.bt.c();
    }

    protected void cp() {
        this.o.a(cy.M, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 0.5 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, 0.0, 0.0, 0.0, afh.f(afi.cb.a(7)));
        this.bu = 100;
    }

    @Override
    public void a(byte by) {
        if (by == 1) {
            this.Z();
            this.bp = 10;
            this.bo = 0;
        } else {
            super.a(by);
        }
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }

    static enum b {
        a(0.0f, 0.0f, 30, 1),
        b(0.8f, 0.2f, 20, 10),
        c(1.0f, 0.45f, 14, 14),
        d(1.75f, 0.4f, 1, 8),
        e(2.0f, 0.7f, 7, 8);

        private final float f;
        private final float g;
        private final int h;
        private final int i;

        private b(float f2, float f3, int n3, int n4) {
            this.f = f2;
            this.g = f3;
            this.h = n3;
            this.i = n4;
        }

        public float a() {
            return this.f;
        }

        public float b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            return this.i;
        }
    }

    static class a
    extends rl {
        public a(tu tu2) {
            super(tu2, pr.class, 1.4, true);
        }

        @Override
        protected double a(pr pr2) {
            return 4.0f + pr2.J;
        }
    }

    static class g
    extends rv {
        private tu b;

        public g(tu tu2, double d2) {
            super(tu2, d2);
            this.b = tu2;
        }

        @Override
        public void e() {
            super.e();
            this.b.b(this.a);
        }
    }

    static class h
    extends ro {
        private final tu c;
        private boolean d;
        private boolean e = false;

        public h(tu tu2) {
            super(tu2, 0.7f, 16);
            this.c = tu2;
        }

        @Override
        public boolean a() {
            if (this.a <= 0) {
                if (!this.c.o.Q().b("mobGriefing")) {
                    return false;
                }
                this.e = false;
                this.d = this.c.cx();
            }
            return super.a();
        }

        @Override
        public boolean b() {
            return this.e && super.b();
        }

        @Override
        public void c() {
            super.c();
        }

        @Override
        public void d() {
            super.d();
        }

        @Override
        public void e() {
            super.e();
            this.c.p().a((double)this.b.n() + 0.5, this.b.o() + 1, (double)this.b.p() + 0.5, 10.0f, this.c.bQ());
            if (this.f()) {
                adm adm2 = this.c.o;
                cj \u26032 = this.b.a();
                alz \u26033 = adm2.p(\u26032);
                afh \u26034 = \u26033.c();
                if (this.e && \u26034 instanceof afq && \u26033.b(afq.a) == 7) {
                    adm2.a(\u26032, afi.a.Q(), 2);
                    adm2.b(\u26032, true);
                    this.c.cp();
                }
                this.e = false;
                this.a = 10;
            }
        }

        @Override
        protected boolean a(adm adm2, cj cj2) {
            afh afh2 = adm2.p(cj2).c();
            if (afh2 == afi.ak && (afh2 = (\u2603 = adm2.p(cj2 = cj2.a())).c()) instanceof afq && \u2603.b(afq.a) == 7 && this.d && !this.e) {
                this.e = true;
                return true;
            }
            return false;
        }
    }

    static class c<T extends pk>
    extends qs<T> {
        private tu c;

        public c(tu tu2, Class<T> clazz, float f2, double d2, double d3) {
            super(tu2, clazz, f2, d2, d3);
            this.c = tu2;
        }

        @Override
        public void e() {
            super.e();
        }
    }

    static class f
    extends qq {
        private tu g;

        public f(tu tu2) {
            super(tu2);
            this.g = tu2;
        }

        @Override
        public void c() {
            if (this.g.C && !this.g.cl()) {
                this.g.b(0.0);
            }
            super.c();
        }
    }

    public class e
    extends qo {
        private tu c;
        private boolean d;

        public e(tu tu3) {
            super(tu3);
            this.d = false;
            this.c = tu3;
        }

        public boolean c() {
            return this.a;
        }

        public boolean d() {
            return this.d;
        }

        public void a(boolean bl2) {
            this.d = bl2;
        }

        @Override
        public void b() {
            if (this.a) {
                this.c.b(tu$b.c);
                this.a = false;
            }
        }
    }

    public static class d
    implements pu {
        public int a;

        public d(int n2) {
            this.a = n2;
        }
    }
}

