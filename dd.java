/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class dd<K, V>
implements db<K, V> {
    private static final Logger a = LogManager.getLogger();
    protected final Map<K, V> c = this.b();

    protected Map<K, V> b() {
        return Maps.newHashMap();
    }

    @Override
    public V a(K k2) {
        return this.c.get(k2);
    }

    @Override
    public void a(K k2, V v2) {
        Validate.notNull(k2);
        Validate.notNull(v2);
        if (this.c.containsKey(k2)) {
            a.debug("Adding duplicate key '" + k2 + "' to registry");
        }
        this.c.put(k2, v2);
    }

    public Set<K> c() {
        return Collections.unmodifiableSet(this.c.keySet());
    }

    public boolean d(K k2) {
        return this.c.containsKey(k2);
    }

    @Override
    public Iterator<V> iterator() {
        return this.c.values().iterator();
    }
}

