/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class yj
extends zw {
    private static final int[] k = new int[]{11, 16, 15, 13};
    public static final String[] a = new String[]{"minecraft:items/empty_armor_slot_helmet", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_boots"};
    private static final cr l = new cn(){

        @Override
        protected zx b(ck ck2, zx zx2) {
            cj cj2 = ck2.d().a(agg.b(ck2.f()));
            int \u26032 = cj2.n();
            int \u26033 = cj2.o();
            int \u26034 = cj2.p();
            aug \u26035 = new aug(\u26032, \u26033, \u26034, \u26032 + 1, \u26033 + 1, \u26034 + 1);
            Predicate<pk> \u26036 = ck2.i().a(pr.class, \u26035, Predicates.and(po.d, new po.a(zx2)));
            if (\u26036.size() > 0) {
                pr pr2 = (pr)\u26036.get(0);
                int \u26037 = pr2 instanceof wn ? 1 : 0;
                int \u26038 = ps.c(zx2);
                zx \u26039 = zx2.k();
                \u26039.b = 1;
                pr2.c(\u26038 - \u26037, \u26039);
                if (pr2 instanceof ps) {
                    ((ps)pr2).a(\u26038, 2.0f);
                }
                --zx2.b;
                return zx2;
            }
            return super.b(ck2, zx2);
        }
    };
    public final int b;
    public final int c;
    public final int d;
    private final a m;

    public yj(a a2, int n2, int n3) {
        this.m = a2;
        this.b = n3;
        this.d = n2;
        this.c = a2.b(n3);
        this.d(a2.a(n3));
        this.h = 1;
        this.a(yz.j);
        agg.N.a(this, l);
    }

    @Override
    public int a(zx zx2, int n2) {
        if (n2 > 0) {
            return 0xFFFFFF;
        }
        \u2603 = this.b(zx2);
        if (\u2603 < 0) {
            \u2603 = 0xFFFFFF;
        }
        return \u2603;
    }

    @Override
    public int b() {
        return this.m.a();
    }

    public a x_() {
        return this.m;
    }

    public boolean d_(zx zx2) {
        if (this.m != yj$a.a) {
            return false;
        }
        if (!zx2.n()) {
            return false;
        }
        if (!zx2.o().b("display", 10)) {
            return false;
        }
        return zx2.o().m("display").b("color", 3);
    }

    public int b(zx zx2) {
        if (this.m != yj$a.a) {
            return -1;
        }
        dn dn2 = zx2.o();
        if (dn2 != null && (\u2603 = dn2.m("display")) != null && \u2603.b("color", 3)) {
            return \u2603.f("color");
        }
        return 10511680;
    }

    public void c(zx zx2) {
        if (this.m != yj$a.a) {
            return;
        }
        dn dn2 = zx2.o();
        if (dn2 == null) {
            return;
        }
        \u2603 = dn2.m("display");
        if (\u2603.c("color")) {
            \u2603.o("color");
        }
    }

    public void b(zx zx2, int n2) {
        if (this.m != yj$a.a) {
            throw new UnsupportedOperationException("Can't dye non-leather!");
        }
        dn dn2 = zx2.o();
        if (dn2 == null) {
            dn2 = new dn();
            zx2.d(dn2);
        }
        \u2603 = dn2.m("display");
        if (!dn2.b("display", 10)) {
            dn2.a("display", \u2603);
        }
        \u2603.a("color", n2);
    }

    @Override
    public boolean a(zx zx2, zx zx3) {
        if (this.m.b() == zx3.b()) {
            return true;
        }
        return super.a(zx2, zx3);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        int n2 = ps.c(zx2) - 1;
        zx \u26032 = wn2.q(n2);
        if (\u26032 == null) {
            wn2.c(n2, zx2.k());
            zx2.b = 0;
        }
        return zx2;
    }

    public static enum a {
        a("leather", 5, new int[]{1, 3, 2, 1}, 15),
        b("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
        c("iron", 15, new int[]{2, 6, 5, 2}, 9),
        d("gold", 7, new int[]{2, 5, 3, 1}, 25),
        e("diamond", 33, new int[]{3, 8, 6, 3}, 10);

        private final String f;
        private final int g;
        private final int[] h;
        private final int i;

        private a(String string2, int n3, int[] nArray, int n4) {
            this.f = string2;
            this.g = n3;
            this.h = nArray;
            this.i = n4;
        }

        public int a(int n2) {
            return k[n2] * this.g;
        }

        public int b(int n2) {
            return this.h[n2];
        }

        public int a() {
            return this.i;
        }

        public zw b() {
            if (this == a) {
                return zy.aF;
            }
            if (this == b) {
                return zy.j;
            }
            if (this == d) {
                return zy.k;
            }
            if (this == c) {
                return zy.j;
            }
            if (this == e) {
                return zy.i;
            }
            return null;
        }

        public String c() {
            return this.f;
        }
    }
}

