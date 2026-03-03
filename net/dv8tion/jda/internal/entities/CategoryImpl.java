/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.requests.restaction.order.CategoryOrderAction;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.requests.CompletedRestAction;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.PermissionUtil;

public class CategoryImpl
extends AbstractChannelImpl<Category, CategoryImpl>
implements Category {
    public CategoryImpl(long id2, GuildImpl guild) {
        super(id2, guild);
    }

    @Override
    public CategoryImpl setPosition(int rawPosition) {
        this.getGuild().getCategoriesView().clearCachedLists();
        return (CategoryImpl)super.setPosition(rawPosition);
    }

    @Override
    public Category getParent() {
        return null;
    }

    @Override
    @Nonnull
    public ChannelType getType() {
        return ChannelType.CATEGORY;
    }

    @Override
    @Nonnull
    public List<Member> getMembers() {
        return Collections.unmodifiableList(this.getChannels().stream().map(GuildChannel::getMembers).flatMap(Collection::stream).distinct().collect(Collectors.toList()));
    }

    @Override
    public int getPosition() {
        List<Category> channels = this.getGuild().getCategories();
        for (int i2 = 0; i2 < channels.size(); ++i2) {
            if (!this.equals(channels.get(i2))) continue;
            return i2;
        }
        throw new IllegalStateException("Somehow when determining position we never found the Category in the Guild's channels? wtf?");
    }

    @Override
    @Nonnull
    public ChannelAction<Category> createCopy(@Nonnull Guild guild) {
        Checks.notNull(guild, "Guild");
        ChannelAction<Category> action = guild.createCategory(this.name);
        if (guild.equals(this.getGuild())) {
            for (PermissionOverride o2 : this.overrides.valueCollection()) {
                if (o2.isMemberOverride()) {
                    action.addMemberPermissionOverride(o2.getIdLong(), o2.getAllowedRaw(), o2.getDeniedRaw());
                    continue;
                }
                action.addRolePermissionOverride(o2.getIdLong(), o2.getAllowedRaw(), o2.getDeniedRaw());
            }
        }
        return action;
    }

    @Override
    @Nonnull
    public InviteAction createInvite() {
        throw new UnsupportedOperationException("Cannot create invites for category!");
    }

    @Override
    @Nonnull
    public RestAction<List<Invite>> retrieveInvites() {
        return new CompletedRestAction<List<Invite>>(this.getJDA(), Collections.emptyList());
    }

    @Override
    @Nonnull
    public List<GuildChannel> getChannels() {
        ArrayList<GuildChannel> channels = new ArrayList<GuildChannel>();
        channels.addAll(this.getStoreChannels());
        channels.addAll(this.getTextChannels());
        channels.addAll(this.getVoiceChannels());
        Collections.sort(channels);
        return Collections.unmodifiableList(channels);
    }

    @Override
    @Nonnull
    public List<StoreChannel> getStoreChannels() {
        return Collections.unmodifiableList(this.getGuild().getStoreChannelCache().stream().filter(channel -> this.equals(channel.getParent())).sorted().collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public List<TextChannel> getTextChannels() {
        return Collections.unmodifiableList(this.getGuild().getTextChannels().stream().filter(channel -> this.equals(channel.getParent())).sorted().collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public List<VoiceChannel> getVoiceChannels() {
        return Collections.unmodifiableList(this.getGuild().getVoiceChannels().stream().filter(channel -> this.equals(channel.getParent())).sorted().collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public ChannelAction<TextChannel> createTextChannel(@Nonnull String name) {
        ChannelAction<TextChannel> action = this.getGuild().createTextChannel(name, this);
        return this.trySync(action);
    }

    @Override
    @Nonnull
    public ChannelAction<VoiceChannel> createVoiceChannel(@Nonnull String name) {
        ChannelAction<VoiceChannel> action = this.getGuild().createVoiceChannel(name, this);
        return this.trySync(action);
    }

    private <T extends GuildChannel> ChannelAction<T> trySync(ChannelAction<T> action) {
        Member selfMember = this.getGuild().getSelfMember();
        if (!selfMember.canSync(this)) {
            long botPerms = PermissionUtil.getEffectivePermission((GuildChannel)this, selfMember);
            for (PermissionOverride override : this.getPermissionOverrides()) {
                long perms = override.getDeniedRaw() | override.getAllowedRaw();
                if ((perms & (botPerms ^ 0xFFFFFFFFFFFFFFFFL)) == 0L) continue;
                return action;
            }
        }
        return action.syncPermissionOverrides();
    }

    @Override
    @Nonnull
    public CategoryOrderAction modifyTextChannelPositions() {
        return this.getGuild().modifyTextChannelPositions(this);
    }

    @Override
    @Nonnull
    public CategoryOrderAction modifyVoiceChannelPositions() {
        return this.getGuild().modifyVoiceChannelPositions(this);
    }

    public String toString() {
        return "GC:" + this.getName() + '(' + this.id + ')';
    }
}

