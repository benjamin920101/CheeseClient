/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;

public abstract class StdScalarDeserializer<T>
extends StdDeserializer<T> {
    private static final long serialVersionUID = 1L;

    protected StdScalarDeserializer(Class<?> vc2) {
        super(vc2);
    }

    protected StdScalarDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected StdScalarDeserializer(StdScalarDeserializer<?> src) {
        super(src);
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromScalar(p2, ctxt);
    }

    @Override
    public T deserialize(JsonParser p2, DeserializationContext ctxt, T intoValue) throws IOException {
        ctxt.handleBadMerge(this);
        return this.deserialize(p2, ctxt);
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.FALSE;
    }

    @Override
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.ALWAYS_NULL;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }
}

