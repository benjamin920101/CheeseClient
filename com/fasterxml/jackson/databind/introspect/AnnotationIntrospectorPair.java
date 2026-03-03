/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair
extends AnnotationIntrospector
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final AnnotationIntrospector _primary;
    protected final AnnotationIntrospector _secondary;

    public AnnotationIntrospectorPair(AnnotationIntrospector p2, AnnotationIntrospector s2) {
        this._primary = p2;
        this._secondary = s2;
    }

    @Override
    public Version version() {
        return this._primary.version();
    }

    public static AnnotationIntrospector create(AnnotationIntrospector primary, AnnotationIntrospector secondary) {
        if (primary == null) {
            return secondary;
        }
        if (secondary == null) {
            return primary;
        }
        return new AnnotationIntrospectorPair(primary, secondary);
    }

    @Override
    public Collection<AnnotationIntrospector> allIntrospectors() {
        return this.allIntrospectors(new ArrayList<AnnotationIntrospector>());
    }

    @Override
    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> result) {
        this._primary.allIntrospectors(result);
        this._secondary.allIntrospectors(result);
        return result;
    }

    @Override
    public boolean isAnnotationBundle(Annotation ann2) {
        return this._primary.isAnnotationBundle(ann2) || this._secondary.isAnnotationBundle(ann2);
    }

    @Override
    public PropertyName findRootName(AnnotatedClass ac2) {
        PropertyName name1 = this._primary.findRootName(ac2);
        if (name1 == null) {
            return this._secondary.findRootName(ac2);
        }
        if (name1.hasSimpleName()) {
            return name1;
        }
        PropertyName name2 = this._secondary.findRootName(ac2);
        return name2 == null ? name1 : name2;
    }

    @Override
    public JsonIgnoreProperties.Value findPropertyIgnorals(Annotated a2) {
        JsonIgnoreProperties.Value v2 = this._secondary.findPropertyIgnorals(a2);
        JsonIgnoreProperties.Value v1 = this._primary.findPropertyIgnorals(a2);
        return v2 == null ? v1 : v2.withOverrides(v1);
    }

    @Override
    public Boolean isIgnorableType(AnnotatedClass ac2) {
        Boolean result = this._primary.isIgnorableType(ac2);
        if (result == null) {
            result = this._secondary.isIgnorableType(ac2);
        }
        return result;
    }

    @Override
    public Object findFilterId(Annotated ann2) {
        Object id2 = this._primary.findFilterId(ann2);
        if (id2 == null) {
            id2 = this._secondary.findFilterId(ann2);
        }
        return id2;
    }

    @Override
    public Object findNamingStrategy(AnnotatedClass ac2) {
        Object str = this._primary.findNamingStrategy(ac2);
        if (str == null) {
            str = this._secondary.findNamingStrategy(ac2);
        }
        return str;
    }

    @Override
    public String findClassDescription(AnnotatedClass ac2) {
        String str = this._primary.findClassDescription(ac2);
        if (str == null || str.isEmpty()) {
            str = this._secondary.findClassDescription(ac2);
        }
        return str;
    }

    @Override
    @Deprecated
    public String[] findPropertiesToIgnore(Annotated ac2) {
        String[] result = this._primary.findPropertiesToIgnore(ac2);
        if (result == null) {
            result = this._secondary.findPropertiesToIgnore(ac2);
        }
        return result;
    }

    @Override
    @Deprecated
    public String[] findPropertiesToIgnore(Annotated ac2, boolean forSerialization) {
        String[] result = this._primary.findPropertiesToIgnore(ac2, forSerialization);
        if (result == null) {
            result = this._secondary.findPropertiesToIgnore(ac2, forSerialization);
        }
        return result;
    }

    @Override
    @Deprecated
    public Boolean findIgnoreUnknownProperties(AnnotatedClass ac2) {
        Boolean result = this._primary.findIgnoreUnknownProperties(ac2);
        if (result == null) {
            result = this._secondary.findIgnoreUnknownProperties(ac2);
        }
        return result;
    }

    @Override
    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass ac2, VisibilityChecker<?> checker) {
        checker = this._secondary.findAutoDetectVisibility(ac2, checker);
        return this._primary.findAutoDetectVisibility(ac2, checker);
    }

    @Override
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac2, JavaType baseType) {
        TypeResolverBuilder<?> b2 = this._primary.findTypeResolver(config, ac2, baseType);
        if (b2 == null) {
            b2 = this._secondary.findTypeResolver(config, ac2, baseType);
        }
        return b2;
    }

    @Override
    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType baseType) {
        TypeResolverBuilder<?> b2 = this._primary.findPropertyTypeResolver(config, am2, baseType);
        if (b2 == null) {
            b2 = this._secondary.findPropertyTypeResolver(config, am2, baseType);
        }
        return b2;
    }

    @Override
    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> config, AnnotatedMember am2, JavaType baseType) {
        TypeResolverBuilder<?> b2 = this._primary.findPropertyContentTypeResolver(config, am2, baseType);
        if (b2 == null) {
            b2 = this._secondary.findPropertyContentTypeResolver(config, am2, baseType);
        }
        return b2;
    }

    @Override
    public List<NamedType> findSubtypes(Annotated a2) {
        List<NamedType> types1 = this._primary.findSubtypes(a2);
        List<NamedType> types2 = this._secondary.findSubtypes(a2);
        if (types1 == null || types1.isEmpty()) {
            return types2;
        }
        if (types2 == null || types2.isEmpty()) {
            return types1;
        }
        ArrayList<NamedType> result = new ArrayList<NamedType>(types1.size() + types2.size());
        result.addAll(types1);
        result.addAll(types2);
        return result;
    }

    @Override
    public String findTypeName(AnnotatedClass ac2) {
        String name = this._primary.findTypeName(ac2);
        if (name == null || name.length() == 0) {
            name = this._secondary.findTypeName(ac2);
        }
        return name;
    }

    @Override
    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember member) {
        AnnotationIntrospector.ReferenceProperty r2 = this._primary.findReferenceType(member);
        return r2 == null ? this._secondary.findReferenceType(member) : r2;
    }

    @Override
    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember member) {
        NameTransformer r2 = this._primary.findUnwrappingNameTransformer(member);
        return r2 == null ? this._secondary.findUnwrappingNameTransformer(member) : r2;
    }

    @Override
    public JacksonInject.Value findInjectableValue(AnnotatedMember m2) {
        JacksonInject.Value r2 = this._primary.findInjectableValue(m2);
        return r2 == null ? this._secondary.findInjectableValue(m2) : r2;
    }

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m2) {
        return this._primary.hasIgnoreMarker(m2) || this._secondary.hasIgnoreMarker(m2);
    }

    @Override
    public Boolean hasRequiredMarker(AnnotatedMember m2) {
        Boolean r2 = this._primary.hasRequiredMarker(m2);
        return r2 == null ? this._secondary.hasRequiredMarker(m2) : r2;
    }

    @Override
    @Deprecated
    public Object findInjectableValueId(AnnotatedMember m2) {
        Object r2 = this._primary.findInjectableValueId(m2);
        return r2 == null ? this._secondary.findInjectableValueId(m2) : r2;
    }

    @Override
    public Object findSerializer(Annotated am2) {
        Object r2 = this._primary.findSerializer(am2);
        if (this._isExplicitClassOrOb(r2, JsonSerializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findSerializer(am2), JsonSerializer.None.class);
    }

    @Override
    public Object findKeySerializer(Annotated a2) {
        Object r2 = this._primary.findKeySerializer(a2);
        if (this._isExplicitClassOrOb(r2, JsonSerializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findKeySerializer(a2), JsonSerializer.None.class);
    }

    @Override
    public Object findContentSerializer(Annotated a2) {
        Object r2 = this._primary.findContentSerializer(a2);
        if (this._isExplicitClassOrOb(r2, JsonSerializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findContentSerializer(a2), JsonSerializer.None.class);
    }

    @Override
    public Object findNullSerializer(Annotated a2) {
        Object r2 = this._primary.findNullSerializer(a2);
        if (this._isExplicitClassOrOb(r2, JsonSerializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findNullSerializer(a2), JsonSerializer.None.class);
    }

    @Override
    @Deprecated
    public JsonInclude.Include findSerializationInclusion(Annotated a2, JsonInclude.Include defValue) {
        defValue = this._secondary.findSerializationInclusion(a2, defValue);
        defValue = this._primary.findSerializationInclusion(a2, defValue);
        return defValue;
    }

    @Override
    @Deprecated
    public JsonInclude.Include findSerializationInclusionForContent(Annotated a2, JsonInclude.Include defValue) {
        defValue = this._secondary.findSerializationInclusionForContent(a2, defValue);
        defValue = this._primary.findSerializationInclusionForContent(a2, defValue);
        return defValue;
    }

    @Override
    public JsonInclude.Value findPropertyInclusion(Annotated a2) {
        JsonInclude.Value v2 = this._secondary.findPropertyInclusion(a2);
        JsonInclude.Value v1 = this._primary.findPropertyInclusion(a2);
        if (v2 == null) {
            return v1;
        }
        return v2.withOverrides(v1);
    }

    @Override
    public JsonSerialize.Typing findSerializationTyping(Annotated a2) {
        JsonSerialize.Typing r2 = this._primary.findSerializationTyping(a2);
        return r2 == null ? this._secondary.findSerializationTyping(a2) : r2;
    }

    @Override
    public Object findSerializationConverter(Annotated a2) {
        Object r2 = this._primary.findSerializationConverter(a2);
        return r2 == null ? this._secondary.findSerializationConverter(a2) : r2;
    }

    @Override
    public Object findSerializationContentConverter(AnnotatedMember a2) {
        Object r2 = this._primary.findSerializationContentConverter(a2);
        return r2 == null ? this._secondary.findSerializationContentConverter(a2) : r2;
    }

    @Override
    public Class<?>[] findViews(Annotated a2) {
        Class<?>[] result = this._primary.findViews(a2);
        if (result == null) {
            result = this._secondary.findViews(a2);
        }
        return result;
    }

    @Override
    public Boolean isTypeId(AnnotatedMember member) {
        Boolean b2 = this._primary.isTypeId(member);
        return b2 == null ? this._secondary.isTypeId(member) : b2;
    }

    @Override
    public ObjectIdInfo findObjectIdInfo(Annotated ann2) {
        ObjectIdInfo r2 = this._primary.findObjectIdInfo(ann2);
        return r2 == null ? this._secondary.findObjectIdInfo(ann2) : r2;
    }

    @Override
    public ObjectIdInfo findObjectReferenceInfo(Annotated ann2, ObjectIdInfo objectIdInfo) {
        objectIdInfo = this._secondary.findObjectReferenceInfo(ann2, objectIdInfo);
        objectIdInfo = this._primary.findObjectReferenceInfo(ann2, objectIdInfo);
        return objectIdInfo;
    }

    @Override
    public JsonFormat.Value findFormat(Annotated ann2) {
        JsonFormat.Value v1 = this._primary.findFormat(ann2);
        JsonFormat.Value v2 = this._secondary.findFormat(ann2);
        if (v2 == null) {
            return v1;
        }
        return v2.withOverrides(v1);
    }

    @Override
    public PropertyName findWrapperName(Annotated ann2) {
        PropertyName name2;
        PropertyName name = this._primary.findWrapperName(ann2);
        if (name == null) {
            name = this._secondary.findWrapperName(ann2);
        } else if (name == PropertyName.USE_DEFAULT && (name2 = this._secondary.findWrapperName(ann2)) != null) {
            name = name2;
        }
        return name;
    }

    @Override
    public String findPropertyDefaultValue(Annotated ann2) {
        String str = this._primary.findPropertyDefaultValue(ann2);
        return str == null || str.isEmpty() ? this._secondary.findPropertyDefaultValue(ann2) : str;
    }

    @Override
    public String findPropertyDescription(Annotated ann2) {
        String r2 = this._primary.findPropertyDescription(ann2);
        return r2 == null ? this._secondary.findPropertyDescription(ann2) : r2;
    }

    @Override
    public Integer findPropertyIndex(Annotated ann2) {
        Integer r2 = this._primary.findPropertyIndex(ann2);
        return r2 == null ? this._secondary.findPropertyIndex(ann2) : r2;
    }

    @Override
    public String findImplicitPropertyName(AnnotatedMember ann2) {
        String r2 = this._primary.findImplicitPropertyName(ann2);
        return r2 == null ? this._secondary.findImplicitPropertyName(ann2) : r2;
    }

    @Override
    public List<PropertyName> findPropertyAliases(Annotated ann2) {
        List<PropertyName> r2 = this._primary.findPropertyAliases(ann2);
        return r2 == null ? this._secondary.findPropertyAliases(ann2) : r2;
    }

    @Override
    public JsonProperty.Access findPropertyAccess(Annotated ann2) {
        JsonProperty.Access acc2 = this._primary.findPropertyAccess(ann2);
        if (acc2 != null && acc2 != JsonProperty.Access.AUTO) {
            return acc2;
        }
        acc2 = this._secondary.findPropertyAccess(ann2);
        if (acc2 != null) {
            return acc2;
        }
        return JsonProperty.Access.AUTO;
    }

    @Override
    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> config, AnnotatedMethod setter1, AnnotatedMethod setter2) {
        AnnotatedMethod res = this._primary.resolveSetterConflict(config, setter1, setter2);
        if (res == null) {
            res = this._secondary.resolveSetterConflict(config, setter1, setter2);
        }
        return res;
    }

    @Override
    public JavaType refineSerializationType(MapperConfig<?> config, Annotated a2, JavaType baseType) throws JsonMappingException {
        JavaType t2 = this._secondary.refineSerializationType(config, a2, baseType);
        return this._primary.refineSerializationType(config, a2, t2);
    }

    @Override
    @Deprecated
    public Class<?> findSerializationType(Annotated a2) {
        Class<?> r2 = this._primary.findSerializationType(a2);
        return r2 == null ? this._secondary.findSerializationType(a2) : r2;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationKeyType(Annotated am2, JavaType baseType) {
        Class<?> r2 = this._primary.findSerializationKeyType(am2, baseType);
        return r2 == null ? this._secondary.findSerializationKeyType(am2, baseType) : r2;
    }

    @Override
    @Deprecated
    public Class<?> findSerializationContentType(Annotated am2, JavaType baseType) {
        Class<?> r2 = this._primary.findSerializationContentType(am2, baseType);
        return r2 == null ? this._secondary.findSerializationContentType(am2, baseType) : r2;
    }

    @Override
    public String[] findSerializationPropertyOrder(AnnotatedClass ac2) {
        String[] r2 = this._primary.findSerializationPropertyOrder(ac2);
        return r2 == null ? this._secondary.findSerializationPropertyOrder(ac2) : r2;
    }

    @Override
    public Boolean findSerializationSortAlphabetically(Annotated ann2) {
        Boolean r2 = this._primary.findSerializationSortAlphabetically(ann2);
        return r2 == null ? this._secondary.findSerializationSortAlphabetically(ann2) : r2;
    }

    @Override
    public void findAndAddVirtualProperties(MapperConfig<?> config, AnnotatedClass ac2, List<BeanPropertyWriter> properties) {
        this._primary.findAndAddVirtualProperties(config, ac2, properties);
        this._secondary.findAndAddVirtualProperties(config, ac2, properties);
    }

    @Override
    public PropertyName findNameForSerialization(Annotated a2) {
        PropertyName n2;
        PropertyName n3 = this._primary.findNameForSerialization(a2);
        if (n3 == null) {
            n3 = this._secondary.findNameForSerialization(a2);
        } else if (n3 == PropertyName.USE_DEFAULT && (n2 = this._secondary.findNameForSerialization(a2)) != null) {
            n3 = n2;
        }
        return n3;
    }

    @Override
    public Boolean hasAsValue(Annotated a2) {
        Boolean b2 = this._primary.hasAsValue(a2);
        if (b2 == null) {
            b2 = this._secondary.hasAsValue(a2);
        }
        return b2;
    }

    @Override
    public Boolean hasAnyGetter(Annotated a2) {
        Boolean b2 = this._primary.hasAnyGetter(a2);
        if (b2 == null) {
            b2 = this._secondary.hasAnyGetter(a2);
        }
        return b2;
    }

    @Override
    public String[] findEnumValues(Class<?> enumType, Enum<?>[] enumValues, String[] names) {
        names = this._secondary.findEnumValues(enumType, enumValues, names);
        names = this._primary.findEnumValues(enumType, enumValues, names);
        return names;
    }

    @Override
    public Enum<?> findDefaultEnumValue(Class<Enum<?>> enumCls) {
        Enum<?> en2 = this._primary.findDefaultEnumValue(enumCls);
        return en2 == null ? this._secondary.findDefaultEnumValue(enumCls) : en2;
    }

    @Override
    @Deprecated
    public String findEnumValue(Enum<?> value) {
        String r2 = this._primary.findEnumValue(value);
        return r2 == null ? this._secondary.findEnumValue(value) : r2;
    }

    @Override
    @Deprecated
    public boolean hasAsValueAnnotation(AnnotatedMethod am2) {
        return this._primary.hasAsValueAnnotation(am2) || this._secondary.hasAsValueAnnotation(am2);
    }

    @Override
    @Deprecated
    public boolean hasAnyGetterAnnotation(AnnotatedMethod am2) {
        return this._primary.hasAnyGetterAnnotation(am2) || this._secondary.hasAnyGetterAnnotation(am2);
    }

    @Override
    public Object findDeserializer(Annotated a2) {
        Object r2 = this._primary.findDeserializer(a2);
        if (this._isExplicitClassOrOb(r2, JsonDeserializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findDeserializer(a2), JsonDeserializer.None.class);
    }

    @Override
    public Object findKeyDeserializer(Annotated a2) {
        Object r2 = this._primary.findKeyDeserializer(a2);
        if (this._isExplicitClassOrOb(r2, KeyDeserializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findKeyDeserializer(a2), KeyDeserializer.None.class);
    }

    @Override
    public Object findContentDeserializer(Annotated am2) {
        Object r2 = this._primary.findContentDeserializer(am2);
        if (this._isExplicitClassOrOb(r2, JsonDeserializer.None.class)) {
            return r2;
        }
        return this._explicitClassOrOb(this._secondary.findContentDeserializer(am2), JsonDeserializer.None.class);
    }

    @Override
    public Object findDeserializationConverter(Annotated a2) {
        Object ob2 = this._primary.findDeserializationConverter(a2);
        return ob2 == null ? this._secondary.findDeserializationConverter(a2) : ob2;
    }

    @Override
    public Object findDeserializationContentConverter(AnnotatedMember a2) {
        Object ob2 = this._primary.findDeserializationContentConverter(a2);
        return ob2 == null ? this._secondary.findDeserializationContentConverter(a2) : ob2;
    }

    @Override
    public JavaType refineDeserializationType(MapperConfig<?> config, Annotated a2, JavaType baseType) throws JsonMappingException {
        JavaType t2 = this._secondary.refineDeserializationType(config, a2, baseType);
        return this._primary.refineDeserializationType(config, a2, t2);
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationType(Annotated am2, JavaType baseType) {
        Class<?> r2 = this._primary.findDeserializationType(am2, baseType);
        return r2 != null ? r2 : this._secondary.findDeserializationType(am2, baseType);
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated am2, JavaType baseKeyType) {
        Class<?> result = this._primary.findDeserializationKeyType(am2, baseKeyType);
        return result == null ? this._secondary.findDeserializationKeyType(am2, baseKeyType) : result;
    }

    @Override
    @Deprecated
    public Class<?> findDeserializationContentType(Annotated am2, JavaType baseContentType) {
        Class<?> result = this._primary.findDeserializationContentType(am2, baseContentType);
        return result == null ? this._secondary.findDeserializationContentType(am2, baseContentType) : result;
    }

    @Override
    public Object findValueInstantiator(AnnotatedClass ac2) {
        Object result = this._primary.findValueInstantiator(ac2);
        return result == null ? this._secondary.findValueInstantiator(ac2) : result;
    }

    @Override
    public Class<?> findPOJOBuilder(AnnotatedClass ac2) {
        Class<?> result = this._primary.findPOJOBuilder(ac2);
        return result == null ? this._secondary.findPOJOBuilder(ac2) : result;
    }

    @Override
    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac2) {
        JsonPOJOBuilder.Value result = this._primary.findPOJOBuilderConfig(ac2);
        return result == null ? this._secondary.findPOJOBuilderConfig(ac2) : result;
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a2) {
        PropertyName n2;
        PropertyName n3 = this._primary.findNameForDeserialization(a2);
        if (n3 == null) {
            n3 = this._secondary.findNameForDeserialization(a2);
        } else if (n3 == PropertyName.USE_DEFAULT && (n2 = this._secondary.findNameForDeserialization(a2)) != null) {
            n3 = n2;
        }
        return n3;
    }

    @Override
    public Boolean hasAnySetter(Annotated a2) {
        Boolean b2 = this._primary.hasAnySetter(a2);
        if (b2 == null) {
            b2 = this._secondary.hasAnySetter(a2);
        }
        return b2;
    }

    @Override
    public JsonSetter.Value findSetterInfo(Annotated a2) {
        JsonSetter.Value v2 = this._secondary.findSetterInfo(a2);
        JsonSetter.Value v1 = this._primary.findSetterInfo(a2);
        return v2 == null ? v1 : v2.withOverrides(v1);
    }

    @Override
    public Boolean findMergeInfo(Annotated a2) {
        Boolean b2 = this._primary.findMergeInfo(a2);
        if (b2 == null) {
            b2 = this._secondary.findMergeInfo(a2);
        }
        return b2;
    }

    @Override
    @Deprecated
    public boolean hasCreatorAnnotation(Annotated a2) {
        return this._primary.hasCreatorAnnotation(a2) || this._secondary.hasCreatorAnnotation(a2);
    }

    @Override
    @Deprecated
    public JsonCreator.Mode findCreatorBinding(Annotated a2) {
        JsonCreator.Mode mode = this._primary.findCreatorBinding(a2);
        if (mode != null) {
            return mode;
        }
        return this._secondary.findCreatorBinding(a2);
    }

    @Override
    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a2) {
        JsonCreator.Mode mode = this._primary.findCreatorAnnotation(config, a2);
        return mode == null ? this._secondary.findCreatorAnnotation(config, a2) : mode;
    }

    @Override
    @Deprecated
    public boolean hasAnySetterAnnotation(AnnotatedMethod am2) {
        return this._primary.hasAnySetterAnnotation(am2) || this._secondary.hasAnySetterAnnotation(am2);
    }

    protected boolean _isExplicitClassOrOb(Object maybeCls, Class<?> implicit) {
        if (maybeCls == null || maybeCls == implicit) {
            return false;
        }
        if (maybeCls instanceof Class) {
            return !ClassUtil.isBogusClass((Class)maybeCls);
        }
        return true;
    }

    protected Object _explicitClassOrOb(Object maybeCls, Class<?> implicit) {
        if (maybeCls == null || maybeCls == implicit) {
            return null;
        }
        if (maybeCls instanceof Class && ClassUtil.isBogusClass((Class)maybeCls)) {
            return null;
        }
        return maybeCls;
    }
}

