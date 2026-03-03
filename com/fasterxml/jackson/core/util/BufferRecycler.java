/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class BufferRecycler {
    public static final int BYTE_READ_IO_BUFFER = 0;
    public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
    public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
    public static final int BYTE_BASE64_CODEC_BUFFER = 3;
    public static final int CHAR_TOKEN_BUFFER = 0;
    public static final int CHAR_CONCAT_BUFFER = 1;
    public static final int CHAR_TEXT_BUFFER = 2;
    public static final int CHAR_NAME_COPY_BUFFER = 3;
    private static final int[] BYTE_BUFFER_LENGTHS = new int[]{8000, 8000, 2000, 2000};
    private static final int[] CHAR_BUFFER_LENGTHS = new int[]{4000, 4000, 200, 200};
    protected final AtomicReferenceArray<byte[]> _byteBuffers;
    protected final AtomicReferenceArray<char[]> _charBuffers;

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int bbCount, int cbCount) {
        this._byteBuffers = new AtomicReferenceArray(bbCount);
        this._charBuffers = new AtomicReferenceArray(cbCount);
    }

    public final byte[] allocByteBuffer(int ix2) {
        return this.allocByteBuffer(ix2, 0);
    }

    public byte[] allocByteBuffer(int ix2, int minSize) {
        byte[] buffer;
        int DEF_SIZE = this.byteBufferLength(ix2);
        if (minSize < DEF_SIZE) {
            minSize = DEF_SIZE;
        }
        if ((buffer = (byte[])this._byteBuffers.getAndSet(ix2, null)) == null || buffer.length < minSize) {
            buffer = this.balloc(minSize);
        }
        return buffer;
    }

    public void releaseByteBuffer(int ix2, byte[] buffer) {
        this._byteBuffers.set(ix2, buffer);
    }

    public final char[] allocCharBuffer(int ix2) {
        return this.allocCharBuffer(ix2, 0);
    }

    public char[] allocCharBuffer(int ix2, int minSize) {
        char[] buffer;
        int DEF_SIZE = this.charBufferLength(ix2);
        if (minSize < DEF_SIZE) {
            minSize = DEF_SIZE;
        }
        if ((buffer = (char[])this._charBuffers.getAndSet(ix2, null)) == null || buffer.length < minSize) {
            buffer = this.calloc(minSize);
        }
        return buffer;
    }

    public void releaseCharBuffer(int ix2, char[] buffer) {
        this._charBuffers.set(ix2, buffer);
    }

    protected int byteBufferLength(int ix2) {
        return BYTE_BUFFER_LENGTHS[ix2];
    }

    protected int charBufferLength(int ix2) {
        return CHAR_BUFFER_LENGTHS[ix2];
    }

    protected byte[] balloc(int size) {
        return new byte[size];
    }

    protected char[] calloc(int size) {
        return new char[size];
    }
}

