/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.GuildVoiceStateImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.entities.MemberPresenceImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.entities.VoiceChannelImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;

public class GuildMemberRemoveHandler
extends SocketHandler {
    public GuildMemberRemoveHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long id2 = content.getLong("guild_id");
        boolean setup = this.getJDA().getGuildSetupController().onRemoveMember(id2, content);
        if (setup) {
            return null;
        }
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildsView().get(id2);
        if (guild == null) {
            return null;
        }
        long userId = content.getObject("user").getUnsignedLong("id");
        if (userId == this.getJDA().getSelfUser().getIdLong()) {
            return null;
        }
        guild.onMemberRemove();
        CacheView.SimpleCacheView<MemberPresenceImpl> presences = guild.getPresenceView();
        if (presences != null) {
            presences.remove(userId);
        }
        UserImpl user = this.api.getEntityBuilder().createUser(content.getObject("user"));
        MemberImpl member = (MemberImpl)guild.getMembersView().remove(userId);
        if (member == null) {
            guild.getVoiceChannelsView().forEachUnordered(channel -> {
                VoiceChannelImpl impl = (VoiceChannelImpl)channel;
                Member connected = impl.getConnectedMembersMap().remove(userId);
                if (connected != null) {
                    this.getJDA().handleEvent(new GuildVoiceLeaveEvent(this.getJDA(), this.responseNumber, connected, (VoiceChannel)channel));
                }
            });
            this.getJDA().handleEvent(new GuildMemberRemoveEvent(this.getJDA(), this.responseNumber, guild, user, null));
            return null;
        }
        GuildVoiceStateImpl voiceState = (GuildVoiceStateImpl)member.getVoiceState();
        if (voiceState != null && voiceState.inVoiceChannel()) {
            VoiceChannel channel2 = voiceState.getChannel();
            voiceState.setConnectedChannel(null);
            ((VoiceChannelImpl)channel2).getConnectedMembersMap().remove(userId);
            this.getJDA().handleEvent(new GuildVoiceLeaveEvent(this.getJDA(), this.responseNumber, member, channel2));
        }
        SnowflakeCacheViewImpl<User> userView = this.getJDA().getUsersView();
        try (UnlockHook hook = userView.writeLock();){
            if (userId != this.getJDA().getSelfUser().getIdLong() && this.getJDA().getGuildsView().stream().noneMatch(g2 -> g2.getMemberById(userId) != null)) {
                userView.remove(userId);
                this.getJDA().getEventCache().clear(EventCache.Type.USER, userId);
            }
        }
        this.getJDA().handleEvent(new GuildMemberLeaveEvent((JDA)this.getJDA(), this.responseNumber, member));
        this.getJDA().handleEvent(new GuildMemberRemoveEvent(this.getJDA(), this.responseNumber, guild, user, member));
        return null;
    }
}

