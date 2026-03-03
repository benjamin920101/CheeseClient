/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ed {
    private static final Logger a = LogManager.getLogger();
    private static final Pattern b = Pattern.compile("\\[[-+\\d|,\\s]+\\]");

    public static dn a(String string) throws ec {
        if (!(string = string.trim()).startsWith("{")) {
            throw new ec("Invalid tag encountered, expected '{' as first char.");
        }
        if (ed.b(string) != 1) {
            throw new ec("Encountered multiple top tags, only one expected");
        }
        return (dn)ed.a("tag", string).a();
    }

    static int b(String string2) throws ec {
        String string2;
        int n2 = 0;
        boolean \u26032 = false;
        Stack<Character> \u26033 = new Stack<Character>();
        for (\u2603 = 0; \u2603 < string2.length(); ++\u2603) {
            char c2 = string2.charAt(\u2603);
            if (c2 == '\"') {
                if (ed.b(string2, \u2603)) {
                    if (\u26032) continue;
                    throw new ec("Illegal use of \\\": " + string2);
                }
                \u26032 = !\u26032;
                continue;
            }
            if (\u26032) continue;
            if (c2 == '{' || c2 == '[') {
                if (\u26033.isEmpty()) {
                    ++n2;
                }
                \u26033.push(Character.valueOf(c2));
                continue;
            }
            if (c2 == '}' && (\u26033.isEmpty() || ((Character)\u26033.pop()).charValue() != '{')) {
                throw new ec("Unbalanced curly brackets {}: " + string2);
            }
            if (c2 != ']' || !\u26033.isEmpty() && ((Character)\u26033.pop()).charValue() == '[') continue;
            throw new ec("Unbalanced square brackets []: " + string2);
        }
        if (\u26032) {
            throw new ec("Unbalanced quotation: " + string2);
        }
        if (!\u26033.isEmpty()) {
            throw new ec("Unbalanced brackets: " + string2);
        }
        if (n2 == 0 && !string2.isEmpty()) {
            n2 = 1;
        }
        return n2;
    }

    static a a(String ... stringArray) throws ec {
        return ed.a(stringArray[0], stringArray[1]);
    }

    static a a(String string, String string22) throws ec {
        String string22;
        if ((string22 = string22.trim()).startsWith("{")) {
            string22 = string22.substring(1, string22.length() - 1);
            b b2 = new b(string);
            while (string22.length() > 0) {
                char \u26032;
                String string3 = ed.b(string22, true);
                if (string3.length() > 0) {
                    \u26032 = '\u0000';
                    b2.b.add(ed.a(string3, \u26032 != '\u0000'));
                }
                if (string22.length() < string3.length() + 1) break;
                \u26032 = string22.charAt(string3.length());
                if (\u26032 != ',' && \u26032 != '{' && \u26032 != '}' && \u26032 != '[' && \u26032 != ']') {
                    throw new ec("Unexpected token '" + \u26032 + "' at: " + string22.substring(string3.length()));
                }
                string22 = string22.substring(string3.length() + 1);
            }
            return b2;
        }
        if (string22.startsWith("[") && !b.matcher(string22).matches()) {
            string22 = string22.substring(1, string22.length() - 1);
            c c2 = new c(string);
            while (string22.length() > 0) {
                char \u26033;
                String string4 = ed.b(string22, false);
                if (string4.length() > 0) {
                    \u26033 = '\u0001';
                    c2.b.add(ed.a(string4, \u26033 != '\u0000'));
                }
                if (string22.length() < string4.length() + 1) break;
                \u26033 = string22.charAt(string4.length());
                if (\u26033 != ',' && \u26033 != '{' && \u26033 != '}' && \u26033 != '[' && \u26033 != ']') {
                    throw new ec("Unexpected token '" + \u26033 + "' at: " + string22.substring(string4.length()));
                }
                string22 = string22.substring(string4.length() + 1);
            }
            return c2;
        }
        return new d(string, string22);
    }

    private static a a(String string, boolean bl2) throws ec {
        String string2 = ed.c(string, bl2);
        \u2603 = ed.d(string, bl2);
        return ed.a(new String[]{string2, \u2603});
    }

    private static String b(String string, boolean bl2) throws ec {
        int n2 = ed.a(string, ':');
        \u2603 = ed.a(string, ',');
        if (bl2) {
            if (n2 == -1) {
                throw new ec("Unable to locate name/value separator for string: " + string);
            }
            if (\u2603 != -1 && \u2603 < n2) {
                throw new ec("Name error at: " + string);
            }
        } else if (n2 == -1 || n2 > \u2603) {
            n2 = -1;
        }
        return ed.a(string, n2);
    }

    private static String a(String string2, int n2) throws ec {
        String string2;
        Stack<Character> stack = new Stack<Character>();
        boolean \u26032 = false;
        boolean \u26033 = false;
        boolean \u26034 = false;
        int \u26035 = 0;
        for (int i2 = n2 + 1; i2 < string2.length(); ++i2) {
            char c2 = string2.charAt(i2);
            if (c2 == '\"') {
                if (ed.b(string2, i2)) {
                    if (!\u26032) {
                        throw new ec("Illegal use of \\\": " + string2);
                    }
                } else {
                    boolean bl2 = \u26032 = !\u26032;
                    if (\u26032 && !\u26034) {
                        \u26033 = true;
                    }
                    if (!\u26032) {
                        \u26035 = i2;
                    }
                }
            } else if (!\u26032) {
                if (c2 == '{' || c2 == '[') {
                    stack.push(Character.valueOf(c2));
                } else {
                    if (c2 == '}' && (stack.isEmpty() || ((Character)stack.pop()).charValue() != '{')) {
                        throw new ec("Unbalanced curly brackets {}: " + string2);
                    }
                    if (c2 == ']' && (stack.isEmpty() || ((Character)stack.pop()).charValue() != '[')) {
                        throw new ec("Unbalanced square brackets []: " + string2);
                    }
                    if (c2 == ',' && stack.isEmpty()) {
                        return string2.substring(0, i2);
                    }
                }
            }
            if (Character.isWhitespace(c2)) continue;
            if (!\u26032 && \u26033 && \u26035 != i2) {
                return string2.substring(0, \u26035 + 1);
            }
            \u26034 = true;
        }
        return string2.substring(0, i2);
    }

    private static String c(String string, boolean bl2) throws ec {
        if (bl2 && ((string = string.trim()).startsWith("{") || string.startsWith("["))) {
            return "";
        }
        int n2 = ed.a(string, ':');
        if (n2 == -1) {
            if (bl2) {
                return "";
            }
            throw new ec("Unable to locate name/value separator for string: " + string);
        }
        return string.substring(0, n2).trim();
    }

    private static String d(String string, boolean bl2) throws ec {
        if (bl2 && ((string = string.trim()).startsWith("{") || string.startsWith("["))) {
            return string;
        }
        int n2 = ed.a(string, ':');
        if (n2 == -1) {
            if (bl2) {
                return string;
            }
            throw new ec("Unable to locate name/value separator for string: " + string);
        }
        return string.substring(n2 + 1).trim();
    }

    private static int a(String string, char c2) {
        boolean bl2 = true;
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c3 = string.charAt(i2);
            if (c3 == '\"') {
                if (ed.b(string, i2)) continue;
                bl2 = !bl2;
                continue;
            }
            if (!bl2) continue;
            if (c3 == c2) {
                return i2;
            }
            if (c3 != '{' && c3 != '[') continue;
            return -1;
        }
        return -1;
    }

    private static boolean b(String string, int n2) {
        return n2 > 0 && string.charAt(n2 - 1) == '\\' && !ed.b(string, n2 - 1);
    }

    static class d
    extends a {
        private static final Pattern c = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");
        private static final Pattern d = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
        private static final Pattern e = Pattern.compile("[-+]?[0-9]+[b|B]");
        private static final Pattern f = Pattern.compile("[-+]?[0-9]+[l|L]");
        private static final Pattern g = Pattern.compile("[-+]?[0-9]+[s|S]");
        private static final Pattern h = Pattern.compile("[-+]?[0-9]+");
        private static final Pattern i = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
        private static final Splitter j = Splitter.on(',').omitEmptyStrings();
        protected String b;

        public d(String string, String string2) {
            this.a = string;
            this.b = string2;
        }

        @Override
        public eb a() throws ec {
            try {
                if (c.matcher(this.b).matches()) {
                    return new dp(Double.parseDouble(this.b.substring(0, this.b.length() - 1)));
                }
                if (d.matcher(this.b).matches()) {
                    return new dr(Float.parseFloat(this.b.substring(0, this.b.length() - 1)));
                }
                if (e.matcher(this.b).matches()) {
                    return new dm(Byte.parseByte(this.b.substring(0, this.b.length() - 1)));
                }
                if (f.matcher(this.b).matches()) {
                    return new dv(Long.parseLong(this.b.substring(0, this.b.length() - 1)));
                }
                if (g.matcher(this.b).matches()) {
                    return new dz(Short.parseShort(this.b.substring(0, this.b.length() - 1)));
                }
                if (h.matcher(this.b).matches()) {
                    return new dt(Integer.parseInt(this.b));
                }
                if (i.matcher(this.b).matches()) {
                    return new dp(Double.parseDouble(this.b));
                }
                if (this.b.equalsIgnoreCase("true") || this.b.equalsIgnoreCase("false")) {
                    return new dm(Boolean.parseBoolean(this.b) ? (byte)1 : 0);
                }
            }
            catch (NumberFormatException numberFormatException) {
                this.b = this.b.replaceAll("\\\\\"", "\"");
                return new ea(this.b);
            }
            if (this.b.startsWith("[") && this.b.endsWith("]")) {
                String string = this.b.substring(1, this.b.length() - 1);
                String[] \u26032 = Iterables.toArray(j.split(string), String.class);
                try {
                    int[] nArray = new int[\u26032.length];
                    for (int i2 = 0; i2 < \u26032.length; ++i2) {
                        nArray[i2] = Integer.parseInt(\u26032[i2].trim());
                    }
                    return new ds(nArray);
                }
                catch (NumberFormatException numberFormatException) {
                    return new ea(this.b);
                }
            }
            if (this.b.startsWith("\"") && this.b.endsWith("\"")) {
                this.b = this.b.substring(1, this.b.length() - 1);
            }
            this.b = this.b.replaceAll("\\\\\"", "\"");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i3 = 0; i3 < this.b.length(); ++i3) {
                if (i3 < this.b.length() - 1 && this.b.charAt(i3) == '\\' && this.b.charAt(i3 + 1) == '\\') {
                    stringBuilder.append('\\');
                    ++i3;
                    continue;
                }
                stringBuilder.append(this.b.charAt(i3));
            }
            return new ea(stringBuilder.toString());
        }
    }

    static class c
    extends a {
        protected List<a> b = Lists.newArrayList();

        public c(String string) {
            this.a = string;
        }

        @Override
        public eb a() throws ec {
            du du2 = new du();
            for (a a2 : this.b) {
                du2.a(a2.a());
            }
            return du2;
        }
    }

    static class b
    extends a {
        protected List<a> b = Lists.newArrayList();

        public b(String string) {
            this.a = string;
        }

        @Override
        public eb a() throws ec {
            dn dn2 = new dn();
            for (a a2 : this.b) {
                dn2.a(a2.a, a2.a());
            }
            return dn2;
        }
    }

    static abstract class a {
        protected String a;

        a() {
        }

        public abstract eb a() throws ec;
    }
}

