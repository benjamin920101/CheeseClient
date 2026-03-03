/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TypeNameIdResolver
extends TypeIdResolverBase {
    protected final MapperConfig<?> _config;
    protected final Map<String, String> _typeToId;
    protected final Map<String, JavaType> _idToType;

    protected TypeNameIdResolver(MapperConfig<?> config, JavaType baseType, Map<String, String> typeToId, Map<String, JavaType> idToType) {
        super(baseType, config.getTypeFactory());
        this._config = config;
        this._typeToId = typeToId;
        this._idToType = idToType;
    }

    public static TypeNameIdResolver construct(MapperConfig<?> config, JavaType baseType, Collection<NamedType> subtypes, boolean forSer, boolean forDeser) {
        if (forSer == forDeser) {
            throw new IllegalArgumentException();
        }
        AbstractMap typeToId = null;
        HashMap<String, JavaType> idToType = null;
        if (forSer) {
            typeToId = new HashMap<String, String>();
        }
        if (forDeser) {
            idToType = new HashMap<String, JavaType>();
            typeToId = new TreeMap();
        }
        if (subtypes != null) {
            for (NamedType t2 : subtypes) {
                JavaType prev;
                String id2;
                Class<?> cls = t2.getType();
                String string = id2 = t2.hasName() ? t2.getName() : TypeNameIdResolver._defaultTypeId(cls);
                if (forSer) {
                    typeToId.put((String)cls.getName(), (String)id2);
                }
                if (!forDeser || (prev = (JavaType)idToType.get(id2)) != null && cls.isAssignableFrom(prev.getRawClass())) continue;
                idToType.put(id2, config.constructType(cls));
            }
        }
        return new TypeNameIdResolver(config, baseType, typeToId, idToType);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.NAME;
    }

    @Override
    public String idFromValue(Object value) {
        return this.idFromClass(value.getClass());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected String idFromClass(Class<?> clazz) {
        String name;
        if (clazz == null) {
            return null;
        }
        Class<?> cls = this._typeFactory.constructType(clazz).getRawClass();
        String key = cls.getName();
        Map<String, String> map = this._typeToId;
        synchronized (map) {
            name = this._typeToId.get(key);
            if (name == null) {
                if (this._config.isAnnotationProcessingEnabled()) {
                    BeanDescription beanDesc = this._config.introspectClassAnnotations(cls);
                    name = this._config.getAnnotationIntrospector().findTypeName(beanDesc.getClassInfo());
                }
                if (name == null) {
                    name = TypeNameIdResolver._defaultTypeId(cls);
                }
                this._typeToId.put(key, name);
            }
        }
        return name;
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> type) {
        if (value == null) {
            return this.idFromClass(type);
        }
        return this.idFromValue(value);
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id2) {
        return this._typeFromId(id2);
    }

    protected JavaType _typeFromId(String id2) {
        return this._idToType.get(id2);
    }

    @Override
    public String getDescForKnownTypeIds() {
        return new TreeSet<String>(this._idToType.keySet()).toString();
    }

    public String toString() {
        return String.format("[%s; id-to-type=%s]", this.getClass().getName(), this._idToType);
    }

    protected static String _defaultTypeId(Class<?> cls) {
        String n2 = cls.getName();
        int ix2 = n2.lastIndexOf(46);
        return ix2 < 0 ? n2 : n2.substring(ix2 + 1);
    }
}

