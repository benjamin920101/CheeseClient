/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

public class bdb
extends adm {
    private bcy a;
    private bcz b;
    private final Set<pk> c = Sets.newHashSet();
    private final Set<pk> d = Sets.newHashSet();
    private final ave I = ave.A();
    private final Set<adg> J = Sets.newHashSet();

    public bdb(bcy bcy2, adp adp2, int n2, oj oj2, nt nt2) {
        super(new atx(), new ato(adp2, "MpServer"), anm.a(n2), nt2, true);
        this.a = bcy2;
        this.P().a(oj2);
        this.B(new cj(8, 64, 8));
        this.t.a(this);
        this.v = this.k();
        this.z = new atz();
        this.B();
        this.C();
    }

    @Override
    public void c() {
        super.c();
        this.a(this.K() + 1L);
        if (this.Q().b("doDaylightCycle")) {
            this.b(this.L() + 1L);
        }
        this.B.a("reEntryProcessing");
        for (int i2 = 0; i2 < 10 && !this.d.isEmpty(); ++i2) {
            pk pk2 = this.d.iterator().next();
            this.d.remove(pk2);
            if (this.f.contains(pk2)) continue;
            this.d(pk2);
        }
        this.B.c("chunkCache");
        this.b.d();
        this.B.c("blocks");
        this.h();
        this.B.b();
    }

    public void a(int n2, int n3, int n4, int n5, int n6, int n7) {
    }

    @Override
    protected amv k() {
        this.b = new bcz(this);
        return this.b;
    }

    @Override
    protected void h() {
        super.h();
        this.J.retainAll(this.E);
        if (this.J.size() == this.E.size()) {
            this.J.clear();
        }
        int n2 = 0;
        for (adg adg2 : this.E) {
            if (this.J.contains(adg2)) continue;
            int n3 = adg2.a * 16;
            \u2603 = adg2.b * 16;
            this.B.a("getChunk");
            amy \u26032 = this.a(adg2.a, adg2.b);
            this.a(n3, \u2603, \u26032);
            this.B.b();
            this.J.add(adg2);
            if (++n2 < 10) continue;
            return;
        }
    }

    public void b(int n2, int n3, boolean bl2) {
        if (bl2) {
            this.b.c(n2, n3);
        } else {
            this.b.b(n2, n3);
        }
        if (!bl2) {
            this.b(n2 * 16, 0, n3 * 16, n2 * 16 + 15, 256, n3 * 16 + 15);
        }
    }

    @Override
    public boolean d(pk pk2) {
        boolean bl2 = super.d(pk2);
        this.c.add(pk2);
        if (!bl2) {
            this.d.add(pk2);
        } else if (pk2 instanceof va) {
            this.I.W().a(new bpd((va)pk2));
        }
        return bl2;
    }

    @Override
    public void e(pk pk2) {
        super.e(pk2);
        this.c.remove(pk2);
    }

    @Override
    protected void a(pk pk2) {
        super.a(pk2);
        if (this.d.contains(pk2)) {
            this.d.remove(pk2);
        }
    }

    @Override
    protected void b(pk pk2) {
        super.b(pk2);
        boolean bl2 = false;
        if (this.c.contains(pk2)) {
            if (pk2.ai()) {
                this.d.add(pk2);
                bl2 = true;
            } else {
                this.c.remove(pk2);
            }
        }
    }

    public void a(int n2, pk pk2) {
        \u2603 = this.a(n2);
        if (\u2603 != null) {
            this.e(\u2603);
        }
        this.c.add(pk2);
        pk2.d(n2);
        if (!this.d(pk2)) {
            this.d.add(pk2);
        }
        this.l.a(n2, pk2);
    }

    @Override
    public pk a(int n2) {
        if (n2 == this.I.h.F()) {
            return this.I.h;
        }
        return super.a(n2);
    }

    public pk e(int n2) {
        pk pk2 = (pk)this.l.d(n2);
        if (pk2 != null) {
            this.c.remove(pk2);
            this.e(pk2);
        }
        return pk2;
    }

    public boolean b(cj cj2, alz alz2) {
        int n2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        this.a(n2, \u2603, \u2603, n2, \u2603, \u2603);
        return super.a(cj2, alz2, 3);
    }

    @Override
    public void H() {
        this.a.a().a(new fa("Quitting"));
    }

    @Override
    protected void p() {
    }

    @Override
    protected int q() {
        return this.I.t.c;
    }

    public void b(int n2, int n3, int n4) {
        \u2603 = 16;
        Random random = new Random();
        zx \u26032 = this.I.h.bA();
        boolean \u26033 = this.I.c.l() == adp.a.c && \u26032 != null && afh.a(\u26032.b()) == afi.cv;
        cj.a \u26034 = new cj.a();
        for (int i2 = 0; i2 < 1000; ++i2) {
            \u2603 = n2 + this.s.nextInt(\u2603) - this.s.nextInt(\u2603);
            \u2603 = n3 + this.s.nextInt(\u2603) - this.s.nextInt(\u2603);
            \u2603 = n4 + this.s.nextInt(\u2603) - this.s.nextInt(\u2603);
            \u26034.c(\u2603, \u2603, \u2603);
            alz alz2 = this.p(\u26034);
            alz2.c().c(this, \u26034, alz2, random);
            if (!\u26033 || alz2.c() != afi.cv) continue;
            this.a(cy.J, (float)\u2603 + 0.5f, (double)((float)\u2603 + 0.5f), (double)((float)\u2603 + 0.5f), 0.0, 0.0, 0.0, new int[0]);
        }
    }

    public void a() {
        int \u26033;
        int \u26032;
        pk pk2;
        int n2;
        this.f.removeAll(this.g);
        for (n2 = 0; n2 < this.g.size(); ++n2) {
            pk2 = (pk)this.g.get(n2);
            \u26032 = pk2.ae;
            \u26033 = pk2.ag;
            if (!pk2.ad || !this.a(\u26032, \u26033, true)) continue;
            this.a(\u26032, \u26033).b(pk2);
        }
        for (n2 = 0; n2 < this.g.size(); ++n2) {
            this.b((pk)this.g.get(n2));
        }
        this.g.clear();
        for (n2 = 0; n2 < this.f.size(); ++n2) {
            pk2 = (pk)this.f.get(n2);
            if (pk2.m != null) {
                if (!pk2.m.I && pk2.m.l == pk2) continue;
                pk2.m.l = null;
                pk2.m = null;
            }
            if (!pk2.I) continue;
            \u26032 = pk2.ae;
            \u26033 = pk2.ag;
            if (pk2.ad && this.a(\u26032, \u26033, true)) {
                this.a(\u26032, \u26033).b(pk2);
            }
            this.f.remove(n2--);
            this.b(pk2);
        }
    }

    @Override
    public c a(b b2) {
        c c2 = super.a(b2);
        c2.a("Forced entities", new Callable<String>(){

            public String a() {
                return bdb.this.c.size() + " total; " + bdb.this.c.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Retry entities", new Callable<String>(){

            public String a() {
                return bdb.this.d.size() + " total; " + bdb.this.d.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Server brand", new Callable<String>(){

            public String a() throws Exception {
                return ((bdb)bdb.this).I.h.w();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Server type", new Callable<String>(){

            public String a() throws Exception {
                return bdb.this.I.G() == null ? "Non-integrated multiplayer server" : "Integrated singleplayer server";
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        return c2;
    }

    public void a(cj cj2, String string, float f2, float f3, boolean bl2) {
        this.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, string, f2, f3, bl2);
    }

    @Override
    public void a(double d2, double d3, double d4, String string, float f2, float f3, boolean bl2) {
        double d5 = this.I.ac().e(d2, d3, d4);
        bpf \u26032 = new bpf(new jy(string), f2, f3, (float)d2, (float)d3, (float)d4);
        if (bl2 && d5 > 100.0) {
            \u2603 = Math.sqrt(d5) / 40.0;
            this.I.W().a(\u26032, (int)(\u2603 * 20.0));
        } else {
            this.I.W().a(\u26032);
        }
    }

    @Override
    public void a(double d2, double d3, double d4, double d5, double d6, double d7, dn dn2) {
        this.I.j.a(new bdq.c(this, d2, d3, d4, d5, d6, d7, this.I.j, dn2));
    }

    public void a(auo auo2) {
        this.C = auo2;
    }

    @Override
    public void b(long l2) {
        if (l2 < 0L) {
            l2 = -l2;
            this.Q().a("doDaylightCycle", "false");
        } else {
            this.Q().a("doDaylightCycle", "true");
        }
        super.b(l2);
    }
}

