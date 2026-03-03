/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketState;
import java.util.List;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface WebSocketListener {
    public void onStateChanged(WebSocket var1, WebSocketState var2) throws Exception;

    public void onConnected(WebSocket var1, Map<String, List<String>> var2) throws Exception;

    public void onConnectError(WebSocket var1, WebSocketException var2) throws Exception;

    public void onDisconnected(WebSocket var1, WebSocketFrame var2, WebSocketFrame var3, boolean var4) throws Exception;

    public void onFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onContinuationFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onTextFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onBinaryFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onCloseFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onPingFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onPongFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onTextMessage(WebSocket var1, String var2) throws Exception;

    public void onTextMessage(WebSocket var1, byte[] var2) throws Exception;

    public void onBinaryMessage(WebSocket var1, byte[] var2) throws Exception;

    public void onSendingFrame(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onFrameSent(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onFrameUnsent(WebSocket var1, WebSocketFrame var2) throws Exception;

    public void onThreadCreated(WebSocket var1, ThreadType var2, Thread var3) throws Exception;

    public void onThreadStarted(WebSocket var1, ThreadType var2, Thread var3) throws Exception;

    public void onThreadStopping(WebSocket var1, ThreadType var2, Thread var3) throws Exception;

    public void onError(WebSocket var1, WebSocketException var2) throws Exception;

    public void onFrameError(WebSocket var1, WebSocketException var2, WebSocketFrame var3) throws Exception;

    public void onMessageError(WebSocket var1, WebSocketException var2, List<WebSocketFrame> var3) throws Exception;

    public void onMessageDecompressionError(WebSocket var1, WebSocketException var2, byte[] var3) throws Exception;

    public void onTextMessageError(WebSocket var1, WebSocketException var2, byte[] var3) throws Exception;

    public void onSendError(WebSocket var1, WebSocketException var2, WebSocketFrame var3) throws Exception;

    public void onUnexpectedError(WebSocket var1, WebSocketException var2) throws Exception;

    public void handleCallbackError(WebSocket var1, Throwable var2) throws Exception;

    public void onSendingHandshake(WebSocket var1, String var2, List<String[]> var3) throws Exception;
}

