/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserUpdateEvent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.utils.Checks;

public enum GatewayIntent {
    GUILD_MEMBERS(1),
    GUILD_BANS(2),
    GUILD_EMOJIS(3),
    GUILD_WEBHOOKS(5),
    GUILD_INVITES(6),
    GUILD_VOICE_STATES(7),
    GUILD_PRESENCES(8),
    GUILD_MESSAGES(9),
    GUILD_MESSAGE_REACTIONS(10),
    GUILD_MESSAGE_TYPING(11),
    DIRECT_MESSAGES(12),
    DIRECT_MESSAGE_REACTIONS(13),
    DIRECT_MESSAGE_TYPING(14);

    public static final int ALL_INTENTS;
    public static final int DEFAULT;
    private final int rawValue;
    private final int offset;

    private GatewayIntent(int offset) {
        this.offset = offset;
        this.rawValue = 1 << offset;
    }

    public int getRawValue() {
        return this.rawValue;
    }

    public int getOffset() {
        return this.offset;
    }

    @Nonnull
    public static EnumSet<GatewayIntent> getIntents(int raw) {
        EnumSet<GatewayIntent> set = EnumSet.noneOf(GatewayIntent.class);
        for (GatewayIntent intent : GatewayIntent.values()) {
            if ((intent.getRawValue() & raw) == 0) continue;
            set.add(intent);
        }
        return set;
    }

    public static int getRaw(@Nonnull Collection<GatewayIntent> set) {
        int raw = 0;
        for (GatewayIntent intent : set) {
            raw |= intent.rawValue;
        }
        return raw;
    }

    public static int getRaw(@Nonnull GatewayIntent intent, GatewayIntent ... set) {
        Checks.notNull((Object)intent, "Intent");
        Checks.notNull(set, "Intent");
        return GatewayIntent.getRaw(EnumSet.of(intent, set));
    }

    @Nonnull
    public static EnumSet<GatewayIntent> fromCacheFlags(@Nonnull CacheFlag flag, CacheFlag ... other) {
        Checks.notNull((Object)flag, "CacheFlag");
        Checks.noneNull((Object[])other, "CacheFlag");
        return GatewayIntent.fromCacheFlags(EnumSet.of(flag, other));
    }

    @Nonnull
    public static EnumSet<GatewayIntent> fromCacheFlags(@Nonnull Collection<CacheFlag> flags) {
        EnumSet<GatewayIntent> intents = EnumSet.noneOf(GatewayIntent.class);
        for (CacheFlag flag : flags) {
            Checks.notNull((Object)flag, "CacheFlag");
            GatewayIntent intent = flag.getRequiredIntent();
            if (intent == null) continue;
            intents.add(intent);
        }
        return intents;
    }

    @Nonnull
    @SafeVarargs
    public static EnumSet<GatewayIntent> fromEvents(Class<? extends GenericEvent> ... events) {
        Checks.noneNull(events, "Event");
        return GatewayIntent.fromEvents(Arrays.asList(events));
    }

    @Nonnull
    public static EnumSet<GatewayIntent> fromEvents(@Nonnull Collection<Class<? extends GenericEvent>> events) {
        EnumSet<GatewayIntent> intents = EnumSet.noneOf(GatewayIntent.class);
        for (Class<? extends GenericEvent> event : events) {
            Checks.notNull(event, "Event");
            if (GenericUserPresenceEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_PRESENCES);
                continue;
            }
            if (GenericUserUpdateEvent.class.isAssignableFrom(event) || GenericGuildMemberEvent.class.isAssignableFrom(event) || GuildMemberRemoveEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_MEMBERS);
                continue;
            }
            if (GuildBanEvent.class.isAssignableFrom(event) || GuildUnbanEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_BANS);
                continue;
            }
            if (GenericEmoteEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_EMOJIS);
                continue;
            }
            if (GenericGuildInviteEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_INVITES);
                continue;
            }
            if (GenericGuildVoiceEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_VOICE_STATES);
                continue;
            }
            if (GenericGuildMessageReactionEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_MESSAGE_REACTIONS);
                continue;
            }
            if (GenericGuildMessageEvent.class.isAssignableFrom(event) || MessageBulkDeleteEvent.class.isAssignableFrom(event)) {
                intents.add(GUILD_MESSAGES);
                continue;
            }
            if (GenericPrivateMessageReactionEvent.class.isAssignableFrom(event)) {
                intents.add(DIRECT_MESSAGE_REACTIONS);
                continue;
            }
            if (GenericPrivateMessageEvent.class.isAssignableFrom(event)) {
                intents.add(DIRECT_MESSAGES);
                continue;
            }
            if (GenericMessageReactionEvent.class.isAssignableFrom(event)) {
                Collections.addAll(intents, GUILD_MESSAGE_REACTIONS, DIRECT_MESSAGE_REACTIONS);
                continue;
            }
            if (GenericMessageEvent.class.isAssignableFrom(event)) {
                Collections.addAll(intents, GUILD_MESSAGES, DIRECT_MESSAGES);
                continue;
            }
            if (!UserTypingEvent.class.isAssignableFrom(event)) continue;
            Collections.addAll(intents, GUILD_MESSAGE_TYPING, DIRECT_MESSAGE_TYPING);
        }
        return intents;
    }

    @Nonnull
    public static EnumSet<GatewayIntent> from(@Nonnull Collection<Class<? extends GenericEvent>> events, @Nonnull Collection<CacheFlag> flags) {
        EnumSet<GatewayIntent> intents = GatewayIntent.fromEvents(events);
        intents.addAll(GatewayIntent.fromCacheFlags(flags));
        return intents;
    }

    static {
        ALL_INTENTS = 1 | GatewayIntent.getRaw(EnumSet.allOf(GatewayIntent.class));
        DEFAULT = ALL_INTENTS & ~GatewayIntent.getRaw(GUILD_MEMBERS, GUILD_PRESENCES, GUILD_WEBHOOKS, GUILD_MESSAGE_TYPING, DIRECT_MESSAGE_TYPING);
    }
}

