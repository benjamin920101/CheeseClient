/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class akl
extends afh {
    public static final amk a = amk.a("up");
    public static final amk b = amk.a("north");
    public static final amk N = amk.a("east");
    public static final amk O = amk.a("south");
    public static final amk P = amk.a("west");
    public static final amm<a> Q = amm.a("variant", a.class);

    public akl(afh afh2) {
        super(afh2.J);
        this.j(this.M.b().a(a, false).a(b, false).a(N, false).a(O, false).a(P, false).a(Q, akl$a.a));
        this.c(afh2.w);
        this.b(afh2.x / 3.0f);
        this.a(afh2.H);
        this.a(yz.b);
    }

    @Override
    public String f() {
        return di.a(this.a() + "." + akl$a.a.c() + ".name");
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean b(adq adq2, cj cj2) {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        boolean bl2 = this.e(adq2, cj2.c());
        \u2603 = this.e(adq2, cj2.d());
        \u2603 = this.e(adq2, cj2.e());
        \u2603 = this.e(adq2, cj2.f());
        float \u26032 = 0.25f;
        float \u26033 = 0.75f;
        float \u26034 = 0.25f;
        float \u26035 = 0.75f;
        float \u26036 = 1.0f;
        if (bl2) {
            \u26034 = 0.0f;
        }
        if (\u2603) {
            \u26035 = 1.0f;
        }
        if (\u2603) {
            \u26032 = 0.0f;
        }
        if (\u2603) {
            \u26033 = 1.0f;
        }
        if (bl2 && \u2603 && !\u2603 && !\u2603) {
            \u26036 = 0.8125f;
            \u26032 = 0.3125f;
            \u26033 = 0.6875f;
        } else if (!bl2 && !\u2603 && \u2603 && \u2603) {
            \u26036 = 0.8125f;
            \u26034 = 0.3125f;
            \u26035 = 0.6875f;
        }
        this.a(\u26032, 0.0f, \u26034, \u26033, \u26036, \u26035);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        this.F = 1.5;
        return super.a(adm2, cj2, alz2);
    }

    public boolean e(adq adq2, cj cj2) {
        afh afh2 = adq2.p(cj2).c();
        if (afh2 == afi.cv) {
            return false;
        }
        if (afh2 == this || afh2 instanceof agu) {
            return true;
        }
        if (afh2.J.k() && afh2.d()) {
            return afh2.J != arm.C;
        }
        return false;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : akl$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(Q).a();
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (cq2 == cq.a) {
            return super.a(adq2, cj2, cq2);
        }
        return true;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(Q, akl$a.a(n2));
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(Q).a();
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2.a(a, !adq2.d(cj2.a())).a(b, this.e(adq2, cj2.c())).a(N, this.e(adq2, cj2.f())).a(O, this.e(adq2, cj2.d())).a(P, this.e(adq2, cj2.e()));
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N, P, O, Q);
    }

    public static enum a implements nw
    {
        a(0, "cobblestone", "normal"),
        b(1, "mossy_cobblestone", "mossy");

        private static final a[] c;
        private final int d;
        private final String e;
        private String f;

        private a(int n3, String string2, String string3) {
            this.d = n3;
            this.e = string2;
            this.f = string3;
        }

        public int a() {
            return this.d;
        }

        public String toString() {
            return this.e;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= c.length) {
                n2 = 0;
            }
            return c[n2];
        }

        @Override
        public String l() {
            return this.e;
        }

        public String c() {
            return this.f;
        }

        static {
            c = new a[akl$a.values().length];
            a[] aArray = akl$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                akl$a.c[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

