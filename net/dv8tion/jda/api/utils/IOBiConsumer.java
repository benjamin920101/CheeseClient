/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.io.IOException;

@FunctionalInterface
public interface IOBiConsumer<T, R> {
    public void accept(T var1, R var2) throws IOException;
}

