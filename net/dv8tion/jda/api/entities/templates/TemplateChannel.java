/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities.templates;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.ISnowflake;

public class TemplateChannel
implements ISnowflake {
    private final long id;
    private final ChannelType channelType;
    private final String name;
    private final String topic;
    private final int rawPosition;
    private final long parentId;
    private final boolean isNews;
    private final List<PermissionOverride> permissionOverrides;
    private final boolean nsfw;
    private final int slowmode;
    private final int bitrate;
    private final int userLimit;

    public TemplateChannel(long id2, ChannelType channelType, String name, String topic, int rawPosition, long parentId, boolean news, List<PermissionOverride> permissionOverrides, boolean nsfw, int slowmode, int bitrate, int userLimit) {
        this.id = id2;
        this.channelType = channelType;
        this.name = name;
        this.topic = topic;
        this.rawPosition = rawPosition;
        this.parentId = parentId;
        this.isNews = news;
        this.permissionOverrides = Collections.unmodifiableList(permissionOverrides);
        this.nsfw = nsfw;
        this.slowmode = slowmode;
        this.bitrate = bitrate;
        this.userLimit = userLimit;
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
    public ChannelType getType() {
        return this.channelType;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getTopic() {
        return this.topic;
    }

    public int getPositionRaw() {
        return this.rawPosition;
    }

    public long getParentId() {
        return this.parentId;
    }

    public boolean isNSFW() {
        return this.nsfw;
    }

    public int getSlowmode() {
        return this.slowmode;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public int getUserLimit() {
        return this.userLimit;
    }

    public boolean isNews() {
        return this.isNews;
    }

    @Nonnull
    public List<PermissionOverride> getPermissionOverrides() {
        return this.permissionOverrides;
    }

    public static class PermissionOverride
    implements ISnowflake {
        private final long id;
        private final long allow;
        private final long deny;

        public PermissionOverride(long id2, long allow, long deny) {
            this.id = id2;
            this.allow = allow;
            this.deny = deny;
        }

        public long getAllowedRaw() {
            return this.allow;
        }

        public long getInheritRaw() {
            return (this.allow | this.deny) ^ 0xFFFFFFFFFFFFFFFFL;
        }

        public long getDeniedRaw() {
            return this.deny;
        }

        @Nonnull
        public EnumSet<Permission> getAllowed() {
            return Permission.getPermissions(this.allow);
        }

        @Nonnull
        public EnumSet<Permission> getInherit() {
            return Permission.getPermissions(this.getInheritRaw());
        }

        @Nonnull
        public EnumSet<Permission> getDenied() {
            return Permission.getPermissions(this.deny);
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Override
        public OffsetDateTime getTimeCreated() {
            throw new UnsupportedOperationException("The date of creation cannot be calculated");
        }
    }
}

