/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okhttp3.internal.cache2.FileOperator;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Relay {
    private static final int SOURCE_UPSTREAM = 1;
    private static final int SOURCE_FILE = 2;
    static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
    static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
    private static final long FILE_HEADER_SIZE = 32L;
    RandomAccessFile file;
    Thread upstreamReader;
    Source upstream;
    final Buffer upstreamBuffer = new Buffer();
    long upstreamPos;
    boolean complete;
    private final ByteString metadata;
    final Buffer buffer = new Buffer();
    final long bufferMaxSize;
    int sourceCount;

    private Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize) {
        this.file = file;
        this.upstream = upstream;
        this.complete = upstream == null;
        this.upstreamPos = upstreamPos;
        this.metadata = metadata;
        this.bufferMaxSize = bufferMaxSize;
    }

    public static Relay edit(File file, Source upstream, ByteString metadata, long bufferMaxSize) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay result = new Relay(randomAccessFile, upstream, 0L, metadata, bufferMaxSize);
        randomAccessFile.setLength(0L);
        result.writeHeader(PREFIX_DIRTY, -1L, -1L);
        return result;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        Buffer header = new Buffer();
        fileOperator.read(0L, header, 32L);
        ByteString prefix = header.readByteString(PREFIX_CLEAN.size());
        if (!prefix.equals(PREFIX_CLEAN)) {
            throw new IOException("unreadable cache file");
        }
        long upstreamSize = header.readLong();
        long metadataSize = header.readLong();
        Buffer metadataBuffer = new Buffer();
        fileOperator.read(32L + upstreamSize, metadataBuffer, metadataSize);
        ByteString metadata = metadataBuffer.readByteString();
        return new Relay(randomAccessFile, null, upstreamSize, metadata, 0L);
    }

    private void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
        Buffer header = new Buffer();
        header.write(prefix);
        header.writeLong(upstreamSize);
        header.writeLong(metadataSize);
        if (header.size() != 32L) {
            throw new IllegalArgumentException();
        }
        FileOperator fileOperator = new FileOperator(this.file.getChannel());
        fileOperator.write(0L, header, 32L);
    }

    private void writeMetadata(long upstreamSize) throws IOException {
        Buffer metadataBuffer = new Buffer();
        metadataBuffer.write(this.metadata);
        FileOperator fileOperator = new FileOperator(this.file.getChannel());
        fileOperator.write(32L + upstreamSize, metadataBuffer, this.metadata.size());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void commit(long upstreamSize) throws IOException {
        this.writeMetadata(upstreamSize);
        this.file.getChannel().force(false);
        this.writeHeader(PREFIX_CLEAN, upstreamSize, this.metadata.size());
        this.file.getChannel().force(false);
        Relay relay = this;
        synchronized (relay) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Source newSource() {
        Relay relay = this;
        synchronized (relay) {
            if (this.file == null) {
                return null;
            }
            ++this.sourceCount;
        }
        return new RelaySource();
    }

    class RelaySource
    implements Source {
        private final Timeout timeout = new Timeout();
        private FileOperator fileOperator;
        private long sourcePos;

        RelaySource() {
            this.fileOperator = new FileOperator(Relay.this.file.getChannel());
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            int source;
            long upstreamPos;
            block27: {
                if (this.fileOperator == null) {
                    throw new IllegalStateException("closed");
                }
                Relay relay = Relay.this;
                synchronized (relay) {
                    while (this.sourcePos == (upstreamPos = Relay.this.upstreamPos)) {
                        if (Relay.this.complete) {
                            return -1L;
                        }
                        if (Relay.this.upstreamReader != null) {
                            this.timeout.waitUntilNotified(Relay.this);
                            continue;
                        }
                        Relay.this.upstreamReader = Thread.currentThread();
                        source = 1;
                        break block27;
                    }
                    long bufferPos = upstreamPos - Relay.this.buffer.size();
                    if (this.sourcePos < bufferPos) {
                        source = 2;
                        break block27;
                    }
                    long bytesToRead = Math.min(byteCount, upstreamPos - this.sourcePos);
                    Relay.this.buffer.copyTo(sink, this.sourcePos - bufferPos, bytesToRead);
                    this.sourcePos += bytesToRead;
                    return bytesToRead;
                }
            }
            if (source == 2) {
                long bytesToRead = Math.min(byteCount, upstreamPos - this.sourcePos);
                this.fileOperator.read(32L + this.sourcePos, sink, bytesToRead);
                this.sourcePos += bytesToRead;
                return bytesToRead;
            }
            try {
                long upstreamBytesRead = Relay.this.upstream.read(Relay.this.upstreamBuffer, Relay.this.bufferMaxSize);
                if (upstreamBytesRead == -1L) {
                    Relay.this.commit(upstreamPos);
                    long l2 = -1L;
                    return l2;
                }
                long bytesRead = Math.min(upstreamBytesRead, byteCount);
                Relay.this.upstreamBuffer.copyTo(sink, 0L, bytesRead);
                this.sourcePos += bytesRead;
                this.fileOperator.write(32L + upstreamPos, Relay.this.upstreamBuffer.clone(), upstreamBytesRead);
                Relay relay = Relay.this;
                synchronized (relay) {
                    Relay.this.buffer.write(Relay.this.upstreamBuffer, upstreamBytesRead);
                    if (Relay.this.buffer.size() > Relay.this.bufferMaxSize) {
                        Relay.this.buffer.skip(Relay.this.buffer.size() - Relay.this.bufferMaxSize);
                    }
                    Relay.this.upstreamPos += upstreamBytesRead;
                }
                long l3 = bytesRead;
                return l3;
            }
            finally {
                Relay relay = Relay.this;
                synchronized (relay) {
                    Relay.this.upstreamReader = null;
                    Relay.this.notifyAll();
                }
            }
        }

        @Override
        public Timeout timeout() {
            return this.timeout;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            if (this.fileOperator == null) {
                return;
            }
            this.fileOperator = null;
            RandomAccessFile fileToClose = null;
            Relay relay = Relay.this;
            synchronized (relay) {
                --Relay.this.sourceCount;
                if (Relay.this.sourceCount == 0) {
                    fileToClose = Relay.this.file;
                    Relay.this.file = null;
                }
            }
            if (fileToClose != null) {
                Util.closeQuietly(fileToClose);
            }
        }
    }
}

