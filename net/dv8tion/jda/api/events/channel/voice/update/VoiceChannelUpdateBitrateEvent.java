/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;

public class VoiceChannelUpdateBitrateEvent
extends GenericVoiceChannelUpdateEvent<Integer> {
    public static final String IDENTIFIER = "bitrate";

    public VoiceChannelUpdateBitrateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull VoiceChannel channel, int oldBitrate) {
        super(api2, responseNumber, channel, oldBitrate, channel.getBitrate(), IDENTIFIER);
    }

    public int getOldBitrate() {
        return (Integer)this.getOldValue();
    }

    public int getNewBitrate() {
        return (Integer)this.getNewValue();
    }
}

