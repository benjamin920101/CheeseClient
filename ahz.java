/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class ahz
extends afh {
    public static final amm<a> a = amm.a("variant", a.class);

    public ahz() {
        super(arm.B);
        this.j(this.M.b().a(a, ahz$a.a));
        this.c(0.0f);
        this.a(yz.c);
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    public static boolean d(alz alz2) {
        afh afh2 = alz2.c();
        return alz2 == afi.b.Q().a(ajy.a, ajy.a.a) || afh2 == afi.e || afh2 == afi.bf;
    }

    @Override
    protected zx i(alz alz2) {
        switch (alz2.b(a)) {
            case b: {
                return new zx(afi.e);
            }
            case c: {
                return new zx(afi.bf);
            }
            case d: {
                return new zx(afi.bf, 1, ajz.a.b.a());
            }
            case e: {
                return new zx(afi.bf, 1, ajz.a.c.a());
            }
            case f: {
                return new zx(afi.bf, 1, ajz.a.d.a());
            }
        }
        return new zx(afi.b);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        if (!adm2.D && adm2.Q().b("doTileDrops")) {
            vz vz2 = new vz(adm2);
            vz2.b((double)cj2.n() + 0.5, cj2.o(), (double)cj2.p() + 0.5, 0.0f, 0.0f);
            adm2.d(vz2);
            vz2.y();
        }
    }

    @Override
    public int j(adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        return alz2.c().c(alz2);
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : ahz$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, ahz$a.a(n2));
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
        a(0, "stone"){

            @Override
            public alz d() {
                return afi.b.Q().a(ajy.a, ajy.a.a);
            }
        }
        ,
        b(1, "cobblestone", "cobble"){

            @Override
            public alz d() {
                return afi.e.Q();
            }
        }
        ,
        c(2, "stone_brick", "brick"){

            @Override
            public alz d() {
                return afi.bf.Q().a(ajz.a, ajz.a.a);
            }
        }
        ,
        d(3, "mossy_brick", "mossybrick"){

            @Override
            public alz d() {
                return afi.bf.Q().a(ajz.a, ajz.a.b);
            }
        }
        ,
        e(4, "cracked_brick", "crackedbrick"){

            @Override
            public alz d() {
                return afi.bf.Q().a(ajz.a, ajz.a.c);
            }
        }
        ,
        f(5, "chiseled_brick", "chiseledbrick"){

            @Override
            public alz d() {
                return afi.bf.Q().a(ajz.a, ajz.a.d);
            }
        };

        private static final a[] g;
        private final int h;
        private final String i;
        private final String j;

        private a(int n3, String string2) {
            this(n3, string2, string2);
        }

        private a(int n3, String string2, String string3) {
            this.h = n3;
            this.i = string2;
            this.j = string3;
        }

        public int a() {
            return this.h;
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

        public String c() {
            return this.j;
        }

        public abstract alz d();

        public static a a(alz alz2) {
            for (a a2 : ahz$a.values()) {
                if (alz2 != a2.d()) continue;
                return a2;
            }
            return a;
        }

        static {
            g = new a[ahz$a.values().length];
            a[] aArray = ahz$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                ahz$a.g[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

