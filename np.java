/*
 * Decompiled with CFR 0.152.
 */
import java.lang.reflect.Array;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class np<T> {
    private final T[] a;
    private final Class<? extends T> b;
    private final ReadWriteLock c = new ReentrantReadWriteLock();
    private int d;
    private int e;

    public np(Class<? extends T> clazz, int n2) {
        this.b = clazz;
        this.a = (Object[])Array.newInstance(clazz, n2);
    }

    public T a(T t2) {
        this.c.writeLock().lock();
        this.a[this.e] = t2;
        this.e = (this.e + 1) % this.b();
        if (this.d < this.b()) {
            ++this.d;
        }
        this.c.writeLock().unlock();
        return t2;
    }

    public int b() {
        this.c.readLock().lock();
        int n2 = this.a.length;
        this.c.readLock().unlock();
        return n2;
    }

    public T[] c() {
        Object[] objectArray = (Object[])Array.newInstance(this.b, this.d);
        this.c.readLock().lock();
        for (int i2 = 0; i2 < this.d; ++i2) {
            \u2603 = (this.e - this.d + i2) % this.b();
            if (\u2603 < 0) {
                \u2603 += this.b();
            }
            objectArray[i2] = this.a[\u2603];
        }
        this.c.readLock().unlock();
        return objectArray;
    }
}

