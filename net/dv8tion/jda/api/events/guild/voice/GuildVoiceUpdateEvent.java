/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.voice;

import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.UpdateEvent;

public interface GuildVoiceUpdateEvent
extends UpdateEvent<Member, VoiceChannel> {
    public static final String IDENTIFIER = "voice-channel";

    @Nullable
    public VoiceChannel getChannelLeft();

    @Nullable
    public VoiceChannel getChannelJoined();
}

