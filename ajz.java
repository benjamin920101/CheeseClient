/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ajz
extends afh {
    public static final amm<a> a = amm.a("variant", a.class);
    public static final int b = ajz$a.a.a();
    public static final int N = ajz$a.b.a();
    public static final int O = ajz$a.c.a();
    public static final int P = ajz$a.d.a();

    public ajz() {
        super(arm.e);
        this.j(this.M.b().a(a, ajz$a.a));
        this.a(yz.b);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : ajz$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, ajz$a.a(n2));
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
        a(0, "stonebrick", "default"),
        b(1, "mossy_stonebrick", "mossy"),
        c(2, "cracked_stonebrick", "cracked"),
        d(3, "chiseled_stonebrick", "chiseled");

        private static final a[] e;
        private final int f;
        private final String g;
        private final String h;

        private a(int n3, String string2, String string3) {
            this.f = n3;
            this.g = string2;
            this.h = string3;
        }

        public int a() {
            return this.f;
        }

        public String toString() {
            return this.g;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= e.length) {
                n2 = 0;
            }
            return e[n2];
        }

        @Override
        public String l() {
            return this.g;
        }

        public String c() {
            return this.h;
        }

        static {
            e = new a[ajz$a.values().length];
            a[] aArray = ajz$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                ajz$a.e[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

