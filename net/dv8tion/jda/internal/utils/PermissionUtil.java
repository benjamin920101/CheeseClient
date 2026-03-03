/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.utils.Checks;
import org.apache.commons.collections4.CollectionUtils;

public class PermissionUtil {
    public static boolean canInteract(Member issuer, Member target) {
        Checks.notNull(issuer, "Issuer Member");
        Checks.notNull(target, "Target Member");
        Guild guild = issuer.getGuild();
        if (!guild.equals(target.getGuild())) {
            throw new IllegalArgumentException("Provided members must both be Member objects of the same Guild!");
        }
        if (issuer.isOwner()) {
            return true;
        }
        if (target.isOwner()) {
            return false;
        }
        List<Role> issuerRoles = issuer.getRoles();
        List<Role> targetRoles = target.getRoles();
        return !issuerRoles.isEmpty() && (targetRoles.isEmpty() || PermissionUtil.canInteract(issuerRoles.get(0), targetRoles.get(0)));
    }

    public static boolean canInteract(Member issuer, Role target) {
        Checks.notNull(issuer, "Issuer Member");
        Checks.notNull(target, "Target Role");
        Guild guild = issuer.getGuild();
        if (!guild.equals(target.getGuild())) {
            throw new IllegalArgumentException("Provided Member issuer and Role target must be from the same Guild!");
        }
        if (issuer.isOwner()) {
            return true;
        }
        List<Role> issuerRoles = issuer.getRoles();
        return !issuerRoles.isEmpty() && PermissionUtil.canInteract(issuerRoles.get(0), target);
    }

    public static boolean canInteract(Role issuer, Role target) {
        Checks.notNull(issuer, "Issuer Role");
        Checks.notNull(target, "Target Role");
        if (!issuer.getGuild().equals(target.getGuild())) {
            throw new IllegalArgumentException("The 2 Roles are not from same Guild!");
        }
        return target.getPosition() < issuer.getPosition();
    }

    public static boolean canInteract(Member issuer, Emote emote) {
        Checks.notNull(issuer, "Issuer Member");
        Checks.notNull(emote, "Target Emote");
        if (!issuer.getGuild().equals(emote.getGuild())) {
            throw new IllegalArgumentException("The issuer and target are not in the same Guild");
        }
        return emote.canProvideRoles() && (emote.getRoles().isEmpty() || CollectionUtils.containsAny(issuer.getRoles(), emote.getRoles()));
    }

    public static boolean canInteract(User issuer, Emote emote, MessageChannel channel, boolean botOverride) {
        Checks.notNull(issuer, "Issuer Member");
        Checks.notNull(emote, "Target Emote");
        Checks.notNull(channel, "Target Channel");
        if (emote.getGuild() == null || !emote.getGuild().isMember(issuer)) {
            return false;
        }
        Member member = emote.getGuild().getMemberById(issuer.getIdLong());
        if (!PermissionUtil.canInteract(member, emote)) {
            return false;
        }
        boolean external = emote.isManaged() || issuer.isBot() && botOverride;
        switch (channel.getType()) {
            case TEXT: {
                TextChannel text = (TextChannel)channel;
                member = text.getGuild().getMemberById(issuer.getIdLong());
                return emote.getGuild().equals(text.getGuild()) || external && member != null && member.hasPermission((GuildChannel)text, Permission.MESSAGE_EXT_EMOJI);
            }
        }
        return external;
    }

    public static boolean canInteract(User issuer, Emote emote, MessageChannel channel) {
        return PermissionUtil.canInteract(issuer, emote, channel, true);
    }

    public static boolean checkPermission(Member member, Permission ... permissions) {
        Checks.notNull(member, "Member");
        Checks.notNull(permissions, "Permissions");
        long effectivePerms = PermissionUtil.getEffectivePermission(member);
        return PermissionUtil.isApplied(effectivePerms, Permission.ADMINISTRATOR.getRawValue()) || PermissionUtil.isApplied(effectivePerms, Permission.getRaw(permissions));
    }

    public static boolean checkPermission(GuildChannel channel, Member member, Permission ... permissions) {
        Checks.notNull(channel, "Channel");
        Checks.notNull(member, "Member");
        Checks.notNull(permissions, "Permissions");
        GuildImpl guild = (GuildImpl)channel.getGuild();
        PermissionUtil.checkGuild(guild, member.getGuild(), "Member");
        long effectivePerms = PermissionUtil.getEffectivePermission(channel, member);
        return PermissionUtil.isApplied(effectivePerms, Permission.getRaw(permissions));
    }

    public static long getEffectivePermission(Member member) {
        Checks.notNull(member, "Member");
        if (member.isOwner()) {
            return Permission.ALL_PERMISSIONS;
        }
        long permission = member.getGuild().getPublicRole().getPermissionsRaw();
        for (Role role : member.getRoles()) {
            if (!PermissionUtil.isApplied(permission |= role.getPermissionsRaw(), Permission.ADMINISTRATOR.getRawValue())) continue;
            return Permission.ALL_PERMISSIONS;
        }
        return permission;
    }

    public static long getEffectivePermission(GuildChannel channel, Member member) {
        long admin;
        Checks.notNull(channel, "Channel");
        Checks.notNull(member, "Member");
        Checks.check(channel.getGuild().equals(member.getGuild()), "Provided channel and provided member are not of the same guild!");
        if (member.isOwner()) {
            return Permission.ALL_PERMISSIONS;
        }
        long permission = PermissionUtil.getEffectivePermission(member);
        if (PermissionUtil.isApplied(permission, admin = Permission.ADMINISTRATOR.getRawValue())) {
            return Permission.ALL_PERMISSIONS;
        }
        if (channel.getParent() != null && PermissionUtil.checkPermission(channel.getParent(), member, Permission.MANAGE_CHANNEL)) {
            permission |= Permission.MANAGE_CHANNEL.getRawValue();
        }
        AtomicLong allow = new AtomicLong(0L);
        AtomicLong deny = new AtomicLong(0L);
        PermissionUtil.getExplicitOverrides(channel, member, allow, deny);
        permission = PermissionUtil.apply(permission, allow.get(), deny.get());
        long viewChannel = Permission.VIEW_CHANNEL.getRawValue();
        long connectChannel = Permission.VOICE_CONNECT.getRawValue();
        boolean hasConnect = channel.getType() != ChannelType.VOICE || PermissionUtil.isApplied(permission, connectChannel);
        boolean hasView = PermissionUtil.isApplied(permission, viewChannel);
        return hasView && hasConnect ? permission : 0L;
    }

    public static long getEffectivePermission(GuildChannel channel, Role role) {
        Checks.notNull(channel, "Channel");
        Checks.notNull(role, "Role");
        Guild guild = channel.getGuild();
        if (!guild.equals(role.getGuild())) {
            throw new IllegalArgumentException("Provided channel and role are not of the same guild!");
        }
        long permissions = PermissionUtil.getExplicitPermission(channel, role);
        if (PermissionUtil.isApplied(permissions, Permission.ADMINISTRATOR.getRawValue())) {
            return Permission.ALL_CHANNEL_PERMISSIONS;
        }
        if (!PermissionUtil.isApplied(permissions, Permission.VIEW_CHANNEL.getRawValue())) {
            return 0L;
        }
        return permissions;
    }

    public static long getExplicitPermission(Member member) {
        Checks.notNull(member, "Member");
        Guild guild = member.getGuild();
        long permission = guild.getPublicRole().getPermissionsRaw();
        for (Role role : member.getRoles()) {
            permission |= role.getPermissionsRaw();
        }
        return permission;
    }

    public static long getExplicitPermission(GuildChannel channel, Member member) {
        return PermissionUtil.getExplicitPermission(channel, member, true);
    }

    public static long getExplicitPermission(GuildChannel channel, Member member, boolean includeRoles) {
        Checks.notNull(channel, "Channel");
        Checks.notNull(member, "Member");
        Guild guild = member.getGuild();
        PermissionUtil.checkGuild(channel.getGuild(), guild, "Member");
        long permission = includeRoles ? PermissionUtil.getExplicitPermission(member) : 0L;
        AtomicLong allow = new AtomicLong(0L);
        AtomicLong deny = new AtomicLong(0L);
        PermissionUtil.getExplicitOverrides(channel, member, allow, deny);
        return PermissionUtil.apply(permission, allow.get(), deny.get());
    }

    public static long getExplicitPermission(GuildChannel channel, Role role) {
        return PermissionUtil.getExplicitPermission(channel, role, true);
    }

    public static long getExplicitPermission(GuildChannel channel, Role role, boolean includeRoles) {
        Checks.notNull(channel, "Channel");
        Checks.notNull(role, "Role");
        Guild guild = role.getGuild();
        PermissionUtil.checkGuild(channel.getGuild(), guild, "Role");
        long permission = includeRoles ? role.getPermissionsRaw() | guild.getPublicRole().getPermissionsRaw() : 0L;
        PermissionOverride override = channel.getPermissionOverride(guild.getPublicRole());
        if (override != null) {
            permission = PermissionUtil.apply(permission, override.getAllowedRaw(), override.getDeniedRaw());
        }
        if (role.isPublicRole()) {
            return permission;
        }
        override = channel.getPermissionOverride(role);
        return override == null ? permission : PermissionUtil.apply(permission, override.getAllowedRaw(), override.getDeniedRaw());
    }

    private static void getExplicitOverrides(GuildChannel channel, Member member, AtomicLong allow, AtomicLong deny) {
        PermissionOverride override = channel.getPermissionOverride(member.getGuild().getPublicRole());
        long allowRaw = 0L;
        long denyRaw = 0L;
        if (override != null) {
            denyRaw = override.getDeniedRaw();
            allowRaw = override.getAllowedRaw();
        }
        long allowRole = 0L;
        long denyRole = 0L;
        for (Role role : member.getRoles()) {
            override = channel.getPermissionOverride(role);
            if (override == null) continue;
            denyRole |= override.getDeniedRaw();
            allowRole |= override.getAllowedRaw();
        }
        allowRaw = allowRaw & (denyRole ^ 0xFFFFFFFFFFFFFFFFL) | allowRole;
        denyRaw = denyRaw & (allowRole ^ 0xFFFFFFFFFFFFFFFFL) | denyRole;
        override = channel.getPermissionOverride(member);
        if (override != null) {
            long oDeny = override.getDeniedRaw();
            long oAllow = override.getAllowedRaw();
            allowRaw = allowRaw & (oDeny ^ 0xFFFFFFFFFFFFFFFFL) | oAllow;
            denyRaw = denyRaw & (oAllow ^ 0xFFFFFFFFFFFFFFFFL) | oDeny;
        }
        allow.set(allowRaw);
        deny.set(denyRaw);
    }

    private static boolean isApplied(long permissions, long perms) {
        return (permissions & perms) == perms;
    }

    private static long apply(long permission, long allow, long deny) {
        permission &= deny ^ 0xFFFFFFFFFFFFFFFFL;
        return permission |= allow;
    }

    private static void checkGuild(Guild o1, Guild o2, String name) {
        Checks.check(o1.equals(o2), "Specified %s is not in the same guild! (%s / %s)", name, o1, o2);
    }
}

