/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer
extends ContainerDeserializerBase<Object[]>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected static final Object[] NO_OBJECTS = new Object[0];
    protected final boolean _untyped;
    protected final Class<?> _elementClass;
    protected JsonDeserializer<Object> _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;

    public ObjectArrayDeserializer(JavaType arrayType, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser) {
        super(arrayType, null, null);
        this._elementClass = arrayType.getContentType().getRawClass();
        this._untyped = this._elementClass == Object.class;
        this._elementDeserializer = elemDeser;
        this._elementTypeDeserializer = elemTypeDeser;
    }

    protected ObjectArrayDeserializer(ObjectArrayDeserializer base, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        super(base, nuller, unwrapSingle);
        this._elementClass = base._elementClass;
        this._untyped = base._untyped;
        this._elementDeserializer = elemDeser;
        this._elementTypeDeserializer = elemTypeDeser;
    }

    public ObjectArrayDeserializer withDeserializer(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser) {
        return this.withResolved(elemTypeDeser, elemDeser, this._nullProvider, this._unwrapSingle);
    }

    public ObjectArrayDeserializer withResolved(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser, NullValueProvider nuller, Boolean unwrapSingle) {
        if (unwrapSingle == this._unwrapSingle && nuller == this._nullProvider && elemDeser == this._elementDeserializer && elemTypeDeser == this._elementTypeDeserializer) {
            return this;
        }
        return new ObjectArrayDeserializer(this, elemDeser, elemTypeDeser, nuller, unwrapSingle);
    }

    @Override
    public boolean isCachable() {
        return this._elementDeserializer == null && this._elementTypeDeserializer == null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<Object> valueDeser = this._elementDeserializer;
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, this._containerType.getRawClass(), JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        valueDeser = this.findConvertingContentDeserializer(ctxt, property, valueDeser);
        JavaType vt2 = this._containerType.getContentType();
        valueDeser = valueDeser == null ? ctxt.findContextualValueDeserializer(vt2, property) : ctxt.handleSecondaryContextualization(valueDeser, property, vt2);
        TypeDeserializer elemTypeDeser = this._elementTypeDeserializer;
        if (elemTypeDeser != null) {
            elemTypeDeser = elemTypeDeser.forProperty(property);
        }
        NullValueProvider nuller = this.findContentNullProvider(ctxt, property, valueDeser);
        return this.withResolved(elemTypeDeser, valueDeser, nuller, unwrapSingle);
    }

    @Override
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._elementDeserializer;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return NO_OBJECTS;
    }

    @Override
    public Object[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        int ix2 = 0;
        TypeDeserializer typeDeser = this._elementTypeDeserializer;
        try {
            JsonToken t2;
            while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                Object value;
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = this._nullProvider.getNullValue(ctxt);
                } else {
                    value = typeDeser == null ? this._elementDeserializer.deserialize(p2, ctxt) : this._elementDeserializer.deserializeWithType(p2, ctxt, typeDeser);
                }
                if (ix2 >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix2 = 0;
                }
                chunk[ix2++] = value;
            }
        }
        catch (Exception e2) {
            throw JsonMappingException.wrapWithPath((Throwable)e2, (Object)chunk, buffer.bufferedSize() + ix2);
        }
        Object[] result = this._untyped ? buffer.completeAndClearBuffer(chunk, ix2) : buffer.completeAndClearBuffer(chunk, ix2, this._elementClass);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    public Object[] deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return (Object[])typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }

    @Override
    public Object[] deserialize(JsonParser p2, DeserializationContext ctxt, Object[] intoValue) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            Object[] arr2 = this.handleNonArray(p2, ctxt);
            if (arr2 == null) {
                return intoValue;
            }
            int offset = intoValue.length;
            Object[] result = new Object[offset + arr2.length];
            System.arraycopy(intoValue, 0, result, 0, offset);
            System.arraycopy(arr2, 0, result, offset, arr2.length);
            return result;
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        int ix2 = intoValue.length;
        Object[] chunk = buffer.resetAndStart(intoValue, ix2);
        TypeDeserializer typeDeser = this._elementTypeDeserializer;
        try {
            JsonToken t2;
            while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                Object value;
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = this._nullProvider.getNullValue(ctxt);
                } else {
                    value = typeDeser == null ? this._elementDeserializer.deserialize(p2, ctxt) : this._elementDeserializer.deserializeWithType(p2, ctxt, typeDeser);
                }
                if (ix2 >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix2 = 0;
                }
                chunk[ix2++] = value;
            }
        }
        catch (Exception e2) {
            throw JsonMappingException.wrapWithPath((Throwable)e2, (Object)chunk, buffer.bufferedSize() + ix2);
        }
        Object[] result = this._untyped ? buffer.completeAndClearBuffer(chunk, ix2) : buffer.completeAndClearBuffer(chunk, ix2, this._elementClass);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    protected Byte[] deserializeFromBase64(JsonParser p2, DeserializationContext ctxt) throws IOException {
        byte[] b2 = p2.getBinaryValue(ctxt.getBase64Variant());
        Byte[] result = new Byte[b2.length];
        int len = b2.length;
        for (int i2 = 0; i2 < len; ++i2) {
            result[i2] = b2[i2];
        }
        return result;
    }

    protected Object[] handleNonArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object value;
        boolean canWrap;
        String str;
        if (p2.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (str = p2.getText()).length() == 0) {
            return null;
        }
        boolean bl2 = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (!canWrap) {
            if (p2.hasToken(JsonToken.VALUE_STRING) && this._elementClass == Byte.class) {
                return this.deserializeFromBase64(p2, ctxt);
            }
            return (Object[])ctxt.handleUnexpectedToken(this._containerType.getRawClass(), p2);
        }
        if (p2.hasToken(JsonToken.VALUE_NULL)) {
            if (this._skipNullValues) {
                return NO_OBJECTS;
            }
            value = this._nullProvider.getNullValue(ctxt);
        } else {
            value = this._elementTypeDeserializer == null ? this._elementDeserializer.deserialize(p2, ctxt) : this._elementDeserializer.deserializeWithType(p2, ctxt, this._elementTypeDeserializer);
        }
        Object[] result = this._untyped ? new Object[1] : (Object[])Array.newInstance(this._elementClass, 1);
        result[0] = value;
        return result;
    }
}

