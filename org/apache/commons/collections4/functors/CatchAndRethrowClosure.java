/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.FunctorException;

public abstract class CatchAndRethrowClosure<E>
implements Closure<E> {
    @Override
    public void execute(E input) {
        try {
            this.executeAndThrow(input);
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
        catch (Throwable t2) {
            throw new FunctorException(t2);
        }
    }

    protected abstract void executeAndThrow(E var1) throws Throwable;
}

