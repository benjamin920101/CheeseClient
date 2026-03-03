/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum CloseCode {
    RECONNECT(4900, "The connection has been closed to reconnect."),
    GRACEFUL_CLOSE(1000, "The connection was closed gracefully or your heartbeats timed out."),
    CLOUD_FLARE_LOAD(1001, "The connection was closed due to CloudFlare load balancing."),
    INTERNAL_SERVER_ERROR(1006, "Something broke on the remote's end, sorry 'bout that... Try reconnecting!"),
    UNKNOWN_ERROR(4000, "The server is not sure what went wrong. Try reconnecting?"),
    UNKNOWN_OPCODE(4001, "You sent an invalid Gateway OP Code. Don't do that!"),
    DECODE_ERROR(4002, "You sent an invalid payload to the server. Don't do that!"),
    NOT_AUTHENTICATED(4003, "You sent a payload prior to identifying."),
    AUTHENTICATION_FAILED(4004, "The account token sent with your identify payload is incorrect.", false),
    ALREADY_AUTHENTICATED(4005, "You sent more than one identify payload. Don't do that!"),
    INVALID_SEQ(4007, "The seq sent when resuming the session was invalid. Reconnect and start a new session."),
    RATE_LIMITED(4008, "Woah nelly! You're sending payloads to us too quickly. Slow it down!"),
    SESSION_TIMEOUT(4009, "Your session timed out. Reconnect and start a new one."),
    INVALID_SHARD(4010, "You sent an invalid shard when identifying.", false),
    SHARDING_REQUIRED(4011, "The session would have handled too many guilds - you are required to shard your connection in order to connect.", false),
    INVALID_INTENTS(4013, "Invalid intents.", false),
    DISALLOWED_INTENTS(4014, "Disallowed intents. Your bot might not be eligible to request a privileged intent such as GUILD_PRESENCES or GUILD_MEMBERS.", false);

    private final int code;
    private final boolean isReconnect;
    private final String meaning;

    private CloseCode(int code, String meaning) {
        this(code, meaning, true);
    }

    private CloseCode(int code, String meaning, boolean isReconnect) {
        this.code = code;
        this.meaning = meaning;
        this.isReconnect = isReconnect;
    }

    public int getCode() {
        return this.code;
    }

    @Nonnull
    public String getMeaning() {
        return this.meaning;
    }

    public boolean isReconnect() {
        return this.isReconnect;
    }

    public String toString() {
        return "CloseCode(" + this.code + " / " + this.meaning + ")";
    }

    @Nullable
    public static CloseCode from(int code) {
        for (CloseCode c2 : CloseCode.values()) {
            if (c2.code != code) continue;
            return c2;
        }
        return null;
    }
}

