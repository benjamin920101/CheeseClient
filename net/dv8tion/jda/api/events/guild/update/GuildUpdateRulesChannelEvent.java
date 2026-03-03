/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.guild.update;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;

public class GuildUpdateRulesChannelEvent
extends GenericGuildUpdateEvent<TextChannel> {
    public static final String IDENTIFIER = "rules_channel";

    public GuildUpdateRulesChannelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable TextChannel oldRulesChannel) {
        super(api2, responseNumber, guild, oldRulesChannel, guild.getRulesChannel(), IDENTIFIER);
    }

    @Nullable
    public TextChannel getOldRulesChannel() {
        return (TextChannel)this.getOldValue();
    }

    @Nullable
    public TextChannel getNewRulesChannel() {
        return (TextChannel)this.getNewValue();
    }
}

