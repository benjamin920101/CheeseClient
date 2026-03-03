/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public abstract class wn
extends pr {
    public wm bi = new wm(this);
    private yd a = new yd();
    public xi bj;
    public xi bk;
    protected xg bl = new xg();
    protected int bm;
    public float bn;
    public float bo;
    public int bp;
    public double bq;
    public double br;
    public double bs;
    public double bt;
    public double bu;
    public double bv;
    protected boolean bw;
    public cj bx;
    private int b;
    public float by;
    public float bZ;
    public float bz;
    private cj c;
    private boolean d;
    private cj e;
    public wl bA = new wl();
    public int bB;
    public int bC;
    public float bD;
    private int f;
    private zx g;
    private int h;
    protected float bE = 0.1f;
    protected float bF = 0.02f;
    private int i;
    private final GameProfile bH;
    private boolean bI = false;
    public ur bG;

    public wn(adm adm2, GameProfile gameProfile) {
        super(adm2);
        this.aq = wn.a(gameProfile);
        this.bH = gameProfile;
        this.bk = this.bj = new xy(this.bi, !adm2.D, this);
        cj cj2 = adm2.M();
        this.b((double)cj2.n() + 0.5, cj2.o() + 1, (double)cj2.p() + 0.5, 0.0f, 0.0f);
        this.aV = 180.0f;
        this.X = 20;
    }

    @Override
    protected void aX() {
        super.aX();
        this.by().b(vy.e).a(1.0);
        this.a(vy.d).a(0.1f);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)0));
        this.ac.a(17, Float.valueOf(0.0f));
        this.ac.a(18, Integer.valueOf(0));
        this.ac.a(10, Byte.valueOf((byte)0));
    }

    public zx bQ() {
        return this.g;
    }

    public int bR() {
        return this.h;
    }

    public boolean bS() {
        return this.g != null;
    }

    public int bT() {
        if (this.bS()) {
            return this.g.l() - this.h;
        }
        return 0;
    }

    public void bU() {
        if (this.g != null) {
            this.g.b(this.o, this, this.h);
        }
        this.bV();
    }

    public void bV() {
        this.g = null;
        this.h = 0;
        if (!this.o.D) {
            this.f(false);
        }
    }

    public boolean bW() {
        return this.bS() && this.g.b().e(this.g) == aba.d;
    }

    @Override
    public void t_() {
        this.T = this.v();
        if (this.v()) {
            this.C = false;
        }
        if (this.g != null) {
            zx zx2 = this.bi.h();
            if (zx2 == this.g) {
                if (this.h <= 25 && this.h % 4 == 0) {
                    this.b(zx2, 5);
                }
                if (--this.h == 0 && !this.o.D) {
                    this.s();
                }
            } else {
                this.bV();
            }
        }
        if (this.bp > 0) {
            --this.bp;
        }
        if (this.bJ()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }
            if (!this.o.D) {
                if (!this.p()) {
                    this.a(true, true, false);
                } else if (this.o.w()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }
        super.t_();
        if (!this.o.D && this.bk != null && !this.bk.a(this)) {
            this.n();
            this.bk = this.bj;
        }
        if (this.at() && this.bA.a) {
            this.N();
        }
        this.bq = this.bt;
        this.br = this.bu;
        this.bs = this.bv;
        double d2 = this.s - this.bt;
        \u2603 = this.t - this.bu;
        \u2603 = this.u - this.bv;
        \u2603 = 10.0;
        if (d2 > \u2603) {
            this.bq = this.bt = this.s;
        }
        if (\u2603 > \u2603) {
            this.bs = this.bv = this.u;
        }
        if (\u2603 > \u2603) {
            this.br = this.bu = this.t;
        }
        if (d2 < -\u2603) {
            this.bq = this.bt = this.s;
        }
        if (\u2603 < -\u2603) {
            this.bs = this.bv = this.u;
        }
        if (\u2603 < -\u2603) {
            this.br = this.bu = this.t;
        }
        this.bt += d2 * 0.25;
        this.bv += \u2603 * 0.25;
        this.bu += \u2603 * 0.25;
        if (this.m == null) {
            this.e = null;
        }
        if (!this.o.D) {
            this.bl.a(this);
            this.b(na.g);
            if (this.ai()) {
                this.b(na.h);
            }
        }
        int \u26032 = 29999999;
        \u2603 = ns.a(this.s, -2.9999999E7, 2.9999999E7);
        \u2603 = ns.a(this.u, -2.9999999E7, 2.9999999E7);
        if (\u2603 != this.s || \u2603 != this.u) {
            this.b(\u2603, this.t, \u2603);
        }
    }

    @Override
    public int L() {
        return this.bA.a ? 0 : 80;
    }

    @Override
    protected String P() {
        return "game.player.swim";
    }

    @Override
    protected String aa() {
        return "game.player.swim.splash";
    }

    @Override
    public int aq() {
        return 10;
    }

    @Override
    public void a(String string, float f2, float f3) {
        this.o.a(this, string, f2, f3);
    }

    protected void b(zx zx2, int n2) {
        if (zx2.m() == aba.c) {
            this.a("random.drink", 0.5f, this.o.s.nextFloat() * 0.1f + 0.9f);
        }
        if (zx2.m() == aba.b) {
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                aui aui2 = new aui(((double)this.V.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
                aui2 = aui2.a(-this.z * (float)Math.PI / 180.0f);
                aui2 = aui2.b(-this.y * (float)Math.PI / 180.0f);
                double \u26032 = (double)(-this.V.nextFloat()) * 0.6 - 0.3;
                \u2603 = new aui(((double)this.V.nextFloat() - 0.5) * 0.3, \u26032, 0.6);
                \u2603 = \u2603.a(-this.z * (float)Math.PI / 180.0f);
                \u2603 = \u2603.b(-this.y * (float)Math.PI / 180.0f);
                \u2603 = \u2603.b(this.s, this.t + (double)this.aS(), this.u);
                if (zx2.f()) {
                    this.o.a(cy.K, \u2603.a, \u2603.b, \u2603.c, aui2.a, aui2.b + 0.05, aui2.c, zw.b(zx2.b()), zx2.i());
                    continue;
                }
                this.o.a(cy.K, \u2603.a, \u2603.b, \u2603.c, aui2.a, aui2.b + 0.05, aui2.c, zw.b(zx2.b()));
            }
            this.a("random.eat", 0.5f + 0.5f * (float)this.V.nextInt(2), (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
        }
    }

    protected void s() {
        if (this.g != null) {
            this.b(this.g, 16);
            int n2 = this.g.b;
            zx \u26032 = this.g.b(this.o, this);
            if (\u26032 != this.g || \u26032 != null && \u26032.b != n2) {
                this.bi.a[this.bi.c] = \u26032;
                if (\u26032.b == 0) {
                    this.bi.a[this.bi.c] = null;
                }
            }
            this.bV();
        }
    }

    @Override
    public void a(byte by) {
        if (by == 9) {
            this.s();
        } else if (by == 23) {
            this.bI = false;
        } else if (by == 22) {
            this.bI = true;
        } else {
            super.a(by);
        }
    }

    @Override
    protected boolean bD() {
        return this.bn() <= 0.0f || this.bJ();
    }

    protected void n() {
        this.bk = this.bj;
    }

    @Override
    public void ak() {
        if (!this.o.D && this.av()) {
            this.a((pk)null);
            this.c(false);
            return;
        }
        double d2 = this.s;
        \u2603 = this.t;
        \u2603 = this.u;
        float \u26032 = this.y;
        float \u26033 = this.z;
        super.ak();
        this.bn = this.bo;
        this.bo = 0.0f;
        this.l(this.s - d2, this.t - \u2603, this.u - \u2603);
        if (this.m instanceof tt) {
            this.z = \u26033;
            this.y = \u26032;
            this.aI = ((tt)this.m).aI;
        }
    }

    @Override
    public void I() {
        this.a(0.6f, 1.8f);
        super.I();
        this.i(this.bu());
        this.ax = 0;
    }

    @Override
    protected void bK() {
        super.bK();
        this.bx();
        this.aK = this.y;
    }

    @Override
    public void m() {
        if (this.bm > 0) {
            --this.bm;
        }
        if (this.o.aa() == oj.a && this.o.Q().b("naturalRegeneration")) {
            if (this.bn() < this.bu() && this.W % 20 == 0) {
                this.h(1.0f);
            }
            if (this.bl.c() && this.W % 10 == 0) {
                this.bl.a(this.bl.a() + 1);
            }
        }
        this.bi.k();
        this.bn = this.bo;
        super.m();
        qc qc2 = this.a(vy.d);
        if (!this.o.D) {
            qc2.a(this.bA.b());
        }
        this.aM = this.bF;
        if (this.aw()) {
            this.aM = (float)((double)this.aM + (double)this.bF * 0.3);
        }
        this.k((float)qc2.e());
        float \u26032 = ns.a(this.v * this.v + this.x * this.x);
        float \u26033 = (float)(Math.atan(-this.w * (double)0.2f) * 15.0);
        if (\u26032 > 0.1f) {
            \u26032 = 0.1f;
        }
        if (!this.C || this.bn() <= 0.0f) {
            \u26032 = 0.0f;
        }
        if (this.C || this.bn() <= 0.0f) {
            \u26033 = 0.0f;
        }
        this.bo += (\u26032 - this.bo) * 0.4f;
        this.aF += (\u26033 - this.aF) * 0.8f;
        if (this.bn() > 0.0f && !this.v()) {
            aug aug2 = null;
            aug2 = this.m != null && !this.m.I ? this.aR().a(this.m.aR()).b(1.0, 0.0, 1.0) : this.aR().b(1.0, 0.5, 1.0);
            List<pk> \u26034 = this.o.b(this, aug2);
            for (int i2 = 0; i2 < \u26034.size(); ++i2) {
                pk pk2 = \u26034.get(i2);
                if (pk2.I) continue;
                this.d(pk2);
            }
        }
    }

    private void d(pk pk2) {
        pk2.d(this);
    }

    public int bX() {
        return this.ac.c(18);
    }

    public void r(int n2) {
        this.ac.b(18, n2);
    }

    public void s(int n2) {
        \u2603 = this.bX();
        this.ac.b(18, \u2603 + n2);
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        this.a(0.2f, 0.2f);
        this.b(this.s, this.t, this.u);
        this.w = 0.1f;
        if (this.e_().equals("Notch")) {
            this.a(new zx(zy.e, 1), true, false);
        }
        if (!this.o.Q().b("keepInventory")) {
            this.bi.n();
        }
        if (ow2 != null) {
            this.v = -ns.b((this.aw + this.y) * (float)Math.PI / 180.0f) * 0.1f;
            this.x = -ns.a((this.aw + this.y) * (float)Math.PI / 180.0f) * 0.1f;
        } else {
            this.x = 0.0;
            this.v = 0.0;
        }
        this.b(na.y);
        this.a(na.h);
    }

    @Override
    protected String bo() {
        return "game.player.hurt";
    }

    @Override
    protected String bp() {
        return "game.player.die";
    }

    @Override
    public void b(pk pk2, int n2) {
        this.s(n2);
        Collection<auk> collection = this.cp().a(auu.f);
        if (pk2 instanceof wn) {
            this.b(na.B);
            collection.addAll(this.cp().a(auu.e));
            collection.addAll(this.e(pk2));
        } else {
            this.b(na.z);
        }
        for (auk auk2 : collection) {
            aum aum2 = this.cp().c(this.e_(), auk2);
            aum2.a();
        }
    }

    private Collection<auk> e(pk pk2) {
        aul aul2;
        aul aul3 = this.cp().h(this.e_());
        if (aul3 != null && (\u2603 = aul3.l().b()) >= 0 && \u2603 < auu.i.length) {
            for (auk auk2 : this.cp().a(auu.i[\u2603])) {
                aum aum2 = this.cp().c(pk2.e_(), auk2);
                aum2.a();
            }
        }
        if ((aul2 = this.cp().h(pk2.e_())) != null && (\u2603 = aul2.l().b()) >= 0 && \u2603 < auu.h.length) {
            return this.cp().a(auu.h[\u2603]);
        }
        return Lists.newArrayList();
    }

    public uz a(boolean bl2) {
        return this.a(this.bi.a(this.bi.c, bl2 && this.bi.h() != null ? this.bi.h().b : 1), false, true);
    }

    public uz a(zx zx2, boolean bl2) {
        return this.a(zx2, false, false);
    }

    public uz a(zx zx2, boolean bl2, boolean bl32) {
        boolean bl32;
        if (zx2 == null) {
            return null;
        }
        if (zx2.b == 0) {
            return null;
        }
        double d2 = this.t - (double)0.3f + (double)this.aS();
        uz \u26032 = new uz(this.o, this.s, d2, this.u, zx2);
        \u26032.a(40);
        if (bl32) {
            \u26032.c(this.e_());
        }
        if (bl2) {
            float f2 = this.V.nextFloat() * 0.5f;
            \u2603 = this.V.nextFloat() * (float)Math.PI * 2.0f;
            \u26032.v = -ns.a(\u2603) * f2;
            \u26032.x = ns.b(\u2603) * f2;
            \u26032.w = 0.2f;
        } else {
            float f3 = 0.3f;
            \u26032.v = -ns.a(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI) * f3;
            \u26032.x = ns.b(this.y / 180.0f * (float)Math.PI) * ns.b(this.z / 180.0f * (float)Math.PI) * f3;
            \u26032.w = -ns.a(this.z / 180.0f * (float)Math.PI) * f3 + 0.1f;
            \u2603 = this.V.nextFloat() * (float)Math.PI * 2.0f;
            f3 = 0.02f * this.V.nextFloat();
            \u26032.v += Math.cos(\u2603) * (double)f3;
            \u26032.w += (double)((this.V.nextFloat() - this.V.nextFloat()) * 0.1f);
            \u26032.x += Math.sin(\u2603) * (double)f3;
        }
        this.a(\u26032);
        if (bl32) {
            this.b(na.v);
        }
        return \u26032;
    }

    protected void a(uz uz2) {
        this.o.d(uz2);
    }

    public float a(afh afh2) {
        float f2 = this.bi.a(afh2);
        if (f2 > 1.0f) {
            int n2 = ack.c(this);
            zx \u26032 = this.bi.h();
            if (n2 > 0 && \u26032 != null) {
                f2 += (float)(n2 * n2 + 1);
            }
        }
        if (this.a(pe.e)) {
            f2 *= 1.0f + (float)(this.b(pe.e).c() + 1) * 0.2f;
        }
        if (this.a(pe.f)) {
            float \u26033 = 1.0f;
            switch (this.b(pe.f).c()) {
                case 0: {
                    \u26033 = 0.3f;
                    break;
                }
                case 1: {
                    \u26033 = 0.09f;
                    break;
                }
                case 2: {
                    \u26033 = 0.0027f;
                    break;
                }
                default: {
                    \u26033 = 8.1E-4f;
                }
            }
            f2 *= \u26033;
        }
        if (this.a(arm.h) && !ack.j(this)) {
            f2 /= 5.0f;
        }
        if (!this.C) {
            f2 /= 5.0f;
        }
        return f2;
    }

    public boolean b(afh afh2) {
        return this.bi.b(afh2);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.aq = wn.a(this.bH);
        du du2 = dn2.c("Inventory", 10);
        this.bi.b(du2);
        this.bi.c = dn2.f("SelectedItemSlot");
        this.bw = dn2.n("Sleeping");
        this.b = dn2.e("SleepTimer");
        this.bD = dn2.h("XpP");
        this.bB = dn2.f("XpLevel");
        this.bC = dn2.f("XpTotal");
        this.f = dn2.f("XpSeed");
        if (this.f == 0) {
            this.f = this.V.nextInt();
        }
        this.r(dn2.f("Score"));
        if (this.bw) {
            this.bx = new cj(this);
            this.a(true, true, false);
        }
        if (dn2.b("SpawnX", 99) && dn2.b("SpawnY", 99) && dn2.b("SpawnZ", 99)) {
            this.c = new cj(dn2.f("SpawnX"), dn2.f("SpawnY"), dn2.f("SpawnZ"));
            this.d = dn2.n("SpawnForced");
        }
        this.bl.a(dn2);
        this.bA.b(dn2);
        if (dn2.b("EnderItems", 9)) {
            \u2603 = dn2.c("EnderItems", 10);
            this.a.a(\u2603);
        }
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Inventory", this.bi.a(new du()));
        dn2.a("SelectedItemSlot", this.bi.c);
        dn2.a("Sleeping", this.bw);
        dn2.a("SleepTimer", (short)this.b);
        dn2.a("XpP", this.bD);
        dn2.a("XpLevel", this.bB);
        dn2.a("XpTotal", this.bC);
        dn2.a("XpSeed", this.f);
        dn2.a("Score", this.bX());
        if (this.c != null) {
            dn2.a("SpawnX", this.c.n());
            dn2.a("SpawnY", this.c.o());
            dn2.a("SpawnZ", this.c.p());
            dn2.a("SpawnForced", this.d);
        }
        this.bl.b(dn2);
        this.bA.a(dn2);
        dn2.a("EnderItems", this.a.h());
        zx zx2 = this.bi.h();
        if (zx2 != null && zx2.b() != null) {
            dn2.a("SelectedItem", zx2.b(new dn()));
        }
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (this.bA.a && !ow2.g()) {
            return false;
        }
        this.aQ = 0;
        if (this.bn() <= 0.0f) {
            return false;
        }
        if (this.bJ() && !this.o.D) {
            this.a(true, true, false);
        }
        if (ow2.r()) {
            if (this.o.aa() == oj.a) {
                f2 = 0.0f;
            }
            if (this.o.aa() == oj.b) {
                f2 = f2 / 2.0f + 1.0f;
            }
            if (this.o.aa() == oj.d) {
                f2 = f2 * 3.0f / 2.0f;
            }
        }
        if (f2 == 0.0f) {
            return false;
        }
        pk pk2 = ow2.j();
        if (pk2 instanceof wq && ((wq)pk2).c != null) {
            pk2 = ((wq)pk2).c;
        }
        return super.a(ow2, f2);
    }

    public boolean a(wn wn2) {
        auq auq2 = this.bO();
        \u2603 = wn2.bO();
        if (auq2 == null) {
            return true;
        }
        if (!auq2.a(\u2603)) {
            return true;
        }
        return auq2.g();
    }

    @Override
    protected void j(float f2) {
        this.bi.a(f2);
    }

    @Override
    public int br() {
        return this.bi.m();
    }

    public float bY() {
        int n2 = 0;
        for (zx zx2 : this.bi.b) {
            if (zx2 == null) continue;
            ++n2;
        }
        return (float)n2 / (float)this.bi.b.length;
    }

    @Override
    protected void d(ow ow2, float f2) {
        if (this.b(ow2)) {
            return;
        }
        if (!ow2.e() && this.bW() && f2 > 0.0f) {
            f2 = (1.0f + f2) * 0.5f;
        }
        f2 = this.b(ow2, f2);
        \u2603 = f2 = this.c(ow2, f2);
        f2 = Math.max(f2 - this.bN(), 0.0f);
        this.m(this.bN() - (\u2603 - f2));
        if (f2 == 0.0f) {
            return;
        }
        this.a(ow2.f());
        \u2603 = this.bn();
        this.i(this.bn() - f2);
        this.bs().a(ow2, \u2603, f2);
        if (f2 < 3.4028235E37f) {
            this.a(na.x, Math.round(f2 * 10.0f));
        }
    }

    public void a(aln aln2) {
    }

    public void a(adc adc2) {
    }

    public void a(acy acy2) {
    }

    public void a(og og2) {
    }

    public void a(tp tp2, og og2) {
    }

    public void a(ol ol2) {
    }

    public void a(zx zx2) {
    }

    public boolean u(pk pk2) {
        if (this.v()) {
            if (pk2 instanceof og) {
                this.a((og)((Object)pk2));
            }
            return false;
        }
        zx zx2 = this.bZ();
        zx zx3 = \u2603 = zx2 != null ? zx2.k() : null;
        if (pk2.e(this)) {
            if (zx2 != null && zx2 == this.bZ()) {
                if (zx2.b <= 0 && !this.bA.d) {
                    this.ca();
                } else if (zx2.b < \u2603.b && this.bA.d) {
                    zx2.b = \u2603.b;
                }
            }
            return true;
        }
        if (zx2 != null && pk2 instanceof pr) {
            if (this.bA.d) {
                zx2 = \u2603;
            }
            if (zx2.a(this, (pr)pk2)) {
                if (zx2.b <= 0 && !this.bA.d) {
                    this.ca();
                }
                return true;
            }
        }
        return false;
    }

    public zx bZ() {
        return this.bi.h();
    }

    public void ca() {
        this.bi.a(this.bi.c, null);
    }

    @Override
    public double am() {
        return -0.35;
    }

    public void f(pk pk2) {
        if (!pk2.aD()) {
            return;
        }
        if (pk2.l(this)) {
            return;
        }
        float f2 = (float)this.a(vy.e).e();
        int \u26032 = 0;
        \u2603 = 0.0f;
        \u2603 = pk2 instanceof pr ? ack.a(this.bA(), ((pr)pk2).bz()) : ack.a(this.bA(), pw.a);
        \u26032 += ack.a(this);
        if (this.aw()) {
            ++\u26032;
        }
        if (f2 > 0.0f || \u2603 > 0.0f) {
            boolean bl2 = \u2603 = this.O > 0.0f && !this.C && !this.k_() && !this.V() && !this.a(pe.q) && this.m == null && pk2 instanceof pr;
            if (\u2603 && f2 > 0.0f) {
                f2 *= 1.5f;
            }
            f2 += \u2603;
            boolean bl3 = false;
            int \u26033 = ack.b(this);
            if (pk2 instanceof pr && \u26033 > 0 && !pk2.at()) {
                bl3 = true;
                pk2.e(1);
            }
            double \u26034 = pk2.v;
            double \u26035 = pk2.w;
            double \u26036 = pk2.x;
            \u2603 = pk2.a(ow.a(this), f2);
            if (\u2603) {
                if (\u26032 > 0) {
                    pk2.g(-ns.a(this.y * (float)Math.PI / 180.0f) * (float)\u26032 * 0.5f, 0.1, ns.b(this.y * (float)Math.PI / 180.0f) * (float)\u26032 * 0.5f);
                    this.v *= 0.6;
                    this.x *= 0.6;
                    this.d(false);
                }
                if (pk2 instanceof lf && pk2.G) {
                    ((lf)pk2).a.a(new hm(pk2));
                    pk2.G = false;
                    pk2.v = \u26034;
                    pk2.w = \u26035;
                    pk2.x = \u26036;
                }
                if (\u2603) {
                    this.b(pk2);
                }
                if (\u2603 > 0.0f) {
                    this.c(pk2);
                }
                if (f2 >= 18.0f) {
                    this.b(mr.F);
                }
                this.p(pk2);
                if (pk2 instanceof pr) {
                    ack.a((pr)pk2, (pk)this);
                }
                ack.b(this, pk2);
                zx zx2 = this.bZ();
                pk \u26037 = pk2;
                if (pk2 instanceof ue && (\u2603 = ((ue)pk2).a) instanceof pr) {
                    \u26037 = (pr)((Object)\u2603);
                }
                if (zx2 != null && \u26037 instanceof pr) {
                    zx2.a((pr)\u26037, this);
                    if (zx2.b <= 0) {
                        this.ca();
                    }
                }
                if (pk2 instanceof pr) {
                    this.a(na.w, Math.round(f2 * 10.0f));
                    if (\u26033 > 0) {
                        pk2.e(\u26033 * 4);
                    }
                }
                this.a(0.3f);
            } else if (bl3) {
                pk2.N();
            }
        }
    }

    public void b(pk pk2) {
    }

    public void c(pk pk2) {
    }

    public void cb() {
    }

    @Override
    public void J() {
        super.J();
        this.bj.b(this);
        if (this.bk != null) {
            this.bk.b(this);
        }
    }

    @Override
    public boolean aj() {
        return !this.bw && super.aj();
    }

    public boolean cc() {
        return false;
    }

    public GameProfile cd() {
        return this.bH;
    }

    public a a(cj cj2) {
        if (!this.o.D) {
            if (this.bJ() || !this.ai()) {
                return wn$a.e;
            }
            if (!this.o.t.d()) {
                return wn$a.b;
            }
            if (this.o.w()) {
                return wn$a.c;
            }
            if (Math.abs(this.s - (double)cj2.n()) > 3.0 || Math.abs(this.t - (double)cj2.o()) > 2.0 || Math.abs(this.u - (double)cj2.p()) > 3.0) {
                return wn$a.d;
            }
            double d2 = 8.0;
            \u2603 = 5.0;
            List<vv> \u26032 = this.o.a(vv.class, new aug((double)cj2.n() - d2, (double)cj2.o() - \u2603, (double)cj2.p() - d2, (double)cj2.n() + d2, (double)cj2.o() + \u2603, (double)cj2.p() + d2));
            if (!\u26032.isEmpty()) {
                return wn$a.f;
            }
        }
        if (this.au()) {
            this.a((pk)null);
        }
        this.a(0.2f, 0.2f);
        if (this.o.e(cj2)) {
            cq \u26033 = this.o.p(cj2).b(age.O);
            float \u26034 = 0.5f;
            float \u26035 = 0.5f;
            switch (\u26033) {
                case d: {
                    \u26035 = 0.9f;
                    break;
                }
                case c: {
                    \u26035 = 0.1f;
                    break;
                }
                case e: {
                    \u26034 = 0.1f;
                    break;
                }
                case f: {
                    \u26034 = 0.9f;
                }
            }
            this.a(\u26033);
            this.b((float)cj2.n() + \u26034, (float)cj2.o() + 0.6875f, (float)cj2.p() + \u26035);
        } else {
            this.b((float)cj2.n() + 0.5f, (float)cj2.o() + 0.6875f, (float)cj2.p() + 0.5f);
        }
        this.bw = true;
        this.b = 0;
        this.bx = cj2;
        this.w = 0.0;
        this.x = 0.0;
        this.v = 0.0;
        if (!this.o.D) {
            this.o.d();
        }
        return wn$a.a;
    }

    private void a(cq cq2) {
        this.by = 0.0f;
        this.bz = 0.0f;
        switch (cq2) {
            case d: {
                this.bz = -1.8f;
                break;
            }
            case c: {
                this.bz = 1.8f;
                break;
            }
            case e: {
                this.by = 1.8f;
                break;
            }
            case f: {
                this.by = -1.8f;
            }
        }
    }

    public void a(boolean bl2, boolean bl32, boolean bl4) {
        boolean bl32;
        this.a(0.6f, 1.8f);
        alz alz2 = this.o.p(this.bx);
        if (this.bx != null && alz2.c() == afi.C) {
            this.o.a(this.bx, alz2.a(afg.b, false), 4);
            cj cj2 = afg.a(this.o, this.bx, 0);
            if (cj2 == null) {
                cj2 = this.bx.a();
            }
            this.b((float)cj2.n() + 0.5f, (float)cj2.o() + 0.1f, (float)cj2.p() + 0.5f);
        }
        this.bw = false;
        if (!this.o.D && bl32) {
            this.o.d();
        }
        int n2 = this.b = bl2 ? 0 : 100;
        if (bl4) {
            this.a(this.bx, false);
        }
    }

    private boolean p() {
        return this.o.p(this.bx).c() == afi.C;
    }

    public static cj a(adm adm22, cj cj2, boolean bl2) {
        adm adm22;
        afh afh2 = adm22.p(cj2).c();
        if (afh2 != afi.C) {
            if (!bl2) {
                return null;
            }
            boolean bl3 = afh2.g();
            \u2603 = adm22.p(cj2.a()).c().g();
            if (bl3 && \u2603) {
                return cj2;
            }
            return null;
        }
        return afg.a(adm22, cj2, 0);
    }

    public float ce() {
        if (this.bx != null) {
            cq cq2 = this.o.p(this.bx).b(age.O);
            switch (cq2) {
                case d: {
                    return 90.0f;
                }
                case e: {
                    return 0.0f;
                }
                case c: {
                    return 270.0f;
                }
                case f: {
                    return 180.0f;
                }
            }
        }
        return 0.0f;
    }

    @Override
    public boolean bJ() {
        return this.bw;
    }

    public boolean cf() {
        return this.bw && this.b >= 100;
    }

    public int cg() {
        return this.b;
    }

    public void b(eu eu2) {
    }

    public cj ch() {
        return this.c;
    }

    public boolean ci() {
        return this.d;
    }

    public void a(cj cj2, boolean bl2) {
        if (cj2 != null) {
            this.c = cj2;
            this.d = bl2;
        } else {
            this.c = null;
            this.d = false;
        }
    }

    public void b(mw mw2) {
        this.a(mw2, 1);
    }

    public void a(mw mw2, int n2) {
    }

    public void a(mw mw2) {
    }

    @Override
    public void bF() {
        super.bF();
        this.b(na.u);
        if (this.aw()) {
            this.a(0.8f);
        } else {
            this.a(0.2f);
        }
    }

    @Override
    public void g(float f22, float f3) {
        double d2 = this.s;
        \u2603 = this.t;
        \u2603 = this.u;
        if (this.bA.b && this.m == null) {
            \u2603 = this.w;
            float f4 = this.aM;
            this.aM = this.bA.a() * (float)(this.aw() ? 2 : 1);
            super.g(f22, f3);
            this.w = \u2603 * 0.6;
            this.aM = f4;
        } else {
            float f22;
            super.g(f22, f3);
        }
        this.k(this.s - d2, this.t - \u2603, this.u - \u2603);
    }

    @Override
    public float bI() {
        return (float)this.a(vy.d).e();
    }

    public void k(double d22, double d3, double d4) {
        if (this.m != null) {
            return;
        }
        if (this.a(arm.h)) {
            int n2 = Math.round(ns.a(d22 * d22 + d3 * d3 + d4 * d4) * 100.0f);
            if (n2 > 0) {
                this.a(na.p, n2);
                this.a(0.015f * (float)n2 * 0.01f);
            }
        } else if (this.V()) {
            double d22;
            int \u26032 = Math.round(ns.a(d22 * d22 + d4 * d4) * 100.0f);
            if (\u26032 > 0) {
                this.a(na.l, \u26032);
                this.a(0.015f * (float)\u26032 * 0.01f);
            }
        } else if (this.k_()) {
            if (d3 > 0.0) {
                this.a(na.n, (int)Math.round(d3 * 100.0));
            }
        } else if (this.C) {
            int \u26033 = Math.round(ns.a(d22 * d22 + d4 * d4) * 100.0f);
            if (\u26033 > 0) {
                this.a(na.i, \u26033);
                if (this.aw()) {
                    this.a(na.k, \u26033);
                    this.a(0.099999994f * (float)\u26033 * 0.01f);
                } else {
                    if (this.av()) {
                        this.a(na.j, \u26033);
                    }
                    this.a(0.01f * (float)\u26033 * 0.01f);
                }
            }
        } else {
            int \u26034 = Math.round(ns.a(d22 * d22 + d4 * d4) * 100.0f);
            if (\u26034 > 25) {
                this.a(na.o, \u26034);
            }
        }
    }

    private void l(double d2, double d3, double d4) {
        if (this.m != null && (\u2603 = Math.round(ns.a(d2 * d2 + d3 * d3 + d4 * d4) * 100.0f)) > 0) {
            if (this.m instanceof va) {
                this.a(na.q, \u2603);
                if (this.e == null) {
                    this.e = new cj(this);
                } else if (this.e.c(ns.c(this.s), ns.c(this.t), ns.c(this.u)) >= 1000000.0) {
                    this.b(mr.q);
                }
            } else if (this.m instanceof ux) {
                this.a(na.r, \u2603);
            } else if (this.m instanceof tt) {
                this.a(na.s, \u2603);
            } else if (this.m instanceof tp) {
                this.a(na.t, \u2603);
            }
        }
    }

    @Override
    public void e(float f2, float f3) {
        if (this.bA.c) {
            return;
        }
        if (f2 >= 2.0f) {
            this.a(na.m, (int)Math.round((double)f2 * 100.0));
        }
        super.e(f2, f3);
    }

    @Override
    protected void X() {
        if (!this.v()) {
            super.X();
        }
    }

    @Override
    protected String n(int n2) {
        if (n2 > 4) {
            return "game.player.hurt.fall.big";
        }
        return "game.player.hurt.fall.small";
    }

    @Override
    public void a(pr pr2) {
        if (pr2 instanceof vq) {
            this.b(mr.s);
        }
        if ((\u2603 = pm.a.get(pm.a(pr2))) != null) {
            this.b(\u2603.d);
        }
    }

    @Override
    public void aA() {
        if (!this.bA.b) {
            super.aA();
        }
    }

    @Override
    public zx q(int n2) {
        return this.bi.e(n2);
    }

    public void u(int n2) {
        this.s(n2);
        \u2603 = Integer.MAX_VALUE - this.bC;
        if (n2 > \u2603) {
            n2 = \u2603;
        }
        this.bD += (float)n2 / (float)this.ck();
        this.bC += n2;
        while (this.bD >= 1.0f) {
            this.bD = (this.bD - 1.0f) * (float)this.ck();
            this.a(1);
            this.bD /= (float)this.ck();
        }
    }

    public int cj() {
        return this.f;
    }

    public void b(int n2) {
        this.bB -= n2;
        if (this.bB < 0) {
            this.bB = 0;
            this.bD = 0.0f;
            this.bC = 0;
        }
        this.f = this.V.nextInt();
    }

    public void a(int n2) {
        this.bB += n2;
        if (this.bB < 0) {
            this.bB = 0;
            this.bD = 0.0f;
            this.bC = 0;
        }
        if (n2 > 0 && this.bB % 5 == 0 && (float)this.i < (float)this.W - 100.0f) {
            float f2 = this.bB > 30 ? 1.0f : (float)this.bB / 30.0f;
            this.o.a((pk)this, "random.levelup", f2 * 0.75f, 1.0f);
            this.i = this.W;
        }
    }

    public int ck() {
        if (this.bB >= 30) {
            return 112 + (this.bB - 30) * 9;
        }
        if (this.bB >= 15) {
            return 37 + (this.bB - 15) * 5;
        }
        return 7 + this.bB * 2;
    }

    public void a(float f2) {
        if (this.bA.a) {
            return;
        }
        if (!this.o.D) {
            this.bl.a(f2);
        }
    }

    public xg cl() {
        return this.bl;
    }

    public boolean j(boolean bl2) {
        return (bl2 || this.bl.c()) && !this.bA.a;
    }

    public boolean cm() {
        return this.bn() > 0.0f && this.bn() < this.bu();
    }

    public void a(zx zx2, int n2) {
        if (zx2 == this.g) {
            return;
        }
        this.g = zx2;
        this.h = n2;
        if (!this.o.D) {
            this.f(true);
        }
    }

    public boolean cn() {
        return this.bA.e;
    }

    public boolean a(cj cj2, cq cq2, zx zx2) {
        if (this.bA.e) {
            return true;
        }
        if (zx2 == null) {
            return false;
        }
        cj cj3 = cj2.a(cq2.d());
        afh \u26032 = this.o.p(cj3).c();
        return zx2.d(\u26032) || zx2.x();
    }

    @Override
    protected int b(wn wn2) {
        if (this.o.Q().b("keepInventory")) {
            return 0;
        }
        int n2 = this.bB * 7;
        if (n2 > 100) {
            return 100;
        }
        return n2;
    }

    @Override
    protected boolean bb() {
        return true;
    }

    @Override
    public boolean aO() {
        return true;
    }

    public void a(wn wn2, boolean bl2) {
        if (bl2) {
            this.bi.b(wn2.bi);
            this.i(wn2.bn());
            this.bl = wn2.bl;
            this.bB = wn2.bB;
            this.bC = wn2.bC;
            this.bD = wn2.bD;
            this.r(wn2.bX());
            this.an = wn2.an;
            this.ao = wn2.ao;
            this.ap = wn2.ap;
        } else if (this.o.Q().b("keepInventory")) {
            this.bi.b(wn2.bi);
            this.bB = wn2.bB;
            this.bC = wn2.bC;
            this.bD = wn2.bD;
            this.r(wn2.bX());
        }
        this.f = wn2.f;
        this.a = wn2.a;
        this.H().b(10, wn2.H().a(10));
    }

    @Override
    protected boolean s_() {
        return !this.bA.b;
    }

    public void t() {
    }

    public void a(adp.a a2) {
    }

    @Override
    public String e_() {
        return this.bH.getName();
    }

    public yd co() {
        return this.a;
    }

    @Override
    public zx p(int n2) {
        if (n2 == 0) {
            return this.bi.h();
        }
        return this.bi.b[n2 - 1];
    }

    @Override
    public zx bA() {
        return this.bi.h();
    }

    @Override
    public void c(int n2, zx zx2) {
        this.bi.b[n2] = zx2;
    }

    @Override
    public boolean f(wn wn2) {
        if (!this.ax()) {
            return false;
        }
        if (wn2.v()) {
            return false;
        }
        auq auq2 = this.bO();
        return auq2 == null || wn2 == null || wn2.bO() != auq2 || !auq2.h();
    }

    public abstract boolean v();

    @Override
    public zx[] as() {
        return this.bi.b;
    }

    @Override
    public boolean aL() {
        return !this.bA.b;
    }

    public auo cp() {
        return this.o.Z();
    }

    @Override
    public auq bO() {
        return this.cp().h(this.e_());
    }

    @Override
    public eu f_() {
        fa fa2 = new fa(aul.a(this.bO(), this.e_()));
        fa2.b().a(new et(et.a.e, "/msg " + this.e_() + " "));
        fa2.b().a(this.aQ());
        fa2.b().a(this.e_());
        return fa2;
    }

    @Override
    public float aS() {
        float f2 = 1.62f;
        if (this.bJ()) {
            f2 = 0.2f;
        }
        if (this.av()) {
            f2 -= 0.08f;
        }
        return f2;
    }

    @Override
    public void m(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.H().b(17, Float.valueOf(f2));
    }

    @Override
    public float bN() {
        return this.H().d(17);
    }

    public static UUID a(GameProfile gameProfile) {
        UUID uUID = gameProfile.getId();
        if (uUID == null) {
            uUID = wn.b(gameProfile.getName());
        }
        return uUID;
    }

    public static UUID b(String string) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + string).getBytes(Charsets.UTF_8));
    }

    public boolean a(on on2) {
        if (on2.a()) {
            return true;
        }
        zx zx2 = this.bZ();
        if (zx2 != null && zx2.s()) {
            return zx2.q().equals(on2.b());
        }
        return false;
    }

    public boolean a(wo wo2) {
        return (this.H().a(10) & wo2.a()) == wo2.a();
    }

    @Override
    public boolean u_() {
        return MinecraftServer.N().d[0].Q().b("sendCommandFeedback");
    }

    @Override
    public boolean d(int n2, zx zx2) {
        if (n2 >= 0 && n2 < this.bi.a.length) {
            this.bi.a(n2, zx2);
            return true;
        }
        int n3 = n2 - 100;
        if (n3 >= 0 && n3 < this.bi.b.length) {
            \u2603 = n3 + 1;
            if (zx2 != null && zx2.b() != null && (zx2.b() instanceof yj ? ps.c(zx2) != \u2603 : \u2603 != 4 || zx2.b() != zy.bX && !(zx2.b() instanceof yo))) {
                return false;
            }
            this.bi.a(n3 + this.bi.a.length, zx2);
            return true;
        }
        \u2603 = n2 - 200;
        if (\u2603 >= 0 && \u2603 < this.a.o_()) {
            this.a.a(\u2603, zx2);
            return true;
        }
        return false;
    }

    public boolean cq() {
        return this.bI;
    }

    public void k(boolean bl2) {
        this.bI = bl2;
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e,
        f;

    }

    public static enum b {
        a(0, "options.chat.visibility.full"),
        b(1, "options.chat.visibility.system"),
        c(2, "options.chat.visibility.hidden");

        private static final b[] d;
        private final int e;
        private final String f;

        private b(int n3, String string2) {
            this.e = n3;
            this.f = string2;
        }

        public int a() {
            return this.e;
        }

        public static b a(int n2) {
            return d[n2 % d.length];
        }

        public String b() {
            return this.f;
        }

        static {
            d = new b[wn$b.values().length];
            b[] bArray = wn$b.values();
            int \u26032 = bArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                wn$b.d[\u2603.e] = \u2603 = bArray[i2];
            }
        }
    }
}

