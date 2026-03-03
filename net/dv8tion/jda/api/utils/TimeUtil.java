/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.internal.utils.Checks;

public class TimeUtil {
    public static final long DISCORD_EPOCH = 1420070400000L;
    public static final long TIMESTAMP_OFFSET = 22L;
    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.RFC_1123_DATE_TIME;

    public static long getDiscordTimestamp(long millisTimestamp) {
        return millisTimestamp - 1420070400000L << 22;
    }

    @Nonnull
    public static OffsetDateTime getTimeCreated(long entityId) {
        long timestamp = (entityId >>> 22) + 1420070400000L;
        Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmt.setTimeInMillis(timestamp);
        return OffsetDateTime.ofInstant(gmt.toInstant(), gmt.getTimeZone().toZoneId());
    }

    @Nonnull
    public static OffsetDateTime getTimeCreated(@Nonnull ISnowflake entity) {
        Checks.notNull(entity, "Entity");
        return TimeUtil.getTimeCreated(entity.getIdLong());
    }

    @Nonnull
    public static String getDateTimeString(@Nonnull OffsetDateTime time) {
        return time.format(dtFormatter);
    }
}

