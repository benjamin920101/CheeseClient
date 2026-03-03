/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Address;
import com.neovisionaries.ws.client.DualStackMode;
import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.ProxyHandshaker;
import com.neovisionaries.ws.client.ProxySettings;
import com.neovisionaries.ws.client.SocketConnector;
import com.neovisionaries.ws.client.SocketFactorySettings;
import com.neovisionaries.ws.client.WebSocket;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class WebSocketFactory {
    private final SocketFactorySettings mSocketFactorySettings;
    private final ProxySettings mProxySettings;
    private int mConnectionTimeout;
    private int mSocketTimeout;
    private DualStackMode mDualStackMode = DualStackMode.BOTH;
    private int mDualStackFallbackDelay = 250;
    private boolean mVerifyHostname = true;
    private String[] mServerNames;

    public WebSocketFactory() {
        this.mSocketFactorySettings = new SocketFactorySettings();
        this.mProxySettings = new ProxySettings(this);
    }

    public WebSocketFactory(WebSocketFactory other) {
        if (other == null) {
            throw new IllegalArgumentException("The given WebSocketFactory is null");
        }
        this.mSocketFactorySettings = new SocketFactorySettings(other.mSocketFactorySettings);
        this.mProxySettings = new ProxySettings(this, other.mProxySettings);
        this.mConnectionTimeout = other.mConnectionTimeout;
        this.mSocketTimeout = other.mSocketTimeout;
        this.mDualStackMode = other.mDualStackMode;
        this.mDualStackFallbackDelay = other.mDualStackFallbackDelay;
        this.mVerifyHostname = other.mVerifyHostname;
        if (other.mServerNames != null) {
            this.mServerNames = new String[other.mServerNames.length];
            System.arraycopy(other.mServerNames, 0, this.mServerNames, 0, this.mServerNames.length);
        }
    }

    public SocketFactory getSocketFactory() {
        return this.mSocketFactorySettings.getSocketFactory();
    }

    public WebSocketFactory setSocketFactory(SocketFactory factory) {
        this.mSocketFactorySettings.setSocketFactory(factory);
        return this;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSocketFactorySettings.getSSLSocketFactory();
    }

    public WebSocketFactory setSSLSocketFactory(SSLSocketFactory factory) {
        this.mSocketFactorySettings.setSSLSocketFactory(factory);
        return this;
    }

    public SSLContext getSSLContext() {
        return this.mSocketFactorySettings.getSSLContext();
    }

    public WebSocketFactory setSSLContext(SSLContext context) {
        this.mSocketFactorySettings.setSSLContext(context);
        return this;
    }

    public ProxySettings getProxySettings() {
        return this.mProxySettings;
    }

    public int getConnectionTimeout() {
        return this.mConnectionTimeout;
    }

    public WebSocketFactory setConnectionTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value cannot be negative.");
        }
        this.mConnectionTimeout = timeout;
        return this;
    }

    public int getSocketTimeout() {
        return this.mSocketTimeout;
    }

    public WebSocketFactory setSocketTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value cannot be negative.");
        }
        this.mSocketTimeout = timeout;
        return this;
    }

    public DualStackMode getDualStackMode() {
        return this.mDualStackMode;
    }

    public WebSocketFactory setDualStackMode(DualStackMode mode) {
        this.mDualStackMode = mode;
        return this;
    }

    public int getDualStackFallbackDelay() {
        return this.mDualStackFallbackDelay;
    }

    public WebSocketFactory setDualStackFallbackDelay(int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay value cannot be negative.");
        }
        this.mDualStackFallbackDelay = delay;
        return this;
    }

    public boolean getVerifyHostname() {
        return this.mVerifyHostname;
    }

    public WebSocketFactory setVerifyHostname(boolean verifyHostname) {
        this.mVerifyHostname = verifyHostname;
        return this;
    }

    public String[] getServerNames() {
        return this.mServerNames;
    }

    public WebSocketFactory setServerNames(String[] serverNames) {
        this.mServerNames = serverNames;
        return this;
    }

    public WebSocketFactory setServerName(String serverName) {
        return this.setServerNames(new String[]{serverName});
    }

    public WebSocket createSocket(String uri) throws IOException {
        return this.createSocket(uri, this.getConnectionTimeout());
    }

    public WebSocket createSocket(String uri, int timeout) throws IOException {
        if (uri == null) {
            throw new IllegalArgumentException("The given URI is null.");
        }
        if (timeout < 0) {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
        return this.createSocket(URI.create(uri), timeout);
    }

    public WebSocket createSocket(URL url) throws IOException {
        return this.createSocket(url, this.getConnectionTimeout());
    }

    public WebSocket createSocket(URL url, int timeout) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("The given URL is null.");
        }
        if (timeout < 0) {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
        try {
            return this.createSocket(url.toURI(), timeout);
        }
        catch (URISyntaxException e2) {
            throw new IllegalArgumentException("Failed to convert the given URL into a URI.");
        }
    }

    public WebSocket createSocket(URI uri) throws IOException {
        return this.createSocket(uri, this.getConnectionTimeout());
    }

    public WebSocket createSocket(URI uri, int timeout) throws IOException {
        if (uri == null) {
            throw new IllegalArgumentException("The given URI is null.");
        }
        if (timeout < 0) {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
        String scheme = uri.getScheme();
        String userInfo = uri.getUserInfo();
        String host = Misc.extractHost(uri);
        int port = uri.getPort();
        String path = uri.getRawPath();
        String query = uri.getRawQuery();
        return this.createSocket(scheme, userInfo, host, port, path, query, timeout);
    }

    private WebSocket createSocket(String scheme, String userInfo, String host, int port, String path, String query, int timeout) throws IOException {
        boolean secure = WebSocketFactory.isSecureConnectionRequired(scheme);
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("The host part is empty.");
        }
        path = WebSocketFactory.determinePath(path);
        SocketConnector connector = this.createRawSocket(host, port, secure, timeout);
        return this.createWebSocket(secure, userInfo, host, port, path, query, connector);
    }

    private static boolean isSecureConnectionRequired(String scheme) {
        if (scheme == null || scheme.length() == 0) {
            throw new IllegalArgumentException("The scheme part is empty.");
        }
        if ("wss".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
            return true;
        }
        if ("ws".equalsIgnoreCase(scheme) || "http".equalsIgnoreCase(scheme)) {
            return false;
        }
        throw new IllegalArgumentException("Bad scheme: " + scheme);
    }

    private static String determinePath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }
        if (path.startsWith("/")) {
            return path;
        }
        return "/" + path;
    }

    private SocketConnector createRawSocket(String host, int port, boolean secure, int timeout) throws IOException {
        boolean proxied;
        port = WebSocketFactory.determinePort(port, secure);
        boolean bl2 = proxied = this.mProxySettings.getHost() != null;
        if (proxied) {
            return this.createProxiedRawSocket(host, port, secure, timeout);
        }
        return this.createDirectRawSocket(host, port, secure, timeout);
    }

    private SocketConnector createProxiedRawSocket(String host, int port, boolean secure, int timeout) {
        int proxyPort = WebSocketFactory.determinePort(this.mProxySettings.getPort(), this.mProxySettings.isSecure());
        SocketFactory factory = this.mProxySettings.selectSocketFactory();
        Address address = new Address(this.mProxySettings.getHost(), proxyPort);
        ProxyHandshaker handshaker = new ProxyHandshaker(host, port, this.mProxySettings);
        SSLSocketFactory sslSocketFactory = secure ? (SSLSocketFactory)this.mSocketFactorySettings.selectSocketFactory(secure) : null;
        return new SocketConnector(factory, address, timeout, this.mSocketTimeout, this.mProxySettings.getServerNames(), handshaker, sslSocketFactory, host, port).setDualStackSettings(this.mDualStackMode, this.mDualStackFallbackDelay).setVerifyHostname(this.mVerifyHostname);
    }

    private SocketConnector createDirectRawSocket(String host, int port, boolean secure, int timeout) {
        SocketFactory factory = this.mSocketFactorySettings.selectSocketFactory(secure);
        Address address = new Address(host, port);
        return new SocketConnector(factory, address, timeout, this.mServerNames, this.mSocketTimeout).setDualStackSettings(this.mDualStackMode, this.mDualStackFallbackDelay).setVerifyHostname(this.mVerifyHostname);
    }

    private static int determinePort(int port, boolean secure) {
        if (0 <= port) {
            return port;
        }
        if (secure) {
            return 443;
        }
        return 80;
    }

    private WebSocket createWebSocket(boolean secure, String userInfo, String host, int port, String path, String query, SocketConnector connector) {
        if (0 <= port) {
            host = host + ":" + port;
        }
        if (query != null) {
            path = path + "?" + query;
        }
        return new WebSocket(this, secure, userInfo, host, path, connector);
    }
}

