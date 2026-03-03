/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class vo
extends vv {
    private static final UUID a = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final qd b = new qd(a, "Attacking speed boost", 0.15f, 0).a(false);
    private static final Set<afh> c = Sets.newIdentityHashSet();
    private boolean bm;

    public vo(adm adm2) {
        super(adm2);
        this.a(0.6f, 2.9f);
        this.S = 1.0f;
        this.i.a(0, new ra(this));
        this.i.a(2, new rl(this, 1.0, false));
        this.i.a(7, new rz(this, 1.0));
        this.i.a(8, new ri(this, wn.class, 8.0f));
        this.i.a(8, new ry(this));
        this.i.a(10, new a(this));
        this.i.a(11, new c(this));
        this.bi.a(1, new sm((py)this, false, new Class[0]));
        this.bi.a(2, new b(this));
        this.bi.a(3, new sp<vp>(this, vp.class, 10, true, false, new Predicate<vp>(){

            public boolean a(vp vp2) {
                return vp2.n();
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((vp)object);
            }
        }));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(40.0);
        this.a(vy.d).a(0.3f);
        this.a(vy.e).a(7.0);
        this.a(vy.b).a(64.0);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, new Short(0));
        this.ac.a(17, new Byte(0));
        this.ac.a(18, new Byte(0));
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        alz alz2 = this.cm();
        dn2.a("carried", (short)afh.a(alz2.c()));
        dn2.a("carriedData", (short)alz2.c().c(alz2));
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        alz alz2 = dn2.b("carried", 8) ? afh.b(dn2.j("carried")).a(dn2.e("carriedData") & 0xFFFF) : afh.c(dn2.e("carried")).a(dn2.e("carriedData") & 0xFFFF);
        this.a(alz2);
    }

    private boolean c(wn wn2) {
        zx zx2 = wn2.bi.b[3];
        if (zx2 != null && zx2.b() == zw.a(afi.aU)) {
            return false;
        }
        aui \u26032 = wn2.d(1.0f).a();
        aui \u26033 = new aui(this.s - wn2.s, this.aR().b + (double)(this.K / 2.0f) - (wn2.t + (double)wn2.aS()), this.u - wn2.u);
        double \u26034 = \u26033.b();
        double \u26035 = \u26032.b(\u26033 = \u26033.a());
        if (\u26035 > 1.0 - 0.025 / \u26034) {
            return wn2.t(this);
        }
        return false;
    }

    @Override
    public float aS() {
        return 2.55f;
    }

    @Override
    public void m() {
        if (this.o.D) {
            for (int i2 = 0; i2 < 2; ++i2) {
                this.o.a(cy.y, this.s + (this.V.nextDouble() - 0.5) * (double)this.J, this.t + this.V.nextDouble() * (double)this.K - 0.25, this.u + (this.V.nextDouble() - 0.5) * (double)this.J, (this.V.nextDouble() - 0.5) * 2.0, -this.V.nextDouble(), (this.V.nextDouble() - 0.5) * 2.0, new int[0]);
            }
        }
        this.aY = false;
        super.m();
    }

    @Override
    protected void E() {
        float f2;
        if (this.U()) {
            this.a(ow.f, 1.0f);
        }
        if (this.co() && !this.bm && this.V.nextInt(100) == 0) {
            this.a(false);
        }
        if (this.o.w() && (f2 = this.c(1.0f)) > 0.5f && this.o.i(new cj(this)) && this.V.nextFloat() * 30.0f < (f2 - 0.4f) * 2.0f) {
            this.d((pr)null);
            this.a(false);
            this.bm = false;
            this.n();
        }
        super.E();
    }

    protected boolean n() {
        double d2 = this.s + (this.V.nextDouble() - 0.5) * 64.0;
        \u2603 = this.t + (double)(this.V.nextInt(64) - 32);
        \u2603 = this.u + (this.V.nextDouble() - 0.5) * 64.0;
        return this.k(d2, \u2603, \u2603);
    }

    protected boolean b(pk pk2) {
        aui aui2 = new aui(this.s - pk2.s, this.aR().b + (double)(this.K / 2.0f) - pk2.t + (double)pk2.aS(), this.u - pk2.u);
        aui2 = aui2.a();
        double \u26032 = 16.0;
        double \u26033 = this.s + (this.V.nextDouble() - 0.5) * 8.0 - aui2.a * \u26032;
        double \u26034 = this.t + (double)(this.V.nextInt(16) - 8) - aui2.b * \u26032;
        double \u26035 = this.u + (this.V.nextDouble() - 0.5) * 8.0 - aui2.c * \u26032;
        return this.k(\u26033, \u26034, \u26035);
    }

    protected boolean k(double d2, double d3, double d4) {
        int n2;
        \u2603 = this.s;
        \u2603 = this.t;
        \u2603 = this.u;
        this.s = d2;
        this.t = d3;
        this.u = d4;
        boolean bl2 = false;
        cj \u26032 = new cj(this.s, this.t, this.u);
        if (this.o.e(\u26032)) {
            n2 = 0;
            while (n2 == 0 && \u26032.o() > 0) {
                cj cj2 = \u26032.b();
                afh \u26033 = this.o.p(cj2).c();
                if (\u26033.t().c()) {
                    n2 = 1;
                    continue;
                }
                this.t -= 1.0;
                \u26032 = cj2;
            }
            if (n2 != 0) {
                super.a(this.s, this.t, this.u);
                if (this.o.a((pk)this, this.aR()).isEmpty() && !this.o.d(this.aR())) {
                    bl2 = true;
                }
            }
        }
        if (bl2) {
            n2 = 128;
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                double d5 = (double)\u2603 / ((double)n2 - 1.0);
                float \u26034 = (this.V.nextFloat() - 0.5f) * 0.2f;
                float \u26035 = (this.V.nextFloat() - 0.5f) * 0.2f;
                float \u26036 = (this.V.nextFloat() - 0.5f) * 0.2f;
                \u2603 = \u2603 + (this.s - \u2603) * d5 + (this.V.nextDouble() - 0.5) * (double)this.J * 2.0;
                \u2603 = \u2603 + (this.t - \u2603) * d5 + this.V.nextDouble() * (double)this.K;
                \u2603 = \u2603 + (this.u - \u2603) * d5 + (this.V.nextDouble() - 0.5) * (double)this.J * 2.0;
                this.o.a(cy.y, \u2603, \u2603, \u2603, (double)\u26034, (double)\u26035, (double)\u26036, new int[0]);
            }
            this.o.a(\u2603, \u2603, \u2603, "mob.endermen.portal", 1.0f, 1.0f);
            this.a("mob.endermen.portal", 1.0f, 1.0f);
            return true;
        }
        this.b(\u2603, \u2603, \u2603);
        return false;
    }

    @Override
    protected String z() {
        return this.co() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    @Override
    protected String bo() {
        return "mob.endermen.hit";
    }

    @Override
    protected String bp() {
        return "mob.endermen.death";
    }

    @Override
    protected zw A() {
        return zy.bu;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        zw zw2 = this.A();
        if (zw2 != null) {
            int n3 = this.V.nextInt(2 + n2);
            for (\u2603 = 0; \u2603 < n3; ++\u2603) {
                this.a(zw2, 1);
            }
        }
    }

    public void a(alz alz2) {
        this.ac.b(16, (short)(afh.f(alz2) & 0xFFFF));
    }

    public alz cm() {
        return afh.d(this.ac.b(16) & 0xFFFF);
    }

    @Override
    public boolean a(ow ow22, float f2) {
        ow ow22;
        if (this.b(ow22)) {
            return false;
        }
        if (ow22.j() == null || !(ow22.j() instanceof vp)) {
            if (!this.o.D) {
                this.a(true);
            }
            if (ow22 instanceof ox && ow22.j() instanceof wn) {
                if (ow22.j() instanceof lf && ((lf)ow22.j()).c.d()) {
                    this.a(false);
                } else {
                    this.bm = true;
                }
            }
            if (ow22 instanceof oy) {
                this.bm = false;
                for (int i2 = 0; i2 < 64; ++i2) {
                    if (!this.n()) continue;
                    return true;
                }
                return false;
            }
        }
        boolean \u26032 = super.a(ow22, f2);
        if (ow22.e() && this.V.nextInt(10) != 0) {
            this.n();
        }
        return \u26032;
    }

    public boolean co() {
        return this.ac.a(18) > 0;
    }

    public void a(boolean bl2) {
        this.ac.b(18, (byte)(bl2 ? (char)'\u0001' : '\u0000'));
    }

    static {
        c.add(afi.c);
        c.add(afi.d);
        c.add(afi.m);
        c.add(afi.n);
        c.add(afi.N);
        c.add(afi.O);
        c.add(afi.P);
        c.add(afi.Q);
        c.add(afi.W);
        c.add(afi.aK);
        c.add(afi.aL);
        c.add(afi.aU);
        c.add(afi.bk);
        c.add(afi.bw);
    }

    static class c
    extends rd {
        private vo a;

        public c(vo vo2) {
            this.a = vo2;
        }

        @Override
        public boolean a() {
            if (!this.a.o.Q().b("mobGriefing")) {
                return false;
            }
            if (this.a.cm().c().t() != arm.a) {
                return false;
            }
            return this.a.bc().nextInt(20) == 0;
        }

        @Override
        public void e() {
            Random random = this.a.bc();
            adm \u26032 = this.a.o;
            int \u26033 = ns.c(this.a.s - 2.0 + random.nextDouble() * 4.0);
            int \u26034 = ns.c(this.a.t + random.nextDouble() * 3.0);
            int \u26035 = ns.c(this.a.u - 2.0 + random.nextDouble() * 4.0);
            cj \u26036 = new cj(\u26033, \u26034, \u26035);
            alz \u26037 = \u26032.p(\u26036);
            afh \u26038 = \u26037.c();
            if (c.contains(\u26038)) {
                this.a.a(\u26037);
                \u26032.a(\u26036, afi.a.Q());
            }
        }
    }

    static class a
    extends rd {
        private vo a;

        public a(vo vo2) {
            this.a = vo2;
        }

        @Override
        public boolean a() {
            if (!this.a.o.Q().b("mobGriefing")) {
                return false;
            }
            if (this.a.cm().c().t() == arm.a) {
                return false;
            }
            return this.a.bc().nextInt(2000) == 0;
        }

        @Override
        public void e() {
            Random random = this.a.bc();
            adm \u26032 = this.a.o;
            int \u26033 = ns.c(this.a.s - 1.0 + random.nextDouble() * 2.0);
            int \u26034 = ns.c(this.a.t + random.nextDouble() * 2.0);
            int \u26035 = ns.c(this.a.u - 1.0 + random.nextDouble() * 2.0);
            cj \u26036 = new cj(\u26033, \u26034, \u26035);
            afh \u26037 = \u26032.p(\u26036).c();
            afh \u26038 = \u26032.p(\u26036.b()).c();
            if (this.a(\u26032, \u26036, this.a.cm().c(), \u26037, \u26038)) {
                \u26032.a(\u26036, this.a.cm(), 3);
                this.a.a(afi.a.Q());
            }
        }

        private boolean a(adm adm2, cj cj2, afh afh2, afh afh3, afh afh4) {
            if (!afh2.d(adm2, cj2)) {
                return false;
            }
            if (afh3.t() != arm.a) {
                return false;
            }
            if (afh4.t() == arm.a) {
                return false;
            }
            return afh4.d();
        }
    }

    static class b
    extends sp {
        private wn g;
        private int h;
        private int i;
        private vo j;

        public b(vo vo2) {
            super((py)vo2, wn.class, true);
            this.j = vo2;
        }

        @Override
        public boolean a() {
            double d2 = this.f();
            Predicate \u26032 = this.e.o.a(wn.class, this.e.aR().b(d2, 4.0, d2), this.c);
            Collections.sort(\u26032, this.b);
            if (\u26032.isEmpty()) {
                return false;
            }
            this.g = (wn)\u26032.get(0);
            return true;
        }

        @Override
        public void c() {
            this.h = 5;
            this.i = 0;
        }

        @Override
        public void d() {
            this.g = null;
            this.j.a(false);
            qc qc2 = this.j.a(vy.d);
            qc2.c(b);
            super.d();
        }

        @Override
        public boolean b() {
            if (this.g != null) {
                if (!this.j.c(this.g)) {
                    return false;
                }
                this.j.bm = true;
                this.j.a(this.g, 10.0f, 10.0f);
                return true;
            }
            return super.b();
        }

        @Override
        public void e() {
            if (this.g != null) {
                if (--this.h <= 0) {
                    this.d = this.g;
                    this.g = null;
                    super.c();
                    this.j.a("mob.endermen.stare", 1.0f, 1.0f);
                    this.j.a(true);
                    qc qc2 = this.j.a(vy.d);
                    qc2.b(b);
                }
            } else {
                if (this.d != null) {
                    if (this.d instanceof wn && this.j.c((wn)this.d)) {
                        if (this.d.h(this.j) < 16.0) {
                            this.j.n();
                        }
                        this.i = 0;
                    } else if (this.d.h(this.j) > 256.0 && this.i++ >= 30 && this.j.b((pk)this.d)) {
                        this.i = 0;
                    }
                }
                super.e();
            }
        }
    }
}

