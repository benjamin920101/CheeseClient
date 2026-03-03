/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class nk<V>
implements Map<String, V> {
    private final Map<String, V> a = Maps.newLinkedHashMap();

    @Override
    public int size() {
        return this.a.size();
    }

    @Override
    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override
    public boolean containsKey(Object object) {
        return this.a.containsKey(object.toString().toLowerCase());
    }

    @Override
    public boolean containsValue(Object object) {
        return this.a.containsKey(object);
    }

    @Override
    public V get(Object object) {
        return this.a.get(object.toString().toLowerCase());
    }

    public V a(String string, V v2) {
        return this.a.put(string.toLowerCase(), v2);
    }

    @Override
    public V remove(Object object) {
        return this.a.remove(object.toString().toLowerCase());
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> map) {
        for (Map.Entry<String, V> entry : map.entrySet()) {
            this.a(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.a.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.a.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.a.values();
    }

    @Override
    public Set<Map.Entry<String, V>> entrySet() {
        return this.a.entrySet();
    }

    @Override
    public /* synthetic */ Object put(Object object, Object object2) {
        return this.a((String)object, object2);
    }
}

