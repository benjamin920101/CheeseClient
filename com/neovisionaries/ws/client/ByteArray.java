/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import java.nio.ByteBuffer;

class ByteArray {
    private static final int ADDITIONAL_BUFFER_SIZE = 1024;
    private ByteBuffer mBuffer;
    private int mLength;

    public ByteArray(int capacity) {
        this.mBuffer = ByteBuffer.allocate(capacity);
        this.mLength = 0;
    }

    public ByteArray(byte[] data) {
        this.mBuffer = ByteBuffer.wrap(data);
        this.mLength = data.length;
    }

    public int length() {
        return this.mLength;
    }

    public byte get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || this.mLength <= index) {
            throw new IndexOutOfBoundsException(String.format("Bad index: index=%d, length=%d", index, this.mLength));
        }
        return this.mBuffer.get(index);
    }

    private void expandBuffer(int newBufferSize) {
        ByteBuffer newBuffer = ByteBuffer.allocate(newBufferSize);
        int oldPosition = this.mBuffer.position();
        this.mBuffer.position(0);
        newBuffer.put(this.mBuffer);
        newBuffer.position(oldPosition);
        this.mBuffer = newBuffer;
    }

    public void put(int data) {
        if (this.mBuffer.capacity() < this.mLength + 1) {
            this.expandBuffer(this.mLength + 1024);
        }
        this.mBuffer.put((byte)data);
        ++this.mLength;
    }

    public void put(byte[] source) {
        if (this.mBuffer.capacity() < this.mLength + source.length) {
            this.expandBuffer(this.mLength + source.length + 1024);
        }
        this.mBuffer.put(source);
        this.mLength += source.length;
    }

    public void put(byte[] source, int index, int length) {
        if (this.mBuffer.capacity() < this.mLength + length) {
            this.expandBuffer(this.mLength + length + 1024);
        }
        this.mBuffer.put(source, index, length);
        this.mLength += length;
    }

    public void put(ByteArray source, int index, int length) {
        this.put(source.mBuffer.array(), index, length);
    }

    public byte[] toBytes() {
        return this.toBytes(0);
    }

    public byte[] toBytes(int beginIndex) {
        return this.toBytes(beginIndex, this.length());
    }

    public byte[] toBytes(int beginIndex, int endIndex) {
        int len = endIndex - beginIndex;
        if (len < 0 || beginIndex < 0 || this.mLength < endIndex) {
            throw new IllegalArgumentException(String.format("Bad range: beginIndex=%d, endIndex=%d, length=%d", beginIndex, endIndex, this.mLength));
        }
        byte[] bytes = new byte[len];
        if (len != 0) {
            System.arraycopy(this.mBuffer.array(), beginIndex, bytes, 0, len);
        }
        return bytes;
    }

    public void clear() {
        this.mBuffer.clear();
        this.mBuffer.position(0);
        this.mLength = 0;
    }

    public void shrink(int size) {
        if (this.mBuffer.capacity() <= size) {
            return;
        }
        int endIndex = this.mLength;
        int beginIndex = this.mLength - size;
        byte[] bytes = this.toBytes(beginIndex, endIndex);
        this.mBuffer = ByteBuffer.wrap(bytes);
        this.mBuffer.position(bytes.length);
        this.mLength = bytes.length;
    }

    public boolean getBit(int bitIndex) {
        int index = bitIndex / 8;
        int shift = bitIndex % 8;
        byte value = this.get(index);
        return (value & 1 << shift) != 0;
    }

    public int getBits(int bitIndex, int nBits) {
        int number = 0;
        int weight = 1;
        int i2 = 0;
        while (i2 < nBits) {
            if (this.getBit(bitIndex + i2)) {
                number += weight;
            }
            ++i2;
            weight *= 2;
        }
        return number;
    }

    public int getHuffmanBits(int bitIndex, int nBits) {
        int number = 0;
        int weight = 1;
        int i2 = nBits - 1;
        while (0 <= i2) {
            if (this.getBit(bitIndex + i2)) {
                number += weight;
            }
            --i2;
            weight *= 2;
        }
        return number;
    }

    public boolean readBit(int[] bitIndex) {
        boolean result = this.getBit(bitIndex[0]);
        bitIndex[0] = bitIndex[0] + 1;
        return result;
    }

    public int readBits(int[] bitIndex, int nBits) {
        int number = this.getBits(bitIndex[0], nBits);
        bitIndex[0] = bitIndex[0] + nBits;
        return number;
    }

    public void setBit(int bitIndex, boolean bit2) {
        int index = bitIndex / 8;
        int shift = bitIndex % 8;
        int value = this.get(index);
        value = bit2 ? (value |= 1 << shift) : (value &= ~(1 << shift));
        this.mBuffer.put(index, (byte)value);
    }

    public void clearBit(int bitIndex) {
        this.setBit(bitIndex, false);
    }
}

