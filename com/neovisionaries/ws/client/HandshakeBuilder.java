/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Base64;
import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.Token;
import com.neovisionaries.ws.client.WebSocketExtension;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class HandshakeBuilder {
    private static final String[] CONNECTION_HEADER = new String[]{"Connection", "Upgrade"};
    private static final String[] UPGRADE_HEADER = new String[]{"Upgrade", "websocket"};
    private static final String[] VERSION_HEADER = new String[]{"Sec-WebSocket-Version", "13"};
    private static final String RN = "\r\n";
    private boolean mSecure;
    private String mUserInfo;
    private final String mHost;
    private final String mPath;
    private final URI mUri;
    private String mKey;
    private Set<String> mProtocols;
    private List<WebSocketExtension> mExtensions;
    private List<String[]> mHeaders;

    public HandshakeBuilder(boolean secure, String userInfo, String host, String path) {
        this.mSecure = secure;
        this.mUserInfo = userInfo;
        this.mHost = host;
        this.mPath = path;
        this.mUri = URI.create(String.format("%s://%s%s", secure ? "wss" : "ws", host, path));
    }

    public HandshakeBuilder(HandshakeBuilder source) {
        this.mSecure = source.mSecure;
        this.mUserInfo = source.mUserInfo;
        this.mHost = source.mHost;
        this.mPath = source.mPath;
        this.mUri = source.mUri;
        this.mKey = source.mKey;
        this.mProtocols = HandshakeBuilder.copyProtocols(source.mProtocols);
        this.mExtensions = HandshakeBuilder.copyExtensions(source.mExtensions);
        this.mHeaders = HandshakeBuilder.copyHeaders(source.mHeaders);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addProtocol(String protocol) {
        if (!HandshakeBuilder.isValidProtocol(protocol)) {
            throw new IllegalArgumentException("'protocol' must be a non-empty string with characters in the range U+0021 to U+007E not including separator characters.");
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mProtocols == null) {
                this.mProtocols = new LinkedHashSet<String>();
            }
            this.mProtocols.add(protocol);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeProtocol(String protocol) {
        if (protocol == null) {
            return;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mProtocols == null) {
                return;
            }
            this.mProtocols.remove(protocol);
            if (this.mProtocols.size() == 0) {
                this.mProtocols = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearProtocols() {
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            this.mProtocols = null;
        }
    }

    private static boolean isValidProtocol(String protocol) {
        if (protocol == null || protocol.length() == 0) {
            return false;
        }
        int len = protocol.length();
        for (int i2 = 0; i2 < len; ++i2) {
            char ch = protocol.charAt(i2);
            if (ch >= '!' && '~' >= ch && !Token.isSeparator(ch)) continue;
            return false;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean containsProtocol(String protocol) {
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mProtocols == null) {
                return false;
            }
            return this.mProtocols.contains(protocol);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addExtension(WebSocketExtension extension) {
        if (extension == null) {
            return;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mExtensions == null) {
                this.mExtensions = new ArrayList<WebSocketExtension>();
            }
            this.mExtensions.add(extension);
        }
    }

    public void addExtension(String extension) {
        this.addExtension(WebSocketExtension.parse(extension));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeExtension(WebSocketExtension extension) {
        if (extension == null) {
            return;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mExtensions == null) {
                return;
            }
            this.mExtensions.remove(extension);
            if (this.mExtensions.size() == 0) {
                this.mExtensions = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeExtensions(String name) {
        if (name == null) {
            return;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mExtensions == null) {
                return;
            }
            ArrayList<WebSocketExtension> extensionsToRemove = new ArrayList<WebSocketExtension>();
            for (WebSocketExtension extension : this.mExtensions) {
                if (!extension.getName().equals(name)) continue;
                extensionsToRemove.add(extension);
            }
            for (WebSocketExtension extension : extensionsToRemove) {
                this.mExtensions.remove(extension);
            }
            if (this.mExtensions.size() == 0) {
                this.mExtensions = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearExtensions() {
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            this.mExtensions = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean containsExtension(WebSocketExtension extension) {
        if (extension == null) {
            return false;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mExtensions == null) {
                return false;
            }
            return this.mExtensions.contains(extension);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean containsExtension(String name) {
        if (name == null) {
            return false;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mExtensions == null) {
                return false;
            }
            for (WebSocketExtension extension : this.mExtensions) {
                if (!extension.getName().equals(name)) continue;
                return true;
            }
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addHeader(String name, String value) {
        if (name == null || name.length() == 0) {
            return;
        }
        if (value == null) {
            value = "";
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mHeaders == null) {
                this.mHeaders = new ArrayList<String[]>();
            }
            this.mHeaders.add(new String[]{name, value});
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeHeaders(String name) {
        if (name == null || name.length() == 0) {
            return;
        }
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            if (this.mHeaders == null) {
                return;
            }
            ArrayList<String[]> headersToRemove = new ArrayList<String[]>();
            for (String[] header : this.mHeaders) {
                if (!header[0].equals(name)) continue;
                headersToRemove.add(header);
            }
            for (String[] header : headersToRemove) {
                this.mHeaders.remove(header);
            }
            if (this.mHeaders.size() == 0) {
                this.mHeaders = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearHeaders() {
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            this.mHeaders = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setUserInfo(String userInfo) {
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            this.mUserInfo = userInfo;
        }
    }

    public void setUserInfo(String id2, String password) {
        if (id2 == null) {
            id2 = "";
        }
        if (password == null) {
            password = "";
        }
        String userInfo = String.format("%s:%s", id2, password);
        this.setUserInfo(userInfo);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearUserInfo() {
        HandshakeBuilder handshakeBuilder = this;
        synchronized (handshakeBuilder) {
            this.mUserInfo = null;
        }
    }

    public URI getURI() {
        return this.mUri;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public String buildRequestLine() {
        return String.format("GET %s HTTP/1.1", this.mPath);
    }

    public List<String[]> buildHeaders() {
        ArrayList<String[]> headers = new ArrayList<String[]>();
        headers.add(new String[]{"Host", this.mHost});
        headers.add(CONNECTION_HEADER);
        headers.add(UPGRADE_HEADER);
        headers.add(VERSION_HEADER);
        headers.add(new String[]{"Sec-WebSocket-Key", this.mKey});
        if (this.mProtocols != null && this.mProtocols.size() != 0) {
            headers.add(new String[]{"Sec-WebSocket-Protocol", Misc.join(this.mProtocols, ", ")});
        }
        if (this.mExtensions != null && this.mExtensions.size() != 0) {
            headers.add(new String[]{"Sec-WebSocket-Extensions", Misc.join(this.mExtensions, ", ")});
        }
        if (this.mUserInfo != null && this.mUserInfo.length() != 0) {
            headers.add(new String[]{"Authorization", "Basic " + Base64.encode(this.mUserInfo)});
        }
        if (this.mHeaders != null && this.mHeaders.size() != 0) {
            headers.addAll(this.mHeaders);
        }
        return headers;
    }

    public static String build(String requestLine, List<String[]> headers) {
        StringBuilder builder = new StringBuilder();
        builder.append(requestLine).append(RN);
        for (String[] header : headers) {
            builder.append(header[0]).append(": ").append(header[1]).append(RN);
        }
        builder.append(RN);
        return builder.toString();
    }

    private static Set<String> copyProtocols(Set<String> protocols) {
        if (protocols == null) {
            return null;
        }
        LinkedHashSet<String> newProtocols = new LinkedHashSet<String>(protocols.size());
        newProtocols.addAll(protocols);
        return newProtocols;
    }

    private static List<WebSocketExtension> copyExtensions(List<WebSocketExtension> extensions) {
        if (extensions == null) {
            return null;
        }
        ArrayList<WebSocketExtension> newExtensions = new ArrayList<WebSocketExtension>(extensions.size());
        for (WebSocketExtension extension : extensions) {
            newExtensions.add(new WebSocketExtension(extension));
        }
        return newExtensions;
    }

    private static List<String[]> copyHeaders(List<String[]> headers) {
        if (headers == null) {
            return null;
        }
        ArrayList<String[]> newHeaders = new ArrayList<String[]>(headers.size());
        for (String[] header : headers) {
            newHeaders.add(HandshakeBuilder.copyHeader(header));
        }
        return newHeaders;
    }

    private static String[] copyHeader(String[] header) {
        String[] newHeader = new String[]{header[0], header[1]};
        return newHeader;
    }
}

