/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.cache.LoadingCache;
import java.util.Random;

public class aip
extends ahj {
    public static final amm<cq.a> a = amm.a((String)"axis", cq.a.class, (Enum[])new cq.a[]{cq.a.a, cq.a.c});

    public aip() {
        super(arm.E, false);
        this.j(this.M.b().a(a, cq.a.a));
        this.a(true);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        super.b(adm2, cj2, alz2, random);
        if (adm2.t.d() && adm2.Q().b("doMobSpawning") && random.nextInt(2000) < adm2.aa().a()) {
            int n2 = cj2.o();
            cj \u26032 = cj2;
            while (!adm.a(adm2, \u26032) && \u26032.o() > 0) {
                \u26032 = \u26032.b();
            }
            if (n2 > 0 && !adm2.p(\u26032.a()).c().v() && (\u2603 = aax.a(adm2, 57, (double)\u26032.n() + 0.5, (double)\u26032.o() + 1.1, (double)\u26032.p() + 0.5)) != null) {
                \u2603.aj = \u2603.aq();
            }
        }
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        cq.a a2 = adq2.p(cj2).b(a);
        float \u26032 = 0.125f;
        float \u26033 = 0.125f;
        if (a2 == cq.a.a) {
            \u26032 = 0.5f;
        }
        if (a2 == cq.a.c) {
            \u26033 = 0.5f;
        }
        this.a(0.5f - \u26032, 0.0f, 0.5f - \u26033, 0.5f + \u26032, 1.0f, 0.5f + \u26033);
    }

    public static int a(cq.a a2) {
        if (a2 == cq.a.a) {
            return 1;
        }
        if (a2 == cq.a.c) {
            return 2;
        }
        return 0;
    }

    @Override
    public boolean d() {
        return false;
    }

    public boolean e(adm adm2, cj cj2) {
        a a2 = new a(adm2, cj2, cq.a.a);
        if (a2.d() && a2.e == 0) {
            a2.e();
            return true;
        }
        \u2603 = new a(adm2, cj2, cq.a.c);
        if (\u2603.d() && \u2603.e == 0) {
            \u2603.e();
            return true;
        }
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        cq.a a2 = alz2.b(a);
        if (a2 == cq.a.a) {
            a a3 = new a(adm2, cj2, cq.a.a);
            if (!a3.d() || a3.e < a3.h * a3.g) {
                adm2.a(cj2, afi.a.Q());
            }
        } else if (!(a2 != cq.a.c || (\u2603 = new a(adm2, cj2, cq.a.c)).d() && \u2603.e >= \u2603.h * \u2603.g)) {
            adm2.a(cj2, afi.a.Q());
        }
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        cq.a a2 = null;
        alz \u26032 = adq2.p(cj2);
        if (adq2.p(cj2).c() == this) {
            a2 = \u26032.b(a);
            if (a2 == null) {
                return false;
            }
            if (a2 == cq.a.c && cq2 != cq.f && cq2 != cq.e) {
                return false;
            }
            if (a2 == cq.a.a && cq2 != cq.d && cq2 != cq.c) {
                return false;
            }
        }
        boolean \u26033 = adq2.p(cj2.e()).c() == this && adq2.p(cj2.f(2)).c() != this;
        boolean \u26034 = adq2.p(cj2.f()).c() == this && adq2.p(cj2.g(2)).c() != this;
        boolean \u26035 = adq2.p(cj2.c()).c() == this && adq2.p(cj2.d(2)).c() != this;
        boolean \u26036 = adq2.p(cj2.d()).c() == this && adq2.p(cj2.e(2)).c() != this;
        boolean \u26037 = \u26033 || \u26034 || a2 == cq.a.a;
        boolean bl2 = \u2603 = \u26035 || \u26036 || a2 == cq.a.c;
        if (\u26037 && cq2 == cq.e) {
            return true;
        }
        if (\u26037 && cq2 == cq.f) {
            return true;
        }
        if (\u2603 && cq2 == cq.c) {
            return true;
        }
        return \u2603 && cq2 == cq.d;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public adf m() {
        return adf.d;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (pk2.m == null && pk2.l == null) {
            pk2.d(cj2);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        if (random.nextInt(100) == 0) {
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "portal.portal", 0.5f, random.nextFloat() * 0.4f + 0.8f, false);
        }
        for (int i2 = 0; i2 < 4; ++i2) {
            double d2 = (float)cj2.n() + random.nextFloat();
            \u2603 = (float)cj2.o() + random.nextFloat();
            \u2603 = (float)cj2.p() + random.nextFloat();
            \u2603 = ((double)random.nextFloat() - 0.5) * 0.5;
            \u2603 = ((double)random.nextFloat() - 0.5) * 0.5;
            \u2603 = ((double)random.nextFloat() - 0.5) * 0.5;
            int \u26032 = random.nextInt(2) * 2 - 1;
            if (adm2.p(cj2.e()).c() == this || adm2.p(cj2.f()).c() == this) {
                \u2603 = (double)cj2.p() + 0.5 + 0.25 * (double)\u26032;
                \u2603 = random.nextFloat() * 2.0f * (float)\u26032;
            } else {
                d2 = (double)cj2.n() + 0.5 + 0.25 * (double)\u26032;
                \u2603 = random.nextFloat() * 2.0f * (float)\u26032;
            }
            adm2.a(cy.y, d2, \u2603, \u2603, \u2603, \u2603, \u2603, new int[0]);
        }
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return null;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, (n2 & 3) == 2 ? cq.a.c : cq.a.a);
    }

    @Override
    public int c(alz alz2) {
        return aip.a(alz2.b(a));
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    /*
     * WARNING - void declaration
     */
    public amd.b f(adm adm2, cj cj2) {
        void var9_11;
        cq.a a2 = cq.a.c;
        a \u26032 = new a(adm2, cj2, cq.a.a);
        LoadingCache<cj, amc> \u26033 = amd.a(adm2, true);
        if (!\u26032.d()) {
            a2 = cq.a.a;
            \u26032 = new a(adm2, cj2, cq.a.c);
        }
        if (!\u26032.d()) {
            return new amd.b(cj2, cq.c, cq.b, \u26033, 1, 1, 1);
        }
        int[] \u26034 = new int[cq.b.values().length];
        cq \u26035 = \u26032.c.f();
        cj \u26036 = \u26032.f.b(\u26032.a() - 1);
        for (cq.b b2 : cq.b.values()) {
            amd.b b3 = new amd.b(\u26035.c() == b2 ? \u26036 : \u26036.a(\u26032.c, \u26032.b() - 1), cq.a(b2, a2), cq.b, \u26033, \u26032.b(), \u26032.a(), 1);
            for (int i2 = 0; i2 < \u26032.b(); ++i2) {
                for (int i3 = 0; i3 < \u26032.a(); ++i3) {
                    amc amc2 = b3.a(i2, i3, 1);
                    if (amc2.a() == null || amc2.a().c().t() == arm.a) continue;
                    int n2 = b2.ordinal();
                    \u26034[n2] = \u26034[n2] + 1;
                }
            }
        }
        cq.b b4 = cq.b.a;
        for (cq.b object2 : cq.b.values()) {
            if (\u26034[object2.ordinal()] >= \u26034[var9_11.ordinal()]) continue;
            cq.b b5 = object2;
        }
        return new amd.b(\u26035.c() == var9_11 ? \u26036 : \u26036.a(\u26032.c, \u26032.b() - 1), cq.a((cq.b)var9_11, a2), cq.b, \u26033, \u26032.b(), \u26032.a(), 1);
    }

    public static class a {
        private final adm a;
        private final cq.a b;
        private final cq c;
        private final cq d;
        private int e = 0;
        private cj f;
        private int g;
        private int h;

        public a(adm adm2, cj cj2, cq.a a2) {
            this.a = adm2;
            this.b = a2;
            if (a2 == cq.a.a) {
                this.d = cq.f;
                this.c = cq.e;
            } else {
                this.d = cq.c;
                this.c = cq.d;
            }
            cj cj3 = cj2;
            while (cj2.o() > cj3.o() - 21 && cj2.o() > 0 && this.a(adm2.p(cj2.b()).c())) {
                cj2 = cj2.b();
            }
            int \u26032 = this.a(cj2, this.d) - 1;
            if (\u26032 >= 0) {
                this.f = cj2.a(this.d, \u26032);
                this.h = this.a(this.f, this.c);
                if (this.h < 2 || this.h > 21) {
                    this.f = null;
                    this.h = 0;
                }
            }
            if (this.f != null) {
                this.g = this.c();
            }
        }

        protected int a(cj cj22, cq cq2) {
            cj cj22;
            for (int i2 = 0; i2 < 22 && this.a(this.a.p((cj)(\u26032 = cj22.a(cq2, i2))).c()) && this.a.p(((cj)\u26032).b()).c() == afi.Z; ++i2) {
            }
            Object \u26032 = this.a.p(cj22.a(cq2, i2)).c();
            if (\u26032 == afi.Z) {
                return i2;
            }
            return 0;
        }

        public int a() {
            return this.g;
        }

        public int b() {
            return this.h;
        }

        protected int c() {
            int n2;
            this.g = 0;
            block0: while (this.g < 21) {
                for (n2 = 0; n2 < this.h; ++n2) {
                    cj cj2 = this.f.a(this.c, n2).b(this.g);
                    afh \u26032 = this.a.p(cj2).c();
                    if (!this.a(\u26032)) break block0;
                    if (\u26032 == afi.aY) {
                        ++this.e;
                    }
                    if (n2 == 0 ? (\u26032 = this.a.p(cj2.a(this.d)).c()) != afi.Z : n2 == this.h - 1 && (\u26032 = this.a.p(cj2.a(this.c)).c()) != afi.Z) break block0;
                }
                ++this.g;
            }
            for (n2 = 0; n2 < this.h; ++n2) {
                if (this.a.p(this.f.a(this.c, n2).b(this.g)).c() == afi.Z) continue;
                this.g = 0;
                break;
            }
            if (this.g > 21 || this.g < 3) {
                this.f = null;
                this.h = 0;
                this.g = 0;
                return 0;
            }
            return this.g;
        }

        protected boolean a(afh afh2) {
            return afh2.J == arm.a || afh2 == afi.ab || afh2 == afi.aY;
        }

        public boolean d() {
            return this.f != null && this.h >= 2 && this.h <= 21 && this.g >= 3 && this.g <= 21;
        }

        public void e() {
            for (int i2 = 0; i2 < this.h; ++i2) {
                cj cj2 = this.f.a(this.c, i2);
                for (int i3 = 0; i3 < this.g; ++i3) {
                    this.a.a(cj2.b(i3), afi.aY.Q().a(a, this.b), 2);
                }
            }
        }
    }
}

