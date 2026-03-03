/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.pagination.ReactionPaginationActionImpl;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.EncodingUtil;

public class MessageReaction {
    private final MessageChannel channel;
    private final ReactionEmote emote;
    private final long messageId;
    private final boolean self;
    private final int count;

    public MessageReaction(@Nonnull MessageChannel channel, @Nonnull ReactionEmote emote, long messageId, boolean self, int count) {
        this.channel = channel;
        this.emote = emote;
        this.messageId = messageId;
        this.self = self;
        this.count = count;
    }

    @Nonnull
    public JDA getJDA() {
        return this.channel.getJDA();
    }

    public boolean isSelf() {
        return this.self;
    }

    public boolean hasCount() {
        return this.count >= 0;
    }

    public int getCount() {
        if (!this.hasCount()) {
            throw new IllegalStateException("Cannot retrieve count for this MessageReaction!");
        }
        return this.count;
    }

    @Nonnull
    public ChannelType getChannelType() {
        return this.channel.getType();
    }

    public boolean isFromType(@Nonnull ChannelType type) {
        return this.getChannelType() == type;
    }

    @Nullable
    public Guild getGuild() {
        TextChannel channel = this.getTextChannel();
        return channel != null ? channel.getGuild() : null;
    }

    @Nullable
    public TextChannel getTextChannel() {
        return this.getChannel() instanceof TextChannel ? (TextChannel)this.getChannel() : null;
    }

    @Nullable
    public PrivateChannel getPrivateChannel() {
        return this.getChannel() instanceof PrivateChannel ? (PrivateChannel)this.getChannel() : null;
    }

    @Nonnull
    public MessageChannel getChannel() {
        return this.channel;
    }

    @Nonnull
    public ReactionEmote getReactionEmote() {
        return this.emote;
    }

    @Nonnull
    public String getMessageId() {
        return Long.toUnsignedString(this.messageId);
    }

    public long getMessageIdLong() {
        return this.messageId;
    }

    @Nonnull
    @CheckReturnValue
    public ReactionPaginationAction retrieveUsers() {
        return new ReactionPaginationActionImpl(this);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReaction() {
        return this.removeReaction(this.getJDA().getSelfUser());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReaction(@Nonnull User user) {
        Checks.notNull(user, "User");
        boolean self = user.equals(this.getJDA().getSelfUser());
        if (!self) {
            if (this.channel.getType() == ChannelType.TEXT) {
                GuildChannel channel = (GuildChannel)((Object)this.channel);
                if (!channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_MANAGE)) {
                    throw new InsufficientPermissionException(channel, Permission.MESSAGE_MANAGE);
                }
            } else {
                throw new PermissionException("Unable to remove Reaction of other user in non-text channel!");
            }
        }
        String code = this.getReactionCode();
        String target = self ? "@me" : user.getId();
        Route.CompiledRoute route = Route.Messages.REMOVE_REACTION.compile(this.channel.getId(), this.getMessageId(), code, target);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactions() {
        if (!this.getChannelType().isGuild()) {
            throw new UnsupportedOperationException("Cannot clear reactions on a message sent from a private channel");
        }
        TextChannel guildChannel = Objects.requireNonNull(this.getTextChannel());
        if (!guildChannel.getGuild().getSelfMember().hasPermission((GuildChannel)guildChannel, Permission.MESSAGE_MANAGE)) {
            throw new InsufficientPermissionException(guildChannel, Permission.MESSAGE_MANAGE);
        }
        String code = this.getReactionCode();
        Route.CompiledRoute route = Route.Messages.CLEAR_EMOTE_REACTIONS.compile(this.channel.getId(), this.getMessageId(), code);
        return new RestActionImpl<Void>(this.getJDA(), route);
    }

    private String getReactionCode() {
        return this.emote.isEmote() ? this.emote.getName() + ":" + this.emote.getId() : EncodingUtil.encodeUTF8(this.emote.getName());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageReaction)) {
            return false;
        }
        MessageReaction r2 = (MessageReaction)obj;
        return r2.emote.equals(this.emote) && r2.self == this.self && r2.messageId == this.messageId;
    }

    public String toString() {
        return "MR:(M:(" + this.messageId + ") / " + this.emote + ")";
    }

    public static class ReactionEmote
    implements ISnowflake {
        private final JDA api;
        private final String name;
        private final long id;
        private final Emote emote;

        private ReactionEmote(@Nonnull String name, @Nonnull JDA api2) {
            this.name = name;
            this.api = api2;
            this.id = 0L;
            this.emote = null;
        }

        private ReactionEmote(@Nonnull Emote emote) {
            this.api = emote.getJDA();
            this.name = emote.getName();
            this.id = emote.getIdLong();
            this.emote = emote;
        }

        @Nonnull
        public static ReactionEmote fromUnicode(@Nonnull String name, @Nonnull JDA api2) {
            return new ReactionEmote(name, api2);
        }

        @Nonnull
        public static ReactionEmote fromCustom(@Nonnull Emote emote) {
            return new ReactionEmote(emote);
        }

        public boolean isEmote() {
            return this.emote != null;
        }

        public boolean isEmoji() {
            return this.emote == null;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        @Nonnull
        public String getAsCodepoints() {
            if (!this.isEmoji()) {
                throw new IllegalStateException("Cannot get codepoint for custom emote reaction");
            }
            return EncodingUtil.encodeCodepoints(this.name);
        }

        @Override
        public long getIdLong() {
            if (!this.isEmote()) {
                throw new IllegalStateException("Cannot get id for emoji reaction");
            }
            return this.id;
        }

        @Nonnull
        public String getAsReactionCode() {
            return this.emote != null ? this.name + ":" + this.id : this.name;
        }

        @Nonnull
        public String getEmoji() {
            if (!this.isEmoji()) {
                throw new IllegalStateException("Cannot get emoji code for custom emote reaction");
            }
            return this.getName();
        }

        @Nonnull
        public Emote getEmote() {
            if (!this.isEmote()) {
                throw new IllegalStateException("Cannot get custom emote for emoji reaction");
            }
            return this.emote;
        }

        @Nonnull
        public JDA getJDA() {
            return this.api;
        }

        public boolean equals(Object obj) {
            return obj instanceof ReactionEmote && Objects.equals(((ReactionEmote)obj).id, this.id) && ((ReactionEmote)obj).getName().equals(this.name);
        }

        public String toString() {
            if (this.isEmoji()) {
                return "RE:" + this.getAsCodepoints();
            }
            return "RE:" + this.getName() + "(" + this.getId() + ")";
        }
    }
}

