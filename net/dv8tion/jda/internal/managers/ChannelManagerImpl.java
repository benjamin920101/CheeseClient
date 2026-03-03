/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;
import java.util.Collection;
import java.util.EnumSet;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.MissingAccessException;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.managers.ManagerBase;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.PermOverrideData;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import okhttp3.RequestBody;

public class ChannelManagerImpl
extends ManagerBase<ChannelManager>
implements ChannelManager {
    protected GuildChannel channel;
    protected String name;
    protected String parent;
    protected String topic;
    protected String region;
    protected int position;
    protected boolean nsfw;
    protected int slowmode;
    protected int userlimit;
    protected int bitrate;
    protected boolean news;
    protected final Object lock = new Object();
    protected final TLongObjectHashMap<PermOverrideData> overridesAdd;
    protected final TLongSet overridesRem;

    public ChannelManagerImpl(GuildChannel channel) {
        super(channel.getJDA(), Route.Channels.MODIFY_CHANNEL.compile(channel.getId()));
        JDA jda = channel.getJDA();
        ChannelType type = channel.getType();
        this.channel = channel;
        if (ChannelManagerImpl.isPermissionChecksEnabled()) {
            this.checkPermissions();
        }
        this.overridesAdd = new TLongObjectHashMap();
        this.overridesRem = new TLongHashSet();
    }

    @Override
    @Nonnull
    public GuildChannel getChannel() {
        GuildChannel realChannel = this.api.getGuildChannelById(this.channel.getType(), this.channel.getIdLong());
        if (realChannel != null) {
            this.channel = realChannel;
        }
        return this.channel;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl reset(long fields) {
        super.reset(fields);
        if ((fields & 1L) == 1L) {
            this.name = null;
        }
        if ((fields & 2L) == 2L) {
            this.parent = null;
        }
        if ((fields & 4L) == 4L) {
            this.topic = null;
        }
        if ((fields & 0x200L) == 512L) {
            this.news = false;
        }
        if ((fields & 0x400L) == 1024L) {
            this.region = null;
        }
        if ((fields & 0x80L) == 128L) {
            this.withLock(this.lock, lock -> {
                this.overridesRem.clear();
                this.overridesAdd.clear();
            });
        }
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl reset(long ... fields) {
        super.reset(fields);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl reset() {
        super.reset();
        this.name = null;
        this.parent = null;
        this.topic = null;
        this.region = null;
        this.news = false;
        this.withLock(this.lock, lock -> {
            this.overridesRem.clear();
            this.overridesAdd.clear();
        });
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl clearOverridesAdded() {
        this.withLock(this.lock, lock -> {
            this.overridesAdd.clear();
            if (this.overridesRem.isEmpty()) {
                this.set &= 0xFFFFFFFFFFFFFF7FL;
            }
        });
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl clearOverridesRemoved() {
        this.withLock(this.lock, lock -> {
            this.overridesRem.clear();
            if (this.overridesAdd.isEmpty()) {
                this.set &= 0xFFFFFFFFFFFFFF7FL;
            }
        });
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl putPermissionOverride(@Nonnull IPermissionHolder permHolder, long allow, long deny) {
        Checks.notNull(permHolder, "PermissionHolder");
        Checks.check(permHolder.getGuild().equals(this.getGuild()), "PermissionHolder is not from the same Guild!");
        Member selfMember = this.getGuild().getSelfMember();
        if (ChannelManagerImpl.isPermissionChecksEnabled() && !selfMember.hasPermission(Permission.ADMINISTRATOR)) {
            long botPerms;
            EnumSet<Permission> missing;
            if (!selfMember.hasPermission(this.channel, Permission.MANAGE_ROLES)) {
                throw new InsufficientPermissionException(this.channel, Permission.MANAGE_PERMISSIONS);
            }
            long channelPermissions = PermissionUtil.getExplicitPermission(this.channel, selfMember, false);
            if ((channelPermissions & Permission.MANAGE_PERMISSIONS.getRawValue()) == 0L && !(missing = Permission.getPermissions((allow | deny) & ((botPerms = PermissionUtil.getEffectivePermission(this.channel, selfMember) & (Permission.MANAGE_ROLES.getRawValue() ^ 0xFFFFFFFFFFFFFFFFL)) ^ 0xFFFFFFFFFFFFFFFFL))).isEmpty()) {
                throw new InsufficientPermissionException(this.channel, Permission.MANAGE_PERMISSIONS, "You must have Permission.MANAGE_PERMISSIONS on the channel explicitly in order to set permissions you don't already have!");
            }
        }
        long id2 = this.getId(permHolder);
        int type = permHolder instanceof Role ? 0 : 1;
        this.withLock(this.lock, lock -> {
            this.overridesRem.remove(id2);
            this.overridesAdd.put(id2, new PermOverrideData(type, id2, allow, deny));
            this.set |= 0x80L;
        });
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl removePermissionOverride(@Nonnull IPermissionHolder permHolder) {
        Checks.notNull(permHolder, "PermissionHolder");
        Checks.check(permHolder.getGuild().equals(this.getGuild()), "PermissionHolder is not from the same Guild!");
        if (ChannelManagerImpl.isPermissionChecksEnabled() && !this.getGuild().getSelfMember().hasPermission(this.getChannel(), Permission.MANAGE_PERMISSIONS)) {
            throw new InsufficientPermissionException(this.getChannel(), Permission.MANAGE_PERMISSIONS);
        }
        long id2 = this.getId(permHolder);
        this.withLock(this.lock, lock -> {
            this.overridesRem.add(id2);
            this.overridesAdd.remove(id2);
            this.set |= 0x80L;
        });
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl sync(@Nonnull GuildChannel syncSource) {
        Checks.notNull(syncSource, "SyncSource");
        Checks.check(this.getGuild().equals(syncSource.getGuild()), "Sync only works for channels of same guild");
        if (syncSource.equals(this.getChannel())) {
            return this;
        }
        if (ChannelManagerImpl.isPermissionChecksEnabled()) {
            Member selfMember = this.getGuild().getSelfMember();
            if (!selfMember.hasPermission(this.getChannel(), Permission.MANAGE_PERMISSIONS)) {
                throw new InsufficientPermissionException(this.getChannel(), Permission.MANAGE_PERMISSIONS);
            }
            if (!selfMember.canSync(this.channel, syncSource)) {
                throw new InsufficientPermissionException(this.getChannel(), Permission.MANAGE_PERMISSIONS, "Cannot sync channel with parent due to permission escalation issues. One of the overrides would set MANAGE_PERMISSIONS or a permission that the bot does not have. This is not possible without explicitly having MANAGE_PERMISSIONS on this channel or ADMINISTRATOR on a role.");
            }
        }
        this.withLock(this.lock, lock -> {
            this.overridesRem.clear();
            this.overridesAdd.clear();
            this.getChannel().getPermissionOverrides().stream().mapToLong(ISnowflake::getIdLong).forEach(this.overridesRem::add);
            syncSource.getPermissionOverrides().forEach(override -> {
                int type = override.isRoleOverride() ? 0 : 1;
                long id2 = override.getIdLong();
                this.overridesRem.remove(id2);
                this.overridesAdd.put(id2, new PermOverrideData(type, id2, override.getAllowedRaw(), override.getDeniedRaw()));
            });
            this.set |= 0x80L;
        });
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setName(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        this.name = name;
        this.set |= 1L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setRegion(@Nonnull Region region) {
        Checks.notNull((Object)region, "Region");
        if (this.getType() != ChannelType.VOICE) {
            throw new IllegalStateException("Can only change region on voice channels!");
        }
        Checks.check(Region.VOICE_CHANNEL_REGIONS.contains((Object)region), "Region is not usable for VoiceChannel region overrides!");
        this.region = region == Region.AUTOMATIC ? null : region.getKey();
        this.set |= 0x400L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setParent(Category category) {
        if (category != null) {
            if (this.getType() == ChannelType.CATEGORY) {
                throw new IllegalStateException("Cannot set the parent of a category");
            }
            Checks.check(category.getGuild().equals(this.getGuild()), "Category is not from the same guild");
        }
        this.parent = category == null ? null : category.getId();
        this.set |= 2L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setPosition(int position) {
        this.position = position;
        this.set |= 8L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setTopic(String topic) {
        if (this.getType() != ChannelType.TEXT) {
            throw new IllegalStateException("Can only set topic on text channels");
        }
        if (topic != null) {
            Checks.notLonger(topic, 1024, "Topic");
        }
        this.topic = topic;
        this.set |= 4L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setNSFW(boolean nsfw) {
        if (this.getType() != ChannelType.TEXT) {
            throw new IllegalStateException("Can only set nsfw on text channels");
        }
        this.nsfw = nsfw;
        this.set |= 0x10L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setSlowmode(int slowmode) {
        if (this.getType() != ChannelType.TEXT) {
            throw new IllegalStateException("Can only set slowmode on text channels");
        }
        Checks.check(slowmode <= 21600 && slowmode >= 0, "Slowmode per user must be between 0 and %d (seconds)!", (Object)21600);
        this.slowmode = slowmode;
        this.set |= 0x100L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setUserLimit(int userLimit) {
        if (this.getType() != ChannelType.VOICE) {
            throw new IllegalStateException("Can only set userlimit on voice channels");
        }
        Checks.notNegative(userLimit, "Userlimit");
        Checks.check(userLimit <= 99, "Userlimit may not be greater than 99");
        this.userlimit = userLimit;
        this.set |= 0x20L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setBitrate(int bitrate) {
        if (this.getType() != ChannelType.VOICE) {
            throw new IllegalStateException("Can only set bitrate on voice channels");
        }
        int maxBitrate = this.getGuild().getMaxBitrate();
        Checks.check(bitrate >= 8000, "Bitrate must be greater or equal to 8000");
        Checks.check(bitrate <= maxBitrate, "Bitrate must be less or equal to %s", (Object)maxBitrate);
        this.bitrate = bitrate;
        this.set |= 0x40L;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public ChannelManagerImpl setNews(boolean news) {
        if (this.getType() != ChannelType.TEXT) {
            throw new IllegalStateException("Can only set channel as news on text channels");
        }
        if (news && !this.getGuild().getFeatures().contains("NEWS")) {
            throw new IllegalStateException("Can only set channel as news for guilds with NEWS feature");
        }
        this.news = news;
        this.set |= 0x200L;
        return this;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject frame = DataObject.empty().put("name", this.getChannel().getName());
        if (this.shouldUpdate(1L)) {
            frame.put("name", this.name);
        }
        if (this.shouldUpdate(8L)) {
            frame.put("position", this.position);
        }
        if (this.shouldUpdate(4L)) {
            frame.put("topic", this.topic);
        }
        if (this.shouldUpdate(16L)) {
            frame.put("nsfw", this.nsfw);
        }
        if (this.shouldUpdate(256L)) {
            frame.put("rate_limit_per_user", this.slowmode);
        }
        if (this.shouldUpdate(32L)) {
            frame.put("user_limit", this.userlimit);
        }
        if (this.shouldUpdate(64L)) {
            frame.put("bitrate", this.bitrate);
        }
        if (this.shouldUpdate(2L)) {
            frame.put("parent_id", this.parent);
        }
        if (this.shouldUpdate(512L)) {
            frame.put("type", this.news ? 5 : 0);
        }
        if (this.shouldUpdate(1024L)) {
            frame.put("rtc_region", this.region);
        }
        this.withLock(this.lock, lock -> {
            if (this.shouldUpdate(128L)) {
                frame.put("permission_overwrites", this.getOverrides());
            }
        });
        this.reset();
        return this.getRequestBody(frame);
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
        if (!selfMember.hasPermission(channel, Permission.MANAGE_CHANNEL)) {
            throw new InsufficientPermissionException(channel, Permission.MANAGE_CHANNEL);
        }
        return super.checkPermissions();
    }

    protected Collection<PermOverrideData> getOverrides() {
        TLongObjectHashMap<PermOverrideData> data = new TLongObjectHashMap<PermOverrideData>(this.overridesAdd);
        AbstractChannelImpl impl = (AbstractChannelImpl)this.getChannel();
        impl.getOverrideMap().forEachEntry((id2, override) -> {
            if (!this.overridesRem.remove(id2) && !data.containsKey(id2)) {
                data.put(id2, new PermOverrideData((PermissionOverride)override));
            }
            return true;
        });
        return data.valueCollection();
    }

    protected long getId(IPermissionHolder holder) {
        if (holder instanceof Role) {
            return ((Role)holder).getIdLong();
        }
        return ((Member)holder).getUser().getIdLong();
    }
}

