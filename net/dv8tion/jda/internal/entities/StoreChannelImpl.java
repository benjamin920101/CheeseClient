/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;

public class StoreChannelImpl
extends AbstractChannelImpl<StoreChannel, StoreChannelImpl>
implements StoreChannel {
    public StoreChannelImpl(long id2, GuildImpl guild) {
        super(id2, guild);
    }

    @Override
    public StoreChannelImpl setPosition(int rawPosition) {
        this.getGuild().getStoreChannelView().clearCachedLists();
        return (StoreChannelImpl)super.setPosition(rawPosition);
    }

    @Override
    @Nonnull
    public ChannelType getType() {
        return ChannelType.STORE;
    }

    @Override
    @Nonnull
    public List<Member> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public int getPosition() {
        ArrayList<TextChannel> channels = new ArrayList<TextChannel>(this.getGuild().getTextChannels());
        channels.addAll(this.getGuild().getStoreChannels());
        Collections.sort(channels);
        for (int i2 = 0; i2 < channels.size(); ++i2) {
            if (!this.equals(channels.get(i2))) continue;
            return i2;
        }
        throw new IllegalStateException("Somehow when determining position we never found the StoreChannel in the Guild's channels? wtf?");
    }

    @Override
    @Nonnull
    public ChannelAction<StoreChannel> createCopy(@Nonnull Guild guild) {
        throw new UnsupportedOperationException("Bots cannot create store channels");
    }

    public String toString() {
        return "SC:" + this.getName() + '(' + this.getId() + ')';
    }
}

