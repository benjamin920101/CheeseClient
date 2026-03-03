/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.functors;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

class FunctorUtils {
    private FunctorUtils() {
    }

    static <T> Predicate<T>[] copy(Predicate<? super T> ... predicates) {
        if (predicates == null) {
            return null;
        }
        return (Predicate[])predicates.clone();
    }

    static <T> Predicate<T> coerce(Predicate<? super T> predicate) {
        return predicate;
    }

    static void validate(Predicate<?> ... predicates) {
        if (predicates == null) {
            throw new NullPointerException("The predicate array must not be null");
        }
        for (int i2 = 0; i2 < predicates.length; ++i2) {
            if (predicates[i2] != null) continue;
            throw new NullPointerException("The predicate array must not contain a null predicate, index " + i2 + " was null");
        }
    }

    static <T> Predicate<? super T>[] validate(Collection<? extends Predicate<? super T>> predicates) {
        if (predicates == null) {
            throw new NullPointerException("The predicate collection must not be null");
        }
        Predicate[] preds = new Predicate[predicates.size()];
        int i2 = 0;
        Iterator<Predicate<T>> i$ = predicates.iterator();
        while (i$.hasNext()) {
            Predicate<? super T> predicate;
            preds[i2] = predicate = i$.next();
            if (preds[i2] == null) {
                throw new NullPointerException("The predicate collection must not contain a null predicate, index " + i2 + " was null");
            }
            ++i2;
        }
        return preds;
    }

    static <E> Closure<E>[] copy(Closure<? super E> ... closures) {
        if (closures == null) {
            return null;
        }
        return (Closure[])closures.clone();
    }

    static void validate(Closure<?> ... closures) {
        if (closures == null) {
            throw new NullPointerException("The closure array must not be null");
        }
        for (int i2 = 0; i2 < closures.length; ++i2) {
            if (closures[i2] != null) continue;
            throw new NullPointerException("The closure array must not contain a null closure, index " + i2 + " was null");
        }
    }

    static <T> Closure<T> coerce(Closure<? super T> closure) {
        return closure;
    }

    static <I, O> Transformer<I, O>[] copy(Transformer<? super I, ? extends O> ... transformers) {
        if (transformers == null) {
            return null;
        }
        return (Transformer[])transformers.clone();
    }

    static void validate(Transformer<?, ?> ... transformers) {
        if (transformers == null) {
            throw new NullPointerException("The transformer array must not be null");
        }
        for (int i2 = 0; i2 < transformers.length; ++i2) {
            if (transformers[i2] != null) continue;
            throw new NullPointerException("The transformer array must not contain a null transformer, index " + i2 + " was null");
        }
    }

    static <I, O> Transformer<I, O> coerce(Transformer<? super I, ? extends O> transformer) {
        return transformer;
    }
}

