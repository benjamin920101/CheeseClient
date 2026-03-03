/*
 * Decompiled with CFR 0.152.
 */
public class lg {
    public adm a;
    public lf b;
    private adp.a c = adp.a.a;
    private boolean d;
    private int e;
    private cj f = cj.a;
    private int g;
    private boolean h;
    private cj i = cj.a;
    private int j;
    private int k = -1;

    public lg(adm adm2) {
        this.a = adm2;
    }

    public void a(adp.a a2) {
        this.c = a2;
        a2.a(this.b.bA);
        this.b.t();
        this.b.b.ap().a(new gz(gz.a.b, this.b));
    }

    public adp.a b() {
        return this.c;
    }

    public boolean c() {
        return this.c.e();
    }

    public boolean d() {
        return this.c.d();
    }

    public void b(adp.a a2) {
        if (this.c == adp.a.a) {
            this.c = a2;
        }
        this.a(this.c);
    }

    public void a() {
        ++this.g;
        if (this.h) {
            int n2 = this.g - this.j;
            afh \u26032 = this.a.p(this.i).c();
            if (\u26032.t() == arm.a) {
                this.h = false;
            } else {
                float f2 = \u26032.a(this.b, this.b.o, this.i) * (float)(n2 + 1);
                int \u26033 = (int)(f2 * 10.0f);
                if (\u26033 != this.k) {
                    this.a.c(this.b.F(), this.i, \u26033);
                    this.k = \u26033;
                }
                if (f2 >= 1.0f) {
                    this.h = false;
                    this.b(this.i);
                }
            }
        } else if (this.d) {
            afh afh2 = this.a.p(this.f).c();
            if (afh2.t() == arm.a) {
                this.a.c(this.b.F(), this.f, -1);
                this.k = -1;
                this.d = false;
            } else {
                int n3 = this.g - this.e;
                float \u26034 = afh2.a(this.b, this.b.o, this.i) * (float)(n3 + 1);
                \u2603 = (int)(\u26034 * 10.0f);
                if (\u2603 != this.k) {
                    this.a.c(this.b.F(), this.f, \u2603);
                    this.k = \u2603;
                }
            }
        }
    }

    public void a(cj cj22, cq cq2) {
        cj cj22;
        if (this.d()) {
            if (!this.a.a(null, cj22, cq2)) {
                this.b(cj22);
            }
            return;
        }
        afh afh2 = this.a.p(cj22).c();
        if (this.c.c()) {
            if (this.c == adp.a.e) {
                return;
            }
            if (!this.b.cn()) {
                zx zx2 = this.b.bZ();
                if (zx2 == null) {
                    return;
                }
                if (!zx2.c(afh2)) {
                    return;
                }
            }
        }
        this.a.a(null, cj22, cq2);
        this.e = this.g;
        float \u26032 = 1.0f;
        if (afh2.t() != arm.a) {
            afh2.a(this.a, cj22, this.b);
            \u26032 = afh2.a(this.b, this.b.o, cj22);
        }
        if (afh2.t() != arm.a && \u26032 >= 1.0f) {
            this.b(cj22);
        } else {
            this.d = true;
            this.f = cj22;
            int n2 = (int)(\u26032 * 10.0f);
            this.a.c(this.b.F(), cj22, n2);
            this.k = n2;
        }
    }

    public void a(cj cj2) {
        if (cj2.equals(this.f)) {
            int n2 = this.g - this.e;
            afh \u26032 = this.a.p(cj2).c();
            if (\u26032.t() != arm.a) {
                float f2 = \u26032.a(this.b, this.b.o, cj2) * (float)(n2 + 1);
                if (f2 >= 0.7f) {
                    this.d = false;
                    this.a.c(this.b.F(), cj2, -1);
                    this.b(cj2);
                } else if (!this.h) {
                    this.d = false;
                    this.h = true;
                    this.i = cj2;
                    this.j = this.e;
                }
            }
        }
    }

    public void e() {
        this.d = false;
        this.a.c(this.b.F(), this.f, -1);
    }

    private boolean c(cj cj2) {
        alz alz2 = this.a.p(cj2);
        alz2.c().a(this.a, cj2, alz2, this.b);
        boolean \u26032 = this.a.g(cj2);
        if (\u26032) {
            alz2.c().d(this.a, cj2, alz2);
        }
        return \u26032;
    }

    public boolean b(cj cj22) {
        cj cj22;
        if (this.c.d() && this.b.bA() != null && this.b.bA().b() instanceof aay) {
            return false;
        }
        alz alz2 = this.a.p(cj22);
        akw \u26032 = this.a.s(cj22);
        if (this.c.c()) {
            if (this.c == adp.a.e) {
                return false;
            }
            if (!this.b.cn()) {
                zx zx2 = this.b.bZ();
                if (zx2 == null) {
                    return false;
                }
                if (!zx2.c(alz2.c())) {
                    return false;
                }
            }
        }
        this.a.a((wn)this.b, 2001, cj22, afh.f(alz2));
        boolean \u26033 = this.c(cj22);
        if (this.d()) {
            this.b.a.a(new fv(this.a, cj22));
        } else {
            zx zx3 = this.b.bZ();
            boolean \u26034 = this.b.b(alz2.c());
            if (zx3 != null) {
                zx3.a(this.a, alz2.c(), cj22, this.b);
                if (zx3.b == 0) {
                    this.b.ca();
                }
            }
            if (\u26033 && \u26034) {
                alz2.c().a(this.a, this.b, cj22, alz2, \u26032);
            }
        }
        return \u26033;
    }

    public boolean a(wn wn2, adm adm2, zx zx2) {
        if (this.c == adp.a.e) {
            return false;
        }
        int n2 = zx2.b;
        \u2603 = zx2.i();
        zx \u26032 = zx2.a(adm2, wn2);
        if (\u26032 != zx2 || \u26032 != null && (\u26032.b != n2 || \u26032.l() > 0 || \u26032.i() != \u2603)) {
            wn2.bi.a[wn2.bi.c] = \u26032;
            if (this.d()) {
                \u26032.b = n2;
                if (\u26032.e()) {
                    \u26032.b(\u2603);
                }
            }
            if (\u26032.b == 0) {
                wn2.bi.a[wn2.bi.c] = null;
            }
            if (!wn2.bS()) {
                ((lf)wn2).a(wn2.bj);
            }
            return true;
        }
        return false;
    }

    public boolean a(wn wn2, adm adm2, zx zx2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (this.c == adp.a.e) {
            akw akw2 = adm2.s(cj2);
            if (akw2 instanceof oo) {
                afh afh2 = adm2.p(cj2).c();
                oo \u26032 = (oo)((Object)akw2);
                if (\u26032 instanceof aky && afh2 instanceof afs) {
                    \u26032 = ((afs)afh2).f(adm2, cj2);
                }
                if (\u26032 != null) {
                    wn2.a(\u26032);
                    return true;
                }
            } else if (akw2 instanceof og) {
                wn2.a((og)((Object)akw2));
                return true;
            }
            return false;
        }
        if ((!wn2.av() || wn2.bA() == null) && (\u2603 = adm2.p(cj2)).c().a(adm2, cj2, \u2603, wn2, cq2, f2, f3, f4)) {
            return true;
        }
        if (zx2 == null) {
            return false;
        }
        if (this.d()) {
            int n2 = zx2.i();
            \u2603 = zx2.b;
            boolean \u26033 = zx2.a(wn2, adm2, cj2, cq2, f2, f3, f4);
            zx2.b(n2);
            zx2.b = \u2603;
            return \u26033;
        }
        return zx2.a(wn2, adm2, cj2, cq2, f2, f3, f4);
    }

    public void a(le le2) {
        this.a = le2;
    }
}

