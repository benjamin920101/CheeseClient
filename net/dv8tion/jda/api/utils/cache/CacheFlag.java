/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.cache;

import java.util.EnumSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.requests.GatewayIntent;

public enum CacheFlag {
    ACTIVITY(GatewayIntent.GUILD_PRESENCES),
    VOICE_STATE(GatewayIntent.GUILD_VOICE_STATES),
    EMOTE(GatewayIntent.GUILD_EMOJIS),
    CLIENT_STATUS(GatewayIntent.GUILD_PRESENCES),
    MEMBER_OVERRIDES,
    ROLE_TAGS,
    ONLINE_STATUS(GatewayIntent.GUILD_PRESENCES);

    private static final EnumSet<CacheFlag> privileged;
    private final GatewayIntent requiredIntent;

    private CacheFlag() {
        this(null);
    }

    private CacheFlag(GatewayIntent requiredIntent) {
        this.requiredIntent = requiredIntent;
    }

    @Nullable
    public GatewayIntent getRequiredIntent() {
        return this.requiredIntent;
    }

    public boolean isPresence() {
        return this.requiredIntent == GatewayIntent.GUILD_PRESENCES;
    }

    @Nonnull
    public static EnumSet<CacheFlag> getPrivileged() {
        return EnumSet.copyOf(privileged);
    }

    static {
        privileged = EnumSet.of(ACTIVITY, CLIENT_STATUS, ONLINE_STATUS);
    }
}

