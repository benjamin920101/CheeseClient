/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.managers.Manager;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.restaction.AuditableRestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public abstract class ManagerBase<M extends Manager<M>>
extends AuditableRestActionImpl<Void>
implements Manager<M> {
    private static boolean enablePermissionChecks = true;
    protected long set = 0L;

    public static void setPermissionChecksEnabled(boolean enable) {
        enablePermissionChecks = enable;
    }

    public static boolean isPermissionChecksEnabled() {
        return enablePermissionChecks;
    }

    protected ManagerBase(JDA api2, Route.CompiledRoute route) {
        super(api2, route);
    }

    @Override
    @Nonnull
    public M setCheck(BooleanSupplier checks) {
        return (M)((Manager)super.setCheck(checks));
    }

    @Override
    @Nonnull
    public M timeout(long timeout, @Nonnull TimeUnit unit) {
        return (M)((Manager)super.timeout(timeout, unit));
    }

    @Override
    @Nonnull
    public M deadline(long timestamp) {
        return (M)((Manager)super.deadline(timestamp));
    }

    @Override
    @Nonnull
    public M reset(long fields) {
        this.set &= fields ^ 0xFFFFFFFFFFFFFFFFL;
        return (M)this;
    }

    @Override
    @Nonnull
    public M reset(long ... fields) {
        Checks.notNull(fields, "Fields");
        if (fields.length == 0) {
            return (M)this;
        }
        if (fields.length == 1) {
            return this.reset(fields[0]);
        }
        long sum = fields[0];
        for (int i2 = 1; i2 < fields.length; ++i2) {
            sum |= fields[i2];
        }
        return this.reset(sum);
    }

    @Override
    @Nonnull
    public M reset() {
        this.set = 0L;
        return (M)this;
    }

    @Override
    public void queue(Consumer<? super Void> success, Consumer<? super Throwable> failure) {
        if (this.shouldUpdate()) {
            super.queue(success, failure);
        } else if (success != null) {
            success.accept(null);
        } else {
            ManagerBase.getDefaultSuccess().accept(null);
        }
    }

    @Override
    public Void complete(boolean shouldQueue) throws RateLimitedException {
        if (this.shouldUpdate()) {
            return (Void)super.complete(shouldQueue);
        }
        return null;
    }

    @Override
    protected BooleanSupplier finalizeChecks() {
        return enablePermissionChecks ? this::checkPermissions : super.finalizeChecks();
    }

    protected boolean shouldUpdate() {
        return this.set != 0L;
    }

    protected boolean shouldUpdate(long bit2) {
        return (this.set & bit2) == bit2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected <E> void withLock(E object, Consumer<? super E> consumer) {
        E e2 = object;
        synchronized (e2) {
            consumer.accept(object);
        }
    }

    protected boolean checkPermissions() {
        return true;
    }
}

