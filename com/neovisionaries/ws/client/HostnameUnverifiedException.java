/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import javax.net.ssl.SSLSocket;

public class HostnameUnverifiedException
extends WebSocketException {
    private static final long serialVersionUID = 1L;
    private final SSLSocket mSSLSocket;
    private final String mHostname;

    public HostnameUnverifiedException(SSLSocket socket, String hostname) {
        super(WebSocketError.HOSTNAME_UNVERIFIED, String.format("The certificate of the peer%s does not match the expected hostname (%s)", HostnameUnverifiedException.stringifyPrincipal(socket), hostname));
        this.mSSLSocket = socket;
        this.mHostname = hostname;
    }

    private static String stringifyPrincipal(SSLSocket socket) {
        try {
            return String.format(" (%s)", socket.getSession().getPeerPrincipal().toString());
        }
        catch (Exception e2) {
            return "";
        }
    }

    public SSLSocket getSSLSocket() {
        return this.mSSLSocket;
    }

    public String getHostname() {
        return this.mHostname;
    }
}

