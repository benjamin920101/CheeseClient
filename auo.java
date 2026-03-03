/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class auo {
    private final Map<String, auk> a = Maps.newHashMap();
    private final Map<auu, List<auk>> b = Maps.newHashMap();
    private final Map<String, Map<auk, aum>> c = Maps.newHashMap();
    private final auk[] d = new auk[19];
    private final Map<String, aul> e = Maps.newHashMap();
    private final Map<String, aul> f = Maps.newHashMap();
    private static String[] g = null;

    public auk b(String string) {
        return this.a.get(string);
    }

    public auk a(String string, auu auu2) {
        if (string.length() > 16) {
            throw new IllegalArgumentException("The objective name '" + string + "' is too long!");
        }
        auk auk2 = this.b(string);
        if (auk2 != null) {
            throw new IllegalArgumentException("An objective with the name '" + string + "' already exists!");
        }
        auk2 = new auk(this, string, auu2);
        List<auk> \u26032 = this.b.get(auu2);
        if (\u26032 == null) {
            \u26032 = Lists.newArrayList();
            this.b.put(auu2, \u26032);
        }
        \u26032.add(auk2);
        this.a.put(string, auk2);
        this.a(auk2);
        return auk2;
    }

    public Collection<auk> a(auu auu2) {
        Collection collection = this.b.get(auu2);
        return collection == null ? Lists.newArrayList() : Lists.newArrayList(collection);
    }

    public boolean b(String string, auk auk2) {
        Map<auk, aum> map = this.c.get(string);
        if (map == null) {
            return false;
        }
        aum \u26032 = map.get(auk2);
        return \u26032 != null;
    }

    public aum c(String string, auk auk2) {
        aum aum2;
        if (string.length() > 40) {
            throw new IllegalArgumentException("The player name '" + string + "' is too long!");
        }
        Map<auk, aum> map = this.c.get(string);
        if (map == null) {
            map = Maps.newHashMap();
            this.c.put(string, map);
        }
        if ((aum2 = map.get(auk2)) == null) {
            aum2 = new aum(this, auk2, string);
            map.put(auk2, aum2);
        }
        return aum2;
    }

    public Collection<aum> i(auk auk2) {
        ArrayList<aum> arrayList = Lists.newArrayList();
        for (Map<auk, aum> map : this.c.values()) {
            aum aum2 = map.get(auk2);
            if (aum2 == null) continue;
            arrayList.add(aum2);
        }
        Collections.sort(arrayList, aum.a);
        return arrayList;
    }

    public Collection<auk> c() {
        return this.a.values();
    }

    public Collection<String> d() {
        return this.c.keySet();
    }

    public void d(String string2, auk auk2) {
        if (auk2 == null) {
            Map<auk, aum> map = this.c.remove(string2);
            if (map != null) {
                this.a(string2);
            }
        } else {
            String string2;
            Map<auk, aum> \u26032 = this.c.get(string2);
            if (\u26032 != null) {
                aum aum2 = \u26032.remove(auk2);
                if (\u26032.size() < 1) {
                    Map<auk, aum> map = this.c.remove(string2);
                    if (map != null) {
                        this.a(string2);
                    }
                } else if (aum2 != null) {
                    this.a(string2, auk2);
                }
            }
        }
    }

    public Collection<aum> e() {
        Collection<Map<auk, aum>> collection = this.c.values();
        ArrayList<aum> \u26032 = Lists.newArrayList();
        for (Map<auk, aum> map : collection) {
            \u26032.addAll(map.values());
        }
        return \u26032;
    }

    public Map<auk, aum> c(String string) {
        Map<auk, aum> map = this.c.get(string);
        if (map == null) {
            map = Maps.newHashMap();
        }
        return map;
    }

    public void k(auk auk22) {
        auk auk22;
        this.a.remove(auk22.b());
        for (int i2 = 0; i2 < 19; ++i2) {
            if (this.a(i2) != auk22) continue;
            this.a(i2, null);
        }
        List<auk> \u26032 = this.b.get(auk22.c());
        if (\u26032 != null) {
            \u26032.remove(auk22);
        }
        for (Map<auk, aum> map : this.c.values()) {
            map.remove(auk22);
        }
        this.c(auk22);
    }

    public void a(int n2, auk auk2) {
        this.d[n2] = auk2;
    }

    public auk a(int n2) {
        return this.d[n2];
    }

    public aul d(String string) {
        return this.e.get(string);
    }

    public aul e(String string) {
        if (string.length() > 16) {
            throw new IllegalArgumentException("The team name '" + string + "' is too long!");
        }
        aul aul2 = this.d(string);
        if (aul2 != null) {
            throw new IllegalArgumentException("A team with the name '" + string + "' already exists!");
        }
        aul2 = new aul(this, string);
        this.e.put(string, aul2);
        this.a(aul2);
        return aul2;
    }

    public void d(aul aul22) {
        aul aul22;
        this.e.remove(aul22.b());
        for (String string : aul22.d()) {
            this.f.remove(string);
        }
        this.c(aul22);
    }

    public boolean a(String string, String string2) {
        if (string.length() > 40) {
            throw new IllegalArgumentException("The player name '" + string + "' is too long!");
        }
        if (!this.e.containsKey(string2)) {
            return false;
        }
        aul aul2 = this.d(string2);
        if (this.h(string) != null) {
            this.f(string);
        }
        this.f.put(string, aul2);
        aul2.d().add(string);
        return true;
    }

    public boolean f(String string) {
        aul aul2 = this.h(string);
        if (aul2 != null) {
            this.a(string, aul2);
            return true;
        }
        return false;
    }

    public void a(String string, aul aul2) {
        if (this.h(string) != aul2) {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + aul2.b() + "'.");
        }
        this.f.remove(string);
        aul2.d().remove(string);
    }

    public Collection<String> f() {
        return this.e.keySet();
    }

    public Collection<aul> g() {
        return this.e.values();
    }

    public aul h(String string) {
        return this.f.get(string);
    }

    public void a(auk auk2) {
    }

    public void b(auk auk2) {
    }

    public void c(auk auk2) {
    }

    public void a(aum aum2) {
    }

    public void a(String string) {
    }

    public void a(String string, auk auk2) {
    }

    public void a(aul aul2) {
    }

    public void b(aul aul2) {
    }

    public void c(aul aul2) {
    }

    public static String b(int n2) {
        switch (n2) {
            case 0: {
                return "list";
            }
            case 1: {
                return "sidebar";
            }
            case 2: {
                return "belowName";
            }
        }
        if (n2 >= 3 && n2 <= 18 && (\u2603 = a.a(n2 - 3)) != null && \u2603 != a.v) {
            return "sidebar.team." + \u2603.e();
        }
        return null;
    }

    public static int i(String string) {
        if (string.equalsIgnoreCase("list")) {
            return 0;
        }
        if (string.equalsIgnoreCase("sidebar")) {
            return 1;
        }
        if (string.equalsIgnoreCase("belowName")) {
            return 2;
        }
        if (string.startsWith("sidebar.team.") && (\u2603 = a.b(\u2603 = string.substring("sidebar.team.".length()))) != null && \u2603.b() >= 0) {
            return \u2603.b() + 3;
        }
        return -1;
    }

    public static String[] h() {
        if (g == null) {
            g = new String[19];
            for (int i2 = 0; i2 < 19; ++i2) {
                auo.g[i2] = auo.b(i2);
            }
        }
        return g;
    }

    public void a(pk pk2) {
        if (pk2 == null || pk2 instanceof wn || pk2.ai()) {
            return;
        }
        String string = pk2.aK().toString();
        this.d(string, null);
        this.f(string);
    }
}

