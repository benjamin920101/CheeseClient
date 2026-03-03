/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;

public class VoiceChannelDeleteEvent
extends GenericVoiceChannelEvent {
    public VoiceChannelDeleteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull VoiceChannel channel) {
        super(api2, responseNumber, channel);
    }
}

