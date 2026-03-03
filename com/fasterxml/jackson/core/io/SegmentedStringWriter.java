/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.Writer;

public final class SegmentedStringWriter
extends Writer {
    private final TextBuffer _buffer;

    public SegmentedStringWriter(BufferRecycler br2) {
        this._buffer = new TextBuffer(br2);
    }

    @Override
    public Writer append(char c2) {
        this.write(c2);
        return this;
    }

    @Override
    public Writer append(CharSequence csq) {
        String str = csq.toString();
        this._buffer.append(str, 0, str.length());
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) {
        String str = csq.subSequence(start, end).toString();
        this._buffer.append(str, 0, str.length());
        return this;
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(char[] cbuf) {
        this._buffer.append(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(char[] cbuf, int off, int len) {
        this._buffer.append(cbuf, off, len);
    }

    @Override
    public void write(int c2) {
        this._buffer.append((char)c2);
    }

    @Override
    public void write(String str) {
        this._buffer.append(str, 0, str.length());
    }

    @Override
    public void write(String str, int off, int len) {
        this._buffer.append(str, off, len);
    }

    public String getAndClear() {
        String result = this._buffer.contentsAsString();
        this._buffer.releaseBuffers();
        return result;
    }
}

