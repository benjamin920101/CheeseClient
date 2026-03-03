/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface Procedure<T> {
    public boolean execute(@Nonnull T var1);
}

