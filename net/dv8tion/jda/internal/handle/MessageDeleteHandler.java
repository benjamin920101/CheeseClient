/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.PrivateChannelImpl;
import net.dv8tion.jda.internal.entities.TextChannelImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class MessageDeleteHandler
extends SocketHandler {
    public MessageDeleteHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        long messageId = content.getLong("id");
        long channelId = content.getLong("channel_id");
        MessageChannel channel = this.getJDA().getTextChannelById(channelId);
        if (channel == null) {
            channel = this.getJDA().getPrivateChannelById(channelId);
        }
        if (channel == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Got message delete for a channel/group that is not yet cached. ChannelId: {}", (Object)channelId);
            return null;
        }
        if (channel instanceof TextChannel) {
            TextChannelImpl tChan = (TextChannelImpl)channel;
            if (this.getJDA().getGuildSetupController().isLocked(tChan.getGuild().getIdLong())) {
                return tChan.getGuild().getIdLong();
            }
            if (tChan.hasLatestMessage() && messageId == channel.getLatestMessageIdLong()) {
                tChan.setLastMessageId(0L);
            }
            this.getJDA().handleEvent(new GuildMessageDeleteEvent(this.getJDA(), this.responseNumber, messageId, tChan));
        } else {
            PrivateChannelImpl pChan = (PrivateChannelImpl)channel;
            if (channel.hasLatestMessage() && messageId == channel.getLatestMessageIdLong()) {
                pChan.setLastMessageId(0L);
            }
            this.getJDA().handleEvent(new PrivateMessageDeleteEvent(this.getJDA(), this.responseNumber, messageId, pChan));
        }
        this.getJDA().handleEvent(new MessageDeleteEvent(this.getJDA(), this.responseNumber, messageId, channel));
        return null;
    }
}

