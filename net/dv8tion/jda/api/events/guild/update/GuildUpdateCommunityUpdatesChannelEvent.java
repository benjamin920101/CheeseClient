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

public class GuildUpdateCommunityUpdatesChannelEvent
extends GenericGuildUpdateEvent<TextChannel> {
    public static final String IDENTIFIER = "community_updates_channel";

    public GuildUpdateCommunityUpdatesChannelEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Guild guild, @Nullable TextChannel oldCommunityUpdatesChannel) {
        super(api2, responseNumber, guild, oldCommunityUpdatesChannel, guild.getCommunityUpdatesChannel(), IDENTIFIER);
    }

    @Nullable
    public TextChannel getOldCommunityUpdatesChannel() {
        return (TextChannel)this.getOldValue();
    }

    @Nullable
    public TextChannel getNewCommunityUpdatesChannel() {
        return (TextChannel)this.getNewValue();
    }
}

