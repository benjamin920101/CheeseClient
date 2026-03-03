/*
 * Decompiled with CFR 0.152.
 */
public final class adp {
    private final long a;
    private final a b;
    private final boolean c;
    private final boolean d;
    private final adr e;
    private boolean f;
    private boolean g;
    private String h = "";

    public adp(long l2, a a2, boolean bl2, boolean bl3, adr adr2) {
        this.a = l2;
        this.b = a2;
        this.c = bl2;
        this.d = bl3;
        this.e = adr2;
    }

    public adp(ato ato2) {
        this(ato2.b(), ato2.r(), ato2.s(), ato2.t(), ato2.u());
    }

    public adp a() {
        this.g = true;
        return this;
    }

    public adp b() {
        this.f = true;
        return this;
    }

    public adp a(String string) {
        this.h = string;
        return this;
    }

    public boolean c() {
        return this.g;
    }

    public long d() {
        return this.a;
    }

    public a e() {
        return this.b;
    }

    public boolean f() {
        return this.d;
    }

    public boolean g() {
        return this.c;
    }

    public adr h() {
        return this.e;
    }

    public boolean i() {
        return this.f;
    }

    public static a a(int n2) {
        return adp$a.a(n2);
    }

    public String j() {
        return this.h;
    }

    public static enum a {
        a(-1, ""),
        b(0, "survival"),
        c(1, "creative"),
        d(2, "adventure"),
        e(3, "spectator");

        int f;
        String g;

        private a(int n3, String string2) {
            this.f = n3;
            this.g = string2;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public void a(wl wl2) {
            if (this == c) {
                wl2.c = true;
                wl2.d = true;
                wl2.a = true;
            } else if (this == e) {
                wl2.c = true;
                wl2.d = false;
                wl2.a = true;
                wl2.b = true;
            } else {
                wl2.c = false;
                wl2.d = false;
                wl2.a = false;
                wl2.b = false;
            }
            wl2.e = !this.c();
        }

        public boolean c() {
            return this == d || this == e;
        }

        public boolean d() {
            return this == c;
        }

        public boolean e() {
            return this == b || this == d;
        }

        public static a a(int n2) {
            for (a a2 : adp$a.values()) {
                if (a2.f != n2) continue;
                return a2;
            }
            return b;
        }

        public static a a(String string) {
            for (a a2 : adp$a.values()) {
                if (!a2.g.equals(string)) continue;
                return a2;
            }
            return b;
        }
    }
}

