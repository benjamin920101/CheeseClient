/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.Segment;
import okio.Source;
import okio.Timeout;

final class PeekSource
implements Source {
    private final BufferedSource upstream;
    private final Buffer buffer;
    private Segment expectedSegment;
    private int expectedPos;
    private boolean closed;
    private long pos;

    PeekSource(BufferedSource upstream) {
        this.upstream = upstream;
        this.buffer = upstream.buffer();
        this.expectedSegment = this.buffer.head;
        this.expectedPos = this.expectedSegment != null ? this.expectedSegment.pos : -1;
    }

    @Override
    public long read(Buffer sink, long byteCount) throws IOException {
        long toCopy;
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.expectedSegment != null && (this.expectedSegment != this.buffer.head || this.expectedPos != this.buffer.head.pos)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        this.upstream.request(this.pos + byteCount);
        if (this.expectedSegment == null && this.buffer.head != null) {
            this.expectedSegment = this.buffer.head;
            this.expectedPos = this.buffer.head.pos;
        }
        if ((toCopy = Math.min(byteCount, this.buffer.size - this.pos)) <= 0L) {
            return -1L;
        }
        this.buffer.copyTo(sink, this.pos, toCopy);
        this.pos += toCopy;
        return toCopy;
    }

    @Override
    public Timeout timeout() {
        return this.upstream.timeout();
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
    }
}

