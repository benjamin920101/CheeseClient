/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class ct<T>
implements cs<T> {
    private final IdentityHashMap<T, Integer> a = new IdentityHashMap(512);
    private final List<T> b = Lists.newArrayList();

    public void a(T t2, int n2) {
        this.a.put(t2, n2);
        while (this.b.size() <= n2) {
            this.b.add(null);
        }
        this.b.set(n2, t2);
    }

    public int b(T t2) {
        Integer n2 = this.a.get(t2);
        return n2 == null ? -1 : n2;
    }

    public final T a(int n2) {
        if (n2 >= 0 && n2 < this.b.size()) {
            return this.b.get(n2);
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.filter(this.b.iterator(), Predicates.notNull());
    }
}

