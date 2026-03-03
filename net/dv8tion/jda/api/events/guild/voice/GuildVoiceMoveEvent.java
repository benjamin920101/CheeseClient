/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceUpdateEvent;

public class GuildVoiceMoveEvent
extends GenericGuildVoiceUpdateEvent {
    public GuildVoiceMoveEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nonnull VoiceChannel channelLeft) {
        super(api2, responseNumber, member, channelLeft, member.getVoiceState().getChannel());
    }

    @Override
    @Nonnull
    public VoiceChannel getChannelLeft() {
        return super.getChannelLeft();
    }

    @Override
    @Nonnull
    public VoiceChannel getChannelJoined() {
        return super.getChannelJoined();
    }

    @Override
    @Nonnull
    public VoiceChannel getOldValue() {
        return super.getOldValue();
    }

    @Override
    @Nonnull
    public VoiceChannel getNewValue() {
        return super.getNewValue();
    }
}

