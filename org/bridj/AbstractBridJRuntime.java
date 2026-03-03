/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Type;
import org.bridj.BridJRuntime;
import org.bridj.NativeObject;
import org.bridj.ann.Constructor;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractBridJRuntime
implements BridJRuntime {
    @Override
    public void unregister(Type type) {
    }

    @Override
    public Type getType(NativeObject instance) {
        if (instance == null) {
            return null;
        }
        return Utils.getClass(instance.getClass());
    }

    protected java.lang.reflect.Constructor findConstructor(Class<?> type, int constructorId, boolean onlyWithAnnotation) throws SecurityException, NoSuchMethodException {
        for (java.lang.reflect.Constructor<?> c2 : type.getDeclaredConstructors()) {
            Constructor ca2 = c2.getAnnotation(Constructor.class);
            if (ca2 == null || ca2.value() != constructorId) continue;
            return c2;
        }
        if (constructorId < 0) {
            return type.getConstructor(new Class[0]);
        }
        Class<?> sup = type.getSuperclass();
        if (sup != null) {
            try {
                java.lang.reflect.Constructor<?>[] ccs;
                java.lang.reflect.Constructor c3 = this.findConstructor(sup, constructorId, onlyWithAnnotation);
                if (onlyWithAnnotation && c3 != null) {
                    return c3;
                }
                Type[] params = c3.getGenericParameterTypes();
                for (java.lang.reflect.Constructor<?> cc2 : ccs = type.getDeclaredConstructors()) {
                    int overrideOffset;
                    Type[] ccparams = cc2.getGenericParameterTypes();
                    if (!AbstractBridJRuntime.isOverridenSignature(params, ccparams, overrideOffset = Utils.getEnclosedConstructorParametersOffset(cc2))) continue;
                    return cc2;
                }
            }
            catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        throw new NoSuchMethodException("Cannot find constructor with index " + constructorId);
    }

    public static boolean isOverridenSignature(Type[] parentSignature, Type[] overrideSignature, int overrideOffset) {
        int n2 = parentSignature.length;
        if (overrideSignature.length - overrideOffset != n2) {
            return false;
        }
        for (int i2 = 0; i2 < n2; ++i2) {
            if (AbstractBridJRuntime.isOverride(parentSignature[i2], overrideSignature[overrideOffset + i2])) continue;
            return false;
        }
        return true;
    }

    protected static boolean isOverride(Type parentSignature, Type overrideSignature) {
        return Utils.getClass(parentSignature).isAssignableFrom(Utils.getClass(overrideSignature));
    }
}

