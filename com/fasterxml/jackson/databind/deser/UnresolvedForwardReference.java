/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.UnresolvedId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnresolvedForwardReference
extends JsonMappingException {
    private static final long serialVersionUID = 1L;
    private ReadableObjectId _roid;
    private List<UnresolvedId> _unresolvedIds;

    public UnresolvedForwardReference(JsonParser p2, String msg, JsonLocation loc, ReadableObjectId roid) {
        super((Closeable)p2, msg, loc);
        this._roid = roid;
    }

    public UnresolvedForwardReference(JsonParser p2, String msg) {
        super(p2, msg);
        this._unresolvedIds = new ArrayList<UnresolvedId>();
    }

    @Deprecated
    public UnresolvedForwardReference(String msg, JsonLocation loc, ReadableObjectId roid) {
        super(msg, loc);
        this._roid = roid;
    }

    @Deprecated
    public UnresolvedForwardReference(String msg) {
        super(msg);
        this._unresolvedIds = new ArrayList<UnresolvedId>();
    }

    public ReadableObjectId getRoid() {
        return this._roid;
    }

    public Object getUnresolvedId() {
        return this._roid.getKey().key;
    }

    public void addUnresolvedId(Object id2, Class<?> type, JsonLocation where) {
        this._unresolvedIds.add(new UnresolvedId(id2, type, where));
    }

    public List<UnresolvedId> getUnresolvedIds() {
        return this._unresolvedIds;
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (this._unresolvedIds == null) {
            return msg;
        }
        StringBuilder sb2 = new StringBuilder(msg);
        Iterator<UnresolvedId> iterator = this._unresolvedIds.iterator();
        while (iterator.hasNext()) {
            UnresolvedId unresolvedId = iterator.next();
            sb2.append(unresolvedId.toString());
            if (!iterator.hasNext()) continue;
            sb2.append(", ");
        }
        sb2.append('.');
        return sb2.toString();
    }
}

