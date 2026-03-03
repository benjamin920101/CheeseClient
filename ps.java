/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.UUID;

public abstract class ps
extends pr {
    public int a_;
    protected int b_;
    private qp a;
    protected qq f;
    protected qo g;
    private qm b;
    protected sw h;
    protected final re i;
    protected final re bi;
    private pr c;
    private ta bk;
    private zx[] bl = new zx[5];
    protected float[] bj = new float[5];
    private boolean bm;
    private boolean bn;
    private boolean bo;
    private pk bp;
    private dn bq;

    public ps(adm adm2) {
        super(adm2);
        this.i = new re(adm2 == null || adm2.B == null ? null : adm2.B);
        this.bi = new re(adm2 == null || adm2.B == null ? null : adm2.B);
        this.a = new qp(this);
        this.f = new qq(this);
        this.g = new qo(this);
        this.b = new qm(this);
        this.h = this.b(adm2);
        this.bk = new ta(this);
        for (int i2 = 0; i2 < this.bj.length; ++i2) {
            this.bj[i2] = 0.085f;
        }
    }

    @Override
    protected void aX() {
        super.aX();
        this.by().b(vy.b).a(16.0);
    }

    protected sw b(adm adm2) {
        return new sv(this, adm2);
    }

    public qp p() {
        return this.a;
    }

    public qq q() {
        return this.f;
    }

    public qo r() {
        return this.g;
    }

    public sw s() {
        return this.h;
    }

    public ta t() {
        return this.bk;
    }

    public pr u() {
        return this.c;
    }

    public void d(pr pr2) {
        this.c = pr2;
    }

    public boolean a(Class<? extends pr> clazz) {
        return clazz != vr.class;
    }

    public void v() {
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(15, Byte.valueOf((byte)0));
    }

    public int w() {
        return 80;
    }

    public void x() {
        String string = this.z();
        if (string != null) {
            this.a(string, this.bB(), this.bC());
        }
    }

    @Override
    public void K() {
        super.K();
        this.o.B.a("mobBaseTick");
        if (this.ai() && this.V.nextInt(1000) < this.a_++) {
            this.a_ = -this.w();
            this.x();
        }
        this.o.B.b();
    }

    @Override
    protected int b(wn wn2) {
        if (this.b_ > 0) {
            int n2 = this.b_;
            zx[] \u26032 = this.as();
            for (\u2603 = 0; \u2603 < \u26032.length; ++\u2603) {
                if (\u26032[\u2603] == null || !(this.bj[\u2603] <= 1.0f)) continue;
                n2 += 1 + this.V.nextInt(3);
            }
            return n2;
        }
        return this.b_;
    }

    public void y() {
        if (this.o.D) {
            for (int i2 = 0; i2 < 20; ++i2) {
                double d2 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                \u2603 = 10.0;
                this.o.a(cy.a, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J - d2 * \u2603, this.t + (double)(this.V.nextFloat() * this.K) - \u2603 * \u2603, this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J - \u2603 * \u2603, d2, \u2603, \u2603, new int[0]);
            }
        } else {
            this.o.a((pk)this, (byte)20);
        }
    }

    @Override
    public void a(byte by) {
        if (by == 20) {
            this.y();
        } else {
            super.a(by);
        }
    }

    @Override
    public void t_() {
        super.t_();
        if (!this.o.D) {
            this.ca();
        }
    }

    @Override
    protected float h(float f2, float f3) {
        this.b.a();
        return f3;
    }

    protected String z() {
        return null;
    }

    protected zw A() {
        return null;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        zw zw2 = this.A();
        if (zw2 != null) {
            int n3 = this.V.nextInt(3);
            if (n2 > 0) {
                n3 += this.V.nextInt(n2 + 1);
            }
            for (\u2603 = 0; \u2603 < n3; ++\u2603) {
                this.a(zw2, 1);
            }
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        dn22.a("CanPickUpLoot", this.bY());
        dn22.a("PersistenceRequired", this.bn);
        du du2 = new du();
        for (int i2 = 0; i2 < this.bl.length; ++i2) {
            dn dn3 = new dn();
            if (this.bl[i2] != null) {
                this.bl[i2].b(dn3);
            }
            du2.a(dn3);
        }
        dn22.a("Equipment", du2);
        du \u26032 = new du();
        for (int i3 = 0; i3 < this.bj.length; ++i3) {
            \u26032.a(new dr(this.bj[i3]));
        }
        dn22.a("DropChances", \u26032);
        dn22.a("Leashed", this.bo);
        if (this.bp != null) {
            \u2603 = new dn();
            if (this.bp instanceof pr) {
                \u2603.a("UUIDMost", this.bp.aK().getMostSignificantBits());
                \u2603.a("UUIDLeast", this.bp.aK().getLeastSignificantBits());
            } else if (this.bp instanceof un) {
                cj cj2 = ((un)this.bp).n();
                \u2603.a("X", cj2.n());
                \u2603.a("Y", cj2.o());
                \u2603.a("Z", cj2.p());
            }
            dn22.a("Leash", \u2603);
        }
        if (this.ce()) {
            dn22.a("NoAI", this.ce());
        }
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        int n2;
        du du2;
        super.a(dn22);
        if (dn22.b("CanPickUpLoot", 1)) {
            this.j(dn22.n("CanPickUpLoot"));
        }
        this.bn = dn22.n("PersistenceRequired");
        if (dn22.b("Equipment", 9)) {
            du2 = dn22.c("Equipment", 10);
            for (n2 = 0; n2 < this.bl.length; ++n2) {
                this.bl[n2] = zx.a(du2.b(n2));
            }
        }
        if (dn22.b("DropChances", 9)) {
            du2 = dn22.c("DropChances", 5);
            for (n2 = 0; n2 < du2.c(); ++n2) {
                this.bj[n2] = du2.e(n2);
            }
        }
        this.bo = dn22.n("Leashed");
        if (this.bo && dn22.b("Leash", 10)) {
            this.bq = dn22.m("Leash");
        }
        this.k(dn22.n("NoAI"));
    }

    public void n(float f2) {
        this.ba = f2;
    }

    @Override
    public void k(float f2) {
        super.k(f2);
        this.n(f2);
    }

    @Override
    public void m() {
        super.m();
        this.o.B.a("looting");
        if (!this.o.D && this.bY() && !this.aP && this.o.Q().b("mobGriefing")) {
            List<uz> list = this.o.a(uz.class, this.aR().b(1.0, 0.0, 1.0));
            for (uz uz2 : list) {
                if (uz2.I || uz2.l() == null || uz2.s()) continue;
                this.a(uz2);
            }
        }
        this.o.B.b();
    }

    protected void a(uz uz2) {
        zx zx2 = uz2.l();
        int \u26032 = ps.c(zx2);
        if (\u26032 > -1) {
            boolean \u26035 = true;
            zx \u26033 = this.p(\u26032);
            if (\u26033 != null) {
                if (\u26032 == 0) {
                    if (zx2.b() instanceof aay && !(\u26033.b() instanceof aay)) {
                        \u26035 = true;
                    } else if (zx2.b() instanceof aay && \u26033.b() instanceof aay) {
                        Object object = (aay)zx2.b();
                        aay \u26034 = (aay)\u26033.b();
                        \u26035 = ((aay)object).g() == \u26034.g() ? zx2.i() > \u26033.i() || zx2.n() && !\u26033.n() : ((aay)object).g() > \u26034.g();
                    } else {
                        \u26035 = zx2.b() instanceof yt && \u26033.b() instanceof yt ? zx2.n() && !\u26033.n() : false;
                    }
                } else if (zx2.b() instanceof yj && !(\u26033.b() instanceof yj)) {
                    \u26035 = true;
                } else if (zx2.b() instanceof yj && \u26033.b() instanceof yj) {
                    object = (yj)zx2.b();
                    yj yj2 = (yj)\u26033.b();
                    \u26035 = ((yj)object).c == yj2.c ? zx2.i() > \u26033.i() || zx2.n() && !\u26033.n() : ((yj)object).c > yj2.c;
                } else {
                    \u26035 = false;
                }
            }
            if (\u26035 && this.a(zx2)) {
                if (\u26033 != null && this.V.nextFloat() - 0.1f < this.bj[\u26032]) {
                    this.a(\u26033, 0.0f);
                }
                if (zx2.b() == zy.i && uz2.n() != null && (object = this.o.a(uz2.n())) != null) {
                    ((wn)object).b(mr.x);
                }
                this.c(\u26032, zx2);
                this.bj[\u26032] = 2.0f;
                this.bn = true;
                this.a((pk)uz2, 1);
                uz2.J();
            }
        }
    }

    protected boolean a(zx zx2) {
        return true;
    }

    protected boolean C() {
        return true;
    }

    protected void D() {
        if (this.bn) {
            this.aQ = 0;
            return;
        }
        wn wn2 = this.o.a((pk)this, -1.0);
        if (wn2 != null) {
            double d2 = wn2.s - this.s;
            \u2603 = wn2.t - this.t;
            \u2603 = wn2.u - this.u;
            \u2603 = d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603;
            if (this.C() && \u2603 > 16384.0) {
                this.J();
            }
            if (this.aQ > 600 && this.V.nextInt(800) == 0 && \u2603 > 1024.0 && this.C()) {
                this.J();
            } else if (\u2603 < 1024.0) {
                this.aQ = 0;
            }
        }
    }

    @Override
    protected final void bK() {
        ++this.aQ;
        this.o.B.a("checkDespawn");
        this.D();
        this.o.B.b();
        this.o.B.a("sensing");
        this.bk.a();
        this.o.B.b();
        this.o.B.a("targetSelector");
        this.bi.a();
        this.o.B.b();
        this.o.B.a("goalSelector");
        this.i.a();
        this.o.B.b();
        this.o.B.a("navigation");
        this.h.k();
        this.o.B.b();
        this.o.B.a("mob tick");
        this.E();
        this.o.B.b();
        this.o.B.a("controls");
        this.o.B.a("move");
        this.f.c();
        this.o.B.c("look");
        this.a.a();
        this.o.B.c("jump");
        this.g.b();
        this.o.B.b();
        this.o.B.b();
    }

    protected void E() {
    }

    public int bQ() {
        return 40;
    }

    public void a(pk pk2, float f2, float f3) {
        double \u26032;
        double d2 = pk2.s - this.s;
        \u2603 = pk2.u - this.u;
        if (pk2 instanceof pr) {
            pr pr2 = (pr)pk2;
            \u26032 = pr2.t + (double)pr2.aS() - (this.t + (double)this.aS());
        } else {
            \u26032 = (pk2.aR().b + pk2.aR().e) / 2.0 - (this.t + (double)this.aS());
        }
        double d3 = ns.a(d2 * d2 + \u2603 * \u2603);
        float \u26033 = (float)(ns.b(\u2603, d2) * 180.0 / 3.1415927410125732) - 90.0f;
        float \u26034 = (float)(-(ns.b(\u26032, d3) * 180.0 / 3.1415927410125732));
        this.z = this.b(this.z, \u26034, f3);
        this.y = this.b(this.y, \u26033, f2);
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

    public boolean bR() {
        return true;
    }

    public boolean bS() {
        return this.o.a(this.aR(), (pk)this) && this.o.a((pk)this, this.aR()).isEmpty() && !this.o.d(this.aR());
    }

    public float bT() {
        return 1.0f;
    }

    public int bV() {
        return 4;
    }

    @Override
    public int aE() {
        if (this.u() == null) {
            return 3;
        }
        int n2 = (int)(this.bn() - this.bu() * 0.33f);
        if ((n2 -= (3 - this.o.aa().a()) * 4) < 0) {
            n2 = 0;
        }
        return n2 + 3;
    }

    @Override
    public zx bA() {
        return this.bl[0];
    }

    @Override
    public zx p(int n2) {
        return this.bl[n2];
    }

    @Override
    public zx q(int n2) {
        return this.bl[n2 + 1];
    }

    @Override
    public void c(int n2, zx zx2) {
        this.bl[n2] = zx2;
    }

    @Override
    public zx[] as() {
        return this.bl;
    }

    @Override
    protected void a(boolean bl2, int n2) {
        for (\u2603 = 0; \u2603 < this.as().length; ++\u2603) {
            zx zx2 = this.p(\u2603);
            boolean bl3 = \u2603 = this.bj[\u2603] > 1.0f;
            if (zx2 == null || !bl2 && !\u2603 || !(this.V.nextFloat() - (float)n2 * 0.01f < this.bj[\u2603])) continue;
            if (!\u2603 && zx2.e()) {
                int n3 = Math.max(zx2.j() - 25, 1);
                \u2603 = zx2.j() - this.V.nextInt(this.V.nextInt(n3) + 1);
                if (\u2603 > n3) {
                    \u2603 = n3;
                }
                if (\u2603 < 1) {
                    \u2603 = 1;
                }
                zx2.b(\u2603);
            }
            this.a(zx2, 0.0f);
        }
    }

    protected void a(ok ok2) {
        if (this.V.nextFloat() < 0.15f * ok2.c()) {
            int n2 = this.V.nextInt(2);
            float f2 = \u2603 = this.o.aa() == oj.d ? 0.1f : 0.25f;
            if (this.V.nextFloat() < 0.095f) {
                ++n2;
            }
            if (this.V.nextFloat() < 0.095f) {
                ++n2;
            }
            if (this.V.nextFloat() < 0.095f) {
                ++n2;
            }
            for (\u2603 = 3; \u2603 >= 0; --\u2603) {
                zx zx2 = this.q(\u2603);
                if (\u2603 < 3 && this.V.nextFloat() < \u2603) break;
                if (zx2 != null || (\u2603 = ps.a(\u2603 + 1, n2)) == null) continue;
                this.c(\u2603 + 1, new zx(\u2603));
            }
        }
    }

    public static int c(zx zx2) {
        if (zx2.b() == zw.a(afi.aU) || zx2.b() == zy.bX) {
            return 4;
        }
        if (zx2.b() instanceof yj) {
            switch (((yj)zx2.b()).b) {
                case 3: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 1: {
                    return 3;
                }
                case 0: {
                    return 4;
                }
            }
        }
        return 0;
    }

    public static zw a(int n2, int n3) {
        switch (n2) {
            case 4: {
                if (n3 == 0) {
                    return zy.Q;
                }
                if (n3 == 1) {
                    return zy.ag;
                }
                if (n3 == 2) {
                    return zy.U;
                }
                if (n3 == 3) {
                    return zy.Y;
                }
                if (n3 == 4) {
                    return zy.ac;
                }
            }
            case 3: {
                if (n3 == 0) {
                    return zy.R;
                }
                if (n3 == 1) {
                    return zy.ah;
                }
                if (n3 == 2) {
                    return zy.V;
                }
                if (n3 == 3) {
                    return zy.Z;
                }
                if (n3 == 4) {
                    return zy.ad;
                }
            }
            case 2: {
                if (n3 == 0) {
                    return zy.S;
                }
                if (n3 == 1) {
                    return zy.ai;
                }
                if (n3 == 2) {
                    return zy.W;
                }
                if (n3 == 3) {
                    return zy.aa;
                }
                if (n3 == 4) {
                    return zy.ae;
                }
            }
            case 1: {
                if (n3 == 0) {
                    return zy.T;
                }
                if (n3 == 1) {
                    return zy.aj;
                }
                if (n3 == 2) {
                    return zy.X;
                }
                if (n3 == 3) {
                    return zy.ab;
                }
                if (n3 != 4) break;
                return zy.af;
            }
        }
        return null;
    }

    protected void b(ok ok2) {
        float f2 = ok2.c();
        if (this.bA() != null && this.V.nextFloat() < 0.25f * f2) {
            ack.a(this.V, this.bA(), (int)(5.0f + f2 * (float)this.V.nextInt(18)));
        }
        for (int i2 = 0; i2 < 4; ++i2) {
            zx zx2 = this.q(i2);
            if (zx2 == null || !(this.V.nextFloat() < 0.5f * f2)) continue;
            ack.a(this.V, zx2, (int)(5.0f + f2 * (float)this.V.nextInt(18)));
        }
    }

    public pu a(ok ok2, pu pu2) {
        this.a(vy.b).b(new qd("Random spawn bonus", this.V.nextGaussian() * 0.05, 1));
        return pu2;
    }

    public boolean bW() {
        return false;
    }

    public void bX() {
        this.bn = true;
    }

    public void a(int n2, float f2) {
        this.bj[n2] = f2;
    }

    public boolean bY() {
        return this.bm;
    }

    public void j(boolean bl2) {
        this.bm = bl2;
    }

    public boolean bZ() {
        return this.bn;
    }

    @Override
    public final boolean e(wn wn2) {
        if (this.cc() && this.cd() == wn2) {
            this.a(true, !wn2.bA.d);
            return true;
        }
        zx zx2 = wn2.bi.h();
        if (zx2 != null && zx2.b() == zy.cn && this.cb()) {
            if (this instanceof qa && ((qa)this).cl()) {
                if (((qa)this).e((pr)wn2)) {
                    this.a((pk)wn2, true);
                    --zx2.b;
                    return true;
                }
            } else {
                this.a((pk)wn2, true);
                --zx2.b;
                return true;
            }
        }
        if (this.a(wn2)) {
            return true;
        }
        return super.e(wn2);
    }

    protected boolean a(wn wn2) {
        return false;
    }

    protected void ca() {
        if (this.bq != null) {
            this.n();
        }
        if (!this.bo) {
            return;
        }
        if (!this.ai()) {
            this.a(true, true);
        }
        if (this.bp == null || this.bp.I) {
            this.a(true, true);
            return;
        }
    }

    public void a(boolean bl2, boolean bl3) {
        if (this.bo) {
            this.bo = false;
            this.bp = null;
            if (!this.o.D && bl3) {
                this.a(zy.cn, 1);
            }
            if (!this.o.D && bl2 && this.o instanceof le) {
                ((le)this.o).s().a(this, new hl(1, this, null));
            }
        }
    }

    public boolean cb() {
        return !this.cc() && !(this instanceof vq);
    }

    public boolean cc() {
        return this.bo;
    }

    public pk cd() {
        return this.bp;
    }

    public void a(pk pk2, boolean bl2) {
        this.bo = true;
        this.bp = pk2;
        if (!this.o.D && bl2 && this.o instanceof le) {
            ((le)this.o).s().a(this, new hl(1, this, this.bp));
        }
    }

    private void n() {
        if (this.bo && this.bq != null) {
            if (this.bq.b("UUIDMost", 4) && this.bq.b("UUIDLeast", 4)) {
                UUID uUID = new UUID(this.bq.g("UUIDMost"), this.bq.g("UUIDLeast"));
                List<pr> \u26032 = this.o.a(pr.class, this.aR().b(10.0, 10.0, 10.0));
                for (pr pr2 : \u26032) {
                    if (!pr2.aK().equals(uUID)) continue;
                    this.bp = pr2;
                    break;
                }
            } else if (this.bq.b("X", 99) && this.bq.b("Y", 99) && this.bq.b("Z", 99)) {
                cj cj2 = new cj(this.bq.f("X"), this.bq.f("Y"), this.bq.f("Z"));
                up \u26033 = up.b(this.o, cj2);
                if (\u26033 == null) {
                    \u26033 = up.a(this.o, cj2);
                }
                this.bp = \u26033;
            } else {
                this.a(false, true);
            }
        }
        this.bq = null;
    }

    @Override
    public boolean d(int n22, zx zx22) {
        zx zx22;
        if (n22 == 99) {
            int n3 = 0;
        } else {
            int n22;
            n3 = n22 - 100 + 1;
            if (n3 < 0 || n3 >= this.bl.length) {
                return false;
            }
        }
        if (zx22 == null || ps.c(zx22) == n3 || n3 == 4 && zx22.b() instanceof yo) {
            this.c(n3, zx22);
            return true;
        }
        return false;
    }

    @Override
    public boolean bM() {
        return super.bM() && !this.ce();
    }

    public void k(boolean bl2) {
        this.ac.b(15, bl2 ? (byte)1 : 0);
    }

    public boolean ce() {
        return this.ac.a(15) != 0;
    }

    public static enum a {
        a,
        b,
        c;

    }
}

