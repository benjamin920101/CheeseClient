/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Map;
import java.util.UUID;

public class avm {
    private final String a;
    private final String b;
    private final String c;
    private final a d;

    public avm(String string, String string2, String string3, String string4) {
        this.a = string;
        this.b = string2;
        this.c = string3;
        this.d = avm$a.a(string4);
    }

    public String a() {
        return "token:" + this.c + ":" + this.b;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.c;
    }

    public GameProfile e() {
        try {
            UUID uUID = UUIDTypeAdapter.fromString(this.b());
            return new GameProfile(uUID, this.c());
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return new GameProfile(null, this.c());
        }
    }

    public a f() {
        return this.d;
    }

    public static enum a {
        a("legacy"),
        b("mojang");

        private static final Map<String, a> c;
        private final String d;

        private a(String string2) {
            this.d = string2;
        }

        public static a a(String string) {
            return c.get(string.toLowerCase());
        }

        static {
            c = Maps.newHashMap();
            for (a a2 : avm$a.values()) {
                c.put(a2.d, a2);
            }
        }
    }
}

