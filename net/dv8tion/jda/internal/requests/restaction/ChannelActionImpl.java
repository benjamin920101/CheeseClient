/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.PermOverrideData;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import okhttp3.RequestBody;

public class ChannelActionImpl<T extends GuildChannel>
extends AuditableRestActionImpl<T>
implements ChannelAction<T> {
    protected final TLongObjectMap<PermOverrideData> overrides = new TLongObjectHashMap<PermOverrideData>();
    protected final Guild guild;
    protected final ChannelType type;
    protected final Class<T> clazz;
    protected String name;
    protected Category parent;
    protected Integer position;
    protected String topic = null;
    protected Boolean nsfw = null;
    protected Integer slowmode = null;
    protected Boolean news = null;
    protected Integer bitrate = null;
    protected Integer userlimit = null;

    public ChannelActionImpl(Class<T> clazz, String name, Guild guild, ChannelType type) {
        super(guild.getJDA(), Route.Guilds.CREATE_CHANNEL.compile(guild.getId()));
        this.clazz = clazz;
        this.guild = guild;
        this.type = type;
        this.name = name;
    }

    @Override
    @Nonnull
    public ChannelActionImpl<T> setCheck(BooleanSupplier checks) {
        return (ChannelActionImpl)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public ChannelActionImpl<T> timeout(long timeout, @Nonnull TimeUnit unit) {
        return (ChannelActionImpl)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public ChannelActionImpl<T> deadline(long timestamp) {
        return (ChannelActionImpl)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    public Guild getGuild() {
        return this.guild;
    }

    @Override
    @Nonnull
    public ChannelType getType() {
        return this.type;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setName(@Nonnull String name) {
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        this.name = name;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setParent(Category category) {
        Checks.check(category == null || category.getGuild().equals(this.guild), "Category is not from same guild!");
        this.parent = category;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setPosition(Integer position) {
        Checks.check(position == null || position >= 0, "Position must be >= 0!");
        this.position = position;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setTopic(String topic) {
        if (this.type != ChannelType.TEXT) {
            throw new UnsupportedOperationException("Can only set the topic for a TextChannel!");
        }
        if (topic != null && topic.length() > 1024) {
            throw new IllegalArgumentException("Channel Topic must not be greater than 1024 in length!");
        }
        this.topic = topic;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setNSFW(boolean nsfw) {
        if (this.type != ChannelType.TEXT) {
            throw new UnsupportedOperationException("Can only set nsfw for a TextChannel!");
        }
        this.nsfw = nsfw;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setSlowmode(int slowmode) {
        if (this.type != ChannelType.TEXT) {
            throw new UnsupportedOperationException("Can only set slowmode on text channels");
        }
        Checks.check(slowmode <= 21600 && slowmode >= 0, "Slowmode must be between 0 and %d (seconds)!", (Object)21600);
        this.slowmode = slowmode;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setNews(boolean news) {
        if (this.type != ChannelType.TEXT) {
            throw new UnsupportedOperationException("Can only set news for a TextChannel!");
        }
        if (news && !this.getGuild().getFeatures().contains("NEWS")) {
            throw new IllegalStateException("Can only set channel as news for guilds with NEWS feature");
        }
        this.news = news;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> addMemberPermissionOverride(long userId, long allow, long deny) {
        return this.addOverride(userId, 1, allow, deny);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> addRolePermissionOverride(long roleId, long allow, long deny) {
        return this.addOverride(roleId, 0, allow, deny);
    }

    @Override
    @Nonnull
    public ChannelAction<T> removePermissionOverride(long id2) {
        this.overrides.remove(id2);
        return this;
    }

    @Override
    @Nonnull
    public ChannelAction<T> clearPermissionOverrides() {
        this.overrides.clear();
        return this;
    }

    @Override
    @Nonnull
    public ChannelAction<T> syncPermissionOverrides() {
        if (this.parent == null) {
            throw new IllegalStateException("Cannot sync overrides without parent category! Use setParent(category) first!");
        }
        this.clearPermissionOverrides();
        Member selfMember = this.getGuild().getSelfMember();
        boolean canSetRoles = selfMember.hasPermission((GuildChannel)this.parent, Permission.MANAGE_ROLES);
        long botPerms = PermissionUtil.getEffectivePermission(selfMember) & (Permission.MANAGE_PERMISSIONS.getRawValue() ^ 0xFFFFFFFFFFFFFFFFL);
        this.parent.getRolePermissionOverrides().forEach(override -> {
            long allow = override.getAllowedRaw();
            long deny = override.getDeniedRaw();
            if (!canSetRoles) {
                allow &= botPerms;
                deny &= botPerms;
            }
            this.addRolePermissionOverride(override.getIdLong(), allow, deny);
        });
        this.parent.getMemberPermissionOverrides().forEach(override -> {
            long allow = override.getAllowedRaw();
            long deny = override.getDeniedRaw();
            if (!canSetRoles) {
                allow &= botPerms;
                deny &= botPerms;
            }
            this.addMemberPermissionOverride(override.getIdLong(), allow, deny);
        });
        return this;
    }

    private ChannelActionImpl<T> addOverride(long targetId, int type, long allow, long deny) {
        long botPerms;
        EnumSet<Permission> missingPerms;
        Member selfMember = this.getGuild().getSelfMember();
        boolean canSetRoles = selfMember.hasPermission(Permission.ADMINISTRATOR);
        if (!canSetRoles && this.parent != null) {
            canSetRoles = selfMember.hasPermission((GuildChannel)this.parent, Permission.MANAGE_ROLES);
        }
        if (!canSetRoles && !(missingPerms = Permission.getPermissions((allow | deny) & ((botPerms = PermissionUtil.getEffectivePermission(selfMember) & (Permission.MANAGE_PERMISSIONS.getRawValue() ^ 0xFFFFFFFFFFFFFFFFL)) ^ 0xFFFFFFFFFFFFFFFFL))).isEmpty()) {
            throw new InsufficientPermissionException(this.guild, Permission.MANAGE_PERMISSIONS, "You must have Permission.MANAGE_PERMISSIONS on the channel explicitly in order to set permissions you don't already have!");
        }
        this.overrides.put(targetId, new PermOverrideData(type, targetId, allow, deny));
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setBitrate(Integer bitrate) {
        if (this.type != ChannelType.VOICE) {
            throw new UnsupportedOperationException("Can only set the bitrate for a VoiceChannel!");
        }
        if (bitrate != null) {
            int maxBitrate = this.getGuild().getMaxBitrate();
            if (bitrate < 8000) {
                throw new IllegalArgumentException("Bitrate must be greater than 8000.");
            }
            if (bitrate > maxBitrate) {
                throw new IllegalArgumentException("Bitrate must be less than " + maxBitrate);
            }
        }
        this.bitrate = bitrate;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelActionImpl<T> setUserlimit(Integer userlimit) {
        if (this.type != ChannelType.VOICE) {
            throw new UnsupportedOperationException("Can only set the userlimit for a VoiceChannel!");
        }
        if (userlimit != null && (userlimit < 0 || userlimit > 99)) {
            throw new IllegalArgumentException("Userlimit must be between 0-99!");
        }
        this.userlimit = userlimit;
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject object = DataObject.empty();
        object.put("name", this.name);
        object.put("type", this.type.getId());
        object.put("permission_overwrites", DataArray.fromCollection(this.overrides.valueCollection()));
        if (this.position != null) {
            object.put("position", this.position);
        }
        switch (this.type) {
            case VOICE: {
                if (this.bitrate != null) {
                    object.put("bitrate", this.bitrate);
                }
                if (this.userlimit == null) break;
                object.put("user_limit", this.userlimit);
                break;
            }
            case TEXT: {
                if (this.topic != null && !this.topic.isEmpty()) {
                    object.put("topic", this.topic);
                }
                if (this.nsfw != null) {
                    object.put("nsfw", this.nsfw);
                }
                if (this.slowmode != null) {
                    object.put("rate_limit_per_user", this.slowmode);
                }
                if (this.news == null) break;
                object.put("type", this.news != false ? 5 : 0);
            }
        }
        if (this.type != ChannelType.CATEGORY && this.parent != null) {
            object.put("parent_id", this.parent.getId());
        }
        return this.getRequestBody(object);
    }

    @Override
    protected void handleSuccess(Response response, Request<T> request) {
        GuildChannel channel;
        EntityBuilder builder = this.api.getEntityBuilder();
        switch (this.type) {
            case VOICE: {
                channel = builder.createVoiceChannel(response.getObject(), this.guild.getIdLong());
                break;
            }
            case TEXT: {
                channel = builder.createTextChannel(response.getObject(), this.guild.getIdLong());
                break;
            }
            case CATEGORY: {
                channel = builder.createCategory(response.getObject(), this.guild.getIdLong());
                break;
            }
            default: {
                request.onFailure(new IllegalStateException("Created channel of unknown type!"));
                return;
            }
        }
        request.onSuccess((GuildChannel)this.clazz.cast(channel));
    }
}

