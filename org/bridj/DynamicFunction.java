/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Method;
import org.bridj.Callback;
import org.bridj.DynamicFunctionFactory;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class DynamicFunction<R>
extends Callback {
    DynamicFunctionFactory factory;
    Method method;

    protected DynamicFunction() {
    }

    public R apply(Object ... args) {
        try {
            return (R)this.method.invoke((Object)this, args);
        }
        catch (Throwable th2) {
            th2.printStackTrace();
            throw new RuntimeException("Failed to invoke callback : " + th2, th2);
        }
    }

    public String toString() {
        return this.method.toString();
    }
}

