/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.nio.Buffer;
import org.bridj.BridJ;
import org.bridj.CLong;
import org.bridj.CallbackInterface;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import org.bridj.SizeT;
import org.bridj.StructIO;
import org.bridj.StructObject;
import org.bridj.TimeT;
import org.bridj.TypedPointer;
import org.bridj.util.DefaultParameterizedType;

class CommonPointerIOs {
    public static final PointerIO<Integer> intIO = new PointerIO<Integer>(Integer.class, 4, null){

        @Override
        public Integer get(Pointer<Integer> pointer, long index) {
            return pointer.getIntAtOffset(index * 4L);
        }

        @Override
        public void set(Pointer<Integer> pointer, long index, Integer value) {
            pointer.setIntAtOffset(index * 4L, value);
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Integer> pointer, long byteOffset, int length) {
            return (B)pointer.getIntBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Integer> pointer, long byteOffset, int length) {
            return pointer.getIntsAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Integer> pointer, long byteOffset, Object array) {
            if (array instanceof int[]) {
                pointer.setIntsAtOffset(byteOffset, (int[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Long> longIO = new PointerIO<Long>(Long.class, 8, null){

        @Override
        public Long get(Pointer<Long> pointer, long index) {
            return pointer.getLongAtOffset(index * 8L);
        }

        @Override
        public void set(Pointer<Long> pointer, long index, Long value) {
            pointer.setLongAtOffset(index * 8L, value);
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Long> pointer, long byteOffset, int length) {
            return (B)pointer.getLongBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Long> pointer, long byteOffset, int length) {
            return pointer.getLongsAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Long> pointer, long byteOffset, Object array) {
            if (array instanceof long[]) {
                pointer.setLongsAtOffset(byteOffset, (long[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Short> shortIO = new PointerIO<Short>(Short.class, 2, null){

        @Override
        public Short get(Pointer<Short> pointer, long index) {
            return pointer.getShortAtOffset(index * 2L);
        }

        @Override
        public void set(Pointer<Short> pointer, long index, Short value) {
            pointer.setShortAtOffset(index * 2L, value);
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Short> pointer, long byteOffset, int length) {
            return (B)pointer.getShortBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Short> pointer, long byteOffset, int length) {
            return pointer.getShortsAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Short> pointer, long byteOffset, Object array) {
            if (array instanceof short[]) {
                pointer.setShortsAtOffset(byteOffset, (short[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Byte> byteIO = new PointerIO<Byte>(Byte.class, 1, null){

        @Override
        public Byte get(Pointer<Byte> pointer, long index) {
            return pointer.getByteAtOffset(index * 1L);
        }

        @Override
        public void set(Pointer<Byte> pointer, long index, Byte value) {
            pointer.setByteAtOffset(index * 1L, value);
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Byte> pointer, long byteOffset, int length) {
            return (B)pointer.getByteBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Byte> pointer, long byteOffset, int length) {
            return pointer.getBytesAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Byte> pointer, long byteOffset, Object array) {
            if (array instanceof byte[]) {
                pointer.setBytesAtOffset(byteOffset, (byte[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Character> charIO = new PointerIO<Character>(Character.class, Platform.WCHAR_T_SIZE, null){

        @Override
        public Character get(Pointer<Character> pointer, long index) {
            return Character.valueOf(pointer.getCharAtOffset(index * (long)Platform.WCHAR_T_SIZE));
        }

        @Override
        public void set(Pointer<Character> pointer, long index, Character value) {
            pointer.setCharAtOffset(index * (long)Platform.WCHAR_T_SIZE, value.charValue());
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Character> pointer, long byteOffset, int length) {
            throw new UnsupportedOperationException("Creating direct char buffers in a cross-platform way is tricky, so it's currently disabled");
        }

        @Override
        public Object getArray(Pointer<Character> pointer, long byteOffset, int length) {
            return pointer.getCharsAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Character> pointer, long byteOffset, Object array) {
            if (array instanceof char[]) {
                pointer.setCharsAtOffset(byteOffset, (char[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Float> floatIO = new PointerIO<Float>(Float.class, 4, null){

        @Override
        public Float get(Pointer<Float> pointer, long index) {
            return Float.valueOf(pointer.getFloatAtOffset(index * 4L));
        }

        @Override
        public void set(Pointer<Float> pointer, long index, Float value) {
            pointer.setFloatAtOffset(index * 4L, value.floatValue());
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Float> pointer, long byteOffset, int length) {
            return (B)pointer.getFloatBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Float> pointer, long byteOffset, int length) {
            return pointer.getFloatsAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Float> pointer, long byteOffset, Object array) {
            if (array instanceof float[]) {
                pointer.setFloatsAtOffset(byteOffset, (float[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Double> doubleIO = new PointerIO<Double>(Double.class, 8, null){

        @Override
        public Double get(Pointer<Double> pointer, long index) {
            return pointer.getDoubleAtOffset(index * 8L);
        }

        @Override
        public void set(Pointer<Double> pointer, long index, Double value) {
            pointer.setDoubleAtOffset(index * 8L, value);
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Double> pointer, long byteOffset, int length) {
            return (B)pointer.getDoubleBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Double> pointer, long byteOffset, int length) {
            return pointer.getDoublesAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Double> pointer, long byteOffset, Object array) {
            if (array instanceof double[]) {
                pointer.setDoublesAtOffset(byteOffset, (double[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<Boolean> booleanIO = new PointerIO<Boolean>(Boolean.class, 1, null){

        @Override
        public Boolean get(Pointer<Boolean> pointer, long index) {
            return pointer.getBooleanAtOffset(index * 1L);
        }

        @Override
        public void set(Pointer<Boolean> pointer, long index, Boolean value) {
            pointer.setBooleanAtOffset(index * 1L, value);
        }

        @Override
        public <B extends Buffer> B getBuffer(Pointer<Boolean> pointer, long byteOffset, int length) {
            return (B)pointer.getByteBufferAtOffset(byteOffset, length);
        }

        @Override
        public Object getArray(Pointer<Boolean> pointer, long byteOffset, int length) {
            return pointer.getBooleansAtOffset(byteOffset, length);
        }

        @Override
        public void setArray(Pointer<Boolean> pointer, long byteOffset, Object array) {
            if (array instanceof boolean[]) {
                pointer.setBooleansAtOffset(byteOffset, (boolean[])array);
            } else {
                super.setArray(pointer, byteOffset, array);
            }
        }
    };
    public static final PointerIO<SizeT> sizeTIO = new PointerIO<SizeT>(SizeT.class, SizeT.SIZE, null){

        @Override
        public SizeT get(Pointer<SizeT> pointer, long index) {
            return new SizeT(pointer.getSizeTAtOffset(index * (long)SizeT.SIZE));
        }

        @Override
        public void set(Pointer<SizeT> pointer, long index, SizeT value) {
            pointer.setSizeTAtOffset(index * (long)SizeT.SIZE, value == null ? 0L : value.longValue());
        }
    };
    public static final PointerIO<TimeT> timeTIO = new PointerIO<TimeT>(TimeT.class, TimeT.SIZE, null){

        @Override
        public TimeT get(Pointer<TimeT> pointer, long index) {
            long offset = index * (long)TimeT.SIZE;
            return new TimeT(TimeT.SIZE == 4 ? (long)pointer.getIntAtOffset(offset) : pointer.getLongAtOffset(offset));
        }

        @Override
        public void set(Pointer<TimeT> pointer, long index, TimeT value) {
            long offset = index * (long)TimeT.SIZE;
            if (TimeT.SIZE == 4) {
                pointer.setIntAtOffset(offset, value == null ? 0 : value.intValue());
            } else {
                pointer.setLongAtOffset(offset, value == null ? 0L : value.longValue());
            }
        }
    };
    public static final PointerIO<CLong> clongIO = new PointerIO<CLong>(CLong.class, CLong.SIZE, null){

        @Override
        public CLong get(Pointer<CLong> pointer, long index) {
            return new CLong(pointer.getCLongAtOffset(index * (long)CLong.SIZE));
        }

        @Override
        public void set(Pointer<CLong> pointer, long index, CLong value) {
            pointer.setCLongAtOffset(index * (long)CLong.SIZE, value == null ? 0L : value.longValue());
        }
    };

    CommonPointerIOs() {
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class TypedPointerPointerIO<P extends TypedPointer>
    extends PointerIO<P> {
        final Constructor cons;
        final Class<P> pointerClass;

        public TypedPointerPointerIO(Class<P> pointerClass) {
            super(pointerClass, Pointer.SIZE, null);
            this.pointerClass = pointerClass;
            try {
                this.cons = pointerClass.getConstructor(Long.TYPE);
            }
            catch (Exception ex2) {
                throw new RuntimeException("Cannot find constructor for " + pointerClass.getName(), ex2);
            }
        }

        @Override
        public P castTarget(long peer) {
            if (peer == 0L) {
                return null;
            }
            try {
                return (P)((TypedPointer)this.cons.newInstance(peer));
            }
            catch (Exception ex2) {
                throw new RuntimeException("Cannot create pointer of type " + this.pointerClass.getName(), ex2);
            }
        }

        @Override
        public P get(Pointer<P> pointer, long index) {
            return (P)this.castTarget(pointer.getSizeTAtOffset(index * (long)Pointer.SIZE));
        }

        @Override
        public void set(Pointer<P> pointer, long index, P value) {
            pointer.setPointerAtOffset(index * (long)Pointer.SIZE, (Pointer<?>)value);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class IntValuedEnumPointerIO<E extends Enum<E>>
    extends PointerIO<IntValuedEnum<E>> {
        final Class<E> enumClass;

        public IntValuedEnumPointerIO(Class<E> enumClass) {
            super((Type)((Object)IntValuedEnum.class), 4, null);
            this.enumClass = enumClass;
        }

        @Override
        public IntValuedEnum<E> get(Pointer<IntValuedEnum<E>> pointer, long index) {
            return FlagSet.fromValue(pointer.getIntAtOffset(4L * index), this.enumClass);
        }

        @Override
        public void set(Pointer<IntValuedEnum<E>> pointer, long index, IntValuedEnum<E> value) {
            pointer.setIntAtOffset(4L * index, (int)value.value());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class CallbackPointerIO<T extends CallbackInterface>
    extends PointerIO<T> {
        final Class<T> callbackClass;

        public CallbackPointerIO(Class<T> callbackClass) {
            super(callbackClass, Pointer.SIZE, null);
            this.callbackClass = callbackClass;
        }

        @Override
        public T get(Pointer<T> pointer, long index) {
            if (index != 0L) {
                throw new UnsupportedOperationException("Cannot get function pointer at index different from 0");
            }
            return (T)((CallbackInterface)pointer.getNativeObjectAtOffset(0L, this.callbackClass));
        }

        @Override
        public void set(Pointer<T> pointer, long index, T value) {
            throw new UnsupportedOperationException("Cannot write to body of function");
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class PointerArrayIO<T>
    extends PointerIO<Pointer<T>> {
        final PointerIO<T> underlyingIO;
        final long[] dimensions;
        final long totalRemainingDims;
        final int iDimension;

        static Type arrayPtrType(Type elementType, long ... dimensions) {
            Type type = elementType;
            for (int i2 = 0; i2 < dimensions.length; ++i2) {
                type = DefaultParameterizedType.paramType(Pointer.class, new Type[]{type});
            }
            return type;
        }

        static long getTotalRemainingDims(long[] dimensions, int iDimension) {
            long d2 = 1L;
            for (int i2 = iDimension + 1; i2 < dimensions.length; ++i2) {
                d2 *= dimensions[i2];
            }
            return d2;
        }

        public PointerArrayIO(PointerIO<T> underlyingIO, long[] dimensions, int iDimension) {
            super(underlyingIO == null ? null : PointerArrayIO.arrayPtrType(underlyingIO.getTargetType(), dimensions), -1, null);
            this.underlyingIO = underlyingIO;
            this.dimensions = dimensions;
            this.iDimension = iDimension;
            this.totalRemainingDims = PointerArrayIO.getTotalRemainingDims(dimensions, iDimension);
        }

        @Override
        public long getTargetSize() {
            long subSize = this.underlyingIO.getTargetSize();
            return this.dimensions[this.iDimension + 1] * subSize;
        }

        @Override
        public Pointer<T> get(Pointer<Pointer<T>> pointer, long index) {
            long targetSize = this.getTargetSize();
            return pointer.offset(index * targetSize).as(this.underlyingIO);
        }

        long getOffset(long index) {
            assert (this.iDimension < this.dimensions.length);
            return index * this.totalRemainingDims;
        }

        @Override
        public void set(Pointer<Pointer<T>> pointer, long index, Pointer<T> value) {
            throw new RuntimeException("Cannot set a multi-dimensional array's sub-arrays pointers !");
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class PointerPointerIO<T>
    extends PointerIO<Pointer<T>> {
        final PointerIO<T> underlyingIO;

        public PointerPointerIO(PointerIO<T> underlyingIO) {
            super((Type)(underlyingIO == null ? Pointer.class : DefaultParameterizedType.paramType(Pointer.class, new Type[]{underlyingIO.getTargetType()})), Pointer.SIZE, null);
            this.underlyingIO = underlyingIO;
        }

        @Override
        public Pointer<T> get(Pointer<Pointer<T>> pointer, long index) {
            return pointer.getPointerAtOffset(index * (long)Pointer.SIZE, this.underlyingIO);
        }

        @Override
        public void set(Pointer<Pointer<T>> pointer, long index, Pointer<T> value) {
            pointer.setPointerAtOffset(index * (long)Pointer.SIZE, value);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class StructPointerIO<S extends StructObject>
    extends NativeObjectPointerIO<S> {
        final StructIO structIO;

        public StructPointerIO(StructIO structIO) {
            super(structIO.getStructType());
            this.structIO = structIO;
        }

        @Override
        protected long computeTargetSize() {
            this.structIO.build();
            return this.structIO.getStructSize();
        }

        @Override
        protected long computeTargetAlignment() {
            this.structIO.build();
            return this.structIO.getStructAlignment();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class NativeObjectPointerIO<N extends NativeObject>
    extends PointerIO<N> {
        protected volatile long targetSize = -1L;
        protected volatile long targetAlignment = -1L;
        protected Type nativeObjectType;

        public NativeObjectPointerIO(Type nativeObjectType) {
            super(nativeObjectType, -1, null);
            this.nativeObjectType = nativeObjectType;
        }

        protected long computeTargetSize() {
            return BridJ.sizeOf(this.nativeObjectType);
        }

        protected long computeTargetAlignment() {
            return this.getTargetSize();
        }

        @Override
        public long getTargetSize() {
            if (this.targetSize < 0L) {
                this.targetSize = this.computeTargetSize();
            }
            return this.targetSize;
        }

        @Override
        public long getTargetAlignment() {
            if (this.targetAlignment < 0L) {
                this.targetAlignment = this.computeTargetAlignment();
            }
            return this.targetAlignment;
        }

        @Override
        public N get(Pointer<N> pointer, long index) {
            return (N)pointer.getNativeObjectAtOffset(index * this.getTargetSize(), this.nativeObjectType);
        }

        @Override
        public void set(Pointer<N> pointer, long index, N value) {
            Pointer<N> ps2 = Pointer.pointerTo(value);
            ps2.copyTo(pointer.offset(index * this.getTargetSize()));
        }
    }
}

