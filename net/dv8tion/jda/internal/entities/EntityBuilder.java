/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.hash.TLongHashSet;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogChange;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.entities.ApplicationTeam;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.ClientType;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageSticker;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.RichPresence;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.StoreChannel;
import net.dv8tion.jda.api.entities.TeamMember;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.WebhookType;
import net.dv8tion.jda.api.entities.templates.Template;
import net.dv8tion.jda.api.entities.templates.TemplateChannel;
import net.dv8tion.jda.api.entities.templates.TemplateGuild;
import net.dv8tion.jda.api.entities.templates.TemplateRole;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateFlagsEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.AbstractChannelImpl;
import net.dv8tion.jda.internal.entities.ActivityImpl;
import net.dv8tion.jda.internal.entities.ApplicationInfoImpl;
import net.dv8tion.jda.internal.entities.ApplicationTeamImpl;
import net.dv8tion.jda.internal.entities.CategoryImpl;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.GuildVoiceStateImpl;
import net.dv8tion.jda.internal.entities.InviteImpl;
import net.dv8tion.jda.internal.entities.MemberImpl;
import net.dv8tion.jda.internal.entities.MemberPresenceImpl;
import net.dv8tion.jda.internal.entities.PermissionOverrideImpl;
import net.dv8tion.jda.internal.entities.PrivateChannelImpl;
import net.dv8tion.jda.internal.entities.ReceivedMessage;
import net.dv8tion.jda.internal.entities.RichPresenceImpl;
import net.dv8tion.jda.internal.entities.RoleImpl;
import net.dv8tion.jda.internal.entities.SelfUserImpl;
import net.dv8tion.jda.internal.entities.StoreChannelImpl;
import net.dv8tion.jda.internal.entities.SystemMessage;
import net.dv8tion.jda.internal.entities.TeamMemberImpl;
import net.dv8tion.jda.internal.entities.TextChannelImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.entities.VoiceChannelImpl;
import net.dv8tion.jda.internal.entities.WebhookImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.MemberCacheViewImpl;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;
import net.dv8tion.jda.internal.utils.cache.SortedSnowflakeCacheViewImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.slf4j.Logger;

public class EntityBuilder {
    public static final Logger LOG = JDALogger.getLog(EntityBuilder.class);
    public static final String MISSING_CHANNEL = "MISSING_CHANNEL";
    public static final String MISSING_USER = "MISSING_USER";
    public static final String UNKNOWN_MESSAGE_TYPE = "UNKNOWN_MESSAGE_TYPE";
    private static final Set<String> richGameFields;
    protected final JDAImpl api;

    public EntityBuilder(JDA api2) {
        this.api = (JDAImpl)api2;
    }

    public JDAImpl getJDA() {
        return this.api;
    }

    public SelfUser createSelfUser(DataObject self) {
        SelfUserImpl selfUser = (SelfUserImpl)(this.getJDA().hasSelfUser() ? this.getJDA().getSelfUser() : null);
        if (selfUser == null) {
            long id2 = self.getLong("id");
            selfUser = new SelfUserImpl(id2, this.getJDA());
            this.getJDA().setSelfUser(selfUser);
        }
        SnowflakeCacheViewImpl<User> userView = this.getJDA().getUsersView();
        try (UnlockHook hook = userView.writeLock();){
            if (userView.getElementById(selfUser.getIdLong()) == null) {
                userView.getMap().put(selfUser.getIdLong(), selfUser);
            }
        }
        if (!self.isNull("application_id")) {
            selfUser.setApplicationId(self.getUnsignedLong("application_id"));
        }
        selfUser.setVerified(self.getBoolean("verified")).setMfaEnabled(self.getBoolean("mfa_enabled")).setName(self.getString("username")).setDiscriminator(self.getString("discriminator")).setAvatarId(self.getString("avatar", null)).setBot(self.getBoolean("bot")).setSystem(false);
        return selfUser;
    }

    public static Activity createActivity(String name, String url, Activity.ActivityType type) {
        return new ActivityImpl(name, url, type);
    }

    private void createGuildEmotePass(GuildImpl guildObj, DataArray array) {
        if (!this.getJDA().isCacheFlagSet(CacheFlag.EMOTE)) {
            return;
        }
        SnowflakeCacheViewImpl<Emote> emoteView = guildObj.getEmotesView();
        try (UnlockHook hook = emoteView.writeLock();){
            TLongObjectMap emoteMap = emoteView.getMap();
            for (int i2 = 0; i2 < array.length(); ++i2) {
                DataObject object = array.getObject(i2);
                if (object.isNull("id")) {
                    LOG.error("Received GUILD_CREATE with an emoji with a null ID. JSON: {}", (Object)object);
                    continue;
                }
                long emoteId = object.getLong("id");
                emoteMap.put(emoteId, this.createEmote(guildObj, object));
            }
        }
    }

    public TLongObjectMap<DataObject> convertToUserMap(ToLongFunction<DataObject> getId, DataArray array) {
        TLongObjectHashMap<DataObject> map = new TLongObjectHashMap<DataObject>();
        for (int i2 = 0; i2 < array.length(); ++i2) {
            DataObject obj = array.getObject(i2);
            long userId = getId.applyAsLong(obj);
            map.put(userId, obj);
        }
        return map;
    }

    public GuildImpl createGuild(long guildId, DataObject guildJson, TLongObjectMap<DataObject> members, int memberCount) {
        GuildImpl guildObj = new GuildImpl(this.getJDA(), guildId);
        String name = guildJson.getString("name", "");
        String iconId = guildJson.getString("icon", null);
        String splashId = guildJson.getString("splash", null);
        String region = guildJson.getString("region", null);
        String description = guildJson.getString("description", null);
        String vanityCode = guildJson.getString("vanity_url_code", null);
        String bannerId = guildJson.getString("banner", null);
        String locale = guildJson.getString("preferred_locale", "en");
        DataArray roleArray = guildJson.getArray("roles");
        DataArray channelArray = guildJson.getArray("channels");
        DataArray emotesArray = guildJson.getArray("emojis");
        DataArray voiceStateArray = guildJson.getArray("voice_states");
        Optional<DataArray> featuresArray = guildJson.optArray("features");
        Optional<DataArray> presencesArray = guildJson.optArray("presences");
        long ownerId = guildJson.getUnsignedLong("owner_id", 0L);
        long afkChannelId = guildJson.getUnsignedLong("afk_channel_id", 0L);
        long systemChannelId = guildJson.getUnsignedLong("system_channel_id", 0L);
        long rulesChannelId = guildJson.getUnsignedLong("rules_channel_id", 0L);
        long communityUpdatesChannelId = guildJson.getUnsignedLong("public_updates_channel_id", 0L);
        int boostCount = guildJson.getInt("premium_subscription_count", 0);
        int boostTier = guildJson.getInt("premium_tier", 0);
        int maxMembers = guildJson.getInt("max_members", 0);
        int maxPresences = guildJson.getInt("max_presences", 5000);
        int mfaLevel = guildJson.getInt("mfa_level", 0);
        int afkTimeout = guildJson.getInt("afk_timeout", 0);
        int verificationLevel = guildJson.getInt("verification_level", 0);
        int notificationLevel = guildJson.getInt("default_message_notifications", 0);
        int explicitContentLevel = guildJson.getInt("explicit_content_filter", 0);
        guildObj.setAvailable(true).setName(name).setIconId(iconId).setSplashId(splashId).setRegion(region).setDescription(description).setBannerId(bannerId).setVanityCode(vanityCode).setMaxMembers(maxMembers).setMaxPresences(maxPresences).setOwnerId(ownerId).setAfkTimeout(Guild.Timeout.fromKey(afkTimeout)).setVerificationLevel(Guild.VerificationLevel.fromKey(verificationLevel)).setDefaultNotificationLevel(Guild.NotificationLevel.fromKey(notificationLevel)).setExplicitContentLevel(Guild.ExplicitContentLevel.fromKey(explicitContentLevel)).setRequiredMFALevel(Guild.MFALevel.fromKey(mfaLevel)).setLocale(locale).setBoostCount(boostCount).setBoostTier(boostTier).setMemberCount(memberCount);
        SnowflakeCacheViewImpl<Guild> guildView = this.getJDA().getGuildsView();
        try (UnlockHook hook = guildView.writeLock();){
            guildView.getMap().put(guildId, guildObj);
        }
        guildObj.setFeatures(featuresArray.map(it2 -> StreamSupport.stream(it2.spliterator(), false).map(String::valueOf).collect(Collectors.toSet())).orElse(Collections.emptySet()));
        SortedSnowflakeCacheViewImpl<Role> roleView = guildObj.getRolesView();
        try (UnlockHook hook = roleView.writeLock();){
            TLongObjectMap map = roleView.getMap();
            for (int i2 = 0; i2 < roleArray.length(); ++i2) {
                DataObject obj = roleArray.getObject(i2);
                Role role = this.createRole(guildObj, obj, guildId);
                map.put(role.getIdLong(), role);
                if (role.getIdLong() != guildObj.getIdLong()) continue;
                guildObj.setPublicRole(role);
            }
        }
        for (int i3 = 0; i3 < channelArray.length(); ++i3) {
            DataObject channelJson = channelArray.getObject(i3);
            this.createGuildChannel(guildObj, channelJson);
        }
        TLongObjectMap<DataObject> voiceStates = this.convertToUserMap(o2 -> o2.getUnsignedLong("user_id", 0L), voiceStateArray);
        TLongObjectMap presences = presencesArray.map(o1 -> this.convertToUserMap(o2 -> o2.getObject("user").getUnsignedLong("id"), (DataArray)o1)).orElseGet(TLongObjectHashMap::new);
        try (UnlockHook h1 = guildObj.getMembersView().writeLock();
             UnlockHook h2 = this.getJDA().getUsersView().writeLock();){
            for (DataObject memberJson : members.valueCollection()) {
                long userId = memberJson.getObject("user").getUnsignedLong("id");
                DataObject voiceState = voiceStates.get(userId);
                DataObject presence = (DataObject)presences.get(userId);
                this.updateMemberCache(this.createMember(guildObj, memberJson, voiceState, presence));
            }
        }
        if (guildObj.getOwner() == null) {
            LOG.debug("Finished setup for guild with a null owner. GuildId: {} OwnerId: {}", (Object)guildId, (Object)guildJson.opt("owner_id").orElse(null));
        }
        this.createGuildEmotePass(guildObj, emotesArray);
        guildObj.setAfkChannel(guildObj.getVoiceChannelById(afkChannelId)).setSystemChannel(guildObj.getTextChannelById(systemChannelId)).setRulesChannel(guildObj.getTextChannelById(rulesChannelId)).setCommunityUpdatesChannel(guildObj.getTextChannelById(communityUpdatesChannelId));
        return guildObj;
    }

    private void createGuildChannel(GuildImpl guildObj, DataObject channelData) {
        ChannelType channelType = ChannelType.fromId(channelData.getInt("type"));
        switch (channelType) {
            case TEXT: {
                this.createTextChannel(guildObj, channelData, guildObj.getIdLong());
                break;
            }
            case VOICE: {
                this.createVoiceChannel(guildObj, channelData, guildObj.getIdLong());
                break;
            }
            case CATEGORY: {
                this.createCategory(guildObj, channelData, guildObj.getIdLong());
                break;
            }
            case STORE: {
                this.createStoreChannel(guildObj, channelData, guildObj.getIdLong());
                break;
            }
            default: {
                LOG.debug("Cannot create channel for type " + channelData.getInt("type"));
            }
        }
    }

    public UserImpl createUser(DataObject user) {
        UserImpl userObj;
        boolean newUser = false;
        long id2 = user.getLong("id");
        SnowflakeCacheViewImpl<User> userView = this.getJDA().getUsersView();
        try (UnlockHook hook = userView.readLock();){
            userObj = (UserImpl)userView.getElementById(id2);
            if (userObj == null) {
                userObj = new UserImpl(id2, this.getJDA());
                newUser = true;
            }
        }
        if (newUser) {
            userObj.setName(user.getString("username")).setDiscriminator(user.get("discriminator").toString()).setAvatarId(user.getString("avatar", null)).setBot(user.getBoolean("bot")).setSystem(user.getBoolean("system")).setFlags(user.getInt("public_flags", 0));
        } else {
            this.updateUser(userObj, user);
        }
        return userObj;
    }

    public void updateUser(UserImpl userObj, DataObject user) {
        String oldName = userObj.getName();
        String newName = user.getString("username");
        String oldDiscriminator = userObj.getDiscriminator();
        String newDiscriminator = user.get("discriminator").toString();
        String oldAvatar = userObj.getAvatarId();
        String newAvatar = user.getString("avatar", null);
        int oldFlags = userObj.getFlagsRaw();
        int newFlags = user.getInt("public_flags", 0);
        JDAImpl jda = this.getJDA();
        long responseNumber = jda.getResponseTotal();
        if (!oldName.equals(newName)) {
            userObj.setName(newName);
            jda.handleEvent(new UserUpdateNameEvent(jda, responseNumber, userObj, oldName));
        }
        if (!oldDiscriminator.equals(newDiscriminator)) {
            userObj.setDiscriminator(newDiscriminator);
            jda.handleEvent(new UserUpdateDiscriminatorEvent(jda, responseNumber, userObj, oldDiscriminator));
        }
        if (!Objects.equals(oldAvatar, newAvatar)) {
            userObj.setAvatarId(newAvatar);
            jda.handleEvent(new UserUpdateAvatarEvent(jda, responseNumber, userObj, oldAvatar));
        }
        if (oldFlags != newFlags) {
            userObj.setFlags(newFlags);
            jda.handleEvent(new UserUpdateFlagsEvent(jda, responseNumber, userObj, User.UserFlag.getFlags(oldFlags)));
        }
    }

    public boolean updateMemberCache(MemberImpl member) {
        return this.updateMemberCache(member, false);
    }

    public boolean updateMemberCache(MemberImpl member, boolean forceRemove) {
        GuildImpl guild = member.getGuild();
        UserImpl user = (UserImpl)member.getUser();
        MemberCacheViewImpl membersView = guild.getMembersView();
        if (forceRemove || !this.getJDA().cacheMember(member)) {
            GuildVoiceStateImpl voiceState;
            if (membersView.remove(member.getIdLong()) == null) {
                return false;
            }
            LOG.trace("Unloading member {}", (Object)member);
            if (user.getMutualGuilds().isEmpty()) {
                user.setFake(true);
                this.getJDA().getUsersView().remove(user.getIdLong());
            }
            if ((voiceState = (GuildVoiceStateImpl)member.getVoiceState()) != null) {
                VoiceChannelImpl connectedChannel = (VoiceChannelImpl)voiceState.getChannel();
                if (connectedChannel != null) {
                    connectedChannel.getConnectedMembersMap().remove(member.getIdLong());
                }
                voiceState.setConnectedChannel(null);
            }
            return false;
        }
        if (guild.getMemberById(member.getIdLong()) != null) {
            return true;
        }
        LOG.trace("Loading member {}", (Object)member);
        if (this.getJDA().getUserById(user.getIdLong()) == null) {
            SnowflakeCacheViewImpl<User> usersView = this.getJDA().getUsersView();
            try (UnlockHook hook1 = usersView.writeLock();){
                usersView.getMap().put(user.getIdLong(), user);
            }
        }
        try (UnlockHook hook = membersView.writeLock();){
            membersView.getMap().put(member.getIdLong(), member);
            if (member.isOwner()) {
                guild.setOwner(member);
            }
        }
        long hashId = guild.getIdLong() ^ user.getIdLong();
        this.getJDA().getEventCache().playbackCache(EventCache.Type.USER, member.getIdLong());
        this.getJDA().getEventCache().playbackCache(EventCache.Type.MEMBER, hashId);
        return true;
    }

    public MemberImpl createMember(GuildImpl guild, DataObject memberJson) {
        return this.createMember(guild, memberJson, null, null);
    }

    public MemberImpl createMember(GuildImpl guild, DataObject memberJson, DataObject voiceStateJson, DataObject presence) {
        boolean playbackCache = false;
        UserImpl user = this.createUser(memberJson.getObject("user"));
        DataArray roleArray = memberJson.getArray("roles");
        MemberImpl member = (MemberImpl)guild.getMember(user);
        if (member == null) {
            member = new MemberImpl(guild, user);
            member.setNickname(memberJson.getString("nick", null));
            long epoch = 0L;
            if (!memberJson.isNull("premium_since")) {
                TemporalAccessor date = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(memberJson.getString("premium_since"));
                epoch = Instant.from(date).toEpochMilli();
            }
            member.setBoostDate(epoch);
            if (!memberJson.isNull("pending")) {
                member.setPending(memberJson.getBoolean("pending"));
            }
            Set<Role> roles = member.getRoleSet();
            for (int i2 = 0; i2 < roleArray.length(); ++i2) {
                long roleId = roleArray.getUnsignedLong(i2);
                Role role = guild.getRoleById(roleId);
                if (role == null) continue;
                roles.add(role);
            }
        } else {
            ArrayList<Role> roles = new ArrayList<Role>(roleArray.length());
            for (int i3 = 0; i3 < roleArray.length(); ++i3) {
                long roleId = roleArray.getUnsignedLong(i3);
                Role role = guild.getRoleById(roleId);
                if (role == null) continue;
                roles.add(role);
            }
            this.updateMember(guild, member, memberJson, roles);
        }
        if (!memberJson.isNull("joined_at") && !member.hasTimeJoined()) {
            String joinedAtRaw = memberJson.getString("joined_at");
            TemporalAccessor joinedAt = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(joinedAtRaw);
            long joinEpoch = Instant.from(joinedAt).toEpochMilli();
            member.setJoinDate(joinEpoch);
        }
        if (voiceStateJson != null && member.getVoiceState() != null) {
            this.createVoiceState(guild, voiceStateJson, user, member);
        }
        if (presence != null) {
            this.createPresence(member, presence);
        }
        return member;
    }

    private void createVoiceState(GuildImpl guild, DataObject voiceStateJson, User user, MemberImpl member) {
        GuildVoiceStateImpl voiceState = (GuildVoiceStateImpl)member.getVoiceState();
        long channelId = voiceStateJson.getLong("channel_id");
        VoiceChannelImpl voiceChannel = (VoiceChannelImpl)guild.getVoiceChannelsView().get(channelId);
        if (voiceChannel != null) {
            voiceChannel.getConnectedMembersMap().put(member.getIdLong(), member);
        } else {
            LOG.error("Received a GuildVoiceState with a channel ID for a non-existent channel! ChannelId: {} GuildId: {} UserId: {}", channelId, guild.getId(), user.getId());
        }
        voiceState.setSelfMuted(voiceStateJson.getBoolean("self_mute")).setSelfDeafened(voiceStateJson.getBoolean("self_deaf")).setGuildMuted(voiceStateJson.getBoolean("mute")).setGuildDeafened(voiceStateJson.getBoolean("deaf")).setSuppressed(voiceStateJson.getBoolean("suppress")).setSessionId(voiceStateJson.getString("session_id")).setStream(voiceStateJson.getBoolean("self_stream")).setConnectedChannel(voiceChannel);
    }

    public void updateMember(GuildImpl guild, MemberImpl member, DataObject content, List<Role> newRoles) {
        boolean oldPending;
        boolean pending;
        String newNick;
        String oldNick;
        long responseNumber = this.getJDA().getResponseTotal();
        if (newRoles != null) {
            this.updateMemberRoles(member, newRoles, responseNumber);
        }
        if (content.hasKey("nick") && !Objects.equals(oldNick = member.getNickname(), newNick = content.getString("nick", null))) {
            member.setNickname(newNick);
            this.getJDA().handleEvent(new GuildMemberUpdateNicknameEvent(this.getJDA(), responseNumber, member, oldNick));
        }
        if (content.hasKey("premium_since")) {
            long epoch = 0L;
            if (!content.isNull("premium_since")) {
                TemporalAccessor date = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(content.getString("premium_since"));
                epoch = Instant.from(date).toEpochMilli();
            }
            if (epoch != member.getBoostDateRaw()) {
                OffsetDateTime oldTime = member.getTimeBoosted();
                member.setBoostDate(epoch);
                this.getJDA().handleEvent(new GuildMemberUpdateBoostTimeEvent(this.getJDA(), responseNumber, member, oldTime));
            }
        }
        if (!content.isNull("joined_at") && !member.hasTimeJoined()) {
            String joinedAtRaw = content.getString("joined_at");
            TemporalAccessor joinedAt = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(joinedAtRaw);
            long joinEpoch = Instant.from(joinedAt).toEpochMilli();
            member.setJoinDate(joinEpoch);
        }
        if (!content.isNull("pending") && (pending = content.getBoolean("pending")) != (oldPending = member.isPending())) {
            member.setPending(pending);
            this.getJDA().handleEvent(new GuildMemberUpdatePendingEvent(this.getJDA(), responseNumber, member, oldPending));
        }
        this.updateUser((UserImpl)member.getUser(), content.getObject("user"));
    }

    private void updateMemberRoles(MemberImpl member, List<Role> newRoles, long responseNumber) {
        Set<Role> currentRoles = member.getRoleSet();
        LinkedList<Role> removedRoles = new LinkedList<Role>();
        block0: for (Role role : currentRoles) {
            Iterator<Role> it2 = newRoles.iterator();
            while (it2.hasNext()) {
                Role r2 = it2.next();
                if (!role.equals(r2)) continue;
                it2.remove();
                continue block0;
            }
            removedRoles.add(role);
        }
        if (removedRoles.size() > 0) {
            currentRoles.removeAll(removedRoles);
        }
        if (newRoles.size() > 0) {
            currentRoles.addAll(newRoles);
        }
        if (removedRoles.size() > 0) {
            this.getJDA().handleEvent(new GuildMemberRoleRemoveEvent(this.getJDA(), responseNumber, member, removedRoles));
        }
        if (newRoles.size() > 0) {
            this.getJDA().handleEvent(new GuildMemberRoleAddEvent(this.getJDA(), responseNumber, member, newRoles));
        }
    }

    public void createPresence(MemberImpl member, DataObject presenceJson) {
        if (member == null) {
            throw new NullPointerException("Provided member was null!");
        }
        OnlineStatus onlineStatus = OnlineStatus.fromKey(presenceJson.getString("status"));
        if (onlineStatus == OnlineStatus.OFFLINE) {
            return;
        }
        MemberPresenceImpl presence = member.getPresence();
        if (presence == null) {
            CacheView.SimpleCacheView<MemberPresenceImpl> view = member.getGuild().getPresenceView();
            if (view == null) {
                return;
            }
            presence = new MemberPresenceImpl();
            try (UnlockHook lock = view.writeLock();){
                view.getMap().put(member.getIdLong(), presence);
            }
        }
        boolean cacheGame = this.getJDA().isCacheFlagSet(CacheFlag.ACTIVITY);
        boolean cacheStatus = this.getJDA().isCacheFlagSet(CacheFlag.CLIENT_STATUS);
        DataArray activityArray = !cacheGame || presenceJson.isNull("activities") ? null : presenceJson.getArray("activities");
        DataObject clientStatusJson = !cacheStatus || presenceJson.isNull("client_status") ? null : presenceJson.getObject("client_status");
        ArrayList<Activity> activities = new ArrayList<Activity>();
        boolean parsedActivity = false;
        if (cacheGame && activityArray != null) {
            for (int i2 = 0; i2 < activityArray.length(); ++i2) {
                try {
                    activities.add(EntityBuilder.createActivity(activityArray.getObject(i2)));
                    parsedActivity = true;
                    continue;
                }
                catch (Exception ex2) {
                    String userId = member.getId();
                    if (LOG.isDebugEnabled()) {
                        LOG.warn("Encountered exception trying to parse a presence! UserId: {} JSON: {}", userId, activityArray, ex2);
                        continue;
                    }
                    LOG.warn("Encountered exception trying to parse a presence! UserId: {} Message: {} Enable debug for details", (Object)userId, (Object)ex2.getMessage());
                }
            }
        }
        if (cacheGame && parsedActivity) {
            presence.setActivities(activities);
        }
        presence.setOnlineStatus(onlineStatus);
        if (clientStatusJson != null) {
            for (String key : clientStatusJson.keys()) {
                ClientType type = ClientType.fromKey(key);
                OnlineStatus status = OnlineStatus.fromKey(clientStatusJson.getString(key));
                presence.setOnlineStatus(type, status);
            }
        }
    }

    public static Activity createActivity(DataObject gameJson) {
        Activity.ActivityType type;
        String name = String.valueOf(gameJson.get("name"));
        String url = gameJson.isNull("url") ? null : String.valueOf(gameJson.get("url"));
        try {
            type = gameJson.isNull("type") ? Activity.ActivityType.DEFAULT : Activity.ActivityType.fromKey(Integer.parseInt(gameJson.get("type").toString()));
        }
        catch (NumberFormatException e2) {
            type = Activity.ActivityType.DEFAULT;
        }
        Activity.Timestamps timestamps = null;
        if (!gameJson.isNull("timestamps")) {
            DataObject obj = gameJson.getObject("timestamps");
            long start = obj.getLong("start", 0L);
            long end = obj.getLong("end", 0L);
            timestamps = new Activity.Timestamps(start, end);
        }
        Activity.Emoji emoji = null;
        if (!gameJson.isNull("emoji")) {
            DataObject emojiJson = gameJson.getObject("emoji");
            String emojiName = emojiJson.getString("name");
            long emojiId = emojiJson.getUnsignedLong("id", 0L);
            boolean emojiAnimated = emojiJson.getBoolean("animated");
            emoji = new Activity.Emoji(emojiName, emojiId, emojiAnimated);
        }
        if (type == Activity.ActivityType.CUSTOM_STATUS && gameJson.hasKey("state") && name.equalsIgnoreCase("Custom Status")) {
            name = gameJson.getString("state", "");
            gameJson = gameJson.remove("state");
        }
        if (!CollectionUtils.containsAny(gameJson.keys(), richGameFields)) {
            return new ActivityImpl(name, url, type, timestamps, emoji);
        }
        long id2 = gameJson.getLong("application_id", 0L);
        String sessionId = gameJson.getString("session_id", null);
        String syncId = gameJson.getString("sync_id", null);
        int flags = gameJson.getInt("flags", 0);
        String details = gameJson.isNull("details") ? null : String.valueOf(gameJson.get("details"));
        String state = gameJson.isNull("state") ? null : String.valueOf(gameJson.get("state"));
        RichPresence.Party party = null;
        if (!gameJson.isNull("party")) {
            DataObject obj = gameJson.getObject("party");
            String partyId = obj.isNull("id") ? null : obj.getString("id");
            DataArray sizeArr = obj.isNull("size") ? null : obj.getArray("size");
            long size = 0L;
            long max = 0L;
            if (sizeArr != null && sizeArr.length() > 0) {
                size = sizeArr.getLong(0);
                max = sizeArr.length() < 2 ? 0L : sizeArr.getLong(1);
            }
            party = new RichPresence.Party(partyId, size, max);
        }
        String smallImageKey = null;
        String smallImageText = null;
        String largeImageKey = null;
        String largeImageText = null;
        if (!gameJson.isNull("assets")) {
            DataObject assets = gameJson.getObject("assets");
            if (!assets.isNull("small_image")) {
                smallImageKey = String.valueOf(assets.get("small_image"));
                String string = smallImageText = assets.isNull("small_text") ? null : String.valueOf(assets.get("small_text"));
            }
            if (!assets.isNull("large_image")) {
                largeImageKey = String.valueOf(assets.get("large_image"));
                largeImageText = assets.isNull("large_text") ? null : String.valueOf(assets.get("large_text"));
            }
        }
        return new RichPresenceImpl(type, name, url, id2, emoji, party, details, state, timestamps, syncId, sessionId, flags, largeImageKey, largeImageText, smallImageKey, smallImageText);
    }

    public EmoteImpl createEmote(GuildImpl guildObj, DataObject json) {
        DataArray emoteRoles = json.optArray("roles").orElseGet(DataArray::empty);
        long emoteId = json.getLong("id");
        UserImpl user = json.isNull("user") ? null : this.createUser(json.getObject("user"));
        EmoteImpl emoteObj = (EmoteImpl)guildObj.getEmoteById(emoteId);
        if (emoteObj == null) {
            emoteObj = new EmoteImpl(emoteId, guildObj);
        }
        Set<Role> roleSet = emoteObj.getRoleSet();
        roleSet.clear();
        for (int j2 = 0; j2 < emoteRoles.length(); ++j2) {
            Role role = guildObj.getRoleById(emoteRoles.getString(j2));
            if (role == null) continue;
            roleSet.add(role);
        }
        if (user != null) {
            emoteObj.setUser(user);
        }
        return emoteObj.setName(json.getString("name", "")).setAnimated(json.getBoolean("animated")).setManaged(json.getBoolean("managed")).setAvailable(json.getBoolean("available", true));
    }

    public Category createCategory(DataObject json, long guildId) {
        return this.createCategory(null, json, guildId);
    }

    public Category createCategory(GuildImpl guild, DataObject json, long guildId) {
        boolean playbackCache = false;
        long id2 = json.getLong("id");
        CategoryImpl channel = (CategoryImpl)this.getJDA().getCategoriesView().get(id2);
        if (channel == null) {
            if (guild == null) {
                guild = (GuildImpl)this.getJDA().getGuildsView().get(guildId);
            }
            SortedSnowflakeCacheViewImpl<Category> guildCategoryView = guild.getCategoriesView();
            SnowflakeCacheViewImpl<Category> categoryView = this.getJDA().getCategoriesView();
            try (UnlockHook glock = guildCategoryView.writeLock();
                 UnlockHook jlock = categoryView.writeLock();){
                channel = new CategoryImpl(id2, guild);
                guildCategoryView.getMap().put(id2, channel);
                playbackCache = categoryView.getMap().put(id2, channel) == null;
            }
        }
        ((CategoryImpl)channel.setName(json.getString("name"))).setPosition(json.getInt("position"));
        this.createOverridesPass(channel, json.getArray("permission_overwrites"));
        if (playbackCache) {
            this.getJDA().getEventCache().playbackCache(EventCache.Type.CHANNEL, id2);
        }
        return channel;
    }

    public StoreChannel createStoreChannel(DataObject json, long guildId) {
        return this.createStoreChannel(null, json, guildId);
    }

    public StoreChannel createStoreChannel(GuildImpl guild, DataObject json, long guildId) {
        boolean playbackCache = false;
        long id2 = json.getLong("id");
        StoreChannelImpl channel = (StoreChannelImpl)this.getJDA().getStoreChannelsView().get(id2);
        if (channel == null) {
            if (guild == null) {
                guild = (GuildImpl)this.getJDA().getGuildById(guildId);
            }
            SortedSnowflakeCacheViewImpl<StoreChannel> guildStoreView = guild.getStoreChannelView();
            SnowflakeCacheViewImpl<StoreChannel> storeView = this.getJDA().getStoreChannelsView();
            try (UnlockHook glock = guildStoreView.writeLock();
                 UnlockHook jlock = storeView.writeLock();){
                channel = new StoreChannelImpl(id2, guild);
                guildStoreView.getMap().put(id2, channel);
                playbackCache = storeView.getMap().put(id2, channel) == null;
            }
        }
        ((StoreChannelImpl)((StoreChannelImpl)channel.setParent(json.getLong("parent_id", 0L))).setName(json.getString("name"))).setPosition(json.getInt("position"));
        this.createOverridesPass(channel, json.getArray("permission_overwrites"));
        if (playbackCache) {
            this.getJDA().getEventCache().playbackCache(EventCache.Type.CHANNEL, id2);
        }
        return channel;
    }

    public TextChannel createTextChannel(DataObject json, long guildId) {
        return this.createTextChannel(null, json, guildId);
    }

    public TextChannel createTextChannel(GuildImpl guildObj, DataObject json, long guildId) {
        boolean playbackCache = false;
        long id2 = json.getLong("id");
        TextChannelImpl channel = (TextChannelImpl)this.getJDA().getTextChannelsView().get(id2);
        if (channel == null) {
            if (guildObj == null) {
                guildObj = (GuildImpl)this.getJDA().getGuildsView().get(guildId);
            }
            SortedSnowflakeCacheViewImpl<TextChannel> guildTextView = guildObj.getTextChannelsView();
            SnowflakeCacheViewImpl<TextChannel> textView = this.getJDA().getTextChannelsView();
            try (UnlockHook glock = guildTextView.writeLock();
                 UnlockHook jlock = textView.writeLock();){
                channel = new TextChannelImpl(id2, guildObj);
                guildTextView.getMap().put(id2, channel);
                playbackCache = textView.getMap().put(id2, channel) == null;
            }
        }
        ((TextChannelImpl)((TextChannelImpl)channel.setParent(json.getLong("parent_id", 0L))).setLastMessageId(json.getLong("last_message_id", 0L)).setName(json.getString("name"))).setTopic(json.getString("topic", null)).setPosition(json.getInt("position")).setNSFW(json.getBoolean("nsfw")).setNews(json.getInt("type") == 5).setSlowmode(json.getInt("rate_limit_per_user", 0));
        this.createOverridesPass(channel, json.getArray("permission_overwrites"));
        if (playbackCache) {
            this.getJDA().getEventCache().playbackCache(EventCache.Type.CHANNEL, id2);
        }
        return channel;
    }

    public VoiceChannel createVoiceChannel(DataObject json, long guildId) {
        return this.createVoiceChannel(null, json, guildId);
    }

    public VoiceChannel createVoiceChannel(GuildImpl guild, DataObject json, long guildId) {
        boolean playbackCache = false;
        long id2 = json.getLong("id");
        VoiceChannelImpl channel = (VoiceChannelImpl)this.getJDA().getVoiceChannelsView().get(id2);
        if (channel == null) {
            if (guild == null) {
                guild = (GuildImpl)this.getJDA().getGuildsView().get(guildId);
            }
            SortedSnowflakeCacheViewImpl<VoiceChannel> guildVoiceView = guild.getVoiceChannelsView();
            SnowflakeCacheViewImpl<VoiceChannel> voiceView = this.getJDA().getVoiceChannelsView();
            try (UnlockHook vlock = guildVoiceView.writeLock();
                 UnlockHook jlock = voiceView.writeLock();){
                channel = new VoiceChannelImpl(id2, guild);
                guildVoiceView.getMap().put(id2, channel);
                playbackCache = voiceView.getMap().put(id2, channel) == null;
            }
        }
        ((VoiceChannelImpl)((VoiceChannelImpl)channel.setParent(json.getLong("parent_id", 0L))).setName(json.getString("name"))).setPosition(json.getInt("position")).setUserLimit(json.getInt("user_limit")).setBitrate(json.getInt("bitrate")).setRegion(json.getString("rtc_region", null));
        this.createOverridesPass(channel, json.getArray("permission_overwrites"));
        if (playbackCache) {
            this.getJDA().getEventCache().playbackCache(EventCache.Type.CHANNEL, id2);
        }
        return channel;
    }

    public PrivateChannel createPrivateChannel(DataObject json) {
        long channelId = json.getUnsignedLong("id");
        PrivateChannel channel = this.api.getPrivateChannelById(channelId);
        this.api.usedPrivateChannel(channelId);
        if (channel != null) {
            return channel;
        }
        DataObject recipient = json.hasKey("recipients") ? json.getArray("recipients").getObject(0) : json.getObject("recipient");
        long userId = recipient.getLong("id");
        UserImpl user = (UserImpl)this.getJDA().getUserById(userId);
        if (user == null) {
            user = this.createUser(recipient);
        }
        return this.createPrivateChannel(json, user);
    }

    public PrivateChannel createPrivateChannel(DataObject json, UserImpl user) {
        long channelId = json.getLong("id");
        PrivateChannelImpl priv = new PrivateChannelImpl(channelId, user).setLastMessageId(json.getLong("last_message_id", 0L));
        user.setPrivateChannel(priv);
        SnowflakeCacheViewImpl<PrivateChannel> privateView = this.getJDA().getPrivateChannelsView();
        try (UnlockHook hook = privateView.writeLock();){
            privateView.getMap().put(channelId, priv);
        }
        this.api.usedPrivateChannel(channelId);
        this.getJDA().getEventCache().playbackCache(EventCache.Type.CHANNEL, channelId);
        return priv;
    }

    public void createOverridesPass(AbstractChannelImpl<?, ?> channel, DataArray overrides) {
        for (int i2 = 0; i2 < overrides.length(); ++i2) {
            try {
                this.createPermissionOverride(overrides.getObject(i2), channel);
                continue;
            }
            catch (NoSuchElementException e2) {
                LOG.debug("{}. Ignoring PermissionOverride.", (Object)e2.getMessage());
                continue;
            }
            catch (IllegalArgumentException e3) {
                LOG.warn("{}. Ignoring PermissionOverride.", (Object)e3.getMessage());
            }
        }
    }

    public Role createRole(GuildImpl guild, DataObject roleJson, long guildId) {
        RoleImpl role;
        boolean playbackCache = false;
        long id2 = roleJson.getLong("id");
        if (guild == null) {
            guild = (GuildImpl)this.getJDA().getGuildsView().get(guildId);
        }
        if ((role = (RoleImpl)guild.getRolesView().get(id2)) == null) {
            SortedSnowflakeCacheViewImpl<Role> roleView = guild.getRolesView();
            try (UnlockHook hook = roleView.writeLock();){
                role = new RoleImpl(id2, guild);
                playbackCache = roleView.getMap().put(id2, role) == null;
            }
        }
        int color = roleJson.getInt("color");
        role.setName(roleJson.getString("name")).setRawPosition(roleJson.getInt("position")).setRawPermissions(roleJson.getLong("permissions")).setManaged(roleJson.getBoolean("managed")).setHoisted(roleJson.getBoolean("hoist")).setColor(color == 0 ? 0x1FFFFFFF : color).setMentionable(roleJson.getBoolean("mentionable")).setTags(roleJson.optObject("tags").orElseGet(DataObject::empty));
        if (playbackCache) {
            this.getJDA().getEventCache().playbackCache(EventCache.Type.ROLE, id2);
        }
        return role;
    }

    public Message createMessage(DataObject jsonObject) {
        return this.createMessage(jsonObject, false);
    }

    public Message createMessage(DataObject jsonObject, boolean modifyCache) {
        long channelId = jsonObject.getLong("channel_id");
        MessageChannel chan = this.getJDA().getTextChannelById(channelId);
        if (chan == null) {
            chan = this.getJDA().getPrivateChannelById(channelId);
        }
        if (chan == null && !jsonObject.isNull("guild_id")) {
            throw new IllegalArgumentException(MISSING_CHANNEL);
        }
        return this.createMessage(jsonObject, chan, modifyCache);
    }

    public Message createMessage(DataObject jsonObject, @Nullable MessageChannel channel, boolean modifyCache) {
        User user;
        long channelId = jsonObject.getUnsignedLong("channel_id");
        if (channel != null && channelId != channel.getIdLong() && (channel = this.api.getTextChannelById(channelId)) == null) {
            channel = this.api.getPrivateChannelById(channelId);
        }
        long id2 = jsonObject.getLong("id");
        DataObject author = jsonObject.getObject("author");
        long authorId = author.getLong("id");
        MemberImpl member = null;
        if (channel == null && jsonObject.isNull("guild_id") && authorId != this.getJDA().getSelfUser().getIdLong()) {
            DataObject channelData = DataObject.empty().put("id", channelId).put("recipient", author);
            channel = this.createPrivateChannel(channelData);
        } else if (channel == null) {
            throw new IllegalArgumentException(MISSING_CHANNEL);
        }
        if (channel.getType().isGuild() && !jsonObject.isNull("member")) {
            DataObject memberJson = jsonObject.getObject("member");
            memberJson.put("user", author);
            GuildChannel guildChannel = (GuildChannel)((Object)channel);
            Guild guild = guildChannel.getGuild();
            member = this.createMember((GuildImpl)guild, memberJson);
            if (modifyCache) {
                this.updateMemberCache(member);
            }
        }
        String content = jsonObject.getString("content", "");
        boolean fromWebhook = jsonObject.hasKey("webhook_id");
        boolean pinned = jsonObject.getBoolean("pinned");
        boolean tts = jsonObject.getBoolean("tts");
        boolean mentionsEveryone = jsonObject.getBoolean("mention_everyone");
        OffsetDateTime editTime = jsonObject.isNull("edited_timestamp") ? null : OffsetDateTime.parse(jsonObject.getString("edited_timestamp"));
        String nonce = jsonObject.isNull("nonce") ? null : jsonObject.get("nonce").toString();
        int flags = jsonObject.getInt("flags", 0);
        MessageChannel tmpChannel = channel;
        List<Message.Attachment> attachments = this.map(jsonObject, "attachments", this::createMessageAttachment);
        List<MessageEmbed> embeds = this.map(jsonObject, "embeds", this::createMessageEmbed);
        List<MessageReaction> reactions = this.map(jsonObject, "reactions", obj -> this.createMessageReaction(tmpChannel, id2, (DataObject)obj));
        List<MessageSticker> stickers = this.map(jsonObject, "stickers", this::createSticker);
        MessageActivity activity = null;
        if (!jsonObject.isNull("activity")) {
            activity = EntityBuilder.createMessageActivity(jsonObject);
        }
        switch (channel.getType()) {
            case PRIVATE: {
                if (authorId == this.getJDA().getSelfUser().getIdLong()) {
                    user = this.getJDA().getSelfUser();
                    break;
                }
                user = ((PrivateChannel)channel).getUser();
                break;
            }
            case GROUP: {
                throw new IllegalStateException("Cannot build a message for a group channel, how did this even get here?");
            }
            case TEXT: {
                Guild guild = ((TextChannel)channel).getGuild();
                if (member == null) {
                    member = (MemberImpl)guild.getMemberById(authorId);
                }
                User user2 = user = member != null ? member.getUser() : null;
                if (user != null) break;
                if (fromWebhook || !modifyCache) {
                    user = this.createUser(author);
                    break;
                }
                throw new IllegalArgumentException(MISSING_USER);
            }
            default: {
                throw new IllegalArgumentException("Invalid Channel for creating a Message [" + (Object)((Object)channel.getType()) + ']');
            }
        }
        if (modifyCache && !fromWebhook) {
            this.updateUser((UserImpl)user, author);
        }
        TLongHashSet mentionedRoles = new TLongHashSet();
        TLongHashSet mentionedUsers = new TLongHashSet(this.map(jsonObject, "mentions", o2 -> o2.getLong("id")));
        Optional<DataArray> roleMentionArr = jsonObject.optArray("mention_roles");
        roleMentionArr.ifPresent(arr2 -> {
            for (int i2 = 0; i2 < arr2.length(); ++i2) {
                mentionedRoles.add(arr2.getLong(i2));
            }
        });
        MessageType type = MessageType.fromId(jsonObject.getInt("type"));
        Message referencedMessage = null;
        if (!jsonObject.isNull("referenced_message")) {
            DataObject referenceJson = jsonObject.getObject("referenced_message");
            try {
                referencedMessage = this.createMessage(referenceJson, channel, false);
            }
            catch (IllegalArgumentException ex2) {
                if (UNKNOWN_MESSAGE_TYPE.equals(ex2.getMessage())) {
                    LOG.debug("Received referenced message with unknown type. Type: {}", (Object)referenceJson.getInt("type", -1));
                }
                if (MISSING_CHANNEL.equals(ex2.getMessage())) {
                    LOG.debug("Received referenced message with unknown channel. channel_id: {} Type: {}", (Object)referenceJson.getUnsignedLong("channel_id", 0L), (Object)referenceJson.getInt("type", -1));
                }
                throw ex2;
            }
        }
        List<ActionRow> components = Collections.emptyList();
        Optional<DataArray> componentsArrayOpt = jsonObject.optArray("components");
        if (componentsArrayOpt.isPresent()) {
            DataArray array = componentsArrayOpt.get();
            components = array.stream(DataArray::getObject).filter(it2 -> it2.getInt("type", 0) == 1).map(ActionRow::fromData).collect(Collectors.toList());
        }
        if (type == MessageType.UNKNOWN) {
            throw new IllegalArgumentException(UNKNOWN_MESSAGE_TYPE);
        }
        if (type.isSystem()) {
            SystemMessage message = new SystemMessage(id2, channel, type, fromWebhook, mentionsEveryone, mentionedUsers, mentionedRoles, tts, pinned, content, nonce, user, member, activity, editTime, reactions, attachments, embeds, stickers, flags);
            return message;
        }
        ReceivedMessage message = new ReceivedMessage(id2, channel, type, referencedMessage, fromWebhook, mentionsEveryone, mentionedUsers, mentionedRoles, tts, pinned, content, nonce, user, member, activity, editTime, reactions, attachments, embeds, stickers, components, flags);
        GuildImpl guild = message.isFromGuild() ? (GuildImpl)message.getGuild() : null;
        ArrayList<User> mentionedUsersList = new ArrayList<User>();
        ArrayList<Member> mentionedMembersList = new ArrayList<Member>();
        DataArray userMentions = jsonObject.getArray("mentions");
        for (int i2 = 0; i2 < userMentions.length(); ++i2) {
            Member mentionedMember;
            DataObject mentionJson = userMentions.getObject(i2);
            if (guild == null || mentionJson.isNull("member")) {
                UserImpl mentionedUser = this.createUser(mentionJson);
                mentionedUsersList.add(mentionedUser);
                if (guild == null || (mentionedMember = guild.getMember(mentionedUser)) == null) continue;
                mentionedMembersList.add(mentionedMember);
                continue;
            }
            DataObject memberJson = mentionJson.getObject("member");
            mentionJson.remove("member");
            memberJson.put("user", mentionJson);
            mentionedMember = this.createMember(guild, memberJson);
            mentionedMembersList.add(mentionedMember);
            mentionedUsersList.add(mentionedMember.getUser());
        }
        message.setMentions(mentionedUsersList, mentionedMembersList);
        return message;
    }

    private static MessageActivity createMessageActivity(DataObject jsonObject) {
        DataObject activityData = jsonObject.getObject("activity");
        MessageActivity.ActivityType activityType = MessageActivity.ActivityType.fromId(activityData.getInt("type"));
        String partyId = activityData.getString("party_id", null);
        MessageActivity.Application application = null;
        if (!jsonObject.isNull("application")) {
            DataObject applicationData = jsonObject.getObject("application");
            String name = applicationData.getString("name");
            String description = applicationData.getString("description", "");
            String iconId = applicationData.getString("icon", null);
            String coverId = applicationData.getString("cover_image", null);
            long applicationId = applicationData.getLong("id");
            application = new MessageActivity.Application(name, description, iconId, coverId, applicationId);
        }
        if (activityType == MessageActivity.ActivityType.UNKNOWN) {
            LOG.debug("Received an unknown ActivityType, Activity: {}", (Object)activityData);
        }
        return new MessageActivity(activityType, partyId, application);
    }

    public MessageReaction createMessageReaction(MessageChannel chan, long id2, DataObject obj) {
        MessageReaction.ReactionEmote reactionEmote;
        DataObject emoji = obj.getObject("emoji");
        Long emojiID = emoji.isNull("id") ? null : Long.valueOf(emoji.getLong("id"));
        String name = emoji.getString("name", "");
        boolean animated = emoji.getBoolean("animated");
        int count = obj.getInt("count", -1);
        boolean me2 = obj.getBoolean("me");
        if (emojiID != null) {
            Emote emote = this.getJDA().getEmoteById(emojiID);
            if (emote == null) {
                emote = new EmoteImpl((long)emojiID, this.getJDA()).setAnimated(animated).setName(name);
            }
            reactionEmote = MessageReaction.ReactionEmote.fromCustom(emote);
        } else {
            reactionEmote = MessageReaction.ReactionEmote.fromUnicode(name, this.getJDA());
        }
        return new MessageReaction(chan, reactionEmote, id2, me2, count);
    }

    public Message.Attachment createMessageAttachment(DataObject jsonObject) {
        int width = jsonObject.getInt("width", -1);
        int height = jsonObject.getInt("height", -1);
        int size = jsonObject.getInt("size");
        String url = jsonObject.getString("url");
        String proxyUrl = jsonObject.getString("proxy_url");
        String filename = jsonObject.getString("filename");
        String contentType = jsonObject.getString("content_type", null);
        long id2 = jsonObject.getLong("id");
        return new Message.Attachment(id2, url, proxyUrl, filename, contentType, size, height, width, this.getJDA());
    }

    public MessageEmbed createMessageEmbed(DataObject content) {
        MessageEmbed.ImageInfo image;
        MessageEmbed.Footer footer;
        MessageEmbed.VideoInfo video;
        MessageEmbed.AuthorInfo author;
        MessageEmbed.Provider provider;
        MessageEmbed.Thumbnail thumbnail;
        int color;
        if (content.isNull("type")) {
            throw new IllegalStateException("Encountered embed object with missing/null type field for Json: " + content);
        }
        EmbedType type = EmbedType.fromKey(content.getString("type"));
        String url = content.getString("url", null);
        String title = content.getString("title", null);
        String description = content.getString("description", null);
        OffsetDateTime timestamp = content.isNull("timestamp") ? null : OffsetDateTime.parse(content.getString("timestamp"));
        int n2 = color = content.isNull("color") ? 0x1FFFFFFF : content.getInt("color");
        if (content.isNull("thumbnail")) {
            thumbnail = null;
        } else {
            DataObject obj2 = content.getObject("thumbnail");
            thumbnail = new MessageEmbed.Thumbnail(obj2.getString("url", null), obj2.getString("proxy_url", null), obj2.getInt("width", -1), obj2.getInt("height", -1));
        }
        if (content.isNull("provider")) {
            provider = null;
        } else {
            DataObject obj3 = content.getObject("provider");
            provider = new MessageEmbed.Provider(obj3.getString("name", null), obj3.getString("url", null));
        }
        if (content.isNull("author")) {
            author = null;
        } else {
            DataObject obj4 = content.getObject("author");
            author = new MessageEmbed.AuthorInfo(obj4.getString("name", null), obj4.getString("url", null), obj4.getString("icon_url", null), obj4.getString("proxy_icon_url", null));
        }
        if (content.isNull("video")) {
            video = null;
        } else {
            DataObject obj5 = content.getObject("video");
            video = new MessageEmbed.VideoInfo(obj5.getString("url", null), obj5.getInt("width", -1), obj5.getInt("height", -1));
        }
        if (content.isNull("footer")) {
            footer = null;
        } else {
            DataObject obj6 = content.getObject("footer");
            footer = new MessageEmbed.Footer(obj6.getString("text", null), obj6.getString("icon_url", null), obj6.getString("proxy_icon_url", null));
        }
        if (content.isNull("image")) {
            image = null;
        } else {
            DataObject obj7 = content.getObject("image");
            image = new MessageEmbed.ImageInfo(obj7.getString("url", null), obj7.getString("proxy_url", null), obj7.getInt("width", -1), obj7.getInt("height", -1));
        }
        List<MessageEmbed.Field> fields = this.map(content, "fields", obj -> new MessageEmbed.Field(obj.getString("name", null), obj.getString("value", null), obj.getBoolean("inline"), false));
        return EntityBuilder.createMessageEmbed(url, title, description, type, timestamp, color, thumbnail, provider, author, video, footer, image, fields);
    }

    public static MessageEmbed createMessageEmbed(String url, String title, String description, EmbedType type, OffsetDateTime timestamp, int color, MessageEmbed.Thumbnail thumbnail, MessageEmbed.Provider siteProvider, MessageEmbed.AuthorInfo author, MessageEmbed.VideoInfo videoInfo, MessageEmbed.Footer footer, MessageEmbed.ImageInfo image, List<MessageEmbed.Field> fields) {
        return new MessageEmbed(url, title, description, type, timestamp, color, thumbnail, siteProvider, author, videoInfo, footer, image, fields);
    }

    public MessageSticker createSticker(DataObject content) {
        Set<String> tags;
        long id2 = content.getLong("id");
        String name = content.getString("name");
        String description = content.getString("description");
        long packId = content.getLong("pack_id");
        String asset = content.getString("asset");
        String previewAsset = content.getString("preview_asset", null);
        MessageSticker.StickerFormat format = MessageSticker.StickerFormat.fromId(content.getInt("format_type"));
        if (content.isNull("tags")) {
            tags = Collections.emptySet();
        } else {
            String[] split = content.getString("tags").split(", ");
            HashSet<String> tmp = new HashSet<String>(Arrays.asList(split));
            tags = Collections.unmodifiableSet(tmp);
        }
        return new MessageSticker(id2, name, description, packId, asset, previewAsset, format, tags);
    }

    @Nullable
    public PermissionOverride createPermissionOverride(DataObject override, AbstractChannelImpl<?, ?> chan) {
        boolean role;
        int type = override.getInt("type");
        long id2 = override.getLong("id");
        boolean bl2 = role = type == 0;
        if (role && chan.getGuild().getRoleById(id2) == null) {
            throw new NoSuchElementException("Attempted to create a PermissionOverride for a non-existent role! JSON: " + override);
        }
        if (!role && type != 1) {
            throw new IllegalArgumentException("Provided with an unknown PermissionOverride type! JSON: " + override);
        }
        if (!role && id2 != this.api.getSelfUser().getIdLong() && !this.api.isCacheFlagSet(CacheFlag.MEMBER_OVERRIDES)) {
            return null;
        }
        long allow = override.getLong("allow");
        long deny = override.getLong("deny");
        if (id2 == chan.getGuild().getIdLong() && (allow | deny) == 0L) {
            return null;
        }
        PermissionOverrideImpl permOverride = (PermissionOverrideImpl)chan.getOverrideMap().get(id2);
        if (permOverride == null) {
            permOverride = new PermissionOverrideImpl(chan, id2, role);
            chan.getOverrideMap().put(id2, permOverride);
        }
        return permOverride.setAllow(allow).setDeny(deny);
    }

    public WebhookImpl createWebhook(DataObject object) {
        return this.createWebhook(object, false);
    }

    public WebhookImpl createWebhook(DataObject object, boolean allowMissingChannel) {
        DataObject source;
        long id2 = object.getLong("id");
        long guildId = object.getUnsignedLong("guild_id");
        long channelId = object.getUnsignedLong("channel_id");
        String token = object.getString("token", null);
        WebhookType type = WebhookType.fromKey(object.getInt("type", -1));
        TextChannel channel = this.getJDA().getTextChannelById(channelId);
        if (channel == null && !allowMissingChannel) {
            throw new NullPointerException(String.format("Tried to create Webhook for an un-cached TextChannel! WebhookId: %s ChannelId: %s GuildId: %s", id2, channelId, guildId));
        }
        Object name = !object.isNull("name") ? object.get("name") : null;
        Object avatar = !object.isNull("avatar") ? object.get("avatar") : null;
        DataObject fakeUser = DataObject.empty().put("username", name).put("discriminator", "0000").put("id", id2).put("avatar", avatar);
        UserImpl defaultUser = this.createUser(fakeUser);
        Optional<DataObject> ownerJson = object.optObject("user");
        User owner = null;
        if (ownerJson.isPresent()) {
            DataObject json = ownerJson.get();
            long userId = json.getLong("id");
            owner = this.getJDA().getUserById(userId);
            if (owner == null) {
                json.put("id", userId);
                owner = this.createUser(json);
            }
        }
        Member ownerMember = owner == null || channel == null ? null : channel.getGuild().getMember(owner);
        WebhookImpl webhook = new WebhookImpl(channel, this.getJDA(), id2, type).setToken(token).setOwner(ownerMember, owner).setUser(defaultUser);
        if (!object.isNull("source_channel")) {
            source = object.getObject("source_channel");
            webhook.setSourceChannel(new Webhook.ChannelReference(source.getUnsignedLong("id"), source.getString("name")));
        }
        if (!object.isNull("source_guild")) {
            source = object.getObject("source_guild");
            webhook.setSourceGuild(new Webhook.GuildReference(source.getUnsignedLong("id"), source.getString("name")));
        }
        return webhook;
    }

    public Invite createInvite(DataObject object) {
        OffsetDateTime timeCreated;
        boolean temporary;
        int uses;
        int maxUses;
        int maxAge;
        boolean expanded;
        InviteImpl.GroupImpl group;
        InviteImpl.ChannelImpl channel;
        InviteImpl.GuildImpl guild;
        Invite.InviteType type;
        String code = object.getString("code");
        UserImpl inviter = object.hasKey("inviter") ? this.createUser(object.getObject("inviter")) : null;
        DataObject channelObject = object.getObject("channel");
        ChannelType channelType = ChannelType.fromId(channelObject.getInt("type"));
        if (channelType == ChannelType.GROUP) {
            type = Invite.InviteType.GROUP;
            guild = null;
            channel = null;
            String groupName = channelObject.getString("name", "");
            long groupId = channelObject.getLong("id");
            String groupIconId = channelObject.getString("icon", null);
            List<String> usernames = channelObject.isNull("recipients") ? null : this.map(channelObject, "recipients", json -> json.getString("username"));
            group = new InviteImpl.GroupImpl(groupIconId, groupName, groupId, usernames);
        } else if (channelType.isGuild()) {
            type = Invite.InviteType.GUILD;
            DataObject guildObject = object.getObject("guild");
            String guildIconId = guildObject.getString("icon", null);
            long guildId = guildObject.getLong("id");
            String guildName = guildObject.getString("name");
            String guildSplashId = guildObject.getString("splash", null);
            Guild.VerificationLevel guildVerificationLevel = Guild.VerificationLevel.fromKey(guildObject.getInt("verification_level", -1));
            int presenceCount = object.getInt("approximate_presence_count", -1);
            int memberCount = object.getInt("approximate_member_count", -1);
            Set<String> guildFeatures = guildObject.isNull("features") ? Collections.emptySet() : Collections.unmodifiableSet(StreamSupport.stream(guildObject.getArray("features").spliterator(), false).map(String::valueOf).collect(Collectors.toSet()));
            guild = new InviteImpl.GuildImpl(guildId, guildIconId, guildName, guildSplashId, guildVerificationLevel, presenceCount, memberCount, guildFeatures);
            String channelName = channelObject.getString("name");
            long channelId = channelObject.getLong("id");
            channel = new InviteImpl.ChannelImpl(channelId, channelName, channelType);
            group = null;
        } else {
            type = Invite.InviteType.UNKNOWN;
            guild = null;
            channel = null;
            group = null;
        }
        if (object.hasKey("max_uses")) {
            expanded = true;
            maxAge = object.getInt("max_age");
            maxUses = object.getInt("max_uses");
            uses = object.getInt("uses");
            temporary = object.getBoolean("temporary");
            timeCreated = OffsetDateTime.parse(object.getString("created_at"));
        } else {
            expanded = false;
            maxAge = -1;
            maxUses = -1;
            uses = -1;
            temporary = false;
            timeCreated = null;
        }
        return new InviteImpl(this.getJDA(), code, expanded, inviter, maxAge, maxUses, temporary, timeCreated, uses, channel, guild, group, type);
    }

    public Template createTemplate(DataObject object) {
        String code = object.getString("code");
        String name = object.getString("name");
        String description = object.getString("description", null);
        int uses = object.getInt("usage_count");
        UserImpl creator = this.createUser(object.getObject("creator"));
        OffsetDateTime createdAt = OffsetDateTime.parse(object.getString("created_at"));
        OffsetDateTime updatedAt = OffsetDateTime.parse(object.getString("updated_at"));
        long guildId = object.getLong("source_guild_id");
        DataObject guildObject = object.getObject("serialized_source_guild");
        String guildName = guildObject.getString("name");
        String guildDescription = guildObject.getString("description", null);
        String region = guildObject.getString("region", null);
        String guildIconId = guildObject.getString("icon_hash", null);
        Guild.VerificationLevel guildVerificationLevel = Guild.VerificationLevel.fromKey(guildObject.getInt("verification_level", -1));
        Guild.NotificationLevel notificationLevel = Guild.NotificationLevel.fromKey(guildObject.getInt("default_message_notifications", 0));
        Guild.ExplicitContentLevel explicitContentLevel = Guild.ExplicitContentLevel.fromKey(guildObject.getInt("explicit_content_filter", 0));
        Locale locale = Locale.forLanguageTag(guildObject.getString("preferred_locale", "en"));
        Guild.Timeout afkTimeout = Guild.Timeout.fromKey(guildObject.getInt("afk_timeout", 0));
        DataArray roleArray = guildObject.getArray("roles");
        DataArray channelsArray = guildObject.getArray("channels");
        long afkChannelId = guildObject.getLong("afk_channel_id", -1L);
        long systemChannelId = guildObject.getLong("system_channel_id", -1L);
        ArrayList<TemplateRole> roles = new ArrayList<TemplateRole>();
        for (int i2 = 0; i2 < roleArray.length(); ++i2) {
            DataObject obj = roleArray.getObject(i2);
            long roleId = obj.getLong("id");
            String roleName = obj.getString("name");
            int roleColor = obj.getInt("color");
            boolean hoisted = obj.getBoolean("hoist");
            boolean mentionable = obj.getBoolean("mentionable");
            long rawPermissions = obj.getLong("permissions");
            roles.add(new TemplateRole(roleId, roleName, roleColor == 0 ? 0x1FFFFFFF : roleColor, hoisted, mentionable, rawPermissions));
        }
        ArrayList<TemplateChannel> channels = new ArrayList<TemplateChannel>();
        for (int i3 = 0; i3 < channelsArray.length(); ++i3) {
            DataObject obj = channelsArray.getObject(i3);
            long channelId = obj.getLong("id");
            int type = obj.getInt("type");
            ChannelType channelType = ChannelType.fromId(type);
            String channelName = obj.getString("name");
            String topic = obj.getString("topic", null);
            int rawPosition = obj.getInt("position");
            long parentId = obj.getLong("parent_id", -1L);
            boolean nsfw = obj.getBoolean("nsfw");
            int slowmode = obj.getInt("rate_limit_per_user");
            int bitrate = obj.getInt("bitrate");
            int userLimit = obj.getInt("user_limit");
            ArrayList<TemplateChannel.PermissionOverride> permissionOverrides = new ArrayList<TemplateChannel.PermissionOverride>();
            DataArray overrides = obj.getArray("permission_overwrites");
            for (int j2 = 0; j2 < overrides.length(); ++j2) {
                DataObject overrideObj = overrides.getObject(j2);
                long overrideId = overrideObj.getLong("id");
                long allow = overrideObj.getLong("allow");
                long deny = overrideObj.getLong("deny");
                permissionOverrides.add(new TemplateChannel.PermissionOverride(overrideId, allow, deny));
            }
            channels.add(new TemplateChannel(channelId, channelType, channelName, topic, rawPosition, parentId, type == 5, permissionOverrides, nsfw, slowmode, bitrate, userLimit));
        }
        TemplateChannel afkChannel = channels.stream().filter(templateChannel -> templateChannel.getIdLong() == afkChannelId).findFirst().orElse(null);
        TemplateChannel systemChannel = channels.stream().filter(templateChannel -> templateChannel.getIdLong() == systemChannelId).findFirst().orElse(null);
        TemplateGuild guild = new TemplateGuild(guildId, guildName, guildDescription, region, guildIconId, guildVerificationLevel, notificationLevel, explicitContentLevel, locale, afkTimeout, afkChannel, systemChannel, roles, channels);
        boolean synced = !object.getBoolean("is_dirty", false);
        return new Template(this.getJDA(), code, name, description, uses, creator, createdAt, updatedAt, guild, synced);
    }

    public ApplicationInfo createApplicationInfo(DataObject object) {
        String description = object.getString("description");
        boolean doesBotRequireCodeGrant = object.getBoolean("bot_require_code_grant");
        String iconId = object.getString("icon", null);
        long id2 = object.getLong("id");
        String name = object.getString("name");
        boolean isBotPublic = object.getBoolean("bot_public");
        UserImpl owner = this.createUser(object.getObject("owner"));
        ApplicationTeam team = !object.isNull("team") ? this.createApplicationTeam(object.getObject("team")) : null;
        return new ApplicationInfoImpl(this.getJDA(), description, doesBotRequireCodeGrant, iconId, id2, isBotPublic, name, owner, team);
    }

    public ApplicationTeam createApplicationTeam(DataObject object) {
        String iconId = object.getString("icon", null);
        long id2 = object.getUnsignedLong("id");
        long ownerId = object.getUnsignedLong("owner_user_id", 0L);
        List<TeamMember> members = this.map(object, "members", o2 -> {
            DataObject userJson = o2.getObject("user");
            TeamMember.MembershipState state = TeamMember.MembershipState.fromKey(o2.getInt("membership_state"));
            UserImpl user = this.createUser(userJson);
            return new TeamMemberImpl(user, state, id2);
        });
        return new ApplicationTeamImpl(iconId, members, id2, ownerId);
    }

    public AuditLogEntry createAuditLogEntry(GuildImpl guild, DataObject entryJson, DataObject userJson, DataObject webhookJson) {
        Set<AuditLogChange> changesList;
        long targetId = entryJson.getLong("target_id", 0L);
        long id2 = entryJson.getLong("id");
        int typeKey = entryJson.getInt("action_type");
        DataArray changes = entryJson.isNull("changes") ? null : entryJson.getArray("changes");
        DataObject options = entryJson.isNull("options") ? null : entryJson.getObject("options");
        String reason = entryJson.getString("reason", null);
        UserImpl user = userJson == null ? null : this.createUser(userJson);
        WebhookImpl webhook = webhookJson == null ? null : this.createWebhook(webhookJson);
        ActionType type = ActionType.from(typeKey);
        if (changes != null) {
            changesList = new HashSet(changes.length());
            for (int i2 = 0; i2 < changes.length(); ++i2) {
                DataObject object = changes.getObject(i2);
                AuditLogChange change = this.createAuditLogChange(object);
                changesList.add(change);
            }
        } else {
            changesList = Collections.emptySet();
        }
        CaseInsensitiveMap<String, AuditLogChange> changeMap = new CaseInsensitiveMap<String, AuditLogChange>(this.changeToMap(changesList));
        CaseInsensitiveMap<String, Object> optionMap = options != null ? new CaseInsensitiveMap<String, Object>(options.toMap()) : null;
        return new AuditLogEntry(type, typeKey, id2, targetId, guild, user, webhook, reason, changeMap, optionMap);
    }

    public AuditLogChange createAuditLogChange(DataObject change) {
        String key = change.getString("key");
        Object oldValue = change.isNull("old_value") ? null : change.get("old_value");
        Object newValue = change.isNull("new_value") ? null : change.get("new_value");
        return new AuditLogChange(oldValue, newValue, key);
    }

    private Map<String, AuditLogChange> changeToMap(Set<AuditLogChange> changesList) {
        return changesList.stream().collect(Collectors.toMap(AuditLogChange::getKey, UnaryOperator.identity()));
    }

    private <T> List<T> map(DataObject jsonObject, String key, Function<DataObject, T> convert) {
        if (jsonObject.isNull(key)) {
            return Collections.emptyList();
        }
        DataArray arr2 = jsonObject.getArray(key);
        ArrayList<T> mappedObjects = new ArrayList<T>(arr2.length());
        for (int i2 = 0; i2 < arr2.length(); ++i2) {
            DataObject obj = arr2.getObject(i2);
            T result = convert.apply(obj);
            if (result == null) continue;
            mappedObjects.add(result);
        }
        return mappedObjects;
    }

    static {
        HashSet<String> tmp = new HashSet<String>();
        tmp.add("application_id");
        tmp.add("assets");
        tmp.add("details");
        tmp.add("flags");
        tmp.add("party");
        tmp.add("session_id");
        tmp.add("state");
        tmp.add("sync_id");
        richGameFields = Collections.unmodifiableSet(tmp);
    }
}

