/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;

public final class AnnotationMap
implements Annotations {
    protected HashMap<Class<?>, Annotation> _annotations;

    public AnnotationMap() {
    }

    public static AnnotationMap of(Class<?> type, Annotation value) {
        HashMap ann2 = new HashMap(4);
        ann2.put(type, value);
        return new AnnotationMap(ann2);
    }

    AnnotationMap(HashMap<Class<?>, Annotation> a2) {
        this._annotations = a2;
    }

    @Override
    public <A extends Annotation> A get(Class<A> cls) {
        if (this._annotations == null) {
            return null;
        }
        return (A)this._annotations.get(cls);
    }

    @Override
    public boolean has(Class<?> cls) {
        if (this._annotations == null) {
            return false;
        }
        return this._annotations.containsKey(cls);
    }

    @Override
    public boolean hasOneOf(Class<? extends Annotation>[] annoClasses) {
        if (this._annotations != null) {
            int end = annoClasses.length;
            for (int i2 = 0; i2 < end; ++i2) {
                if (!this._annotations.containsKey(annoClasses[i2])) continue;
                return true;
            }
        }
        return false;
    }

    public Iterable<Annotation> annotations() {
        if (this._annotations == null || this._annotations.size() == 0) {
            return Collections.emptyList();
        }
        return this._annotations.values();
    }

    public static AnnotationMap merge(AnnotationMap primary, AnnotationMap secondary) {
        if (primary == null || primary._annotations == null || primary._annotations.isEmpty()) {
            return secondary;
        }
        if (secondary == null || secondary._annotations == null || secondary._annotations.isEmpty()) {
            return primary;
        }
        HashMap annotations = new HashMap();
        for (Annotation ann2 : secondary._annotations.values()) {
            annotations.put(ann2.annotationType(), ann2);
        }
        for (Annotation ann2 : primary._annotations.values()) {
            annotations.put(ann2.annotationType(), ann2);
        }
        return new AnnotationMap(annotations);
    }

    @Override
    public int size() {
        return this._annotations == null ? 0 : this._annotations.size();
    }

    public boolean addIfNotPresent(Annotation ann2) {
        if (this._annotations == null || !this._annotations.containsKey(ann2.annotationType())) {
            this._add(ann2);
            return true;
        }
        return false;
    }

    public boolean add(Annotation ann2) {
        return this._add(ann2);
    }

    public String toString() {
        if (this._annotations == null) {
            return "[null]";
        }
        return this._annotations.toString();
    }

    protected final boolean _add(Annotation ann2) {
        Annotation previous;
        if (this._annotations == null) {
            this._annotations = new HashMap();
        }
        return (previous = this._annotations.put(ann2.annotationType(), ann2)) == null || !previous.equals(ann2);
    }
}

