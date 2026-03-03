/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aio
extends afh {
    public static final amm<a> a = amm.a("variant", a.class);

    public aio() {
        super(arm.d);
        this.j(this.M.b().a(a, aio$a.a));
        this.a(yz.b);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : aio$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, aio$a.a(n2));
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(a).c();
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
        a(0, "oak", arn.o),
        b(1, "spruce", arn.J),
        c(2, "birch", arn.d),
        d(3, "jungle", arn.l),
        e(4, "acacia", arn.q),
        f(5, "dark_oak", "big_oak", arn.B);

        private static final a[] g;
        private final int h;
        private final String i;
        private final String j;
        private final arn k;

        private a(int n3, String string2, arn arn2) {
            this(n3, string2, string2, arn2);
        }

        private a(int n3, String string2, String string3, arn arn2) {
            this.h = n3;
            this.i = string2;
            this.j = string3;
            this.k = arn2;
        }

        public int a() {
            return this.h;
        }

        public arn c() {
            return this.k;
        }

        public String toString() {
            return this.i;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= g.length) {
                n2 = 0;
            }
            return g[n2];
        }

        @Override
        public String l() {
            return this.i;
        }

        public String d() {
            return this.j;
        }

        static {
            g = new a[aio$a.values().length];
            a[] aArray = aio$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                aio$a.g[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

