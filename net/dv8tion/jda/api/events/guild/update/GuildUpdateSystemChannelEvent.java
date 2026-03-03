/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateSystemChannelEvent
extends GenericGuildUpdateEvent<TextChannel> {
    public static final String IDENTIFIER = "system_channel";

    public GuildUpdateSystemChannelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable TextChannel oldSystemChannel) {
        super(api2, responseNumber, guild, oldSystemChannel, guild.getSystemChannel(), IDENTIFIER);
    }

    @Nullable
    public TextChannel getOldSystemChannel() {
        return (TextChannel)this.getOldValue();
    }

    @Nullable
    public TextChannel getNewSystemChannel() {
        return (TextChannel)this.getNewValue();
    }
}

