/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.order;

import java.util.EnumSet;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.requests.restaction.order.OrderAction;

public interface ChannelOrderAction
extends OrderAction<GuildChannel, ChannelOrderAction> {
    @Nonnull
    public Guild getGuild();

    public int getSortBucket();

    @Nonnull
    default public EnumSet<ChannelType> getChannelTypes() {
        return ChannelType.fromSortBucket(this.getSortBucket());
    }
}

