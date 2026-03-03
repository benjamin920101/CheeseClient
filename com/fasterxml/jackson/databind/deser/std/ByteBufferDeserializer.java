/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.util.ByteBufferBackedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferDeserializer
extends StdScalarDeserializer<ByteBuffer> {
    private static final long serialVersionUID = 1L;

    protected ByteBufferDeserializer() {
        super(ByteBuffer.class);
    }

    @Override
    public ByteBuffer deserialize(JsonParser parser, DeserializationContext cx2) throws IOException {
        byte[] b2 = parser.getBinaryValue();
        return ByteBuffer.wrap(b2);
    }

    @Override
    public ByteBuffer deserialize(JsonParser jp2, DeserializationContext ctxt, ByteBuffer intoValue) throws IOException {
        ByteBufferBackedOutputStream out = new ByteBufferBackedOutputStream(intoValue);
        jp2.readBinaryValue(ctxt.getBase64Variant(), out);
        out.close();
        return intoValue;
    }
}

