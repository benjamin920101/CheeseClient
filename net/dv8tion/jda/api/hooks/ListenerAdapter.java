/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.application.ApplicationCommandCreateEvent;
import net.dv8tion.jda.api.events.application.ApplicationCommandDeleteEvent;
import net.dv8tion.jda.api.events.application.ApplicationCommandUpdateEvent;
import net.dv8tion.jda.api.events.application.GenericApplicationCommandEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.GenericStoreChannelEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.update.GenericStoreChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNewsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateRegionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateRolesEvent;
import net.dv8tion.jda.api.events.emote.update.GenericEmoteUpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildTimeoutEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
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
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivitiesEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivityOrderEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateFlagsEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.internal.utils.ClassWalker;

public abstract class ListenerAdapter
implements EventListener {
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static final ConcurrentMap<Class<?>, MethodHandle> methods = new ConcurrentHashMap();
    private static final Set<Class<?>> unresolved = ConcurrentHashMap.newKeySet();

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    public void onTextChannelUpdatePermissions(@Nonnull TextChannelUpdatePermissionsEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    public void onStoreChannelUpdatePermissions(@Nonnull StoreChannelUpdatePermissionsEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    public void onVoiceChannelUpdatePermissions(@Nonnull VoiceChannelUpdatePermissionsEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    public void onCategoryUpdatePermissions(@Nonnull CategoryUpdatePermissionsEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.0")
    @ReplaceWith(value="onGuildMemberRemove(GuildMemberRemoveEvent)")
    public void onGuildMemberLeave(@Nonnull GuildMemberLeaveEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.1")
    @ReplaceWith(value="onResumed(ResumedEvent)")
    public void onResume(@Nonnull ResumedEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.2.1")
    @ReplaceWith(value="onReconnected(ReconnectedEvent)")
    public void onReconnect(@Nonnull ReconnectedEvent event) {
    }

    public void onGenericEvent(@Nonnull GenericEvent event) {
    }

    public void onGenericUpdate(@Nonnull UpdateEvent<?, ?> event) {
    }

    public void onRawGateway(@Nonnull RawGatewayEvent event) {
    }

    public void onGatewayPing(@Nonnull GatewayPingEvent event) {
    }

    public void onReady(@Nonnull ReadyEvent event) {
    }

    public void onResumed(@Nonnull ResumedEvent event) {
    }

    public void onReconnected(@Nonnull ReconnectedEvent event) {
    }

    public void onDisconnect(@Nonnull DisconnectEvent event) {
    }

    public void onShutdown(@Nonnull ShutdownEvent event) {
    }

    public void onStatusChange(@Nonnull StatusChangeEvent event) {
    }

    public void onException(@Nonnull ExceptionEvent event) {
    }

    public void onSlashCommand(@Nonnull SlashCommandEvent event) {
    }

    public void onButtonClick(@Nonnull ButtonClickEvent event) {
    }

    public void onApplicationCommandUpdate(@Nonnull ApplicationCommandUpdateEvent event) {
    }

    public void onApplicationCommandDelete(@Nonnull ApplicationCommandDeleteEvent event) {
    }

    public void onApplicationCommandCreate(@Nonnull ApplicationCommandCreateEvent event) {
    }

    public void onUserUpdateName(@Nonnull UserUpdateNameEvent event) {
    }

    public void onUserUpdateDiscriminator(@Nonnull UserUpdateDiscriminatorEvent event) {
    }

    public void onUserUpdateAvatar(@Nonnull UserUpdateAvatarEvent event) {
    }

    public void onUserUpdateOnlineStatus(@Nonnull UserUpdateOnlineStatusEvent event) {
    }

    public void onUserUpdateActivityOrder(@Nonnull UserUpdateActivityOrderEvent event) {
    }

    public void onUserUpdateFlags(@Nonnull UserUpdateFlagsEvent event) {
    }

    public void onUserTyping(@Nonnull UserTypingEvent event) {
    }

    public void onUserActivityStart(@Nonnull UserActivityStartEvent event) {
    }

    public void onUserActivityEnd(@Nonnull UserActivityEndEvent event) {
    }

    public void onUserUpdateActivities(@Nonnull UserUpdateActivitiesEvent event) {
    }

    public void onSelfUpdateAvatar(@Nonnull SelfUpdateAvatarEvent event) {
    }

    public void onSelfUpdateMFA(@Nonnull SelfUpdateMFAEvent event) {
    }

    public void onSelfUpdateName(@Nonnull SelfUpdateNameEvent event) {
    }

    public void onSelfUpdateVerified(@Nonnull SelfUpdateVerifiedEvent event) {
    }

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
    }

    public void onGuildMessageUpdate(@Nonnull GuildMessageUpdateEvent event) {
    }

    public void onGuildMessageDelete(@Nonnull GuildMessageDeleteEvent event) {
    }

    public void onGuildMessageEmbed(@Nonnull GuildMessageEmbedEvent event) {
    }

    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
    }

    public void onGuildMessageReactionRemove(@Nonnull GuildMessageReactionRemoveEvent event) {
    }

    public void onGuildMessageReactionRemoveAll(@Nonnull GuildMessageReactionRemoveAllEvent event) {
    }

    public void onGuildMessageReactionRemoveEmote(@Nonnull GuildMessageReactionRemoveEmoteEvent event) {
    }

    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event) {
    }

    public void onPrivateMessageUpdate(@Nonnull PrivateMessageUpdateEvent event) {
    }

    public void onPrivateMessageDelete(@Nonnull PrivateMessageDeleteEvent event) {
    }

    public void onPrivateMessageEmbed(@Nonnull PrivateMessageEmbedEvent event) {
    }

    public void onPrivateMessageReactionAdd(@Nonnull PrivateMessageReactionAddEvent event) {
    }

    public void onPrivateMessageReactionRemove(@Nonnull PrivateMessageReactionRemoveEvent event) {
    }

    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
    }

    public void onMessageUpdate(@Nonnull MessageUpdateEvent event) {
    }

    public void onMessageDelete(@Nonnull MessageDeleteEvent event) {
    }

    public void onMessageBulkDelete(@Nonnull MessageBulkDeleteEvent event) {
    }

    public void onMessageEmbed(@Nonnull MessageEmbedEvent event) {
    }

    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
    }

    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {
    }

    public void onMessageReactionRemoveAll(@Nonnull MessageReactionRemoveAllEvent event) {
    }

    public void onMessageReactionRemoveEmote(@Nonnull MessageReactionRemoveEmoteEvent event) {
    }

    public void onPermissionOverrideDelete(@Nonnull PermissionOverrideDeleteEvent event) {
    }

    public void onPermissionOverrideUpdate(@Nonnull PermissionOverrideUpdateEvent event) {
    }

    public void onPermissionOverrideCreate(@Nonnull PermissionOverrideCreateEvent event) {
    }

    public void onStoreChannelDelete(@Nonnull StoreChannelDeleteEvent event) {
    }

    public void onStoreChannelUpdateName(@Nonnull StoreChannelUpdateNameEvent event) {
    }

    public void onStoreChannelUpdatePosition(@Nonnull StoreChannelUpdatePositionEvent event) {
    }

    public void onStoreChannelCreate(@Nonnull StoreChannelCreateEvent event) {
    }

    public void onTextChannelDelete(@Nonnull TextChannelDeleteEvent event) {
    }

    public void onTextChannelUpdateName(@Nonnull TextChannelUpdateNameEvent event) {
    }

    public void onTextChannelUpdateTopic(@Nonnull TextChannelUpdateTopicEvent event) {
    }

    public void onTextChannelUpdatePosition(@Nonnull TextChannelUpdatePositionEvent event) {
    }

    public void onTextChannelUpdateNSFW(@Nonnull TextChannelUpdateNSFWEvent event) {
    }

    public void onTextChannelUpdateParent(@Nonnull TextChannelUpdateParentEvent event) {
    }

    public void onTextChannelUpdateSlowmode(@Nonnull TextChannelUpdateSlowmodeEvent event) {
    }

    public void onTextChannelUpdateNews(@Nonnull TextChannelUpdateNewsEvent event) {
    }

    public void onTextChannelCreate(@Nonnull TextChannelCreateEvent event) {
    }

    public void onVoiceChannelDelete(@Nonnull VoiceChannelDeleteEvent event) {
    }

    public void onVoiceChannelUpdateName(@Nonnull VoiceChannelUpdateNameEvent event) {
    }

    public void onVoiceChannelUpdatePosition(@Nonnull VoiceChannelUpdatePositionEvent event) {
    }

    public void onVoiceChannelUpdateUserLimit(@Nonnull VoiceChannelUpdateUserLimitEvent event) {
    }

    public void onVoiceChannelUpdateBitrate(@Nonnull VoiceChannelUpdateBitrateEvent event) {
    }

    public void onVoiceChannelUpdateParent(@Nonnull VoiceChannelUpdateParentEvent event) {
    }

    public void onVoiceChannelUpdateRegion(@Nonnull VoiceChannelUpdateRegionEvent event) {
    }

    public void onVoiceChannelCreate(@Nonnull VoiceChannelCreateEvent event) {
    }

    public void onCategoryDelete(@Nonnull CategoryDeleteEvent event) {
    }

    public void onCategoryUpdateName(@Nonnull CategoryUpdateNameEvent event) {
    }

    public void onCategoryUpdatePosition(@Nonnull CategoryUpdatePositionEvent event) {
    }

    public void onCategoryCreate(@Nonnull CategoryCreateEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.3.0")
    public void onPrivateChannelCreate(@Nonnull PrivateChannelCreateEvent event) {
    }

    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @DeprecatedSince(value="4.3.0")
    public void onPrivateChannelDelete(@Nonnull PrivateChannelDeleteEvent event) {
    }

    public void onGuildReady(@Nonnull GuildReadyEvent event) {
    }

    public void onGuildTimeout(@Nonnull GuildTimeoutEvent event) {
    }

    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
    }

    public void onGuildLeave(@Nonnull GuildLeaveEvent event) {
    }

    public void onGuildAvailable(@Nonnull GuildAvailableEvent event) {
    }

    public void onGuildUnavailable(@Nonnull GuildUnavailableEvent event) {
    }

    public void onUnavailableGuildJoined(@Nonnull UnavailableGuildJoinedEvent event) {
    }

    public void onUnavailableGuildLeave(@Nonnull UnavailableGuildLeaveEvent event) {
    }

    public void onGuildBan(@Nonnull GuildBanEvent event) {
    }

    public void onGuildUnban(@Nonnull GuildUnbanEvent event) {
    }

    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
    }

    public void onGuildUpdateAfkChannel(@Nonnull GuildUpdateAfkChannelEvent event) {
    }

    public void onGuildUpdateSystemChannel(@Nonnull GuildUpdateSystemChannelEvent event) {
    }

    public void onGuildUpdateRulesChannel(@Nonnull GuildUpdateRulesChannelEvent event) {
    }

    public void onGuildUpdateCommunityUpdatesChannel(@Nonnull GuildUpdateCommunityUpdatesChannelEvent event) {
    }

    public void onGuildUpdateAfkTimeout(@Nonnull GuildUpdateAfkTimeoutEvent event) {
    }

    public void onGuildUpdateExplicitContentLevel(@Nonnull GuildUpdateExplicitContentLevelEvent event) {
    }

    public void onGuildUpdateIcon(@Nonnull GuildUpdateIconEvent event) {
    }

    public void onGuildUpdateMFALevel(@Nonnull GuildUpdateMFALevelEvent event) {
    }

    public void onGuildUpdateName(@Nonnull GuildUpdateNameEvent event) {
    }

    public void onGuildUpdateNotificationLevel(@Nonnull GuildUpdateNotificationLevelEvent event) {
    }

    public void onGuildUpdateOwner(@Nonnull GuildUpdateOwnerEvent event) {
    }

    @Deprecated
    @ReplaceWith(value="VoiceChannelUpdateRegionEvent")
    public void onGuildUpdateRegion(@Nonnull GuildUpdateRegionEvent event) {
    }

    public void onGuildUpdateSplash(@Nonnull GuildUpdateSplashEvent event) {
    }

    public void onGuildUpdateVerificationLevel(@Nonnull GuildUpdateVerificationLevelEvent event) {
    }

    public void onGuildUpdateLocale(@Nonnull GuildUpdateLocaleEvent event) {
    }

    public void onGuildUpdateFeatures(@Nonnull GuildUpdateFeaturesEvent event) {
    }

    public void onGuildUpdateVanityCode(@Nonnull GuildUpdateVanityCodeEvent event) {
    }

    public void onGuildUpdateBanner(@Nonnull GuildUpdateBannerEvent event) {
    }

    public void onGuildUpdateDescription(@Nonnull GuildUpdateDescriptionEvent event) {
    }

    public void onGuildUpdateBoostTier(@Nonnull GuildUpdateBoostTierEvent event) {
    }

    public void onGuildUpdateBoostCount(@Nonnull GuildUpdateBoostCountEvent event) {
    }

    public void onGuildUpdateMaxMembers(@Nonnull GuildUpdateMaxMembersEvent event) {
    }

    public void onGuildUpdateMaxPresences(@Nonnull GuildUpdateMaxPresencesEvent event) {
    }

    public void onGuildInviteCreate(@Nonnull GuildInviteCreateEvent event) {
    }

    public void onGuildInviteDelete(@Nonnull GuildInviteDeleteEvent event) {
    }

    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
    }

    public void onGuildMemberRoleAdd(@Nonnull GuildMemberRoleAddEvent event) {
    }

    public void onGuildMemberRoleRemove(@Nonnull GuildMemberRoleRemoveEvent event) {
    }

    public void onGuildMemberUpdate(@Nonnull GuildMemberUpdateEvent event) {
    }

    public void onGuildMemberUpdateNickname(@Nonnull GuildMemberUpdateNicknameEvent event) {
    }

    public void onGuildMemberUpdateBoostTime(@Nonnull GuildMemberUpdateBoostTimeEvent event) {
    }

    public void onGuildMemberUpdatePending(@Nonnull GuildMemberUpdatePendingEvent event) {
    }

    public void onGuildVoiceUpdate(@Nonnull GuildVoiceUpdateEvent event) {
    }

    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
    }

    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
    }

    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
    }

    public void onGuildVoiceMute(@Nonnull GuildVoiceMuteEvent event) {
    }

    public void onGuildVoiceDeafen(@Nonnull GuildVoiceDeafenEvent event) {
    }

    public void onGuildVoiceGuildMute(@Nonnull GuildVoiceGuildMuteEvent event) {
    }

    public void onGuildVoiceGuildDeafen(@Nonnull GuildVoiceGuildDeafenEvent event) {
    }

    public void onGuildVoiceSelfMute(@Nonnull GuildVoiceSelfMuteEvent event) {
    }

    public void onGuildVoiceSelfDeafen(@Nonnull GuildVoiceSelfDeafenEvent event) {
    }

    public void onGuildVoiceSuppress(@Nonnull GuildVoiceSuppressEvent event) {
    }

    public void onGuildVoiceStream(@Nonnull GuildVoiceStreamEvent event) {
    }

    public void onRoleCreate(@Nonnull RoleCreateEvent event) {
    }

    public void onRoleDelete(@Nonnull RoleDeleteEvent event) {
    }

    public void onRoleUpdateColor(@Nonnull RoleUpdateColorEvent event) {
    }

    public void onRoleUpdateHoisted(@Nonnull RoleUpdateHoistedEvent event) {
    }

    public void onRoleUpdateMentionable(@Nonnull RoleUpdateMentionableEvent event) {
    }

    public void onRoleUpdateName(@Nonnull RoleUpdateNameEvent event) {
    }

    public void onRoleUpdatePermissions(@Nonnull RoleUpdatePermissionsEvent event) {
    }

    public void onRoleUpdatePosition(@Nonnull RoleUpdatePositionEvent event) {
    }

    public void onEmoteAdded(@Nonnull EmoteAddedEvent event) {
    }

    public void onEmoteRemoved(@Nonnull EmoteRemovedEvent event) {
    }

    public void onEmoteUpdateName(@Nonnull EmoteUpdateNameEvent event) {
    }

    public void onEmoteUpdateRoles(@Nonnull EmoteUpdateRolesEvent event) {
    }

    public void onHttpRequest(@Nonnull HttpRequestEvent event) {
    }

    public void onGenericApplicationCommand(@Nonnull GenericApplicationCommandEvent event) {
    }

    public void onGenericInteractionCreate(@Nonnull GenericInteractionCreateEvent event) {
    }

    public void onGenericMessage(@Nonnull GenericMessageEvent event) {
    }

    public void onGenericMessageReaction(@Nonnull GenericMessageReactionEvent event) {
    }

    public void onGenericGuildMessage(@Nonnull GenericGuildMessageEvent event) {
    }

    public void onGenericGuildMessageReaction(@Nonnull GenericGuildMessageReactionEvent event) {
    }

    public void onGenericPrivateMessage(@Nonnull GenericPrivateMessageEvent event) {
    }

    public void onGenericPrivateMessageReaction(@Nonnull GenericPrivateMessageReactionEvent event) {
    }

    public void onGenericUser(@Nonnull GenericUserEvent event) {
    }

    public void onGenericUserPresence(@Nonnull GenericUserPresenceEvent event) {
    }

    public void onGenericSelfUpdate(@Nonnull GenericSelfUpdateEvent event) {
    }

    public void onGenericStoreChannel(@Nonnull GenericStoreChannelEvent event) {
    }

    public void onGenericStoreChannelUpdate(@Nonnull GenericStoreChannelUpdateEvent event) {
    }

    public void onGenericTextChannel(@Nonnull GenericTextChannelEvent event) {
    }

    public void onGenericTextChannelUpdate(@Nonnull GenericTextChannelUpdateEvent event) {
    }

    public void onGenericVoiceChannel(@Nonnull GenericVoiceChannelEvent event) {
    }

    public void onGenericVoiceChannelUpdate(@Nonnull GenericVoiceChannelUpdateEvent event) {
    }

    public void onGenericCategory(@Nonnull GenericCategoryEvent event) {
    }

    public void onGenericCategoryUpdate(@Nonnull GenericCategoryUpdateEvent event) {
    }

    public void onGenericGuild(@Nonnull GenericGuildEvent event) {
    }

    public void onGenericGuildUpdate(@Nonnull GenericGuildUpdateEvent event) {
    }

    public void onGenericGuildInvite(@Nonnull GenericGuildInviteEvent event) {
    }

    public void onGenericGuildMember(@Nonnull GenericGuildMemberEvent event) {
    }

    public void onGenericGuildMemberUpdate(@Nonnull GenericGuildMemberUpdateEvent event) {
    }

    public void onGenericGuildVoice(@Nonnull GenericGuildVoiceEvent event) {
    }

    public void onGenericRole(@Nonnull GenericRoleEvent event) {
    }

    public void onGenericRoleUpdate(@Nonnull GenericRoleUpdateEvent event) {
    }

    public void onGenericEmote(@Nonnull GenericEmoteEvent event) {
    }

    public void onGenericEmoteUpdate(@Nonnull GenericEmoteUpdateEvent event) {
    }

    public void onGenericPermissionOverride(@Nonnull GenericPermissionOverrideEvent event) {
    }

    @Override
    public final void onEvent(@Nonnull GenericEvent event) {
        this.onGenericEvent(event);
        if (event instanceof UpdateEvent) {
            this.onGenericUpdate((UpdateEvent)event);
        }
        if (event instanceof ResumedEvent) {
            this.onResume((ResumedEvent)event);
        } else if (event instanceof ReconnectedEvent) {
            this.onReconnect((ReconnectedEvent)event);
        }
        for (Class<?> clazz : ClassWalker.range(event.getClass(), GenericEvent.class)) {
            if (unresolved.contains(clazz)) continue;
            MethodHandle mh = methods.computeIfAbsent(clazz, ListenerAdapter::findMethod);
            if (mh == null) {
                unresolved.add(clazz);
                continue;
            }
            try {
                mh.invoke(this, event);
            }
            catch (Throwable throwable) {
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException)throwable;
                }
                if (throwable instanceof Error) {
                    throw (Error)throwable;
                }
                throw new IllegalStateException(throwable);
            }
        }
    }

    private static MethodHandle findMethod(Class<?> clazz) {
        String name = clazz.getSimpleName();
        MethodType type = MethodType.methodType(Void.TYPE, clazz);
        try {
            name = "on" + name.substring(0, name.length() - "Event".length());
            return lookup.findVirtual(ListenerAdapter.class, name, type);
        }
        catch (IllegalAccessException | NoSuchMethodException reflectiveOperationException) {
            return null;
        }
    }

    static {
        Collections.addAll(unresolved, Object.class, Event.class, UpdateEvent.class, GenericEvent.class);
    }
}

