/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audit;

public enum AuditLogOption {
    COUNT("count"),
    MESSAGE("message_id"),
    CHANNEL("channel_id"),
    USER("user_id"),
    ROLE("role_id"),
    ROLE_NAME("role_name"),
    TYPE("type"),
    ID("id"),
    DELETE_MEMBER_DAYS("delete_member_days"),
    MEMBERS_REMOVED("members_removed");

    private final String key;

    private AuditLogOption(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public String toString() {
        return this.name() + '(' + this.key + ')';
    }
}

