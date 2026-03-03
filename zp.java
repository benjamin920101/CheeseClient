/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class zp
extends zs {
    private final boolean b;

    public zp(boolean bl2) {
        super(0, 0.0f, false);
        this.b = bl2;
    }

    @Override
    public int h(zx zx2) {
        a a2 = zp$a.a(zx2);
        if (this.b && a2.g()) {
            return a2.e();
        }
        return a2.c();
    }

    @Override
    public float i(zx zx2) {
        a a2 = zp$a.a(zx2);
        if (this.b && a2.g()) {
            return a2.f();
        }
        return a2.d();
    }

    @Override
    public String j(zx zx2) {
        if (zp$a.a(zx2) == zp$a.d) {
            return abe.m;
        }
        return null;
    }

    @Override
    protected void c(zx zx2, adm adm2, wn wn2) {
        a a2 = zp$a.a(zx2);
        if (a2 == zp$a.d) {
            wn2.c(new pf(pe.u.H, 1200, 3));
            wn2.c(new pf(pe.s.H, 300, 2));
            wn2.c(new pf(pe.k.H, 300, 1));
        }
        super.c(zx2, adm2, wn2);
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (a a2 : zp$a.values()) {
            if (this.b && !a2.g()) continue;
            list.add(new zx(this, 1, a2.a()));
        }
    }

    @Override
    public String e_(zx zx2) {
        a a2 = zp$a.a(zx2);
        return this.a() + "." + a2.b() + "." + (this.b && a2.g() ? "cooked" : "raw");
    }

    public static enum a {
        a(0, "cod", 2, 0.1f, 5, 0.6f),
        b(1, "salmon", 2, 0.1f, 6, 0.8f),
        c(2, "clownfish", 1, 0.1f),
        d(3, "pufferfish", 1, 0.1f);

        private static final Map<Integer, a> e;
        private final int f;
        private final String g;
        private final int h;
        private final float i;
        private final int j;
        private final float k;
        private boolean l = false;

        private a(int n3, String string2, int n4, float f2, int n5, float f3) {
            this.f = n3;
            this.g = string2;
            this.h = n4;
            this.i = f2;
            this.j = n5;
            this.k = f3;
            this.l = true;
        }

        private a(int n3, String string2, int n4, float f2) {
            this.f = n3;
            this.g = string2;
            this.h = n4;
            this.i = f2;
            this.j = 0;
            this.k = 0.0f;
            this.l = false;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public float d() {
            return this.i;
        }

        public int e() {
            return this.j;
        }

        public float f() {
            return this.k;
        }

        public boolean g() {
            return this.l;
        }

        public static a a(int n2) {
            a a2 = e.get(n2);
            if (a2 == null) {
                return a;
            }
            return a2;
        }

        public static a a(zx zx2) {
            if (zx2.b() instanceof zp) {
                return zp$a.a(zx2.i());
            }
            return a;
        }

        static {
            e = Maps.newHashMap();
            for (a a2 : zp$a.values()) {
                e.put(a2.a(), a2);
            }
        }
    }
}

