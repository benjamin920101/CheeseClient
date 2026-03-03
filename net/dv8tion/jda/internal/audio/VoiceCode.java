/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.audio;

public final class VoiceCode {
    public static final int IDENTIFY = 0;
    public static final int SELECT_PROTOCOL = 1;
    public static final int READY = 2;
    public static final int HEARTBEAT = 3;
    public static final int SESSION_DESCRIPTION = 4;
    public static final int USER_SPEAKING_UPDATE = 5;
    public static final int HEARTBEAT_ACK = 6;
    public static final int RESUME = 7;
    public static final int HELLO = 8;
    public static final int RESUMED = 9;
    public static final int USER_DISCONNECT = 13;

    public static enum Close {
        HEARTBEAT_TIMEOUT(1000, "We did not heartbeat in time"),
        UNKNOWN_OP_CODE(4001, "Sent an invalid op code"),
        NOT_AUTHENTICATED(4003, "Tried to send payload before authenticating session"),
        AUTHENTICATION_FAILED(4004, "The token sent in the identify payload is incorrect"),
        ALREADY_AUTHENTICATED(4005, "Tried to authenticate when already authenticated"),
        INVALID_SESSION(4006, "The session with which we attempted to resume is invalid"),
        SESSION_TIMEOUT(4009, "Heartbeat timed out"),
        SERVER_NOT_FOUND(4011, "The server we attempted to connect to was not found"),
        UNKNOWN_PROTOCOL(4012, "The selected protocol is not supported"),
        DISCONNECTED(4014, "The connection has been dropped normally"),
        SERVER_CRASH(4015, "The server we were connected to has crashed"),
        UNKNOWN_ENCRYPTION_MODE(4016, "The specified encryption method is not supported"),
        UNKNOWN(0, "Unknown code");

        private final int code;
        private final String meaning;

        private Close(int code, String meaning) {
            this.code = code;
            this.meaning = meaning;
        }

        public static Close from(int code) {
            for (Close c2 : Close.values()) {
                if (c2.code != code) continue;
                return c2;
            }
            return UNKNOWN;
        }

        public int getCode() {
            return this.code;
        }

        public String getMeaning() {
            return this.meaning;
        }
    }
}

