/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.util.RequestPayload;

public class InputCoercionException
extends StreamReadException {
    private static final long serialVersionUID = 1L;
    protected final JsonToken _inputType;
    protected final Class<?> _targetType;

    public InputCoercionException(JsonParser p2, String msg, JsonToken inputType, Class<?> targetType) {
        super(p2, msg);
        this._inputType = inputType;
        this._targetType = targetType;
    }

    @Override
    public InputCoercionException withParser(JsonParser p2) {
        this._processor = p2;
        return this;
    }

    @Override
    public InputCoercionException withRequestPayload(RequestPayload p2) {
        this._requestPayload = p2;
        return this;
    }

    public JsonToken getInputType() {
        return this._inputType;
    }

    public Class<?> getTargetType() {
        return this._targetType;
    }
}

