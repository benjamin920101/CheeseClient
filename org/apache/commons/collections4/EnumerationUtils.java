/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.iterators.EnumerationIterator;

public class EnumerationUtils {
    private EnumerationUtils() {
    }

    public static <T> T get(Enumeration<T> e2, int index) {
        int i2 = index;
        CollectionUtils.checkIndexBounds(i2);
        while (e2.hasMoreElements()) {
            if (--i2 == -1) {
                return e2.nextElement();
            }
            e2.nextElement();
        }
        throw new IndexOutOfBoundsException("Entry does not exist: " + i2);
    }

    public static <E> List<E> toList(Enumeration<? extends E> enumeration) {
        return IteratorUtils.toList(new EnumerationIterator<E>(enumeration));
    }

    public static List<String> toList(StringTokenizer stringTokenizer) {
        ArrayList<String> result = new ArrayList<String>(stringTokenizer.countTokens());
        while (stringTokenizer.hasMoreTokens()) {
            result.add(stringTokenizer.nextToken());
        }
        return result;
    }
}

