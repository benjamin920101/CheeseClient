/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import java.util.HashMap;
import java.util.Map;

public class SimpleObjectIdResolver
implements ObjectIdResolver {
    protected Map<ObjectIdGenerator.IdKey, Object> _items;

    @Override
    public void bindItem(ObjectIdGenerator.IdKey id2, Object ob2) {
        if (this._items == null) {
            this._items = new HashMap<ObjectIdGenerator.IdKey, Object>();
        } else if (this._items.containsKey(id2)) {
            throw new IllegalStateException("Already had POJO for id (" + id2.key.getClass().getName() + ") [" + id2 + "]");
        }
        this._items.put(id2, ob2);
    }

    @Override
    public Object resolveId(ObjectIdGenerator.IdKey id2) {
        return this._items == null ? null : this._items.get(id2);
    }

    @Override
    public boolean canUseFor(ObjectIdResolver resolverType) {
        return resolverType.getClass() == this.getClass();
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return new SimpleObjectIdResolver();
    }
}

