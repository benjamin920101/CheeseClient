/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lf
extends wn
implements xn {
    private static final Logger bH = LogManager.getLogger();
    private String bI = "en_US";
    public lm a;
    public final MinecraftServer b;
    public final lg c;
    public double d;
    public double e;
    public final List<adg> f = Lists.newLinkedList();
    private final List<Integer> bJ = Lists.newLinkedList();
    private final mv bK;
    private float bL = Float.MIN_VALUE;
    private float bM = -1.0E8f;
    private int bN = -99999999;
    private boolean bO = true;
    private int bP = -99999999;
    private int bQ = 60;
    private wn.b bR;
    private boolean bS = true;
    private long bT = System.currentTimeMillis();
    private pk bU = null;
    private int bV;
    public boolean g;
    public int h;
    public boolean i;

    public lf(MinecraftServer minecraftServer, le le2, GameProfile gameProfile, lg lg2) {
        super(le2, gameProfile);
        lg2.b = this;
        this.c = lg2;
        cj \u26032 = le2.M();
        if (!le2.t.o() && le2.P().r() != adp.a.d) {
            int n2 = Math.max(5, minecraftServer.aw() - 6);
            \u2603 = ns.c(le2.af().b(\u26032.n(), \u26032.p()));
            if (\u2603 < n2) {
                n2 = \u2603;
            }
            if (\u2603 <= 1) {
                n2 = 1;
            }
            \u26032 = le2.r(\u26032.a(this.V.nextInt(n2 * 2) - n2, 0, this.V.nextInt(n2 * 2) - n2));
        }
        this.b = minecraftServer;
        this.bK = minecraftServer.ap().a((wn)this);
        this.S = 0.0f;
        this.a(\u26032, 0.0f, 0.0f);
        while (!le2.a((pk)this, this.aR()).isEmpty() && this.t < 255.0) {
            this.b(this.s, this.t + 1.0, this.u);
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        if (dn2.b("playerGameType", 99)) {
            if (MinecraftServer.N().ax()) {
                this.c.a(MinecraftServer.N().m());
            } else {
                this.c.a(adp.a.a(dn2.f("playerGameType")));
            }
        }
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("playerGameType", this.c.b().a());
    }

    @Override
    public void a(int n2) {
        super.a(n2);
        this.bP = -1;
    }

    @Override
    public void b(int n2) {
        super.b(n2);
        this.bP = -1;
    }

    public void g_() {
        this.bk.a(this);
    }

    @Override
    public void h_() {
        super.h_();
        this.a.a(new gy(this.bs(), gy.a.a));
    }

    @Override
    public void j() {
        super.j();
        this.a.a(new gy(this.bs(), gy.a.b));
    }

    @Override
    public void t_() {
        pk pk2;
        this.c.a();
        --this.bQ;
        if (this.Z > 0) {
            --this.Z;
        }
        this.bk.b();
        if (!this.o.D && !this.bk.a(this)) {
            this.n();
            this.bk = this.bj;
        }
        while (!this.bJ.isEmpty()) {
            int n2 = Math.min(this.bJ.size(), Integer.MAX_VALUE);
            int[] \u26032 = new int[n2];
            Iterator<Integer> iterator = this.bJ.iterator();
            int \u26035 = 0;
            while (iterator.hasNext() && \u26035 < n2) {
                \u26032[\u26035++] = iterator.next();
                iterator.remove();
            }
            this.a.a(new hb(\u26032));
        }
        if (!this.f.isEmpty()) {
            ArrayList<amy> \u26034 = Lists.newArrayList();
            Iterator<adg> \u26036 = this.f.iterator();
            ArrayList<akw> \u26033 = Lists.newArrayList();
            while (\u26036.hasNext() && \u26034.size() < 10) {
                adg adg2 = \u26036.next();
                if (adg2 != null) {
                    amy amy2;
                    if (!this.o.e(new cj(adg2.a << 4, 0, adg2.b << 4)) || !(amy2 = this.o.a(adg2.a, adg2.b)).i()) continue;
                    \u26034.add(amy2);
                    \u26033.addAll(((le)this.o).a(adg2.a * 16, 0, adg2.b * 16, adg2.a * 16 + 16, 256, adg2.b * 16 + 16));
                    \u26036.remove();
                    continue;
                }
                \u26036.remove();
            }
            if (!\u26034.isEmpty()) {
                if (\u26034.size() == 1) {
                    this.a.a(new go((amy)\u26034.get(0), true, 65535));
                } else {
                    this.a.a(new gp(\u26034));
                }
                for (akw akw2 : \u26033) {
                    this.a(akw2);
                }
                for (amy amy3 : \u26034) {
                    this.u().s().a(this, amy3);
                }
            }
        }
        if ((pk2 = this.C()) != this) {
            if (!pk2.ai()) {
                this.e((pk)this);
            } else {
                this.a(pk2.s, pk2.t, pk2.u, pk2.y, pk2.z);
                this.b.ap().d(this);
                if (this.av()) {
                    this.e((pk)this);
                }
            }
        }
    }

    public void l() {
        try {
            super.t_();
            for (int i2 = 0; i2 < this.bi.o_(); ++i2) {
                ff ff2;
                zx zx2 = this.bi.a(i2);
                if (zx2 == null || !zx2.b().f() || (ff2 = ((yy)zx2.b()).c(zx2, this.o, this)) == null) continue;
                this.a.a(ff2);
            }
            if (this.bn() != this.bM || this.bN != this.bl.a() || this.bl.e() == 0.0f != this.bO) {
                this.a.a(new hp(this.bn(), this.bl.a(), this.bl.e()));
                this.bM = this.bn();
                this.bN = this.bl.a();
                boolean bl2 = this.bO = this.bl.e() == 0.0f;
            }
            if (this.bn() + this.bN() != this.bL) {
                this.bL = this.bn() + this.bN();
                Collection<auk> collection = this.cp().a(auu.g);
                for (auk auk2 : collection) {
                    this.cp().c(this.e_(), auk2).a(Arrays.asList(this));
                }
            }
            if (this.bC != this.bP) {
                this.bP = this.bC;
                this.a.a(new ho(this.bD, this.bC, this.bB));
            }
            if (this.W % 20 * 5 == 0 && !this.A().a(mr.L)) {
                this.i_();
            }
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Ticking player");
            c c2 = b2.a("Player being ticked");
            this.a(c2);
            throw new e(b2);
        }
    }

    protected void i_() {
        ady ady2 = this.o.b(new cj(ns.c(this.s), 0, ns.c(this.u)));
        String \u26032 = ady2.ah;
        nc \u26033 = (nc)this.A().b((mw)mr.L);
        if (\u26033 == null) {
            \u26033 = this.A().a(mr.L, new nc());
        }
        \u26033.add(\u26032);
        if (this.A().b(mr.L) && \u26033.size() >= ady.n.size()) {
            HashSet<ady> hashSet = Sets.newHashSet(ady.n);
            for (String string : \u26033) {
                Iterator iterator = hashSet.iterator();
                while (iterator.hasNext()) {
                    ady ady3 = (ady)iterator.next();
                    if (!ady3.ah.equals(string)) continue;
                    iterator.remove();
                }
                if (!hashSet.isEmpty()) continue;
                break;
            }
            if (hashSet.isEmpty()) {
                this.b(mr.L);
            }
        }
    }

    @Override
    public void a(ow ow2) {
        Object object;
        if (this.o.Q().b("showDeathMessages")) {
            object = this.bO();
            if (object == null || ((auq)object).j() == auq.a.a) {
                this.b.ap().a(this.bs().b());
            } else if (((auq)object).j() == auq.a.c) {
                this.b.ap().a((wn)this, this.bs().b());
            } else if (((auq)object).j() == auq.a.d) {
                this.b.ap().b((wn)this, this.bs().b());
            }
        }
        if (!this.o.Q().b("keepInventory")) {
            this.bi.n();
        }
        object = this.o.Z().a(auu.d);
        object2 = object.iterator();
        while (object2.hasNext()) {
            \u2603 = (auk)object2.next();
            aum aum2 = this.cp().c(this.e_(), (auk)\u2603);
            aum2.a();
        }
        Object object2 = this.bt();
        if (object2 != null) {
            \u2603 = pm.a.get(pm.a((pk)object2));
            if (\u2603 != null) {
                this.b(((pm.a)\u2603).e);
            }
            ((pk)object2).b(this, this.aW);
        }
        this.b(na.y);
        this.a(na.h);
        this.bs().g();
    }

    @Override
    public boolean a(ow ow22, float f2) {
        ow ow22;
        if (this.b(ow22)) {
            return false;
        }
        boolean bl2 = \u2603 = this.b.ae() && this.cr() && "fall".equals(ow22.p);
        if (!\u2603 && this.bQ > 0 && ow22 != ow.j) {
            return false;
        }
        if (ow22 instanceof ox) {
            pk pk2 = ow22.j();
            if (pk2 instanceof wn && !this.a((wn)pk2)) {
                return false;
            }
            if (pk2 instanceof wq) {
                wq wq2 = (wq)pk2;
                if (wq2.c instanceof wn && !this.a((wn)wq2.c)) {
                    return false;
                }
            }
        }
        return super.a(ow22, f2);
    }

    @Override
    public boolean a(wn wn2) {
        if (!this.cr()) {
            return false;
        }
        return super.a(wn2);
    }

    private boolean cr() {
        return this.b.aj();
    }

    @Override
    public void c(int \u260322) {
        if (this.am == 1 && \u260322 == 1) {
            this.b(mr.D);
            this.o.e(this);
            this.i = true;
            this.a.a(new gm(4, 0.0f));
        } else {
            int \u260322;
            if (this.am == 0 && \u260322 == 1) {
                this.b(mr.C);
                cj cj2 = this.b.a(\u260322).m();
                if (cj2 != null) {
                    this.a.a(cj2.n(), cj2.o(), cj2.p(), 0.0f, 0.0f);
                }
                \u260322 = 1;
            } else {
                this.b(mr.y);
            }
            this.b.ap().a(this, \u260322);
            this.bP = -1;
            this.bM = -1.0f;
            this.bN = -1;
        }
    }

    @Override
    public boolean a(lf lf2) {
        if (lf2.v()) {
            return this.C() == this;
        }
        if (this.v()) {
            return false;
        }
        return super.a(lf2);
    }

    private void a(akw akw2) {
        if (akw2 != null && (\u2603 = akw2.y_()) != null) {
            this.a.a(\u2603);
        }
    }

    @Override
    public void a(pk pk2, int n2) {
        super.a(pk2, n2);
        this.bk.b();
    }

    @Override
    public wn.a a(cj cj2) {
        wn.a a2 = super.a(cj2);
        if (a2 == wn.a.a) {
            ha ha2 = new ha(this, cj2);
            this.u().s().a((pk)this, ha2);
            this.a.a(this.s, this.t, this.u, this.y, this.z);
            this.a.a(ha2);
        }
        return a2;
    }

    @Override
    public void a(boolean bl2, boolean bl3, boolean bl4) {
        if (this.bJ()) {
            this.u().s().b(this, new fq(this, 2));
        }
        super.a(bl2, bl3, bl4);
        if (this.a != null) {
            this.a.a(this.s, this.t, this.u, this.y, this.z);
        }
    }

    @Override
    public void a(pk pk2) {
        \u2603 = this.m;
        super.a(pk2);
        if (pk2 != \u2603) {
            this.a.a(new hl(0, this, this.m));
            this.a.a(this.s, this.t, this.u, this.y, this.z);
        }
    }

    @Override
    protected void a(double d2, boolean bl2, afh afh2, cj cj2) {
    }

    public void a(double d2, boolean bl2) {
        int n2 = ns.c(this.s);
        cj \u26032 = new cj(n2, \u2603 = ns.c(this.t - (double)0.2f), \u2603 = ns.c(this.u));
        afh \u26033 = this.o.p(\u26032).c();
        if (\u26033.t() == arm.a && ((\u2603 = this.o.p(\u26032.b()).c()) instanceof agt || \u2603 instanceof akl || \u2603 instanceof agu)) {
            \u26032 = \u26032.b();
            \u26033 = this.o.p(\u26032).c();
        }
        super.a(d2, bl2, \u26033, \u26032);
    }

    @Override
    public void a(aln aln2) {
        aln2.a(this);
        this.a.a(new gw(aln2.v()));
    }

    private void cs() {
        this.bV = this.bV % 100 + 1;
    }

    @Override
    public void a(ol ol2) {
        this.cs();
        this.a.a(new gc(this.bV, ol2.k(), ol2.f_()));
        this.bk = ol2.a(this.bi, this);
        this.bk.d = this.bV;
        this.bk.a(this);
    }

    @Override
    public void a(og og2) {
        if (this.bk != this.bj) {
            this.n();
        }
        if (og2 instanceof oo && (\u2603 = (oo)og2).r_() && !this.a(\u2603.i()) && !this.v()) {
            this.a.a(new fy(new fb("container.isLocked", og2.f_()), 2));
            this.a.a(new gs("random.door_close", this.s, this.t, this.u, 1.0f, 1.0f));
            return;
        }
        this.cs();
        if (og2 instanceof ol) {
            this.a.a(new gc(this.bV, ((ol)((Object)og2)).k(), og2.f_(), og2.o_()));
            this.bk = ((ol)((Object)og2)).a(this.bi, this);
        } else {
            this.a.a(new gc(this.bV, "minecraft:container", og2.f_(), og2.o_()));
            this.bk = new xo(this.bi, og2, this);
        }
        this.bk.d = this.bV;
        this.bk.a(this);
    }

    @Override
    public void a(acy acy2) {
        this.cs();
        this.bk = new yb(this.bi, acy2, this.o);
        this.bk.d = this.bV;
        this.bk.a(this);
        ya ya2 = ((yb)this.bk).e();
        eu \u26032 = acy2.f_();
        this.a.a(new gc(this.bV, "minecraft:villager", \u26032, ya2.o_()));
        ada \u26033 = acy2.b_(this);
        if (\u26033 != null) {
            em em2 = new em(Unpooled.buffer());
            em2.writeInt(this.bV);
            \u26033.a(em2);
            this.a.a(new gg("MC|TrList", em2));
        }
    }

    @Override
    public void a(tp tp2, og og2) {
        if (this.bk != this.bj) {
            this.n();
        }
        this.cs();
        this.a.a(new gc(this.bV, "EntityHorse", og2.f_(), og2.o_(), tp2.F()));
        this.bk = new xx(this.bi, og2, tp2, this);
        this.bk.d = this.bV;
        this.bk.a(this);
    }

    @Override
    public void a(zx zx2) {
        zw zw2 = zx2.b();
        if (zw2 == zy.bN) {
            this.a.a(new gg("MC|BOpen", new em(Unpooled.buffer())));
        }
    }

    @Override
    public void a(xi xi2, int n2, zx zx2) {
        if (xi2.a(n2) instanceof yf) {
            return;
        }
        if (this.g) {
            return;
        }
        this.a.a(new gf(xi2.d, n2, zx2));
    }

    public void a(xi xi2) {
        this.a(xi2, xi2.a());
    }

    @Override
    public void a(xi xi2, List<zx> list) {
        this.a.a(new gd(xi2.d, list));
        this.a.a(new gf(-1, -1, this.bi.p()));
    }

    @Override
    public void a(xi xi2, int n2, int n3) {
        this.a.a(new ge(xi2.d, n2, n3));
    }

    @Override
    public void a(xi xi2, og og2) {
        for (int i2 = 0; i2 < og2.g(); ++i2) {
            this.a.a(new ge(xi2.d, i2, og2.a_(i2)));
        }
    }

    @Override
    public void n() {
        this.a.a(new gb(this.bk.d));
        this.p();
    }

    public void o() {
        if (this.g) {
            return;
        }
        this.a.a(new gf(-1, -1, this.bi.p()));
    }

    public void p() {
        this.bk.b(this);
        this.bk = this.bj;
    }

    public void a(float f2, float f3, boolean bl2, boolean bl3) {
        if (this.m != null) {
            if (f2 >= -1.0f && f2 <= 1.0f) {
                this.aZ = f2;
            }
            if (f3 >= -1.0f && f3 <= 1.0f) {
                this.ba = f3;
            }
            this.aY = bl2;
            this.c(bl3);
        }
    }

    @Override
    public void a(mw mw2, int n2) {
        if (mw2 == null) {
            return;
        }
        this.bK.b(this, mw2, n2);
        for (auk auk2 : this.cp().a(mw2.k())) {
            this.cp().c(this.e_(), auk2).a(n2);
        }
        if (this.bK.e()) {
            this.bK.a(this);
        }
    }

    @Override
    public void a(mw mw2) {
        if (mw2 == null) {
            return;
        }
        this.bK.a(this, mw2, 0);
        for (auk auk2 : this.cp().a(mw2.k())) {
            this.cp().c(this.e_(), auk2).c(0);
        }
        if (this.bK.e()) {
            this.bK.a(this);
        }
    }

    public void q() {
        if (this.l != null) {
            this.l.a((pk)this);
        }
        if (this.bw) {
            this.a(true, false, false);
        }
    }

    public void r() {
        this.bM = -1.0E8f;
    }

    @Override
    public void b(eu eu2) {
        this.a.a(new fy(eu2));
    }

    @Override
    protected void s() {
        this.a.a(new gi(this, 9));
        super.s();
    }

    @Override
    public void a(zx zx2, int n2) {
        super.a(zx2, n2);
        if (zx2 != null && zx2.b() != null && zx2.b().e(zx2) == aba.b) {
            this.u().s().b(this, new fq(this, 3));
        }
    }

    @Override
    public void a(wn wn2, boolean bl2) {
        super.a(wn2, bl2);
        this.bP = -1;
        this.bM = -1.0f;
        this.bN = -1;
        this.bJ.addAll(((lf)wn2).bJ);
    }

    @Override
    protected void a(pf pf2) {
        super.a(pf2);
        this.a.a(new ib(this.F(), pf2));
    }

    @Override
    protected void a(pf pf2, boolean bl2) {
        super.a(pf2, bl2);
        this.a.a(new ib(this.F(), pf2));
    }

    @Override
    protected void b(pf pf2) {
        super.b(pf2);
        this.a.a(new hc(this.F(), pf2));
    }

    @Override
    public void a(double d2, double d3, double d4) {
        this.a.a(d2, d3, d4, this.y, this.z);
    }

    @Override
    public void b(pk pk2) {
        this.u().s().b(this, new fq(pk2, 4));
    }

    @Override
    public void c(pk pk2) {
        this.u().s().b(this, new fq(pk2, 5));
    }

    @Override
    public void t() {
        if (this.a == null) {
            return;
        }
        this.a.a(new gx(this.bA));
        this.B();
    }

    public le u() {
        return (le)this.o;
    }

    @Override
    public void a(adp.a a2) {
        this.c.a(a2);
        this.a.a(new gm(3, a2.a()));
        if (a2 == adp.a.e) {
            this.a((pk)null);
        } else {
            this.e((pk)this);
        }
        this.t();
        this.bP();
    }

    @Override
    public boolean v() {
        return this.c.b() == adp.a.e;
    }

    @Override
    public void a(eu eu2) {
        this.a.a(new fy(eu2));
    }

    @Override
    public boolean a(int n2, String string) {
        if ("seed".equals(string) && !this.b.ae()) {
            return true;
        }
        if ("tell".equals(string) || "help".equals(string) || "me".equals(string) || "trigger".equals(string)) {
            return true;
        }
        if (this.b.ap().h(this.cd())) {
            lz lz2 = (lz)this.b.ap().m().b(this.cd());
            if (lz2 != null) {
                return lz2.a() >= n2;
            }
            return this.b.p() >= n2;
        }
        return false;
    }

    public String w() {
        String string = this.a.a.b().toString();
        string = string.substring(string.indexOf("/") + 1);
        string = string.substring(0, string.indexOf(":"));
        return string;
    }

    public void a(ih ih2) {
        this.bI = ih2.a();
        this.bR = ih2.c();
        this.bS = ih2.d();
        this.H().b(10, (byte)ih2.e());
    }

    public wn.b y() {
        return this.bR;
    }

    public void a(String string, String string2) {
        this.a.a(new hd(string, string2));
    }

    @Override
    public cj c() {
        return new cj(this.s, this.t + 0.5, this.u);
    }

    public void z() {
        this.bT = MinecraftServer.az();
    }

    public mv A() {
        return this.bK;
    }

    public void d(pk pk2) {
        if (pk2 instanceof wn) {
            this.a.a(new hb(pk2.F()));
        } else {
            this.bJ.add(pk2.F());
        }
    }

    @Override
    protected void B() {
        if (this.v()) {
            this.bj();
            this.e(true);
        } else {
            super.B();
        }
        this.u().s().a(this);
    }

    public pk C() {
        return this.bU == null ? this : this.bU;
    }

    public void e(pk pk2) {
        \u2603 = this.C();
        pk pk3 = this.bU = pk2 == null ? this : pk2;
        if (\u2603 != this.bU) {
            this.a.a(new hh(this.bU));
            this.a(this.bU.s, this.bU.t, this.bU.u);
        }
    }

    @Override
    public void f(pk pk2) {
        if (this.c.b() == adp.a.e) {
            this.e(pk2);
        } else {
            super.f(pk2);
        }
    }

    public long D() {
        return this.bT;
    }

    public eu E() {
        return null;
    }
}

