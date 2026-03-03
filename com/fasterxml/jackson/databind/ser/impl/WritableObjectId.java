/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import java.io.IOException;

public final class WritableObjectId {
    public final ObjectIdGenerator<?> generator;
    public Object id;
    protected boolean idWritten = false;

    public WritableObjectId(ObjectIdGenerator<?> generator) {
        this.generator = generator;
    }

    public boolean writeAsId(JsonGenerator gen, SerializerProvider provider, ObjectIdWriter w2) throws IOException {
        if (this.id != null && (this.idWritten || w2.alwaysAsId)) {
            if (gen.canWriteObjectId()) {
                gen.writeObjectRef(String.valueOf(this.id));
            } else {
                w2.serializer.serialize(this.id, gen, provider);
            }
            return true;
        }
        return false;
    }

    public Object generateId(Object forPojo) {
        if (this.id == null) {
            this.id = this.generator.generateId(forPojo);
        }
        return this.id;
    }

    public void writeAsField(JsonGenerator gen, SerializerProvider provider, ObjectIdWriter w2) throws IOException {
        this.idWritten = true;
        if (gen.canWriteObjectId()) {
            String idStr = this.id == null ? null : String.valueOf(this.id);
            gen.writeObjectId(idStr);
            return;
        }
        SerializableString name = w2.propertyName;
        if (name != null) {
            gen.writeFieldName(name);
            w2.serializer.serialize(this.id, gen, provider);
        }
    }
}

