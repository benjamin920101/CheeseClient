/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class PropertyBindingException
extends MismatchedInputException {
    protected final Class<?> _referringClass;
    protected final String _propertyName;
    protected final Collection<Object> _propertyIds;
    protected transient String _propertiesAsString;
    private static final int MAX_DESC_LENGTH = 1000;

    protected PropertyBindingException(JsonParser p2, String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(p2, msg, loc);
        this._referringClass = referringClass;
        this._propertyName = propName;
        this._propertyIds = propertyIds;
    }

    @Deprecated
    protected PropertyBindingException(String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        this(null, msg, loc, referringClass, propName, propertyIds);
    }

    @Override
    public String getMessageSuffix() {
        String suffix = this._propertiesAsString;
        if (suffix == null && this._propertyIds != null) {
            StringBuilder sb2 = new StringBuilder(100);
            int len = this._propertyIds.size();
            if (len == 1) {
                sb2.append(" (one known property: \"");
                sb2.append(String.valueOf(this._propertyIds.iterator().next()));
                sb2.append('\"');
            } else {
                sb2.append(" (").append(len).append(" known properties: ");
                Iterator<Object> it2 = this._propertyIds.iterator();
                while (it2.hasNext()) {
                    sb2.append('\"');
                    sb2.append(String.valueOf(it2.next()));
                    sb2.append('\"');
                    if (sb2.length() > 1000) {
                        sb2.append(" [truncated]");
                        break;
                    }
                    if (!it2.hasNext()) continue;
                    sb2.append(", ");
                }
            }
            sb2.append("])");
            this._propertiesAsString = suffix = sb2.toString();
        }
        return suffix;
    }

    public Class<?> getReferringClass() {
        return this._referringClass;
    }

    public String getPropertyName() {
        return this._propertyName;
    }

    public Collection<Object> getKnownPropertyIds() {
        if (this._propertyIds == null) {
            return null;
        }
        return Collections.unmodifiableCollection(this._propertyIds);
    }
}

