/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public abstract class afe
extends afh {
    protected final boolean a;

    public static boolean e(adm adm2, cj cj2) {
        return afe.d(adm2.p(cj2));
    }

    public static boolean d(alz alz2) {
        afh afh2 = alz2.c();
        return afh2 == afi.av || afh2 == afi.D || afh2 == afi.E || afh2 == afi.cs;
    }

    protected afe(boolean bl2) {
        super(arm.q);
        this.a = bl2;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.a(yz.e);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, aui2, aui3);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        b b2 = \u2603 = alz2.c() == this ? alz2.b(this.n()) : null;
        if (\u2603 != null && \u2603.c()) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.625f, 1.0f);
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        }
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return adm.a(adm2, cj2.b());
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        if (!adm2.D) {
            alz2 = this.a(adm2, cj2, alz2, true);
            if (this.a) {
                this.a(adm2, cj2, alz2, this);
            }
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (adm2.D) {
            return;
        }
        b b2 = alz2.b(this.n());
        boolean \u26032 = false;
        if (!adm.a(adm2, cj2.b())) {
            \u26032 = true;
        }
        if (b2 == b.c && !adm.a(adm2, cj2.f())) {
            \u26032 = true;
        } else if (b2 == b.d && !adm.a(adm2, cj2.e())) {
            \u26032 = true;
        } else if (b2 == b.e && !adm.a(adm2, cj2.c())) {
            \u26032 = true;
        } else if (b2 == b.f && !adm.a(adm2, cj2.d())) {
            \u26032 = true;
        }
        if (\u26032) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        } else {
            this.b(adm2, cj2, alz2, afh2);
        }
    }

    protected void b(adm adm2, cj cj2, alz alz2, afh afh2) {
    }

    protected alz a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        if (adm2.D) {
            return alz2;
        }
        return new a(adm2, cj2, alz2).a(adm2.z(cj2), bl2).b();
    }

    @Override
    public int k() {
        return 0;
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        super.b(adm2, cj2, alz2);
        if (alz2.b(this.n()).c()) {
            adm2.c(cj2.a(), this);
        }
        if (this.a) {
            adm2.c(cj2, this);
            adm2.c(cj2.b(), this);
        }
    }

    public abstract amo<b> n();

    public static enum b implements nw
    {
        a(0, "north_south"),
        b(1, "east_west"),
        c(2, "ascending_east"),
        d(3, "ascending_west"),
        e(4, "ascending_north"),
        f(5, "ascending_south"),
        g(6, "south_east"),
        h(7, "south_west"),
        i(8, "north_west"),
        j(9, "north_east");

        private static final b[] k;
        private final int l;
        private final String m;

        private b(int n3, String string2) {
            this.l = n3;
            this.m = string2;
        }

        public int a() {
            return this.l;
        }

        public String toString() {
            return this.m;
        }

        public boolean c() {
            return this == e || this == c || this == f || this == d;
        }

        public static b a(int n2) {
            if (n2 < 0 || n2 >= k.length) {
                n2 = 0;
            }
            return k[n2];
        }

        @Override
        public String l() {
            return this.m;
        }

        static {
            k = new b[afe$b.values().length];
            b[] bArray = afe$b.values();
            int \u26032 = bArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                afe$b.k[\u2603.a()] = \u2603 = bArray[i2];
            }
        }
    }

    public class a {
        private final adm b;
        private final cj c;
        private final afe d;
        private alz e;
        private final boolean f;
        private final List<cj> g = Lists.newArrayList();

        public a(adm adm2, cj cj2, alz alz2) {
            this.b = adm2;
            this.c = cj2;
            this.e = alz2;
            this.d = (afe)alz2.c();
            b b2 = alz2.b(afe.this.n());
            this.f = this.d.a;
            this.a(b2);
        }

        private void a(b b2) {
            this.g.clear();
            switch (b2) {
                case a: {
                    this.g.add(this.c.c());
                    this.g.add(this.c.d());
                    break;
                }
                case b: {
                    this.g.add(this.c.e());
                    this.g.add(this.c.f());
                    break;
                }
                case c: {
                    this.g.add(this.c.e());
                    this.g.add(this.c.f().a());
                    break;
                }
                case d: {
                    this.g.add(this.c.e().a());
                    this.g.add(this.c.f());
                    break;
                }
                case e: {
                    this.g.add(this.c.c().a());
                    this.g.add(this.c.d());
                    break;
                }
                case f: {
                    this.g.add(this.c.c());
                    this.g.add(this.c.d().a());
                    break;
                }
                case g: {
                    this.g.add(this.c.f());
                    this.g.add(this.c.d());
                    break;
                }
                case h: {
                    this.g.add(this.c.e());
                    this.g.add(this.c.d());
                    break;
                }
                case i: {
                    this.g.add(this.c.e());
                    this.g.add(this.c.c());
                    break;
                }
                case j: {
                    this.g.add(this.c.f());
                    this.g.add(this.c.c());
                }
            }
        }

        private void c() {
            for (int i2 = 0; i2 < this.g.size(); ++i2) {
                a a2 = this.b(this.g.get(i2));
                if (a2 == null || !a2.a(this)) {
                    this.g.remove(i2--);
                    continue;
                }
                this.g.set(i2, a2.c);
            }
        }

        private boolean a(cj cj2) {
            return afe.e(this.b, cj2) || afe.e(this.b, cj2.a()) || afe.e(this.b, cj2.b());
        }

        private a b(cj cj2) {
            \u26032 = cj2;
            alz alz2 = this.b.p(\u26032);
            if (afe.d(alz2)) {
                return new a(this.b, \u26032, alz2);
            }
            cj \u26032 = cj2.a();
            alz2 = this.b.p(\u26032);
            if (afe.d(alz2)) {
                return new a(this.b, \u26032, alz2);
            }
            \u26032 = cj2.b();
            alz2 = this.b.p(\u26032);
            if (afe.d(alz2)) {
                return new a(this.b, \u26032, alz2);
            }
            return null;
        }

        private boolean a(a a2) {
            return this.c(a2.c);
        }

        private boolean c(cj cj2) {
            for (int i2 = 0; i2 < this.g.size(); ++i2) {
                cj cj3 = this.g.get(i2);
                if (cj3.n() != cj2.n() || cj3.p() != cj2.p()) continue;
                return true;
            }
            return false;
        }

        protected int a() {
            int n2 = 0;
            for (cq cq2 : cq.c.a) {
                if (!this.a(this.c.a(cq2))) continue;
                ++n2;
            }
            return n2;
        }

        private boolean b(a a2) {
            return this.a(a2) || this.g.size() != 2;
        }

        private void c(a a2) {
            this.g.add(a2.c);
            cj cj2 = this.c.c();
            \u2603 = this.c.d();
            \u2603 = this.c.e();
            \u2603 = this.c.f();
            boolean \u26032 = this.c(cj2);
            boolean \u26033 = this.c(\u2603);
            boolean \u26034 = this.c(\u2603);
            boolean \u26035 = this.c(\u2603);
            b \u26036 = null;
            if (\u26032 || \u26033) {
                \u26036 = afe$b.a;
            }
            if (\u26034 || \u26035) {
                \u26036 = afe$b.b;
            }
            if (!this.f) {
                if (\u26033 && \u26035 && !\u26032 && !\u26034) {
                    \u26036 = afe$b.g;
                }
                if (\u26033 && \u26034 && !\u26032 && !\u26035) {
                    \u26036 = afe$b.h;
                }
                if (\u26032 && \u26034 && !\u26033 && !\u26035) {
                    \u26036 = afe$b.i;
                }
                if (\u26032 && \u26035 && !\u26033 && !\u26034) {
                    \u26036 = afe$b.j;
                }
            }
            if (\u26036 == afe$b.a) {
                if (afe.e(this.b, cj2.a())) {
                    \u26036 = afe$b.e;
                }
                if (afe.e(this.b, \u2603.a())) {
                    \u26036 = afe$b.f;
                }
            }
            if (\u26036 == afe$b.b) {
                if (afe.e(this.b, \u2603.a())) {
                    \u26036 = afe$b.c;
                }
                if (afe.e(this.b, \u2603.a())) {
                    \u26036 = afe$b.d;
                }
            }
            if (\u26036 == null) {
                \u26036 = afe$b.a;
            }
            this.e = this.e.a(this.d.n(), \u26036);
            this.b.a(this.c, this.e, 3);
        }

        private boolean d(cj cj2) {
            a a2 = this.b(cj2);
            if (a2 == null) {
                return false;
            }
            a2.c();
            return a2.b(this);
        }

        public a a(boolean bl2, boolean bl3) {
            cj cj2 = this.c.c();
            \u2603 = this.c.d();
            \u2603 = this.c.e();
            \u2603 = this.c.f();
            boolean \u26032 = this.d(cj2);
            boolean \u26033 = this.d(\u2603);
            boolean \u26034 = this.d(\u2603);
            boolean \u26035 = this.d(\u2603);
            b \u26036 = null;
            if ((\u26032 || \u26033) && !\u26034 && !\u26035) {
                \u26036 = afe$b.a;
            }
            if ((\u26034 || \u26035) && !\u26032 && !\u26033) {
                \u26036 = afe$b.b;
            }
            if (!this.f) {
                if (\u26033 && \u26035 && !\u26032 && !\u26034) {
                    \u26036 = afe$b.g;
                }
                if (\u26033 && \u26034 && !\u26032 && !\u26035) {
                    \u26036 = afe$b.h;
                }
                if (\u26032 && \u26034 && !\u26033 && !\u26035) {
                    \u26036 = afe$b.i;
                }
                if (\u26032 && \u26035 && !\u26033 && !\u26034) {
                    \u26036 = afe$b.j;
                }
            }
            if (\u26036 == null) {
                if (\u26032 || \u26033) {
                    \u26036 = afe$b.a;
                }
                if (\u26034 || \u26035) {
                    \u26036 = afe$b.b;
                }
                if (!this.f) {
                    if (bl2) {
                        if (\u26033 && \u26035) {
                            \u26036 = afe$b.g;
                        }
                        if (\u26034 && \u26033) {
                            \u26036 = afe$b.h;
                        }
                        if (\u26035 && \u26032) {
                            \u26036 = afe$b.j;
                        }
                        if (\u26032 && \u26034) {
                            \u26036 = afe$b.i;
                        }
                    } else {
                        if (\u26032 && \u26034) {
                            \u26036 = afe$b.i;
                        }
                        if (\u26035 && \u26032) {
                            \u26036 = afe$b.j;
                        }
                        if (\u26034 && \u26033) {
                            \u26036 = afe$b.h;
                        }
                        if (\u26033 && \u26035) {
                            \u26036 = afe$b.g;
                        }
                    }
                }
            }
            if (\u26036 == afe$b.a) {
                if (afe.e(this.b, cj2.a())) {
                    \u26036 = afe$b.e;
                }
                if (afe.e(this.b, \u2603.a())) {
                    \u26036 = afe$b.f;
                }
            }
            if (\u26036 == afe$b.b) {
                if (afe.e(this.b, \u2603.a())) {
                    \u26036 = afe$b.c;
                }
                if (afe.e(this.b, \u2603.a())) {
                    \u26036 = afe$b.d;
                }
            }
            if (\u26036 == null) {
                \u26036 = afe$b.a;
            }
            this.a(\u26036);
            this.e = this.e.a(this.d.n(), \u26036);
            if (bl3 || this.b.p(this.c) != this.e) {
                this.b.a(this.c, this.e, 3);
                for (int i2 = 0; i2 < this.g.size(); ++i2) {
                    a a2 = this.b(this.g.get(i2));
                    if (a2 == null) continue;
                    a2.c();
                    if (!a2.b(this)) continue;
                    a2.c(this);
                }
            }
            return this;
        }

        public alz b() {
            return this.e;
        }
    }
}

