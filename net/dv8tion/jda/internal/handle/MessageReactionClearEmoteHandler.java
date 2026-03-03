/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class MessageReactionClearEmoteHandler
extends SocketHandler {
    public MessageReactionClearEmoteHandler(JDAImpl api2) {
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
            EventCache.LOG.debug("Caching MESSAGE_REACTION_REMOVE_EMOJI event for unknown guild {}", (Object)guildId);
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, guildId, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        long channelId = content.getUnsignedLong("channel_id");
        TextChannel channel = guild.getTextChannelById(channelId);
        if (channel == null) {
            EventCache.LOG.debug("Caching MESSAGE_REACTION_REMOVE_EMOJI event for unknown channel {}", (Object)channelId);
            this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        long messageId = content.getUnsignedInt("message_id");
        DataObject emoji = content.getObject("emoji");
        MessageReaction.ReactionEmote reactionEmote = null;
        if (emoji.isNull("id")) {
            reactionEmote = MessageReaction.ReactionEmote.fromUnicode(emoji.getString("name"), this.getJDA());
        } else {
            long emoteId = emoji.getUnsignedLong("emoji");
            Emote emote = this.getJDA().getEmoteById(emoteId);
            if (emote == null) {
                emote = new EmoteImpl(emoteId, this.getJDA()).setAnimated(emoji.getBoolean("animated")).setName(emoji.getString("name", ""));
            }
            reactionEmote = MessageReaction.ReactionEmote.fromCustom(emote);
        }
        MessageReaction reaction = new MessageReaction(channel, reactionEmote, messageId, false, 0);
        this.getJDA().handleEvent(new GuildMessageReactionRemoveEmoteEvent(this.getJDA(), this.responseNumber, channel, reaction, messageId));
        this.getJDA().handleEvent(new MessageReactionRemoveEmoteEvent(this.getJDA(), this.responseNumber, messageId, channel, reaction));
        return null;
    }
}

