/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ApplicationTeam;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.utils.Checks;

public interface ApplicationInfo
extends ISnowflake {
    public boolean doesBotRequireCodeGrant();

    @Nonnull
    public String getDescription();

    @Nullable
    public String getIconId();

    @Nullable
    public String getIconUrl();

    @Nullable
    public ApplicationTeam getTeam();

    @Nonnull
    default public ApplicationInfo setRequiredScopes(String ... scopes) {
        Checks.noneNull(scopes, "Scopes");
        return this.setRequiredScopes(Arrays.asList(scopes));
    }

    @Nonnull
    public ApplicationInfo setRequiredScopes(@Nonnull Collection<String> var1);

    @Nonnull
    default public String getInviteUrl(@Nullable Collection<Permission> permissions) {
        return this.getInviteUrl(null, permissions);
    }

    @Nonnull
    default public String getInviteUrl(Permission ... permissions) {
        return this.getInviteUrl((String)null, permissions);
    }

    @Nonnull
    public String getInviteUrl(@Nullable String var1, @Nullable Collection<Permission> var2);

    @Nonnull
    default public String getInviteUrl(long guildId, @Nullable Collection<Permission> permissions) {
        return this.getInviteUrl(Long.toUnsignedString(guildId), permissions);
    }

    @Nonnull
    default public String getInviteUrl(@Nullable String guildId, Permission ... permissions) {
        return this.getInviteUrl(guildId, permissions == null ? null : Arrays.asList(permissions));
    }

    @Nonnull
    default public String getInviteUrl(long guildId, Permission ... permissions) {
        return this.getInviteUrl(Long.toUnsignedString(guildId), permissions);
    }

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public String getName();

    @Nonnull
    public User getOwner();

    public boolean isBotPublic();
}

