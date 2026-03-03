/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T>
implements Iterator<T>,
Closeable {
    protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
    protected static final int STATE_CLOSED = 0;
    protected static final int STATE_NEED_RESYNC = 1;
    protected static final int STATE_MAY_HAVE_VALUE = 2;
    protected static final int STATE_HAS_VALUE = 3;
    protected final JavaType _type;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected final JsonParser _parser;
    protected final JsonStreamContext _seqContext;
    protected final T _updatedValue;
    protected final boolean _closeParser;
    protected int _state;

    protected MappingIterator(JavaType type, JsonParser p2, DeserializationContext ctxt, JsonDeserializer<?> deser, boolean managedParser, Object valueToUpdate) {
        this._type = type;
        this._parser = p2;
        this._context = ctxt;
        this._deserializer = deser;
        this._closeParser = managedParser;
        this._updatedValue = valueToUpdate == null ? null : valueToUpdate;
        if (p2 == null) {
            this._seqContext = null;
            this._state = 0;
        } else {
            JsonStreamContext sctxt = p2.getParsingContext();
            if (managedParser && p2.isExpectedStartArrayToken()) {
                p2.clearCurrentToken();
            } else {
                JsonToken t2 = p2.currentToken();
                if (t2 == JsonToken.START_OBJECT || t2 == JsonToken.START_ARRAY) {
                    sctxt = sctxt.getParent();
                }
            }
            this._seqContext = sctxt;
            this._state = 2;
        }
    }

    public static <T> MappingIterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    @Override
    public boolean hasNext() {
        try {
            return this.hasNextValue();
        }
        catch (JsonMappingException e2) {
            return (Boolean)this._handleMappingException(e2);
        }
        catch (IOException e3) {
            return (Boolean)this._handleIOException(e3);
        }
    }

    @Override
    public T next() {
        try {
            return this.nextValue();
        }
        catch (JsonMappingException e2) {
            return (T)this._handleMappingException(e2);
        }
        catch (IOException e3) {
            return (T)this._handleIOException(e3);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        if (this._state != 0) {
            this._state = 0;
            if (this._parser != null) {
                this._parser.close();
            }
        }
    }

    public boolean hasNextValue() throws IOException {
        switch (this._state) {
            case 0: {
                return false;
            }
            case 1: {
                this._resync();
            }
            case 2: {
                JsonToken t2 = this._parser.currentToken();
                if (t2 == null && ((t2 = this._parser.nextToken()) == null || t2 == JsonToken.END_ARRAY)) {
                    this._state = 0;
                    if (this._closeParser && this._parser != null) {
                        this._parser.close();
                    }
                    return false;
                }
                this._state = 3;
                return true;
            }
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public T nextValue() throws IOException {
        switch (this._state) {
            case 0: {
                return (T)this._throwNoSuchElement();
            }
            case 1: 
            case 2: {
                if (this.hasNextValue()) break;
                return (T)this._throwNoSuchElement();
            }
        }
        int nextState = 1;
        try {
            T value;
            if (this._updatedValue == null) {
                value = this._deserializer.deserialize(this._parser, this._context);
            } else {
                this._deserializer.deserialize(this._parser, this._context, this._updatedValue);
                value = this._updatedValue;
            }
            nextState = 2;
            T t2 = value;
            return t2;
        }
        finally {
            this._state = nextState;
            this._parser.clearCurrentToken();
        }
    }

    public List<T> readAll() throws IOException {
        return this.readAll((Collection)new ArrayList());
    }

    public <L extends List<? super T>> L readAll(L resultList) throws IOException {
        while (this.hasNextValue()) {
            resultList.add(this.nextValue());
        }
        return resultList;
    }

    public <C extends Collection<? super T>> C readAll(C results) throws IOException {
        while (this.hasNextValue()) {
            results.add(this.nextValue());
        }
        return results;
    }

    public JsonParser getParser() {
        return this._parser;
    }

    public FormatSchema getParserSchema() {
        return this._parser.getSchema();
    }

    public JsonLocation getCurrentLocation() {
        return this._parser.getCurrentLocation();
    }

    protected void _resync() throws IOException {
        JsonParser p2 = this._parser;
        if (p2.getParsingContext() == this._seqContext) {
            return;
        }
        while (true) {
            JsonToken t2;
            if ((t2 = p2.nextToken()) == JsonToken.END_ARRAY || t2 == JsonToken.END_OBJECT) {
                if (p2.getParsingContext() != this._seqContext) continue;
                p2.clearCurrentToken();
                return;
            }
            if (t2 == JsonToken.START_ARRAY || t2 == JsonToken.START_OBJECT) {
                p2.skipChildren();
                continue;
            }
            if (t2 == null) break;
        }
    }

    protected <R> R _throwNoSuchElement() {
        throw new NoSuchElementException();
    }

    protected <R> R _handleMappingException(JsonMappingException e2) {
        throw new RuntimeJsonMappingException(e2.getMessage(), e2);
    }

    protected <R> R _handleIOException(IOException e2) {
        throw new RuntimeException(e2.getMessage(), e2);
    }
}

