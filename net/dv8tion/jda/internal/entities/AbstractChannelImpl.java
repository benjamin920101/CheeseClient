/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.map.TLongObjectMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.managers.ChannelManagerImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.InviteActionImpl;
import net.dv8tion.jda.internal.requests.restaction.PermissionOverrideActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public abstract class AbstractChannelImpl<T extends GuildChannel, M extends AbstractChannelImpl<T, M>>
implements GuildChannel {
    protected final long id;
    protected final JDAImpl api;
    protected final TLongObjectMap<PermissionOverride> overrides = MiscUtil.newLongMap();
    protected ChannelManager manager;
    protected GuildImpl guild;
    protected long parentId;
    protected String name;
    protected int rawPosition;

    public AbstractChannelImpl(long id2, GuildImpl guild) {
        this.id = id2;
        this.api = guild.getJDA();
        this.guild = guild;
    }

    @Override
    public int compareTo(@Nonnull GuildChannel o2) {
        Checks.notNull(o2, "Channel");
        if (this.getType().getSortBucket() != o2.getType().getSortBucket()) {
            return Integer.compare(this.getType().getSortBucket(), o2.getType().getSortBucket());
        }
        if (this.getPositionRaw() != o2.getPositionRaw()) {
            return Integer.compare(this.getPositionRaw(), o2.getPositionRaw());
        }
        return Long.compareUnsigned(this.id, o2.getIdLong());
    }

    @Override
    @Nonnull
    public String getAsMention() {
        return "<#" + this.id + '>';
    }

    @Nonnull
    public abstract ChannelAction<T> createCopy(@Nonnull Guild var1);

    @Nonnull
    public ChannelAction<T> createCopy() {
        return this.createCopy(this.getGuild());
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    @Nonnull
    public GuildImpl getGuild() {
        GuildImpl realGuild = (GuildImpl)this.api.getGuildById(this.guild.getIdLong());
        if (realGuild != null) {
            this.guild = realGuild;
        }
        return this.guild;
    }

    @Override
    public Category getParent() {
        return (Category)this.getGuild().getCategoriesView().get(this.parentId);
    }

    @Override
    public int getPositionRaw() {
        return this.rawPosition;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    public PermissionOverride getPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        Checks.notNull(permissionHolder, "Permission Holder");
        Checks.check(permissionHolder.getGuild().equals(this.getGuild()), "Provided permission holder is not from the same guild as this channel!");
        return this.overrides.get(permissionHolder.getIdLong());
    }

    @Override
    @Nonnull
    public List<PermissionOverride> getPermissionOverrides() {
        return Arrays.asList(this.overrides.values((PermissionOverride[])new PermissionOverride[this.overrides.size()]));
    }

    @Override
    @Nonnull
    public List<PermissionOverride> getMemberPermissionOverrides() {
        return Collections.unmodifiableList(this.getPermissionOverrides().stream().filter(PermissionOverride::isMemberOverride).collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public List<PermissionOverride> getRolePermissionOverrides() {
        return Collections.unmodifiableList(this.getPermissionOverrides().stream().filter(PermissionOverride::isRoleOverride).collect(Collectors.toList()));
    }

    @Override
    public boolean isSynced() {
        AbstractChannelImpl parent = (AbstractChannelImpl)((Object)this.getParent());
        if (parent == null) {
            return true;
        }
        TLongObjectMap<PermissionOverride> parentOverrides = parent.getOverrideMap();
        if (parentOverrides.size() != this.overrides.size()) {
            return false;
        }
        for (PermissionOverride override : parentOverrides.valueCollection()) {
            PermissionOverride ourOverride = this.overrides.get(override.getIdLong());
            if (ourOverride == null) {
                return false;
            }
            if (ourOverride.getAllowedRaw() == override.getAllowedRaw() && ourOverride.getDeniedRaw() == override.getDeniedRaw()) continue;
            return false;
        }
        return true;
    }

    @Override
    @Nonnull
    public ChannelManager getManager() {
        if (this.manager == null) {
            this.manager = new ChannelManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    @Override
    @Nonnull
    public AuditableRestAction<Void> delete() {
        this.checkPermission(Permission.MANAGE_CHANNEL);
        Route.CompiledRoute route = Route.Channels.DELETE_CHANNEL.compile(this.getId());
        return new AuditableRestActionImpl<Void>(this.getJDA(), route);
    }

    @Override
    @Nonnull
    public PermissionOverrideAction createPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        Checks.notNull(permissionHolder, "PermissionHolder");
        if (this.getPermissionOverride(permissionHolder) != null) {
            throw new IllegalStateException("Provided member already has a PermissionOverride in this channel!");
        }
        return this.putPermissionOverride(permissionHolder);
    }

    @Override
    @Nonnull
    public PermissionOverrideAction putPermissionOverride(@Nonnull IPermissionHolder permissionHolder) {
        this.checkPermission(Permission.MANAGE_PERMISSIONS);
        Checks.notNull(permissionHolder, "PermissionHolder");
        Checks.check(permissionHolder.getGuild().equals(this.getGuild()), "Provided permission holder is not from the same guild as this channel!");
        return new PermissionOverrideActionImpl(this.getJDA(), this, permissionHolder);
    }

    @Override
    @Nonnull
    public InviteAction createInvite() {
        this.checkPermission(Permission.CREATE_INSTANT_INVITE);
        return new InviteActionImpl(this.getJDA(), this.getId());
    }

    @Override
    @Nonnull
    public RestAction<List<Invite>> retrieveInvites() {
        this.checkPermission(Permission.MANAGE_CHANNEL);
        Route.CompiledRoute route = Route.Invites.GET_CHANNEL_INVITES.compile(this.getId());
        JDAImpl jda = (JDAImpl)this.getJDA();
        return new RestActionImpl<List<Invite>>((JDA)jda, route, (response, request) -> {
            EntityBuilder entityBuilder = jda.getEntityBuilder();
            DataArray array = response.getArray();
            ArrayList<Invite> invites = new ArrayList<Invite>(array.length());
            for (int i2 = 0; i2 < array.length(); ++i2) {
                invites.add(entityBuilder.createInvite(array.getObject(i2)));
            }
            return Collections.unmodifiableList(invites);
        });
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GuildChannel)) {
            return false;
        }
        GuildChannel channel = (GuildChannel)obj;
        return channel.getIdLong() == this.getIdLong();
    }

    public TLongObjectMap<PermissionOverride> getOverrideMap() {
        return this.overrides;
    }

    public M setName(String name) {
        this.name = name;
        return (M)this;
    }

    public M setParent(long parentId) {
        this.parentId = parentId;
        return (M)this;
    }

    public M setPosition(int rawPosition) {
        this.rawPosition = rawPosition;
        return (M)this;
    }

    protected void checkAccess() {
        Member selfMember = this.getGuild().getSelfMember();
        if (!selfMember.hasPermission((GuildChannel)this, Permission.VIEW_CHANNEL)) {
            throw new MissingAccessException(this, Permission.VIEW_CHANNEL);
        }
        if (!selfMember.hasAccess(this)) {
            throw new MissingAccessException(this, Permission.VOICE_CONNECT);
        }
    }

    protected void checkPermission(Permission permission) {
        this.checkPermission(permission, null);
    }

    protected void checkPermission(Permission permission, String message) {
        this.checkAccess();
        if (!this.getGuild().getSelfMember().hasPermission((GuildChannel)this, permission)) {
            if (message != null) {
                throw new InsufficientPermissionException(this, permission, message);
            }
            throw new InsufficientPermissionException(this, permission);
        }
    }
}

