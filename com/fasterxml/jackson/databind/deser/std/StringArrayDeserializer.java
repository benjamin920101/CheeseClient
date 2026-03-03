/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer
extends StdDeserializer<String[]>
implements ContextualDeserializer {
    private static final long serialVersionUID = 2L;
    private static final String[] NO_STRINGS = new String[0];
    public static final StringArrayDeserializer instance = new StringArrayDeserializer();
    protected JsonDeserializer<String> _elementDeserializer;
    protected final NullValueProvider _nullProvider;
    protected final Boolean _unwrapSingle;
    protected final boolean _skipNullValues;

    public StringArrayDeserializer() {
        this(null, null, null);
    }

    protected StringArrayDeserializer(JsonDeserializer<?> deser, NullValueProvider nuller, Boolean unwrapSingle) {
        super(String[].class);
        this._elementDeserializer = deser;
        this._nullProvider = nuller;
        this._unwrapSingle = unwrapSingle;
        this._skipNullValues = NullsConstantProvider.isSkipper(nuller);
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.TRUE;
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return NO_STRINGS;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<Object> deser = this._elementDeserializer;
        deser = this.findConvertingContentDeserializer(ctxt, property, deser);
        JavaType type = ctxt.constructType(String.class);
        deser = deser == null ? ctxt.findContextualValueDeserializer(type, property) : ctxt.handleSecondaryContextualization(deser, property, type);
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, String[].class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        NullValueProvider nuller = this.findContentNullProvider(ctxt, property, deser);
        if (deser != null && this.isDefaultDeserializer(deser)) {
            deser = null;
        }
        if (this._elementDeserializer == deser && this._unwrapSingle == unwrapSingle && this._nullProvider == nuller) {
            return this;
        }
        return new StringArrayDeserializer(deser, nuller, unwrapSingle);
    }

    @Override
    public String[] deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt);
        }
        if (this._elementDeserializer != null) {
            return this._deserializeCustom(p2, ctxt, null);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        int ix2 = 0;
        try {
            while (true) {
                String value;
                if ((value = p2.nextTextValue()) == null) {
                    JsonToken t2 = p2.getCurrentToken();
                    if (t2 == JsonToken.END_ARRAY) break;
                    if (t2 == JsonToken.VALUE_NULL) {
                        if (this._skipNullValues) continue;
                        value = (String)this._nullProvider.getNullValue(ctxt);
                    } else {
                        value = this._parseString(p2, ctxt);
                    }
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
        String[] result = buffer.completeAndClearBuffer(chunk, ix2, String.class);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    protected final String[] _deserializeCustom(JsonParser p2, DeserializationContext ctxt, String[] old) throws IOException {
        Object[] chunk;
        int ix2;
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        if (old == null) {
            ix2 = 0;
            chunk = buffer.resetAndStart();
        } else {
            ix2 = old.length;
            chunk = buffer.resetAndStart(old, ix2);
        }
        JsonDeserializer<String> deser = this._elementDeserializer;
        try {
            while (true) {
                String value;
                if (p2.nextTextValue() == null) {
                    JsonToken t2 = p2.getCurrentToken();
                    if (t2 == JsonToken.END_ARRAY) break;
                    if (t2 == JsonToken.VALUE_NULL) {
                        if (this._skipNullValues) continue;
                        value = (String)this._nullProvider.getNullValue(ctxt);
                    } else {
                        value = deser.deserialize(p2, ctxt);
                    }
                } else {
                    value = deser.deserialize(p2, ctxt);
                }
                if (ix2 >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix2 = 0;
                }
                chunk[ix2++] = value;
            }
        }
        catch (Exception e2) {
            throw JsonMappingException.wrapWithPath((Throwable)e2, String.class, ix2);
        }
        String[] result = buffer.completeAndClearBuffer(chunk, ix2, String.class);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }

    @Override
    public String[] deserialize(JsonParser p2, DeserializationContext ctxt, String[] intoValue) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            String[] arr2 = this.handleNonArray(p2, ctxt);
            if (arr2 == null) {
                return intoValue;
            }
            int offset = intoValue.length;
            String[] result = new String[offset + arr2.length];
            System.arraycopy(intoValue, 0, result, 0, offset);
            System.arraycopy(arr2, 0, result, offset, arr2.length);
            return result;
        }
        if (this._elementDeserializer != null) {
            return this._deserializeCustom(p2, ctxt, intoValue);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        int ix2 = intoValue.length;
        Object[] chunk = buffer.resetAndStart(intoValue, ix2);
        try {
            while (true) {
                String value;
                if ((value = p2.nextTextValue()) == null) {
                    JsonToken t2 = p2.getCurrentToken();
                    if (t2 == JsonToken.END_ARRAY) break;
                    if (t2 == JsonToken.VALUE_NULL) {
                        if (this._skipNullValues) {
                            return NO_STRINGS;
                        }
                        value = (String)this._nullProvider.getNullValue(ctxt);
                    } else {
                        value = this._parseString(p2, ctxt);
                    }
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
        String[] result = buffer.completeAndClearBuffer(chunk, ix2, String.class);
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    private final String[] handleNonArray(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String str;
        boolean canWrap;
        boolean bl2 = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (canWrap) {
            String value = p2.hasToken(JsonToken.VALUE_NULL) ? (String)this._nullProvider.getNullValue(ctxt) : this._parseString(p2, ctxt);
            return new String[]{value};
        }
        if (p2.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (str = p2.getText()).length() == 0) {
            return null;
        }
        return (String[])ctxt.handleUnexpectedToken(this._valueClass, p2);
    }
}

