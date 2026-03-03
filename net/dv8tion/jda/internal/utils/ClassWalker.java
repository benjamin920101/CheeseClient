/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import javax.annotation.Nonnull;

public class ClassWalker
implements Iterable<Class<?>> {
    private final Class<?> clazz;
    private final Class<?> end;

    private ClassWalker(Class<?> clazz) {
        this(clazz, Object.class);
    }

    private ClassWalker(Class<?> clazz, Class<?> end) {
        this.clazz = clazz;
        this.end = end;
    }

    public static ClassWalker range(Class<?> start, Class<?> end) {
        return new ClassWalker(start, end);
    }

    public static ClassWalker walk(Class<?> start) {
        return new ClassWalker(start);
    }

    @Override
    @Nonnull
    public Iterator<Class<?>> iterator() {
        return new Iterator<Class<?>>(){
            private final Set<Class<?>> done = new HashSet();
            private final Deque<Class<?>> work = new LinkedList();
            {
                this.work.addLast(ClassWalker.this.clazz);
                this.done.add(ClassWalker.this.end);
            }

            @Override
            public boolean hasNext() {
                return !this.work.isEmpty();
            }

            @Override
            public Class<?> next() {
                Class<?> current = this.work.removeFirst();
                this.done.add(current);
                for (Class<?> parent : current.getInterfaces()) {
                    if (this.done.contains(parent)) continue;
                    this.work.addLast(parent);
                }
                Class<?> parent = current.getSuperclass();
                if (parent != null && !this.done.contains(parent)) {
                    this.work.addLast(parent);
                }
                return current;
            }
        };
    }
}

