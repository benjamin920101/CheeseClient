/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;

abstract class PerMessageCompressionExtension
extends WebSocketExtension {
    public PerMessageCompressionExtension(String name) {
        super(name);
    }

    public PerMessageCompressionExtension(WebSocketExtension source) {
        super(source);
    }

    protected abstract byte[] decompress(byte[] var1) throws WebSocketException;

    protected abstract byte[] compress(byte[] var1) throws WebSocketException;
}

