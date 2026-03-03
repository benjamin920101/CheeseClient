/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class GuildVoiceStateImpl
implements GuildVoiceState {
    private final JDA api;
    private Guild guild;
    private Member member;
    private VoiceChannel connectedChannel;
    private String sessionId;
    private boolean selfMuted = false;
    private boolean selfDeafened = false;
    private boolean guildMuted = false;
    private boolean guildDeafened = false;
    private boolean suppressed = false;
    private boolean stream = false;

    public GuildVoiceStateImpl(Member member) {
        this.api = member.getJDA();
        this.guild = member.getGuild();
        this.member = member;
    }

    @Override
    public boolean isSelfMuted() {
        return this.selfMuted;
    }

    @Override
    public boolean isSelfDeafened() {
        return this.selfDeafened;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    public String getSessionId() {
        return this.sessionId;
    }

    @Override
    public boolean isMuted() {
        return this.isSelfMuted() || this.isGuildMuted();
    }

    @Override
    public boolean isDeafened() {
        return this.isSelfDeafened() || this.isGuildDeafened();
    }

    @Override
    public boolean isGuildMuted() {
        return this.guildMuted;
    }

    @Override
    public boolean isGuildDeafened() {
        return this.guildDeafened;
    }

    @Override
    public boolean isSuppressed() {
        return this.suppressed;
    }

    @Override
    public boolean isStream() {
        return this.stream;
    }

    @Override
    public VoiceChannel getChannel() {
        return this.connectedChannel;
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        Guild realGuild = this.api.getGuildById(this.guild.getIdLong());
        if (realGuild != null) {
            this.guild = realGuild;
        }
        return this.guild;
    }

    @Override
    @Nonnull
    public Member getMember() {
        Member realMember = this.getGuild().getMemberById(this.member.getIdLong());
        if (realMember != null) {
            this.member = realMember;
        }
        return this.member;
    }

    @Override
    public boolean inVoiceChannel() {
        return this.getChannel() != null;
    }

    public int hashCode() {
        return this.getMember().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GuildVoiceState)) {
            return false;
        }
        GuildVoiceState oStatus = (GuildVoiceState)obj;
        return this.getMember().equals(oStatus.getMember());
    }

    public String toString() {
        return "VS:" + this.getGuild().getName() + ':' + this.getMember().getEffectiveName();
    }

    public GuildVoiceStateImpl setConnectedChannel(VoiceChannel connectedChannel) {
        this.connectedChannel = connectedChannel;
        return this;
    }

    public GuildVoiceStateImpl setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public GuildVoiceStateImpl setSelfMuted(boolean selfMuted) {
        this.selfMuted = selfMuted;
        return this;
    }

    public GuildVoiceStateImpl setSelfDeafened(boolean selfDeafened) {
        this.selfDeafened = selfDeafened;
        return this;
    }

    public GuildVoiceStateImpl setGuildMuted(boolean guildMuted) {
        this.guildMuted = guildMuted;
        return this;
    }

    public GuildVoiceStateImpl setGuildDeafened(boolean guildDeafened) {
        this.guildDeafened = guildDeafened;
        return this;
    }

    public GuildVoiceStateImpl setSuppressed(boolean suppressed) {
        this.suppressed = suppressed;
        return this;
    }

    public GuildVoiceStateImpl setStream(boolean stream) {
        this.stream = stream;
        return this;
    }
}

