/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class InviteDeleteHandler
extends SocketHandler {
    public InviteDeleteHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long guildId = content.getUnsignedLong("guild_id");
        if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
            return guildId;
        }
        Guild guild = this.getJDA().getGuildById(guildId);
        if (guild == null) {
            EventCache.LOG.debug("Caching INVITE_DELETE for unknown guild {}", (Object)guildId);
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, guildId, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        long channelId = content.getUnsignedLong("channel_id");
        GuildChannel channel = guild.getGuildChannelById(channelId);
        if (channel == null) {
            EventCache.LOG.debug("Caching INVITE_DELETE for unknown channel {} in guild {}", (Object)channelId, (Object)guildId);
            this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        String code = content.getString("code");
        this.getJDA().handleEvent(new GuildInviteDeleteEvent(this.getJDA(), this.responseNumber, code, channel));
        return null;
    }
}

