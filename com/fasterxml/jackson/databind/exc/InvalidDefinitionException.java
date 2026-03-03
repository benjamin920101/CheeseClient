/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class InvalidDefinitionException
extends JsonMappingException {
    protected final JavaType _type;
    protected transient BeanDescription _beanDesc;
    protected transient BeanPropertyDefinition _property;

    protected InvalidDefinitionException(JsonParser p2, String msg, JavaType type) {
        super(p2, msg);
        this._type = type;
        this._beanDesc = null;
        this._property = null;
    }

    protected InvalidDefinitionException(JsonGenerator g2, String msg, JavaType type) {
        super(g2, msg);
        this._type = type;
        this._beanDesc = null;
        this._property = null;
    }

    protected InvalidDefinitionException(JsonParser p2, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        super(p2, msg);
        this._type = bean == null ? null : bean.getType();
        this._beanDesc = bean;
        this._property = prop;
    }

    protected InvalidDefinitionException(JsonGenerator g2, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        super(g2, msg);
        this._type = bean == null ? null : bean.getType();
        this._beanDesc = bean;
        this._property = prop;
    }

    public static InvalidDefinitionException from(JsonParser p2, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        return new InvalidDefinitionException(p2, msg, bean, prop);
    }

    public static InvalidDefinitionException from(JsonParser p2, String msg, JavaType type) {
        return new InvalidDefinitionException(p2, msg, type);
    }

    public static InvalidDefinitionException from(JsonGenerator g2, String msg, BeanDescription bean, BeanPropertyDefinition prop) {
        return new InvalidDefinitionException(g2, msg, bean, prop);
    }

    public static InvalidDefinitionException from(JsonGenerator g2, String msg, JavaType type) {
        return new InvalidDefinitionException(g2, msg, type);
    }

    public JavaType getType() {
        return this._type;
    }

    public BeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public BeanPropertyDefinition getProperty() {
        return this._property;
    }
}

