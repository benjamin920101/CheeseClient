/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateAfkChannelEvent
extends GenericGuildUpdateEvent<VoiceChannel> {
    public static final String IDENTIFIER = "afk_channel";

    public GuildUpdateAfkChannelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable VoiceChannel oldAfkChannel) {
        super(api2, responseNumber, guild, oldAfkChannel, guild.getAfkChannel(), IDENTIFIER);
    }

    @Nullable
    public VoiceChannel getOldAfkChannel() {
        return (VoiceChannel)this.getOldValue();
    }

    @Nullable
    public VoiceChannel getNewAfkChannel() {
        return (VoiceChannel)this.getNewValue();
    }
}

