/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.Incubating;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.RichPresence;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.EncodingUtil;
import net.dv8tion.jda.internal.utils.Helpers;

public interface Activity {
    public static final Pattern STREAMING_URL = Pattern.compile("https?://(www\\.)?(twitch\\.tv/|youtube\\.com/watch\\?v=).+", 2);

    public boolean isRich();

    @Nullable
    public RichPresence asRichPresence();

    @Nonnull
    public String getName();

    @Nullable
    public String getUrl();

    @Nonnull
    public ActivityType getType();

    @Nullable
    public Timestamps getTimestamps();

    @Nullable
    public Emoji getEmoji();

    @Nonnull
    public static Activity playing(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notLonger(name, 128, "Name");
        return EntityBuilder.createActivity(name, null, ActivityType.DEFAULT);
    }

    @Nonnull
    public static Activity streaming(@Nonnull String name, @Nullable String url) {
        Checks.notEmpty(name, "Provided game name");
        name = Helpers.isBlank(name) ? name : name.trim();
        Checks.notLonger(name, 128, "Name");
        ActivityType type = Activity.isValidStreamingUrl(url) ? ActivityType.STREAMING : ActivityType.DEFAULT;
        return EntityBuilder.createActivity(name, url, type);
    }

    @Nonnull
    public static Activity listening(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notLonger(name, 128, "Name");
        return EntityBuilder.createActivity(name, null, ActivityType.LISTENING);
    }

    @Nonnull
    @Incubating
    public static Activity watching(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notLonger(name, 128, "Name");
        return EntityBuilder.createActivity(name, null, ActivityType.WATCHING);
    }

    @Nonnull
    public static Activity competing(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notLonger(name, 128, "Name");
        return EntityBuilder.createActivity(name, null, ActivityType.COMPETING);
    }

    @Nonnull
    public static Activity of(@Nonnull ActivityType type, @Nonnull String name) {
        return Activity.of(type, name, null);
    }

    @Nonnull
    public static Activity of(@Nonnull ActivityType type, @Nonnull String name, @Nullable String url) {
        Checks.notNull((Object)type, "Type");
        switch (type) {
            case DEFAULT: {
                return Activity.playing(name);
            }
            case STREAMING: {
                return Activity.streaming(name, url);
            }
            case LISTENING: {
                return Activity.listening(name);
            }
            case WATCHING: {
                return Activity.watching(name);
            }
            case COMPETING: {
                return Activity.competing(name);
            }
        }
        throw new IllegalArgumentException("ActivityType " + (Object)((Object)type) + " is not supported!");
    }

    public static boolean isValidStreamingUrl(@Nullable String url) {
        return url != null && STREAMING_URL.matcher(url).matches();
    }

    public static class Emoji
    implements ISnowflake,
    IMentionable {
        private final String name;
        private final long id;
        private final boolean animated;

        public Emoji(String name, long id2, boolean animated) {
            this.name = name;
            this.id = id2;
            this.animated = animated;
        }

        public Emoji(String name) {
            this(name, 0L, false);
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        @Nonnull
        public String getAsCodepoints() {
            if (!this.isEmoji()) {
                throw new IllegalStateException("Cannot convert custom emote to codepoints");
            }
            return EncodingUtil.encodeCodepoints(this.name);
        }

        @Override
        public long getIdLong() {
            if (!this.isEmote()) {
                throw new IllegalStateException("Cannot get id for unicode emoji");
            }
            return this.id;
        }

        public boolean isAnimated() {
            return this.animated;
        }

        public boolean isEmoji() {
            return this.id == 0L;
        }

        public boolean isEmote() {
            return this.id != 0L;
        }

        @Override
        @Nonnull
        public String getAsMention() {
            if (this.isEmoji()) {
                return this.name;
            }
            return String.format("<%s:%s:%s>", this.isAnimated() ? "a" : "", this.name, this.getId());
        }

        public int hashCode() {
            return this.id == 0L ? this.name.hashCode() : Long.hashCode(this.id);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Emoji)) {
                return false;
            }
            Emoji other = (Emoji)obj;
            return this.id == 0L ? other.name.equals(this.name) : other.id == this.id;
        }

        public String toString() {
            if (this.isEmoji()) {
                return "ActivityEmoji(" + this.getAsCodepoints() + ')';
            }
            return "ActivityEmoji(" + Long.toUnsignedString(this.id) + " / " + this.name + ')';
        }
    }

    public static class Timestamps {
        protected final long start;
        protected final long end;

        public Timestamps(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getStart() {
            return this.start;
        }

        @Nullable
        public Instant getStartTime() {
            return this.start <= 0L ? null : Instant.ofEpochMilli(this.start);
        }

        public long getEnd() {
            return this.end;
        }

        @Nullable
        public Instant getEndTime() {
            return this.end <= 0L ? null : Instant.ofEpochMilli(this.end);
        }

        public long getRemainingTime(TemporalUnit unit) {
            Checks.notNull(unit, "TemporalUnit");
            Instant end = this.getEndTime();
            return end != null ? Instant.now().until(end, unit) : -1L;
        }

        public long getElapsedTime(TemporalUnit unit) {
            Checks.notNull(unit, "TemporalUnit");
            Instant start = this.getStartTime();
            return start != null ? start.until(Instant.now(), unit) : -1L;
        }

        public String toString() {
            return Helpers.format("RichPresenceTimestamp(%d-%d)", this.start, this.end);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Timestamps)) {
                return false;
            }
            Timestamps t2 = (Timestamps)obj;
            return this.start == t2.start && this.end == t2.end;
        }

        public int hashCode() {
            return Objects.hash(this.start, this.end);
        }
    }

    public static enum ActivityType {
        DEFAULT(0),
        STREAMING(1),
        LISTENING(2),
        WATCHING(3),
        CUSTOM_STATUS(4),
        COMPETING(5);

        private final int key;

        private ActivityType(int key) {
            this.key = key;
        }

        public int getKey() {
            return this.key;
        }

        @Nonnull
        public static ActivityType fromKey(int key) {
            switch (key) {
                default: {
                    return DEFAULT;
                }
                case 1: {
                    return STREAMING;
                }
                case 2: {
                    return LISTENING;
                }
                case 3: {
                    return WATCHING;
                }
                case 4: {
                    return CUSTOM_STATUS;
                }
                case 5: 
            }
            return COMPETING;
        }
    }
}

