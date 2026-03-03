/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceUpdateEvent;

public class GuildVoiceJoinEvent
extends GenericGuildVoiceUpdateEvent {
    public GuildVoiceJoinEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member, null, member.getVoiceState().getChannel());
    }

    @Override
    @Nonnull
    public VoiceChannel getChannelJoined() {
        return super.getChannelJoined();
    }

    @Override
    @Nonnull
    public VoiceChannel getNewValue() {
        return super.getNewValue();
    }
}

