/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.WebSocketState;

class StateManager {
    private WebSocketState mState;
    private CloseInitiator mCloseInitiator = CloseInitiator.NONE;

    public StateManager() {
        this.mState = WebSocketState.CREATED;
    }

    public WebSocketState getState() {
        return this.mState;
    }

    public void setState(WebSocketState state) {
        this.mState = state;
    }

    public void changeToClosing(CloseInitiator closeInitiator) {
        this.mState = WebSocketState.CLOSING;
        if (this.mCloseInitiator == CloseInitiator.NONE) {
            this.mCloseInitiator = closeInitiator;
        }
    }

    public boolean getClosedByServer() {
        return this.mCloseInitiator == CloseInitiator.SERVER;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static enum CloseInitiator {
        NONE,
        SERVER,
        CLIENT;

    }
}

