/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class ListenerManager {
    private final WebSocket mWebSocket;
    private final List<WebSocketListener> mListeners = new ArrayList<WebSocketListener>();
    private boolean mSyncNeeded = true;
    private List<WebSocketListener> mCopiedListeners;

    public ListenerManager(WebSocket websocket) {
        this.mWebSocket = websocket;
    }

    public List<WebSocketListener> getListeners() {
        return this.mListeners;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addListener(WebSocketListener listener) {
        if (listener == null) {
            return;
        }
        List<WebSocketListener> list = this.mListeners;
        synchronized (list) {
            this.mListeners.add(listener);
            this.mSyncNeeded = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addListeners(List<WebSocketListener> listeners) {
        if (listeners == null) {
            return;
        }
        List<WebSocketListener> list = this.mListeners;
        synchronized (list) {
            for (WebSocketListener listener : listeners) {
                if (listener == null) continue;
                this.mListeners.add(listener);
                this.mSyncNeeded = true;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeListener(WebSocketListener listener) {
        if (listener == null) {
            return;
        }
        List<WebSocketListener> list = this.mListeners;
        synchronized (list) {
            if (this.mListeners.remove(listener)) {
                this.mSyncNeeded = true;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeListeners(List<WebSocketListener> listeners) {
        if (listeners == null) {
            return;
        }
        List<WebSocketListener> list = this.mListeners;
        synchronized (list) {
            for (WebSocketListener listener : listeners) {
                if (listener == null || !this.mListeners.remove(listener)) continue;
                this.mSyncNeeded = true;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearListeners() {
        List<WebSocketListener> list = this.mListeners;
        synchronized (list) {
            if (this.mListeners.size() == 0) {
                return;
            }
            this.mListeners.clear();
            this.mCopiedListeners = null;
            this.mSyncNeeded = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private List<WebSocketListener> getSynchronizedListeners() {
        List<WebSocketListener> list = this.mListeners;
        synchronized (list) {
            if (!this.mSyncNeeded) {
                return this.mCopiedListeners;
            }
            ArrayList<WebSocketListener> copiedListeners = new ArrayList<WebSocketListener>(this.mListeners.size());
            for (WebSocketListener listener : this.mListeners) {
                copiedListeners.add(listener);
            }
            this.mCopiedListeners = copiedListeners;
            this.mSyncNeeded = false;
            return copiedListeners;
        }
    }

    public void callOnStateChanged(WebSocketState newState) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onStateChanged(this.mWebSocket, newState);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnConnected(Map<String, List<String>> headers) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onConnected(this.mWebSocket, headers);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnConnectError(WebSocketException cause) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onConnectError(this.mWebSocket, cause);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnDisconnected(WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onDisconnected(this.mWebSocket, serverCloseFrame, clientCloseFrame, closedByServer);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnContinuationFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onContinuationFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnTextFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onTextFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnBinaryFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onBinaryFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnCloseFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onCloseFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnPingFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onPingFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnPongFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onPongFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnTextMessage(String message) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onTextMessage(this.mWebSocket, message);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnTextMessage(byte[] data) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onTextMessage(this.mWebSocket, data);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnBinaryMessage(byte[] message) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onBinaryMessage(this.mWebSocket, message);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnSendingFrame(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onSendingFrame(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnFrameSent(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onFrameSent(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnFrameUnsent(WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onFrameUnsent(this.mWebSocket, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnThreadCreated(ThreadType threadType, Thread thread) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onThreadCreated(this.mWebSocket, threadType, thread);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnThreadStarted(ThreadType threadType, Thread thread) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onThreadStarted(this.mWebSocket, threadType, thread);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnThreadStopping(ThreadType threadType, Thread thread) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onThreadStopping(this.mWebSocket, threadType, thread);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnError(WebSocketException cause) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onError(this.mWebSocket, cause);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnFrameError(WebSocketException cause, WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onFrameError(this.mWebSocket, cause, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnMessageError(WebSocketException cause, List<WebSocketFrame> frames) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onMessageError(this.mWebSocket, cause, frames);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnMessageDecompressionError(WebSocketException cause, byte[] compressed) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onMessageDecompressionError(this.mWebSocket, cause, compressed);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnTextMessageError(WebSocketException cause, byte[] data) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onTextMessageError(this.mWebSocket, cause, data);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnSendError(WebSocketException cause, WebSocketFrame frame) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onSendError(this.mWebSocket, cause, frame);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    public void callOnUnexpectedError(WebSocketException cause) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onUnexpectedError(this.mWebSocket, cause);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }

    private void callHandleCallbackError(WebSocketListener listener, Throwable cause) {
        try {
            listener.handleCallbackError(this.mWebSocket, cause);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public void callOnSendingHandshake(String requestLine, List<String[]> headers) {
        for (WebSocketListener listener : this.getSynchronizedListeners()) {
            try {
                listener.onSendingHandshake(this.mWebSocket, requestLine, headers);
            }
            catch (Throwable t2) {
                this.callHandleCallbackError(listener, t2);
            }
        }
    }
}

