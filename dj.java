/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class dj {
    private static final Pattern a = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
    private static final Splitter b = Splitter.on('=').limit(2);
    private static dj c = new dj();
    private final Map<String, String> d = Maps.newHashMap();
    private long e;

    public dj() {
        try {
            InputStream inputStream = dj.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang");
            for (String string : IOUtils.readLines(inputStream, Charsets.UTF_8)) {
                if (string.isEmpty() || string.charAt(0) == '#' || (\u2603 = Iterables.toArray(b.split(string), String.class)) == null || \u2603.length != 2) continue;
                \u2603 = \u2603[0];
                \u2603 = a.matcher(\u2603[1]).replaceAll("%$1s");
                this.d.put(\u2603, \u2603);
            }
            this.e = System.currentTimeMillis();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    static dj a() {
        return c;
    }

    public static synchronized void a(Map<String, String> map) {
        dj.c.d.clear();
        dj.c.d.putAll(map);
        dj.c.e = System.currentTimeMillis();
    }

    public synchronized String a(String string) {
        return this.c(string);
    }

    public synchronized String a(String string, Object ... objectArray) {
        String string2 = this.c(string);
        try {
            return String.format(string2, objectArray);
        }
        catch (IllegalFormatException \u26032) {
            return "Format error: " + string2;
        }
    }

    private String c(String string) {
        \u2603 = this.d.get(string);
        return \u2603 == null ? string : \u2603;
    }

    public synchronized boolean b(String string) {
        return this.d.containsKey(string);
    }

    public long c() {
        return this.e;
    }
}

