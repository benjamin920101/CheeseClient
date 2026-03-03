/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aho
extends afh {
    public static final amm<a> a = amm.a("variant", a.class);
    private final afh b;

    public aho(arm arm2, arn arn2, afh afh2) {
        super(arm2, arn2);
        this.j(this.M.b().a(a, aho$a.l));
        this.b = afh2;
    }

    @Override
    public int a(Random random) {
        return Math.max(0, random.nextInt(10) - 7);
    }

    @Override
    public arn g(alz alz2) {
        switch (alz2.b(a)) {
            case m: {
                return arn.e;
            }
            case k: {
                return arn.d;
            }
            case j: {
                return arn.d;
            }
        }
        return super.g(alz2);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(this.b);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(this.b);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q();
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, aho$a.a(n2));
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
        a(1, "north_west"),
        b(2, "north"),
        c(3, "north_east"),
        d(4, "west"),
        e(5, "center"),
        f(6, "east"),
        g(7, "south_west"),
        h(8, "south"),
        i(9, "south_east"),
        j(10, "stem"),
        k(0, "all_inside"),
        l(14, "all_outside"),
        m(15, "all_stem");

        private static final a[] n;
        private final int o;
        private final String p;

        private a(int n3, String string2) {
            this.o = n3;
            this.p = string2;
        }

        public int a() {
            return this.o;
        }

        public String toString() {
            return this.p;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= n.length) {
                n2 = 0;
            }
            return (\u2603 = n[n2]) == null ? n[0] : \u2603;
        }

        @Override
        public String l() {
            return this.p;
        }

        static {
            n = new a[16];
            a[] aArray = aho$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                aho$a.n[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

