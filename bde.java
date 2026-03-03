/*
 * Decompiled with CFR 0.152.
 */
public class bde {
    public String a;
    public String b;
    public String c;
    public String d;
    public long e;
    public int f = 47;
    public String g = "1.8.8";
    public boolean h;
    public String i;
    private a j = bde$a.c;
    private String k;
    private boolean l;

    public bde(String string, String string2, boolean bl2) {
        this.a = string;
        this.b = string2;
        this.l = bl2;
    }

    public dn a() {
        dn dn2 = new dn();
        dn2.a("name", this.a);
        dn2.a("ip", this.b);
        if (this.k != null) {
            dn2.a("icon", this.k);
        }
        if (this.j == bde$a.a) {
            dn2.a("acceptTextures", true);
        } else if (this.j == bde$a.b) {
            dn2.a("acceptTextures", false);
        }
        return dn2;
    }

    public a b() {
        return this.j;
    }

    public void a(a a2) {
        this.j = a2;
    }

    public static bde a(dn dn2) {
        bde bde2 = new bde(dn2.j("name"), dn2.j("ip"), false);
        if (dn2.b("icon", 8)) {
            bde2.a(dn2.j("icon"));
        }
        if (dn2.b("acceptTextures", 1)) {
            if (dn2.n("acceptTextures")) {
                bde2.a(bde$a.a);
            } else {
                bde2.a(bde$a.b);
            }
        } else {
            bde2.a(bde$a.c);
        }
        return bde2;
    }

    public String c() {
        return this.k;
    }

    public void a(String string) {
        this.k = string;
    }

    public boolean d() {
        return this.l;
    }

    public void a(bde bde2) {
        this.b = bde2.b;
        this.a = bde2.a;
        this.a(bde2.b());
        this.k = bde2.k;
        this.l = bde2.l;
    }

    public static enum a {
        a("enabled"),
        b("disabled"),
        c("prompt");

        private final eu d;

        private a(String string2) {
            this.d = new fb("addServer.resourcePack." + string2, new Object[0]);
        }

        public eu a() {
            return this.d;
        }
    }
}

