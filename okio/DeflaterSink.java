/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Segment;
import okio.SegmentPool;
import okio.Sink;
import okio.Timeout;
import okio.Util;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class DeflaterSink
implements Sink {
    private final BufferedSink sink;
    private final Deflater deflater;
    private boolean closed;

    public DeflaterSink(Sink sink, Deflater deflater) {
        this(Okio.buffer(sink), deflater);
    }

    DeflaterSink(BufferedSink sink, Deflater deflater) {
        if (sink == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.sink = sink;
        this.deflater = deflater;
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {
        Util.checkOffsetAndCount(source.size, 0L, byteCount);
        while (byteCount > 0L) {
            Segment head = source.head;
            int toDeflate = (int)Math.min(byteCount, (long)(head.limit - head.pos));
            this.deflater.setInput(head.data, head.pos, toDeflate);
            this.deflate(false);
            source.size -= (long)toDeflate;
            head.pos += toDeflate;
            if (head.pos == head.limit) {
                source.head = head.pop();
                SegmentPool.recycle(head);
            }
            byteCount -= (long)toDeflate;
        }
    }

    @IgnoreJRERequirement
    private void deflate(boolean syncFlush) throws IOException {
        Segment s2;
        Buffer buffer = this.sink.buffer();
        while (true) {
            int deflated;
            s2 = buffer.writableSegment(1);
            int n2 = deflated = syncFlush ? this.deflater.deflate(s2.data, s2.limit, 8192 - s2.limit, 2) : this.deflater.deflate(s2.data, s2.limit, 8192 - s2.limit);
            if (deflated > 0) {
                s2.limit += deflated;
                buffer.size += (long)deflated;
                this.sink.emitCompleteSegments();
                continue;
            }
            if (this.deflater.needsInput()) break;
        }
        if (s2.pos == s2.limit) {
            buffer.head = s2.pop();
            SegmentPool.recycle(s2);
        }
    }

    @Override
    public void flush() throws IOException {
        this.deflate(true);
        this.sink.flush();
    }

    void finishDeflate() throws IOException {
        this.deflater.finish();
        this.deflate(false);
    }

    @Override
    public void close() throws IOException {
        Throwable thrown;
        block9: {
            block8: {
                if (this.closed) {
                    return;
                }
                thrown = null;
                try {
                    this.finishDeflate();
                }
                catch (Throwable e2) {
                    thrown = e2;
                }
                try {
                    this.deflater.end();
                }
                catch (Throwable e3) {
                    if (thrown != null) break block8;
                    thrown = e3;
                }
            }
            try {
                this.sink.close();
            }
            catch (Throwable e4) {
                if (thrown != null) break block9;
                thrown = e4;
            }
        }
        this.closed = true;
        if (thrown != null) {
            Util.sneakyRethrow(thrown);
        }
    }

    @Override
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.sink + ")";
    }
}

