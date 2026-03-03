/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.io.IOException;

@FunctionalInterface
public interface IOFunction<T, R> {
    public R apply(T var1) throws IOException;
}

