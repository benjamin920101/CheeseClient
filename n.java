/*
 * Decompiled with CFR 0.152.
 */
public class n {
    private static final int a = n$a.values().length;
    private static final String[] b = new String[a];
    private String[] c = b;
    private String[] d = b;

    public void a(final m m2, a a2, int n2) {
        String string = this.c[a2.a()];
        if (string == null) {
            return;
        }
        m \u26032 = new m(){

            @Override
            public String e_() {
                return m2.e_();
            }

            @Override
            public eu f_() {
                return m2.f_();
            }

            @Override
            public void a(eu eu2) {
                m2.a(eu2);
            }

            @Override
            public boolean a(int n2, String string) {
                return true;
            }

            @Override
            public cj c() {
                return m2.c();
            }

            @Override
            public aui d() {
                return m2.d();
            }

            @Override
            public adm e() {
                return m2.e();
            }

            @Override
            public pk f() {
                return m2.f();
            }

            @Override
            public boolean u_() {
                return m2.u_();
            }

            @Override
            public void a(a a2, int n2) {
                m2.a(a2, n2);
            }
        };
        try {
            \u2603 = i.e(\u26032, string);
        }
        catch (ca \u26033) {
            return;
        }
        \u2603 = this.d[a2.a()];
        if (\u2603 == null) {
            return;
        }
        auo \u26034 = m2.e().Z();
        auk \u26035 = \u26034.b(\u2603);
        if (\u26035 == null) {
            return;
        }
        if (!\u26034.b(\u2603, \u26035)) {
            return;
        }
        aum \u26036 = \u26034.c(\u2603, \u26035);
        \u26036.c(n2);
    }

    public void a(dn dn2) {
        if (!dn2.b("CommandStats", 10)) {
            return;
        }
        \u2603 = dn2.m("CommandStats");
        for (a a2 : n$a.values()) {
            String string = a2.b() + "Name";
            \u2603 = a2.b() + "Objective";
            if (!\u2603.b(string, 8) || !\u2603.b(\u2603, 8)) continue;
            \u2603 = \u2603.j(string);
            \u2603 = \u2603.j(\u2603);
            n.a(this, a2, \u2603, \u2603);
        }
    }

    public void b(dn dn2) {
        dn dn3;
        dn3 = new dn();
        for (a a2 : n$a.values()) {
            String string = this.c[a2.a()];
            \u2603 = this.d[a2.a()];
            if (string == null || \u2603 == null) continue;
            dn3.a(a2.b() + "Name", string);
            dn3.a(a2.b() + "Objective", \u2603);
        }
        if (!dn3.c_()) {
            dn2.a("CommandStats", dn3);
        }
    }

    public static void a(n n2, a a2, String string, String string2) {
        if (string == null || string.length() == 0 || string2 == null || string2.length() == 0) {
            n.a(n2, a2);
            return;
        }
        if (n2.c == b || n2.d == b) {
            n2.c = new String[a];
            n2.d = new String[a];
        }
        n2.c[a2.a()] = string;
        n2.d[a2.a()] = string2;
    }

    private static void a(n n2, a a2) {
        if (n2.c == b || n2.d == b) {
            return;
        }
        n2.c[a2.a()] = null;
        n2.d[a2.a()] = null;
        boolean bl2 = true;
        for (a a3 : n$a.values()) {
            if (n2.c[a3.a()] == null || n2.d[a3.a()] == null) continue;
            bl2 = false;
            break;
        }
        if (bl2) {
            n2.c = b;
            n2.d = b;
        }
    }

    public void a(n n2) {
        for (a a2 : n$a.values()) {
            n.a(this, a2, n2.c[a2.a()], n2.d[a2.a()]);
        }
    }

    public static enum a {
        a(0, "SuccessCount"),
        b(1, "AffectedBlocks"),
        c(2, "AffectedEntities"),
        d(3, "AffectedItems"),
        e(4, "QueryResult");

        final int f;
        final String g;

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

        public static String[] c() {
            String[] stringArray = new String[n$a.values().length];
            int \u26032 = 0;
            for (a a2 : n$a.values()) {
                stringArray[\u26032++] = a2.b();
            }
            return stringArray;
        }

        public static a a(String string) {
            for (a a2 : n$a.values()) {
                if (!a2.b().equals(string)) continue;
                return a2;
            }
            return null;
        }
    }
}

