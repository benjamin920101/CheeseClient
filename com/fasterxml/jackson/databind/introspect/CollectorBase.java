/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

class CollectorBase {
    protected static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
    protected final AnnotationIntrospector _intr;

    protected CollectorBase(AnnotationIntrospector intr) {
        this._intr = intr;
    }

    protected final AnnotationCollector collectAnnotations(Annotation[] anns) {
        AnnotationCollector c2 = AnnotationCollector.emptyCollector();
        for (Annotation ann2 : anns) {
            c2 = c2.addOrOverride(ann2);
            if (!this._intr.isAnnotationBundle(ann2)) continue;
            c2 = this.collectFromBundle(c2, ann2);
        }
        return c2;
    }

    protected final AnnotationCollector collectAnnotations(AnnotationCollector c2, Annotation[] anns) {
        for (Annotation ann2 : anns) {
            c2 = c2.addOrOverride(ann2);
            if (!this._intr.isAnnotationBundle(ann2)) continue;
            c2 = this.collectFromBundle(c2, ann2);
        }
        return c2;
    }

    protected final AnnotationCollector collectFromBundle(AnnotationCollector c2, Annotation bundle) {
        for (Annotation ann2 : ClassUtil.findClassAnnotations(bundle.annotationType())) {
            if (CollectorBase._ignorableAnnotation(ann2)) continue;
            if (this._intr.isAnnotationBundle(ann2)) {
                if (c2.isPresent(ann2)) continue;
                c2 = c2.addOrOverride(ann2);
                c2 = this.collectFromBundle(c2, ann2);
                continue;
            }
            c2 = c2.addOrOverride(ann2);
        }
        return c2;
    }

    protected final AnnotationCollector collectDefaultAnnotations(AnnotationCollector c2, Annotation[] anns) {
        for (Annotation ann2 : anns) {
            if (c2.isPresent(ann2)) continue;
            c2 = c2.addOrOverride(ann2);
            if (!this._intr.isAnnotationBundle(ann2)) continue;
            c2 = this.collectDefaultFromBundle(c2, ann2);
        }
        return c2;
    }

    protected final AnnotationCollector collectDefaultFromBundle(AnnotationCollector c2, Annotation bundle) {
        for (Annotation ann2 : ClassUtil.findClassAnnotations(bundle.annotationType())) {
            if (CollectorBase._ignorableAnnotation(ann2) || c2.isPresent(ann2)) continue;
            c2 = c2.addOrOverride(ann2);
            if (!this._intr.isAnnotationBundle(ann2)) continue;
            c2 = this.collectFromBundle(c2, ann2);
        }
        return c2;
    }

    protected static final boolean _ignorableAnnotation(Annotation a2) {
        return a2 instanceof Target || a2 instanceof Retention;
    }

    static AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    static AnnotationMap[] _emptyAnnotationMaps(int count) {
        if (count == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] maps = new AnnotationMap[count];
        for (int i2 = 0; i2 < count; ++i2) {
            maps[i2] = CollectorBase._emptyAnnotationMap();
        }
        return maps;
    }
}

