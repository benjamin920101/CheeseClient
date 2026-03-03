/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.util.RequestPayload;

public class JsonParseException
extends StreamReadException {
    private static final long serialVersionUID = 2L;

    @Deprecated
    public JsonParseException(String msg, JsonLocation loc) {
        super(msg, loc, null);
    }

    @Deprecated
    public JsonParseException(String msg, JsonLocation loc, Throwable root) {
        super(msg, loc, root);
    }

    public JsonParseException(JsonParser p2, String msg) {
        super(p2, msg);
    }

    public JsonParseException(JsonParser p2, String msg, Throwable root) {
        super(p2, msg, root);
    }

    public JsonParseException(JsonParser p2, String msg, JsonLocation loc) {
        super(p2, msg, loc);
    }

    public JsonParseException(JsonParser p2, String msg, JsonLocation loc, Throwable root) {
        super(msg, loc, root);
    }

    @Override
    public JsonParseException withParser(JsonParser p2) {
        this._processor = p2;
        return this;
    }

    @Override
    public JsonParseException withRequestPayload(RequestPayload p2) {
        this._requestPayload = p2;
        return this;
    }

    @Override
    public JsonParser getProcessor() {
        return super.getProcessor();
    }

    @Override
    public RequestPayload getRequestPayload() {
        return super.getRequestPayload();
    }

    @Override
    public String getRequestPayloadAsString() {
        return super.getRequestPayloadAsString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

