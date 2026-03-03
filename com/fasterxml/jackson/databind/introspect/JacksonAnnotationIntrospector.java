/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.ext.Java7Support;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VirtualAnnotatedMember;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class JacksonAnnotationIntrospector
extends AnnotationIntrospector
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_SER = new Class[]{JsonSerialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonRawValue.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class};
    private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_DESER = new Class[]{JsonDeserialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class, JsonMerge.class};
    private static final Java7Support _java7Helper;
    protected transient LRUMap<Class<?>, Boolean> _annotationsInside = new LRUMap(48, 48);
    protected boolean _cfgConstructorPropertiesImpliesCreator = true;

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    protected Object readResolve() {
        if (this._annotationsInside == null) {
            this._annotationsInside = new LRUMap(48, 48);
        }
        return this;
    }

    public JacksonAnnotationIntrospector setConstructorPropertiesImpliesCreator(boolean b2) {
        this._cfgConstructorPropertiesImpliesCreator = b2;
        return this;
    }

    @Override
    public boolean isAnnotationBundle(Annotation ann2) {
        Class<? extends Annotation> type = ann2.annotationType();
        Boolean b2 = this._annotationsInside.get(type);
        if (b2 == null) {
            b2 = type.getAnnotation(JacksonAnnotationsInside.class) != null;
            this._annotationsInside.putIfAbsent(type, b2);
        }
        return b2;
    }

    @Override
    @Deprecated
    public String findEnumValue(Enum<?> value) {
        try {
            String n2;
            JsonProperty prop;
            Field f2 = value.getClass().getField(value.name());
            if (f2 != null && (prop = f2.getAnnotation(JsonProperty.class)) != null && (n2 = prop.value()) != null && !n2.isEmpty()) {
                return n2;
            }
        }
        catch (SecurityException securityException) {
        }
        catch (NoSuchFieldException noSuchFieldException) {
            // empty catch block
        }
        return value.name();
    }

    @Override
    public String[] findEnumValues(Class<?> enumType, Enum<?>[] enumValues, String[] names) {
        HashMap<String, String> expl = null;
        for (Field f2 : ClassUtil.getDeclaredFields(enumType)) {
            String n2;
            JsonProperty prop;
            if (!f2.isEnumConstant() || (prop = f2.getAnnotation(JsonProperty.class)) == null || (n2 = prop.value()).isEmpty()) continue;
            if (expl == null) {
                expl = new HashMap<String, String>();
            }
            expl.put(f2.getName(), n2);
        }
        if (expl != null) {
            int end = enumValues.length;
            for (int i2 = 0; i2 < end; ++i2) {
                String defName = enumValues[i2].name();
                String explValue = (String)expl.get(defName);
                if (explValue == null) continue;
                names[i2] = explValue;
            }
        }
        return names;
    }

    @Override
    public Enum<?> findDefaultEnumValue(Class<Enum<?>> enumCls) {
        return ClassUtil.findFirstAnnotatedEnumValue(enumCls, JsonEnumDefaultValue.class);
    }

    @Override
    public PropertyName findRootName(AnnotatedClass ac2) {
        JsonRootName ann2 = this._findAnnotation(ac2, JsonRootName.class);
        if (ann2 == null) {
            return null;
        }
        String ns2 = ann2.namespace();
        if (ns2 != null && ns2.length() == 0) {
            ns2 = null;
        }
        return PropertyName.construct(ann2.value(), ns2);
    }

    @Override
    public JsonIgnoreProperties.Value findPropertyIgnorals(Annotated a2) {
        JsonIgnoreProperties v2 = this._findAnnotation(a2, JsonIgnoreProperties.class);
        if (v2 == null) {
            return JsonIgnoreProperties.Value.empty();
        }
        return JsonIgnoreProperties.Value.from(v2);
    }

    @Override
    public Boolean isIgnorableType(AnnotatedClass ac2) {
        JsonIgnoreType ignore = this._findAnnotation(ac2, JsonIgnoreType.class);
        return ignore == null ? null : Boolean.valueOf(ignore.value());
    }

    @Override
    public Object findFilterId(Annotated a2) {
        String id2;
        JsonFilter ann2 = this._findAnnotation(a2, JsonFilter.class);
        if (ann2 != null && (id2 = ann2.value()).length() > 0) {
            return id2;
        }
        return null;
    }

    @Override
    public Object findNamingStrategy(AnnotatedClass ac2) {
        JsonNaming ann2 = this._findAnnotation(ac2, JsonNaming.class);
        return ann2 == null ? null : ann2.value();
    }

    @Override
    public String findClassDescription(AnnotatedClass ac2) {
        JsonClassDescription ann2 = this._findAnnotation(ac2, JsonClassDescription.class);
        return ann2 == null ? null : ann2.value();
    }

    @Override
    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass ac2, VisibilityChecker<?> checker) {
        JsonAutoDetect ann2 = this._findAnnotation(ac2, JsonAutoDetect.class);
        return ann2 == null ? checker : checker.with(ann2);
    }

    @Override
    public String findImplicitPropertyName(AnnotatedMember m2) {
        PropertyName n2 = this._findConstructorName(m2);
        return n2 == null ? null : n2.getSimpleName();
    }

    @Override
    public List<PropertyName> findPropertyAliases(Annotated m2) {
        JsonAlias ann2 = this._findAnnotation(m2, JsonAlias.class);
        if (ann2 == null) {
            return null;
        }
        String[] strs = ann2.value();
        int len = strs.length;
        if (len == 0) {
            return Collections.emptyList();
        }
        ArrayList<PropertyName> result = new ArrayList<PropertyName>(len);
        for (int i2 = 0; i2 < len; ++i2) {
            result.add(PropertyName.construct(strs[i2]));
        }
        return result;
    }

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m2) {
        return this._isIgnorable(m2);
    }

    @Override
    public Boolean hasRequiredMarker(AnnotatedMember m2) {
        JsonProperty ann2 = this._findAnnotation(m2, JsonProperty.class);
        if (ann2 != null) {
            return ann2.required();
        }
        return null;
    }

    @Override
    public JsonProperty.Access findPropertyAccess(Annotated m2) {
        JsonProperty ann2 = this._findAnnotation(m2, JsonProperty.class);
        if (ann2 != null) {
            return ann2.access();
        }
        return null;
    }

    @Override
    public String findPropertyDescription(Annotated ann2) {
        JsonPropertyDescription desc = this._findAnnotation(ann2, JsonPropertyDescription.class);
        return desc == null ? null : desc.value();
    }

    @Override
    public Integer findPropertyIndex(Annotated ann2) {
        int ix2;
        JsonProperty prop = this._findAnnotation(ann2, JsonProperty.class);
        if (prop != null && (ix2 = prop.index()) != -1) {
            return ix2;
        }
        return null;
    }

    @Override
    public String findPropertyDefaultValue(Annotated ann2) {
        JsonProperty prop = this._findAnnotation(ann2, JsonProperty.class);
        if (prop == null) {
            return null;
        }
        String str = prop.defaultValue();
        return str.isEmpty() ? null : str;
    }

    @Override
    public JsonFormat.Value findFormat(Annotated ann2) {
        JsonFormat f2 = this._findAnnotation(ann2, JsonFormat.class);
        return f2 == null ? null : new JsonFormat.Value(f2);
    }

    @Override
    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember member) {
        JsonManagedReference ref1 = this._findAnnotation(member, JsonManagedReference.class);
        if (ref1 != null) {
            return AnnotationIntrospector.ReferenceProperty.managed(ref1.value());
        }
        JsonBackReference ref2 = this._findAnnotation(member, JsonBackReference.class);
        if (ref2 != null) {
            return AnnotationIntrospector.ReferenceProperty.back(ref2.value());
        }
        return null;
    }

    @Override
    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember member) {
        JsonUnwrapped ann2 = this._findAnnotation(member, JsonUnwrapped.class);
        if (ann2 == null || !ann2.enabled()) {
            return null;
        }
        String prefix = ann2.prefix();
        String suffix = ann2.suffix();
        return NameTransformer.simpleTransformer(prefix, suffix);
    }

    @Override
    public JacksonInject.Value findInjectableValue(AnnotatedMember m2) {
        JacksonInject ann2 = this._findAnnotation(m2, JacksonInject.class);
        if (ann2 == null) {
            return null;
        }
        JacksonInject.Value v2 = JacksonInject.Value.from(ann2);
        if (!v2.hasId()) {
            AnnotatedMethod am2;
            String id2 = !(m2 instanceof AnnotatedMethod) ? m2.getRawType().getName() : ((am2 = (AnnotatedMethod)m2).getParameterCount() == 0 ? m2.getRawType().getName() : am2.getRawParameterType(0).getName());
            v2 = v2.withId(id2);
        }
        return v2;
    }

    @Override
    @Deprecated
    public Object findInjectableValueId(AnnotatedMember m2) {
        JacksonInject.Value v2 = this.findInjectableValue(m2);
        return v2 == null ? null : v2.getId();
    }

    @Override
    public Class<?>[] findViews(Annotated a2) {
        JsonView ann2 = this._findAnnotation(a2, JsonView.class);
        return ann2 == null ? null : ann2.value();
    }

    @Override
    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> config, AnnotatedMethod setter1, AnnotatedMethod setter2) {
        Class<?> cls1 = setter1.getRawParameterType(0);
        Class<?> cls2 = setter2.getRawParameterType(0);
        if (cls1.isPrimitive()) {
            if (!cls2.isPrimitive()) {
                return setter1;
            }
        } else if (cls2.isPrimitive()) {
            return setter2;
        }
        if (cls1 == String.class) {
            if (cls2 != String.class) {
                return setter1;
            }
        } else if (cls2 == String.class) {
            return setter2;
        }
        return null;
    }

    @Override
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac2, JavaType baseType) {
        return this._findTypeResolver(config, ac2, baseType);
    }

    @Override
    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType baseType) {
        if (baseType.isContainerType() || baseType.isReferenceType()) {
            return null;
        }
        return this._findTypeResolver(config, am2, baseType);
    }

    @Override
    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType containerType) {
        if (containerType.getContentType() == null) {
            throw new IllegalArgumentException("Must call method with a container or reference type (got " + containerType + ")");
        }
        return this._findTypeResolver(config, am2, containerType);
    }

    @Override
    public List<NamedType> findSubtypes(Annotated a2) {
        JsonSubTypes t2 = this._findAnnotation(a2, JsonSubTypes.class);
        if (t2 == null) {
            return null;
        }
        JsonSubTypes.Type[] types = t2.value();
        ArrayList<NamedType> result = new ArrayList<NamedType>(types.length);
        for (JsonSubTypes.Type type : types) {
            result.add(new NamedType(type.value(), type.name()));
        }
        return result;
    }

    @Override
    public String findTypeName(AnnotatedClass ac2) {
        JsonTypeName tn2 = this._findAnnotation(ac2, JsonTypeName.class);
        return tn2 == null ? null : tn2.value();
    }

    @Override
    public Boolean isTypeId(AnnotatedMember member) {
        return this._hasAnnotation(member, JsonTypeId.class);
    }

    @Override
    public ObjectIdInfo findObjectIdInfo(Annotated ann2) {
        JsonIdentityInfo info = this._findAnnotation(ann2, JsonIdentityInfo.class);
        if (info == null || info.generator() == ObjectIdGenerators.None.class) {
            return null;
        }
        PropertyName name = PropertyName.construct(info.property());
        return new ObjectIdInfo(name, info.scope(), info.generator(), info.resolver());
    }

    @Override
    public ObjectIdInfo findObjectReferenceInfo(Annotated ann2, ObjectIdInfo objectIdInfo) {
        JsonIdentityReference ref = this._findAnnotation(ann2, JsonIdentityReference.class);
        if (ref == null) {
            return objectIdInfo;
        }
        if (objectIdInfo == null) {
            objectIdInfo = ObjectIdInfo.empty();
        }
        return objectIdInfo.withAlwaysAsId(ref.alwaysAsId());
    }

    @Override
    public Object findSerializer(Annotated a2) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        if (ann2 != null && (serClass = ann2.using()) != JsonSerializer.None.class) {
            return serClass;
        }
        JsonRawValue annRaw = this._findAnnotation(a2, JsonRawValue.class);
        if (annRaw != null && annRaw.value()) {
            Class<?> cls = a2.getRawType();
            return new RawSerializer(cls);
        }
        return null;
    }

    @Override
    public Object findKeySerializer(Annotated a2) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        if (ann2 != null && (serClass = ann2.keyUsing()) != JsonSerializer.None.class) {
            return serClass;
        }
        return null;
    }

    @Override
    public Object findContentSerializer(Annotated a2) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        if (ann2 != null && (serClass = ann2.contentUsing()) != JsonSerializer.None.class) {
            return serClass;
        }
        return null;
    }

    @Override
    public Object findNullSerializer(Annotated a2) {
        Class<? extends JsonSerializer> serClass;
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        if (ann2 != null && (serClass = ann2.nullsUsing()) != JsonSerializer.None.class) {
            return serClass;
        }
        return null;
    }

    @Override
    public JsonInclude.Value findPropertyInclusion(Annotated a2) {
        JsonInclude.Value value;
        JsonInclude inc = this._findAnnotation(a2, JsonInclude.class);
        JsonInclude.Value value2 = value = inc == null ? JsonInclude.Value.empty() : JsonInclude.Value.from(inc);
        if (value.getValueInclusion() == JsonInclude.Include.USE_DEFAULTS) {
            value = this._refinePropertyInclusion(a2, value);
        }
        return value;
    }

    private JsonInclude.Value _refinePropertyInclusion(Annotated a2, JsonInclude.Value value) {
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        if (ann2 != null) {
            switch (ann2.include()) {
                case ALWAYS: {
                    return value.withValueInclusion(JsonInclude.Include.ALWAYS);
                }
                case NON_NULL: {
                    return value.withValueInclusion(JsonInclude.Include.NON_NULL);
                }
                case NON_DEFAULT: {
                    return value.withValueInclusion(JsonInclude.Include.NON_DEFAULT);
                }
                case NON_EMPTY: {
                    return value.withValueInclusion(JsonInclude.Include.NON_EMPTY);
                }
            }
        }
        return value;
    }

    @Override
    public JsonSerialize.Typing findSerializationTyping(Annotated a2) {
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        return ann2 == null ? null : ann2.typing();
    }

    @Override
    public Object findSerializationConverter(Annotated a2) {
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        return ann2 == null ? null : this._classIfExplicit(ann2.converter(), Converter.None.class);
    }

    @Override
    public Object findSerializationContentConverter(AnnotatedMember a2) {
        JsonSerialize ann2 = this._findAnnotation(a2, JsonSerialize.class);
        return ann2 == null ? null : this._classIfExplicit(ann2.contentConverter(), Converter.None.class);
    }

    @Override
    public JavaType refineSerializationType(MapperConfig<?> config, Annotated a2, JavaType baseType) throws JsonMappingException {
        JavaType contentType;
        Class<?> currRaw;
        JsonSerialize jsonSer;
        TypeFactory tf2;
        JavaType type;
        block26: {
            Class<?> serClass;
            type = baseType;
            tf2 = config.getTypeFactory();
            jsonSer = this._findAnnotation(a2, JsonSerialize.class);
            Class<?> clazz = serClass = jsonSer == null ? null : this._classIfExplicit(jsonSer.as());
            if (serClass != null) {
                if (type.hasRawClass(serClass)) {
                    type = type.withStaticTyping();
                } else {
                    Class<?> currRaw2 = type.getRawClass();
                    try {
                        if (serClass.isAssignableFrom(currRaw2)) {
                            type = tf2.constructGeneralizedType(type, serClass);
                            break block26;
                        }
                        if (currRaw2.isAssignableFrom(serClass)) {
                            type = tf2.constructSpecializedType(type, serClass);
                            break block26;
                        }
                        if (this._primitiveAndWrapper(currRaw2, serClass)) {
                            type = type.withStaticTyping();
                            break block26;
                        }
                        throw new JsonMappingException(null, String.format("Cannot refine serialization type %s into %s; types not related", type, serClass.getName()));
                    }
                    catch (IllegalArgumentException iae) {
                        throw new JsonMappingException(null, String.format("Failed to widen type %s with annotation (value %s), from '%s': %s", type, serClass.getName(), a2.getName(), iae.getMessage()), (Throwable)iae);
                    }
                }
            }
        }
        if (type.isMapLikeType()) {
            Class<?> keyClass;
            JavaType keyType = type.getKeyType();
            Class<?> clazz = keyClass = jsonSer == null ? null : this._classIfExplicit(jsonSer.keyAs());
            if (keyClass != null) {
                block27: {
                    if (keyType.hasRawClass(keyClass)) {
                        keyType = keyType.withStaticTyping();
                    } else {
                        currRaw = keyType.getRawClass();
                        try {
                            if (keyClass.isAssignableFrom(currRaw)) {
                                keyType = tf2.constructGeneralizedType(keyType, keyClass);
                                break block27;
                            }
                            if (currRaw.isAssignableFrom(keyClass)) {
                                keyType = tf2.constructSpecializedType(keyType, keyClass);
                                break block27;
                            }
                            if (this._primitiveAndWrapper(currRaw, keyClass)) {
                                keyType = keyType.withStaticTyping();
                                break block27;
                            }
                            throw new JsonMappingException(null, String.format("Cannot refine serialization key type %s into %s; types not related", keyType, keyClass.getName()));
                        }
                        catch (IllegalArgumentException iae) {
                            throw new JsonMappingException(null, String.format("Failed to widen key type of %s with concrete-type annotation (value %s), from '%s': %s", type, keyClass.getName(), a2.getName(), iae.getMessage()), (Throwable)iae);
                        }
                    }
                }
                type = ((MapLikeType)type).withKeyType(keyType);
            }
        }
        if ((contentType = type.getContentType()) != null) {
            Class<?> contentClass;
            Class<?> clazz = contentClass = jsonSer == null ? null : this._classIfExplicit(jsonSer.contentAs());
            if (contentClass != null) {
                block28: {
                    if (contentType.hasRawClass(contentClass)) {
                        contentType = contentType.withStaticTyping();
                    } else {
                        currRaw = contentType.getRawClass();
                        try {
                            if (contentClass.isAssignableFrom(currRaw)) {
                                contentType = tf2.constructGeneralizedType(contentType, contentClass);
                                break block28;
                            }
                            if (currRaw.isAssignableFrom(contentClass)) {
                                contentType = tf2.constructSpecializedType(contentType, contentClass);
                                break block28;
                            }
                            if (this._primitiveAndWrapper(currRaw, contentClass)) {
                                contentType = contentType.withStaticTyping();
                                break block28;
                            }
                            throw new JsonMappingException(null, String.format("Cannot refine serialization content type %s into %s; types not related", contentType, contentClass.getName()));
                        }
                        catch (IllegalArgumentException iae) {
                            throw new JsonMappingException(null, String.format("Internal error: failed to refine value type of %s with concrete-type annotation (value %s), from '%s': %s", type, contentClass.getName(), a2.getName(), iae.getMessage()), (Throwable)iae);
                        }
                    }
                }
                type = type.withContentType(contentType);
            }
        }
        return type;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationType(Annotated am2) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationKeyType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationContentType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Override
    public String[] findSerializationPropertyOrder(AnnotatedClass ac2) {
        JsonPropertyOrder order = this._findAnnotation(ac2, JsonPropertyOrder.class);
        return order == null ? null : order.value();
    }

    @Override
    public Boolean findSerializationSortAlphabetically(Annotated ann2) {
        return this._findSortAlpha(ann2);
    }

    private final Boolean _findSortAlpha(Annotated ann2) {
        JsonPropertyOrder order = this._findAnnotation(ann2, JsonPropertyOrder.class);
        if (order != null && order.alphabetic()) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override
    public void findAndAddVirtualProperties(MapperConfig<?> config, AnnotatedClass ac2, List<BeanPropertyWriter> properties) {
        JsonAppend ann2 = this._findAnnotation(ac2, JsonAppend.class);
        if (ann2 == null) {
            return;
        }
        boolean prepend = ann2.prepend();
        JavaType propType = null;
        JsonAppend.Attr[] attrs = ann2.attrs();
        int len = attrs.length;
        for (int i2 = 0; i2 < len; ++i2) {
            if (propType == null) {
                propType = config.constructType(Object.class);
            }
            BeanPropertyWriter bpw2 = this._constructVirtualProperty(attrs[i2], config, ac2, propType);
            if (prepend) {
                properties.add(i2, bpw2);
                continue;
            }
            properties.add(bpw2);
        }
        JsonAppend.Prop[] props = ann2.props();
        int len2 = props.length;
        for (int i3 = 0; i3 < len2; ++i3) {
            BeanPropertyWriter bpw3 = this._constructVirtualProperty(props[i3], config, ac2);
            if (prepend) {
                properties.add(i3, bpw3);
                continue;
            }
            properties.add(bpw3);
        }
    }

    protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Attr attr, MapperConfig<?> config, AnnotatedClass ac2, JavaType type) {
        PropertyMetadata metadata = attr.required() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
        String attrName = attr.value();
        PropertyName propName = this._propertyName(attr.propName(), attr.propNamespace());
        if (!propName.hasSimpleName()) {
            propName = PropertyName.construct(attrName);
        }
        VirtualAnnotatedMember member = new VirtualAnnotatedMember(ac2, ac2.getRawType(), attrName, type);
        SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config, (AnnotatedMember)member, propName, metadata, attr.include());
        return AttributePropertyWriter.construct(attrName, propDef, ac2.getAnnotations(), type);
    }

    protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Prop prop, MapperConfig<?> config, AnnotatedClass ac2) {
        VirtualBeanPropertyWriter bpw2;
        PropertyMetadata metadata = prop.required() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
        PropertyName propName = this._propertyName(prop.name(), prop.namespace());
        JavaType type = config.constructType(prop.type());
        VirtualAnnotatedMember member = new VirtualAnnotatedMember(ac2, ac2.getRawType(), propName.getSimpleName(), type);
        SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config, (AnnotatedMember)member, propName, metadata, prop.include());
        Class<? extends VirtualBeanPropertyWriter> implClass = prop.value();
        HandlerInstantiator hi2 = config.getHandlerInstantiator();
        VirtualBeanPropertyWriter virtualBeanPropertyWriter = bpw2 = hi2 == null ? null : hi2.virtualPropertyWriterInstance(config, implClass);
        if (bpw2 == null) {
            bpw2 = ClassUtil.createInstance(implClass, config.canOverrideAccessModifiers());
        }
        return bpw2.withConfig(config, ac2, propDef, type);
    }

    @Override
    public PropertyName findNameForSerialization(Annotated a2) {
        JsonProperty pann;
        boolean useDefault = false;
        JsonGetter jg2 = this._findAnnotation(a2, JsonGetter.class);
        if (jg2 != null) {
            String s2 = jg2.value();
            if (!s2.isEmpty()) {
                return PropertyName.construct(s2);
            }
            useDefault = true;
        }
        if ((pann = this._findAnnotation(a2, JsonProperty.class)) != null) {
            return PropertyName.construct(pann.value());
        }
        if (useDefault || this._hasOneOf(a2, ANNOTATIONS_TO_INFER_SER)) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }

    @Override
    public Boolean hasAsValue(Annotated a2) {
        JsonValue ann2 = this._findAnnotation(a2, JsonValue.class);
        if (ann2 == null) {
            return null;
        }
        return ann2.value();
    }

    @Override
    public Boolean hasAnyGetter(Annotated a2) {
        JsonAnyGetter ann2 = this._findAnnotation(a2, JsonAnyGetter.class);
        if (ann2 == null) {
            return null;
        }
        return ann2.enabled();
    }

    @Override
    @Deprecated
    public boolean hasAnyGetterAnnotation(AnnotatedMethod am2) {
        return this._hasAnnotation(am2, JsonAnyGetter.class);
    }

    @Override
    @Deprecated
    public boolean hasAsValueAnnotation(AnnotatedMethod am2) {
        JsonValue ann2 = this._findAnnotation(am2, JsonValue.class);
        return ann2 != null && ann2.value();
    }

    @Override
    public Object findDeserializer(Annotated a2) {
        Class<? extends JsonDeserializer> deserClass;
        JsonDeserialize ann2 = this._findAnnotation(a2, JsonDeserialize.class);
        if (ann2 != null && (deserClass = ann2.using()) != JsonDeserializer.None.class) {
            return deserClass;
        }
        return null;
    }

    @Override
    public Object findKeyDeserializer(Annotated a2) {
        Class<? extends KeyDeserializer> deserClass;
        JsonDeserialize ann2 = this._findAnnotation(a2, JsonDeserialize.class);
        if (ann2 != null && (deserClass = ann2.keyUsing()) != KeyDeserializer.None.class) {
            return deserClass;
        }
        return null;
    }

    @Override
    public Object findContentDeserializer(Annotated a2) {
        Class<? extends JsonDeserializer> deserClass;
        JsonDeserialize ann2 = this._findAnnotation(a2, JsonDeserialize.class);
        if (ann2 != null && (deserClass = ann2.contentUsing()) != JsonDeserializer.None.class) {
            return deserClass;
        }
        return null;
    }

    @Override
    public Object findDeserializationConverter(Annotated a2) {
        JsonDeserialize ann2 = this._findAnnotation(a2, JsonDeserialize.class);
        return ann2 == null ? null : this._classIfExplicit(ann2.converter(), Converter.None.class);
    }

    @Override
    public Object findDeserializationContentConverter(AnnotatedMember a2) {
        JsonDeserialize ann2 = this._findAnnotation(a2, JsonDeserialize.class);
        return ann2 == null ? null : this._classIfExplicit(ann2.contentConverter(), Converter.None.class);
    }

    @Override
    public JavaType refineDeserializationType(MapperConfig<?> config, Annotated a2, JavaType baseType) throws JsonMappingException {
        JavaType contentType;
        Class<?> valueClass;
        JavaType type = baseType;
        TypeFactory tf2 = config.getTypeFactory();
        JsonDeserialize jsonDeser = this._findAnnotation(a2, JsonDeserialize.class);
        Class<?> clazz = valueClass = jsonDeser == null ? null : this._classIfExplicit(jsonDeser.as());
        if (valueClass != null && !type.hasRawClass(valueClass) && !this._primitiveAndWrapper(type, valueClass)) {
            try {
                type = tf2.constructSpecializedType(type, valueClass);
            }
            catch (IllegalArgumentException iae) {
                throw new JsonMappingException(null, String.format("Failed to narrow type %s with annotation (value %s), from '%s': %s", type, valueClass.getName(), a2.getName(), iae.getMessage()), (Throwable)iae);
            }
        }
        if (type.isMapLikeType()) {
            Class<?> keyClass;
            JavaType keyType = type.getKeyType();
            Class<?> clazz2 = keyClass = jsonDeser == null ? null : this._classIfExplicit(jsonDeser.keyAs());
            if (keyClass != null && !this._primitiveAndWrapper(keyType, keyClass)) {
                try {
                    keyType = tf2.constructSpecializedType(keyType, keyClass);
                    type = ((MapLikeType)type).withKeyType(keyType);
                }
                catch (IllegalArgumentException iae) {
                    throw new JsonMappingException(null, String.format("Failed to narrow key type of %s with concrete-type annotation (value %s), from '%s': %s", type, keyClass.getName(), a2.getName(), iae.getMessage()), (Throwable)iae);
                }
            }
        }
        if ((contentType = type.getContentType()) != null) {
            Class<?> contentClass;
            Class<?> clazz3 = contentClass = jsonDeser == null ? null : this._classIfExplicit(jsonDeser.contentAs());
            if (contentClass != null && !this._primitiveAndWrapper(contentType, contentClass)) {
                try {
                    contentType = tf2.constructSpecializedType(contentType, contentClass);
                    type = type.withContentType(contentType);
                }
                catch (IllegalArgumentException iae) {
                    throw new JsonMappingException(null, String.format("Failed to narrow value type of %s with concrete-type annotation (value %s), from '%s': %s", type, contentClass.getName(), a2.getName(), iae.getMessage()), (Throwable)iae);
                }
            }
        }
        return type;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationContentType(Annotated am2, JavaType baseContentType) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationType(Annotated am2, JavaType baseType) {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated am2, JavaType baseKeyType) {
        return null;
    }

    @Override
    public Object findValueInstantiator(AnnotatedClass ac2) {
        JsonValueInstantiator ann2 = this._findAnnotation(ac2, JsonValueInstantiator.class);
        return ann2 == null ? null : ann2.value();
    }

    @Override
    public Class<?> findPOJOBuilder(AnnotatedClass ac2) {
        JsonDeserialize ann2 = this._findAnnotation(ac2, JsonDeserialize.class);
        return ann2 == null ? null : this._classIfExplicit(ann2.builder());
    }

    @Override
    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac2) {
        JsonPOJOBuilder ann2 = this._findAnnotation(ac2, JsonPOJOBuilder.class);
        return ann2 == null ? null : new JsonPOJOBuilder.Value(ann2);
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a2) {
        JsonProperty pann;
        boolean useDefault = false;
        JsonSetter js2 = this._findAnnotation(a2, JsonSetter.class);
        if (js2 != null) {
            String s2 = js2.value();
            if (s2.isEmpty()) {
                useDefault = true;
            } else {
                return PropertyName.construct(s2);
            }
        }
        if ((pann = this._findAnnotation(a2, JsonProperty.class)) != null) {
            return PropertyName.construct(pann.value());
        }
        if (useDefault || this._hasOneOf(a2, ANNOTATIONS_TO_INFER_DESER)) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }

    @Override
    public Boolean hasAnySetter(Annotated a2) {
        JsonAnySetter ann2 = this._findAnnotation(a2, JsonAnySetter.class);
        return ann2 == null ? null : Boolean.valueOf(ann2.enabled());
    }

    @Override
    public JsonSetter.Value findSetterInfo(Annotated a2) {
        return JsonSetter.Value.from(this._findAnnotation(a2, JsonSetter.class));
    }

    @Override
    public Boolean findMergeInfo(Annotated a2) {
        JsonMerge ann2 = this._findAnnotation(a2, JsonMerge.class);
        return ann2 == null ? null : ann2.value().asBoolean();
    }

    @Override
    @Deprecated
    public boolean hasAnySetterAnnotation(AnnotatedMethod am2) {
        return this._hasAnnotation(am2, JsonAnySetter.class);
    }

    @Override
    @Deprecated
    public boolean hasCreatorAnnotation(Annotated a2) {
        Boolean b2;
        JsonCreator ann2 = this._findAnnotation(a2, JsonCreator.class);
        if (ann2 != null) {
            return ann2.mode() != JsonCreator.Mode.DISABLED;
        }
        if (this._cfgConstructorPropertiesImpliesCreator && a2 instanceof AnnotatedConstructor && _java7Helper != null && (b2 = _java7Helper.hasCreatorAnnotation(a2)) != null) {
            return b2;
        }
        return false;
    }

    @Override
    @Deprecated
    public JsonCreator.Mode findCreatorBinding(Annotated a2) {
        JsonCreator ann2 = this._findAnnotation(a2, JsonCreator.class);
        return ann2 == null ? null : ann2.mode();
    }

    @Override
    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a2) {
        Boolean b2;
        JsonCreator ann2 = this._findAnnotation(a2, JsonCreator.class);
        if (ann2 != null) {
            return ann2.mode();
        }
        if (this._cfgConstructorPropertiesImpliesCreator && config.isEnabled(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES) && a2 instanceof AnnotatedConstructor && _java7Helper != null && (b2 = _java7Helper.hasCreatorAnnotation(a2)) != null && b2.booleanValue()) {
            return JsonCreator.Mode.PROPERTIES;
        }
        return null;
    }

    protected boolean _isIgnorable(Annotated a2) {
        Boolean b2;
        JsonIgnore ann2 = this._findAnnotation(a2, JsonIgnore.class);
        if (ann2 != null) {
            return ann2.value();
        }
        if (_java7Helper != null && (b2 = _java7Helper.findTransient(a2)) != null) {
            return b2;
        }
        return false;
    }

    protected Class<?> _classIfExplicit(Class<?> cls) {
        if (cls == null || ClassUtil.isBogusClass(cls)) {
            return null;
        }
        return cls;
    }

    protected Class<?> _classIfExplicit(Class<?> cls, Class<?> implicit) {
        return (cls = this._classIfExplicit(cls)) == null || cls == implicit ? null : cls;
    }

    protected PropertyName _propertyName(String localName, String namespace) {
        if (localName.isEmpty()) {
            return PropertyName.USE_DEFAULT;
        }
        if (namespace == null || namespace.isEmpty()) {
            return PropertyName.construct(localName);
        }
        return PropertyName.construct(localName, namespace);
    }

    protected PropertyName _findConstructorName(Annotated a2) {
        PropertyName name;
        AnnotatedParameter p2;
        AnnotatedWithParams ctor;
        if (a2 instanceof AnnotatedParameter && (ctor = (p2 = (AnnotatedParameter)a2).getOwner()) != null && _java7Helper != null && (name = _java7Helper.findConstructorName(p2)) != null) {
            return name;
        }
        return null;
    }

    protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> config, Annotated ann2, JavaType baseType) {
        TypeIdResolver idRes;
        StdTypeResolverBuilder b2;
        JsonTypeInfo info = this._findAnnotation(ann2, JsonTypeInfo.class);
        JsonTypeResolver resAnn = this._findAnnotation(ann2, JsonTypeResolver.class);
        if (resAnn != null) {
            if (info == null) {
                return null;
            }
            b2 = config.typeResolverBuilderInstance(ann2, resAnn.value());
        } else {
            if (info == null) {
                return null;
            }
            if (info.use() == JsonTypeInfo.Id.NONE) {
                return this._constructNoTypeResolverBuilder();
            }
            b2 = this._constructStdTypeResolverBuilder();
        }
        JsonTypeIdResolver idResInfo = this._findAnnotation(ann2, JsonTypeIdResolver.class);
        TypeIdResolver typeIdResolver = idRes = idResInfo == null ? null : config.typeIdResolverInstance(ann2, idResInfo.value());
        if (idRes != null) {
            idRes.init(baseType);
        }
        b2 = b2.init(info.use(), idRes);
        JsonTypeInfo.As inclusion = info.include();
        if (inclusion == JsonTypeInfo.As.EXTERNAL_PROPERTY && ann2 instanceof AnnotatedClass) {
            inclusion = JsonTypeInfo.As.PROPERTY;
        }
        b2 = b2.inclusion(inclusion);
        b2 = b2.typeProperty(info.property());
        Class<?> defaultImpl = info.defaultImpl();
        if (defaultImpl != JsonTypeInfo.None.class && !defaultImpl.isAnnotation()) {
            b2 = b2.defaultImpl(defaultImpl);
        }
        b2 = b2.typeIdVisibility(info.visible());
        return b2;
    }

    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new StdTypeResolverBuilder();
    }

    protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
        return StdTypeResolverBuilder.noTypeInfoBuilder();
    }

    private boolean _primitiveAndWrapper(Class<?> baseType, Class<?> refinement) {
        if (baseType.isPrimitive()) {
            return baseType == ClassUtil.primitiveType(refinement);
        }
        if (refinement.isPrimitive()) {
            return refinement == ClassUtil.primitiveType(baseType);
        }
        return false;
    }

    private boolean _primitiveAndWrapper(JavaType baseType, Class<?> refinement) {
        if (baseType.isPrimitive()) {
            return baseType.hasRawClass(ClassUtil.primitiveType(refinement));
        }
        if (refinement.isPrimitive()) {
            return refinement == ClassUtil.primitiveType(baseType.getRawClass());
        }
        return false;
    }

    static {
        Java7Support x2 = null;
        try {
            x2 = Java7Support.instance();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        _java7Helper = x2;
    }
}

