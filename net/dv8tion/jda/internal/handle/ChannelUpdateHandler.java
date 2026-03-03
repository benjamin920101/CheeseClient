/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import gnu.trove.map.hash.TLongObjectHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNewsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateRegionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.CategoryImpl;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.PermissionOverrideImpl;
import net.dv8tion.jda.internal.entities.StoreChannelImpl;
import net.dv8tion.jda.internal.entities.TextChannelImpl;
import net.dv8tion.jda.internal.entities.VoiceChannelImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class ChannelUpdateHandler
extends SocketHandler {
    public ChannelUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        int rawType = content.getInt("type");
        boolean news = rawType == 5;
        ChannelType type = ChannelType.fromId(rawType);
        if (type == ChannelType.GROUP) {
            WebSocketClient.LOG.warn("Ignoring CHANNEL_UPDATE for a group which we don't support");
            return null;
        }
        long channelId = content.getLong("id");
        Long parentId = content.isNull("parent_id") ? null : Long.valueOf(content.getLong("parent_id"));
        int position = content.getInt("position");
        String name = content.getString("name");
        boolean nsfw = content.getBoolean("nsfw");
        int slowmode = content.getInt("rate_limit_per_user", 0);
        DataArray permOverwrites = content.getArray("permission_overwrites");
        switch (type) {
            case STORE: {
                StoreChannelImpl storeChannel = (StoreChannelImpl)this.getJDA().getStoreChannelById(channelId);
                if (storeChannel == null) {
                    this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
                    EventCache.LOG.debug("CHANNEL_UPDATE attempted to update a StoreChannel that does not exist. JSON: {}", (Object)content);
                    return null;
                }
                String oldName = storeChannel.getName();
                int oldPosition = storeChannel.getPositionRaw();
                if (!Objects.equals(oldName, name)) {
                    storeChannel.setName(name);
                    this.getJDA().handleEvent(new StoreChannelUpdateNameEvent(this.getJDA(), this.responseNumber, storeChannel, oldName));
                }
                if (!Objects.equals(oldPosition, position)) {
                    storeChannel.setPosition(position);
                    this.getJDA().handleEvent(new StoreChannelUpdatePositionEvent(this.getJDA(), this.responseNumber, storeChannel, oldPosition));
                }
                this.applyPermissions(storeChannel, permOverwrites);
                break;
            }
            case TEXT: {
                String topic = content.getString("topic", null);
                TextChannelImpl textChannel = (TextChannelImpl)this.getJDA().getTextChannelsView().get(channelId);
                if (textChannel == null) {
                    this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
                    EventCache.LOG.debug("CHANNEL_UPDATE attempted to update a TextChannel that does not exist. JSON: {}", (Object)content);
                    return null;
                }
                Category parent = textChannel.getParent();
                Long oldParent = parent == null ? null : Long.valueOf(parent.getIdLong());
                String oldName = textChannel.getName();
                String oldTopic = textChannel.getTopic();
                int oldPosition = textChannel.getPositionRaw();
                boolean oldNsfw = textChannel.isNSFW();
                int oldSlowmode = textChannel.getSlowmode();
                if (!Objects.equals(oldName, name)) {
                    textChannel.setName(name);
                    this.getJDA().handleEvent(new TextChannelUpdateNameEvent(this.getJDA(), this.responseNumber, textChannel, oldName));
                }
                if (!Objects.equals(oldParent, parentId)) {
                    textChannel.setParent(parentId == null ? 0L : parentId);
                    this.getJDA().handleEvent(new TextChannelUpdateParentEvent(this.getJDA(), this.responseNumber, textChannel, parent));
                }
                if (!Objects.equals(oldTopic, topic)) {
                    textChannel.setTopic(topic);
                    this.getJDA().handleEvent(new TextChannelUpdateTopicEvent(this.getJDA(), this.responseNumber, textChannel, oldTopic));
                }
                if (oldPosition != position) {
                    textChannel.setPosition(position);
                    this.getJDA().handleEvent(new TextChannelUpdatePositionEvent(this.getJDA(), this.responseNumber, textChannel, oldPosition));
                }
                if (oldNsfw != nsfw) {
                    textChannel.setNSFW(nsfw);
                    this.getJDA().handleEvent(new TextChannelUpdateNSFWEvent(this.getJDA(), this.responseNumber, textChannel, oldNsfw));
                }
                if (oldSlowmode != slowmode) {
                    textChannel.setSlowmode(slowmode);
                    this.getJDA().handleEvent(new TextChannelUpdateSlowmodeEvent(this.getJDA(), this.responseNumber, textChannel, oldSlowmode));
                }
                if (news != textChannel.isNews()) {
                    textChannel.setNews(news);
                    this.getJDA().handleEvent(new TextChannelUpdateNewsEvent(this.getJDA(), this.responseNumber, textChannel));
                }
                this.applyPermissions(textChannel, permOverwrites);
                break;
            }
            case VOICE: {
                VoiceChannelImpl voiceChannel = (VoiceChannelImpl)this.getJDA().getVoiceChannelsView().get(channelId);
                int userLimit = content.getInt("user_limit");
                int bitrate = content.getInt("bitrate");
                String region = content.getString("rtc_region", null);
                if (voiceChannel == null) {
                    this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
                    EventCache.LOG.debug("CHANNEL_UPDATE attempted to update a VoiceChannel that does not exist. JSON: {}", (Object)content);
                    return null;
                }
                Category parent = voiceChannel.getParent();
                Long oldParent = parent == null ? null : Long.valueOf(parent.getIdLong());
                String oldName = voiceChannel.getName();
                String oldRegion = voiceChannel.getRegionRaw();
                int oldPosition = voiceChannel.getPositionRaw();
                int oldLimit = voiceChannel.getUserLimit();
                int oldBitrate = voiceChannel.getBitrate();
                if (!Objects.equals(oldName, name)) {
                    voiceChannel.setName(name);
                    this.getJDA().handleEvent(new VoiceChannelUpdateNameEvent(this.getJDA(), this.responseNumber, voiceChannel, oldName));
                }
                if (!Objects.equals(oldRegion, region)) {
                    voiceChannel.setRegion(region);
                    this.getJDA().handleEvent(new VoiceChannelUpdateRegionEvent(this.getJDA(), this.responseNumber, voiceChannel, oldRegion));
                }
                if (!Objects.equals(oldParent, parentId)) {
                    voiceChannel.setParent(parentId == null ? 0L : parentId);
                    this.getJDA().handleEvent(new VoiceChannelUpdateParentEvent(this.getJDA(), this.responseNumber, voiceChannel, parent));
                }
                if (oldPosition != position) {
                    voiceChannel.setPosition(position);
                    this.getJDA().handleEvent(new VoiceChannelUpdatePositionEvent(this.getJDA(), this.responseNumber, voiceChannel, oldPosition));
                }
                if (oldLimit != userLimit) {
                    voiceChannel.setUserLimit(userLimit);
                    this.getJDA().handleEvent(new VoiceChannelUpdateUserLimitEvent(this.getJDA(), this.responseNumber, voiceChannel, oldLimit));
                }
                if (oldBitrate != bitrate) {
                    voiceChannel.setBitrate(bitrate);
                    this.getJDA().handleEvent(new VoiceChannelUpdateBitrateEvent(this.getJDA(), this.responseNumber, voiceChannel, oldBitrate));
                }
                this.applyPermissions(voiceChannel, permOverwrites);
                break;
            }
            case CATEGORY: {
                CategoryImpl category = (CategoryImpl)this.getJDA().getCategoryById(channelId);
                if (category == null) {
                    this.getJDA().getEventCache().cache(EventCache.Type.CHANNEL, channelId, this.responseNumber, this.allContent, this::handle);
                    EventCache.LOG.debug("CHANNEL_UPDATE attempted to update a Category that does not exist. JSON: {}", (Object)content);
                    return null;
                }
                String oldName = category.getName();
                int oldPosition = category.getPositionRaw();
                if (!Objects.equals(oldName, name)) {
                    category.setName(name);
                    this.getJDA().handleEvent(new CategoryUpdateNameEvent(this.getJDA(), this.responseNumber, category, oldName));
                }
                if (!Objects.equals(oldPosition, position)) {
                    category.setPosition(position);
                    this.getJDA().handleEvent(new CategoryUpdatePositionEvent(this.getJDA(), this.responseNumber, category, oldPosition));
                }
                this.applyPermissions(category, permOverwrites);
                break;
            }
            default: {
                WebSocketClient.LOG.debug("CHANNEL_UPDATE provided an unrecognized channel type JSON: {}", (Object)content);
            }
        }
        return null;
    }

    private void applyPermissions(AbstractChannelImpl<?, ?> channel, DataArray permOverwrites) {
        TLongObjectHashMap<PermissionOverride> currentOverrides = new TLongObjectHashMap<PermissionOverride>(channel.getOverrideMap());
        ArrayList<IPermissionHolder> changed = new ArrayList<IPermissionHolder>(currentOverrides.size());
        GuildImpl guild = channel.getGuild();
        for (int i2 = 0; i2 < permOverwrites.length(); ++i2) {
            DataObject overrideJson = permOverwrites.getObject(i2);
            long id2 = overrideJson.getUnsignedLong("id", 0L);
            if (!this.handlePermissionOverride((PermissionOverride)currentOverrides.remove(id2), overrideJson, id2, channel)) continue;
            this.addPermissionHolder(changed, guild, id2);
        }
        currentOverrides.forEachValue(override -> {
            channel.getOverrideMap().remove(override.getIdLong());
            this.addPermissionHolder(changed, guild, override.getIdLong());
            this.api.handleEvent(new PermissionOverrideDeleteEvent(this.api, this.responseNumber, channel, (PermissionOverride)override));
            return true;
        });
        if (changed.isEmpty()) {
            return;
        }
        switch (channel.getType()) {
            case CATEGORY: {
                this.api.handleEvent(new CategoryUpdatePermissionsEvent(this.api, this.responseNumber, (Category)((Object)channel), changed));
                break;
            }
            case STORE: {
                this.api.handleEvent(new StoreChannelUpdatePermissionsEvent(this.api, this.responseNumber, (StoreChannel)((Object)channel), changed));
                break;
            }
            case VOICE: {
                this.api.handleEvent(new VoiceChannelUpdatePermissionsEvent(this.api, this.responseNumber, (VoiceChannel)((Object)channel), changed));
                break;
            }
            case TEXT: {
                this.api.handleEvent(new TextChannelUpdatePermissionsEvent(this.api, this.responseNumber, (TextChannel)((Object)channel), changed));
            }
        }
    }

    private void addPermissionHolder(List<IPermissionHolder> changed, Guild guild, long id2) {
        IPermissionHolder holder = guild.getRoleById(id2);
        if (holder == null) {
            holder = guild.getMemberById(id2);
        }
        if (holder != null) {
            changed.add(holder);
        }
    }

    private boolean handlePermissionOverride(PermissionOverride currentOverride, DataObject override, long overrideId, AbstractChannelImpl<?, ?> channel) {
        boolean isRole;
        long allow = override.getLong("allow");
        long deny = override.getLong("deny");
        int type = override.getInt("type");
        boolean bl2 = isRole = type == 0;
        if (!isRole) {
            if (type != 1) {
                EntityBuilder.LOG.debug("Ignoring unknown invite of type '{}'. JSON: {}", (Object)type, (Object)override);
                return false;
            }
            if (!this.api.isCacheFlagSet(CacheFlag.MEMBER_OVERRIDES) && overrideId != this.api.getSelfUser().getIdLong()) {
                return false;
            }
        }
        if (currentOverride != null) {
            long oldAllow = currentOverride.getAllowedRaw();
            long oldDeny = currentOverride.getDeniedRaw();
            PermissionOverrideImpl impl = (PermissionOverrideImpl)currentOverride;
            if (oldAllow == allow && oldDeny == deny) {
                return false;
            }
            if (overrideId == channel.getGuild().getIdLong() && (allow | deny) == 0L) {
                channel.getOverrideMap().remove(overrideId);
                this.api.handleEvent(new PermissionOverrideDeleteEvent(this.api, this.responseNumber, channel, currentOverride));
                return true;
            }
            impl.setAllow(allow);
            impl.setDeny(deny);
            this.api.handleEvent(new PermissionOverrideUpdateEvent(this.api, this.responseNumber, channel, currentOverride, oldAllow, oldDeny));
        } else {
            if (overrideId == channel.getGuild().getIdLong() && (allow | deny) == 0L) {
                return false;
            }
            PermissionOverrideImpl impl = new PermissionOverrideImpl(channel, overrideId, isRole);
            currentOverride = impl;
            impl.setAllow(allow);
            impl.setDeny(deny);
            channel.getOverrideMap().put(overrideId, currentOverride);
            this.api.handleEvent(new PermissionOverrideCreateEvent(this.api, this.responseNumber, channel, currentOverride));
        }
        return true;
    }
}

