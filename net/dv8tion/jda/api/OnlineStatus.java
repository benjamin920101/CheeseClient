/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api;

public enum OnlineStatus {
    ONLINE("online"),
    IDLE("idle"),
    DO_NOT_DISTURB("dnd"),
    INVISIBLE("invisible"),
    OFFLINE("offline"),
    UNKNOWN("");

    private final String key;

    private OnlineStatus(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static OnlineStatus fromKey(String key) {
        for (OnlineStatus onlineStatus : OnlineStatus.values()) {
            if (!onlineStatus.key.equalsIgnoreCase(key)) continue;
            return onlineStatus;
        }
        return UNKNOWN;
    }
}

