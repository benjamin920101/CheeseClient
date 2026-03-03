/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.message.guild;

import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;

public class GuildMessageEmbedEvent
extends GenericGuildMessageEvent {
    private final List<MessageEmbed> embeds;

    public GuildMessageEmbedEvent(@Nonnull JDA api2, long responseNumber, long messageId, @Nonnull TextChannel channel, @Nonnull List<MessageEmbed> embeds) {
        super(api2, responseNumber, messageId, channel);
        this.embeds = embeds;
    }

    @Nonnull
    public List<MessageEmbed> getMessageEmbeds() {
        return this.embeds;
    }
}

