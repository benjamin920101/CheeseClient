/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.io.File;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.WebhookAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.TimeUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.WebhookImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.WebhookActionImpl;
import net.dv8tion.jda.internal.requests.restaction.pagination.ReactionPaginationActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.EncodingUtil;

public class TextChannelImpl
extends AbstractChannelImpl<TextChannel, TextChannelImpl>
implements TextChannel {
    private String topic;
    private long lastMessageId;
    private boolean nsfw;
    private boolean news;
    private int slowmode;

    public TextChannelImpl(long id2, GuildImpl guild) {
        super(id2, guild);
    }

    @Override
    public TextChannelImpl setPosition(int rawPosition) {
        this.getGuild().getTextChannelsView().clearCachedLists();
        return (TextChannelImpl)super.setPosition(rawPosition);
    }

    @Override
    @Nonnull
    public RestAction<List<Webhook>> retrieveWebhooks() {
        this.checkPermission(Permission.MANAGE_WEBHOOKS);
        Route.CompiledRoute route = Route.Channels.GET_WEBHOOKS.compile(this.getId());
        JDAImpl jda = (JDAImpl)this.getJDA();
        return new RestActionImpl<List<Webhook>>((JDA)jda, route, (response, request) -> {
            DataArray array = response.getArray();
            ArrayList<WebhookImpl> webhooks = new ArrayList<WebhookImpl>(array.length());
            EntityBuilder builder = jda.getEntityBuilder();
            for (int i2 = 0; i2 < array.length(); ++i2) {
                try {
                    webhooks.add(builder.createWebhook(array.getObject(i2)));
                    continue;
                }
                catch (UncheckedIOException | NullPointerException e2) {
                    JDAImpl.LOG.error("Error while creating websocket from json", e2);
                }
            }
            return Collections.unmodifiableList(webhooks);
        });
    }

    @Override
    @Nonnull
    public WebhookAction createWebhook(@Nonnull String name) {
        Checks.notBlank(name, "Webhook name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        this.checkPermission(Permission.MANAGE_WEBHOOKS);
        return new WebhookActionImpl(this.getJDA(), this, name);
    }

    @Override
    @Nonnull
    public RestAction<Webhook.WebhookReference> follow(@Nonnull String targetChannelId) {
        Checks.notNull(targetChannelId, "Target Channel ID");
        if (!this.isNews()) {
            throw new IllegalStateException("Can only follow news channels!");
        }
        Route.CompiledRoute route = Route.Channels.FOLLOW_CHANNEL.compile(this.getId());
        DataObject body = DataObject.empty().put("webhook_channel_id", targetChannelId);
        return new RestActionImpl<Webhook.WebhookReference>(this.getJDA(), route, body, (response, request) -> {
            DataObject json = response.getObject();
            return new Webhook.WebhookReference(request.getJDA(), json.getUnsignedLong("webhook_id"), json.getUnsignedLong("channel_id"));
        });
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteMessages(@Nonnull Collection<Message> messages) {
        Checks.notEmpty(messages, "Messages collection");
        return this.deleteMessagesByIds(messages.stream().map(ISnowflake::getId).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteMessagesByIds(@Nonnull Collection<String> messageIds) {
        this.checkPermission(Permission.MESSAGE_MANAGE, "Must have MESSAGE_MANAGE in order to bulk delete messages in this channel regardless of author.");
        if (messageIds.size() < 2 || messageIds.size() > 100) {
            throw new IllegalArgumentException("Must provide at least 2 or at most 100 messages to be deleted.");
        }
        long twoWeeksAgo = TimeUtil.getDiscordTimestamp(System.currentTimeMillis() - 1209600000L);
        for (String id2 : messageIds) {
            Checks.check(MiscUtil.parseSnowflake(id2) > twoWeeksAgo, "Message Id provided was older than 2 weeks. Id: " + id2);
        }
        return this.deleteMessages0(messageIds);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> deleteWebhookById(@Nonnull String id2) {
        Checks.isSnowflake(id2, "Webhook ID");
        this.checkPermission(Permission.MANAGE_WEBHOOKS);
        Route.CompiledRoute route = Route.Webhooks.DELETE_WEBHOOK.compile(id2);
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    public boolean canTalk() {
        return this.canTalk(this.getGuild().getSelfMember());
    }

    @Override
    public boolean canTalk(@Nonnull Member member) {
        if (!this.getGuild().equals(member.getGuild())) {
            throw new IllegalArgumentException("Provided Member is not from the Guild that this TextChannel is part of.");
        }
        return member.hasPermission((GuildChannel)this, Permission.MESSAGE_READ, Permission.MESSAGE_WRITE);
    }

    @Override
    @Nonnull
    public List<CompletableFuture<Void>> purgeMessages(@Nonnull List<? extends Message> messages) {
        if (messages == null || messages.isEmpty()) {
            return Collections.emptyList();
        }
        boolean hasPerms = this.getGuild().getSelfMember().hasPermission((GuildChannel)this, Permission.MESSAGE_MANAGE);
        if (!hasPerms) {
            for (Message message : messages) {
                if (message.getAuthor().equals(this.getJDA().getSelfUser())) continue;
                throw new InsufficientPermissionException(this, Permission.MESSAGE_MANAGE, "Cannot delete messages of other users");
            }
        }
        return TextChannel.super.purgeMessages(messages);
    }

    @Override
    @Nonnull
    public List<CompletableFuture<Void>> purgeMessagesById(long ... messageIds) {
        if (messageIds == null || messageIds.length == 0) {
            return Collections.emptyList();
        }
        if (this.getJDA().getAccountType() != AccountType.BOT || !this.getGuild().getSelfMember().hasPermission((GuildChannel)this, Permission.MESSAGE_MANAGE)) {
            return TextChannel.super.purgeMessagesById(messageIds);
        }
        LinkedList<CompletableFuture<Void>> list = new LinkedList<CompletableFuture<Void>>();
        TreeSet bulk = new TreeSet(Comparator.reverseOrder());
        TreeSet norm = new TreeSet(Comparator.reverseOrder());
        long twoWeeksAgo = TimeUtil.getDiscordTimestamp(System.currentTimeMillis() - 1209600000L + 10000L);
        for (long messageId : messageIds) {
            if (messageId > twoWeeksAgo) {
                bulk.add(messageId);
                continue;
            }
            norm.add(messageId);
        }
        if (!bulk.isEmpty()) {
            ArrayList<String> toDelete = new ArrayList<String>(100);
            while (!bulk.isEmpty()) {
                toDelete.clear();
                for (int i2 = 0; i2 < 100 && !bulk.isEmpty(); ++i2) {
                    toDelete.add(Long.toUnsignedString((Long)bulk.pollLast()));
                }
                if (toDelete.size() == 1) {
                    list.add(this.deleteMessageById((String)toDelete.get(0)).submit());
                    continue;
                }
                if (toDelete.isEmpty()) continue;
                list.add(this.deleteMessages0(toDelete).submit());
            }
        }
        if (!norm.isEmpty()) {
            Object object = norm.iterator();
            while (object.hasNext()) {
                long message = (Long)object.next();
                list.add(this.deleteMessageById(message).submit());
            }
        }
        return list;
    }

    @Override
    public long getLatestMessageIdLong() {
        long messageId = this.lastMessageId;
        if (messageId == 0L) {
            throw new IllegalStateException("No last message id found.");
        }
        return messageId;
    }

    @Override
    public boolean hasLatestMessage() {
        return this.lastMessageId != 0L;
    }

    @Override
    @Nonnull
    public ChannelType getType() {
        return ChannelType.TEXT;
    }

    @Override
    public String getTopic() {
        return this.topic;
    }

    @Override
    public boolean isNSFW() {
        return this.nsfw;
    }

    @Override
    public boolean isNews() {
        return this.news && this.getGuild().getFeatures().contains("NEWS");
    }

    @Override
    public int getSlowmode() {
        return this.slowmode;
    }

    @Override
    @Nonnull
    public List<Member> getMembers() {
        return Collections.unmodifiableList(this.getGuild().getMembersView().stream().filter(m2 -> m2.hasPermission((GuildChannel)this, Permission.MESSAGE_READ)).collect(Collectors.toList()));
    }

    @Override
    public int getPosition() {
        ArrayList<TextChannel> channels = new ArrayList<TextChannel>(this.getGuild().getTextChannels());
        channels.addAll(this.getGuild().getStoreChannels());
        Collections.sort(channels);
        for (int i2 = 0; i2 < channels.size(); ++i2) {
            if (!this.equals(channels.get(i2))) continue;
            return i2;
        }
        throw new IllegalStateException("Somehow when determining position we never found the TextChannel in the Guild's channels? wtf?");
    }

    @Override
    @Nonnull
    public ChannelAction<TextChannel> createCopy(@Nonnull Guild guild) {
        Checks.notNull(guild, "Guild");
        ChannelAction<TextChannel> action = guild.createTextChannel(this.name).setNSFW(this.nsfw).setTopic(this.topic).setSlowmode(this.slowmode);
        if (guild.equals(this.getGuild())) {
            Category parent = this.getParent();
            if (parent != null) {
                action.setParent(parent);
            }
            for (PermissionOverride o2 : this.overrides.valueCollection()) {
                if (o2.isMemberOverride()) {
                    action.addMemberPermissionOverride(o2.getIdLong(), o2.getAllowedRaw(), o2.getDeniedRaw());
                    continue;
                }
                action.addRolePermissionOverride(o2.getIdLong(), o2.getAllowedRaw(), o2.getDeniedRaw());
            }
        }
        return action;
    }

    @Override
    @Nonnull
    public MessageAction sendMessage(@Nonnull CharSequence text) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        return TextChannel.super.sendMessage(text);
    }

    @Override
    @Nonnull
    public MessageAction sendMessage(@Nonnull MessageEmbed embed) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        this.checkPermission(Permission.MESSAGE_EMBED_LINKS);
        return TextChannel.super.sendMessage(embed);
    }

    @Override
    @Nonnull
    public MessageAction sendMessage(@Nonnull Message msg) {
        Checks.notNull(msg, "Message");
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        if (msg.getContentRaw().isEmpty() && !msg.getEmbeds().isEmpty()) {
            this.checkPermission(Permission.MESSAGE_EMBED_LINKS);
        }
        return TextChannel.super.sendMessage(msg);
    }

    @Override
    @Nonnull
    public MessageAction sendFile(@Nonnull File file, @Nonnull String fileName, AttachmentOption ... options) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        this.checkPermission(Permission.MESSAGE_ATTACH_FILES);
        long maxSize = this.getGuild().getMaxFileSize();
        Checks.check(file == null || file.length() <= maxSize, "File may not exceed the maximum file length of %d bytes!", (Object)maxSize);
        return TextChannel.super.sendFile(file, fileName, options);
    }

    @Override
    @Nonnull
    public MessageAction sendFile(@Nonnull InputStream data, @Nonnull String fileName, AttachmentOption ... options) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        this.checkPermission(Permission.MESSAGE_ATTACH_FILES);
        return TextChannel.super.sendFile(data, fileName, options);
    }

    @Override
    @Nonnull
    public MessageAction sendFile(@Nonnull byte[] data, @Nonnull String fileName, AttachmentOption ... options) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        this.checkPermission(Permission.MESSAGE_ATTACH_FILES);
        long maxSize = this.getGuild().getMaxFileSize();
        Checks.check(data == null || (long)data.length <= maxSize, "File is too big! Max file-size is %d bytes", (Object)maxSize);
        return TextChannel.super.sendFile(data, fileName, options);
    }

    @Override
    @Nonnull
    public RestAction<Message> retrieveMessageById(@Nonnull String messageId) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_HISTORY);
        return TextChannel.super.retrieveMessageById(messageId);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> deleteMessageById(@Nonnull String messageId) {
        Checks.isSnowflake(messageId, "Message ID");
        this.checkPermission(Permission.MESSAGE_READ);
        return TextChannel.super.deleteMessageById(messageId);
    }

    @Override
    @Nonnull
    public RestAction<Void> pinMessageById(@Nonnull String messageId) {
        this.checkPermission(Permission.MESSAGE_READ, "You cannot pin a message in a channel you can't access. (MESSAGE_READ)");
        this.checkPermission(Permission.MESSAGE_MANAGE, "You need MESSAGE_MANAGE to pin or unpin messages.");
        return TextChannel.super.pinMessageById(messageId);
    }

    @Override
    @Nonnull
    public RestAction<Void> unpinMessageById(@Nonnull String messageId) {
        this.checkPermission(Permission.MESSAGE_READ, "You cannot unpin a message in a channel you can't access. (MESSAGE_READ)");
        this.checkPermission(Permission.MESSAGE_MANAGE, "You need MESSAGE_MANAGE to pin or unpin messages.");
        return TextChannel.super.unpinMessageById(messageId);
    }

    @Override
    @Nonnull
    public RestAction<List<Message>> retrievePinnedMessages() {
        this.checkPermission(Permission.MESSAGE_READ, "Cannot get the pinned message of a channel without MESSAGE_READ access.");
        return TextChannel.super.retrievePinnedMessages();
    }

    @Override
    @Nonnull
    public RestAction<Void> addReactionById(@Nonnull String messageId, @Nonnull String unicode) {
        this.checkPermission(Permission.MESSAGE_HISTORY);
        return TextChannel.super.addReactionById(messageId, unicode);
    }

    @Override
    @Nonnull
    public RestAction<Void> addReactionById(@Nonnull String messageId, @Nonnull Emote emote) {
        this.checkPermission(Permission.MESSAGE_HISTORY);
        return TextChannel.super.addReactionById(messageId, emote);
    }

    @Override
    @Nonnull
    public RestAction<Void> clearReactionsById(@Nonnull String messageId) {
        Checks.isSnowflake(messageId, "Message ID");
        this.checkPermission(Permission.MESSAGE_MANAGE);
        Route.CompiledRoute route = Route.Messages.REMOVE_ALL_REACTIONS.compile(this.getId(), messageId);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public RestAction<Void> clearReactionsById(@Nonnull String messageId, @Nonnull String unicode) {
        Checks.notNull(messageId, "Message ID");
        Checks.notNull(unicode, "Emote Name");
        this.checkPermission(Permission.MESSAGE_MANAGE);
        String code = EncodingUtil.encodeReaction(unicode);
        Route.CompiledRoute route = Route.Messages.CLEAR_EMOTE_REACTIONS.compile(this.getId(), messageId, code);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public RestAction<Void> clearReactionsById(@Nonnull String messageId, @Nonnull Emote emote) {
        Checks.notNull(emote, "Emote");
        return this.clearReactionsById(messageId, emote.getName() + ":" + emote.getId());
    }

    @Nonnull
    public RestActionImpl<Void> removeReactionById(@Nonnull String messageId, @Nonnull String unicode, @Nonnull User user) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(unicode, "Provided Unicode");
        unicode = unicode.trim();
        Checks.notEmpty(unicode, "Provided Unicode");
        Checks.notNull(user, "User");
        if (!this.getJDA().getSelfUser().equals(user)) {
            this.checkPermission(Permission.MESSAGE_MANAGE);
        }
        String encoded = EncodingUtil.encodeReaction(unicode);
        String targetUser = user.equals(this.getJDA().getSelfUser()) ? "@me" : user.getId();
        Route.CompiledRoute route = Route.Messages.REMOVE_REACTION.compile(this.getId(), messageId, encoded, targetUser);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public ReactionPaginationAction retrieveReactionUsersById(@Nonnull String messageId, @Nonnull String unicode) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notEmpty(unicode, "Emoji");
        Checks.noWhitespace(unicode, "Emoji");
        this.checkPermission(Permission.MESSAGE_HISTORY);
        return new ReactionPaginationActionImpl(this, messageId, EncodingUtil.encodeUTF8(unicode));
    }

    @Override
    @Nonnull
    public ReactionPaginationAction retrieveReactionUsersById(@Nonnull String messageId, @Nonnull Emote emote) {
        Checks.isSnowflake(messageId, "Message ID");
        Checks.notNull(emote, "Emote");
        this.checkPermission(Permission.MESSAGE_HISTORY);
        return new ReactionPaginationActionImpl(this, messageId, String.format("%s:%s", emote, emote.getId()));
    }

    @Override
    @Nonnull
    public MessageAction editMessageById(@Nonnull String messageId, @Nonnull CharSequence newContent) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        return TextChannel.super.editMessageById(messageId, newContent);
    }

    @Override
    @Nonnull
    public MessageAction editMessageById(@Nonnull String messageId, @Nonnull MessageEmbed newEmbed) {
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        this.checkPermission(Permission.MESSAGE_EMBED_LINKS);
        return TextChannel.super.editMessageById(messageId, newEmbed);
    }

    @Override
    @Nonnull
    public MessageAction editMessageById(@Nonnull String id2, @Nonnull Message newContent) {
        Checks.notNull(newContent, "Message");
        this.checkPermission(Permission.MESSAGE_READ);
        this.checkPermission(Permission.MESSAGE_WRITE);
        if (newContent.getContentRaw().isEmpty() && !newContent.getEmbeds().isEmpty()) {
            this.checkPermission(Permission.MESSAGE_EMBED_LINKS);
        }
        return TextChannel.super.editMessageById(id2, newContent);
    }

    public String toString() {
        return "TC:" + this.getName() + '(' + this.id + ')';
    }

    public TextChannelImpl setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public TextChannelImpl setLastMessageId(long id2) {
        this.lastMessageId = id2;
        return this;
    }

    public TextChannelImpl setNSFW(boolean nsfw) {
        this.nsfw = nsfw;
        return this;
    }

    public TextChannelImpl setSlowmode(int slowmode) {
        this.slowmode = slowmode;
        return this;
    }

    public TextChannelImpl setNews(boolean news) {
        this.news = news;
        return this;
    }

    private RestActionImpl<Void> deleteMessages0(Collection<String> messageIds) {
        DataObject body = DataObject.empty().put("messages", messageIds);
        Route.CompiledRoute route = Route.Messages.DELETE_MESSAGES.compile(this.getId());
        return new RestActionImpl<Void>(this.getJDA(), route, body);
    }
}

