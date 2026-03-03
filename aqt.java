/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public abstract class aqt {
    protected aqe l;
    protected cq m;
    protected int n;

    public aqt() {
    }

    protected aqt(int n2) {
        this.n = n2;
    }

    public dn b() {
        dn dn2 = new dn();
        dn2.a("id", aqr.a(this));
        dn2.a("BB", this.l.g());
        dn2.a("O", this.m == null ? -1 : this.m.b());
        dn2.a("GD", this.n);
        this.a(dn2);
        return dn2;
    }

    protected abstract void a(dn var1);

    public void a(adm adm2, dn dn2) {
        if (dn2.c("BB")) {
            this.l = new aqe(dn2.l("BB"));
        }
        this.m = (\u2603 = dn2.f("O")) == -1 ? null : cq.b(\u2603);
        this.n = dn2.f("GD");
        this.b(dn2);
    }

    protected abstract void b(dn var1);

    public void a(aqt aqt2, List<aqt> list, Random random) {
    }

    public abstract boolean a(adm var1, Random var2, aqe var3);

    public aqe c() {
        return this.l;
    }

    public int d() {
        return this.n;
    }

    public static aqt a(List<aqt> list, aqe aqe2) {
        for (aqt aqt2 : list) {
            if (aqt2.c() == null || !aqt2.c().a(aqe2)) continue;
            return aqt2;
        }
        return null;
    }

    public cj a() {
        return new cj(this.l.f());
    }

    protected boolean a(adm adm2, aqe aqe2) {
        int n2 = Math.max(this.l.a - 1, aqe2.a);
        \u2603 = Math.max(this.l.b - 1, aqe2.b);
        \u2603 = Math.max(this.l.c - 1, aqe2.c);
        \u2603 = Math.min(this.l.d + 1, aqe2.d);
        \u2603 = Math.min(this.l.e + 1, aqe2.e);
        \u2603 = Math.min(this.l.f + 1, aqe2.f);
        cj.a \u26032 = new cj.a();
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                if (adm2.p(\u26032.c(\u2603, \u2603, \u2603)).c().t().d()) {
                    return true;
                }
                if (!adm2.p(\u26032.c(\u2603, \u2603, \u2603)).c().t().d()) continue;
                return true;
            }
        }
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                if (adm2.p(\u26032.c(\u2603, \u2603, \u2603)).c().t().d()) {
                    return true;
                }
                if (!adm2.p(\u26032.c(\u2603, \u2603, \u2603)).c().t().d()) continue;
                return true;
            }
        }
        for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                if (adm2.p(\u26032.c(n2, \u2603, \u2603)).c().t().d()) {
                    return true;
                }
                if (!adm2.p(\u26032.c(\u2603, \u2603, \u2603)).c().t().d()) continue;
                return true;
            }
        }
        return false;
    }

    protected int a(int n2, int n3) {
        if (this.m == null) {
            return n2;
        }
        switch (this.m) {
            case c: 
            case d: {
                return this.l.a + n2;
            }
            case e: {
                return this.l.d - n3;
            }
            case f: {
                return this.l.a + n3;
            }
        }
        return n2;
    }

    protected int d(int n2) {
        if (this.m == null) {
            return n2;
        }
        return n2 + this.l.b;
    }

    protected int b(int n2, int n3) {
        if (this.m == null) {
            return n3;
        }
        switch (this.m) {
            case c: {
                return this.l.f - n3;
            }
            case d: {
                return this.l.c + n3;
            }
            case e: 
            case f: {
                return this.l.c + n2;
            }
        }
        return n3;
    }

    protected int a(afh afh22, int n2) {
        afh afh22;
        if (afh22 == afi.av) {
            if (this.m == cq.e || this.m == cq.f) {
                if (n2 == 1) {
                    return 0;
                }
                return 1;
            }
        } else if (afh22 instanceof agh) {
            if (this.m == cq.d) {
                if (n2 == 0) {
                    return 2;
                }
                if (n2 == 2) {
                    return 0;
                }
            } else {
                if (this.m == cq.e) {
                    return n2 + 1 & 3;
                }
                if (this.m == cq.f) {
                    return n2 + 3 & 3;
                }
            }
        } else if (afh22 == afi.aw || afh22 == afi.ad || afh22 == afi.bA || afh22 == afi.bv || afh22 == afi.bO) {
            if (this.m == cq.d) {
                if (n2 == 2) {
                    return 3;
                }
                if (n2 == 3) {
                    return 2;
                }
            } else if (this.m == cq.e) {
                if (n2 == 0) {
                    return 2;
                }
                if (n2 == 1) {
                    return 3;
                }
                if (n2 == 2) {
                    return 0;
                }
                if (n2 == 3) {
                    return 1;
                }
            } else if (this.m == cq.f) {
                if (n2 == 0) {
                    return 2;
                }
                if (n2 == 1) {
                    return 3;
                }
                if (n2 == 2) {
                    return 1;
                }
                if (n2 == 3) {
                    return 0;
                }
            }
        } else if (afh22 == afi.au) {
            if (this.m == cq.d) {
                if (n2 == cq.c.a()) {
                    return cq.d.a();
                }
                if (n2 == cq.d.a()) {
                    return cq.c.a();
                }
            } else if (this.m == cq.e) {
                if (n2 == cq.c.a()) {
                    return cq.e.a();
                }
                if (n2 == cq.d.a()) {
                    return cq.f.a();
                }
                if (n2 == cq.e.a()) {
                    return cq.c.a();
                }
                if (n2 == cq.f.a()) {
                    return cq.d.a();
                }
            } else if (this.m == cq.f) {
                if (n2 == cq.c.a()) {
                    return cq.f.a();
                }
                if (n2 == cq.d.a()) {
                    return cq.e.a();
                }
                if (n2 == cq.e.a()) {
                    return cq.c.a();
                }
                if (n2 == cq.f.a()) {
                    return cq.d.a();
                }
            }
        } else if (afh22 == afi.aG) {
            if (this.m == cq.d) {
                if (n2 == 3) {
                    return 4;
                }
                if (n2 == 4) {
                    return 3;
                }
            } else if (this.m == cq.e) {
                if (n2 == 3) {
                    return 1;
                }
                if (n2 == 4) {
                    return 2;
                }
                if (n2 == 2) {
                    return 3;
                }
                if (n2 == 1) {
                    return 4;
                }
            } else if (this.m == cq.f) {
                if (n2 == 3) {
                    return 2;
                }
                if (n2 == 4) {
                    return 1;
                }
                if (n2 == 2) {
                    return 3;
                }
                if (n2 == 1) {
                    return 4;
                }
            }
        } else if (afh22 == afi.bR || afh22 instanceof age) {
            cq cq2 = cq.b(n2);
            if (this.m == cq.d) {
                if (cq2 == cq.d || cq2 == cq.c) {
                    return cq2.d().b();
                }
            } else if (this.m == cq.e) {
                if (cq2 == cq.c) {
                    return cq.e.b();
                }
                if (cq2 == cq.d) {
                    return cq.f.b();
                }
                if (cq2 == cq.e) {
                    return cq.c.b();
                }
                if (cq2 == cq.f) {
                    return cq.d.b();
                }
            } else if (this.m == cq.f) {
                if (cq2 == cq.c) {
                    return cq.f.b();
                }
                if (cq2 == cq.d) {
                    return cq.e.b();
                }
                if (cq2 == cq.e) {
                    return cq.c.b();
                }
                if (cq2 == cq.f) {
                    return cq.d.b();
                }
            }
        } else if (afh22 == afi.J || afh22 == afi.F || afh22 == afi.ay || afh22 == afi.z) {
            if (this.m == cq.d) {
                if (n2 == cq.c.a() || n2 == cq.d.a()) {
                    return cq.a(n2).d().a();
                }
            } else if (this.m == cq.e) {
                if (n2 == cq.c.a()) {
                    return cq.e.a();
                }
                if (n2 == cq.d.a()) {
                    return cq.f.a();
                }
                if (n2 == cq.e.a()) {
                    return cq.c.a();
                }
                if (n2 == cq.f.a()) {
                    return cq.d.a();
                }
            } else if (this.m == cq.f) {
                if (n2 == cq.c.a()) {
                    return cq.f.a();
                }
                if (n2 == cq.d.a()) {
                    return cq.e.a();
                }
                if (n2 == cq.e.a()) {
                    return cq.c.a();
                }
                if (n2 == cq.f.a()) {
                    return cq.d.a();
                }
            }
        }
        return n2;
    }

    protected void a(adm adm2, alz alz2, int n2, int n3, int n4, aqe aqe2) {
        cj cj2 = new cj(this.a(n2, n4), this.d(n3), this.b(n2, n4));
        if (!aqe2.b(cj2)) {
            return;
        }
        adm2.a(cj2, alz2, 2);
    }

    protected alz a(adm adm2, int n2, int n3, int n4, aqe aqe2) {
        int n5 = this.a(n2, n4);
        cj \u26032 = new cj(n5, \u2603 = this.d(n3), \u2603 = this.b(n2, n4));
        if (!aqe2.b(\u26032)) {
            return afi.a.Q();
        }
        return adm2.p(\u26032);
    }

    protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7) {
        for (\u2603 = n3; \u2603 <= n6; ++\u2603) {
            for (\u2603 = n2; \u2603 <= n5; ++\u2603) {
                for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                    this.a(adm2, afi.a.Q(), \u2603, \u2603, \u2603, aqe2);
                }
            }
        }
    }

    protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7, alz alz2, alz alz3, boolean bl2) {
        for (int i2 = n3; i2 <= n6; ++i2) {
            for (\u2603 = n2; \u2603 <= n5; ++\u2603) {
                for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                    if (bl2 && this.a(adm2, \u2603, i2, \u2603, aqe2).c().t() == arm.a) continue;
                    if (i2 == n3 || i2 == n6 || \u2603 == n2 || \u2603 == n5 || \u2603 == n4 || \u2603 == n7) {
                        this.a(adm2, alz2, \u2603, i2, \u2603, aqe2);
                        continue;
                    }
                    this.a(adm2, alz3, \u2603, i2, \u2603, aqe2);
                }
            }
        }
    }

    protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl2, Random random, a a2) {
        for (int i2 = n3; i2 <= n6; ++i2) {
            for (\u2603 = n2; \u2603 <= n5; ++\u2603) {
                for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                    if (bl2 && this.a(adm2, \u2603, i2, \u2603, aqe2).c().t() == arm.a) continue;
                    a2.a(random, \u2603, i2, \u2603, i2 == n3 || i2 == n6 || \u2603 == n2 || \u2603 == n5 || \u2603 == n4 || \u2603 == n7);
                    this.a(adm2, a2.a(), \u2603, i2, \u2603, aqe2);
                }
            }
        }
    }

    protected void a(adm adm2, aqe aqe2, Random random, float f2, int n2, int n3, int n4, int n5, int n6, int n7, alz alz2, alz alz3, boolean bl2) {
        for (int i2 = n3; i2 <= n6; ++i2) {
            for (\u2603 = n2; \u2603 <= n5; ++\u2603) {
                for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                    if (random.nextFloat() > f2 || bl2 && this.a(adm2, \u2603, i2, \u2603, aqe2).c().t() == arm.a) continue;
                    if (i2 == n3 || i2 == n6 || \u2603 == n2 || \u2603 == n5 || \u2603 == n4 || \u2603 == n7) {
                        this.a(adm2, alz2, \u2603, i2, \u2603, aqe2);
                        continue;
                    }
                    this.a(adm2, alz3, \u2603, i2, \u2603, aqe2);
                }
            }
        }
    }

    protected void a(adm adm2, aqe aqe2, Random random, float f2, int n2, int n3, int n4, alz alz2) {
        if (random.nextFloat() < f2) {
            this.a(adm2, alz2, n2, n3, n4, aqe2);
        }
    }

    protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7, alz alz2, boolean bl2) {
        float f2 = n5 - n2 + 1;
        \u2603 = n6 - n3 + 1;
        \u2603 = n7 - n4 + 1;
        \u2603 = (float)n2 + f2 / 2.0f;
        \u2603 = (float)n4 + \u2603 / 2.0f;
        for (int i2 = n3; i2 <= n6; ++i2) {
            float f3 = (float)(i2 - n3) / \u2603;
            for (int i3 = n2; i3 <= n5; ++i3) {
                float f4 = ((float)i3 - \u2603) / (f2 * 0.5f);
                for (int i4 = n4; i4 <= n7; ++i4) {
                    float f5 = ((float)i4 - \u2603) / (\u2603 * 0.5f);
                    if (bl2 && this.a(adm2, i3, i2, i4, aqe2).c().t() == arm.a || !((\u2603 = f4 * f4 + f3 * f3 + f5 * f5) <= 1.05f)) continue;
                    this.a(adm2, alz2, i3, i2, i4, aqe2);
                }
            }
        }
    }

    protected void b(adm adm2, int n2, int n3, int n4, aqe aqe2) {
        cj cj2 = new cj(this.a(n2, n4), this.d(n3), this.b(n2, n4));
        if (!aqe2.b(cj2)) {
            return;
        }
        while (!adm2.d(cj2) && cj2.o() < 255) {
            adm2.a(cj2, afi.a.Q(), 2);
            cj2 = cj2.a();
        }
    }

    protected void b(adm adm2, alz alz2, int n2, int n3, int n4, aqe aqe2) {
        int n5 = this.a(n2, n4);
        if (!aqe2.b(new cj(n5, \u2603 = this.d(n3), \u2603 = this.b(n2, n4)))) {
            return;
        }
        while ((adm2.d(new cj(n5, \u2603, \u2603)) || adm2.p(new cj(n5, \u2603, \u2603)).c().t().d()) && \u2603 > 1) {
            adm2.a(new cj(n5, \u2603, \u2603), alz2, 2);
            --\u2603;
        }
    }

    protected boolean a(adm adm2, aqe aqe2, Random random, int n2, int n3, int n4, List<ob> list, int n5) {
        cj cj2 = new cj(this.a(n2, n4), this.d(n3), this.b(n2, n4));
        if (aqe2.b(cj2) && adm2.p(cj2).c() != afi.ae) {
            alz alz2 = afi.ae.Q();
            adm2.a(cj2, afi.ae.f(adm2, cj2, alz2), 2);
            akw \u26032 = adm2.s(cj2);
            if (\u26032 instanceof aky) {
                ob.a(random, list, (aky)\u26032, n5);
            }
            return true;
        }
        return false;
    }

    protected boolean a(adm adm2, aqe aqe2, Random random, int n2, int n3, int n4, int n5, List<ob> list, int n6) {
        cj cj2 = new cj(this.a(n2, n4), this.d(n3), this.b(n2, n4));
        if (aqe2.b(cj2) && adm2.p(cj2).c() != afi.z) {
            adm2.a(cj2, afi.z.a(this.a(afi.z, n5)), 2);
            akw akw2 = adm2.s(cj2);
            if (akw2 instanceof alc) {
                ob.a(random, list, (alc)akw2, n6);
            }
            return true;
        }
        return false;
    }

    protected void a(adm adm2, aqe aqe2, Random random, int n2, int n3, int n4, cq cq2) {
        cj cj2 = new cj(this.a(n2, n4), this.d(n3), this.b(n2, n4));
        if (aqe2.b(cj2)) {
            zb.a(adm2, cj2, cq2.f(), afi.ao);
        }
    }

    public void a(int n2, int n3, int n4) {
        this.l.a(n2, n3, n4);
    }

    public static abstract class a {
        protected alz a = afi.a.Q();

        protected a() {
        }

        public abstract void a(Random var1, int var2, int var3, int var4, boolean var5);

        public alz a() {
            return this.a;
        }
    }
}

