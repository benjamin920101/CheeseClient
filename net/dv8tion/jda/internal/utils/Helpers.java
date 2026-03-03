/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;
import net.dv8tion.jda.internal.utils.Checks;

public final class Helpers {
    private static final Consumer EMPTY_CONSUMER = v2 -> {};

    public static <T> Consumer<T> emptyConsumer() {
        return EMPTY_CONSUMER;
    }

    public static String format(String format, Object ... args) {
        return String.format(Locale.ROOT, format, args);
    }

    public static boolean isEmpty(CharSequence seq) {
        return seq == null || seq.length() == 0;
    }

    public static boolean containsWhitespace(CharSequence seq) {
        if (Helpers.isEmpty(seq)) {
            return false;
        }
        for (int i2 = 0; i2 < seq.length(); ++i2) {
            if (!Character.isWhitespace(seq.charAt(i2))) continue;
            return true;
        }
        return false;
    }

    public static boolean isBlank(CharSequence seq) {
        if (Helpers.isEmpty(seq)) {
            return true;
        }
        for (int i2 = 0; i2 < seq.length(); ++i2) {
            if (Character.isWhitespace(seq.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static int countMatches(CharSequence seq, char c2) {
        if (Helpers.isEmpty(seq)) {
            return 0;
        }
        int count = 0;
        for (int i2 = 0; i2 < seq.length(); ++i2) {
            if (seq.charAt(i2) != c2) continue;
            ++count;
        }
        return count;
    }

    public static String truncate(String input, int maxWidth) {
        if (input == null) {
            return null;
        }
        Checks.notNegative(maxWidth, "maxWidth");
        if (input.length() <= maxWidth) {
            return input;
        }
        if (maxWidth == 0) {
            return "";
        }
        return input.substring(0, maxWidth);
    }

    public static String rightPad(String input, int size) {
        int pads = size - input.length();
        if (pads <= 0) {
            return input;
        }
        StringBuilder out = new StringBuilder(input);
        for (int i2 = pads; i2 > 0; --i2) {
            out.append(' ');
        }
        return out.toString();
    }

    public static String leftPad(String input, int size) {
        int pads = size - input.length();
        if (pads <= 0) {
            return input;
        }
        StringBuilder out = new StringBuilder();
        for (int i2 = pads; i2 > 0; --i2) {
            out.append(' ');
        }
        return out.append(input).toString();
    }

    public static boolean isNumeric(String input) {
        if (Helpers.isEmpty(input)) {
            return false;
        }
        for (char c2 : input.toCharArray()) {
            if (Character.isDigit(c2)) continue;
            return false;
        }
        return true;
    }

    public static int codePointLength(String string) {
        return string.codePointCount(0, string.length());
    }

    public static boolean deepEquals(Collection<?> first, Collection<?> second) {
        if (first == second) {
            return true;
        }
        if (first == null || second == null || first.size() != second.size()) {
            return false;
        }
        Iterator<?> itFirst = first.iterator();
        Iterator<?> itSecond = second.iterator();
        while (itFirst.hasNext()) {
            Object elementSecond;
            Object elementFirst = itFirst.next();
            if (Objects.equals(elementFirst, elementSecond = itSecond.next())) continue;
            return false;
        }
        return true;
    }

    public static boolean deepEqualsUnordered(Collection<?> first, Collection<?> second) {
        if (first == second) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        return first.size() == second.size() && second.containsAll(first);
    }

    public static <E extends Enum<E>> EnumSet<E> copyEnumSet(Class<E> clazz, Collection<E> col) {
        return col == null || col.isEmpty() ? EnumSet.noneOf(clazz) : EnumSet.copyOf(col);
    }

    public static <T extends Throwable> T appendCause(T throwable, Throwable cause) {
        Object t2 = throwable;
        while (t2.getCause() != null) {
            t2 = t2.getCause();
        }
        t2.initCause(cause);
        return throwable;
    }

    public static boolean hasCause(Throwable throwable, Class<? extends Throwable> cause) {
        for (Throwable cursor = throwable; cursor != null; cursor = cursor.getCause()) {
            if (!cause.isInstance(cursor)) continue;
            return true;
        }
        return false;
    }
}

