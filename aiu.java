/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aiu
extends afh {
    public static final amm<a> a = amm.a("variant", a.class);
    public static final int b = aiu$a.a.a();
    public static final int N = aiu$a.b.a();
    public static final int O = aiu$a.c.a();

    public aiu() {
        super(arm.e);
        this.j(this.M.b().a(a, aiu$a.a));
        this.a(yz.b);
    }

    @Override
    public String f() {
        return di.a(this.a() + "." + aiu$a.a.c() + ".name");
    }

    @Override
    public arn g(alz alz2) {
        if (alz2.b(a) == aiu$a.a) {
            return arn.y;
        }
        return arn.G;
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, aiu$a.a(n2));
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, b));
        list.add(new zx(zw2, 1, N));
        list.add(new zx(zw2, 1, O));
    }

    public static enum a implements nw
    {
        a(0, "prismarine", "rough"),
        b(1, "prismarine_bricks", "bricks"),
        c(2, "dark_prismarine", "dark");

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
            d = new a[aiu$a.values().length];
            a[] aArray = aiu$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                aiu$a.d[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

