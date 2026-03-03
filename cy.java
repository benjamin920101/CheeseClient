/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;

public enum cy {
    a("explode", 0, true),
    b("largeexplode", 1, true),
    c("hugeexplosion", 2, true),
    d("fireworksSpark", 3, false),
    e("bubble", 4, false),
    f("splash", 5, false),
    g("wake", 6, false),
    h("suspended", 7, false),
    i("depthsuspend", 8, false),
    j("crit", 9, false),
    k("magicCrit", 10, false),
    l("smoke", 11, false),
    m("largesmoke", 12, false),
    n("spell", 13, false),
    o("instantSpell", 14, false),
    p("mobSpell", 15, false),
    q("mobSpellAmbient", 16, false),
    r("witchMagic", 17, false),
    s("dripWater", 18, false),
    t("dripLava", 19, false),
    u("angryVillager", 20, false),
    v("happyVillager", 21, false),
    w("townaura", 22, false),
    x("note", 23, false),
    y("portal", 24, false),
    z("enchantmenttable", 25, false),
    A("flame", 26, false),
    B("lava", 27, false),
    C("footstep", 28, false),
    D("cloud", 29, false),
    E("reddust", 30, false),
    F("snowballpoof", 31, false),
    G("snowshovel", 32, false),
    H("slime", 33, false),
    I("heart", 34, false),
    J("barrier", 35, false),
    K("iconcrack_", 36, false, 2),
    L("blockcrack_", 37, false, 1),
    M("blockdust_", 38, false, 1),
    N("droplet", 39, false),
    O("take", 40, false),
    P("mobappearance", 41, true);

    private final String Q;
    private final int R;
    private final boolean S;
    private final int T;
    private static final Map<Integer, cy> U;
    private static final String[] V;

    private cy(String string2, int n3, boolean bl2, int n4) {
        this.Q = string2;
        this.R = n3;
        this.S = bl2;
        this.T = n4;
    }

    private cy(String string2, int n3, boolean bl2) {
        this(string2, n3, bl2, 0);
    }

    public static String[] a() {
        return V;
    }

    public String b() {
        return this.Q;
    }

    public int c() {
        return this.R;
    }

    public int d() {
        return this.T;
    }

    public boolean e() {
        return this.S;
    }

    public boolean f() {
        return this.T > 0;
    }

    public static cy a(int n2) {
        return U.get(n2);
    }

    static {
        U = Maps.newHashMap();
        ArrayList<String> arrayList = Lists.newArrayList();
        for (cy cy2 : cy.values()) {
            U.put(cy2.c(), cy2);
            if (cy2.b().endsWith("_")) continue;
            arrayList.add(cy2.b());
        }
        V = arrayList.toArray(new String[arrayList.size()]);
    }
}

