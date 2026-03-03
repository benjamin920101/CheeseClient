/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ListenerManager;
import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;

abstract class WebSocketThread
extends Thread {
    protected final WebSocket mWebSocket;
    private final ThreadType mThreadType;

    WebSocketThread(String name, WebSocket ws2, ThreadType type) {
        super(name);
        this.mWebSocket = ws2;
        this.mThreadType = type;
    }

    public void run() {
        ListenerManager lm2 = this.mWebSocket.getListenerManager();
        if (lm2 != null) {
            lm2.callOnThreadStarted(this.mThreadType, this);
        }
        this.runMain();
        if (lm2 != null) {
            lm2.callOnThreadStopping(this.mThreadType, this);
        }
    }

    public void callOnThreadCreated() {
        ListenerManager lm2 = this.mWebSocket.getListenerManager();
        if (lm2 != null) {
            lm2.callOnThreadCreated(this.mThreadType, this);
        }
    }

    protected abstract void runMain();
}

