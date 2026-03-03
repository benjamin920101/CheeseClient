/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;

public class GuildVoiceStreamEvent
extends GenericGuildVoiceEvent {
    private final boolean stream;

    public GuildVoiceStreamEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, boolean stream) {
        super(api2, responseNumber, member);
        this.stream = stream;
    }

    public boolean isStream() {
        return this.stream;
    }
}

