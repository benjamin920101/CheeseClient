/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Type;
import org.bridj.NativeObject;
import org.bridj.Pointer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface BridJRuntime {
    public Type getType(NativeObject var1);

    public void register(Type var1);

    public void unregister(Type var1);

    public <T extends NativeObject> TypeInfo<T> getTypeInfo(Type var1);

    public boolean isAvailable();

    public <T extends NativeObject> Class<? extends T> getActualInstanceClass(Pointer<T> var1, Type var2);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface TypeInfo<T extends NativeObject> {
        public T cast(Pointer var1);

        public void initialize(T var1);

        public void initialize(T var1, Pointer var2);

        public void initialize(T var1, int var2, Object[] var3);

        public void destroy(T var1);

        public T createReturnInstance();

        public T clone(T var1) throws CloneNotSupportedException;

        public BridJRuntime getRuntime();

        public Type getType();

        public boolean equal(T var1, T var2);

        public int compare(T var1, T var2);

        public long sizeOf();

        public void writeToNative(T var1);

        public String describe(T var1);

        public String describe();

        public void readFromNative(T var1);

        public void copyNativeObjectToAddress(T var1, Pointer<T> var2);
    }
}

