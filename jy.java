/*
 * Decompiled with CFR 0.152.
 */
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class jy {
    protected final String a;
    protected final String b;

    protected jy(int n2, String ... stringArray) {
        this.a = StringUtils.isEmpty(stringArray[0]) ? "minecraft" : stringArray[0].toLowerCase();
        this.b = stringArray[1];
        Validate.notNull(this.b);
    }

    public jy(String string) {
        this(0, jy.a(string));
    }

    public jy(String string, String string2) {
        this(0, string, string2);
    }

    protected static String[] a(String string) {
        String[] stringArray = new String[]{null, string};
        int \u26032 = string.indexOf(58);
        if (\u26032 >= 0) {
            stringArray[1] = string.substring(\u26032 + 1, string.length());
            if (\u26032 > 1) {
                stringArray[0] = string.substring(0, \u26032);
            }
        }
        return stringArray;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public String toString() {
        return this.a + ':' + this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof jy) {
            jy jy2 = (jy)object;
            return this.a.equals(jy2.a) && this.b.equals(jy2.b);
        }
        return false;
    }

    public int hashCode() {
        return 31 * this.a.hashCode() + this.b.hashCode();
    }
}

