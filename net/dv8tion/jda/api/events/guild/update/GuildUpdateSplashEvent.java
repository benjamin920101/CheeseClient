/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateSplashEvent
extends GenericGuildUpdateEvent<String> {
    public static final String IDENTIFIER = "splash";

    public GuildUpdateSplashEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable String oldSplashId) {
        super(api2, responseNumber, guild, oldSplashId, guild.getSplashId(), IDENTIFIER);
    }

    @Nullable
    public String getOldSplashId() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getOldSplashUrl() {
        return this.previous == null ? null : String.format("https://cdn.discordapp.com/splashes/%s/%s.png", this.guild.getId(), this.previous);
    }

    @Nullable
    public String getNewSplashId() {
        return (String)this.getNewValue();
    }

    @Nullable
    public String getNewSplashUrl() {
        return this.next == null ? null : String.format("https://cdn.discordapp.com/splashes/%s/%s.png", this.guild.getId(), this.next);
    }
}

