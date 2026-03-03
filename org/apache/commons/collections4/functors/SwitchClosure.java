/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.FunctorUtils;
import org.apache.commons.collections4.functors.NOPClosure;

public class SwitchClosure<E>
implements Closure<E>,
Serializable {
    private static final long serialVersionUID = 3518477308466486130L;
    private final Predicate<? super E>[] iPredicates;
    private final Closure<? super E>[] iClosures;
    private final Closure<? super E> iDefault;

    public static <E> Closure<E> switchClosure(Predicate<? super E>[] predicates, Closure<? super E>[] closures, Closure<? super E> defaultClosure) {
        FunctorUtils.validate(predicates);
        FunctorUtils.validate(closures);
        if (predicates.length != closures.length) {
            throw new IllegalArgumentException("The predicate and closure arrays must be the same size");
        }
        if (predicates.length == 0) {
            return defaultClosure == null ? NOPClosure.nopClosure() : defaultClosure;
        }
        return new SwitchClosure<E>(predicates, closures, defaultClosure);
    }

    public static <E> Closure<E> switchClosure(Map<Predicate<E>, Closure<E>> predicatesAndClosures) {
        if (predicatesAndClosures == null) {
            throw new NullPointerException("The predicate and closure map must not be null");
        }
        Closure<E> defaultClosure = predicatesAndClosures.remove(null);
        int size = predicatesAndClosures.size();
        if (size == 0) {
            return defaultClosure == null ? NOPClosure.nopClosure() : defaultClosure;
        }
        Closure[] closures = new Closure[size];
        Predicate[] preds = new Predicate[size];
        int i2 = 0;
        for (Map.Entry<Predicate<E>, Closure<E>> entry : predicatesAndClosures.entrySet()) {
            preds[i2] = entry.getKey();
            closures[i2] = entry.getValue();
            ++i2;
        }
        return new SwitchClosure<E>(false, preds, closures, defaultClosure);
    }

    private SwitchClosure(boolean clone, Predicate<? super E>[] predicates, Closure<? super E>[] closures, Closure<? super E> defaultClosure) {
        this.iPredicates = clone ? FunctorUtils.copy(predicates) : predicates;
        this.iClosures = clone ? FunctorUtils.copy(closures) : closures;
        this.iDefault = defaultClosure == null ? NOPClosure.nopClosure() : defaultClosure;
    }

    public SwitchClosure(Predicate<? super E>[] predicates, Closure<? super E>[] closures, Closure<? super E> defaultClosure) {
        this(true, predicates, closures, defaultClosure);
    }

    @Override
    public void execute(E input) {
        for (int i2 = 0; i2 < this.iPredicates.length; ++i2) {
            if (!this.iPredicates[i2].evaluate(input)) continue;
            this.iClosures[i2].execute(input);
            return;
        }
        this.iDefault.execute(input);
    }

    public Predicate<? super E>[] getPredicates() {
        return FunctorUtils.copy(this.iPredicates);
    }

    public Closure<? super E>[] getClosures() {
        return FunctorUtils.copy(this.iClosures);
    }

    public Closure<? super E> getDefaultClosure() {
        return this.iDefault;
    }
}

