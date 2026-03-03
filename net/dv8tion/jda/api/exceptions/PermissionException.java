/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import net.dv8tion.jda.api.Permission;

public class PermissionException
extends RuntimeException {
    private final Permission permission;

    public PermissionException(String reason) {
        this(Permission.UNKNOWN, reason);
    }

    protected PermissionException(Permission permission) {
        this(permission, "Cannot perform action due to a lack of Permission. Missing permission: " + permission.toString());
    }

    protected PermissionException(Permission permission, String reason) {
        super(reason);
        this.permission = permission;
    }

    public Permission getPermission() {
        return this.permission;
    }
}

