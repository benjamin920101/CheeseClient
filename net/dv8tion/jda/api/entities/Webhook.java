/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.regex.Pattern;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.WebhookType;
import net.dv8tion.jda.api.managers.WebhookManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;

public interface Webhook
extends ISnowflake {
    public static final Pattern WEBHOOK_URL = Pattern.compile("https?://(?:[^\\s.]+\\.)?discord(?:app)?\\.com/api(?:/v\\d+)?/webhooks/(?<id>\\d+)/(?<token>[^\\s/]+)", 2);

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public WebhookType getType();

    public boolean isPartial();

    @Nonnull
    public Guild getGuild();

    @Nonnull
    public TextChannel getChannel();

    @Nullable
    public Member getOwner();

    @Nullable
    public User getOwnerAsUser();

    @Nonnull
    public User getDefaultUser();

    @Nonnull
    public String getName();

    @Nullable
    public String getToken();

    @Nonnull
    public String getUrl();

    @Nullable
    public ChannelReference getSourceChannel();

    @Nullable
    public GuildReference getSourceGuild();

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete(@Nonnull String var1);

    @Nonnull
    public WebhookManager getManager();

    public static class GuildReference
    implements ISnowflake {
        private final long id;
        private final String name;

        public GuildReference(long id2, String name) {
            this.id = id2;
            this.name = name;
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }
    }

    public static class ChannelReference
    implements ISnowflake {
        private final long id;
        private final String name;

        public ChannelReference(long id2, String name) {
            this.id = id2;
            this.name = name;
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }
    }

    public static class WebhookReference
    implements ISnowflake {
        private final JDA api;
        private final long webhookId;
        private final long channelId;

        public WebhookReference(JDA api2, long webhookId, long channelId) {
            this.api = api2;
            this.webhookId = webhookId;
            this.channelId = channelId;
        }

        @Override
        public long getIdLong() {
            return this.webhookId;
        }

        @Nonnull
        public String getChannelId() {
            return Long.toUnsignedString(this.channelId);
        }

        public long getChannelIdLong() {
            return this.channelId;
        }

        @Nonnull
        @CheckReturnValue
        public RestAction<Webhook> resolve() {
            Route.CompiledRoute route = Route.Webhooks.GET_WEBHOOK.compile(this.getId());
            return new RestActionImpl<Webhook>(this.api, route, (response, request) -> request.getJDA().getEntityBuilder().createWebhook(response.getObject(), true));
        }
    }
}

