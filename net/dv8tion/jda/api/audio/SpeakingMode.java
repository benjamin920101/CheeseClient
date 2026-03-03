/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio;

import java.util.Collection;
import java.util.EnumSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum SpeakingMode {
    VOICE(1),
    SOUNDSHARE(2),
    PRIORITY(4);

    private final int raw;

    private SpeakingMode(int raw) {
        this.raw = raw;
    }

    public int getRaw() {
        return this.raw;
    }

    @Nonnull
    public static EnumSet<SpeakingMode> getModes(int mask) {
        SpeakingMode[] values;
        EnumSet<SpeakingMode> modes = EnumSet.noneOf(SpeakingMode.class);
        if (mask == 0) {
            return modes;
        }
        for (SpeakingMode mode : values = SpeakingMode.values()) {
            if ((mode.raw & mask) != mode.raw) continue;
            modes.add(mode);
        }
        return modes;
    }

    public static int getRaw(SpeakingMode ... modes) {
        if (modes == null || modes.length == 0) {
            return 0;
        }
        int mask = 0;
        for (SpeakingMode m2 : modes) {
            mask |= m2.raw;
        }
        return mask;
    }

    public static int getRaw(@Nullable Collection<SpeakingMode> modes) {
        if (modes == null) {
            return 0;
        }
        int raw = 0;
        for (SpeakingMode mode : modes) {
            raw |= mode.getRaw();
        }
        return raw;
    }
}

