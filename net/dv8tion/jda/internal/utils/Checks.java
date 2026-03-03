/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.Collection;
import java.util.Locale;
import java.util.regex.Pattern;
import net.dv8tion.jda.internal.utils.Helpers;
import org.jetbrains.annotations.Contract;

public class Checks {
    public static final Pattern ALPHANUMERIC_WITH_DASH = Pattern.compile("[\\w-]+", 256);
    public static final Pattern ALPHANUMERIC = Pattern.compile("[\\w]+", 256);

    @Contract(value="null -> fail")
    public static void isSnowflake(String snowflake) {
        Checks.isSnowflake(snowflake, snowflake);
    }

    @Contract(value="null, _ -> fail")
    public static void isSnowflake(String snowflake, String message) {
        Checks.notNull(snowflake, message);
        if (snowflake.length() > 20 || !Helpers.isNumeric(snowflake)) {
            throw new IllegalArgumentException(message + " is not a valid snowflake value! Provided: \"" + snowflake + "\"");
        }
    }

    @Contract(value="false, _ -> fail")
    public static void check(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    @Contract(value="false, _, _ -> fail")
    public static void check(boolean expression, String message, Object ... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    @Contract(value="false, _, _ -> fail")
    public static void check(boolean expression, String message, Object arg2) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, arg2));
        }
    }

    @Contract(value="null, _ -> fail")
    public static void notNull(Object argument, String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
    }

    @Contract(value="null, _ -> fail")
    public static void notEmpty(CharSequence argument, String name) {
        Checks.notNull(argument, name);
        if (Helpers.isEmpty(argument)) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
    }

    @Contract(value="null, _ -> fail")
    public static void notBlank(CharSequence argument, String name) {
        Checks.notNull(argument, name);
        if (Helpers.isBlank(argument)) {
            throw new IllegalArgumentException(name + " may not be blank");
        }
    }

    @Contract(value="null, _ -> fail")
    public static void noWhitespace(CharSequence argument, String name) {
        Checks.notNull(argument, name);
        if (Helpers.containsWhitespace(argument)) {
            throw new IllegalArgumentException(name + " may not contain blanks. Provided: \"" + argument + "\"");
        }
    }

    @Contract(value="null, _ -> fail")
    public static void notEmpty(Collection<?> argument, String name) {
        Checks.notNull(argument, name);
        if (argument.isEmpty()) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
    }

    @Contract(value="null, _ -> fail")
    public static void notEmpty(Object[] argument, String name) {
        Checks.notNull(argument, name);
        if (argument.length == 0) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
    }

    @Contract(value="null, _ -> fail")
    public static void noneNull(Collection<?> argument, String name) {
        Checks.notNull(argument, name);
        argument.forEach(it2 -> Checks.notNull(it2, name));
    }

    @Contract(value="null, _ -> fail")
    public static void noneNull(Object[] argument, String name) {
        Checks.notNull(argument, name);
        for (Object it2 : argument) {
            Checks.notNull(it2, name);
        }
    }

    @Contract(value="null, _ -> fail")
    public static <T extends CharSequence> void noneEmpty(Collection<T> argument, String name) {
        Checks.notNull(argument, name);
        argument.forEach(it2 -> Checks.notEmpty(it2, name));
    }

    @Contract(value="null, _ -> fail")
    public static <T extends CharSequence> void noneBlank(Collection<T> argument, String name) {
        Checks.notNull(argument, name);
        argument.forEach(it2 -> Checks.notBlank(it2, name));
    }

    @Contract(value="null, _ -> fail")
    public static <T extends CharSequence> void noneContainBlanks(Collection<T> argument, String name) {
        Checks.notNull(argument, name);
        argument.forEach(it2 -> Checks.noWhitespace(it2, name));
    }

    public static void notLonger(String input, int length, String name) {
        Checks.notNull(input, name);
        Checks.check(Helpers.codePointLength(input) <= length, "%s may not be longer than %d characters! Provided: \"%s\"", name, length, input);
    }

    public static void matches(String input, Pattern pattern, String name) {
        Checks.notNull(input, name);
        Checks.check(pattern.matcher(input).matches(), "%s must match regex ^%s$. Provided: \"%s\"", name, pattern.pattern(), input);
    }

    public static void isLowercase(String input, String name) {
        Checks.notNull(input, name);
        Checks.check(input.toLowerCase(Locale.ROOT).equals(input), "%s must be lowercase only! Provided: \"%s\"", name, input);
    }

    public static void positive(int n2, String name) {
        if (n2 <= 0) {
            throw new IllegalArgumentException(name + " may not be negative or zero");
        }
    }

    public static void positive(long n2, String name) {
        if (n2 <= 0L) {
            throw new IllegalArgumentException(name + " may not be negative or zero");
        }
    }

    public static void notNegative(int n2, String name) {
        if (n2 < 0) {
            throw new IllegalArgumentException(name + " may not be negative");
        }
    }

    public static void notNegative(long n2, String name) {
        if (n2 < 0L) {
            throw new IllegalArgumentException(name + " may not be negative");
        }
    }
}

