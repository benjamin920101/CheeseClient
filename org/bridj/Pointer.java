/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.bridj.BridJ;
import org.bridj.CLong;
import org.bridj.CRuntime;
import org.bridj.CommonPointerIOs;
import org.bridj.DefaultNativeList;
import org.bridj.DynamicCallback;
import org.bridj.DynamicFunction;
import org.bridj.IntValuedEnum;
import org.bridj.JNI;
import org.bridj.MethodCallInfo;
import org.bridj.NativeList;
import org.bridj.NativeObject;
import org.bridj.NativeObjectInterface;
import org.bridj.Platform;
import org.bridj.PointerIO;
import org.bridj.SizeT;
import org.bridj.TypedPointer;
import org.bridj.ann.Convention;
import org.bridj.util.DefaultParameterizedType;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Pointer<T>
implements Comparable<Pointer<?>>,
Iterable<T> {
    public static final Pointer NULL = null;
    public static final int SIZE = Platform.POINTER_SIZE;
    protected static long UNKNOWN_VALIDITY;
    protected static long NO_PARENT;
    public static final int defaultAlignment;
    protected final PointerIO<T> io;
    protected final long peer;
    protected final long offsetInParent;
    protected final Pointer<?> parent;
    protected volatile Object sibling;
    protected final long validStart;
    protected final long validEnd;
    Throwable creationTrace;
    static Releaser freeReleaser;

    Pointer(PointerIO<T> io2, long peer, long validStart, long validEnd, Pointer<?> parent, long offsetInParent, Object sibling) {
        this.io = io2;
        this.peer = peer;
        this.validStart = validStart;
        this.validEnd = validEnd;
        this.parent = parent;
        this.offsetInParent = offsetInParent;
        this.sibling = sibling;
        if (peer == 0L) {
            throw new IllegalArgumentException("Pointer instance cannot have NULL peer ! (use null Pointer instead)");
        }
        if (BridJ.debugPointers) {
            this.creationTrace = new RuntimeException().fillInStackTrace();
        }
    }

    public static Type pointerType(Type targetType) {
        return DefaultParameterizedType.paramType(Pointer.class, new Type[]{targetType});
    }

    public static <E extends Enum<E>> Type intEnumType(Class<? extends IntValuedEnum<E>> targetType) {
        return DefaultParameterizedType.paramType(IntValuedEnum.class, new Type[]{targetType});
    }

    public synchronized void release() {
        Object sibling = this.sibling;
        this.sibling = null;
        if (sibling instanceof Pointer) {
            ((Pointer)sibling).release();
        }
    }

    @Override
    public int compareTo(Pointer<?> p2) {
        long p22;
        if (p2 == null) {
            return 1;
        }
        long p1 = this.getPeer();
        return p1 == (p22 = p2.getPeer()) ? 0 : (p1 < p22 ? -1 : 1);
    }

    public int compareBytes(Pointer<?> other, long byteCount) {
        return this.compareBytesAtOffset(0L, other, 0L, byteCount);
    }

    public int compareBytesAtOffset(long byteOffset, Pointer<?> other, long otherByteOffset, long byteCount) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, byteCount);
        }
        return JNI.memcmp(checkedPeer, super.getCheckedPeer(otherByteOffset, byteCount), byteCount);
    }

    public int hashCode() {
        int hc2 = new Long(this.getPeer()).hashCode();
        return hc2;
    }

    public String toString() {
        return "Pointer(peer = 0x" + Long.toHexString(this.getPeer()) + ", targetType = " + Utils.toString(this.getTargetType()) + ", order = " + this.order() + ")";
    }

    protected final void checkPeer(long peer, long validityCheckLength) {
        if (peer < this.validStart || peer + validityCheckLength > this.validEnd) {
            throw new IndexOutOfBoundsException("Cannot access to memory data of length " + validityCheckLength + " at offset " + (peer - this.getPeer()) + " : valid memory start is " + this.validStart + ", valid memory size is " + (this.validEnd - this.validStart));
        }
    }

    private final long getCheckedPeer(long byteOffset, long validityCheckLength) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, validityCheckLength);
        }
        return checkedPeer;
    }

    public Pointer<T> offset(long byteOffset) {
        return this.offset(byteOffset, this.getIO());
    }

    <U> Pointer<U> offset(long byteOffset, PointerIO<U> pio) {
        Object newSibling;
        if (byteOffset == 0L) {
            return pio == this.io ? this : this.as(pio);
        }
        long newPeer = this.getPeer() + byteOffset;
        Object object = newSibling = this.getSibling() != null ? this.getSibling() : this;
        if (this.validStart == UNKNOWN_VALIDITY) {
            return Pointer.newPointer(pio, newPeer, this.isOrdered(), UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, NO_PARENT, null, newSibling);
        }
        if (newPeer > this.validEnd || newPeer < this.validStart) {
            throw new IndexOutOfBoundsException("Invalid pointer offset : " + byteOffset + " (validBytes = " + this.getValidBytes() + ") !");
        }
        return Pointer.newPointer(pio, newPeer, this.isOrdered(), this.validStart, this.validEnd, null, NO_PARENT, null, newSibling);
    }

    public Pointer<T> validBytes(long byteCount) {
        long peer = this.getPeer();
        long newValidEnd = peer + byteCount;
        if (this.validStart == peer && this.validEnd == newValidEnd) {
            return this;
        }
        if (this.validEnd != UNKNOWN_VALIDITY && newValidEnd > this.validEnd) {
            throw new IndexOutOfBoundsException("Cannot extend validity of pointed memory from " + this.validEnd + " to " + newValidEnd);
        }
        Object newSibling = this.getSibling() != null ? this.getSibling() : this;
        return Pointer.newPointer(this.getIO(), peer, this.isOrdered(), this.validStart, newValidEnd, this.parent, this.offsetInParent, null, newSibling);
    }

    @Deprecated
    public Pointer<T> withoutValidityInformation() {
        long peer = this.getPeer();
        if (this.validStart == UNKNOWN_VALIDITY) {
            return this;
        }
        Object newSibling = this.getSibling() != null ? this.getSibling() : this;
        return Pointer.newPointer(this.getIO(), peer, this.isOrdered(), UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, this.parent, this.offsetInParent, null, newSibling);
    }

    public Pointer<T> clone() {
        long length = this.getValidElements();
        if (length < 0L) {
            throw new UnsupportedOperationException("Number of bytes unknown, unable to clone memory (use validBytes(long))");
        }
        Pointer<T> c2 = Pointer.allocateArray(this.getIO(), length);
        this.copyTo(c2);
        return c2;
    }

    public Pointer<T> validElements(long elementCount) {
        return this.validBytes(elementCount * this.getIO("Cannot define elements validity").getTargetSize());
    }

    public Pointer<Pointer<T>> getReference() {
        if (this.parent == null) {
            throw new UnsupportedOperationException("Cannot get reference to this pointer, it wasn't created from Pointer.getPointer(offset) or from a similar method.");
        }
        PointerIO<T> io2 = this.getIO();
        return this.parent.offset(this.offsetInParent).as(io2 == null ? null : io2.getReferenceIO());
    }

    public final long getPeer() {
        return this.peer;
    }

    public static <R> Pointer<DynamicFunction<R>> allocateDynamicCallback(DynamicCallback<R> callback, Convention.Style callingConvention, Type returnType, Type ... parameterTypes) {
        if (callback == null) {
            throw new IllegalArgumentException("Java callback handler cannot be null !");
        }
        if (returnType == null) {
            throw new IllegalArgumentException("Callback return type cannot be null !");
        }
        if (parameterTypes == null) {
            throw new IllegalArgumentException("Invalid (null) list of parameter types !");
        }
        try {
            MethodCallInfo mci = new MethodCallInfo(returnType, parameterTypes, false);
            Method method = DynamicCallback.class.getMethod("apply", Object[].class);
            mci.setMethod(method);
            mci.setJavaSignature("([Ljava/lang/Object;)Ljava/lang/Object;");
            mci.setCallingConvention(callingConvention);
            mci.setGenericCallback(true);
            mci.setJavaCallback(callback);
            return CRuntime.createCToJavaCallback(mci, DynamicCallback.class);
        }
        catch (Exception ex2) {
            throw new RuntimeException("Failed to allocate dynamic callback for convention " + (Object)((Object)callingConvention) + ", return type " + Utils.toString(returnType) + " and parameter types " + Arrays.asList(parameterTypes) + " : " + ex2, ex2);
        }
    }

    public <U> Pointer<U> as(PointerIO<U> newIO) {
        return this.viewAs(this.isOrdered(), newIO);
    }

    public Pointer<T> order(ByteOrder order) {
        if (order.equals(ByteOrder.nativeOrder()) == this.isOrdered()) {
            return this;
        }
        return this.viewAs(!this.isOrdered(), this.getIO());
    }

    public ByteOrder order() {
        ByteOrder order = this.isOrdered() ? ByteOrder.nativeOrder() : (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        return order;
    }

    <U> Pointer<U> viewAs(boolean ordered, PointerIO<U> newIO) {
        if (newIO == this.io && ordered == this.isOrdered()) {
            return this;
        }
        return Pointer.newPointer(newIO, this.getPeer(), ordered, this.getValidStart(), this.getValidEnd(), this.getParent(), this.getOffsetInParent(), null, this.getSibling() != null ? this.getSibling() : this);
    }

    public final PointerIO<T> getIO() {
        return this.io;
    }

    public abstract boolean isOrdered();

    final long getOffsetInParent() {
        return this.offsetInParent;
    }

    final Pointer<?> getParent() {
        return this.parent;
    }

    final Object getSibling() {
        return this.sibling;
    }

    final long getValidEnd() {
        return this.validEnd;
    }

    final long getValidStart() {
        return this.validStart;
    }

    public <U> Pointer<U> as(Type type) {
        PointerIO pio = PointerIO.getInstance(type);
        return this.as(pio);
    }

    public <U> Pointer<U> as(Class<U> type) {
        return this.as((Type)type);
    }

    public <R> DynamicFunction<R> asDynamicFunction(Convention.Style callingConvention, Type returnType, Type ... parameterTypes) {
        return CRuntime.getInstance().getDynamicFunctionFactory(null, callingConvention, returnType, parameterTypes).newInstance(this);
    }

    public Pointer<?> asUntyped() {
        return this.as((Class)null);
    }

    public long getValidBytes() {
        long ve2 = this.getValidEnd();
        return ve2 == UNKNOWN_VALIDITY ? -1L : ve2 - this.getPeer();
    }

    public long getValidElements() {
        long bytes = this.getValidBytes();
        long elementSize = this.getTargetSize();
        if (bytes < 0L || elementSize <= 0L) {
            return -1L;
        }
        return bytes / elementSize;
    }

    @Override
    public ListIterator<T> iterator() {
        return new ListIterator<T>(){
            Pointer<T> next;
            Pointer<T> previous;
            {
                this.next = Pointer.this.getValidElements() != 0L ? Pointer.this : null;
            }

            @Override
            public T next() {
                if (this.next == null) {
                    throw new NoSuchElementException();
                }
                Object value = this.next.get();
                this.previous = this.next;
                long valid = this.next.getValidElements();
                this.next = valid < 0L || valid > 1L ? this.next.next(1L) : null;
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                long rem;
                return this.next != null && ((rem = this.next.getValidBytes()) < 0L || rem > 0L);
            }

            @Override
            public void add(T o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasPrevious() {
                return this.previous != null;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T o2) {
                if (this.previous == null) {
                    throw new NoSuchElementException("You haven't called next() prior to calling ListIterator.set(E)");
                }
                this.previous.set(o2);
            }
        };
    }

    public static <N extends NativeObject> Pointer<N> pointerTo(N instance) {
        return Pointer.pointerTo(instance, null);
    }

    public static <N extends NativeObjectInterface> Pointer<N> pointerTo(N instance) {
        return Pointer.pointerTo((NativeObject)instance);
    }

    public static <R extends NativeObject> Pointer<R> pointerTo(NativeObject instance, Type targetType) {
        return instance == null ? null : instance.peer;
    }

    public static long getAddress(NativeObject instance, Class targetType) {
        return Pointer.getPeer(Pointer.pointerTo(instance, targetType));
    }

    public <O extends NativeObject> O getNativeObjectAtOffset(long byteOffset, Type type) {
        return (O)BridJ.createNativeObjectFromPointer(byteOffset == 0L ? this : this.offset(byteOffset), type);
    }

    public <O extends NativeObject> Pointer<T> setNativeObject(O value, Type type) {
        BridJ.copyNativeObjectToAddress(value, type, this);
        return this;
    }

    public <O extends NativeObject> O getNativeObjectAtOffset(long byteOffset, Class<O> type) {
        return this.getNativeObjectAtOffset(byteOffset, (Type)type);
    }

    public <O extends NativeObject> O getNativeObject(Class<O> type) {
        return this.getNativeObject((Type)type);
    }

    public <O extends NativeObject> O getNativeObject(Type type) {
        O o2 = this.getNativeObjectAtOffset(0L, type);
        return o2;
    }

    public boolean isAligned() {
        return this.isAligned(this.getIO("Cannot check alignment").getTargetAlignment());
    }

    public boolean isAligned(long alignment) {
        return Pointer.isAligned(this.getPeer(), alignment);
    }

    protected static boolean isAligned(long address, long alignment) {
        return Pointer.computeRemainder(address, alignment) == 0;
    }

    protected static int computeRemainder(long address, long alignment) {
        switch ((int)alignment) {
            case -1: 
            case 0: 
            case 1: {
                return 0;
            }
            case 2: {
                return (int)(address & 1L);
            }
            case 4: {
                return (int)(address & 3L);
            }
            case 8: {
                return (int)(address & 7L);
            }
            case 16: {
                return (int)(address & 0xFL);
            }
            case 32: {
                return (int)(address & 0x1FL);
            }
            case 64: {
                return (int)(address & 0x3FL);
            }
        }
        if (alignment < 0L) {
            return 0;
        }
        return (int)(address % alignment);
    }

    public T get() {
        return this.get(0L);
    }

    public static <T> T get(Pointer<T> pointer) {
        return pointer == null ? null : (T)pointer.get();
    }

    public T get(long index) {
        return this.getIO("Cannot get pointed value").get(this, index);
    }

    public T set(T value) {
        return this.set(0L, value);
    }

    private static long getTargetSizeToAllocateArrayOrThrow(PointerIO<?> io2) {
        long targetSize = -1L;
        if (io2 == null || (targetSize = io2.getTargetSize()) < 0L) {
            Pointer.throwBecauseUntyped("Cannot allocate array ");
        }
        return targetSize;
    }

    private static void throwBecauseUntyped(String message) {
        throw new RuntimeException("Pointer is not typed (call Pointer.as(Type) to create a typed pointer) : " + message);
    }

    static void throwUnexpected(Throwable ex2) {
        throw new RuntimeException("Unexpected error", ex2);
    }

    public T set(long index, T value) {
        this.getIO("Cannot set pointed value").set(this, index, value);
        return value;
    }

    public static long getPeer(Pointer<?> pointer) {
        return pointer == null ? 0L : pointer.getPeer();
    }

    public long getTargetSize() {
        return this.getIO("Cannot compute target size").getTargetSize();
    }

    public Pointer<T> next() {
        return this.next(1L);
    }

    public Pointer<T> next(long delta) {
        return this.offset(this.getIO("Cannot get pointers to next or previous targets").getTargetSize() * delta);
    }

    public static void release(Pointer ... pointers) {
        for (Pointer pointer : pointers) {
            if (pointer == null) continue;
            pointer.release();
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Pointer)) {
            return false;
        }
        Pointer p2 = (Pointer)obj;
        return this.getPeer() == p2.getPeer();
    }

    @Deprecated
    public static Pointer<?> pointerToAddress(long peer) {
        return Pointer.newPointer(null, peer, true, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, NO_PARENT, null, null);
    }

    @Deprecated
    public static Pointer<?> pointerToAddress(long peer, long size) {
        return Pointer.newPointer(null, peer, true, peer, peer + size, null, NO_PARENT, null, null);
    }

    public static <P> Pointer<P> pointerToAddress(long peer, Class<P> targetClass, Releaser releaser) {
        return Pointer.pointerToAddress(peer, targetClass, releaser);
    }

    public static <P> Pointer<P> pointerToAddress(long peer, Type targetType, Releaser releaser) {
        PointerIO pio = PointerIO.getInstance(targetType);
        return Pointer.newPointer(pio, peer, true, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, -1L, releaser, null);
    }

    public static <P> Pointer<P> pointerToAddress(long peer, PointerIO<P> io2) {
        return Pointer.newPointer(io2, peer, true, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, NO_PARENT, null, null);
    }

    static <P> Pointer<P> pointerToAddress(long peer, PointerIO<P> io2, Releaser releaser) {
        return Pointer.newPointer(io2, peer, true, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, NO_PARENT, releaser, null);
    }

    @Deprecated
    public static Pointer<?> pointerToAddress(long peer, Releaser releaser) {
        return Pointer.newPointer(null, peer, true, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, NO_PARENT, releaser, null);
    }

    public static Pointer<?> pointerToAddress(long peer, long size, Releaser releaser) {
        return Pointer.newPointer(null, peer, true, peer, peer + size, null, NO_PARENT, releaser, null);
    }

    @Deprecated
    public static <P> Pointer<P> pointerToAddress(long peer, Class<P> targetClass) {
        return Pointer.pointerToAddress(peer, targetClass);
    }

    @Deprecated
    public static <P> Pointer<P> pointerToAddress(long peer, Type targetType) {
        return Pointer.newPointer(PointerIO.getInstance(targetType), peer, true, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, -1L, null, null);
    }

    static <U> Pointer<U> pointerToAddress(long peer, long size, PointerIO<U> io2) {
        return Pointer.newPointer(io2, peer, true, peer, peer + size, null, NO_PARENT, null, null);
    }

    static <U> Pointer<U> newPointer(PointerIO<U> io2, long peer, boolean ordered, long validStart, long validEnd, Pointer<?> parent, long offsetInParent, final Releaser releaser, Object sibling) {
        long size;
        if (peer == 0L) {
            return null;
        }
        if (validEnd != UNKNOWN_VALIDITY && (size = validEnd - validStart) <= 0L) {
            return null;
        }
        if (releaser == null) {
            if (ordered) {
                return new OrderedPointer<U>(io2, peer, validStart, validEnd, parent, offsetInParent, sibling);
            }
            return new DisorderedPointer<U>(io2, peer, validStart, validEnd, parent, offsetInParent, sibling);
        }
        assert (sibling == null);
        if (ordered) {
            return new OrderedPointer<U>(io2, peer, validStart, validEnd, parent, offsetInParent, sibling){
                private volatile Releaser rel;
                {
                    super(x0, x1, x2, x3, x4, x5, x6);
                    this.rel = releaser;
                }

                @Override
                public synchronized void release() {
                    if (this.rel != null) {
                        Releaser rel = this.rel;
                        this.rel = null;
                        rel.release(this);
                    }
                }

                protected void finalize() {
                    this.release();
                }

                @Override
                @Deprecated
                public synchronized Pointer<U> withReleaser(final Releaser beforeDeallocation) {
                    final Releaser thisReleaser = this.rel;
                    this.rel = null;
                    return 2.newPointer(this.getIO(), this.getPeer(), this.isOrdered(), this.getValidStart(), this.getValidEnd(), null, NO_PARENT, beforeDeallocation == null ? thisReleaser : new Releaser(){

                        @Override
                        public void release(Pointer<?> p2) {
                            beforeDeallocation.release(p2);
                            if (thisReleaser != null) {
                                thisReleaser.release(p2);
                            }
                        }
                    }, null);
                }
            };
        }
        return new DisorderedPointer<U>(io2, peer, validStart, validEnd, parent, offsetInParent, sibling){
            private volatile Releaser rel;
            {
                super(x0, x1, x2, x3, x4, x5, x6);
                this.rel = releaser;
            }

            @Override
            public synchronized void release() {
                if (this.rel != null) {
                    Releaser rel = this.rel;
                    this.rel = null;
                    rel.release(this);
                }
            }

            protected void finalize() {
                this.release();
            }

            @Override
            @Deprecated
            public synchronized Pointer<U> withReleaser(final Releaser beforeDeallocation) {
                final Releaser thisReleaser = this.rel;
                this.rel = null;
                return 3.newPointer(this.getIO(), this.getPeer(), this.isOrdered(), this.getValidStart(), this.getValidEnd(), null, NO_PARENT, beforeDeallocation == null ? thisReleaser : new Releaser(){

                    @Override
                    public void release(Pointer<?> p2) {
                        beforeDeallocation.release(p2);
                        if (thisReleaser != null) {
                            thisReleaser.release(p2);
                        }
                    }
                }, null);
            }
        };
    }

    public static <P extends TypedPointer> Pointer<P> allocateTypedPointer(Class<P> type) {
        return Pointer.allocate(PointerIO.getInstance(type));
    }

    public static <P extends TypedPointer> Pointer<P> allocateTypedPointers(Class<P> type, long arrayLength) {
        return Pointer.allocateArray(PointerIO.getInstance(type), arrayLength);
    }

    public static <P> Pointer<Pointer<P>> allocatePointer(Class<P> targetType) {
        return Pointer.allocatePointer(targetType);
    }

    public static <P> Pointer<Pointer<P>> allocatePointer(Type targetType) {
        return Pointer.allocate(PointerIO.getPointerInstance(targetType));
    }

    public static <P> Pointer<Pointer<Pointer<P>>> allocatePointerPointer(Type targetType) {
        return Pointer.allocatePointer(Pointer.pointerType(targetType));
    }

    public static <P> Pointer<Pointer<Pointer<P>>> allocatePointerPointer(Class<P> targetType) {
        return Pointer.allocatePointerPointer(targetType);
    }

    public static <V> Pointer<Pointer<?>> allocatePointer() {
        return Pointer.allocate(PointerIO.getPointerInstance());
    }

    public static Pointer<Pointer<?>> allocatePointers(int arrayLength) {
        return Pointer.allocateArray(PointerIO.getPointerInstance(), (long)arrayLength);
    }

    public static <P> Pointer<Pointer<P>> allocatePointers(Class<P> targetType, int arrayLength) {
        return Pointer.allocatePointers(targetType, arrayLength);
    }

    public static <P> Pointer<Pointer<P>> allocatePointers(Type targetType, int arrayLength) {
        return Pointer.allocateArray(PointerIO.getPointerInstance(targetType), (long)arrayLength);
    }

    public static <V> Pointer<V> allocate(Class<V> elementClass) {
        return Pointer.allocate(elementClass);
    }

    public static <V> Pointer<V> allocate(Type elementClass) {
        return Pointer.allocateArray(elementClass, 1L);
    }

    public static <V> Pointer<V> allocate(PointerIO<V> io2) {
        return Pointer.allocateBytes(io2, Pointer.getTargetSizeToAllocateArrayOrThrow(io2), null);
    }

    public static <V> Pointer<V> allocateArray(PointerIO<V> io2, long arrayLength) {
        return Pointer.allocateBytes(io2, Pointer.getTargetSizeToAllocateArrayOrThrow(io2) * arrayLength, null);
    }

    public static <V> Pointer<V> allocateArray(PointerIO<V> io2, long arrayLength, Releaser beforeDeallocation) {
        return Pointer.allocateBytes(io2, Pointer.getTargetSizeToAllocateArrayOrThrow(io2) * arrayLength, beforeDeallocation);
    }

    public static <V> Pointer<V> allocateBytes(PointerIO<V> io2, long byteSize, Releaser beforeDeallocation) {
        return Pointer.allocateAlignedBytes(io2, byteSize, defaultAlignment, beforeDeallocation);
    }

    public static <V> Pointer<V> allocateAlignedBytes(PointerIO<V> io2, long byteSize, int alignment, final Releaser beforeDeallocation) {
        long address;
        if (byteSize == 0L) {
            return null;
        }
        if (byteSize < 0L) {
            throw new IllegalArgumentException("Cannot allocate a negative amount of memory !");
        }
        long offset = 0L;
        if (alignment <= 1) {
            address = JNI.mallocNulled(byteSize);
        } else {
            address = JNI.mallocNulled(byteSize + (long)alignment - 1L);
            long remainder = address % (long)alignment;
            if (remainder > 0L) {
                offset = (long)alignment - remainder;
            }
        }
        if (address == 0L) {
            throw new RuntimeException("Failed to allocate " + byteSize);
        }
        Pointer<V> ptr = Pointer.newPointer(io2, address, true, address, address + byteSize + offset, null, NO_PARENT, beforeDeallocation == null ? freeReleaser : new Releaser(){

            @Override
            public void release(Pointer<?> p2) {
                beforeDeallocation.release(p2);
                freeReleaser.release(p2);
            }
        }, null);
        if (offset > 0L) {
            ptr = ptr.offset(offset);
        }
        return ptr;
    }

    @Deprecated
    public synchronized Pointer<T> withReleaser(Releaser beforeDeallocation) {
        return Pointer.newPointer(this.getIO(), this.getPeer(), this.isOrdered(), this.getValidStart(), this.getValidEnd(), null, NO_PARENT, beforeDeallocation, null);
    }

    public static <V> Pointer<V> allocateArray(Class<V> elementClass, long arrayLength) {
        return Pointer.allocateArray(elementClass, arrayLength);
    }

    public static <V> Pointer<V> allocateArray(Type elementClass, long arrayLength) {
        if (arrayLength == 0L) {
            return null;
        }
        PointerIO pio = PointerIO.getInstance(elementClass);
        if (pio == null) {
            throw new UnsupportedOperationException("Cannot allocate memory for type " + (elementClass instanceof Class ? ((Class)elementClass).getName() : elementClass.toString()));
        }
        return Pointer.allocateArray(pio, arrayLength);
    }

    public static <V> Pointer<V> allocateAlignedArray(Class<V> elementClass, long arrayLength, int alignment) {
        return Pointer.allocateAlignedArray(elementClass, arrayLength, alignment);
    }

    public static <V> Pointer<V> allocateAlignedArray(Type elementClass, long arrayLength, int alignment) {
        PointerIO io2 = PointerIO.getInstance(elementClass);
        if (io2 == null) {
            throw new UnsupportedOperationException("Cannot allocate memory for type " + (elementClass instanceof Class ? ((Class)elementClass).getName() : elementClass.toString()));
        }
        return Pointer.allocateAlignedBytes(io2, Pointer.getTargetSizeToAllocateArrayOrThrow(io2) * arrayLength, alignment, null);
    }

    public static Pointer<?> pointerToBuffer(Buffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (buffer instanceof IntBuffer) {
            return Pointer.pointerToInts((IntBuffer)buffer);
        }
        if (buffer instanceof LongBuffer) {
            return Pointer.pointerToLongs((LongBuffer)buffer);
        }
        if (buffer instanceof ShortBuffer) {
            return Pointer.pointerToShorts((ShortBuffer)buffer);
        }
        if (buffer instanceof ByteBuffer) {
            return Pointer.pointerToBytes((ByteBuffer)buffer);
        }
        if (buffer instanceof CharBuffer) {
            return Pointer.pointerToChars((CharBuffer)buffer);
        }
        if (buffer instanceof FloatBuffer) {
            return Pointer.pointerToFloats((FloatBuffer)buffer);
        }
        if (buffer instanceof DoubleBuffer) {
            return Pointer.pointerToDoubles((DoubleBuffer)buffer);
        }
        throw new UnsupportedOperationException("Unhandled buffer type : " + buffer.getClass().getName());
    }

    public void updateBuffer(Buffer buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException("Cannot update a null Buffer !");
        }
        if (Utils.isDirect(buffer)) {
            long address = JNI.getDirectBufferAddress(buffer);
            if (address != this.getPeer()) {
                throw new IllegalArgumentException("Direct buffer does not point to the same location as this Pointer instance, updating it makes no sense !");
            }
        } else {
            if (buffer instanceof IntBuffer) {
                ((IntBuffer)buffer).duplicate().put(this.getIntBuffer());
                return;
            }
            if (buffer instanceof LongBuffer) {
                ((LongBuffer)buffer).duplicate().put(this.getLongBuffer());
                return;
            }
            if (buffer instanceof ShortBuffer) {
                ((ShortBuffer)buffer).duplicate().put(this.getShortBuffer());
                return;
            }
            if (buffer instanceof ByteBuffer) {
                ((ByteBuffer)buffer).duplicate().put(this.getByteBuffer());
                return;
            }
            if (buffer instanceof FloatBuffer) {
                ((FloatBuffer)buffer).duplicate().put(this.getFloatBuffer());
                return;
            }
            if (buffer instanceof DoubleBuffer) {
                ((DoubleBuffer)buffer).duplicate().put(this.getDoubleBuffer());
                return;
            }
            throw new UnsupportedOperationException("Unhandled buffer type : " + buffer.getClass().getName());
        }
    }

    public static Pointer<Integer> pointerToInt(int value) {
        Pointer<Integer> mem = Pointer.allocate(PointerIO.getIntInstance());
        mem.setInt(value);
        return mem;
    }

    public static Pointer<Integer> pointerToInts(int ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Integer> mem = Pointer.allocateArray(PointerIO.getIntInstance(), (long)values.length);
        mem.setIntsAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Integer>> pointerToInts(int[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Integer>> mem = Pointer.allocateInts(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setIntsAtOffset((long)(i1 * dim2 * 4), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Integer>>> pointerToInts(int[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Integer>>> mem = Pointer.allocateInts(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setIntsAtOffset((long)(offset2 * 4), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Integer> allocateInt() {
        return Pointer.allocate(PointerIO.getIntInstance());
    }

    public static Pointer<Integer> allocateInts(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getIntInstance(), arrayLength);
    }

    public static Pointer<Pointer<Integer>> allocateInts(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getIntInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Integer>>> allocateInts(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getIntInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Long> pointerToLong(long value) {
        Pointer<Long> mem = Pointer.allocate(PointerIO.getLongInstance());
        mem.setLong(value);
        return mem;
    }

    public static Pointer<Long> pointerToLongs(long ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Long> mem = Pointer.allocateArray(PointerIO.getLongInstance(), (long)values.length);
        mem.setLongsAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Long>> pointerToLongs(long[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Long>> mem = Pointer.allocateLongs(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setLongsAtOffset((long)(i1 * dim2 * 8), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Long>>> pointerToLongs(long[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Long>>> mem = Pointer.allocateLongs(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setLongsAtOffset((long)(offset2 * 8), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Long> allocateLong() {
        return Pointer.allocate(PointerIO.getLongInstance());
    }

    public static Pointer<Long> allocateLongs(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getLongInstance(), arrayLength);
    }

    public static Pointer<Pointer<Long>> allocateLongs(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getLongInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Long>>> allocateLongs(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getLongInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Short> pointerToShort(short value) {
        Pointer<Short> mem = Pointer.allocate(PointerIO.getShortInstance());
        mem.setShort(value);
        return mem;
    }

    public static Pointer<Short> pointerToShorts(short ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Short> mem = Pointer.allocateArray(PointerIO.getShortInstance(), (long)values.length);
        mem.setShortsAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Short>> pointerToShorts(short[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Short>> mem = Pointer.allocateShorts(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setShortsAtOffset((long)(i1 * dim2 * 2), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Short>>> pointerToShorts(short[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Short>>> mem = Pointer.allocateShorts(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setShortsAtOffset((long)(offset2 * 2), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Short> allocateShort() {
        return Pointer.allocate(PointerIO.getShortInstance());
    }

    public static Pointer<Short> allocateShorts(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getShortInstance(), arrayLength);
    }

    public static Pointer<Pointer<Short>> allocateShorts(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getShortInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Short>>> allocateShorts(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getShortInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Byte> pointerToByte(byte value) {
        Pointer<Byte> mem = Pointer.allocate(PointerIO.getByteInstance());
        mem.setByte(value);
        return mem;
    }

    public static Pointer<Byte> pointerToBytes(byte ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Byte> mem = Pointer.allocateArray(PointerIO.getByteInstance(), (long)values.length);
        mem.setBytesAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Byte>> pointerToBytes(byte[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Byte>> mem = Pointer.allocateBytes(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setBytesAtOffset((long)(i1 * dim2 * 1), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Byte>>> pointerToBytes(byte[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Byte>>> mem = Pointer.allocateBytes(dim1, (long)dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setBytesAtOffset((long)(offset2 * 1), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Byte> allocateByte() {
        return Pointer.allocate(PointerIO.getByteInstance());
    }

    public static Pointer<Byte> allocateBytes(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getByteInstance(), arrayLength);
    }

    public static Pointer<Pointer<Byte>> allocateBytes(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getByteInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Byte>>> allocateBytes(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getByteInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Character> pointerToChar(char value) {
        Pointer<Character> mem = Pointer.allocate(PointerIO.getCharInstance());
        mem.setChar(value);
        return mem;
    }

    public static Pointer<Character> pointerToChars(char ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Character> mem = Pointer.allocateArray(PointerIO.getCharInstance(), (long)values.length);
        mem.setCharsAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Character>> pointerToChars(char[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Character>> mem = Pointer.allocateChars(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setCharsAtOffset((long)(i1 * dim2 * Platform.WCHAR_T_SIZE), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Character>>> pointerToChars(char[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Character>>> mem = Pointer.allocateChars(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setCharsAtOffset((long)(offset2 * Platform.WCHAR_T_SIZE), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Character> allocateChar() {
        return Pointer.allocate(PointerIO.getCharInstance());
    }

    public static Pointer<Character> allocateChars(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getCharInstance(), arrayLength);
    }

    public static Pointer<Pointer<Character>> allocateChars(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getCharInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Character>>> allocateChars(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getCharInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Float> pointerToFloat(float value) {
        Pointer<Float> mem = Pointer.allocate(PointerIO.getFloatInstance());
        mem.setFloat(value);
        return mem;
    }

    public static Pointer<Float> pointerToFloats(float ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Float> mem = Pointer.allocateArray(PointerIO.getFloatInstance(), (long)values.length);
        mem.setFloatsAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Float>> pointerToFloats(float[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Float>> mem = Pointer.allocateFloats(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setFloatsAtOffset((long)(i1 * dim2 * 4), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Float>>> pointerToFloats(float[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Float>>> mem = Pointer.allocateFloats(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setFloatsAtOffset((long)(offset2 * 4), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Float> allocateFloat() {
        return Pointer.allocate(PointerIO.getFloatInstance());
    }

    public static Pointer<Float> allocateFloats(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getFloatInstance(), arrayLength);
    }

    public static Pointer<Pointer<Float>> allocateFloats(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getFloatInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Float>>> allocateFloats(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getFloatInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Double> pointerToDouble(double value) {
        Pointer<Double> mem = Pointer.allocate(PointerIO.getDoubleInstance());
        mem.setDouble(value);
        return mem;
    }

    public static Pointer<Double> pointerToDoubles(double ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Double> mem = Pointer.allocateArray(PointerIO.getDoubleInstance(), (long)values.length);
        mem.setDoublesAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Double>> pointerToDoubles(double[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Double>> mem = Pointer.allocateDoubles(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setDoublesAtOffset((long)(i1 * dim2 * 8), values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Double>>> pointerToDoubles(double[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Double>>> mem = Pointer.allocateDoubles(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setDoublesAtOffset((long)(offset2 * 8), values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Double> allocateDouble() {
        return Pointer.allocate(PointerIO.getDoubleInstance());
    }

    public static Pointer<Double> allocateDoubles(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getDoubleInstance(), arrayLength);
    }

    public static Pointer<Pointer<Double>> allocateDoubles(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getDoubleInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Double>>> allocateDoubles(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getDoubleInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Boolean> pointerToBoolean(boolean value) {
        Pointer<Boolean> mem = Pointer.allocate(PointerIO.getBooleanInstance());
        mem.setBoolean(value);
        return mem;
    }

    public static Pointer<Boolean> pointerToBooleans(boolean ... values) {
        if (values == null) {
            return null;
        }
        Pointer<Boolean> mem = Pointer.allocateArray(PointerIO.getBooleanInstance(), (long)values.length);
        mem.setBooleansAtOffset(0L, values, 0, values.length);
        return mem;
    }

    public static Pointer<Pointer<Boolean>> pointerToBooleans(boolean[][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        Pointer<Pointer<Boolean>> mem = Pointer.allocateBooleans(dim1, dim2);
        for (int i1 = 0; i1 < dim1; ++i1) {
            mem.setBooleansAtOffset(i1 * dim2 * 1, values[i1], 0, dim2);
        }
        return mem;
    }

    public static Pointer<Pointer<Pointer<Boolean>>> pointerToBooleans(boolean[][][] values) {
        if (values == null) {
            return null;
        }
        int dim1 = values.length;
        int dim2 = values[0].length;
        int dim3 = values[0][0].length;
        Pointer<Pointer<Pointer<Boolean>>> mem = Pointer.allocateBooleans(dim1, dim2, dim3);
        for (int i1 = 0; i1 < dim1; ++i1) {
            int offset1 = i1 * dim2;
            for (int i2 = 0; i2 < dim2; ++i2) {
                int offset2 = (offset1 + i2) * dim3;
                mem.setBooleansAtOffset(offset2 * 1, values[i1][i2], 0, dim3);
            }
        }
        return mem;
    }

    public static Pointer<Boolean> allocateBoolean() {
        return Pointer.allocate(PointerIO.getBooleanInstance());
    }

    public static Pointer<Boolean> allocateBooleans(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getBooleanInstance(), arrayLength);
    }

    public static Pointer<Pointer<Boolean>> allocateBooleans(long dim1, long dim2) {
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getBooleanInstance(), new long[]{dim1, dim2}, 0), dim1);
    }

    public static Pointer<Pointer<Pointer<Boolean>>> allocateBooleans(long dim1, long dim2, long dim3) {
        long[] dims = new long[]{dim1, dim2, dim3};
        return Pointer.allocateArray(PointerIO.getArrayInstance(PointerIO.getArrayInstance(PointerIO.getBooleanInstance(), dims, 1), dims, 0), dim1);
    }

    public static Pointer<Integer> pointerToInts(IntBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToInts(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= 4L) == 0L) {
            return null;
        }
        PointerIO<Integer> io2 = CommonPointerIOs.intIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public static Pointer<Long> pointerToLongs(LongBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToLongs(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= 8L) == 0L) {
            return null;
        }
        PointerIO<Long> io2 = CommonPointerIOs.longIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public static Pointer<Short> pointerToShorts(ShortBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToShorts(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= 2L) == 0L) {
            return null;
        }
        PointerIO<Short> io2 = CommonPointerIOs.shortIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public static Pointer<Byte> pointerToBytes(ByteBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToBytes(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= 1L) == 0L) {
            return null;
        }
        PointerIO<Byte> io2 = CommonPointerIOs.byteIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public static Pointer<Character> pointerToChars(CharBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToChars(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= (long)Platform.WCHAR_T_SIZE) == 0L) {
            return null;
        }
        PointerIO<Character> io2 = CommonPointerIOs.charIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public static Pointer<Float> pointerToFloats(FloatBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToFloats(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= 4L) == 0L) {
            return null;
        }
        PointerIO<Float> io2 = CommonPointerIOs.floatIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public static Pointer<Double> pointerToDoubles(DoubleBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (!buffer.isDirect()) {
            return Pointer.pointerToDoubles(buffer.array());
        }
        long address = JNI.getDirectBufferAddress(buffer);
        long size = JNI.getDirectBufferCapacity(buffer);
        if (address == 0L || (size *= 8L) == 0L) {
            return null;
        }
        PointerIO<Double> io2 = CommonPointerIOs.doubleIO;
        boolean ordered = buffer.order().equals(ByteOrder.nativeOrder());
        return Pointer.newPointer(io2, address, ordered, address, address + size, null, NO_PARENT, null, buffer);
    }

    public Type getTargetType() {
        PointerIO<T> io2 = this.getIO();
        return io2 == null ? null : io2.getTargetType();
    }

    @Deprecated
    public Pointer<?> getPointer() {
        return this.getPointerAtOffset(0L, (PointerIO)null);
    }

    public Pointer<?> getPointerAtOffset(long byteOffset) {
        return this.getPointerAtOffset(byteOffset, (PointerIO)null);
    }

    public Pointer<?> getPointerAtIndex(long valueIndex) {
        return this.getPointerAtOffset(valueIndex * (long)SIZE);
    }

    public <U> Pointer<U> getPointer(Class<U> c2) {
        return this.getPointerAtOffset(0L, PointerIO.getInstance(c2));
    }

    public <U> Pointer<U> getPointer(PointerIO<U> pio) {
        return this.getPointerAtOffset(0L, pio);
    }

    public <U> Pointer<U> getPointerAtOffset(long byteOffset, Class<U> c2) {
        return this.getPointerAtOffset(byteOffset, (Type)c2);
    }

    public <U> Pointer<U> getPointerAtOffset(long byteOffset, Type t2) {
        return this.getPointerAtOffset(byteOffset, t2 == null ? null : PointerIO.getInstance(t2));
    }

    public <U> Pointer<U> getPointerAtOffset(long byteOffset, PointerIO<U> pio) {
        long value = this.getSizeTAtOffset(byteOffset);
        if (value == 0L) {
            return null;
        }
        return Pointer.newPointer(pio, value, this.isOrdered(), UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, this, byteOffset, null, null);
    }

    public Pointer<T> setPointer(Pointer<?> value) {
        return this.setPointerAtOffset(0L, value);
    }

    public Pointer<T> setPointerAtOffset(long byteOffset, Pointer<?> value) {
        this.setSizeTAtOffset(byteOffset, value == null ? 0L : value.getPeer());
        return this;
    }

    public Pointer<T> setPointerAtIndex(long valueIndex, Pointer<?> value) {
        this.setPointerAtOffset(valueIndex * (long)SIZE, value);
        return this;
    }

    public Pointer<?>[] getPointersAtOffset(long byteOffset, int arrayLength) {
        return this.getPointersAtOffset(byteOffset, arrayLength, (PointerIO)null);
    }

    @Deprecated
    public Pointer<?>[] getPointers() {
        long rem = this.getValidElements("Cannot create array if remaining length is not known. Please use getPointers(int length) instead.");
        return this.getPointersAtOffset(0L, (int)rem);
    }

    @Deprecated
    public Pointer<?>[] getPointers(int arrayLength) {
        return this.getPointersAtOffset(0L, arrayLength);
    }

    public <U> Pointer<U>[] getPointersAtOffset(long byteOffset, int arrayLength, Type t2) {
        return this.getPointersAtOffset(byteOffset, arrayLength, t2 == null ? null : PointerIO.getInstance(t2));
    }

    public <U> Pointer<U>[] getPointersAtOffset(long byteOffset, int arrayLength, Class<U> t2) {
        return this.getPointersAtOffset(byteOffset, arrayLength, (Type)t2);
    }

    public <U> Pointer<U>[] getPointersAtOffset(long byteOffset, int arrayLength, PointerIO pio) {
        Pointer[] values = new Pointer[arrayLength];
        int s2 = Platform.POINTER_SIZE;
        for (int i2 = 0; i2 < arrayLength; ++i2) {
            values[i2] = this.getPointerAtOffset(byteOffset + (long)(i2 * s2), pio);
        }
        return values;
    }

    public Pointer<T> setPointersAtOffset(long byteOffset, Pointer<?>[] values) {
        return this.setPointersAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setPointersAtOffset(long byteOffset, Pointer<?>[] values, int valuesOffset, int length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        int n2 = length;
        int s2 = Platform.POINTER_SIZE;
        for (int i2 = 0; i2 < n2; ++i2) {
            this.setPointerAtOffset(byteOffset + (long)(i2 * s2), values[valuesOffset + i2]);
        }
        return this;
    }

    public Pointer<T> setPointers(Pointer<?>[] values) {
        return this.setPointersAtOffset(0L, values);
    }

    public Object getArrayAtOffset(long byteOffset, int length) {
        return this.getIO("Cannot create sublist").getArray(this, byteOffset, length);
    }

    public Object getArray(int length) {
        return this.getArrayAtOffset(0L, length);
    }

    public Object getArray() {
        return this.getArray((int)this.getValidElements());
    }

    public <B extends Buffer> B getBufferAtOffset(long byteOffset, int length) {
        return this.getIO("Cannot create Buffer").getBuffer(this, byteOffset, length);
    }

    public <B extends Buffer> B getBuffer(int length) {
        return this.getBufferAtOffset(0L, length);
    }

    public <B extends Buffer> B getBuffer() {
        return this.getBuffer((int)this.getValidElements());
    }

    public Pointer<T> setArrayAtOffset(long byteOffset, Object array) {
        this.getIO("Cannot create sublist").setArray(this, byteOffset, array);
        return this;
    }

    public static <T> Pointer<T> pointerToArray(Object array) {
        if (array == null) {
            return null;
        }
        PointerIO io2 = PointerIO.getArrayIO(array);
        if (io2 == null) {
            Pointer.throwBecauseUntyped("Cannot create pointer to array");
        }
        Pointer ptr = Pointer.allocateArray(io2, (long)Array.getLength(array));
        io2.setArray(ptr, 0L, array);
        return ptr;
    }

    public Pointer<T> setArray(Object array) {
        return this.setArrayAtOffset(0L, array);
    }

    public static Pointer<SizeT> pointerToSizeT(long value) {
        Pointer<SizeT> p2 = Pointer.allocate(PointerIO.getSizeTInstance());
        p2.setSizeT(value);
        return p2;
    }

    public static Pointer<SizeT> pointerToSizeT(SizeT value) {
        Pointer<SizeT> p2 = Pointer.allocate(PointerIO.getSizeTInstance());
        p2.setSizeT(value);
        return p2;
    }

    public static Pointer<SizeT> pointerToSizeTs(long ... values) {
        if (values == null) {
            return null;
        }
        return Pointer.allocateArray(PointerIO.getSizeTInstance(), (long)values.length).setSizeTsAtOffset(0L, values);
    }

    public static Pointer<SizeT> pointerToSizeTs(SizeT ... values) {
        if (values == null) {
            return null;
        }
        return Pointer.allocateArray(PointerIO.getSizeTInstance(), (long)values.length).setSizeTsAtOffset(0L, values);
    }

    public static Pointer<SizeT> pointerToSizeTs(int[] values) {
        if (values == null) {
            return null;
        }
        return Pointer.allocateArray(PointerIO.getSizeTInstance(), (long)values.length).setSizeTsAtOffset(0L, values);
    }

    public static Pointer<SizeT> allocateSizeTs(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getSizeTInstance(), arrayLength);
    }

    public static Pointer<SizeT> allocateSizeT() {
        return Pointer.allocate(PointerIO.getSizeTInstance());
    }

    public long getSizeT() {
        return SizeT.SIZE == 8 ? this.getLong() : (long)this.getInt();
    }

    public long getSizeTAtOffset(long byteOffset) {
        return SizeT.SIZE == 8 ? this.getLongAtOffset(byteOffset) : (long)this.getIntAtOffset(byteOffset);
    }

    public long getSizeTAtIndex(long valueIndex) {
        return this.getSizeTAtOffset(valueIndex * (long)SizeT.SIZE);
    }

    public long[] getSizeTs() {
        long rem = this.getValidElements("Cannot create array if remaining length is not known. Please use getSizeTs(int length) instead.");
        if (SizeT.SIZE == 8) {
            return this.getLongs((int)rem);
        }
        return this.getSizeTs((int)rem);
    }

    public long[] getSizeTs(int arrayLength) {
        if (SizeT.SIZE == 8) {
            return this.getLongs(arrayLength);
        }
        return this.getSizeTsAtOffset(0L, arrayLength);
    }

    public long[] getSizeTsAtOffset(long byteOffset, int arrayLength) {
        if (SizeT.SIZE == 8) {
            return this.getLongsAtOffset(byteOffset, arrayLength);
        }
        int[] values = this.getIntsAtOffset(byteOffset, arrayLength);
        long[] ret = new long[arrayLength];
        for (int i2 = 0; i2 < arrayLength; ++i2) {
            ret[i2] = values[i2];
        }
        return ret;
    }

    public Pointer<T> setSizeT(long value) {
        if (SizeT.SIZE == 8) {
            this.setLong(value);
        } else {
            this.setInt(SizeT.safeIntCast(value));
        }
        return this;
    }

    public Pointer<T> setSizeT(SizeT value) {
        return this.setSizeT(value.longValue());
    }

    public Pointer<T> setSizeTAtOffset(long byteOffset, long value) {
        if (SizeT.SIZE == 8) {
            this.setLongAtOffset(byteOffset, value);
        } else {
            this.setIntAtOffset(byteOffset, SizeT.safeIntCast(value));
        }
        return this;
    }

    public Pointer<T> setSizeTAtIndex(long valueIndex, long value) {
        return this.setSizeTAtOffset(valueIndex * (long)SizeT.SIZE, value);
    }

    public Pointer<T> setSizeTAtOffset(long byteOffset, SizeT value) {
        return this.setSizeTAtOffset(byteOffset, value.longValue());
    }

    public Pointer<T> setSizeTs(long[] values) {
        if (SizeT.SIZE == 8) {
            return this.setLongs(values);
        }
        return this.setSizeTsAtOffset(0L, values);
    }

    public Pointer<T> setSizeTs(int[] values) {
        if (SizeT.SIZE == 4) {
            return this.setInts(values);
        }
        return this.setSizeTsAtOffset(0L, values);
    }

    public Pointer<T> setSizeTs(SizeT[] values) {
        return this.setSizeTsAtOffset(0L, values);
    }

    public Pointer<T> setSizeTsAtOffset(long byteOffset, long[] values) {
        return this.setSizeTsAtOffset(byteOffset, values, 0, values.length);
    }

    public abstract Pointer<T> setSizeTsAtOffset(long var1, long[] var3, int var4, int var5);

    public Pointer<T> setSizeTsAtOffset(long byteOffset, SizeT ... values) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        int n2 = values.length;
        int s2 = SizeT.SIZE;
        for (int i2 = 0; i2 < n2; ++i2) {
            this.setSizeTAtOffset(byteOffset + (long)(i2 * s2), values[i2].longValue());
        }
        return this;
    }

    public abstract Pointer<T> setSizeTsAtOffset(long var1, int[] var3);

    public static Pointer<CLong> pointerToCLong(long value) {
        Pointer<CLong> p2 = Pointer.allocate(PointerIO.getCLongInstance());
        p2.setCLong(value);
        return p2;
    }

    public static Pointer<CLong> pointerToCLong(CLong value) {
        Pointer<CLong> p2 = Pointer.allocate(PointerIO.getCLongInstance());
        p2.setCLong(value);
        return p2;
    }

    public static Pointer<CLong> pointerToCLongs(long ... values) {
        if (values == null) {
            return null;
        }
        return Pointer.allocateArray(PointerIO.getCLongInstance(), (long)values.length).setCLongsAtOffset(0L, values);
    }

    public static Pointer<CLong> pointerToCLongs(CLong ... values) {
        if (values == null) {
            return null;
        }
        return Pointer.allocateArray(PointerIO.getCLongInstance(), (long)values.length).setCLongsAtOffset(0L, values);
    }

    public static Pointer<CLong> pointerToCLongs(int[] values) {
        if (values == null) {
            return null;
        }
        return Pointer.allocateArray(PointerIO.getCLongInstance(), (long)values.length).setCLongsAtOffset(0L, values);
    }

    public static Pointer<CLong> allocateCLongs(long arrayLength) {
        return Pointer.allocateArray(PointerIO.getCLongInstance(), arrayLength);
    }

    public static Pointer<CLong> allocateCLong() {
        return Pointer.allocate(PointerIO.getCLongInstance());
    }

    public long getCLong() {
        return CLong.SIZE == 8 ? this.getLong() : (long)this.getInt();
    }

    public long getCLongAtOffset(long byteOffset) {
        return CLong.SIZE == 8 ? this.getLongAtOffset(byteOffset) : (long)this.getIntAtOffset(byteOffset);
    }

    public long getCLongAtIndex(long valueIndex) {
        return this.getCLongAtOffset(valueIndex * (long)CLong.SIZE);
    }

    public long[] getCLongs() {
        long rem = this.getValidElements("Cannot create array if remaining length is not known. Please use getCLongs(int length) instead.");
        if (CLong.SIZE == 8) {
            return this.getLongs((int)rem);
        }
        return this.getCLongs((int)rem);
    }

    public long[] getCLongs(int arrayLength) {
        if (CLong.SIZE == 8) {
            return this.getLongs(arrayLength);
        }
        return this.getCLongsAtOffset(0L, arrayLength);
    }

    public long[] getCLongsAtOffset(long byteOffset, int arrayLength) {
        if (CLong.SIZE == 8) {
            return this.getLongsAtOffset(byteOffset, arrayLength);
        }
        int[] values = this.getIntsAtOffset(byteOffset, arrayLength);
        long[] ret = new long[arrayLength];
        for (int i2 = 0; i2 < arrayLength; ++i2) {
            ret[i2] = values[i2];
        }
        return ret;
    }

    public Pointer<T> setCLong(long value) {
        if (CLong.SIZE == 8) {
            this.setLong(value);
        } else {
            this.setInt(SizeT.safeIntCast(value));
        }
        return this;
    }

    public Pointer<T> setCLong(CLong value) {
        return this.setCLong(value.longValue());
    }

    public Pointer<T> setCLongAtOffset(long byteOffset, long value) {
        if (CLong.SIZE == 8) {
            this.setLongAtOffset(byteOffset, value);
        } else {
            this.setIntAtOffset(byteOffset, SizeT.safeIntCast(value));
        }
        return this;
    }

    public Pointer<T> setCLongAtIndex(long valueIndex, long value) {
        return this.setCLongAtOffset(valueIndex * (long)CLong.SIZE, value);
    }

    public Pointer<T> setCLongAtOffset(long byteOffset, CLong value) {
        return this.setCLongAtOffset(byteOffset, value.longValue());
    }

    public Pointer<T> setCLongs(long[] values) {
        if (CLong.SIZE == 8) {
            return this.setLongs(values);
        }
        return this.setCLongsAtOffset(0L, values);
    }

    public Pointer<T> setCLongs(int[] values) {
        if (CLong.SIZE == 4) {
            return this.setInts(values);
        }
        return this.setCLongsAtOffset(0L, values);
    }

    public Pointer<T> setCLongs(CLong[] values) {
        return this.setCLongsAtOffset(0L, values);
    }

    public Pointer<T> setCLongsAtOffset(long byteOffset, long[] values) {
        return this.setCLongsAtOffset(byteOffset, values, 0, values.length);
    }

    public abstract Pointer<T> setCLongsAtOffset(long var1, long[] var3, int var4, int var5);

    public Pointer<T> setCLongsAtOffset(long byteOffset, CLong ... values) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        int n2 = values.length;
        int s2 = CLong.SIZE;
        for (int i2 = 0; i2 < n2; ++i2) {
            this.setCLongAtOffset(byteOffset + (long)(i2 * s2), values[i2].longValue());
        }
        return this;
    }

    public abstract Pointer<T> setCLongsAtOffset(long var1, int[] var3);

    void setSignedIntegralAtOffset(long byteOffset, long value, long sizeOfIntegral) {
        switch ((int)sizeOfIntegral) {
            case 1: {
                if (value > 127L || value < -128L) {
                    throw new RuntimeException("Value out of byte bounds : " + value);
                }
                this.setByteAtOffset(byteOffset, (byte)value);
                break;
            }
            case 2: {
                if (value > 32767L || value < -32768L) {
                    throw new RuntimeException("Value out of short bounds : " + value);
                }
                this.setShortAtOffset(byteOffset, (short)value);
                break;
            }
            case 4: {
                if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
                    throw new RuntimeException("Value out of int bounds : " + value);
                }
                this.setIntAtOffset(byteOffset, (int)value);
                break;
            }
            case 8: {
                this.setLongAtOffset(byteOffset, value);
                break;
            }
            default: {
                throw new IllegalArgumentException("Cannot write integral type of size " + sizeOfIntegral + " (value = " + value + ")");
            }
        }
    }

    long getSignedIntegralAtOffset(long byteOffset, long sizeOfIntegral) {
        switch ((int)sizeOfIntegral) {
            case 1: {
                return this.getByteAtOffset(byteOffset);
            }
            case 2: {
                return this.getShortAtOffset(byteOffset);
            }
            case 4: {
                return this.getIntAtOffset(byteOffset);
            }
            case 8: {
                return this.getLongAtOffset(byteOffset);
            }
        }
        throw new IllegalArgumentException("Cannot read integral type of size " + sizeOfIntegral);
    }

    public static <T> Pointer<Pointer<T>> pointerToPointer(Pointer<T> value) {
        Pointer<Pointer<T>> p2 = Pointer.allocate(PointerIO.getPointerInstance());
        p2.setPointerAtOffset(0L, value);
        return p2;
    }

    public static <T> Pointer<Pointer<T>> pointerToPointers(Pointer<T> ... values) {
        if (values == null) {
            return null;
        }
        int n2 = values.length;
        int s2 = SIZE;
        PointerIO<Pointer> pio = PointerIO.getPointerInstance();
        Pointer<Pointer<T>> p2 = Pointer.allocateArray(pio, (long)n2);
        for (int i2 = 0; i2 < n2; ++i2) {
            p2.setPointerAtOffset(i2 * s2, values[i2]);
        }
        return p2;
    }

    public Pointer<T> setValuesAtOffset(long byteOffset, Buffer values) {
        if (values instanceof IntBuffer) {
            this.setIntsAtOffset(byteOffset, (IntBuffer)values);
            return this;
        }
        if (values instanceof LongBuffer) {
            this.setLongsAtOffset(byteOffset, (LongBuffer)values);
            return this;
        }
        if (values instanceof ShortBuffer) {
            this.setShortsAtOffset(byteOffset, (ShortBuffer)values);
            return this;
        }
        if (values instanceof ByteBuffer) {
            this.setBytesAtOffset(byteOffset, (ByteBuffer)values);
            return this;
        }
        if (values instanceof CharBuffer) {
            this.setCharsAtOffset(byteOffset, (CharBuffer)values);
            return this;
        }
        if (values instanceof FloatBuffer) {
            this.setFloatsAtOffset(byteOffset, (FloatBuffer)values);
            return this;
        }
        if (values instanceof DoubleBuffer) {
            this.setDoublesAtOffset(byteOffset, (DoubleBuffer)values);
            return this;
        }
        throw new UnsupportedOperationException("Unhandled buffer type : " + values.getClass().getName());
    }

    public Pointer<T> setValuesAtOffset(long byteOffset, Buffer values, int valuesOffset, int length) {
        if (values instanceof IntBuffer) {
            this.setIntsAtOffset(byteOffset, (IntBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        if (values instanceof LongBuffer) {
            this.setLongsAtOffset(byteOffset, (LongBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        if (values instanceof ShortBuffer) {
            this.setShortsAtOffset(byteOffset, (ShortBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        if (values instanceof ByteBuffer) {
            this.setBytesAtOffset(byteOffset, (ByteBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        if (values instanceof CharBuffer) {
            this.setCharsAtOffset(byteOffset, (CharBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        if (values instanceof FloatBuffer) {
            this.setFloatsAtOffset(byteOffset, (FloatBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        if (values instanceof DoubleBuffer) {
            this.setDoublesAtOffset(byteOffset, (DoubleBuffer)values, (long)valuesOffset, (long)length);
            return this;
        }
        throw new UnsupportedOperationException("Unhandled buffer type : " + values.getClass().getName());
    }

    public Pointer<T> setValues(Buffer values) {
        if (values instanceof IntBuffer) {
            this.setInts((IntBuffer)values);
            return this;
        }
        if (values instanceof LongBuffer) {
            this.setLongs((LongBuffer)values);
            return this;
        }
        if (values instanceof ShortBuffer) {
            this.setShorts((ShortBuffer)values);
            return this;
        }
        if (values instanceof ByteBuffer) {
            this.setBytes((ByteBuffer)values);
            return this;
        }
        if (values instanceof CharBuffer) {
            this.setChars((CharBuffer)values);
            return this;
        }
        if (values instanceof FloatBuffer) {
            this.setFloats((FloatBuffer)values);
            return this;
        }
        if (values instanceof DoubleBuffer) {
            this.setDoubles((DoubleBuffer)values);
            return this;
        }
        throw new UnsupportedOperationException("Unhandled buffer type : " + values.getClass().getName());
    }

    @Deprecated
    public Pointer<T> copyBytesAtOffsetTo(long byteOffset, Pointer<?> destination, long byteOffsetInDestination, long byteCount) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, byteCount);
        }
        JNI.memcpy(super.getCheckedPeer(byteOffsetInDestination, byteCount), checkedPeer, byteCount);
        return this;
    }

    @Deprecated
    public Pointer<T> copyBytesTo(Pointer<?> destination, long byteCount) {
        return this.copyBytesAtOffsetTo(0L, destination, 0L, byteCount);
    }

    @Deprecated
    public Pointer<T> moveBytesAtOffsetTo(long byteOffset, Pointer<?> destination, long byteOffsetInDestination, long byteCount) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, byteCount);
        }
        JNI.memmove(super.getCheckedPeer(byteOffsetInDestination, byteCount), checkedPeer, byteCount);
        return this;
    }

    public Pointer<T> moveBytesTo(Pointer<?> destination, long byteCount) {
        return this.moveBytesAtOffsetTo(0L, destination, 0L, byteCount);
    }

    public Pointer<T> moveBytesTo(Pointer<?> destination) {
        return this.moveBytesTo(destination, this.getValidBytes("Cannot move an unbounded memory location. Please use validBytes(long)."));
    }

    final long getValidBytes(String error) {
        long rem = this.getValidBytes();
        if (rem < 0L) {
            throw new IndexOutOfBoundsException(error);
        }
        return rem;
    }

    final long getValidElements(String error) {
        long rem = this.getValidElements();
        if (rem < 0L) {
            throw new IndexOutOfBoundsException(error);
        }
        return rem;
    }

    final PointerIO<T> getIO(String error) {
        PointerIO<T> io2 = this.getIO();
        if (io2 == null) {
            Pointer.throwBecauseUntyped(error);
        }
        return io2;
    }

    public Pointer<T> copyTo(Pointer<?> destination) {
        return this.copyTo(destination, this.getValidElements());
    }

    public Pointer<T> copyTo(Pointer<?> destination, long elementCount) {
        PointerIO<T> io2 = this.getIO("Cannot copy untyped pointer without byte count information. Please use copyBytesAtOffsetTo(offset, destination, destinationOffset, byteCount) instead");
        return this.copyBytesAtOffsetTo(0L, destination, 0L, elementCount * io2.getTargetSize());
    }

    public Pointer<T> find(Pointer<?> needle) {
        if (needle == null) {
            return null;
        }
        long firstOccurrence = JNI.memmem(this.getPeer(), this.getValidBytes("Cannot search an unbounded memory area. Please set bounds with validBytes(long)."), needle.getPeer(), needle.getValidBytes("Cannot search for an unbounded content. Please set bounds with validBytes(long)."));
        return Pointer.pointerToAddress(firstOccurrence, this.io);
    }

    public Pointer<T> findLast(Pointer<?> needle) {
        if (needle == null) {
            return null;
        }
        long lastOccurrence = JNI.memmem_last(this.getPeer(), this.getValidBytes("Cannot search an unbounded memory area. Please set bounds with validBytes(long)."), needle.getPeer(), needle.getValidBytes("Cannot search for an unbounded content. Please set bounds with validBytes(long)."));
        return Pointer.pointerToAddress(lastOccurrence, this.io);
    }

    public abstract Pointer<T> setInt(int var1);

    public abstract Pointer<T> setIntAtOffset(long var1, int var3);

    public Pointer<T> setIntAtIndex(long valueIndex, int value) {
        return this.setIntAtOffset(valueIndex * 4L, value);
    }

    public Pointer<T> setInts(int[] values) {
        return this.setIntsAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setIntsAtOffset(long byteOffset, int[] values) {
        return this.setIntsAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setIntsAtOffset(long byteOffset, int[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 4 * length);
        }
        if (!this.isOrdered()) {
            JNI.set_int_array_disordered(checkedPeer, values, valuesOffset, length);
            return this;
        }
        JNI.set_int_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract int getInt();

    public abstract int getIntAtOffset(long var1);

    public int getIntAtIndex(long valueIndex) {
        return this.getIntAtOffset(valueIndex * 4L);
    }

    public int[] getInts(int length) {
        return this.getIntsAtOffset(0L, length);
    }

    public int[] getInts() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getInts(int length) instead.");
        return this.getInts((int)(validBytes / 4L));
    }

    public int[] getIntsAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 4 * length);
        }
        if (!this.isOrdered()) {
            return JNI.get_int_array_disordered(checkedPeer, length);
        }
        return JNI.get_int_array(checkedPeer, length);
    }

    public abstract Pointer<T> setLong(long var1);

    public abstract Pointer<T> setLongAtOffset(long var1, long var3);

    public Pointer<T> setLongAtIndex(long valueIndex, long value) {
        return this.setLongAtOffset(valueIndex * 8L, value);
    }

    public Pointer<T> setLongs(long[] values) {
        return this.setLongsAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setLongsAtOffset(long byteOffset, long[] values) {
        return this.setLongsAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setLongsAtOffset(long byteOffset, long[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 8 * length);
        }
        if (!this.isOrdered()) {
            JNI.set_long_array_disordered(checkedPeer, values, valuesOffset, length);
            return this;
        }
        JNI.set_long_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract long getLong();

    public abstract long getLongAtOffset(long var1);

    public long getLongAtIndex(long valueIndex) {
        return this.getLongAtOffset(valueIndex * 8L);
    }

    public long[] getLongs(int length) {
        return this.getLongsAtOffset(0L, length);
    }

    public long[] getLongs() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getLongs(int length) instead.");
        return this.getLongs((int)(validBytes / 8L));
    }

    public long[] getLongsAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 8 * length);
        }
        if (!this.isOrdered()) {
            return JNI.get_long_array_disordered(checkedPeer, length);
        }
        return JNI.get_long_array(checkedPeer, length);
    }

    public abstract Pointer<T> setShort(short var1);

    public abstract Pointer<T> setShortAtOffset(long var1, short var3);

    public Pointer<T> setShortAtIndex(long valueIndex, short value) {
        return this.setShortAtOffset(valueIndex * 2L, value);
    }

    public Pointer<T> setShorts(short[] values) {
        return this.setShortsAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setShortsAtOffset(long byteOffset, short[] values) {
        return this.setShortsAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setShortsAtOffset(long byteOffset, short[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 2 * length);
        }
        if (!this.isOrdered()) {
            JNI.set_short_array_disordered(checkedPeer, values, valuesOffset, length);
            return this;
        }
        JNI.set_short_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract short getShort();

    public abstract short getShortAtOffset(long var1);

    public short getShortAtIndex(long valueIndex) {
        return this.getShortAtOffset(valueIndex * 2L);
    }

    public short[] getShorts(int length) {
        return this.getShortsAtOffset(0L, length);
    }

    public short[] getShorts() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getShorts(int length) instead.");
        return this.getShorts((int)(validBytes / 2L));
    }

    public short[] getShortsAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 2 * length);
        }
        if (!this.isOrdered()) {
            return JNI.get_short_array_disordered(checkedPeer, length);
        }
        return JNI.get_short_array(checkedPeer, length);
    }

    public abstract Pointer<T> setByte(byte var1);

    public abstract Pointer<T> setByteAtOffset(long var1, byte var3);

    public Pointer<T> setByteAtIndex(long valueIndex, byte value) {
        return this.setByteAtOffset(valueIndex * 1L, value);
    }

    public Pointer<T> setBytes(byte[] values) {
        return this.setBytesAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setBytesAtOffset(long byteOffset, byte[] values) {
        return this.setBytesAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setBytesAtOffset(long byteOffset, byte[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 1 * length);
        }
        JNI.set_byte_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract byte getByte();

    public abstract byte getByteAtOffset(long var1);

    public byte getByteAtIndex(long valueIndex) {
        return this.getByteAtOffset(valueIndex * 1L);
    }

    public byte[] getBytes(int length) {
        return this.getBytesAtOffset(0L, length);
    }

    public byte[] getBytes() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getBytes(int length) instead.");
        return this.getBytes((int)(validBytes / 1L));
    }

    public byte[] getBytesAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 1 * length);
        }
        return JNI.get_byte_array(checkedPeer, length);
    }

    public abstract Pointer<T> setChar(char var1);

    public abstract Pointer<T> setCharAtOffset(long var1, char var3);

    public Pointer<T> setCharAtIndex(long valueIndex, char value) {
        return this.setCharAtOffset(valueIndex * (long)Platform.WCHAR_T_SIZE, value);
    }

    public Pointer<T> setChars(char[] values) {
        return this.setCharsAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setCharsAtOffset(long byteOffset, char[] values) {
        return this.setCharsAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setCharsAtOffset(long byteOffset, char[] values, int valuesOffset, int length) {
        if (Platform.WCHAR_T_SIZE == 4) {
            return this.setIntsAtOffset(byteOffset, Pointer.wcharsToInts(values, valuesOffset, length));
        }
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE * length);
        }
        if (!this.isOrdered()) {
            JNI.set_char_array_disordered(checkedPeer, values, valuesOffset, length);
            return this;
        }
        JNI.set_char_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract char getChar();

    public abstract char getCharAtOffset(long var1);

    public char getCharAtIndex(long valueIndex) {
        return this.getCharAtOffset(valueIndex * (long)Platform.WCHAR_T_SIZE);
    }

    public char[] getChars(int length) {
        return this.getCharsAtOffset(0L, length);
    }

    public char[] getChars() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getChars(int length) instead.");
        return this.getChars((int)(validBytes / (long)Platform.WCHAR_T_SIZE));
    }

    public char[] getCharsAtOffset(long byteOffset, int length) {
        if (Platform.WCHAR_T_SIZE == 4) {
            return Pointer.intsToWChars(this.getIntsAtOffset(byteOffset, length));
        }
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE * length);
        }
        if (!this.isOrdered()) {
            return JNI.get_char_array_disordered(checkedPeer, length);
        }
        return JNI.get_char_array(checkedPeer, length);
    }

    public abstract Pointer<T> setFloat(float var1);

    public abstract Pointer<T> setFloatAtOffset(long var1, float var3);

    public Pointer<T> setFloatAtIndex(long valueIndex, float value) {
        return this.setFloatAtOffset(valueIndex * 4L, value);
    }

    public Pointer<T> setFloats(float[] values) {
        return this.setFloatsAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setFloatsAtOffset(long byteOffset, float[] values) {
        return this.setFloatsAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setFloatsAtOffset(long byteOffset, float[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 4 * length);
        }
        if (!this.isOrdered()) {
            JNI.set_float_array_disordered(checkedPeer, values, valuesOffset, length);
            return this;
        }
        JNI.set_float_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract float getFloat();

    public abstract float getFloatAtOffset(long var1);

    public float getFloatAtIndex(long valueIndex) {
        return this.getFloatAtOffset(valueIndex * 4L);
    }

    public float[] getFloats(int length) {
        return this.getFloatsAtOffset(0L, length);
    }

    public float[] getFloats() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getFloats(int length) instead.");
        return this.getFloats((int)(validBytes / 4L));
    }

    public float[] getFloatsAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 4 * length);
        }
        if (!this.isOrdered()) {
            return JNI.get_float_array_disordered(checkedPeer, length);
        }
        return JNI.get_float_array(checkedPeer, length);
    }

    public abstract Pointer<T> setDouble(double var1);

    public abstract Pointer<T> setDoubleAtOffset(long var1, double var3);

    public Pointer<T> setDoubleAtIndex(long valueIndex, double value) {
        return this.setDoubleAtOffset(valueIndex * 8L, value);
    }

    public Pointer<T> setDoubles(double[] values) {
        return this.setDoublesAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setDoublesAtOffset(long byteOffset, double[] values) {
        return this.setDoublesAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setDoublesAtOffset(long byteOffset, double[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 8 * length);
        }
        if (!this.isOrdered()) {
            JNI.set_double_array_disordered(checkedPeer, values, valuesOffset, length);
            return this;
        }
        JNI.set_double_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract double getDouble();

    public abstract double getDoubleAtOffset(long var1);

    public double getDoubleAtIndex(long valueIndex) {
        return this.getDoubleAtOffset(valueIndex * 8L);
    }

    public double[] getDoubles(int length) {
        return this.getDoublesAtOffset(0L, length);
    }

    public double[] getDoubles() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getDoubles(int length) instead.");
        return this.getDoubles((int)(validBytes / 8L));
    }

    public double[] getDoublesAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 8 * length);
        }
        if (!this.isOrdered()) {
            return JNI.get_double_array_disordered(checkedPeer, length);
        }
        return JNI.get_double_array(checkedPeer, length);
    }

    public abstract Pointer<T> setBoolean(boolean var1);

    public abstract Pointer<T> setBooleanAtOffset(long var1, boolean var3);

    public Pointer<T> setBooleanAtIndex(long valueIndex, boolean value) {
        return this.setBooleanAtOffset(valueIndex * 1L, value);
    }

    public Pointer<T> setBooleans(boolean[] values) {
        return this.setBooleansAtOffset(0L, values, 0, values.length);
    }

    public Pointer<T> setBooleansAtOffset(long byteOffset, boolean[] values) {
        return this.setBooleansAtOffset(byteOffset, values, 0, values.length);
    }

    public Pointer<T> setBooleansAtOffset(long byteOffset, boolean[] values, int valuesOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 1 * length);
        }
        JNI.set_boolean_array(checkedPeer, values, valuesOffset, length);
        return this;
    }

    public abstract boolean getBoolean();

    public abstract boolean getBooleanAtOffset(long var1);

    public boolean getBooleanAtIndex(long valueIndex) {
        return this.getBooleanAtOffset(valueIndex * 1L);
    }

    public boolean[] getBooleans(int length) {
        return this.getBooleansAtOffset(0L, length);
    }

    public boolean[] getBooleans() {
        long validBytes = this.getValidBytes("Cannot create array if remaining length is not known. Please use getBooleans(int length) instead.");
        return this.getBooleans((int)(validBytes / 1L));
    }

    public boolean[] getBooleansAtOffset(long byteOffset, int length) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 1 * length);
        }
        return JNI.get_boolean_array(checkedPeer, length);
    }

    public void getInts(int[] dest) {
        this.getIntBuffer().get(dest);
    }

    public void getInts(IntBuffer dest) {
        dest.duplicate().put(this.getIntBuffer());
    }

    public void getIntsAtOffset(long byteOffset, int[] dest, int destOffset, int length) {
        this.getIntBufferAtOffset(byteOffset, length).get(dest, destOffset, length);
    }

    public Pointer<T> setInts(IntBuffer values) {
        return this.setIntsAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setIntsAtOffset(long byteOffset, IntBuffer values) {
        return this.setIntsAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setIntsAtOffset(long byteOffset, IntBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (values.isDirect()) {
            long len = length * 4L;
            long off = valuesOffset * 4L;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= 4L) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else if (values.isReadOnly()) {
            this.getIntBufferAtOffset(byteOffset, length).put(values.duplicate());
        } else {
            this.setIntsAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public IntBuffer getIntBuffer(long length) {
        return this.getIntBufferAtOffset(0L, length);
    }

    public IntBuffer getIntBuffer() {
        long validBytes = this.getValidBytes("Cannot create buffer if remaining length is not known. Please use getIntBuffer(long length) instead.");
        return this.getIntBufferAtOffset(0L, validBytes / 4L);
    }

    public IntBuffer getIntBufferAtOffset(long byteOffset, long length) {
        long blen = 4L * length;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, blen);
        }
        ByteBuffer buffer = JNI.newDirectByteBuffer(checkedPeer, blen);
        buffer.order(this.order());
        return buffer.asIntBuffer();
    }

    public void getLongs(long[] dest) {
        this.getLongBuffer().get(dest);
    }

    public void getLongs(LongBuffer dest) {
        dest.duplicate().put(this.getLongBuffer());
    }

    public void getLongsAtOffset(long byteOffset, long[] dest, int destOffset, int length) {
        this.getLongBufferAtOffset(byteOffset, length).get(dest, destOffset, length);
    }

    public Pointer<T> setLongs(LongBuffer values) {
        return this.setLongsAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setLongsAtOffset(long byteOffset, LongBuffer values) {
        return this.setLongsAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setLongsAtOffset(long byteOffset, LongBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (values.isDirect()) {
            long len = length * 8L;
            long off = valuesOffset * 8L;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= 8L) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else if (values.isReadOnly()) {
            this.getLongBufferAtOffset(byteOffset, length).put(values.duplicate());
        } else {
            this.setLongsAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public LongBuffer getLongBuffer(long length) {
        return this.getLongBufferAtOffset(0L, length);
    }

    public LongBuffer getLongBuffer() {
        long validBytes = this.getValidBytes("Cannot create buffer if remaining length is not known. Please use getLongBuffer(long length) instead.");
        return this.getLongBufferAtOffset(0L, validBytes / 8L);
    }

    public LongBuffer getLongBufferAtOffset(long byteOffset, long length) {
        long blen = 8L * length;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, blen);
        }
        ByteBuffer buffer = JNI.newDirectByteBuffer(checkedPeer, blen);
        buffer.order(this.order());
        return buffer.asLongBuffer();
    }

    public void getShorts(short[] dest) {
        this.getShortBuffer().get(dest);
    }

    public void getShorts(ShortBuffer dest) {
        dest.duplicate().put(this.getShortBuffer());
    }

    public void getShortsAtOffset(long byteOffset, short[] dest, int destOffset, int length) {
        this.getShortBufferAtOffset(byteOffset, length).get(dest, destOffset, length);
    }

    public Pointer<T> setShorts(ShortBuffer values) {
        return this.setShortsAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setShortsAtOffset(long byteOffset, ShortBuffer values) {
        return this.setShortsAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setShortsAtOffset(long byteOffset, ShortBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (values.isDirect()) {
            long len = length * 2L;
            long off = valuesOffset * 2L;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= 2L) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else if (values.isReadOnly()) {
            this.getShortBufferAtOffset(byteOffset, length).put(values.duplicate());
        } else {
            this.setShortsAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public ShortBuffer getShortBuffer(long length) {
        return this.getShortBufferAtOffset(0L, length);
    }

    public ShortBuffer getShortBuffer() {
        long validBytes = this.getValidBytes("Cannot create buffer if remaining length is not known. Please use getShortBuffer(long length) instead.");
        return this.getShortBufferAtOffset(0L, validBytes / 2L);
    }

    public ShortBuffer getShortBufferAtOffset(long byteOffset, long length) {
        long blen = 2L * length;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, blen);
        }
        ByteBuffer buffer = JNI.newDirectByteBuffer(checkedPeer, blen);
        buffer.order(this.order());
        return buffer.asShortBuffer();
    }

    public void getBytes(byte[] dest) {
        this.getByteBuffer().get(dest);
    }

    public void getBytes(ByteBuffer dest) {
        dest.duplicate().put(this.getByteBuffer());
    }

    public void getBytesAtOffset(long byteOffset, byte[] dest, int destOffset, int length) {
        this.getByteBufferAtOffset(byteOffset, length).get(dest, destOffset, length);
    }

    public Pointer<T> setBytes(ByteBuffer values) {
        return this.setBytesAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setBytesAtOffset(long byteOffset, ByteBuffer values) {
        return this.setBytesAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setBytesAtOffset(long byteOffset, ByteBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (values.isDirect()) {
            long len = length * 1L;
            long off = valuesOffset * 1L;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= 1L) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else if (values.isReadOnly()) {
            this.getByteBufferAtOffset(byteOffset, length).put(values.duplicate());
        } else {
            this.setBytesAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public ByteBuffer getByteBuffer(long length) {
        return this.getByteBufferAtOffset(0L, length);
    }

    public ByteBuffer getByteBuffer() {
        long validBytes = this.getValidBytes("Cannot create buffer if remaining length is not known. Please use getByteBuffer(long length) instead.");
        return this.getByteBufferAtOffset(0L, validBytes / 1L);
    }

    public ByteBuffer getByteBufferAtOffset(long byteOffset, long length) {
        long blen = 1L * length;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, blen);
        }
        ByteBuffer buffer = JNI.newDirectByteBuffer(checkedPeer, blen);
        buffer.order(this.order());
        return buffer;
    }

    public Pointer<T> setChars(CharBuffer values) {
        return this.setCharsAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setCharsAtOffset(long byteOffset, CharBuffer values) {
        return this.setCharsAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setCharsAtOffset(long byteOffset, CharBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (Platform.WCHAR_T_SIZE == 4) {
            int i2 = 0;
            while ((long)i2 < length) {
                this.setCharAtOffset(byteOffset + (long)i2, values.get((int)(valuesOffset + (long)i2)));
                ++i2;
            }
            return this;
        }
        if (values.isDirect()) {
            long len = length * (long)Platform.WCHAR_T_SIZE;
            long off = valuesOffset * (long)Platform.WCHAR_T_SIZE;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= (long)Platform.WCHAR_T_SIZE) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, (long)Platform.WCHAR_T_SIZE * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else {
            this.setCharsAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public void getFloats(float[] dest) {
        this.getFloatBuffer().get(dest);
    }

    public void getFloats(FloatBuffer dest) {
        dest.duplicate().put(this.getFloatBuffer());
    }

    public void getFloatsAtOffset(long byteOffset, float[] dest, int destOffset, int length) {
        this.getFloatBufferAtOffset(byteOffset, length).get(dest, destOffset, length);
    }

    public Pointer<T> setFloats(FloatBuffer values) {
        return this.setFloatsAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setFloatsAtOffset(long byteOffset, FloatBuffer values) {
        return this.setFloatsAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setFloatsAtOffset(long byteOffset, FloatBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (values.isDirect()) {
            long len = length * 4L;
            long off = valuesOffset * 4L;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= 4L) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else if (values.isReadOnly()) {
            this.getFloatBufferAtOffset(byteOffset, length).put(values.duplicate());
        } else {
            this.setFloatsAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public FloatBuffer getFloatBuffer(long length) {
        return this.getFloatBufferAtOffset(0L, length);
    }

    public FloatBuffer getFloatBuffer() {
        long validBytes = this.getValidBytes("Cannot create buffer if remaining length is not known. Please use getFloatBuffer(long length) instead.");
        return this.getFloatBufferAtOffset(0L, validBytes / 4L);
    }

    public FloatBuffer getFloatBufferAtOffset(long byteOffset, long length) {
        long blen = 4L * length;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, blen);
        }
        ByteBuffer buffer = JNI.newDirectByteBuffer(checkedPeer, blen);
        buffer.order(this.order());
        return buffer.asFloatBuffer();
    }

    public void getDoubles(double[] dest) {
        this.getDoubleBuffer().get(dest);
    }

    public void getDoubles(DoubleBuffer dest) {
        dest.duplicate().put(this.getDoubleBuffer());
    }

    public void getDoublesAtOffset(long byteOffset, double[] dest, int destOffset, int length) {
        this.getDoubleBufferAtOffset(byteOffset, length).get(dest, destOffset, length);
    }

    public Pointer<T> setDoubles(DoubleBuffer values) {
        return this.setDoublesAtOffset(0L, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setDoublesAtOffset(long byteOffset, DoubleBuffer values) {
        return this.setDoublesAtOffset(byteOffset, values, 0L, (long)values.capacity());
    }

    public Pointer<T> setDoublesAtOffset(long byteOffset, DoubleBuffer values, long valuesOffset, long length) {
        if (values == null) {
            throw new IllegalArgumentException("Null values");
        }
        if (values.isDirect()) {
            long len = length * 8L;
            long off = valuesOffset * 8L;
            long cap = JNI.getDirectBufferCapacity(values);
            if ((cap *= 8L) < off + len) {
                throw new IndexOutOfBoundsException("The provided buffer has a capacity (" + cap + " bytes) smaller than the requested write operation (" + len + " bytes starting at byte offset " + off + ")");
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L * length);
            }
            JNI.memcpy(checkedPeer, JNI.getDirectBufferAddress(values) + off, len);
        } else if (values.isReadOnly()) {
            this.getDoubleBufferAtOffset(byteOffset, length).put(values.duplicate());
        } else {
            this.setDoublesAtOffset(byteOffset, values.array(), (int)((long)values.arrayOffset() + valuesOffset), (int)length);
        }
        return this;
    }

    public DoubleBuffer getDoubleBuffer(long length) {
        return this.getDoubleBufferAtOffset(0L, length);
    }

    public DoubleBuffer getDoubleBuffer() {
        long validBytes = this.getValidBytes("Cannot create buffer if remaining length is not known. Please use getDoubleBuffer(long length) instead.");
        return this.getDoubleBufferAtOffset(0L, validBytes / 8L);
    }

    public DoubleBuffer getDoubleBufferAtOffset(long byteOffset, long length) {
        long blen = 8L * length;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, blen);
        }
        ByteBuffer buffer = JNI.newDirectByteBuffer(checkedPeer, blen);
        buffer.order(this.order());
        return buffer.asDoubleBuffer();
    }

    private static void notAString(StringType type, String reason) {
        throw new RuntimeException("There is no " + (Object)((Object)type) + " String here ! (" + reason + ")");
    }

    protected void checkIntRefCount(StringType type, long byteOffset) {
        int refCount = this.getIntAtOffset(byteOffset);
        if (refCount <= 0) {
            Pointer.notAString(type, "invalid refcount: " + refCount);
        }
    }

    public String getString(StringType type) {
        return this.getStringAtOffset(0L, type, null);
    }

    public String getString(StringType type, Charset charset) {
        return this.getStringAtOffset(0L, type, charset);
    }

    String getSTLStringAtOffset(long byteOffset, StringType type, Charset charset) {
        char endChar;
        Pointer<?> p2;
        long pOff;
        boolean wide = type == StringType.WideSTL;
        int fixedBuffLength = 16;
        int fixedBuffSize = wide ? fixedBuffLength * Platform.WCHAR_T_SIZE : fixedBuffLength;
        long length = this.getSizeTAtOffset(byteOffset + (long)fixedBuffSize + (long)SIZE);
        if (length < (long)(fixedBuffLength - 1)) {
            pOff = byteOffset;
            p2 = this;
        } else {
            pOff = 0L;
            p2 = this.getPointerAtOffset(byteOffset + (long)fixedBuffSize + (long)SIZE);
        }
        char c2 = endChar = wide ? p2.getCharAtOffset(pOff + length * (long)Platform.WCHAR_T_SIZE) : p2.getByteAtOffset(pOff + length);
        if (endChar != '\u0000') {
            Pointer.notAString(type, "STL string format is not recognized : did not find a NULL char at the expected end of string of expected length " + length);
        }
        return p2.getStringAtOffset(pOff, wide ? StringType.WideC : StringType.C, charset);
    }

    static <U> Pointer<U> setSTLString(Pointer<U> pointer, long byteOffset, String s2, StringType type, Charset charset) {
        char endChar;
        Pointer<Object> p2;
        long pOff;
        boolean wide = type == StringType.WideSTL;
        int fixedBuffLength = 16;
        int fixedBuffSize = wide ? fixedBuffLength * Platform.WCHAR_T_SIZE : fixedBuffLength;
        long lengthOffset = byteOffset + (long)fixedBuffSize + (long)SIZE;
        long capacityOffset = lengthOffset + (long)SIZE;
        long length = s2.length();
        if (pointer == null) {
            throw new UnsupportedOperationException("Cannot create STL strings (yet)");
        }
        long currentLength = pointer.getSizeTAtOffset(lengthOffset);
        long currentCapacity = pointer.getSizeTAtOffset(capacityOffset);
        if (currentLength < 0L || currentCapacity < 0L || currentLength > currentCapacity) {
            Pointer.notAString(type, "STL string format not recognized : currentLength = " + currentLength + ", currentCapacity = " + currentCapacity);
        }
        if (length > currentCapacity) {
            throw new RuntimeException("The target STL string is not large enough to write a string of length " + length + " (current capacity = " + currentCapacity + ")");
        }
        pointer.setSizeTAtOffset(lengthOffset, length);
        if (length < (long)(fixedBuffLength - 1)) {
            pOff = byteOffset;
            p2 = pointer;
        } else {
            pOff = 0L;
            p2 = pointer.getPointerAtOffset(byteOffset + (long)fixedBuffSize + (long)SizeT.SIZE);
        }
        char c2 = endChar = wide ? p2.getCharAtOffset(pOff + currentLength * (long)Platform.WCHAR_T_SIZE) : p2.getByteAtOffset(pOff + currentLength);
        if (endChar != '\u0000') {
            Pointer.notAString(type, "STL string format is not recognized : did not find a NULL char at the expected end of string of expected length " + currentLength);
        }
        p2.setStringAtOffset(pOff, s2, wide ? StringType.WideC : StringType.C, charset);
        return pointer;
    }

    public String getStringAtOffset(long byteOffset, StringType type, Charset charset) {
        try {
            switch (type) {
                case PascalShort: {
                    long len = this.getByteAtOffset(byteOffset) & 0xFF;
                    return new String(this.getBytesAtOffset(byteOffset + 1L, SizeT.safeIntCast(len)), Pointer.charset(charset));
                }
                case PascalWide: {
                    this.checkIntRefCount(type, byteOffset - 8L);
                }
                case BSTR: {
                    long len = this.getIntAtOffset(byteOffset - 4L);
                    if (len < 0L || (len & 1L) == 1L) {
                        Pointer.notAString(type, "invalid byte length: " + len);
                    }
                    if (this.getCharAtOffset(byteOffset + len) != '\u0000') {
                        Pointer.notAString(type, "no null short after the " + len + " declared bytes");
                    }
                    return new String(this.getCharsAtOffset(byteOffset, SizeT.safeIntCast(len / (long)Platform.WCHAR_T_SIZE)));
                }
                case PascalAnsi: {
                    this.checkIntRefCount(type, byteOffset - 8L);
                    long len = this.getIntAtOffset(byteOffset - 4L);
                    if (len < 0L) {
                        Pointer.notAString(type, "invalid byte length: " + len);
                    }
                    if (this.getByteAtOffset(byteOffset + len) != 0) {
                        Pointer.notAString(type, "no null short after the " + len + " declared bytes");
                    }
                    return new String(this.getBytesAtOffset(byteOffset, SizeT.safeIntCast(len)), Pointer.charset(charset));
                }
                case C: {
                    long len = this.strlen(byteOffset);
                    return new String(this.getBytesAtOffset(byteOffset, SizeT.safeIntCast(len)), Pointer.charset(charset));
                }
                case WideC: {
                    long len = this.wcslen(byteOffset);
                    return new String(this.getCharsAtOffset(byteOffset, SizeT.safeIntCast(len)));
                }
                case STL: 
                case WideSTL: {
                    return this.getSTLStringAtOffset(byteOffset, type, charset);
                }
            }
            throw new RuntimeException("Unhandled string type : " + (Object)((Object)type));
        }
        catch (UnsupportedEncodingException ex2) {
            Pointer.throwUnexpected(ex2);
            return null;
        }
    }

    public Pointer<T> setString(String s2, StringType type) {
        return Pointer.setString(this, 0L, s2, type, null);
    }

    public Pointer<T> setStringAtOffset(long byteOffset, String s2, StringType type, Charset charset) {
        return Pointer.setString(this, byteOffset, s2, type, charset);
    }

    private static String charset(Charset charset) {
        return (charset == null ? Charset.defaultCharset() : charset).name();
    }

    static <U> Pointer<U> setString(Pointer<U> pointer, long byteOffset, String s2, StringType type, Charset charset) {
        try {
            if (s2 == null) {
                return null;
            }
            switch (type) {
                case PascalShort: {
                    byte[] bytes = s2.getBytes(Pointer.charset(charset));
                    int bytesCount = bytes.length;
                    if (pointer == null) {
                        pointer = Pointer.allocateBytes(bytesCount + 1);
                    }
                    if (bytesCount > 255) {
                        throw new IllegalArgumentException("Pascal strings cannot be more than 255 chars long (tried to write string of byte length " + bytesCount + ")");
                    }
                    pointer.setByteAtOffset(byteOffset, (byte)bytesCount);
                    pointer.setBytesAtOffset(byteOffset + 1L, bytes, 0, bytesCount);
                    break;
                }
                case C: {
                    byte[] bytes = s2.getBytes(Pointer.charset(charset));
                    int bytesCount = bytes.length;
                    if (pointer == null) {
                        pointer = Pointer.allocateBytes(bytesCount + 1);
                    }
                    pointer.setBytesAtOffset(byteOffset, bytes, 0, bytesCount);
                    pointer.setByteAtOffset(byteOffset + (long)bytesCount, (byte)0);
                    break;
                }
                case WideC: {
                    char[] chars = s2.toCharArray();
                    int bytesCount = chars.length * Platform.WCHAR_T_SIZE;
                    if (pointer == null) {
                        pointer = Pointer.allocateChars(bytesCount + 2);
                    }
                    pointer.setCharsAtOffset(byteOffset, chars);
                    pointer.setCharAtOffset(byteOffset + (long)bytesCount, '\u0000');
                    break;
                }
                case PascalWide: {
                    int headerShift;
                    int headerBytes = 8;
                    char[] chars = s2.toCharArray();
                    int bytesCount = chars.length * Platform.WCHAR_T_SIZE;
                    if (pointer == null) {
                        pointer = Pointer.allocateChars(headerBytes + bytesCount + 2);
                        headerShift = headerBytes;
                        byteOffset = headerShift;
                    } else {
                        headerShift = 0;
                    }
                    pointer.setIntAtOffset(byteOffset - 8L, 1);
                    pointer.setIntAtOffset(byteOffset - 4L, bytesCount);
                    pointer.setCharsAtOffset(byteOffset, chars);
                    pointer.setCharAtOffset(byteOffset + (long)bytesCount, '\u0000');
                    return pointer.offset(headerShift);
                }
                case PascalAnsi: {
                    int headerShift;
                    int headerBytes = 8;
                    byte[] bytes = s2.getBytes(Pointer.charset(charset));
                    int bytesCount = bytes.length;
                    if (pointer == null) {
                        pointer = Pointer.allocateBytes(headerBytes + bytesCount + 1);
                        headerShift = headerBytes;
                        byteOffset = headerShift;
                    } else {
                        headerShift = 0;
                    }
                    pointer.setIntAtOffset(byteOffset - 8L, 1);
                    pointer.setIntAtOffset(byteOffset - 4L, bytesCount);
                    pointer.setBytesAtOffset(byteOffset, bytes);
                    pointer.setByteAtOffset(byteOffset + (long)bytesCount, (byte)0);
                    return pointer.offset(headerShift);
                }
                case BSTR: {
                    int headerShift;
                    int headerBytes = 4;
                    char[] chars = s2.toCharArray();
                    int bytesCount = chars.length * Platform.WCHAR_T_SIZE;
                    if (pointer == null) {
                        pointer = Pointer.allocateChars(headerBytes + bytesCount + 2);
                        headerShift = headerBytes;
                        byteOffset = headerShift;
                    } else {
                        headerShift = 0;
                    }
                    pointer.setIntAtOffset(byteOffset - 4L, bytesCount);
                    pointer.setCharsAtOffset(byteOffset, chars);
                    pointer.setCharAtOffset(byteOffset + (long)bytesCount, '\u0000');
                    return pointer.offset(headerShift);
                }
                case STL: 
                case WideSTL: {
                    return Pointer.setSTLString(pointer, byteOffset, s2, type, charset);
                }
                default: {
                    throw new RuntimeException("Unhandled string type : " + (Object)((Object)type));
                }
            }
            return pointer;
        }
        catch (UnsupportedEncodingException ex2) {
            Pointer.throwUnexpected(ex2);
            return null;
        }
    }

    public static Pointer<?> pointerToString(String string, StringType type, Charset charset) {
        return Pointer.setString(null, 0L, string, type, charset);
    }

    public static Pointer<Byte> pointerToCString(String string) {
        return Pointer.setString(null, 0L, string, StringType.C, null);
    }

    public static Pointer<Pointer<Byte>> pointerToCStrings(final String ... strings) {
        if (strings == null) {
            return null;
        }
        final int len = strings.length;
        final Pointer[] pointers = new Pointer[len];
        Pointer<Pointer<Byte>> mem = Pointer.allocateArray(PointerIO.getPointerInstance(Byte.class), len, new Releaser(){

            @Override
            public void release(Pointer<?> p2) {
                Pointer<?> mem = p2;
                for (int i2 = 0; i2 < len; ++i2) {
                    Pointer pp2 = (Pointer)mem.get(i2);
                    if (pp2 != null) {
                        strings[i2] = pp2.getCString();
                    }
                    if ((pp2 = pointers[i2]) == null) continue;
                    pp2.release();
                }
            }
        });
        for (int i2 = 0; i2 < len; ++i2) {
            pointers[i2] = Pointer.pointerToCString(strings[i2]);
            mem.set(i2, pointers[i2]);
        }
        return mem;
    }

    public static Pointer<Character> pointerToWideCString(String string) {
        return Pointer.setString(null, 0L, string, StringType.WideC, null);
    }

    public static Pointer<Pointer<Character>> pointerToWideCStrings(final String ... strings) {
        if (strings == null) {
            return null;
        }
        final int len = strings.length;
        final Pointer[] pointers = new Pointer[len];
        Pointer<Pointer<Character>> mem = Pointer.allocateArray(PointerIO.getPointerInstance(Character.class), len, new Releaser(){

            @Override
            public void release(Pointer<?> p2) {
                Pointer<?> mem = p2;
                for (int i2 = 0; i2 < len; ++i2) {
                    Pointer pp2 = (Pointer)mem.get(i2);
                    if (pp2 != null) {
                        strings[i2] = pp2.getWideCString();
                    }
                    if ((pp2 = pointers[i2]) == null) continue;
                    pp2.release();
                }
            }
        });
        for (int i2 = 0; i2 < len; ++i2) {
            pointers[i2] = Pointer.pointerToWideCString(strings[i2]);
            mem.set(i2, pointers[i2]);
        }
        return mem;
    }

    public String getCString() {
        return this.getCStringAtOffset(0L);
    }

    public String getCStringAtOffset(long byteOffset) {
        return this.getStringAtOffset(byteOffset, StringType.C, null);
    }

    public Pointer<T> setCString(String s2) {
        return this.setCStringAtOffset(0L, s2);
    }

    public Pointer<T> setCStringAtOffset(long byteOffset, String s2) {
        return this.setStringAtOffset(byteOffset, s2, StringType.C, null);
    }

    public String getWideCString() {
        return this.getWideCStringAtOffset(0L);
    }

    public String getWideCStringAtOffset(long byteOffset) {
        return this.getStringAtOffset(byteOffset, StringType.WideC, null);
    }

    public Pointer<T> setWideCString(String s2) {
        return this.setWideCStringAtOffset(0L, s2);
    }

    public Pointer<T> setWideCStringAtOffset(long byteOffset, String s2) {
        return this.setStringAtOffset(byteOffset, s2, StringType.WideC, null);
    }

    protected long strlen(long byteOffset) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, 1L);
        }
        return JNI.strlen(checkedPeer);
    }

    protected long wcslen(long byteOffset) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
        }
        return JNI.wcslen(checkedPeer);
    }

    public void clearValidBytes() {
        long bytes = this.getValidBytes();
        if (bytes < 0L) {
            throw new UnsupportedOperationException("Number of valid bytes is unknown. Please use clearBytes(long) or validBytes(long).");
        }
        this.clearBytes(bytes);
    }

    public void clearBytes(long length) {
        this.clearBytesAtOffset(0L, length, (byte)0);
    }

    public void clearBytesAtOffset(long byteOffset, long length, byte value) {
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, length);
        }
        JNI.memset(checkedPeer, value, length);
    }

    public Pointer<T> findByte(long byteOffset, byte value, long searchLength) {
        long found;
        long checkedPeer = this.peer + byteOffset;
        if (this.validStart != UNKNOWN_VALIDITY) {
            this.checkPeer(checkedPeer, searchLength);
        }
        return (found = JNI.memchr(checkedPeer, value, searchLength)) == 0L ? null : this.offset(found - checkedPeer);
    }

    public final T apply(long index) {
        return this.get(index);
    }

    public final void update(long index, T element) {
        this.set(index, element);
    }

    public T[] toArray() {
        this.getIO("Cannot create array");
        return this.toArray((int)this.getValidElements("Length of pointed memory is unknown, cannot create array out of this pointer"));
    }

    T[] toArray(int length) {
        Class c2 = Utils.getClass(this.getIO("Cannot create array").getTargetType());
        if (c2 == null) {
            throw new RuntimeException("Unable to get the target type's class (target type = " + this.io.getTargetType() + ")");
        }
        return this.toArray((Object[])Array.newInstance(c2, length));
    }

    public <U> U[] toArray(U[] array) {
        int n2 = (int)this.getValidElements();
        if (n2 < 0) {
            Pointer.throwBecauseUntyped("Cannot create array");
        }
        if (array.length != n2) {
            return this.toArray();
        }
        for (int i2 = 0; i2 < n2; ++i2) {
            array[i2] = this.get(i2);
        }
        return array;
    }

    public NativeList<T> asList() {
        return this.asList(ListType.FixedCapacity);
    }

    public NativeList<T> asList(ListType type) {
        return new DefaultNativeList(this, type);
    }

    public static <E> NativeList<E> allocateList(PointerIO<E> io2, long capacity) {
        DefaultNativeList<E> list = new DefaultNativeList<E>(Pointer.allocateArray(io2, capacity), ListType.Dynamic);
        list.clear();
        return list;
    }

    public static <E> NativeList<E> allocateList(Class<E> type, long capacity) {
        return Pointer.allocateList(type, capacity);
    }

    public static <E> NativeList<E> allocateList(Type type, long capacity) {
        return Pointer.allocateList(PointerIO.getInstance(type), capacity);
    }

    private static char[] intsToWChars(int[] in2) {
        int n2 = in2.length;
        char[] out = new char[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            out[i2] = (char)in2[i2];
        }
        return out;
    }

    private static int[] wcharsToInts(char[] in2, int valuesOffset, int length) {
        int[] out = new int[length];
        for (int i2 = 0; i2 < length; ++i2) {
            out[i2] = in2[valuesOffset + i2];
        }
        return out;
    }

    static {
        Platform.initLibrary();
        UNKNOWN_VALIDITY = -1L;
        NO_PARENT = 0L;
        defaultAlignment = Integer.parseInt(Platform.getenvOrProperty("BRIDJ_DEFAULT_ALIGNMENT", "bridj.defaultAlignment", "-1"));
        freeReleaser = new FreeReleaser();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum ListType {
        Unmodifiable,
        FixedCapacity,
        Dynamic;

    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum StringType {
        C(false, true),
        WideC(true, true),
        PascalShort(false, true),
        PascalWide(true, true),
        PascalAnsi(false, true),
        BSTR(true, true),
        STL(false, false),
        WideSTL(true, false);

        final boolean isWide;
        final boolean canCreate;

        private StringType(boolean isWide, boolean canCreate) {
            this.isWide = isWide;
            this.canCreate = canCreate;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class FreeReleaser
    implements Releaser {
        FreeReleaser() {
        }

        @Override
        public void release(Pointer<?> p2) {
            assert (p2.getSibling() == null);
            assert (p2.validStart == p2.getPeer());
            if (BridJ.debugPointers) {
                BridJ.info("Freeing pointer " + p2 + "\n(Creation trace = \n\t" + Utils.toString(p2.creationTrace).replaceAll("\n", "\n\t") + "\n)", new RuntimeException().fillInStackTrace());
            }
            if (!BridJ.debugNeverFree) {
                JNI.free(p2.getPeer());
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class DisorderedPointer<T>
    extends Pointer<T> {
        DisorderedPointer(PointerIO<T> io2, long peer, long validStart, long validEnd, Pointer<?> parent, long offsetInParent, Object sibling) {
            super(io2, peer, validStart, validEnd, parent, offsetInParent, sibling);
        }

        @Override
        public boolean isOrdered() {
            return false;
        }

        @Override
        public Pointer<T> setInt(int value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_int_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setIntAtOffset(long byteOffset, int value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_int_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public int getInt() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_int_disordered(checkedPeer);
        }

        @Override
        public int getIntAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_int_disordered(checkedPeer);
        }

        @Override
        public Pointer<T> setLong(long value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_long_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setLongAtOffset(long byteOffset, long value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_long_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public long getLong() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_long_disordered(checkedPeer);
        }

        @Override
        public long getLongAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_long_disordered(checkedPeer);
        }

        @Override
        public Pointer<T> setShort(short value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            JNI.set_short_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setShortAtOffset(long byteOffset, short value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            JNI.set_short_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public short getShort() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            return JNI.get_short_disordered(checkedPeer);
        }

        @Override
        public short getShortAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            return JNI.get_short_disordered(checkedPeer);
        }

        @Override
        public Pointer<T> setByte(byte value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_byte(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setByteAtOffset(long byteOffset, byte value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_byte(checkedPeer, value);
            return this;
        }

        @Override
        public byte getByte() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_byte(checkedPeer);
        }

        @Override
        public byte getByteAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_byte(checkedPeer);
        }

        @Override
        public Pointer<T> setChar(char value) {
            if (Platform.WCHAR_T_SIZE == 4) {
                return this.setInt(value);
            }
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            JNI.set_char_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setCharAtOffset(long byteOffset, char value) {
            if (Platform.WCHAR_T_SIZE == 4) {
                return this.setIntAtOffset(byteOffset, value);
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            JNI.set_char_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public char getChar() {
            if (Platform.WCHAR_T_SIZE == 4) {
                return (char)this.getInt();
            }
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            return JNI.get_char_disordered(checkedPeer);
        }

        @Override
        public char getCharAtOffset(long byteOffset) {
            if (Platform.WCHAR_T_SIZE == 4) {
                return (char)this.getIntAtOffset(byteOffset);
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            return JNI.get_char_disordered(checkedPeer);
        }

        @Override
        public Pointer<T> setFloat(float value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_float_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setFloatAtOffset(long byteOffset, float value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_float_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public float getFloat() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_float_disordered(checkedPeer);
        }

        @Override
        public float getFloatAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_float_disordered(checkedPeer);
        }

        @Override
        public Pointer<T> setDouble(double value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_double_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setDoubleAtOffset(long byteOffset, double value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_double_disordered(checkedPeer, value);
            return this;
        }

        @Override
        public double getDouble() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_double_disordered(checkedPeer);
        }

        @Override
        public double getDoubleAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_double_disordered(checkedPeer);
        }

        @Override
        public Pointer<T> setBoolean(boolean value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_boolean(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setBooleanAtOffset(long byteOffset, boolean value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_boolean(checkedPeer, value);
            return this;
        }

        @Override
        public boolean getBoolean() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_boolean(checkedPeer);
        }

        @Override
        public boolean getBooleanAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_boolean(checkedPeer);
        }

        @Override
        public Pointer<T> setSizeTsAtOffset(long byteOffset, long[] values, int valuesOffset, int length) {
            if (values == null) {
                throw new IllegalArgumentException("Null values");
            }
            if (SizeT.SIZE == 8) {
                this.setLongsAtOffset(byteOffset, values, valuesOffset, length);
            } else {
                int n2 = length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 4);
                }
                long peer = checkedPeer;
                int valuesIndex = valuesOffset;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = (int)values[valuesIndex];
                    JNI.set_int_disordered(peer, value);
                    peer += 4L;
                    ++valuesIndex;
                }
            }
            return this;
        }

        @Override
        public Pointer<T> setSizeTsAtOffset(long byteOffset, int[] values) {
            if (SizeT.SIZE == 4) {
                this.setIntsAtOffset(byteOffset, values);
            } else {
                int n2 = values.length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 8);
                }
                long peer = checkedPeer;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = values[i2];
                    JNI.set_long_disordered(peer, value);
                    peer += 8L;
                }
            }
            return this;
        }

        @Override
        public Pointer<T> setCLongsAtOffset(long byteOffset, long[] values, int valuesOffset, int length) {
            if (values == null) {
                throw new IllegalArgumentException("Null values");
            }
            if (CLong.SIZE == 8) {
                this.setLongsAtOffset(byteOffset, values, valuesOffset, length);
            } else {
                int n2 = length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 4);
                }
                long peer = checkedPeer;
                int valuesIndex = valuesOffset;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = (int)values[valuesIndex];
                    JNI.set_int_disordered(peer, value);
                    peer += 4L;
                    ++valuesIndex;
                }
            }
            return this;
        }

        @Override
        public Pointer<T> setCLongsAtOffset(long byteOffset, int[] values) {
            if (CLong.SIZE == 4) {
                this.setIntsAtOffset(byteOffset, values);
            } else {
                int n2 = values.length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 8);
                }
                long peer = checkedPeer;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = values[i2];
                    JNI.set_long_disordered(peer, value);
                    peer += 8L;
                }
            }
            return this;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class OrderedPointer<T>
    extends Pointer<T> {
        OrderedPointer(PointerIO<T> io2, long peer, long validStart, long validEnd, Pointer<?> parent, long offsetInParent, Object sibling) {
            super(io2, peer, validStart, validEnd, parent, offsetInParent, sibling);
        }

        @Override
        public boolean isOrdered() {
            return true;
        }

        @Override
        public Pointer<T> setInt(int value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_int(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setIntAtOffset(long byteOffset, int value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_int(checkedPeer, value);
            return this;
        }

        @Override
        public int getInt() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_int(checkedPeer);
        }

        @Override
        public int getIntAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_int(checkedPeer);
        }

        @Override
        public Pointer<T> setLong(long value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_long(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setLongAtOffset(long byteOffset, long value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_long(checkedPeer, value);
            return this;
        }

        @Override
        public long getLong() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_long(checkedPeer);
        }

        @Override
        public long getLongAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_long(checkedPeer);
        }

        @Override
        public Pointer<T> setShort(short value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            JNI.set_short(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setShortAtOffset(long byteOffset, short value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            JNI.set_short(checkedPeer, value);
            return this;
        }

        @Override
        public short getShort() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            return JNI.get_short(checkedPeer);
        }

        @Override
        public short getShortAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 2L);
            }
            return JNI.get_short(checkedPeer);
        }

        @Override
        public Pointer<T> setByte(byte value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_byte(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setByteAtOffset(long byteOffset, byte value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_byte(checkedPeer, value);
            return this;
        }

        @Override
        public byte getByte() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_byte(checkedPeer);
        }

        @Override
        public byte getByteAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_byte(checkedPeer);
        }

        @Override
        public Pointer<T> setChar(char value) {
            if (Platform.WCHAR_T_SIZE == 4) {
                return this.setInt(value);
            }
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            JNI.set_char(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setCharAtOffset(long byteOffset, char value) {
            if (Platform.WCHAR_T_SIZE == 4) {
                return this.setIntAtOffset(byteOffset, value);
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            JNI.set_char(checkedPeer, value);
            return this;
        }

        @Override
        public char getChar() {
            if (Platform.WCHAR_T_SIZE == 4) {
                return (char)this.getInt();
            }
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            return JNI.get_char(checkedPeer);
        }

        @Override
        public char getCharAtOffset(long byteOffset) {
            if (Platform.WCHAR_T_SIZE == 4) {
                return (char)this.getIntAtOffset(byteOffset);
            }
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, Platform.WCHAR_T_SIZE);
            }
            return JNI.get_char(checkedPeer);
        }

        @Override
        public Pointer<T> setFloat(float value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_float(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setFloatAtOffset(long byteOffset, float value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            JNI.set_float(checkedPeer, value);
            return this;
        }

        @Override
        public float getFloat() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_float(checkedPeer);
        }

        @Override
        public float getFloatAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 4L);
            }
            return JNI.get_float(checkedPeer);
        }

        @Override
        public Pointer<T> setDouble(double value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_double(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setDoubleAtOffset(long byteOffset, double value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            JNI.set_double(checkedPeer, value);
            return this;
        }

        @Override
        public double getDouble() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_double(checkedPeer);
        }

        @Override
        public double getDoubleAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 8L);
            }
            return JNI.get_double(checkedPeer);
        }

        @Override
        public Pointer<T> setBoolean(boolean value) {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_boolean(checkedPeer, value);
            return this;
        }

        @Override
        public Pointer<T> setBooleanAtOffset(long byteOffset, boolean value) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            JNI.set_boolean(checkedPeer, value);
            return this;
        }

        @Override
        public boolean getBoolean() {
            long checkedPeer = this.peer;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_boolean(checkedPeer);
        }

        @Override
        public boolean getBooleanAtOffset(long byteOffset) {
            long checkedPeer = this.peer + byteOffset;
            if (this.validStart != UNKNOWN_VALIDITY) {
                this.checkPeer(checkedPeer, 1L);
            }
            return JNI.get_boolean(checkedPeer);
        }

        @Override
        public Pointer<T> setSizeTsAtOffset(long byteOffset, long[] values, int valuesOffset, int length) {
            if (values == null) {
                throw new IllegalArgumentException("Null values");
            }
            if (SizeT.SIZE == 8) {
                this.setLongsAtOffset(byteOffset, values, valuesOffset, length);
            } else {
                int n2 = length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 4);
                }
                long peer = checkedPeer;
                int valuesIndex = valuesOffset;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = (int)values[valuesIndex];
                    JNI.set_int(peer, value);
                    peer += 4L;
                    ++valuesIndex;
                }
            }
            return this;
        }

        @Override
        public Pointer<T> setSizeTsAtOffset(long byteOffset, int[] values) {
            if (SizeT.SIZE == 4) {
                this.setIntsAtOffset(byteOffset, values);
            } else {
                int n2 = values.length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 8);
                }
                long peer = checkedPeer;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = values[i2];
                    JNI.set_long(peer, value);
                    peer += 8L;
                }
            }
            return this;
        }

        @Override
        public Pointer<T> setCLongsAtOffset(long byteOffset, long[] values, int valuesOffset, int length) {
            if (values == null) {
                throw new IllegalArgumentException("Null values");
            }
            if (CLong.SIZE == 8) {
                this.setLongsAtOffset(byteOffset, values, valuesOffset, length);
            } else {
                int n2 = length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 4);
                }
                long peer = checkedPeer;
                int valuesIndex = valuesOffset;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = (int)values[valuesIndex];
                    JNI.set_int(peer, value);
                    peer += 4L;
                    ++valuesIndex;
                }
            }
            return this;
        }

        @Override
        public Pointer<T> setCLongsAtOffset(long byteOffset, int[] values) {
            if (CLong.SIZE == 4) {
                this.setIntsAtOffset(byteOffset, values);
            } else {
                int n2 = values.length;
                long checkedPeer = this.peer + byteOffset;
                if (this.validStart != UNKNOWN_VALIDITY) {
                    this.checkPeer(checkedPeer, n2 * 8);
                }
                long peer = checkedPeer;
                for (int i2 = 0; i2 < n2; ++i2) {
                    int value = values[i2];
                    JNI.set_long(peer, value);
                    peer += 8L;
                }
            }
            return this;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface Releaser {
        public void release(Pointer<?> var1);
    }
}

