/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.managers.PermOverrideManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.managers.ManagerBase;
import net.dv8tion.jda.internal.requests.Route;
import okhttp3.RequestBody;

public class PermOverrideManagerImpl
extends ManagerBase<PermOverrideManager>
implements PermOverrideManager {
    protected final boolean role;
    protected PermissionOverride override;
    protected long allowed;
    protected long denied;

    public PermOverrideManagerImpl(PermissionOverride override) {
        super(override.getJDA(), Route.Channels.MODIFY_PERM_OVERRIDE.compile(override.getChannel().getId(), override.getId()));
        this.override = override;
        this.role = override.isRoleOverride();
        this.allowed = override.getAllowedRaw();
        this.denied = override.getDeniedRaw();
        if (PermOverrideManagerImpl.isPermissionChecksEnabled()) {
            this.checkPermissions();
        }
    }

    private void setupValues() {
        if (!this.shouldUpdate(2L)) {
            this.allowed = this.getPermissionOverride().getAllowedRaw();
        }
        if (!this.shouldUpdate(1L)) {
            this.denied = this.getPermissionOverride().getDeniedRaw();
        }
    }

    @Override
    @Nonnull
    public PermissionOverride getPermissionOverride() {
        AbstractChannelImpl channel = (AbstractChannelImpl)this.override.getChannel();
        PermissionOverride realOverride = channel.getOverrideMap().get(this.override.getIdLong());
        if (realOverride != null) {
            this.override = realOverride;
        }
        return this.override;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermOverrideManagerImpl reset(long fields) {
        super.reset(fields);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermOverrideManagerImpl reset(long ... fields) {
        super.reset(fields);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermOverrideManagerImpl reset() {
        super.reset();
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermOverrideManagerImpl grant(long permissions) {
        if (permissions == 0L) {
            return this;
        }
        this.setupValues();
        this.allowed |= permissions;
        this.denied &= permissions ^ 0xFFFFFFFFFFFFFFFFL;
        this.set |= 3L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermOverrideManagerImpl deny(long permissions) {
        if (permissions == 0L) {
            return this;
        }
        this.setupValues();
        this.denied |= permissions;
        this.allowed &= permissions ^ 0xFFFFFFFFFFFFFFFFL;
        this.set |= 3L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public PermOverrideManagerImpl clear(long permissions) {
        this.setupValues();
        if ((this.allowed & permissions) != 0L) {
            this.allowed &= permissions ^ 0xFFFFFFFFFFFFFFFFL;
            this.set |= 2L;
        }
        if ((this.denied & permissions) != 0L) {
            this.denied &= permissions ^ 0xFFFFFFFFFFFFFFFFL;
            this.set |= 1L;
        }
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        String targetId = this.override.getId();
        this.setupValues();
        RequestBody data = this.getRequestBody(DataObject.empty().put("id", targetId).put("type", this.role ? "role" : "member").put("allow", this.allowed).put("deny", this.denied));
        this.reset();
        return data;
    }

    @Override
    protected boolean checkPermissions() {
        Member selfMember = this.getGuild().getSelfMember();
        GuildChannel channel = this.getChannel();
        if (!selfMember.hasPermission(channel, Permission.VIEW_CHANNEL)) {
            throw new MissingAccessException(channel, Permission.VIEW_CHANNEL);
        }
        if (!selfMember.hasAccess(channel)) {
            throw new MissingAccessException(channel, Permission.VOICE_CONNECT);
        }
        if (!selfMember.hasPermission(channel, Permission.MANAGE_PERMISSIONS)) {
            throw new InsufficientPermissionException(channel, Permission.MANAGE_PERMISSIONS);
        }
        return super.checkPermissions();
    }
}

