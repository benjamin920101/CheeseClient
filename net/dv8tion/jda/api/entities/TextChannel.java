/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Collection;
import java.util.Formatter;
import java.util.List;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.WebhookAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;

public interface TextChannel
extends GuildChannel,
MessageChannel {
    public static final int MAX_SLOWMODE = 21600;

    @Nullable
    public String getTopic();

    public boolean isNSFW();

    public boolean isNews();

    public int getSlowmode();

    @Nonnull
    public ChannelAction<TextChannel> createCopy(@Nonnull Guild var1);

    @Nonnull
    public ChannelAction<TextChannel> createCopy();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Webhook>> retrieveWebhooks();

    @Nonnull
    @CheckReturnValue
    public WebhookAction createWebhook(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Webhook.WebhookReference> follow(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Webhook.WebhookReference> follow(long targetChannelId) {
        return this.follow(Long.toUnsignedString(targetChannelId));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Webhook.WebhookReference> follow(@Nonnull TextChannel targetChannel) {
        Checks.notNull(targetChannel, "Target Channel");
        Member selfMember = targetChannel.getGuild().getSelfMember();
        if (!selfMember.hasAccess(targetChannel)) {
            throw new MissingAccessException(targetChannel, Permission.VIEW_CHANNEL);
        }
        if (!selfMember.hasPermission((GuildChannel)targetChannel, Permission.MANAGE_WEBHOOKS)) {
            throw new InsufficientPermissionException(targetChannel, Permission.MANAGE_WEBHOOKS);
        }
        return this.follow(targetChannel.getId());
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> deleteMessages(@Nonnull Collection<Message> var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> deleteMessagesByIds(@Nonnull Collection<String> var1);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> deleteWebhookById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactionsById(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> clearReactionsById(long messageId) {
        return this.clearReactionsById(Long.toUnsignedString(messageId));
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactionsById(@Nonnull String var1, @Nonnull String var2);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactionsById(@Nonnull String var1, @Nonnull Emote var2);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> clearReactionsById(long messageId, @Nonnull String unicode) {
        return this.clearReactionsById(Long.toUnsignedString(messageId), unicode);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> clearReactionsById(long messageId, @Nonnull Emote emote) {
        return this.clearReactionsById(Long.toUnsignedString(messageId), emote);
    }

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReactionById(@Nonnull String var1, @Nonnull String var2, @Nonnull User var3);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(long messageId, @Nonnull String unicode, @Nonnull User user) {
        return this.removeReactionById(Long.toUnsignedString(messageId), unicode, user);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(@Nonnull String messageId, @Nonnull Emote emote, @Nonnull User user) {
        Checks.notNull(emote, "Emote");
        return this.removeReactionById(messageId, emote.getName() + ":" + emote.getId(), user);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Void> removeReactionById(long messageId, @Nonnull Emote emote, @Nonnull User user) {
        return this.removeReactionById(Long.toUnsignedString(messageId), emote, user);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Message> crosspostMessageById(@Nonnull String messageId) {
        if (!this.isNews()) {
            throw new IllegalStateException("You can only crosspost messages in news channels!");
        }
        Checks.isSnowflake(messageId);
        if (!this.getGuild().getSelfMember().hasAccess(this)) {
            throw new MissingAccessException(this, Permission.VIEW_CHANNEL);
        }
        Route.CompiledRoute route = Route.Messages.CROSSPOST_MESSAGE.compile(this.getId(), messageId);
        return new RestActionImpl<Message>(this.getJDA(), route, (response, request) -> request.getJDA().getEntityBuilder().createMessage(response.getObject()));
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<Message> crosspostMessageById(long messageId) {
        return this.crosspostMessageById(Long.toUnsignedString(messageId));
    }

    public boolean canTalk();

    public boolean canTalk(@Nonnull Member var1);

    @Override
    default public void formatTo(Formatter formatter, int flags, int width, int precision) {
        boolean alt2;
        boolean leftJustified = (flags & 1) == 1;
        boolean upper = (flags & 2) == 2;
        boolean bl2 = alt2 = (flags & 4) == 4;
        String out = alt2 ? "#" + (upper ? this.getName().toUpperCase(formatter.locale()) : this.getName()) : this.getAsMention();
        MiscUtil.appendTo(formatter, width, precision, leftJustified, out);
    }
}

