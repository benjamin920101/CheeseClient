/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

public class AsPropertyTypeDeserializer
extends AsArrayTypeDeserializer {
    private static final long serialVersionUID = 1L;
    protected final JsonTypeInfo.As _inclusion;

    public AsPropertyTypeDeserializer(JavaType bt2, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        this(bt2, idRes, typePropertyName, typeIdVisible, defaultImpl, JsonTypeInfo.As.PROPERTY);
    }

    public AsPropertyTypeDeserializer(JavaType bt2, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl, JsonTypeInfo.As inclusion) {
        super(bt2, idRes, typePropertyName, typeIdVisible, defaultImpl);
        this._inclusion = inclusion;
    }

    public AsPropertyTypeDeserializer(AsPropertyTypeDeserializer src, BeanProperty property) {
        super(src, property);
        this._inclusion = src._inclusion;
    }

    @Override
    public TypeDeserializer forProperty(BeanProperty prop) {
        return prop == this._property ? this : new AsPropertyTypeDeserializer(this, prop);
    }

    @Override
    public JsonTypeInfo.As getTypeInclusion() {
        return this._inclusion;
    }

    @Override
    public Object deserializeTypedFromObject(JsonParser p2, DeserializationContext ctxt) throws IOException {
        Object typeId;
        if (p2.canReadTypeId() && (typeId = p2.getTypeId()) != null) {
            return this._deserializeWithNativeTypeId(p2, ctxt, typeId);
        }
        JsonToken t2 = p2.currentToken();
        if (t2 == JsonToken.START_OBJECT) {
            t2 = p2.nextToken();
        } else if (t2 != JsonToken.FIELD_NAME) {
            return this._deserializeTypedUsingDefaultImpl(p2, ctxt, null);
        }
        TokenBuffer tb = null;
        while (t2 == JsonToken.FIELD_NAME) {
            String name = p2.getCurrentName();
            p2.nextToken();
            if (name.equals(this._typePropertyName)) {
                return this._deserializeTypedForId(p2, ctxt, tb);
            }
            if (tb == null) {
                tb = new TokenBuffer(p2, ctxt);
            }
            tb.writeFieldName(name);
            tb.copyCurrentStructure(p2);
            t2 = p2.nextToken();
        }
        return this._deserializeTypedUsingDefaultImpl(p2, ctxt, tb);
    }

    protected Object _deserializeTypedForId(JsonParser p2, DeserializationContext ctxt, TokenBuffer tb) throws IOException {
        String typeId = p2.getText();
        JsonDeserializer<Object> deser = this._findDeserializer(ctxt, typeId);
        if (this._typeIdVisible) {
            if (tb == null) {
                tb = new TokenBuffer(p2, ctxt);
            }
            tb.writeFieldName(p2.getCurrentName());
            tb.writeString(typeId);
        }
        if (tb != null) {
            p2.clearCurrentToken();
            p2 = JsonParserSequence.createFlattened(false, tb.asParser(p2), p2);
        }
        p2.nextToken();
        return deser.deserialize(p2, ctxt);
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser p2, DeserializationContext ctxt, TokenBuffer tb) throws IOException {
        JsonDeserializer<Object> deser = this._findDefaultImplDeserializer(ctxt);
        if (deser == null) {
            JavaType t2;
            String str;
            Object result = TypeDeserializer.deserializeIfNatural(p2, ctxt, this._baseType);
            if (result != null) {
                return result;
            }
            if (p2.isExpectedStartArrayToken()) {
                return super.deserializeTypedFromAny(p2, ctxt);
            }
            if (p2.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (str = p2.getText().trim()).isEmpty()) {
                return null;
            }
            String msg = String.format("missing type id property '%s'", this._typePropertyName);
            if (this._property != null) {
                msg = String.format("%s (for POJO property '%s')", msg, this._property.getName());
            }
            if ((t2 = this._handleMissingTypeId(ctxt, msg)) == null) {
                return null;
            }
            deser = ctxt.findContextualValueDeserializer(t2, this._property);
        }
        if (tb != null) {
            tb.writeEndObject();
            p2 = tb.asParser(p2);
            p2.nextToken();
        }
        return deser.deserialize(p2, ctxt);
    }

    @Override
    public Object deserializeTypedFromAny(JsonParser p2, DeserializationContext ctxt) throws IOException {
        if (p2.hasToken(JsonToken.START_ARRAY)) {
            return super.deserializeTypedFromArray(p2, ctxt);
        }
        return this.deserializeTypedFromObject(p2, ctxt);
    }
}

