/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.RealBufferedSink;
import okio.RealBufferedSource;
import okio.Segment;
import okio.SegmentPool;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.Util;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class Okio {
    static final Logger logger = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    public static BufferedSource buffer(Source source) {
        return new RealBufferedSource(source);
    }

    public static BufferedSink buffer(Sink sink) {
        return new RealBufferedSink(sink);
    }

    public static Sink sink(OutputStream out) {
        return Okio.sink(out, new Timeout());
    }

    private static Sink sink(final OutputStream out, final Timeout timeout) {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (timeout == null) {
            throw new IllegalArgumentException("timeout == null");
        }
        return new Sink(){

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                Util.checkOffsetAndCount(source.size, 0L, byteCount);
                while (byteCount > 0L) {
                    timeout.throwIfReached();
                    Segment head = source.head;
                    int toCopy = (int)Math.min(byteCount, (long)(head.limit - head.pos));
                    out.write(head.data, head.pos, toCopy);
                    head.pos += toCopy;
                    byteCount -= (long)toCopy;
                    source.size -= (long)toCopy;
                    if (head.pos != head.limit) continue;
                    source.head = head.pop();
                    SegmentPool.recycle(head);
                }
            }

            @Override
            public void flush() throws IOException {
                out.flush();
            }

            @Override
            public void close() throws IOException {
                out.close();
            }

            @Override
            public Timeout timeout() {
                return timeout;
            }

            public String toString() {
                return "sink(" + out + ")";
            }
        };
    }

    public static Sink sink(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        AsyncTimeout timeout = Okio.timeout(socket);
        Sink sink = Okio.sink(socket.getOutputStream(), timeout);
        return timeout.sink(sink);
    }

    public static Source source(InputStream in2) {
        return Okio.source(in2, new Timeout());
    }

    private static Source source(final InputStream in2, final Timeout timeout) {
        if (in2 == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout == null) {
            throw new IllegalArgumentException("timeout == null");
        }
        return new Source(){

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                if (byteCount < 0L) {
                    throw new IllegalArgumentException("byteCount < 0: " + byteCount);
                }
                if (byteCount == 0L) {
                    return 0L;
                }
                try {
                    timeout.throwIfReached();
                    Segment tail = sink.writableSegment(1);
                    int maxToCopy = (int)Math.min(byteCount, (long)(8192 - tail.limit));
                    int bytesRead = in2.read(tail.data, tail.limit, maxToCopy);
                    if (bytesRead == -1) {
                        return -1L;
                    }
                    tail.limit += bytesRead;
                    sink.size += (long)bytesRead;
                    return bytesRead;
                }
                catch (AssertionError e2) {
                    if (Okio.isAndroidGetsocknameError(e2)) {
                        throw new IOException((Throwable)((Object)e2));
                    }
                    throw e2;
                }
            }

            @Override
            public void close() throws IOException {
                in2.close();
            }

            @Override
            public Timeout timeout() {
                return timeout;
            }

            public String toString() {
                return "source(" + in2 + ")";
            }
        };
    }

    public static Source source(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        return Okio.source(new FileInputStream(file));
    }

    @IgnoreJRERequirement
    public static Source source(Path path, OpenOption ... options) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
        return Okio.source(Files.newInputStream(path, options));
    }

    public static Sink sink(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        return Okio.sink(new FileOutputStream(file));
    }

    public static Sink appendingSink(File file) throws FileNotFoundException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        return Okio.sink(new FileOutputStream(file, true));
    }

    @IgnoreJRERequirement
    public static Sink sink(Path path, OpenOption ... options) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
        return Okio.sink(Files.newOutputStream(path, options));
    }

    public static Sink blackhole() {
        return new Sink(){

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                source.skip(byteCount);
            }

            @Override
            public void flush() throws IOException {
            }

            @Override
            public Timeout timeout() {
                return Timeout.NONE;
            }

            @Override
            public void close() throws IOException {
            }
        };
    }

    public static Source source(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        AsyncTimeout timeout = Okio.timeout(socket);
        Source source = Okio.source(socket.getInputStream(), timeout);
        return timeout.source(source);
    }

    private static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout(){

            @Override
            protected IOException newTimeoutException(@Nullable IOException cause) {
                SocketTimeoutException ioe = new SocketTimeoutException("timeout");
                if (cause != null) {
                    ioe.initCause(cause);
                }
                return ioe;
            }

            @Override
            protected void timedOut() {
                try {
                    socket.close();
                }
                catch (Exception e2) {
                    logger.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                }
                catch (AssertionError e3) {
                    if (Okio.isAndroidGetsocknameError(e3)) {
                        logger.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable)((Object)e3));
                    }
                    throw e3;
                }
            }
        };
    }

    static boolean isAndroidGetsocknameError(AssertionError e2) {
        return ((Throwable)((Object)e2)).getCause() != null && ((Throwable)((Object)e2)).getMessage() != null && ((Throwable)((Object)e2)).getMessage().contains("getsockname failed");
    }
}

