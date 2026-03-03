/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audit;

import net.dv8tion.jda.api.audit.TargetType;

public enum ActionType {
    GUILD_UPDATE(1, TargetType.GUILD),
    CHANNEL_CREATE(10, TargetType.CHANNEL),
    CHANNEL_UPDATE(11, TargetType.CHANNEL),
    CHANNEL_DELETE(12, TargetType.CHANNEL),
    CHANNEL_OVERRIDE_CREATE(13, TargetType.CHANNEL),
    CHANNEL_OVERRIDE_UPDATE(14, TargetType.CHANNEL),
    CHANNEL_OVERRIDE_DELETE(15, TargetType.CHANNEL),
    KICK(20, TargetType.MEMBER),
    PRUNE(21, TargetType.MEMBER),
    BAN(22, TargetType.MEMBER),
    UNBAN(23, TargetType.MEMBER),
    MEMBER_UPDATE(24, TargetType.MEMBER),
    MEMBER_ROLE_UPDATE(25, TargetType.MEMBER),
    MEMBER_VOICE_MOVE(26, TargetType.MEMBER),
    MEMBER_VOICE_KICK(27, TargetType.MEMBER),
    BOT_ADD(28, TargetType.MEMBER),
    ROLE_CREATE(30, TargetType.ROLE),
    ROLE_UPDATE(31, TargetType.ROLE),
    ROLE_DELETE(32, TargetType.ROLE),
    INVITE_CREATE(40, TargetType.INVITE),
    INVITE_UPDATE(41, TargetType.INVITE),
    INVITE_DELETE(42, TargetType.INVITE),
    WEBHOOK_CREATE(50, TargetType.WEBHOOK),
    WEBHOOK_UPDATE(51, TargetType.WEBHOOK),
    WEBHOOK_REMOVE(52, TargetType.WEBHOOK),
    EMOTE_CREATE(60, TargetType.EMOTE),
    EMOTE_UPDATE(61, TargetType.EMOTE),
    EMOTE_DELETE(62, TargetType.EMOTE),
    MESSAGE_CREATE(70, TargetType.UNKNOWN),
    MESSAGE_UPDATE(71, TargetType.UNKNOWN),
    MESSAGE_DELETE(72, TargetType.MEMBER),
    MESSAGE_BULK_DELETE(73, TargetType.CHANNEL),
    MESSAGE_PIN(74, TargetType.CHANNEL),
    MESSAGE_UNPIN(75, TargetType.CHANNEL),
    INTEGRATION_CREATE(80, TargetType.INTEGRATION),
    INTEGRATION_UPDATE(81, TargetType.INTEGRATION),
    INTEGRATION_DELETE(82, TargetType.INTEGRATION),
    UNKNOWN(-1, TargetType.UNKNOWN);

    private final int key;
    private final TargetType target;

    private ActionType(int key, TargetType target) {
        this.key = key;
        this.target = target;
    }

    public int getKey() {
        return this.key;
    }

    public TargetType getTargetType() {
        return this.target;
    }

    public static ActionType from(int key) {
        for (ActionType type : ActionType.values()) {
            if (type.key != key) continue;
            return type;
        }
        return UNKNOWN;
    }
}

