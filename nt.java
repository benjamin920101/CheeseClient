/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class nt {
    private static final Logger b = LogManager.getLogger();
    private final List<String> c = Lists.newArrayList();
    private final List<Long> d = Lists.newArrayList();
    public boolean a;
    private String e = "";
    private final Map<String, Long> f = Maps.newHashMap();

    public void a() {
        this.f.clear();
        this.e = "";
        this.c.clear();
    }

    public void a(String string) {
        if (!this.a) {
            return;
        }
        if (this.e.length() > 0) {
            this.e = this.e + ".";
        }
        this.e = this.e + string;
        this.c.add(this.e);
        this.d.add(System.nanoTime());
    }

    public void b() {
        if (!this.a) {
            return;
        }
        long l2 = System.nanoTime();
        \u2603 = this.d.remove(this.d.size() - 1);
        this.c.remove(this.c.size() - 1);
        \u2603 = l2 - \u2603;
        if (this.f.containsKey(this.e)) {
            this.f.put(this.e, this.f.get(this.e) + \u2603);
        } else {
            this.f.put(this.e, \u2603);
        }
        if (\u2603 > 100000000L) {
            b.warn("Something's taking too long! '" + this.e + "' took aprox " + (double)\u2603 / 1000000.0 + " ms");
        }
        this.e = !this.c.isEmpty() ? this.c.get(this.c.size() - 1) : "";
    }

    public List<a> b(String string2) {
        if (!this.a) {
            return null;
        }
        String string = string2;
        long l2 = this.f.containsKey("root") ? this.f.get("root") : 0L;
        long \u26037 = this.f.containsKey(string2) ? this.f.get(string2) : -1L;
        ArrayList<a> \u26032 = Lists.newArrayList();
        if (string2.length() > 0) {
            string2 = string2 + ".";
        }
        long l3 = 0L;
        for (String string3 : this.f.keySet()) {
            if (string3.length() <= string2.length() || !string3.startsWith(string2) || string3.indexOf(".", string2.length() + 1) >= 0) continue;
            l3 += this.f.get(string3).longValue();
        }
        float \u26033 = l3;
        if (l3 < \u26037) {
            l3 = \u26037;
        }
        if (l2 < l3) {
            l2 = l3;
        }
        for (String string3 : this.f.keySet()) {
            if (string3.length() <= string2.length() || !string3.startsWith(string2) || string3.indexOf(".", string2.length() + 1) >= 0) continue;
            long l4 = this.f.get(string3);
            double \u26034 = (double)l4 * 100.0 / (double)l3;
            double \u26035 = (double)l4 * 100.0 / (double)l2;
            String \u26036 = string3.substring(string2.length());
            \u26032.add(new a(\u26036, \u26034, \u26035));
        }
        for (String string4 : this.f.keySet()) {
            this.f.put(string4, this.f.get(string4) * 999L / 1000L);
        }
        if ((float)l3 > \u26033) {
            \u26032.add(new a("unspecified", (double)((float)l3 - \u26033) * 100.0 / (double)l3, (double)((float)l3 - \u26033) * 100.0 / (double)l2));
        }
        Collections.sort(\u26032);
        \u26032.add(0, new a(string, 100.0, (double)l3 * 100.0 / (double)l2));
        return \u26032;
    }

    public void c(String string) {
        this.b();
        this.a(string);
    }

    public String c() {
        return this.c.size() == 0 ? "[UNKNOWN]" : this.c.get(this.c.size() - 1);
    }

    public static final class a
    implements Comparable<a> {
        public double a;
        public double b;
        public String c;

        public a(String string, double d2, double d3) {
            this.c = string;
            this.a = d2;
            this.b = d3;
        }

        public int a(a a2) {
            if (a2.a < this.a) {
                return -1;
            }
            if (a2.a > this.a) {
                return 1;
            }
            return a2.c.compareTo(this.c);
        }

        public int a() {
            return (this.c.hashCode() & 0xAAAAAA) + 0x444444;
        }

        @Override
        public /* synthetic */ int compareTo(Object object) {
            return this.a((a)object);
        }
    }
}

