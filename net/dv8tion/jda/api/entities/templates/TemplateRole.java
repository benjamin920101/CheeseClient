/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities.templates;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.EnumSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ISnowflake;

public class TemplateRole
implements ISnowflake {
    private final long id;
    private final String name;
    private final int color;
    private final boolean hoisted;
    private final boolean mentionable;
    private final long rawPermissions;

    public TemplateRole(long id2, String name, int color, boolean hoisted, boolean mentionable, long rawPermissions) {
        this.id = id2;
        this.name = name;
        this.color = color;
        this.hoisted = hoisted;
        this.mentionable = mentionable;
        this.rawPermissions = rawPermissions;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Override
    public OffsetDateTime getTimeCreated() {
        throw new UnsupportedOperationException("The date of creation cannot be calculated");
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nullable
    public Color getColor() {
        return this.color == 0x1FFFFFFF ? null : new Color(this.color);
    }

    public int getColorRaw() {
        return this.color;
    }

    public boolean isHoisted() {
        return this.hoisted;
    }

    public boolean isMentionable() {
        return this.mentionable;
    }

    @Nonnull
    public EnumSet<Permission> getPermissions() {
        return Permission.getPermissions(this.rawPermissions);
    }

    public long getPermissionsRaw() {
        return this.rawPermissions;
    }
}

