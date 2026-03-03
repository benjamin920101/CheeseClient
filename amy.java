/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class amy {
    private static final Logger c = LogManager.getLogger();
    private final amz[] d = new amz[16];
    private final byte[] e = new byte[256];
    private final int[] f = new int[256];
    private final boolean[] g = new boolean[256];
    private boolean h;
    private final adm i;
    private final int[] j;
    public final int a;
    public final int b;
    private boolean k;
    private final Map<cj, akw> l = Maps.newHashMap();
    private final ne<pk>[] m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private long s;
    private int t;
    private long u;
    private int v = 4096;
    private ConcurrentLinkedQueue<cj> w = Queues.newConcurrentLinkedQueue();

    public amy(adm adm2, int n2, int n3) {
        this.m = new ne[16];
        this.i = adm2;
        this.a = n2;
        this.b = n3;
        this.j = new int[256];
        for (\u2603 = 0; \u2603 < this.m.length; ++\u2603) {
            this.m[\u2603] = new ne<pk>(pk.class);
        }
        Arrays.fill(this.f, -999);
        Arrays.fill(this.e, (byte)-1);
    }

    public amy(adm adm2, ans ans2, int n2, int n3) {
        this(adm2, n2, n3);
        \u2603 = 256;
        boolean bl2 = !adm2.t.o();
        for (int i2 = 0; i2 < 16; ++i2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                    \u2603 = i2 * \u2603 * 16 | \u2603 * \u2603 | \u2603;
                    alz alz2 = ans2.a(\u2603);
                    if (alz2.c().t() == arm.a) continue;
                    int \u26032 = \u2603 >> 4;
                    if (this.d[\u26032] == null) {
                        this.d[\u26032] = new amz(\u26032 << 4, bl2);
                    }
                    this.d[\u26032].a(i2, \u2603 & 0xF, \u2603, alz2);
                }
            }
        }
    }

    public boolean a(int n2, int n3) {
        return n2 == this.a && n3 == this.b;
    }

    public int f(cj cj2) {
        return this.b(cj2.n() & 0xF, cj2.p() & 0xF);
    }

    public int b(int n2, int n3) {
        return this.j[n3 << 4 | n2];
    }

    public int g() {
        for (int i2 = this.d.length - 1; i2 >= 0; --i2) {
            if (this.d[i2] == null) continue;
            return this.d[i2].d();
        }
        return 0;
    }

    public amz[] h() {
        return this.d;
    }

    protected void a() {
        int n2 = this.g();
        this.t = Integer.MAX_VALUE;
        for (\u2603 = 0; \u2603 < 16; ++\u2603) {
            block1: for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                this.f[\u2603 + (\u2603 << 4)] = -999;
                for (\u2603 = n2 + 16; \u2603 > 0; --\u2603) {
                    afh afh2 = this.f(\u2603, \u2603 - 1, \u2603);
                    if (afh2.p() == 0) continue;
                    this.j[\u2603 << 4 | \u2603] = \u2603;
                    if (\u2603 >= this.t) continue block1;
                    this.t = \u2603;
                    continue block1;
                }
            }
        }
        this.q = true;
    }

    public void b() {
        int n2 = this.g();
        this.t = Integer.MAX_VALUE;
        for (\u2603 = 0; \u2603 < 16; ++\u2603) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                this.f[\u2603 + (\u2603 << 4)] = -999;
                for (\u2603 = n2 + 16; \u2603 > 0; --\u2603) {
                    if (this.e(\u2603, \u2603 - 1, \u2603) == 0) continue;
                    this.j[\u2603 << 4 | \u2603] = \u2603;
                    if (\u2603 >= this.t) break;
                    this.t = \u2603;
                    break;
                }
                if (this.i.t.o()) continue;
                \u2603 = 15;
                \u2603 = n2 + 16 - 1;
                do {
                    if ((\u2603 = this.e(\u2603, \u2603, \u2603)) == 0 && \u2603 != 15) {
                        \u2603 = 1;
                    }
                    if ((\u2603 -= \u2603) <= 0 || (\u2603 = this.d[\u2603 >> 4]) == null) continue;
                    \u2603.a(\u2603, \u2603 & 0xF, \u2603, \u2603);
                    this.i.n(new cj((this.a << 4) + \u2603, \u2603, (this.b << 4) + \u2603));
                } while (--\u2603 > 0 && \u2603 > 0);
            }
        }
        this.q = true;
    }

    private void d(int n2, int n3) {
        this.g[n2 + n3 * 16] = true;
        this.k = true;
    }

    private void h(boolean bl22) {
        this.i.B.a("recheckGaps");
        if (this.i.a(new cj(this.a * 16 + 8, 0, this.b * 16 + 8), 16)) {
            for (int i2 = 0; i2 < 16; ++i2) {
                for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                    boolean bl22;
                    int n2;
                    if (!this.g[i2 + \u2603 * 16]) continue;
                    this.g[i2 + \u2603 * 16] = false;
                    \u2603 = this.b(i2, \u2603);
                    n2 = this.a * 16 + i2;
                    \u2603 = this.b * 16 + \u2603;
                    n3 = Integer.MAX_VALUE;
                    for (cq cq2 : cq.c.a) {
                        int n3 = Math.min(n3, this.i.b(n2 + cq2.g(), \u2603 + cq2.i()));
                    }
                    this.c(n2, \u2603, n3);
                    for (cq cq2 : cq.c.a) {
                        this.c(n2 + cq2.g(), \u2603 + cq2.i(), \u2603);
                    }
                    if (!bl22) continue;
                    this.i.B.b();
                    return;
                }
            }
            this.k = false;
        }
        this.i.B.b();
    }

    private void c(int n2, int n3, int n4) {
        \u2603 = this.i.m(new cj(n2, 0, n3)).o();
        if (\u2603 > n4) {
            this.a(n2, n3, n4, \u2603 + 1);
        } else if (\u2603 < n4) {
            this.a(n2, n3, \u2603, n4 + 1);
        }
    }

    private void a(int n2, int n3, int n4, int n5) {
        if (n5 > n4 && this.i.a(new cj(n2, 0, n3), 16)) {
            for (\u2603 = n4; \u2603 < n5; ++\u2603) {
                this.i.c(ads.a, new cj(n2, \u2603, n3));
            }
            this.q = true;
        }
    }

    private void d(int n2, int n3, int n4) {
        int n5;
        \u2603 = \u2603 = this.j[n4 << 4 | n2] & 0xFF;
        if (n3 > \u2603) {
            \u2603 = n3;
        }
        while (\u2603 > 0 && this.e(n2, \u2603 - 1, n4) == 0) {
            --\u2603;
        }
        if (\u2603 == \u2603) {
            return;
        }
        this.i.a(n2 + this.a * 16, n4 + this.b * 16, \u2603, \u2603);
        this.j[n4 << 4 | n2] = \u2603;
        n7 = this.a * 16 + n2;
        \u2603 = this.b * 16 + n4;
        if (!this.i.t.o()) {
            int n6;
            amz amz2;
            if (\u2603 < \u2603) {
                for (n6 = \u2603; n6 < \u2603; ++n6) {
                    amz2 = this.d[n6 >> 4];
                    if (amz2 == null) continue;
                    amz2.a(n2, n6 & 0xF, n4, 15);
                    this.i.n(new cj((this.a << 4) + n2, n6, (this.b << 4) + n4));
                }
            } else {
                for (n6 = \u2603; n6 < \u2603; ++n6) {
                    amz2 = this.d[n6 >> 4];
                    if (amz2 == null) continue;
                    amz2.a(n2, n6 & 0xF, n4, 0);
                    this.i.n(new cj((this.a << 4) + n2, n6, (this.b << 4) + n4));
                }
            }
            n6 = 15;
            while (\u2603 > 0 && n6 > 0) {
                if ((\u2603 = this.e(n2, --\u2603, n4)) == 0) {
                    \u2603 = 1;
                }
                if ((n6 -= \u2603) < 0) {
                    n6 = 0;
                }
                if ((\u2603 = this.d[\u2603 >> 4]) == null) continue;
                \u2603.a(n2, \u2603 & 0xF, n4, n6);
            }
        }
        if ((n5 = (n6 = this.j[n4 << 4 | n2])) < (\u2603 = \u2603)) {
            \u2603 = \u2603;
            \u2603 = n5;
            n5 = \u2603;
        }
        if (n6 < this.t) {
            this.t = n6;
        }
        if (!this.i.t.o()) {
            int n7;
            for (cq cq2 : cq.c.a) {
                this.a(n7 + cq2.g(), \u2603 + cq2.i(), \u2603, n5);
            }
            this.a(n7, \u2603, \u2603, n5);
        }
        this.q = true;
    }

    public int b(cj cj2) {
        return this.a(cj2).p();
    }

    private int e(int n2, int n3, int n4) {
        return this.f(n2, n3, n4).p();
    }

    private afh f(int n2, int n3, int n4) {
        afh afh2 = afi.a;
        if (n3 >= 0 && n3 >> 4 < this.d.length && (\u2603 = this.d[n3 >> 4]) != null) {
            try {
                afh2 = \u2603.b(n2, n3 & 0xF, n4);
            }
            catch (Throwable throwable) {
                b b2 = b.a(throwable, "Getting block");
                throw new e(b2);
            }
        }
        return afh2;
    }

    public afh a(final int n2, final int n3, final int n4) {
        try {
            return this.f(n2 & 0xF, n3, n4 & 0xF);
        }
        catch (e e2) {
            c c2 = e2.a().a("Block being got");
            c2.a("Location", new Callable<String>(){

                public String a() throws Exception {
                    return c.a(new cj(amy.this.a * 16 + n2, n3, amy.this.b * 16 + n4));
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw e2;
        }
    }

    public afh a(final cj cj2) {
        try {
            return this.f(cj2.n() & 0xF, cj2.o(), cj2.p() & 0xF);
        }
        catch (e e2) {
            c c2 = e2.a().a("Block being got");
            c2.a("Location", new Callable<String>(){

                public String a() throws Exception {
                    return c.a(cj2);
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw e2;
        }
    }

    public alz g(final cj cj22) {
        if (this.i.G() == adr.g) {
            alz alz2 = null;
            if (cj22.o() == 60) {
                alz2 = afi.cv.Q();
            }
            if (cj22.o() == 70) {
                alz2 = anu.b(cj22.n(), cj22.p());
            }
            return alz2 == null ? afi.a.Q() : alz2;
        }
        try {
            cj cj22;
            if (cj22.o() >= 0 && cj22.o() >> 4 < this.d.length && (\u2603 = this.d[cj22.o() >> 4]) != null) {
                int n2 = cj22.n() & 0xF;
                \u2603 = cj22.o() & 0xF;
                \u2603 = cj22.p() & 0xF;
                return \u2603.a(n2, \u2603, \u2603);
            }
            return afi.a.Q();
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Getting block state");
            c \u26032 = b2.a("Block being got");
            \u26032.a("Location", new Callable<String>(){

                public String a() throws Exception {
                    return c.a(cj22);
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
    }

    private int g(int n2, int n3, int n4) {
        if (n3 >> 4 >= this.d.length) {
            return 0;
        }
        amz amz2 = this.d[n3 >> 4];
        if (amz2 != null) {
            return amz2.c(n2, n3 & 0xF, n4);
        }
        return 0;
    }

    public int c(cj cj2) {
        return this.g(cj2.n() & 0xF, cj2.o(), cj2.p() & 0xF);
    }

    public alz a(cj cj2, alz alz2) {
        int n2 = cj2.n() & 0xF;
        \u2603 = cj2.o();
        if (\u2603 >= this.f[\u2603 = (\u2603 = cj2.p() & 0xF) << 4 | n2] - 1) {
            this.f[\u2603] = -999;
        }
        \u2603 = this.j[\u2603];
        alz \u26032 = this.g(cj2);
        if (\u26032 == alz2) {
            return null;
        }
        afh \u26033 = alz2.c();
        afh \u26034 = \u26032.c();
        amz \u26035 = this.d[\u2603 >> 4];
        boolean \u26036 = false;
        if (\u26035 == null) {
            if (\u26033 == afi.a) {
                return null;
            }
            amz amz2 = new amz(\u2603 >> 4 << 4, !this.i.t.o());
            this.d[\u2603 >> 4] = amz2;
            \u26035 = amz2;
            \u26036 = \u2603 >= \u2603;
        }
        \u26035.a(n2, \u2603 & 0xF, \u2603, alz2);
        if (\u26034 != \u26033) {
            if (!this.i.D) {
                \u26034.b(this.i, cj2, \u26032);
            } else if (\u26034 instanceof agq) {
                this.i.t(cj2);
            }
        }
        if (\u26035.b(n2, \u2603 & 0xF, \u2603) != \u26033) {
            return null;
        }
        if (\u26036) {
            this.b();
        } else {
            \u2603 = \u26033.p();
            \u2603 = \u26034.p();
            if (\u2603 > 0) {
                if (\u2603 >= \u2603) {
                    this.d(n2, \u2603 + 1, \u2603);
                }
            } else if (\u2603 == \u2603 - 1) {
                this.d(n2, \u2603, \u2603);
            }
            if (\u2603 != \u2603 && (\u2603 < \u2603 || this.a(ads.a, cj2) > 0 || this.a(ads.b, cj2) > 0)) {
                this.d(n2, \u2603);
            }
        }
        if (\u26034 instanceof agq && (\u2603 = this.a(cj2, amy$a.c)) != null) {
            \u2603.E();
        }
        if (!this.i.D && \u26034 != \u26033) {
            \u26033.c(this.i, cj2, alz2);
        }
        if (\u26033 instanceof agq) {
            akw akw2 = this.a(cj2, amy$a.c);
            if (akw2 == null) {
                akw2 = ((agq)((Object)\u26033)).a(this.i, \u26033.c(alz2));
                this.i.a(cj2, akw2);
            }
            if (akw2 != null) {
                akw2.E();
            }
        }
        this.q = true;
        return \u26032;
    }

    public int a(ads ads2, cj cj2) {
        int n2 = cj2.n() & 0xF;
        \u2603 = cj2.o();
        \u2603 = cj2.p() & 0xF;
        amz \u26032 = this.d[\u2603 >> 4];
        if (\u26032 == null) {
            if (this.d(cj2)) {
                return ads2.c;
            }
            return 0;
        }
        if (ads2 == ads.a) {
            if (this.i.t.o()) {
                return 0;
            }
            return \u26032.d(n2, \u2603 & 0xF, \u2603);
        }
        if (ads2 == ads.b) {
            return \u26032.e(n2, \u2603 & 0xF, \u2603);
        }
        return ads2.c;
    }

    public void a(ads ads2, cj cj2, int n2) {
        \u2603 = cj2.n() & 0xF;
        \u2603 = cj2.o();
        \u2603 = cj2.p() & 0xF;
        amz amz2 = this.d[\u2603 >> 4];
        if (amz2 == null) {
            amz amz3 = new amz(\u2603 >> 4 << 4, !this.i.t.o());
            this.d[\u2603 >> 4] = amz3;
            amz2 = amz3;
            this.b();
        }
        this.q = true;
        if (ads2 == ads.a) {
            if (!this.i.t.o()) {
                amz2.a(\u2603, \u2603 & 0xF, \u2603, n2);
            }
        } else if (ads2 == ads.b) {
            amz2.b(\u2603, \u2603 & 0xF, \u2603, n2);
        }
    }

    public int a(cj cj2, int n2) {
        \u2603 = cj2.n() & 0xF;
        \u2603 = cj2.o();
        \u2603 = cj2.p() & 0xF;
        amz amz2 = this.d[\u2603 >> 4];
        if (amz2 == null) {
            if (!this.i.t.o() && n2 < ads.a.c) {
                return ads.a.c - n2;
            }
            return 0;
        }
        int \u26032 = this.i.t.o() ? 0 : amz2.d(\u2603, \u2603 & 0xF, \u2603);
        int \u26033 = amz2.e(\u2603, \u2603 & 0xF, \u2603);
        if (\u26033 > (\u26032 -= n2)) {
            \u26032 = \u26033;
        }
        return \u26032;
    }

    public void a(pk pk2) {
        this.r = true;
        int n2 = ns.c(pk2.s / 16.0);
        \u2603 = ns.c(pk2.u / 16.0);
        if (n2 != this.a || \u2603 != this.b) {
            c.warn("Wrong location! (" + n2 + ", " + \u2603 + ") should be (" + this.a + ", " + this.b + "), " + pk2, pk2);
            pk2.J();
        }
        if ((\u2603 = ns.c(pk2.t / 16.0)) < 0) {
            \u2603 = 0;
        }
        if (\u2603 >= this.m.length) {
            \u2603 = this.m.length - 1;
        }
        pk2.ad = true;
        pk2.ae = this.a;
        pk2.af = \u2603;
        pk2.ag = this.b;
        this.m[\u2603].add(pk2);
    }

    public void b(pk pk2) {
        this.a(pk2, pk2.af);
    }

    public void a(pk pk2, int n2) {
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= this.m.length) {
            n2 = this.m.length - 1;
        }
        this.m[n2].remove(pk2);
    }

    public boolean d(cj cj2) {
        int n2 = cj2.n() & 0xF;
        \u2603 = cj2.o();
        return \u2603 >= this.j[(\u2603 = cj2.p() & 0xF) << 4 | n2];
    }

    private akw i(cj cj2) {
        afh afh2 = this.a(cj2);
        if (!afh2.z()) {
            return null;
        }
        return ((agq)((Object)afh2)).a(this.i, this.c(cj2));
    }

    public akw a(cj cj2, a a2) {
        akw akw2 = this.l.get(cj2);
        if (akw2 == null) {
            if (a2 == amy$a.a) {
                akw2 = this.i(cj2);
                this.i.a(cj2, akw2);
            } else if (a2 == amy$a.b) {
                this.w.add(cj2);
            }
        } else if (akw2.x()) {
            this.l.remove(cj2);
            return null;
        }
        return akw2;
    }

    public void a(akw akw2) {
        this.a(akw2.v(), akw2);
        if (this.h) {
            this.i.a(akw2);
        }
    }

    public void a(cj cj2, akw akw2) {
        akw2.a(this.i);
        akw2.a(cj2);
        if (!(this.a(cj2) instanceof agq)) {
            return;
        }
        if (this.l.containsKey(cj2)) {
            this.l.get(cj2).y();
        }
        akw2.D();
        this.l.put(cj2, akw2);
    }

    public void e(cj cj2) {
        if (this.h && (\u2603 = this.l.remove(cj2)) != null) {
            \u2603.y();
        }
    }

    public void c() {
        this.h = true;
        this.i.a(this.l.values());
        for (int i2 = 0; i2 < this.m.length; ++i2) {
            for (pk pk2 : this.m[i2]) {
                pk2.ah();
            }
            this.i.b(this.m[i2]);
        }
    }

    public void d() {
        this.h = false;
        for (akw akw2 : this.l.values()) {
            this.i.b(akw2);
        }
        for (int i2 = 0; i2 < this.m.length; ++i2) {
            this.i.c(this.m[i2]);
        }
    }

    public void e() {
        this.q = true;
    }

    public void a(pk pk2, aug aug2, List<pk> list, Predicate<? super pk> predicate) {
        int n2 = ns.c((aug2.b - 2.0) / 16.0);
        \u2603 = ns.c((aug2.e + 2.0) / 16.0);
        n2 = ns.a(n2, 0, this.m.length - 1);
        \u2603 = ns.a(\u2603, 0, this.m.length - 1);
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            if (this.m[\u2603].isEmpty()) continue;
            for (pk pk32 : this.m[\u2603]) {
                if (!pk32.aR().b(aug2) || pk32 == pk2) continue;
                if (predicate == null || predicate.apply(pk32)) {
                    list.add(pk32);
                }
                if ((\u2603 = pk32.aB()) == null) continue;
                for (int i2 = 0; i2 < \u2603.length; ++i2) {
                    pk pk32;
                    pk32 = \u2603[i2];
                    if (pk32 == pk2 || !pk32.aR().b(aug2) || predicate != null && !predicate.apply(pk32)) continue;
                    list.add(pk32);
                }
            }
        }
    }

    public <T extends pk> void a(Class<? extends T> clazz, aug aug2, List<T> list, Predicate<? super T> predicate) {
        int n2 = ns.c((aug2.b - 2.0) / 16.0);
        \u2603 = ns.c((aug2.e + 2.0) / 16.0);
        n2 = ns.a(n2, 0, this.m.length - 1);
        \u2603 = ns.a(\u2603, 0, this.m.length - 1);
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (pk pk2 : this.m[\u2603].c(clazz)) {
                if (!pk2.aR().b(aug2) || predicate != null && !predicate.apply(pk2)) continue;
                list.add(pk2);
            }
        }
    }

    public boolean a(boolean bl2) {
        if (bl2 ? this.r && this.i.K() != this.s || this.q : this.r && this.i.K() >= this.s + 600L) {
            return true;
        }
        return this.q;
    }

    public Random a(long l2) {
        return new Random(this.i.J() + (long)(this.a * this.a * 4987142) + (long)(this.a * 5947611) + (long)(this.b * this.b) * 4392871L + (long)(this.b * 389711) ^ l2);
    }

    public boolean f() {
        return false;
    }

    public void a(amv amv2, amv amv3, int n2, int n3) {
        boolean bl2;
        amy amy2;
        boolean bl3 = amv2.a(n2, n3 - 1);
        \u2603 = amv2.a(n2 + 1, n3);
        \u2603 = amv2.a(n2, n3 + 1);
        \u2603 = amv2.a(n2 - 1, n3);
        bl2 = amv2.a(n2 - 1, n3 - 1);
        \u2603 = amv2.a(n2 + 1, n3 + 1);
        \u2603 = amv2.a(n2 - 1, n3 + 1);
        \u2603 = amv2.a(n2 + 1, n3 - 1);
        if (\u2603 && \u2603 && \u2603) {
            if (!this.n) {
                amv2.a(amv3, n2, n3);
            } else {
                amv2.a(amv3, this, n2, n3);
            }
        }
        if (\u2603 && \u2603 && \u2603) {
            amy2 = amv2.d(n2 - 1, n3);
            if (!amy2.n) {
                amv2.a(amv3, n2 - 1, n3);
            } else {
                amv2.a(amv3, amy2, n2 - 1, n3);
            }
        }
        if (bl3 && \u2603 && \u2603) {
            amy2 = amv2.d(n2, n3 - 1);
            if (!amy2.n) {
                amv2.a(amv3, n2, n3 - 1);
            } else {
                amv2.a(amv3, amy2, n2, n3 - 1);
            }
        }
        if (bl2 && bl3 && \u2603) {
            amy2 = amv2.d(n2 - 1, n3 - 1);
            if (!amy2.n) {
                amv2.a(amv3, n2 - 1, n3 - 1);
            } else {
                amv2.a(amv3, amy2, n2 - 1, n3 - 1);
            }
        }
    }

    public cj h(cj cj2) {
        int n2 = cj2.n() & 0xF;
        \u2603 = cj2.p() & 0xF;
        \u2603 = n2 | \u2603 << 4;
        cj \u26032 = new cj(cj2.n(), this.f[\u2603], cj2.p());
        if (\u26032.o() == -999) {
            int \u26034;
            \u2603 = this.g() + 15;
            \u26032 = new cj(cj2.n(), \u2603, cj2.p());
            \u26034 = -1;
            while (\u26032.o() > 0 && \u26034 == -1) {
                afh afh2 = this.a(\u26032);
                arm \u26033 = afh2.t();
                if (!\u26033.c() && !\u26033.d()) {
                    \u26032 = \u26032.b();
                    continue;
                }
                \u26034 = \u26032.o() + 1;
            }
            this.f[\u2603] = \u26034;
        }
        return new cj(cj2.n(), this.f[\u2603], cj2.p());
    }

    public void b(boolean bl2) {
        if (this.k && !this.i.t.o() && !bl2) {
            this.h(this.i.D);
        }
        this.p = true;
        if (!this.o && this.n) {
            this.n();
        }
        while (!this.w.isEmpty()) {
            cj cj2 = this.w.poll();
            if (this.a(cj2, amy$a.c) != null || !this.a(cj2).z()) continue;
            akw \u26032 = this.i(cj2);
            this.i.a(cj2, \u26032);
            this.i.b(cj2, cj2);
        }
    }

    public boolean i() {
        return this.p && this.n && this.o;
    }

    public adg j() {
        return new adg(this.a, this.b);
    }

    public boolean c(int n2, int n3) {
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 >= 256) {
            n3 = 255;
        }
        for (\u2603 = n2; \u2603 <= n3; \u2603 += 16) {
            amz amz2 = this.d[\u2603 >> 4];
            if (amz2 == null || amz2.a()) continue;
            return false;
        }
        return true;
    }

    public void a(amz[] amzArray) {
        if (this.d.length != amzArray.length) {
            c.warn("Could not set level chunk sections, array length is " + amzArray.length + " instead of " + this.d.length);
            return;
        }
        for (int i2 = 0; i2 < this.d.length; ++i2) {
            this.d[i2] = amzArray[i2];
        }
    }

    public void a(byte[] byArray, int n2, boolean bl22) {
        int n3;
        int n4 = 0;
        boolean \u26032 = !this.i.t.o();
        for (int n32 = 0; n32 < this.d.length; ++n32) {
            if ((n2 & 1 << n32) != 0) {
                if (this.d[n32] == null) {
                    this.d[n32] = new amz(n32 << 4, \u26032);
                }
                char[] cArray = this.d[n32].g();
                for (int i2 = 0; i2 < cArray.length; ++i2) {
                    cArray[i2] = (char)((byArray[n4 + 1] & 0xFF) << 8 | byArray[n4] & 0xFF);
                    n4 += 2;
                }
                continue;
            }
            if (!bl22 || this.d[n32] == null) continue;
            this.d[n32] = null;
        }
        for (n3 = 0; n3 < this.d.length; ++n3) {
            if ((n2 & 1 << n3) == 0 || this.d[n3] == null) continue;
            amw amw2 = this.d[n3].h();
            System.arraycopy(byArray, n4, amw2.a(), 0, amw2.a().length);
            n4 += amw2.a().length;
        }
        if (\u26032) {
            for (n3 = 0; n3 < this.d.length; ++n3) {
                if ((n2 & 1 << n3) == 0 || this.d[n3] == null) continue;
                amw amw3 = this.d[n3].i();
                System.arraycopy(byArray, n4, amw3.a(), 0, amw3.a().length);
                n4 += amw3.a().length;
            }
        }
        if (bl22) {
            System.arraycopy(byArray, n4, this.e, 0, this.e.length);
            n4 += this.e.length;
        }
        for (n3 = 0; n3 < this.d.length; ++n3) {
            if (this.d[n3] == null || (n2 & 1 << n3) == 0) continue;
            this.d[n3].e();
        }
        this.o = true;
        this.n = true;
        this.a();
        for (akw akw2 : this.l.values()) {
            akw2.E();
        }
    }

    public ady a(cj cj2, aec aec2) {
        int \u26032;
        int n2 = cj2.n() & 0xF;
        \u2603 = cj2.p() & 0xF;
        \u26032 = this.e[\u2603 << 4 | n2] & 0xFF;
        if (\u26032 == 255) {
            ady ady2 = aec2.a(cj2, ady.q);
            \u26032 = ady2.az;
            this.e[\u2603 << 4 | n2] = (byte)(\u26032 & 0xFF);
        }
        if ((ady2 = ady.e(\u26032)) == null) {
            return ady.q;
        }
        return ady2;
    }

    public byte[] k() {
        return this.e;
    }

    public void a(byte[] byArray) {
        if (this.e.length != byArray.length) {
            c.warn("Could not set level chunk biomes, array length is " + byArray.length + " instead of " + this.e.length);
            return;
        }
        for (int i2 = 0; i2 < this.e.length; ++i2) {
            this.e[i2] = byArray[i2];
        }
    }

    public void l() {
        this.v = 0;
    }

    public void m() {
        cj cj2 = new cj(this.a << 4, 0, this.b << 4);
        for (int i2 = 0; i2 < 8; ++i2) {
            if (this.v >= 4096) {
                return;
            }
            \u2603 = this.v % 16;
            \u2603 = this.v / 16 % 16;
            \u2603 = this.v / 256;
            ++this.v;
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                cj cj3 = cj2.a(\u2603, (\u2603 << 4) + \u2603, \u2603);
                boolean bl2 = \u2603 = \u2603 == 0 || \u2603 == 15 || \u2603 == 0 || \u2603 == 15 || \u2603 == 0 || \u2603 == 15;
                if ((this.d[\u2603] != null || !\u2603) && (this.d[\u2603] == null || this.d[\u2603].b(\u2603, \u2603, \u2603).t() != arm.a)) continue;
                for (cq cq2 : cq.values()) {
                    cj cj4 = cj3.a(cq2);
                    if (this.i.p(cj4).c().r() <= 0) continue;
                    this.i.x(cj4);
                }
                this.i.x(cj3);
            }
        }
    }

    public void n() {
        this.n = true;
        this.o = true;
        cj cj2 = new cj(this.a << 4, 0, this.b << 4);
        if (!this.i.t.o()) {
            if (this.i.a(cj2.a(-1, 0, -1), cj2.a(16, this.i.F(), 16))) {
                block0: for (int i2 = 0; i2 < 16; ++i2) {
                    for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                        if (this.e(i2, \u2603)) continue;
                        this.o = false;
                        break block0;
                    }
                }
                if (this.o) {
                    for (cq cq2 : cq.c.a) {
                        int n2 = cq2.c() == cq.b.a ? 16 : 1;
                        this.i.f(cj2.a(cq2, n2)).a(cq2.d());
                    }
                    this.y();
                }
            } else {
                this.o = false;
            }
        }
    }

    private void y() {
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            this.g[i2] = true;
        }
        this.h(false);
    }

    private void a(cq cq22) {
        block6: {
            cq cq22;
            block8: {
                block7: {
                    block5: {
                        if (!this.n) {
                            return;
                        }
                        if (cq22 != cq.f) break block5;
                        for (int i2 = 0; i2 < 16; ++i2) {
                            this.e(15, i2);
                        }
                        break block6;
                    }
                    if (cq22 != cq.e) break block7;
                    for (int i3 = 0; i3 < 16; ++i3) {
                        this.e(0, i3);
                    }
                    break block6;
                }
                if (cq22 != cq.d) break block8;
                for (int i4 = 0; i4 < 16; ++i4) {
                    this.e(i4, 15);
                }
                break block6;
            }
            if (cq22 != cq.c) break block6;
            for (int i5 = 0; i5 < 16; ++i5) {
                this.e(i5, 0);
            }
        }
    }

    private boolean e(int n2, int n3) {
        int n4;
        \u2603 = this.g();
        boolean bl2 = false;
        bl3 = false;
        cj.a \u26032 = new cj.a((this.a << 4) + n2, 0, (this.b << 4) + n3);
        for (n4 = \u2603 + 16 - 1; n4 > this.i.F() || n4 > 0 && !bl3; --n4) {
            \u26032.c(\u26032.n(), n4, \u26032.p());
            \u2603 = this.b(\u26032);
            if (\u2603 == 255 && \u26032.o() < this.i.F()) {
                boolean bl3 = true;
            }
            if (!bl2 && \u2603 > 0) {
                bl2 = true;
                continue;
            }
            if (!bl2 || \u2603 != 0 || this.i.x(\u26032)) continue;
            return false;
        }
        for (n4 = \u26032.o(); n4 > 0; --n4) {
            \u26032.c(\u26032.n(), n4, \u26032.p());
            if (this.a(\u26032).r() <= 0) continue;
            this.i.x(\u26032);
        }
        return true;
    }

    public boolean o() {
        return this.h;
    }

    public void c(boolean bl2) {
        this.h = bl2;
    }

    public adm p() {
        return this.i;
    }

    public int[] q() {
        return this.j;
    }

    public void a(int[] nArray) {
        if (this.j.length != nArray.length) {
            c.warn("Could not set level chunk heightmap, array length is " + nArray.length + " instead of " + this.j.length);
            return;
        }
        for (int i2 = 0; i2 < this.j.length; ++i2) {
            this.j[i2] = nArray[i2];
        }
    }

    public Map<cj, akw> r() {
        return this.l;
    }

    public ne<pk>[] s() {
        return this.m;
    }

    public boolean t() {
        return this.n;
    }

    public void d(boolean bl2) {
        this.n = bl2;
    }

    public boolean u() {
        return this.o;
    }

    public void e(boolean bl2) {
        this.o = bl2;
    }

    public void f(boolean bl2) {
        this.q = bl2;
    }

    public void g(boolean bl2) {
        this.r = bl2;
    }

    public void b(long l2) {
        this.s = l2;
    }

    public int v() {
        return this.t;
    }

    public long w() {
        return this.u;
    }

    public void c(long l2) {
        this.u = l2;
    }

    public static enum a {
        a,
        b,
        c;

    }
}

