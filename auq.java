/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public abstract class auq {
    public boolean a(auq auq2) {
        if (auq2 == null) {
            return false;
        }
        return this == auq2;
    }

    public abstract String b();

    public abstract String d(String var1);

    public abstract boolean h();

    public abstract boolean g();

    public abstract a i();

    public abstract Collection<String> d();

    public abstract a j();

    public static enum a {
        a("always", 0),
        b("never", 1),
        c("hideForOtherTeams", 2),
        d("hideForOwnTeam", 3);

        private static Map<String, a> g;
        public final String e;
        public final int f;

        public static String[] a() {
            return g.keySet().toArray(new String[g.size()]);
        }

        public static a a(String string) {
            return g.get(string);
        }

        private a(String string2, int n3) {
            this.e = string2;
            this.f = n3;
        }

        static {
            g = Maps.newHashMap();
            for (a a2 : auq$a.values()) {
                g.put(a2.e, a2);
            }
        }
    }
}

