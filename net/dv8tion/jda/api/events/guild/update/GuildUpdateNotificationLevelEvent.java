/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateNotificationLevelEvent
extends GenericGuildUpdateEvent<Guild.NotificationLevel> {
    public static final String IDENTIFIER = "notification_level";

    public GuildUpdateNotificationLevelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nonnull Guild.NotificationLevel oldNotificationLevel) {
        super(api2, responseNumber, guild, oldNotificationLevel, guild.getDefaultNotificationLevel(), IDENTIFIER);
    }

    @Nonnull
    public Guild.NotificationLevel getOldNotificationLevel() {
        return this.getOldValue();
    }

    @Nonnull
    public Guild.NotificationLevel getNewNotificationLevel() {
        return this.getNewValue();
    }

    @Override
    @Nonnull
    public Guild.NotificationLevel getOldValue() {
        return (Guild.NotificationLevel)((Object)super.getOldValue());
    }

    @Override
    @Nonnull
    public Guild.NotificationLevel getNewValue() {
        return (Guild.NotificationLevel)((Object)super.getNewValue());
    }
}

