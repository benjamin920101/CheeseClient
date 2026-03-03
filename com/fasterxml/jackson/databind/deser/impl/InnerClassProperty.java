/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.reflect.Constructor;

public final class InnerClassProperty
extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1L;
    protected final transient Constructor<?> _creator;
    protected AnnotatedConstructor _annotated;

    public InnerClassProperty(SettableBeanProperty delegate, Constructor<?> ctor) {
        super(delegate);
        this._creator = ctor;
    }

    protected InnerClassProperty(SettableBeanProperty src, AnnotatedConstructor ann2) {
        super(src);
        this._annotated = ann2;
        Constructor<?> constructor = this._creator = this._annotated == null ? null : this._annotated.getAnnotated();
        if (this._creator == null) {
            throw new IllegalArgumentException("Missing constructor (broken JDK (de)serialization?)");
        }
    }

    @Override
    protected SettableBeanProperty withDelegate(SettableBeanProperty d2) {
        if (d2 == this.delegate) {
            return this;
        }
        return new InnerClassProperty(d2, this._creator);
    }

    @Override
    public void deserializeAndSet(JsonParser p2, DeserializationContext ctxt, Object bean) throws IOException {
        Object value;
        JsonToken t2 = p2.currentToken();
        if (t2 == JsonToken.VALUE_NULL) {
            value = this._valueDeserializer.getNullValue(ctxt);
        } else if (this._valueTypeDeserializer != null) {
            value = this._valueDeserializer.deserializeWithType(p2, ctxt, this._valueTypeDeserializer);
        } else {
            try {
                value = this._creator.newInstance(bean);
            }
            catch (Exception e2) {
                ClassUtil.unwrapAndThrowAsIAE(e2, String.format("Failed to instantiate class %s, problem: %s", this._creator.getDeclaringClass().getName(), e2.getMessage()));
                value = null;
            }
            this._valueDeserializer.deserialize(p2, ctxt, value);
        }
        this.set(bean, value);
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser p2, DeserializationContext ctxt, Object instance) throws IOException {
        return this.setAndReturn(instance, this.deserialize(p2, ctxt));
    }

    Object readResolve() {
        return new InnerClassProperty((SettableBeanProperty)this, this._annotated);
    }

    Object writeReplace() {
        if (this._annotated == null) {
            return new InnerClassProperty((SettableBeanProperty)this, new AnnotatedConstructor(null, this._creator, null, null));
        }
        return this;
    }
}

