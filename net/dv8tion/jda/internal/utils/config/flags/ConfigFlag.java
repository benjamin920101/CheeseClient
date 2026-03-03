/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.flags;

import java.util.EnumSet;

public enum ConfigFlag {
    RAW_EVENTS,
    USE_RELATIVE_RATELIMIT(true),
    RETRY_TIMEOUT(true),
    BULK_DELETE_SPLIT(true),
    SHUTDOWN_HOOK(true),
    MDC_CONTEXT(true),
    AUTO_RECONNECT(true);

    private final boolean isDefault;

    private ConfigFlag() {
        this(false);
    }

    private ConfigFlag(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public static EnumSet<ConfigFlag> getDefault() {
        EnumSet<ConfigFlag> set = EnumSet.noneOf(ConfigFlag.class);
        for (ConfigFlag flag : ConfigFlag.values()) {
            if (!flag.isDefault) continue;
            set.add(flag);
        }
        return set;
    }
}

