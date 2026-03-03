/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;

public class GenericGuildVoiceUpdateEvent
extends GenericGuildVoiceEvent
implements GuildVoiceUpdateEvent {
    protected final VoiceChannel joined;
    protected final VoiceChannel left;

    public GenericGuildVoiceUpdateEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Member member, @Nullable VoiceChannel left, @Nullable VoiceChannel joined) {
        super(api2, responseNumber, member);
        this.left = left;
        this.joined = joined;
    }

    @Override
    @Nullable
    public VoiceChannel getChannelLeft() {
        return this.left;
    }

    @Override
    @Nullable
    public VoiceChannel getChannelJoined() {
        return this.joined;
    }

    @Override
    @Nonnull
    public String getPropertyIdentifier() {
        return "voice-channel";
    }

    @Override
    @Nonnull
    public Member getEntity() {
        return this.getMember();
    }

    @Override
    @Nullable
    public VoiceChannel getOldValue() {
        return this.getChannelLeft();
    }

    @Override
    @Nullable
    public VoiceChannel getNewValue() {
        return this.getChannelJoined();
    }

    public String toString() {
        return "MemberVoiceUpdate[" + this.getPropertyIdentifier() + "](" + this.getOldValue() + "->" + this.getNewValue() + ')';
    }
}

