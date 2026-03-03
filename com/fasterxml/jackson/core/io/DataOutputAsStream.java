/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputAsStream
extends OutputStream {
    protected final DataOutput _output;

    public DataOutputAsStream(DataOutput out) {
        this._output = out;
    }

    @Override
    public void write(int b2) throws IOException {
        this._output.write(b2);
    }

    @Override
    public void write(byte[] b2) throws IOException {
        this._output.write(b2, 0, b2.length);
    }

    @Override
    public void write(byte[] b2, int offset, int length) throws IOException {
        this._output.write(b2, offset, length);
    }
}

