/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import java.util.Collection;

public class UnrecognizedPropertyException
extends PropertyBindingException {
    private static final long serialVersionUID = 1L;

    public UnrecognizedPropertyException(JsonParser p2, String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(p2, msg, loc, referringClass, propName, propertyIds);
    }

    @Deprecated
    public UnrecognizedPropertyException(String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(msg, loc, referringClass, propName, propertyIds);
    }

    public static UnrecognizedPropertyException from(JsonParser p2, Object fromObjectOrClass, String propertyName, Collection<Object> propertyIds) {
        Class<?> ref = fromObjectOrClass instanceof Class ? (Class<?>)fromObjectOrClass : fromObjectOrClass.getClass();
        String msg = String.format("Unrecognized field \"%s\" (class %s), not marked as ignorable", propertyName, ref.getName());
        UnrecognizedPropertyException e2 = new UnrecognizedPropertyException(p2, msg, p2.getCurrentLocation(), ref, propertyName, propertyIds);
        e2.prependPath(fromObjectOrClass, propertyName);
        return e2;
    }
}

