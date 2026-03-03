/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Address;
import com.neovisionaries.ws.client.DualStackMode;
import com.neovisionaries.ws.client.SNIHelper;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;

public class SocketInitiator {
    private final SocketFactory mSocketFactory;
    private final Address mAddress;
    private final int mConnectTimeout;
    private final String[] mServerNames;
    private final DualStackMode mMode;
    private final int mFallbackDelay;

    public SocketInitiator(SocketFactory socketFactory, Address address, int connectTimeout, String[] serverNames, DualStackMode mode, int fallbackDelay) {
        this.mSocketFactory = socketFactory;
        this.mAddress = address;
        this.mConnectTimeout = connectTimeout;
        this.mServerNames = serverNames;
        this.mMode = mode;
        this.mFallbackDelay = fallbackDelay;
    }

    public Socket establish(InetAddress[] addresses) throws Exception {
        SocketFuture future = new SocketFuture();
        ArrayList<SocketRacer> racers = new ArrayList<SocketRacer>(addresses.length);
        int delay = 0;
        Signal startSignal = null;
        for (InetAddress address : addresses) {
            if (this.mMode == DualStackMode.IPV4_ONLY && !(address instanceof Inet4Address) || this.mMode == DualStackMode.IPV6_ONLY && !(address instanceof Inet6Address)) continue;
            Signal doneSignal = new Signal(delay += this.mFallbackDelay);
            InetSocketAddress socketAddress = new InetSocketAddress(address, this.mAddress.getPort());
            SocketRacer racer = new SocketRacer(future, this.mSocketFactory, socketAddress, this.mServerNames, this.mConnectTimeout, startSignal, doneSignal);
            racers.add(racer);
            startSignal = doneSignal;
        }
        return future.await(racers);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class SocketFuture {
        private CountDownLatch mLatch;
        private List<SocketRacer> mRacers;
        private Socket mSocket;
        private Exception mException;

        private SocketFuture() {
        }

        synchronized boolean hasSocket() {
            return this.mSocket != null;
        }

        synchronized void setSocket(SocketRacer current, Socket socket) {
            if (this.mLatch == null || this.mRacers == null) {
                throw new IllegalStateException("Cannot set socket before awaiting!");
            }
            if (this.mSocket == null) {
                this.mSocket = socket;
                for (SocketRacer racer : this.mRacers) {
                    if (racer == current) continue;
                    racer.abort(new InterruptedException());
                    racer.interrupt();
                }
            } else {
                try {
                    socket.close();
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
            this.mLatch.countDown();
        }

        synchronized void setException(Exception exception) {
            if (this.mLatch == null || this.mRacers == null) {
                throw new IllegalStateException("Cannot set exception before awaiting!");
            }
            if (this.mException == null) {
                this.mException = exception;
            }
            this.mLatch.countDown();
        }

        Socket await(List<SocketRacer> racers) throws Exception {
            this.mRacers = racers;
            this.mLatch = new CountDownLatch(this.mRacers.size());
            for (SocketRacer racer : this.mRacers) {
                racer.start();
            }
            this.mLatch.await();
            if (this.mSocket != null) {
                return this.mSocket;
            }
            if (this.mException != null) {
                throw this.mException;
            }
            throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, "No viable interface to connect");
        }
    }

    private class SocketRacer
    extends Thread {
        private final SocketFuture mFuture;
        private final SocketFactory mSocketFactory;
        private final SocketAddress mSocketAddress;
        private String[] mServerNames;
        private final int mConnectTimeout;
        private final Signal mStartSignal;
        private final Signal mDoneSignal;

        SocketRacer(SocketFuture future, SocketFactory socketFactory, SocketAddress socketAddress, String[] serverNames, int connectTimeout, Signal startSignal, Signal doneSignal) {
            this.mFuture = future;
            this.mSocketFactory = socketFactory;
            this.mSocketAddress = socketAddress;
            this.mServerNames = serverNames;
            this.mConnectTimeout = connectTimeout;
            this.mStartSignal = startSignal;
            this.mDoneSignal = doneSignal;
        }

        public void run() {
            block6: {
                Socket socket = null;
                try {
                    if (this.mStartSignal != null) {
                        this.mStartSignal.await();
                    }
                    if (this.mFuture.hasSocket()) {
                        return;
                    }
                    socket = this.mSocketFactory.createSocket();
                    SNIHelper.setServerNames(socket, this.mServerNames);
                    socket.connect(this.mSocketAddress, this.mConnectTimeout);
                    this.complete(socket);
                }
                catch (Exception e2) {
                    this.abort(e2);
                    if (socket == null) break block6;
                    try {
                        socket.close();
                    }
                    catch (IOException iOException) {
                        // empty catch block
                    }
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void complete(Socket socket) {
            SocketFuture socketFuture = this.mFuture;
            synchronized (socketFuture) {
                if (this.mDoneSignal.isDone()) {
                    return;
                }
                this.mFuture.setSocket(this, socket);
                this.mDoneSignal.done();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void abort(Exception exception) {
            SocketFuture socketFuture = this.mFuture;
            synchronized (socketFuture) {
                if (this.mDoneSignal.isDone()) {
                    return;
                }
                this.mFuture.setException(exception);
                this.mDoneSignal.done();
            }
        }
    }

    private class Signal {
        private final CountDownLatch mLatch = new CountDownLatch(1);
        private final int mMaxDelay;

        Signal(int maxDelay) {
            this.mMaxDelay = maxDelay;
        }

        boolean isDone() {
            return this.mLatch.getCount() == 0L;
        }

        void await() throws InterruptedException {
            this.mLatch.await(this.mMaxDelay, TimeUnit.MILLISECONDS);
        }

        void done() {
            this.mLatch.countDown();
        }
    }
}

