/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;

public class VoiceChannelUpdatePositionEvent
extends GenericVoiceChannelUpdateEvent<Integer> {
    public static final String IDENTIFIER = "position";

    public VoiceChannelUpdatePositionEvent(@Nonnull JDA api2, long responseNumber, @Nonnull VoiceChannel channel, int oldPosition) {
        super(api2, responseNumber, channel, oldPosition, channel.getPositionRaw(), IDENTIFIER);
    }

    public int getOldPosition() {
        return (Integer)this.getOldValue();
    }

    public int getNewPosition() {
        return (Integer)this.getNewValue();
    }
}

