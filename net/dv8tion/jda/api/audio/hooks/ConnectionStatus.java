/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.hooks;

public enum ConnectionStatus {
    NOT_CONNECTED(false),
    SHUTTING_DOWN(false),
    CONNECTING_AWAITING_ENDPOINT,
    CONNECTING_AWAITING_WEBSOCKET_CONNECT,
    CONNECTING_AWAITING_AUTHENTICATION,
    CONNECTING_ATTEMPTING_UDP_DISCOVERY,
    CONNECTING_AWAITING_READY,
    CONNECTED,
    DISCONNECTED_LOST_PERMISSION(false),
    DISCONNECTED_CHANNEL_DELETED(false),
    DISCONNECTED_REMOVED_FROM_GUILD(false),
    DISCONNECTED_KICKED_FROM_CHANNEL(false),
    DISCONNECTED_REMOVED_DURING_RECONNECT(false),
    DISCONNECTED_AUTHENTICATION_FAILURE,
    AUDIO_REGION_CHANGE,
    ERROR_LOST_CONNECTION,
    ERROR_CANNOT_RESUME,
    ERROR_WEBSOCKET_UNABLE_TO_CONNECT,
    ERROR_UNSUPPORTED_ENCRYPTION_MODES,
    ERROR_UDP_UNABLE_TO_CONNECT,
    ERROR_CONNECTION_TIMEOUT;

    private final boolean shouldReconnect;

    private ConnectionStatus() {
        this(true);
    }

    private ConnectionStatus(boolean shouldReconnect) {
        this.shouldReconnect = shouldReconnect;
    }

    public boolean shouldReconnect() {
        return this.shouldReconnect;
    }
}

