/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleFilterProvider
extends FilterProvider
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final Map<String, PropertyFilter> _filtersById;
    protected PropertyFilter _defaultFilter;
    protected boolean _cfgFailOnUnknownId = true;

    public SimpleFilterProvider() {
        this(new HashMap());
    }

    public SimpleFilterProvider(Map<String, ?> mapping) {
        for (Object ob2 : mapping.values()) {
            if (ob2 instanceof PropertyFilter) continue;
            this._filtersById = SimpleFilterProvider._convert(mapping);
            return;
        }
        this._filtersById = mapping;
    }

    private static final Map<String, PropertyFilter> _convert(Map<String, ?> filters) {
        HashMap<String, PropertyFilter> result = new HashMap<String, PropertyFilter>();
        for (Map.Entry<String, ?> entry : filters.entrySet()) {
            Object f2 = entry.getValue();
            if (f2 instanceof PropertyFilter) {
                result.put(entry.getKey(), (PropertyFilter)f2);
                continue;
            }
            if (f2 instanceof BeanPropertyFilter) {
                result.put(entry.getKey(), SimpleFilterProvider._convert((BeanPropertyFilter)f2));
                continue;
            }
            throw new IllegalArgumentException("Unrecognized filter type (" + f2.getClass().getName() + ")");
        }
        return result;
    }

    private static final PropertyFilter _convert(BeanPropertyFilter f2) {
        return SimpleBeanPropertyFilter.from(f2);
    }

    @Deprecated
    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter f2) {
        this._defaultFilter = SimpleBeanPropertyFilter.from(f2);
        return this;
    }

    public SimpleFilterProvider setDefaultFilter(PropertyFilter f2) {
        this._defaultFilter = f2;
        return this;
    }

    public SimpleFilterProvider setDefaultFilter(SimpleBeanPropertyFilter f2) {
        this._defaultFilter = f2;
        return this;
    }

    public PropertyFilter getDefaultFilter() {
        return this._defaultFilter;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean state) {
        this._cfgFailOnUnknownId = state;
        return this;
    }

    public boolean willFailOnUnknownId() {
        return this._cfgFailOnUnknownId;
    }

    @Deprecated
    public SimpleFilterProvider addFilter(String id2, BeanPropertyFilter filter) {
        this._filtersById.put(id2, SimpleFilterProvider._convert(filter));
        return this;
    }

    public SimpleFilterProvider addFilter(String id2, PropertyFilter filter) {
        this._filtersById.put(id2, filter);
        return this;
    }

    public SimpleFilterProvider addFilter(String id2, SimpleBeanPropertyFilter filter) {
        this._filtersById.put(id2, filter);
        return this;
    }

    public PropertyFilter removeFilter(String id2) {
        return this._filtersById.remove(id2);
    }

    @Override
    @Deprecated
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Access to deprecated filters not supported");
    }

    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        PropertyFilter f2 = this._filtersById.get(filterId);
        if (f2 == null && (f2 = this._defaultFilter) == null && this._cfgFailOnUnknownId) {
            throw new IllegalArgumentException("No filter configured with id '" + filterId + "' (type " + filterId.getClass().getName() + ")");
        }
        return f2;
    }
}

