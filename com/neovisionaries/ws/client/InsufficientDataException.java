/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;

class InsufficientDataException
extends WebSocketException {
    private static final long serialVersionUID = 1L;
    private final int mRequestedByteCount;
    private final int mReadByteCount;

    public InsufficientDataException(int requestedByteCount, int readByteCount) {
        super(WebSocketError.INSUFFICENT_DATA, "The end of the stream has been reached unexpectedly.");
        this.mRequestedByteCount = requestedByteCount;
        this.mReadByteCount = readByteCount;
    }

    public int getRequestedByteCount() {
        return this.mRequestedByteCount;
    }

    public int getReadByteCount() {
        return this.mReadByteCount;
    }
}

