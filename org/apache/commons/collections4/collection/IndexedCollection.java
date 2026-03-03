/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.map.MultiValueMap;

public class IndexedCollection<K, C>
extends AbstractCollectionDecorator<C> {
    private static final long serialVersionUID = -5512610452568370038L;
    private final Transformer<C, K> keyTransformer;
    private final MultiMap<K, C> index;
    private final boolean uniqueIndex;

    public static <K, C> IndexedCollection<K, C> uniqueIndexedCollection(Collection<C> coll, Transformer<C, K> keyTransformer) {
        return new IndexedCollection<K, C>(coll, keyTransformer, MultiValueMap.multiValueMap(new HashMap()), true);
    }

    public static <K, C> IndexedCollection<K, C> nonUniqueIndexedCollection(Collection<C> coll, Transformer<C, K> keyTransformer) {
        return new IndexedCollection<K, C>(coll, keyTransformer, MultiValueMap.multiValueMap(new HashMap()), false);
    }

    public IndexedCollection(Collection<C> coll, Transformer<C, K> keyTransformer, MultiMap<K, C> map, boolean uniqueIndex) {
        super(coll);
        this.keyTransformer = keyTransformer;
        this.index = map;
        this.uniqueIndex = uniqueIndex;
        this.reindex();
    }

    @Override
    public boolean add(C object) {
        boolean added = super.add(object);
        if (added) {
            this.addToIndex(object);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends C> coll) {
        boolean changed = false;
        for (C c2 : coll) {
            changed |= this.add(c2);
        }
        return changed;
    }

    @Override
    public void clear() {
        super.clear();
        this.index.clear();
    }

    @Override
    public boolean contains(Object object) {
        return this.index.containsKey(this.keyTransformer.transform(object));
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        for (Object o2 : coll) {
            if (this.contains(o2)) continue;
            return false;
        }
        return true;
    }

    public C get(K key) {
        Collection coll = (Collection)this.index.get(key);
        return coll == null ? null : (C)coll.iterator().next();
    }

    public Collection<C> values(K key) {
        return (Collection)this.index.get(key);
    }

    public void reindex() {
        this.index.clear();
        for (Object c2 : this.decorated()) {
            this.addToIndex(c2);
        }
    }

    @Override
    public boolean remove(Object object) {
        boolean removed = super.remove(object);
        if (removed) {
            this.removeFromIndex(object);
        }
        return removed;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean changed = false;
        for (Object o2 : coll) {
            changed |= this.remove(o2);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        boolean changed = super.retainAll(coll);
        if (changed) {
            this.reindex();
        }
        return changed;
    }

    private void addToIndex(C object) {
        K key = this.keyTransformer.transform(object);
        if (this.uniqueIndex && this.index.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate key in uniquely indexed collection.");
        }
        this.index.put(key, (Object)object);
    }

    private void removeFromIndex(C object) {
        this.index.remove(this.keyTransformer.transform(object));
    }
}

