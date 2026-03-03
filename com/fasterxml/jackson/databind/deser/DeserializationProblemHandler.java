/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public abstract class DeserializationProblemHandler {
    public static final Object NOT_HANDLED = new Object();

    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p2, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
        return false;
    }

    public Object handleWeirdKey(DeserializationContext ctxt, Class<?> rawKeyType, String keyValue, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdStringValue(DeserializationContext ctxt, Class<?> targetType, String valueToConvert, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdNumberValue(DeserializationContext ctxt, Class<?> targetType, Number valueToConvert, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdNativeValue(DeserializationContext ctxt, JavaType targetType, Object valueToConvert, JsonParser p2) throws IOException {
        return NOT_HANDLED;
    }

    @Deprecated
    public Object handleUnexpectedToken(DeserializationContext ctxt, Class<?> targetType, JsonToken t2, JsonParser p2, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleUnexpectedToken(DeserializationContext ctxt, JavaType targetType, JsonToken t2, JsonParser p2, String failureMsg) throws IOException {
        return this.handleUnexpectedToken(ctxt, targetType.getRawClass(), t2, p2, failureMsg);
    }

    public Object handleInstantiationProblem(DeserializationContext ctxt, Class<?> instClass, Object argument, Throwable t2) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleMissingInstantiator(DeserializationContext ctxt, Class<?> instClass, ValueInstantiator valueInsta, JsonParser p2, String msg) throws IOException {
        return this.handleMissingInstantiator(ctxt, instClass, p2, msg);
    }

    public JavaType handleUnknownTypeId(DeserializationContext ctxt, JavaType baseType, String subTypeId, TypeIdResolver idResolver, String failureMsg) throws IOException {
        return null;
    }

    public JavaType handleMissingTypeId(DeserializationContext ctxt, JavaType baseType, TypeIdResolver idResolver, String failureMsg) throws IOException {
        return null;
    }

    @Deprecated
    public Object handleMissingInstantiator(DeserializationContext ctxt, Class<?> instClass, JsonParser p2, String msg) throws IOException {
        return NOT_HANDLED;
    }
}

