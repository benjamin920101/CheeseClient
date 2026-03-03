/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ne<T>
extends AbstractSet<T> {
    private static final Set<Class<?>> a = Sets.newHashSet();
    private final Map<Class<?>, List<T>> b = Maps.newHashMap();
    private final Set<Class<?>> c = Sets.newIdentityHashSet();
    private final Class<T> d;
    private final List<T> e = Lists.newArrayList();

    public ne(Class<T> clazz) {
        this.d = clazz;
        this.c.add(clazz);
        this.b.put(clazz, this.e);
        for (Class<?> clazz2 : a) {
            this.a(clazz2);
        }
    }

    protected void a(Class<?> clazz2) {
        Class<?> clazz2;
        a.add(clazz2);
        for (T t2 : this.e) {
            if (!clazz2.isAssignableFrom(t2.getClass())) continue;
            this.a(t2, clazz2);
        }
        this.c.add(clazz2);
    }

    protected Class<?> b(Class<?> clazz) {
        if (this.d.isAssignableFrom(clazz)) {
            if (!this.c.contains(clazz)) {
                this.a(clazz);
            }
            return clazz;
        }
        throw new IllegalArgumentException("Don't know how to search for " + clazz);
    }

    @Override
    public boolean add(T t2) {
        for (Class<?> clazz : this.c) {
            if (!clazz.isAssignableFrom(t2.getClass())) continue;
            this.a(t2, clazz);
        }
        return true;
    }

    private void a(T t2, Class<?> clazz) {
        List<T> list = this.b.get(clazz);
        if (list == null) {
            this.b.put(clazz, Lists.newArrayList(t2));
        } else {
            list.add(t2);
        }
    }

    @Override
    public boolean remove(Object object) {
        \u2603 = object;
        boolean bl2 = false;
        for (Class<?> clazz : this.c) {
            if (!clazz.isAssignableFrom(\u2603.getClass()) || (\u2603 = this.b.get(clazz)) == null || !\u2603.remove(\u2603)) continue;
            bl2 = true;
        }
        return bl2;
    }

    @Override
    public boolean contains(Object object) {
        return Iterators.contains(this.c(object.getClass()).iterator(), object);
    }

    public <S> Iterable<S> c(final Class<S> clazz) {
        return new Iterable<S>(){

            @Override
            public Iterator<S> iterator() {
                List list = (List)ne.this.b.get(ne.this.b(clazz));
                if (list == null) {
                    return Iterators.emptyIterator();
                }
                Iterator \u26032 = list.iterator();
                return Iterators.filter(\u26032, clazz);
            }
        };
    }

    @Override
    public Iterator<T> iterator() {
        if (this.e.isEmpty()) {
            return Iterators.emptyIterator();
        }
        return Iterators.unmodifiableIterator(this.e.iterator());
    }

    @Override
    public int size() {
        return this.e.size();
    }
}

