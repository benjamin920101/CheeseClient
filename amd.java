/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class amd {
    private final Predicate<amc>[][][] a;
    private final int b;
    private final int c;
    private final int d;

    public amd(Predicate<amc>[][][] predicateArray) {
        this.a = predicateArray;
        this.b = predicateArray.length;
        if (this.b > 0) {
            this.c = predicateArray[0].length;
            this.d = this.c > 0 ? predicateArray[0][0].length : 0;
        } else {
            this.c = 0;
            this.d = 0;
        }
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    private b a(cj cj22, cq cq2, cq cq3, LoadingCache<cj, amc> loadingCache) {
        cj cj22;
        for (int i2 = 0; i2 < this.d; ++i2) {
            for (\u2603 = 0; \u2603 < this.c; ++\u2603) {
                for (\u2603 = 0; \u2603 < this.b; ++\u2603) {
                    if (this.a[\u2603][\u2603][i2].apply(loadingCache.getUnchecked(amd.a(cj22, cq2, cq3, i2, \u2603, \u2603)))) continue;
                    return null;
                }
            }
        }
        return new b(cj22, cq2, cq3, loadingCache, this.d, this.c, this.b);
    }

    public b a(adm adm2, cj cj2) {
        LoadingCache<cj, amc> loadingCache = amd.a(adm2, false);
        int \u26032 = Math.max(Math.max(this.d, this.c), this.b);
        for (cj cj3 : cj.a(cj2, cj2.a(\u26032 - 1, \u26032 - 1, \u26032 - 1))) {
            for (cq cq2 : cq.values()) {
                for (cq cq3 : cq.values()) {
                    if (cq3 == cq2 || cq3 == cq2.d() || (\u2603 = this.a(cj3, cq2, cq3, loadingCache)) == null) continue;
                    return \u2603;
                }
            }
        }
        return null;
    }

    public static LoadingCache<cj, amc> a(adm adm2, boolean bl2) {
        return CacheBuilder.newBuilder().build(new a(adm2, bl2));
    }

    protected static cj a(cj cj2, cq cq2, cq cq3, int n2, int n3, int n4) {
        if (cq2 == cq3 || cq2 == cq3.d()) {
            throw new IllegalArgumentException("Invalid forwards & up combination");
        }
        df df2 = new df(cq2.g(), cq2.h(), cq2.i());
        \u2603 = new df(cq3.g(), cq3.h(), cq3.i());
        \u2603 = df2.d(\u2603);
        return cj2.a(\u2603.n() * -n3 + \u2603.n() * n2 + df2.n() * n4, \u2603.o() * -n3 + \u2603.o() * n2 + df2.o() * n4, \u2603.p() * -n3 + \u2603.p() * n2 + df2.p() * n4);
    }

    public static class b {
        private final cj a;
        private final cq b;
        private final cq c;
        private final LoadingCache<cj, amc> d;
        private final int e;
        private final int f;
        private final int g;

        public b(cj cj2, cq cq2, cq cq3, LoadingCache<cj, amc> loadingCache, int n2, int n3, int n4) {
            this.a = cj2;
            this.b = cq2;
            this.c = cq3;
            this.d = loadingCache;
            this.e = n2;
            this.f = n3;
            this.g = n4;
        }

        public cj a() {
            return this.a;
        }

        public cq b() {
            return this.b;
        }

        public cq c() {
            return this.c;
        }

        public int d() {
            return this.e;
        }

        public int e() {
            return this.f;
        }

        public amc a(int n2, int n3, int n4) {
            return this.d.getUnchecked(amd.a(this.a, this.b(), this.c(), n2, n3, n4));
        }

        public String toString() {
            return Objects.toStringHelper(this).add("up", this.c).add("forwards", this.b).add("frontTopLeft", this.a).toString();
        }
    }

    static class a
    extends CacheLoader<cj, amc> {
        private final adm a;
        private final boolean b;

        public a(adm adm2, boolean bl2) {
            this.a = adm2;
            this.b = bl2;
        }

        public amc a(cj cj2) throws Exception {
            return new amc(this.a, cj2, this.b);
        }

        @Override
        public /* synthetic */ Object load(Object object) throws Exception {
            return this.a((cj)object);
        }
    }
}

