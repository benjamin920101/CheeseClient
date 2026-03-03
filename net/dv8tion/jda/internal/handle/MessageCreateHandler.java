/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.PrivateChannelImpl;
import net.dv8tion.jda.internal.entities.TextChannelImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class MessageCreateHandler
extends SocketHandler {
    public MessageCreateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        Message message;
        MessageType type = MessageType.fromId(content.getInt("type"));
        if (type == MessageType.UNKNOWN) {
            WebSocketClient.LOG.debug("JDA received a message of unknown type. Type: {}  JSON: {}", (Object)type, (Object)content);
            return null;
        }
        JDAImpl jda = this.getJDA();
        if (!content.isNull("guild_id")) {
            long guildId = content.getLong("guild_id");
            if (jda.getGuildSetupController().isLocked(guildId)) {
                return guildId;
            }
        }
        try {
            message = jda.getEntityBuilder().createMessage(content, true);
        }
        catch (IllegalArgumentException e2) {
            switch (e2.getMessage()) {
                case "MISSING_CHANNEL": {
                    long channelId = content.getLong("channel_id");
                    jda.getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
                    EventCache.LOG.debug("Received a message for a channel that JDA does not currently have cached");
                    return null;
                }
                case "MISSING_USER": {
                    long authorId = content.getObject("author").getLong("id");
                    jda.getEventCache().cache(EventCache.Type.USER, authorId, this.responseNumber, this.allContent, this::handle);
                    EventCache.LOG.debug("Received a message for a user that JDA does not currently have cached");
                    return null;
                }
                case "UNKNOWN_MESSAGE_TYPE": {
                    WebSocketClient.LOG.debug("Ignoring message with unknown type: {}", (Object)content);
                    return null;
                }
            }
            throw e2;
        }
        switch (message.getChannelType()) {
            case TEXT: {
                TextChannelImpl channel = (TextChannelImpl)message.getTextChannel();
                if (jda.getGuildSetupController().isLocked(channel.getGuild().getIdLong())) {
                    return channel.getGuild().getIdLong();
                }
                channel.setLastMessageId(message.getIdLong());
                jda.handleEvent(new GuildMessageReceivedEvent((JDA)jda, this.responseNumber, message));
                break;
            }
            case PRIVATE: {
                PrivateChannelImpl channel = (PrivateChannelImpl)message.getPrivateChannel();
                channel.setLastMessageId(message.getIdLong());
                this.api.usedPrivateChannel(channel.getIdLong());
                jda.handleEvent(new PrivateMessageReceivedEvent(jda, this.responseNumber, message));
                break;
            }
            case GROUP: {
                WebSocketClient.LOG.error("Received a MESSAGE_CREATE for a group channel which should not be possible");
                return null;
            }
            default: {
                WebSocketClient.LOG.warn("Received a MESSAGE_CREATE with a unknown MessageChannel ChannelType. JSON: {}", (Object)content);
                return null;
            }
        }
        jda.handleEvent(new MessageReceivedEvent(jda, this.responseNumber, message));
        return null;
    }
}

