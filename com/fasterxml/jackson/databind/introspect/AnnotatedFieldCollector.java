/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.CollectorBase;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedFieldCollector
extends CollectorBase {
    private final TypeFactory _typeFactory;
    private final ClassIntrospector.MixInResolver _mixInResolver;

    AnnotatedFieldCollector(AnnotationIntrospector intr, TypeFactory types, ClassIntrospector.MixInResolver mixins) {
        super(intr);
        this._typeFactory = types;
        this._mixInResolver = intr == null ? null : mixins;
    }

    public static List<AnnotatedField> collectFields(AnnotationIntrospector intr, TypeResolutionContext tc2, ClassIntrospector.MixInResolver mixins, TypeFactory types, JavaType type) {
        return new AnnotatedFieldCollector(intr, types, mixins).collect(tc2, type);
    }

    List<AnnotatedField> collect(TypeResolutionContext tc2, JavaType type) {
        Map<String, FieldBuilder> foundFields = this._findFields(tc2, type, null);
        if (foundFields == null) {
            return Collections.emptyList();
        }
        ArrayList<AnnotatedField> result = new ArrayList<AnnotatedField>(foundFields.size());
        for (FieldBuilder b2 : foundFields.values()) {
            result.add(b2.build());
        }
        return result;
    }

    private Map<String, FieldBuilder> _findFields(TypeResolutionContext tc2, JavaType type, Map<String, FieldBuilder> fields) {
        Class<?> mixin;
        JavaType parent = type.getSuperClass();
        if (parent == null) {
            return fields;
        }
        Class<?> cls = type.getRawClass();
        fields = this._findFields(new TypeResolutionContext.Basic(this._typeFactory, parent.getBindings()), parent, fields);
        for (Field f2 : ClassUtil.getDeclaredFields(cls)) {
            if (!this._isIncludableField(f2)) continue;
            if (fields == null) {
                fields = new LinkedHashMap<String, FieldBuilder>();
            }
            FieldBuilder b2 = new FieldBuilder(tc2, f2);
            if (this._intr != null) {
                b2.annotations = this.collectAnnotations(b2.annotations, f2.getDeclaredAnnotations());
            }
            fields.put(f2.getName(), b2);
        }
        if (this._mixInResolver != null && (mixin = this._mixInResolver.findMixInClassFor(cls)) != null) {
            this._addFieldMixIns(mixin, cls, fields);
        }
        return fields;
    }

    private void _addFieldMixIns(Class<?> mixInCls, Class<?> targetClass, Map<String, FieldBuilder> fields) {
        List<Class<?>> parents = ClassUtil.findSuperClasses(mixInCls, targetClass, true);
        for (Class<?> mixin : parents) {
            for (Field mixinField : ClassUtil.getDeclaredFields(mixin)) {
                String name;
                FieldBuilder b2;
                if (!this._isIncludableField(mixinField) || (b2 = fields.get(name = mixinField.getName())) == null) continue;
                b2.annotations = this.collectAnnotations(b2.annotations, mixinField.getDeclaredAnnotations());
            }
        }
    }

    private boolean _isIncludableField(Field f2) {
        if (f2.isSynthetic()) {
            return false;
        }
        int mods = f2.getModifiers();
        return !Modifier.isStatic(mods);
    }

    private static final class FieldBuilder {
        public final TypeResolutionContext typeContext;
        public final Field field;
        public AnnotationCollector annotations;

        public FieldBuilder(TypeResolutionContext tc2, Field f2) {
            this.typeContext = tc2;
            this.field = f2;
            this.annotations = AnnotationCollector.emptyCollector();
        }

        public AnnotatedField build() {
            return new AnnotatedField(this.typeContext, this.field, this.annotations.asAnnotationMap());
        }
    }
}

