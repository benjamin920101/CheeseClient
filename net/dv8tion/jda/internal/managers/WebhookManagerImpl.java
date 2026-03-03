/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.managers.WebhookManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.managers.ManagerBase;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class WebhookManagerImpl
extends ManagerBase<WebhookManager>
implements WebhookManager {
    protected final Webhook webhook;
    protected String name;
    protected String channel;
    protected Icon avatar;

    public WebhookManagerImpl(Webhook webhook) {
        super(webhook.getJDA(), Route.Webhooks.MODIFY_WEBHOOK.compile(webhook.getId()));
        this.webhook = webhook;
        if (WebhookManagerImpl.isPermissionChecksEnabled()) {
            this.checkPermissions();
        }
    }

    @Override
    @Nonnull
    public Webhook getWebhook() {
        return this.webhook;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public WebhookManagerImpl reset(long fields) {
        super.reset(fields);
        if ((fields & 1L) == 1L) {
            this.name = null;
        }
        if ((fields & 2L) == 2L) {
            this.channel = null;
        }
        if ((fields & 4L) == 4L) {
            this.avatar = null;
        }
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public WebhookManagerImpl reset(long ... fields) {
        super.reset(fields);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public WebhookManagerImpl reset() {
        super.reset();
        this.name = null;
        this.channel = null;
        this.avatar = null;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public WebhookManagerImpl setName(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        this.name = name;
        this.set |= 1L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public WebhookManagerImpl setAvatar(Icon icon) {
        this.avatar = icon;
        this.set |= 4L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public WebhookManagerImpl setChannel(@Nonnull TextChannel channel) {
        Checks.notNull(channel, "Channel");
        Checks.check(channel.getGuild().equals(this.getGuild()), "Channel is not from the same guild");
        this.channel = channel.getId();
        this.set |= 2L;
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject data = DataObject.empty();
        if (this.shouldUpdate(1L)) {
            data.put("name", this.name);
        }
        if (this.shouldUpdate(2L)) {
            data.put("channel_id", this.channel);
        }
        if (this.shouldUpdate(4L)) {
            data.put("avatar", this.avatar == null ? null : this.avatar.getEncoding());
        }
        return this.getRequestBody(data);
    }

    @Override
    protected boolean checkPermissions() {
        TextChannel channel;
        Member selfMember = this.getGuild().getSelfMember();
        if (!selfMember.hasAccess(channel = this.getChannel())) {
            throw new MissingAccessException(channel, Permission.VIEW_CHANNEL);
        }
        if (!selfMember.hasPermission((GuildChannel)channel, Permission.MANAGE_WEBHOOKS)) {
            throw new InsufficientPermissionException(channel, Permission.MANAGE_WEBHOOKS);
        }
        return super.checkPermissions();
    }
}

