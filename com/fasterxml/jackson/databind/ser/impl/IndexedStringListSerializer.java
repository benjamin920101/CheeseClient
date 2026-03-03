/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer
extends StaticListSerializerBase<List<String>> {
    private static final long serialVersionUID = 1L;
    public static final IndexedStringListSerializer instance = new IndexedStringListSerializer();

    protected IndexedStringListSerializer() {
        super(List.class);
    }

    public IndexedStringListSerializer(IndexedStringListSerializer src, Boolean unwrapSingle) {
        super(src, unwrapSingle);
    }

    @Override
    public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
        return new IndexedStringListSerializer(this, unwrapSingle);
    }

    @Override
    protected JsonNode contentSchema() {
        return this.createSchemaNode("string", true);
    }

    @Override
    protected void acceptContentVisitor(JsonArrayFormatVisitor visitor) throws JsonMappingException {
        visitor.itemsFormat(JsonFormatTypes.STRING);
    }

    @Override
    public void serialize(List<String> value, JsonGenerator g2, SerializerProvider provider) throws IOException {
        int len = value.size();
        if (len == 1 && (this._unwrapSingle == null && provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || this._unwrapSingle == Boolean.TRUE)) {
            this.serializeContents(value, g2, provider, 1);
            return;
        }
        g2.writeStartArray(value, len);
        this.serializeContents(value, g2, provider, len);
        g2.writeEndArray();
    }

    @Override
    public void serializeWithType(List<String> value, JsonGenerator g2, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g2, typeSer.typeId(value, JsonToken.START_ARRAY));
        g2.setCurrentValue(value);
        this.serializeContents(value, g2, provider, value.size());
        typeSer.writeTypeSuffix(g2, typeIdDef);
    }

    private final void serializeContents(List<String> value, JsonGenerator g2, SerializerProvider provider, int len) throws IOException {
        int i2;
        try {
            for (i2 = 0; i2 < len; ++i2) {
                String str = value.get(i2);
                if (str == null) {
                    provider.defaultSerializeNull(g2);
                    continue;
                }
                g2.writeString(str);
            }
        }
        catch (Exception e2) {
            this.wrapAndThrow(provider, (Throwable)e2, value, i2);
        }
    }
}

