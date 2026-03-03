/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.util.Arrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class StringUtils {
    public static String implode(double[] array, String separator) {
        StringBuffer out = new StringBuffer();
        boolean first = true;
        for (double v2 : array) {
            if (first) {
                first = false;
            } else {
                out.append(separator);
            }
            out.append(v2);
        }
        return out.toString();
    }

    public static String implode(Object[] values) {
        return StringUtils.implode(values, (Object)", ");
    }

    public static String implode(Object[] values, Object separator) {
        return StringUtils.implode(Arrays.asList(values), separator);
    }

    public static final <T> String implode(Iterable<T> elements, Object separator) {
        String sepStr = separator.toString();
        StringBuilder out = new StringBuilder();
        boolean first = true;
        for (T s2 : elements) {
            if (s2 == null) continue;
            if (first) {
                first = false;
            } else {
                out.append(sepStr);
            }
            out.append(s2);
        }
        return out.toString();
    }

    public static final String implode(Iterable<?> strings) {
        return StringUtils.implode(strings, (Object)", ");
    }
}

