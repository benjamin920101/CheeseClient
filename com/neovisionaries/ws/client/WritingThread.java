/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ListenerManager;
import com.neovisionaries.ws.client.PerMessageCompressionExtension;
import com.neovisionaries.ws.client.StateManager;
import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketState;
import com.neovisionaries.ws.client.WebSocketThread;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

class WritingThread
extends WebSocketThread {
    private static final int SHOULD_SEND = 0;
    private static final int SHOULD_STOP = 1;
    private static final int SHOULD_CONTINUE = 2;
    private static final int SHOULD_FLUSH = 3;
    private static final int FLUSH_THRESHOLD = 1000;
    private final LinkedList<WebSocketFrame> mFrames = new LinkedList();
    private final PerMessageCompressionExtension mPMCE;
    private boolean mStopRequested;
    private WebSocketFrame mCloseFrame;
    private boolean mFlushNeeded;
    private boolean mStopped;

    public WritingThread(WebSocket websocket) {
        super("WritingThread", websocket, ThreadType.WRITING_THREAD);
        this.mPMCE = websocket.getPerMessageCompressionExtension();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void runMain() {
        try {
            this.main();
        }
        catch (Throwable t2) {
            WebSocketException cause = new WebSocketException(WebSocketError.UNEXPECTED_ERROR_IN_WRITING_THREAD, "An uncaught throwable was detected in the writing thread: " + t2.getMessage(), t2);
            ListenerManager manager = this.mWebSocket.getListenerManager();
            manager.callOnError(cause);
            manager.callOnUnexpectedError(cause);
        }
        WritingThread writingThread = this;
        synchronized (writingThread) {
            this.mStopped = true;
            this.notifyAll();
        }
        this.notifyFinished();
    }

    private void main() {
        int result;
        this.mWebSocket.onWritingThreadStarted();
        while ((result = this.waitForFrames()) != 1) {
            if (result == 3) {
                this.flushIgnoreError();
                continue;
            }
            if (result == 2) continue;
            try {
                this.sendFrames(false);
            }
            catch (WebSocketException e2) {
                break;
            }
        }
        try {
            this.sendFrames(true);
        }
        catch (WebSocketException webSocketException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void requestStop() {
        WritingThread writingThread = this;
        synchronized (writingThread) {
            this.mStopRequested = true;
            this.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean queueFrame(WebSocketFrame frame) {
        WritingThread writingThread = this;
        synchronized (writingThread) {
            while (true) {
                int queueSize;
                if (this.mStopped) {
                    return false;
                }
                if (this.mStopRequested || this.mCloseFrame != null || frame.isControlFrame() || (queueSize = this.mWebSocket.getFrameQueueSize()) == 0 || this.mFrames.size() < queueSize) break;
                try {
                    this.wait();
                }
                catch (InterruptedException interruptedException) {}
            }
            if (WritingThread.isHighPriorityFrame(frame)) {
                this.addHighPriorityFrame(frame);
            } else {
                this.mFrames.addLast(frame);
            }
            this.notifyAll();
            return true;
        }
    }

    private static boolean isHighPriorityFrame(WebSocketFrame frame) {
        return frame.isPingFrame() || frame.isPongFrame();
    }

    private void addHighPriorityFrame(WebSocketFrame frame) {
        WebSocketFrame f2;
        int index = 0;
        Iterator iterator = this.mFrames.iterator();
        while (iterator.hasNext() && WritingThread.isHighPriorityFrame(f2 = (WebSocketFrame)iterator.next())) {
            ++index;
        }
        this.mFrames.add(index, frame);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void queueFlush() {
        WritingThread writingThread = this;
        synchronized (writingThread) {
            this.mFlushNeeded = true;
            this.notifyAll();
        }
    }

    private void flushIgnoreError() {
        try {
            this.flush();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private void flush() throws IOException {
        this.mWebSocket.getOutput().flush();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int waitForFrames() {
        WritingThread writingThread = this;
        synchronized (writingThread) {
            if (this.mStopRequested) {
                return 1;
            }
            if (this.mCloseFrame != null) {
                return 1;
            }
            if (this.mFrames.size() == 0) {
                if (this.mFlushNeeded) {
                    this.mFlushNeeded = false;
                    return 3;
                }
                try {
                    this.wait();
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
            if (this.mStopRequested) {
                return 1;
            }
            if (this.mFrames.size() == 0) {
                if (this.mFlushNeeded) {
                    this.mFlushNeeded = false;
                    return 3;
                }
                return 2;
            }
        }
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void sendFrames(boolean last) throws WebSocketException {
        long lastFlushAt = System.currentTimeMillis();
        while (true) {
            WebSocketFrame frame;
            WritingThread writingThread = this;
            synchronized (writingThread) {
                frame = this.mFrames.poll();
                this.notifyAll();
                if (frame == null) {
                    break;
                }
            }
            this.sendFrame(frame);
            if (frame.isPingFrame() || frame.isPongFrame()) {
                this.doFlush();
                lastFlushAt = System.currentTimeMillis();
                continue;
            }
            if (!this.isFlushNeeded(last)) continue;
            lastFlushAt = this.flushIfLongInterval(lastFlushAt);
        }
        if (this.isFlushNeeded(last)) {
            this.doFlush();
        }
    }

    private boolean isFlushNeeded(boolean last) {
        return last || this.mWebSocket.isAutoFlush() || this.mFlushNeeded || this.mCloseFrame != null;
    }

    private long flushIfLongInterval(long lastFlushAt) throws WebSocketException {
        long current = System.currentTimeMillis();
        if (1000L < current - lastFlushAt) {
            this.doFlush();
            return current;
        }
        return lastFlushAt;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doFlush() throws WebSocketException {
        try {
            this.flush();
            WritingThread writingThread = this;
            synchronized (writingThread) {
                this.mFlushNeeded = false;
            }
        }
        catch (IOException e2) {
            WebSocketException cause = new WebSocketException(WebSocketError.FLUSH_ERROR, "Flushing frames to the server failed: " + e2.getMessage(), e2);
            ListenerManager manager = this.mWebSocket.getListenerManager();
            manager.callOnError(cause);
            manager.callOnSendError(cause, null);
            throw cause;
        }
    }

    private void sendFrame(WebSocketFrame frame) throws WebSocketException {
        frame = WebSocketFrame.compressFrame(frame, this.mPMCE);
        this.mWebSocket.getListenerManager().callOnSendingFrame(frame);
        boolean unsent = false;
        if (this.mCloseFrame != null) {
            unsent = true;
        } else if (frame.isCloseFrame()) {
            this.mCloseFrame = frame;
        }
        if (unsent) {
            this.mWebSocket.getListenerManager().callOnFrameUnsent(frame);
            return;
        }
        if (frame.isCloseFrame()) {
            this.changeToClosing();
        }
        try {
            this.mWebSocket.getOutput().write(frame);
        }
        catch (IOException e2) {
            WebSocketException cause = new WebSocketException(WebSocketError.IO_ERROR_IN_WRITING, "An I/O error occurred when a frame was tried to be sent: " + e2.getMessage(), e2);
            ListenerManager manager = this.mWebSocket.getListenerManager();
            manager.callOnError(cause);
            manager.callOnSendError(cause, frame);
            throw cause;
        }
        this.mWebSocket.getListenerManager().callOnFrameSent(frame);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void changeToClosing() {
        StateManager manager = this.mWebSocket.getStateManager();
        boolean stateChanged = false;
        StateManager stateManager = manager;
        synchronized (stateManager) {
            WebSocketState state = manager.getState();
            if (state != WebSocketState.CLOSING && state != WebSocketState.CLOSED) {
                manager.changeToClosing(StateManager.CloseInitiator.CLIENT);
                stateChanged = true;
            }
        }
        if (stateChanged) {
            this.mWebSocket.getListenerManager().callOnStateChanged(WebSocketState.CLOSING);
        }
    }

    private void notifyFinished() {
        this.mWebSocket.onWritingThreadFinished(this.mCloseFrame);
    }
}

