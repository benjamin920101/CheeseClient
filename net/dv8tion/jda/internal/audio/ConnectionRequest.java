/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.audio;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.internal.audio.ConnectionStage;

public class ConnectionRequest {
    protected final long guildId;
    protected long nextAttemptEpoch;
    protected ConnectionStage stage;
    protected long channelId;

    public ConnectionRequest(Guild guild) {
        this.stage = ConnectionStage.DISCONNECT;
        this.guildId = guild.getIdLong();
    }

    public ConnectionRequest(VoiceChannel channel, ConnectionStage stage) {
        this.channelId = channel.getIdLong();
        this.guildId = channel.getGuild().getIdLong();
        this.stage = stage;
        this.nextAttemptEpoch = System.currentTimeMillis();
    }

    public void setStage(ConnectionStage stage) {
        this.stage = stage;
    }

    public void setChannel(VoiceChannel channel) {
        this.channelId = channel.getIdLong();
    }

    public void setNextAttemptEpoch(long epochMillis) {
        this.nextAttemptEpoch = epochMillis;
    }

    public VoiceChannel getChannel(JDA api2) {
        return api2.getVoiceChannelById(this.channelId);
    }

    public long getChannelId() {
        return this.channelId;
    }

    public ConnectionStage getStage() {
        return this.stage;
    }

    public long getNextAttemptEpoch() {
        return this.nextAttemptEpoch;
    }

    public long getGuildIdLong() {
        return this.guildId;
    }

    public String toString() {
        return (Object)((Object)this.stage) + "(" + Long.toUnsignedString(this.guildId) + "#" + Long.toUnsignedString(this.channelId) + ")";
    }
}

