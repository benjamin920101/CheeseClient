/*
 * Decompiled with CFR 0.152.
 */
import java.net.IDN;
import java.util.Hashtable;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class bdd {
    private final String a;
    private final int b;

    private bdd(String string, int n2) {
        this.a = string;
        this.b = n2;
    }

    public String a() {
        return IDN.toASCII(this.a);
    }

    public int b() {
        return this.b;
    }

    public static bdd a(String string) {
        String string2;
        Object \u26032;
        if (string == null) {
            return null;
        }
        String[] \u26033 = string.split(":");
        if (string.startsWith("[") && (\u2603 = string.indexOf("]")) > 0) {
            String string3 = string.substring(1, \u2603);
            \u26032 = string.substring(\u2603 + 1).trim();
            if (\u26032.startsWith(":") && \u26032.length() > 0) {
                \u26032 = \u26032.substring(1);
                \u26033 = new String[]{string3, \u26032};
            } else {
                \u26033 = new String[]{string3};
            }
        }
        if (\u26033.length > 2) {
            \u26033 = new String[]{string};
        }
        string2 = \u26033[0];
        int n2 = n3 = \u26033.length > 1 ? bdd.a(\u26033[1], 25565) : 25565;
        if (n3 == 25565) {
            \u26032 = bdd.b(string2);
            string2 = \u26032[0];
            int n3 = bdd.a(\u26032[1], 25565);
        }
        return new bdd(string2, n3);
    }

    private static String[] b(String string) {
        try {
            \u2603 = "com.sun.jndi.dns.DnsContextFactory";
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            hashtable.put("java.naming.provider.url", "dns:");
            hashtable.put("com.sun.jndi.dns.timeout.retries", "1");
            InitialDirContext \u26032 = new InitialDirContext(hashtable);
            Attributes \u26033 = \u26032.getAttributes("_minecraft._tcp." + string, new String[]{"SRV"});
            String[] \u26034 = \u26033.get("srv").get().toString().split(" ", 4);
            return new String[]{\u26034[3], \u26034[2]};
        }
        catch (Throwable throwable) {
            return new String[]{string, Integer.toString(25565)};
        }
    }

    private static int a(String string, int n2) {
        try {
            return Integer.parseInt(string.trim());
        }
        catch (Exception exception) {
            return n2;
        }
    }
}

