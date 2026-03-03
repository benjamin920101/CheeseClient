/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class cm {
    public static <T> Iterable<T[]> a(Class<T> clazz, Iterable<? extends Iterable<? extends T>> iterable) {
        return new b(clazz, cm.b(Iterable.class, iterable));
    }

    public static <T> Iterable<List<T>> a(Iterable<? extends Iterable<? extends T>> iterable) {
        return cm.b(cm.a(Object.class, iterable));
    }

    private static <T> Iterable<List<T>> b(Iterable<Object[]> iterable) {
        return Iterables.transform(iterable, new a());
    }

    private static <T> T[] b(Class<? super T> clazz, Iterable<? extends T> iterable) {
        ArrayList<T> arrayList = Lists.newArrayList();
        for (T t2 : iterable) {
            arrayList.add(t2);
        }
        return arrayList.toArray(cm.b(clazz, arrayList.size()));
    }

    private static <T> T[] b(Class<? super T> clazz, int n2) {
        return (Object[])Array.newInstance(clazz, n2);
    }

    static class b<T>
    implements Iterable<T[]> {
        private final Class<T> a;
        private final Iterable<? extends T>[] b;

        private b(Class<T> clazz, Iterable<? extends T>[] iterableArray) {
            this.a = clazz;
            this.b = iterableArray;
        }

        @Override
        public Iterator<T[]> iterator() {
            if (this.b.length <= 0) {
                return Collections.singletonList(cm.b(this.a, 0)).iterator();
            }
            return new a(this.a, this.b);
        }

        static class a<T>
        extends UnmodifiableIterator<T[]> {
            private int a = -2;
            private final Iterable<? extends T>[] b;
            private final Iterator<? extends T>[] c;
            private final T[] d;

            private a(Class<T> clazz2, Iterable<? extends T>[] iterableArray) {
                Class<T> clazz2;
                this.b = iterableArray;
                this.c = (Iterator[])cm.b(Iterator.class, this.b.length);
                for (int i2 = 0; i2 < this.b.length; ++i2) {
                    this.c[i2] = iterableArray[i2].iterator();
                }
                this.d = cm.b(clazz2, this.c.length);
            }

            private void b() {
                this.a = -1;
                Arrays.fill(this.c, null);
                Arrays.fill(this.d, null);
            }

            @Override
            public boolean hasNext() {
                if (this.a == -2) {
                    this.a = 0;
                    for (Iterator<T> iterator : this.c) {
                        if (iterator.hasNext()) continue;
                        this.b();
                        break;
                    }
                    return true;
                }
                if (this.a >= this.c.length) {
                    Iterator<T> iterator;
                    this.a = this.c.length - 1;
                    while (this.a >= 0 && !(iterator = this.c[this.a]).hasNext()) {
                        if (this.a == 0) {
                            this.b();
                            break;
                        }
                        iterator = this.b[this.a].iterator();
                        this.c[this.a] = iterator;
                        if (!iterator.hasNext()) {
                            this.b();
                            break;
                        }
                        --this.a;
                    }
                }
                return this.a >= 0;
            }

            public T[] a() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                while (this.a < this.c.length) {
                    this.d[this.a] = this.c[this.a].next();
                    ++this.a;
                }
                return (Object[])this.d.clone();
            }

            @Override
            public /* synthetic */ Object next() {
                return this.a();
            }
        }
    }

    static class a<T>
    implements Function<Object[], List<T>> {
        private a() {
        }

        public List<T> a(Object[] objectArray) {
            return Arrays.asList(objectArray);
        }

        @Override
        public /* synthetic */ Object apply(Object object) {
            return this.a((Object[])object);
        }
    }
}

