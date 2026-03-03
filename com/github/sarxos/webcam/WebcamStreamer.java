/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamListener;
import java.awt.image.BufferedImage;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamStreamer
implements ThreadFactory,
WebcamListener {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamStreamer.class);
    private static final String BOUNDARY = "mjpegframe";
    private static final String CRLF = "\r\n";
    private Webcam webcam = null;
    private double fps = 0.0;
    private int number = 0;
    private int port = 0;
    private long last = -1L;
    private long delay = -1L;
    private BufferedImage image = null;
    private ExecutorService executor = Executors.newCachedThreadPool(this);
    private AtomicBoolean started = new AtomicBoolean(false);

    public WebcamStreamer(int port, Webcam webcam, double fps, boolean start) {
        if (webcam == null) {
            throw new IllegalArgumentException("Webcam for streaming cannot be null");
        }
        this.port = port;
        this.webcam = webcam;
        this.fps = fps;
        this.delay = (long)(1000.0 / fps);
        if (start) {
            this.start();
        }
    }

    @Override
    public Thread newThread(Runnable r2) {
        Thread thread = new Thread(r2, String.format("streamer-thread-%s", this.number++));
        thread.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
        thread.setDaemon(true);
        return thread;
    }

    public void start() {
        if (this.started.compareAndSet(false, true)) {
            this.webcam.addWebcamListener(this);
            this.webcam.open();
            this.executor.execute(new Acceptor());
        }
    }

    public void stop() {
        if (this.started.compareAndSet(true, false)) {
            this.executor.shutdown();
            this.webcam.removeWebcamListener(this);
            this.webcam.close();
        }
    }

    @Override
    public void webcamOpen(WebcamEvent we2) {
        this.start();
    }

    @Override
    public void webcamClosed(WebcamEvent we2) {
        this.stop();
    }

    @Override
    public void webcamDisposed(WebcamEvent we2) {
    }

    @Override
    public void webcamImageObtained(WebcamEvent we2) {
    }

    public double getFPS() {
        return this.fps;
    }

    public boolean isInitialized() {
        return this.started.get();
    }

    public int getPort() {
        return this.port;
    }

    static /* synthetic */ Webcam access$400(WebcamStreamer x0) {
        return x0.webcam;
    }

    static /* synthetic */ long access$500(WebcamStreamer x0) {
        return x0.last;
    }

    static /* synthetic */ long access$600(WebcamStreamer x0) {
        return x0.delay;
    }

    static /* synthetic */ BufferedImage access$702(WebcamStreamer x0, BufferedImage x1) {
        x0.image = x1;
        return x0.image;
    }

    static /* synthetic */ BufferedImage access$700(WebcamStreamer x0) {
        return x0.image;
    }

    private class Connection
    implements Runnable {
        private Socket socket = null;

        public Connection(Socket socket) {
            this.socket = socket;
        }

        /*
         * Exception decompiling
         */
        @Override
        public void run() {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 48[DOLOOP]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }
    }

    private class Acceptor
    implements Runnable {
        private Acceptor() {
        }

        @Override
        public void run() {
            try {
                ServerSocket server = new ServerSocket(WebcamStreamer.this.port);
                while (WebcamStreamer.this.started.get()) {
                    Socket socket = server.accept();
                    LOG.info("New connection from {}", (Object)socket.getRemoteSocketAddress());
                    WebcamStreamer.this.executor.execute(new Connection(socket));
                }
            }
            catch (Exception e2) {
                LOG.error("Cannot accept socket connection", e2);
            }
        }
    }
}

