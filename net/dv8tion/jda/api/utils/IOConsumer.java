/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.io.IOException;

@FunctionalInterface
public interface IOConsumer<T> {
    public void accept(T var1) throws IOException;
}

