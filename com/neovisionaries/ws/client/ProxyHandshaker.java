/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Base64;
import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.ProxySettings;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

class ProxyHandshaker {
    private static final String RN = "\r\n";
    private final String mHost;
    private final int mPort;
    private final ProxySettings mSettings;

    public ProxyHandshaker(String host, int port, ProxySettings settings) {
        this.mHost = host;
        this.mPort = port;
        this.mSettings = settings;
    }

    public void perform(Socket socket) throws IOException {
        this.sendRequest(socket);
        this.receiveResponse(socket);
    }

    private void sendRequest(Socket socket) throws IOException {
        String request = this.buildRequest();
        byte[] requestBytes = Misc.getBytesUTF8(request);
        OutputStream output = socket.getOutputStream();
        output.write(requestBytes);
        output.flush();
    }

    private String buildRequest() {
        String host = String.format("%s:%d", this.mHost, this.mPort);
        StringBuilder builder = new StringBuilder().append("CONNECT ").append(host).append(" HTTP/1.1").append(RN).append("Host: ").append(host).append(RN);
        this.addHeaders(builder);
        this.addProxyAuthorization(builder);
        return builder.append(RN).toString();
    }

    private void addHeaders(StringBuilder builder) {
        for (Map.Entry<String, List<String>> header : this.mSettings.getHeaders().entrySet()) {
            String name = header.getKey();
            for (String value : header.getValue()) {
                if (value == null) {
                    value = "";
                }
                builder.append(name).append(": ").append(value).append(RN);
            }
        }
    }

    private void addProxyAuthorization(StringBuilder builder) {
        String id2 = this.mSettings.getId();
        if (id2 == null || id2.length() == 0) {
            return;
        }
        String password = this.mSettings.getPassword();
        if (password == null) {
            password = "";
        }
        String credentials = String.format("%s:%s", id2, password);
        builder.append("Proxy-Authorization: Basic ").append(Base64.encode(credentials)).append(RN);
    }

    private void receiveResponse(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        this.readStatusLine(input);
        this.skipHeaders(input);
    }

    private void readStatusLine(InputStream input) throws IOException {
        String statusLine = Misc.readLine(input, "UTF-8");
        if (statusLine == null || statusLine.length() == 0) {
            throw new IOException("The response from the proxy server does not contain a status line.");
        }
        String[] elements = statusLine.split(" +", 3);
        if (elements.length < 2) {
            throw new IOException("The status line in the response from the proxy server is badly formatted. The status line is: " + statusLine);
        }
        if (!"200".equals(elements[1])) {
            throw new IOException("The status code in the response from the proxy server is not '200 Connection established'. The status line is: " + statusLine);
        }
    }

    private void skipHeaders(InputStream input) throws IOException {
        int count = 0;
        while (true) {
            int ch;
            if ((ch = input.read()) == -1) {
                throw new EOFException("The end of the stream from the proxy server was reached unexpectedly.");
            }
            if (ch == 10) {
                if (count == 0) {
                    return;
                }
                count = 0;
                continue;
            }
            if (ch != 13) {
                ++count;
                continue;
            }
            ch = input.read();
            if (ch == -1) {
                throw new EOFException("The end of the stream from the proxy server was reached unexpectedly after a carriage return.");
            }
            if (ch != 10) {
                count += 2;
                continue;
            }
            if (count == 0) {
                return;
            }
            count = 0;
        }
    }

    String getProxiedHostname() {
        return this.mHost;
    }
}

