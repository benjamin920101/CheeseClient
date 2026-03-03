/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class uk
extends vv
implements uc,
vx {
    private float[] a = new float[2];
    private float[] b = new float[2];
    private float[] c = new float[2];
    private float[] bm = new float[2];
    private int[] bn = new int[2];
    private int[] bo = new int[2];
    private int bp;
    private static final Predicate<pk> bq = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return pk2 instanceof pr && ((pr)pk2).bz() != pw.b;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };

    public uk(adm adm2) {
        super(adm2);
        this.i(this.bu());
        this.a(0.9f, 3.5f);
        this.ab = true;
        ((sv)this.s()).d(true);
        this.i.a(0, new ra(this));
        this.i.a(2, new sa(this, 1.0, 40, 20.0f));
        this.i.a(5, new rz(this, 1.0));
        this.i.a(6, new ri(this, wn.class, 8.0f));
        this.i.a(7, new ry(this));
        this.bi.a(1, new sm((py)this, false, new Class[0]));
        this.bi.a(2, new sp<pk>(this, ps.class, 0, false, false, bq));
        this.b_ = 50;
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(17, new Integer(0));
        this.ac.a(18, new Integer(0));
        this.ac.a(19, new Integer(0));
        this.ac.a(20, new Integer(0));
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Invul", this.cl());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.r(dn2.f("Invul"));
    }

    @Override
    protected String z() {
        return "mob.wither.idle";
    }

    @Override
    protected String bo() {
        return "mob.wither.hurt";
    }

    @Override
    protected String bp() {
        return "mob.wither.death";
    }

    @Override
    public void m() {
        int n2;
        pk pk2;
        this.w *= (double)0.6f;
        if (!this.o.D && this.s(0) > 0 && (pk2 = this.o.a(this.s(0))) != null) {
            if (this.t < pk2.t || !this.cm() && this.t < pk2.t + 5.0) {
                if (this.w < 0.0) {
                    this.w = 0.0;
                }
                this.w += (0.5 - this.w) * (double)0.6f;
            }
            if ((\u2603 = (\u2603 = pk2.s - this.s) * \u2603 + (d3 = pk2.u - this.u) * d3) > 9.0) {
                double d2 = ns.a(\u2603);
                this.v += (\u2603 / d2 * 0.5 - this.v) * (double)0.6f;
                this.x += (d3 / d2 * 0.5 - this.x) * (double)0.6f;
            }
        }
        if (this.v * this.v + this.x * this.x > (double)0.05f) {
            this.y = (float)ns.b(this.x, this.v) * 57.295776f - 90.0f;
        }
        super.m();
        for (n2 = 0; n2 < 2; ++n2) {
            this.bm[n2] = this.b[n2];
            this.c[n2] = this.a[n2];
        }
        for (n2 = 0; n2 < 2; ++n2) {
            \u2603 = this.s(n2 + 1);
            pk pk3 = null;
            if (\u2603 > 0) {
                pk3 = this.o.a(\u2603);
            }
            if (pk3 != null) {
                double d3 = this.t(n2 + 1);
                \u2603 = this.u(n2 + 1);
                d2 = this.v(n2 + 1);
                \u2603 = pk3.s - d3;
                \u2603 = pk3.t + (double)pk3.aS() - \u2603;
                \u2603 = pk3.u - d2;
                \u2603 = ns.a(\u2603 * \u2603 + \u2603 * \u2603);
                float \u26032 = (float)(ns.b(\u2603, \u2603) * 180.0 / 3.1415927410125732) - 90.0f;
                float \u26033 = (float)(-(ns.b(\u2603, \u2603) * 180.0 / 3.1415927410125732));
                this.a[n2] = this.b(this.a[n2], \u26033, 40.0f);
                this.b[n2] = this.b(this.b[n2], \u26032, 10.0f);
                continue;
            }
            this.b[n2] = this.b(this.b[n2], this.aI, 10.0f);
        }
        n2 = this.cm() ? '\u0001' : '\u0000';
        for (i2 = 0; i2 < 3; ++i2) {
            double d4 = this.t(i2);
            \u2603 = this.u(i2);
            \u2603 = this.v(i2);
            this.o.a(cy.l, d4 + this.V.nextGaussian() * (double)0.3f, \u2603 + this.V.nextGaussian() * (double)0.3f, \u2603 + this.V.nextGaussian() * (double)0.3f, 0.0, 0.0, 0.0, new int[0]);
            if (n2 == 0 || this.o.s.nextInt(4) != 0) continue;
            this.o.a(cy.p, d4 + this.V.nextGaussian() * (double)0.3f, \u2603 + this.V.nextGaussian() * (double)0.3f, \u2603 + this.V.nextGaussian() * (double)0.3f, (double)0.7f, (double)0.7f, 0.5, new int[0]);
        }
        if (this.cl() > 0) {
            for (int i2 = 0; i2 < 3; ++i2) {
                this.o.a(cy.p, this.s + this.V.nextGaussian() * 1.0, this.t + (double)(this.V.nextFloat() * 3.3f), this.u + this.V.nextGaussian() * 1.0, (double)0.7f, (double)0.7f, (double)0.9f, new int[0]);
            }
        }
    }

    @Override
    protected void E() {
        int n2;
        if (this.cl() > 0) {
            int n3 = this.cl() - 1;
            if (n3 <= 0) {
                this.o.a(this, this.s, this.t + (double)this.aS(), this.u, 7.0f, false, this.o.Q().b("mobGriefing"));
                this.o.a(1013, new cj(this), 0);
            }
            this.r(n3);
            if (this.W % 10 == 0) {
                this.h(10.0f);
            }
            return;
        }
        super.E();
        block0: for (n2 = 1; n2 < 3; ++n2) {
            Object object;
            if (this.W < this.bn[n2 - 1]) continue;
            this.bn[n2 - 1] = this.W + 10 + this.V.nextInt(10);
            if (this.o.aa() == oj.c || this.o.aa() == oj.d) {
                int n4 = n2 - 1;
                int n5 = this.bo[n4];
                this.bo[n4] = n5 + 1;
                if (n5 > 15) {
                    float f2 = 10.0f;
                    \u2603 = 5.0f;
                    double \u26032 = ns.a(this.V, this.s - (double)f2, this.s + (double)f2);
                    double \u26033 = ns.a(this.V, this.t - (double)\u2603, this.t + (double)\u2603);
                    double \u26034 = ns.a(this.V, this.u - (double)f2, this.u + (double)f2);
                    this.a(n2 + 1, \u26032, \u26033, \u26034, true);
                    this.bo[n2 - 1] = 0;
                }
            }
            if ((\u2603 = this.s(n2)) > 0) {
                object = this.o.a(\u2603);
                if (object == null || !((pk)object).ai() || this.h((pk)object) > 900.0 || !this.t((pk)object)) {
                    this.b(n2, 0);
                    continue;
                }
                if (object instanceof wn && ((wn)object).bA.a) {
                    this.b(n2, 0);
                    continue;
                }
                this.a(n2 + 1, (pr)object);
                this.bn[n2 - 1] = this.W + 40 + this.V.nextInt(20);
                this.bo[n2 - 1] = 0;
                continue;
            }
            object = this.o.a(pr.class, this.aR().b(20.0, 8.0, 20.0), Predicates.and(bq, po.d));
            for (int i2 = 0; i2 < 10 && !object.isEmpty(); ++i2) {
                pr pr2 = (pr)object.get(this.V.nextInt(object.size()));
                if (pr2 != this && pr2.ai() && this.t(pr2)) {
                    if (pr2 instanceof wn) {
                        if (((wn)pr2).bA.a) continue block0;
                        this.b(n2, pr2.F());
                        continue block0;
                    }
                    this.b(n2, pr2.F());
                    continue block0;
                }
                object.remove(pr2);
            }
        }
        if (this.u() != null) {
            this.b(0, this.u().F());
        } else {
            this.b(0, 0);
        }
        if (this.bp > 0) {
            --this.bp;
            if (this.bp == 0 && this.o.Q().b("mobGriefing")) {
                n2 = ns.c(this.t);
                \u2603 = ns.c(this.s);
                \u2603 = ns.c(this.u);
                boolean \u26035 = false;
                for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                    for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                        for (\u2603 = 0; \u2603 <= 3; ++\u2603) {
                            \u2603 = \u2603 + \u2603;
                            \u2603 = n2 + \u2603;
                            \u2603 = \u2603 + \u2603;
                            cj cj2 = new cj(\u2603, \u2603, \u2603);
                            afh \u26036 = this.o.p(cj2).c();
                            if (\u26036.t() == arm.a || !uk.a(\u26036)) continue;
                            \u26035 = this.o.b(cj2, true) || \u26035;
                        }
                    }
                }
                if (\u26035) {
                    this.o.a(null, 1012, new cj(this), 0);
                }
            }
        }
        if (this.W % 20 == 0) {
            this.h(1.0f);
        }
    }

    public static boolean a(afh afh2) {
        return afh2 != afi.h && afh2 != afi.bF && afh2 != afi.bG && afh2 != afi.bX && afh2 != afi.cv;
    }

    public void n() {
        this.r(220);
        this.i(this.bu() / 3.0f);
    }

    @Override
    public void aA() {
    }

    @Override
    public int br() {
        return 4;
    }

    private double t(int n2) {
        if (n2 <= 0) {
            return this.s;
        }
        float f2 = (this.aI + (float)(180 * (n2 - 1))) / 180.0f * (float)Math.PI;
        \u2603 = ns.b(f2);
        return this.s + (double)\u2603 * 1.3;
    }

    private double u(int n2) {
        if (n2 <= 0) {
            return this.t + 3.0;
        }
        return this.t + 2.2;
    }

    private double v(int n2) {
        if (n2 <= 0) {
            return this.u;
        }
        float f2 = (this.aI + (float)(180 * (n2 - 1))) / 180.0f * (float)Math.PI;
        \u2603 = ns.a(f2);
        return this.u + (double)\u2603 * 1.3;
    }

    private float b(float f2, float f3, float f4) {
        \u2603 = ns.g(f3 - f2);
        if (\u2603 > f4) {
            \u2603 = f4;
        }
        if (\u2603 < -f4) {
            \u2603 = -f4;
        }
        return f2 + \u2603;
    }

    private void a(int n2, pr pr2) {
        this.a(n2, pr2.s, pr2.t + (double)pr2.aS() * 0.5, pr2.u, n2 == 0 && this.V.nextFloat() < 0.001f);
    }

    private void a(int n2, double d2, double d3, double d4, boolean bl2) {
        this.o.a(null, 1014, new cj(this), 0);
        double d5 = this.t(n2);
        \u2603 = this.u(n2);
        \u2603 = this.v(n2);
        \u2603 = d2 - d5;
        \u2603 = d3 - \u2603;
        \u2603 = d4 - \u2603;
        xd \u26032 = new xd(this.o, this, \u2603, \u2603, \u2603);
        if (bl2) {
            \u26032.a(true);
        }
        \u26032.t = \u2603;
        \u26032.s = d5;
        \u26032.u = \u2603;
        this.o.d(\u26032);
    }

    @Override
    public void a(pr pr2, float f2) {
        this.a(0, pr2);
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (ow2 == ow.f || ow2.j() instanceof uk) {
            return false;
        }
        if (this.cl() > 0 && ow2 != ow.j) {
            return false;
        }
        if (this.cm() && (pk2 = ow2.i()) instanceof wq) {
            return false;
        }
        pk pk2 = ow2.j();
        if (pk2 != null && !(pk2 instanceof wn) && pk2 instanceof pr && ((pr)pk2).bz() == this.bz()) {
            return false;
        }
        if (this.bp <= 0) {
            this.bp = 20;
        }
        int \u26032 = 0;
        while (\u26032 < this.bo.length) {
            int n2 = \u26032++;
            this.bo[n2] = this.bo[n2] + 3;
        }
        return super.a(ow2, f2);
    }

    @Override
    protected void b(boolean bl2, int n2) {
        uz uz2 = this.a(zy.bZ, 1);
        if (uz2 != null) {
            uz2.u();
        }
        if (!this.o.D) {
            for (wn wn2 : this.o.a(wn.class, this.aR().b(50.0, 100.0, 50.0))) {
                wn2.b(mr.J);
            }
        }
    }

    @Override
    protected void D() {
        this.aQ = 0;
    }

    @Override
    public int b(float f2) {
        return 0xF000F0;
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    public void c(pf pf2) {
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(300.0);
        this.a(vy.d).a(0.6f);
        this.a(vy.b).a(40.0);
    }

    public float a(int n2) {
        return this.b[n2];
    }

    public float b(int n2) {
        return this.a[n2];
    }

    public int cl() {
        return this.ac.c(20);
    }

    public void r(int n2) {
        this.ac.b(20, n2);
    }

    public int s(int n2) {
        return this.ac.c(17 + n2);
    }

    public void b(int n2, int n3) {
        this.ac.b(17 + n2, n3);
    }

    public boolean cm() {
        return this.bn() <= this.bu() / 2.0f;
    }

    @Override
    public pw bz() {
        return pw.b;
    }

    @Override
    public void a(pk pk2) {
        this.m = null;
    }
}

