/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.CollectorBase;
import com.fasterxml.jackson.databind.introspect.MemberKey;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedMethodCollector
extends CollectorBase {
    private final ClassIntrospector.MixInResolver _mixInResolver;

    AnnotatedMethodCollector(AnnotationIntrospector intr, ClassIntrospector.MixInResolver mixins) {
        super(intr);
        this._mixInResolver = intr == null ? null : mixins;
    }

    public static AnnotatedMethodMap collectMethods(AnnotationIntrospector intr, TypeResolutionContext tc2, ClassIntrospector.MixInResolver mixins, TypeFactory types, JavaType type, List<JavaType> superTypes, Class<?> primaryMixIn) {
        return new AnnotatedMethodCollector(intr, mixins).collect(types, tc2, type, superTypes, primaryMixIn);
    }

    AnnotatedMethodMap collect(TypeFactory typeFactory, TypeResolutionContext tc2, JavaType mainType, List<JavaType> superTypes, Class<?> primaryMixIn) {
        Object mixin;
        LinkedHashMap<MemberKey, MethodBuilder> methods = new LinkedHashMap<MemberKey, MethodBuilder>();
        this._addMemberMethods(tc2, mainType.getRawClass(), methods, primaryMixIn);
        for (JavaType type : superTypes) {
            Class<?> clazz = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(type.getRawClass());
            this._addMemberMethods(new TypeResolutionContext.Basic(typeFactory, type.getBindings()), type.getRawClass(), methods, clazz);
        }
        boolean checkJavaLangObject = false;
        if (this._mixInResolver != null && (mixin = this._mixInResolver.findMixInClassFor(Object.class)) != null) {
            this._addMethodMixIns(tc2, mainType.getRawClass(), (Map<MemberKey, MethodBuilder>)methods, (Class<?>)mixin);
            checkJavaLangObject = true;
        }
        if (checkJavaLangObject && this._intr != null && !methods.isEmpty()) {
            for (Map.Entry entry : methods.entrySet()) {
                MemberKey k2 = (MemberKey)entry.getKey();
                if (!"hashCode".equals(k2.getName()) || 0 != k2.argCount()) continue;
                try {
                    Method m2 = Object.class.getDeclaredMethod(k2.getName(), new Class[0]);
                    if (m2 == null) continue;
                    MethodBuilder b2 = (MethodBuilder)entry.getValue();
                    b2.annotations = this.collectDefaultAnnotations(b2.annotations, m2.getDeclaredAnnotations());
                    b2.method = m2;
                }
                catch (Exception m2) {}
            }
        }
        if (methods.isEmpty()) {
            return new AnnotatedMethodMap();
        }
        LinkedHashMap<MemberKey, AnnotatedMethod> actual = new LinkedHashMap<MemberKey, AnnotatedMethod>(methods.size());
        for (Map.Entry entry : methods.entrySet()) {
            AnnotatedMethod am2 = ((MethodBuilder)entry.getValue()).build();
            if (am2 == null) continue;
            actual.put((MemberKey)entry.getKey(), am2);
        }
        return new AnnotatedMethodMap(actual);
    }

    private void _addMemberMethods(TypeResolutionContext tc2, Class<?> cls, Map<MemberKey, MethodBuilder> methods, Class<?> mixInCls) {
        if (mixInCls != null) {
            this._addMethodMixIns(tc2, cls, methods, mixInCls);
        }
        if (cls == null) {
            return;
        }
        for (Method m2 : ClassUtil.getClassMethods(cls)) {
            Method old;
            if (!this._isIncludableMemberMethod(m2)) continue;
            MemberKey key = new MemberKey(m2);
            MethodBuilder b2 = methods.get(key);
            if (b2 == null) {
                AnnotationCollector c2 = this._intr == null ? AnnotationCollector.emptyCollector() : this.collectAnnotations(m2.getDeclaredAnnotations());
                methods.put(key, new MethodBuilder(tc2, m2, c2));
                continue;
            }
            if (this._intr != null) {
                b2.annotations = this.collectDefaultAnnotations(b2.annotations, m2.getDeclaredAnnotations());
            }
            if ((old = b2.method) == null) {
                b2.method = m2;
                continue;
            }
            if (!Modifier.isAbstract(old.getModifiers()) || Modifier.isAbstract(m2.getModifiers())) continue;
            b2.method = m2;
            b2.typeContext = tc2;
        }
    }

    protected void _addMethodMixIns(TypeResolutionContext tc2, Class<?> targetClass, Map<MemberKey, MethodBuilder> methods, Class<?> mixInCls) {
        if (this._intr == null) {
            return;
        }
        for (Class<?> mixin : ClassUtil.findRawSuperTypes(mixInCls, targetClass, true)) {
            for (Method m2 : ClassUtil.getDeclaredMethods(mixin)) {
                if (!this._isIncludableMemberMethod(m2)) continue;
                MemberKey key = new MemberKey(m2);
                MethodBuilder b2 = methods.get(key);
                Annotation[] anns = m2.getDeclaredAnnotations();
                if (b2 == null) {
                    methods.put(key, new MethodBuilder(tc2, null, this.collectAnnotations(anns)));
                    continue;
                }
                b2.annotations = this.collectDefaultAnnotations(b2.annotations, anns);
            }
        }
    }

    private boolean _isIncludableMemberMethod(Method m2) {
        if (Modifier.isStatic(m2.getModifiers()) || m2.isSynthetic() || m2.isBridge()) {
            return false;
        }
        int pcount = m2.getParameterTypes().length;
        return pcount <= 2;
    }

    private static final class MethodBuilder {
        public TypeResolutionContext typeContext;
        public Method method;
        public AnnotationCollector annotations;

        public MethodBuilder(TypeResolutionContext tc2, Method m2, AnnotationCollector ann2) {
            this.typeContext = tc2;
            this.method = m2;
            this.annotations = ann2;
        }

        public AnnotatedMethod build() {
            if (this.method == null) {
                return null;
            }
            return new AnnotatedMethod(this.typeContext, this.method, this.annotations.asAnnotationMap(), null);
        }
    }
}

