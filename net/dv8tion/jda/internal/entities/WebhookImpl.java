/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.WebhookType;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.WebhookManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.entities.AbstractWebhookClient;
import net.dv8tion.jda.internal.managers.WebhookManagerImpl;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.WebhookMessageActionImpl;
import net.dv8tion.jda.internal.requests.restaction.WebhookMessageUpdateActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class WebhookImpl
extends AbstractWebhookClient<Void>
implements Webhook {
    private final TextChannel channel;
    private final WebhookType type;
    private WebhookManager manager;
    private Member owner;
    private User user;
    private User ownerUser;
    private Webhook.ChannelReference sourceChannel;
    private Webhook.GuildReference sourceGuild;

    public WebhookImpl(TextChannel channel, long id2, WebhookType type) {
        this(channel, channel.getJDA(), id2, type);
    }

    public WebhookImpl(TextChannel channel, JDA api2, long id2, WebhookType type) {
        super(id2, null, api2);
        this.channel = channel;
        this.type = type;
    }

    @Override
    @Nonnull
    public WebhookType getType() {
        return this.type;
    }

    @Override
    public boolean isPartial() {
        return this.channel == null;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        if (this.channel == null) {
            throw new IllegalStateException("Cannot provide guild for this Webhook instance because it does not belong to this shard");
        }
        return this.getChannel().getGuild();
    }

    @Override
    @Nonnull
    public TextChannel getChannel() {
        if (this.channel == null) {
            throw new IllegalStateException("Cannot provide channel for this Webhook instance because it does not belong to this shard");
        }
        return this.channel;
    }

    @Override
    public Member getOwner() {
        if (this.owner == null && this.channel != null && this.ownerUser != null) {
            return this.getGuild().getMember(this.ownerUser);
        }
        return this.owner;
    }

    @Override
    public User getOwnerAsUser() {
        return this.ownerUser;
    }

    @Override
    @Nonnull
    public User getDefaultUser() {
        return this.user;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.user.getName();
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    @Nonnull
    public String getUrl() {
        return Requester.DISCORD_API_PREFIX + "webhooks/" + this.getId() + (this.getToken() == null ? "" : "/" + this.getToken());
    }

    @Override
    public Webhook.ChannelReference getSourceChannel() {
        return this.sourceChannel;
    }

    @Override
    public Webhook.GuildReference getSourceGuild() {
        return this.sourceGuild;
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        if (this.token != null) {
            return this.delete(this.token);
        }
        if (!this.getGuild().getSelfMember().hasPermission((GuildChannel)this.getChannel(), Permission.MANAGE_WEBHOOKS)) {
            throw new InsufficientPermissionException(this.getChannel(), Permission.MANAGE_WEBHOOKS);
        }
        Route.CompiledRoute route = Route.Webhooks.DELETE_WEBHOOK.compile(this.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete(@Nonnull String token) {
        Checks.notNull(token, "Token");
        Route.CompiledRoute route = Route.Webhooks.DELETE_TOKEN_WEBHOOK.compile(this.getId(), token);
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public WebhookManager getManager() {
        if (this.manager == null) {
            this.manager = new WebhookManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public WebhookImpl setOwner(Member member, User user) {
        this.owner = member;
        this.ownerUser = user;
        return this;
    }

    public WebhookImpl setToken(String token) {
        this.token = token;
        return this;
    }

    public WebhookImpl setUser(User user) {
        this.user = user;
        return this;
    }

    public WebhookImpl setSourceGuild(Webhook.GuildReference reference) {
        this.sourceGuild = reference;
        return this;
    }

    public WebhookImpl setSourceChannel(Webhook.ChannelReference reference) {
        this.sourceChannel = reference;
        return this;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebhookImpl)) {
            return false;
        }
        WebhookImpl impl = (WebhookImpl)obj;
        return impl.id == this.id;
    }

    public String toString() {
        return "WH:" + this.getName() + "(" + this.id + ")";
    }

    @Override
    public WebhookMessageActionImpl<Void> sendRequest() {
        this.checkToken();
        Route.CompiledRoute route = Route.Webhooks.EXECUTE_WEBHOOK.compile(this.getId(), this.token);
        WebhookMessageActionImpl<Void> action = new WebhookMessageActionImpl<Void>(this.api, this.channel, route, json -> null);
        action.run();
        return action;
    }

    @Override
    public WebhookMessageUpdateActionImpl<Void> editRequest(String messageId) {
        this.checkToken();
        Checks.isSnowflake(messageId);
        Route.CompiledRoute route = Route.Webhooks.EXECUTE_WEBHOOK_EDIT.compile(this.getId(), this.token, messageId);
        WebhookMessageUpdateActionImpl<Void> action = new WebhookMessageUpdateActionImpl<Void>(this.api, route, json -> null);
        action.run();
        return action;
    }

    @Override
    @Nonnull
    public RestAction<Void> deleteMessageById(@Nonnull String messageId) {
        this.checkToken();
        return super.deleteMessageById(messageId);
    }

    private void checkToken() {
        if (this.token == null) {
            throw new UnsupportedOperationException("Cannot execute webhook without a token!");
        }
    }
}

