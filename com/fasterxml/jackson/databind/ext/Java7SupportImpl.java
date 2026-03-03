/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.ext.Java7Support;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.beans.ConstructorProperties;
import java.beans.Transient;

public class Java7SupportImpl
extends Java7Support {
    private final Class<?> _bogus;

    public Java7SupportImpl() {
        Class cls = Transient.class;
        cls = ConstructorProperties.class;
        this._bogus = cls;
    }

    @Override
    public Boolean findTransient(Annotated a2) {
        Transient t2 = a2.getAnnotation(Transient.class);
        if (t2 != null) {
            return t2.value();
        }
        return null;
    }

    @Override
    public Boolean hasCreatorAnnotation(Annotated a2) {
        ConstructorProperties props = a2.getAnnotation(ConstructorProperties.class);
        if (props != null) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override
    public PropertyName findConstructorName(AnnotatedParameter p2) {
        ConstructorProperties props;
        AnnotatedWithParams ctor = p2.getOwner();
        if (ctor != null && (props = ctor.getAnnotation(ConstructorProperties.class)) != null) {
            String[] names = props.value();
            int ix2 = p2.getIndex();
            if (ix2 < names.length) {
                return PropertyName.construct(names[ix2]);
            }
        }
        return null;
    }
}

