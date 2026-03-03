/*
 * Decompiled with CFR 0.152.
 */
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class adk {
    private TreeMap<String, a> a = new TreeMap();

    public adk() {
        this.a("doFireTick", "true", b.b);
        this.a("mobGriefing", "true", b.b);
        this.a("keepInventory", "false", b.b);
        this.a("doMobSpawning", "true", b.b);
        this.a("doMobLoot", "true", b.b);
        this.a("doTileDrops", "true", b.b);
        this.a("doEntityDrops", "true", b.b);
        this.a("commandBlockOutput", "true", b.b);
        this.a("naturalRegeneration", "true", b.b);
        this.a("doDaylightCycle", "true", b.b);
        this.a("logAdminCommands", "true", b.b);
        this.a("showDeathMessages", "true", b.b);
        this.a("randomTickSpeed", "3", b.c);
        this.a("sendCommandFeedback", "true", b.b);
        this.a("reducedDebugInfo", "false", b.b);
    }

    public void a(String string, String string2, b b2) {
        this.a.put(string, new a(string2, b2));
    }

    public void a(String string, String string2) {
        a a2 = this.a.get(string);
        if (a2 != null) {
            a2.a(string2);
        } else {
            this.a(string, string2, b.a);
        }
    }

    public String a(String string) {
        a a2 = this.a.get(string);
        if (a2 != null) {
            return a2.a();
        }
        return "";
    }

    public boolean b(String string) {
        a a2 = this.a.get(string);
        if (a2 != null) {
            return a2.b();
        }
        return false;
    }

    public int c(String string) {
        a a2 = this.a.get(string);
        if (a2 != null) {
            return a2.c();
        }
        return 0;
    }

    public dn a() {
        dn dn2 = new dn();
        for (String string : this.a.keySet()) {
            a a2 = this.a.get(string);
            dn2.a(string, a2.a());
        }
        return dn2;
    }

    public void a(dn dn2) {
        Set<String> set = dn2.c();
        Iterator<String> \u26032 = set.iterator();
        while (\u26032.hasNext()) {
            String string = \u2603 = \u26032.next();
            \u2603 = dn2.j(\u2603);
            this.a(string, \u2603);
        }
    }

    public String[] b() {
        Set<String> set = this.a.keySet();
        return set.toArray(new String[set.size()]);
    }

    public boolean e(String string) {
        return this.a.containsKey(string);
    }

    public boolean a(String string, b b2) {
        a a2 = this.a.get(string);
        return a2 != null && (a2.e() == b2 || b2 == b.a);
    }

    public static enum b {
        a,
        b,
        c;

    }

    static class a {
        private String a;
        private boolean b;
        private int c;
        private double d;
        private final b e;

        public a(String string, b b2) {
            this.e = b2;
            this.a(string);
        }

        public void a(String string) {
            this.a = string;
            this.b = Boolean.parseBoolean(string);
            this.c = this.b ? 1 : 0;
            try {
                this.c = Integer.parseInt(string);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
            try {
                this.d = Double.parseDouble(string);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }

        public String a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public b e() {
            return this.e;
        }
    }
}

