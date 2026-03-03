/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class le
extends adm
implements od {
    private static final Logger a = LogManager.getLogger();
    private final MinecraftServer I;
    private final la J;
    private final lc K;
    private final Set<adw> L = Sets.newHashSet();
    private final TreeSet<adw> M = new TreeSet();
    private final Map<UUID, pk> N = Maps.newHashMap();
    public ld b;
    public boolean c;
    private boolean O;
    private int P;
    private final adu Q;
    private final adt R = new adt();
    protected final tg d = new tg(this);
    private a[] S = new a[]{new a(), new a()};
    private int T;
    private static final List<ob> U = Lists.newArrayList(new ob(zy.y, 0, 1, 3, 10), new ob(zw.a(afi.f), 0, 1, 3, 10), new ob(zw.a(afi.r), 0, 1, 3, 10), new ob(zy.t, 0, 1, 1, 3), new ob(zy.p, 0, 1, 1, 5), new ob(zy.s, 0, 1, 1, 3), new ob(zy.o, 0, 1, 1, 5), new ob(zy.e, 0, 2, 3, 5), new ob(zy.P, 0, 2, 3, 3), new ob(zw.a(afi.s), 0, 1, 3, 10));
    private List<adw> V = Lists.newArrayList();

    public le(MinecraftServer minecraftServer, atp atp2, ato ato2, int n2, nt nt2) {
        super(atp2, ato2, anm.a(n2), nt2, false);
        this.I = minecraftServer;
        this.J = new la(this);
        this.K = new lc(this);
        this.t.a(this);
        this.v = this.k();
        this.Q = new adu(this);
        this.B();
        this.C();
        this.af().a(minecraftServer.aI());
    }

    @Override
    public adm b() {
        this.z = new aua(this.w);
        String string = th.a(this.t);
        th \u26032 = (th)this.z.a(th.class, string);
        if (\u26032 == null) {
            this.A = new th(this);
            this.z.a(string, this.A);
        } else {
            this.A = \u26032;
            this.A.a(this);
        }
        this.C = new kk(this.I);
        aup \u26033 = (aup)this.z.a(aup.class, "scoreboard");
        if (\u26033 == null) {
            \u26033 = new aup();
            this.z.a("scoreboard", \u26033);
        }
        \u26033.a(this.C);
        ((kk)this.C).a(\u26033);
        this.af().c(this.x.C(), this.x.D());
        this.af().c(this.x.I());
        this.af().b(this.x.H());
        this.af().c(this.x.J());
        this.af().b(this.x.K());
        if (this.x.F() > 0L) {
            this.af().a(this.x.E(), this.x.G(), this.x.F());
        } else {
            this.af().a(this.x.E());
        }
        return this;
    }

    @Override
    public void c() {
        super.c();
        if (this.P().t() && this.aa() != oj.d) {
            this.P().a(oj.d);
        }
        this.t.m().b();
        if (this.f()) {
            if (this.Q().b("doDaylightCycle")) {
                long l2 = this.x.g() + 24000L;
                this.x.c(l2 - l2 % 24000L);
            }
            this.e();
        }
        this.B.a("mobSpawner");
        if (this.Q().b("doMobSpawning") && this.x.u() != adr.g) {
            this.R.a(this, this.F, this.G, this.x.f() % 400L == 0L);
        }
        this.B.c("chunkSource");
        this.v.d();
        int n2 = this.a(1.0f);
        if (n2 != this.ab()) {
            this.c(n2);
        }
        this.x.b(this.x.f() + 1L);
        if (this.Q().b("doDaylightCycle")) {
            this.x.c(this.x.g() + 1L);
        }
        this.B.c("tickPending");
        this.a(false);
        this.B.c("tickBlocks");
        this.h();
        this.B.c("chunkMap");
        this.K.b();
        this.B.c("village");
        this.A.a();
        this.d.a();
        this.B.c("portalForcer");
        this.Q.a(this.K());
        this.B.b();
        this.ak();
    }

    public ady.c a(pt pt2, cj cj2) {
        List<ady.c> list = this.N().a(pt2, cj2);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return oa.a(this.s, list);
    }

    public boolean a(pt pt2, ady.c c2, cj cj2) {
        List<ady.c> list = this.N().a(pt2, cj2);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return list.contains(c2);
    }

    @Override
    public void d() {
        this.O = false;
        if (!this.j.isEmpty()) {
            int n2;
            int n3 = 0;
            n2 = 0;
            for (wn wn2 : this.j) {
                if (wn2.v()) {
                    ++n3;
                    continue;
                }
                if (!wn2.bJ()) continue;
                ++n2;
            }
            this.O = n2 > 0 && n2 >= this.j.size() - n3;
        }
    }

    protected void e() {
        this.O = false;
        for (wn wn2 : this.j) {
            if (!wn2.bJ()) continue;
            wn2.a(false, false, true);
        }
        this.ag();
    }

    private void ag() {
        this.x.g(0);
        this.x.b(false);
        this.x.f(0);
        this.x.a(false);
    }

    public boolean f() {
        if (this.O && !this.D) {
            for (wn wn2 : this.j) {
                if (!wn2.v() && wn2.cf()) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void g() {
        if (this.x.d() <= 0) {
            this.x.b(this.F() + 1);
        }
        int n2 = this.x.c();
        \u2603 = this.x.e();
        \u2603 = 0;
        while (this.c(new cj(n2, 0, \u2603)).t() == arm.a) {
            n2 += this.s.nextInt(8) - this.s.nextInt(8);
            \u2603 += this.s.nextInt(8) - this.s.nextInt(8);
            if (++\u2603 != 10000) continue;
        }
        this.x.a(n2);
        this.x.c(\u2603);
    }

    @Override
    protected void h() {
        super.h();
        if (this.x.u() == adr.g) {
            for (adg adg2 : this.E) {
                this.a(adg2.a, adg2.b).b(false);
            }
            return;
        }
        int n2 = 0;
        \u2603 = 0;
        for (adg adg3 : this.E) {
            Object \u26033;
            int n3 = adg3.a * 16;
            \u2603 = adg3.b * 16;
            this.B.a("getChunk");
            amy \u26032 = this.a(adg3.a, adg3.b);
            this.a(n3, \u2603, \u26032);
            this.B.c("tickChunk");
            \u26032.b(false);
            this.B.c("thunder");
            if (this.s.nextInt(100000) == 0 && this.S() && this.R()) {
                this.m = this.m * 3 + 1013904223;
                n4 = this.m >> 2;
                \u26033 = this.a(new cj(n3 + (n4 & 0xF), 0, \u2603 + (n4 >> 8 & 0xF)));
                if (this.C((cj)\u26033)) {
                    this.c(new uv(this, ((df)\u26033).n(), ((df)\u26033).o(), ((df)\u26033).p()));
                }
            }
            this.B.c("iceandsnow");
            if (this.s.nextInt(16) == 0) {
                this.m = this.m * 3 + 1013904223;
                int n4 = this.m >> 2;
                \u26033 = this.q(new cj(n3 + (n4 & 0xF), 0, \u2603 + (n4 >> 8 & 0xF)));
                cj \u26034 = ((cj)\u26033).b();
                if (this.w(\u26034)) {
                    this.a(\u26034, afi.aI.Q());
                }
                if (this.S() && this.f((cj)\u26033, true)) {
                    this.a((cj)\u26033, afi.aH.Q());
                }
                if (this.S() && this.b(\u26034).e()) {
                    this.p(\u26034).c().k(this, \u26034);
                }
            }
            this.B.c("tickBlocks");
            n4 = this.Q().c("randomTickSpeed");
            if (n4 > 0) {
                for (amz amz2 : \u26032.h()) {
                    if (amz2 == null || !amz2.b()) continue;
                    for (int i2 = 0; i2 < n4; ++i2) {
                        this.m = this.m * 3 + 1013904223;
                        \u2603 = this.m >> 2;
                        \u2603 = \u2603 & 0xF;
                        \u2603 = \u2603 >> 8 & 0xF;
                        \u2603 = \u2603 >> 16 & 0xF;
                        ++\u2603;
                        alz alz2 = amz2.a(\u2603, \u2603, \u2603);
                        afh \u26035 = alz2.c();
                        if (!\u26035.y()) continue;
                        ++n2;
                        \u26035.a((adm)this, new cj(\u2603 + n3, \u2603 + amz2.d(), \u2603 + \u2603), alz2, this.s);
                    }
                }
            }
            this.B.b();
        }
    }

    protected cj a(cj cj2) {
        \u2603 = this.q(cj2);
        aug aug2 = new aug(\u2603, new cj(\u2603.n(), this.U(), \u2603.p())).b(3.0, 3.0, 3.0);
        1 \u26032 = this.a(pr.class, aug2, new Predicate<pr>(){

            public boolean a(pr pr2) {
                return pr2 != null && pr2.ai() && le.this.i(pr2.c());
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pr)object);
            }
        });
        if (!\u26032.isEmpty()) {
            return ((pr)\u26032.get(this.s.nextInt(\u26032.size()))).c();
        }
        return \u2603;
    }

    @Override
    public boolean a(cj cj2, afh afh2) {
        adw adw2 = new adw(cj2, afh2);
        return this.V.contains(adw2);
    }

    @Override
    public void a(cj cj2, afh afh2, int n2) {
        this.a(cj2, afh2, n2, 0);
    }

    @Override
    public void a(cj cj22, afh afh2, int n22, int n3) {
        cj cj22;
        adw adw2 = new adw(cj22, afh2);
        int \u26032 = 0;
        if (this.e && afh2.t() != arm.a) {
            if (afh2.N()) {
                \u26032 = 8;
                if (this.a(adw2.a.a(-\u26032, -\u26032, -\u26032), adw2.a.a(\u26032, \u26032, \u26032)) && (\u2603 = this.p(adw2.a)).c().t() != arm.a && \u2603.c() == adw2.a()) {
                    \u2603.c().b((adm)this, adw2.a, \u2603, this.s);
                }
                return;
            }
            int n22 = 1;
        }
        if (this.a(cj22.a(-\u26032, -\u26032, -\u26032), cj22.a(\u26032, \u26032, \u26032))) {
            if (afh2.t() != arm.a) {
                adw2.a((long)n22 + this.x.f());
                adw2.a(n3);
            }
            if (!this.L.contains(adw2)) {
                this.L.add(adw2);
                this.M.add(adw2);
            }
        }
    }

    @Override
    public void b(cj cj2, afh afh2, int n2, int n3) {
        adw adw2 = new adw(cj2, afh2);
        adw2.a(n3);
        if (afh2.t() != arm.a) {
            adw2.a((long)n2 + this.x.f());
        }
        if (!this.L.contains(adw2)) {
            this.L.add(adw2);
            this.M.add(adw2);
        }
    }

    @Override
    public void i() {
        if (this.j.isEmpty()) {
            if (this.P++ >= 1200) {
                return;
            }
        } else {
            this.j();
        }
        super.i();
    }

    public void j() {
        this.P = 0;
    }

    @Override
    public boolean a(boolean bl2) {
        adw adw2;
        if (this.x.u() == adr.g) {
            return false;
        }
        int n2 = this.M.size();
        if (n2 != this.L.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        }
        if (n2 > 1000) {
            n2 = 1000;
        }
        this.B.a("cleaning");
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            adw2 = this.M.first();
            if (!bl2 && adw2.b > this.x.f()) break;
            this.M.remove(adw2);
            this.L.remove(adw2);
            this.V.add(adw2);
        }
        this.B.b();
        this.B.a("ticking");
        Iterator<adw> iterator = this.V.iterator();
        while (iterator.hasNext()) {
            adw2 = iterator.next();
            iterator.remove();
            int \u26032 = 0;
            if (this.a(adw2.a.a(-\u26032, -\u26032, -\u26032), adw2.a.a(\u26032, \u26032, \u26032))) {
                alz alz2 = this.p(adw2.a);
                if (alz2.c().t() == arm.a || !afh.a(alz2.c(), adw2.a())) continue;
                try {
                    alz2.c().b((adm)this, adw2.a, alz2, this.s);
                    continue;
                }
                catch (Throwable \u26033) {
                    b b2 = b.a(\u26033, "Exception while ticking a block");
                    c \u26034 = b2.a("Block being ticked");
                    c.a(\u26034, adw2.a, alz2);
                    throw new e(b2);
                }
            }
            this.a(adw2.a, adw2.a(), 0);
        }
        this.B.b();
        this.V.clear();
        return !this.M.isEmpty();
    }

    @Override
    public List<adw> a(amy amy2, boolean bl2) {
        adg adg2 = amy2.j();
        int \u26032 = (adg2.a << 4) - 2;
        int \u26033 = \u26032 + 16 + 2;
        int \u26034 = (adg2.b << 4) - 2;
        int \u26035 = \u26034 + 16 + 2;
        return this.a(new aqe(\u26032, 0, \u26034, \u26033, 256, \u26035), bl2);
    }

    @Override
    public List<adw> a(aqe aqe2, boolean bl2) {
        ArrayList<adw> arrayList = null;
        for (int i2 = 0; i2 < 2; ++i2) {
            Iterator<adw> iterator = i2 == 0 ? this.M.iterator() : this.V.iterator();
            while (iterator.hasNext()) {
                adw adw2 = iterator.next();
                cj \u26032 = adw2.a;
                if (\u26032.n() < aqe2.a || \u26032.n() >= aqe2.d || \u26032.p() < aqe2.c || \u26032.p() >= aqe2.f) continue;
                if (bl2) {
                    this.L.remove(adw2);
                    iterator.remove();
                }
                if (arrayList == null) {
                    arrayList = Lists.newArrayList();
                }
                arrayList.add(adw2);
            }
        }
        return arrayList;
    }

    @Override
    public void a(pk pk2, boolean bl2) {
        if (!this.ai() && (pk2 instanceof tm || pk2 instanceof tz)) {
            pk2.J();
        }
        if (!this.ah() && pk2 instanceof wh) {
            pk2.J();
        }
        super.a(pk2, bl2);
    }

    private boolean ah() {
        return this.I.ah();
    }

    private boolean ai() {
        return this.I.ag();
    }

    @Override
    protected amv k() {
        and and2 = this.w.a(this.t);
        this.b = new ld(this, and2, this.t.c());
        return this.b;
    }

    public List<akw> a(int n2, int n3, int n4, int n5, int n6, int n7) {
        ArrayList<akw> arrayList = Lists.newArrayList();
        for (int i2 = 0; i2 < this.h.size(); ++i2) {
            akw akw2 = (akw)this.h.get(i2);
            cj \u26032 = akw2.v();
            if (\u26032.n() < n2 || \u26032.o() < n3 || \u26032.p() < n4 || \u26032.n() >= n5 || \u26032.o() >= n6 || \u26032.p() >= n7) continue;
            arrayList.add(akw2);
        }
        return arrayList;
    }

    @Override
    public boolean a(wn wn2, cj cj2) {
        return !this.I.a(this, cj2, wn2) && this.af().a(cj2);
    }

    @Override
    public void a(adp adp2) {
        if (!this.x.w()) {
            try {
                this.b(adp2);
                if (this.x.u() == adr.g) {
                    this.aj();
                }
                super.a(adp2);
            }
            catch (Throwable throwable) {
                b b2 = b.a(throwable, "Exception initializing level");
                try {
                    this.a(b2);
                }
                catch (Throwable throwable2) {
                    // empty catch block
                }
                throw new e(b2);
            }
            this.x.d(true);
        }
    }

    private void aj() {
        this.x.f(false);
        this.x.c(true);
        this.x.b(false);
        this.x.a(false);
        this.x.i(1000000000);
        this.x.c(6000L);
        this.x.a(adp.a.e);
        this.x.g(false);
        this.x.a(oj.a);
        this.x.e(true);
        this.Q().a("doDaylightCycle", "false");
    }

    private void b(adp adp2) {
        if (!this.t.e()) {
            this.x.a(cj.a.b(this.t.i()));
            return;
        }
        if (this.x.u() == adr.g) {
            this.x.a(cj.a.a());
            return;
        }
        this.y = true;
        aec aec2 = this.t.m();
        List<ady> \u26032 = aec2.a();
        Random \u26033 = new Random(this.J());
        cj \u26034 = aec2.a(0, 0, 256, \u26032, \u26033);
        int \u26035 = 0;
        int \u26036 = this.t.i();
        int \u26037 = 0;
        if (\u26034 != null) {
            \u26035 = \u26034.n();
            \u26037 = \u26034.p();
        } else {
            a.warn("Unable to find spawn biome");
        }
        int \u26038 = 0;
        while (!this.t.a(\u26035, \u26037)) {
            \u26035 += \u26033.nextInt(64) - \u26033.nextInt(64);
            \u26037 += \u26033.nextInt(64) - \u26033.nextInt(64);
            if (++\u26038 != 1000) continue;
        }
        this.x.a(new cj(\u26035, \u26036, \u26037));
        this.y = false;
        if (adp2.c()) {
            this.l();
        }
    }

    protected void l() {
        aol aol2 = new aol(U, 10);
        for (int i2 = 0; i2 < 10 && !aol2.b(this, this.s, \u2603 = this.r(new cj(\u2603 = this.x.c() + this.s.nextInt(6) - this.s.nextInt(6), 0, \u2603 = this.x.e() + this.s.nextInt(6) - this.s.nextInt(6))).a()); ++i2) {
        }
    }

    public cj m() {
        return this.t.h();
    }

    public void a(boolean bl2, nu nu2) throws adn {
        if (!this.v.e()) {
            return;
        }
        if (nu2 != null) {
            nu2.a("Saving level");
        }
        this.a();
        if (nu2 != null) {
            nu2.c("Saving chunks");
        }
        this.v.a(bl2, nu2);
        ArrayList<amy> arrayList = Lists.newArrayList(this.b.a());
        for (amy amy2 : arrayList) {
            if (amy2 == null || this.K.a(amy2.a, amy2.b)) continue;
            this.b.b(amy2.a, amy2.b);
        }
    }

    public void n() {
        if (!this.v.e()) {
            return;
        }
        this.v.c();
    }

    protected void a() throws adn {
        this.I();
        this.x.a(this.af().h());
        this.x.d(this.af().f());
        this.x.c(this.af().g());
        this.x.e(this.af().m());
        this.x.f(this.af().n());
        this.x.j(this.af().q());
        this.x.k(this.af().p());
        this.x.b(this.af().j());
        this.x.e(this.af().i());
        this.w.a(this.x, this.I.ap().t());
        this.z.a();
    }

    @Override
    protected void a(pk pk2) {
        super.a(pk2);
        this.l.a(pk2.F(), pk2);
        this.N.put(pk2.aK(), pk2);
        pk[] pkArray = pk2.aB();
        if (pkArray != null) {
            for (int i2 = 0; i2 < pkArray.length; ++i2) {
                this.l.a(pkArray[i2].F(), pkArray[i2]);
            }
        }
    }

    @Override
    protected void b(pk pk2) {
        super.b(pk2);
        this.l.d(pk2.F());
        this.N.remove(pk2.aK());
        pk[] pkArray = pk2.aB();
        if (pkArray != null) {
            for (int i2 = 0; i2 < pkArray.length; ++i2) {
                this.l.d(pkArray[i2].F());
            }
        }
    }

    @Override
    public boolean c(pk pk2) {
        if (super.c(pk2)) {
            this.I.ap().a(pk2.s, pk2.t, pk2.u, 512.0, this.t.q(), new fm(pk2));
            return true;
        }
        return false;
    }

    @Override
    public void a(pk pk2, byte by) {
        this.s().b(pk2, new gi(pk2, by));
    }

    @Override
    public adi a(pk pk2, double d2, double d3, double d4, float f2, boolean bl2, boolean bl3) {
        adi adi2 = new adi(this, pk2, d2, d3, d4, f2, bl2, bl3);
        adi2.a();
        adi2.a(false);
        if (!bl3) {
            adi2.d();
        }
        for (wn wn2 : this.j) {
            if (!(wn2.e(d2, d3, d4) < 4096.0)) continue;
            ((lf)wn2).a.a(new gk(d2, d3, d4, f2, adi2.e(), adi2.b().get(wn2)));
        }
        return adi2;
    }

    @Override
    public void c(cj cj2, afh afh2, int n2, int n3) {
        ade ade2 = new ade(cj2, afh2, n2, n3);
        for (ade ade3 : this.S[this.T]) {
            if (!ade3.equals(ade2)) continue;
            return;
        }
        this.S[this.T].add(ade2);
    }

    private void ak() {
        while (!this.S[this.T].isEmpty()) {
            int n2 = this.T;
            this.T ^= 1;
            for (ade ade2 : this.S[n2]) {
                if (!this.a(ade2)) continue;
                this.I.ap().a(ade2.a().n(), ade2.a().o(), ade2.a().p(), 64.0, this.t.q(), new fu(ade2.a(), ade2.d(), ade2.b(), ade2.c()));
            }
            this.S[n2].clear();
        }
    }

    private boolean a(ade ade2) {
        alz alz2 = this.p(ade2.a());
        if (alz2.c() == ade2.d()) {
            return alz2.c().a((adm)this, ade2.a(), alz2, ade2.b(), ade2.c());
        }
        return false;
    }

    public void o() {
        this.w.a();
    }

    @Override
    protected void p() {
        boolean bl2 = this.S();
        super.p();
        if (this.o != this.p) {
            this.I.ap().a(new gm(7, this.p), this.t.q());
        }
        if (this.q != this.r) {
            this.I.ap().a(new gm(8, this.r), this.t.q());
        }
        if (bl2 != this.S()) {
            if (bl2) {
                this.I.ap().a(new gm(2, 0.0f));
            } else {
                this.I.ap().a(new gm(1, 0.0f));
            }
            this.I.ap().a(new gm(7, this.p));
            this.I.ap().a(new gm(8, this.r));
        }
    }

    @Override
    protected int q() {
        return this.I.ap().s();
    }

    public MinecraftServer r() {
        return this.I;
    }

    public la s() {
        return this.J;
    }

    public lc t() {
        return this.K;
    }

    public adu u() {
        return this.Q;
    }

    public void a(cy cy2, double d2, double d3, double d4, int n2, double d5, double d6, double d7, double d8, int ... nArray) {
        this.a(cy2, false, d2, d3, d4, n2, d5, d6, d7, d8, nArray);
    }

    public void a(cy cy2, boolean bl2, double d2, double d3, double d4, int n2, double d5, double d6, double d7, double d8, int ... nArray) {
        gr gr2 = new gr(cy2, bl2, (float)d2, (float)d3, (float)d4, (float)d5, (float)d6, (float)d7, (float)d8, n2, nArray);
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            lf lf2 = (lf)this.j.get(i2);
            cj \u26032 = lf2.c();
            double \u26033 = \u26032.c(d2, d3, d4);
            if (!(\u26033 <= 256.0) && (!bl2 || !(\u26033 <= 65536.0))) continue;
            lf2.a.a(gr2);
        }
    }

    public pk a(UUID uUID) {
        return this.N.get(uUID);
    }

    @Override
    public ListenableFuture<Object> a(Runnable runnable) {
        return this.I.a(runnable);
    }

    @Override
    public boolean aJ() {
        return this.I.aJ();
    }

    static class a
    extends ArrayList<ade> {
        private a() {
        }
    }
}

