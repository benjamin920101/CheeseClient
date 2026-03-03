/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumSet;

public class EnumSetDeserializer
extends StdDeserializer<EnumSet<?>>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected final JavaType _enumType;
    protected final Class<Enum> _enumClass;
    protected JsonDeserializer<Enum<?>> _enumDeserializer;
    protected final NullValueProvider _nullProvider;
    protected final boolean _skipNullValues;
    protected final Boolean _unwrapSingle;

    public EnumSetDeserializer(JavaType enumType, JsonDeserializer<?> deser) {
        super(EnumSet.class);
        this._enumType = enumType;
        this._enumClass = enumType.getRawClass();
        if (!ClassUtil.isEnumType(this._enumClass)) {
            throw new IllegalArgumentException("Type " + enumType + " not Java Enum type");
        }
        this._enumDeserializer = deser;
        this._unwrapSingle = null;
        this._nullProvider = null;
        this._skipNullValues = false;
    }

    @Deprecated
    protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer<?> deser, Boolean unwrapSingle) {
        this(base, deser, base._nullProvider, unwrapSingle);
    }

    protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer<?> deser, NullValueProvider nuller, Boolean unwrapSingle) {
        super(base);
        this._enumType = base._enumType;
        this._enumClass = base._enumClass;
        this._enumDeserializer = deser;
        this._nullProvider = nuller;
        this._skipNullValues = NullsConstantProvider.isSkipper(nuller);
        this._unwrapSingle = unwrapSingle;
    }

    public EnumSetDeserializer withDeserializer(JsonDeserializer<?> deser) {
        if (this._enumDeserializer == deser) {
            return this;
        }
        return new EnumSetDeserializer(this, deser, this._nullProvider, this._unwrapSingle);
    }

    @Deprecated
    public EnumSetDeserializer withResolved(JsonDeserializer<?> deser, Boolean unwrapSingle) {
        return this.withResolved(deser, this._nullProvider, unwrapSingle);
    }

    public EnumSetDeserializer withResolved(JsonDeserializer<?> deser, NullValueProvider nuller, Boolean unwrapSingle) {
        if (this._unwrapSingle == unwrapSingle && this._enumDeserializer == deser && this._nullProvider == deser) {
            return this;
        }
        return new EnumSetDeserializer(this, deser, nuller, unwrapSingle);
    }

    @Override
    public boolean isCachable() {
        return this._enumType.getValueHandler() == null;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.TRUE;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return this.constructSet();
    }

    @Override
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonDeserializer<Object> deser = this._enumDeserializer;
        deser = deser == null ? ctxt.findContextualValueDeserializer(this._enumType, property) : ctxt.handleSecondaryContextualization(deser, property, this._enumType);
        return this.withResolved(deser, this.findContentNullProvider(ctxt, property, deser), unwrapSingle);
    }

    @Override
    public EnumSet<?> deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        EnumSet result = this.constructSet();
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt, result);
        }
        return this._deserialize(p2, ctxt, result);
    }

    @Override
    public EnumSet<?> deserialize(JsonParser p2, DeserializationContext ctxt, EnumSet<?> result) throws IOException {
        if (!p2.isExpectedStartArrayToken()) {
            return this.handleNonArray(p2, ctxt, result);
        }
        return this._deserialize(p2, ctxt, result);
    }

    protected final EnumSet<?> _deserialize(JsonParser p2, DeserializationContext ctxt, EnumSet result) throws IOException {
        try {
            JsonToken t2;
            while ((t2 = p2.nextToken()) != JsonToken.END_ARRAY) {
                Enum value;
                if (t2 == JsonToken.VALUE_NULL) {
                    if (this._skipNullValues) continue;
                    value = (Enum)this._nullProvider.getNullValue(ctxt);
                } else {
                    value = this._enumDeserializer.deserialize(p2, ctxt);
                }
                if (value == null) continue;
                result.add(value);
            }
        }
        catch (Exception e2) {
            throw JsonMappingException.wrapWithPath((Throwable)e2, (Object)result, result.size());
        }
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(p2, ctxt);
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }

    protected EnumSet<?> handleNonArray(JsonParser p2, DeserializationContext ctxt, EnumSet result) throws IOException {
        boolean canWrap;
        boolean bl2 = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (!canWrap) {
            return (EnumSet)ctxt.handleUnexpectedToken(EnumSet.class, p2);
        }
        if (p2.hasToken(JsonToken.VALUE_NULL)) {
            return (EnumSet)ctxt.handleUnexpectedToken(this._enumClass, p2);
        }
        try {
            Enum<?> value = this._enumDeserializer.deserialize(p2, ctxt);
            if (value != null) {
                result.add(value);
            }
        }
        catch (Exception e2) {
            throw JsonMappingException.wrapWithPath((Throwable)e2, (Object)result, result.size());
        }
        return result;
    }
}

