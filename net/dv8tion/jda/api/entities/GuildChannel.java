/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.List;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.AbstractChannel;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;

public interface GuildChannel
extends AbstractChannel,
IMentionable,
Comparable<GuildChannel> {
    @Nonnull
    public Guild getGuild();

    @Nullable
    public Category getParent();

    @Nonnull
    public List<Member> getMembers();

    public int getPosition();

    public int getPositionRaw();

    @Nullable
    public PermissionOverride getPermissionOverride(@Nonnull IPermissionHolder var1);

    @Nonnull
    public List<PermissionOverride> getPermissionOverrides();

    @Nonnull
    public List<PermissionOverride> getMemberPermissionOverrides();

    @Nonnull
    public List<PermissionOverride> getRolePermissionOverrides();

    public boolean isSynced();

    @Nonnull
    @CheckReturnValue
    public ChannelAction<? extends GuildChannel> createCopy(@Nonnull Guild var1);

    @Nonnull
    @CheckReturnValue
    default public ChannelAction<? extends GuildChannel> createCopy() {
        return this.createCopy(this.getGuild());
    }

    @Nonnull
    public ChannelManager getManager();

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction createPermissionOverride(@Nonnull IPermissionHolder var1);

    @Nonnull
    @CheckReturnValue
    public PermissionOverrideAction putPermissionOverride(@Nonnull IPermissionHolder var1);

    @Nonnull
    @CheckReturnValue
    default public PermissionOverrideAction upsertPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        PermissionOverride override = this.getPermissionOverride(permissionHolder);
        if (override != null) {
            return override.getManager();
        }
        return this.putPermissionOverride(permissionHolder);
    }

    @Nonnull
    @CheckReturnValue
    public InviteAction createInvite();

    @Nonnull
    @CheckReturnValue
    public RestAction<List<Invite>> retrieveInvites();
}

