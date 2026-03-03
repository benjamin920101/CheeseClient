/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

public enum Compression {
    NONE(""),
    ZLIB("zlib-stream");

    private final String key;

    private Compression(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}

