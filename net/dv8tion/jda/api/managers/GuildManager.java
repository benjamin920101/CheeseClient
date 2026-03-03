/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.Manager;

public interface GuildManager
extends Manager<GuildManager> {
    public static final long NAME = 1L;
    public static final long REGION = 2L;
    public static final long ICON = 4L;
    public static final long SPLASH = 8L;
    public static final long AFK_CHANNEL = 16L;
    public static final long AFK_TIMEOUT = 32L;
    public static final long SYSTEM_CHANNEL = 64L;
    public static final long MFA_LEVEL = 128L;
    public static final long NOTIFICATION_LEVEL = 256L;
    public static final long EXPLICIT_CONTENT_LEVEL = 512L;
    public static final long VERIFICATION_LEVEL = 1024L;
    public static final long BANNER = 2048L;
    public static final long VANITY_URL = 4096L;
    public static final long DESCRIPTION = 8192L;
    public static final long RULES_CHANNEL = 16384L;
    public static final long COMMUNITY_UPDATES_CHANNEL = 32768L;

    @Override
    @Nonnull
    public GuildManager reset(long var1);

    @Override
    @Nonnull
    public GuildManager reset(long ... var1);

    @Nonnull
    public Guild getGuild();

    @Nonnull
    @CheckReturnValue
    public GuildManager setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    @Deprecated
    @ReplaceWith(value="ChannelManager.setRegion()")
    @DeprecatedSince(value="4.3.0")
    public GuildManager setRegion(@Nonnull Region var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setIcon(@Nullable Icon var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setSplash(@Nullable Icon var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setAfkChannel(@Nullable VoiceChannel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setSystemChannel(@Nullable TextChannel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setRulesChannel(@Nullable TextChannel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setCommunityUpdatesChannel(@Nullable TextChannel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setAfkTimeout(@Nonnull Guild.Timeout var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setVerificationLevel(@Nonnull Guild.VerificationLevel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setDefaultNotificationLevel(@Nonnull Guild.NotificationLevel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setRequiredMFALevel(@Nonnull Guild.MFALevel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setExplicitContentLevel(@Nonnull Guild.ExplicitContentLevel var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setBanner(@Nullable Icon var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setVanityCode(@Nullable String var1);

    @Nonnull
    @CheckReturnValue
    public GuildManager setDescription(@Nullable String var1);
}

