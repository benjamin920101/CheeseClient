/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

@JacksonStdImpl
public class MapEntryDeserializer
extends ContainerDeserializerBase<Map.Entry<Object, Object>>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected final KeyDeserializer _keyDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapEntryDeserializer(JavaType type, KeyDeserializer keyDeser, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser) {
        super(type);
        if (type.containedTypeCount() != 2) {
            throw new IllegalArgumentException("Missing generic type information for " + type);
        }
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
    }

    protected MapEntryDeserializer(MapEntryDeserializer src) {
        super(src);
        this._keyDeserializer = src._keyDeserializer;
        this._valueDeserializer = src._valueDeserializer;
        this._valueTypeDeserializer = src._valueTypeDeserializer;
    }

    protected MapEntryDeserializer(MapEntryDeserializer src, KeyDeserializer keyDeser, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser) {
        super(src);
        this._keyDeserializer = keyDeser;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
    }

    protected MapEntryDeserializer withResolved(KeyDeserializer keyDeser, TypeDeserializer valueTypeDeser, JsonDeserializer<?> valueDeser) {
        if (this._keyDeserializer == keyDeser && this._valueDeserializer == valueDeser && this._valueTypeDeserializer == valueTypeDeser) {
            return this;
        }
        return new MapEntryDeserializer(this, keyDeser, valueDeser, valueTypeDeser);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        KeyDeserializer kd = this._keyDeserializer;
        if (kd == null) {
            kd = ctxt.findKeyDeserializer(this._containerType.containedType(0), property);
        } else if (kd instanceof ContextualKeyDeserializer) {
            kd = ((ContextualKeyDeserializer)((Object)kd)).createContextual(ctxt, property);
        }
        JsonDeserializer<Object> vd2 = this._valueDeserializer;
        vd2 = this.findConvertingContentDeserializer(ctxt, property, vd2);
        JavaType contentType = this._containerType.containedType(1);
        vd2 = vd2 == null ? ctxt.findContextualValueDeserializer(contentType, property) : ctxt.handleSecondaryContextualization(vd2, property, contentType);
        TypeDeserializer vtd = this._valueTypeDeserializer;
        if (vtd != null) {
            vtd = vtd.forProperty(property);
        }
        return this.withResolved(kd, vtd, vd2);
    }

    @Override
    public JavaType getContentType() {
        return this._containerType.containedType(1);
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override
    public Map.Entry<Object, Object> deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.currentToken();
        if (t2 != JsonToken.START_OBJECT && t2 != JsonToken.FIELD_NAME && t2 != JsonToken.END_OBJECT) {
            return (Map.Entry)this._deserializeFromEmpty(p2, ctxt);
        }
        if (t2 == JsonToken.START_OBJECT) {
            t2 = p2.nextToken();
        }
        if (t2 != JsonToken.FIELD_NAME) {
            if (t2 == JsonToken.END_OBJECT) {
                return (Map.Entry)ctxt.reportInputMismatch(this, "Cannot deserialize a Map.Entry out of empty JSON Object", new Object[0]);
            }
            return (Map.Entry)ctxt.handleUnexpectedToken(this.handledType(), p2);
        }
        KeyDeserializer keyDes = this._keyDeserializer;
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        String keyStr = p2.getCurrentName();
        Object key = keyDes.deserializeKey(keyStr, ctxt);
        Object value = null;
        t2 = p2.nextToken();
        try {
            value = t2 == JsonToken.VALUE_NULL ? valueDes.getNullValue(ctxt) : (typeDeser == null ? valueDes.deserialize(p2, ctxt) : valueDes.deserializeWithType(p2, ctxt, typeDeser));
        }
        catch (Exception e2) {
            this.wrapAndThrow(e2, Map.Entry.class, keyStr);
        }
        t2 = p2.nextToken();
        if (t2 != JsonToken.END_OBJECT) {
            if (t2 == JsonToken.FIELD_NAME) {
                ctxt.reportInputMismatch(this, "Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '%s')", p2.getCurrentName());
            } else {
                ctxt.reportInputMismatch(this, "Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + (Object)((Object)t2), new Object[0]);
            }
            return null;
        }
        return new AbstractMap.SimpleEntry<Object, Object>(key, value);
    }

    @Override
    public Map.Entry<Object, Object> deserialize(JsonParser p2, DeserializationContext ctxt, Map.Entry<Object, Object> result) throws IOException {
        throw new IllegalStateException("Cannot update Map.Entry values");
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(p2, ctxt);
    }
}

