/*
 * Decompiled with CFR 0.152.
 */
import org.apache.commons.lang3.Validate;

public class co<K, V>
extends cx<K, V> {
    private final K d;
    private V e;

    public co(K k2) {
        this.d = k2;
    }

    @Override
    public void a(int n2, K k2, V v2) {
        if (this.d.equals(k2)) {
            this.e = v2;
        }
        super.a(n2, k2, v2);
    }

    public void a() {
        Validate.notNull(this.d);
    }

    @Override
    public V a(K k2) {
        Object v2 = super.a(k2);
        return v2 == null ? this.e : v2;
    }

    @Override
    public V a(int n2) {
        Object v2 = super.a(n2);
        return v2 == null ? this.e : v2;
    }
}

