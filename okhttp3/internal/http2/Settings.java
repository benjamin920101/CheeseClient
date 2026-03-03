/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.util.Arrays;

public final class Settings {
    static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    static final int HEADER_TABLE_SIZE = 1;
    static final int ENABLE_PUSH = 2;
    static final int MAX_CONCURRENT_STREAMS = 4;
    static final int MAX_FRAME_SIZE = 5;
    static final int MAX_HEADER_LIST_SIZE = 6;
    static final int INITIAL_WINDOW_SIZE = 7;
    static final int COUNT = 10;
    private int set;
    private final int[] values = new int[10];

    void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    Settings set(int id2, int value) {
        if (id2 < 0 || id2 >= this.values.length) {
            return this;
        }
        int bit2 = 1 << id2;
        this.set |= bit2;
        this.values[id2] = value;
        return this;
    }

    boolean isSet(int id2) {
        int bit2 = 1 << id2;
        return (this.set & bit2) != 0;
    }

    int get(int id2) {
        return this.values[id2];
    }

    int size() {
        return Integer.bitCount(this.set);
    }

    int getHeaderTableSize() {
        int bit2 = 2;
        return (bit2 & this.set) != 0 ? this.values[1] : -1;
    }

    boolean getEnablePush(boolean defaultValue) {
        int bit2 = 4;
        return ((bit2 & this.set) != 0 ? this.values[2] : (defaultValue ? 1 : 0)) == 1;
    }

    int getMaxConcurrentStreams(int defaultValue) {
        int bit2 = 16;
        return (bit2 & this.set) != 0 ? this.values[4] : defaultValue;
    }

    int getMaxFrameSize(int defaultValue) {
        int bit2 = 32;
        return (bit2 & this.set) != 0 ? this.values[5] : defaultValue;
    }

    int getMaxHeaderListSize(int defaultValue) {
        int bit2 = 64;
        return (bit2 & this.set) != 0 ? this.values[6] : defaultValue;
    }

    int getInitialWindowSize() {
        int bit2 = 128;
        return (bit2 & this.set) != 0 ? this.values[7] : 65535;
    }

    void merge(Settings other) {
        for (int i2 = 0; i2 < 10; ++i2) {
            if (!other.isSet(i2)) continue;
            this.set(i2, other.get(i2));
        }
    }
}

