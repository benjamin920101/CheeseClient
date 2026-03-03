/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class et {
    private final a a;
    private final String b;

    public et(a a2, String string) {
        this.a = a2;
        this.b = string;
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        et et2 = (et)object;
        if (this.a != et2.a) {
            return false;
        }
        return !(this.b != null ? !this.b.equals(et2.b) : et2.b != null);
    }

    public String toString() {
        return "ClickEvent{action=" + (Object)((Object)this.a) + ", value='" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int n2 = this.a.hashCode();
        n2 = 31 * n2 + (this.b != null ? this.b.hashCode() : 0);
        return n2;
    }

    public static enum a {
        a("open_url", true),
        b("open_file", false),
        c("run_command", true),
        d("twitch_user_info", false),
        e("suggest_command", true),
        f("change_page", true);

        private static final Map<String, a> g;
        private final boolean h;
        private final String i;

        private a(String string2, boolean bl2) {
            this.i = string2;
            this.h = bl2;
        }

        public boolean a() {
            return this.h;
        }

        public String b() {
            return this.i;
        }

        public static a a(String string) {
            return g.get(string);
        }

        static {
            g = Maps.newHashMap();
            for (a a2 : et$a.values()) {
                g.put(a2.b(), a2);
            }
        }
    }
}

