/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBannerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostTierEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateCommunityUpdatesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateExplicitContentLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateFeaturesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateLocaleEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxMembersEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxPresencesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRulesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVanityCodeEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.handle.EventCache;
import net.dv8tion.jda.internal.handle.SocketHandler;
import net.dv8tion.jda.internal.requests.WebSocketClient;

public class GuildUpdateHandler
extends SocketHandler {
    public GuildUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        Set<String> features;
        TextChannel communityUpdatesChannel;
        long id2 = content.getLong("id");
        if (this.getJDA().getGuildSetupController().isLocked(id2)) {
            return id2;
        }
        GuildImpl guild = (GuildImpl)this.getJDA().getGuildById(id2);
        if (guild == null) {
            EventCache.LOG.debug("Caching GUILD_UPDATE for guild with id: {}", (Object)id2);
            this.getJDA().getEventCache().cache(EventCache.Type.GUILD, id2, this.responseNumber, this.allContent, this::handle);
            return null;
        }
        int maxMembers = content.getInt("max_members", 0);
        int maxPresences = content.getInt("max_presences", 5000);
        if (guild.getMaxMembers() == 0) {
            guild.setMaxPresences(maxPresences);
            guild.setMaxMembers(maxMembers);
        }
        long ownerId = content.getLong("owner_id");
        int boostCount = content.getInt("premium_subscription_count", 0);
        int boostTier = content.getInt("premium_tier", 0);
        String description = content.getString("description", null);
        String vanityCode = content.getString("vanity_url_code", null);
        String bannerId = content.getString("banner", null);
        String name = content.getString("name");
        String iconId = content.getString("icon", null);
        String splashId = content.getString("splash", null);
        String region = content.getString("region");
        Guild.VerificationLevel verificationLevel = Guild.VerificationLevel.fromKey(content.getInt("verification_level"));
        Guild.NotificationLevel notificationLevel = Guild.NotificationLevel.fromKey(content.getInt("default_message_notifications"));
        Guild.MFALevel mfaLevel = Guild.MFALevel.fromKey(content.getInt("mfa_level"));
        Guild.ExplicitContentLevel explicitContentLevel = Guild.ExplicitContentLevel.fromKey(content.getInt("explicit_content_filter"));
        Guild.Timeout afkTimeout = Guild.Timeout.fromKey(content.getInt("afk_timeout"));
        Locale locale = Locale.forLanguageTag(content.getString("preferred_locale"));
        VoiceChannel afkChannel = content.isNull("afk_channel_id") ? null : (VoiceChannel)guild.getVoiceChannelsView().get(content.getLong("afk_channel_id"));
        TextChannel systemChannel = content.isNull("system_channel_id") ? null : (TextChannel)guild.getTextChannelsView().get(content.getLong("system_channel_id"));
        TextChannel rulesChannel = content.isNull("rules_channel_id") ? null : (TextChannel)guild.getTextChannelsView().get(content.getLong("rules_channel_id"));
        TextChannel textChannel = communityUpdatesChannel = content.isNull("public_updates_channel_id") ? null : (TextChannel)guild.getTextChannelsView().get(content.getLong("public_updates_channel_id"));
        if (!content.isNull("features")) {
            DataArray featureArr = content.getArray("features");
            features = StreamSupport.stream(featureArr.spliterator(), false).map(String::valueOf).collect(Collectors.toSet());
        } else {
            features = Collections.emptySet();
        }
        if (ownerId != guild.getOwnerIdLong()) {
            long oldOwnerId = guild.getOwnerIdLong();
            Member oldOwner = guild.getOwner();
            Member newOwner = (Member)guild.getMembersView().get(ownerId);
            if (newOwner == null) {
                WebSocketClient.LOG.debug("Received {} with owner not in cache. UserId: {} GuildId: {}", this.allContent.get("t"), ownerId, id2);
            }
            guild.setOwner(newOwner);
            guild.setOwnerId(ownerId);
            this.getJDA().handleEvent(new GuildUpdateOwnerEvent((JDA)this.getJDA(), this.responseNumber, (Guild)guild, oldOwner, oldOwnerId, ownerId));
        }
        if (!Objects.equals(description, guild.getDescription())) {
            String oldDescription = guild.getDescription();
            guild.setDescription(description);
            this.getJDA().handleEvent(new GuildUpdateDescriptionEvent(this.getJDA(), this.responseNumber, guild, oldDescription));
        }
        if (!Objects.equals(bannerId, guild.getBannerId())) {
            String oldBanner = guild.getBannerId();
            guild.setBannerId(bannerId);
            this.getJDA().handleEvent(new GuildUpdateBannerEvent(this.getJDA(), this.responseNumber, guild, oldBanner));
        }
        if (!Objects.equals(vanityCode, guild.getVanityCode())) {
            String oldCode = guild.getVanityCode();
            guild.setVanityCode(vanityCode);
            this.getJDA().handleEvent(new GuildUpdateVanityCodeEvent(this.getJDA(), this.responseNumber, guild, oldCode));
        }
        if (maxMembers != guild.getMaxMembers()) {
            int oldMax = guild.getMaxMembers();
            guild.setMaxMembers(maxMembers);
            this.getJDA().handleEvent(new GuildUpdateMaxMembersEvent(this.getJDA(), this.responseNumber, guild, oldMax));
        }
        if (maxPresences != guild.getMaxPresences()) {
            int oldMax = guild.getMaxPresences();
            guild.setMaxPresences(maxPresences);
            this.getJDA().handleEvent(new GuildUpdateMaxPresencesEvent(this.getJDA(), this.responseNumber, guild, oldMax));
        }
        if (boostCount != guild.getBoostCount()) {
            int oldCount = guild.getBoostCount();
            guild.setBoostCount(boostCount);
            this.getJDA().handleEvent(new GuildUpdateBoostCountEvent(this.getJDA(), this.responseNumber, guild, oldCount));
        }
        if (Guild.BoostTier.fromKey(boostTier) != guild.getBoostTier()) {
            Guild.BoostTier oldTier = guild.getBoostTier();
            guild.setBoostTier(boostTier);
            this.getJDA().handleEvent(new GuildUpdateBoostTierEvent(this.getJDA(), this.responseNumber, guild, oldTier));
        }
        if (!Objects.equals(name, guild.getName())) {
            String oldName = guild.getName();
            guild.setName(name);
            this.getJDA().handleEvent(new GuildUpdateNameEvent(this.getJDA(), this.responseNumber, guild, oldName));
        }
        if (!Objects.equals(iconId, guild.getIconId())) {
            String oldIconId = guild.getIconId();
            guild.setIconId(iconId);
            this.getJDA().handleEvent(new GuildUpdateIconEvent(this.getJDA(), this.responseNumber, guild, oldIconId));
        }
        if (!features.equals(guild.getFeatures())) {
            Set<String> oldFeatures = guild.getFeatures();
            guild.setFeatures(features);
            this.getJDA().handleEvent(new GuildUpdateFeaturesEvent(this.getJDA(), this.responseNumber, guild, oldFeatures));
        }
        if (!Objects.equals(splashId, guild.getSplashId())) {
            String oldSplashId = guild.getSplashId();
            guild.setSplashId(splashId);
            this.getJDA().handleEvent(new GuildUpdateSplashEvent(this.getJDA(), this.responseNumber, guild, oldSplashId));
        }
        if (!Objects.equals(region, guild.getRegionRaw())) {
            String oldRegion = guild.getRegionRaw();
            guild.setRegion(region);
            this.getJDA().handleEvent(new GuildUpdateRegionEvent(this.getJDA(), this.responseNumber, guild, oldRegion));
        }
        if (!Objects.equals((Object)verificationLevel, (Object)guild.getVerificationLevel())) {
            Guild.VerificationLevel oldVerificationLevel = guild.getVerificationLevel();
            guild.setVerificationLevel(verificationLevel);
            this.getJDA().handleEvent(new GuildUpdateVerificationLevelEvent(this.getJDA(), this.responseNumber, guild, oldVerificationLevel));
        }
        if (!Objects.equals((Object)notificationLevel, (Object)guild.getDefaultNotificationLevel())) {
            Guild.NotificationLevel oldNotificationLevel = guild.getDefaultNotificationLevel();
            guild.setDefaultNotificationLevel(notificationLevel);
            this.getJDA().handleEvent(new GuildUpdateNotificationLevelEvent(this.getJDA(), this.responseNumber, guild, oldNotificationLevel));
        }
        if (!Objects.equals((Object)mfaLevel, (Object)guild.getRequiredMFALevel())) {
            Guild.MFALevel oldMfaLevel = guild.getRequiredMFALevel();
            guild.setRequiredMFALevel(mfaLevel);
            this.getJDA().handleEvent(new GuildUpdateMFALevelEvent(this.getJDA(), this.responseNumber, guild, oldMfaLevel));
        }
        if (!Objects.equals((Object)explicitContentLevel, (Object)guild.getExplicitContentLevel())) {
            Guild.ExplicitContentLevel oldExplicitContentLevel = guild.getExplicitContentLevel();
            guild.setExplicitContentLevel(explicitContentLevel);
            this.getJDA().handleEvent(new GuildUpdateExplicitContentLevelEvent(this.getJDA(), this.responseNumber, guild, oldExplicitContentLevel));
        }
        if (!Objects.equals((Object)afkTimeout, (Object)guild.getAfkTimeout())) {
            Guild.Timeout oldAfkTimeout = guild.getAfkTimeout();
            guild.setAfkTimeout(afkTimeout);
            this.getJDA().handleEvent(new GuildUpdateAfkTimeoutEvent(this.getJDA(), this.responseNumber, guild, oldAfkTimeout));
        }
        if (!Objects.equals(locale, guild.getLocale())) {
            Locale oldLocale = guild.getLocale();
            guild.setLocale(locale.toLanguageTag());
            this.getJDA().handleEvent(new GuildUpdateLocaleEvent(this.getJDA(), this.responseNumber, guild, oldLocale));
        }
        if (!Objects.equals(afkChannel, guild.getAfkChannel())) {
            VoiceChannel oldAfkChannel = guild.getAfkChannel();
            guild.setAfkChannel(afkChannel);
            this.getJDA().handleEvent(new GuildUpdateAfkChannelEvent(this.getJDA(), this.responseNumber, guild, oldAfkChannel));
        }
        if (!Objects.equals(systemChannel, guild.getSystemChannel())) {
            TextChannel oldSystemChannel = guild.getSystemChannel();
            guild.setSystemChannel(systemChannel);
            this.getJDA().handleEvent(new GuildUpdateSystemChannelEvent(this.getJDA(), this.responseNumber, guild, oldSystemChannel));
        }
        if (!Objects.equals(rulesChannel, guild.getRulesChannel())) {
            TextChannel oldRulesChannel = guild.getRulesChannel();
            guild.setRulesChannel(rulesChannel);
            this.getJDA().handleEvent(new GuildUpdateRulesChannelEvent(this.getJDA(), this.responseNumber, guild, oldRulesChannel));
        }
        if (!Objects.equals(communityUpdatesChannel, guild.getCommunityUpdatesChannel())) {
            TextChannel oldCommunityUpdatesChannel = guild.getCommunityUpdatesChannel();
            guild.setCommunityUpdatesChannel(communityUpdatesChannel);
            this.getJDA().handleEvent(new GuildUpdateCommunityUpdatesChannelEvent(this.getJDA(), this.responseNumber, guild, oldCommunityUpdatesChannel));
        }
        return null;
    }
}

