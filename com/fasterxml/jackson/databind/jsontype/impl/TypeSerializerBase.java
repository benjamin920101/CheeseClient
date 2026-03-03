/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeSerializerBase
extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeSerializerBase(TypeIdResolver idRes, BeanProperty property) {
        this._idResolver = idRes;
        this._property = property;
    }

    @Override
    public abstract JsonTypeInfo.As getTypeInclusion();

    @Override
    public String getPropertyName() {
        return null;
    }

    @Override
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    @Override
    public WritableTypeId writeTypePrefix(JsonGenerator g2, WritableTypeId idMetadata) throws IOException {
        this._generateTypeId(idMetadata);
        return g2.writeTypePrefix(idMetadata);
    }

    @Override
    public WritableTypeId writeTypeSuffix(JsonGenerator g2, WritableTypeId idMetadata) throws IOException {
        return g2.writeTypeSuffix(idMetadata);
    }

    protected void _generateTypeId(WritableTypeId idMetadata) {
        Object id2 = idMetadata.id;
        if (id2 == null) {
            Object value = idMetadata.forValue;
            Class<?> typeForId = idMetadata.forValueType;
            id2 = typeForId == null ? this.idFromValue(value) : this.idFromValueAndType(value, typeForId);
            idMetadata.id = id2;
        }
    }

    protected String idFromValue(Object value) {
        String id2 = this._idResolver.idFromValue(value);
        if (id2 == null) {
            this.handleMissingId(value);
        }
        return id2;
    }

    protected String idFromValueAndType(Object value, Class<?> type) {
        String id2 = this._idResolver.idFromValueAndType(value, type);
        if (id2 == null) {
            this.handleMissingId(value);
        }
        return id2;
    }

    protected void handleMissingId(Object value) {
    }
}

