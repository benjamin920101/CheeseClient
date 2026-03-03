/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.util.Objects;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.GuildVoiceStateImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.entities.VoiceChannelImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;

public class VoiceStateUpdateHandler
extends SocketHandler {
    public VoiceStateUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        Long guildId;
        Long l2 = guildId = content.isNull("guild_id") ? null : Long.valueOf(content.getLong("guild_id"));
        if (guildId == null) {
            return null;
        }
        if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
            return guildId;
        }
        this.handleGuildVoiceState(content);
        return null;
    }

    private void handleGuildVoiceState(DataObject content) {
        VoiceChannelImpl channel;
        long userId = content.getLong("user_id");
        long guildId = content.getLong("guild_id");
        Long channelId = !content.isNull("channel_id") ? Long.valueOf(content.getLong("channel_id")) : null;
        String sessionId = !content.isNull("session_id") ? content.getString("session_id") : null;
        boolean selfMuted = content.getBoolean("self_mute");
        boolean selfDeafened = content.getBoolean("self_deaf");
        boolean guildMuted = content.getBoolean("mute");
        boolean guildDeafened = content.getBoolean("deaf");
        boolean suppressed = content.getBoolean("suppress");
        boolean stream = content.getBoolean("self_stream");
        Guild guild = this.getJDA().getGuildById(guildId);
        if (guild == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, guildId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received a VOICE_STATE_UPDATE for a Guild that has yet to be cached. JSON: {}", (Object)content);
            return;
        }
        VoiceChannelImpl voiceChannelImpl = channel = channelId != null ? (VoiceChannelImpl)guild.getVoiceChannelById(channelId) : null;
        if (channel == null && channelId != null) {
            this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received VOICE_STATE_UPDATE for a VoiceChannel that has yet to be cached. JSON: {}", (Object)content);
            return;
        }
        DataObject memberJson = content.getObject("member");
        MemberImpl member = this.getJDA().getEntityBuilder().createMember((GuildImpl)guild, memberJson);
        if (member == null) {
            return;
        }
        GuildVoiceStateImpl vState = (GuildVoiceStateImpl)member.getVoiceState();
        if (vState == null) {
            return;
        }
        vState.setSessionId(sessionId);
        VoiceDispatchInterceptor voiceInterceptor = this.getJDA().getVoiceInterceptor();
        boolean isSelf = guild.getSelfMember().equals(member);
        boolean wasMute = vState.isMuted();
        boolean wasDeaf = vState.isDeafened();
        if (selfMuted != vState.isSelfMuted()) {
            vState.setSelfMuted(selfMuted);
            this.getJDA().getEntityBuilder().updateMemberCache(member);
            this.getJDA().handleEvent(new GuildVoiceSelfMuteEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (selfDeafened != vState.isSelfDeafened()) {
            vState.setSelfDeafened(selfDeafened);
            this.getJDA().getEntityBuilder().updateMemberCache(member);
            this.getJDA().handleEvent(new GuildVoiceSelfDeafenEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (guildMuted != vState.isGuildMuted()) {
            vState.setGuildMuted(guildMuted);
            this.getJDA().getEntityBuilder().updateMemberCache(member);
            this.getJDA().handleEvent(new GuildVoiceGuildMuteEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (guildDeafened != vState.isGuildDeafened()) {
            vState.setGuildDeafened(guildDeafened);
            this.getJDA().getEntityBuilder().updateMemberCache(member);
            this.getJDA().handleEvent(new GuildVoiceGuildDeafenEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (suppressed != vState.isSuppressed()) {
            vState.setSuppressed(suppressed);
            this.getJDA().getEntityBuilder().updateMemberCache(member);
            this.getJDA().handleEvent(new GuildVoiceSuppressEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (stream != vState.isStream()) {
            vState.setStream(stream);
            this.getJDA().getEntityBuilder().updateMemberCache(member);
            this.getJDA().handleEvent(new GuildVoiceStreamEvent(this.getJDA(), this.responseNumber, member, stream));
        }
        if (wasMute != vState.isMuted()) {
            this.getJDA().handleEvent(new GuildVoiceMuteEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (wasDeaf != vState.isDeafened()) {
            this.getJDA().handleEvent(new GuildVoiceDeafenEvent((JDA)this.getJDA(), this.responseNumber, member));
        }
        if (!Objects.equals(channel, vState.getChannel())) {
            VoiceChannelImpl oldChannel = (VoiceChannelImpl)vState.getChannel();
            vState.setConnectedChannel(channel);
            if (oldChannel == null) {
                channel.getConnectedMembersMap().put(userId, member);
                this.getJDA().getEntityBuilder().updateMemberCache(member);
                this.getJDA().handleEvent(new GuildVoiceJoinEvent((JDA)this.getJDA(), this.responseNumber, member));
            } else if (channel == null) {
                oldChannel.getConnectedMembersMap().remove(userId);
                if (isSelf) {
                    this.getJDA().getDirectAudioController().update(guild, null);
                }
                this.getJDA().getEntityBuilder().updateMemberCache(member);
                this.getJDA().handleEvent(new GuildVoiceLeaveEvent(this.getJDA(), this.responseNumber, member, oldChannel));
            } else {
                AudioManagerImpl mng = (AudioManagerImpl)this.getJDA().getAudioManagersView().get(guildId);
                if (isSelf && mng != null && voiceInterceptor == null) {
                    if (mng.isConnected()) {
                        mng.setConnectedChannel(channel);
                    }
                    if (mng.isConnected()) {
                        this.getJDA().getDirectAudioController().update(guild, channel);
                    }
                }
                channel.getConnectedMembersMap().put(userId, member);
                oldChannel.getConnectedMembersMap().remove(userId);
                this.getJDA().getEntityBuilder().updateMemberCache(member);
                this.getJDA().handleEvent(new GuildVoiceMoveEvent(this.getJDA(), this.responseNumber, member, oldChannel));
            }
        }
        if (isSelf && voiceInterceptor != null && voiceInterceptor.onVoiceStateUpdate(new VoiceDispatchInterceptor.VoiceStateUpdate(channel, vState, this.allContent))) {
            this.getJDA().getDirectAudioController().update(guild, channel);
        }
    }
}

