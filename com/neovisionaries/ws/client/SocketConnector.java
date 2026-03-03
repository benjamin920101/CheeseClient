/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Address;
import com.neovisionaries.ws.client.DualStackMode;
import com.neovisionaries.ws.client.HostnameUnverifiedException;
import com.neovisionaries.ws.client.OkHostnameVerifier;
import com.neovisionaries.ws.client.ProxyHandshaker;
import com.neovisionaries.ws.client.SocketInitiator;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class SocketConnector {
    private final SocketFactory mSocketFactory;
    private final Address mAddress;
    private final int mConnectionTimeout;
    private final int mSocketTimeout;
    private final String[] mServerNames;
    private final ProxyHandshaker mProxyHandshaker;
    private final SSLSocketFactory mSSLSocketFactory;
    private final String mHost;
    private final int mPort;
    private DualStackMode mDualStackMode = DualStackMode.BOTH;
    private int mDualStackFallbackDelay = 250;
    private boolean mVerifyHostname;
    private Socket mSocket;

    SocketConnector(SocketFactory socketFactory, Address address, int timeout, String[] serverNames, int socketTimeout) {
        this(socketFactory, address, timeout, socketTimeout, serverNames, null, null, null, 0);
    }

    SocketConnector(SocketFactory socketFactory, Address address, int timeout, int socketTimeout, String[] serverNames, ProxyHandshaker handshaker, SSLSocketFactory sslSocketFactory, String host, int port) {
        this.mSocketFactory = socketFactory;
        this.mAddress = address;
        this.mConnectionTimeout = timeout;
        this.mSocketTimeout = socketTimeout;
        this.mServerNames = serverNames;
        this.mProxyHandshaker = handshaker;
        this.mSSLSocketFactory = sslSocketFactory;
        this.mHost = host;
        this.mPort = port;
    }

    public int getConnectionTimeout() {
        return this.mConnectionTimeout;
    }

    public Socket getSocket() {
        return this.mSocket;
    }

    public Socket getConnectedSocket() throws WebSocketException {
        if (this.mSocket == null) {
            this.connectSocket();
        }
        return this.mSocket;
    }

    private void connectSocket() throws WebSocketException {
        SocketInitiator socketInitiator = new SocketInitiator(this.mSocketFactory, this.mAddress, this.mConnectionTimeout, this.mServerNames, this.mDualStackMode, this.mDualStackFallbackDelay);
        InetAddress[] addresses = this.resolveHostname();
        try {
            this.mSocket = socketInitiator.establish(addresses);
        }
        catch (Exception e2) {
            boolean proxied = this.mProxyHandshaker != null;
            String message = String.format("Failed to connect to %s'%s': %s", proxied ? "the proxy " : "", this.mAddress, e2.getMessage());
            throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, message, e2);
        }
    }

    private InetAddress[] resolveHostname() throws WebSocketException {
        InetAddress[] addresses = null;
        UnknownHostException exception = null;
        try {
            addresses = InetAddress.getAllByName(this.mAddress.getHostname());
            Arrays.sort(addresses, new Comparator<InetAddress>(){

                @Override
                public int compare(InetAddress left, InetAddress right) {
                    if (left.getClass() == right.getClass()) {
                        return 0;
                    }
                    if (left instanceof Inet6Address) {
                        return -1;
                    }
                    return 1;
                }
            });
        }
        catch (UnknownHostException e2) {
            exception = e2;
        }
        if (addresses != null && addresses.length > 0) {
            return addresses;
        }
        if (exception == null) {
            exception = new UnknownHostException("No IP addresses found");
        }
        String message = String.format("Failed to resolve hostname %s: %s", this.mAddress, exception.getMessage());
        throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, message, exception);
    }

    public Socket connect() throws WebSocketException {
        try {
            this.doConnect();
            assert (this.mSocket != null);
            return this.mSocket;
        }
        catch (WebSocketException e2) {
            if (this.mSocket != null) {
                try {
                    this.mSocket.close();
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
            throw e2;
        }
    }

    SocketConnector setDualStackSettings(DualStackMode mode, int fallbackDelay) {
        this.mDualStackMode = mode;
        this.mDualStackFallbackDelay = fallbackDelay;
        return this;
    }

    SocketConnector setVerifyHostname(boolean verifyHostname) {
        this.mVerifyHostname = verifyHostname;
        return this;
    }

    private void doConnect() throws WebSocketException {
        boolean proxied = this.mProxyHandshaker != null;
        this.connectSocket();
        assert (this.mSocket != null);
        if (this.mSocketTimeout > 0) {
            this.setSoTimeout(this.mSocketTimeout);
        }
        if (this.mSocket instanceof SSLSocket) {
            this.verifyHostname((SSLSocket)this.mSocket, this.mAddress.getHostname());
        }
        if (proxied) {
            this.handshake();
        }
    }

    private void setSoTimeout(int timeout) throws WebSocketException {
        assert (this.mSocket != null);
        try {
            this.mSocket.setSoTimeout(timeout);
        }
        catch (SocketException e2) {
            String message = String.format("Failed to set SO_TIMEOUT: %s", e2.getMessage());
            throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, message, e2);
        }
    }

    private void verifyHostname(SSLSocket socket, String hostname) throws HostnameUnverifiedException {
        if (!this.mVerifyHostname) {
            return;
        }
        OkHostnameVerifier verifier = OkHostnameVerifier.INSTANCE;
        SSLSession session = socket.getSession();
        if (verifier.verify(hostname, session)) {
            return;
        }
        throw new HostnameUnverifiedException(socket, hostname);
    }

    private void handshake() throws WebSocketException {
        assert (this.mSocket != null);
        try {
            this.mProxyHandshaker.perform(this.mSocket);
        }
        catch (IOException e2) {
            String message = String.format("Handshake with the proxy server (%s) failed: %s", this.mAddress, e2.getMessage());
            throw new WebSocketException(WebSocketError.PROXY_HANDSHAKE_ERROR, message, e2);
        }
        if (this.mSSLSocketFactory == null) {
            return;
        }
        try {
            this.mSocket = this.mSSLSocketFactory.createSocket(this.mSocket, this.mHost, this.mPort, true);
        }
        catch (IOException e3) {
            String message = "Failed to overlay an existing socket: " + e3.getMessage();
            throw new WebSocketException(WebSocketError.SOCKET_OVERLAY_ERROR, message, e3);
        }
        try {
            ((SSLSocket)this.mSocket).startHandshake();
            this.verifyHostname((SSLSocket)this.mSocket, this.mProxyHandshaker.getProxiedHostname());
        }
        catch (IOException e4) {
            String message = String.format("SSL handshake with the WebSocket endpoint (%s) failed: %s", this.mAddress, e4.getMessage());
            throw new WebSocketException(WebSocketError.SSL_HANDSHAKE_ERROR, message, e4);
        }
    }

    void closeSilently() {
        if (this.mSocket != null) {
            try {
                this.mSocket.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }
}

