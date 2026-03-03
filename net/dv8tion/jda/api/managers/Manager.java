/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.internal.managers.ManagerBase;

public interface Manager<M extends Manager<M>>
extends AuditableRestAction<Void> {
    public static void setPermissionChecksEnabled(boolean enable) {
        ManagerBase.setPermissionChecksEnabled(enable);
    }

    public static boolean isPermissionChecksEnabled() {
        return ManagerBase.isPermissionChecksEnabled();
    }

    @Nonnull
    public M setCheck(BooleanSupplier var1);

    @Nonnull
    public M timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public M deadline(long var1);

    @Nonnull
    @CheckReturnValue
    public M reset(long var1);

    @Nonnull
    @CheckReturnValue
    public M reset(long ... var1);

    @Nonnull
    @CheckReturnValue
    public M reset();
}

