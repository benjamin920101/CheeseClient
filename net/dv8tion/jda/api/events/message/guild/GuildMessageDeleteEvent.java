/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;

public class GuildMessageDeleteEvent
extends GenericGuildMessageEvent {
    public GuildMessageDeleteEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull TextChannel channel) {
        super(api2, responseNumber, messageId, channel);
    }
}

