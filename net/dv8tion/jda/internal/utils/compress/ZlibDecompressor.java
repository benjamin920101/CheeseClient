/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.compress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.internal.utils.IOUtil;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.compress.Decompressor;

public class ZlibDecompressor
implements Decompressor {
    private static final int Z_SYNC_FLUSH = 65535;
    private final int maxBufferSize;
    private final Inflater inflater = new Inflater();
    private ByteBuffer flushBuffer = null;
    private SoftReference<ByteArrayOutputStream> decompressBuffer = null;

    public ZlibDecompressor(int maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
    }

    private SoftReference<ByteArrayOutputStream> newDecompressBuffer() {
        return new SoftReference<ByteArrayOutputStream>(new ByteArrayOutputStream(Math.min(1024, this.maxBufferSize)));
    }

    private ByteArrayOutputStream getDecompressBuffer() {
        ByteArrayOutputStream buffer;
        if (this.decompressBuffer == null) {
            this.decompressBuffer = this.newDecompressBuffer();
        }
        if ((buffer = this.decompressBuffer.get()) == null) {
            buffer = new ByteArrayOutputStream(Math.min(1024, this.maxBufferSize));
            this.decompressBuffer = new SoftReference<ByteArrayOutputStream>(buffer);
        }
        return buffer;
    }

    private boolean isFlush(byte[] data) {
        if (data.length < 4) {
            return false;
        }
        int suffix = IOUtil.getIntBigEndian(data, data.length - 4);
        return suffix == 65535;
    }

    private void buffer(byte[] data) {
        if (this.flushBuffer == null) {
            this.flushBuffer = ByteBuffer.allocate(data.length * 2);
        }
        if (this.flushBuffer.capacity() < data.length + this.flushBuffer.position()) {
            this.flushBuffer.flip();
            this.flushBuffer = IOUtil.reallocate(this.flushBuffer, (this.flushBuffer.capacity() + data.length) * 2);
        }
        this.flushBuffer.put(data);
    }

    private Object lazy(byte[] data) {
        return JDALogger.getLazyString(() -> Arrays.toString(data));
    }

    @Override
    public Compression getType() {
        return Compression.ZLIB;
    }

    @Override
    public void reset() {
        this.inflater.reset();
    }

    @Override
    public void shutdown() {
        this.reset();
    }

    @Override
    public byte[] decompress(byte[] data) throws DataFormatException {
        if (!this.isFlush(data)) {
            LOG.debug("Received incomplete data, writing to buffer. Length: {}", (Object)data.length);
            this.buffer(data);
            return null;
        }
        if (this.flushBuffer != null) {
            LOG.debug("Received final part of incomplete data");
            this.buffer(data);
            byte[] arr2 = this.flushBuffer.array();
            data = new byte[this.flushBuffer.position()];
            System.arraycopy(arr2, 0, data, 0, data.length);
            this.flushBuffer = null;
        }
        LOG.trace("Decompressing data {}", this.lazy(data));
        ByteArrayOutputStream buffer = this.getDecompressBuffer();
        try {
            byte[] byArray;
            InflaterOutputStream decompressor = new InflaterOutputStream(buffer, this.inflater);
            try {
                decompressor.write(data);
                byArray = buffer.toByteArray();
            }
            catch (Throwable throwable) {
                try {
                    try {
                        decompressor.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                catch (IOException e2) {
                    throw (DataFormatException)new DataFormatException("Malformed").initCause(e2);
                }
            }
            decompressor.close();
            return byArray;
        }
        finally {
            if (buffer.size() > this.maxBufferSize) {
                this.decompressBuffer = this.newDecompressBuffer();
            } else {
                buffer.reset();
            }
        }
    }
}

