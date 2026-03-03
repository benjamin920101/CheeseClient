/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.SocketFactorySettings;
import com.neovisionaries.ws.client.WebSocketFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ProxySettings {
    private final WebSocketFactory mWebSocketFactory;
    private final Map<String, List<String>> mHeaders;
    private final SocketFactorySettings mSocketFactorySettings;
    private boolean mSecure;
    private String mHost;
    private int mPort;
    private String mId;
    private String mPassword;
    private String[] mServerNames;

    ProxySettings(WebSocketFactory factory) {
        this.mWebSocketFactory = factory;
        this.mHeaders = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
        this.mSocketFactorySettings = new SocketFactorySettings();
        this.reset();
    }

    ProxySettings(WebSocketFactory factory, ProxySettings settings) {
        this(factory);
        this.mHeaders.putAll(settings.mHeaders);
        this.mSecure = settings.mSecure;
        this.mHost = settings.mHost;
        this.mPort = settings.mPort;
        this.mId = settings.mId;
        this.mPassword = settings.mPassword;
        if (settings.mServerNames != null) {
            this.mServerNames = new String[settings.mServerNames.length];
            System.arraycopy(settings.mServerNames, 0, this.mServerNames, 0, this.mServerNames.length);
        }
    }

    public WebSocketFactory getWebSocketFactory() {
        return this.mWebSocketFactory;
    }

    public ProxySettings reset() {
        this.mSecure = false;
        this.mHost = null;
        this.mPort = -1;
        this.mId = null;
        this.mPassword = null;
        this.mHeaders.clear();
        this.mServerNames = null;
        return this;
    }

    public boolean isSecure() {
        return this.mSecure;
    }

    public ProxySettings setSecure(boolean secure) {
        this.mSecure = secure;
        return this;
    }

    public String getHost() {
        return this.mHost;
    }

    public ProxySettings setHost(String host) {
        this.mHost = host;
        return this;
    }

    public int getPort() {
        return this.mPort;
    }

    public ProxySettings setPort(int port) {
        this.mPort = port;
        return this;
    }

    public String getId() {
        return this.mId;
    }

    public ProxySettings setId(String id2) {
        this.mId = id2;
        return this;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public ProxySettings setPassword(String password) {
        this.mPassword = password;
        return this;
    }

    public ProxySettings setCredentials(String id2, String password) {
        return this.setId(id2).setPassword(password);
    }

    public ProxySettings setServer(String uri) {
        if (uri == null) {
            return this;
        }
        return this.setServer(URI.create(uri));
    }

    public ProxySettings setServer(URL url) {
        if (url == null) {
            return this;
        }
        try {
            return this.setServer(url.toURI());
        }
        catch (URISyntaxException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public ProxySettings setServer(URI uri) {
        if (uri == null) {
            return this;
        }
        String scheme = uri.getScheme();
        String userInfo = uri.getUserInfo();
        String host = uri.getHost();
        int port = uri.getPort();
        return this.setServer(scheme, userInfo, host, port);
    }

    private ProxySettings setServer(String scheme, String userInfo, String host, int port) {
        this.setByScheme(scheme);
        this.setByUserInfo(userInfo);
        this.mHost = host;
        this.mPort = port;
        return this;
    }

    private void setByScheme(String scheme) {
        if ("http".equalsIgnoreCase(scheme)) {
            this.mSecure = false;
        } else if ("https".equalsIgnoreCase(scheme)) {
            this.mSecure = true;
        }
    }

    private void setByUserInfo(String userInfo) {
        String pw2;
        String id2;
        if (userInfo == null) {
            return;
        }
        String[] pair = userInfo.split(":", 2);
        switch (pair.length) {
            case 2: {
                id2 = pair[0];
                pw2 = pair[1];
                break;
            }
            case 1: {
                id2 = pair[0];
                pw2 = null;
                break;
            }
            default: {
                return;
            }
        }
        if (id2.length() == 0) {
            return;
        }
        this.mId = id2;
        this.mPassword = pw2;
    }

    public Map<String, List<String>> getHeaders() {
        return this.mHeaders;
    }

    public ProxySettings addHeader(String name, String value) {
        if (name == null || name.length() == 0) {
            return this;
        }
        List<String> list = this.mHeaders.get(name);
        if (list == null) {
            list = new ArrayList<String>();
            this.mHeaders.put(name, list);
        }
        list.add(value);
        return this;
    }

    public SocketFactory getSocketFactory() {
        return this.mSocketFactorySettings.getSocketFactory();
    }

    public ProxySettings setSocketFactory(SocketFactory factory) {
        this.mSocketFactorySettings.setSocketFactory(factory);
        return this;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSocketFactorySettings.getSSLSocketFactory();
    }

    public ProxySettings setSSLSocketFactory(SSLSocketFactory factory) {
        this.mSocketFactorySettings.setSSLSocketFactory(factory);
        return this;
    }

    public SSLContext getSSLContext() {
        return this.mSocketFactorySettings.getSSLContext();
    }

    public ProxySettings setSSLContext(SSLContext context) {
        this.mSocketFactorySettings.setSSLContext(context);
        return this;
    }

    SocketFactory selectSocketFactory() {
        return this.mSocketFactorySettings.selectSocketFactory(this.mSecure);
    }

    public String[] getServerNames() {
        return this.mServerNames;
    }

    public ProxySettings setServerNames(String[] serverNames) {
        this.mServerNames = serverNames;
        return this;
    }

    public ProxySettings setServerName(String serverName) {
        return this.setServerNames(new String[]{serverName});
    }
}

