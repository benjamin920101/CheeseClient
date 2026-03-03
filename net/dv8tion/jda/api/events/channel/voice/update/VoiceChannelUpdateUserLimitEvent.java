/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;

public class VoiceChannelUpdateUserLimitEvent
extends GenericVoiceChannelUpdateEvent<Integer> {
    public static final String IDENTIFIER = "userlimit";

    public VoiceChannelUpdateUserLimitEvent(@Nonnull JDA api2, long responseNumber, @Nonnull VoiceChannel channel, int oldUserLimit) {
        super(api2, responseNumber, channel, oldUserLimit, channel.getUserLimit(), IDENTIFIER);
    }

    public int getOldUserLimit() {
        return (Integer)this.getOldValue();
    }

    public int getNewUserLimit() {
        return (Integer)this.getNewValue();
    }
}

