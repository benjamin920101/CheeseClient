/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aiz
extends afh {
    public static final amm<a> a = amm.a("type", a.class);

    public aiz() {
        super(arm.e, ajh.a.b.c());
        this.j(this.M.b().a(a, aiz$a.a));
        this.a(yz.b);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : aiz$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, aiz$a.a(n2));
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
        a(0, "red_sandstone", "default"),
        b(1, "chiseled_red_sandstone", "chiseled"),
        c(2, "smooth_red_sandstone", "smooth");

        private static final a[] d;
        private final int e;
        private final String f;
        private final String g;

        private a(int n3, String string2, String string3) {
            this.e = n3;
            this.f = string2;
            this.g = string3;
        }

        public int a() {
            return this.e;
        }

        public String toString() {
            return this.f;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= d.length) {
                n2 = 0;
            }
            return d[n2];
        }

        @Override
        public String l() {
            return this.f;
        }

        public String c() {
            return this.g;
        }

        static {
            d = new a[aiz$a.values().length];
            a[] aArray = aiz$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                aiz$a.d[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

