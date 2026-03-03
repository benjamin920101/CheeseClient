/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.entities.UserById;
import net.dv8tion.jda.internal.utils.Checks;

public interface User
extends IMentionable {
    public static final Pattern USER_TAG = Pattern.compile("(.{2,32})#(\\d{4})");
    public static final String AVATAR_URL = "https://cdn.discordapp.com/avatars/%s/%s.%s";
    public static final String DEFAULT_AVATAR_URL = "https://cdn.discordapp.com/embed/avatars/%s.png";

    @Nonnull
    public static User fromId(long id2) {
        return new UserById(id2);
    }

    @Nonnull
    public static User fromId(@Nonnull String id2) {
        return User.fromId(MiscUtil.parseSnowflake(id2));
    }

    @Nonnull
    public String getName();

    @Nonnull
    public String getDiscriminator();

    @Nullable
    public String getAvatarId();

    @Nullable
    default public String getAvatarUrl() {
        String avatarId = this.getAvatarId();
        return avatarId == null ? null : String.format(AVATAR_URL, this.getId(), avatarId, avatarId.startsWith("a_") ? "gif" : "png");
    }

    @Nonnull
    public String getDefaultAvatarId();

    @Nonnull
    default public String getDefaultAvatarUrl() {
        return String.format(DEFAULT_AVATAR_URL, this.getDefaultAvatarId());
    }

    @Nonnull
    default public String getEffectiveAvatarUrl() {
        String avatarUrl = this.getAvatarUrl();
        return avatarUrl == null ? this.getDefaultAvatarUrl() : avatarUrl;
    }

    @Nonnull
    public String getAsTag();

    public boolean hasPrivateChannel();

    @Nonnull
    @CheckReturnValue
    public RestAction<PrivateChannel> openPrivateChannel();

    @Nonnull
    public List<Guild> getMutualGuilds();

    public boolean isBot();

    public boolean isSystem();

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public EnumSet<UserFlag> getFlags();

    public int getFlagsRaw();

    public static enum UserFlag {
        STAFF(0, "Discord Employee"),
        PARTNER(1, "Partnered Server Owner"),
        HYPESQUAD(2, "HypeSquad Events"),
        BUG_HUNTER_LEVEL_1(3, "Bug Hunter Level 1"),
        HYPESQUAD_BRAVERY(6, "HypeSquad Bravery"),
        HYPESQUAD_BRILLIANCE(7, "HypeSquad Brilliance"),
        HYPESQUAD_BALANCE(8, "HypeSquad Balance"),
        EARLY_SUPPORTER(9, "Early Supporter"),
        TEAM_USER(10, "Team User"),
        SYSTEM(12, "System User"),
        BUG_HUNTER_LEVEL_2(14, "Bug Hunter Level 2"),
        VERIFIED_BOT(16, "Verified Bot"),
        VERIFIED_DEVELOPER(17, "Early Verified Bot Developer"),
        CERTIFIED_MODERATOR(18, "Discord Certified Moderator"),
        UNKNOWN(-1, "Unknown");

        public static final UserFlag[] EMPTY_FLAGS;
        private final int offset;
        private final int raw;
        private final String name;

        private UserFlag(int offset, String name) {
            this.offset = offset;
            this.raw = 1 << offset;
            this.name = name;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getRawValue() {
            return this.raw;
        }

        @Nonnull
        public static UserFlag getFromOffset(int offset) {
            for (UserFlag flag : UserFlag.values()) {
                if (flag.offset != offset) continue;
                return flag;
            }
            return UNKNOWN;
        }

        @Nonnull
        public static EnumSet<UserFlag> getFlags(int flags) {
            EnumSet<UserFlag> foundFlags = EnumSet.noneOf(UserFlag.class);
            if (flags == 0) {
                return foundFlags;
            }
            for (UserFlag flag : UserFlag.values()) {
                if (flag == UNKNOWN || (flags & flag.raw) != flag.raw) continue;
                foundFlags.add(flag);
            }
            return foundFlags;
        }

        public static int getRaw(UserFlag ... flags) {
            Checks.noneNull((Object[])flags, "UserFlags");
            int raw = 0;
            for (UserFlag flag : flags) {
                if (flag == null || flag == UNKNOWN) continue;
                raw |= flag.raw;
            }
            return raw;
        }

        public static int getRaw(@Nonnull Collection<UserFlag> flags) {
            Checks.notNull(flags, "Flag Collection");
            return UserFlag.getRaw(flags.toArray(EMPTY_FLAGS));
        }

        static {
            EMPTY_FLAGS = new UserFlag[0];
        }
    }
}

