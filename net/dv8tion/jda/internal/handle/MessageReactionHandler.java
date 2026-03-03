/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;
import net.dv8tion.jda.internal.utils.JDALogger;

public class MessageReactionHandler
extends SocketHandler {
    private final boolean add;

    public MessageReactionHandler(JDAImpl api2, boolean add2) {
        super(api2);
        this.add = add2;
    }

    @Override
    protected Long handleInternally(DataObject content) {
        MessageReaction.ReactionEmote rEmote;
        User user;
        if (!content.isNull("guild_id")) {
            long guildId = content.getLong("guild_id");
            if (this.getJDA().getGuildSetupController().isLocked(guildId)) {
                return guildId;
            }
        }
        DataObject emoji = content.getObject("emoji");
        long userId = content.getLong("user_id");
        long messageId = content.getLong("message_id");
        long channelId = content.getLong("channel_id");
        Long emojiId = emoji.isNull("id") ? null : Long.valueOf(emoji.getLong("id"));
        String emojiName = emoji.getString("name", null);
        boolean emojiAnimated = emoji.getBoolean("animated");
        if (emojiId == null && emojiName == null) {
            WebSocketClient.LOG.debug("Received a reaction {} with no name nor id. json: {}", JDALogger.getLazyString(() -> this.add ? "add" : "remove"), (Object)content);
            return null;
        }
        Guild guild = this.getJDA().getGuildById(content.getUnsignedLong("guild_id", 0L));
        MemberImpl member = null;
        if (guild != null) {
            member = (MemberImpl)guild.getMemberById(userId);
            Optional<DataObject> memberJson = content.optObject("member");
            if (memberJson.isPresent()) {
                DataObject json = memberJson.get();
                if (member == null || !member.hasTimeJoined()) {
                    member = this.getJDA().getEntityBuilder().createMember((GuildImpl)guild, json);
                } else {
                    List<Role> roles = json.getArray("roles").stream(DataArray::getUnsignedLong).map(guild::getRoleById).filter(Objects::nonNull).collect(Collectors.toList());
                    this.getJDA().getEntityBuilder().updateMember((GuildImpl)guild, member, json, roles);
                }
                this.getJDA().getEntityBuilder().updateMemberCache(member);
            }
            if (member == null && this.add && guild.isLoaded()) {
                WebSocketClient.LOG.debug("Dropping reaction event for unknown member {}", (Object)content);
                return null;
            }
        }
        if ((user = this.getJDA().getUserById(userId)) == null && member != null) {
            user = member.getUser();
        }
        if (user == null && this.add && guild != null) {
            this.getJDA().getEventCache().cache(EventCache.Type.USER, userId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received a reaction for a user that JDA does not currently have cached. UserID: {} ChannelId: {} MessageId: {}", userId, channelId, messageId);
            return null;
        }
        MessageChannel channel = this.getJDA().getTextChannelById(channelId);
        if (channel == null) {
            channel = this.getJDA().getPrivateChannelById(channelId);
        }
        if (channel == null) {
            this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
            EventCache.LOG.debug("Received a reaction for a channel that JDA does not currently have cached");
            return null;
        }
        if (emojiId != null) {
            Emote emote = this.getJDA().getEmoteById(emojiId);
            if (emote == null) {
                if (emojiName != null) {
                    emote = new EmoteImpl((long)emojiId, this.getJDA()).setAnimated(emojiAnimated).setName(emojiName);
                } else {
                    WebSocketClient.LOG.debug("Received a reaction {} with a null name. json: {}", JDALogger.getLazyString(() -> this.add ? "add" : "remove"), (Object)content);
                    return null;
                }
            }
            rEmote = MessageReaction.ReactionEmote.fromCustom(emote);
        } else {
            rEmote = MessageReaction.ReactionEmote.fromUnicode(emojiName, this.getJDA());
        }
        MessageReaction reaction = new MessageReaction(channel, rEmote, messageId, userId == this.getJDA().getSelfUser().getIdLong(), -1);
        if (this.add) {
            this.onAdd(reaction, user, member, userId);
        } else {
            this.onRemove(reaction, user, member, userId);
        }
        return null;
    }

    private void onAdd(MessageReaction reaction, User user, Member member, long userId) {
        JDAImpl jda = this.getJDA();
        switch (reaction.getChannelType()) {
            case TEXT: {
                jda.handleEvent(new GuildMessageReactionAddEvent((JDA)jda, this.responseNumber, Objects.requireNonNull(member), reaction));
                break;
            }
            case PRIVATE: {
                jda.usedPrivateChannel(reaction.getChannel().getIdLong());
                jda.handleEvent(new PrivateMessageReactionAddEvent((JDA)jda, this.responseNumber, reaction, userId));
                break;
            }
            case GROUP: {
                WebSocketClient.LOG.debug("Received a reaction add for a group which should not be possible");
                return;
            }
        }
        jda.handleEvent(new MessageReactionAddEvent(jda, this.responseNumber, user, member, reaction, userId));
    }

    private void onRemove(MessageReaction reaction, User user, Member member, long userId) {
        JDAImpl jda = this.getJDA();
        switch (reaction.getChannelType()) {
            case TEXT: {
                jda.handleEvent(new GuildMessageReactionRemoveEvent(jda, this.responseNumber, member, reaction, userId));
                break;
            }
            case PRIVATE: {
                jda.usedPrivateChannel(reaction.getChannel().getIdLong());
                jda.handleEvent(new PrivateMessageReactionRemoveEvent((JDA)jda, this.responseNumber, reaction, userId));
                break;
            }
            case GROUP: {
                WebSocketClient.LOG.debug("Received a reaction remove for a group which should not be possible");
                return;
            }
        }
        jda.handleEvent(new MessageReactionRemoveEvent(jda, this.responseNumber, user, member, reaction, userId));
    }
}

