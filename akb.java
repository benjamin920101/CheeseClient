/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public abstract class akb
extends ahh {
    public static final amk b = amk.a("seamless");
    public static final amm<a> N = amm.a("variant", a.class);

    public akb() {
        super(arm.e);
        alz alz2 = this.M.b();
        alz2 = this.l() ? alz2.a(b, false) : alz2.a(a, ahh.a.b);
        this.j(alz2.a(N, akb$a.a));
        this.a(yz.b);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.U);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.U);
    }

    @Override
    public String b(int n2) {
        return super.a() + "." + akb$a.a(n2).d();
    }

    @Override
    public amo<?> n() {
        return N;
    }

    @Override
    public Object a(zx zx2) {
        return akb$a.a(zx2.i() & 7);
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        if (zw2 == zw.a(afi.T)) {
            return;
        }
        for (a a2 : akb$a.values()) {
            if (a2 == akb$a.c) continue;
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        alz alz2 = this.Q().a(N, akb$a.a(n2 & 7));
        alz2 = this.l() ? alz2.a(b, (n2 & 8) != 0) : alz2.a(a, (n2 & 8) == 0 ? ahh.a.b : ahh.a.a);
        return alz2;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(N).a();
        if (this.l()) {
            if (alz2.b(b).booleanValue()) {
                n2 |= 8;
            }
        } else if (alz2.b(a) == ahh.a.a) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        if (this.l()) {
            return new ama(this, b, N);
        }
        return new ama(this, a, N);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(N).a();
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(N).c();
    }

    public static enum a implements nw
    {
        a(0, arn.m, "stone"),
        b(1, arn.d, "sandstone", "sand"),
        c(2, arn.o, "wood_old", "wood"),
        d(3, arn.m, "cobblestone", "cobble"),
        e(4, arn.D, "brick"),
        f(5, arn.m, "stone_brick", "smoothStoneBrick"),
        g(6, arn.K, "nether_brick", "netherBrick"),
        h(7, arn.p, "quartz");

        private static final a[] i;
        private final int j;
        private final arn k;
        private final String l;
        private final String m;

        private a(int n3, arn arn2, String string2) {
            this(n3, arn2, string2, string2);
        }

        private a(int n3, arn arn2, String string2, String string3) {
            this.j = n3;
            this.k = arn2;
            this.l = string2;
            this.m = string3;
        }

        public int a() {
            return this.j;
        }

        public arn c() {
            return this.k;
        }

        public String toString() {
            return this.l;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= i.length) {
                n2 = 0;
            }
            return i[n2];
        }

        @Override
        public String l() {
            return this.l;
        }

        public String d() {
            return this.m;
        }

        static {
            i = new a[akb$a.values().length];
            a[] aArray = akb$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                akb$a.i[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

