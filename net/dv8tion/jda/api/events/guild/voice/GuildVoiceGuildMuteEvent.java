/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;

public class GuildVoiceGuildMuteEvent
extends GenericGuildVoiceEvent {
    protected final boolean guildMuted;

    public GuildVoiceGuildMuteEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member);
        this.guildMuted = member.getVoiceState().isGuildMuted();
    }

    public boolean isGuildMuted() {
        return this.guildMuted;
    }
}

