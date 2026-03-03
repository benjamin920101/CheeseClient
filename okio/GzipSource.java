/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import okio.Buffer;
import okio.BufferedSource;
import okio.InflaterSource;
import okio.Okio;
import okio.Segment;
import okio.Source;
import okio.Timeout;

public final class GzipSource
implements Source {
    private static final byte FHCRC = 1;
    private static final byte FEXTRA = 2;
    private static final byte FNAME = 3;
    private static final byte FCOMMENT = 4;
    private static final byte SECTION_HEADER = 0;
    private static final byte SECTION_BODY = 1;
    private static final byte SECTION_TRAILER = 2;
    private static final byte SECTION_DONE = 3;
    private int section = 0;
    private final BufferedSource source;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private final CRC32 crc = new CRC32();

    public GzipSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.inflater = new Inflater(true);
        this.source = Okio.buffer(source);
        this.inflaterSource = new InflaterSource(this.source, this.inflater);
    }

    @Override
    public long read(Buffer sink, long byteCount) throws IOException {
        if (byteCount < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        if (byteCount == 0L) {
            return 0L;
        }
        if (this.section == 0) {
            this.consumeHeader();
            this.section = 1;
        }
        if (this.section == 1) {
            long offset = sink.size;
            long result = this.inflaterSource.read(sink, byteCount);
            if (result != -1L) {
                this.updateCrc(sink, offset, result);
                return result;
            }
            this.section = 2;
        }
        if (this.section == 2) {
            this.consumeTrailer();
            this.section = 3;
            if (!this.source.exhausted()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    private void consumeHeader() throws IOException {
        boolean fhcrc;
        this.source.require(10L);
        byte flags = this.source.buffer().getByte(3L);
        boolean bl2 = fhcrc = (flags >> 1 & 1) == 1;
        if (fhcrc) {
            this.updateCrc(this.source.buffer(), 0L, 10L);
        }
        short id1id2 = this.source.readShort();
        this.checkEqual("ID1ID2", 8075, id1id2);
        this.source.skip(8L);
        if ((flags >> 2 & 1) == 1) {
            this.source.require(2L);
            if (fhcrc) {
                this.updateCrc(this.source.buffer(), 0L, 2L);
            }
            short xlen = this.source.buffer().readShortLe();
            this.source.require(xlen);
            if (fhcrc) {
                this.updateCrc(this.source.buffer(), 0L, xlen);
            }
            this.source.skip(xlen);
        }
        if ((flags >> 3 & 1) == 1) {
            long index = this.source.indexOf((byte)0);
            if (index == -1L) {
                throw new EOFException();
            }
            if (fhcrc) {
                this.updateCrc(this.source.buffer(), 0L, index + 1L);
            }
            this.source.skip(index + 1L);
        }
        if ((flags >> 4 & 1) == 1) {
            long index = this.source.indexOf((byte)0);
            if (index == -1L) {
                throw new EOFException();
            }
            if (fhcrc) {
                this.updateCrc(this.source.buffer(), 0L, index + 1L);
            }
            this.source.skip(index + 1L);
        }
        if (fhcrc) {
            this.checkEqual("FHCRC", this.source.readShortLe(), (short)this.crc.getValue());
            this.crc.reset();
        }
    }

    private void consumeTrailer() throws IOException {
        this.checkEqual("CRC", this.source.readIntLe(), (int)this.crc.getValue());
        this.checkEqual("ISIZE", this.source.readIntLe(), (int)this.inflater.getBytesWritten());
    }

    @Override
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private void updateCrc(Buffer buffer, long offset, long byteCount) {
        Segment s2 = buffer.head;
        while (offset >= (long)(s2.limit - s2.pos)) {
            offset -= (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        while (byteCount > 0L) {
            int pos = (int)((long)s2.pos + offset);
            int toUpdate = (int)Math.min((long)(s2.limit - pos), byteCount);
            this.crc.update(s2.data, pos, toUpdate);
            byteCount -= (long)toUpdate;
            offset = 0L;
            s2 = s2.next;
        }
    }

    private void checkEqual(String name, int expected, int actual) throws IOException {
        if (actual != expected) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", name, actual, expected));
        }
    }
}

