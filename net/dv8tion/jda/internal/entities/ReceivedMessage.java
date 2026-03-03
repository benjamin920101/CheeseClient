/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.set.TLongSet;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Formatter;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageSticker;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.MarkdownSanitizer;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractMessage;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.bag.HashBag;

public class ReceivedMessage
extends AbstractMessage {
    private final Object mutex = new Object();
    protected final JDAImpl api;
    protected final long id;
    protected final MessageType type;
    protected final MessageChannel channel;
    protected final Message referencedMessage;
    protected final boolean fromWebhook;
    protected final boolean mentionsEveryone;
    protected final boolean pinned;
    protected final User author;
    protected final Member member;
    protected final MessageActivity activity;
    protected final OffsetDateTime editedTime;
    protected final List<MessageReaction> reactions;
    protected final List<Message.Attachment> attachments;
    protected final List<MessageEmbed> embeds;
    protected final List<MessageSticker> stickers;
    protected final List<ActionRow> components;
    protected final TLongSet mentionedUsers;
    protected final TLongSet mentionedRoles;
    protected final int flags;
    protected String altContent = null;
    protected String strippedContent = null;
    protected List<User> userMentions = null;
    protected List<Member> memberMentions = null;
    protected List<Emote> emoteMentions = null;
    protected List<Role> roleMentions = null;
    protected List<TextChannel> channelMentions = null;
    protected List<String> invites = null;

    public ReceivedMessage(long id2, MessageChannel channel, MessageType type, Message referencedMessage, boolean fromWebhook, boolean mentionsEveryone, TLongSet mentionedUsers, TLongSet mentionedRoles, boolean tts, boolean pinned, String content, String nonce, User author, Member member, MessageActivity activity, OffsetDateTime editTime, List<MessageReaction> reactions, List<Message.Attachment> attachments, List<MessageEmbed> embeds, List<MessageSticker> stickers, List<ActionRow> components, int flags) {
        super(content, nonce, tts);
        this.id = id2;
        this.channel = channel;
        this.referencedMessage = referencedMessage;
        this.type = type;
        this.api = channel != null ? (JDAImpl)channel.getJDA() : null;
        this.fromWebhook = fromWebhook;
        this.mentionsEveryone = mentionsEveryone;
        this.pinned = pinned;
        this.author = author;
        this.member = member;
        this.activity = activity;
        this.editedTime = editTime;
        this.reactions = Collections.unmodifiableList(reactions);
        this.attachments = Collections.unmodifiableList(attachments);
        this.embeds = Collections.unmodifiableList(embeds);
        this.stickers = Collections.unmodifiableList(stickers);
        this.components = Collections.unmodifiableList(components);
        this.mentionedUsers = mentionedUsers;
        this.mentionedRoles = mentionedRoles;
        this.flags = flags;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    public Message getReferencedMessage() {
        return this.referencedMessage;
    }

    @Override
    public boolean isPinned() {
        return this.pinned;
    }

    @Override
    @Nonnull
    public RestAction<Void> pin() {
        return this.channel.pinMessageById(this.getId());
    }

    @Override
    @Nonnull
    public RestAction<Void> unpin() {
        return this.channel.unpinMessageById(this.getId());
    }

    @Override
    @Nonnull
    public RestAction<Void> addReaction(@Nonnull Emote emote) {
        Checks.notNull(emote, "Emote");
        boolean missingReaction = this.reactions.stream().map(MessageReaction::getReactionEmote).filter(MessageReaction.ReactionEmote::isEmote).noneMatch(r2 -> r2.getIdLong() == emote.getIdLong());
        if (missingReaction) {
            Checks.check(emote.canInteract(this.getJDA().getSelfUser(), this.channel), "Cannot react with the provided emote because it is not available in the current channel.");
        }
        return this.channel.addReactionById(this.getId(), emote);
    }

    @Override
    @Nonnull
    public RestAction<Void> addReaction(@Nonnull String unicode) {
        return this.channel.addReactionById(this.getId(), unicode);
    }

    @Override
    @Nonnull
    public RestAction<Void> clearReactions() {
        if (!this.isFromGuild()) {
            throw new IllegalStateException("Cannot clear reactions from a message in a Group or PrivateChannel.");
        }
        return this.getTextChannel().clearReactionsById(this.getId());
    }

    @Override
    @Nonnull
    public RestAction<Void> clearReactions(@Nonnull String unicode) {
        if (!this.isFromGuild()) {
            throw new IllegalStateException("Cannot clear reactions from a message in a Group or PrivateChannel.");
        }
        return this.getTextChannel().clearReactionsById(this.getId(), unicode);
    }

    @Override
    @Nonnull
    public RestAction<Void> clearReactions(@Nonnull Emote emote) {
        if (!this.isFromGuild()) {
            throw new IllegalStateException("Cannot clear reactions from a message in a Group or PrivateChannel.");
        }
        return this.getTextChannel().clearReactionsById(this.getId(), emote);
    }

    @Override
    @Nonnull
    public RestAction<Void> removeReaction(@Nonnull Emote emote) {
        return this.channel.removeReactionById(this.getId(), emote);
    }

    @Override
    @Nonnull
    public RestAction<Void> removeReaction(@Nonnull Emote emote, @Nonnull User user) {
        Checks.notNull(user, "User");
        if (user.equals(this.getJDA().getSelfUser())) {
            return this.channel.removeReactionById(this.getIdLong(), emote);
        }
        if (!this.isFromGuild()) {
            throw new IllegalStateException("Cannot remove reactions of others from a message in a Group or PrivateChannel.");
        }
        return this.getTextChannel().removeReactionById(this.getIdLong(), emote, user);
    }

    @Override
    @Nonnull
    public RestAction<Void> removeReaction(@Nonnull String unicode) {
        return this.channel.removeReactionById(this.getId(), unicode);
    }

    @Override
    @Nonnull
    public RestAction<Void> removeReaction(@Nonnull String unicode, @Nonnull User user) {
        Checks.notNull(user, "User");
        if (user.equals(this.getJDA().getSelfUser())) {
            return this.channel.removeReactionById(this.getIdLong(), unicode);
        }
        if (!this.isFromGuild()) {
            throw new IllegalStateException("Cannot remove reactions of others from a message in a Group or PrivateChannel.");
        }
        return this.getTextChannel().removeReactionById(this.getId(), unicode, user);
    }

    @Override
    @Nonnull
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull Emote emote) {
        return this.channel.retrieveReactionUsersById(this.id, emote);
    }

    @Override
    @Nonnull
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull String unicode) {
        return this.channel.retrieveReactionUsersById(this.id, unicode);
    }

    @Override
    public MessageReaction.ReactionEmote getReactionByUnicode(@Nonnull String unicode) {
        Checks.notEmpty(unicode, "Emoji");
        Checks.noWhitespace(unicode, "Emoji");
        return this.reactions.stream().map(MessageReaction::getReactionEmote).filter(r2 -> r2.isEmoji() && r2.getEmoji().equals(unicode)).findFirst().orElse(null);
    }

    @Override
    public MessageReaction.ReactionEmote getReactionById(@Nonnull String id2) {
        return this.getReactionById(MiscUtil.parseSnowflake(id2));
    }

    @Override
    public MessageReaction.ReactionEmote getReactionById(long id2) {
        return this.reactions.stream().map(MessageReaction::getReactionEmote).filter(r2 -> r2.isEmote() && r2.getIdLong() == id2).findFirst().orElse(null);
    }

    @Override
    @Nonnull
    public MessageType getType() {
        return this.type;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Override
    @Nonnull
    public String getJumpUrl() {
        return String.format("https://discord.com/channels/%s/%s/%s", this.isFromGuild() ? this.getGuild().getId() : "@me", this.getChannel().getId(), this.getId());
    }

    private User matchUser(Matcher matcher) {
        long userId = MiscUtil.parseSnowflake(matcher.group(1));
        if (!this.mentionedUsers.contains(userId)) {
            return null;
        }
        User user = this.getJDA().getUserById(userId);
        if (user == null && this.userMentions != null) {
            user = this.userMentions.stream().filter(it2 -> it2.getIdLong() == userId).findFirst().orElse(null);
        }
        return user;
    }

    @Override
    @Nonnull
    public synchronized List<User> getMentionedUsers() {
        if (this.userMentions == null) {
            this.userMentions = Collections.unmodifiableList(this.processMentions(Message.MentionType.USER, new ArrayList(), true, this::matchUser));
        }
        return this.userMentions;
    }

    @Override
    @Nonnull
    public Bag<User> getMentionedUsersBag() {
        return this.processMentions(Message.MentionType.USER, new HashBag(), false, this::matchUser);
    }

    private TextChannel matchTextChannel(Matcher matcher) {
        long channelId = MiscUtil.parseSnowflake(matcher.group(1));
        return this.getJDA().getTextChannelById(channelId);
    }

    @Override
    @Nonnull
    public synchronized List<TextChannel> getMentionedChannels() {
        if (this.channelMentions == null) {
            this.channelMentions = Collections.unmodifiableList(this.processMentions(Message.MentionType.CHANNEL, new ArrayList(), true, this::matchTextChannel));
        }
        return this.channelMentions;
    }

    @Override
    @Nonnull
    public Bag<TextChannel> getMentionedChannelsBag() {
        return this.processMentions(Message.MentionType.CHANNEL, new HashBag(), false, this::matchTextChannel);
    }

    private Role matchRole(Matcher matcher) {
        long roleId = MiscUtil.parseSnowflake(matcher.group(1));
        if (!this.mentionedRoles.contains(roleId)) {
            return null;
        }
        if (this.getChannelType().isGuild()) {
            return this.getGuild().getRoleById(roleId);
        }
        return this.getJDA().getRoleById(roleId);
    }

    @Override
    @Nonnull
    public synchronized List<Role> getMentionedRoles() {
        if (this.roleMentions == null) {
            this.roleMentions = Collections.unmodifiableList(this.processMentions(Message.MentionType.ROLE, new ArrayList(), true, this::matchRole));
        }
        return this.roleMentions;
    }

    @Override
    @Nonnull
    public Bag<Role> getMentionedRolesBag() {
        return this.processMentions(Message.MentionType.ROLE, new HashBag(), false, this::matchRole);
    }

    @Override
    @Nonnull
    public List<Member> getMentionedMembers(@Nonnull Guild guild) {
        Checks.notNull(guild, "Guild");
        if (this.isFromGuild() && guild.equals(this.getGuild()) && this.memberMentions != null) {
            return this.memberMentions;
        }
        List<User> mentionedUsers = this.getMentionedUsers();
        ArrayList<Member> members = new ArrayList<Member>();
        for (User user : mentionedUsers) {
            Member member = guild.getMember(user);
            if (member == null) continue;
            members.add(member);
        }
        return Collections.unmodifiableList(members);
    }

    @Override
    @Nonnull
    public List<Member> getMentionedMembers() {
        if (this.isFromGuild()) {
            return this.getMentionedMembers(this.getGuild());
        }
        throw new IllegalStateException("You must specify a Guild for Messages which are not sent from a TextChannel!");
    }

    @Override
    @Nonnull
    public List<IMentionable> getMentions(Message.MentionType ... types) {
        if (types == null || types.length == 0) {
            return this.getMentions(Message.MentionType.values());
        }
        ArrayList<IMentionable> mentions = new ArrayList<IMentionable>();
        boolean channel = false;
        boolean role = false;
        boolean user = false;
        boolean emote = false;
        block6: for (Message.MentionType type : types) {
            switch (type) {
                default: {
                    continue block6;
                }
                case CHANNEL: {
                    if (!channel) {
                        mentions.addAll(this.getMentionedChannels());
                    }
                    channel = true;
                    continue block6;
                }
                case USER: {
                    if (!user) {
                        mentions.addAll(this.getMentionedUsers());
                    }
                    user = true;
                    continue block6;
                }
                case ROLE: {
                    if (!role) {
                        mentions.addAll(this.getMentionedRoles());
                    }
                    role = true;
                    continue block6;
                }
                case EMOTE: {
                    if (!emote) {
                        mentions.addAll(this.getEmotes());
                    }
                    emote = true;
                }
            }
        }
        return Collections.unmodifiableList(mentions);
    }

    @Override
    public boolean isMentioned(@Nonnull IMentionable mentionable, Message.MentionType ... types) {
        Checks.notNull(types, "Mention Types");
        if (types.length == 0) {
            return this.isMentioned(mentionable, Message.MentionType.values());
        }
        boolean isUserEntity = mentionable instanceof User || mentionable instanceof Member;
        block8: for (Message.MentionType type : types) {
            switch (type) {
                case HERE: {
                    if (!this.isMass("@here") || !isUserEntity) continue block8;
                    return true;
                }
                case EVERYONE: {
                    if (!this.isMass("@everyone") || !isUserEntity) continue block8;
                    return true;
                }
                case USER: {
                    if (!this.isUserMentioned(mentionable)) continue block8;
                    return true;
                }
                case ROLE: {
                    if (!this.isRoleMentioned(mentionable)) continue block8;
                    return true;
                }
                case CHANNEL: {
                    if (!(mentionable instanceof TextChannel) || !this.getMentionedChannels().contains(mentionable)) continue block8;
                    return true;
                }
                case EMOTE: {
                    if (!(mentionable instanceof Emote) || !this.getEmotes().contains(mentionable)) continue block8;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isUserMentioned(IMentionable mentionable) {
        if (mentionable instanceof User) {
            return this.getMentionedUsers().contains(mentionable);
        }
        if (mentionable instanceof Member) {
            Member member = (Member)mentionable;
            return this.getMentionedUsers().contains(member.getUser());
        }
        return false;
    }

    private boolean isRoleMentioned(IMentionable mentionable) {
        if (mentionable instanceof Role) {
            return this.getMentionedRoles().contains(mentionable);
        }
        if (mentionable instanceof Member) {
            Member member = (Member)mentionable;
            return CollectionUtils.containsAny(this.getMentionedRoles(), member.getRoles());
        }
        if (this.isFromGuild() && mentionable instanceof User) {
            Member member = this.getGuild().getMember((User)mentionable);
            return member != null && CollectionUtils.containsAny(this.getMentionedRoles(), member.getRoles());
        }
        return false;
    }

    private boolean isMass(String s2) {
        return this.mentionsEveryone && this.content.contains(s2);
    }

    @Override
    public boolean mentionsEveryone() {
        return this.mentionsEveryone;
    }

    @Override
    public boolean isEdited() {
        return this.editedTime != null;
    }

    @Override
    public OffsetDateTime getTimeEdited() {
        return this.editedTime;
    }

    @Override
    @Nonnull
    public User getAuthor() {
        return this.author;
    }

    @Override
    public Member getMember() {
        return this.member;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Nonnull
    public String getContentStripped() {
        if (this.strippedContent != null) {
            return this.strippedContent;
        }
        Object object = this.mutex;
        synchronized (object) {
            if (this.strippedContent != null) {
                return this.strippedContent;
            }
            this.strippedContent = MarkdownSanitizer.sanitize(this.getContentDisplay());
            return this.strippedContent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Nonnull
    public String getContentDisplay() {
        if (this.altContent != null) {
            return this.altContent;
        }
        Object object = this.mutex;
        synchronized (object) {
            if (this.altContent != null) {
                return this.altContent;
            }
            String tmp = this.content;
            for (User user : this.getMentionedUsers()) {
                String name = this.isFromGuild() && this.getGuild().isMember(user) ? this.getGuild().getMember(user).getEffectiveName() : user.getName();
                tmp = tmp.replaceAll("<@!?" + Pattern.quote(user.getId()) + '>', '@' + Matcher.quoteReplacement(name));
            }
            for (Emote emote : this.getEmotes()) {
                tmp = tmp.replace(emote.getAsMention(), ":" + emote.getName() + ":");
            }
            for (TextChannel mentionedChannel : this.getMentionedChannels()) {
                tmp = tmp.replace(mentionedChannel.getAsMention(), '#' + mentionedChannel.getName());
            }
            for (Role mentionedRole : this.getMentionedRoles()) {
                tmp = tmp.replace(mentionedRole.getAsMention(), '@' + mentionedRole.getName());
            }
            this.altContent = tmp;
            return this.altContent;
        }
    }

    @Override
    @Nonnull
    public String getContentRaw() {
        return this.content;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Nonnull
    public List<String> getInvites() {
        if (this.invites != null) {
            return this.invites;
        }
        Object object = this.mutex;
        synchronized (object) {
            if (this.invites != null) {
                return this.invites;
            }
            this.invites = new ArrayList<String>();
            Matcher m2 = INVITE_PATTERN.matcher(this.getContentRaw());
            while (m2.find()) {
                this.invites.add(m2.group(1));
            }
            this.invites = Collections.unmodifiableList(this.invites);
            return this.invites;
        }
    }

    @Override
    public String getNonce() {
        return this.nonce;
    }

    @Override
    public boolean isFromType(@Nonnull ChannelType type) {
        return this.getChannelType() == type;
    }

    @Override
    @Nonnull
    public ChannelType getChannelType() {
        return this.channel.getType();
    }

    @Override
    @Nonnull
    public MessageChannel getChannel() {
        return this.channel;
    }

    @Override
    @Nonnull
    public PrivateChannel getPrivateChannel() {
        if (!this.isFromType(ChannelType.PRIVATE)) {
            throw new IllegalStateException("This message was not sent in a private channel");
        }
        return (PrivateChannel)this.channel;
    }

    @Override
    @Nonnull
    public TextChannel getTextChannel() {
        if (!this.isFromType(ChannelType.TEXT)) {
            throw new IllegalStateException("This message was not sent in a text channel");
        }
        return (TextChannel)this.channel;
    }

    @Override
    public Category getCategory() {
        return this.isFromGuild() ? this.getTextChannel().getParent() : null;
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.getTextChannel().getGuild();
    }

    @Override
    @Nonnull
    public List<Message.Attachment> getAttachments() {
        return this.attachments;
    }

    @Override
    @Nonnull
    public List<MessageEmbed> getEmbeds() {
        return this.embeds;
    }

    @Override
    @Nonnull
    public List<ActionRow> getActionRows() {
        return this.components;
    }

    private Emote matchEmote(Matcher m2) {
        long emoteId = MiscUtil.parseSnowflake(m2.group(2));
        String name = m2.group(1);
        boolean animated = m2.group(0).startsWith("<a:");
        Emote emote = this.getJDA().getEmoteById(emoteId);
        if (emote == null) {
            emote = new EmoteImpl(emoteId, this.api).setName(name).setAnimated(animated);
        }
        return emote;
    }

    @Override
    @Nonnull
    public synchronized List<Emote> getEmotes() {
        if (this.emoteMentions == null) {
            this.emoteMentions = Collections.unmodifiableList(this.processMentions(Message.MentionType.EMOTE, new ArrayList(), true, this::matchEmote));
        }
        return this.emoteMentions;
    }

    @Override
    @Nonnull
    public Bag<Emote> getEmotesBag() {
        return this.processMentions(Message.MentionType.EMOTE, new HashBag(), false, this::matchEmote);
    }

    @Override
    @Nonnull
    public List<MessageReaction> getReactions() {
        return this.reactions;
    }

    @Override
    @Nonnull
    public List<MessageSticker> getStickers() {
        return this.stickers;
    }

    @Override
    public boolean isWebhookMessage() {
        return this.fromWebhook;
    }

    @Override
    public boolean isTTS() {
        return this.isTTS;
    }

    @Override
    @Nullable
    public MessageActivity getActivity() {
        return this.activity;
    }

    @Override
    @Nonnull
    public MessageAction editMessage(@Nonnull CharSequence newContent) {
        this.checkUser();
        return this.channel.editMessageById(this.getId(), newContent);
    }

    @Override
    @Nonnull
    public MessageAction editMessage(@Nonnull MessageEmbed newContent) {
        this.checkUser();
        return this.channel.editMessageById(this.getId(), newContent);
    }

    @Override
    @Nonnull
    public MessageAction editMessageFormat(@Nonnull String format, Object ... args) {
        this.checkUser();
        return this.channel.editMessageFormatById(this.getId(), format, args);
    }

    @Override
    @Nonnull
    public MessageAction editMessage(@Nonnull Message newContent) {
        this.checkUser();
        return this.channel.editMessageById(this.getIdLong(), newContent);
    }

    private void checkUser() {
        if (!this.getJDA().getSelfUser().equals(this.getAuthor())) {
            throw new IllegalStateException("Attempted to update message that was not sent by this account. You cannot modify other User's messages!");
        }
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        if (!this.getJDA().getSelfUser().equals(this.getAuthor())) {
            if (this.isFromType(ChannelType.PRIVATE)) {
                throw new IllegalStateException("Cannot delete another User's messages in a PrivateChannel.");
            }
            if (!this.getGuild().getSelfMember().hasAccess(this.getTextChannel())) {
                throw new MissingAccessException(this.getTextChannel(), Permission.VIEW_CHANNEL);
            }
            if (!this.getGuild().getSelfMember().hasPermission((GuildChannel)((TextChannel)this.getChannel()), Permission.MESSAGE_MANAGE)) {
                throw new InsufficientPermissionException(this.getTextChannel(), Permission.MESSAGE_MANAGE);
            }
        }
        return this.channel.deleteMessageById(this.getIdLong());
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> suppressEmbeds(boolean suppressed) {
        JDAImpl jda = (JDAImpl)this.getJDA();
        Route.CompiledRoute route = Route.Messages.EDIT_MESSAGE.compile(this.getChannel().getId(), this.getId());
        int newFlags = this.flags;
        int suppressionValue = Message.MessageFlag.EMBEDS_SUPPRESSED.getValue();
        newFlags = suppressed ? (newFlags |= suppressionValue) : (newFlags &= ~suppressionValue);
        return new AuditableRestActionImpl<Void>((JDA)jda, route, DataObject.empty().put("flags", newFlags));
    }

    @Override
    @Nonnull
    public RestAction<Message> crosspost() {
        if (this.getFlags().contains((Object)Message.MessageFlag.CROSSPOSTED)) {
            return new CompletedRestAction<Message>(this.getJDA(), this);
        }
        TextChannel textChannel = this.getTextChannel();
        if (!this.getGuild().getSelfMember().hasAccess(textChannel)) {
            throw new MissingAccessException(textChannel, Permission.VIEW_CHANNEL);
        }
        if (!this.getAuthor().equals(this.getJDA().getSelfUser()) && !this.getGuild().getSelfMember().hasPermission((GuildChannel)textChannel, Permission.MESSAGE_MANAGE)) {
            throw new InsufficientPermissionException(textChannel, Permission.MESSAGE_MANAGE);
        }
        return textChannel.crosspostMessageById(this.getId());
    }

    @Override
    public boolean isSuppressedEmbeds() {
        return (this.flags & Message.MessageFlag.EMBEDS_SUPPRESSED.getValue()) > 0;
    }

    @Override
    @Nonnull
    public EnumSet<Message.MessageFlag> getFlags() {
        return Message.MessageFlag.fromBitField(this.flags);
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof ReceivedMessage)) {
            return false;
        }
        ReceivedMessage oMsg = (ReceivedMessage)o2;
        return this.id == oMsg.id;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public String toString() {
        return this.author != null ? String.format("M:%#s:%.20s(%s)", this.author, this, this.getId()) : String.format("M:%.20s", this);
    }

    @Override
    protected void unsupported() {
        throw new UnsupportedOperationException("This operation is not supported on received messages!");
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        String out;
        boolean upper = (flags & 2) == 2;
        boolean leftJustified = (flags & 1) == 1;
        boolean alt2 = (flags & 4) == 4;
        String string = out = alt2 ? this.getContentRaw() : this.getContentDisplay();
        if (upper) {
            out = out.toUpperCase(formatter.locale());
        }
        this.appendFormat(formatter, width, precision, leftJustified, out);
    }

    public void setMentions(List<User> users, List<Member> members) {
        users.sort(Comparator.comparing(user -> Math.max(this.content.indexOf("<@" + user.getId() + ">"), this.content.indexOf("<@!" + user.getId() + ">"))));
        members.sort(Comparator.comparing(user -> Math.max(this.content.indexOf("<@" + user.getId() + ">"), this.content.indexOf("<@!" + user.getId() + ">"))));
        this.userMentions = Collections.unmodifiableList(users);
        this.memberMentions = Collections.unmodifiableList(members);
    }

    private <T, C extends Collection<T>> C processMentions(Message.MentionType type, C collection, boolean distinct, Function<Matcher, T> map) {
        Matcher matcher = type.getPattern().matcher(this.getContentRaw());
        while (matcher.find()) {
            try {
                T elem = map.apply(matcher);
                if (elem == null || distinct && collection.contains(elem)) continue;
                collection.add(elem);
            }
            catch (NumberFormatException numberFormatException) {}
        }
        return collection;
    }

    private static class FormatToken {
        public final String format;
        public final int start;

        public FormatToken(String format, int start) {
            this.format = format;
            this.start = start;
        }
    }
}

