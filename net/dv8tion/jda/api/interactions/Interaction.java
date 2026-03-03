/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions;

import java.util.Collection;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.InteractionType;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import net.dv8tion.jda.internal.requests.restaction.interactions.ReplyActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public interface Interaction
extends ISnowflake {
    public int getTypeRaw();

    @Nonnull
    default public InteractionType getType() {
        return InteractionType.fromKey(this.getTypeRaw());
    }

    @Nonnull
    public String getToken();

    @Nullable
    public Guild getGuild();

    default public boolean isFromGuild() {
        return this.getGuild() != null;
    }

    @Nonnull
    default public ChannelType getChannelType() {
        AbstractChannel channel = this.getChannel();
        return channel != null ? channel.getType() : ChannelType.UNKNOWN;
    }

    @Nonnull
    public User getUser();

    @Nullable
    public Member getMember();

    @Nullable
    public AbstractChannel getChannel();

    @Nonnull
    public InteractionHook getHook();

    public boolean isAcknowledged();

    @Nonnull
    @CheckReturnValue
    public ReplyAction deferReply();

    @Nonnull
    @CheckReturnValue
    default public ReplyAction deferReply(boolean ephemeral) {
        return this.deferReply().setEphemeral(ephemeral);
    }

    @Nonnull
    @CheckReturnValue
    default public ReplyAction reply(@Nonnull Message message) {
        Checks.notNull(message, "Message");
        ReplyActionImpl action = (ReplyActionImpl)this.deferReply();
        return action.applyMessage(message);
    }

    @Nonnull
    @CheckReturnValue
    default public ReplyAction reply(@Nonnull String content) {
        Checks.notNull(content, "Content");
        return this.deferReply().setContent(content);
    }

    @Nonnull
    @CheckReturnValue
    default public ReplyAction replyEmbeds(@Nonnull Collection<? extends MessageEmbed> embeds) {
        return this.deferReply().addEmbeds(embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public ReplyAction replyEmbeds(@Nonnull MessageEmbed embed, MessageEmbed ... embeds) {
        Checks.notNull(embed, "MessageEmbed");
        Checks.noneNull(embeds, "MessageEmbed");
        return this.deferReply().addEmbeds(embed).addEmbeds(embeds);
    }

    @Nonnull
    @CheckReturnValue
    default public ReplyAction replyFormat(@Nonnull String format, Object ... args) {
        Checks.notNull(format, "Format String");
        return this.reply(String.format(format, args));
    }

    @Nonnull
    default public GuildChannel getGuildChannel() {
        AbstractChannel channel = this.getChannel();
        if (channel instanceof GuildChannel) {
            return (GuildChannel)channel;
        }
        throw new IllegalStateException("Cannot convert channel of type " + (Object)((Object)this.getChannelType()) + " to GuildChannel");
    }

    @Nonnull
    default public MessageChannel getMessageChannel() {
        AbstractChannel channel = this.getChannel();
        if (channel instanceof MessageChannel) {
            return (MessageChannel)channel;
        }
        throw new IllegalStateException("Cannot convert channel of type " + (Object)((Object)this.getChannelType()) + " to MessageChannel");
    }

    @Nonnull
    default public TextChannel getTextChannel() {
        AbstractChannel channel = this.getChannel();
        if (channel instanceof TextChannel) {
            return (TextChannel)channel;
        }
        throw new IllegalStateException("Cannot convert channel of type " + (Object)((Object)this.getChannelType()) + " to TextChannel");
    }

    @Nonnull
    default public VoiceChannel getVoiceChannel() {
        AbstractChannel channel = this.getChannel();
        if (channel instanceof VoiceChannel) {
            return (VoiceChannel)channel;
        }
        throw new IllegalStateException("Cannot convert channel of type " + (Object)((Object)this.getChannelType()) + " to VoiceChannel");
    }

    @Nonnull
    default public PrivateChannel getPrivateChannel() {
        AbstractChannel channel = this.getChannel();
        if (channel instanceof PrivateChannel) {
            return (PrivateChannel)channel;
        }
        throw new IllegalStateException("Cannot convert channel of type " + (Object)((Object)this.getChannelType()) + " to PrivateChannel");
    }
}

