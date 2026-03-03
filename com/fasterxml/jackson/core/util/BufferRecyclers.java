/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ThreadLocalBufferManager;
import java.lang.ref.SoftReference;

public class BufferRecyclers {
    public static final String SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS = "com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers";
    private static final ThreadLocalBufferManager _bufferRecyclerTracker = "true".equals(System.getProperty("com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers")) ? ThreadLocalBufferManager.instance() : null;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal();

    public static BufferRecycler getBufferRecycler() {
        BufferRecycler br2;
        SoftReference<BufferRecycler> ref = _recyclerRef.get();
        BufferRecycler bufferRecycler = br2 = ref == null ? null : ref.get();
        if (br2 == null) {
            br2 = new BufferRecycler();
            ref = _bufferRecyclerTracker != null ? _bufferRecyclerTracker.wrapAndTrack(br2) : new SoftReference<BufferRecycler>(br2);
            _recyclerRef.set(ref);
        }
        return br2;
    }

    public static int releaseBuffers() {
        if (_bufferRecyclerTracker != null) {
            return _bufferRecyclerTracker.releaseBuffers();
        }
        return -1;
    }
}

