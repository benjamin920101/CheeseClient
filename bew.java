/*
 * Decompiled with CFR 0.152.
 */
public class bew
extends bet {
    public final bcy a;
    private final nb bJ;
    private double bK;
    private double bL;
    private double bM;
    private float bN;
    private float bO;
    private boolean bP;
    private boolean bQ;
    private int bR;
    private boolean bS;
    private String bT;
    public beu b;
    protected ave c;
    protected int d;
    public int e;
    public float f;
    public float g;
    public float h;
    public float i;
    private int bU;
    private float bV;
    public float bH;
    public float bI;

    public bew(ave ave2, adm adm2, bcy bcy2, nb nb2) {
        super(adm2, bcy2.e());
        this.a = bcy2;
        this.bJ = nb2;
        this.c = ave2;
        this.am = 0;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        return false;
    }

    @Override
    public void h(float f2) {
    }

    @Override
    public void a(pk pk2) {
        super.a(pk2);
        if (pk2 instanceof va) {
            this.c.W().a(new bpe(this, (va)pk2));
        }
    }

    @Override
    public void t_() {
        if (!this.o.e(new cj(this.s, 0.0, this.u))) {
            return;
        }
        super.t_();
        if (this.au()) {
            this.a.a(new ip.c(this.y, this.z, this.C));
            this.a.a(new it(this.aZ, this.ba, this.b.c, this.b.d));
        } else {
            this.p();
        }
    }

    public void p() {
        boolean bl2 = this.aw();
        if (bl2 != this.bQ) {
            if (bl2) {
                this.a.a(new is(this, is.a.d));
            } else {
                this.a.a(new is(this, is.a.e));
            }
            this.bQ = bl2;
        }
        if ((\u2603 = this.av()) != this.bP) {
            if (\u2603) {
                this.a.a(new is(this, is.a.a));
            } else {
                this.a.a(new is(this, is.a.b));
            }
            this.bP = \u2603;
        }
        if (this.A()) {
            double d2 = this.s - this.bK;
            \u2603 = this.aR().b - this.bL;
            \u2603 = this.u - this.bM;
            \u2603 = this.y - this.bN;
            \u2603 = this.z - this.bO;
            boolean \u26032 = d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603 > 9.0E-4 || this.bR >= 20;
            boolean bl3 = \u2603 = \u2603 != 0.0 || \u2603 != 0.0;
            if (this.m == null) {
                if (\u26032 && \u2603) {
                    this.a.a(new ip.b(this.s, this.aR().b, this.u, this.y, this.z, this.C));
                } else if (\u26032) {
                    this.a.a(new ip.a(this.s, this.aR().b, this.u, this.C));
                } else if (\u2603) {
                    this.a.a(new ip.c(this.y, this.z, this.C));
                } else {
                    this.a.a(new ip(this.C));
                }
            } else {
                this.a.a(new ip.b(this.v, -999.0, this.x, this.y, this.z, this.C));
                \u26032 = false;
            }
            ++this.bR;
            if (\u26032) {
                this.bK = this.s;
                this.bL = this.aR().b;
                this.bM = this.u;
                this.bR = 0;
            }
            if (\u2603) {
                this.bN = this.y;
                this.bO = this.z;
            }
        }
    }

    @Override
    public uz a(boolean bl2) {
        ir.a a2 = bl2 ? ir.a.d : ir.a.e;
        this.a.a(new ir(a2, cj.a, cq.a));
        return null;
    }

    @Override
    protected void a(uz uz2) {
    }

    public void e(String string) {
        this.a.a(new ie(string));
    }

    @Override
    public void bw() {
        super.bw();
        this.a.a(new iy());
    }

    @Override
    public void cb() {
        this.a.a(new ig(ig.a.a));
    }

    @Override
    protected void d(ow ow2, float f2) {
        if (this.b(ow2)) {
            return;
        }
        this.i(this.bn() - f2);
    }

    @Override
    public void n() {
        this.a.a(new il(this.bk.d));
        this.q();
    }

    public void q() {
        this.bi.b((zx)null);
        super.n();
        this.c.a((axu)null);
    }

    public void n(float f2) {
        if (this.bS) {
            \u2603 = this.bn() - f2;
            if (\u2603 <= 0.0f) {
                this.i(f2);
                if (\u2603 < 0.0f) {
                    this.Z = this.aD / 2;
                }
            } else {
                this.aX = \u2603;
                this.i(this.bn());
                this.Z = this.aD;
                this.d(ow.k, \u2603);
                this.av = 10;
                this.au = 10;
            }
        } else {
            this.i(f2);
            this.bS = true;
        }
    }

    @Override
    public void a(mw mw2, int n2) {
        if (mw2 == null) {
            return;
        }
        if (mw2.f) {
            super.a(mw2, n2);
        }
    }

    @Override
    public void t() {
        this.a.a(new iq(this.bA));
    }

    @Override
    public boolean cc() {
        return true;
    }

    protected void r() {
        this.a.a(new is(this, is.a.f, (int)(this.z() * 100.0f)));
    }

    public void u() {
        this.a.a(new is(this, is.a.g));
    }

    public void f(String string) {
        this.bT = string;
    }

    public String w() {
        return this.bT;
    }

    public nb x() {
        return this.bJ;
    }

    @Override
    public void b(eu eu2) {
        this.c.q.d().a(eu2);
    }

    @Override
    protected boolean j(double d2, double d3, double d4) {
        if (this.T) {
            return false;
        }
        cj cj2 = new cj(d2, d3, d4);
        double \u26032 = d2 - (double)cj2.n();
        double \u26033 = d4 - (double)cj2.p();
        if (!this.e(cj2)) {
            int n2 = -1;
            double \u26034 = 9999.0;
            if (this.e(cj2.e()) && \u26032 < \u26034) {
                \u26034 = \u26032;
                n2 = 0;
            }
            if (this.e(cj2.f()) && 1.0 - \u26032 < \u26034) {
                \u26034 = 1.0 - \u26032;
                n2 = 1;
            }
            if (this.e(cj2.c()) && \u26033 < \u26034) {
                \u26034 = \u26033;
                n2 = 4;
            }
            if (this.e(cj2.d()) && 1.0 - \u26033 < \u26034) {
                \u26034 = 1.0 - \u26033;
                n2 = 5;
            }
            float \u26035 = 0.1f;
            if (n2 == 0) {
                this.v = -\u26035;
            }
            if (n2 == 1) {
                this.v = \u26035;
            }
            if (n2 == 4) {
                this.x = -\u26035;
            }
            if (n2 == 5) {
                this.x = \u26035;
            }
        }
        return false;
    }

    private boolean e(cj cj2) {
        return !this.o.p(cj2).c().v() && !this.o.p(cj2.a()).c().v();
    }

    @Override
    public void d(boolean bl2) {
        super.d(bl2);
        this.e = bl2 ? 600 : 0;
    }

    public void a(float f2, int n2, int n3) {
        this.bD = f2;
        this.bC = n2;
        this.bB = n3;
    }

    @Override
    public void a(eu eu2) {
        this.c.q.d().a(eu2);
    }

    @Override
    public boolean a(int n2, String string) {
        return n2 <= 0;
    }

    @Override
    public cj c() {
        return new cj(this.s + 0.5, this.t + 0.5, this.u + 0.5);
    }

    @Override
    public void a(String string, float f2, float f3) {
        this.o.a(this.s, this.t, this.u, string, f2, f3, false);
    }

    @Override
    public boolean bM() {
        return true;
    }

    public boolean y() {
        return this.m != null && this.m instanceof tp && ((tp)this.m).cG();
    }

    public float z() {
        return this.bV;
    }

    @Override
    public void a(aln aln2) {
        this.c.a(new aze(aln2));
    }

    @Override
    public void a(adc adc2) {
        this.c.a(new ayq(adc2));
    }

    @Override
    public void a(zx zx2) {
        zw zw2 = zx2.b();
        if (zw2 == zy.bM) {
            this.c.a(new ayo(this, zx2, true));
        }
    }

    @Override
    public void a(og og2) {
        String string = \u2603 = og2 instanceof ol ? ((ol)((Object)og2)).k() : "minecraft:container";
        if ("minecraft:chest".equals(\u2603)) {
            this.c.a(new ayr(this.bi, og2));
        } else if ("minecraft:hopper".equals(\u2603)) {
            this.c.a(new aza(this.bi, og2));
        } else if ("minecraft:furnace".equals(\u2603)) {
            this.c.a(new ayz(this.bi, og2));
        } else if ("minecraft:brewing_stand".equals(\u2603)) {
            this.c.a(new ayp(this.bi, og2));
        } else if ("minecraft:beacon".equals(\u2603)) {
            this.c.a(new ayn(this.bi, og2));
        } else if ("minecraft:dispenser".equals(\u2603) || "minecraft:dropper".equals(\u2603)) {
            this.c.a(new ayv(this.bi, og2));
        } else {
            this.c.a(new ayr(this.bi, og2));
        }
    }

    @Override
    public void a(tp tp2, og og2) {
        this.c.a(new azb(this.bi, og2, tp2));
    }

    @Override
    public void a(ol ol2) {
        String string = ol2.k();
        if ("minecraft:crafting_table".equals(string)) {
            this.c.a(new ays(this.bi, this.o));
        } else if ("minecraft:enchanting_table".equals(string)) {
            this.c.a(new ayy(this.bi, this.o, ol2));
        } else if ("minecraft:anvil".equals(string)) {
            this.c.a(new aym(this.bi, this.o));
        }
    }

    @Override
    public void a(acy acy2) {
        this.c.a(new azd(this.bi, acy2, this.o));
    }

    @Override
    public void b(pk pk2) {
        this.c.j.a(pk2, cy.j);
    }

    @Override
    public void c(pk pk2) {
        this.c.j.a(pk2, cy.k);
    }

    @Override
    public boolean av() {
        boolean bl2 = this.b != null ? this.b.d : false;
        return bl2 && !this.bw;
    }

    @Override
    public void bK() {
        super.bK();
        if (this.A()) {
            this.aZ = this.b.a;
            this.ba = this.b.b;
            this.aY = this.b.c;
            this.h = this.f;
            this.i = this.g;
            this.g = (float)((double)this.g + (double)(this.z - this.g) * 0.5);
            this.f = (float)((double)this.f + (double)(this.y - this.f) * 0.5);
        }
    }

    protected boolean A() {
        return this.c.ac() == this;
    }

    @Override
    public void m() {
        if (this.e > 0) {
            --this.e;
            if (this.e == 0) {
                this.d(false);
            }
        }
        if (this.d > 0) {
            --this.d;
        }
        this.bI = this.bH;
        if (this.ak) {
            if (this.c.m != null && !this.c.m.d()) {
                this.c.a((axu)null);
            }
            if (this.bH == 0.0f) {
                this.c.W().a(bpf.a(new jy("portal.trigger"), this.V.nextFloat() * 0.4f + 0.8f));
            }
            this.bH += 0.0125f;
            if (this.bH >= 1.0f) {
                this.bH = 1.0f;
            }
            this.ak = false;
        } else if (this.a(pe.k) && this.b(pe.k).b() > 60) {
            this.bH += 0.006666667f;
            if (this.bH > 1.0f) {
                this.bH = 1.0f;
            }
        } else {
            if (this.bH > 0.0f) {
                this.bH -= 0.05f;
            }
            if (this.bH < 0.0f) {
                this.bH = 0.0f;
            }
        }
        if (this.aj > 0) {
            --this.aj;
        }
        boolean bl2 = this.b.c;
        \u2603 = this.b.d;
        float \u26032 = 0.8f;
        \u2603 = this.b.b >= \u26032;
        this.b.a();
        if (this.bS() && !this.au()) {
            this.b.a *= 0.2f;
            this.b.b *= 0.2f;
            this.d = 0;
        }
        this.j(this.s - (double)this.J * 0.35, this.aR().b + 0.5, this.u + (double)this.J * 0.35);
        this.j(this.s - (double)this.J * 0.35, this.aR().b + 0.5, this.u - (double)this.J * 0.35);
        this.j(this.s + (double)this.J * 0.35, this.aR().b + 0.5, this.u - (double)this.J * 0.35);
        this.j(this.s + (double)this.J * 0.35, this.aR().b + 0.5, this.u + (double)this.J * 0.35);
        boolean bl3 = \u2603 = (float)this.cl().a() > 6.0f || this.bA.c;
        if (this.C && !\u2603 && !\u2603 && this.b.b >= \u26032 && !this.aw() && \u2603 && !this.bS() && !this.a(pe.q)) {
            if (this.d > 0 || this.c.t.ad.d()) {
                this.d(true);
            } else {
                this.d = 7;
            }
        }
        if (!this.aw() && this.b.b >= \u26032 && \u2603 && !this.bS() && !this.a(pe.q) && this.c.t.ad.d()) {
            this.d(true);
        }
        if (this.aw() && (this.b.b < \u26032 || this.D || !\u2603)) {
            this.d(false);
        }
        if (this.bA.c) {
            if (this.c.c.k()) {
                if (!this.bA.b) {
                    this.bA.b = true;
                    this.t();
                }
            } else if (!bl2 && this.b.c) {
                if (this.bm == 0) {
                    this.bm = 7;
                } else {
                    this.bA.b = !this.bA.b;
                    this.t();
                    this.bm = 0;
                }
            }
        }
        if (this.bA.b && this.A()) {
            if (this.b.d) {
                this.w -= (double)(this.bA.a() * 3.0f);
            }
            if (this.b.c) {
                this.w += (double)(this.bA.a() * 3.0f);
            }
        }
        if (this.y()) {
            if (this.bU < 0) {
                ++this.bU;
                if (this.bU == 0) {
                    this.bV = 0.0f;
                }
            }
            if (bl2 && !this.b.c) {
                this.bU = -10;
                this.r();
            } else if (!bl2 && this.b.c) {
                this.bU = 0;
                this.bV = 0.0f;
            } else if (bl2) {
                ++this.bU;
                this.bV = this.bU < 10 ? (float)this.bU * 0.1f : 0.8f + 2.0f / (float)(this.bU - 9) * 0.1f;
            }
        } else {
            this.bV = 0.0f;
        }
        super.m();
        if (this.C && this.bA.b && !this.c.c.k()) {
            this.bA.b = false;
            this.t();
        }
    }
}

