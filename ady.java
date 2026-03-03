/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ady {
    private static final Logger aD = LogManager.getLogger();
    protected static final a a = new a(0.1f, 0.2f);
    protected static final a b = new a(-0.5f, 0.0f);
    protected static final a c = new a(-1.0f, 0.1f);
    protected static final a d = new a(-1.8f, 0.1f);
    protected static final a e = new a(0.125f, 0.05f);
    protected static final a f = new a(0.2f, 0.2f);
    protected static final a g = new a(0.45f, 0.3f);
    protected static final a h = new a(1.5f, 0.025f);
    protected static final a i = new a(1.0f, 0.5f);
    protected static final a j = new a(0.0f, 0.025f);
    protected static final a k = new a(0.1f, 0.8f);
    protected static final a l = new a(0.2f, 0.3f);
    protected static final a m = new a(-0.2f, 0.1f);
    private static final ady[] aE = new ady[256];
    public static final Set<ady> n = Sets.newHashSet();
    public static final Map<String, ady> o = Maps.newHashMap();
    public static final ady p = new aen(0).b(112).a("Ocean").a(c);
    public static final ady q = new aeo(1).b(9286496).a("Plains");
    public static final ady r = new aed(2).b(16421912).a("Desert").b().a(2.0f, 0.0f).a(e);
    public static final ady s = new aee(3, false).b(0x606060).a("Extreme Hills").a(i).a(0.2f, 0.3f);
    public static final ady t = new aeg(4, 0).b(353825).a("Forest");
    public static final ady u = new aeu(5, 0).b(747097).a("Taiga").a(5159473).a(0.25f, 0.8f).a(f);
    public static final ady v = new aet(6).b(522674).a("Swampland").a(9154376).a(m).a(0.8f, 0.9f);
    public static final ady w = new aeq(7).b(255).a("River").a(b);
    public static final ady x = new aeh(8).b(0xFF0000).a("Hell").b().a(2.0f, 0.0f);
    public static final ady y = new aev(9).b(0x8080FF).a("The End").b();
    public static final ady z = new aen(10).b(0x9090A0).a("FrozenOcean").c().a(c).a(0.0f, 0.5f);
    public static final ady A = new aeq(11).b(0xA0A0FF).a("FrozenRiver").c().a(b).a(0.0f, 0.5f);
    public static final ady B = new aei(12, false).b(0xFFFFFF).a("Ice Plains").c().a(0.0f, 0.5f).a(e);
    public static final ady C = new aei(13, false).b(0xA0A0A0).a("Ice Mountains").c().a(g).a(0.0f, 0.5f);
    public static final ady D = new ael(14).b(0xFF00FF).a("MushroomIsland").a(0.9f, 1.0f).a(l);
    public static final ady E = new ael(15).b(0xA000FF).a("MushroomIslandShore").a(0.9f, 1.0f).a(j);
    public static final ady F = new adx(16).b(16440917).a("Beach").a(0.8f, 0.4f).a(j);
    public static final ady G = new aed(17).b(13786898).a("DesertHills").b().a(2.0f, 0.0f).a(g);
    public static final ady H = new aeg(18, 0).b(2250012).a("ForestHills").a(g);
    public static final ady I = new aeu(19, 0).b(1456435).a("TaigaHills").a(5159473).a(0.25f, 0.8f).a(g);
    public static final ady J = new aee(20, true).b(7501978).a("Extreme Hills Edge").a(i.a()).a(0.2f, 0.3f);
    public static final ady K = new aej(21, false).b(5470985).a("Jungle").a(5470985).a(0.95f, 0.9f);
    public static final ady L = new aej(22, false).b(2900485).a("JungleHills").a(5470985).a(0.95f, 0.9f).a(g);
    public static final ady M = new aej(23, true).b(6458135).a("JungleEdge").a(5470985).a(0.95f, 0.8f);
    public static final ady N = new aen(24).b(48).a("Deep Ocean").a(d);
    public static final ady O = new aes(25).b(10658436).a("Stone Beach").a(0.2f, 0.3f).a(k);
    public static final ady P = new adx(26).b(16445632).a("Cold Beach").a(0.05f, 0.3f).a(j).c();
    public static final ady Q = new aeg(27, 2).a("Birch Forest").b(3175492);
    public static final ady R = new aeg(28, 2).a("Birch Forest Hills").b(2055986).a(g);
    public static final ady S = new aeg(29, 3).b(4215066).a("Roofed Forest");
    public static final ady T = new aeu(30, 0).b(3233098).a("Cold Taiga").a(5159473).c().a(-0.5f, 0.4f).a(f).c(0xFFFFFF);
    public static final ady U = new aeu(31, 0).b(2375478).a("Cold Taiga Hills").a(5159473).c().a(-0.5f, 0.4f).a(g).c(0xFFFFFF);
    public static final ady V = new aeu(32, 1).b(5858897).a("Mega Taiga").a(5159473).a(0.3f, 0.8f).a(f);
    public static final ady W = new aeu(33, 1).b(4542270).a("Mega Taiga Hills").a(5159473).a(0.3f, 0.8f).a(g);
    public static final ady X = new aee(34, true).b(0x507050).a("Extreme Hills+").a(i).a(0.2f, 0.3f);
    public static final ady Y = new aer(35).b(12431967).a("Savanna").a(1.2f, 0.0f).b().a(e);
    public static final ady Z = new aer(36).b(10984804).a("Savanna Plateau").a(1.0f, 0.0f).b().a(h);
    public static final ady aa = new aek(37, false, false).b(14238997).a("Mesa");
    public static final ady ab = new aek(38, false, true).b(11573093).a("Mesa Plateau F").a(h);
    public static final ady ac = new aek(39, false, false).b(13274213).a("Mesa Plateau").a(h);
    public static final ady ad = p;
    protected static final ard ae;
    protected static final ard af;
    protected static final aos ag;
    public String ah;
    public int ai;
    public int aj;
    public alz ak = afi.c.Q();
    public alz al = afi.d.Q();
    public int am = 5169201;
    public float an;
    public float ao;
    public float ap;
    public float aq;
    public int ar;
    public aeb as;
    protected List<c> at;
    protected List<c> au;
    protected List<c> av;
    protected List<c> aw;
    protected boolean ax;
    protected boolean ay;
    public final int az;
    protected apv aA;
    protected aoi aB;
    protected apt aC;

    protected ady(int n2) {
        this.an = ady.a.a;
        this.ao = ady.a.b;
        this.ap = 0.5f;
        this.aq = 0.5f;
        this.ar = 0xFFFFFF;
        this.at = Lists.newArrayList();
        this.au = Lists.newArrayList();
        this.av = Lists.newArrayList();
        this.aw = Lists.newArrayList();
        this.ay = true;
        this.aA = new apv(false);
        this.aB = new aoi(false);
        this.aC = new apt();
        this.az = n2;
        ady.aE[n2] = this;
        this.as = this.a();
        this.au.add(new c(tv.class, 12, 4, 4));
        this.au.add(new c(tu.class, 10, 3, 3));
        this.au.add(new c(tt.class, 10, 4, 4));
        this.au.add(new c(tn.class, 10, 4, 4));
        this.au.add(new c(to.class, 8, 4, 4));
        this.at.add(new c(wc.class, 100, 4, 4));
        this.at.add(new c(we.class, 100, 4, 4));
        this.at.add(new c(wa.class, 100, 4, 4));
        this.at.add(new c(vn.class, 100, 4, 4));
        this.at.add(new c(wb.class, 100, 4, 4));
        this.at.add(new c(vo.class, 10, 1, 4));
        this.at.add(new c(wd.class, 5, 1, 1));
        this.av.add(new c(tx.class, 10, 4, 4));
        this.aw.add(new c(tk.class, 10, 8, 8));
    }

    protected aeb a() {
        return new aeb();
    }

    protected ady a(float f2, float f3) {
        if (f2 > 0.1f && f2 < 0.2f) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        this.ap = f2;
        this.aq = f3;
        return this;
    }

    protected final ady a(a a2) {
        this.an = a2.a;
        this.ao = a2.b;
        return this;
    }

    protected ady b() {
        this.ay = false;
        return this;
    }

    public aoh a(Random random) {
        if (random.nextInt(10) == 0) {
            return this.aB;
        }
        return this.aA;
    }

    public aot b(Random random) {
        return new apu(akc.a.b);
    }

    public agw.a a(Random random, cj cj2) {
        if (random.nextInt(3) > 0) {
            return agw.a.a;
        }
        return agw.a.b;
    }

    protected ady c() {
        this.ax = true;
        return this;
    }

    protected ady a(String string) {
        this.ah = string;
        return this;
    }

    protected ady a(int n2) {
        this.am = n2;
        return this;
    }

    protected ady b(int n2) {
        this.a(n2, false);
        return this;
    }

    protected ady c(int n2) {
        this.aj = n2;
        return this;
    }

    protected ady a(int n2, boolean bl2) {
        this.ai = n2;
        this.aj = bl2 ? (n2 & 0xFEFEFE) >> 1 : n2;
        return this;
    }

    public int a(float f2) {
        f2 /= 3.0f;
        f2 = ns.a(f2, -1.0f, 1.0f);
        return ns.c(0.62222224f - f2 * 0.05f, 0.5f + f2 * 0.1f, 1.0f);
    }

    public List<c> a(pt pt2) {
        switch (pt2) {
            case a: {
                return this.at;
            }
            case b: {
                return this.au;
            }
            case d: {
                return this.av;
            }
            case c: {
                return this.aw;
            }
        }
        return Collections.emptyList();
    }

    public boolean d() {
        return this.j();
    }

    public boolean e() {
        if (this.j()) {
            return false;
        }
        return this.ay;
    }

    public boolean f() {
        return this.aq > 0.85f;
    }

    public float g() {
        return 0.1f;
    }

    public final int h() {
        return (int)(this.aq * 65536.0f);
    }

    public final float i() {
        return this.aq;
    }

    public final float a(cj cj2) {
        if (cj2.o() > 64) {
            float f2 = (float)(ae.a((double)cj2.n() * 1.0 / 8.0, (double)cj2.p() * 1.0 / 8.0) * 4.0);
            return this.ap - (f2 + (float)cj2.o() - 64.0f) * 0.05f / 30.0f;
        }
        return this.ap;
    }

    public void a(adm adm2, Random random, cj cj2) {
        this.as.a(adm2, random, this, cj2);
    }

    public int b(cj cj2) {
        double d2 = ns.a(this.a(cj2), 0.0f, 1.0f);
        \u2603 = ns.a(this.i(), 0.0f, 1.0f);
        return adl.a(d2, \u2603);
    }

    public int c(cj cj2) {
        double d2 = ns.a(this.a(cj2), 0.0f, 1.0f);
        \u2603 = ns.a(this.i(), 0.0f, 1.0f);
        return adj.a(d2, \u2603);
    }

    public boolean j() {
        return this.ax;
    }

    public void a(adm adm2, Random random, ans ans2, int n2, int n3, double d2) {
        this.b(adm2, random, ans2, n2, n3, d2);
    }

    public final void b(adm adm2, Random random, ans ans2, int n2, int n3, double d2) {
        int n4 = adm2.F();
        alz \u26032 = this.ak;
        alz \u26033 = this.al;
        n5 = -1;
        \u2603 = (int)(d2 / 3.0 + 3.0 + random.nextDouble() * 0.25);
        \u2603 = n2 & 0xF;
        \u2603 = n3 & 0xF;
        cj.a \u26034 = new cj.a();
        for (\u2603 = 255; \u2603 >= 0; --\u2603) {
            int n5;
            if (\u2603 <= random.nextInt(5)) {
                ans2.a(\u2603, \u2603, \u2603, afi.h.Q());
                continue;
            }
            alz alz2 = ans2.a(\u2603, \u2603, \u2603);
            if (alz2.c().t() == arm.a) {
                n5 = -1;
                continue;
            }
            if (alz2.c() != afi.b) continue;
            if (n5 == -1) {
                if (\u2603 <= 0) {
                    \u26032 = null;
                    \u26033 = afi.b.Q();
                } else if (\u2603 >= n4 - 4 && \u2603 <= n4 + 1) {
                    \u26032 = this.ak;
                    \u26033 = this.al;
                }
                if (\u2603 < n4 && (\u26032 == null || \u26032.c().t() == arm.a)) {
                    \u26032 = this.a(\u26034.c(n2, \u2603, n3)) < 0.15f ? afi.aI.Q() : afi.j.Q();
                }
                n5 = \u2603;
                if (\u2603 >= n4 - 1) {
                    ans2.a(\u2603, \u2603, \u2603, \u26032);
                    continue;
                }
                if (\u2603 < n4 - 7 - \u2603) {
                    \u26032 = null;
                    \u26033 = afi.b.Q();
                    ans2.a(\u2603, \u2603, \u2603, afi.n.Q());
                    continue;
                }
                ans2.a(\u2603, \u2603, \u2603, \u26033);
                continue;
            }
            if (n5 <= 0) continue;
            ans2.a(\u2603, \u2603, \u2603, \u26033);
            if (--n5 != 0 || \u26033.c() != afi.m) continue;
            n5 = random.nextInt(4) + Math.max(0, \u2603 - 63);
            \u26033 = \u26033.b(ajh.a) == ajh.a.b ? afi.cM.Q() : afi.A.Q();
        }
    }

    protected ady k() {
        return this.d(this.az + 128);
    }

    protected ady d(int n2) {
        return new aem(n2, this);
    }

    public Class<? extends ady> l() {
        return this.getClass();
    }

    public boolean a(ady ady2) {
        if (ady2 == this) {
            return true;
        }
        if (ady2 == null) {
            return false;
        }
        return this.l() == ady2.l();
    }

    public b m() {
        if ((double)this.ap < 0.2) {
            return ady$b.b;
        }
        if ((double)this.ap < 1.0) {
            return ady$b.c;
        }
        return ady$b.d;
    }

    public static ady[] n() {
        return aE;
    }

    public static ady e(int n2) {
        return ady.a(n2, null);
    }

    public static ady a(int n2, ady ady2) {
        if (n2 < 0 || n2 > aE.length) {
            aD.warn("Biome ID is out of bounds: " + n2 + ", defaulting to 0 (Ocean)");
            return p;
        }
        \u2603 = aE[n2];
        if (\u2603 == null) {
            return ady2;
        }
        return \u2603;
    }

    static {
        q.k();
        r.k();
        t.k();
        u.k();
        v.k();
        B.k();
        K.k();
        M.k();
        T.k();
        Y.k();
        Z.k();
        aa.k();
        ab.k();
        ac.k();
        Q.k();
        R.k();
        S.k();
        V.k();
        s.k();
        X.k();
        V.d(ady.W.az + 128).a("Redwood Taiga Hills M");
        for (ady ady2 : aE) {
            if (ady2 == null) continue;
            if (o.containsKey(ady2.ah)) {
                throw new Error("Biome \"" + ady2.ah + "\" is defined as both ID " + ady.o.get((Object)ady2.ah).az + " and " + ady2.az);
            }
            o.put(ady2.ah, ady2);
            if (ady2.az >= 128) continue;
            n.add(ady2);
        }
        n.remove(x);
        n.remove(y);
        n.remove(z);
        n.remove(J);
        ae = new ard(new Random(1234L), 1);
        af = new ard(new Random(2345L), 1);
        ag = new aos();
    }

    public static class c
    extends oa.a {
        public Class<? extends ps> b;
        public int c;
        public int d;

        public c(Class<? extends ps> clazz, int n2, int n3, int n4) {
            super(n2);
            this.b = clazz;
            this.c = n3;
            this.d = n4;
        }

        public String toString() {
            return this.b.getSimpleName() + "*(" + this.c + "-" + this.d + "):" + this.a;
        }
    }

    public static class a {
        public float a;
        public float b;

        public a(float f2, float f3) {
            this.a = f2;
            this.b = f3;
        }

        public a a() {
            return new a(this.a * 0.8f, this.b * 0.6f);
        }
    }

    public static enum b {
        a,
        b,
        c,
        d;

    }
}

