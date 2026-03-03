/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import java.util.concurrent.Callable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class Connectable
implements Callable<WebSocket> {
    private final WebSocket mWebSocket;

    public Connectable(WebSocket ws2) {
        this.mWebSocket = ws2;
    }

    @Override
    public WebSocket call() throws WebSocketException {
        return this.mWebSocket.connect();
    }
}

