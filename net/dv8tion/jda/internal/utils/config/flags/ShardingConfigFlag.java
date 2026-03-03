/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config.flags;

import java.util.EnumSet;

public enum ShardingConfigFlag {
    SHUTDOWN_NOW;


    public static EnumSet<ShardingConfigFlag> getDefault() {
        return EnumSet.noneOf(ShardingConfigFlag.class);
    }
}

