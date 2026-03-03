/*
 * Decompiled with CFR 0.152.
 */
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class nx {
    private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static String a(int n2) {
        \u2603 = n2 / 20;
        \u2603 = \u2603 / 60;
        if ((\u2603 %= 60) < 10) {
            return \u2603 + ":0" + \u2603;
        }
        return \u2603 + ":" + \u2603;
    }

    public static String a(String string) {
        return a.matcher(string).replaceAll("");
    }

    public static boolean b(String string) {
        return StringUtils.isEmpty(string);
    }
}

