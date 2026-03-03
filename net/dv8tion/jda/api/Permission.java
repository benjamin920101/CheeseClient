/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.internal.utils.Checks;

public enum Permission {
    CREATE_INSTANT_INVITE(0, true, true, "Create Instant Invite"),
    KICK_MEMBERS(1, true, false, "Kick Members"),
    BAN_MEMBERS(2, true, false, "Ban Members"),
    ADMINISTRATOR(3, true, false, "Administrator"),
    MANAGE_CHANNEL(4, true, true, "Manage Channels"),
    MANAGE_SERVER(5, true, false, "Manage Server"),
    MESSAGE_ADD_REACTION(6, true, true, "Add Reactions"),
    VIEW_AUDIT_LOGS(7, true, false, "View Audit Logs"),
    PRIORITY_SPEAKER(8, true, true, "Priority Speaker"),
    VIEW_GUILD_INSIGHTS(19, true, false, "View Server Insights"),
    VIEW_CHANNEL(10, true, true, "Read Text Channels & See Voice Channels"),
    MESSAGE_READ(10, true, true, "Read Messages"),
    MESSAGE_WRITE(11, true, true, "Send Messages"),
    MESSAGE_TTS(12, true, true, "Send TTS Messages"),
    MESSAGE_MANAGE(13, true, true, "Manage Messages"),
    MESSAGE_EMBED_LINKS(14, true, true, "Embed Links"),
    MESSAGE_ATTACH_FILES(15, true, true, "Attach Files"),
    MESSAGE_HISTORY(16, true, true, "Read History"),
    MESSAGE_MENTION_EVERYONE(17, true, true, "Mention Everyone"),
    MESSAGE_EXT_EMOJI(18, true, true, "Use External Emojis"),
    USE_SLASH_COMMANDS(31, true, true, "Use Slash Commands"),
    VOICE_STREAM(9, true, true, "Video"),
    VOICE_CONNECT(20, true, true, "Connect"),
    VOICE_SPEAK(21, true, true, "Speak"),
    VOICE_MUTE_OTHERS(22, true, true, "Mute Members"),
    VOICE_DEAF_OTHERS(23, true, true, "Deafen Members"),
    VOICE_MOVE_OTHERS(24, true, true, "Move Members"),
    VOICE_USE_VAD(25, true, true, "Use Voice Activity"),
    NICKNAME_CHANGE(26, true, false, "Change Nickname"),
    NICKNAME_MANAGE(27, true, false, "Manage Nicknames"),
    MANAGE_ROLES(28, true, false, "Manage Roles"),
    MANAGE_PERMISSIONS(28, false, true, "Manage Permissions"),
    MANAGE_WEBHOOKS(29, true, true, "Manage Webhooks"),
    MANAGE_EMOTES(30, true, false, "Manage Emojis"),
    UNKNOWN(-1, false, false, "Unknown");

    public static final Permission[] EMPTY_PERMISSIONS;
    public static final long ALL_PERMISSIONS;
    public static final long ALL_CHANNEL_PERMISSIONS;
    public static final long ALL_GUILD_PERMISSIONS;
    public static final long ALL_TEXT_PERMISSIONS;
    public static final long ALL_VOICE_PERMISSIONS;
    private final int offset;
    private final long raw;
    private final boolean isGuild;
    private final boolean isChannel;
    private final String name;

    private Permission(int offset, @Nonnull boolean isGuild, boolean isChannel, String name) {
        this.offset = offset;
        this.raw = 1L << offset;
        this.isGuild = isGuild;
        this.isChannel = isChannel;
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    public int getOffset() {
        return this.offset;
    }

    public long getRawValue() {
        return this.raw;
    }

    public boolean isGuild() {
        return this.isGuild;
    }

    public boolean isChannel() {
        return this.isChannel;
    }

    public boolean isText() {
        return (this.raw & ALL_TEXT_PERMISSIONS) == this.raw;
    }

    public boolean isVoice() {
        return (this.raw & ALL_VOICE_PERMISSIONS) == this.raw;
    }

    @Nonnull
    public static Permission getFromOffset(int offset) {
        for (Permission perm : Permission.values()) {
            if (perm.offset != offset) continue;
            return perm;
        }
        return UNKNOWN;
    }

    @Nonnull
    public static EnumSet<Permission> getPermissions(long permissions) {
        if (permissions == 0L) {
            return EnumSet.noneOf(Permission.class);
        }
        EnumSet<Permission> perms = EnumSet.noneOf(Permission.class);
        for (Permission perm : Permission.values()) {
            if (perm == UNKNOWN || (permissions & perm.raw) != perm.raw) continue;
            perms.add(perm);
        }
        return perms;
    }

    public static long getRaw(Permission ... permissions) {
        long raw = 0L;
        for (Permission perm : permissions) {
            if (perm == null || perm == UNKNOWN) continue;
            raw |= perm.raw;
        }
        return raw;
    }

    public static long getRaw(@Nonnull Collection<Permission> permissions) {
        Checks.notNull(permissions, "Permission Collection");
        return Permission.getRaw(permissions.toArray(EMPTY_PERMISSIONS));
    }

    static {
        EMPTY_PERMISSIONS = new Permission[0];
        ALL_PERMISSIONS = Permission.getRaw(Permission.values());
        ALL_CHANNEL_PERMISSIONS = Permission.getRaw(Arrays.stream(Permission.values()).filter(Permission::isChannel).collect(Collectors.toSet()));
        ALL_GUILD_PERMISSIONS = Permission.getRaw(Arrays.stream(Permission.values()).filter(Permission::isGuild).collect(Collectors.toSet()));
        ALL_TEXT_PERMISSIONS = Permission.getRaw(MESSAGE_ADD_REACTION, MESSAGE_WRITE, MESSAGE_TTS, MESSAGE_MANAGE, MESSAGE_EMBED_LINKS, MESSAGE_ATTACH_FILES, MESSAGE_HISTORY, MESSAGE_MENTION_EVERYONE, USE_SLASH_COMMANDS);
        ALL_VOICE_PERMISSIONS = Permission.getRaw(VOICE_STREAM, VOICE_CONNECT, VOICE_SPEAK, VOICE_MUTE_OTHERS, VOICE_DEAF_OTHERS, VOICE_MOVE_OTHERS, VOICE_USE_VAD, PRIORITY_SPEAKER);
    }
}

