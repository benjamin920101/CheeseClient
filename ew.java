/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class ew {
    private final a a;
    private final eu b;

    public ew(a a2, eu eu2) {
        this.a = a2;
        this.b = eu2;
    }

    public a a() {
        return this.a;
    }

    public eu b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ew ew2 = (ew)object;
        if (this.a != ew2.a) {
            return false;
        }
        return !(this.b != null ? !this.b.equals(ew2.b) : ew2.b != null);
    }

    public String toString() {
        return "HoverEvent{action=" + (Object)((Object)this.a) + ", value='" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int n2 = this.a.hashCode();
        n2 = 31 * n2 + (this.b != null ? this.b.hashCode() : 0);
        return n2;
    }

    public static enum a {
        a("show_text", true),
        b("show_achievement", true),
        c("show_item", true),
        d("show_entity", true);

        private static final Map<String, a> e;
        private final boolean f;
        private final String g;

        private a(String string2, boolean bl2) {
            this.g = string2;
            this.f = bl2;
        }

        public boolean a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public static a a(String string) {
            return e.get(string);
        }

        static {
            e = Maps.newHashMap();
            for (a a2 : ew$a.values()) {
                e.put(a2.b(), a2);
            }
        }
    }
}

