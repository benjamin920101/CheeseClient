/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import java.util.Collection;

public class IgnoredPropertyException
extends PropertyBindingException {
    private static final long serialVersionUID = 1L;

    public IgnoredPropertyException(JsonParser p2, String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(p2, msg, loc, referringClass, propName, propertyIds);
    }

    @Deprecated
    public IgnoredPropertyException(String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(msg, loc, referringClass, propName, propertyIds);
    }

    public static IgnoredPropertyException from(JsonParser p2, Object fromObjectOrClass, String propertyName, Collection<Object> propertyIds) {
        Class<?> ref = fromObjectOrClass instanceof Class ? (Class<?>)fromObjectOrClass : fromObjectOrClass.getClass();
        String msg = String.format("Ignored field \"%s\" (class %s) encountered; mapper configured not to allow this", propertyName, ref.getName());
        IgnoredPropertyException e2 = new IgnoredPropertyException(p2, msg, p2.getCurrentLocation(), ref, propertyName, propertyIds);
        e2.prependPath(fromObjectOrClass, propertyName);
        return e2;
    }
}

