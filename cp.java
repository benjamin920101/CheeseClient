/*
 * Decompiled with CFR 0.152.
 */
public class cp<K, V>
extends dd<K, V> {
    private final V a;

    public cp(V v2) {
        this.a = v2;
    }

    @Override
    public V a(K k2) {
        Object v2 = super.a(k2);
        return v2 == null ? this.a : v2;
    }
}

