/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;

public class MinimalClassNameIdResolver
extends ClassNameIdResolver {
    protected final String _basePackageName;
    protected final String _basePackagePrefix;

    protected MinimalClassNameIdResolver(JavaType baseType, TypeFactory typeFactory, PolymorphicTypeValidator ptv) {
        super(baseType, typeFactory, ptv);
        String base = baseType.getRawClass().getName();
        int ix2 = base.lastIndexOf(46);
        if (ix2 < 0) {
            this._basePackageName = "";
            this._basePackagePrefix = ".";
        } else {
            this._basePackagePrefix = base.substring(0, ix2 + 1);
            this._basePackageName = base.substring(0, ix2);
        }
    }

    public static MinimalClassNameIdResolver construct(JavaType baseType, MapperConfig<?> config, PolymorphicTypeValidator ptv) {
        return new MinimalClassNameIdResolver(baseType, config.getTypeFactory(), ptv);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.MINIMAL_CLASS;
    }

    @Override
    public String idFromValue(Object value) {
        String n2 = value.getClass().getName();
        if (n2.startsWith(this._basePackagePrefix)) {
            return n2.substring(this._basePackagePrefix.length() - 1);
        }
        return n2;
    }

    @Override
    protected JavaType _typeFromId(String id2, DatabindContext ctxt) throws IOException {
        if (id2.startsWith(".")) {
            StringBuilder sb2 = new StringBuilder(id2.length() + this._basePackageName.length());
            if (this._basePackageName.length() == 0) {
                sb2.append(id2.substring(1));
            } else {
                sb2.append(this._basePackageName).append(id2);
            }
            id2 = sb2.toString();
        }
        return super._typeFromId(id2, ctxt);
    }
}

