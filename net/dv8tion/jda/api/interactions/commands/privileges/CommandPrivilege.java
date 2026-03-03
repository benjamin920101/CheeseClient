/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.interactions.commands.privileges;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.utils.Checks;

public class CommandPrivilege
implements ISnowflake,
SerializableData {
    private final Type type;
    private final boolean enabled;
    private final long id;

    public CommandPrivilege(@Nonnull Type type, boolean enabled, long id2) {
        Checks.notNull((Object)type, "Type");
        this.type = type;
        this.enabled = enabled;
        this.id = id2;
    }

    @Nonnull
    public static CommandPrivilege enable(@Nonnull Role role) {
        Checks.notNull(role, "Role");
        return new CommandPrivilege(Type.ROLE, true, role.getIdLong());
    }

    @Nonnull
    public static CommandPrivilege enable(@Nonnull User user) {
        Checks.notNull(user, "User");
        return new CommandPrivilege(Type.USER, true, user.getIdLong());
    }

    @Nonnull
    public static CommandPrivilege enableUser(@Nonnull String userId) {
        return CommandPrivilege.enableUser(MiscUtil.parseSnowflake(userId));
    }

    @Nonnull
    public static CommandPrivilege enableUser(long userId) {
        return new CommandPrivilege(Type.USER, true, userId);
    }

    @Nonnull
    public static CommandPrivilege enableRole(@Nonnull String roleId) {
        return CommandPrivilege.enableRole(MiscUtil.parseSnowflake(roleId));
    }

    @Nonnull
    public static CommandPrivilege enableRole(long roleId) {
        return new CommandPrivilege(Type.ROLE, true, roleId);
    }

    @Nonnull
    public static CommandPrivilege disable(@Nonnull Role role) {
        Checks.notNull(role, "Role");
        return new CommandPrivilege(Type.ROLE, false, role.getIdLong());
    }

    @Nonnull
    public static CommandPrivilege disable(@Nonnull User user) {
        Checks.notNull(user, "User");
        return new CommandPrivilege(Type.USER, false, user.getIdLong());
    }

    @Nonnull
    public static CommandPrivilege disableUser(@Nonnull String userId) {
        return CommandPrivilege.disableUser(MiscUtil.parseSnowflake(userId));
    }

    @Nonnull
    public static CommandPrivilege disableUser(long userId) {
        return new CommandPrivilege(Type.USER, false, userId);
    }

    @Nonnull
    public static CommandPrivilege disableRole(@Nonnull String roleId) {
        return CommandPrivilege.disableRole(MiscUtil.parseSnowflake(roleId));
    }

    @Nonnull
    public static CommandPrivilege disableRole(long roleId) {
        return new CommandPrivilege(Type.ROLE, false, roleId);
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Nonnull
    public Type getType() {
        return this.type;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isDisabled() {
        return !this.enabled;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CommandPrivilege)) {
            return false;
        }
        return ((CommandPrivilege)obj).id == this.id;
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return DataObject.empty().put("id", this.id).put("type", this.type.key).put("permission", this.enabled);
    }

    public static enum Type {
        UNKNOWN(-1),
        ROLE(1),
        USER(2);

        private final int key;

        private Type(int key) {
            this.key = key;
        }

        @Nonnull
        public static Type fromKey(int key) {
            for (Type type : Type.values()) {
                if (type.key != key) continue;
                return type;
            }
            return UNKNOWN;
        }
    }
}

