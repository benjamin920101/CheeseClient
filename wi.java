/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class wi
extends ph
implements acy,
wh {
    private int bn;
    private boolean bo;
    private boolean bp;
    tf bm;
    private wn bq;
    private ada br;
    private int bs;
    private boolean bt;
    private boolean bu;
    private int bv;
    private String bw;
    private int bx;
    private int by;
    private boolean bz;
    private boolean bA;
    private oq bB = new oq("Items", false, 8);
    private static final f[][][][] bC = new f[][][][]{{{{new a(zy.O, new g(18, 22)), new a(zy.bS, new g(15, 19)), new a(zy.bR, new g(15, 19)), new e(zy.P, new g(-4, -2))}, {new a(zw.a(afi.aU), new g(8, 13)), new e(zy.ca, new g(-3, -2))}, {new a(zw.a(afi.bk), new g(7, 12)), new e(zy.e, new g(-5, -7))}, {new e(zy.bc, new g(-6, -10)), new e(zy.aZ, new g(1, 1))}}, {{new a(zy.F, new g(15, 20)), new a(zy.h, new g(16, 24)), new d(zy.aU, new g(6, 6), zy.aV, new g(6, 6))}, {new c(zy.aR, new g(7, 8))}}, {{new a(zw.a(afi.L), new g(16, 22)), new e(zy.be, new g(3, 4))}, {new e(new zx(zw.a(afi.L), 1, 0), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 1), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 2), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 3), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 4), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 5), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 6), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 7), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 8), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 9), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 10), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 11), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 12), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 13), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 14), new g(1, 2)), new e(new zx(zw.a(afi.L), 1, 15), new g(1, 2))}}, {{new a(zy.F, new g(15, 20)), new e(zy.g, new g(-12, -8))}, {new e(zy.f, new g(2, 3)), new d(zw.a(afi.n), new g(10, 10), zy.ak, new g(6, 10))}}}, {{{new a(zy.aK, new g(24, 36)), new b()}, {new a(zy.aL, new g(8, 10)), new e(zy.aQ, new g(10, 12)), new e(zw.a(afi.X), new g(3, 4))}, {new a(zy.bN, new g(2, 2)), new e(zy.aS, new g(10, 12)), new e(zw.a(afi.w), new g(-5, -3))}, {new b()}, {new b()}, {new e(zy.co, new g(20, 22))}}}, {{{new a(zy.bt, new g(36, 40)), new a(zy.k, new g(8, 10))}, {new e(zy.aC, new g(-4, -1)), new e(new zx(zy.aW, 1, zd.l.b()), new g(-2, -1))}, {new e(zy.bH, new g(7, 11)), new e(zw.a(afi.aX), new g(-3, -1))}, {new e(zy.bK, new g(3, 11))}}}, {{{new a(zy.h, new g(16, 24)), new e(zy.Y, new g(4, 6))}, {new a(zy.j, new g(7, 9)), new e(zy.Z, new g(10, 14))}, {new a(zy.i, new g(3, 4)), new c(zy.ad, new g(16, 19))}, {new e(zy.X, new g(5, 7)), new e(zy.W, new g(9, 11)), new e(zy.U, new g(5, 7)), new e(zy.V, new g(11, 15))}}, {{new a(zy.h, new g(16, 24)), new e(zy.c, new g(6, 8))}, {new a(zy.j, new g(7, 9)), new c(zy.l, new g(9, 10))}, {new a(zy.i, new g(3, 4)), new c(zy.u, new g(12, 15)), new c(zy.x, new g(9, 12))}}, {{new a(zy.h, new g(16, 24)), new c(zy.a, new g(5, 7))}, {new a(zy.j, new g(7, 9)), new c(zy.b, new g(9, 11))}, {new a(zy.i, new g(3, 4)), new c(zy.w, new g(12, 15))}}}, {{{new a(zy.al, new g(14, 18)), new a(zy.bk, new g(14, 18))}, {new a(zy.h, new g(16, 24)), new e(zy.am, new g(-7, -5)), new e(zy.bl, new g(-8, -6))}}, {{new a(zy.aF, new g(9, 12)), new e(zy.S, new g(2, 4))}, {new c(zy.R, new g(7, 12))}, {new e(zy.aA, new g(8, 10))}}}};

    public wi(adm adm2) {
        this(adm2, 0);
    }

    public wi(adm adm2, int n2) {
        super(adm2);
        this.r(n2);
        this.a(0.6f, 1.8f);
        ((sv)this.s()).b(true);
        ((sv)this.s()).a(true);
        this.i.a(0, new ra(this));
        this.i.a(1, new qs<we>(this, we.class, 8.0f, 0.6, 0.6));
        this.i.a(1, new si(this));
        this.i.a(1, new rj(this));
        this.i.a(2, new rm(this));
        this.i.a(3, new sb(this));
        this.i.a(4, new ru(this, true));
        this.i.a(5, new rp(this, 0.6));
        this.i.a(6, new rk(this));
        this.i.a(7, new sg(this));
        this.i.a(9, new rg(this, wn.class, 3.0f, 1.0f));
        this.i.a(9, new sj(this));
        this.i.a(9, new rz(this, 0.6));
        this.i.a(10, new ri(this, ps.class, 8.0f));
        this.j(true);
    }

    private void cv() {
        if (this.bA) {
            return;
        }
        this.bA = true;
        if (this.j_()) {
            this.i.a(8, new rw(this, 0.32));
        } else if (this.cl() == 0) {
            this.i.a(6, new rf(this, 0.6));
        }
    }

    @Override
    protected void n() {
        if (this.cl() == 0) {
            this.i.a(8, new rf(this, 0.6));
        }
        super.n();
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.d).a(0.5);
    }

    @Override
    protected void E() {
        if (--this.bn <= 0) {
            cj cj2 = new cj(this);
            this.o.ae().a(cj2);
            this.bn = 70 + this.V.nextInt(50);
            this.bm = this.o.ae().a(cj2, 32);
            if (this.bm == null) {
                this.cj();
            } else {
                Object object = this.bm.a();
                this.a((cj)object, (int)((float)this.bm.b() * 1.0f));
                if (this.bz) {
                    this.bz = false;
                    this.bm.b(5);
                }
            }
        }
        if (!this.co() && this.bs > 0) {
            --this.bs;
            if (this.bs <= 0) {
                if (this.bt) {
                    for (Object object : this.br) {
                        if (!((acz)object).h()) continue;
                        ((acz)object).a(this.V.nextInt(6) + this.V.nextInt(6) + 2);
                    }
                    this.cw();
                    this.bt = false;
                    if (this.bm != null && this.bw != null) {
                        this.o.a((pk)this, (byte)14);
                        this.bm.a(this.bw, 1);
                    }
                }
                this.c(new pf(pe.l.H, 200, 0));
            }
        }
        super.E();
    }

    @Override
    public boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        boolean bl2 = \u2603 = zx2 != null && zx2.b() == zy.bJ;
        if (!\u2603 && this.ai() && !this.co() && !this.j_()) {
            if (!(this.o.D || this.br != null && this.br.size() <= 0)) {
                this.a_(wn2);
                wn2.a(this);
            }
            wn2.b(na.F);
            return true;
        }
        return super.a(wn2);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Integer.valueOf(0));
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        dn22.a("Profession", this.cl());
        dn22.a("Riches", this.bv);
        dn22.a("Career", this.bx);
        dn22.a("CareerLevel", this.by);
        dn22.a("Willing", this.bu);
        if (this.br != null) {
            dn22.a("Offers", this.br.a());
        }
        du du2 = new du();
        for (int i2 = 0; i2 < this.bB.o_(); ++i2) {
            zx zx2 = this.bB.a(i2);
            if (zx2 == null) continue;
            du2.a(zx2.b(new dn()));
        }
        dn22.a("Inventory", du2);
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        eb \u26032;
        super.a(dn22);
        this.r(dn22.f("Profession"));
        this.bv = dn22.f("Riches");
        this.bx = dn22.f("Career");
        this.by = dn22.f("CareerLevel");
        this.bu = dn22.n("Willing");
        if (dn22.b("Offers", 10)) {
            \u26032 = dn22.m("Offers");
            this.br = new ada((dn)\u26032);
        }
        \u26032 = dn22.c("Inventory", 10);
        for (int i2 = 0; i2 < ((du)\u26032).c(); ++i2) {
            zx zx2 = zx.a(((du)\u26032).b(i2));
            if (zx2 == null) continue;
            this.bB.a(zx2);
        }
        this.j(true);
        this.cv();
    }

    @Override
    protected boolean C() {
        return false;
    }

    @Override
    protected String z() {
        if (this.co()) {
            return "mob.villager.haggle";
        }
        return "mob.villager.idle";
    }

    @Override
    protected String bo() {
        return "mob.villager.hit";
    }

    @Override
    protected String bp() {
        return "mob.villager.death";
    }

    public void r(int n2) {
        this.ac.b(16, n2);
    }

    public int cl() {
        return Math.max(this.ac.c(16) % 5, 0);
    }

    public boolean cm() {
        return this.bo;
    }

    public void l(boolean bl2) {
        this.bo = bl2;
    }

    public void m(boolean bl2) {
        this.bp = bl2;
    }

    public boolean cn() {
        return this.bp;
    }

    @Override
    public void b(pr pr2) {
        super.b(pr2);
        if (this.bm != null && pr2 != null) {
            this.bm.a(pr2);
            if (pr2 instanceof wn) {
                int n2 = -1;
                if (this.j_()) {
                    n2 = -3;
                }
                this.bm.a(pr2.e_(), n2);
                if (this.ai()) {
                    this.o.a((pk)this, (byte)13);
                }
            }
        }
    }

    @Override
    public void a(ow ow22) {
        ow ow22;
        if (this.bm != null) {
            pk pk2 = ow22.j();
            if (pk2 != null) {
                if (pk2 instanceof wn) {
                    this.bm.a(pk2.e_(), -2);
                } else if (pk2 instanceof vq) {
                    this.bm.h();
                }
            } else {
                wn wn2 = this.o.a((pk)this, 16.0);
                if (wn2 != null) {
                    this.bm.h();
                }
            }
        }
        super.a(ow22);
    }

    @Override
    public void a_(wn wn2) {
        this.bq = wn2;
    }

    @Override
    public wn v_() {
        return this.bq;
    }

    public boolean co() {
        return this.bq != null;
    }

    public boolean n(boolean bl2) {
        if (!this.bu && bl2 && this.cr()) {
            bl3 = false;
            for (int i2 = 0; i2 < this.bB.o_(); ++i2) {
                boolean bl3;
                zx zx2 = this.bB.a(i2);
                if (zx2 != null) {
                    if (zx2.b() == zy.P && zx2.b >= 3) {
                        bl3 = true;
                        this.bB.a(i2, 3);
                    } else if ((zx2.b() == zy.bS || zx2.b() == zy.bR) && zx2.b >= 12) {
                        bl3 = true;
                        this.bB.a(i2, 12);
                    }
                }
                if (!bl3) continue;
                this.o.a((pk)this, (byte)18);
                this.bu = true;
                break;
            }
        }
        return this.bu;
    }

    public void o(boolean bl2) {
        this.bu = bl2;
    }

    @Override
    public void a(acz acz2) {
        acz2.g();
        this.a_ = -this.w();
        this.a("mob.villager.yes", this.bB(), this.bC());
        int n2 = 3 + this.V.nextInt(4);
        if (acz2.e() == 1 || this.V.nextInt(5) == 0) {
            this.bs = 40;
            this.bt = true;
            this.bu = true;
            this.bw = this.bq != null ? this.bq.e_() : null;
            n2 += 5;
        }
        if (acz2.a().b() == zy.bO) {
            this.bv += acz2.a().b;
        }
        if (acz2.j()) {
            this.o.d(new pp(this.o, this.s, this.t + 0.5, this.u, n2));
        }
    }

    @Override
    public void a_(zx zx2) {
        if (!this.o.D && this.a_ > -this.w() + 20) {
            this.a_ = -this.w();
            if (zx2 != null) {
                this.a("mob.villager.yes", this.bB(), this.bC());
            } else {
                this.a("mob.villager.no", this.bB(), this.bC());
            }
        }
    }

    @Override
    public ada b_(wn wn2) {
        if (this.br == null) {
            this.cw();
        }
        return this.br;
    }

    private void cw() {
        f[][][] fArray = bC[this.cl()];
        if (this.bx == 0 || this.by == 0) {
            this.bx = this.V.nextInt(fArray.length) + 1;
            this.by = 1;
        } else {
            ++this.by;
        }
        if (this.br == null) {
            this.br = new ada();
        }
        int \u26032 = this.bx - 1;
        int \u26033 = this.by - 1;
        f[][] \u26034 = fArray[\u26032];
        if (\u26033 >= 0 && \u26033 < \u26034.length) {
            for (f f2 : \u2603 = \u26034[\u26033]) {
                f2.a(this.br, this.V);
            }
        }
    }

    @Override
    public void a(ada ada2) {
    }

    @Override
    public eu f_() {
        String string = this.aM();
        if (string != null && string.length() > 0) {
            fa fa2 = new fa(string);
            fa2.b().a(this.aQ());
            fa2.b().a(this.aK().toString());
            return fa2;
        }
        if (this.br == null) {
            this.cw();
        }
        String string2 = null;
        switch (this.cl()) {
            case 0: {
                if (this.bx == 1) {
                    string2 = "farmer";
                    break;
                }
                if (this.bx == 2) {
                    string2 = "fisherman";
                    break;
                }
                if (this.bx == 3) {
                    string2 = "shepherd";
                    break;
                }
                if (this.bx != 4) break;
                string2 = "fletcher";
                break;
            }
            case 1: {
                string2 = "librarian";
                break;
            }
            case 2: {
                string2 = "cleric";
                break;
            }
            case 3: {
                if (this.bx == 1) {
                    string2 = "armor";
                    break;
                }
                if (this.bx == 2) {
                    string2 = "weapon";
                    break;
                }
                if (this.bx != 3) break;
                string2 = "tool";
                break;
            }
            case 4: {
                if (this.bx == 1) {
                    string2 = "butcher";
                    break;
                }
                if (this.bx != 2) break;
                string2 = "leather";
            }
        }
        if (string2 != null) {
            fb fb2 = new fb("entity.Villager." + string2, new Object[0]);
            fb2.b().a(this.aQ());
            fb2.b().a(this.aK().toString());
            return fb2;
        }
        return super.f_();
    }

    @Override
    public float aS() {
        float f2 = 1.62f;
        if (this.j_()) {
            f2 = (float)((double)f2 - 0.81);
        }
        return f2;
    }

    @Override
    public void a(byte by) {
        if (by == 12) {
            this.a(cy.I);
        } else if (by == 13) {
            this.a(cy.u);
        } else if (by == 14) {
            this.a(cy.v);
        } else {
            super.a(by);
        }
    }

    private void a(cy cy2) {
        for (int i2 = 0; i2 < 5; ++i2) {
            double d2 = this.V.nextGaussian() * 0.02;
            \u2603 = this.V.nextGaussian() * 0.02;
            \u2603 = this.V.nextGaussian() * 0.02;
            this.o.a(cy2, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 1.0 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, d2, \u2603, \u2603, new int[0]);
        }
    }

    @Override
    public pu a(ok ok2, pu pu2) {
        pu2 = super.a(ok2, pu2);
        this.r(this.o.s.nextInt(5));
        this.cv();
        return pu2;
    }

    public void cp() {
        this.bz = true;
    }

    public wi b(ph ph2) {
        wi wi2 = new wi(this.o);
        wi2.a(this.o.E(new cj(wi2)), null);
        return wi2;
    }

    @Override
    public boolean cb() {
        return false;
    }

    @Override
    public void a(uv uv2) {
        if (this.o.D || this.I) {
            return;
        }
        wd wd2 = new wd(this.o);
        wd2.b(this.s, this.t, this.u, this.y, this.z);
        wd2.a(this.o.E(new cj(wd2)), null);
        wd2.k(this.ce());
        if (this.l_()) {
            wd2.a(this.aM());
            wd2.g(this.aN());
        }
        this.o.d(wd2);
        this.J();
    }

    public oq cq() {
        return this.bB;
    }

    @Override
    protected void a(uz uz2) {
        zx zx2 = uz2.l();
        zw \u26032 = zx2.b();
        if (this.a(\u26032)) {
            \u2603 = this.bB.a(zx2);
            if (\u2603 == null) {
                uz2.J();
            } else {
                zx2.b = \u2603.b;
            }
        }
    }

    private boolean a(zw zw2) {
        return zw2 == zy.P || zw2 == zy.bS || zw2 == zy.bR || zw2 == zy.O || zw2 == zy.N;
    }

    public boolean cr() {
        return this.s(1);
    }

    public boolean cs() {
        return this.s(2);
    }

    public boolean ct() {
        boolean bl2;
        boolean bl3 = bl2 = this.cl() == 0;
        if (bl2) {
            return !this.s(5);
        }
        return !this.s(1);
    }

    private boolean s(int n2) {
        boolean bl2 = this.cl() == 0;
        for (int i2 = 0; i2 < this.bB.o_(); ++i2) {
            zx zx2 = this.bB.a(i2);
            if (zx2 == null) continue;
            if (zx2.b() == zy.P && zx2.b >= 3 * n2 || zx2.b() == zy.bS && zx2.b >= 12 * n2 || zx2.b() == zy.bR && zx2.b >= 12 * n2) {
                return true;
            }
            if (!bl2 || zx2.b() != zy.O || zx2.b < 9 * n2) continue;
            return true;
        }
        return false;
    }

    public boolean cu() {
        for (int i2 = 0; i2 < this.bB.o_(); ++i2) {
            zx zx2 = this.bB.a(i2);
            if (zx2 == null || zx2.b() != zy.N && zx2.b() != zy.bS && zx2.b() != zy.bR) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean d(int n2, zx zx2) {
        if (super.d(n2, zx2)) {
            return true;
        }
        int n3 = n2 - 300;
        if (n3 >= 0 && n3 < this.bB.o_()) {
            this.bB.a(n3, zx2);
            return true;
        }
        return false;
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }

    static class d
    implements f {
        public zx a;
        public g b;
        public zx c;
        public g d;

        public d(zw zw2, g g2, zw zw3, g g3) {
            this.a = new zx(zw2);
            this.b = g2;
            this.c = new zx(zw3);
            this.d = g3;
        }

        @Override
        public void a(ada ada2, Random random) {
            int n2 = 1;
            if (this.b != null) {
                n2 = this.b.a(random);
            }
            \u2603 = 1;
            if (this.d != null) {
                \u2603 = this.d.a(random);
            }
            ada2.add(new acz(new zx(this.a.b(), n2, this.a.i()), new zx(zy.bO), new zx(this.c.b(), \u2603, this.c.i())));
        }
    }

    static class b
    implements f {
        @Override
        public void a(ada ada2, Random random) {
            aci aci2 = aci.b[random.nextInt(aci.b.length)];
            int \u26032 = ns.a(random, aci2.e(), aci2.b());
            zx \u26033 = zy.cd.a(new acl(aci2, \u26032));
            int \u26034 = 2 + random.nextInt(5 + \u26032 * 10) + 3 * \u26032;
            if (\u26034 > 64) {
                \u26034 = 64;
            }
            ada2.add(new acz(new zx(zy.aL), new zx(zy.bO, \u26034), \u26033));
        }
    }

    static class c
    implements f {
        public zx a;
        public g b;

        public c(zw zw2, g g2) {
            this.a = new zx(zw2);
            this.b = g2;
        }

        @Override
        public void a(ada ada2, Random random) {
            int n2 = 1;
            if (this.b != null) {
                n2 = this.b.a(random);
            }
            zx \u26032 = new zx(zy.bO, n2, 0);
            zx \u26033 = new zx(this.a.b(), 1, this.a.i());
            \u26033 = ack.a(random, \u26033, 5 + random.nextInt(15));
            ada2.add(new acz(\u26032, \u26033));
        }
    }

    static class e
    implements f {
        public zx a;
        public g b;

        public e(zw zw2, g g2) {
            this.a = new zx(zw2);
            this.b = g2;
        }

        public e(zx zx2, g g2) {
            this.a = zx2;
            this.b = g2;
        }

        @Override
        public void a(ada ada2, Random random) {
            zx \u26033;
            zx \u26032;
            int n2 = 1;
            if (this.b != null) {
                n2 = this.b.a(random);
            }
            if (n2 < 0) {
                \u26032 = new zx(zy.bO, 1, 0);
                \u26033 = new zx(this.a.b(), -n2, this.a.i());
            } else {
                \u26032 = new zx(zy.bO, n2, 0);
                \u26033 = new zx(this.a.b(), 1, this.a.i());
            }
            ada2.add(new acz(\u26032, \u26033));
        }
    }

    static class a
    implements f {
        public zw a;
        public g b;

        public a(zw zw2, g g2) {
            this.a = zw2;
            this.b = g2;
        }

        @Override
        public void a(ada ada2, Random random) {
            int n2 = 1;
            if (this.b != null) {
                n2 = this.b.a(random);
            }
            ada2.add(new acz(new zx(this.a, n2, 0), zy.bO));
        }
    }

    static interface f {
        public void a(ada var1, Random var2);
    }

    static class g
    extends nz<Integer, Integer> {
        public g(int n2, int n3) {
            super(n2, n3);
        }

        public int a(Random random) {
            if ((Integer)this.a() >= (Integer)this.b()) {
                return (Integer)this.a();
            }
            return (Integer)this.a() + random.nextInt((Integer)this.b() - (Integer)this.a() + 1);
        }
    }
}

