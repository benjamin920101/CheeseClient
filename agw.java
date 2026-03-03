/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;

public abstract class agw
extends afm {
    protected amm<a> a;

    protected agw() {
        this.j(this.M.b().a(this.n(), this.l() == b.b ? agw$a.b : agw$a.a));
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(this.n()).b();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : agw$a.a(this.l())) {
            list.add(new zx(zw2, 1, a2.b()));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(this.n(), agw$a.a(this.l(), n2));
    }

    public abstract b l();

    public amo<a> n() {
        if (this.a == null) {
            this.a = amm.a("type", a.class, new Predicate<a>(){

                public boolean a(a a2) {
                    return a2.a() == agw.this.l();
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((a)object);
                }
            });
        }
        return this.a;
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(this.n()).b();
    }

    @Override
    protected ama e() {
        return new ama(this, this.n());
    }

    @Override
    public afh.a R() {
        return afh.a.b;
    }

    public static enum a implements nw
    {
        a(agw$b.a, 0, "dandelion"),
        b(agw$b.b, 0, "poppy"),
        c(agw$b.b, 1, "blue_orchid", "blueOrchid"),
        d(agw$b.b, 2, "allium"),
        e(agw$b.b, 3, "houstonia"),
        f(agw$b.b, 4, "red_tulip", "tulipRed"),
        g(agw$b.b, 5, "orange_tulip", "tulipOrange"),
        h(agw$b.b, 6, "white_tulip", "tulipWhite"),
        i(agw$b.b, 7, "pink_tulip", "tulipPink"),
        j(agw$b.b, 8, "oxeye_daisy", "oxeyeDaisy");

        private static final a[][] k;
        private final b l;
        private final int m;
        private final String n;
        private final String o;

        private a(b b2, int n3, String string2) {
            this(b2, n3, string2, string2);
        }

        private a(b b2, int n3, String string2, String string3) {
            this.l = b2;
            this.m = n3;
            this.n = string2;
            this.o = string3;
        }

        public b a() {
            return this.l;
        }

        public int b() {
            return this.m;
        }

        public static a a(b b2, int n22) {
            a[] aArray = k[b2.ordinal()];
            if (n22 < 0 || n22 >= aArray.length) {
                int n22 = 0;
            }
            return aArray[n22];
        }

        public static a[] a(b b2) {
            return k[b2.ordinal()];
        }

        public String toString() {
            return this.n;
        }

        @Override
        public String l() {
            return this.n;
        }

        public String d() {
            return this.o;
        }

        static {
            k = new a[agw$b.values().length][];
            for (final b b2 : agw$b.values()) {
                Collection<a> collection = Collections2.filter(Lists.newArrayList(agw$a.values()), new Predicate<a>(){

                    public boolean a(a a2) {
                        return a2.a() == b2;
                    }

                    @Override
                    public /* synthetic */ boolean apply(Object object) {
                        return this.a((a)object);
                    }
                });
                agw$a.k[b2.ordinal()] = collection.toArray(new a[collection.size()]);
            }
        }
    }

    public static enum b {
        a,
        b;


        public agw a() {
            if (this == a) {
                return afi.N;
            }
            return afi.O;
        }
    }
}

