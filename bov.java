/*
 * Decompiled with CFR 0.152.
 */
import org.apache.commons.lang3.StringUtils;

public class bov
extends jy {
    private final String c;

    protected bov(int n2, String ... stringArray) {
        super(0, stringArray[0], stringArray[1]);
        this.c = StringUtils.isEmpty(stringArray[2]) ? "normal" : stringArray[2].toLowerCase();
    }

    public bov(String string) {
        this(0, bov.b(string));
    }

    public bov(jy jy2, String string) {
        this(jy2.toString(), string);
    }

    public bov(String string, String string2) {
        this(0, bov.b(string + '#' + (string2 == null ? "normal" : string2)));
    }

    protected static String[] b(String string) {
        String[] stringArray = new String[]{null, string, null};
        int \u26032 = string.indexOf(35);
        String \u26033 = string;
        if (\u26032 >= 0) {
            stringArray[2] = string.substring(\u26032 + 1, string.length());
            if (\u26032 > 1) {
                \u26033 = string.substring(0, \u26032);
            }
        }
        System.arraycopy(jy.a(\u26033), 0, stringArray, 0, 2);
        return stringArray;
    }

    public String c() {
        return this.c;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof bov && super.equals(object)) {
            bov bov2 = (bov)object;
            return this.c.equals(bov2.c);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + this.c.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + '#' + this.c;
    }
}

