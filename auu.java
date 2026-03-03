/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public interface auu {
    public static final Map<String, auu> a = Maps.newHashMap();
    public static final auu b = new aus("dummy");
    public static final auu c = new aus("trigger");
    public static final auu d = new aus("deathCount");
    public static final auu e = new aus("playerKillCount");
    public static final auu f = new aus("totalKillCount");
    public static final auu g = new aut("health");
    public static final auu[] h = new auu[]{new aur("teamkill.", a.a), new aur("teamkill.", a.b), new aur("teamkill.", a.c), new aur("teamkill.", a.d), new aur("teamkill.", a.e), new aur("teamkill.", a.f), new aur("teamkill.", a.g), new aur("teamkill.", a.h), new aur("teamkill.", a.i), new aur("teamkill.", a.j), new aur("teamkill.", a.k), new aur("teamkill.", a.l), new aur("teamkill.", a.m), new aur("teamkill.", a.n), new aur("teamkill.", a.o), new aur("teamkill.", a.p)};
    public static final auu[] i = new auu[]{new aur("killedByTeam.", a.a), new aur("killedByTeam.", a.b), new aur("killedByTeam.", a.c), new aur("killedByTeam.", a.d), new aur("killedByTeam.", a.e), new aur("killedByTeam.", a.f), new aur("killedByTeam.", a.g), new aur("killedByTeam.", a.h), new aur("killedByTeam.", a.i), new aur("killedByTeam.", a.j), new aur("killedByTeam.", a.k), new aur("killedByTeam.", a.l), new aur("killedByTeam.", a.m), new aur("killedByTeam.", a.n), new aur("killedByTeam.", a.o), new aur("killedByTeam.", a.p)};

    public String a();

    public int a(List<wn> var1);

    public boolean b();

    public a c();

    public static enum a {
        a("integer"),
        b("hearts");

        private static final Map<String, a> c;
        private final String d;

        private a(String string2) {
            this.d = string2;
        }

        public String a() {
            return this.d;
        }

        public static a a(String string) {
            a a2 = c.get(string);
            return a2 == null ? a : a2;
        }

        static {
            c = Maps.newHashMap();
            for (a a2 : auu$a.values()) {
                c.put(a2.a(), a2);
            }
        }
    }
}

