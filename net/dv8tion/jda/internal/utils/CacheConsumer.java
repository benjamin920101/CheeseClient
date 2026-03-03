/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import net.dv8tion.jda.api.utils.data.DataObject;

@FunctionalInterface
public interface CacheConsumer {
    public void execute(long var1, DataObject var3);
}

