/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.IOException;

public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    private final String protocol;

    private Protocol(String protocol) {
        this.protocol = protocol;
    }

    public static Protocol get(String protocol) throws IOException {
        if (protocol.equals(Protocol.HTTP_1_0.protocol)) {
            return HTTP_1_0;
        }
        if (protocol.equals(Protocol.HTTP_1_1.protocol)) {
            return HTTP_1_1;
        }
        if (protocol.equals(Protocol.H2_PRIOR_KNOWLEDGE.protocol)) {
            return H2_PRIOR_KNOWLEDGE;
        }
        if (protocol.equals(Protocol.HTTP_2.protocol)) {
            return HTTP_2;
        }
        if (protocol.equals(Protocol.SPDY_3.protocol)) {
            return SPDY_3;
        }
        if (protocol.equals(Protocol.QUIC.protocol)) {
            return QUIC;
        }
        throw new IOException("Unexpected protocol: " + protocol);
    }

    public String toString() {
        return this.protocol;
    }
}

