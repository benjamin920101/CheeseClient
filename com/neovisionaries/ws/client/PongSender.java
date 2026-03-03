/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.PayloadGenerator;
import com.neovisionaries.ws.client.PeriodicalFrameSender;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFrame;

class PongSender
extends PeriodicalFrameSender {
    private static final String TIMER_NAME = "PongSender";

    public PongSender(WebSocket webSocket, PayloadGenerator generator) {
        super(webSocket, TIMER_NAME, generator);
    }

    protected WebSocketFrame createFrame(byte[] payload) {
        return WebSocketFrame.createPongFrame(payload);
    }
}

