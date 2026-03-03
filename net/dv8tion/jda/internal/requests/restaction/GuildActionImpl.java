/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;

public class GuildActionImpl
extends RestActionImpl<Void>
implements GuildAction {
    protected String name;
    protected Region region;
    protected Icon icon;
    protected Guild.VerificationLevel verificationLevel;
    protected Guild.NotificationLevel notificationLevel;
    protected Guild.ExplicitContentLevel explicitContentLevel;
    protected final List<GuildAction.RoleData> roles;
    protected final List<GuildAction.ChannelData> channels;

    public GuildActionImpl(JDA api2, String name) {
        super(api2, Route.Guilds.CREATE_GUILD.compile(new String[0]));
        this.setName(name);
        this.roles = new LinkedList<GuildAction.RoleData>();
        this.channels = new LinkedList<GuildAction.ChannelData>();
        this.roles.add(new GuildAction.RoleData(0L));
    }

    @Override
    @Nonnull
    public GuildActionImpl setCheck(BooleanSupplier checks) {
        return (GuildActionImpl)super.setCheck(checks);
    }

    @Override
    @Nonnull
    public GuildActionImpl timeout(long timeout, @Nonnull TimeUnit unit) {
        return (GuildActionImpl)super.timeout(timeout, unit);
    }

    @Override
    @Nonnull
    public GuildActionImpl deadline(long timestamp) {
        return (GuildActionImpl)super.deadline(timestamp);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl setRegion(Region region) {
        Checks.check(region == null || !region.isVip(), "Cannot create a Guild with a VIP voice region!");
        this.region = region;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl setIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl setName(@Nonnull String name) {
        Checks.notBlank(name, "Name");
        name = name.trim();
        Checks.notEmpty(name, "Name");
        Checks.notLonger(name, 100, "Name");
        this.name = name;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl setVerificationLevel(Guild.VerificationLevel level) {
        this.verificationLevel = level;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl setNotificationLevel(Guild.NotificationLevel level) {
        this.notificationLevel = level;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl setExplicitContentLevel(Guild.ExplicitContentLevel level) {
        this.explicitContentLevel = level;
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl addChannel(@Nonnull GuildAction.ChannelData channel) {
        Checks.notNull(channel, "Channel");
        this.channels.add(channel);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildAction.ChannelData getChannel(int index) {
        return this.channels.get(index);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildAction.ChannelData removeChannel(int index) {
        return this.channels.remove(index);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildActionImpl removeChannel(@Nonnull GuildAction.ChannelData data) {
        this.channels.remove(data);
        return this;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildAction.ChannelData newChannel(@Nonnull ChannelType type, @Nonnull String name) {
        GuildAction.ChannelData data = new GuildAction.ChannelData(type, name);
        this.addChannel(data);
        return data;
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildAction.RoleData getPublicRole() {
        return this.roles.get(0);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildAction.RoleData getRole(int index) {
        return this.roles.get(index);
    }

    @Override
    @Nonnull
    @CheckReturnValue
    public GuildAction.RoleData newRole() {
        GuildAction.RoleData role = new GuildAction.RoleData(this.roles.size());
        this.roles.add(role);
        return role;
    }

    @Override
    protected RequestBody finalizeData() {
        DataObject object = DataObject.empty();
        object.put("name", this.name);
        object.put("roles", DataArray.fromCollection(this.roles));
        if (!this.channels.isEmpty()) {
            object.put("channels", DataArray.fromCollection(this.channels));
        }
        if (this.icon != null) {
            object.put("icon", this.icon.getEncoding());
        }
        if (this.verificationLevel != null) {
            object.put("verification_level", this.verificationLevel.getKey());
        }
        if (this.notificationLevel != null) {
            object.put("default_message_notifications", this.notificationLevel.getKey());
        }
        if (this.explicitContentLevel != null) {
            object.put("explicit_content_filter", this.explicitContentLevel.getKey());
        }
        if (this.region != null) {
            object.put("region", this.region.getKey());
        }
        return this.getRequestBody(object);
    }
}

