/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public enum a {
    a("BLACK", '0', 0),
    b("DARK_BLUE", '1', 1),
    c("DARK_GREEN", '2', 2),
    d("DARK_AQUA", '3', 3),
    e("DARK_RED", '4', 4),
    f("DARK_PURPLE", '5', 5),
    g("GOLD", '6', 6),
    h("GRAY", '7', 7),
    i("DARK_GRAY", '8', 8),
    j("BLUE", '9', 9),
    k("GREEN", 'a', 10),
    l("AQUA", 'b', 11),
    m("RED", 'c', 12),
    n("LIGHT_PURPLE", 'd', 13),
    o("YELLOW", 'e', 14),
    p("WHITE", 'f', 15),
    q("OBFUSCATED", 'k', true),
    r("BOLD", 'l', true),
    s("STRIKETHROUGH", 'm', true),
    t("UNDERLINE", 'n', true),
    u("ITALIC", 'o', true),
    v("RESET", 'r', -1);

    private static final Map<String, a> w;
    private static final Pattern x;
    private final String y;
    private final char z;
    private final boolean A;
    private final String B;
    private final int C;

    private static String c(String string) {
        return string.toLowerCase().replaceAll("[^a-z]", "");
    }

    private a(String string2, char c2, int n3) {
        this(string2, c2, false, n3);
    }

    private a(String string2, char c2, boolean bl2) {
        this(string2, c2, bl2, -1);
    }

    private a(String string2, char c2, boolean bl2, int n3) {
        this.y = string2;
        this.z = c2;
        this.A = bl2;
        this.C = n3;
        this.B = "\u00a7" + c2;
    }

    public int b() {
        return this.C;
    }

    public boolean c() {
        return this.A;
    }

    public boolean d() {
        return !this.A && this != v;
    }

    public String e() {
        return this.name().toLowerCase();
    }

    public String toString() {
        return this.B;
    }

    public static String a(String string) {
        return string == null ? null : x.matcher(string).replaceAll("");
    }

    public static a b(String string) {
        if (string == null) {
            return null;
        }
        return w.get(a.c(string));
    }

    public static a a(int n2) {
        if (n2 < 0) {
            return v;
        }
        for (a a2 : a.values()) {
            if (a2.b() != n2) continue;
            return a2;
        }
        return null;
    }

    public static Collection<String> a(boolean bl2, boolean bl3) {
        ArrayList<String> arrayList = Lists.newArrayList();
        for (a a2 : a.values()) {
            if (a2.d() && !bl2 || a2.c() && !bl3) continue;
            arrayList.add(a2.e());
        }
        return arrayList;
    }

    static {
        w = Maps.newHashMap();
        x = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
        for (a a2 : a.values()) {
            w.put(a.c(a2.y), a2);
        }
    }
}

