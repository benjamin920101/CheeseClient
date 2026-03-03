/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import java.io.IOException;
import java.util.BitSet;

public class PropertyValueBuffer {
    protected final JsonParser _parser;
    protected final DeserializationContext _context;
    protected final ObjectIdReader _objectIdReader;
    protected final Object[] _creatorParameters;
    protected int _paramsNeeded;
    protected int _paramsSeen;
    protected final BitSet _paramsSeenBig;
    protected PropertyValue _buffered;
    protected Object _idValue;

    public PropertyValueBuffer(JsonParser p2, DeserializationContext ctxt, int paramCount, ObjectIdReader oir) {
        this._parser = p2;
        this._context = ctxt;
        this._paramsNeeded = paramCount;
        this._objectIdReader = oir;
        this._creatorParameters = new Object[paramCount];
        this._paramsSeenBig = paramCount < 32 ? null : new BitSet();
    }

    public final boolean hasParameter(SettableBeanProperty prop) {
        if (this._paramsSeenBig == null) {
            return (this._paramsSeen >> prop.getCreatorIndex() & 1) == 1;
        }
        return this._paramsSeenBig.get(prop.getCreatorIndex());
    }

    public Object getParameter(SettableBeanProperty prop) throws JsonMappingException {
        Object value;
        if (this.hasParameter(prop)) {
            value = this._creatorParameters[prop.getCreatorIndex()];
        } else {
            Object object = this._findMissing(prop);
            this._creatorParameters[prop.getCreatorIndex()] = object;
            value = object;
        }
        if (value == null && this._context.isEnabled(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)) {
            return this._context.reportInputMismatch(prop, "Null value for creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_NULL_FOR_CREATOR_PARAMETERS` enabled", prop.getName(), prop.getCreatorIndex());
        }
        return value;
    }

    public Object[] getParameters(SettableBeanProperty[] props) throws JsonMappingException {
        if (this._paramsNeeded > 0) {
            int ix2;
            if (this._paramsSeenBig == null) {
                int mask = this._paramsSeen;
                ix2 = 0;
                int len = this._creatorParameters.length;
                while (ix2 < len) {
                    if ((mask & 1) == 0) {
                        this._creatorParameters[ix2] = this._findMissing(props[ix2]);
                    }
                    ++ix2;
                    mask >>= 1;
                }
            } else {
                int len = this._creatorParameters.length;
                ix2 = 0;
                while ((ix2 = this._paramsSeenBig.nextClearBit(ix2)) < len) {
                    this._creatorParameters[ix2] = this._findMissing(props[ix2]);
                    ++ix2;
                }
            }
        }
        if (this._context.isEnabled(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)) {
            for (int ix3 = 0; ix3 < props.length; ++ix3) {
                if (this._creatorParameters[ix3] != null) continue;
                SettableBeanProperty prop = props[ix3];
                this._context.reportInputMismatch(prop, "Null value for creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_NULL_FOR_CREATOR_PARAMETERS` enabled", prop.getName(), props[ix3].getCreatorIndex());
            }
        }
        return this._creatorParameters;
    }

    protected Object _findMissing(SettableBeanProperty prop) throws JsonMappingException {
        Object nullValue;
        Object injectableValueId = prop.getInjectableValueId();
        if (injectableValueId != null) {
            return this._context.findInjectableValue(prop.getInjectableValueId(), prop, null);
        }
        if (prop.isRequired()) {
            this._context.reportInputMismatch(prop, "Missing required creator property '%s' (index %d)", prop.getName(), prop.getCreatorIndex());
        }
        if (this._context.isEnabled(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)) {
            this._context.reportInputMismatch(prop, "Missing creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES` enabled", prop.getName(), prop.getCreatorIndex());
        }
        if ((nullValue = prop.getNullValueProvider().getNullValue(this._context)) != null) {
            return nullValue;
        }
        JsonDeserializer<Object> deser = prop.getValueDeserializer();
        return deser.getNullValue(this._context);
    }

    public boolean readIdProperty(String propName) throws IOException {
        if (this._objectIdReader != null && propName.equals(this._objectIdReader.propertyName.getSimpleName())) {
            this._idValue = this._objectIdReader.readObjectReference(this._parser, this._context);
            return true;
        }
        return false;
    }

    public Object handleIdValue(DeserializationContext ctxt, Object bean) throws IOException {
        if (this._objectIdReader != null) {
            if (this._idValue != null) {
                ReadableObjectId roid = ctxt.findObjectId(this._idValue, this._objectIdReader.generator, this._objectIdReader.resolver);
                roid.bindItem(bean);
                SettableBeanProperty idProp = this._objectIdReader.idProperty;
                if (idProp != null) {
                    return idProp.setAndReturn(bean, this._idValue);
                }
            } else {
                ctxt.reportUnresolvedObjectId(this._objectIdReader, bean);
            }
        }
        return bean;
    }

    protected PropertyValue buffered() {
        return this._buffered;
    }

    public boolean isComplete() {
        return this._paramsNeeded <= 0;
    }

    public boolean assignParameter(SettableBeanProperty prop, Object value) {
        int ix2 = prop.getCreatorIndex();
        this._creatorParameters[ix2] = value;
        if (this._paramsSeenBig == null) {
            int old = this._paramsSeen;
            int newValue = old | 1 << ix2;
            if (old != newValue) {
                this._paramsSeen = newValue;
                if (--this._paramsNeeded <= 0) {
                    return this._objectIdReader == null || this._idValue != null;
                }
            }
        } else if (!this._paramsSeenBig.get(ix2)) {
            this._paramsSeenBig.set(ix2);
            if (--this._paramsNeeded <= 0) {
                // empty if block
            }
        }
        return false;
    }

    public void bufferProperty(SettableBeanProperty prop, Object value) {
        this._buffered = new PropertyValue.Regular(this._buffered, value, prop);
    }

    public void bufferAnyProperty(SettableAnyProperty prop, String propName, Object value) {
        this._buffered = new PropertyValue.Any(this._buffered, value, prop, propName);
    }

    public void bufferMapProperty(Object key, Object value) {
        this._buffered = new PropertyValue.Map(this._buffered, value, key);
    }
}

