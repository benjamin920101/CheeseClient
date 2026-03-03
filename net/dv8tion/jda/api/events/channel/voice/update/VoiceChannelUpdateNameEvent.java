/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;

public class VoiceChannelUpdateNameEvent
extends GenericVoiceChannelUpdateEvent<String> {
    public static final String IDENTIFIER = "name";

    public VoiceChannelUpdateNameEvent(@Nonnull JDA api2, long responseNumber, @Nonnull VoiceChannel channel, @Nonnull String oldName) {
        super(api2, responseNumber, channel, oldName, channel.getName(), IDENTIFIER);
    }

    @Nonnull
    public String getOldName() {
        return this.getOldValue();
    }

    @Nonnull
    public String getNewName() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public String getOldValue() {
        return (String)super.getOldValue();
    }

    @Override
    @Nonnull
    public String getNewValue() {
        return (String)super.getNewValue();
    }
}

