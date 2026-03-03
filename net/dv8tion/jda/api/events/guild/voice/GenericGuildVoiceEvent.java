/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public abstract class GenericGuildVoiceEvent
extends GenericGuildEvent {
    protected final Member member;

    public GenericGuildVoiceEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member) {
        super(api2, responseNumber, member.getGuild());
        this.member = member;
    }

    @Nonnull
    public Member getMember() {
        return this.member;
    }

    @Nonnull
    public GuildVoiceState getVoiceState() {
        return this.member.getVoiceState();
    }
}

