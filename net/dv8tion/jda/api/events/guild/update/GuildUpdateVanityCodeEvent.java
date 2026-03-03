/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateVanityCodeEvent
extends GenericGuildUpdateEvent<String> {
    public static final String IDENTIFIER = "vanity_code";

    public GuildUpdateVanityCodeEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable String previous) {
        super(api2, responseNumber, guild, previous, guild.getVanityCode(), IDENTIFIER);
    }

    @Nullable
    public String getOldVanityCode() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getOldVanityUrl() {
        return this.getOldVanityCode() == null ? null : "https://discord.gg/" + this.getOldVanityCode();
    }

    @Nullable
    public String getNewVanityCode() {
        return (String)this.getNewValue();
    }

    @Nullable
    public String getNewVanityUrl() {
        return this.getNewVanityCode() == null ? null : "https://discord.gg/" + this.getNewVanityCode();
    }
}

