/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class wc
extends vv {
    public wc(adm adm2) {
        super(adm2);
        this.a(1.4f, 0.9f);
        this.i.a(1, new ra(this));
        this.i.a(3, new rh(this, 0.4f));
        this.i.a(4, new a(this, wn.class));
        this.i.a(4, new a(this, ty.class));
        this.i.a(5, new rz(this, 0.8));
        this.i.a(6, new ri(this, wn.class, 8.0f));
        this.i.a(6, new ry(this));
        this.bi.a(1, new sm((py)this, false, new Class[0]));
        this.bi.a(2, new c<wn>(this, wn.class));
        this.bi.a(3, new c<ty>(this, ty.class));
    }

    @Override
    public double an() {
        return this.K * 0.5f;
    }

    @Override
    protected sw b(adm adm2) {
        return new sx(this, adm2);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, new Byte(0));
    }

    @Override
    public void t_() {
        super.t_();
        if (!this.o.D) {
            this.a(this.D);
        }
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(16.0);
        this.a(vy.d).a(0.3f);
    }

    @Override
    protected String z() {
        return "mob.spider.say";
    }

    @Override
    protected String bo() {
        return "mob.spider.say";
    }

    @Override
    protected String bp() {
        return "mob.spider.death";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.spider.step", 0.15f, 1.0f);
    }

    @Override
    protected zw A() {
        return zy.F;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        super.b(bl2, n2);
        if (bl2 && (this.V.nextInt(3) == 0 || this.V.nextInt(1 + n2) > 0)) {
            this.a(zy.bB, 1);
        }
    }

    @Override
    public boolean k_() {
        return this.n();
    }

    @Override
    public void aA() {
    }

    @Override
    public pw bz() {
        return pw.c;
    }

    @Override
    public boolean d(pf pf2) {
        if (pf2.a() == pe.u.H) {
            return false;
        }
        return super.d(pf2);
    }

    public boolean n() {
        return (this.ac.a(16) & 1) != 0;
    }

    public void a(boolean bl2) {
        byte by = this.ac.a(16);
        by = bl2 ? (byte)(by | 1) : (byte)(by & 0xFFFFFFFE);
        this.ac.b(16, by);
    }

    @Override
    public pu a(ok ok2, pu pu22) {
        pu pu22;
        pu22 = super.a(ok2, pu22);
        if (this.o.s.nextInt(100) == 0) {
            wa wa2 = new wa(this.o);
            wa2.b(this.s, this.t, this.u, this.y, 0.0f);
            wa2.a(ok2, null);
            this.o.d(wa2);
            wa2.a((pk)this);
        }
        if (pu22 == null) {
            pu22 = new b();
            if (this.o.aa() == oj.d && this.o.s.nextFloat() < 0.1f * ok2.c()) {
                ((b)pu22).a(this.o.s);
            }
        }
        if (pu22 instanceof b && (\u2603 = ((b)pu22).a) > 0 && pe.a[\u2603] != null) {
            this.c(new pf(\u2603, Integer.MAX_VALUE));
        }
        return pu22;
    }

    @Override
    public float aS() {
        return 0.65f;
    }

    static class c<T extends pr>
    extends sp {
        public c(wc wc2, Class<T> clazz) {
            super((py)wc2, clazz, true);
        }

        @Override
        public boolean a() {
            float f2 = this.e.c(1.0f);
            if (f2 >= 0.5f) {
                return false;
            }
            return super.a();
        }
    }

    static class a
    extends rl {
        public a(wc wc2, Class<? extends pk> clazz) {
            super(wc2, clazz, 1.0, true);
        }

        @Override
        public boolean b() {
            float f2 = this.b.c(1.0f);
            if (f2 >= 0.5f && this.b.bc().nextInt(100) == 0) {
                this.b.d((pr)null);
                return false;
            }
            return super.b();
        }

        @Override
        protected double a(pr pr2) {
            return 4.0f + pr2.J;
        }
    }

    public static class b
    implements pu {
        public int a;

        public void a(Random random) {
            int n2 = random.nextInt(5);
            if (n2 <= 1) {
                this.a = pe.c.H;
            } else if (n2 <= 2) {
                this.a = pe.g.H;
            } else if (n2 <= 3) {
                this.a = pe.l.H;
            } else if (n2 <= 4) {
                this.a = pe.p.H;
            }
        }
    }
}

