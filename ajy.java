/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class ajy
extends afh {
    public static final amm<a> a = amm.a("variant", a.class);

    public ajy() {
        super(arm.e);
        this.j(this.M.b().a(a, ajy$a.a));
        this.a(yz.b);
    }

    @Override
    public String f() {
        return di.a(this.a() + "." + ajy$a.a.d() + ".name");
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(a).c();
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (alz2.b(a) == ajy$a.a) {
            return zw.a(afi.e);
        }
        return zw.a(afi.b);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : ajy$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, ajy$a.a(n2));
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    public static enum a implements nw
    {
        a(0, arn.m, "stone"),
        b(1, arn.l, "granite"),
        c(2, arn.l, "smooth_granite", "graniteSmooth"),
        d(3, arn.p, "diorite"),
        e(4, arn.p, "smooth_diorite", "dioriteSmooth"),
        f(5, arn.m, "andesite"),
        g(6, arn.m, "smooth_andesite", "andesiteSmooth");

        private static final a[] h;
        private final int i;
        private final String j;
        private final String k;
        private final arn l;

        private a(int n3, arn arn2, String string2) {
            this(n3, arn2, string2, string2);
        }

        private a(int n3, arn arn2, String string2, String string3) {
            this.i = n3;
            this.j = string2;
            this.k = string3;
            this.l = arn2;
        }

        public int a() {
            return this.i;
        }

        public arn c() {
            return this.l;
        }

        public String toString() {
            return this.j;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= h.length) {
                n2 = 0;
            }
            return h[n2];
        }

        @Override
        public String l() {
            return this.j;
        }

        public String d() {
            return this.k;
        }

        static {
            h = new a[ajy$a.values().length];
            a[] aArray = ajy$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                ajy$a.h[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

