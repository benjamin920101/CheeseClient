/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassStack;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.type.TypeParser;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class TypeFactory
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final Class<?> CLS_STRING = String.class;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_COMPARABLE = Comparable.class;
    private static final Class<?> CLS_CLASS = Class.class;
    private static final Class<?> CLS_ENUM = Enum.class;
    private static final Class<?> CLS_JSON_NODE = JsonNode.class;
    private static final Class<?> CLS_BOOL = Boolean.TYPE;
    private static final Class<?> CLS_INT = Integer.TYPE;
    private static final Class<?> CLS_LONG = Long.TYPE;
    protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
    protected static final SimpleType CORE_TYPE_INT = new SimpleType(CLS_INT);
    protected static final SimpleType CORE_TYPE_LONG = new SimpleType(CLS_LONG);
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(CLS_STRING);
    protected static final SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
    protected static final SimpleType CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
    protected static final SimpleType CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
    protected static final SimpleType CORE_TYPE_CLASS = new SimpleType(CLS_CLASS);
    protected static final SimpleType CORE_TYPE_JSON_NODE = new SimpleType(CLS_JSON_NODE);
    protected final LRUMap<Object, JavaType> _typeCache;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final ClassLoader _classLoader;

    private TypeFactory() {
        this(null);
    }

    protected TypeFactory(LRUMap<Object, JavaType> typeCache) {
        if (typeCache == null) {
            typeCache = new LRUMap(16, 200);
        }
        this._typeCache = typeCache;
        this._parser = new TypeParser(this);
        this._modifiers = null;
        this._classLoader = null;
    }

    protected TypeFactory(LRUMap<Object, JavaType> typeCache, TypeParser p2, TypeModifier[] mods, ClassLoader classLoader) {
        if (typeCache == null) {
            typeCache = new LRUMap(16, 200);
        }
        this._typeCache = typeCache;
        this._parser = p2.withFactory(this);
        this._modifiers = mods;
        this._classLoader = classLoader;
    }

    public TypeFactory withModifier(TypeModifier mod) {
        TypeModifier[] mods;
        LRUMap<Object, JavaType> typeCache = this._typeCache;
        if (mod == null) {
            mods = null;
            typeCache = null;
        } else if (this._modifiers == null) {
            mods = new TypeModifier[]{mod};
            typeCache = null;
        } else {
            mods = ArrayBuilders.insertInListNoDup(this._modifiers, mod);
        }
        return new TypeFactory(typeCache, this._parser, mods, this._classLoader);
    }

    public TypeFactory withClassLoader(ClassLoader classLoader) {
        return new TypeFactory(this._typeCache, this._parser, this._modifiers, classLoader);
    }

    public TypeFactory withCache(LRUMap<Object, JavaType> cache) {
        return new TypeFactory(cache, this._parser, this._modifiers, this._classLoader);
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public void clearCache() {
        this._typeCache.clear();
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    public static JavaType unknownType() {
        return TypeFactory.defaultInstance()._unknownType();
    }

    public static Class<?> rawClass(Type t2) {
        if (t2 instanceof Class) {
            return (Class)t2;
        }
        return TypeFactory.defaultInstance().constructType(t2).getRawClass();
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        Class<?> prim;
        if (className.indexOf(46) < 0 && (prim = this._findPrimitive(className)) != null) {
            return prim;
        }
        Throwable prob = null;
        ClassLoader loader = this.getClassLoader();
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        if (loader != null) {
            try {
                return this.classForName(className, true, loader);
            }
            catch (Exception e2) {
                prob = ClassUtil.getRootCause(e2);
            }
        }
        try {
            return this.classForName(className);
        }
        catch (Exception e3) {
            if (prob == null) {
                prob = ClassUtil.getRootCause(e3);
            }
            ClassUtil.throwIfRTE(prob);
            throw new ClassNotFoundException(prob.getMessage(), prob);
        }
    }

    protected Class<?> classForName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
        return Class.forName(name, true, loader);
    }

    protected Class<?> classForName(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }

    protected Class<?> _findPrimitive(String className) {
        if ("int".equals(className)) {
            return Integer.TYPE;
        }
        if ("long".equals(className)) {
            return Long.TYPE;
        }
        if ("float".equals(className)) {
            return Float.TYPE;
        }
        if ("double".equals(className)) {
            return Double.TYPE;
        }
        if ("boolean".equals(className)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(className)) {
            return Byte.TYPE;
        }
        if ("char".equals(className)) {
            return Character.TYPE;
        }
        if ("short".equals(className)) {
            return Short.TYPE;
        }
        if ("void".equals(className)) {
            return Void.TYPE;
        }
        return null;
    }

    /*
     * Unable to fully structure code
     */
    public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) {
        block8: {
            block10: {
                block9: {
                    block7: {
                        rawBase = baseType.getRawClass();
                        if (rawBase == subclass) {
                            return baseType;
                        }
                        if (rawBase != Object.class) break block7;
                        newType = this._fromClass(null, subclass, TypeFactory.EMPTY_BINDINGS);
                        break block8;
                    }
                    if (!rawBase.isAssignableFrom(subclass)) {
                        throw new IllegalArgumentException(String.format("Class %s not subtype of %s", new Object[]{subclass.getName(), baseType}));
                    }
                    if (!baseType.getBindings().isEmpty()) break block9;
                    newType = this._fromClass(null, subclass, TypeFactory.EMPTY_BINDINGS);
                    break block8;
                }
                if (!baseType.isContainerType()) ** GOTO lbl26
                if (!baseType.isMapLikeType()) break block10;
                if (subclass != HashMap.class && subclass != LinkedHashMap.class && subclass != EnumMap.class && subclass != TreeMap.class) ** GOTO lbl26
                newType = this._fromClass(null, subclass, TypeBindings.create(subclass, baseType.getKeyType(), baseType.getContentType()));
                break block8;
            }
            if (!baseType.isCollectionLikeType()) ** GOTO lbl26
            if (subclass == ArrayList.class || subclass == LinkedList.class || subclass == HashSet.class || subclass == TreeSet.class) {
                newType = this._fromClass(null, subclass, TypeBindings.create(subclass, baseType.getContentType()));
            } else {
                if (rawBase == EnumSet.class) {
                    return baseType;
                }
lbl26:
                // 5 sources

                if ((typeParamCount = subclass.getTypeParameters().length) == 0) {
                    newType = this._fromClass(null, subclass, TypeFactory.EMPTY_BINDINGS);
                } else {
                    tb = this._bindingsForSubtype(baseType, typeParamCount, subclass);
                    newType = this._fromClass(null, subclass, tb);
                }
            }
        }
        newType = newType.withHandlersFrom(baseType);
        return newType;
    }

    private TypeBindings _bindingsForSubtype(JavaType baseType, int typeParamCount, Class<?> subclass) {
        JavaType[] placeholders = new PlaceholderForType[typeParamCount];
        for (int i2 = 0; i2 < typeParamCount; ++i2) {
            placeholders[i2] = new PlaceholderForType(i2);
        }
        TypeBindings b2 = TypeBindings.create(subclass, placeholders);
        JavaType tmpSub = this._fromClass(null, subclass, b2);
        JavaType baseWithPlaceholders = tmpSub.findSuperType(baseType.getRawClass());
        if (baseWithPlaceholders == null) {
            throw new IllegalArgumentException(String.format("Internal error: unable to locate supertype (%s) from resolved subtype %s", baseType.getRawClass().getName(), subclass.getName()));
        }
        String error = this._resolveTypePlaceholders(baseType, baseWithPlaceholders);
        if (error != null) {
            throw new IllegalArgumentException("Failed to specialize base type " + baseType.toCanonical() + " as " + subclass.getName() + ", problem: " + error);
        }
        JavaType[] typeParams = new JavaType[typeParamCount];
        for (int i3 = 0; i3 < typeParamCount; ++i3) {
            JavaType t2 = ((PlaceholderForType)placeholders[i3]).actualType();
            if (t2 == null) {
                t2 = TypeFactory.unknownType();
            }
            typeParams[i3] = t2;
        }
        return TypeBindings.create(subclass, typeParams);
    }

    private String _resolveTypePlaceholders(JavaType sourceType, JavaType actualType) throws IllegalArgumentException {
        List<JavaType> expectedTypes = sourceType.getBindings().getTypeParameters();
        List<JavaType> actualTypes = actualType.getBindings().getTypeParameters();
        int len = expectedTypes.size();
        for (int i2 = 0; i2 < len; ++i2) {
            JavaType act2;
            JavaType exp = expectedTypes.get(i2);
            if (this._verifyAndResolvePlaceholders(exp, act2 = actualTypes.get(i2)) || exp.hasRawClass(Object.class) || i2 == 0 && sourceType.isMapLikeType() && act2.hasRawClass(Object.class) || exp.isInterface() && exp.isTypeOrSuperTypeOf(act2.getRawClass())) continue;
            return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", i2 + 1, len, exp.toCanonical(), act2.toCanonical());
        }
        return null;
    }

    private boolean _verifyAndResolvePlaceholders(JavaType exp, JavaType act2) {
        if (act2 instanceof PlaceholderForType) {
            ((PlaceholderForType)act2).actualType(exp);
            return true;
        }
        if (exp.getRawClass() != act2.getRawClass()) {
            return false;
        }
        List<JavaType> expectedTypes = exp.getBindings().getTypeParameters();
        List<JavaType> actualTypes = act2.getBindings().getTypeParameters();
        int len = expectedTypes.size();
        for (int i2 = 0; i2 < len; ++i2) {
            JavaType act22;
            JavaType exp2 = expectedTypes.get(i2);
            if (this._verifyAndResolvePlaceholders(exp2, act22 = actualTypes.get(i2))) continue;
            return false;
        }
        return true;
    }

    public JavaType constructGeneralizedType(JavaType baseType, Class<?> superClass) {
        Class<?> rawBase = baseType.getRawClass();
        if (rawBase == superClass) {
            return baseType;
        }
        JavaType superType = baseType.findSuperType(superClass);
        if (superType == null) {
            if (!superClass.isAssignableFrom(rawBase)) {
                throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", superClass.getName(), baseType));
            }
            throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", superClass.getName(), baseType));
        }
        return superType;
    }

    public JavaType constructFromCanonical(String canonical) throws IllegalArgumentException {
        return this._parser.parse(canonical);
    }

    public JavaType[] findTypeParameters(JavaType type, Class<?> expType) {
        JavaType match = type.findSuperType(expType);
        if (match == null) {
            return NO_TYPES;
        }
        return match.getBindings().typeParameterArray();
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> clz, Class<?> expType, TypeBindings bindings) {
        return this.findTypeParameters(this.constructType(clz, bindings), expType);
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> clz, Class<?> expType) {
        return this.findTypeParameters(this.constructType(clz), expType);
    }

    public JavaType moreSpecificType(JavaType type1, JavaType type2) {
        Class<?> raw2;
        if (type1 == null) {
            return type2;
        }
        if (type2 == null) {
            return type1;
        }
        Class<?> raw1 = type1.getRawClass();
        if (raw1 == (raw2 = type2.getRawClass())) {
            return type1;
        }
        if (raw1.isAssignableFrom(raw2)) {
            return type2;
        }
        return type1;
    }

    public JavaType constructType(Type type) {
        return this._fromAny(null, type, EMPTY_BINDINGS);
    }

    public JavaType constructType(Type type, TypeBindings bindings) {
        return this._fromAny(null, type, bindings);
    }

    public JavaType constructType(TypeReference<?> typeRef) {
        return this._fromAny(null, typeRef.getType(), EMPTY_BINDINGS);
    }

    @Deprecated
    public JavaType constructType(Type type, Class<?> contextClass) {
        JavaType contextType = contextClass == null ? null : this.constructType(contextClass);
        return this.constructType(type, contextType);
    }

    @Deprecated
    public JavaType constructType(Type type, JavaType contextType) {
        TypeBindings bindings;
        if (contextType == null) {
            bindings = EMPTY_BINDINGS;
        } else {
            bindings = contextType.getBindings();
            if (type.getClass() != Class.class) {
                while (bindings.isEmpty() && (contextType = contextType.getSuperClass()) != null) {
                    bindings = contextType.getBindings();
                }
            }
        }
        return this._fromAny(null, type, bindings);
    }

    public ArrayType constructArrayType(Class<?> elementType) {
        return ArrayType.construct(this._fromAny(null, elementType, null), null);
    }

    public ArrayType constructArrayType(JavaType elementType) {
        return ArrayType.construct(elementType, null);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return this.constructCollectionType(collectionClass, this._fromClass(null, elementClass, EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, JavaType elementType) {
        JavaType t2;
        JavaType realET;
        TypeBindings bindings = TypeBindings.createIfNeeded(collectionClass, elementType);
        CollectionType result = (CollectionType)this._fromClass(null, collectionClass, bindings);
        if (bindings.isEmpty() && elementType != null && !(realET = (t2 = result.findSuperType(Collection.class)).getContentType()).equals(elementType)) {
            throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", ClassUtil.nameOf(collectionClass), elementType, realET));
        }
        return result;
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> collectionClass, Class<?> elementClass) {
        return this.constructCollectionLikeType(collectionClass, this._fromClass(null, elementClass, EMPTY_BINDINGS));
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> collectionClass, JavaType elementType) {
        JavaType type = this._fromClass(null, collectionClass, TypeBindings.createIfNeeded(collectionClass, elementType));
        if (type instanceof CollectionLikeType) {
            return (CollectionLikeType)type;
        }
        return CollectionLikeType.upgradeFrom(type, elementType);
    }

    public MapType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        JavaType kt;
        JavaType vt2;
        if (mapClass == Properties.class) {
            kt = vt2 = CORE_TYPE_STRING;
        } else {
            kt = this._fromClass(null, keyClass, EMPTY_BINDINGS);
            vt2 = this._fromClass(null, valueClass, EMPTY_BINDINGS);
        }
        return this.constructMapType(mapClass, kt, vt2);
    }

    public MapType constructMapType(Class<? extends Map> mapClass, JavaType keyType, JavaType valueType) {
        TypeBindings bindings = TypeBindings.createIfNeeded(mapClass, new JavaType[]{keyType, valueType});
        MapType result = (MapType)this._fromClass(null, mapClass, bindings);
        if (bindings.isEmpty()) {
            JavaType t2 = result.findSuperType(Map.class);
            JavaType realKT = t2.getKeyType();
            if (!realKT.equals(keyType)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", ClassUtil.nameOf(mapClass), keyType, realKT));
            }
            JavaType realVT = t2.getContentType();
            if (!realVT.equals(valueType)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", ClassUtil.nameOf(mapClass), valueType, realVT));
            }
        }
        return result;
    }

    public MapLikeType constructMapLikeType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return this.constructMapLikeType(mapClass, this._fromClass(null, keyClass, EMPTY_BINDINGS), this._fromClass(null, valueClass, EMPTY_BINDINGS));
    }

    public MapLikeType constructMapLikeType(Class<?> mapClass, JavaType keyType, JavaType valueType) {
        JavaType type = this._fromClass(null, mapClass, TypeBindings.createIfNeeded(mapClass, new JavaType[]{keyType, valueType}));
        if (type instanceof MapLikeType) {
            return (MapLikeType)type;
        }
        return MapLikeType.upgradeFrom(type, keyType, valueType);
    }

    public JavaType constructSimpleType(Class<?> rawType, JavaType[] parameterTypes) {
        return this._fromClass(null, rawType, TypeBindings.create(rawType, parameterTypes));
    }

    @Deprecated
    public JavaType constructSimpleType(Class<?> rawType, Class<?> parameterTarget, JavaType[] parameterTypes) {
        return this.constructSimpleType(rawType, parameterTypes);
    }

    public JavaType constructReferenceType(Class<?> rawType, JavaType referredType) {
        return ReferenceType.construct(rawType, null, null, null, referredType);
    }

    @Deprecated
    public JavaType uncheckedSimpleType(Class<?> cls) {
        return this._constructSimple(cls, EMPTY_BINDINGS, null, null);
    }

    public JavaType constructParametricType(Class<?> parametrized, Class<?> ... parameterClasses) {
        int len = parameterClasses.length;
        JavaType[] pt2 = new JavaType[len];
        for (int i2 = 0; i2 < len; ++i2) {
            pt2[i2] = this._fromClass(null, parameterClasses[i2], EMPTY_BINDINGS);
        }
        return this.constructParametricType(parametrized, pt2);
    }

    public JavaType constructParametricType(Class<?> rawType, JavaType ... parameterTypes) {
        return this._fromClass(null, rawType, TypeBindings.create(rawType, parameterTypes));
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> parametrized, Class<?> parametersFor, JavaType ... parameterTypes) {
        return this.constructParametricType(parametrized, parameterTypes);
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> parametrized, Class<?> parametersFor, Class<?> ... parameterClasses) {
        return this.constructParametricType(parametrized, parameterClasses);
    }

    public CollectionType constructRawCollectionType(Class<? extends Collection> collectionClass) {
        return this.constructCollectionType(collectionClass, TypeFactory.unknownType());
    }

    public CollectionLikeType constructRawCollectionLikeType(Class<?> collectionClass) {
        return this.constructCollectionLikeType(collectionClass, TypeFactory.unknownType());
    }

    public MapType constructRawMapType(Class<? extends Map> mapClass) {
        return this.constructMapType(mapClass, TypeFactory.unknownType(), TypeFactory.unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class<?> mapClass) {
        return this.constructMapLikeType(mapClass, TypeFactory.unknownType(), TypeFactory.unknownType());
    }

    private JavaType _mapType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType kt;
        JavaType vt2;
        if (rawClass == Properties.class) {
            kt = vt2 = CORE_TYPE_STRING;
        } else {
            List<JavaType> typeParams = bindings.getTypeParameters();
            switch (typeParams.size()) {
                case 0: {
                    kt = vt2 = this._unknownType();
                    break;
                }
                case 2: {
                    kt = typeParams.get(0);
                    vt2 = typeParams.get(1);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Strange Map type " + rawClass.getName() + ": cannot determine type parameters");
                }
            }
        }
        return MapType.construct(rawClass, bindings, superClass, superInterfaces, kt, vt2);
    }

    private JavaType _collectionType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType ct2;
        List<JavaType> typeParams = bindings.getTypeParameters();
        if (typeParams.isEmpty()) {
            ct2 = this._unknownType();
        } else if (typeParams.size() == 1) {
            ct2 = typeParams.get(0);
        } else {
            throw new IllegalArgumentException("Strange Collection type " + rawClass.getName() + ": cannot determine type parameters");
        }
        return CollectionType.construct(rawClass, bindings, superClass, superInterfaces, ct2);
    }

    private JavaType _referenceType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType ct2;
        List<JavaType> typeParams = bindings.getTypeParameters();
        if (typeParams.isEmpty()) {
            ct2 = this._unknownType();
        } else if (typeParams.size() == 1) {
            ct2 = typeParams.get(0);
        } else {
            throw new IllegalArgumentException("Strange Reference type " + rawClass.getName() + ": cannot determine type parameters");
        }
        return ReferenceType.construct(rawClass, bindings, superClass, superInterfaces, ct2);
    }

    protected JavaType _constructSimple(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType result;
        if (bindings.isEmpty() && (result = this._findWellKnownSimple(raw)) != null) {
            return result;
        }
        return this._newSimpleType(raw, bindings, superClass, superInterfaces);
    }

    protected JavaType _newSimpleType(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return new SimpleType(raw, bindings, superClass, superInterfaces);
    }

    protected JavaType _unknownType() {
        return CORE_TYPE_OBJECT;
    }

    protected JavaType _findWellKnownSimple(Class<?> clz) {
        if (clz.isPrimitive()) {
            if (clz == CLS_BOOL) {
                return CORE_TYPE_BOOL;
            }
            if (clz == CLS_INT) {
                return CORE_TYPE_INT;
            }
            if (clz == CLS_LONG) {
                return CORE_TYPE_LONG;
            }
        } else {
            if (clz == CLS_STRING) {
                return CORE_TYPE_STRING;
            }
            if (clz == CLS_OBJECT) {
                return CORE_TYPE_OBJECT;
            }
            if (clz == CLS_JSON_NODE) {
                return CORE_TYPE_JSON_NODE;
            }
        }
        return null;
    }

    protected JavaType _fromAny(ClassStack context, Type type, TypeBindings bindings) {
        JavaType resultType;
        if (type instanceof Class) {
            resultType = this._fromClass(context, (Class)type, EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            resultType = this._fromParamType(context, (ParameterizedType)type, bindings);
        } else {
            if (type instanceof JavaType) {
                return (JavaType)type;
            }
            if (type instanceof GenericArrayType) {
                resultType = this._fromArrayType(context, (GenericArrayType)type, bindings);
            } else if (type instanceof TypeVariable) {
                resultType = this._fromVariable(context, (TypeVariable)type, bindings);
            } else if (type instanceof WildcardType) {
                resultType = this._fromWildcard(context, (WildcardType)type, bindings);
            } else {
                throw new IllegalArgumentException("Unrecognized Type: " + (type == null ? "[null]" : type.toString()));
            }
        }
        if (this._modifiers != null) {
            TypeBindings b2 = resultType.getBindings();
            if (b2 == null) {
                b2 = EMPTY_BINDINGS;
            }
            for (TypeModifier mod : this._modifiers) {
                JavaType t2 = mod.modifyType(resultType, type, b2, this);
                if (t2 == null) {
                    throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", mod, mod.getClass().getName(), resultType));
                }
                resultType = t2;
            }
        }
        return resultType;
    }

    protected JavaType _fromClass(ClassStack context, Class<?> rawType, TypeBindings bindings) {
        JavaType result = this._findWellKnownSimple(rawType);
        if (result != null) {
            return result;
        }
        Object key = bindings == null || bindings.isEmpty() ? rawType : bindings.asKey(rawType);
        result = this._typeCache.get(key);
        if (result != null) {
            return result;
        }
        if (context == null) {
            context = new ClassStack(rawType);
        } else {
            ClassStack prev = context.find(rawType);
            if (prev != null) {
                ResolvedRecursiveType selfRef = new ResolvedRecursiveType(rawType, EMPTY_BINDINGS);
                prev.addSelfReference(selfRef);
                return selfRef;
            }
            context = context.child(rawType);
        }
        if (rawType.isArray()) {
            result = ArrayType.construct(this._fromAny(context, rawType.getComponentType(), bindings), bindings);
        } else {
            JavaType[] superInterfaces;
            JavaType superClass;
            if (rawType.isInterface()) {
                superClass = null;
                superInterfaces = this._resolveSuperInterfaces(context, rawType, bindings);
            } else {
                superClass = this._resolveSuperClass(context, rawType, bindings);
                superInterfaces = this._resolveSuperInterfaces(context, rawType, bindings);
            }
            if (rawType == Properties.class) {
                result = MapType.construct(rawType, bindings, superClass, superInterfaces, CORE_TYPE_STRING, CORE_TYPE_STRING);
            } else if (superClass != null) {
                result = superClass.refine(rawType, bindings, superClass, superInterfaces);
            }
            if (result == null && (result = this._fromWellKnownClass(context, rawType, bindings, superClass, superInterfaces)) == null && (result = this._fromWellKnownInterface(context, rawType, bindings, superClass, superInterfaces)) == null) {
                result = this._newSimpleType(rawType, bindings, superClass, superInterfaces);
            }
        }
        context.resolveSelfReferences(result);
        if (!result.hasHandlers()) {
            this._typeCache.putIfAbsent(key, result);
        }
        return result;
    }

    protected JavaType _resolveSuperClass(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
        Type parent = ClassUtil.getGenericSuperclass(rawType);
        if (parent == null) {
            return null;
        }
        return this._fromAny(context, parent, parentBindings);
    }

    protected JavaType[] _resolveSuperInterfaces(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
        Type[] types = ClassUtil.getGenericInterfaces(rawType);
        if (types == null || types.length == 0) {
            return NO_TYPES;
        }
        int len = types.length;
        JavaType[] resolved = new JavaType[len];
        for (int i2 = 0; i2 < len; ++i2) {
            Type type = types[i2];
            resolved[i2] = this._fromAny(context, type, parentBindings);
        }
        return resolved;
    }

    protected JavaType _fromWellKnownClass(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        if (bindings == null) {
            bindings = EMPTY_BINDINGS;
        }
        if (rawType == Map.class) {
            return this._mapType(rawType, bindings, superClass, superInterfaces);
        }
        if (rawType == Collection.class) {
            return this._collectionType(rawType, bindings, superClass, superInterfaces);
        }
        if (rawType == AtomicReference.class) {
            return this._referenceType(rawType, bindings, superClass, superInterfaces);
        }
        return null;
    }

    protected JavaType _fromWellKnownInterface(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        int intCount = superInterfaces.length;
        for (int i2 = 0; i2 < intCount; ++i2) {
            JavaType result = superInterfaces[i2].refine(rawType, bindings, superClass, superInterfaces);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    protected JavaType _fromParamType(ClassStack context, ParameterizedType ptype, TypeBindings parentBindings) {
        TypeBindings newBindings;
        int paramCount;
        Class rawType = (Class)ptype.getRawType();
        if (rawType == CLS_ENUM) {
            return CORE_TYPE_ENUM;
        }
        if (rawType == CLS_COMPARABLE) {
            return CORE_TYPE_COMPARABLE;
        }
        if (rawType == CLS_CLASS) {
            return CORE_TYPE_CLASS;
        }
        Type[] args = ptype.getActualTypeArguments();
        int n2 = paramCount = args == null ? 0 : args.length;
        if (paramCount == 0) {
            newBindings = EMPTY_BINDINGS;
        } else {
            JavaType[] pt2 = new JavaType[paramCount];
            for (int i2 = 0; i2 < paramCount; ++i2) {
                pt2[i2] = this._fromAny(context, args[i2], parentBindings);
            }
            newBindings = TypeBindings.create(rawType, pt2);
        }
        return this._fromClass(context, rawType, newBindings);
    }

    protected JavaType _fromArrayType(ClassStack context, GenericArrayType type, TypeBindings bindings) {
        JavaType elementType = this._fromAny(context, type.getGenericComponentType(), bindings);
        return ArrayType.construct(elementType, bindings);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected JavaType _fromVariable(ClassStack context, TypeVariable<?> var, TypeBindings bindings) {
        Type[] bounds;
        String name = var.getName();
        if (bindings == null) {
            throw new IllegalArgumentException("Null `bindings` passed (type variable \"" + name + "\")");
        }
        JavaType type = bindings.findBoundType(name);
        if (type != null) {
            return type;
        }
        if (bindings.hasUnbound(name)) {
            return CORE_TYPE_OBJECT;
        }
        bindings = bindings.withUnboundVariable(name);
        TypeVariable<?> typeVariable = var;
        synchronized (typeVariable) {
            bounds = var.getBounds();
        }
        return this._fromAny(context, bounds[0], bindings);
    }

    protected JavaType _fromWildcard(ClassStack context, WildcardType type, TypeBindings bindings) {
        return this._fromAny(context, type.getUpperBounds()[0], bindings);
    }
}

