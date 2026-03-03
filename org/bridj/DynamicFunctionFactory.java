/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.DynamicFunction;
import org.bridj.JNI;
import org.bridj.MethodCallInfo;
import org.bridj.Pointer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DynamicFunctionFactory {
    final Constructor<? extends DynamicFunction> constructor;
    final Method method;
    final long callbackHandle;

    DynamicFunctionFactory(Class<? extends DynamicFunction> callbackClass, Method method, CRuntime.MethodCallInfoBuilder methodCallInfoBuilder) {
        try {
            this.constructor = callbackClass.getConstructor(new Class[0]);
            this.method = method;
            MethodCallInfo mci = methodCallInfoBuilder.apply(method);
            this.callbackHandle = JNI.bindJavaToCCallbacks(mci);
        }
        catch (Throwable th2) {
            th2.printStackTrace();
            throw new RuntimeException("Failed to instantiate callback : " + th2, th2);
        }
    }

    protected void finalize() throws Throwable {
        if (BridJ.debugNeverFree) {
            return;
        }
        JNI.freeJavaToCCallbacks(this.callbackHandle, 1);
    }

    public DynamicFunction newInstance(Pointer<?> functionPointer) {
        if (functionPointer == null) {
            return null;
        }
        try {
            DynamicFunction dcb = this.constructor.newInstance(new Object[0]);
            dcb.peer = functionPointer;
            dcb.method = this.method;
            dcb.factory = this;
            return dcb;
        }
        catch (Throwable th2) {
            th2.printStackTrace();
            throw new RuntimeException("Failed to instantiate callback : " + th2, th2);
        }
    }

    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.method + ")";
    }
}

