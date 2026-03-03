/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class vz
extends vv {
    private b a;

    public vz(adm adm2) {
        super(adm2);
        this.a(0.4f, 0.3f);
        this.i.a(1, new ra(this));
        this.a = new b(this);
        this.i.a(3, this.a);
        this.i.a(4, new rl(this, wn.class, 1.0, false));
        this.i.a(5, new a(this));
        this.bi.a(1, new sm((py)this, true, new Class[0]));
        this.bi.a(2, new sp<wn>((py)this, wn.class, true));
    }

    @Override
    public double am() {
        return 0.2;
    }

    @Override
    public float aS() {
        return 0.1f;
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(8.0);
        this.a(vy.d).a(0.25);
        this.a(vy.e).a(1.0);
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected String z() {
        return "mob.silverfish.say";
    }

    @Override
    protected String bo() {
        return "mob.silverfish.hit";
    }

    @Override
    protected String bp() {
        return "mob.silverfish.kill";
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (ow2 instanceof ox || ow2 == ow.l) {
            this.a.f();
        }
        return super.a(ow2, f2);
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.silverfish.step", 0.15f, 1.0f);
    }

    @Override
    protected zw A() {
        return null;
    }

    @Override
    public void t_() {
        this.aI = this.y;
        super.t_();
    }

    @Override
    public float a(cj cj2) {
        if (this.o.p(cj2.b()).c() == afi.b) {
            return 10.0f;
        }
        return super.a(cj2);
    }

    @Override
    protected boolean n_() {
        return true;
    }

    @Override
    public boolean bR() {
        if (super.bR()) {
            wn wn2 = this.o.a((pk)this, 5.0);
            return wn2 == null;
        }
        return false;
    }

    @Override
    public pw bz() {
        return pw.c;
    }

    static class a
    extends rz {
        private final vz a;
        private cq b;
        private boolean c;

        public a(vz vz2) {
            super(vz2, 1.0, 10);
            this.a = vz2;
            this.a(1);
        }

        @Override
        public boolean a() {
            if (this.a.u() != null) {
                return false;
            }
            if (!this.a.s().m()) {
                return false;
            }
            Random random = this.a.bc();
            if (random.nextInt(10) == 0) {
                this.b = cq.a(random);
                cj cj2 = new cj(this.a.s, this.a.t + 0.5, this.a.u).a(this.b);
                alz \u26032 = this.a.o.p(cj2);
                if (ahz.d(\u26032)) {
                    this.c = true;
                    return true;
                }
            }
            this.c = false;
            return super.a();
        }

        @Override
        public boolean b() {
            if (this.c) {
                return false;
            }
            return super.b();
        }

        @Override
        public void c() {
            if (!this.c) {
                super.c();
                return;
            }
            adm adm2 = this.a.o;
            cj \u26032 = new cj(this.a.s, this.a.t + 0.5, this.a.u).a(this.b);
            alz \u26033 = adm2.p(\u26032);
            if (ahz.d(\u26033)) {
                adm2.a(\u26032, afi.be.Q().a(ahz.a, ahz.a.a(\u26033)), 3);
                this.a.y();
                this.a.J();
            }
        }
    }

    static class b
    extends rd {
        private vz a;
        private int b;

        public b(vz vz2) {
            this.a = vz2;
        }

        public void f() {
            if (this.b == 0) {
                this.b = 20;
            }
        }

        @Override
        public boolean a() {
            return this.b > 0;
        }

        @Override
        public void e() {
            --this.b;
            if (this.b <= 0) {
                adm adm2 = this.a.o;
                Random \u26032 = this.a.bc();
                cj \u26033 = new cj(this.a);
                int \u26034 = 0;
                block0: while (\u26034 <= 5 && \u26034 >= -5) {
                    int n2 = 0;
                    while (n2 <= 10 && n2 >= -10) {
                        \u26036 = 0;
                        while (\u26036 <= 10 && \u26036 >= -10) {
                            cj cj2 = \u26033.a(n2, \u26034, \u26036);
                            alz \u26035 = adm2.p(cj2);
                            if (\u26035.c() == afi.be) {
                                if (adm2.Q().b("mobGriefing")) {
                                    adm2.b(cj2, true);
                                } else {
                                    adm2.a(cj2, \u26035.b(ahz.a).d(), 3);
                                }
                                if (\u26032.nextBoolean()) break block0;
                            }
                            int \u26036 = \u26036 <= 0 ? 1 - \u26036 : 0 - \u26036;
                        }
                        n2 = n2 <= 0 ? 1 - n2 : 0 - n2;
                    }
                    \u26034 = \u26034 <= 0 ? 1 - \u26034 : 0 - \u26034;
                }
            }
        }
    }
}

