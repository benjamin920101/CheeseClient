/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.managers.Manager;

public interface WebhookManager
extends Manager<WebhookManager> {
    public static final long NAME = 1L;
    public static final long CHANNEL = 2L;
    public static final long AVATAR = 4L;

    @Override
    @Nonnull
    public WebhookManager reset(long var1);

    @Override
    @Nonnull
    public WebhookManager reset(long ... var1);

    @Nonnull
    public Webhook getWebhook();

    @Nonnull
    default public TextChannel getChannel() {
        return this.getWebhook().getChannel();
    }

    @Nonnull
    default public Guild getGuild() {
        return this.getWebhook().getGuild();
    }

    @Nonnull
    @CheckReturnValue
    public WebhookManager setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public WebhookManager setAvatar(@Nullable Icon var1);

    @Nonnull
    @CheckReturnValue
    public WebhookManager setChannel(@Nonnull TextChannel var1);
}

