/*
 * Decompiled with CFR 0.152.
 */
public class afs
extends afc {
    public static final aml a = aml.a("facing", cq.c.a);
    public final int b;

    protected afs(int n2) {
        super(arm.d);
        this.j(this.M.b().a(a, cq.c));
        this.b = n2;
        this.a(yz.c);
        this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        if (adq2.p(cj2.c()).c() == this) {
            this.a(0.0625f, 0.0f, 0.0f, 0.9375f, 0.875f, 0.9375f);
        } else if (adq2.p(cj2.d()).c() == this) {
            this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 1.0f);
        } else if (adq2.p(cj2.e()).c() == this) {
            this.a(0.0f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
        } else if (adq2.p(cj2.f()).c() == this) {
            this.a(0.0625f, 0.0f, 0.0625f, 1.0f, 0.875f, 0.9375f);
        } else {
            this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.e(adm2, cj2, alz2);
        for (cq cq2 : cq.c.a) {
            cj cj3 = cj2.a(cq2);
            alz \u26032 = adm2.p(cj3);
            if (\u26032.c() != this) continue;
            this.e(adm2, cj3, \u26032);
        }
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, pr2.aP());
    }

    @Override
    public void a(adm adm2, cj cj2, alz \u260322, pr pr2, zx zx2) {
        cq cq2 = cq.b(ns.c((double)(pr2.y * 4.0f / 360.0f) + 0.5) & 3).d();
        alz \u260322 = \u260322.a(a, cq2);
        cj \u26033 = cj2.c();
        cj \u26034 = cj2.d();
        cj \u26035 = cj2.e();
        cj \u26036 = cj2.f();
        boolean \u26037 = this == adm2.p(\u26033).c();
        boolean \u26038 = this == adm2.p(\u26034).c();
        boolean \u26039 = this == adm2.p(\u26035).c();
        boolean bl2 = \u2603 = this == adm2.p(\u26036).c();
        if (\u26037 || \u26038 || \u26039 || \u2603) {
            if (cq2.k() == cq.a.a && (\u26037 || \u26038)) {
                if (\u26037) {
                    adm2.a(\u26033, \u260322, 3);
                } else {
                    adm2.a(\u26034, \u260322, 3);
                }
                adm2.a(cj2, \u260322, 3);
            } else if (cq2.k() == cq.a.c && (\u26039 || \u2603)) {
                if (\u26039) {
                    adm2.a(\u26035, \u260322, 3);
                } else {
                    adm2.a(\u26036, \u260322, 3);
                }
                adm2.a(cj2, \u260322, 3);
            }
        } else {
            adm2.a(cj2, \u260322, 3);
        }
        if (zx2.s() && (\u2603 = adm2.s(cj2)) instanceof aky) {
            ((aky)\u2603).a(zx2.q());
        }
    }

    public alz e(adm adm2, cj cj2, alz alz2) {
        if (adm2.D) {
            return alz2;
        }
        \u2603 = adm2.p(cj2.c());
        \u2603 = adm2.p(cj2.d());
        \u2603 = adm2.p(cj2.e());
        \u2603 = adm2.p(cj2.f());
        cq \u26038 = alz2.b(a);
        afh \u26032 = \u2603.c();
        afh \u26033 = \u2603.c();
        afh \u26034 = \u2603.c();
        afh \u26035 = \u2603.c();
        if (\u26032 == this || \u26033 == this) {
            cj cj3 = \u26032 == this ? cj2.c() : cj2.d();
            alz \u26036 = adm2.p(cj3.e());
            alz \u26037 = adm2.p(cj3.f());
            \u26038 = cq.f;
            cq \u26039 = \u26032 == this ? \u2603.b(a) : \u2603.b(a);
            if (\u26039 == cq.e) {
                \u26038 = cq.e;
            }
            afh \u260310 = \u26036.c();
            afh \u260311 = \u26037.c();
            if ((\u26034.o() || \u260310.o()) && !\u26035.o() && !\u260311.o()) {
                \u26038 = cq.f;
            }
            if ((\u26035.o() || \u260311.o()) && !\u26034.o() && !\u260310.o()) {
                \u26038 = cq.e;
            }
        } else {
            boolean \u260312 = \u26032.o();
            boolean \u260313 = \u26033.o();
            if (\u26034 == this || \u26035 == this) {
                \u2603 = \u26034 == this ? cj2.e() : cj2.f();
                alz alz3 = adm2.p(\u2603.c());
                \u2603 = adm2.p(\u2603.d());
                \u26038 = cq.d;
                cq \u260314 = \u26034 == this ? \u2603.b(a) : \u2603.b(a);
                if (\u260314 == cq.c) {
                    \u26038 = cq.c;
                }
                afh \u260315 = alz3.c();
                afh \u260316 = \u2603.c();
                if ((\u260312 || \u260315.o()) && !\u260313 && !\u260316.o()) {
                    \u26038 = cq.d;
                }
                if ((\u260313 || \u260316.o()) && !\u260312 && !\u260315.o()) {
                    \u26038 = cq.c;
                }
            }
        }
        alz2 = alz2.a(a, \u26038);
        adm2.a(cj2, alz2, 3);
        return alz2;
    }

    public alz f(adm adm2, cj cj2, alz alz2) {
        cq cq2 = null;
        for (cq cq3 : cq.c.a) {
            alz alz3 = adm2.p(cj2.a(cq3));
            if (alz3.c() == this) {
                return alz2;
            }
            if (!alz3.c().o()) continue;
            if (cq2 == null) {
                cq2 = cq3;
                continue;
            }
            cq2 = null;
            break;
        }
        if (cq2 != null) {
            return alz2.a(a, cq2.d());
        }
        Object \u26032 = alz2.b(a);
        if (adm2.p(cj2.a((cq)\u26032)).c().o()) {
            \u26032 = ((cq)\u26032).d();
        }
        if (adm2.p(cj2.a((cq)\u26032)).c().o()) {
            \u26032 = ((cq)\u26032).e();
        }
        if (adm2.p(cj2.a((cq)\u26032)).c().o()) {
            \u26032 = ((cq)\u26032).d();
        }
        return alz2.a(a, \u26032);
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        int n2 = 0;
        cj \u26032 = cj2.e();
        cj \u26033 = cj2.f();
        cj \u26034 = cj2.c();
        cj \u26035 = cj2.d();
        if (adm2.p(\u26032).c() == this) {
            if (this.m(adm2, \u26032)) {
                return false;
            }
            ++n2;
        }
        if (adm2.p(\u26033).c() == this) {
            if (this.m(adm2, \u26033)) {
                return false;
            }
            ++n2;
        }
        if (adm2.p(\u26034).c() == this) {
            if (this.m(adm2, \u26034)) {
                return false;
            }
            ++n2;
        }
        if (adm2.p(\u26035).c() == this) {
            if (this.m(adm2, \u26035)) {
                return false;
            }
            ++n2;
        }
        return n2 <= 1;
    }

    private boolean m(adm adm2, cj cj2) {
        if (adm2.p(cj2).c() != this) {
            return false;
        }
        for (cq cq2 : cq.c.a) {
            if (adm2.p(cj2.a(cq2)).c() != this) continue;
            return true;
        }
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        super.a(adm2, cj2, alz2, afh2);
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof aky) {
            akw2.E();
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof og) {
            oi.a(adm2, cj2, (og)((Object)akw2));
            adm2.e(cj2, this);
        }
        super.b(adm2, cj2, alz2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        oo oo2 = this.f(adm2, cj2);
        if (oo2 != null) {
            wn2.a(oo2);
            if (this.b == 0) {
                wn2.b(na.aa);
            } else if (this.b == 1) {
                wn2.b(na.U);
            }
        }
        return true;
    }

    public oo f(adm adm2, cj cj2) {
        akw akw2 = adm2.s(cj2);
        if (!(akw2 instanceof aky)) {
            return null;
        }
        oo \u26032 = (aky)akw2;
        if (this.n(adm2, cj2)) {
            return null;
        }
        for (cq cq2 : cq.c.a) {
            cj cj3 = cj2.a(cq2);
            afh \u26033 = adm2.p(cj3).c();
            if (\u26033 != this) continue;
            if (this.n(adm2, cj3)) {
                return null;
            }
            akw \u26034 = adm2.s(cj3);
            if (!(\u26034 instanceof aky)) continue;
            if (cq2 == cq.e || cq2 == cq.c) {
                \u26032 = new of("container.chestDouble", (aky)\u26034, \u26032);
                continue;
            }
            \u26032 = new of("container.chestDouble", \u26032, (aky)\u26034);
        }
        return \u26032;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new aky();
    }

    @Override
    public boolean i() {
        return this.b == 1;
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (!this.i()) {
            return 0;
        }
        int n2 = 0;
        akw \u26032 = adq2.s(cj2);
        if (\u26032 instanceof aky) {
            n2 = ((aky)\u26032).l;
        }
        return ns.a(n2, 0, 15);
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (cq2 == cq.b) {
            return this.a(adq2, cj2, alz2, cq2);
        }
        return 0;
    }

    private boolean n(adm adm2, cj cj2) {
        return this.o(adm2, cj2) || this.p(adm2, cj2);
    }

    private boolean o(adm adm2, cj cj2) {
        return adm2.p(cj2.a()).c().v();
    }

    private boolean p(adm adm2, cj cj2) {
        for (pk pk2 : adm2.a(ts.class, new aug(cj2.n(), cj2.o() + 1, cj2.p(), cj2.n() + 1, cj2.o() + 2, cj2.p() + 1))) {
            ts ts2 = (ts)pk2;
            if (!ts2.cn()) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        return xi.b(this.f(adm2, cj2));
    }

    @Override
    public alz a(int n2) {
        cq cq2 = cq.a(n2);
        if (cq2.k() == cq.a.b) {
            cq2 = cq.c;
        }
        return this.Q().a(a, cq2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

