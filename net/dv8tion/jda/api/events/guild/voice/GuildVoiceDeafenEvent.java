/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;

public class GuildVoiceDeafenEvent
extends GenericGuildVoiceEvent {
    protected final boolean deafened;

    public GuildVoiceDeafenEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member);
        this.deafened = member.getVoiceState().isDeafened();
    }

    public boolean isDeafened() {
        return this.deafened;
    }
}

