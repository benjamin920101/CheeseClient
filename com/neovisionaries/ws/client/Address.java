/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

class Address {
    private final String mHost;
    private final int mPort;
    private transient String mString;

    Address(String host, int port) {
        this.mHost = host;
        this.mPort = port;
    }

    String getHostname() {
        return this.mHost;
    }

    int getPort() {
        return this.mPort;
    }

    public String toString() {
        if (this.mString == null) {
            this.mString = String.format("%s:%d", this.mHost, this.mPort);
        }
        return this.mString;
    }
}

