/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import org.bridj.ann.Forwardable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AnnotationUtils {
    public static <A extends Annotation> A getInheritableAnnotation(Class<A> ac2, AnnotatedElement m2, Annotation ... directAnnotations) {
        return AnnotationUtils.getAnnotation(ac2, true, m2, directAnnotations);
    }

    public static <A extends Annotation> A getAnnotation(Class<A> ac2, AnnotatedElement m2, Annotation ... directAnnotations) {
        return AnnotationUtils.getAnnotation(ac2, false, m2, directAnnotations);
    }

    private static boolean isForwardable(Class<? extends Annotation> ac2) {
        return ac2.isAnnotationPresent(Forwardable.class);
    }

    public static boolean isAnnotationPresent(Class<? extends Annotation> ac2, Annotation ... annotations) {
        return AnnotationUtils.isAnnotationPresent(ac2, AnnotationUtils.isForwardable(ac2), annotations);
    }

    private static boolean isAnnotationPresent(Class<? extends Annotation> ac2, boolean isForwardable, Annotation ... annotations) {
        for (Annotation ann2 : annotations) {
            if (ac2.isInstance(ann2)) {
                return true;
            }
            if (!isForwardable || !ann2.annotationType().isAnnotationPresent(ac2)) continue;
            return true;
        }
        return false;
    }

    public static boolean isAnnotationPresent(Class<? extends Annotation> ac2, AnnotatedElement m2, Annotation ... directAnnotations) {
        boolean isForwardable = AnnotationUtils.isForwardable(ac2);
        if (m2 != null && (isForwardable ? AnnotationUtils.isAnnotationPresent(ac2, true, m2.getAnnotations()) : m2.isAnnotationPresent(ac2))) {
            return true;
        }
        if (directAnnotations != null) {
            return AnnotationUtils.isAnnotationPresent(ac2, isForwardable, directAnnotations);
        }
        return false;
    }

    private static <A extends Annotation> A getAnnotation(Class<A> ac2, boolean inherit, AnnotatedElement m2, Annotation ... directAnnotations) {
        if (directAnnotations != null) {
            for (Annotation ann2 : directAnnotations) {
                if (!ac2.isInstance(ann2)) continue;
                return (A)((Annotation)ac2.cast(ann2));
            }
        }
        if (m2 == null) {
            return null;
        }
        A a2 = m2.getAnnotation(ac2);
        if (a2 != null) {
            return a2;
        }
        if (inherit) {
            if (m2 instanceof Member) {
                return AnnotationUtils.getAnnotation(ac2, inherit, ((Member)((Object)m2)).getDeclaringClass(), new Annotation[0]);
            }
            if (m2 instanceof Class) {
                Class c2 = (Class)m2;
                Class<?> dc2 = c2.getDeclaringClass();
                for (Class p2 = c2.getSuperclass(); p2 != null; p2 = p2.getSuperclass()) {
                    a2 = AnnotationUtils.getAnnotation(ac2, true, p2, new Annotation[0]);
                    if (a2 == null) continue;
                    return a2;
                }
                if (dc2 != null) {
                    return AnnotationUtils.getAnnotation(ac2, inherit, dc2, new Annotation[0]);
                }
            }
        }
        return null;
    }
}

