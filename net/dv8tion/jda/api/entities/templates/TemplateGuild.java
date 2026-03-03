/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities.templates;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.templates.TemplateChannel;
import net.dv8tion.jda.api.entities.templates.TemplateRole;

public class TemplateGuild
implements ISnowflake {
    private final long id;
    private final String name;
    private final String description;
    private final String region;
    private final String iconId;
    private final Guild.VerificationLevel verificationLevel;
    private final Guild.NotificationLevel notificationLevel;
    private final Guild.ExplicitContentLevel explicitContentLevel;
    private final Locale locale;
    private final Guild.Timeout afkTimeout;
    private final TemplateChannel afkChannel;
    private final TemplateChannel systemChannel;
    private final List<TemplateRole> roles;
    private final List<TemplateChannel> channels;

    public TemplateGuild(long id2, String name, String description, String region, String iconId, Guild.VerificationLevel verificationLevel, Guild.NotificationLevel notificationLevel, Guild.ExplicitContentLevel explicitContentLevel, Locale locale, Guild.Timeout afkTimeout, TemplateChannel afkChannel, TemplateChannel systemChannel, List<TemplateRole> roles, List<TemplateChannel> channels) {
        this.id = id2;
        this.name = name;
        this.description = description;
        this.region = region;
        this.iconId = iconId;
        this.verificationLevel = verificationLevel;
        this.notificationLevel = notificationLevel;
        this.explicitContentLevel = explicitContentLevel;
        this.locale = locale;
        this.afkTimeout = afkTimeout;
        this.afkChannel = afkChannel;
        this.systemChannel = systemChannel;
        this.roles = Collections.unmodifiableList(roles);
        this.channels = Collections.unmodifiableList(channels);
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nonnull
    public Region getRegion() {
        return Region.fromKey(this.region);
    }

    @Nonnull
    public String getRegionRaw() {
        return this.region;
    }

    @Nullable
    public String getIconId() {
        return this.iconId;
    }

    @Nullable
    public String getIconUrl() {
        return this.iconId == null ? null : String.format("https://cdn.discordapp.com/icons/%s/%s.%s", this.id, this.iconId, this.iconId.startsWith("a_") ? "gif" : "png");
    }

    @Nonnull
    public Guild.VerificationLevel getVerificationLevel() {
        return this.verificationLevel;
    }

    @Nonnull
    public Guild.NotificationLevel getDefaultNotificationLevel() {
        return this.notificationLevel;
    }

    @Nonnull
    public Guild.ExplicitContentLevel getExplicitContentLevel() {
        return this.explicitContentLevel;
    }

    @Nonnull
    public Locale getLocale() {
        return this.locale;
    }

    @Nonnull
    public Guild.Timeout getAfkTimeout() {
        return this.afkTimeout;
    }

    @Nullable
    public TemplateChannel getAfkChannel() {
        return this.afkChannel;
    }

    @Nullable
    public TemplateChannel getSystemChannel() {
        return this.systemChannel;
    }

    @Nonnull
    public List<TemplateRole> getRoles() {
        return this.roles;
    }

    @Nonnull
    public List<TemplateChannel> getChannels() {
        return this.channels;
    }
}

