/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.awt.Color;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.managers.RoleManager;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.RoleAction;

public interface Role
extends IMentionable,
IPermissionHolder,
Comparable<Role> {
    public static final int DEFAULT_COLOR_RAW = 0x1FFFFFFF;

    public int getPosition();

    public int getPositionRaw();

    @Nonnull
    public String getName();

    public boolean isManaged();

    public boolean isHoisted();

    public boolean isMentionable();

    public long getPermissionsRaw();

    @Nullable
    public Color getColor();

    public int getColorRaw();

    public boolean isPublicRole();

    public boolean canInteract(@Nonnull Role var1);

    @Override
    @Nonnull
    public Guild getGuild();

    @Nonnull
    @CheckReturnValue
    public RoleAction createCopy(@Nonnull Guild var1);

    @Nonnull
    @CheckReturnValue
    default public RoleAction createCopy() {
        return this.createCopy(this.getGuild());
    }

    @Nonnull
    public RoleManager getManager();

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public RoleTags getTags();

    public static interface RoleTags {
        public boolean isBot();

        public long getBotIdLong();

        @Nullable
        default public String getBotId() {
            return this.isBot() ? Long.toUnsignedString(this.getBotIdLong()) : null;
        }

        public boolean isBoost();

        public boolean isIntegration();

        public long getIntegrationIdLong();

        @Nullable
        default public String getIntegrationId() {
            return this.isIntegration() ? Long.toUnsignedString(this.getIntegrationIdLong()) : null;
        }
    }
}

