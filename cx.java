/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;

public class cx<K, V>
extends dd<K, V>
implements cs<V> {
    protected final ct<V> a = new ct();
    protected final Map<V, K> b = ((BiMap)this.c).inverse();

    public void a(int n2, K k2, V v2) {
        this.a.a(v2, n2);
        this.a(k2, v2);
    }

    @Override
    protected Map<K, V> b() {
        return HashBiMap.create();
    }

    @Override
    public V a(K k2) {
        return super.a(k2);
    }

    public K c(V v2) {
        return this.b.get(v2);
    }

    @Override
    public boolean d(K k2) {
        return super.d(k2);
    }

    public int b(V v2) {
        return this.a.b(v2);
    }

    @Override
    public V a(int n2) {
        return this.a.a(n2);
    }

    @Override
    public Iterator<V> iterator() {
        return this.a.iterator();
    }
}

