/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class bnt {
    private static final Splitter b = Splitter.on('=').limit(2);
    private static final Pattern c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
    Map<String, String> a = Maps.newHashMap();
    private boolean d;

    public synchronized void a(bni bni2, List<String> list) {
        this.a.clear();
        for (String string : list) {
            \u2603 = String.format("lang/%s.lang", string);
            for (String string2 : bni2.a()) {
                try {
                    this.a(bni2.b(new jy(string2, \u2603)));
                }
                catch (IOException iOException) {}
            }
        }
        this.b();
    }

    public boolean a() {
        return this.d;
    }

    private void b() {
        this.d = false;
        int n2 = 0;
        \u2603 = 0;
        for (String string : this.a.values()) {
            int n3 = string.length();
            \u2603 += n3;
            for (\u2603 = 0; \u2603 < n3; ++\u2603) {
                if (string.charAt(\u2603) < '\u0100') continue;
                ++n2;
            }
        }
        float \u26032 = (float)n2 / (float)\u2603;
        this.d = (double)\u26032 > 0.1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(List<bnh> list) throws IOException {
        for (bnh bnh2 : list) {
            InputStream inputStream = bnh2.b();
            try {
                this.a(inputStream);
            }
            finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    private void a(InputStream inputStream) throws IOException {
        for (String string : IOUtils.readLines(inputStream, Charsets.UTF_8)) {
            if (string.isEmpty() || string.charAt(0) == '#' || (\u2603 = Iterables.toArray(b.split(string), String.class)) == null || \u2603.length != 2) continue;
            \u2603 = \u2603[0];
            \u2603 = c.matcher(\u2603[1]).replaceAll("%$1s");
            this.a.put(\u2603, \u2603);
        }
    }

    private String b(String string) {
        \u2603 = this.a.get(string);
        return \u2603 == null ? string : \u2603;
    }

    public String a(String string, Object[] objectArray) {
        String string2 = this.b(string);
        try {
            return String.format(string2, objectArray);
        }
        catch (IllegalFormatException \u26032) {
            return "Format error: " + string2;
        }
    }
}

