/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateBannerEvent
extends GenericGuildUpdateEvent<String> {
    public static final String IDENTIFIER = "banner";

    public GuildUpdateBannerEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable String previous) {
        super(api2, responseNumber, guild, previous, guild.getBannerId(), IDENTIFIER);
    }

    @Nullable
    public String getNewBannerId() {
        return (String)this.getNewValue();
    }

    @Nullable
    public String getNewBannerUrl() {
        return this.next == null ? null : String.format("https://cdn.discordapp.com/banners/%s/%s.png", this.guild.getId(), this.next);
    }

    @Nullable
    @Deprecated
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="getNewBannerUrl()")
    public String getNewBannerIdUrl() {
        return this.getNewBannerUrl();
    }

    @Nullable
    public String getOldBannerId() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getOldBannerUrl() {
        return this.previous == null ? null : String.format("https://cdn.discordapp.com/banners/%s/%s.png", this.guild.getId(), this.previous);
    }
}

