/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class vr
extends pq
implements vq {
    private int a = 1;

    public vr(adm adm2) {
        super(adm2);
        this.a(4.0f, 4.0f);
        this.ab = true;
        this.b_ = 5;
        this.f = new b(this);
        this.i.a(5, new d(this));
        this.i.a(7, new a(this));
        this.i.a(7, new c(this));
        this.bi.a(1, new so(this));
    }

    public boolean n() {
        return this.ac.a(16) != 0;
    }

    public void a(boolean bl2) {
        this.ac.b(16, bl2 ? (byte)1 : 0);
    }

    public int cf() {
        return this.a;
    }

    @Override
    public void t_() {
        super.t_();
        if (!this.o.D && this.o.aa() == oj.a) {
            this.J();
        }
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if ("fireball".equals(ow2.p()) && ow2.j() instanceof wn) {
            super.a(ow2, 1000.0f);
            ((wn)ow2.j()).b(mr.z);
            return true;
        }
        return super.a(ow2, f2);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)0));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(10.0);
        this.a(vy.b).a(100.0);
    }

    @Override
    protected String z() {
        return "mob.ghast.moan";
    }

    @Override
    protected String bo() {
        return "mob.ghast.scream";
    }

    @Override
    protected String bp() {
        return "mob.ghast.death";
    }

    @Override
    protected zw A() {
        return zy.H;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(2) + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.bw, 1);
        }
        \u2603 = this.V.nextInt(3) + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.H, 1);
        }
    }

    @Override
    protected float bB() {
        return 10.0f;
    }

    @Override
    public boolean bR() {
        return this.V.nextInt(20) == 0 && super.bR() && this.o.aa() != oj.a;
    }

    @Override
    public int bV() {
        return 1;
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("ExplosionPower", this.a);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        if (dn2.b("ExplosionPower", 99)) {
            this.a = dn2.f("ExplosionPower");
        }
    }

    @Override
    public float aS() {
        return 2.6f;
    }

    static class c
    extends rd {
        private vr b;
        public int a;

        public c(vr vr2) {
            this.b = vr2;
        }

        @Override
        public boolean a() {
            return this.b.u() != null;
        }

        @Override
        public void c() {
            this.a = 0;
        }

        @Override
        public void d() {
            this.b.a(false);
        }

        @Override
        public void e() {
            pr pr2 = this.b.u();
            double \u26032 = 64.0;
            if (pr2.h(this.b) < \u26032 * \u26032 && this.b.t(pr2)) {
                adm adm2 = this.b.o;
                ++this.a;
                if (this.a == 10) {
                    adm2.a(null, 1007, new cj(this.b), 0);
                }
                if (this.a == 20) {
                    double d2 = 4.0;
                    aui \u26033 = this.b.d(1.0f);
                    \u2603 = pr2.s - (this.b.s + \u26033.a * d2);
                    \u2603 = pr2.aR().b + (double)(pr2.K / 2.0f) - (0.5 + this.b.t + (double)(this.b.K / 2.0f));
                    \u2603 = pr2.u - (this.b.u + \u26033.c * d2);
                    adm2.a(null, 1008, new cj(this.b), 0);
                    wu \u26034 = new wu(adm2, this.b, \u2603, \u2603, \u2603);
                    \u26034.e = this.b.cf();
                    \u26034.s = this.b.s + \u26033.a * d2;
                    \u26034.t = this.b.t + (double)(this.b.K / 2.0f) + 0.5;
                    \u26034.u = this.b.u + \u26033.c * d2;
                    adm2.d(\u26034);
                    this.a = -40;
                }
            } else if (this.a > 0) {
                --this.a;
            }
            this.b.a(this.a > 10);
        }
    }

    static class a
    extends rd {
        private vr a;

        public a(vr vr2) {
            this.a = vr2;
            this.a(2);
        }

        @Override
        public boolean a() {
            return true;
        }

        @Override
        public void e() {
            if (this.a.u() == null) {
                this.a.aI = this.a.y = -((float)ns.b(this.a.v, this.a.x)) * 180.0f / (float)Math.PI;
            } else {
                pr pr2 = this.a.u();
                double \u26032 = 64.0;
                if (pr2.h(this.a) < \u26032 * \u26032) {
                    double d2 = pr2.s - this.a.s;
                    \u2603 = pr2.u - this.a.u;
                    this.a.aI = this.a.y = -((float)ns.b(d2, \u2603)) * 180.0f / (float)Math.PI;
                }
            }
        }
    }

    static class d
    extends rd {
        private vr a;

        public d(vr vr2) {
            this.a = vr2;
            this.a(1);
        }

        @Override
        public boolean a() {
            qq qq2 = this.a.q();
            if (!qq2.a()) {
                return true;
            }
            double \u26032 = qq2.d() - this.a.s;
            double \u26033 = \u26032 * \u26032 + (\u2603 = qq2.e() - this.a.t) * \u2603 + (\u2603 = qq2.f() - this.a.u) * \u2603;
            return \u26033 < 1.0 || \u26033 > 3600.0;
        }

        @Override
        public boolean b() {
            return false;
        }

        @Override
        public void c() {
            Random random = this.a.bc();
            double \u26032 = this.a.s + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
            double \u26033 = this.a.t + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
            double \u26034 = this.a.u + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.a.q().a(\u26032, \u26033, \u26034, 1.0);
        }
    }

    static class b
    extends qq {
        private vr g;
        private int h;

        public b(vr vr2) {
            super(vr2);
            this.g = vr2;
        }

        @Override
        public void c() {
            if (!this.f) {
                return;
            }
            double d2 = this.b - this.g.s;
            \u2603 = this.c - this.g.t;
            \u2603 = this.d - this.g.u;
            \u2603 = d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603;
            if (this.h-- <= 0) {
                this.h += this.g.bc().nextInt(5) + 2;
                if (this.b(this.b, this.c, this.d, \u2603 = (double)ns.a(\u2603))) {
                    this.g.v += d2 / \u2603 * 0.1;
                    this.g.w += \u2603 / \u2603 * 0.1;
                    this.g.x += \u2603 / \u2603 * 0.1;
                } else {
                    this.f = false;
                }
            }
        }

        private boolean b(double d2, double d3, double d4, double d5) {
            \u2603 = (d2 - this.g.s) / d5;
            \u2603 = (d3 - this.g.t) / d5;
            \u2603 = (d4 - this.g.u) / d5;
            aug aug2 = this.g.aR();
            int \u26032 = 1;
            while ((double)\u26032 < d5) {
                if (!this.g.o.a((pk)this.g, aug2 = aug2.c(\u2603, \u2603, \u2603)).isEmpty()) {
                    return false;
                }
                ++\u26032;
            }
            return true;
        }
    }
}

