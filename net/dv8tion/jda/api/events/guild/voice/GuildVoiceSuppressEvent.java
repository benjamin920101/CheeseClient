/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;

public class GuildVoiceSuppressEvent
extends GenericGuildVoiceEvent {
    protected final boolean suppressed;

    public GuildVoiceSuppressEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member);
        this.suppressed = member.getVoiceState().isSuppressed();
    }

    public boolean isSuppressed() {
        return this.suppressed;
    }
}

