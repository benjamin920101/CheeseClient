/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;

public interface PredicateDecorator<T>
extends Predicate<T> {
    public Predicate<? super T>[] getPredicates();
}

