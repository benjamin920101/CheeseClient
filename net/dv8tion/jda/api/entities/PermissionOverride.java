/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.EnumSet;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;

public interface PermissionOverride
extends ISnowflake {
    public long getAllowedRaw();

    public long getInheritRaw();

    public long getDeniedRaw();

    @Nonnull
    public EnumSet<Permission> getAllowed();

    @Nonnull
    public EnumSet<Permission> getInherit();

    @Nonnull
    public EnumSet<Permission> getDenied();

    @Nonnull
    public JDA getJDA();

    @Nullable
    public IPermissionHolder getPermissionHolder();

    @Nullable
    public Member getMember();

    @Nullable
    public Role getRole();

    @Nonnull
    public GuildChannel getChannel();

    @Nonnull
    public Guild getGuild();

    public boolean isMemberOverride();

    public boolean isRoleOverride();

    @Nonnull
    public PermissionOverrideAction getManager();

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();
}

