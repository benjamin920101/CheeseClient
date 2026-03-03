/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import okio.Buffer;
import okio.BufferedSink;
import okio.DeflaterSink;
import okio.Okio;
import okio.Segment;
import okio.Sink;
import okio.Timeout;
import okio.Util;

public final class GzipSink
implements Sink {
    private final BufferedSink sink;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private boolean closed;
    private final CRC32 crc = new CRC32();

    public GzipSink(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.deflater = new Deflater(-1, true);
        this.sink = Okio.buffer(sink);
        this.deflaterSink = new DeflaterSink(this.sink, this.deflater);
        this.writeHeader();
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {
        if (byteCount < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        if (byteCount == 0L) {
            return;
        }
        this.updateCrc(source, byteCount);
        this.deflaterSink.write(source, byteCount);
    }

    @Override
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    @Override
    public Timeout timeout() {
        return this.sink.timeout();
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
                    this.deflaterSink.finishDeflate();
                    this.writeFooter();
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

    public final Deflater deflater() {
        return this.deflater;
    }

    private void writeHeader() {
        Buffer buffer = this.sink.buffer();
        buffer.writeShort(8075);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }

    private void writeFooter() throws IOException {
        this.sink.writeIntLe((int)this.crc.getValue());
        this.sink.writeIntLe((int)this.deflater.getBytesRead());
    }

    private void updateCrc(Buffer buffer, long byteCount) {
        Segment head = buffer.head;
        while (byteCount > 0L) {
            int segmentLength = (int)Math.min(byteCount, (long)(head.limit - head.pos));
            this.crc.update(head.data, head.pos, segmentLength);
            byteCount -= (long)segmentLength;
            head = head.next;
        }
    }
}

