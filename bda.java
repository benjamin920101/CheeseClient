/*
 * Decompiled with CFR 0.152.
 */
public class bda {
    private final ave a;
    private final bcy b;
    private cj c = new cj(-1, -1, -1);
    private zx d;
    private float e;
    private float f;
    private int g;
    private boolean h;
    private adp.a i = adp.a.b;
    private int j;

    public bda(ave ave2, bcy bcy2) {
        this.a = ave2;
        this.b = bcy2;
    }

    public static void a(ave ave2, bda bda2, cj cj2, cq cq2) {
        if (!ave2.f.a(ave2.h, cj2, cq2)) {
            bda2.a(cj2, cq2);
        }
    }

    public void a(wn wn2) {
        this.i.a(wn2.bA);
    }

    public boolean a() {
        return this.i == adp.a.e;
    }

    public void a(adp.a a2) {
        this.i = a2;
        this.i.a(this.a.h.bA);
    }

    public void b(wn wn2) {
        wn2.y = -180.0f;
    }

    public boolean b() {
        return this.i.e();
    }

    public boolean a(cj cj2, cq cq2) {
        Object object;
        if (this.i.c()) {
            if (this.i == adp.a.e) {
                return false;
            }
            if (!this.a.h.cn()) {
                object = this.a.f.p(cj2).c();
                \u2603 = this.a.h.bZ();
                if (\u2603 == null) {
                    return false;
                }
                if (!((zx)\u2603).c((afh)object)) {
                    return false;
                }
            }
        }
        if (this.i.d() && this.a.h.bA() != null && this.a.h.bA().b() instanceof aay) {
            return false;
        }
        object = this.a.f;
        \u2603 = ((adm)object).p(cj2);
        afh \u26032 = \u2603.c();
        if (\u26032.t() == arm.a) {
            return false;
        }
        ((adm)object).b(2001, cj2, afh.f((alz)\u2603));
        boolean \u26033 = ((adm)object).g(cj2);
        if (\u26033) {
            \u26032.d((adm)object, cj2, (alz)\u2603);
        }
        this.c = new cj(this.c.n(), -1, this.c.p());
        if (!this.i.d() && (\u2603 = this.a.h.bZ()) != null) {
            \u2603.a((adm)object, \u26032, cj2, this.a.h);
            if (\u2603.b == 0) {
                this.a.h.ca();
            }
        }
        return \u26033;
    }

    public boolean b(cj cj2, cq cq2) {
        if (this.i.c()) {
            if (this.i == adp.a.e) {
                return false;
            }
            if (!this.a.h.cn()) {
                afh afh2 = this.a.f.p(cj2).c();
                zx \u26032 = this.a.h.bZ();
                if (\u26032 == null) {
                    return false;
                }
                if (!\u26032.c(afh2)) {
                    return false;
                }
            }
        }
        if (!this.a.f.af().a(cj2)) {
            return false;
        }
        if (this.i.d()) {
            this.b.a(new ir(ir.a.a, cj2, cq2));
            bda.a(this.a, this, cj2, cq2);
            this.g = 5;
        } else if (!this.h || !this.a(cj2)) {
            if (this.h) {
                this.b.a(new ir(ir.a.b, this.c, cq2));
            }
            this.b.a(new ir(ir.a.a, cj2, cq2));
            afh2 = this.a.f.p(cj2).c();
            boolean bl2 = \u2603 = afh2.t() != arm.a;
            if (\u2603 && this.e == 0.0f) {
                afh2.a((adm)this.a.f, cj2, this.a.h);
            }
            if (\u2603 && afh2.a(this.a.h, this.a.h.o, cj2) >= 1.0f) {
                this.a(cj2, cq2);
            } else {
                this.h = true;
                this.c = cj2;
                this.d = this.a.h.bA();
                this.e = 0.0f;
                this.f = 0.0f;
                this.a.f.c(this.a.h.F(), this.c, (int)(this.e * 10.0f) - 1);
            }
        }
        return true;
    }

    public void c() {
        if (this.h) {
            this.b.a(new ir(ir.a.b, this.c, cq.a));
            this.h = false;
            this.e = 0.0f;
            this.a.f.c(this.a.h.F(), this.c, -1);
        }
    }

    public boolean c(cj cj22, cq cq2) {
        this.n();
        if (this.g > 0) {
            --this.g;
            return true;
        }
        if (this.i.d() && this.a.f.af().a(cj22)) {
            this.g = 5;
            this.b.a(new ir(ir.a.a, cj22, cq2));
            bda.a(this.a, this, cj22, cq2);
            return true;
        }
        if (this.a(cj22)) {
            afh afh2 = this.a.f.p(cj22).c();
            if (afh2.t() == arm.a) {
                this.h = false;
                return false;
            }
            this.e += afh2.a(this.a.h, this.a.h.o, cj22);
            if (this.f % 4.0f == 0.0f) {
                this.a.W().a(new bpf(new jy(afh2.H.c()), (afh2.H.d() + 1.0f) / 8.0f, afh2.H.e() * 0.5f, (float)cj22.n() + 0.5f, (float)cj22.o() + 0.5f, (float)cj22.p() + 0.5f));
            }
            this.f += 1.0f;
            if (this.e >= 1.0f) {
                this.h = false;
                this.b.a(new ir(ir.a.c, cj22, cq2));
                this.a(cj22, cq2);
                this.e = 0.0f;
                this.f = 0.0f;
                this.g = 5;
            }
        } else {
            cj cj22;
            return this.b(cj22, cq2);
        }
        this.a.f.c(this.a.h.F(), this.c, (int)(this.e * 10.0f) - 1);
        return true;
    }

    public float d() {
        if (this.i.d()) {
            return 5.0f;
        }
        return 4.5f;
    }

    public void e() {
        this.n();
        if (this.b.a().g()) {
            this.b.a().a();
        } else {
            this.b.a().l();
        }
    }

    private boolean a(cj cj22) {
        cj cj22;
        zx zx2 = this.a.h.bA();
        boolean bl2 = bl3 = this.d == null && zx2 == null;
        if (this.d != null && zx2 != null) {
            boolean bl3 = zx2.b() == this.d.b() && zx.a(zx2, this.d) && (zx2.e() || zx2.i() == this.d.i());
        }
        return cj22.equals(this.c) && bl3;
    }

    private void n() {
        int n2 = this.a.h.bi.c;
        if (n2 != this.j) {
            this.j = n2;
            this.b.a(new iv(this.j));
        }
    }

    public boolean a(bew bew2, bdb bdb2, zx zx2, cj cj22, cq cq2, aui aui2) {
        cj cj22;
        this.n();
        float f2 = (float)(aui2.a - (double)cj22.n());
        \u2603 = (float)(aui2.b - (double)cj22.o());
        \u2603 = (float)(aui2.c - (double)cj22.p());
        boolean \u26032 = false;
        if (!this.a.f.af().a(cj22)) {
            return false;
        }
        if (this.i != adp.a.e) {
            alz alz2 = bdb2.p(cj22);
            if ((!bew2.av() || bew2.bA() == null) && alz2.c().a((adm)bdb2, cj22, alz2, bew2, cq2, f2, \u2603, \u2603)) {
                \u26032 = true;
            }
            if (!\u26032 && zx2 != null && zx2.b() instanceof yo && !(\u2603 = (yo)zx2.b()).a(bdb2, cj22, cq2, bew2, zx2)) {
                return false;
            }
        }
        this.b.a(new ja(cj22, cq2.a(), bew2.bi.h(), f2, \u2603, \u2603));
        if (\u26032 || this.i == adp.a.e) {
            return true;
        }
        if (zx2 == null) {
            return false;
        }
        if (this.i.d()) {
            int n2 = zx2.i();
            \u2603 = zx2.b;
            boolean \u26033 = zx2.a(bew2, bdb2, cj22, cq2, f2, \u2603, \u2603);
            zx2.b(n2);
            zx2.b = \u2603;
            return \u26033;
        }
        return zx2.a(bew2, bdb2, cj22, cq2, f2, \u2603, \u2603);
    }

    public boolean a(wn wn2, adm adm2, zx zx2) {
        if (this.i == adp.a.e) {
            return false;
        }
        this.n();
        this.b.a(new ja(wn2.bi.h()));
        int n2 = zx2.b;
        zx \u26032 = zx2.a(adm2, wn2);
        if (\u26032 != zx2 || \u26032 != null && \u26032.b != n2) {
            wn2.bi.a[wn2.bi.c] = \u26032;
            if (\u26032.b == 0) {
                wn2.bi.a[wn2.bi.c] = null;
            }
            return true;
        }
        return false;
    }

    public bew a(adm adm2, nb nb2) {
        return new bew(this.a, adm2, this.b, nb2);
    }

    public void a(wn wn2, pk pk2) {
        this.n();
        this.b.a(new in(pk2, in.a.b));
        if (this.i != adp.a.e) {
            wn2.f(pk2);
        }
    }

    public boolean b(wn wn2, pk pk2) {
        this.n();
        this.b.a(new in(pk2, in.a.a));
        return this.i != adp.a.e && wn2.u(pk2);
    }

    public boolean a(wn wn2, pk pk2, auh auh2) {
        this.n();
        aui aui2 = new aui(auh2.c.a - pk2.s, auh2.c.b - pk2.t, auh2.c.c - pk2.u);
        this.b.a(new in(pk2, aui2));
        return this.i != adp.a.e && pk2.a(wn2, aui2);
    }

    public zx a(int n2, int n3, int n4, int n5, wn wn2) {
        short s2 = wn2.bk.a(wn2.bi);
        zx \u26032 = wn2.bk.a(n3, n4, n5, wn2);
        this.b.a(new ik(n2, n3, n4, n5, \u26032, s2));
        return \u26032;
    }

    public void a(int n2, int n3) {
        this.b.a(new ij(n2, n3));
    }

    public void a(zx zx2, int n2) {
        if (this.i.d()) {
            this.b.a(new iw(n2, zx2));
        }
    }

    public void a(zx zx2) {
        if (this.i.d() && zx2 != null) {
            this.b.a(new iw(-1, zx2));
        }
    }

    public void c(wn wn2) {
        this.n();
        this.b.a(new ir(ir.a.f, cj.a, cq.a));
        wn2.bU();
    }

    public boolean f() {
        return this.i.e();
    }

    public boolean g() {
        return !this.i.d();
    }

    public boolean h() {
        return this.i.d();
    }

    public boolean i() {
        return this.i.d();
    }

    public boolean j() {
        return this.a.h.au() && this.a.h.m instanceof tp;
    }

    public boolean k() {
        return this.i == adp.a.e;
    }

    public adp.a l() {
        return this.i;
    }

    public boolean m() {
        return this.h;
    }
}

