/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateIconEvent
extends GenericGuildUpdateEvent<String> {
    public static final String IDENTIFIER = "icon";

    public GuildUpdateIconEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable String oldIconId) {
        super(api2, responseNumber, guild, oldIconId, guild.getIconId(), IDENTIFIER);
    }

    @Nullable
    public String getOldIconId() {
        return (String)this.getOldValue();
    }

    @Nullable
    public String getOldIconUrl() {
        return this.previous == null ? null : String.format("https://cdn.discordapp.com/icons/%s/%s.%s", this.guild.getId(), this.previous, ((String)this.previous).startsWith("a_") ? "gif" : "png");
    }

    @Nullable
    public String getNewIconId() {
        return (String)this.getNewValue();
    }

    @Nullable
    public String getNewIconUrl() {
        return this.next == null ? null : String.format("https://cdn.discordapp.com/icons/%s/%s.%s", this.guild.getId(), this.next, ((String)this.next).startsWith("a_") ? "gif" : "png");
    }
}

