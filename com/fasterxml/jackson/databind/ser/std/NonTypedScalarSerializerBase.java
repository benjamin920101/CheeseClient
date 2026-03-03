/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;

@Deprecated
public abstract class NonTypedScalarSerializerBase<T>
extends StdScalarSerializer<T> {
    protected NonTypedScalarSerializerBase(Class<T> t2) {
        super(t2);
    }

    protected NonTypedScalarSerializerBase(Class<?> t2, boolean bogus) {
        super(t2, bogus);
    }

    @Override
    public final void serializeWithType(T value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }
}

