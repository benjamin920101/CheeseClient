/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import gnu.trove.impl.sync.TSynchronizedLongObjectMap;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;

public class MiscUtil {
    public static int getShardForGuild(long guildId, int shards) {
        return (int)((guildId >>> 22) % (long)shards);
    }

    public static int getShardForGuild(String guildId, int shards) {
        return MiscUtil.getShardForGuild(MiscUtil.parseSnowflake(guildId), shards);
    }

    public static int getShardForGuild(Guild guild, int shards) {
        return MiscUtil.getShardForGuild(guild.getIdLong(), shards);
    }

    public static <T> TLongObjectMap<T> newLongMap() {
        return new TSynchronizedLongObjectMap(new TLongObjectHashMap(), new Object());
    }

    public static long parseLong(String input) {
        if (input.startsWith("-")) {
            return Long.parseLong(input);
        }
        return Long.parseUnsignedLong(input);
    }

    public static long parseSnowflake(String input) {
        Checks.notEmpty(input, "ID");
        try {
            return MiscUtil.parseLong(input);
        }
        catch (NumberFormatException ex2) {
            throw new NumberFormatException(Helpers.format("The specified ID is not a valid snowflake (%s). Expecting a valid long value!", input));
        }
    }

    public static <E> E locked(ReentrantLock lock, Supplier<E> task) {
        try {
            MiscUtil.tryLock(lock);
            E e2 = task.get();
            return e2;
        }
        finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void locked(ReentrantLock lock, Runnable task) {
        try {
            MiscUtil.tryLock(lock);
            task.run();
        }
        finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void tryLock(Lock lock) {
        try {
            if (!lock.tryLock() && !lock.tryLock(10L, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Could not acquire lock in a reasonable timeframe! (10 seconds)");
            }
        }
        catch (InterruptedException e2) {
            throw new IllegalStateException("Unable to acquire lock while thread is interrupted!");
        }
    }

    public static void appendTo(Formatter formatter, int width, int precision, boolean leftJustified, String out) {
        try {
            Appendable appendable = formatter.out();
            if (precision > -1 && out.length() > precision) {
                appendable.append(Helpers.truncate(out, precision));
                return;
            }
            if (leftJustified) {
                appendable.append(Helpers.rightPad(out, width));
            } else {
                appendable.append(Helpers.leftPad(out, width));
            }
        }
        catch (IOException e2) {
            throw new UncheckedIOException(e2);
        }
    }
}

