/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import java.util.Collection;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.managers.Manager;

public interface ChannelManager
extends Manager<ChannelManager> {
    public static final long NAME = 1L;
    public static final long PARENT = 2L;
    public static final long TOPIC = 4L;
    public static final long POSITION = 8L;
    public static final long NSFW = 16L;
    public static final long USERLIMIT = 32L;
    public static final long BITRATE = 64L;
    public static final long PERMISSION = 128L;
    public static final long SLOWMODE = 256L;
    public static final long NEWS = 512L;
    public static final long REGION = 1024L;

    @Override
    @Nonnull
    public ChannelManager reset(long var1);

    @Override
    @Nonnull
    public ChannelManager reset(long ... var1);

    @Nonnull
    public GuildChannel getChannel();

    @Nonnull
    default public ChannelType getType() {
        return this.getChannel().getType();
    }

    @Nonnull
    default public Guild getGuild() {
        return this.getChannel().getGuild();
    }

    @Nonnull
    @CheckReturnValue
    public ChannelManager clearOverridesAdded();

    @Nonnull
    @CheckReturnValue
    public ChannelManager clearOverridesRemoved();

    @Nonnull
    @CheckReturnValue
    public ChannelManager putPermissionOverride(@Nonnull IPermissionHolder var1, long var2, long var4);

    @Nonnull
    @CheckReturnValue
    default public ChannelManager putPermissionOverride(@Nonnull IPermissionHolder permHolder, @Nullable Collection<Permission> allow, @Nullable Collection<Permission> deny) {
        long allowRaw = allow == null ? 0L : Permission.getRaw(allow);
        long denyRaw = deny == null ? 0L : Permission.getRaw(deny);
        return this.putPermissionOverride(permHolder, allowRaw, denyRaw);
    }

    @Nonnull
    @CheckReturnValue
    public ChannelManager removePermissionOverride(@Nonnull IPermissionHolder var1);

    @Nonnull
    @CheckReturnValue
    default public ChannelManager sync() {
        if (this.getChannel().getParent() == null) {
            throw new IllegalStateException("sync() requires a parent category");
        }
        return this.sync(this.getChannel().getParent());
    }

    @Nonnull
    @CheckReturnValue
    public ChannelManager sync(@Nonnull GuildChannel var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setParent(@Nullable Category var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setPosition(int var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setTopic(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setNSFW(boolean var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setSlowmode(int var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setUserLimit(int var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setBitrate(int var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setRegion(Region var1);

    @Nonnull
    @CheckReturnValue
    public ChannelManager setNews(boolean var1);
}

