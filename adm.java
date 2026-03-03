/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public abstract class adm
implements adq {
    private int a = 63;
    protected boolean e;
    public final List<pk> f = Lists.newArrayList();
    protected final List<pk> g = Lists.newArrayList();
    public final List<akw> h = Lists.newArrayList();
    public final List<akw> i = Lists.newArrayList();
    private final List<akw> b = Lists.newArrayList();
    private final List<akw> c = Lists.newArrayList();
    public final List<wn> j = Lists.newArrayList();
    public final List<pk> k = Lists.newArrayList();
    protected final nm<pk> l = new nm();
    private long d = 0xFFFFFFL;
    private int I;
    protected int m = new Random().nextInt();
    protected final int n = 1013904223;
    protected float o;
    protected float p;
    protected float q;
    protected float r;
    private int J;
    public final Random s = new Random();
    public final anm t;
    protected List<ado> u = Lists.newArrayList();
    protected amv v;
    protected final atp w;
    protected ato x;
    protected boolean y;
    protected aua z;
    protected th A;
    public final nt B;
    private final Calendar K = Calendar.getInstance();
    protected auo C = new auo();
    public final boolean D;
    protected Set<adg> E = Sets.newHashSet();
    private int L = this.s.nextInt(12000);
    protected boolean F = true;
    protected boolean G = true;
    private boolean M;
    private final ams N;
    int[] H = new int[32768];

    protected adm(atp atp2, ato ato2, anm anm2, nt nt2, boolean bl2) {
        this.w = atp2;
        this.B = nt2;
        this.x = ato2;
        this.t = anm2;
        this.D = bl2;
        this.N = anm2.r();
    }

    public adm b() {
        return this;
    }

    @Override
    public ady b(final cj cj2) {
        if (this.e(cj2)) {
            amy amy2 = this.f(cj2);
            try {
                return amy2.a(cj2, this.t.m());
            }
            catch (Throwable \u26032) {
                b b2 = b.a(\u26032, "Getting biome");
                c \u26033 = b2.a("Coordinates of biome request");
                \u26033.a("Location", new Callable<String>(){

                    public String a() throws Exception {
                        return c.a(cj2);
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                throw new e(b2);
            }
        }
        return this.t.m().a(cj2, ady.q);
    }

    public aec v() {
        return this.t.m();
    }

    protected abstract amv k();

    public void a(adp adp2) {
        this.x.d(true);
    }

    public void g() {
        this.B(new cj(8, 64, 8));
    }

    public afh c(cj cj2) {
        \u2603 = new cj(cj2.n(), this.F(), cj2.p());
        while (!this.d(\u2603.a())) {
            \u2603 = \u2603.a();
        }
        return this.p(\u2603).c();
    }

    private boolean a(cj cj2) {
        return cj2.n() >= -30000000 && cj2.p() >= -30000000 && cj2.n() < 30000000 && cj2.p() < 30000000 && cj2.o() >= 0 && cj2.o() < 256;
    }

    @Override
    public boolean d(cj cj2) {
        return this.p(cj2).c().t() == arm.a;
    }

    public boolean e(cj cj2) {
        return this.a(cj2, true);
    }

    public boolean a(cj cj2, boolean bl2) {
        if (!this.a(cj2)) {
            return false;
        }
        return this.a(cj2.n() >> 4, cj2.p() >> 4, bl2);
    }

    public boolean a(cj cj2, int n2) {
        return this.a(cj2, n2, true);
    }

    public boolean a(cj cj2, int n2, boolean bl2) {
        return this.a(cj2.n() - n2, cj2.o() - n2, cj2.p() - n2, cj2.n() + n2, cj2.o() + n2, cj2.p() + n2, bl2);
    }

    public boolean a(cj cj2, cj cj3) {
        return this.a(cj2, cj3, true);
    }

    public boolean a(cj cj2, cj cj3, boolean bl2) {
        return this.a(cj2.n(), cj2.o(), cj2.p(), cj3.n(), cj3.o(), cj3.p(), bl2);
    }

    public boolean a(aqe aqe2) {
        return this.b(aqe2, true);
    }

    public boolean b(aqe aqe2, boolean bl2) {
        return this.a(aqe2.a, aqe2.b, aqe2.c, aqe2.d, aqe2.e, aqe2.f, bl2);
    }

    private boolean a(int n2, int n3, int n4, int n5, int n6, int n7, boolean bl2) {
        if (n6 < 0 || n3 >= 256) {
            return false;
        }
        n4 >>= 4;
        n5 >>= 4;
        n7 >>= 4;
        for (int i2 = n2 >>= 4; i2 <= n5; ++i2) {
            for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                if (this.a(i2, \u2603, bl2)) continue;
                return false;
            }
        }
        return true;
    }

    protected boolean a(int n2, int n3, boolean bl2) {
        return this.v.a(n2, n3) && (bl2 || !this.v.d(n2, n3).f());
    }

    public amy f(cj cj2) {
        return this.a(cj2.n() >> 4, cj2.p() >> 4);
    }

    public amy a(int n2, int n3) {
        return this.v.d(n2, n3);
    }

    public boolean a(cj cj2, alz alz2, int n2) {
        if (!this.a(cj2)) {
            return false;
        }
        if (!this.D && this.x.u() == adr.g) {
            return false;
        }
        amy amy2 = this.f(cj2);
        afh \u26032 = alz2.c();
        alz \u26033 = amy2.a(cj2, alz2);
        if (\u26033 != null) {
            afh afh2 = \u26033.c();
            if (\u26032.p() != afh2.p() || \u26032.r() != afh2.r()) {
                this.B.a("checkLight");
                this.x(cj2);
                this.B.b();
            }
            if ((n2 & 2) != 0 && (!this.D || (n2 & 4) == 0) && amy2.i()) {
                this.h(cj2);
            }
            if (!this.D && (n2 & 1) != 0) {
                this.b(cj2, \u26033.c());
                if (\u26032.O()) {
                    this.e(cj2, \u26032);
                }
            }
            return true;
        }
        return false;
    }

    public boolean g(cj cj2) {
        return this.a(cj2, afi.a.Q(), 3);
    }

    public boolean b(cj cj2, boolean bl2) {
        alz alz2 = this.p(cj2);
        afh \u26032 = alz2.c();
        if (\u26032.t() == arm.a) {
            return false;
        }
        this.b(2001, cj2, afh.f(alz2));
        if (bl2) {
            \u26032.b(this, cj2, alz2, 0);
        }
        return this.a(cj2, afi.a.Q(), 3);
    }

    public boolean a(cj cj2, alz alz2) {
        return this.a(cj2, alz2, 3);
    }

    public void h(cj cj2) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(cj2);
        }
    }

    public void b(cj cj2, afh afh2) {
        if (this.x.u() != adr.g) {
            this.c(cj2, afh2);
        }
    }

    public void a(int n2, int n3, int n4, int n5) {
        if (n4 > n5) {
            \u2603 = n5;
            n5 = n4;
            n4 = \u2603;
        }
        if (!this.t.o()) {
            for (\u2603 = n4; \u2603 <= n5; ++\u2603) {
                this.c(ads.a, new cj(n2, \u2603, n3));
            }
        }
        this.b(n2, n4, n3, n2, n5, n3);
    }

    public void b(cj cj2, cj cj3) {
        this.b(cj2.n(), cj2.o(), cj2.p(), cj3.n(), cj3.o(), cj3.p());
    }

    public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        for (\u2603 = 0; \u2603 < this.u.size(); ++\u2603) {
            this.u.get(\u2603).a(n2, n3, n4, n5, n6, n7);
        }
    }

    public void c(cj cj2, afh afh2) {
        this.d(cj2.e(), afh2);
        this.d(cj2.f(), afh2);
        this.d(cj2.b(), afh2);
        this.d(cj2.a(), afh2);
        this.d(cj2.c(), afh2);
        this.d(cj2.d(), afh2);
    }

    public void a(cj cj2, afh afh2, cq cq2) {
        if (cq2 != cq.e) {
            this.d(cj2.e(), afh2);
        }
        if (cq2 != cq.f) {
            this.d(cj2.f(), afh2);
        }
        if (cq2 != cq.a) {
            this.d(cj2.b(), afh2);
        }
        if (cq2 != cq.b) {
            this.d(cj2.a(), afh2);
        }
        if (cq2 != cq.c) {
            this.d(cj2.c(), afh2);
        }
        if (cq2 != cq.d) {
            this.d(cj2.d(), afh2);
        }
    }

    public void d(cj cj2, final afh afh2) {
        if (this.D) {
            return;
        }
        alz alz2 = this.p(cj2);
        try {
            alz2.c().a(this, cj2, alz2, afh2);
        }
        catch (Throwable \u26032) {
            b b2 = b.a(\u26032, "Exception while updating neighbours");
            c \u26033 = b2.a("Block being updated");
            \u26033.a("Source block type", new Callable<String>(){

                public String a() throws Exception {
                    try {
                        return String.format("ID #%d (%s // %s)", afh.a(afh2), afh2.a(), afh2.getClass().getCanonicalName());
                    }
                    catch (Throwable throwable) {
                        return "ID #" + afh.a(afh2);
                    }
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            c.a(\u26033, cj2, alz2);
            throw new e(b2);
        }
    }

    public boolean a(cj cj2, afh afh2) {
        return false;
    }

    public boolean i(cj cj2) {
        return this.f(cj2).d(cj2);
    }

    public boolean j(cj cj2) {
        if (cj2.o() >= this.F()) {
            return this.i(cj2);
        }
        \u26032 = new cj(cj2.n(), this.F(), cj2.p());
        if (!this.i(\u26032)) {
            return false;
        }
        \u26032 = \u26032.b();
        while (\u26032.o() > cj2.o()) {
            afh afh2 = this.p(\u26032).c();
            if (afh2.p() > 0 && !afh2.t().d()) {
                return false;
            }
            cj \u26032 = \u26032.b();
        }
        return true;
    }

    public int k(cj cj2) {
        if (cj2.o() < 0) {
            return 0;
        }
        if (cj2.o() >= 256) {
            cj2 = new cj(cj2.n(), 255, cj2.p());
        }
        return this.f(cj2).a(cj2, 0);
    }

    public int l(cj cj2) {
        return this.c(cj2, true);
    }

    public int c(cj cj22, boolean bl2) {
        cj cj22;
        if (cj22.n() < -30000000 || cj22.p() < -30000000 || cj22.n() >= 30000000 || cj22.p() >= 30000000) {
            return 15;
        }
        if (bl2 && this.p(cj22).c().s()) {
            int n2 = this.c(cj22.a(), false);
            \u2603 = this.c(cj22.f(), false);
            \u2603 = this.c(cj22.e(), false);
            \u2603 = this.c(cj22.d(), false);
            \u2603 = this.c(cj22.c(), false);
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            return n2;
        }
        if (cj22.o() < 0) {
            return 0;
        }
        if (cj22.o() >= 256) {
            cj22 = new cj(cj22.n(), 255, cj22.p());
        }
        amy \u26032 = this.f(cj22);
        return \u26032.a(cj22, this.I);
    }

    public cj m(cj cj2) {
        int n2 = cj2.n() < -30000000 || cj2.p() < -30000000 || cj2.n() >= 30000000 || cj2.p() >= 30000000 ? this.F() + 1 : (this.a(cj2.n() >> 4, cj2.p() >> 4, true) ? this.a(cj2.n() >> 4, cj2.p() >> 4).b(cj2.n() & 0xF, cj2.p() & 0xF) : 0);
        return new cj(cj2.n(), n2, cj2.p());
    }

    public int b(int n2, int n3) {
        if (n2 < -30000000 || n3 < -30000000 || n2 >= 30000000 || n3 >= 30000000) {
            return this.F() + 1;
        }
        if (!this.a(n2 >> 4, n3 >> 4, true)) {
            return 0;
        }
        amy amy2 = this.a(n2 >> 4, n3 >> 4);
        return amy2.v();
    }

    public int a(ads ads2, cj cj22) {
        cj cj22;
        if (this.t.o() && ads2 == ads.a) {
            return 0;
        }
        if (cj22.o() < 0) {
            cj22 = new cj(cj22.n(), 0, cj22.p());
        }
        if (!this.a(cj22)) {
            return ads2.c;
        }
        if (!this.e(cj22)) {
            return ads2.c;
        }
        if (this.p(cj22).c().s()) {
            int n2 = this.b(ads2, cj22.a());
            \u2603 = this.b(ads2, cj22.f());
            \u2603 = this.b(ads2, cj22.e());
            \u2603 = this.b(ads2, cj22.d());
            \u2603 = this.b(ads2, cj22.c());
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            if (\u2603 > n2) {
                n2 = \u2603;
            }
            return n2;
        }
        amy \u26032 = this.f(cj22);
        return \u26032.a(ads2, cj22);
    }

    public int b(ads ads2, cj cj2) {
        if (cj2.o() < 0) {
            cj2 = new cj(cj2.n(), 0, cj2.p());
        }
        if (!this.a(cj2)) {
            return ads2.c;
        }
        if (!this.e(cj2)) {
            return ads2.c;
        }
        amy amy2 = this.f(cj2);
        return amy2.a(ads2, cj2);
    }

    public void a(ads ads2, cj cj2, int n2) {
        if (!this.a(cj2)) {
            return;
        }
        if (!this.e(cj2)) {
            return;
        }
        amy amy2 = this.f(cj2);
        amy2.a(ads2, cj2, n2);
        this.n(cj2);
    }

    public void n(cj cj2) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).b(cj2);
        }
    }

    @Override
    public int b(cj cj2, int n2) {
        \u2603 = this.a(ads.a, cj2);
        \u2603 = this.a(ads.b, cj2);
        if (\u2603 < n2) {
            \u2603 = n2;
        }
        return \u2603 << 20 | \u2603 << 4;
    }

    public float o(cj cj2) {
        return this.t.p()[this.l(cj2)];
    }

    @Override
    public alz p(cj cj2) {
        if (!this.a(cj2)) {
            return afi.a.Q();
        }
        amy amy2 = this.f(cj2);
        return amy2.g(cj2);
    }

    public boolean w() {
        return this.I < 4;
    }

    public auh a(aui aui2, aui aui3) {
        return this.a(aui2, aui3, false, false, false);
    }

    public auh a(aui aui2, aui aui3, boolean bl2) {
        return this.a(aui2, aui3, bl2, false, false);
    }

    public auh a(aui \u2603142, aui aui2, boolean bl2, boolean bl3, boolean bl42) {
        boolean bl42;
        if (Double.isNaN(\u2603142.a) || Double.isNaN(\u2603142.b) || Double.isNaN(\u2603142.c)) {
            return null;
        }
        if (Double.isNaN(aui2.a) || Double.isNaN(aui2.b) || Double.isNaN(aui2.c)) {
            return null;
        }
        int n2 = ns.c(aui2.a);
        n3 = ns.c(aui2.b);
        \u2603 = ns.c(aui2.c);
        \u2603 = ns.c(\u2603142.a);
        \u2603 = ns.c(\u2603142.b);
        \u2603 = ns.c(\u2603142.c);
        cj \u26032 = new cj(\u2603, \u2603, \u2603);
        Object \u26033 = this.p(\u26032);
        afh \u26034 = \u26033.c();
        if ((!bl3 || \u26034.a(this, \u26032, (alz)\u26033) != null) && \u26034.a((alz)\u26033, bl2) && (\u2603 = \u26034.a(this, \u26032, \u2603142, aui2)) != null) {
            return \u2603;
        }
        \u26033 = null;
        \u2603 = 200;
        while (\u2603-- >= 0) {
            aui \u2603142;
            cq \u260315;
            if (Double.isNaN(\u2603142.a) || Double.isNaN(\u2603142.b) || Double.isNaN(\u2603142.c)) {
                return null;
            }
            if (\u2603 == n2 && \u2603 == n3 && \u2603 == \u2603) {
                return bl42 ? \u26033 : null;
            }
            boolean bl5 = true;
            \u2603 = true;
            \u2603 = true;
            double \u26035 = 999.0;
            double \u26036 = 999.0;
            double \u26037 = 999.0;
            if (n2 > \u2603) {
                \u26035 = (double)\u2603 + 1.0;
            } else if (n2 < \u2603) {
                \u26035 = (double)\u2603 + 0.0;
            } else {
                bl5 = false;
            }
            if (n3 > \u2603) {
                \u26036 = (double)\u2603 + 1.0;
            } else if (n3 < \u2603) {
                \u26036 = (double)\u2603 + 0.0;
            } else {
                \u2603 = false;
            }
            if (\u2603 > \u2603) {
                \u26037 = (double)\u2603 + 1.0;
            } else if (\u2603 < \u2603) {
                \u26037 = (double)\u2603 + 0.0;
            } else {
                \u2603 = false;
            }
            double \u26038 = 999.0;
            double \u26039 = 999.0;
            double \u260310 = 999.0;
            double \u260311 = aui2.a - \u2603142.a;
            double \u260312 = aui2.b - \u2603142.b;
            double \u260313 = aui2.c - \u2603142.c;
            if (bl5) {
                \u26038 = (\u26035 - \u2603142.a) / \u260311;
            }
            if (\u2603) {
                \u26039 = (\u26036 - \u2603142.b) / \u260312;
            }
            if (\u2603) {
                \u260310 = (\u26037 - \u2603142.c) / \u260313;
            }
            if (\u26038 == -0.0) {
                \u26038 = -1.0E-4;
            }
            if (\u26039 == -0.0) {
                \u26039 = -1.0E-4;
            }
            if (\u260310 == -0.0) {
                \u260310 = -1.0E-4;
            }
            if (\u26038 < \u26039 && \u26038 < \u260310) {
                \u260315 = n2 > \u2603 ? cq.e : cq.f;
                \u2603142 = new aui(\u26035, \u2603142.b + \u260312 * \u26038, \u2603142.c + \u260313 * \u26038);
            } else if (\u26039 < \u260310) {
                int n3;
                \u260315 = n3 > \u2603 ? cq.a : cq.b;
                \u2603142 = new aui(\u2603142.a + \u260311 * \u26039, \u26036, \u2603142.c + \u260313 * \u26039);
            } else {
                \u260315 = \u2603 > \u2603 ? cq.c : cq.d;
                \u2603142 = new aui(\u2603142.a + \u260311 * \u260310, \u2603142.b + \u260312 * \u260310, \u26037);
            }
            \u2603 = ns.c(\u2603142.a) - (\u260315 == cq.f ? 1 : 0);
            \u2603 = ns.c(\u2603142.b) - (\u260315 == cq.b ? 1 : 0);
            \u2603 = ns.c(\u2603142.c) - (\u260315 == cq.d ? 1 : 0);
            \u26032 = new cj(\u2603, \u2603, \u2603);
            alz alz2 = this.p(\u26032);
            afh \u260316 = alz2.c();
            if (bl3 && \u260316.a(this, \u26032, alz2) == null) continue;
            if (\u260316.a(alz2, bl2)) {
                auh auh2 = \u260316.a(this, \u26032, \u2603142, aui2);
                if (auh2 == null) continue;
                return auh2;
            }
            \u26033 = new auh(auh.a.a, \u2603142, \u260315, \u26032);
        }
        return bl42 ? \u26033 : null;
    }

    public void a(pk pk2, String string, float f2, float f3) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(string, pk2.s, pk2.t, pk2.u, f2, f3);
        }
    }

    public void a(wn wn2, String string, float f2, float f3) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(wn2, string, wn2.s, wn2.t, wn2.u, f2, f3);
        }
    }

    public void a(double d2, double d3, double d4, String string, float f2, float f3) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(string, d2, d3, d4, f2, f3);
        }
    }

    public void a(double d2, double d3, double d4, String string, float f2, float f3, boolean bl2) {
    }

    public void a(cj cj2, String string) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(string, cj2);
        }
    }

    public void a(cy cy2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
        this.a(cy2.c(), cy2.e(), d2, d3, d4, d5, d6, d7, nArray);
    }

    public void a(cy cy2, boolean bl2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
        this.a(cy2.c(), cy2.e() | bl2, d2, d3, d4, d5, d6, d7, nArray);
    }

    private void a(int n2, boolean bl2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(n2, bl2, d2, d3, d4, d5, d6, d7, nArray);
        }
    }

    public boolean c(pk pk2) {
        this.k.add(pk2);
        return true;
    }

    public boolean d(pk pk2) {
        int n2 = ns.c(pk2.s / 16.0);
        \u2603 = ns.c(pk2.u / 16.0);
        boolean \u26032 = pk2.n;
        if (pk2 instanceof wn) {
            \u26032 = true;
        }
        if (\u26032 || this.a(n2, \u2603, true)) {
            if (pk2 instanceof wn) {
                wn wn2 = (wn)pk2;
                this.j.add(wn2);
                this.d();
            }
            this.a(n2, \u2603).a(pk2);
            this.f.add(pk2);
            this.a(pk2);
            return true;
        }
        return false;
    }

    protected void a(pk pk2) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).a(pk2);
        }
    }

    protected void b(pk pk2) {
        for (int i2 = 0; i2 < this.u.size(); ++i2) {
            this.u.get(i2).b(pk2);
        }
    }

    public void e(pk pk2) {
        if (pk2.l != null) {
            pk2.l.a((pk)null);
        }
        if (pk2.m != null) {
            pk2.a((pk)null);
        }
        pk2.J();
        if (pk2 instanceof wn) {
            this.j.remove(pk2);
            this.d();
            this.b(pk2);
        }
    }

    public void f(pk pk2) {
        pk2.J();
        if (pk2 instanceof wn) {
            this.j.remove(pk2);
            this.d();
        }
        int n2 = pk2.ae;
        \u2603 = pk2.ag;
        if (pk2.ad && this.a(n2, \u2603, true)) {
            this.a(n2, \u2603).b(pk2);
        }
        this.f.remove(pk2);
        this.b(pk2);
    }

    public void a(ado ado2) {
        this.u.add(ado2);
    }

    public void b(ado ado2) {
        this.u.remove(ado2);
    }

    public List<aug> a(pk pk2, aug aug2) {
        ArrayList<aug> arrayList = Lists.newArrayList();
        int \u26032 = ns.c(aug2.a);
        int \u26033 = ns.c(aug2.d + 1.0);
        int \u26034 = ns.c(aug2.b);
        int \u26035 = ns.c(aug2.e + 1.0);
        int \u26036 = ns.c(aug2.c);
        int \u26037 = ns.c(aug2.f + 1.0);
        ams \u26038 = this.af();
        boolean \u26039 = pk2.aT();
        boolean \u260310 = this.a(\u26038, pk2);
        alz \u260311 = afi.b.Q();
        cj.a \u260312 = new cj.a();
        for (int i2 = \u26032; i2 < \u26033; ++i2) {
            for (\u2603 = \u26036; \u2603 < \u26037; ++\u2603) {
                if (!this.e(\u260312.c(i2, 64, \u2603))) continue;
                for (\u2603 = \u26034 - 1; \u2603 < \u26035; ++\u2603) {
                    \u260312.c(i2, \u2603, \u2603);
                    if (\u26039 && \u260310) {
                        pk2.h(false);
                    } else if (!\u26039 && !\u260310) {
                        pk2.h(true);
                    }
                    alz alz2 = \u260311;
                    if (\u26038.a(\u260312) || !\u260310) {
                        alz2 = this.p(\u260312);
                    }
                    alz2.c().a(this, \u260312, alz2, aug2, arrayList, pk2);
                }
            }
        }
        double d2 = 0.25;
        List<pk> \u260313 = this.b(pk2, aug2.b(d2, d2, d2));
        for (int i3 = 0; i3 < \u260313.size(); ++i3) {
            if (pk2.l == \u260313 || pk2.m == \u260313) continue;
            aug aug3 = \u260313.get(i3).S();
            if (aug3 != null && aug3.b(aug2)) {
                arrayList.add(aug3);
            }
            if ((aug3 = pk2.j(\u260313.get(i3))) == null || !aug3.b(aug2)) continue;
            arrayList.add(aug3);
        }
        return arrayList;
    }

    public boolean a(ams ams2, pk pk2) {
        double d2 = ams2.b();
        \u2603 = ams2.c();
        \u2603 = ams2.d();
        \u2603 = ams2.e();
        if (pk2.aT()) {
            d2 += 1.0;
            \u2603 += 1.0;
            \u2603 -= 1.0;
            \u2603 -= 1.0;
        } else {
            d2 -= 1.0;
            \u2603 -= 1.0;
            \u2603 += 1.0;
            \u2603 += 1.0;
        }
        return pk2.s > d2 && pk2.s < \u2603 && pk2.u > \u2603 && pk2.u < \u2603;
    }

    public List<aug> a(aug aug2) {
        ArrayList<aug> arrayList = Lists.newArrayList();
        int \u26032 = ns.c(aug2.a);
        int \u26033 = ns.c(aug2.d + 1.0);
        int \u26034 = ns.c(aug2.b);
        int \u26035 = ns.c(aug2.e + 1.0);
        int \u26036 = ns.c(aug2.c);
        int \u26037 = ns.c(aug2.f + 1.0);
        cj.a \u26038 = new cj.a();
        for (int i2 = \u26032; i2 < \u26033; ++i2) {
            for (\u2603 = \u26036; \u2603 < \u26037; ++\u2603) {
                if (!this.e(\u26038.c(i2, 64, \u2603))) continue;
                for (\u2603 = \u26034 - 1; \u2603 < \u26035; ++\u2603) {
                    \u26038.c(i2, \u2603, \u2603);
                    alz alz2 = i2 < -30000000 || i2 >= 30000000 || \u2603 < -30000000 || \u2603 >= 30000000 ? afi.h.Q() : this.p(\u26038);
                    alz2.c().a(this, \u26038, alz2, aug2, arrayList, null);
                }
            }
        }
        return arrayList;
    }

    public int a(float f2) {
        \u2603 = this.c(f2);
        \u2603 = 1.0f - (ns.b(\u2603 * (float)Math.PI * 2.0f) * 2.0f + 0.5f);
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = 1.0f - \u2603;
        \u2603 = (float)((double)\u2603 * (1.0 - (double)(this.j(f2) * 5.0f) / 16.0));
        \u2603 = (float)((double)\u2603 * (1.0 - (double)(this.h(f2) * 5.0f) / 16.0));
        \u2603 = 1.0f - \u2603;
        return (int)(\u2603 * 11.0f);
    }

    public float b(float f2) {
        \u2603 = this.c(f2);
        \u2603 = 1.0f - (ns.b(\u2603 * (float)Math.PI * 2.0f) * 2.0f + 0.2f);
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = 1.0f - \u2603;
        \u2603 = (float)((double)\u2603 * (1.0 - (double)(this.j(f2) * 5.0f) / 16.0));
        \u2603 = (float)((double)\u2603 * (1.0 - (double)(this.h(f2) * 5.0f) / 16.0));
        return \u2603 * 0.8f + 0.2f;
    }

    public aui a(pk pk2, float f2) {
        float f3;
        \u2603 = this.c(f2);
        \u2603 = ns.b(\u2603 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        int n2 = ns.c(pk2.s);
        \u2603 = ns.c(pk2.t);
        \u2603 = ns.c(pk2.u);
        cj \u26032 = new cj(n2, \u2603, \u2603);
        ady \u26033 = this.b(\u26032);
        float \u26034 = \u26033.a(\u26032);
        \u2603 = \u26033.a(\u26034);
        float \u26035 = (float)(\u2603 >> 16 & 0xFF) / 255.0f;
        float \u26036 = (float)(\u2603 >> 8 & 0xFF) / 255.0f;
        float \u26037 = (float)(\u2603 & 0xFF) / 255.0f;
        \u26035 *= \u2603;
        \u26036 *= \u2603;
        \u26037 *= \u2603;
        float \u26038 = this.j(f2);
        if (\u26038 > 0.0f) {
            f3 = (\u26035 * 0.3f + \u26036 * 0.59f + \u26037 * 0.11f) * 0.6f;
            \u2603 = 1.0f - \u26038 * 0.75f;
            \u26035 = \u26035 * \u2603 + f3 * (1.0f - \u2603);
            \u26036 = \u26036 * \u2603 + f3 * (1.0f - \u2603);
            \u26037 = \u26037 * \u2603 + f3 * (1.0f - \u2603);
        }
        if ((f3 = this.h(f2)) > 0.0f) {
            \u2603 = (\u26035 * 0.3f + \u26036 * 0.59f + \u26037 * 0.11f) * 0.2f;
            \u2603 = 1.0f - f3 * 0.75f;
            \u26035 = \u26035 * \u2603 + \u2603 * (1.0f - \u2603);
            \u26036 = \u26036 * \u2603 + \u2603 * (1.0f - \u2603);
            \u26037 = \u26037 * \u2603 + \u2603 * (1.0f - \u2603);
        }
        if (this.J > 0) {
            \u2603 = (float)this.J - f2;
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            \u26035 = \u26035 * (1.0f - (\u2603 *= 0.45f)) + 0.8f * \u2603;
            \u26036 = \u26036 * (1.0f - \u2603) + 0.8f * \u2603;
            \u26037 = \u26037 * (1.0f - \u2603) + 1.0f * \u2603;
        }
        return new aui(\u26035, \u26036, \u26037);
    }

    public float c(float f2) {
        return this.t.a(this.x.g(), f2);
    }

    public int x() {
        return this.t.a(this.x.g());
    }

    public float y() {
        return anm.a[this.t.a(this.x.g())];
    }

    public float d(float f2) {
        \u2603 = this.c(f2);
        return \u2603 * (float)Math.PI * 2.0f;
    }

    public aui e(float f2) {
        \u2603 = this.c(f2);
        \u2603 = ns.b(\u2603 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = (float)(this.d >> 16 & 0xFFL) / 255.0f;
        \u2603 = (float)(this.d >> 8 & 0xFFL) / 255.0f;
        \u2603 = (float)(this.d & 0xFFL) / 255.0f;
        \u2603 = this.j(f2);
        if (\u2603 > 0.0f) {
            \u2603 = (\u2603 * 0.3f + \u2603 * 0.59f + \u2603 * 0.11f) * 0.6f;
            \u2603 = 1.0f - \u2603 * 0.95f;
            \u2603 = \u2603 * \u2603 + \u2603 * (1.0f - \u2603);
            \u2603 = \u2603 * \u2603 + \u2603 * (1.0f - \u2603);
            \u2603 = \u2603 * \u2603 + \u2603 * (1.0f - \u2603);
        }
        \u2603 *= \u2603 * 0.9f + 0.1f;
        \u2603 *= \u2603 * 0.9f + 0.1f;
        \u2603 *= \u2603 * 0.85f + 0.15f;
        \u2603 = this.h(f2);
        if (\u2603 > 0.0f) {
            \u2603 = (\u2603 * 0.3f + \u2603 * 0.59f + \u2603 * 0.11f) * 0.2f;
            \u2603 = 1.0f - \u2603 * 0.95f;
            \u2603 = \u2603 * \u2603 + \u2603 * (1.0f - \u2603);
            \u2603 = \u2603 * \u2603 + \u2603 * (1.0f - \u2603);
            \u2603 = \u2603 * \u2603 + \u2603 * (1.0f - \u2603);
        }
        return new aui(\u2603, \u2603, \u2603);
    }

    public aui f(float f2) {
        \u2603 = this.c(f2);
        return this.t.b(\u2603, f2);
    }

    public cj q(cj cj2) {
        return this.f(cj2).h(cj2);
    }

    public cj r(cj cj2) {
        amy amy2 = this.f(cj2);
        cj \u26032 = new cj(cj2.n(), amy2.g() + 16, cj2.p());
        while (!(\u26032.o() < 0 || (\u2603 = amy2.a(\u2603 = \u26032.b()).t()).c() && \u2603 != arm.j)) {
            \u26032 = \u2603;
        }
        return \u26032;
    }

    public float g(float f2) {
        \u2603 = this.c(f2);
        \u2603 = 1.0f - (ns.b(\u2603 * (float)Math.PI * 2.0f) * 2.0f + 0.25f);
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        return \u2603 * \u2603 * 0.5f;
    }

    public void a(cj cj2, afh afh2, int n2) {
    }

    public void a(cj cj2, afh afh2, int n2, int n3) {
    }

    public void b(cj cj2, afh afh2, int n2, int n3) {
    }

    public void i() {
        int \u26035;
        int \u26034;
        Object object;
        int n2;
        this.B.a("entities");
        this.B.a("global");
        for (n2 = 0; n2 < this.k.size(); ++n2) {
            object = this.k.get(n2);
            try {
                ++((pk)object).W;
                ((pk)object).t_();
            }
            catch (Throwable \u26032) {
                b b2 = b.a(\u26032, "Ticking entity");
                c \u26033 = b2.a("Entity being ticked");
                if (object == null) {
                    \u26033.a("Entity", "~~NULL~~");
                } else {
                    ((pk)object).a(\u26033);
                }
                throw new e(b2);
            }
            if (!((pk)object).I) continue;
            this.k.remove(n2--);
        }
        this.B.c("remove");
        this.f.removeAll(this.g);
        for (n2 = 0; n2 < this.g.size(); ++n2) {
            object = this.g.get(n2);
            \u26034 = ((pk)object).ae;
            \u26035 = ((pk)object).ag;
            if (!((pk)object).ad || !this.a(\u26034, \u26035, true)) continue;
            this.a(\u26034, \u26035).b((pk)object);
        }
        for (n2 = 0; n2 < this.g.size(); ++n2) {
            this.b(this.g.get(n2));
        }
        this.g.clear();
        this.B.c("regular");
        for (n2 = 0; n2 < this.f.size(); ++n2) {
            object = this.f.get(n2);
            if (((pk)object).m != null) {
                if (!((pk)object).m.I && ((pk)object).m.l == object) continue;
                ((pk)object).m.l = null;
                ((pk)object).m = null;
            }
            this.B.a("tick");
            if (!((pk)object).I) {
                try {
                    this.g((pk)object);
                }
                catch (Throwable throwable) {
                    b b3 = b.a(throwable, "Ticking entity");
                    c \u26036 = b3.a("Entity being ticked");
                    ((pk)object).a(\u26036);
                    throw new e(b3);
                }
            }
            this.B.b();
            this.B.a("remove");
            if (((pk)object).I) {
                \u26034 = ((pk)object).ae;
                \u26035 = ((pk)object).ag;
                if (((pk)object).ad && this.a(\u26034, \u26035, true)) {
                    this.a(\u26034, \u26035).b((pk)object);
                }
                this.f.remove(n2--);
                this.b((pk)object);
            }
            this.B.b();
        }
        this.B.c("blockEntities");
        this.M = true;
        Iterator<akw> iterator = this.i.iterator();
        while (iterator.hasNext()) {
            object = iterator.next();
            if (!((akw)object).x() && ((akw)object).t() && this.e(\u2603 = ((akw)object).v()) && this.N.a(\u2603)) {
                try {
                    ((km)object).c();
                }
                catch (Throwable throwable) {
                    b b4 = b.a(throwable, "Ticking block entity");
                    c \u26037 = b4.a("Block entity being ticked");
                    ((akw)object).a(\u26037);
                    throw new e(b4);
                }
            }
            if (!((akw)object).x()) continue;
            iterator.remove();
            this.h.remove(object);
            if (!this.e(((akw)object).v())) continue;
            this.f(((akw)object).v()).e(((akw)object).v());
        }
        this.M = false;
        if (!this.c.isEmpty()) {
            this.i.removeAll(this.c);
            this.h.removeAll(this.c);
            this.c.clear();
        }
        this.B.c("pendingBlockEntities");
        if (!this.b.isEmpty()) {
            for (int i2 = 0; i2 < this.b.size(); ++i2) {
                akw akw2 = this.b.get(i2);
                if (akw2.x()) continue;
                if (!this.h.contains(akw2)) {
                    this.a(akw2);
                }
                if (this.e(akw2.v())) {
                    this.f(akw2.v()).a(akw2.v(), akw2);
                }
                this.h(akw2.v());
            }
            this.b.clear();
        }
        this.B.b();
        this.B.b();
    }

    public boolean a(akw akw2) {
        boolean bl2 = this.h.add(akw2);
        if (bl2 && akw2 instanceof km) {
            this.i.add(akw2);
        }
        return bl2;
    }

    public void a(Collection<akw> collection) {
        if (this.M) {
            this.b.addAll(collection);
        } else {
            for (akw akw2 : collection) {
                this.h.add(akw2);
                if (!(akw2 instanceof km)) continue;
                this.i.add(akw2);
            }
        }
    }

    public void g(pk pk2) {
        this.a(pk2, true);
    }

    public void a(pk pk2, boolean bl2) {
        int n2 = ns.c(pk2.s);
        \u2603 = ns.c(pk2.u);
        \u2603 = 32;
        if (bl2 && !this.a(n2 - \u2603, 0, \u2603 - \u2603, n2 + \u2603, 0, \u2603 + \u2603, true)) {
            return;
        }
        pk2.P = pk2.s;
        pk2.Q = pk2.t;
        pk2.R = pk2.u;
        pk2.A = pk2.y;
        pk2.B = pk2.z;
        if (bl2 && pk2.ad) {
            ++pk2.W;
            if (pk2.m != null) {
                pk2.ak();
            } else {
                pk2.t_();
            }
        }
        this.B.a("chunkCheck");
        if (Double.isNaN(pk2.s) || Double.isInfinite(pk2.s)) {
            pk2.s = pk2.P;
        }
        if (Double.isNaN(pk2.t) || Double.isInfinite(pk2.t)) {
            pk2.t = pk2.Q;
        }
        if (Double.isNaN(pk2.u) || Double.isInfinite(pk2.u)) {
            pk2.u = pk2.R;
        }
        if (Double.isNaN(pk2.z) || Double.isInfinite(pk2.z)) {
            pk2.z = pk2.B;
        }
        if (Double.isNaN(pk2.y) || Double.isInfinite(pk2.y)) {
            pk2.y = pk2.A;
        }
        \u2603 = ns.c(pk2.s / 16.0);
        \u2603 = ns.c(pk2.t / 16.0);
        \u2603 = ns.c(pk2.u / 16.0);
        if (!pk2.ad || pk2.ae != \u2603 || pk2.af != \u2603 || pk2.ag != \u2603) {
            if (pk2.ad && this.a(pk2.ae, pk2.ag, true)) {
                this.a(pk2.ae, pk2.ag).a(pk2, pk2.af);
            }
            if (this.a(\u2603, \u2603, true)) {
                pk2.ad = true;
                this.a(\u2603, \u2603).a(pk2);
            } else {
                pk2.ad = false;
            }
        }
        this.B.b();
        if (bl2 && pk2.ad && pk2.l != null) {
            if (pk2.l.I || pk2.l.m != pk2) {
                pk2.l.m = null;
                pk2.l = null;
            } else {
                this.g(pk2.l);
            }
        }
    }

    public boolean b(aug aug2) {
        return this.a(aug2, (pk)null);
    }

    public boolean a(aug aug2, pk pk2) {
        List<pk> list = this.b(null, aug2);
        for (int i2 = 0; i2 < list.size(); ++i2) {
            pk pk3 = list.get(i2);
            if (pk3.I || !pk3.k || pk3 == pk2 || pk2 != null && (pk2.m == pk3 || pk2.l == pk3)) continue;
            return false;
        }
        return true;
    }

    public boolean c(aug aug2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.d);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.e);
        \u2603 = ns.c(aug2.c);
        \u2603 = ns.c(aug2.f);
        cj.a \u26032 = new cj.a();
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    afh afh2 = this.p(\u26032.c(\u2603, \u2603, \u2603)).c();
                    if (afh2.t() == arm.a) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean d(aug aug2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.d);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.e);
        \u2603 = ns.c(aug2.c);
        \u2603 = ns.c(aug2.f);
        cj.a \u26032 = new cj.a();
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    afh afh2 = this.p(\u26032.c(\u2603, \u2603, \u2603)).c();
                    if (!afh2.t().d()) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean e(aug aug2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.d + 1.0);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.e + 1.0);
        \u2603 = ns.c(aug2.c);
        if (this.a(n2, \u2603, \u2603, \u2603, \u2603, \u2603 = ns.c(aug2.f + 1.0), true)) {
            cj.a a2 = new cj.a();
            for (int i2 = n2; i2 < \u2603; ++i2) {
                for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                    for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                        afh afh2 = this.p(a2.c(i2, \u2603, \u2603)).c();
                        if (afh2 != afi.ab && afh2 != afi.k && afh2 != afi.l) continue;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean a(aug aug2, arm arm2, pk pk2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.d + 1.0);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.e + 1.0);
        \u2603 = ns.c(aug2.c);
        if (!this.a(n2, \u2603, \u2603, \u2603, \u2603, \u2603 = ns.c(aug2.f + 1.0), true)) {
            return false;
        }
        boolean \u26032 = false;
        aui \u26033 = new aui(0.0, 0.0, 0.0);
        cj.a \u26034 = new cj.a();
        for (\u2603 = n2; \u2603 < \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                    \u26034.c(\u2603, \u2603, \u2603);
                    alz alz2 = this.p(\u26034);
                    afh \u26035 = alz2.c();
                    if (\u26035.t() != arm2 || !((double)\u2603 >= (\u2603 = (double)((float)(\u2603 + 1) - ahv.b(alz2.b(ahv.b)))))) continue;
                    \u26032 = true;
                    \u26033 = \u26035.a(this, (cj)\u26034, pk2, \u26033);
                }
            }
        }
        if (\u26033.b() > 0.0 && pk2.aL()) {
            \u26033 = \u26033.a();
            double d2 = 0.014;
            pk2.v += \u26033.a * d2;
            pk2.w += \u26033.b * d2;
            pk2.x += \u26033.c * d2;
        }
        return \u26032;
    }

    public boolean a(aug aug2, arm arm2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.d + 1.0);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.e + 1.0);
        \u2603 = ns.c(aug2.c);
        \u2603 = ns.c(aug2.f + 1.0);
        cj.a \u26032 = new cj.a();
        for (\u2603 = n2; \u2603 < \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                    if (this.p(\u26032.c(\u2603, \u2603, \u2603)).c().t() != arm2) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b(aug aug2, arm arm2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.d + 1.0);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.e + 1.0);
        \u2603 = ns.c(aug2.c);
        \u2603 = ns.c(aug2.f + 1.0);
        cj.a \u26032 = new cj.a();
        for (\u2603 = n2; \u2603 < \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 < \u2603; ++\u2603) {
                    alz alz2 = this.p(\u26032.c(\u2603, \u2603, \u2603));
                    afh \u26033 = alz2.c();
                    if (\u26033.t() != arm2) continue;
                    int \u26034 = alz2.b(ahv.b);
                    double \u26035 = \u2603 + 1;
                    if (\u26034 < 8) {
                        \u26035 = (double)(\u2603 + 1) - (double)\u26034 / 8.0;
                    }
                    if (!(\u26035 >= aug2.b)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public adi a(pk pk2, double d2, double d3, double d4, float f2, boolean bl2) {
        return this.a(pk2, d2, d3, d4, f2, false, bl2);
    }

    public adi a(pk pk2, double d2, double d3, double d4, float f2, boolean bl2, boolean bl3) {
        adi adi2 = new adi(this, pk2, d2, d3, d4, f2, bl2, bl3);
        adi2.a();
        adi2.a(true);
        return adi2;
    }

    public float a(aui aui2, aug aug2) {
        double d2 = 1.0 / ((aug2.d - aug2.a) * 2.0 + 1.0);
        \u2603 = 1.0 / ((aug2.e - aug2.b) * 2.0 + 1.0);
        \u2603 = 1.0 / ((aug2.f - aug2.c) * 2.0 + 1.0);
        \u2603 = (1.0 - Math.floor(1.0 / d2) * d2) / 2.0;
        \u2603 = (1.0 - Math.floor(1.0 / \u2603) * \u2603) / 2.0;
        if (d2 < 0.0 || \u2603 < 0.0 || \u2603 < 0.0) {
            return 0.0f;
        }
        int \u26032 = 0;
        int \u26033 = 0;
        float \u26034 = 0.0f;
        while (\u26034 <= 1.0f) {
            float f2 = 0.0f;
            while (f2 <= 1.0f) {
                \u26035 = 0.0f;
                while (\u26035 <= 1.0f) {
                    double d3 = aug2.a + (aug2.d - aug2.a) * (double)\u26034;
                    \u2603 = aug2.b + (aug2.e - aug2.b) * (double)f2;
                    \u2603 = aug2.c + (aug2.f - aug2.c) * (double)\u26035;
                    if (this.a(new aui(d3 + \u2603, \u2603, \u2603 + \u2603), aui2) == null) {
                        ++\u26032;
                    }
                    ++\u26033;
                    float \u26035 = (float)((double)\u26035 + \u2603);
                }
                f2 = (float)((double)f2 + \u2603);
            }
            \u26034 = (float)((double)\u26034 + d2);
        }
        return (float)\u26032 / (float)\u26033;
    }

    public boolean a(wn wn2, cj cj2, cq cq2) {
        if (this.p(cj2 = cj2.a(cq2)).c() == afi.ab) {
            this.a(wn2, 1004, cj2, 0);
            this.g(cj2);
            return true;
        }
        return false;
    }

    public String z() {
        return "All: " + this.f.size();
    }

    public String A() {
        return this.v.f();
    }

    @Override
    public akw s(cj cj2) {
        akw akw2;
        int n2;
        if (!this.a(cj2)) {
            return null;
        }
        akw akw3 = null;
        if (this.M) {
            for (n2 = 0; n2 < this.b.size(); ++n2) {
                akw2 = this.b.get(n2);
                if (akw2.x() || !akw2.v().equals(cj2)) continue;
                akw3 = akw2;
                break;
            }
        }
        if (akw3 == null) {
            akw3 = this.f(cj2).a(cj2, amy.a.a);
        }
        if (akw3 == null) {
            for (n2 = 0; n2 < this.b.size(); ++n2) {
                akw2 = this.b.get(n2);
                if (akw2.x() || !akw2.v().equals(cj2)) continue;
                akw3 = akw2;
                break;
            }
        }
        return akw3;
    }

    public void a(cj cj2, akw akw22) {
        if (akw22 != null && !akw22.x()) {
            akw akw22;
            if (this.M) {
                akw22.a(cj2);
                Iterator<akw> iterator = this.b.iterator();
                while (iterator.hasNext()) {
                    akw akw3 = iterator.next();
                    if (!akw3.v().equals(cj2)) continue;
                    akw3.y();
                    iterator.remove();
                }
                this.b.add(akw22);
            } else {
                this.a(akw22);
                this.f(cj2).a(cj2, akw22);
            }
        }
    }

    public void t(cj cj2) {
        akw akw2 = this.s(cj2);
        if (akw2 != null && this.M) {
            akw2.y();
            this.b.remove(akw2);
        } else {
            if (akw2 != null) {
                this.b.remove(akw2);
                this.h.remove(akw2);
                this.i.remove(akw2);
            }
            this.f(cj2).e(cj2);
        }
    }

    public void b(akw akw2) {
        this.c.add(akw2);
    }

    public boolean u(cj cj2) {
        alz alz2 = this.p(cj2);
        aug \u26032 = alz2.c().a(this, cj2, alz2);
        return \u26032 != null && \u26032.a() >= 1.0;
    }

    public static boolean a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        afh \u26032 = alz2.c();
        if (\u26032.t().k() && \u26032.d()) {
            return true;
        }
        if (\u26032 instanceof aju) {
            return alz2.b(aju.b) == aju.a.a;
        }
        if (\u26032 instanceof ahh) {
            return alz2.b(ahh.a) == ahh.a.a;
        }
        if (\u26032 instanceof ahn) {
            return true;
        }
        if (\u26032 instanceof ajp) {
            return alz2.b(ajp.a) == 7;
        }
        return false;
    }

    public boolean d(cj cj2, boolean bl2) {
        if (!this.a(cj2)) {
            return bl2;
        }
        amy amy2 = this.v.a(cj2);
        if (amy2.f()) {
            return bl2;
        }
        afh \u26032 = this.p(cj2).c();
        return \u26032.t().k() && \u26032.d();
    }

    public void B() {
        int n2 = this.a(1.0f);
        if (n2 != this.I) {
            this.I = n2;
        }
    }

    public void a(boolean bl2, boolean bl3) {
        this.F = bl2;
        this.G = bl3;
    }

    public void c() {
        this.p();
    }

    protected void C() {
        if (this.x.p()) {
            this.p = 1.0f;
            if (this.x.n()) {
                this.r = 1.0f;
            }
        }
    }

    protected void p() {
        if (this.t.o()) {
            return;
        }
        if (this.D) {
            return;
        }
        int n2 = this.x.A();
        if (n2 > 0) {
            this.x.i(--n2);
            this.x.f(this.x.n() ? 1 : 2);
            this.x.g(this.x.p() ? 1 : 2);
        }
        if ((\u2603 = this.x.o()) <= 0) {
            if (this.x.n()) {
                this.x.f(this.s.nextInt(12000) + 3600);
            } else {
                this.x.f(this.s.nextInt(168000) + 12000);
            }
        } else {
            this.x.f(--\u2603);
            if (\u2603 <= 0) {
                this.x.a(!this.x.n());
            }
        }
        this.q = this.r;
        this.r = this.x.n() ? (float)((double)this.r + 0.01) : (float)((double)this.r - 0.01);
        this.r = ns.a(this.r, 0.0f, 1.0f);
        \u2603 = this.x.q();
        if (\u2603 <= 0) {
            if (this.x.p()) {
                this.x.g(this.s.nextInt(12000) + 12000);
            } else {
                this.x.g(this.s.nextInt(168000) + 12000);
            }
        } else {
            this.x.g(--\u2603);
            if (\u2603 <= 0) {
                this.x.b(!this.x.p());
            }
        }
        this.o = this.p;
        this.p = this.x.p() ? (float)((double)this.p + 0.01) : (float)((double)this.p - 0.01);
        this.p = ns.a(this.p, 0.0f, 1.0f);
    }

    protected void D() {
        int \u26034;
        int \u26033;
        int \u26032;
        wn \u26035;
        int n2;
        this.E.clear();
        this.B.a("buildList");
        for (n2 = 0; n2 < this.j.size(); ++n2) {
            \u26035 = this.j.get(n2);
            \u26032 = ns.c(\u26035.s / 16.0);
            \u26033 = ns.c(\u26035.u / 16.0);
            \u26034 = this.q();
            for (int i2 = -\u26034; i2 <= \u26034; ++i2) {
                for (\u2603 = -\u26034; \u2603 <= \u26034; ++\u2603) {
                    this.E.add(new adg(i2 + \u26032, \u2603 + \u26033));
                }
            }
        }
        this.B.b();
        if (this.L > 0) {
            --this.L;
        }
        this.B.a("playerCheckLight");
        if (!this.j.isEmpty()) {
            n2 = this.s.nextInt(this.j.size());
            \u26035 = this.j.get(n2);
            \u26032 = ns.c(\u26035.s) + this.s.nextInt(11) - 5;
            \u26033 = ns.c(\u26035.t) + this.s.nextInt(11) - 5;
            \u26034 = ns.c(\u26035.u) + this.s.nextInt(11) - 5;
            this.x(new cj(\u26032, \u26033, \u26034));
        }
        this.B.b();
    }

    protected abstract int q();

    protected void a(int n2, int n3, amy amy2) {
        this.B.c("moodSound");
        if (this.L == 0 && !this.D) {
            this.m = this.m * 3 + 1013904223;
            int n4 = this.m >> 2;
            \u2603 = n4 & 0xF;
            \u2603 = n4 >> 8 & 0xF;
            \u2603 = n4 >> 16 & 0xFF;
            cj \u26032 = new cj(\u2603, \u2603, \u2603);
            afh \u26033 = amy2.a(\u26032);
            if (\u26033.t() == arm.a && this.k(\u26032) <= this.s.nextInt(8) && this.b(ads.a, \u26032) <= 0 && (\u2603 = this.a((double)(\u2603 += n2) + 0.5, (double)\u2603 + 0.5, (double)(\u2603 += n3) + 0.5, 8.0)) != null && \u2603.e((double)\u2603 + 0.5, (double)\u2603 + 0.5, (double)\u2603 + 0.5) > 4.0) {
                this.a((double)\u2603 + 0.5, (double)\u2603 + 0.5, (double)\u2603 + 0.5, "ambient.cave.cave", 0.7f, 0.8f + this.s.nextFloat() * 0.2f);
                this.L = this.s.nextInt(12000) + 6000;
            }
        }
        this.B.c("checkLight");
        amy2.m();
    }

    protected void h() {
        this.D();
    }

    public void a(afh afh2, cj cj2, Random random) {
        this.e = true;
        afh2.b(this, cj2, this.p(cj2), random);
        this.e = false;
    }

    public boolean v(cj cj2) {
        return this.e(cj2, false);
    }

    public boolean w(cj cj2) {
        return this.e(cj2, true);
    }

    public boolean e(cj cj2, boolean bl2) {
        ady ady2 = this.b(cj2);
        float \u26032 = ady2.a(cj2);
        if (\u26032 > 0.15f) {
            return false;
        }
        if (cj2.o() >= 0 && cj2.o() < 256 && this.b(ads.b, cj2) < 10 && ((\u2603 = (\u2603 = this.p(cj2)).c()) == afi.j || \u2603 == afi.i) && \u2603.b(ahv.b) == 0) {
            if (!bl2) {
                return true;
            }
            boolean bl3 = \u2603 = this.F(cj2.e()) && this.F(cj2.f()) && this.F(cj2.c()) && this.F(cj2.d());
            if (!\u2603) {
                return true;
            }
        }
        return false;
    }

    private boolean F(cj cj2) {
        return this.p(cj2).c().t() == arm.h;
    }

    public boolean f(cj cj2, boolean bl2) {
        ady ady2 = this.b(cj2);
        float \u26032 = ady2.a(cj2);
        if (\u26032 > 0.15f) {
            return false;
        }
        if (!bl2) {
            return true;
        }
        return cj2.o() >= 0 && cj2.o() < 256 && this.b(ads.b, cj2) < 10 && (\u2603 = this.p(cj2).c()).t() == arm.a && afi.aH.d(this, cj2);
    }

    public boolean x(cj cj2) {
        boolean bl2 = false;
        if (!this.t.o()) {
            bl2 |= this.c(ads.a, cj2);
        }
        return bl2 |= this.c(ads.b, cj2);
    }

    private int a(cj cj2, ads ads2) {
        if (ads2 == ads.a && this.i(cj2)) {
            return 15;
        }
        afh afh2 = this.p(cj2).c();
        int \u26032 = ads2 == ads.a ? 0 : afh2.r();
        int \u26033 = afh2.p();
        if (\u26033 >= 15 && afh2.r() > 0) {
            \u26033 = 1;
        }
        if (\u26033 < 1) {
            \u26033 = 1;
        }
        if (\u26033 >= 15) {
            return 0;
        }
        if (\u26032 >= 14) {
            return \u26032;
        }
        for (cq cq2 : cq.values()) {
            cj cj3 = cj2.a(cq2);
            int \u26034 = this.b(ads2, cj3) - \u26033;
            if (\u26034 > \u26032) {
                \u26032 = \u26034;
            }
            if (\u26032 < 14) continue;
            return \u26032;
        }
        return \u26032;
    }

    public boolean c(ads ads2, cj cj2) {
        int \u26032;
        if (!this.a(cj2, 17, false)) {
            return false;
        }
        int n2 = 0;
        \u2603 = 0;
        this.B.a("getBrightness");
        \u2603 = this.b(ads2, cj2);
        \u2603 = this.a(cj2, ads2);
        \u2603 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        if (\u2603 > \u2603) {
            this.H[\u2603++] = 133152;
        } else if (\u2603 < \u2603) {
            this.H[\u2603++] = 0x20820 | \u2603 << 18;
            while (n2 < \u2603) {
                \u2603 = this.H[n2++];
                \u2603 = (\u2603 & 0x3F) - 32 + \u2603;
                \u2603 = (\u2603 >> 6 & 0x3F) - 32 + \u2603;
                \u2603 = (\u2603 >> 12 & 0x3F) - 32 + \u2603;
                \u2603 = \u2603 >> 18 & 0xF;
                cj cj3 = new cj(\u2603, \u2603, \u2603);
                \u26032 = this.b(ads2, cj3);
                if (\u26032 != \u2603) continue;
                this.a(ads2, cj3, 0);
                if (\u2603 <= 0 || (\u26035 = ns.a(\u2603 - \u2603)) + (\u26036 = ns.a(\u2603 - \u2603)) + (\u26037 = ns.a(\u2603 - \u2603)) >= 17) continue;
                cj.a \u26033 = new cj.a();
                for (cq cq2 : cq.values()) {
                    int n3 = \u2603 + cq2.g();
                    \u2603 = \u2603 + cq2.h();
                    \u2603 = \u2603 + cq2.i();
                    \u26033.c(n3, \u2603, \u2603);
                    \u2603 = Math.max(1, this.p(\u26033).c().p());
                    \u26032 = this.b(ads2, (cj)\u26033);
                    if (\u26032 != \u2603 - \u2603 || \u2603 >= this.H.length) continue;
                    this.H[\u2603++] = n3 - \u2603 + 32 | \u2603 - \u2603 + 32 << 6 | \u2603 - \u2603 + 32 << 12 | \u2603 - \u2603 << 18;
                }
            }
            n2 = 0;
        }
        this.B.b();
        this.B.a("checkedPosition < toCheckCount");
        while (n2 < \u2603) {
            \u2603 = this.H[n2++];
            \u2603 = (\u2603 & 0x3F) - 32 + \u2603;
            \u2603 = (\u2603 >> 6 & 0x3F) - 32 + \u2603;
            \u2603 = (\u2603 >> 12 & 0x3F) - 32 + \u2603;
            cj cj4 = new cj(\u2603, \u2603, \u2603);
            int \u26034 = this.b(ads2, cj4);
            \u26032 = this.a(cj4, ads2);
            if (\u26032 == \u26034) continue;
            this.a(ads2, cj4, \u26032);
            if (\u26032 <= \u26034) continue;
            int \u26035 = Math.abs(\u2603 - \u2603);
            int \u26036 = Math.abs(\u2603 - \u2603);
            int \u26037 = Math.abs(\u2603 - \u2603);
            boolean bl2 = \u2603 = \u2603 < this.H.length - 6;
            if (\u26035 + \u26036 + \u26037 >= 17 || !\u2603) continue;
            if (this.b(ads2, cj4.e()) < \u26032) {
                this.H[\u2603++] = \u2603 - 1 - \u2603 + 32 + (\u2603 - \u2603 + 32 << 6) + (\u2603 - \u2603 + 32 << 12);
            }
            if (this.b(ads2, cj4.f()) < \u26032) {
                this.H[\u2603++] = \u2603 + 1 - \u2603 + 32 + (\u2603 - \u2603 + 32 << 6) + (\u2603 - \u2603 + 32 << 12);
            }
            if (this.b(ads2, cj4.b()) < \u26032) {
                this.H[\u2603++] = \u2603 - \u2603 + 32 + (\u2603 - 1 - \u2603 + 32 << 6) + (\u2603 - \u2603 + 32 << 12);
            }
            if (this.b(ads2, cj4.a()) < \u26032) {
                this.H[\u2603++] = \u2603 - \u2603 + 32 + (\u2603 + 1 - \u2603 + 32 << 6) + (\u2603 - \u2603 + 32 << 12);
            }
            if (this.b(ads2, cj4.c()) < \u26032) {
                this.H[\u2603++] = \u2603 - \u2603 + 32 + (\u2603 - \u2603 + 32 << 6) + (\u2603 - 1 - \u2603 + 32 << 12);
            }
            if (this.b(ads2, cj4.d()) >= \u26032) continue;
            this.H[\u2603++] = \u2603 - \u2603 + 32 + (\u2603 - \u2603 + 32 << 6) + (\u2603 + 1 - \u2603 + 32 << 12);
        }
        this.B.b();
        return true;
    }

    public boolean a(boolean bl2) {
        return false;
    }

    public List<adw> a(amy amy2, boolean bl2) {
        return null;
    }

    public List<adw> a(aqe aqe2, boolean bl2) {
        return null;
    }

    public List<pk> b(pk pk2, aug aug2) {
        return this.a(pk2, aug2, po.d);
    }

    public List<pk> a(pk pk2, aug aug2, Predicate<? super pk> predicate) {
        ArrayList<pk> arrayList = Lists.newArrayList();
        int \u26032 = ns.c((aug2.a - 2.0) / 16.0);
        int \u26033 = ns.c((aug2.d + 2.0) / 16.0);
        int \u26034 = ns.c((aug2.c - 2.0) / 16.0);
        int \u26035 = ns.c((aug2.f + 2.0) / 16.0);
        for (int i2 = \u26032; i2 <= \u26033; ++i2) {
            for (\u2603 = \u26034; \u2603 <= \u26035; ++\u2603) {
                if (!this.a(i2, \u2603, true)) continue;
                this.a(i2, \u2603).a(pk2, aug2, arrayList, predicate);
            }
        }
        return arrayList;
    }

    public <T extends pk> List<T> a(Class<? extends T> clazz, Predicate<? super T> predicate) {
        ArrayList<pk> arrayList = Lists.newArrayList();
        for (pk pk2 : this.f) {
            if (!clazz.isAssignableFrom(pk2.getClass()) || !predicate.apply(pk2)) continue;
            arrayList.add(pk2);
        }
        return arrayList;
    }

    public <T extends pk> List<T> b(Class<? extends T> clazz, Predicate<? super T> predicate) {
        ArrayList<pk> arrayList = Lists.newArrayList();
        for (pk pk2 : this.j) {
            if (!clazz.isAssignableFrom(pk2.getClass()) || !predicate.apply(pk2)) continue;
            arrayList.add(pk2);
        }
        return arrayList;
    }

    public <T extends pk> List<T> a(Class<? extends T> clazz, aug aug2) {
        return this.a(clazz, aug2, (T)((Object)po.d));
    }

    public <T extends pk> List<T> a(Class<? extends T> clazz, aug aug2, Predicate<? super T> predicate) {
        int n2 = ns.c((aug2.a - 2.0) / 16.0);
        \u2603 = ns.c((aug2.d + 2.0) / 16.0);
        \u2603 = ns.c((aug2.c - 2.0) / 16.0);
        \u2603 = ns.c((aug2.f + 2.0) / 16.0);
        ArrayList \u26032 = Lists.newArrayList();
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                if (!this.a(\u2603, \u2603, true)) continue;
                this.a(\u2603, \u2603).a(clazz, aug2, \u26032, predicate);
            }
        }
        return \u26032;
    }

    public <T extends pk> T a(Class<? extends T> clazz, aug aug2, T t2) {
        List<T> list = this.a(clazz, aug2);
        pk \u26032 = null;
        double \u26033 = Double.MAX_VALUE;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            pk pk2 = (pk)list.get(i2);
            if (pk2 == t2 || !po.d.apply(pk2) || (\u2603 = t2.h(pk2)) > \u26033) continue;
            \u26032 = pk2;
            \u26033 = \u2603;
        }
        return (T)\u26032;
    }

    public pk a(int n2) {
        return this.l.a(n2);
    }

    public List<pk> E() {
        return this.f;
    }

    public void b(cj cj2, akw akw2) {
        if (this.e(cj2)) {
            this.f(cj2).e();
        }
    }

    public int a(Class<?> clazz) {
        int n2 = 0;
        for (pk pk2 : this.f) {
            if (pk2 instanceof ps && ((ps)pk2).bZ() || !clazz.isAssignableFrom(pk2.getClass())) continue;
            ++n2;
        }
        return n2;
    }

    public void b(Collection<pk> collection) {
        this.f.addAll(collection);
        for (pk pk2 : collection) {
            this.a(pk2);
        }
    }

    public void c(Collection<pk> collection) {
        this.g.addAll(collection);
    }

    public boolean a(afh afh2, cj cj2, boolean bl2, cq cq2, pk pk2, zx zx2) {
        afh afh3 = this.p(cj2).c();
        aug aug2 = \u2603 = bl2 ? null : afh2.a(this, cj2, afh2.Q());
        if (\u2603 != null && !this.a(\u2603, pk2)) {
            return false;
        }
        if (afh3.t() == arm.q && afh2 == afi.cf) {
            return true;
        }
        return afh3.t().j() && afh2.a(this, cj2, cq2, zx2);
    }

    public int F() {
        return this.a;
    }

    public void b(int n2) {
        this.a = n2;
    }

    @Override
    public int a(cj cj2, cq cq2) {
        alz alz2 = this.p(cj2);
        return alz2.c().b((adq)this, cj2, alz2, cq2);
    }

    @Override
    public adr G() {
        return this.x.u();
    }

    public int y(cj cj2) {
        int n2 = 0;
        if ((n2 = Math.max(n2, this.a(cj2.b(), cq.a))) >= 15) {
            return n2;
        }
        if ((n2 = Math.max(n2, this.a(cj2.a(), cq.b))) >= 15) {
            return n2;
        }
        if ((n2 = Math.max(n2, this.a(cj2.c(), cq.c))) >= 15) {
            return n2;
        }
        if ((n2 = Math.max(n2, this.a(cj2.d(), cq.d))) >= 15) {
            return n2;
        }
        if ((n2 = Math.max(n2, this.a(cj2.e(), cq.e))) >= 15) {
            return n2;
        }
        if ((n2 = Math.max(n2, this.a(cj2.f(), cq.f))) >= 15) {
            return n2;
        }
        return n2;
    }

    public boolean b(cj cj2, cq cq2) {
        return this.c(cj2, cq2) > 0;
    }

    public int c(cj cj2, cq cq2) {
        alz alz2 = this.p(cj2);
        afh \u26032 = alz2.c();
        if (\u26032.v()) {
            return this.y(cj2);
        }
        return \u26032.a((adq)this, cj2, alz2, cq2);
    }

    public boolean z(cj cj2) {
        if (this.c(cj2.b(), cq.a) > 0) {
            return true;
        }
        if (this.c(cj2.a(), cq.b) > 0) {
            return true;
        }
        if (this.c(cj2.c(), cq.c) > 0) {
            return true;
        }
        if (this.c(cj2.d(), cq.d) > 0) {
            return true;
        }
        if (this.c(cj2.e(), cq.e) > 0) {
            return true;
        }
        return this.c(cj2.f(), cq.f) > 0;
    }

    public int A(cj cj2) {
        int n2 = 0;
        for (cq cq2 : cq.values()) {
            int n3 = this.c(cj2.a(cq2), cq2);
            if (n3 >= 15) {
                return 15;
            }
            if (n3 <= n2) continue;
            n2 = n3;
        }
        return n2;
    }

    public wn a(pk pk2, double d2) {
        return this.a(pk2.s, pk2.t, pk2.u, d2);
    }

    public wn a(double d2, double d3, double d4, double d5) {
        \u26033 = -1.0;
        wn wn2 = null;
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            wn wn3 = this.j.get(i2);
            if (!po.d.apply(wn3)) continue;
            double \u26032 = wn3.e(d2, d3, d4);
            if (!(d5 < 0.0) && !(\u26032 < d5 * d5) || \u26033 != -1.0 && !(\u26032 < \u26033)) continue;
            double \u26033 = \u26032;
            wn2 = wn3;
        }
        return wn2;
    }

    public boolean b(double d2, double d3, double d4, double d5) {
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            wn wn2 = this.j.get(i2);
            if (!po.d.apply(wn2)) continue;
            double \u26032 = wn2.e(d2, d3, d4);
            if (!(d5 < 0.0) && !(\u26032 < d5 * d5)) continue;
            return true;
        }
        return false;
    }

    public wn a(String string) {
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            wn wn2 = this.j.get(i2);
            if (!string.equals(wn2.e_())) continue;
            return wn2;
        }
        return null;
    }

    public wn b(UUID uUID) {
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            wn wn2 = this.j.get(i2);
            if (!uUID.equals(wn2.aK())) continue;
            return wn2;
        }
        return null;
    }

    public void H() {
    }

    public void I() throws adn {
        this.w.c();
    }

    public void a(long l2) {
        this.x.b(l2);
    }

    public long J() {
        return this.x.b();
    }

    public long K() {
        return this.x.f();
    }

    public long L() {
        return this.x.g();
    }

    public void b(long l2) {
        this.x.c(l2);
    }

    public cj M() {
        cj cj2 = new cj(this.x.c(), this.x.d(), this.x.e());
        if (!this.af().a(cj2)) {
            cj2 = this.m(new cj(this.af().f(), 0.0, this.af().g()));
        }
        return cj2;
    }

    public void B(cj cj2) {
        this.x.a(cj2);
    }

    public void h(pk pk2) {
        int n2 = ns.c(pk2.s / 16.0);
        \u2603 = ns.c(pk2.u / 16.0);
        \u2603 = 2;
        for (\u2603 = n2 - \u2603; \u2603 <= n2 + \u2603; ++\u2603) {
            for (\u2603 = \u2603 - \u2603; \u2603 <= \u2603 + \u2603; ++\u2603) {
                this.a(\u2603, \u2603);
            }
        }
        if (!this.f.contains(pk2)) {
            this.f.add(pk2);
        }
    }

    public boolean a(wn wn2, cj cj2) {
        return true;
    }

    public void a(pk pk2, byte by) {
    }

    public amv N() {
        return this.v;
    }

    public void c(cj cj2, afh afh2, int n2, int n3) {
        afh2.a(this, cj2, this.p(cj2), n2, n3);
    }

    public atp O() {
        return this.w;
    }

    public ato P() {
        return this.x;
    }

    public adk Q() {
        return this.x.x();
    }

    public void d() {
    }

    public float h(float f2) {
        return (this.q + (this.r - this.q) * f2) * this.j(f2);
    }

    public void i(float f2) {
        this.q = f2;
        this.r = f2;
    }

    public float j(float f2) {
        return this.o + (this.p - this.o) * f2;
    }

    public void k(float f2) {
        this.o = f2;
        this.p = f2;
    }

    public boolean R() {
        return (double)this.h(1.0f) > 0.9;
    }

    public boolean S() {
        return (double)this.j(1.0f) > 0.2;
    }

    public boolean C(cj cj2) {
        if (!this.S()) {
            return false;
        }
        if (!this.i(cj2)) {
            return false;
        }
        if (this.q(cj2).o() > cj2.o()) {
            return false;
        }
        ady ady2 = this.b(cj2);
        if (ady2.d()) {
            return false;
        }
        if (this.f(cj2, false)) {
            return false;
        }
        return ady2.e();
    }

    public boolean D(cj cj2) {
        ady ady2 = this.b(cj2);
        return ady2.f();
    }

    public aua T() {
        return this.z;
    }

    public void a(String string, ate ate2) {
        this.z.a(string, ate2);
    }

    public ate a(Class<? extends ate> clazz, String string) {
        return this.z.a(clazz, string);
    }

    public int b(String string) {
        return this.z.a(string);
    }

    public void a(int n2, cj cj2, int n3) {
        for (\u2603 = 0; \u2603 < this.u.size(); ++\u2603) {
            this.u.get(\u2603).a(n2, cj2, n3);
        }
    }

    public void b(int n2, cj cj2, int n3) {
        this.a(null, n2, cj2, n3);
    }

    public void a(wn wn2, int n2, cj cj2, int n3) {
        try {
            for (\u2603 = 0; \u2603 < this.u.size(); ++\u2603) {
                this.u.get(\u2603).a(wn2, n2, cj2, n3);
            }
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Playing level event");
            c \u26032 = b2.a("Level event being played");
            \u26032.a("Block coordinates", c.a(cj2));
            \u26032.a("Event source", wn2);
            \u26032.a("Event type", n2);
            \u26032.a("Event data", n3);
            throw new e(b2);
        }
    }

    public int U() {
        return 256;
    }

    public int V() {
        return this.t.o() ? 128 : 256;
    }

    public Random a(int n2, int n3, int n4) {
        long l2 = (long)n2 * 341873128712L + (long)n3 * 132897987541L + this.P().b() + (long)n4;
        this.s.setSeed(l2);
        return this.s;
    }

    public cj a(String string, cj cj2) {
        return this.N().a(this, string, cj2);
    }

    @Override
    public boolean W() {
        return false;
    }

    public double X() {
        if (this.x.u() == adr.c) {
            return 0.0;
        }
        return 63.0;
    }

    public c a(b b2) {
        c c2 = b2.a("Affected level", 1);
        c2.a("Level name", this.x == null ? "????" : this.x.k());
        c2.a("All players", new Callable<String>(){

            public String a() {
                return adm.this.j.size() + " total; " + adm.this.j.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Chunk stats", new Callable<String>(){

            public String a() {
                return adm.this.v.f();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        try {
            this.x.a(c2);
        }
        catch (Throwable \u26032) {
            c2.a("Level Data Unobtainable", \u26032);
        }
        return c2;
    }

    public void c(int n2, cj cj2, int n3) {
        for (\u2603 = 0; \u2603 < this.u.size(); ++\u2603) {
            ado ado2 = this.u.get(\u2603);
            ado2.b(n2, cj2, n3);
        }
    }

    public Calendar Y() {
        if (this.K() % 600L == 0L) {
            this.K.setTimeInMillis(MinecraftServer.az());
        }
        return this.K;
    }

    public void a(double d2, double d3, double d4, double d5, double d6, double d7, dn dn2) {
    }

    public auo Z() {
        return this.C;
    }

    public void e(cj cj2, afh afh2) {
        for (cq cq2 : cq.c.a) {
            cj cj3 = cj2.a(cq2);
            if (!this.e(cj3)) continue;
            alz \u26032 = this.p(cj3);
            if (afi.cj.e(\u26032.c())) {
                \u26032.c().a(this, cj3, \u26032, afh2);
                continue;
            }
            if (!\u26032.c().v() || !afi.cj.e((\u26032 = this.p(cj3 = cj3.a(cq2))).c())) continue;
            \u26032.c().a(this, cj3, \u26032, afh2);
        }
    }

    public ok E(cj cj2) {
        long l2 = 0L;
        float \u26032 = 0.0f;
        if (this.e(cj2)) {
            \u26032 = this.y();
            l2 = this.f(cj2).w();
        }
        return new ok(this.aa(), this.L(), l2, \u26032);
    }

    public oj aa() {
        return this.P().y();
    }

    public int ab() {
        return this.I;
    }

    public void c(int n2) {
        this.I = n2;
    }

    public int ac() {
        return this.J;
    }

    public void d(int n2) {
        this.J = n2;
    }

    public boolean ad() {
        return this.y;
    }

    public th ae() {
        return this.A;
    }

    public ams af() {
        return this.N;
    }

    public boolean c(int n2, int n3) {
        cj cj2 = this.M();
        int \u26032 = n2 * 16 + 8 - cj2.n();
        int \u26033 = n3 * 16 + 8 - cj2.p();
        int \u26034 = 128;
        return \u26032 >= -\u26034 && \u26032 <= \u26034 && \u26033 >= -\u26034 && \u26033 <= \u26034;
    }
}

