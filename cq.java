/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public enum cq implements nw
{
    a(0, 1, -1, "down", cq$b.b, cq$a.b, new df(0, -1, 0)),
    b(1, 0, -1, "up", cq$b.a, cq$a.b, new df(0, 1, 0)),
    c(2, 3, 2, "north", cq$b.b, cq$a.c, new df(0, 0, -1)),
    d(3, 2, 0, "south", cq$b.a, cq$a.c, new df(0, 0, 1)),
    e(4, 5, 1, "west", cq$b.b, cq$a.a, new df(-1, 0, 0)),
    f(5, 4, 3, "east", cq$b.a, cq$a.a, new df(1, 0, 0));

    private final int g;
    private final int h;
    private final int i;
    private final String j;
    private final a k;
    private final b l;
    private final df m;
    private static final cq[] n;
    private static final cq[] o;
    private static final Map<String, cq> p;

    private cq(int n3, int n4, int n5, String string2, b b2, a a2, df df2) {
        this.g = n3;
        this.i = n5;
        this.h = n4;
        this.j = string2;
        this.k = a2;
        this.l = b2;
        this.m = df2;
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.i;
    }

    public b c() {
        return this.l;
    }

    public cq d() {
        return cq.a(this.h);
    }

    public cq a(a a2) {
        switch (a2) {
            case a: {
                if (this == e || this == f) {
                    return this;
                }
                return this.n();
            }
            case b: {
                if (this == b || this == a) {
                    return this;
                }
                return this.e();
            }
            case c: {
                if (this == c || this == d) {
                    return this;
                }
                return this.p();
            }
        }
        throw new IllegalStateException("Unable to get CW facing for axis " + a2);
    }

    public cq e() {
        switch (this) {
            case c: {
                return f;
            }
            case f: {
                return d;
            }
            case d: {
                return e;
            }
            case e: {
                return c;
            }
        }
        throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
    }

    private cq n() {
        switch (this) {
            case b: {
                return c;
            }
            case c: {
                return a;
            }
            case a: {
                return d;
            }
            case d: {
                return b;
            }
        }
        throw new IllegalStateException("Unable to get X-rotated facing of " + this);
    }

    private cq p() {
        switch (this) {
            case b: {
                return f;
            }
            case f: {
                return a;
            }
            case a: {
                return e;
            }
            case e: {
                return b;
            }
        }
        throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
    }

    public cq f() {
        switch (this) {
            case c: {
                return e;
            }
            case f: {
                return c;
            }
            case d: {
                return f;
            }
            case e: {
                return d;
            }
        }
        throw new IllegalStateException("Unable to get CCW facing of " + this);
    }

    public int g() {
        if (this.k == cq$a.a) {
            return this.l.a();
        }
        return 0;
    }

    public int h() {
        if (this.k == cq$a.b) {
            return this.l.a();
        }
        return 0;
    }

    public int i() {
        if (this.k == cq$a.c) {
            return this.l.a();
        }
        return 0;
    }

    public String j() {
        return this.j;
    }

    public a k() {
        return this.k;
    }

    public static cq a(String string) {
        if (string == null) {
            return null;
        }
        return p.get(string.toLowerCase());
    }

    public static cq a(int n2) {
        return n[ns.a(n2 % n.length)];
    }

    public static cq b(int n2) {
        return o[ns.a(n2 % o.length)];
    }

    public static cq a(double d2) {
        return cq.b(ns.c(d2 / 90.0 + 0.5) & 3);
    }

    public static cq a(Random random) {
        return cq.values()[random.nextInt(cq.values().length)];
    }

    public static cq a(float f2, float f3, float f4) {
        cq \u26033 = c;
        float \u26032 = Float.MIN_VALUE;
        for (cq cq2 : cq.values()) {
            float f5 = f2 * (float)cq2.m.n() + f3 * (float)cq2.m.o() + f4 * (float)cq2.m.p();
            if (!(f5 > \u26032)) continue;
            \u26032 = f5;
            \u26033 = cq2;
        }
        return \u26033;
    }

    public String toString() {
        return this.j;
    }

    @Override
    public String l() {
        return this.j;
    }

    public static cq a(b b22, a a2) {
        b b22;
        for (cq cq2 : cq.values()) {
            if (cq2.c() != b22 || cq2.k() != a2) continue;
            return cq2;
        }
        throw new IllegalArgumentException("No such direction: " + (Object)((Object)b22) + " " + a2);
    }

    public df m() {
        return this.m;
    }

    static {
        n = new cq[6];
        o = new cq[4];
        p = Maps.newHashMap();
        cq[] cqArray = cq.values();
        int \u26032 = cqArray.length;
        for (int i2 = 0; i2 < \u26032; ++i2) {
            cq.n[\u2603.g] = \u2603 = cqArray[i2];
            if (\u2603.k().c()) {
                cq.o[\u2603.i] = \u2603;
            }
            p.put(\u2603.j().toLowerCase(), \u2603);
        }
    }

    public static enum c implements Predicate<cq>,
    Iterable<cq>
    {
        a,
        b;


        public cq[] a() {
            switch (this) {
                case a: {
                    return new cq[]{c, f, d, e};
                }
                case b: {
                    return new cq[]{b, a};
                }
            }
            throw new Error("Someone's been tampering with the universe!");
        }

        public cq a(Random random) {
            cq[] cqArray = this.a();
            return cqArray[random.nextInt(cqArray.length)];
        }

        public boolean a(cq cq2) {
            return cq2 != null && cq2.k().d() == this;
        }

        @Override
        public Iterator<cq> iterator() {
            return Iterators.forArray(this.a());
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((cq)object);
        }
    }

    public static enum b {
        a(1, "Towards positive"),
        b(-1, "Towards negative");

        private final int c;
        private final String d;

        private b(int n3, String string2) {
            this.c = n3;
            this.d = string2;
        }

        public int a() {
            return this.c;
        }

        public String toString() {
            return this.d;
        }
    }

    public static enum a implements Predicate<cq>,
    nw
    {
        a("x", cq$c.a),
        b("y", cq$c.b),
        c("z", cq$c.a);

        private static final Map<String, a> d;
        private final String e;
        private final c f;

        private a(String string2, c c2) {
            this.e = string2;
            this.f = c2;
        }

        public static a a(String string) {
            if (string == null) {
                return null;
            }
            return d.get(string.toLowerCase());
        }

        public String a() {
            return this.e;
        }

        public boolean b() {
            return this.f == cq$c.b;
        }

        public boolean c() {
            return this.f == cq$c.a;
        }

        public String toString() {
            return this.e;
        }

        public boolean a(cq cq2) {
            return cq2 != null && cq2.k() == this;
        }

        public c d() {
            return this.f;
        }

        @Override
        public String l() {
            return this.e;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((cq)object);
        }

        static {
            d = Maps.newHashMap();
            for (a a2 : cq$a.values()) {
                d.put(a2.a().toLowerCase(), a2);
            }
        }
    }
}

