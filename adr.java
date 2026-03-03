/*
 * Decompiled with CFR 0.152.
 */
public class adr {
    public static final adr[] a = new adr[16];
    public static final adr b = new adr(0, "default", 1).i();
    public static final adr c = new adr(1, "flat");
    public static final adr d = new adr(2, "largeBiomes");
    public static final adr e = new adr(3, "amplified").j();
    public static final adr f = new adr(4, "customized");
    public static final adr g = new adr(5, "debug_all_block_states");
    public static final adr h = new adr(8, "default_1_1", 0).a(false);
    private final int i;
    private final String j;
    private final int k;
    private boolean l;
    private boolean m;
    private boolean n;

    private adr(int n2, String string) {
        this(n2, string, 0);
    }

    private adr(int n2, String string, int n3) {
        this.j = string;
        this.k = n3;
        this.l = true;
        this.i = n2;
        adr.a[n2] = this;
    }

    public String a() {
        return this.j;
    }

    public String b() {
        return "generator." + this.j;
    }

    public String c() {
        return this.b() + ".info";
    }

    public int d() {
        return this.k;
    }

    public adr a(int n2) {
        if (this == b && n2 == 0) {
            return h;
        }
        return this;
    }

    private adr a(boolean bl2) {
        this.l = bl2;
        return this;
    }

    public boolean e() {
        return this.l;
    }

    private adr i() {
        this.m = true;
        return this;
    }

    public boolean f() {
        return this.m;
    }

    public static adr a(String string) {
        for (int i2 = 0; i2 < a.length; ++i2) {
            if (a[i2] == null || !adr.a[i2].j.equalsIgnoreCase(string)) continue;
            return a[i2];
        }
        return null;
    }

    public int g() {
        return this.i;
    }

    public boolean h() {
        return this.n;
    }

    private adr j() {
        this.n = true;
        return this;
    }
}

