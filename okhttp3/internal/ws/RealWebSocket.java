/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.WebSocketProtocol;
import okhttp3.internal.ws.WebSocketReader;
import okhttp3.internal.ws.WebSocketWriter;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class RealWebSocket
implements WebSocket,
WebSocketReader.FrameCallback {
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private static final long MAX_QUEUE_SIZE = 0x1000000L;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L;
    private final Request originalRequest;
    final WebSocketListener listener;
    private final Random random;
    private final long pingIntervalMillis;
    private final String key;
    private Call call;
    private final Runnable writerRunnable;
    private WebSocketReader reader;
    private WebSocketWriter writer;
    private ScheduledExecutorService executor;
    private Streams streams;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque();
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque();
    private long queueSize;
    private boolean enqueuedClose;
    private ScheduledFuture<?> cancelFuture;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private boolean failed;
    private int sentPingCount;
    private int receivedPingCount;
    private int receivedPongCount;
    private boolean awaitingPong;

    public RealWebSocket(Request request, WebSocketListener listener, Random random, long pingIntervalMillis) {
        if (!"GET".equals(request.method())) {
            throw new IllegalArgumentException("Request must be GET: " + request.method());
        }
        this.originalRequest = request;
        this.listener = listener;
        this.random = random;
        this.pingIntervalMillis = pingIntervalMillis;
        byte[] nonce = new byte[16];
        random.nextBytes(nonce);
        this.key = ByteString.of(nonce).base64();
        this.writerRunnable = () -> {
            try {
                while (this.writeOneFrame()) {
                }
            }
            catch (IOException e2) {
                this.failWebSocket(e2, null);
            }
        };
    }

    @Override
    public Request request() {
        return this.originalRequest;
    }

    @Override
    public synchronized long queueSize() {
        return this.queueSize;
    }

    @Override
    public void cancel() {
        this.call.cancel();
    }

    public void connect(OkHttpClient client) {
        client = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request request = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = Internal.instance.newWebSocketCall(client, request);
        this.call.timeout().clearTimeout();
        this.call.enqueue(new Callback(){

            @Override
            public void onResponse(Call call, Response response) {
                StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                try {
                    RealWebSocket.this.checkResponse(response);
                }
                catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly(response);
                    streamAllocation.streamFailed(e2);
                    return;
                }
                streamAllocation.noNewStreams();
                Streams streams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                try {
                    String name = "OkHttp WebSocket " + request.url().redact();
                    RealWebSocket.this.initReaderAndWriter(name, streams);
                    RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                    streamAllocation.connection().socket().setSoTimeout(0);
                    RealWebSocket.this.loopReader();
                }
                catch (Exception e3) {
                    RealWebSocket.this.failWebSocket(e3, null);
                }
            }

            @Override
            public void onFailure(Call call, IOException e2) {
                RealWebSocket.this.failWebSocket(e2, null);
            }
        });
    }

    void checkResponse(Response response) throws ProtocolException {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
        }
        String headerConnection = response.header("Connection");
        if (!"Upgrade".equalsIgnoreCase(headerConnection)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + "'");
        }
        String headerUpgrade = response.header("Upgrade");
        if (!"websocket".equalsIgnoreCase(headerUpgrade)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + "'");
        }
        String headerAccept = response.header("Sec-WebSocket-Accept");
        String acceptExpected = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if (!acceptExpected.equals(headerAccept)) {
            throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + "'");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void initReaderAndWriter(String name, Streams streams) throws IOException {
        RealWebSocket realWebSocket = this;
        synchronized (realWebSocket) {
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.client, streams.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(name, false));
            if (this.pingIntervalMillis != 0L) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                this.runWriter();
            }
        }
        this.reader = new WebSocketReader(streams.client, streams.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            return this.receivedCloseCode == -1;
        }
        catch (Exception e2) {
            this.failWebSocket(e2, null);
            return false;
        }
    }

    void awaitTermination(int timeout, TimeUnit timeUnit) throws InterruptedException {
        this.executor.awaitTermination(timeout, timeUnit);
    }

    void tearDown() throws InterruptedException {
        if (this.cancelFuture != null) {
            this.cancelFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10L, TimeUnit.SECONDS);
    }

    synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    @Override
    public void onReadMessage(String text) throws IOException {
        this.listener.onMessage((WebSocket)this, text);
    }

    @Override
    public void onReadMessage(ByteString bytes) throws IOException {
        this.listener.onMessage((WebSocket)this, bytes);
    }

    @Override
    public synchronized void onReadPing(ByteString payload) {
        if (this.failed || this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
            return;
        }
        this.pongQueue.add(payload);
        this.runWriter();
        ++this.receivedPingCount;
    }

    @Override
    public synchronized void onReadPong(ByteString buffer) {
        ++this.receivedPongCount;
        this.awaitingPong = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onReadClose(int code, String reason) {
        if (code == -1) {
            throw new IllegalArgumentException();
        }
        Streams toClose = null;
        RealWebSocket realWebSocket = this;
        synchronized (realWebSocket) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = code;
            this.receivedCloseReason = reason;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                toClose = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                this.executor.shutdown();
            }
        }
        try {
            this.listener.onClosing(this, code, reason);
            if (toClose != null) {
                this.listener.onClosed(this, code, reason);
            }
        }
        finally {
            Util.closeQuietly(toClose);
        }
    }

    @Override
    public boolean send(String text) {
        if (text == null) {
            throw new NullPointerException("text == null");
        }
        return this.send(ByteString.encodeUtf8(text), 1);
    }

    @Override
    public boolean send(ByteString bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes == null");
        }
        return this.send(bytes, 2);
    }

    private synchronized boolean send(ByteString data, int formatOpcode) {
        if (this.failed || this.enqueuedClose) {
            return false;
        }
        if (this.queueSize + (long)data.size() > 0x1000000L) {
            this.close(1001, null);
            return false;
        }
        this.queueSize += (long)data.size();
        this.messageAndCloseQueue.add(new Message(formatOpcode, data));
        this.runWriter();
        return true;
    }

    synchronized boolean pong(ByteString payload) {
        if (this.failed || this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
            return false;
        }
        this.pongQueue.add(payload);
        this.runWriter();
        return true;
    }

    @Override
    public boolean close(int code, String reason) {
        return this.close(code, reason, 60000L);
    }

    synchronized boolean close(int code, String reason, long cancelAfterCloseMillis) {
        WebSocketProtocol.validateCloseCode(code);
        ByteString reasonBytes = null;
        if (reason != null && (long)(reasonBytes = ByteString.encodeUtf8(reason)).size() > 123L) {
            throw new IllegalArgumentException("reason.size() > 123: " + reason);
        }
        if (this.failed || this.enqueuedClose) {
            return false;
        }
        this.enqueuedClose = true;
        this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis));
        this.runWriter();
        return true;
    }

    private void runWriter() {
        assert (Thread.holdsLock(this));
        if (this.executor != null) {
            this.executor.execute(this.writerRunnable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    boolean writeOneFrame() throws IOException {
        ByteString pong;
        WebSocketWriter writer;
        Object messageOrClose = null;
        int receivedCloseCode = -1;
        String receivedCloseReason = null;
        Streams streamsToClose = null;
        RealWebSocket realWebSocket = this;
        synchronized (realWebSocket) {
            if (this.failed) {
                return false;
            }
            writer = this.writer;
            pong = this.pongQueue.poll();
            if (pong == null) {
                messageOrClose = this.messageAndCloseQueue.poll();
                if (messageOrClose instanceof Close) {
                    receivedCloseCode = this.receivedCloseCode;
                    receivedCloseReason = this.receivedCloseReason;
                    if (receivedCloseCode != -1) {
                        streamsToClose = this.streams;
                        this.streams = null;
                        this.executor.shutdown();
                    } else {
                        this.cancelFuture = this.executor.schedule(new CancelRunnable(), ((Close)messageOrClose).cancelAfterCloseMillis, TimeUnit.MILLISECONDS);
                    }
                } else if (messageOrClose == null) {
                    return false;
                }
            }
        }
        try {
            if (pong != null) {
                writer.writePong(pong);
            } else if (messageOrClose instanceof Message) {
                ByteString data = ((Message)messageOrClose).data;
                BufferedSink sink = Okio.buffer(writer.newMessageSink(((Message)messageOrClose).formatOpcode, data.size()));
                sink.write(data);
                sink.close();
                RealWebSocket realWebSocket2 = this;
                synchronized (realWebSocket2) {
                    this.queueSize -= (long)data.size();
                }
            } else if (messageOrClose instanceof Close) {
                Close close = (Close)messageOrClose;
                writer.writeClose(close.code, close.reason);
                if (streamsToClose != null) {
                    this.listener.onClosed(this, receivedCloseCode, receivedCloseReason);
                }
            } else {
                throw new AssertionError();
            }
            boolean bl2 = true;
            return bl2;
        }
        finally {
            Util.closeQuietly(streamsToClose);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void writePingFrame() {
        int failedPing;
        WebSocketWriter writer;
        RealWebSocket realWebSocket = this;
        synchronized (realWebSocket) {
            if (this.failed) {
                return;
            }
            writer = this.writer;
            failedPing = this.awaitingPong ? this.sentPingCount : -1;
            ++this.sentPingCount;
            this.awaitingPong = true;
        }
        if (failedPing != -1) {
            this.failWebSocket(new SocketTimeoutException("sent ping but didn't receive pong within " + this.pingIntervalMillis + "ms (after " + (failedPing - 1) + " successful ping/pongs)"), null);
            return;
        }
        try {
            writer.writePing(ByteString.EMPTY);
        }
        catch (IOException e2) {
            this.failWebSocket(e2, null);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void failWebSocket(Exception e2, @Nullable Response response) {
        Streams streamsToClose;
        RealWebSocket realWebSocket = this;
        synchronized (realWebSocket) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            streamsToClose = this.streams;
            this.streams = null;
            if (this.cancelFuture != null) {
                this.cancelFuture.cancel(false);
            }
            if (this.executor != null) {
                this.executor.shutdown();
            }
        }
        try {
            this.listener.onFailure(this, e2, response);
        }
        finally {
            Util.closeQuietly(streamsToClose);
        }
    }

    final class CancelRunnable
    implements Runnable {
        CancelRunnable() {
        }

        @Override
        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    public static abstract class Streams
    implements Closeable {
        public final boolean client;
        public final BufferedSource source;
        public final BufferedSink sink;

        public Streams(boolean client, BufferedSource source, BufferedSink sink) {
            this.client = client;
            this.source = source;
            this.sink = sink;
        }
    }

    static final class Close {
        final int code;
        final ByteString reason;
        final long cancelAfterCloseMillis;

        Close(int code, ByteString reason, long cancelAfterCloseMillis) {
            this.code = code;
            this.reason = reason;
            this.cancelAfterCloseMillis = cancelAfterCloseMillis;
        }
    }

    static final class Message {
        final int formatOpcode;
        final ByteString data;

        Message(int formatOpcode, ByteString data) {
            this.formatOpcode = formatOpcode;
            this.data = data;
        }
    }

    private final class PingRunnable
    implements Runnable {
        PingRunnable() {
        }

        @Override
        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }
}

