/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public abstract class yz {
    public static final yz[] a = new yz[12];
    public static final yz b = new yz(0, "buildingBlocks"){

        @Override
        public zw e() {
            return zw.a(afi.V);
        }
    };
    public static final yz c = new yz(1, "decorations"){

        @Override
        public zw e() {
            return zw.a(afi.cF);
        }

        @Override
        public int f() {
            return agi.b.f.a();
        }
    };
    public static final yz d = new yz(2, "redstone"){

        @Override
        public zw e() {
            return zy.aC;
        }
    };
    public static final yz e = new yz(3, "transportation"){

        @Override
        public zw e() {
            return zw.a(afi.D);
        }
    };
    public static final yz f = new yz(4, "misc"){

        @Override
        public zw e() {
            return zy.ay;
        }
    }.a(new acj[]{acj.a});
    public static final yz g = new yz(5, "search"){

        @Override
        public zw e() {
            return zy.aQ;
        }
    }.a("item_search.png");
    public static final yz h = new yz(6, "food"){

        @Override
        public zw e() {
            return zy.e;
        }
    };
    public static final yz i = new yz(7, "tools"){

        @Override
        public zw e() {
            return zy.c;
        }
    }.a(acj.h, acj.i, acj.j);
    public static final yz j = new yz(8, "combat"){

        @Override
        public zw e() {
            return zy.B;
        }
    }.a(acj.b, acj.c, acj.f, acj.d, acj.e, acj.k, acj.g);
    public static final yz k = new yz(9, "brewing"){

        @Override
        public zw e() {
            return zy.bz;
        }
    };
    public static final yz l = new yz(10, "materials"){

        @Override
        public zw e() {
            return zy.y;
        }
    };
    public static final yz m = new yz(11, "inventory"){

        @Override
        public zw e() {
            return zw.a(afi.ae);
        }
    }.a("inventory.png").k().i();
    private final int n;
    private final String o;
    private String p = "items.png";
    private boolean q = true;
    private boolean r = true;
    private acj[] s;
    private zx t;

    public yz(int n2, String string) {
        this.n = n2;
        this.o = string;
        yz.a[n2] = this;
    }

    public int a() {
        return this.n;
    }

    public String b() {
        return this.o;
    }

    public String c() {
        return "itemGroup." + this.b();
    }

    public zx d() {
        if (this.t == null) {
            this.t = new zx(this.e(), 1, this.f());
        }
        return this.t;
    }

    public abstract zw e();

    public int f() {
        return 0;
    }

    public String g() {
        return this.p;
    }

    public yz a(String string) {
        this.p = string;
        return this;
    }

    public boolean h() {
        return this.r;
    }

    public yz i() {
        this.r = false;
        return this;
    }

    public boolean j() {
        return this.q;
    }

    public yz k() {
        this.q = false;
        return this;
    }

    public int l() {
        return this.n % 6;
    }

    public boolean m() {
        return this.n < 6;
    }

    public acj[] n() {
        return this.s;
    }

    public yz a(acj ... acjArray) {
        this.s = acjArray;
        return this;
    }

    public boolean a(acj acj2) {
        if (this.s == null) {
            return false;
        }
        for (acj acj3 : this.s) {
            if (acj3 != acj2) continue;
            return true;
        }
        return false;
    }

    public void a(List<zx> list2) {
        for (zw zw2 : zw.e) {
            if (zw2 == null || zw2.c() != this) continue;
            zw2.a(zw2, this, list2);
        }
        if (this.n() != null) {
            List<zx> list2;
            this.a(list2, this.n());
        }
    }

    public void a(List<zx> list, acj ... acjArray) {
        for (aci aci2 : aci.b) {
            if (aci2 == null || aci2.C == null) continue;
            boolean bl2 = false;
            for (int i2 = 0; i2 < acjArray.length && !bl2; ++i2) {
                if (aci2.C != acjArray[i2]) continue;
                bl2 = true;
            }
            if (!bl2) continue;
            list.add(zy.cd.a(new acl(aci2, aci2.b())));
        }
    }
}

