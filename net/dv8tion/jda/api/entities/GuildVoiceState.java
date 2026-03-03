/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public interface GuildVoiceState {
    @Nonnull
    public JDA getJDA();

    public boolean isSelfMuted();

    public boolean isSelfDeafened();

    public boolean isMuted();

    public boolean isDeafened();

    public boolean isGuildMuted();

    public boolean isGuildDeafened();

    public boolean isSuppressed();

    public boolean isStream();

    @Nullable
    public VoiceChannel getChannel();

    @Nonnull
    public Guild getGuild();

    @Nonnull
    public Member getMember();

    public boolean inVoiceChannel();

    @Nullable
    public String getSessionId();
}

