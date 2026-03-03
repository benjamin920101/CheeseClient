/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBase;
import com.fasterxml.jackson.databind.type.TypeBindings;

public class ResolvedRecursiveType
extends TypeBase {
    private static final long serialVersionUID = 1L;
    protected JavaType _referencedType;

    public ResolvedRecursiveType(Class<?> erasedType, TypeBindings bindings) {
        super(erasedType, bindings, null, null, 0, null, null, false);
    }

    public void setReference(JavaType ref) {
        if (this._referencedType != null) {
            throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + ref);
        }
        this._referencedType = ref;
    }

    @Override
    public JavaType getSuperClass() {
        if (this._referencedType != null) {
            return this._referencedType.getSuperClass();
        }
        return super.getSuperClass();
    }

    public JavaType getSelfReferencedType() {
        return this._referencedType;
    }

    @Override
    public TypeBindings getBindings() {
        if (this._referencedType != null) {
            return this._referencedType.getBindings();
        }
        return super.getBindings();
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb2) {
        if (this._referencedType != null) {
            return this._referencedType.getErasedSignature(sb2);
        }
        return sb2.append("?");
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb2) {
        if (this._referencedType != null) {
            return this._referencedType.getErasedSignature(sb2);
        }
        return sb2;
    }

    @Override
    public JavaType withContentType(JavaType contentType) {
        return this;
    }

    @Override
    public JavaType withTypeHandler(Object h2) {
        return this;
    }

    @Override
    public JavaType withContentTypeHandler(Object h2) {
        return this;
    }

    @Override
    public JavaType withValueHandler(Object h2) {
        return this;
    }

    @Override
    public JavaType withContentValueHandler(Object h2) {
        return this;
    }

    @Override
    public JavaType withStaticTyping() {
        return this;
    }

    @Override
    @Deprecated
    protected JavaType _narrow(Class<?> subclass) {
        return this;
    }

    @Override
    public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return null;
    }

    @Override
    public boolean isContainerType() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb2 = new StringBuilder(40).append("[recursive type; ");
        if (this._referencedType == null) {
            sb2.append("UNRESOLVED");
        } else {
            sb2.append(this._referencedType.getRawClass().getName());
        }
        return sb2.toString();
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2.getClass() == this.getClass()) {
            return false;
        }
        return false;
    }
}

