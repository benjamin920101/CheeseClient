/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBase;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class PlaceholderForType
extends TypeBase {
    private static final long serialVersionUID = 1L;
    protected final int _ordinal;
    protected JavaType _actualType;

    public PlaceholderForType(int ordinal) {
        super(Object.class, TypeBindings.emptyBindings(), TypeFactory.unknownType(), null, 1, null, null, false);
        this._ordinal = ordinal;
    }

    public JavaType actualType() {
        return this._actualType;
    }

    public void actualType(JavaType t2) {
        this._actualType = t2;
    }

    @Override
    protected String buildCanonicalName() {
        return this.toString();
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb2) {
        return this.getErasedSignature(sb2);
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb2) {
        sb2.append('$').append(this._ordinal + 1);
        return sb2;
    }

    @Override
    public JavaType withTypeHandler(Object h2) {
        return (JavaType)this._unsupported();
    }

    @Override
    public JavaType withContentTypeHandler(Object h2) {
        return (JavaType)this._unsupported();
    }

    @Override
    public JavaType withValueHandler(Object h2) {
        return (JavaType)this._unsupported();
    }

    @Override
    public JavaType withContentValueHandler(Object h2) {
        return (JavaType)this._unsupported();
    }

    @Override
    public JavaType withContentType(JavaType contentType) {
        return (JavaType)this._unsupported();
    }

    @Override
    public JavaType withStaticTyping() {
        return (JavaType)this._unsupported();
    }

    @Override
    public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return (JavaType)this._unsupported();
    }

    @Override
    protected JavaType _narrow(Class<?> subclass) {
        return (JavaType)this._unsupported();
    }

    @Override
    public boolean isContainerType() {
        return false;
    }

    @Override
    public String toString() {
        return this.getErasedSignature(new StringBuilder()).toString();
    }

    @Override
    public boolean equals(Object o2) {
        return o2 == this;
    }

    private <T> T _unsupported() {
        throw new UnsupportedOperationException("Operation should not be attempted on " + this.getClass().getName());
    }
}

