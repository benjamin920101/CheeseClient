/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.util.AbstractList;
import java.util.Collection;
import java.util.RandomAccess;
import org.bridj.NativeList;
import org.bridj.Pointer;
import org.bridj.PointerIO;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class DefaultNativeList<T>
extends AbstractList<T>
implements NativeList<T>,
RandomAccess {
    final Pointer.ListType type;
    final PointerIO<T> io;
    volatile Pointer<T> pointer;
    volatile long size;

    @Override
    public Pointer<?> getPointer() {
        return this.pointer;
    }

    DefaultNativeList(Pointer<T> pointer, Pointer.ListType type) {
        if (pointer == null || type == null) {
            throw new IllegalArgumentException("Cannot build a " + this.getClass().getSimpleName() + " with " + pointer + " and " + (Object)((Object)type));
        }
        this.io = pointer.getIO("Cannot create a list out of untyped pointer " + pointer);
        this.type = type;
        this.size = pointer.getValidElements();
        this.pointer = pointer;
    }

    protected void checkModifiable() {
        if (this.type == Pointer.ListType.Unmodifiable) {
            throw new UnsupportedOperationException("This list is unmodifiable");
        }
    }

    protected int safelyCastLongToInt(long i2, String content) {
        if (i2 > Integer.MAX_VALUE) {
            throw new RuntimeException(content + " is bigger than Java int's maximum value : " + i2);
        }
        return (int)i2;
    }

    @Override
    public int size() {
        return this.safelyCastLongToInt(this.size, "Size of the native list");
    }

    @Override
    public void clear() {
        this.checkModifiable();
        this.size = 0L;
    }

    @Override
    public T get(int i2) {
        if ((long)i2 >= this.size || i2 < 0) {
            throw new IndexOutOfBoundsException("Invalid index : " + i2 + " (list has size " + this.size + ")");
        }
        return this.pointer.get(i2);
    }

    @Override
    public T set(int i2, T e2) {
        this.checkModifiable();
        if ((long)i2 >= this.size || i2 < 0) {
            throw new IndexOutOfBoundsException("Invalid index : " + i2 + " (list has size " + this.size + ")");
        }
        T old = this.pointer.get(i2);
        this.pointer.set(i2, e2);
        return old;
    }

    void add(long i2, T e2) {
        this.checkModifiable();
        if (i2 > this.size || i2 < 0L) {
            throw new IndexOutOfBoundsException("Invalid index : " + i2 + " (list has size " + this.size + ")");
        }
        this.requireSize(this.size + 1L);
        if (i2 < this.size) {
            this.pointer.moveBytesAtOffsetTo(i2, this.pointer, i2 + 1L, this.size - i2);
        }
        this.pointer.set(i2, e2);
        ++this.size;
    }

    @Override
    public void add(int i2, T e2) {
        this.add((long)i2, e2);
    }

    protected void requireSize(long newSize) {
        if (newSize > this.pointer.getValidElements()) {
            switch (this.type) {
                case Dynamic: {
                    long nextSize = newSize < 5L ? newSize + 1L : (long)((double)newSize * 1.6);
                    Pointer<T> newPointer = Pointer.allocateArray(this.io, nextSize);
                    this.pointer.copyTo(newPointer);
                    this.pointer = newPointer;
                    break;
                }
                case FixedCapacity: {
                    throw new UnsupportedOperationException("This list has a fixed capacity, cannot grow its storage");
                }
                case Unmodifiable: {
                    this.checkModifiable();
                }
            }
        }
    }

    T remove(long i2) {
        this.checkModifiable();
        if (i2 >= this.size || i2 < 0L) {
            throw new IndexOutOfBoundsException("Invalid index : " + i2 + " (list has size " + this.size + ")");
        }
        T old = this.pointer.get(i2);
        long targetSize = this.io.getTargetSize();
        this.pointer.moveBytesAtOffsetTo((i2 + 1L) * targetSize, this.pointer, i2 * targetSize, targetSize);
        --this.size;
        return old;
    }

    @Override
    public T remove(int i2) {
        return this.remove((long)i2);
    }

    @Override
    public boolean remove(Object o2) {
        this.checkModifiable();
        long i2 = this.indexOf(o2, true, 0);
        if (i2 < 0L) {
            return false;
        }
        this.remove(i2);
        return true;
    }

    long indexOf(Object o2, boolean last, int offset) {
        Pointer<T> occurrence;
        Pointer<T> pointer = this.pointer;
        assert (offset >= 0 && (last || offset > 0));
        if (offset > 0) {
            pointer = pointer.next(offset);
        }
        Pointer<Object> needle = Pointer.allocate(this.io);
        needle.set(o2);
        Pointer<T> pointer2 = occurrence = last ? pointer.findLast(needle) : pointer.find(needle);
        if (occurrence == null) {
            return -1L;
        }
        return occurrence.getPeer() - pointer.getPeer();
    }

    @Override
    public int indexOf(Object o2) {
        return this.safelyCastLongToInt(this.indexOf(o2, false, 0), "Index of the object");
    }

    @Override
    public int lastIndexOf(Object o2) {
        return this.safelyCastLongToInt(this.indexOf(o2, true, 0), "Last index of the object");
    }

    @Override
    public boolean contains(Object o2) {
        return this.indexOf(o2) >= 0;
    }

    @Override
    public boolean addAll(int i2, Collection<? extends T> clctn) {
        if (i2 >= 0 && (long)i2 < this.size) {
            this.requireSize(this.size + (long)clctn.size());
        }
        return super.addAll(i2, clctn);
    }

    @Override
    public Object[] toArray() {
        return this.pointer.validElements(this.size).toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts2) {
        return this.pointer.validElements(this.size).toArray(ts2);
    }
}

