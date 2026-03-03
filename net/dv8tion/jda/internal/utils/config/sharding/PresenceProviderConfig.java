/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.sharding;

import java.util.function.IntFunction;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class PresenceProviderConfig {
    private IntFunction<? extends Activity> activityProvider;
    private IntFunction<OnlineStatus> statusProvider;
    private IntFunction<Boolean> idleProvider;

    @Nullable
    public IntFunction<? extends Activity> getActivityProvider() {
        return this.activityProvider;
    }

    public void setActivityProvider(@Nullable IntFunction<? extends Activity> activityProvider) {
        this.activityProvider = activityProvider;
    }

    @Nullable
    public IntFunction<OnlineStatus> getStatusProvider() {
        return this.statusProvider;
    }

    public void setStatusProvider(@Nullable IntFunction<OnlineStatus> statusProvider) {
        this.statusProvider = statusProvider;
    }

    @Nullable
    public IntFunction<Boolean> getIdleProvider() {
        return this.idleProvider;
    }

    public void setIdleProvider(@Nullable IntFunction<Boolean> idleProvider) {
        this.idleProvider = idleProvider;
    }

    @Nonnull
    public static PresenceProviderConfig getDefault() {
        return new PresenceProviderConfig();
    }
}

