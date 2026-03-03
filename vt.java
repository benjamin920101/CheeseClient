/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.List;

public class vt
extends vv {
    private float a;
    private float b;
    private float c;
    private float bm;
    private float bn;
    private pr bo;
    private int bp;
    private boolean bq;
    private rz br;

    public vt(adm adm2) {
        super(adm2);
        this.b_ = 10;
        this.a(0.85f, 0.85f);
        this.i.a(4, new a(this));
        rp rp2 = new rp(this, 1.0);
        this.i.a(5, rp2);
        this.br = new rz(this, 1.0, 80);
        this.i.a(7, this.br);
        this.i.a(8, new ri(this, wn.class, 8.0f));
        this.i.a(8, new ri(this, vt.class, 12.0f, 0.01f));
        this.i.a(9, new ry(this));
        this.br.a(3);
        rp2.a(3);
        this.bi.a(1, new sp<pr>(this, pr.class, 10, true, false, new b(this)));
        this.f = new c(this);
        this.b = this.a = this.V.nextFloat();
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.e).a(6.0);
        this.a(vy.d).a(0.5);
        this.a(vy.b).a(16.0);
        this.a(vy.a).a(30.0);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a(dn2.n("Elder"));
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Elder", this.cn());
    }

    @Override
    protected sw b(adm adm2) {
        return new sy(this, adm2);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Integer.valueOf(0));
        this.ac.a(17, Integer.valueOf(0));
    }

    private boolean a(int n2) {
        return (this.ac.c(16) & n2) != 0;
    }

    private void a(int n2, boolean bl2) {
        int n3 = this.ac.c(16);
        if (bl2) {
            this.ac.b(16, n3 | n2);
        } else {
            this.ac.b(16, n3 & ~n2);
        }
    }

    public boolean n() {
        return this.a(2);
    }

    private void l(boolean bl2) {
        this.a(2, bl2);
    }

    public int cm() {
        if (this.cn()) {
            return 60;
        }
        return 80;
    }

    public boolean cn() {
        return this.a(4);
    }

    public void a(boolean bl2) {
        this.a(4, bl2);
        if (bl2) {
            this.a(1.9975f, 1.9975f);
            this.a(vy.d).a(0.3f);
            this.a(vy.e).a(8.0);
            this.a(vy.a).a(80.0);
            this.bX();
            this.br.b(400);
        }
    }

    public void co() {
        this.a(true);
        this.bm = 1.0f;
        this.bn = 1.0f;
    }

    private void b(int n2) {
        this.ac.b(17, n2);
    }

    public boolean cp() {
        return this.ac.c(17) != 0;
    }

    public pr cq() {
        if (!this.cp()) {
            return null;
        }
        if (this.o.D) {
            if (this.bo != null) {
                return this.bo;
            }
            pk pk2 = this.o.a(this.ac.c(17));
            if (pk2 instanceof pr) {
                this.bo = (pr)pk2;
                return this.bo;
            }
            return null;
        }
        return this.u();
    }

    @Override
    public void i(int n2) {
        super.i(n2);
        if (n2 == 16) {
            if (this.cn() && this.J < 1.0f) {
                this.a(1.9975f, 1.9975f);
            }
        } else if (n2 == 17) {
            this.bp = 0;
            this.bo = null;
        }
    }

    @Override
    public int w() {
        return 160;
    }

    @Override
    protected String z() {
        if (!this.V()) {
            return "mob.guardian.land.idle";
        }
        if (this.cn()) {
            return "mob.guardian.elder.idle";
        }
        return "mob.guardian.idle";
    }

    @Override
    protected String bo() {
        if (!this.V()) {
            return "mob.guardian.land.hit";
        }
        if (this.cn()) {
            return "mob.guardian.elder.hit";
        }
        return "mob.guardian.hit";
    }

    @Override
    protected String bp() {
        if (!this.V()) {
            return "mob.guardian.land.death";
        }
        if (this.cn()) {
            return "mob.guardian.elder.death";
        }
        return "mob.guardian.death";
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    public float aS() {
        return this.K * 0.5f;
    }

    @Override
    public float a(cj cj2) {
        if (this.o.p(cj2).c().t() == arm.h) {
            return 10.0f + this.o.o(cj2) - 0.5f;
        }
        return super.a(cj2);
    }

    @Override
    public void m() {
        if (this.o.D) {
            Object object;
            this.b = this.a;
            if (!this.V()) {
                this.c = 2.0f;
                if (this.w > 0.0 && this.bq && !this.R()) {
                    this.o.a(this.s, this.t, this.u, "mob.guardian.flop", 1.0f, 1.0f, false);
                }
                this.bq = this.w < 0.0 && this.o.d(new cj(this).b(), false);
            } else {
                this.c = this.n() ? (this.c < 0.5f ? 4.0f : (this.c += (0.5f - this.c) * 0.1f)) : (this.c += (0.125f - this.c) * 0.2f);
            }
            this.a += this.c;
            this.bn = this.bm;
            this.bm = !this.V() ? this.V.nextFloat() : (this.n() ? (this.bm += (0.0f - this.bm) * 0.25f) : (this.bm += (1.0f - this.bm) * 0.06f));
            if (this.n() && this.V()) {
                object = this.d(0.0f);
                for (int i2 = 0; i2 < 2; ++i2) {
                    this.o.a(cy.e, this.s + (this.V.nextDouble() - 0.5) * (double)this.J - ((aui)object).a * 1.5, this.t + this.V.nextDouble() * (double)this.K - ((aui)object).b * 1.5, this.u + (this.V.nextDouble() - 0.5) * (double)this.J - ((aui)object).c * 1.5, 0.0, 0.0, 0.0, new int[0]);
                }
            }
            if (this.cp()) {
                if (this.bp < this.cm()) {
                    ++this.bp;
                }
                if ((object = this.cq()) != null) {
                    this.p().a((pk)object, 90.0f, 90.0f);
                    this.p().a();
                    double d2 = this.q(0.0f);
                    \u2603 = ((pr)object).s - this.s;
                    \u2603 = ((pr)object).t + (double)(((pr)object).K * 0.5f) - (this.t + (double)this.aS());
                    \u2603 = ((pr)object).u - this.u;
                    \u2603 = Math.sqrt(\u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603);
                    \u2603 /= \u2603;
                    \u2603 /= \u2603;
                    \u2603 /= \u2603;
                    \u2603 = this.V.nextDouble();
                    while (\u2603 < \u2603) {
                        this.o.a(cy.e, this.s + \u2603 * (\u2603 += 1.8 - d2 + this.V.nextDouble() * (1.7 - d2)), this.t + \u2603 * \u2603 + (double)this.aS(), this.u + \u2603 * \u2603, 0.0, 0.0, 0.0, new int[0]);
                    }
                }
            }
        }
        if (this.Y) {
            this.h(300);
        } else if (this.C) {
            this.w += 0.5;
            this.v += (double)((this.V.nextFloat() * 2.0f - 1.0f) * 0.4f);
            this.x += (double)((this.V.nextFloat() * 2.0f - 1.0f) * 0.4f);
            this.y = this.V.nextFloat() * 360.0f;
            this.C = false;
            this.ai = true;
        }
        if (this.cp()) {
            this.y = this.aK;
        }
        super.m();
    }

    public float a(float f2) {
        return this.b + (this.a - this.b) * f2;
    }

    public float p(float f2) {
        return this.bn + (this.bm - this.bn) * f2;
    }

    public float q(float f2) {
        return ((float)this.bp + f2) / (float)this.cm();
    }

    @Override
    protected void E() {
        super.E();
        if (this.cn()) {
            int n2 = 1200;
            \u2603 = 1200;
            \u2603 = 6000;
            \u2603 = 2;
            if ((this.W + this.F()) % 1200 == 0) {
                pe pe2 = pe.f;
                List<lf> \u26032 = this.o.b(lf.class, new Predicate<lf>(){

                    public boolean a(lf lf2) {
                        return vt.this.h(lf2) < 2500.0 && lf2.c.c();
                    }

                    @Override
                    public /* synthetic */ boolean apply(Object object) {
                        return this.a((lf)object);
                    }
                });
                for (lf lf2 : \u26032) {
                    if (lf2.a(pe2) && lf2.b(pe2).c() >= 2 && lf2.b(pe2).b() >= 1200) continue;
                    lf2.a.a(new gm(10, 0.0f));
                    lf2.c(new pf(pe2.H, 6000, 2));
                }
            }
            if (!this.ck()) {
                this.a(new cj(this), 16);
            }
        }
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3) + this.V.nextInt(n2 + 1);
        if (\u2603 > 0) {
            this.a(new zx(zy.cC, \u2603, 0), 1.0f);
        }
        if (this.V.nextInt(3 + n2) > 1) {
            this.a(new zx(zy.aU, 1, zp.a.a.a()), 1.0f);
        } else if (this.V.nextInt(3 + n2) > 1) {
            this.a(new zx(zy.cD, 1, 0), 1.0f);
        }
        if (bl2 && this.cn()) {
            this.a(new zx(afi.v, 1, 1), 1.0f);
        }
    }

    @Override
    protected void bq() {
        zx zx2 = oa.a(this.V, ur.j()).a(this.V);
        this.a(zx2, 1.0f);
    }

    @Override
    protected boolean n_() {
        return true;
    }

    @Override
    public boolean bS() {
        return this.o.a(this.aR(), (pk)this) && this.o.a((pk)this, this.aR()).isEmpty();
    }

    @Override
    public boolean bR() {
        return (this.V.nextInt(20) == 0 || !this.o.j(new cj(this))) && super.bR();
    }

    @Override
    public boolean a(ow ow22, float f2) {
        ow ow22;
        if (!this.n() && !ow22.s() && ow22.i() instanceof pr) {
            pr pr2 = (pr)ow22.i();
            if (!ow22.c()) {
                pr2.a(ow.a((pk)this), 2.0f);
                pr2.a("damage.thorns", 0.5f, 1.0f);
            }
        }
        this.br.f();
        return super.a(ow22, f2);
    }

    @Override
    public int bQ() {
        return 180;
    }

    @Override
    public void g(float f2, float f3) {
        if (this.bM()) {
            if (this.V()) {
                this.a(f2, f3, 0.1f);
                this.d(this.v, this.w, this.x);
                this.v *= (double)0.9f;
                this.w *= (double)0.9f;
                this.x *= (double)0.9f;
                if (!this.n() && this.u() == null) {
                    this.w -= 0.005;
                }
            } else {
                super.g(f2, f3);
            }
        } else {
            super.g(f2, f3);
        }
    }

    static class c
    extends qq {
        private vt g;

        public c(vt vt2) {
            super(vt2);
            this.g = vt2;
        }

        @Override
        public void c() {
            if (!this.f || this.g.s().m()) {
                this.g.k(0.0f);
                this.g.l(false);
                return;
            }
            double d2 = this.b - this.g.s;
            \u2603 = this.c - this.g.t;
            \u2603 = this.d - this.g.u;
            \u2603 = d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603;
            \u2603 = ns.a(\u2603);
            \u2603 /= \u2603;
            float \u26032 = (float)(ns.b(\u2603, d2) * 180.0 / 3.1415927410125732) - 90.0f;
            this.g.aI = this.g.y = this.a(this.g.y, \u26032, 30.0f);
            float \u26033 = (float)(this.e * this.g.a(vy.d).e());
            this.g.k(this.g.bI() + (\u26033 - this.g.bI()) * 0.125f);
            \u2603 = Math.sin((double)(this.g.W + this.g.F()) * 0.5) * 0.05;
            \u2603 = Math.cos(this.g.y * (float)Math.PI / 180.0f);
            \u2603 = Math.sin(this.g.y * (float)Math.PI / 180.0f);
            this.g.v += \u2603 * \u2603;
            this.g.x += \u2603 * \u2603;
            \u2603 = Math.sin((double)(this.g.W + this.g.F()) * 0.75) * 0.05;
            this.g.w += \u2603 * (\u2603 + \u2603) * 0.25;
            this.g.w += (double)this.g.bI() * \u2603 * 0.1;
            qp \u26034 = this.g.p();
            \u2603 = this.g.s + d2 / \u2603 * 2.0;
            \u2603 = (double)this.g.aS() + this.g.t + \u2603 / \u2603 * 1.0;
            \u2603 = this.g.u + \u2603 / \u2603 * 2.0;
            \u2603 = \u26034.e();
            \u2603 = \u26034.f();
            \u2603 = \u26034.g();
            if (!\u26034.b()) {
                \u2603 = \u2603;
                \u2603 = \u2603;
                \u2603 = \u2603;
            }
            this.g.p().a(\u2603 + (\u2603 - \u2603) * 0.125, \u2603 + (\u2603 - \u2603) * 0.125, \u2603 + (\u2603 - \u2603) * 0.125, 10.0f, 40.0f);
            this.g.l(true);
        }
    }

    static class a
    extends rd {
        private vt a;
        private int b;

        public a(vt vt2) {
            this.a = vt2;
            this.a(3);
        }

        @Override
        public boolean a() {
            pr pr2 = this.a.u();
            return pr2 != null && pr2.ai();
        }

        @Override
        public boolean b() {
            return super.b() && (this.a.cn() || this.a.h(this.a.u()) > 9.0);
        }

        @Override
        public void c() {
            this.b = -10;
            this.a.s().n();
            this.a.p().a(this.a.u(), 90.0f, 90.0f);
            this.a.ai = true;
        }

        @Override
        public void d() {
            this.a.b(0);
            this.a.d((pr)null);
            this.a.br.f();
        }

        @Override
        public void e() {
            pr pr2 = this.a.u();
            this.a.s().n();
            this.a.p().a(pr2, 90.0f, 90.0f);
            if (!this.a.t(pr2)) {
                this.a.d((pr)null);
                return;
            }
            ++this.b;
            if (this.b == 0) {
                this.a.b(this.a.u().F());
                this.a.o.a((pk)this.a, (byte)21);
            } else if (this.b >= this.a.cm()) {
                float f2 = 1.0f;
                if (this.a.o.aa() == oj.d) {
                    f2 += 2.0f;
                }
                if (this.a.cn()) {
                    f2 += 2.0f;
                }
                pr2.a(ow.b(this.a, this.a), f2);
                pr2.a(ow.a(this.a), (float)this.a.a(vy.e).e());
                this.a.d((pr)null);
            } else if (this.b < 60 || this.b % 20 == 0) {
                // empty if block
            }
            super.e();
        }
    }

    static class b
    implements Predicate<pr> {
        private vt a;

        public b(vt vt2) {
            this.a = vt2;
        }

        public boolean a(pr pr2) {
            return (pr2 instanceof wn || pr2 instanceof tx) && pr2.h(this.a) > 9.0;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pr)object);
        }
    }
}

