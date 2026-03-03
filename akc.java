/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class akc
extends afm
implements afj {
    public static final amm<a> a = amm.a("type", a.class);

    protected akc() {
        super(arm.l);
        this.j(this.M.b().a(a, akc$a.a));
        float f2 = 0.4f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, 0.8f, 0.5f + f2);
    }

    @Override
    public int H() {
        return adl.a(0.5, 1.0);
    }

    @Override
    public boolean f(adm adm2, cj cj2, alz alz2) {
        return this.c(adm2.p(cj2.b()).c());
    }

    @Override
    public boolean a(adm adm2, cj cj2) {
        return true;
    }

    @Override
    public int h(alz alz2) {
        if (alz2.c() != this) {
            return super.h(alz2);
        }
        a a2 = alz2.b(a);
        if (a2 == akc$a.a) {
            return 0xFFFFFF;
        }
        return adl.a(0.5, 1.0);
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        return adq2.b(cj2).b(cj2);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (random.nextInt(8) == 0) {
            return zy.N;
        }
        return null;
    }

    @Override
    public int a(int n2, Random random) {
        return 1 + random.nextInt(n2 * 2 + 1);
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        if (!adm2.D && wn2.bZ() != null && wn2.bZ().b() == zy.be) {
            wn2.b(na.ab[afh.a(this)]);
            akc.a(adm2, cj2, new zx(afi.H, 1, alz2.b(a).a()));
        } else {
            super.a(adm2, wn2, cj2, alz2, akw2);
        }
    }

    @Override
    public int j(adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        return alz2.c().c(alz2);
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (int i2 = 1; i2 < 3; ++i2) {
            list.add(new zx(zw2, 1, i2));
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        return alz2.b(a) != akc$a.a;
    }

    @Override
    public boolean a(adm adm2, Random random, cj cj2, alz alz2) {
        return true;
    }

    @Override
    public void b(adm adm2, Random random, cj cj2, alz alz2) {
        agi.b b2 = agi.b.c;
        if (alz2.b(a) == akc$a.c) {
            b2 = agi.b.d;
        }
        if (afi.cF.d(adm2, cj2)) {
            afi.cF.a(adm2, cj2, b2, 2);
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, akc$a.a(n2));
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
    public afh.a R() {
        return afh.a.c;
    }

    public static enum a implements nw
    {
        a(0, "dead_bush"),
        b(1, "tall_grass"),
        c(2, "fern");

        private static final a[] d;
        private final int e;
        private final String f;

        private a(int n3, String string2) {
            this.e = n3;
            this.f = string2;
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

        static {
            d = new a[akc$a.values().length];
            a[] aArray = akc$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                akc$a.d[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

