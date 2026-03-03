/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Base64;
import com.neovisionaries.ws.client.ConnectThread;
import com.neovisionaries.ws.client.Connectable;
import com.neovisionaries.ws.client.CounterPayloadGenerator;
import com.neovisionaries.ws.client.FinishThread;
import com.neovisionaries.ws.client.HandshakeBuilder;
import com.neovisionaries.ws.client.HandshakeReader;
import com.neovisionaries.ws.client.ListenerManager;
import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.PayloadGenerator;
import com.neovisionaries.ws.client.PerMessageCompressionExtension;
import com.neovisionaries.ws.client.PingSender;
import com.neovisionaries.ws.client.PongSender;
import com.neovisionaries.ws.client.ReadingThread;
import com.neovisionaries.ws.client.SocketConnector;
import com.neovisionaries.ws.client.StateManager;
import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketInputStream;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketOutputStream;
import com.neovisionaries.ws.client.WebSocketState;
import com.neovisionaries.ws.client.WritingThread;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WebSocket {
    private static final long DEFAULT_CLOSE_DELAY = 10000L;
    private final WebSocketFactory mWebSocketFactory;
    private final SocketConnector mSocketConnector;
    private final StateManager mStateManager;
    private HandshakeBuilder mHandshakeBuilder;
    private final ListenerManager mListenerManager;
    private final PingSender mPingSender;
    private final PongSender mPongSender;
    private final Object mThreadsLock = new Object();
    private WebSocketInputStream mInput;
    private WebSocketOutputStream mOutput;
    private ReadingThread mReadingThread;
    private WritingThread mWritingThread;
    private Map<String, List<String>> mServerHeaders;
    private List<WebSocketExtension> mAgreedExtensions;
    private String mAgreedProtocol;
    private boolean mExtended;
    private boolean mAutoFlush = true;
    private boolean mMissingCloseFrameAllowed = true;
    private boolean mDirectTextMessage;
    private int mFrameQueueSize;
    private int mMaxPayloadSize;
    private boolean mOnConnectedCalled;
    private Object mOnConnectedCalledLock = new Object();
    private boolean mReadingThreadStarted;
    private boolean mWritingThreadStarted;
    private boolean mReadingThreadFinished;
    private boolean mWritingThreadFinished;
    private WebSocketFrame mServerCloseFrame;
    private WebSocketFrame mClientCloseFrame;
    private PerMessageCompressionExtension mPerMessageCompressionExtension;

    WebSocket(WebSocketFactory factory, boolean secure, String userInfo, String host, String path, SocketConnector connector) {
        this.mWebSocketFactory = factory;
        this.mSocketConnector = connector;
        this.mStateManager = new StateManager();
        this.mHandshakeBuilder = new HandshakeBuilder(secure, userInfo, host, path);
        this.mListenerManager = new ListenerManager(this);
        this.mPingSender = new PingSender(this, new CounterPayloadGenerator());
        this.mPongSender = new PongSender(this, new CounterPayloadGenerator());
    }

    public WebSocket recreate() throws IOException {
        return this.recreate(this.mSocketConnector.getConnectionTimeout());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public WebSocket recreate(int timeout) throws IOException {
        List<WebSocketListener> listeners;
        if (timeout < 0) {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
        WebSocket instance = this.mWebSocketFactory.createSocket(this.getURI(), timeout);
        instance.mHandshakeBuilder = new HandshakeBuilder(this.mHandshakeBuilder);
        instance.setPingInterval(this.getPingInterval());
        instance.setPongInterval(this.getPongInterval());
        instance.setPingPayloadGenerator(this.getPingPayloadGenerator());
        instance.setPongPayloadGenerator(this.getPongPayloadGenerator());
        instance.mExtended = this.mExtended;
        instance.mAutoFlush = this.mAutoFlush;
        instance.mMissingCloseFrameAllowed = this.mMissingCloseFrameAllowed;
        instance.mDirectTextMessage = this.mDirectTextMessage;
        instance.mFrameQueueSize = this.mFrameQueueSize;
        List<WebSocketListener> list = listeners = this.mListenerManager.getListeners();
        synchronized (list) {
            instance.addListeners(listeners);
        }
        return instance;
    }

    protected void finalize() throws Throwable {
        if (this.isInState(WebSocketState.CREATED)) {
            this.finish();
        }
        super.finalize();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public WebSocketState getState() {
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            return this.mStateManager.getState();
        }
    }

    public boolean isOpen() {
        return this.isInState(WebSocketState.OPEN);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean isInState(WebSocketState state) {
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            return this.mStateManager.getState() == state;
        }
    }

    public WebSocket addProtocol(String protocol) {
        this.mHandshakeBuilder.addProtocol(protocol);
        return this;
    }

    public WebSocket removeProtocol(String protocol) {
        this.mHandshakeBuilder.removeProtocol(protocol);
        return this;
    }

    public WebSocket clearProtocols() {
        this.mHandshakeBuilder.clearProtocols();
        return this;
    }

    public WebSocket addExtension(WebSocketExtension extension) {
        this.mHandshakeBuilder.addExtension(extension);
        return this;
    }

    public WebSocket addExtension(String extension) {
        this.mHandshakeBuilder.addExtension(extension);
        return this;
    }

    public WebSocket removeExtension(WebSocketExtension extension) {
        this.mHandshakeBuilder.removeExtension(extension);
        return this;
    }

    public WebSocket removeExtensions(String name) {
        this.mHandshakeBuilder.removeExtensions(name);
        return this;
    }

    public WebSocket clearExtensions() {
        this.mHandshakeBuilder.clearExtensions();
        return this;
    }

    public WebSocket addHeader(String name, String value) {
        this.mHandshakeBuilder.addHeader(name, value);
        return this;
    }

    public WebSocket removeHeaders(String name) {
        this.mHandshakeBuilder.removeHeaders(name);
        return this;
    }

    public WebSocket clearHeaders() {
        this.mHandshakeBuilder.clearHeaders();
        return this;
    }

    public WebSocket setUserInfo(String userInfo) {
        this.mHandshakeBuilder.setUserInfo(userInfo);
        return this;
    }

    public WebSocket setUserInfo(String id2, String password) {
        this.mHandshakeBuilder.setUserInfo(id2, password);
        return this;
    }

    public WebSocket clearUserInfo() {
        this.mHandshakeBuilder.clearUserInfo();
        return this;
    }

    public boolean isExtended() {
        return this.mExtended;
    }

    public WebSocket setExtended(boolean extended) {
        this.mExtended = extended;
        return this;
    }

    public boolean isAutoFlush() {
        return this.mAutoFlush;
    }

    public WebSocket setAutoFlush(boolean auto) {
        this.mAutoFlush = auto;
        return this;
    }

    public boolean isMissingCloseFrameAllowed() {
        return this.mMissingCloseFrameAllowed;
    }

    public WebSocket setMissingCloseFrameAllowed(boolean allowed) {
        this.mMissingCloseFrameAllowed = allowed;
        return this;
    }

    public boolean isDirectTextMessage() {
        return this.mDirectTextMessage;
    }

    public WebSocket setDirectTextMessage(boolean direct) {
        this.mDirectTextMessage = direct;
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public WebSocket flush() {
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            WebSocketState state = this.mStateManager.getState();
            if (state != WebSocketState.OPEN && state != WebSocketState.CLOSING) {
                return this;
            }
        }
        WritingThread wt2 = this.mWritingThread;
        if (wt2 != null) {
            wt2.queueFlush();
        }
        return this;
    }

    public int getFrameQueueSize() {
        return this.mFrameQueueSize;
    }

    public WebSocket setFrameQueueSize(int size) throws IllegalArgumentException {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative.");
        }
        this.mFrameQueueSize = size;
        return this;
    }

    public int getMaxPayloadSize() {
        return this.mMaxPayloadSize;
    }

    public WebSocket setMaxPayloadSize(int size) throws IllegalArgumentException {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative.");
        }
        this.mMaxPayloadSize = size;
        return this;
    }

    public long getPingInterval() {
        return this.mPingSender.getInterval();
    }

    public WebSocket setPingInterval(long interval) {
        this.mPingSender.setInterval(interval);
        return this;
    }

    public long getPongInterval() {
        return this.mPongSender.getInterval();
    }

    public WebSocket setPongInterval(long interval) {
        this.mPongSender.setInterval(interval);
        return this;
    }

    public PayloadGenerator getPingPayloadGenerator() {
        return this.mPingSender.getPayloadGenerator();
    }

    public WebSocket setPingPayloadGenerator(PayloadGenerator generator) {
        this.mPingSender.setPayloadGenerator(generator);
        return this;
    }

    public PayloadGenerator getPongPayloadGenerator() {
        return this.mPongSender.getPayloadGenerator();
    }

    public WebSocket setPongPayloadGenerator(PayloadGenerator generator) {
        this.mPongSender.setPayloadGenerator(generator);
        return this;
    }

    public String getPingSenderName() {
        return this.mPingSender.getTimerName();
    }

    public WebSocket setPingSenderName(String name) {
        this.mPingSender.setTimerName(name);
        return this;
    }

    public String getPongSenderName() {
        return this.mPongSender.getTimerName();
    }

    public WebSocket setPongSenderName(String name) {
        this.mPongSender.setTimerName(name);
        return this;
    }

    public WebSocket addListener(WebSocketListener listener) {
        this.mListenerManager.addListener(listener);
        return this;
    }

    public WebSocket addListeners(List<WebSocketListener> listeners) {
        this.mListenerManager.addListeners(listeners);
        return this;
    }

    public WebSocket removeListener(WebSocketListener listener) {
        this.mListenerManager.removeListener(listener);
        return this;
    }

    public WebSocket removeListeners(List<WebSocketListener> listeners) {
        this.mListenerManager.removeListeners(listeners);
        return this;
    }

    public WebSocket clearListeners() {
        this.mListenerManager.clearListeners();
        return this;
    }

    public Socket getSocket() {
        return this.mSocketConnector.getSocket();
    }

    public Socket getConnectedSocket() throws WebSocketException {
        return this.mSocketConnector.getConnectedSocket();
    }

    public URI getURI() {
        return this.mHandshakeBuilder.getURI();
    }

    public WebSocket connect() throws WebSocketException {
        Map<String, List<String>> headers;
        this.changeStateOnConnect();
        try {
            Socket socket = this.mSocketConnector.connect();
            headers = this.shakeHands(socket);
        }
        catch (WebSocketException e2) {
            this.mSocketConnector.closeSilently();
            this.mStateManager.setState(WebSocketState.CLOSED);
            this.mListenerManager.callOnStateChanged(WebSocketState.CLOSED);
            throw e2;
        }
        this.mServerHeaders = headers;
        this.mPerMessageCompressionExtension = this.findAgreedPerMessageCompressionExtension();
        this.mStateManager.setState(WebSocketState.OPEN);
        this.mListenerManager.callOnStateChanged(WebSocketState.OPEN);
        this.startThreads();
        return this;
    }

    public Future<WebSocket> connect(ExecutorService executorService) {
        return executorService.submit(this.connectable());
    }

    public Callable<WebSocket> connectable() {
        return new Connectable(this);
    }

    public WebSocket connectAsynchronously() {
        ConnectThread thread = new ConnectThread(this);
        ListenerManager lm2 = this.mListenerManager;
        if (lm2 != null) {
            lm2.callOnThreadCreated(ThreadType.CONNECT_THREAD, thread);
        }
        thread.start();
        return this;
    }

    public WebSocket disconnect() {
        return this.disconnect(1000, null);
    }

    public WebSocket disconnect(int closeCode) {
        return this.disconnect(closeCode, null);
    }

    public WebSocket disconnect(String reason) {
        return this.disconnect(1000, reason);
    }

    public WebSocket disconnect(int closeCode, String reason) {
        return this.disconnect(closeCode, reason, 10000L);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public WebSocket disconnect(int closeCode, String reason, long closeDelay) {
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            switch (this.mStateManager.getState()) {
                case CREATED: {
                    this.finishAsynchronously();
                    return this;
                }
                case OPEN: {
                    break;
                }
                default: {
                    return this;
                }
            }
            this.mStateManager.changeToClosing(StateManager.CloseInitiator.CLIENT);
            WebSocketFrame frame = WebSocketFrame.createCloseFrame(closeCode, reason);
            this.sendFrame(frame);
        }
        this.mListenerManager.callOnStateChanged(WebSocketState.CLOSING);
        if (closeDelay < 0L) {
            closeDelay = 10000L;
        }
        this.stopThreads(closeDelay);
        return this;
    }

    public List<WebSocketExtension> getAgreedExtensions() {
        return this.mAgreedExtensions;
    }

    public String getAgreedProtocol() {
        return this.mAgreedProtocol;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public WebSocket sendFrame(WebSocketFrame frame) {
        if (frame == null) {
            return this;
        }
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            WebSocketState state = this.mStateManager.getState();
            if (state != WebSocketState.OPEN && state != WebSocketState.CLOSING) {
                return this;
            }
        }
        WritingThread wt2 = this.mWritingThread;
        if (wt2 == null) {
            return this;
        }
        List<WebSocketFrame> frames = this.splitIfNecessary(frame);
        if (frames == null) {
            wt2.queueFrame(frame);
        } else {
            for (WebSocketFrame f2 : frames) {
                wt2.queueFrame(f2);
            }
        }
        return this;
    }

    private List<WebSocketFrame> splitIfNecessary(WebSocketFrame frame) {
        return WebSocketFrame.splitIfNecessary(frame, this.mMaxPayloadSize, this.mPerMessageCompressionExtension);
    }

    public WebSocket sendContinuation() {
        return this.sendFrame(WebSocketFrame.createContinuationFrame());
    }

    public WebSocket sendContinuation(boolean fin) {
        return this.sendFrame(WebSocketFrame.createContinuationFrame().setFin(fin));
    }

    public WebSocket sendContinuation(String payload) {
        return this.sendFrame(WebSocketFrame.createContinuationFrame(payload));
    }

    public WebSocket sendContinuation(String payload, boolean fin) {
        return this.sendFrame(WebSocketFrame.createContinuationFrame(payload).setFin(fin));
    }

    public WebSocket sendContinuation(byte[] payload) {
        return this.sendFrame(WebSocketFrame.createContinuationFrame(payload));
    }

    public WebSocket sendContinuation(byte[] payload, boolean fin) {
        return this.sendFrame(WebSocketFrame.createContinuationFrame(payload).setFin(fin));
    }

    public WebSocket sendText(String message) {
        return this.sendFrame(WebSocketFrame.createTextFrame(message));
    }

    public WebSocket sendText(String payload, boolean fin) {
        return this.sendFrame(WebSocketFrame.createTextFrame(payload).setFin(fin));
    }

    public WebSocket sendBinary(byte[] message) {
        return this.sendFrame(WebSocketFrame.createBinaryFrame(message));
    }

    public WebSocket sendBinary(byte[] payload, boolean fin) {
        return this.sendFrame(WebSocketFrame.createBinaryFrame(payload).setFin(fin));
    }

    public WebSocket sendClose() {
        return this.sendFrame(WebSocketFrame.createCloseFrame());
    }

    public WebSocket sendClose(int closeCode) {
        return this.sendFrame(WebSocketFrame.createCloseFrame(closeCode));
    }

    public WebSocket sendClose(int closeCode, String reason) {
        return this.sendFrame(WebSocketFrame.createCloseFrame(closeCode, reason));
    }

    public WebSocket sendPing() {
        return this.sendFrame(WebSocketFrame.createPingFrame());
    }

    public WebSocket sendPing(byte[] payload) {
        return this.sendFrame(WebSocketFrame.createPingFrame(payload));
    }

    public WebSocket sendPing(String payload) {
        return this.sendFrame(WebSocketFrame.createPingFrame(payload));
    }

    public WebSocket sendPong() {
        return this.sendFrame(WebSocketFrame.createPongFrame());
    }

    public WebSocket sendPong(byte[] payload) {
        return this.sendFrame(WebSocketFrame.createPongFrame(payload));
    }

    public WebSocket sendPong(String payload) {
        return this.sendFrame(WebSocketFrame.createPongFrame(payload));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void changeStateOnConnect() throws WebSocketException {
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            if (this.mStateManager.getState() != WebSocketState.CREATED) {
                throw new WebSocketException(WebSocketError.NOT_IN_CREATED_STATE, "The current state of the WebSocket is not CREATED.");
            }
            this.mStateManager.setState(WebSocketState.CONNECTING);
        }
        this.mListenerManager.callOnStateChanged(WebSocketState.CONNECTING);
    }

    private Map<String, List<String>> shakeHands(Socket socket) throws WebSocketException {
        WebSocketInputStream input = this.openInputStream(socket);
        WebSocketOutputStream output = this.openOutputStream(socket);
        String key = WebSocket.generateWebSocketKey();
        this.writeHandshake(output, key);
        Map<String, List<String>> headers = this.readHandshake(input, key);
        this.mInput = input;
        this.mOutput = output;
        return headers;
    }

    private WebSocketInputStream openInputStream(Socket socket) throws WebSocketException {
        try {
            return new WebSocketInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch (IOException e2) {
            throw new WebSocketException(WebSocketError.SOCKET_INPUT_STREAM_FAILURE, "Failed to get the input stream of the raw socket: " + e2.getMessage(), e2);
        }
    }

    private WebSocketOutputStream openOutputStream(Socket socket) throws WebSocketException {
        try {
            return new WebSocketOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        }
        catch (IOException e2) {
            throw new WebSocketException(WebSocketError.SOCKET_OUTPUT_STREAM_FAILURE, "Failed to get the output stream from the raw socket: " + e2.getMessage(), e2);
        }
    }

    private static String generateWebSocketKey() {
        byte[] data = new byte[16];
        Misc.nextBytes(data);
        return Base64.encode(data);
    }

    private void writeHandshake(WebSocketOutputStream output, String key) throws WebSocketException {
        this.mHandshakeBuilder.setKey(key);
        String requestLine = this.mHandshakeBuilder.buildRequestLine();
        List<String[]> headers = this.mHandshakeBuilder.buildHeaders();
        String handshake = HandshakeBuilder.build(requestLine, headers);
        this.mListenerManager.callOnSendingHandshake(requestLine, headers);
        try {
            output.write(handshake);
            output.flush();
        }
        catch (IOException e2) {
            throw new WebSocketException(WebSocketError.OPENING_HAHDSHAKE_REQUEST_FAILURE, "Failed to send an opening handshake request to the server: " + e2.getMessage(), e2);
        }
    }

    private Map<String, List<String>> readHandshake(WebSocketInputStream input, String key) throws WebSocketException {
        return new HandshakeReader(this).readHandshake(input, key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void startThreads() {
        ReadingThread readingThread = new ReadingThread(this);
        WritingThread writingThread = new WritingThread(this);
        Object object = this.mThreadsLock;
        synchronized (object) {
            this.mReadingThread = readingThread;
            this.mWritingThread = writingThread;
        }
        readingThread.callOnThreadCreated();
        writingThread.callOnThreadCreated();
        readingThread.start();
        writingThread.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void stopThreads(long closeDelay) {
        WritingThread writingThread;
        ReadingThread readingThread;
        Object object = this.mThreadsLock;
        synchronized (object) {
            readingThread = this.mReadingThread;
            writingThread = this.mWritingThread;
            this.mReadingThread = null;
            this.mWritingThread = null;
        }
        if (readingThread != null) {
            readingThread.requestStop(closeDelay);
        }
        if (writingThread != null) {
            writingThread.requestStop();
        }
    }

    WebSocketInputStream getInput() {
        return this.mInput;
    }

    WebSocketOutputStream getOutput() {
        return this.mOutput;
    }

    StateManager getStateManager() {
        return this.mStateManager;
    }

    ListenerManager getListenerManager() {
        return this.mListenerManager;
    }

    HandshakeBuilder getHandshakeBuilder() {
        return this.mHandshakeBuilder;
    }

    void setAgreedExtensions(List<WebSocketExtension> extensions) {
        this.mAgreedExtensions = extensions;
    }

    void setAgreedProtocol(String protocol) {
        this.mAgreedProtocol = protocol;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void onReadingThreadStarted() {
        boolean bothStarted = false;
        Object object = this.mThreadsLock;
        synchronized (object) {
            this.mReadingThreadStarted = true;
            if (this.mWritingThreadStarted) {
                bothStarted = true;
            }
        }
        this.callOnConnectedIfNotYet();
        if (bothStarted) {
            this.onThreadsStarted();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void onWritingThreadStarted() {
        boolean bothStarted = false;
        Object object = this.mThreadsLock;
        synchronized (object) {
            this.mWritingThreadStarted = true;
            if (this.mReadingThreadStarted) {
                bothStarted = true;
            }
        }
        this.callOnConnectedIfNotYet();
        if (bothStarted) {
            this.onThreadsStarted();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void callOnConnectedIfNotYet() {
        Object object = this.mOnConnectedCalledLock;
        synchronized (object) {
            if (this.mOnConnectedCalled) {
                return;
            }
            this.mOnConnectedCalled = true;
        }
        this.mListenerManager.callOnConnected(this.mServerHeaders);
    }

    private void onThreadsStarted() {
        this.mPingSender.start();
        this.mPongSender.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void onReadingThreadFinished(WebSocketFrame closeFrame) {
        Object object = this.mThreadsLock;
        synchronized (object) {
            this.mReadingThreadFinished = true;
            this.mServerCloseFrame = closeFrame;
            if (!this.mWritingThreadFinished) {
                return;
            }
        }
        this.onThreadsFinished();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void onWritingThreadFinished(WebSocketFrame closeFrame) {
        Object object = this.mThreadsLock;
        synchronized (object) {
            this.mWritingThreadFinished = true;
            this.mClientCloseFrame = closeFrame;
            if (!this.mReadingThreadFinished) {
                return;
            }
        }
        this.onThreadsFinished();
    }

    private void onThreadsFinished() {
        this.finish();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void finish() {
        this.mPingSender.stop();
        this.mPongSender.stop();
        Socket socket = this.mSocketConnector.getSocket();
        if (socket != null) {
            try {
                socket.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        StateManager stateManager = this.mStateManager;
        synchronized (stateManager) {
            this.mStateManager.setState(WebSocketState.CLOSED);
        }
        this.mListenerManager.callOnStateChanged(WebSocketState.CLOSED);
        this.mListenerManager.callOnDisconnected(this.mServerCloseFrame, this.mClientCloseFrame, this.mStateManager.getClosedByServer());
    }

    private void finishAsynchronously() {
        FinishThread thread = new FinishThread(this);
        thread.callOnThreadCreated();
        thread.start();
    }

    private PerMessageCompressionExtension findAgreedPerMessageCompressionExtension() {
        if (this.mAgreedExtensions == null) {
            return null;
        }
        for (WebSocketExtension extension : this.mAgreedExtensions) {
            if (!(extension instanceof PerMessageCompressionExtension)) continue;
            return (PerMessageCompressionExtension)extension;
        }
        return null;
    }

    PerMessageCompressionExtension getPerMessageCompressionExtension() {
        return this.mPerMessageCompressionExtension;
    }
}

