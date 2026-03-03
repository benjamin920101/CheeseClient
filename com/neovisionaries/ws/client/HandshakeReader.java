/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Base64;
import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.OpeningHandshakeException;
import com.neovisionaries.ws.client.PerMessageCompressionExtension;
import com.neovisionaries.ws.client.StatusLine;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class HandshakeReader {
    private static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private final WebSocket mWebSocket;

    public HandshakeReader(WebSocket websocket) {
        this.mWebSocket = websocket;
    }

    public Map<String, List<String>> readHandshake(WebSocketInputStream input, String key) throws WebSocketException {
        StatusLine statusLine = this.readStatusLine(input);
        Map<String, List<String>> headers = this.readHttpHeaders(input);
        this.validateStatusLine(statusLine, headers, input);
        this.validateUpgrade(statusLine, headers);
        this.validateConnection(statusLine, headers);
        this.validateAccept(statusLine, headers, key);
        this.validateExtensions(statusLine, headers);
        this.validateProtocol(statusLine, headers);
        return headers;
    }

    private StatusLine readStatusLine(WebSocketInputStream input) throws WebSocketException {
        String line;
        try {
            line = input.readLine();
        }
        catch (IOException e2) {
            throw new WebSocketException(WebSocketError.OPENING_HANDSHAKE_RESPONSE_FAILURE, "Failed to read an opening handshake response from the server: " + e2.getMessage(), e2);
        }
        if (line == null || line.length() == 0) {
            throw new WebSocketException(WebSocketError.STATUS_LINE_EMPTY, "The status line of the opening handshake response is empty.");
        }
        try {
            return new StatusLine(line);
        }
        catch (Exception e3) {
            throw new WebSocketException(WebSocketError.STATUS_LINE_BAD_FORMAT, "The status line of the opening handshake response is badly formatted. The status line is: " + line);
        }
    }

    private Map<String, List<String>> readHttpHeaders(WebSocketInputStream input) throws WebSocketException {
        TreeMap<String, List<String>> headers = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
        StringBuilder builder = null;
        while (true) {
            String line;
            try {
                line = input.readLine();
            }
            catch (IOException e2) {
                throw new WebSocketException(WebSocketError.HTTP_HEADER_FAILURE, "An error occurred while HTTP header section was being read: " + e2.getMessage(), e2);
            }
            if (line == null || line.length() == 0) {
                if (builder == null) break;
                this.parseHttpHeader(headers, builder.toString());
                break;
            }
            char ch = line.charAt(0);
            if (ch == ' ' || ch == '\t') {
                if (builder == null) continue;
                line = line.replaceAll("^[ \t]+", " ");
                builder.append(line);
                continue;
            }
            if (builder != null) {
                this.parseHttpHeader(headers, builder.toString());
            }
            builder = new StringBuilder(line);
        }
        return headers;
    }

    private void parseHttpHeader(Map<String, List<String>> headers, String header) {
        String[] pair = header.split(":", 2);
        if (pair.length < 2) {
            return;
        }
        String name = pair[0].trim();
        String value = pair[1].trim();
        List<String> list = headers.get(name);
        if (list == null) {
            list = new ArrayList<String>();
            headers.put(name, list);
        }
        list.add(value);
    }

    private void validateStatusLine(StatusLine statusLine, Map<String, List<String>> headers, WebSocketInputStream input) throws WebSocketException {
        if (statusLine.getStatusCode() == 101) {
            return;
        }
        byte[] body = this.readBody(headers, input);
        throw new OpeningHandshakeException(WebSocketError.NOT_SWITCHING_PROTOCOLS, "The status code of the opening handshake response is not '101 Switching Protocols'. The status line is: " + statusLine, statusLine, headers, body);
    }

    private byte[] readBody(Map<String, List<String>> headers, WebSocketInputStream input) {
        int length = this.getContentLength(headers);
        if (length <= 0) {
            return null;
        }
        try {
            byte[] body = new byte[length];
            input.readBytes(body, length);
            return body;
        }
        catch (Throwable t2) {
            return null;
        }
    }

    private int getContentLength(Map<String, List<String>> headers) {
        try {
            return Integer.parseInt(headers.get("Content-Length").get(0));
        }
        catch (Exception e2) {
            return -1;
        }
    }

    private void validateUpgrade(StatusLine statusLine, Map<String, List<String>> headers) throws WebSocketException {
        List<String> values = headers.get("Upgrade");
        if (values == null || values.size() == 0) {
            throw new OpeningHandshakeException(WebSocketError.NO_UPGRADE_HEADER, "The opening handshake response does not contain 'Upgrade' header.", statusLine, headers);
        }
        for (String value : values) {
            String[] elements;
            for (String element : elements = value.split("\\s*,\\s*")) {
                if (!"websocket".equalsIgnoreCase(element)) continue;
                return;
            }
        }
        throw new OpeningHandshakeException(WebSocketError.NO_WEBSOCKET_IN_UPGRADE_HEADER, "'websocket' was not found in 'Upgrade' header.", statusLine, headers);
    }

    private void validateConnection(StatusLine statusLine, Map<String, List<String>> headers) throws WebSocketException {
        List<String> values = headers.get("Connection");
        if (values == null || values.size() == 0) {
            throw new OpeningHandshakeException(WebSocketError.NO_CONNECTION_HEADER, "The opening handshake response does not contain 'Connection' header.", statusLine, headers);
        }
        for (String value : values) {
            String[] elements;
            for (String element : elements = value.split("\\s*,\\s*")) {
                if (!"Upgrade".equalsIgnoreCase(element)) continue;
                return;
            }
        }
        throw new OpeningHandshakeException(WebSocketError.NO_UPGRADE_IN_CONNECTION_HEADER, "'Upgrade' was not found in 'Connection' header.", statusLine, headers);
    }

    private void validateAccept(StatusLine statusLine, Map<String, List<String>> headers, String key) throws WebSocketException {
        String expected;
        List<String> values = headers.get("Sec-WebSocket-Accept");
        if (values == null) {
            throw new OpeningHandshakeException(WebSocketError.NO_SEC_WEBSOCKET_ACCEPT_HEADER, "The opening handshake response does not contain 'Sec-WebSocket-Accept' header.", statusLine, headers);
        }
        String actual = values.get(0);
        String input = key + ACCEPT_MAGIC;
        try {
            MessageDigest md2 = MessageDigest.getInstance("SHA-1");
            byte[] digest = md2.digest(Misc.getBytesUTF8(input));
            expected = Base64.encode(digest);
        }
        catch (Exception e2) {
            return;
        }
        if (!expected.equals(actual)) {
            throw new OpeningHandshakeException(WebSocketError.UNEXPECTED_SEC_WEBSOCKET_ACCEPT_HEADER, "The value of 'Sec-WebSocket-Accept' header is different from the expected one.", statusLine, headers);
        }
    }

    private void validateExtensions(StatusLine statusLine, Map<String, List<String>> headers) throws WebSocketException {
        List<String> values = headers.get("Sec-WebSocket-Extensions");
        if (values == null || values.size() == 0) {
            return;
        }
        ArrayList<WebSocketExtension> extensions = new ArrayList<WebSocketExtension>();
        for (String value : values) {
            String[] elements;
            for (String element : elements = value.split("\\s*,\\s*")) {
                WebSocketExtension extension = WebSocketExtension.parse(element);
                if (extension == null) {
                    throw new OpeningHandshakeException(WebSocketError.EXTENSION_PARSE_ERROR, "The value in 'Sec-WebSocket-Extensions' failed to be parsed: " + element, statusLine, headers);
                }
                String name = extension.getName();
                if (!this.mWebSocket.getHandshakeBuilder().containsExtension(name)) {
                    throw new OpeningHandshakeException(WebSocketError.UNSUPPORTED_EXTENSION, "The extension contained in the Sec-WebSocket-Extensions header is not supported: " + name, statusLine, headers);
                }
                extension.validate();
                extensions.add(extension);
            }
        }
        this.validateExtensionCombination(statusLine, headers, extensions);
        this.mWebSocket.setAgreedExtensions(extensions);
    }

    private void validateExtensionCombination(StatusLine statusLine, Map<String, List<String>> headers, List<WebSocketExtension> extensions) throws WebSocketException {
        WebSocketExtension pmce = null;
        for (WebSocketExtension extension : extensions) {
            if (!(extension instanceof PerMessageCompressionExtension)) continue;
            if (pmce == null) {
                pmce = extension;
                continue;
            }
            String message = String.format("'%s' extension and '%s' extension conflict with each other.", pmce.getName(), extension.getName());
            throw new OpeningHandshakeException(WebSocketError.EXTENSIONS_CONFLICT, message, statusLine, headers);
        }
    }

    private void validateProtocol(StatusLine statusLine, Map<String, List<String>> headers) throws WebSocketException {
        List<String> values = headers.get("Sec-WebSocket-Protocol");
        if (values == null) {
            return;
        }
        String protocol = values.get(0);
        if (protocol == null || protocol.length() == 0) {
            return;
        }
        if (!this.mWebSocket.getHandshakeBuilder().containsProtocol(protocol)) {
            throw new OpeningHandshakeException(WebSocketError.UNSUPPORTED_PROTOCOL, "The protocol contained in the Sec-WebSocket-Protocol header is not supported: " + protocol, statusLine, headers);
        }
        this.mWebSocket.setAgreedProtocol(protocol);
    }
}

