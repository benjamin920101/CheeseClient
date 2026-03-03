/*
 * Decompiled with CFR 0.152.
 */
package okio;

import javax.annotation.Nullable;
import okio.Segment;

final class SegmentPool {
    static final long MAX_SIZE = 65536L;
    @Nullable
    static Segment next;
    static long byteCount;

    private SegmentPool() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static Segment take() {
        Class<SegmentPool> clazz = SegmentPool.class;
        synchronized (SegmentPool.class) {
            if (next != null) {
                Segment result = next;
                next = result.next;
                result.next = null;
                byteCount -= 8192L;
                // ** MonitorExit[var0] (shouldn't be in output)
                return result;
            }
            // ** MonitorExit[var0] (shouldn't be in output)
            return new Segment();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        }
        if (segment.shared) {
            return;
        }
        Class<SegmentPool> clazz = SegmentPool.class;
        synchronized (SegmentPool.class) {
            if (byteCount + 8192L > 65536L) {
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return;
            }
            byteCount += 8192L;
            segment.next = next;
            segment.limit = 0;
            segment.pos = 0;
            next = segment;
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return;
        }
    }
}

