/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.bridj.CLong;
import org.bridj.Callback;
import org.bridj.CommonPointerIOs;
import org.bridj.IntValuedEnum;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.SizeT;
import org.bridj.StructIO;
import org.bridj.StructObject;
import org.bridj.TimeT;
import org.bridj.TypedPointer;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class PointerIO<T> {
    final Type targetType;
    final Class<?> typedPointerClass;
    final int targetSize;
    final int targetAlignment = -1;
    private static final ConcurrentHashMap<StructIO, PointerIO<?>> structIOs = new ConcurrentHashMap();
    private static final ConcurrentHashMap<Type, PointerIO<?>> ios = new ConcurrentHashMap();
    private static final AtomicReference<PointerIO<Integer>> intInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Long>> longInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Short>> shortInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Byte>> byteInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Character>> charInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Float>> floatInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Double>> doubleInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Boolean>> booleanInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<CLong>> CLongInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<SizeT>> SizeTInstance = new AtomicReference();
    private static final AtomicReference<PointerIO<Pointer>> PointerInstance = new AtomicReference();
    private static final AtomicReference<PointerIO> stringInstance = new AtomicReference();

    public PointerIO(Type targetType, int targetSize, Class<?> typedPointerClass) {
        this.targetType = targetType;
        this.targetSize = targetSize;
        this.typedPointerClass = typedPointerClass;
    }

    abstract T get(Pointer<T> var1, long var2);

    abstract void set(Pointer<T> var1, long var2, T var4);

    public Object getArray(Pointer<T> pointer, long byteOffset, int length) {
        return pointer.offset(byteOffset).toArray(length);
    }

    public <B extends Buffer> B getBuffer(Pointer<T> pointer, long byteOffset, int length) {
        throw new UnsupportedOperationException("Cannot create a Buffer instance of elements of type " + this.getTargetType());
    }

    public void setArray(Pointer<T> pointer, long byteOffset, Object array) {
        Object[] a2 = (Object[])array;
        int n2 = a2.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            this.set(pointer, i2, a2[i2]);
        }
    }

    public T castTarget(long peer) {
        throw new UnsupportedOperationException("Cannot cast pointer to " + this.targetType);
    }

    PointerIO<Pointer<T>> getReferenceIO() {
        return new CommonPointerIOs.PointerPointerIO(this);
    }

    public long getTargetSize() {
        return this.targetSize;
    }

    public long getTargetAlignment() {
        return this.getTargetSize();
    }

    public boolean isTypedPointer() {
        return this.typedPointerClass != null;
    }

    public Class<?> getTypedPointerClass() {
        return this.typedPointerClass;
    }

    public Type getTargetType() {
        return this.targetType;
    }

    static Class<?> getClass(Type type) {
        if (type instanceof Class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            return PointerIO.getClass(((ParameterizedType)type).getRawType());
        }
        return null;
    }

    public static <T> PointerIO<Pointer<T>> getPointerInstance(Type target) {
        return PointerIO.getPointerInstance(PointerIO.getInstance(target));
    }

    public static <T> PointerIO<Pointer<T>> getPointerInstance(PointerIO<T> targetIO) {
        return new CommonPointerIOs.PointerPointerIO<T>(targetIO);
    }

    public static <T> PointerIO<Pointer<T>> getArrayInstance(PointerIO<T> targetIO, long[] dimensions, int iDimension) {
        return new CommonPointerIOs.PointerArrayIO<T>(targetIO, dimensions, iDimension);
    }

    static <T> PointerIO<T> getArrayIO(Object array) {
        if (array instanceof int[]) {
            return PointerIO.getIntInstance();
        }
        if (array instanceof long[]) {
            return PointerIO.getLongInstance();
        }
        if (array instanceof short[]) {
            return PointerIO.getShortInstance();
        }
        if (array instanceof byte[]) {
            return PointerIO.getByteInstance();
        }
        if (array instanceof char[]) {
            return PointerIO.getCharInstance();
        }
        if (array instanceof float[]) {
            return PointerIO.getFloatInstance();
        }
        if (array instanceof double[]) {
            return PointerIO.getDoubleInstance();
        }
        if (array instanceof boolean[]) {
            return PointerIO.getBooleanInstance();
        }
        return PointerIO.getInstance(array.getClass().getComponentType());
    }

    public static <S extends StructObject> PointerIO<S> getInstance(StructIO s2) {
        PointerIO<?> previousIO;
        PointerIO<?> io2 = structIOs.get(s2);
        if (io2 == null && (previousIO = structIOs.putIfAbsent(s2, io2 = new CommonPointerIOs.StructPointerIO(s2))) != null) {
            io2 = previousIO;
        }
        return io2;
    }

    public static <P> PointerIO<P> getInstance(Type type) {
        if (type == null) {
            return null;
        }
        PointerIO<Object> io2 = ios.get(type);
        if (io2 == null) {
            PointerIO<?> previousIO;
            Class cl2 = Utils.getClass(type);
            if (type == Integer.TYPE || type == Integer.class) {
                io2 = CommonPointerIOs.intIO;
            } else if (type == Long.TYPE || type == Long.class) {
                io2 = CommonPointerIOs.longIO;
            } else if (type == Short.TYPE || type == Short.class) {
                io2 = CommonPointerIOs.shortIO;
            } else if (type == Byte.TYPE || type == Byte.class) {
                io2 = CommonPointerIOs.byteIO;
            } else if (type == Character.TYPE || type == Character.class) {
                io2 = CommonPointerIOs.charIO;
            } else if (type == Float.TYPE || type == Float.class) {
                io2 = CommonPointerIOs.floatIO;
            } else if (type == Double.TYPE || type == Double.class) {
                io2 = CommonPointerIOs.doubleIO;
            } else if (type == Boolean.TYPE || type == Boolean.class) {
                io2 = CommonPointerIOs.booleanIO;
            } else if (cl2 != null) {
                Type enumType;
                if (TypedPointer.class.isAssignableFrom(cl2)) {
                    io2 = new CommonPointerIOs.TypedPointerPointerIO(cl2);
                } else if (Pointer.class.isAssignableFrom(cl2)) {
                    io2 = Pointer.class.equals((Object)type) || !(type instanceof ParameterizedType) ? PointerIO.getPointerInstance((PointerIO)null) : PointerIO.getPointerInstance(((ParameterizedType)type).getActualTypeArguments()[0]);
                } else if (SizeT.class.isAssignableFrom(cl2)) {
                    io2 = CommonPointerIOs.sizeTIO;
                } else if (TimeT.class.isAssignableFrom(cl2)) {
                    io2 = CommonPointerIOs.timeTIO;
                } else if (CLong.class.isAssignableFrom(cl2)) {
                    io2 = CommonPointerIOs.clongIO;
                } else if (StructObject.class.isAssignableFrom(cl2)) {
                    io2 = PointerIO.getInstance(StructIO.getInstance(cl2, type));
                } else if (Callback.class.isAssignableFrom(cl2)) {
                    io2 = new CommonPointerIOs.CallbackPointerIO(cl2);
                } else if (NativeObject.class.isAssignableFrom(cl2)) {
                    io2 = new CommonPointerIOs.NativeObjectPointerIO(type);
                } else if (IntValuedEnum.class.isAssignableFrom(cl2) && type instanceof ParameterizedType && (enumType = ((ParameterizedType)type).getActualTypeArguments()[0]) instanceof Class) {
                    io2 = new CommonPointerIOs.IntValuedEnumPointerIO((Class)enumType);
                }
            }
            if (io2 != null && (previousIO = ios.putIfAbsent(type, io2)) != null) {
                io2 = previousIO;
            }
        }
        return io2;
    }

    private static PointerIO atomicInstance(AtomicReference ref, Type type) {
        PointerIO io2 = (PointerIO)ref.get();
        if (io2 != null) {
            return io2;
        }
        io2 = PointerIO.getInstance(type);
        if (ref.compareAndSet(null, io2)) {
            return io2;
        }
        return (PointerIO)ref.get();
    }

    public static PointerIO<Integer> getIntInstance() {
        return PointerIO.atomicInstance(intInstance, Integer.class);
    }

    public static PointerIO<Long> getLongInstance() {
        return PointerIO.atomicInstance(longInstance, Long.class);
    }

    public static PointerIO<Short> getShortInstance() {
        return PointerIO.atomicInstance(shortInstance, Short.class);
    }

    public static PointerIO<Byte> getByteInstance() {
        return PointerIO.atomicInstance(byteInstance, Byte.class);
    }

    public static PointerIO<Character> getCharInstance() {
        return PointerIO.atomicInstance(charInstance, Character.class);
    }

    public static PointerIO<Float> getFloatInstance() {
        return PointerIO.atomicInstance(floatInstance, Float.class);
    }

    public static PointerIO<Double> getDoubleInstance() {
        return PointerIO.atomicInstance(doubleInstance, Double.class);
    }

    public static PointerIO<Boolean> getBooleanInstance() {
        return PointerIO.atomicInstance(booleanInstance, Boolean.class);
    }

    public static PointerIO<CLong> getCLongInstance() {
        return PointerIO.atomicInstance(CLongInstance, CLong.class);
    }

    public static PointerIO<SizeT> getSizeTInstance() {
        return PointerIO.atomicInstance(SizeTInstance, SizeT.class);
    }

    public static PointerIO<Pointer> getPointerInstance() {
        return PointerIO.atomicInstance(PointerInstance, Pointer.class);
    }

    public static <P> PointerIO<P> getBufferPrimitiveInstance(Buffer buffer) {
        if (buffer instanceof IntBuffer) {
            return PointerIO.getIntInstance();
        }
        if (buffer instanceof LongBuffer) {
            return PointerIO.getLongInstance();
        }
        if (buffer instanceof ShortBuffer) {
            return PointerIO.getShortInstance();
        }
        if (buffer instanceof ByteBuffer) {
            return PointerIO.getByteInstance();
        }
        if (buffer instanceof CharBuffer) {
            return PointerIO.getCharInstance();
        }
        if (buffer instanceof FloatBuffer) {
            return PointerIO.getFloatInstance();
        }
        if (buffer instanceof DoubleBuffer) {
            return PointerIO.getDoubleInstance();
        }
        throw new UnsupportedOperationException();
    }

    public static PointerIO<String> getStringInstance() {
        return PointerIO.atomicInstance(stringInstance, String.class);
    }
}

