/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.entities.InviteImpl;

public interface Invite {
    @Nonnull
    public static RestAction<Invite> resolve(@Nonnull JDA api2, @Nonnull String code) {
        return Invite.resolve(api2, code, false);
    }

    @Nonnull
    public static RestAction<Invite> resolve(@Nonnull JDA api2, @Nonnull String code, boolean withCounts) {
        return InviteImpl.resolve(api2, code, withCounts);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();

    @Nonnull
    @CheckReturnValue
    public RestAction<Invite> expand();

    @Nonnull
    public InviteType getType();

    @Nullable
    public Channel getChannel();

    @Nonnull
    public String getCode();

    @Nullable
    public Group getGroup();

    @Nonnull
    default public String getUrl() {
        return "https://discord.gg/" + this.getCode();
    }

    @Nonnull
    @Deprecated
    @DeprecatedSince(value="4.0.0")
    @ReplaceWith(value="getTimeCreated()")
    public OffsetDateTime getCreationTime();

    @Nullable
    public Guild getGuild();

    @Nullable
    public User getInviter();

    @Nonnull
    public JDA getJDA();

    public int getMaxAge();

    public int getMaxUses();

    @Nonnull
    public OffsetDateTime getTimeCreated();

    public int getUses();

    public boolean isExpanded();

    public boolean isTemporary();

    public static enum InviteType {
        GUILD,
        GROUP,
        UNKNOWN;

    }

    public static interface Group
    extends ISnowflake {
        @Nullable
        public String getIconId();

        @Nullable
        public String getIconUrl();

        @Nullable
        public String getName();

        @Nullable
        public List<String> getUsers();
    }

    public static interface Guild
    extends ISnowflake {
        @Nullable
        public String getIconId();

        @Nullable
        public String getIconUrl();

        @Nonnull
        public String getName();

        @Nullable
        public String getSplashId();

        @Nullable
        public String getSplashUrl();

        @Nonnull
        public Guild.VerificationLevel getVerificationLevel();

        public int getOnlineCount();

        public int getMemberCount();

        @Nonnull
        public Set<String> getFeatures();
    }

    public static interface Channel
    extends ISnowflake {
        @Nonnull
        public String getName();

        @Nonnull
        public ChannelType getType();
    }
}

