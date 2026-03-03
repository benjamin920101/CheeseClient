/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.audio;

import net.dv8tion.jda.api.utils.data.DataArray;

public enum AudioEncryption {
    XSALSA20_POLY1305_LITE,
    XSALSA20_POLY1305_SUFFIX,
    XSALSA20_POLY1305;

    private final String key = this.name().toLowerCase();

    public String getKey() {
        return this.key;
    }

    public static AudioEncryption getPreferredMode(DataArray array) {
        AudioEncryption encryption = null;
        for (Object o2 : array) {
            try {
                String name = String.valueOf(o2).toUpperCase();
                AudioEncryption e2 = AudioEncryption.valueOf(name);
                if (encryption != null && e2.ordinal() >= encryption.ordinal()) continue;
                encryption = e2;
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        return encryption;
    }
}

