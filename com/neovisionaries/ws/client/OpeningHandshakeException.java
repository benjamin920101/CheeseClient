/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.StatusLine;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import java.util.List;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OpeningHandshakeException
extends WebSocketException {
    private static final long serialVersionUID = 1L;
    private final StatusLine mStatusLine;
    private final Map<String, List<String>> mHeaders;
    private final byte[] mBody;

    OpeningHandshakeException(WebSocketError error, String message, StatusLine statusLine, Map<String, List<String>> headers) {
        this(error, message, statusLine, headers, null);
    }

    OpeningHandshakeException(WebSocketError error, String message, StatusLine statusLine, Map<String, List<String>> headers, byte[] body) {
        super(error, message);
        this.mStatusLine = statusLine;
        this.mHeaders = headers;
        this.mBody = body;
    }

    public StatusLine getStatusLine() {
        return this.mStatusLine;
    }

    public Map<String, List<String>> getHeaders() {
        return this.mHeaders;
    }

    public byte[] getBody() {
        return this.mBody;
    }
}

