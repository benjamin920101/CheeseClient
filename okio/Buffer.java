/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.Util;

public final class Buffer
implements BufferedSource,
BufferedSink,
Cloneable,
ByteChannel {
    private static final byte[] DIGITS = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    Segment head;
    long size;

    public final long size() {
        return this.size;
    }

    @Override
    public Buffer buffer() {
        return this;
    }

    @Override
    public Buffer getBuffer() {
        return this;
    }

    @Override
    public OutputStream outputStream() {
        return new OutputStream(){

            @Override
            public void write(int b2) {
                Buffer.this.writeByte((byte)b2);
            }

            @Override
            public void write(byte[] data, int offset, int byteCount) {
                Buffer.this.write(data, offset, byteCount);
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    @Override
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override
    public BufferedSink emit() {
        return this;
    }

    @Override
    public boolean exhausted() {
        return this.size == 0L;
    }

    @Override
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    @Override
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override
    public InputStream inputStream() {
        return new InputStream(){

            @Override
            public int read() {
                if (Buffer.this.size > 0L) {
                    return Buffer.this.readByte() & 0xFF;
                }
                return -1;
            }

            @Override
            public int read(byte[] sink, int offset, int byteCount) {
                return Buffer.this.read(sink, offset, byteCount);
            }

            @Override
            public int available() {
                return (int)Math.min(Buffer.this.size, Integer.MAX_VALUE);
            }

            @Override
            public void close() {
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public final Buffer copyTo(OutputStream out) throws IOException {
        return this.copyTo(out, 0L, this.size);
    }

    public final Buffer copyTo(OutputStream out, long offset, long byteCount) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, offset, byteCount);
        if (byteCount == 0L) {
            return this;
        }
        Segment s2 = this.head;
        while (offset >= (long)(s2.limit - s2.pos)) {
            offset -= (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        while (byteCount > 0L) {
            int pos = (int)((long)s2.pos + offset);
            int toCopy = (int)Math.min((long)(s2.limit - pos), byteCount);
            out.write(s2.data, pos, toCopy);
            byteCount -= (long)toCopy;
            offset = 0L;
            s2 = s2.next;
        }
        return this;
    }

    public final Buffer copyTo(Buffer out, long offset, long byteCount) {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, offset, byteCount);
        if (byteCount == 0L) {
            return this;
        }
        out.size += byteCount;
        Segment s2 = this.head;
        while (offset >= (long)(s2.limit - s2.pos)) {
            offset -= (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        while (byteCount > 0L) {
            Segment copy = s2.sharedCopy();
            copy.pos = (int)((long)copy.pos + offset);
            copy.limit = Math.min(copy.pos + (int)byteCount, copy.limit);
            if (out.head == null) {
                copy.next = copy.prev = copy;
                out.head = copy.prev;
            } else {
                out.head.prev.push(copy);
            }
            byteCount -= (long)(copy.limit - copy.pos);
            offset = 0L;
            s2 = s2.next;
        }
        return this;
    }

    public final Buffer writeTo(OutputStream out) throws IOException {
        return this.writeTo(out, this.size);
    }

    public final Buffer writeTo(OutputStream out, long byteCount) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0L, byteCount);
        Segment s2 = this.head;
        while (byteCount > 0L) {
            int toCopy = (int)Math.min(byteCount, (long)(s2.limit - s2.pos));
            out.write(s2.data, s2.pos, toCopy);
            s2.pos += toCopy;
            this.size -= (long)toCopy;
            byteCount -= (long)toCopy;
            if (s2.pos != s2.limit) continue;
            Segment toRecycle = s2;
            this.head = s2 = toRecycle.pop();
            SegmentPool.recycle(toRecycle);
        }
        return this;
    }

    public final Buffer readFrom(InputStream in2) throws IOException {
        this.readFrom(in2, Long.MAX_VALUE, true);
        return this;
    }

    public final Buffer readFrom(InputStream in2, long byteCount) throws IOException {
        if (byteCount < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        this.readFrom(in2, byteCount, false);
        return this;
    }

    private void readFrom(InputStream in2, long byteCount, boolean forever) throws IOException {
        if (in2 == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (byteCount > 0L || forever) {
            Segment tail = this.writableSegment(1);
            int maxToCopy = (int)Math.min(byteCount, (long)(8192 - tail.limit));
            int bytesRead = in2.read(tail.data, tail.limit, maxToCopy);
            if (bytesRead == -1) {
                if (forever) {
                    return;
                }
                throw new EOFException();
            }
            tail.limit += bytesRead;
            this.size += (long)bytesRead;
            byteCount -= (long)bytesRead;
        }
    }

    public final long completeSegmentByteCount() {
        long result = this.size;
        if (result == 0L) {
            return 0L;
        }
        Segment tail = this.head.prev;
        if (tail.limit < 8192 && tail.owner) {
            result -= (long)(tail.limit - tail.pos);
        }
        return result;
    }

    @Override
    public byte readByte() {
        if (this.size == 0L) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        byte[] data = segment.data;
        byte b2 = data[pos++];
        --this.size;
        if (pos == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos;
        }
        return b2;
    }

    public final byte getByte(long pos) {
        Util.checkOffsetAndCount(this.size, pos, 1L);
        if (this.size - pos > pos) {
            Segment s2 = this.head;
            while (true) {
                int segmentByteCount;
                if (pos < (long)(segmentByteCount = s2.limit - s2.pos)) {
                    return s2.data[s2.pos + (int)pos];
                }
                pos -= (long)segmentByteCount;
                s2 = s2.next;
            }
        }
        pos -= this.size;
        Segment s3 = this.head.prev;
        while ((pos += (long)(s3.limit - s3.pos)) < 0L) {
            s3 = s3.prev;
        }
        return s3.data[s3.pos + (int)pos];
    }

    @Override
    public short readShort() {
        if (this.size < 2L) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        Segment segment = this.head;
        int limit = segment.limit;
        int pos = segment.pos;
        if (limit - pos < 2) {
            int s2 = (this.readByte() & 0xFF) << 8 | this.readByte() & 0xFF;
            return (short)s2;
        }
        byte[] data = segment.data;
        int s3 = (data[pos++] & 0xFF) << 8 | data[pos++] & 0xFF;
        this.size -= 2L;
        if (pos == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos;
        }
        return (short)s3;
    }

    @Override
    public int readInt() {
        if (this.size < 4L) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        Segment segment = this.head;
        int limit = segment.limit;
        int pos = segment.pos;
        if (limit - pos < 4) {
            return (this.readByte() & 0xFF) << 24 | (this.readByte() & 0xFF) << 16 | (this.readByte() & 0xFF) << 8 | this.readByte() & 0xFF;
        }
        byte[] data = segment.data;
        int i2 = (data[pos++] & 0xFF) << 24 | (data[pos++] & 0xFF) << 16 | (data[pos++] & 0xFF) << 8 | data[pos++] & 0xFF;
        this.size -= 4L;
        if (pos == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos;
        }
        return i2;
    }

    @Override
    public long readLong() {
        if (this.size < 8L) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        Segment segment = this.head;
        int limit = segment.limit;
        int pos = segment.pos;
        if (limit - pos < 8) {
            return ((long)this.readInt() & 0xFFFFFFFFL) << 32 | (long)this.readInt() & 0xFFFFFFFFL;
        }
        byte[] data = segment.data;
        long v2 = ((long)data[pos++] & 0xFFL) << 56 | ((long)data[pos++] & 0xFFL) << 48 | ((long)data[pos++] & 0xFFL) << 40 | ((long)data[pos++] & 0xFFL) << 32 | ((long)data[pos++] & 0xFFL) << 24 | ((long)data[pos++] & 0xFFL) << 16 | ((long)data[pos++] & 0xFFL) << 8 | (long)data[pos++] & 0xFFL;
        this.size -= 8L;
        if (pos == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos;
        }
        return v2;
    }

    @Override
    public short readShortLe() {
        return Util.reverseBytesShort(this.readShort());
    }

    @Override
    public int readIntLe() {
        return Util.reverseBytesInt(this.readInt());
    }

    @Override
    public long readLongLe() {
        return Util.reverseBytesLong(this.readLong());
    }

    @Override
    public long readDecimalLong() {
        if (this.size == 0L) {
            throw new IllegalStateException("size == 0");
        }
        long value = 0L;
        int seen = 0;
        boolean negative = false;
        boolean done = false;
        long overflowZone = -922337203685477580L;
        long overflowDigit = -7L;
        do {
            Segment segment = this.head;
            byte[] data = segment.data;
            int pos = segment.pos;
            int limit = segment.limit;
            while (pos < limit) {
                byte b2 = data[pos];
                if (b2 >= 48 && b2 <= 57) {
                    int digit = 48 - b2;
                    if (value < overflowZone || value == overflowZone && (long)digit < overflowDigit) {
                        Buffer buffer = new Buffer().writeDecimalLong(value).writeByte(b2);
                        if (!negative) {
                            buffer.readByte();
                        }
                        throw new NumberFormatException("Number too large: " + buffer.readUtf8());
                    }
                    value *= 10L;
                    value += (long)digit;
                } else if (b2 == 45 && seen == 0) {
                    negative = true;
                    --overflowDigit;
                } else {
                    if (seen == 0) {
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b2));
                    }
                    done = true;
                    break;
                }
                ++pos;
                ++seen;
            }
            if (pos == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
                continue;
            }
            segment.pos = pos;
        } while (!done && this.head != null);
        this.size -= (long)seen;
        return negative ? value : -value;
    }

    @Override
    public long readHexadecimalUnsignedLong() {
        if (this.size == 0L) {
            throw new IllegalStateException("size == 0");
        }
        long value = 0L;
        int seen = 0;
        boolean done = false;
        do {
            Segment segment = this.head;
            byte[] data = segment.data;
            int pos = segment.pos;
            int limit = segment.limit;
            while (pos < limit) {
                int digit;
                byte b2 = data[pos];
                if (b2 >= 48 && b2 <= 57) {
                    digit = b2 - 48;
                } else if (b2 >= 97 && b2 <= 102) {
                    digit = b2 - 97 + 10;
                } else if (b2 >= 65 && b2 <= 70) {
                    digit = b2 - 65 + 10;
                } else {
                    if (seen == 0) {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b2));
                    }
                    done = true;
                    break;
                }
                if ((value & 0xF000000000000000L) != 0L) {
                    Buffer buffer = new Buffer().writeHexadecimalUnsignedLong(value).writeByte(b2);
                    throw new NumberFormatException("Number too large: " + buffer.readUtf8());
                }
                value <<= 4;
                value |= (long)digit;
                ++pos;
                ++seen;
            }
            if (pos == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
                continue;
            }
            segment.pos = pos;
        } while (!done && this.head != null);
        this.size -= (long)seen;
        return value;
    }

    @Override
    public ByteString readByteString() {
        return new ByteString(this.readByteArray());
    }

    @Override
    public ByteString readByteString(long byteCount) throws EOFException {
        return new ByteString(this.readByteArray(byteCount));
    }

    @Override
    public int select(Options options) {
        int index = this.selectPrefix(options, false);
        if (index == -1) {
            return -1;
        }
        int selectedSize = options.byteStrings[index].size();
        try {
            this.skip(selectedSize);
        }
        catch (EOFException e2) {
            throw new AssertionError();
        }
        return index;
    }

    int selectPrefix(Options options, boolean selectTruncated) {
        Segment head = this.head;
        if (head == null) {
            if (selectTruncated) {
                return -2;
            }
            return options.indexOf(ByteString.EMPTY);
        }
        Segment s2 = head;
        byte[] data = head.data;
        int pos = head.pos;
        int limit = head.limit;
        int[] trie = options.trie;
        int triePos = 0;
        int prefixIndex = -1;
        block0: while (true) {
            int nextStep;
            int possiblePrefixIndex;
            int scanOrSelect = trie[triePos++];
            if ((possiblePrefixIndex = trie[triePos++]) != -1) {
                prefixIndex = possiblePrefixIndex;
            }
            if (s2 == null) break;
            if (scanOrSelect < 0) {
                boolean scanComplete;
                int scanByteCount = -1 * scanOrSelect;
                int trieLimit = triePos + scanByteCount;
                do {
                    int b2;
                    if ((b2 = data[pos++] & 0xFF) != trie[triePos++]) {
                        return prefixIndex;
                    }
                    boolean bl2 = scanComplete = triePos == trieLimit;
                    if (pos != limit) continue;
                    s2 = s2.next;
                    pos = s2.pos;
                    data = s2.data;
                    limit = s2.limit;
                    if (s2 != head) continue;
                    if (!scanComplete) break block0;
                    s2 = null;
                } while (!scanComplete);
                nextStep = trie[triePos];
            } else {
                int selectChoiceCount = scanOrSelect;
                int b3 = data[pos++] & 0xFF;
                int selectLimit = triePos + selectChoiceCount;
                while (true) {
                    if (triePos == selectLimit) {
                        return prefixIndex;
                    }
                    if (b3 == trie[triePos]) break;
                    ++triePos;
                }
                nextStep = trie[triePos + selectChoiceCount];
                if (pos == limit) {
                    s2 = s2.next;
                    pos = s2.pos;
                    data = s2.data;
                    limit = s2.limit;
                    if (s2 == head) {
                        s2 = null;
                    }
                }
            }
            if (nextStep >= 0) {
                return nextStep;
            }
            triePos = -nextStep;
        }
        if (selectTruncated) {
            return -2;
        }
        return prefixIndex;
    }

    @Override
    public void readFully(Buffer sink, long byteCount) throws EOFException {
        if (this.size < byteCount) {
            sink.write(this, this.size);
            throw new EOFException();
        }
        sink.write(this, byteCount);
    }

    @Override
    public long readAll(Sink sink) throws IOException {
        long byteCount = this.size;
        if (byteCount > 0L) {
            sink.write(this, byteCount);
        }
        return byteCount;
    }

    @Override
    public String readUtf8() {
        try {
            return this.readString(this.size, Util.UTF_8);
        }
        catch (EOFException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Override
    public String readUtf8(long byteCount) throws EOFException {
        return this.readString(byteCount, Util.UTF_8);
    }

    @Override
    public String readString(Charset charset) {
        try {
            return this.readString(this.size, charset);
        }
        catch (EOFException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Override
    public String readString(long byteCount, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, byteCount);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (byteCount > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        }
        if (byteCount == 0L) {
            return "";
        }
        Segment s2 = this.head;
        if ((long)s2.pos + byteCount > (long)s2.limit) {
            return new String(this.readByteArray(byteCount), charset);
        }
        String result = new String(s2.data, s2.pos, (int)byteCount, charset);
        s2.pos = (int)((long)s2.pos + byteCount);
        this.size -= byteCount;
        if (s2.pos == s2.limit) {
            this.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return result;
    }

    @Override
    @Nullable
    public String readUtf8Line() throws EOFException {
        long newline = this.indexOf((byte)10);
        if (newline == -1L) {
            return this.size != 0L ? this.readUtf8(this.size) : null;
        }
        return this.readUtf8Line(newline);
    }

    @Override
    public String readUtf8LineStrict() throws EOFException {
        return this.readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override
    public String readUtf8LineStrict(long limit) throws EOFException {
        if (limit < 0L) {
            throw new IllegalArgumentException("limit < 0: " + limit);
        }
        long scanLength = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1L;
        long newline = this.indexOf((byte)10, 0L, scanLength);
        if (newline != -1L) {
            return this.readUtf8Line(newline);
        }
        if (scanLength < this.size() && this.getByte(scanLength - 1L) == 13 && this.getByte(scanLength) == 10) {
            return this.readUtf8Line(scanLength);
        }
        Buffer data = new Buffer();
        this.copyTo(data, 0L, Math.min(32L, this.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.size(), limit) + " content=" + data.readByteString().hex() + '\u2026');
    }

    String readUtf8Line(long newline) throws EOFException {
        if (newline > 0L && this.getByte(newline - 1L) == 13) {
            String result = this.readUtf8(newline - 1L);
            this.skip(2L);
            return result;
        }
        String result = this.readUtf8(newline);
        this.skip(1L);
        return result;
    }

    @Override
    public int readUtf8CodePoint() throws EOFException {
        int min;
        int byteCount;
        int codePoint;
        if (this.size == 0L) {
            throw new EOFException();
        }
        byte b0 = this.getByte(0L);
        if ((b0 & 0x80) == 0) {
            codePoint = b0 & 0x7F;
            byteCount = 1;
            min = 0;
        } else if ((b0 & 0xE0) == 192) {
            codePoint = b0 & 0x1F;
            byteCount = 2;
            min = 128;
        } else if ((b0 & 0xF0) == 224) {
            codePoint = b0 & 0xF;
            byteCount = 3;
            min = 2048;
        } else if ((b0 & 0xF8) == 240) {
            codePoint = b0 & 7;
            byteCount = 4;
            min = 65536;
        } else {
            this.skip(1L);
            return 65533;
        }
        if (this.size < (long)byteCount) {
            throw new EOFException("size < " + byteCount + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b0) + ")");
        }
        for (int i2 = 1; i2 < byteCount; ++i2) {
            byte b2 = this.getByte(i2);
            if ((b2 & 0xC0) == 128) {
                codePoint <<= 6;
                codePoint |= b2 & 0x3F;
                continue;
            }
            this.skip(i2);
            return 65533;
        }
        this.skip(byteCount);
        if (codePoint > 0x10FFFF) {
            return 65533;
        }
        if (codePoint >= 55296 && codePoint <= 57343) {
            return 65533;
        }
        if (codePoint < min) {
            return 65533;
        }
        return codePoint;
    }

    @Override
    public byte[] readByteArray() {
        try {
            return this.readByteArray(this.size);
        }
        catch (EOFException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Override
    public byte[] readByteArray(long byteCount) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, byteCount);
        if (byteCount > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        }
        byte[] result = new byte[(int)byteCount];
        this.readFully(result);
        return result;
    }

    @Override
    public int read(byte[] sink) {
        return this.read(sink, 0, sink.length);
    }

    @Override
    public void readFully(byte[] sink) throws EOFException {
        int read;
        for (int offset = 0; offset < sink.length; offset += read) {
            read = this.read(sink, offset, sink.length - offset);
            if (read != -1) continue;
            throw new EOFException();
        }
    }

    @Override
    public int read(byte[] sink, int offset, int byteCount) {
        Util.checkOffsetAndCount(sink.length, offset, byteCount);
        Segment s2 = this.head;
        if (s2 == null) {
            return -1;
        }
        int toCopy = Math.min(byteCount, s2.limit - s2.pos);
        System.arraycopy(s2.data, s2.pos, sink, offset, toCopy);
        s2.pos += toCopy;
        this.size -= (long)toCopy;
        if (s2.pos == s2.limit) {
            this.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return toCopy;
    }

    @Override
    public int read(ByteBuffer sink) throws IOException {
        Segment s2 = this.head;
        if (s2 == null) {
            return -1;
        }
        int toCopy = Math.min(sink.remaining(), s2.limit - s2.pos);
        sink.put(s2.data, s2.pos, toCopy);
        s2.pos += toCopy;
        this.size -= (long)toCopy;
        if (s2.pos == s2.limit) {
            this.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return toCopy;
    }

    public final void clear() {
        try {
            this.skip(this.size);
        }
        catch (EOFException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Override
    public void skip(long byteCount) throws EOFException {
        while (byteCount > 0L) {
            if (this.head == null) {
                throw new EOFException();
            }
            int toSkip = (int)Math.min(byteCount, (long)(this.head.limit - this.head.pos));
            this.size -= (long)toSkip;
            byteCount -= (long)toSkip;
            this.head.pos += toSkip;
            if (this.head.pos != this.head.limit) continue;
            Segment toRecycle = this.head;
            this.head = toRecycle.pop();
            SegmentPool.recycle(toRecycle);
        }
    }

    @Override
    public Buffer write(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    @Override
    public Buffer writeUtf8(String string) {
        return this.writeUtf8(string, 0, string.length());
    }

    @Override
    public Buffer writeUtf8(String string, int beginIndex, int endIndex) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + beginIndex);
        }
        if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        }
        if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        }
        int i2 = beginIndex;
        while (i2 < endIndex) {
            char low;
            char c2 = string.charAt(i2);
            if (c2 < '\u0080') {
                Segment tail = this.writableSegment(1);
                byte[] data = tail.data;
                int segmentOffset = tail.limit - i2;
                int runLimit = Math.min(endIndex, 8192 - segmentOffset);
                data[segmentOffset + i2++] = (byte)c2;
                while (i2 < runLimit && (c2 = string.charAt(i2)) < '\u0080') {
                    data[segmentOffset + i2++] = (byte)c2;
                }
                int runSize = i2 + segmentOffset - tail.limit;
                tail.limit += runSize;
                this.size += (long)runSize;
                continue;
            }
            if (c2 < '\u0800') {
                this.writeByte(c2 >> 6 | 0xC0);
                this.writeByte(c2 & 0x3F | 0x80);
                ++i2;
                continue;
            }
            if (c2 < '\ud800' || c2 > '\udfff') {
                this.writeByte(c2 >> 12 | 0xE0);
                this.writeByte(c2 >> 6 & 0x3F | 0x80);
                this.writeByte(c2 & 0x3F | 0x80);
                ++i2;
                continue;
            }
            char c3 = low = i2 + 1 < endIndex ? string.charAt(i2 + 1) : (char)'\u0000';
            if (c2 > '\udbff' || low < '\udc00' || low > '\udfff') {
                this.writeByte(63);
                ++i2;
                continue;
            }
            int codePoint = 65536 + ((c2 & 0xFFFF27FF) << 10 | low & 0xFFFF23FF);
            this.writeByte(codePoint >> 18 | 0xF0);
            this.writeByte(codePoint >> 12 & 0x3F | 0x80);
            this.writeByte(codePoint >> 6 & 0x3F | 0x80);
            this.writeByte(codePoint & 0x3F | 0x80);
            i2 += 2;
        }
        return this;
    }

    @Override
    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            this.writeByte(codePoint);
        } else if (codePoint < 2048) {
            this.writeByte(codePoint >> 6 | 0xC0);
            this.writeByte(codePoint & 0x3F | 0x80);
        } else if (codePoint < 65536) {
            if (codePoint >= 55296 && codePoint <= 57343) {
                this.writeByte(63);
            } else {
                this.writeByte(codePoint >> 12 | 0xE0);
                this.writeByte(codePoint >> 6 & 0x3F | 0x80);
                this.writeByte(codePoint & 0x3F | 0x80);
            }
        } else if (codePoint <= 0x10FFFF) {
            this.writeByte(codePoint >> 18 | 0xF0);
            this.writeByte(codePoint >> 12 & 0x3F | 0x80);
            this.writeByte(codePoint >> 6 & 0x3F | 0x80);
            this.writeByte(codePoint & 0x3F | 0x80);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(codePoint));
        }
        return this;
    }

    @Override
    public Buffer writeString(String string, Charset charset) {
        return this.writeString(string, 0, string.length(), charset);
    }

    @Override
    public Buffer writeString(String string, int beginIndex, int endIndex, Charset charset) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (beginIndex < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + beginIndex);
        }
        if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        }
        if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (charset.equals(Util.UTF_8)) {
            return this.writeUtf8(string, beginIndex, endIndex);
        }
        byte[] data = string.substring(beginIndex, endIndex).getBytes(charset);
        return this.write(data, 0, data.length);
    }

    @Override
    public Buffer write(byte[] source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        return this.write(source, 0, source.length);
    }

    @Override
    public Buffer write(byte[] source, int offset, int byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        Util.checkOffsetAndCount(source.length, offset, byteCount);
        int limit = offset + byteCount;
        while (offset < limit) {
            Segment tail = this.writableSegment(1);
            int toCopy = Math.min(limit - offset, 8192 - tail.limit);
            System.arraycopy(source, offset, tail.data, tail.limit, toCopy);
            offset += toCopy;
            tail.limit += toCopy;
        }
        this.size += (long)byteCount;
        return this;
    }

    @Override
    public int write(ByteBuffer source) throws IOException {
        int byteCount;
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        int remaining = byteCount = source.remaining();
        while (remaining > 0) {
            Segment tail = this.writableSegment(1);
            int toCopy = Math.min(remaining, 8192 - tail.limit);
            source.get(tail.data, tail.limit, toCopy);
            remaining -= toCopy;
            tail.limit += toCopy;
        }
        this.size += (long)byteCount;
        return byteCount;
    }

    @Override
    public long writeAll(Source source) throws IOException {
        long readCount;
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0L;
        while ((readCount = source.read(this, 8192L)) != -1L) {
            totalBytesRead += readCount;
        }
        return totalBytesRead;
    }

    @Override
    public BufferedSink write(Source source, long byteCount) throws IOException {
        while (byteCount > 0L) {
            long read = source.read(this, byteCount);
            if (read == -1L) {
                throw new EOFException();
            }
            byteCount -= read;
        }
        return this;
    }

    @Override
    public Buffer writeByte(int b2) {
        Segment tail = this.writableSegment(1);
        tail.data[tail.limit++] = (byte)b2;
        ++this.size;
        return this;
    }

    @Override
    public Buffer writeShort(int s2) {
        Segment tail = this.writableSegment(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        data[limit++] = (byte)(s2 >>> 8 & 0xFF);
        data[limit++] = (byte)(s2 & 0xFF);
        tail.limit = limit;
        this.size += 2L;
        return this;
    }

    @Override
    public Buffer writeShortLe(int s2) {
        return this.writeShort(Util.reverseBytesShort((short)s2));
    }

    @Override
    public Buffer writeInt(int i2) {
        Segment tail = this.writableSegment(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        data[limit++] = (byte)(i2 >>> 24 & 0xFF);
        data[limit++] = (byte)(i2 >>> 16 & 0xFF);
        data[limit++] = (byte)(i2 >>> 8 & 0xFF);
        data[limit++] = (byte)(i2 & 0xFF);
        tail.limit = limit;
        this.size += 4L;
        return this;
    }

    @Override
    public Buffer writeIntLe(int i2) {
        return this.writeInt(Util.reverseBytesInt(i2));
    }

    @Override
    public Buffer writeLong(long v2) {
        Segment tail = this.writableSegment(8);
        byte[] data = tail.data;
        int limit = tail.limit;
        data[limit++] = (byte)(v2 >>> 56 & 0xFFL);
        data[limit++] = (byte)(v2 >>> 48 & 0xFFL);
        data[limit++] = (byte)(v2 >>> 40 & 0xFFL);
        data[limit++] = (byte)(v2 >>> 32 & 0xFFL);
        data[limit++] = (byte)(v2 >>> 24 & 0xFFL);
        data[limit++] = (byte)(v2 >>> 16 & 0xFFL);
        data[limit++] = (byte)(v2 >>> 8 & 0xFFL);
        data[limit++] = (byte)(v2 & 0xFFL);
        tail.limit = limit;
        this.size += 8L;
        return this;
    }

    @Override
    public Buffer writeLongLe(long v2) {
        return this.writeLong(Util.reverseBytesLong(v2));
    }

    @Override
    public Buffer writeDecimalLong(long v2) {
        int width;
        if (v2 == 0L) {
            return this.writeByte(48);
        }
        boolean negative = false;
        if (v2 < 0L) {
            if ((v2 = -v2) < 0L) {
                return this.writeUtf8("-9223372036854775808");
            }
            negative = true;
        }
        int n2 = v2 < 100000000L ? (v2 < 10000L ? (v2 < 100L ? (v2 < 10L ? 1 : 2) : (v2 < 1000L ? 3 : 4)) : (v2 < 1000000L ? (v2 < 100000L ? 5 : 6) : (v2 < 10000000L ? 7 : 8))) : (v2 < 1000000000000L ? (v2 < 10000000000L ? (v2 < 1000000000L ? 9 : 10) : (v2 < 100000000000L ? 11 : 12)) : (v2 < 1000000000000000L ? (v2 < 10000000000000L ? 13 : (v2 < 100000000000000L ? 14 : 15)) : (v2 < 100000000000000000L ? (v2 < 10000000000000000L ? 16 : 17) : (width = v2 < 1000000000000000000L ? 18 : 19))));
        if (negative) {
            ++width;
        }
        Segment tail = this.writableSegment(width);
        byte[] data = tail.data;
        int pos = tail.limit + width;
        while (v2 != 0L) {
            int digit = (int)(v2 % 10L);
            data[--pos] = DIGITS[digit];
            v2 /= 10L;
        }
        if (negative) {
            data[--pos] = 45;
        }
        tail.limit += width;
        this.size += (long)width;
        return this;
    }

    @Override
    public Buffer writeHexadecimalUnsignedLong(long v2) {
        if (v2 == 0L) {
            return this.writeByte(48);
        }
        int width = Long.numberOfTrailingZeros(Long.highestOneBit(v2)) / 4 + 1;
        Segment tail = this.writableSegment(width);
        byte[] data = tail.data;
        int start = tail.limit;
        for (int pos = tail.limit + width - 1; pos >= start; --pos) {
            data[pos] = DIGITS[(int)(v2 & 0xFL)];
            v2 >>>= 4;
        }
        tail.limit += width;
        this.size += (long)width;
        return this;
    }

    Segment writableSegment(int minimumCapacity) {
        if (minimumCapacity < 1 || minimumCapacity > 8192) {
            throw new IllegalArgumentException();
        }
        if (this.head == null) {
            this.head.next = this.head.prev = (this.head = SegmentPool.take());
            return this.head.prev;
        }
        Segment tail = this.head.prev;
        if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
            tail = tail.push(SegmentPool.take());
        }
        return tail;
    }

    @Override
    public void write(Buffer source, long byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (source == this) {
            throw new IllegalArgumentException("source == this");
        }
        Util.checkOffsetAndCount(source.size, 0L, byteCount);
        while (byteCount > 0L) {
            if (byteCount < (long)(source.head.limit - source.head.pos)) {
                Segment tail;
                Segment segment = tail = this.head != null ? this.head.prev : null;
                if (tail != null && tail.owner && byteCount + (long)tail.limit - (long)(tail.shared ? 0 : tail.pos) <= 8192L) {
                    source.head.writeTo(tail, (int)byteCount);
                    source.size -= byteCount;
                    this.size += byteCount;
                    return;
                }
                source.head = source.head.split((int)byteCount);
            }
            Segment segmentToMove = source.head;
            long movedByteCount = segmentToMove.limit - segmentToMove.pos;
            source.head = segmentToMove.pop();
            if (this.head == null) {
                this.head.next = this.head.prev = (this.head = segmentToMove);
            } else {
                Segment tail = this.head.prev;
                tail = tail.push(segmentToMove);
                tail.compact();
            }
            source.size -= movedByteCount;
            this.size += movedByteCount;
            byteCount -= movedByteCount;
        }
    }

    @Override
    public long read(Buffer sink, long byteCount) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (byteCount < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        if (this.size == 0L) {
            return -1L;
        }
        if (byteCount > this.size) {
            byteCount = this.size;
        }
        sink.write(this, byteCount);
        return byteCount;
    }

    @Override
    public long indexOf(byte b2) {
        return this.indexOf(b2, 0L, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b2, long fromIndex) {
        return this.indexOf(b2, fromIndex, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b2, long fromIndex, long toIndex) {
        long offset;
        if (fromIndex < 0L || toIndex < fromIndex) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", this.size, fromIndex, toIndex));
        }
        if (toIndex > this.size) {
            toIndex = this.size;
        }
        if (fromIndex == toIndex) {
            return -1L;
        }
        Segment s2 = this.head;
        if (s2 == null) {
            return -1L;
        }
        if (this.size - fromIndex < fromIndex) {
            for (offset = this.size; offset > fromIndex; offset -= (long)(s2.limit - s2.pos)) {
                s2 = s2.prev;
            }
        } else {
            long nextOffset;
            offset = 0L;
            while ((nextOffset = offset + (long)(s2.limit - s2.pos)) < fromIndex) {
                s2 = s2.next;
                offset = nextOffset;
            }
        }
        while (offset < toIndex) {
            byte[] data = s2.data;
            int limit = (int)Math.min((long)s2.limit, (long)s2.pos + toIndex - offset);
            for (int pos = (int)((long)s2.pos + fromIndex - offset); pos < limit; ++pos) {
                if (data[pos] != b2) continue;
                return (long)(pos - s2.pos) + offset;
            }
            fromIndex = offset += (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        return -1L;
    }

    @Override
    public long indexOf(ByteString bytes) throws IOException {
        return this.indexOf(bytes, 0L);
    }

    @Override
    public long indexOf(ByteString bytes, long fromIndex) throws IOException {
        long offset;
        if (bytes.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        if (fromIndex < 0L) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment s2 = this.head;
        if (s2 == null) {
            return -1L;
        }
        if (this.size - fromIndex < fromIndex) {
            for (offset = this.size; offset > fromIndex; offset -= (long)(s2.limit - s2.pos)) {
                s2 = s2.prev;
            }
        } else {
            long nextOffset;
            offset = 0L;
            while ((nextOffset = offset + (long)(s2.limit - s2.pos)) < fromIndex) {
                s2 = s2.next;
                offset = nextOffset;
            }
        }
        byte b0 = bytes.getByte(0);
        int bytesSize = bytes.size();
        long resultLimit = this.size - (long)bytesSize + 1L;
        while (offset < resultLimit) {
            byte[] data = s2.data;
            int segmentLimit = (int)Math.min((long)s2.limit, (long)s2.pos + resultLimit - offset);
            for (int pos = (int)((long)s2.pos + fromIndex - offset); pos < segmentLimit; ++pos) {
                if (data[pos] != b0 || !this.rangeEquals(s2, pos + 1, bytes, 1, bytesSize)) continue;
                return (long)(pos - s2.pos) + offset;
            }
            fromIndex = offset += (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        return -1L;
    }

    @Override
    public long indexOfElement(ByteString targetBytes) {
        return this.indexOfElement(targetBytes, 0L);
    }

    @Override
    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        long offset;
        if (fromIndex < 0L) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment s2 = this.head;
        if (s2 == null) {
            return -1L;
        }
        if (this.size - fromIndex < fromIndex) {
            for (offset = this.size; offset > fromIndex; offset -= (long)(s2.limit - s2.pos)) {
                s2 = s2.prev;
            }
        } else {
            long nextOffset;
            offset = 0L;
            while ((nextOffset = offset + (long)(s2.limit - s2.pos)) < fromIndex) {
                s2 = s2.next;
                offset = nextOffset;
            }
        }
        if (targetBytes.size() == 2) {
            byte b0 = targetBytes.getByte(0);
            byte b1 = targetBytes.getByte(1);
            while (offset < this.size) {
                byte[] data = s2.data;
                int limit = s2.limit;
                for (int pos = (int)((long)s2.pos + fromIndex - offset); pos < limit; ++pos) {
                    byte b2 = data[pos];
                    if (b2 != b0 && b2 != b1) continue;
                    return (long)(pos - s2.pos) + offset;
                }
                fromIndex = offset += (long)(s2.limit - s2.pos);
                s2 = s2.next;
            }
        } else {
            byte[] targetByteArray = targetBytes.internalArray();
            while (offset < this.size) {
                byte[] data = s2.data;
                int limit = s2.limit;
                for (int pos = (int)((long)s2.pos + fromIndex - offset); pos < limit; ++pos) {
                    byte b3 = data[pos];
                    for (byte t2 : targetByteArray) {
                        if (b3 != t2) continue;
                        return (long)(pos - s2.pos) + offset;
                    }
                }
                fromIndex = offset += (long)(s2.limit - s2.pos);
                s2 = s2.next;
            }
        }
        return -1L;
    }

    @Override
    public boolean rangeEquals(long offset, ByteString bytes) {
        return this.rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override
    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        if (offset < 0L || bytesOffset < 0 || byteCount < 0 || this.size - offset < (long)byteCount || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i2 = 0; i2 < byteCount; ++i2) {
            if (this.getByte(offset + (long)i2) == bytes.getByte(bytesOffset + i2)) continue;
            return false;
        }
        return true;
    }

    private boolean rangeEquals(Segment segment, int segmentPos, ByteString bytes, int bytesOffset, int bytesLimit) {
        int segmentLimit = segment.limit;
        byte[] data = segment.data;
        for (int i2 = bytesOffset; i2 < bytesLimit; ++i2) {
            if (segmentPos == segmentLimit) {
                segment = segment.next;
                data = segment.data;
                segmentPos = segment.pos;
                segmentLimit = segment.limit;
            }
            if (data[segmentPos] != bytes.getByte(i2)) {
                return false;
            }
            ++segmentPos;
        }
        return true;
    }

    @Override
    public void flush() {
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void close() {
    }

    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }

    List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(this.head.limit - this.head.pos);
        Segment s2 = this.head.next;
        while (s2 != this.head) {
            result.add(s2.limit - s2.pos);
            s2 = s2.next;
        }
        return result;
    }

    public final ByteString md5() {
        return this.digest("MD5");
    }

    public final ByteString sha1() {
        return this.digest("SHA-1");
    }

    public final ByteString sha256() {
        return this.digest("SHA-256");
    }

    public final ByteString sha512() {
        return this.digest("SHA-512");
    }

    private ByteString digest(String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            if (this.head != null) {
                messageDigest.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                Segment s2 = this.head.next;
                while (s2 != this.head) {
                    messageDigest.update(s2.data, s2.pos, s2.limit - s2.pos);
                    s2 = s2.next;
                }
            }
            return ByteString.of(messageDigest.digest());
        }
        catch (NoSuchAlgorithmException e2) {
            throw new AssertionError();
        }
    }

    public final ByteString hmacSha1(ByteString key) {
        return this.hmac("HmacSHA1", key);
    }

    public final ByteString hmacSha256(ByteString key) {
        return this.hmac("HmacSHA256", key);
    }

    public final ByteString hmacSha512(ByteString key) {
        return this.hmac("HmacSHA512", key);
    }

    private ByteString hmac(String algorithm, ByteString key) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            if (this.head != null) {
                mac.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                Segment s2 = this.head.next;
                while (s2 != this.head) {
                    mac.update(s2.data, s2.pos, s2.limit - s2.pos);
                    s2 = s2.next;
                }
            }
            return ByteString.of(mac.doFinal());
        }
        catch (NoSuchAlgorithmException e2) {
            throw new AssertionError();
        }
        catch (InvalidKeyException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    public boolean equals(Object o2) {
        long count;
        if (this == o2) {
            return true;
        }
        if (!(o2 instanceof Buffer)) {
            return false;
        }
        Buffer that = (Buffer)o2;
        if (this.size != that.size) {
            return false;
        }
        if (this.size == 0L) {
            return true;
        }
        Segment sa2 = this.head;
        Segment sb2 = that.head;
        int posA = sa2.pos;
        int posB = sb2.pos;
        for (long pos = 0L; pos < this.size; pos += count) {
            count = Math.min(sa2.limit - posA, sb2.limit - posB);
            int i2 = 0;
            while ((long)i2 < count) {
                if (sa2.data[posA++] != sb2.data[posB++]) {
                    return false;
                }
                ++i2;
            }
            if (posA == sa2.limit) {
                sa2 = sa2.next;
                posA = sa2.pos;
            }
            if (posB != sb2.limit) continue;
            sb2 = sb2.next;
            posB = sb2.pos;
        }
        return true;
    }

    public int hashCode() {
        Segment s2 = this.head;
        if (s2 == null) {
            return 0;
        }
        int result = 1;
        do {
            int limit = s2.limit;
            for (int pos = s2.pos; pos < limit; ++pos) {
                result = 31 * result + s2.data[pos];
            }
        } while ((s2 = s2.next) != this.head);
        return result;
    }

    public String toString() {
        return this.snapshot().toString();
    }

    public Buffer clone() {
        Buffer result = new Buffer();
        if (this.size == 0L) {
            return result;
        }
        result.head.next = result.head.prev = (result.head = this.head.sharedCopy());
        Segment s2 = this.head.next;
        while (s2 != this.head) {
            result.head.prev.push(s2.sharedCopy());
            s2 = s2.next;
        }
        result.size = this.size;
        return result;
    }

    public final ByteString snapshot() {
        if (this.size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
        }
        return this.snapshot((int)this.size);
    }

    public final ByteString snapshot(int byteCount) {
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, byteCount);
    }

    public final UnsafeCursor readUnsafe() {
        return this.readUnsafe(new UnsafeCursor());
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = false;
        return unsafeCursor;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return this.readAndWriteUnsafe(new UnsafeCursor());
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = true;
        return unsafeCursor;
    }

    public static final class UnsafeCursor
    implements Closeable {
        public Buffer buffer;
        public boolean readWrite;
        private Segment segment;
        public long offset = -1L;
        public byte[] data;
        public int start = -1;
        public int end = -1;

        public final int next() {
            if (this.offset == this.buffer.size) {
                throw new IllegalStateException();
            }
            if (this.offset == -1L) {
                return this.seek(0L);
            }
            return this.seek(this.offset + (long)(this.end - this.start));
        }

        public final int seek(long offset) {
            long nextOffset;
            Segment next;
            if (offset < -1L || offset > this.buffer.size) {
                throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", offset, this.buffer.size));
            }
            if (offset == -1L || offset == this.buffer.size) {
                this.segment = null;
                this.offset = offset;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            }
            long min = 0L;
            long max = this.buffer.size;
            Segment head = this.buffer.head;
            Segment tail = this.buffer.head;
            if (this.segment != null) {
                long segmentOffset = this.offset - (long)(this.start - this.segment.pos);
                if (segmentOffset > offset) {
                    max = segmentOffset;
                    tail = this.segment;
                } else {
                    min = segmentOffset;
                    head = this.segment;
                }
            }
            if (max - offset > offset - min) {
                next = head;
                nextOffset = min;
                while (offset >= nextOffset + (long)(next.limit - next.pos)) {
                    nextOffset += (long)(next.limit - next.pos);
                    next = next.next;
                }
            } else {
                next = tail;
                for (nextOffset = max; nextOffset > offset; nextOffset -= (long)(next.limit - next.pos)) {
                    next = next.prev;
                }
            }
            if (this.readWrite && next.shared) {
                Segment unsharedNext = next.unsharedCopy();
                if (this.buffer.head == next) {
                    this.buffer.head = unsharedNext;
                }
                next = next.push(unsharedNext);
                next.prev.pop();
            }
            this.segment = next;
            this.offset = offset;
            this.data = next.data;
            this.start = next.pos + (int)(offset - nextOffset);
            this.end = next.limit;
            return this.end - this.start;
        }

        public final long resizeBuffer(long newSize) {
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long oldSize = this.buffer.size;
            if (newSize <= oldSize) {
                int tailSize;
                if (newSize < 0L) {
                    throw new IllegalArgumentException("newSize < 0: " + newSize);
                }
                for (long bytesToSubtract = oldSize - newSize; bytesToSubtract > 0L; bytesToSubtract -= (long)tailSize) {
                    Segment tail = this.buffer.head.prev;
                    tailSize = tail.limit - tail.pos;
                    if ((long)tailSize <= bytesToSubtract) {
                        this.buffer.head = tail.pop();
                        SegmentPool.recycle(tail);
                        continue;
                    }
                    tail.limit = (int)((long)tail.limit - bytesToSubtract);
                    break;
                }
                this.segment = null;
                this.offset = newSize;
                this.data = null;
                this.start = -1;
                this.end = -1;
            } else if (newSize > oldSize) {
                int segmentBytesToAdd;
                boolean needsToSeek = true;
                for (long bytesToAdd = newSize - oldSize; bytesToAdd > 0L; bytesToAdd -= (long)segmentBytesToAdd) {
                    Segment tail = this.buffer.writableSegment(1);
                    segmentBytesToAdd = (int)Math.min(bytesToAdd, (long)(8192 - tail.limit));
                    tail.limit += segmentBytesToAdd;
                    if (!needsToSeek) continue;
                    this.segment = tail;
                    this.offset = oldSize;
                    this.data = tail.data;
                    this.start = tail.limit - segmentBytesToAdd;
                    this.end = tail.limit;
                    needsToSeek = false;
                }
            }
            this.buffer.size = newSize;
            return oldSize;
        }

        public final long expandBuffer(int minByteCount) {
            if (minByteCount <= 0) {
                throw new IllegalArgumentException("minByteCount <= 0: " + minByteCount);
            }
            if (minByteCount > 8192) {
                throw new IllegalArgumentException("minByteCount > Segment.SIZE: " + minByteCount);
            }
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.readWrite) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
            }
            long oldSize = this.buffer.size;
            Segment tail = this.buffer.writableSegment(minByteCount);
            int result = 8192 - tail.limit;
            tail.limit = 8192;
            this.buffer.size = oldSize + (long)result;
            this.segment = tail;
            this.offset = oldSize;
            this.data = tail.data;
            this.start = 8192 - result;
            this.end = 8192;
            return result;
        }

        @Override
        public void close() {
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.buffer = null;
            this.segment = null;
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }
    }
}

