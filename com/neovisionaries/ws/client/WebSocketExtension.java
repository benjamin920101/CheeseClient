/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.PerMessageDeflateExtension;
import com.neovisionaries.ws.client.Token;
import com.neovisionaries.ws.client.WebSocketException;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WebSocketExtension {
    public static final String PERMESSAGE_DEFLATE = "permessage-deflate";
    private final String mName;
    private final Map<String, String> mParameters;

    public WebSocketExtension(String name) {
        if (!Token.isValid(name)) {
            throw new IllegalArgumentException("'name' is not a valid token.");
        }
        this.mName = name;
        this.mParameters = new LinkedHashMap<String, String>();
    }

    public WebSocketExtension(WebSocketExtension source) {
        if (source == null) {
            throw new IllegalArgumentException("'source' is null.");
        }
        this.mName = source.getName();
        this.mParameters = new LinkedHashMap<String, String>(source.getParameters());
    }

    public String getName() {
        return this.mName;
    }

    public Map<String, String> getParameters() {
        return this.mParameters;
    }

    public boolean containsParameter(String key) {
        return this.mParameters.containsKey(key);
    }

    public String getParameter(String key) {
        return this.mParameters.get(key);
    }

    public WebSocketExtension setParameter(String key, String value) {
        if (!Token.isValid(key)) {
            throw new IllegalArgumentException("'key' is not a valid token.");
        }
        if (value != null && !Token.isValid(value)) {
            throw new IllegalArgumentException("'value' is not a valid token.");
        }
        this.mParameters.put(key, value);
        return this;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(this.mName);
        for (Map.Entry<String, String> entry : this.mParameters.entrySet()) {
            builder.append("; ").append(entry.getKey());
            String value = entry.getValue();
            if (value == null || value.length() == 0) continue;
            builder.append("=").append(value);
        }
        return builder.toString();
    }

    void validate() throws WebSocketException {
    }

    public static WebSocketExtension parse(String string) {
        if (string == null) {
            return null;
        }
        String[] elements = string.trim().split("\\s*;\\s*");
        if (elements.length == 0) {
            return null;
        }
        String name = elements[0];
        if (!Token.isValid(name)) {
            return null;
        }
        WebSocketExtension extension = WebSocketExtension.createInstance(name);
        for (int i2 = 1; i2 < elements.length; ++i2) {
            String value;
            String key;
            String[] pair = elements[i2].split("\\s*=\\s*", 2);
            if (pair.length == 0 || pair[0].length() == 0 || !Token.isValid(key = pair[0]) || (value = WebSocketExtension.extractValue(pair)) != null && !Token.isValid(value)) continue;
            extension.setParameter(key, value);
        }
        return extension;
    }

    private static String extractValue(String[] pair) {
        if (pair.length != 2) {
            return null;
        }
        return Token.unquote(pair[1]);
    }

    private static WebSocketExtension createInstance(String name) {
        if (PERMESSAGE_DEFLATE.equals(name)) {
            return new PerMessageDeflateExtension(name);
        }
        return new WebSocketExtension(name);
    }
}

